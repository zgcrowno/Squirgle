package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen implements Screen, InputProcessor {
    final Squirgle game;

    private final static int PAUSE_BACK = 0;
    private final static int PAUSE_QUIT = 1;

    private final static int PARTITION_DIVISOR = 80;
    private final static int LINE_WIDTH = 20;

    private static final float SQUIRGLE_RADIUS_DECREMENT = 0.8f;

    private float partitionSize;
    private float pauseInputWidth;
    private float pauseInputHeight;
    private int timeSignature;
    private float initPromptRadius;
    private float backgroundColorListElementRadius;
    private float promptIncrease;
    private float endLineWidthIncrease;
    private float backgroundColorShapeListMaxHeight;
    private float backgroundColorShapeListMinHeight;
    private Shape promptShape;
    private Shape outsideTargetShape;
    private List<Shape> priorShapeList;
    private List<Shape> targetShapeList;
    private List<Shape> backgroundColorShapeList;
    private List<Shape> touchDownShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShape;
    private int targetShapesMatched;
    private Vector2 inputPointSpawn;
    private Vector2 inputLineSpawn;
    private Vector2 inputTriangleSpawn;
    private Vector2 inputSquareSpawn;
    private Vector2 inputPentagonSpawn;
    private Vector2 inputHexagonSpawn;
    private Vector2 inputSeptagonSpawn;
    private Vector2 inputOctagonSpawn;
    private Vector2 inputNonagonSpawn;
    private Vector2 inputPlaySpawn;
    private Vector2 inputHomeSpawn;
    private Vector2 inputExitSpawn;
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

    public GameplayScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        partitionSize = game.camera.viewportHeight / PARTITION_DIVISOR;
        pauseInputWidth = game.camera.viewportWidth - (partitionSize * 2);
        pauseInputHeight = (game.camera.viewportHeight - (partitionSize * 3)) / 2;

        timeSignature = 4; //This represents the number of quarter notes per background color change (4 = 4/4, 5 = 5/4, etc.)
        initPromptRadius = 20;
        backgroundColorListElementRadius = 15;
        promptIncrease = 1.0005f;
        endLineWidthIncrease = 2;
        backgroundColorShapeListMaxHeight = (game.camera.viewportHeight - (Draw.INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7);
        backgroundColorShapeListMinHeight = game.camera.viewportHeight - (Draw.INPUT_RADIUS / 2);
        promptShape = new Shape(MathUtils.random(game.base - 1),
                initPromptRadius, Color.BLACK,
                null,
                initPromptRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        outsideTargetShape = new Shape(MathUtils.random(game.base - 1),
                Draw.INPUT_RADIUS,
                Color.BLACK, null,
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(Draw.TARGET_RADIUS / 2.5f,
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2.5f)));
        priorShapeList = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
        backgroundColorShapeList = new ArrayList<Shape>();
        touchDownShapeList = new ArrayList<Shape>();
        targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(Draw.TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
        targetShapeList.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(Draw.TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
        if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                outsideTargetShape.setShape(MathUtils.random(game.base - 1));
            }
        }
        for (int i = 0; i <= 6; i++) {
            if (i == 0) {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        backgroundColorListElementRadius,
                        Color.WHITE,
                        ColorUtils.randomColor(),
                        backgroundColorListElementRadius / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(Draw.TARGET_RADIUS + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7),
                                backgroundColorShapeListMaxHeight)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        backgroundColorListElementRadius,
                        Color.WHITE,
                        ColorUtils.randomColor(),
                        backgroundColorListElementRadius / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(Draw.TARGET_RADIUS + (i * ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7)),
                                backgroundColorShapeListMinHeight)));
            }
        }
        backgroundColorShape = new Shape(Shape.randomBackgroundColorShape(),
                game.camera.viewportWidth / 2,
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));
        currentTargetShape = targetShapeList.get(0);
        targetShapesMatched = 0;
        for(int i = 1; i <= game.base; i++) {
            Vector2 inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * Draw.INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * Draw.INPUT_RADIUS), (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
            switch(i) {
                case 1 : inputPointSpawn = inputVector;
                case 2 : inputLineSpawn = inputVector;
                case 3 : inputTriangleSpawn = inputVector;
                case 4 : inputSquareSpawn = inputVector;
                case 5 : inputPentagonSpawn = inputVector;
                case 6 : inputHexagonSpawn = inputVector;
                case 7 : inputSeptagonSpawn = inputVector;
                case 8 : inputOctagonSpawn = inputVector;
                case 9 : inputNonagonSpawn = inputVector;
            }
        }
        inputPlaySpawn = new Vector2(game.camera.viewportWidth / 4, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputHomeSpawn = new Vector2((2 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputExitSpawn = new Vector2((3 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
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
        destructionIndex = 1;
        firstPriorShapePreviousX = 0;
        resultsColor = new Color();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        float primaryShapeThreshold = game.camera.viewportWidth * game.draw.THRESHOLD_MULTIPLIER;
        Shape primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        boolean primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        if(!paused) {
            promptShape.setRadius(promptShape.getRadius() * promptIncrease);

            if (!primaryShapeAtThreshold) {
                promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
            }

            if (!gameOver) {
                game.draw.drawBackgroundColorShape(backgroundColorShape, game.shapeRendererFilled);
            }

            game.draw.drawPrompt(promptShape, priorShapeList, primaryShapeAtThreshold, game.shapeRendererLine);

            game.draw.drawShapes(priorShapeList, promptShape, primaryShapeAtThreshold, game, game.shapeRendererFilled);

            //TODO: Make sure you execute the inverse of this given certain player input...Also adjust all of this
            //TODO: so that the promptIncrease, colorListSpeed and colorSpeed are all increasing by proportional rates.
            if (!gameOver) {
                if ((System.currentTimeMillis() - startTime - timePaused) / 1000 > 10) {
                    promptIncrease += .0005;
                    startTime = System.currentTimeMillis();
                    game.draw.setColorListSpeed(game.draw.getColorListSpeed() + 0.1f);
                    game.draw.setColorSpeed(game.draw.getColorSpeed() + 20);
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
                            primaryShape.setLineWidth(primaryShape.getLineWidth() * endLineWidthIncrease);
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

            //This code is being executed twice: once before setting the prompt's end game coordinates, and again afterwards.
            //This way, the shapes are drawn with their new values, and the first element in priorShapeList doesn't veer off
            //the screen to the right.
            //TODO: separate draw methods out into distinct ones, one of which assigns radii and coordinates, and the other of
            //TODO: which actually draws the shapes. It's overkill to draw the shapes multiple times.
            game.draw.drawShapes(priorShapeList, promptShape, primaryShapeAtThreshold, game, game.shapeRendererFilled);

            if (!gameOver) {
                game.draw.drawBackgroundColorShapeList(backgroundColorShapeList, backgroundColorShape, clearColor, game.shapeRendererFilled);
                SoundUtils.playMusic(timeSignature,
                        backgroundColorShapeListMaxHeight - backgroundColorShapeListMinHeight,
                        game.draw.getColorListSpeed(),
                        backgroundColorShapeList,
                        game);
                game.draw.drawInputButtons(game, inputPointSpawn, inputLineSpawn, inputTriangleSpawn, inputSquareSpawn, inputPentagonSpawn, inputHexagonSpawn, inputSeptagonSpawn, inputOctagonSpawn, inputNonagonSpawn, game.shapeRendererFilled);
                game.draw.drawTargetSemicircle(game.shapeRendererFilled);
                game.draw.drawScoreTriangle(game.shapeRendererFilled);
                game.draw.drawPrompt(outsideTargetShape, targetShapeList, false, game.shapeRendererFilled);
                game.draw.drawShapes(targetShapeList, outsideTargetShape, false, game, game.shapeRendererFilled);
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
                    //TODO: Account for game ending with empty priorShapeList
                }
            }

            if (priorShapeList.size() > 0) {
                firstPriorShapePreviousX = priorShapeList.get(0).getCoordinates().x;
            } else {
                firstPriorShapePreviousX = promptShape.getCoordinates().x;
            }

            if (showResults) {
                game.draw.drawResultsInputButtons(inputPlaySpawn, inputHomeSpawn, inputExitSpawn, game.shapeRendererFilled);
            }

            game.draw.drawTouchDownPoints(touchDownShapeList, game.shapeRendererLine);
        } else {
            drawInputRectangles();
        }

        game.shapeRendererFilled.end();

        game.shapeRendererLine.end();

        if(!paused) {
            if (!gameOver) {
                FontUtils.printText(game.batch,
                        game.font,
                        game.layout,
                        backgroundColorShape.getColor(),
                        String.valueOf(score),
                        game.camera.viewportWidth - (Draw.TARGET_RADIUS / 3.2f),
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 3.2f),
                        -45);
                FontUtils.printText(game.batch,
                        game.font,
                        game.layout,
                        Color.WHITE,
                        "X" + String.valueOf(multiplier),
                        game.camera.viewportWidth - (Draw.TARGET_RADIUS / 2.58f),
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 1.25f),
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
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
        }

        currentTargetShape.setColor(ColorUtils.randomColor());
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
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        pointTouched = touchPoint.x > inputPointSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPointSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPointSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPointSpawn.y + Draw.INPUT_RADIUS;
        lineTouched = touchPoint.x > inputLineSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputLineSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputLineSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputLineSpawn.y + Draw.INPUT_RADIUS;
        triangleTouched = touchPoint.x > inputTriangleSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputTriangleSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputTriangleSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputTriangleSpawn.y + Draw.INPUT_RADIUS;
        squareTouched = touchPoint.x > inputSquareSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSquareSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSquareSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSquareSpawn.y + Draw.INPUT_RADIUS;
        pentagonTouched = touchPoint.x > inputPentagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPentagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPentagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPentagonSpawn.y + Draw.INPUT_RADIUS;
        hexagonTouched = touchPoint.x > inputHexagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHexagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHexagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHexagonSpawn.y + Draw.INPUT_RADIUS;
        septagonTouched = touchPoint.x > inputSeptagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSeptagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSeptagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSeptagonSpawn.y + Draw.INPUT_RADIUS;
        octagonTouched = touchPoint.x > inputOctagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputOctagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputOctagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputOctagonSpawn.y + Draw.INPUT_RADIUS;
        nonagonTouched = touchPoint.x > inputNonagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputNonagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputNonagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputNonagonSpawn.y + Draw.INPUT_RADIUS;
        playTouched = touchPoint.x > inputPlaySpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPlaySpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPlaySpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPlaySpawn.y + Draw.INPUT_RADIUS;
        homeTouched = touchPoint.x > inputHomeSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHomeSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHomeSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHomeSpawn.y + Draw.INPUT_RADIUS;
        exitTouched = touchPoint.x > inputExitSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputExitSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputExitSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputExitSpawn.y + Draw.INPUT_RADIUS;

        for (int i = 1; i < 20; i++) {
            if (!gameOver) {
                if (pointTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputPointSpawn.x,
                                    inputPointSpawn.y)));
                } else if (lineTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputLineSpawn.x,
                                    inputTriangleSpawn.y)));
                } else if (triangleTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputTriangleSpawn.x,
                                    inputTriangleSpawn.y)));
                } else if (squareTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputSquareSpawn.x,
                                    inputSquareSpawn.y)));
                } else if(pentagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputPentagonSpawn.x,
                                    inputPentagonSpawn.y)));
                } else if(hexagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputHexagonSpawn.x,
                                    inputHexagonSpawn.y)));
                } else if(septagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputSeptagonSpawn.x,
                                    inputSeptagonSpawn.y)));
                } else if(octagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputOctagonSpawn.x,
                                    inputOctagonSpawn.y)));
                } else if(nonagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputNonagonSpawn.x,
                                    inputNonagonSpawn.y)));
                }
            } else if (showResults) {
                if (playTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputPlaySpawn.x,
                                    inputPlaySpawn.y)));
                } else if (homeTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i, ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputHomeSpawn.x,
                                    inputHomeSpawn.y)));
                } else {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(inputExitSpawn.x,
                                    inputExitSpawn.y)));
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

        pointTouched = touchPoint.x > inputPointSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPointSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPointSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPointSpawn.y + Draw.INPUT_RADIUS;
        lineTouched = touchPoint.x > inputLineSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputLineSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputLineSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputLineSpawn.y + Draw.INPUT_RADIUS;
        triangleTouched = touchPoint.x > inputTriangleSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputTriangleSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputTriangleSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputTriangleSpawn.y + Draw.INPUT_RADIUS;
        squareTouched = touchPoint.x > inputSquareSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSquareSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSquareSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSquareSpawn.y + Draw.INPUT_RADIUS;
        pentagonTouched = touchPoint.x > inputPentagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPentagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPentagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPentagonSpawn.y + Draw.INPUT_RADIUS;
        hexagonTouched = touchPoint.x > inputHexagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHexagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHexagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHexagonSpawn.y + Draw.INPUT_RADIUS;
        septagonTouched = touchPoint.x > inputSeptagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSeptagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSeptagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSeptagonSpawn.y + Draw.INPUT_RADIUS;
        octagonTouched = touchPoint.x > inputOctagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputOctagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputOctagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputOctagonSpawn.y + Draw.INPUT_RADIUS;
        nonagonTouched = touchPoint.x > inputNonagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputNonagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputNonagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputNonagonSpawn.y + Draw.INPUT_RADIUS;
        playTouched = touchPoint.x > inputPlaySpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPlaySpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPlaySpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPlaySpawn.y + Draw.INPUT_RADIUS;
        homeTouched = touchPoint.x > inputHomeSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHomeSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHomeSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHomeSpawn.y + Draw.INPUT_RADIUS;
        exitTouched = touchPoint.x > inputExitSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputExitSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputExitSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputExitSpawn.y + Draw.INPUT_RADIUS;
        //TODO: Set and use variables for these values
        pauseTouched = touchPoint.x > game.camera.viewportWidth - (2 *(game.camera.viewportWidth / 20))
                && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20);
        pauseBackTouched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + pauseInputWidth
                && touchPoint.y > partitionSize
                && touchPoint.y < partitionSize + pauseInputHeight;
        pauseQuitTouched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + pauseInputWidth
                && touchPoint.y > (2 * partitionSize) + pauseInputHeight
                && touchPoint.y < game.camera.viewportHeight - partitionSize;
        inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched || pauseTouched;
        inputTouchedResults = playTouched || homeTouched || exitTouched;

        if (!gameOver) {
            if(!paused) {
                if (pointTouched) {
                    if (promptShape.getShape() + 1 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 1) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 1);
                    }
                } else if (lineTouched) {
                    if (promptShape.getShape() + 2 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 2) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 2);
                    }
                } else if (triangleTouched) {
                    if (promptShape.getShape() + 3 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 3) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 3);
                    }
                } else if (squareTouched) {
                    if (promptShape.getShape() + 4 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 4) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 4);
                    }
                } else if (pentagonTouched) {
                    if (promptShape.getShape() + 5 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 5) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 5);
                    }
                } else if (hexagonTouched) {
                    if (promptShape.getShape() + 6 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 6) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 6);
                    }
                } else if (septagonTouched) {
                    if (promptShape.getShape() + 7 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 7) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 7);
                    }
                } else if (octagonTouched) {
                    if (promptShape.getShape() + 8 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 8) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 8);
                    }
                } else if (nonagonTouched) {
                    if (promptShape.getShape() + 9 >= game.base) {
                        promptShape.setShape((promptShape.getShape() + 9) - game.base);
                    } else {
                        promptShape.setShape(promptShape.getShape() + 9);
                    }
                } else if (pauseTouched) {
                    pauseStartTime = System.currentTimeMillis();
                    pause();
                }
            } else {
                if(pauseBackTouched) {
                    timePaused += System.currentTimeMillis() - pauseStartTime;
                    resume();
                } else if(pauseQuitTouched) {
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }
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
                if (priorShapeList.get(priorShapeList.size() - 4).getColor() == priorShapeList.get(priorShapeList.size() - 2).getColor()) {
                    //SQUIRGLE!!!
                    outsideTargetShape.setShape(Shape.TRIANGLE);
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 2.5f,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2.5f))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 3,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
                } else {
                    outsideTargetShape.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 2.5f,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2.5f))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 3,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
                    if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
                        while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                            outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeList.get(priorShapeList.size() - 4).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - 2).getShape() == Shape.TRIANGLE) {
                    promptShape.setRadius(promptShape.getRadius() * SQUIRGLE_RADIUS_DECREMENT);
                }
                currentTargetShape = targetShapeList.get(0);
            }
        } else {
            //The wrong shape was touched
            //TODO: Add wrong shape penalization here.
            multiplier = 1;
        }
        if (inputTouchedResults && showResults) {
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
        return false;
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
                game.shapeRendererFilled.rect(partitionSize,
                        partitionSize,
                        pauseInputWidth,
                        pauseInputHeight);
            }
            case PAUSE_QUIT : {
                game.shapeRendererFilled.rect(partitionSize,
                        (2 * partitionSize) + pauseInputHeight,
                        pauseInputWidth,
                        pauseInputHeight);
            }
        }
    }

    public void drawPauseQuitInput() {
        drawInputRectangle(PAUSE_QUIT);
        game.draw.drawX(game.camera.viewportWidth / 2,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                game.camera.viewportHeight / 4,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawPauseBackInput() {
        drawInputRectangle(PAUSE_BACK);
        game.draw.drawBackButton(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 4,
                game.camera.viewportHeight / 4,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
