package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.SoundUtils;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen implements Screen, InputProcessor {
    final Squirgle game;

    public static float INIT_PROMPT_RADIUS;
    public static float BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT;
    public static float BACKGROUND_COLOR_SHAPE_LIST_WIDTH;
    public static float INPUT_RADIUS;
    public static float TARGET_RADIUS;
    public static float PAUSE_INPUT_WIDTH;
    public static float PAUSE_INPUT_HEIGHT;
    public static Vector2 INPUT_POINT_SPAWN;
    public static Vector2 INPUT_LINE_SPAWN;
    public static Vector2 INPUT_TRIANGLE_SPAWN;
    public static Vector2 INPUT_SQUARE_SPAWN;
    public static Vector2 INPUT_PENTAGON_SPAWN;
    public static Vector2 INPUT_HEXAGON_SPAWN;
    public static Vector2 INPUT_SEPTAGON_SPAWN;
    public static Vector2 INPUT_OCTAGON_SPAWN;
    public static Vector2 INPUT_NONAGON_SPAWN;
    public static Vector2 INPUT_PLAY_SPAWN;
    public static Vector2 INPUT_HOME_SPAWN;
    public static Vector2 INPUT_EXIT_SPAWN;

    private final static int PAUSE_BACK = 0;
    private final static int PAUSE_QUIT = 1;

    private final static int END_LINE_WIDTH_INCREASE = 2;
    private final static int NUM_MUSIC_PHASES = 3;

    private float promptIncrease;
    private float equationWidth;
    private Shape promptShape;
    private Shape lastShapeTouched;
    private Shape lastPromptShape;
    private Shape outsideTargetShape;
    private List<Shape> priorShapeList;
    private List<Shape> targetShapeList;
    private List<Shape> backgroundColorShapeList;
    private List<Shape> touchDownShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShape;
    private Shape lastTargetShape;
    private int targetShapesMatched;
    private Vector3 touchPoint;
    boolean pointTouched;
    boolean lineTouched;
    boolean triangleTouched;
    boolean squareTouched;
    boolean pentagonTouched;
    boolean hexagonTouched;
    boolean septagonTouched;
    boolean octagonTouched;
    boolean nonagonTouched;
    boolean playTouched;
    boolean homeTouched;
    boolean exitTouched;
    boolean pauseTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    boolean inputTouchedGameplay;
    boolean inputTouchedResults;
    private Color clearColor;
    private int score;
    private boolean gameOver;
    private boolean showResults;
    private boolean paused;
    private int multiplier;
    private long startTime;
    private long endTime;
    private long pauseStartTime;
    private long timePaused;
    private int destructionIndex;
    private float firstPriorShapePreviousX;
    private Color resultsColor;
    Shape primaryShape;
    float primaryShapeThreshold;
    boolean primaryShapeAtThreshold;

    public GameplayScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFont(MathUtils.round(game.camera.viewportWidth / 11.1f));

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        setUpNonFinalNonStaticData();

        playMusic();
    }

    @Override
    public void render(float delta) {
        setUpGL();

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        primaryShapeThreshold = game.widthOrHeight * game.draw.THRESHOLD_MULTIPLIER;
        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;

        if(!paused) {
            if(!gameOver) {
                promptShape.setRadius(promptShape.getRadius() + (promptIncrease / 2));
            } else {
                promptShape.setRadius(promptShape.getRadius() * promptIncrease);
            }

            if (!primaryShapeAtThreshold) {
                promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
            }

            if (!gameOver) {
                game.draw.drawBackgroundColorShape(backgroundColorShape, game.shapeRendererFilled);
            }

            game.draw.drawPrompt(promptShape, priorShapeList, backgroundColorShape, false, game.shapeRendererFilled);

            game.draw.drawShapes(priorShapeList, promptShape, primaryShapeAtThreshold, game.shapeRendererFilled);

            //TODO: Make sure you execute the inverse of this given certain player input...Also adjust all of this
            //TODO: so that the promptIncrease, colorListSpeed and colorSpeed are all increasing by proportional rates.
            if (!gameOver) {
                if ((System.currentTimeMillis() - startTime - timePaused) / 1000 > 10) {
                    startTime = System.currentTimeMillis();
                    game.draw.setColorListSpeed(game.draw.getColorListSpeed() + 0.1f);
                    game.draw.setColorSpeed(game.draw.getColorSpeed() + 20);
                    promptIncrease = (game.widthOrHeight * (game.draw.getColorListSpeed() / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))) / 2;
                }
            } else {
                if ((System.currentTimeMillis() - endTime) / 1000 > 2) {
                    if (!primaryShapeAtThreshold) { //TODO: Also account for height (different screen orientations?)
                        promptIncrease += .0001;
                        promptShape.setCoordinates(new Vector2(promptShape.getCoordinates().x - (primaryShape.getCoordinates().x - firstPriorShapePreviousX),
                                promptShape.getCoordinates().y));
                    } else {
                        promptIncrease = 1;
                        if (primaryShape.getShape() == Shape.LINE && primaryShape.getLineWidth() < (game.camera.viewportWidth * 4)) {
                            primaryShape.setLineWidth(primaryShape.getLineWidth() * END_LINE_WIDTH_INCREASE);
                        } else if (primaryShape.getShape() == Shape.LINE) {
                            //Prevent shape lines from being visible in the event that primaryShape is promptShape
                            primaryShape.setColor(clearColor);
                        }
                        if (primaryShape.getShape() != Shape.LINE || primaryShape.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                            showResults = true;
                        }
                    }
                }
            }

            //This code is being executed three times: once before setting the prompt's end game coordinates, and again afterwards.
            //This way, the shapes are drawn with their new values, and the first element in priorShapeList doesn't veer off
            //the screen to the right.
            //TODO: separate draw methods out into distinct ones, one of which assigns radii and coordinates, and the other of
            //TODO: which actually draws the shapes. It's overkill to draw the shapes multiple times.
            game.draw.drawShapes(priorShapeList, promptShape, primaryShapeAtThreshold, game.shapeRendererFilled);

            if (!gameOver) {
                game.draw.drawPerimeter(promptShape, game.shapeRendererLine);
                game.draw.drawBackgroundColorShapeList(backgroundColorShapeList, backgroundColorShape, clearColor, game.shapeRendererFilled);
                game.draw.drawTimelines(promptShape, backgroundColorShapeList, game.shapeRendererFilled);
                SoundUtils.playMusic(promptShape, game);
                game.draw.drawTargetSemicircle(game.shapeRendererFilled);
                if(equationWidth > 0) {
                    game.draw.drawEquation(lastShapeTouched, lastPromptShape, lastTargetShape, equationWidth, game.shapeRendererFilled);
                    equationWidth -= INPUT_RADIUS / 60;
                } else {
                    equationWidth = 0;
                }
                //TODO: Maybe remove input buttons when on Desktop, as input will be number key oriented?
                game.draw.drawInputButtons(game, game.shapeRendererFilled);
                game.draw.drawScoreTriangle(game.shapeRendererFilled);
                game.draw.drawPrompt(outsideTargetShape, targetShapeList, backgroundColorShape, true, game.shapeRendererFilled);
                game.draw.drawShapes(targetShapeList, outsideTargetShape, false, game.shapeRendererFilled);
                game.draw.drawPauseInput(game);
            }

            //Prevent shapes from getting too large
            if (promptShape.getRadius() >= game.camera.viewportWidth * 15) {
                if (priorShapeList.size() > destructionIndex) {
                    promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                    for (int i = 0; i < destructionIndex; i++) {
                        priorShapeList.remove(priorShapeList.size() - 1);
                    }
                    destructionIndex = 2;
                } else {
                    //TODO: Account for game ending with empty priorShapeList (maybe?)
                }
            }

            if (priorShapeList.size() > 0) {
                firstPriorShapePreviousX = priorShapeList.get(0).getCoordinates().x;
            } else {
                firstPriorShapePreviousX = promptShape.getCoordinates().x;
            }

            if (showResults) {
                game.draw.drawResultsInputButtons(INPUT_PLAY_SPAWN, INPUT_HOME_SPAWN, INPUT_EXIT_SPAWN, game.shapeRendererFilled);
            }

            game.draw.drawTouchDownPoints(touchDownShapeList, game.shapeRendererLine);
        } else {
            drawInputRectangles();
        }

        game.shapeRendererFilled.end();

        game.shapeRendererLine.end();

        if(!paused) {
            if (!gameOver) {
                com.screenlooker.squirgle.util.FontUtils.printText(game.batch,
                        game.font,
                        game.layout,
                        backgroundColorShape.getColor(),
                        String.valueOf(score),
                        game.camera.viewportWidth - (TARGET_RADIUS / 3.16f),
                        game.camera.viewportHeight - (TARGET_RADIUS / 3.16f),
                        -45);
                com.screenlooker.squirgle.util.FontUtils.printText(game.batch,
                        game.font,
                        game.layout,
                        Color.WHITE,
                        "X" + String.valueOf(multiplier),
                        game.camera.viewportWidth - (TARGET_RADIUS / 2.68f),
                        game.camera.viewportHeight - (TARGET_RADIUS / 1.25f),
                        -45);
            }

            if (showResults) {
                if (priorShapeList.size() > 0 && (priorShapeList.get(0).getShape() == Shape.LINE || priorShapeList.get(0).getShape() == Shape.POINT)) {
                    resultsColor = Color.BLACK;
                } else {
                    resultsColor = Color.WHITE;
                }
                FontUtils.printText(game.batch,
                        game.font,
                        game.layout,
                        resultsColor,
                        String.valueOf(score),
                        game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2,
                        0);
            }
        }

        //Game over condition
        if ((promptShape.getRadius() >= game.camera.viewportWidth / 2 || promptShape.getRadius() >= game.camera.viewportHeight / 2) && !gameOver) {
            gameOver = true;
            stopMusic();
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
        }

        //Transition color to distinguish current target shape from other
        ColorUtils.transitionColor(currentTargetShape);
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //TODO: Maybe remove gameOver check once I add animations to screen switches
        if (button != Input.Buttons.LEFT || pointer > 0 || gameOver) {
            return false;
        }

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        pointTouched = touchPoint.x > INPUT_POINT_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_POINT_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_POINT_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_POINT_SPAWN.y + INPUT_RADIUS;
        lineTouched = touchPoint.x > INPUT_LINE_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_LINE_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_LINE_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_LINE_SPAWN.y + INPUT_RADIUS;
        triangleTouched = touchPoint.x > INPUT_TRIANGLE_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_TRIANGLE_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_TRIANGLE_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_TRIANGLE_SPAWN.y + INPUT_RADIUS;
        squareTouched = touchPoint.x > INPUT_SQUARE_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_SQUARE_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_SQUARE_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_SQUARE_SPAWN.y + INPUT_RADIUS;
        pentagonTouched = touchPoint.x > INPUT_PENTAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_PENTAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_PENTAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_PENTAGON_SPAWN.y + INPUT_RADIUS;
        hexagonTouched = touchPoint.x > INPUT_HEXAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_HEXAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_HEXAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_HEXAGON_SPAWN.y + INPUT_RADIUS;
        septagonTouched = touchPoint.x > INPUT_SEPTAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_SEPTAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_SEPTAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_SEPTAGON_SPAWN.y + INPUT_RADIUS;
        octagonTouched = touchPoint.x > INPUT_OCTAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_OCTAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_OCTAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_OCTAGON_SPAWN.y + INPUT_RADIUS;
        nonagonTouched = touchPoint.x > INPUT_NONAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_NONAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_NONAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_NONAGON_SPAWN.y + INPUT_RADIUS;
        playTouched = touchPoint.x > INPUT_PLAY_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_PLAY_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_PLAY_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_PLAY_SPAWN.y + INPUT_RADIUS;
        homeTouched = touchPoint.x > INPUT_HOME_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_HOME_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_HOME_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_HOME_SPAWN.y + INPUT_RADIUS;
        exitTouched = touchPoint.x > INPUT_EXIT_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_EXIT_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_EXIT_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_EXIT_SPAWN.y + INPUT_RADIUS;

        for (int i = 1; i < 20; i++) {
            if (!gameOver) {
                if (pointTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_POINT_SPAWN.x,
                                    INPUT_POINT_SPAWN.y)));
                } else if (lineTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_LINE_SPAWN.x,
                                    INPUT_TRIANGLE_SPAWN.y)));
                } else if (triangleTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_TRIANGLE_SPAWN.x,
                                    INPUT_TRIANGLE_SPAWN.y)));
                } else if (squareTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_SQUARE_SPAWN.x,
                                    INPUT_SQUARE_SPAWN.y)));
                } else if(pentagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_PENTAGON_SPAWN.x,
                                    INPUT_PENTAGON_SPAWN.y)));
                } else if(hexagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_HEXAGON_SPAWN.x,
                                    INPUT_HEXAGON_SPAWN.y)));
                } else if(septagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_SEPTAGON_SPAWN.x,
                                    INPUT_SEPTAGON_SPAWN.y)));
                } else if(octagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_OCTAGON_SPAWN.x,
                                    INPUT_OCTAGON_SPAWN.y)));
                } else if(nonagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_NONAGON_SPAWN.x,
                                    INPUT_NONAGON_SPAWN.y)));
                }
            } else if (showResults) {
                if (playTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_PLAY_SPAWN.x,
                                    INPUT_PLAY_SPAWN.y)));
                } else if (homeTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i, com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_HOME_SPAWN.x,
                                    INPUT_HOME_SPAWN.y)));
                } else {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_EXIT_SPAWN.x,
                                    INPUT_EXIT_SPAWN.y)));
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        pointTouched = touchPoint.x > INPUT_POINT_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_POINT_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_POINT_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_POINT_SPAWN.y + INPUT_RADIUS;
        lineTouched = touchPoint.x > INPUT_LINE_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_LINE_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_LINE_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_LINE_SPAWN.y + INPUT_RADIUS;
        triangleTouched = touchPoint.x > INPUT_TRIANGLE_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_TRIANGLE_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_TRIANGLE_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_TRIANGLE_SPAWN.y + INPUT_RADIUS;
        squareTouched = touchPoint.x > INPUT_SQUARE_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_SQUARE_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_SQUARE_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_SQUARE_SPAWN.y + INPUT_RADIUS;
        pentagonTouched = touchPoint.x > INPUT_PENTAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_PENTAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_PENTAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_PENTAGON_SPAWN.y + INPUT_RADIUS;
        hexagonTouched = touchPoint.x > INPUT_HEXAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_HEXAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_HEXAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_HEXAGON_SPAWN.y + INPUT_RADIUS;
        septagonTouched = touchPoint.x > INPUT_SEPTAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_SEPTAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_SEPTAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_SEPTAGON_SPAWN.y + INPUT_RADIUS;
        octagonTouched = touchPoint.x > INPUT_OCTAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_OCTAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_OCTAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_OCTAGON_SPAWN.y + INPUT_RADIUS;
        nonagonTouched = touchPoint.x > INPUT_NONAGON_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_NONAGON_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_NONAGON_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_NONAGON_SPAWN.y + INPUT_RADIUS;
        playTouched = touchPoint.x > INPUT_PLAY_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_PLAY_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_PLAY_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_PLAY_SPAWN.y + INPUT_RADIUS;
        homeTouched = touchPoint.x > INPUT_HOME_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_HOME_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_HOME_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_HOME_SPAWN.y + INPUT_RADIUS;
        exitTouched = touchPoint.x > INPUT_EXIT_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_EXIT_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_EXIT_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_EXIT_SPAWN.y + INPUT_RADIUS;
        //TODO: Set and use variables for these values
        pauseTouched = touchPoint.x > game.camera.viewportWidth - (2 *(game.camera.viewportWidth / 20))
                && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20);
        pauseBackTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.partitionSize + PAUSE_INPUT_WIDTH
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT;
        pauseQuitTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.partitionSize + PAUSE_INPUT_WIDTH
                && touchPoint.y > (2 * game.partitionSize) + PAUSE_INPUT_HEIGHT
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        inputTouchedResults = playTouched || homeTouched || exitTouched;

        handleInput();

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            //TODO: Somehow activate numlock so numpad is always available for use

            pointTouched = keycode == Input.Keys.NUM_1 || keycode == Input.Keys.NUMPAD_1;
            lineTouched = keycode == Input.Keys.NUM_2 || keycode == Input.Keys.NUMPAD_2;
            triangleTouched = keycode == Input.Keys.NUM_3 || keycode == Input.Keys.NUMPAD_3;
            squareTouched = keycode == Input.Keys.NUM_4 || keycode == Input.Keys.NUMPAD_4;
            pentagonTouched = keycode == Input.Keys.NUM_5 || keycode == Input.Keys.NUMPAD_5;
            hexagonTouched = keycode == Input.Keys.NUM_6 || keycode == Input.Keys.NUMPAD_6;
            septagonTouched = keycode == Input.Keys.NUM_7 || keycode == Input.Keys.NUMPAD_7;
            octagonTouched = keycode == Input.Keys.NUM_8 || keycode == Input.Keys.NUMPAD_8;
            nonagonTouched = keycode == Input.Keys.NUM_9 || keycode == Input.Keys.NUMPAD_9;
            playTouched = pointTouched;
            homeTouched = lineTouched;
            exitTouched = triangleTouched;
            //TODO: Set and use variables for these values
            pauseTouched = keycode == Input.Keys.ESCAPE;
            pauseBackTouched = pauseTouched;
            pauseQuitTouched = keycode == Input.Keys.X;
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
            inputTouchedResults = playTouched || homeTouched || exitTouched;

            handleInput();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void drawInputRectangles() {
        drawPauseBackInput();
        drawPauseQuitInput();
    }

    public void drawInputRectangle(int placement) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case PAUSE_BACK : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT);
            }
            case PAUSE_QUIT : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        (2 * game.partitionSize) + PAUSE_INPUT_HEIGHT,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT);
            }
        }
    }

    public void drawPauseQuitInput() {
        drawInputRectangle(PAUSE_QUIT);
        game.draw.drawX(game.camera.viewportWidth / 2,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                game.camera.viewportHeight / 4,
                Squirgle.LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawPauseBackInput() {
        drawInputRectangle(PAUSE_BACK);
        game.draw.drawBackButton(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 4,
                game.camera.viewportHeight / 4,
                Squirgle.LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void playMusic() {
        for(int i = 0; i < NUM_MUSIC_PHASES; i++) {
            game.trackMap.get(game.track).get(i).play();
        }
    }

    public void stopMusic() {
        for(int i = 0; i < NUM_MUSIC_PHASES; i++) {
            game.trackMap.get(game.track).get(i).stop();
        }
    }

    public void handleInput() {
        if(!paused) {
            if (!gameOver) {
                if (pointTouched) {
                    lastShapeTouched.setShape(Shape.POINT);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 1 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 1) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 1);
                    }
                } else if (lineTouched) {
                    lastShapeTouched.setShape(Shape.LINE);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 2 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 2) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 2);
                    }
                } else if (triangleTouched) {
                    lastShapeTouched.setShape(Shape.TRIANGLE);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 3 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 3) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 3);
                    }
                } else if (squareTouched) {
                    lastShapeTouched.setShape(Shape.SQUARE);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 4 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 4) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 4);
                    }
                } else if (pentagonTouched) {
                    lastShapeTouched.setShape(Shape.PENTAGON);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 5 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 5) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 5);
                    }
                } else if (hexagonTouched) {
                    lastShapeTouched.setShape(Shape.HEXAGON);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 6 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 6) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 6);
                    }
                } else if (septagonTouched) {
                    lastShapeTouched.setShape(Shape.SEPTAGON);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 7 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 7) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 7);
                    }
                } else if (octagonTouched) {
                    lastShapeTouched.setShape(Shape.OCTAGON);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 8 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 8) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 8);
                    }
                } else if (nonagonTouched) {
                    lastShapeTouched.setShape(Shape.NONAGON);
                    lastPromptShape.setShape(promptShape.getShape());
                    if(lastPromptShape.getShape() == Shape.POINT) {
                        lastPromptShape.setRadius(promptShape.getRadius() / 2);
                    } else {
                        lastPromptShape.setRadius(promptShape.getRadius());
                    }
                    lastTargetShape.setShape(currentTargetShape.getShape());
                    equationWidth = INPUT_RADIUS;
                    if (promptShape.getShape() + 9 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 9) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 9);
                    }
                } else if (pauseTouched) {
                    pauseStartTime = System.currentTimeMillis();
                    pause();
                }
            }
            if (inputTouchedGameplay && !gameOver && promptShape.getShape() == currentTargetShape.getShape()) {
                targetShapesMatched++;
                Shape circleContainer = new Shape(Shape.CIRCLE,
                        promptShape.getRadius(),
                        Color.BLACK,
                        null,
                        promptShape.getLineWidth(),
                        promptShape.getCoordinates());
                Shape promptShapeToAdd = new Shape(promptShape.getShape(),
                        promptShape.getRadius(),
                        backgroundColorShape.getColor(),
                        null,
                        promptShape.getLineWidth(),
                        promptShape.getCoordinates());
                if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
                    promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
                }
                promptShape.setShape(MathUtils.random(game.base - 1));
                priorShapeList.add(promptShapeToAdd);
                priorShapeList.add(circleContainer);
                if (targetShapesMatched == 1) {
                    currentTargetShape = outsideTargetShape;
                } else {
                    targetShapesMatched = 0;
                    score += multiplier;
                    if (multiplier < 5) {
                        multiplier++;
                    }
                    targetShapeList.clear();
                    if (priorShapeList.get(priorShapeList.size() - 4).getColor().equals(priorShapeList.get(priorShapeList.size() - 2).getColor())) {
                        //SQUIRGLE!!!
                        outsideTargetShape.setShape(Shape.TRIANGLE);
                        outsideTargetShape.setColor(Color.BLACK);
                        targetShapeList.add(new Shape(Shape.SQUARE,
                                0,
                                Color.WHITE,
                                null,
                                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                                new Vector2(TARGET_RADIUS / 2.5f,
                                        game.camera.viewportHeight - (TARGET_RADIUS / 2.5f))));
                        targetShapeList.add(new Shape(Shape.CIRCLE,
                                0,
                                Color.BLACK,
                                null,
                                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                                new Vector2(TARGET_RADIUS / 3,
                                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
                    } else {
                        outsideTargetShape.setShape(MathUtils.random(game.base - 1));
                        outsideTargetShape.setColor(Color.BLACK);
                        targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                                0,
                                Color.WHITE,
                                null,
                                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                                new Vector2(TARGET_RADIUS / 2.5f,
                                        game.camera.viewportHeight - (TARGET_RADIUS / 2.5f))));
                        targetShapeList.add(new Shape(Shape.CIRCLE,
                                0,
                                Color.BLACK,
                                null,
                                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                                new Vector2(TARGET_RADIUS / 3,
                                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
                        if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
                            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                                outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                            }
                        }
                    }
                    if (priorShapeList.get(priorShapeList.size() - 4).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - 2).getShape() == Shape.TRIANGLE) {
                        //SQUIRGLE MATCHED!!!
                        promptShape.setRadius(INIT_PROMPT_RADIUS);
                    }
                    currentTargetShape = targetShapeList.get(0);
                }
            } else if (!gameOver && inputTouchedGameplay) {
                //The wrong shape was touched
                if (game.widthGreater) {
                    if (promptShape.getRadius() + (game.camera.viewportHeight * ((backgroundColorShapeList.get(3).getCoordinates().x - backgroundColorShapeList.get(2).getCoordinates().x) / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))) > (game.camera.viewportHeight / 2)) {
                        promptShape.setRadius(game.camera.viewportHeight / 2);
                    } else {
                        promptShape.setRadius(promptShape.getRadius() + (game.camera.viewportHeight * ((backgroundColorShapeList.get(3).getCoordinates().x - backgroundColorShapeList.get(2).getCoordinates().x) / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))));
                    }
                } else {
                    if (promptShape.getRadius() + (game.camera.viewportWidth * ((backgroundColorShapeList.get(3).getCoordinates().x - backgroundColorShapeList.get(2).getCoordinates().x) / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))) > (game.camera.viewportWidth / 2)) {
                        promptShape.setRadius(game.camera.viewportWidth / 2);
                    } else {
                        promptShape.setRadius(promptShape.getRadius() + (game.camera.viewportWidth * ((backgroundColorShapeList.get(3).getCoordinates().x - backgroundColorShapeList.get(2).getCoordinates().x) / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))));
                    }
                }
                multiplier = 1;
            }
            if (inputTouchedResults && showResults) {
                //Unlock new base options
                if(game.base == game.maxBase && score >= 150 && game.maxBase < 9) {
                    game.maxBase++;
                    game.updateSave(game.SAVE_MAX_BASE, game.maxBase);
                }

                if (playTouched) {
                    game.setScreen(new GameplayScreen(game));
                } else if (homeTouched) {
                    game.setScreen(new MainMenuScreen(game));
                } else {
                    dispose();
                    System.exit(0);
                }
                dispose();
            }
        } else {
            if (pauseBackTouched) {
                timePaused += System.currentTimeMillis() - pauseStartTime;
                resume();
            } else if (pauseQuitTouched) {
                stopMusic();
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        }
    }

    public void setUpNonFinalStaticData() {
        INPUT_RADIUS = game.camera.viewportWidth / 19;
        TARGET_RADIUS = game.camera.viewportWidth / 5.12f;
        PAUSE_INPUT_WIDTH = game.camera.viewportWidth - (game.partitionSize * 2);
        PAUSE_INPUT_HEIGHT = (game.camera.viewportHeight - (game.partitionSize * 3)) / 2;
        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS = game.camera.viewportHeight / 68;
        BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT = (game.camera.viewportHeight - (INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7);
        BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT = game.camera.viewportHeight - (INPUT_RADIUS / 2);
        BACKGROUND_COLOR_SHAPE_LIST_WIDTH = (TARGET_RADIUS + (6 * ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7))) - (TARGET_RADIUS + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7));
        INIT_PROMPT_RADIUS = game.widthOrHeight / 4;
        for(int i = 1; i <= game.base; i++) {
            Vector2 inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
            switch(i) {
                case 1 : INPUT_POINT_SPAWN = inputVector;
                case 2 : INPUT_LINE_SPAWN = inputVector;
                case 3 : INPUT_TRIANGLE_SPAWN = inputVector;
                case 4 : INPUT_SQUARE_SPAWN = inputVector;
                case 5 : INPUT_PENTAGON_SPAWN = inputVector;
                case 6 : INPUT_HEXAGON_SPAWN = inputVector;
                case 7 : INPUT_SEPTAGON_SPAWN = inputVector;
                case 8 : INPUT_OCTAGON_SPAWN = inputVector;
                case 9 : INPUT_NONAGON_SPAWN = inputVector;
            }
        }
        INPUT_PLAY_SPAWN = new Vector2(game.camera.viewportWidth / 4, (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
        INPUT_HOME_SPAWN = new Vector2((2 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
        INPUT_EXIT_SPAWN = new Vector2((3 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
    }

    public void setUpNonFinalNonStaticData() {
        backgroundColorShapeList = new ArrayList<Shape>();
        for (int i = 0; i <= 6; i++) {
            if (i == 0) {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        Color.WHITE,
                        com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(TARGET_RADIUS + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7),
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        Color.WHITE,
                        com.screenlooker.squirgle.util.ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(TARGET_RADIUS + (i * ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7)),
                                BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT)));
            }
        }

        //Set prompt increase such that without player input, three passes of backgroundColorShapeList will
        //occur before game over
        promptIncrease = (game.widthOrHeight * (game.draw.getColorListSpeed() / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))) / 2;
        promptShape = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS, Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        lastShapeTouched = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, GameplayScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        lastPromptShape = new Shape(Shape.POINT, promptShape.getRadius(), Color.BLACK, Color.BLACK, GameplayScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        outsideTargetShape = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / 2.43f,
                Color.BLACK,
                null,
                (TARGET_RADIUS / 2.43f) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 2.43f,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2.43f)));
        priorShapeList = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
        touchDownShapeList = new ArrayList<Shape>();
        targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        targetShapeList.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                outsideTargetShape.setShape(MathUtils.random(game.base - 1));
            }
        }
        backgroundColorShape = new Shape(Shape.POINT,
                game.camera.viewportWidth / 2,
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));
        currentTargetShape = targetShapeList.get(0);
        lastTargetShape = currentTargetShape;
        targetShapesMatched = 0;
        touchPoint = new Vector3();
        pointTouched = false;
        lineTouched = false;
        triangleTouched = false;
        squareTouched = false;
        playTouched = false;
        homeTouched = false;
        exitTouched = false;
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        inputTouchedGameplay = false;
        inputTouchedResults = false;
        clearColor = new Color();
        score = 0;
        multiplier = 1;
        gameOver = false;
        showResults = false;
        paused = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
        pauseStartTime = 0;
        timePaused = 0;
        equationWidth = 0;
        destructionIndex = 1;
        firstPriorShapePreviousX = 0;
        resultsColor = new Color();
        primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        primaryShapeThreshold = game.widthOrHeight * game.draw.THRESHOLD_MULTIPLIER;
        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}