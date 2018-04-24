package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.InputUtils;
import com.screenlooker.squirgle.util.SoundUtils;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen implements Screen, InputProcessor {
    final Squirgle game;
    public final int gameplayType;

    public static float INIT_PROMPT_RADIUS;
    public static float BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_X;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_X;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_Y;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_Y;
    public static float BACKGROUND_COLOR_SHAPE_LIST_WIDTH;
    public static float BACKGROUND_COLOR_SHAPE_LIST_HEIGHT;
    public static float COLOR_LIST_SPEED_ADDITIVE;
    public static float INPUT_RADIUS;
    public static float TARGET_RADIUS;
    public static float PAUSE_INPUT_WIDTH;
    public static float PAUSE_INPUT_HEIGHT;
    public static float FONT_SCORE_SIZE_DIVISOR;
    public static float FONT_TARGET_SIZE_DIVISOR;
    public static float FONT_SQUIRGLE_SIZE_DIVISOR;
    public static float FONT_SKIP_SIZE_DIVISOR;
    public static Vector2 INPUT_POINT_SPAWN;
    public static Vector2 INPUT_LINE_SPAWN;
    public static Vector2 INPUT_TRIANGLE_SPAWN;
    public static Vector2 INPUT_SQUARE_SPAWN;
    public static Vector2 INPUT_PENTAGON_SPAWN;
    public static Vector2 INPUT_HEXAGON_SPAWN;
    public static Vector2 INPUT_SEPTAGON_SPAWN;
    public static Vector2 INPUT_OCTAGON_SPAWN;
    public static Vector2 INPUT_NONAGON_SPAWN;
    public static Vector2 INPUT_POINT_SPAWN_P1;
    public static Vector2 INPUT_LINE_SPAWN_P1;
    public static Vector2 INPUT_TRIANGLE_SPAWN_P1;
    public static Vector2 INPUT_SQUARE_SPAWN_P1;
    public static Vector2 INPUT_PENTAGON_SPAWN_P1;
    public static Vector2 INPUT_HEXAGON_SPAWN_P1;
    public static Vector2 INPUT_SEPTAGON_SPAWN_P1;
    public static Vector2 INPUT_OCTAGON_SPAWN_P1;
    public static Vector2 INPUT_NONAGON_SPAWN_P1;
    public static Vector2 INPUT_POINT_SPAWN_P2;
    public static Vector2 INPUT_LINE_SPAWN_P2;
    public static Vector2 INPUT_TRIANGLE_SPAWN_P2;
    public static Vector2 INPUT_SQUARE_SPAWN_P2;
    public static Vector2 INPUT_PENTAGON_SPAWN_P2;
    public static Vector2 INPUT_HEXAGON_SPAWN_P2;
    public static Vector2 INPUT_SEPTAGON_SPAWN_P2;
    public static Vector2 INPUT_OCTAGON_SPAWN_P2;
    public static Vector2 INPUT_NONAGON_SPAWN_P2;
    public static Vector2 INPUT_PLAY_SPAWN;
    public static Vector2 INPUT_HOME_SPAWN;
    public static Vector2 INPUT_EXIT_SPAWN;

    private final static int PAUSE_BACK = 0;
    private final static int PAUSE_QUIT = 1;

    private final static int END_LINE_WIDTH_INCREASE = 2;
    private final static int NUM_MUSIC_PHASES = 3;
    private final static int NUM_TIMELINES = 3;
    private final static int SCORE_ANGLE = -45;
    private final static int ONE_THOUSAND = 1000;
    private final static int TWO_SECONDS = 2;
    private final static int TEN_SECONDS = 10;
    private final static int TARGET_ARC_SPEED = 5;
    private final static int COLOR_SPEED_ADDITIVE = 20;
    private final static int EQUATION_WIDTH_DIVISOR = 60;
    private final static int SHAPE_SIZE_LIMIT_MULTIPLIER = 15;
    private final static int MAX_MULTIPLIER = 5;
    private final static int ONE_SHAPE_AGO = 2;
    private final static int TWO_SHAPES_AGO = 4;
    private final static int THIRTY_DEGREES = 30;
    private final static int TWO_HUNDRED_AND_SEVENTY_DEGREES = 270;
    private final static int THREE_HUNDRED_AND_THIRTY_DEGREES = 330;
    private final static int SKIP_TEXT_DISAPPEARANCE_TIME = 5;

    private final static float FONT_MULTIPLIER_INPUT = 1.39f;
    private final static float SCORE_DIVISOR = 3.16f;
    private final static float TARGET_RADIUS_DIVISOR = 2.43f;
    private final static float MULTIPLIER_X_DIVISOR = 2.68f;
    private final static float MULTIPLIER_Y_DIVISOR = 1.25f;
    private final static float PROMPT_INCREASE_ADDITIVE = .0001f;
    private final static float SQUIRGLE_OPACITY_DECREMENT = .03f;

    public final static String P1 = "P1";
    public final static String P2 = "P2";
    public final static int MAX_SATURATION = 15;
    private final static String X = "X";
    private final static String COLON = ":";
    private final static String MINUTES = "m";
    private final static String SECONDS = "s";
    private final static String NEW_BEST = "NEW BEST";
    private final static String SKIP_TEXT = "TAP AGAIN TO SKIP";
    private final static String SQUIRGLE = "SQUIRGLE";

    private float promptIncrease;
    private float equationWidth;
    private float equationWidthP1;
    private float equationWidthP2;
    private float targetArcStart;
    private float targetArcStartP1;
    private float targetArcStartP2;
    private float squirgleOpacity;
    private float squirgleOpacityP1;
    private float squirgleOpacityP2;
    private Shape promptShape;
    private Shape promptShapeP1;
    private Shape promptShapeP2;
    private Shape dummyPromptForTimelines;
    private Shape lastShapeTouched;
    private Shape lastShapeTouchedP1;
    private Shape lastShapeTouchedP2;
    private Shape lastPromptShape;
    private Shape lastPromptShapeP1;
    private Shape lastPromptShapeP2;
    private Shape outsideTargetShape;
    private Shape outsideTargetShapeP1;
    private Shape outsideTargetShapeP2;
    private List<Shape> priorShapeList;
    private List<Shape> priorShapeListP1;
    private List<Shape> priorShapeListP2;
    private List<Shape> targetShapeList;
    private List<Shape> targetShapeListP1;
    private List<Shape> targetShapeListP2;
    private List<Shape> backgroundColorShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShape;
    private Shape currentTargetShapeP1;
    private Shape currentTargetShapeP2;
    private Shape lastTargetShape;
    private Shape lastTargetShapeP1;
    private Shape lastTargetShapeP2;
    private int targetShapesMatched;
    private int targetShapesMatchedP1;
    private int targetShapesMatchedP2;
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
    boolean pointTouchedP1;
    boolean lineTouchedP1;
    boolean triangleTouchedP1;
    boolean squareTouchedP1;
    boolean pentagonTouchedP1;
    boolean hexagonTouchedP1;
    boolean septagonTouchedP1;
    boolean octagonTouchedP1;
    boolean nonagonTouchedP1;
    boolean pointTouchedP2;
    boolean lineTouchedP2;
    boolean triangleTouchedP2;
    boolean squareTouchedP2;
    boolean pentagonTouchedP2;
    boolean hexagonTouchedP2;
    boolean septagonTouchedP2;
    boolean octagonTouchedP2;
    boolean nonagonTouchedP2;
    boolean playTouched;
    boolean homeTouched;
    boolean exitTouched;
    boolean pauseTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    boolean inputTouchedGameplay;
    boolean inputTouchedGameplayP1;
    boolean inputTouchedGameplayP2;
    boolean inputTouchedResults;
    private Color targetArcColor;
    private Color clearColor;
    private int score;
    private int scoreP1;
    private int scoreP2;
    private boolean gameOver;
    private boolean showResults;
    private boolean paused;
    private int multiplier;
    private int multiplierP1;
    private int multiplierP2;
    private long startTime;
    private long endTime;
    private long lastSpeedIncreaseTime;
    private long pauseStartTime;
    private long timePaused;
    private long opponentTime;
    private long timeSinceTouched;
    private int destructionIndex;
    private float firstPriorShapePreviousX;
    private float firstPriorShapePreviousXP1;
    private float firstPriorShapePreviousXP2;
    private Color resultsColor;
    Shape primaryShape;
    Shape primaryShapeP1;
    Shape primaryShapeP2;
    float primaryShapeThreshold;
    boolean primaryShapeAtThreshold;
    boolean primaryShapeAtThresholdP1;
    boolean primaryShapeAtThresholdP2;
    private int saturationP1;
    private int saturationP2;
    private boolean splitScreen;
    private boolean useSaturation;
    private boolean blackAndWhite;
    private boolean admitsOfSquirgle;
    private boolean multiplayer;
    private boolean local;
    private boolean online;
    private boolean newHighScore;
    private boolean newLongestRun;
    private boolean newFastestVictory;
    private boolean skipZoom;
    private boolean shouldUnlockNewBase;
    private Color veilColor;
    private float veilOpacity;

    public GameplayScreen(final Squirgle game, final int gameplayType) {
        this.game = game;
        this.gameplayType = gameplayType;
        this.splitScreen = !(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE || gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK);
        this.useSaturation = gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_BATTLE_ONLINE;
        this.blackAndWhite = gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_ONLINE;
        this.admitsOfSquirgle = gameplayType == Squirgle.GAMEPLAY_SQUIRGLE || gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_BATTLE_ONLINE;
        this.multiplayer = gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_BATTLE_ONLINE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_ONLINE;
        this.local = gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL;
        this.online = multiplayer && !local;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        //TODO: Eventually set this in render using delta? See maintainSpeed() in TimeAttackScreen
        game.draw.setColorListSpeed(((BACKGROUND_COLOR_SHAPE_LIST_HEIGHT * NUM_TIMELINES) / game.FPS) / game.THIRTY_SECONDS);

        setUpNonFinalNonStaticData();

        game.setUpFontScore(MathUtils.round(game.camera.viewportWidth / FONT_SCORE_SIZE_DIVISOR));
        game.setUpFontTarget(MathUtils.round(game.camera.viewportWidth / FONT_TARGET_SIZE_DIVISOR));
        game.setUpFontSquirgle(MathUtils.round(game.camera.viewportWidth / FONT_SQUIRGLE_SIZE_DIVISOR));
        game.setUpFontSkip(MathUtils.round(game.camera.viewportWidth / FONT_SKIP_SIZE_DIVISOR));

        SoundUtils.setVolume(splitScreen ? dummyPromptForTimelines : promptShape, game);

        playMusic();

        game.stats.incrementNumTimesPlayedMode(gameplayType);
        game.stats.incrementNumTimesPlayedBaseOrTrack(true, game.base, gameplayType);
        game.stats.incrementNumTimesPlayedBaseOrTrack(false, game.track, gameplayType);
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
        primaryShapeP1 = priorShapeListP1.size() > 0 ? priorShapeListP1.get(0) : promptShapeP1;
        primaryShapeP2 = priorShapeListP2.size() > 0 ? priorShapeListP2.get(0) : promptShapeP2;

        primaryShapeThreshold = game.widthOrHeightSmaller * game.draw.THRESHOLD_MULTIPLIER;
        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP1 = primaryShapeP1.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP2 = primaryShapeP2.getRadius() >= primaryShapeThreshold;

        if(!splitScreen) {
            increasePromptRadius();
        } else {
            managePromptRadii();
            increaseDummyPromptRadius();
        }

        managePrimaryShapeLineWidth();

        drawBackgroundColorShape();

        if(!paused) {
            if (!gameOver) {
                if(!splitScreen) {
                    game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                            game.camera.viewportHeight / 2,
                            blackAndWhite ? Color.WHITE : Color.BLACK,
                            (3 * game.widthOrHeightSmaller) / 8,
                            promptShape);
                } else {
                    game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                            (3 * game.camera.viewportHeight) / 4,
                            blackAndWhite ? Color.WHITE : Color.BLACK,
                            game.camera.viewportHeight / 2 > game.camera.viewportWidth ? (3 * game.camera.viewportWidth) / 8 : (3 * (game.camera.viewportHeight / 2)) / 8,
                            promptShapeP2);
                    game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                            game.camera.viewportHeight / 4,
                            blackAndWhite ? Color.WHITE : Color.BLACK,
                            game.camera.viewportHeight / 2 > game.camera.viewportWidth ? (3 * game.camera.viewportWidth) / 8 : (3 * (game.camera.viewportHeight / 2)) / 8,
                            promptShapeP1);
                    game.draw.drawScreenDivision(blackAndWhite, multiplayer);
                }
                game.draw.drawTargetSemicircles(splitScreen, local && !game.desktop);
            }
            if(!skipZoom) {
                if (!splitScreen) {
                    game.draw.drawPrompt(false, promptShape, priorShapeList, 0, backgroundColorShape, false, false);
                    game.draw.orientShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
                } else if (useSaturation) {
                    if (!(gameOver && saturationP1 > saturationP2)) {
                        game.draw.drawPrompt(false, promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                    if (!(gameOver && saturationP2 >= saturationP1)) {
                        game.draw.drawPrompt(local && !game.desktop, promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(local && !game.desktop, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                } else {
                    if (!(gameOver && scoreP1 < scoreP2)) {
                        game.draw.drawPrompt(false, promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                    if (!(gameOver && scoreP2 <= scoreP1)) {
                        game.draw.drawPrompt(local && !game.desktop, promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(local && !game.desktop, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                }
            }
        }

        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            increaseSpeed();
        } else {
            maintainSpeed();
        }
        if(!splitScreen) {
            decrementSquirgleOpacity();
        } else {
            decrementSquirgleOpacities();
        }
        zoomThroughShapes();

        if(!paused) {
            if(!skipZoom) {
                if (!splitScreen) {
                    game.draw.orientAndDrawShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
                } else if (useSaturation) {
                    if (!(gameOver && saturationP1 > saturationP2)) {
                        game.draw.orientAndDrawShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                    if (!(gameOver && saturationP2 >= saturationP1)) {
                        game.draw.orientAndDrawShapes(local && !game.desktop, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                } else {
                    if (!(gameOver && scoreP1 < scoreP2)) {
                        game.draw.orientAndDrawShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                    if (!(gameOver && scoreP2 <= scoreP1)) {
                        game.draw.orientAndDrawShapes(local && !game.desktop, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                }
            }
        }

        if(splitScreen && !multiplayer) {
            executeOpponenentAI();
        }

        drawEquations();

        if(!paused) {
            if (!gameOver) {
                game.draw.drawInputButtons(splitScreen, local && !game.desktop, backgroundColorShape.getColor(), game);
                if(!splitScreen) {
                    game.draw.drawPrompt(false, outsideTargetShape, targetShapeList, targetShapesMatched, backgroundColorShape, false, true);
                    game.draw.orientShapes(false, targetShapeList, outsideTargetShape, false);
                    game.draw.drawShapes(false, targetShapeList);
                } else {
                    game.draw.drawPrompt(false, outsideTargetShapeP1, targetShapeListP1, targetShapesMatchedP1, backgroundColorShape, false, true);
                    game.draw.orientShapes(false, targetShapeListP1, outsideTargetShapeP1, false);
                    game.draw.drawShapes(false, targetShapeListP1);
                    game.draw.drawPrompt(local && !game.desktop, outsideTargetShapeP2, targetShapeListP2, targetShapesMatchedP2, backgroundColorShape, false, true);
                    game.draw.orientShapes(local && !game.desktop, targetShapeListP2, outsideTargetShapeP2, false);
                    game.draw.drawShapes(local && !game.desktop, targetShapeListP2);
                }
                drawTargetArcs();
                game.draw.drawBackgroundColorShapeList(splitScreen, blackAndWhite, local && !game.desktop, backgroundColorShapeList, backgroundColorShape, clearColor);
                game.draw.drawTimelines(splitScreen, local && !game.desktop, splitScreen ? dummyPromptForTimelines : promptShape, backgroundColorShapeList);
                game.draw.drawScoreTriangles(splitScreen, local && !game.desktop, backgroundColorShape.getColor());
                if(useSaturation) {
                    if(saturationP1 > 0) {
                        game.draw.drawSaturationTriangles(P1, local, saturationP1);
                    }
                    if(saturationP2 > 0) {
                        game.draw.drawSaturationTriangles(P2, local && !game.desktop, saturationP2);
                    }
                    game.draw.drawSaturationIncrements(local && !game.desktop, backgroundColorShape.getColor());
                }
                //TODO: Draw another pause input for local multiplayer
                game.draw.drawPauseInput(splitScreen, local, game);
            }
        }

        destroyOversizedShapes();

        if(!paused) {
            if(!splitScreen) {
                firstPriorShapePreviousX = primaryShape.getCoordinates().x;
            } else {
                firstPriorShapePreviousXP1 = primaryShapeP1.getCoordinates().x;
                firstPriorShapePreviousXP2 = primaryShapeP2.getCoordinates().x;
            }
        }

        drawResultsInputButtons();

        if(paused) {
            //TODO: Configure this for local multiplayer
            drawInputRectangles();
        }

        game.draw.drawVeil(veilColor, veilOpacity);

        if(!paused && !showResults && (System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < SKIP_TEXT_DISAPPEARANCE_TIME) {
            drawSkipTextBox();
        }

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        SoundUtils.setVolume(splitScreen ? dummyPromptForTimelines : promptShape, game);

        drawText();

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
        }

        gameOver();

        if(!splitScreen) {
            ColorUtils.transitionColor(currentTargetShape);
        } else {
            ColorUtils.transitionColor(currentTargetShapeP1);
            ColorUtils.transitionColor(currentTargetShapeP2);
        }

        if(veilOpacity > 0) {
            veilOpacity -= 0.01;
        }

        InputUtils.keepCursorInBounds(game);
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
        pauseStartTime = System.currentTimeMillis();
        clearColor.set(backgroundColorShape.getColor().r,
                backgroundColorShape.getColor().g,
                backgroundColorShape.getColor().b,
                backgroundColorShape.getColor().a);
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

        if(!paused && !showResults && (System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < SKIP_TEXT_DISAPPEARANCE_TIME) {
            List<Shape> priorShapeListToUse = new ArrayList<Shape>();
            if(!splitScreen) {
                priorShapeListToUse = priorShapeList;
            } else if(useSaturation) {
                if(saturationP1 > saturationP2) {
                    priorShapeListToUse = priorShapeListP2;
                } else {
                    priorShapeListToUse = priorShapeListP1;
                }
            } else {
                if(scoreP1 >= scoreP2) {
                    priorShapeListToUse = priorShapeListP1;
                } else {
                    priorShapeListToUse = priorShapeListP2;
                }
            }

            if(!blackAndWhite) {
                if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() == Shape.LINE || priorShapeListToUse.get(0).getShape() == Shape.POINT)) {
                    clearColor = Color.WHITE;
                } else {
                    clearColor = Color.BLACK;
                }
            } else {
                if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() != Shape.LINE && priorShapeListToUse.get(0).getShape() != Shape.POINT)) {
                    clearColor = Color.WHITE;
                } else {
                    clearColor = Color.BLACK;
                }
            }

            skipZoom = true;
            veilOpacity = 0;
            showResults = true;
        }

        if(gameOver && !showResults) {
            timeSinceTouched = System.currentTimeMillis();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        determineTouchedInput(screenX, screenY);

        if(!splitScreen) {
            handleInput(null);
        } else {
            if(multiplayer) {
                handleInput(P2);
                if(!game.desktop) {
                    handleInput(P1);
                }
            } else {
                handleInput(P1);
            }
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
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            //TODO: Somehow activate numlock so numpad is always available for use

            determineKeyedInput(keycode);

            if(!splitScreen) {
                handleInput(null);
            } else {
                handleInput(P1);
            }

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
        game.draw.drawPauseSymbol(game.partitionSize + (PAUSE_INPUT_WIDTH / 2),
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                blackAndWhite ? Color.WHITE : Color.BLACK);
        drawPauseBackInput();
        drawPauseQuitInput();
    }

    public void drawInputRectangle(int placement) {
        switch(placement) {
            case PAUSE_BACK : {
                game.draw.rect(game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT,
                        Color.WHITE);
            }
            case PAUSE_QUIT : {
                game.draw.rect((2 * game.partitionSize) + PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT,
                        Color.WHITE);
            }
        }
    }

    public void drawPauseQuitInput() {
        drawInputRectangle(PAUSE_QUIT);
        game.draw.drawStopSymbol(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                Color.BLACK);
    }

    public void drawPauseBackInput() {
        drawInputRectangle(PAUSE_BACK);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (PAUSE_INPUT_WIDTH / 2),
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void playMusic() {
        if(game.usePhases) {
            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
                game.trackMapPhase.get(game.track).get(i).play();
            }
        } else {
            game.trackMapFull.get(game.track).play();
        }
    }

    public void stopMusic() {
        if(game.usePhases) {
            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
                game.trackMapPhase.get(game.track).get(i).stop();
            }
        } else {
            game.trackMapFull.get(game.track).stop();
        }
    }

    public void drawSkipTextBox() {
        game.draw.rect((3 * game.camera.viewportWidth) / 8,
                game.camera.viewportHeight / 4,
                game.camera.viewportWidth / 4,
                game.camera.viewportHeight / 10,
                blackAndWhite ? Color.BLACK : Color.WHITE);
    }

    public void drawSkipText() {
        FontUtils.printText(game.batch,
                game.fontSkip,
                game.layout,
                blackAndWhite ? Color.WHITE : Color.BLACK,
                SKIP_TEXT,
                game.camera.viewportWidth / 2,
                (game.camera.viewportHeight / 4) + (game.camera.viewportHeight / 20) + (game.fontSkip.getCapHeight() / 4),
                0,
                1);
    }

    //TODO: Add text for time of run and victory time (as well as new best text for each)
    public void drawText() {
        if(!paused) {
            if (!gameOver) {
                drawScoreText();

                //drawHandText();

                //drawTargetText();

                drawSquirgleText();

                //TODO: Decide if I actually want to instate this behavior, and if so, make a new BitmapFont object in Squirgle class
                //Input Numbers
                /*for(int i = 1; i <= game.base; i++) {
                    FontUtils.printText(game.batch,
                            game.font,
                            game.layout,
                            Color.WHITE,
                            String.valueOf(i),
                            ((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS)) + (INPUT_RADIUS * FONT_MULTIPLIER_INPUT),
                            (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS) + (INPUT_RADIUS * FONT_MULTIPLIER_INPUT),
                            SCORE_ANGLE,
                            1);
                }*/
            } else {
                if(!paused && !showResults && (System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < SKIP_TEXT_DISAPPEARANCE_TIME) {
                    drawSkipText();
                }
            }

            if (showResults && !shouldUnlockNewBase) {
                List<Shape> priorShapeListToUse = new ArrayList<Shape>();
                if(!splitScreen) {
                    priorShapeListToUse = priorShapeList;
                } else if(useSaturation) {
                    if(saturationP1 > saturationP2) {
                        priorShapeListToUse = priorShapeListP2;
                    } else {
                        priorShapeListToUse = priorShapeListP1;
                    }
                } else {
                    if(scoreP1 >= scoreP2) {
                        priorShapeListToUse = priorShapeListP1;
                    } else {
                        priorShapeListToUse = priorShapeListP2;
                    }
                }
                if(!blackAndWhite) {
                    if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() == Shape.LINE || priorShapeListToUse.get(0).getShape() == Shape.POINT)) {
                        resultsColor = Color.BLACK;
                    } else {
                        resultsColor = Color.WHITE;
                    }
                } else {
                    if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() != Shape.LINE && priorShapeListToUse.get(0).getShape() != Shape.POINT)) {
                        resultsColor = Color.BLACK;
                    } else {
                        resultsColor = Color.WHITE;
                    }
                }
                if(!splitScreen) {
                    if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                        long minutesPlayed = MathUtils.floor((endTime - startTime) / 1000 / 60);
                        long secondsPlayed = MathUtils.floor((endTime - startTime) / 1000 - (minutesPlayed * 60));
                        String timePlayed = minutesPlayed + MINUTES + secondsPlayed + SECONDS;

                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                String.valueOf(score),
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((2 * (game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS))) / 3),
                                0,
                                1);
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                timePlayed,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 3),
                                0,
                                1);
                        if(newHighScore) {
                            FontUtils.printText(game.batch,
                                    game.fontScore,
                                    game.layout,
                                    currentTargetShape.getColor(),
                                    NEW_BEST,
                                    (3 * game.camera.viewportWidth) / 4,
                                    INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((2 * (game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS))) / 3),
                                    0,
                                    1);
                        }
                        if(newLongestRun) {
                            FontUtils.printText(game.batch,
                                    game.fontScore,
                                    game.layout,
                                    currentTargetShape.getColor(),
                                    NEW_BEST,
                                    (3 * game.camera.viewportWidth) / 4,
                                    INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 3),
                                    0,
                                    1);
                        }
                    } else {
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                String.valueOf(score),
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2),
                                0,
                                1);
                        if(newHighScore) {
                            FontUtils.printText(game.batch,
                                    game.fontScore,
                                    game.layout,
                                    currentTargetShape.getColor(),
                                    NEW_BEST,
                                    (3 * game.camera.viewportWidth) / 4,
                                    INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2),
                                    0,
                                    1);
                        }
                    }
                } else {
                    if(useSaturation) {
                        long minutesPlayed = MathUtils.floor((endTime - startTime) / 1000 / 60);
                        long secondsPlayed = MathUtils.floor((endTime - startTime) / 1000 - (minutesPlayed * 60));
                        String timePlayed = minutesPlayed + MINUTES + secondsPlayed + SECONDS;

                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                saturationP1 > saturationP2 ? Squirgle.RESULTS_DEFEAT : saturationP1 < saturationP2 ? Squirgle.RESULTS_VICTORY : Squirgle.RESULTS_TIE,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((2 * (game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS))) / 3),
                                0,
                                1);
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                timePlayed,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 3),
                                0,
                                1);
                        if(newFastestVictory) {
                            FontUtils.printText(game.batch,
                                    game.fontScore,
                                    game.layout,
                                    currentTargetShapeP1.getColor(),
                                    NEW_BEST,
                                    (3 * game.camera.viewportWidth) / 4,
                                    INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 3),
                                    0,
                                    1);
                        }
                    } else {
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                scoreP1 < scoreP2 ? Squirgle.RESULTS_DEFEAT : scoreP1 > scoreP2 ? Squirgle.RESULTS_VICTORY : Squirgle.RESULTS_TIE,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2),
                                0,
                                1);
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                P1 + COLON + scoreP1,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 4),
                                0,
                                1);
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                P2 + COLON + scoreP2,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((3 * (game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS))) / 4),
                                0,
                                1);
                        if(newHighScore) {
                            FontUtils.printText(game.batch,
                                    game.fontScore,
                                    game.layout,
                                    currentTargetShapeP1.getColor(),
                                    NEW_BEST,
                                    (3 * game.camera.viewportWidth) / 4,
                                    INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 4),
                                    0,
                                    1);
                        }
                    }
                }
            }
        }
    }

    public void drawScoreText() {
        float targetPolypRadius = (TARGET_RADIUS / 4);
        float targetPolypOffset = (float)(Math.sqrt(Math.pow(TARGET_RADIUS, 2) + Math.pow(TARGET_RADIUS / 4, 2)) - TARGET_RADIUS);
        if(!splitScreen) {
            //Score
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    backgroundColorShape.getColor(),
                    String.valueOf(score),
                    game.camera.viewportWidth - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                    game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                    SCORE_ANGLE,
                    1);

            //Multiplier
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    Color.WHITE,
                    X + String.valueOf(multiplier),
                    game.camera.viewportWidth - (TARGET_RADIUS / 4),
                    game.camera.viewportHeight - TARGET_RADIUS + (game.fontScore.getCapHeight() / 5.5f),
                    SCORE_ANGLE,
                    1);

            if(!game.hardcore) {
                //Prompt number
                String promptNumber = String.valueOf(promptShape.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumber,
                        game.camera.viewportWidth - TARGET_RADIUS,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        0,
                        1);

                //Target number
                String targetNumber = String.valueOf(currentTargetShape.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShape.getColor(),
                        targetNumber,
                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        0,
                        1);
            }
        } else if(blackAndWhite) {
            //We're in a time battle mode

            //Scores
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    backgroundColorShape.getColor(),
                    String.valueOf(scoreP1),
                    game.camera.viewportWidth - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                    (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                    SCORE_ANGLE,
                    1);
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    backgroundColorShape.getColor(),
                    String.valueOf(scoreP2),
                    local && !game.desktop ? (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 2.1f) : game.camera.viewportWidth - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                    local && !game.desktop ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 2.1f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                    local && !game.desktop ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                    1);

            //Player designations
            game.layout.setText(game.fontTarget, P1);
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    !game.hardcore ? Color.WHITE : backgroundColorShape.getColor(),
                    P1,
                    !game.hardcore ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS,
                    !game.hardcore ? (game.camera.viewportHeight / 2) - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4) : (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                    0,
                    1);
            game.layout.setText(game.fontTarget, P2);
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    !game.hardcore ? Color.WHITE : backgroundColorShape.getColor(),
                    P2,
                    !game.hardcore ? (local && !game.desktop ? TARGET_RADIUS + targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius) : (local && !game.desktop ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS),
                    !game.hardcore ? (local && !game.desktop ? (game.camera.viewportHeight / 2) + (2 * targetPolypRadius) + ((3 * game.layout.height) / 4) : game.camera.viewportHeight - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4)) : (local && !game.desktop ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f)),
                    local && !game.desktop && !game.desktop ? Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES : 0,
                    1);

            //Multipliers
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    Color.WHITE,
                    X + multiplierP1,
                    game.camera.viewportWidth - (TARGET_RADIUS / 4) - (game.fontScore.getCapHeight() / 4.7f),
                    (game.camera.viewportHeight / 2) - ((3 * TARGET_RADIUS) / 4) - (game.fontScore.getCapHeight() / 4.7f),
                    SCORE_ANGLE,
                    1);
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    Color.WHITE,
                    X + multiplierP2,
                    local && !game.desktop ? (TARGET_RADIUS / 4) + (game.fontScore.getCapHeight() / 4.7f) : game.camera.viewportWidth - (TARGET_RADIUS / 4) - (game.fontScore.getCapHeight() / 4.7f),
                    local && !game.desktop ? (game.camera.viewportHeight / 2) + ((3 * TARGET_RADIUS) / 4) + (game.fontScore.getCapHeight() / 4.7f) : game.camera.viewportHeight - ((3 * TARGET_RADIUS) / 4) - (game.fontScore.getCapHeight() / 4.7f),
                    local && !game.desktop ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                    1);

            if(!game.hardcore) {
                //Prompt numbers
                String promptNumberP1 = String.valueOf(promptShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP1,
                        game.camera.viewportWidth - TARGET_RADIUS,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        0,
                        1);
                String promptNumberP2 = String.valueOf(promptShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP2,
                        local && !game.desktop ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS,
                        local && !game.desktop ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        local && !game.desktop ? 180 : 0,
                        1);

                //Target numbers
                String targetNumberP1 = String.valueOf(currentTargetShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP1.getColor(),
                        targetNumberP1,
                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        (game.camera.viewportHeight / 2) - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        0,
                        1);
                String targetNumberP2 = String.valueOf(currentTargetShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP2.getColor(),
                        targetNumberP2,
                        local && !game.desktop ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius + targetPolypOffset : TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        local && !game.desktop ? (game.camera.viewportHeight / 2) + targetPolypRadius - (game.fontScore.getCapHeight() / 5.5f) : game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        local && !game.desktop ? 180 : 0,
                        1);
            }
        } else {
            //We're in a non-time battle mode

            //Player designations
            game.layout.setText(game.fontTarget, P1);
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    !game.hardcore ? Color.WHITE : backgroundColorShape.getColor(),
                    P1,
                    !game.hardcore ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS,
                    !game.hardcore ? (game.camera.viewportHeight / 2) - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4) : (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                    0,
                    1);
            game.layout.setText(game.fontTarget, P2);
            FontUtils.printText(game.batch,
                    game.fontScore,
                    game.layout,
                    !game.hardcore ? Color.WHITE : backgroundColorShape.getColor(),
                    P2,
                    !game.hardcore ? (local && !game.desktop ? TARGET_RADIUS + targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius) : (local && !game.desktop ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS),
                    !game.hardcore ? (local && !game.desktop ? (game.camera.viewportHeight / 2) + (2 * targetPolypRadius) + ((3 * game.layout.height) / 4) : game.camera.viewportHeight - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4)) : (local && !game.desktop ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f)),
                    local && !game.desktop ? Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES : 0,
                    1);

            if(!game.hardcore) {
                //Prompt numbers
                String promptNumberP1 = String.valueOf(promptShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP1,
                        game.camera.viewportWidth - TARGET_RADIUS,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        0,
                        1);
                String promptNumberP2 = String.valueOf(promptShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP2,
                        local && !game.desktop ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS,
                        local && !game.desktop ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        local && !game.desktop ? 180 : 0,
                        1);

                //Target numbers
                String targetNumberP1 = String.valueOf(currentTargetShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP1.getColor(),
                        targetNumberP1,
                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        (game.camera.viewportHeight / 2) - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        0,
                        1);
                String targetNumberP2 = String.valueOf(currentTargetShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP2.getColor(),
                        targetNumberP2,
                        local && !game.desktop ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius + targetPolypOffset : TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        local && !game.desktop ? (game.camera.viewportHeight / 2) + targetPolypRadius - (game.fontScore.getCapHeight() / 5.5f) : game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        local && !game.desktop ? 180 : 0,
                        1);
            }
        }
    }

    public void drawHandText() {
        game.layout.setText(game.fontTarget, Squirgle.HAND);
        if(!splitScreen) {
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Squirgle.HAND,
                    game.camera.viewportWidth - (game.layout.width / 2),
                    game.camera.viewportHeight - TARGET_RADIUS + ((29 * game.layout.height) / 16),
                    SCORE_ANGLE,
                    1);
        } else {
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Squirgle.HAND,
                    game.camera.viewportWidth - (game.layout.width / 2),
                    (game.camera.viewportHeight / 2) - TARGET_RADIUS + ((29 * game.layout.height) / 16),
                    SCORE_ANGLE,
                    1);
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Squirgle.HAND,
                    local && !game.desktop ? game.layout.width / 2 : game.camera.viewportWidth - (game.layout.width / 2),
                    local && !game.desktop ? (game.camera.viewportHeight / 2) + TARGET_RADIUS - ((29 * game.layout.height) / 16) : game.camera.viewportHeight - TARGET_RADIUS + ((29 * game.layout.height) / 16),
                    local && !game.desktop ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                    1);
        }
    }

    public void drawTargetText() {
        float degree = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degreeP1 = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degreeP2 = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degrees = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        float degreesP1 = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        float degreesP2 = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        String shapeText = targetShapeList.get(0).getPrefix() + targetShapeList.get(1).getBridge() + outsideTargetShape.getSuffix();
        String shapeTextP1 = targetShapeListP1.get(0).getPrefix() + targetShapeListP1.get(1).getBridge() + outsideTargetShapeP1.getSuffix();
        String shapeTextP2 = targetShapeListP2.get(0).getPrefix() + targetShapeListP2.get(1).getBridge() + outsideTargetShapeP2.getSuffix();

        //Target Text
        if(!splitScreen) {
            for (int i = 0; i < Squirgle.TARGET.length(); i++, degrees += degree) {
                FontUtils.printText(game.batch,
                        game.fontTarget,
                        game.layout,
                        backgroundColorShape.getColor(),
                        Squirgle.TARGET.substring(i, i + 1),
                        (float) (TARGET_RADIUS * Math.cos(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth())))),
                        (float) (game.camera.viewportHeight + ((2 * game.fontTarget.getCapHeight()) / 3) + (TARGET_RADIUS * Math.sin(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                        degrees - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                        1);
            }
        } else {
            for(int i = 0; i < Squirgle.TARGET.length(); i++, degreesP1 += degreeP1, degreesP2 += degreeP2) {
                FontUtils.printText(game.batch,
                        game.fontTarget,
                        game.layout,
                        backgroundColorShape.getColor(),
                        Squirgle.TARGET.substring(i, i + 1),
                        (float) (TARGET_RADIUS * Math.cos(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth())))),
                        (float) ((game.camera.viewportHeight / 2) + ((2 * game.fontTarget.getCapHeight()) / 3) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                        degreesP1 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                        1);
                if(local && !game.desktop) {
                    FontUtils.printText(game.batch,
                            game.fontTarget,
                            game.layout,
                            backgroundColorShape.getColor(),
                            Squirgle.TARGET.substring(i, i + 1),
                            (float) (game.camera.viewportWidth - (TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                            (float) ((game.camera.viewportHeight / 2) - ((2 * game.fontTarget.getCapHeight()) / 3) - (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                            degreesP2 + TWO_HUNDRED_AND_SEVENTY_DEGREES,
                            1);
                } else {
                    FontUtils.printText(game.batch,
                            game.fontTarget,
                            game.layout,
                            backgroundColorShape.getColor(),
                            Squirgle.TARGET.substring(i, i + 1),
                            (float) (TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth())))),
                            (float) (game.camera.viewportHeight + ((2 * game.fontTarget.getCapHeight()) / 3) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                            degreesP2 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                            1);
                }
            }
        }

//        //Shape Text
//        degree = THIRTY_DEGREES / shapeText.length();
//        degreeP1 = THIRTY_DEGREES / shapeTextP1.length();
//        degreeP2 = THIRTY_DEGREES / shapeTextP2.length();
//        degrees = THREE_HUNDRED_AND_THIRTY_DEGREES;
//        degreesP1 = THREE_HUNDRED_AND_THIRTY_DEGREES;
//        degreesP2 = THREE_HUNDRED_AND_THIRTY_DEGREES;
//        if(!splitScreen) {
//            for (int i = 0; i < shapeText.length(); i++, degrees += degree) {
//                FontUtils.printText(game.batch,
//                        game.fontTarget,
//                        game.layout,
//                        Color.WHITE,
//                        shapeText.substring(i, i + 1),
//                        (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                        (float) (game.camera.viewportHeight + (TARGET_RADIUS * Math.sin(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                        degrees - TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                        1);
//            }
//        } else {
//            for(int i = 0; i < shapeTextP1.length(); i++, degreesP1 += degreeP1) {
//                FontUtils.printText(game.batch,
//                        game.fontTarget,
//                        game.layout,
//                        Color.WHITE,
//                        shapeTextP1.substring(i, i + 1),
//                        (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                        (float) ((game.camera.viewportHeight / 2) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                        degreesP1 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                        1);
//            }
//            for(int i = 0; i < shapeTextP2.length(); i++, degreesP2 += degreeP2) {
//                if(local && !game.desktop) {
//                    FontUtils.printText(game.batch,
//                            game.fontTarget,
//                            game.layout,
//                            Color.WHITE,
//                            shapeTextP2.substring(i, i + 1),
//                            (float) (game.camera.viewportWidth - (game.fontTarget.getCapHeight() / 3) - TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                            (float) ((game.camera.viewportHeight / 2) - (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                            degreesP2 + TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                            1);
//                } else {
//                    FontUtils.printText(game.batch,
//                            game.fontTarget,
//                            game.layout,
//                            Color.WHITE,
//                            shapeTextP2.substring(i, i + 1),
//                            (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                            (float) (game.camera.viewportHeight + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                            degreesP2 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                            1);
//                }
//            }
//        }
    }

    public void drawSquirgleText() {
        for(int i = 1; i <= SQUIRGLE.length(); i++) {
            if(!splitScreen) {
                FontUtils.printText(game.batch,
                        game.fontSquirgle,
                        game.layout,
                        Color.WHITE,
                        SQUIRGLE.substring(i - 1, i),
                        (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                        game.camera.viewportHeight / 2,
                        0,
                        squirgleOpacity);
            } else {
                if(local && !game.desktop) {
                    FontUtils.printText(game.batch,
                            game.fontSquirgle,
                            game.layout,
                            Color.WHITE,
                            SQUIRGLE.substring(i - 1, i),
                            game.camera.viewportWidth - ((i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS)),
                            (3 * game.camera.viewportHeight) / 4,
                            Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES,
                            squirgleOpacityP2);
                } else {
                    FontUtils.printText(game.batch,
                            game.fontSquirgle,
                            game.layout,
                            Color.WHITE,
                            SQUIRGLE.substring(i - 1, i),
                            (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                            (3 * game.camera.viewportHeight) / 4,
                            0,
                            squirgleOpacityP2);
                }
                FontUtils.printText(game.batch,
                        game.fontSquirgle,
                        game.layout,
                        Color.WHITE,
                        SQUIRGLE.substring(i - 1, i),
                        (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                        game.camera.viewportHeight / 4,
                        0,
                        squirgleOpacityP1);
            }
        }
    }

    public void drawTargetArcs() {
        if(!splitScreen) {
            game.draw.drawArc(0, game.camera.viewportHeight, targetArcStart, targetArcColor);
            if (targetArcStart > -Draw.NINETY_ONE_DEGREES) {
                targetArcStart -= TARGET_ARC_SPEED;
            }
        } else {
            game.draw.drawArc(0, game.camera.viewportHeight / 2, targetArcStartP1, targetArcColor);
            if(targetArcStartP1 > -Draw.NINETY_ONE_DEGREES) {
                targetArcStartP1 -= TARGET_ARC_SPEED;
            }
            if(local && !game.desktop) {
                game.draw.drawArc(game.camera.viewportWidth, game.camera.viewportHeight / 2, targetArcStartP2, targetArcColor);
                if(targetArcStartP2 > Draw.NINETY_ONE_DEGREES) {
                    targetArcStartP2 -= TARGET_ARC_SPEED;
                }
            } else {
                game.draw.drawArc(0, game.camera.viewportHeight, targetArcStartP2, targetArcColor);
                if(targetArcStartP2 > -Draw.NINETY_ONE_DEGREES) {
                    targetArcStartP2 -= TARGET_ARC_SPEED;
                }
            }
        }
    }

    public void increasePromptRadius() {
        if(!paused) {
            promptShape.setRadius(gameOver ? promptShape.getRadius() * promptIncrease : promptShape.getRadius() + (promptIncrease / 2));
        }
    }

    public void managePromptRadii() {
        if(!paused) {
            if(useSaturation) {
                float screenRemainderP1 = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth / 4 : (game.camera.viewportHeight / 8);
                float screenRemainderP2 = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth / 4 : (game.camera.viewportHeight / 8);
                promptShapeP1.setRadius(gameOver ? promptShapeP1.getRadius() * promptIncrease : INIT_PROMPT_RADIUS + ((float) saturationP1 / MAX_SATURATION) * screenRemainderP1);
                promptShapeP2.setRadius(gameOver ? promptShapeP2.getRadius() * promptIncrease : INIT_PROMPT_RADIUS + ((float) saturationP2 / MAX_SATURATION) * screenRemainderP2);
            } else {
                promptShapeP1.setRadius(gameOver ? promptShapeP1.getRadius() * promptIncrease : promptShapeP1.getRadius() + (promptIncrease / 4));
                promptShapeP2.setRadius(gameOver ? promptShapeP2.getRadius() * promptIncrease : promptShapeP2.getRadius() + (promptIncrease / 4));
            }
        }
    }

    public void increaseDummyPromptRadius() {
        if(!paused && !gameOver) {
            dummyPromptForTimelines.setRadius(dummyPromptForTimelines.getRadius() + (promptIncrease / 2));
        }
    }

    public void managePrimaryShapeLineWidth() {
        if(!paused) {
            if(!splitScreen) {
                if (!primaryShapeAtThreshold) {
                    promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
                }
            } else {
                if (!primaryShapeAtThresholdP1) {
                    promptShapeP1.setLineWidth(promptShapeP1.getRadius() / Draw.LINE_WIDTH_DIVISOR);
                }
                if (!primaryShapeAtThresholdP2) {
                    promptShapeP2.setLineWidth(promptShapeP2.getRadius() / Draw.LINE_WIDTH_DIVISOR);
                }
            }
        }
    }

    public void drawBackgroundColorShape() {
        if(!paused) {
            if (!gameOver) {
                game.draw.drawBackgroundColorShape(backgroundColorShape);
            }
        }
    }

    public void increaseSpeed() {
        if(!gameOver) {
            if(!paused) {
                if ((System.currentTimeMillis() - lastSpeedIncreaseTime - timePaused) / ONE_THOUSAND > TEN_SECONDS) {
                    lastSpeedIncreaseTime = System.currentTimeMillis();
                    game.draw.setColorListSpeed(game.draw.getColorListSpeed() + COLOR_LIST_SPEED_ADDITIVE);
                    game.draw.setColorSpeed(game.draw.getColorSpeed() + COLOR_SPEED_ADDITIVE);
                    promptIncrease = (game.widthOrHeightSmaller * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
                }
            }
        }
    }

    public void maintainSpeed() {
        if(!gameOver) {
            if(!paused) {
                float actualFPS = Gdx.graphics.getRawDeltaTime() * game.FPS;
                if(blackAndWhite) {
                    game.draw.setColorListSpeed((NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT) / (game.timeAttackNumSeconds * actualFPS * game.FPS));
                } else {
                    //We're in Battle mode here
                    game.draw.setColorListSpeed((NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT) / (game.THIRTY_SECONDS * actualFPS * game.FPS));
                }
                promptIncrease = (game.widthOrHeightSmaller * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
            }
        }
    }

    public void decrementSquirgleOpacity() {
        if(squirgleOpacity > 0) {
            squirgleOpacity -= SQUIRGLE_OPACITY_DECREMENT;
        } else {
            squirgleOpacity = 0;
        }
    }

    public void decrementSquirgleOpacities() {
        if(squirgleOpacityP1 > 0) {
            squirgleOpacityP1 -= SQUIRGLE_OPACITY_DECREMENT;
        } else {
            squirgleOpacityP1 = 0;
        }
        if(squirgleOpacityP2 > 0) {
            squirgleOpacityP2 -= SQUIRGLE_OPACITY_DECREMENT;
        } else {
            squirgleOpacityP2 = 0;
        }
    }

    public void zoomThroughShapes() {
        if(!paused) {
            if(gameOver) {
                if ((System.currentTimeMillis() - endTime) / ONE_THOUSAND > TWO_SECONDS) {
                    Shape promptShapeToUse = new Shape();
                    if(!splitScreen) {
                        promptShapeToUse = promptShape;
                    } else if(useSaturation) {
                        if(saturationP1 > saturationP2) {
                            promptShapeToUse = promptShapeP2;
                        } else {
                            promptShapeToUse = promptShapeP1;
                        }
                    } else {
                        if(scoreP1 >= scoreP2) {
                            promptShapeToUse = promptShapeP1;
                        } else {
                            promptShapeToUse = promptShapeP2;
                        }
                    }
                    if(promptShapeToUse == promptShape) {
                        if (!primaryShapeAtThreshold) { //TODO: Also account for height (different screen orientations?)
                            promptIncrease += PROMPT_INCREASE_ADDITIVE;
                            promptShape.setCoordinates(new Vector2(promptShape.getCoordinates().x - (primaryShape.getCoordinates().x - firstPriorShapePreviousX),
                                    promptShape.getCoordinates().y));
                        } else {
                            promptIncrease = 1;
                            if (primaryShape.getShape() == Shape.LINE && primaryShape.getLineWidth() < (game.camera.viewportWidth * 4)) {
                                primaryShape.setLineWidth(primaryShape.getLineWidth() * END_LINE_WIDTH_INCREASE);
                            } else if (primaryShape.getShape() == Shape.LINE) {
                                //Prevent shape lines from being visible in the event that primaryShape is promptShape
                                clearColor = primaryShape.getColor();
                            }
                            if (primaryShape.getShape() != Shape.LINE || primaryShape.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                                showResults = true;
                            }
                        }
                    } else if(promptShapeToUse == promptShapeP1) {
                        if (!primaryShapeAtThresholdP1) { //TODO: Also account for height (different screen orientations?)
                            promptIncrease += PROMPT_INCREASE_ADDITIVE;
                            promptShapeP1.setCoordinates(new Vector2(promptShapeP1.getCoordinates().x - (primaryShapeP1.getCoordinates().x - firstPriorShapePreviousXP1),
                                    promptShapeP1.getCoordinates().y));
                        } else {
                            promptIncrease = 1;
                            if (primaryShapeP1.getShape() == Shape.LINE && primaryShapeP1.getLineWidth() < (game.camera.viewportWidth * 4)) {
                                primaryShapeP1.setLineWidth(primaryShapeP1.getLineWidth() * END_LINE_WIDTH_INCREASE);
                            } else if (primaryShapeP1.getShape() == Shape.LINE) {
                                //Prevent shape lines from being visible in the event that primaryShape is promptShape
                                clearColor = primaryShape.getColor();
                            }
                            if (primaryShapeP1.getShape() != Shape.LINE || primaryShapeP1.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                                showResults = true;
                            }
                        }
                    } else {
                        if (!primaryShapeAtThresholdP2) { //TODO: Also account for height (different screen orientations?)
                            promptIncrease += PROMPT_INCREASE_ADDITIVE;
                            promptShapeP2.setCoordinates(new Vector2(promptShapeP2.getCoordinates().x - (primaryShapeP2.getCoordinates().x - firstPriorShapePreviousXP2),
                                    promptShapeP2.getCoordinates().y));
                        } else {
                            promptIncrease = 1;
                            if (primaryShapeP2.getShape() == Shape.LINE && primaryShapeP2.getLineWidth() < (game.camera.viewportWidth * 4)) {
                                primaryShapeP2.setLineWidth(primaryShapeP2.getLineWidth() * END_LINE_WIDTH_INCREASE);
                            } else if (primaryShapeP2.getShape() == Shape.LINE) {
                                //Prevent shape lines from being visible in the event that primaryShape is promptShape
                                clearColor = primaryShape.getColor();
                            }
                            if (primaryShapeP2.getShape() != Shape.LINE || primaryShapeP2.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                                showResults = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void drawEquations() {
        if(!paused) {
            if(!gameOver) {
                if(!splitScreen) {
                    if (equationWidth > 0) {
                        game.draw.drawEquation(false, null, lastShapeTouched, lastPromptShape, lastTargetShape, equationWidth);
                        equationWidth -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidth = 0;
                    }
                } else {
                    if(equationWidthP1 > 0) {
                        game.draw.drawEquation(false, P1, lastShapeTouchedP1, lastPromptShapeP1, lastTargetShapeP1, equationWidthP1);
                        equationWidthP1 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidthP1 = 0;
                    }
                    if(equationWidthP2 > 0) {
                        game.draw.drawEquation(local && !game.desktop, P2, lastShapeTouchedP2, lastPromptShapeP2, lastTargetShapeP2, equationWidthP2);
                        equationWidthP2 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidthP2 = 0;
                    }
                }
            }
        }
    }

    public void destroyOversizedShapes() {
        if(!paused) {
            //Prevent shapes from getting too large
            if(!splitScreen) {
                if (promptShape.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                    if (priorShapeList.size() > destructionIndex) {
                        promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                        for (int i = 0; i < destructionIndex; i++) {
                            priorShapeList.remove(priorShapeList.size() - 1);
                        }
                        destructionIndex = 2;
                    }
                }
            } else {
                if (promptShapeP1.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                    if (priorShapeListP1.size() > destructionIndex) {
                        promptShapeP1 = priorShapeListP1.get(priorShapeListP1.size() - destructionIndex);
                        for (int i = 0; i < destructionIndex; i++) {
                            priorShapeListP1.remove(priorShapeListP1.size() - 1);
                        }
                        destructionIndex = 2;
                    }
                }
                if (promptShapeP2.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                    if (priorShapeListP2.size() > destructionIndex) {
                        promptShapeP2 = priorShapeListP2.get(priorShapeListP2.size() - destructionIndex);
                        for (int i = 0; i < destructionIndex; i++) {
                            priorShapeListP2.remove(priorShapeListP2.size() - 1);
                        }
                        destructionIndex = 2;
                    }
                }
            }
        }
    }

    public void drawResultsInputButtons() {
        if(!paused) {
            if (showResults) {
                if(game.base == game.maxBase && score >= Squirgle.SCORE_TO_UNLOCK_NEW_BASE && game.maxBase < Squirgle.MAX_POSSIBLE_BASE && gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                    shouldUnlockNewBase = true;
                    unlockNewBase();
                } else {
                    game.draw.drawResultsInputButtons(resultsColor, INPUT_PLAY_SPAWN, INPUT_HOME_SPAWN, INPUT_EXIT_SPAWN);
                }
            }
        }
    }

    public void gameOver() {
        //Game over condition
        boolean gameOverCondition = false;
        if(!splitScreen) {
            gameOverCondition = promptShape.getRadius() >= game.widthOrHeightSmaller / 2 && !gameOver;
        } else {
            gameOverCondition = (dummyPromptForTimelines.getRadius() >= game.widthOrHeightSmaller / 2
                    || saturationP1 >= MAX_SATURATION || saturationP2 >= MAX_SATURATION)
                    && !gameOver;
        }
        if (gameOverCondition) {
            gameOver = true;
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
            veilOpacity = 1;
            clearColor.set(backgroundColorShape.getColor().r,
                    backgroundColorShape.getColor().g,
                    backgroundColorShape.getColor().b,
                    backgroundColorShape.getColor().a);
            game.stats.updateTimePlayed(endTime - startTime, gameplayType);
            newHighScore = game.stats.updateHighestScore(splitScreen ? scoreP1 : score, gameplayType, game.base, game.timeAttackNumSeconds, game.difficulty);
            game.stats.incrementNumTimesWonOrLost(scoreP1 > scoreP2 || saturationP1 < saturationP2, gameplayType, game.base, game.timeAttackNumSeconds, game.difficulty);
            if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                newLongestRun = game.stats.updateLongestRun(endTime - startTime, game.base);
            } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
                newFastestVictory = game.stats.updateFastestVictory(endTime - startTime, game.base, game.difficulty);
            }
            if(splitScreen) {
                //We have to set the radii here to prevent stuttering when zooming through the shapes.
                if (useSaturation) {
                    if (saturationP1 <= saturationP2) {
                        promptShapeP1.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP1.setCoordinates(new Vector2(promptShapeP1.getCoordinates().x, game.camera.viewportHeight / 2));
                    } else {
                        promptShapeP2.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP2.setCoordinates(new Vector2(promptShapeP2.getCoordinates().x, game.camera.viewportHeight / 2));
                    }
                } else {
                    if (scoreP1 >= scoreP2) {
                        promptShapeP1.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP1.setCoordinates(new Vector2(promptShapeP1.getCoordinates().x, game.camera.viewportHeight / 2));
                    } else {
                        promptShapeP2.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP2.setCoordinates(new Vector2(promptShapeP2.getCoordinates().x, game.camera.viewportHeight / 2));
                    }
                }
            }
        }
    }

    public void determineTouchedInput(int screenX, int screenY) {
        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        if(!splitScreen) {
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
            pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                    && touchPoint.y > ((game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (((game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2)) - (game.camera.viewportWidth / 20)
                    && touchPoint.y < ((game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (((game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2)) + (game.camera.viewportWidth / 20);
        } else {
            pointTouchedP1 = touchPoint.x > INPUT_POINT_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_POINT_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_POINT_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS;
            lineTouchedP1 = touchPoint.x > INPUT_LINE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_LINE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_LINE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_LINE_SPAWN_P1.y + INPUT_RADIUS;
            triangleTouchedP1 = touchPoint.x > INPUT_TRIANGLE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_TRIANGLE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_TRIANGLE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_TRIANGLE_SPAWN_P1.y + INPUT_RADIUS;
            squareTouchedP1 = touchPoint.x > INPUT_SQUARE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SQUARE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SQUARE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SQUARE_SPAWN_P1.y + INPUT_RADIUS;
            pentagonTouchedP1 = touchPoint.x > INPUT_PENTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_PENTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_PENTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_PENTAGON_SPAWN_P1.y + INPUT_RADIUS;
            hexagonTouchedP1 = touchPoint.x > INPUT_HEXAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_HEXAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_HEXAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_HEXAGON_SPAWN_P1.y + INPUT_RADIUS;
            septagonTouchedP1 = touchPoint.x > INPUT_SEPTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SEPTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SEPTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SEPTAGON_SPAWN_P1.y + INPUT_RADIUS;
            octagonTouchedP1 = touchPoint.x > INPUT_OCTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_OCTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_OCTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_OCTAGON_SPAWN_P1.y + INPUT_RADIUS;
            nonagonTouchedP1 = touchPoint.x > INPUT_NONAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_NONAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_NONAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_NONAGON_SPAWN_P1.y + INPUT_RADIUS;
            if(game.widthGreater) {
                pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 40)
                        && touchPoint.y > (((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2)) - (game.camera.viewportWidth / 40)
                        && touchPoint.y < (((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2)) + (game.camera.viewportWidth / 40);
            } else {
                pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                        && touchPoint.y > (((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2)) - (game.camera.viewportWidth / 20)
                        && touchPoint.y < (((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2)) + (game.camera.viewportWidth / 20);
            }
            if(multiplayer) {
                pointTouchedP2 = touchPoint.x > INPUT_POINT_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_POINT_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_POINT_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_POINT_SPAWN_P2.y + INPUT_RADIUS;
                lineTouchedP2 = touchPoint.x > INPUT_LINE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_LINE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_LINE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_LINE_SPAWN_P2.y + INPUT_RADIUS;
                triangleTouchedP2 = touchPoint.x > INPUT_TRIANGLE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_TRIANGLE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_TRIANGLE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_TRIANGLE_SPAWN_P2.y + INPUT_RADIUS;
                squareTouchedP2 = touchPoint.x > INPUT_SQUARE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_SQUARE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_SQUARE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_SQUARE_SPAWN_P2.y + INPUT_RADIUS;
                pentagonTouchedP2 = touchPoint.x > INPUT_PENTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_PENTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_PENTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_PENTAGON_SPAWN_P2.y + INPUT_RADIUS;
                hexagonTouchedP2 = touchPoint.x > INPUT_HEXAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_HEXAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_HEXAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_HEXAGON_SPAWN_P2.y + INPUT_RADIUS;
                septagonTouchedP2 = touchPoint.x > INPUT_SEPTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_SEPTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_SEPTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_SEPTAGON_SPAWN_P2.y + INPUT_RADIUS;
                octagonTouchedP2 = touchPoint.x > INPUT_OCTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_OCTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_OCTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_OCTAGON_SPAWN_P2.y + INPUT_RADIUS;
                nonagonTouchedP2 = touchPoint.x > INPUT_NONAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_NONAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_NONAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_NONAGON_SPAWN_P2.y + INPUT_RADIUS;
                inputTouchedGameplayP2 = pointTouchedP2 || lineTouchedP2 || triangleTouchedP2 || squareTouchedP2 || pentagonTouchedP2 || hexagonTouchedP2 || septagonTouchedP2 || octagonTouchedP2 || nonagonTouchedP2;
            }
        }
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
        pauseBackTouched = touchPoint.x > game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT;
        pauseQuitTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        if(!splitScreen) {
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        } else {
            inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        }
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void determineKeyedInput(int keycode) {
        if(!splitScreen) {
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
        } else {
            pointTouchedP1 = keycode == Input.Keys.NUM_1 || keycode == Input.Keys.NUMPAD_1;
            lineTouchedP1 = keycode == Input.Keys.NUM_2 || keycode == Input.Keys.NUMPAD_2;
            triangleTouchedP1 = keycode == Input.Keys.NUM_3 || keycode == Input.Keys.NUMPAD_3;
            squareTouchedP1 = keycode == Input.Keys.NUM_4 || keycode == Input.Keys.NUMPAD_4;
            pentagonTouchedP1 = keycode == Input.Keys.NUM_5 || keycode == Input.Keys.NUMPAD_5;
            hexagonTouchedP1 = keycode == Input.Keys.NUM_6 || keycode == Input.Keys.NUMPAD_6;
            septagonTouchedP1 = keycode == Input.Keys.NUM_7 || keycode == Input.Keys.NUMPAD_7;
            octagonTouchedP1 = keycode == Input.Keys.NUM_8 || keycode == Input.Keys.NUMPAD_8;
            nonagonTouchedP1 = keycode == Input.Keys.NUM_9 || keycode == Input.Keys.NUMPAD_9;
            playTouched = pointTouchedP1;
            homeTouched = lineTouchedP1;
            exitTouched = triangleTouchedP1;
        }
        pauseTouched = keycode == Input.Keys.ESCAPE;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
        if(!splitScreen) {
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        } else {
            inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        }
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void handleInput(String player) {
        if(!paused) {
            if (!gameOver) {
                if(player == null) {
                    if (pointTouched) {
                        transitionShape(null, Shape.POINT);
                    } else if (lineTouched) {
                        transitionShape(null, Shape.LINE);
                    } else if (triangleTouched) {
                        transitionShape(null, Shape.TRIANGLE);
                    } else if (squareTouched) {
                        transitionShape(null, Shape.SQUARE);
                    } else if (pentagonTouched) {
                        transitionShape(null, Shape.PENTAGON);
                    } else if (hexagonTouched) {
                        transitionShape(null, Shape.HEXAGON);
                    } else if (septagonTouched) {
                        transitionShape(null, Shape.SEPTAGON);
                    } else if (octagonTouched) {
                        transitionShape(null, Shape.OCTAGON);
                    } else if (nonagonTouched) {
                        transitionShape(null, Shape.NONAGON);
                    } else if (pauseTouched) {
                        pause();
                        pauseTouched = false;
                        pauseBackTouched = false;
                        pauseQuitTouched = false;
                    }
                } else if(player.equals(P1)) {
                    if (pointTouchedP1) {
                        transitionShape(P1, Shape.POINT);
                    } else if (lineTouchedP1) {
                        transitionShape(P1, Shape.LINE);
                    } else if (triangleTouchedP1) {
                        transitionShape(P1, Shape.TRIANGLE);
                    } else if (squareTouchedP1) {
                        transitionShape(P1, Shape.SQUARE);
                    } else if (pentagonTouchedP1) {
                        transitionShape(P1, Shape.PENTAGON);
                    } else if (hexagonTouchedP1) {
                        transitionShape(P1, Shape.HEXAGON);
                    } else if (septagonTouchedP1) {
                        transitionShape(P1, Shape.SEPTAGON);
                    } else if (octagonTouchedP1) {
                        transitionShape(P1, Shape.OCTAGON);
                    } else if (nonagonTouchedP1) {
                        transitionShape(P1, Shape.NONAGON);
                    } else if (pauseTouched) {
                        pause();
                        pauseTouched = false;
                        pauseBackTouched = false;
                        pauseQuitTouched = false;
                    }
                } else if(player.equals(P2)) {
                    if (pointTouchedP2) {
                        transitionShape(P2, Shape.POINT);
                    } else if (lineTouchedP2) {
                        transitionShape(P2, Shape.LINE);
                    } else if (triangleTouchedP2) {
                        transitionShape(P2, Shape.TRIANGLE);
                    } else if (squareTouchedP2) {
                        transitionShape(P2, Shape.SQUARE);
                    } else if (pentagonTouchedP2) {
                        transitionShape(P2, Shape.PENTAGON);
                    } else if (hexagonTouchedP2) {
                        transitionShape(P2, Shape.HEXAGON);
                    } else if (septagonTouchedP2) {
                        transitionShape(P2, Shape.SEPTAGON);
                    } else if (octagonTouchedP2) {
                        transitionShape(P2, Shape.OCTAGON);
                    } else if (nonagonTouchedP2) {
                        transitionShape(P2, Shape.NONAGON);
                    } else if (pauseTouched) {
                        pause();
                        pauseTouched = false;
                        pauseBackTouched = false;
                        pauseQuitTouched = false;
                    }
                }
            }
            if(player == null) {
                if (inputTouchedGameplay && !gameOver && promptShape.getShape() == currentTargetShape.getShape()) {
                    shapesMatchedBehavior(null);
                } else if (!gameOver && inputTouchedGameplay) {
                    shapesMismatchedBehavior(null);
                }
            } else if(player.equals(P1)) {
                if (inputTouchedGameplayP1 && !gameOver && promptShapeP1.getShape() == currentTargetShapeP1.getShape()) {
                    shapesMatchedBehavior(P1);
                } else if (!gameOver && inputTouchedGameplayP1) {
                    shapesMismatchedBehavior(P1);
                }
            } else {
                if (inputTouchedGameplayP2 && !gameOver && promptShapeP2.getShape() == currentTargetShapeP2.getShape()) {
                    shapesMatchedBehavior(P2);
                } else if (!gameOver && inputTouchedGameplayP2) {
                    shapesMismatchedBehavior(P2);
                }
            }
            if(player == null || player.equals(P1)) {
                if (inputTouchedResults && showResults) {
                    handleResultsInput();
                }
            }
        } else {
            handlePauseInput();
        }
    }

    public void handleResultsInput() {
        if (playTouched) {
            stopMusic();
            game.setScreen(new GameplayScreen(game, gameplayType));
        } else if (homeTouched) {
            stopMusic();
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
        } else {
            dispose();
            System.exit(0);
        }
        dispose();
    }

    public void handlePauseInput() {
        if (pauseBackTouched) {
            timePaused += System.currentTimeMillis() - pauseStartTime;
            resume();
        } else if (pauseQuitTouched) {
            endTime = System.currentTimeMillis();
            game.stats.updateTimePlayed(endTime - startTime, gameplayType);
            stopMusic();
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
            dispose();
        }
    }

    public void transitionShape(String player, int shapeAdded) {
        if(player == null) {
            lastShapeTouched.setShape(shapeAdded);
            lastPromptShape.setShape(promptShape.getShape());
            if (lastPromptShape.getShape() == Shape.POINT) {
                lastPromptShape.setRadius(promptShape.getRadius() / 2);
            } else {
                lastPromptShape.setRadius(promptShape.getRadius());
            }
            lastTargetShape.setShape(currentTargetShape.getShape());
            equationWidth = INPUT_RADIUS;
            if (promptShape.getShape() + (shapeAdded + 1) >= game.base) {
                promptShape.setShape((promptShape.getShape() + (shapeAdded + 1)) - game.base);
            } else {
                promptShape.setShape(promptShape.getShape() + (shapeAdded + 1));
            }
        } else if(player.equals("P1")) {
            lastShapeTouchedP1.setShape(shapeAdded);
            lastPromptShapeP1.setShape(promptShapeP1.getShape());
            if (lastPromptShapeP1.getShape() == Shape.POINT) {
                lastPromptShapeP1.setRadius(promptShapeP1.getRadius() / 2);
            } else {
                lastPromptShapeP1.setRadius(promptShapeP1.getRadius());
            }
            lastTargetShapeP1.setShape(currentTargetShapeP1.getShape());
            equationWidthP1 = INPUT_RADIUS;
            if (promptShapeP1.getShape() + (shapeAdded + 1) >= game.base) {
                promptShapeP1.setShape((promptShapeP1.getShape() + (shapeAdded + 1)) - game.base);
            } else {
                promptShapeP1.setShape(promptShapeP1.getShape() + (shapeAdded + 1));
            }
        } else {
            lastShapeTouchedP2.setShape(shapeAdded);
            lastPromptShapeP2.setShape(promptShapeP2.getShape());
            if (lastPromptShapeP2.getShape() == Shape.POINT) {
                lastPromptShapeP2.setRadius(promptShapeP2.getRadius() / 2);
            } else {
                lastPromptShapeP2.setRadius(promptShapeP2.getRadius());
            }
            lastTargetShapeP2.setShape(currentTargetShapeP2.getShape());
            equationWidthP2 = INPUT_RADIUS;
            if (promptShapeP2.getShape() + (shapeAdded + 1) >= game.base) {
                promptShapeP2.setShape((promptShapeP2.getShape() + (shapeAdded + 1)) - game.base);
            } else {
                promptShapeP2.setShape(promptShapeP2.getShape() + (shapeAdded + 1));
            }
        }
    }

    public void shapesMatchedBehavior(String player) {
        if(player == null) {
            targetShapesMatched++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShape.getRadius(),
                    blackAndWhite ? Color.WHITE : Color.BLACK,
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
                currentTargetShape.setColor(blackAndWhite ? Color.WHITE : priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShape = outsideTargetShape;
            } else {
                targetShapesMatched = 0;
                score += multiplier;
                if (multiplier < MAX_MULTIPLIER) {
                    multiplier++;
                }
                targetShapeList.clear();
                if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle) {
                    //SQUIRGLE!!!
                    outsideTargetShape.setShape(Shape.TRIANGLE);
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetArcStart = Draw.NINETY_ONE_DEGREES;
                    targetArcColor = priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor();
                } else {
                    outsideTargetShape.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if (targetShapeList.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
                        while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                            outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE && admitsOfSquirgle) {
                    //SQUIRGLE MATCHED!!!
                    if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                        promptShape.setRadius(INIT_PROMPT_RADIUS);
                        squirgleOpacity = 1;
                    }
                    game.stats.incrementNumSquirgles(gameplayType, game.base, game.difficulty);
                }
                currentTargetShape = targetShapeList.get(0);
            }
        } else if(player.equals(P1)) {
            targetShapesMatchedP1++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShapeP1.getRadius(),
                    blackAndWhite ? Color.WHITE : Color.BLACK,
                    null,
                    promptShapeP1.getLineWidth(),
                    promptShapeP1.getCoordinates());
            Shape promptShapeToAdd = new Shape(promptShapeP1.getShape(),
                    promptShapeP1.getRadius(),
                    backgroundColorShape.getColor(),
                    null,
                    promptShapeP1.getLineWidth(),
                    promptShapeP1.getCoordinates());
            if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeListP1.isEmpty())) {
                promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
            }
            promptShapeP1.setShape(MathUtils.random(game.base - 1));
            priorShapeListP1.add(promptShapeToAdd);
            priorShapeListP1.add(circleContainer);
            if (targetShapesMatchedP1 == 1) {
                currentTargetShapeP1.setColor(blackAndWhite ? Color.WHITE : priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShapeP1 = outsideTargetShapeP1;
            } else {
                targetShapesMatchedP1 = 0;
                if(!useSaturation) {
                    scoreP1 += multiplierP1;
                    if (multiplierP1 < MAX_MULTIPLIER) {
                        multiplierP1++;
                    }
                }
                targetShapeListP1.clear();
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle) {
                    //SQUIRGLE!!!
                    outsideTargetShapeP1.setShape(Shape.TRIANGLE);
                    outsideTargetShapeP1.setColor(Color.BLACK);
                    targetShapeListP1.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP1.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetArcStartP1 = Draw.NINETY_ONE_DEGREES;
                    targetArcColor = priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getColor();
                } else {
                    outsideTargetShapeP1.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShapeP1.setColor(Color.BLACK);
                    targetShapeListP1.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP1.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if (targetShapeListP1.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
                        while (outsideTargetShapeP1.getShape() == Shape.TRIANGLE) {
                            outsideTargetShapeP1.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE && admitsOfSquirgle) {
                    //SQUIRGLE MATCHED!!!
                    if(useSaturation) {
                        saturationP1 -= 5;
                        saturationP2 += 3;
                        squirgleOpacityP1 = 1;
                    }
                    game.stats.incrementNumSquirgles(gameplayType, game.base, game.difficulty);
                } else if(useSaturation) {
                    saturationP2++;
                }
                currentTargetShapeP1 = targetShapeListP1.get(0);
            }
        } else {
            targetShapesMatchedP2++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShapeP2.getRadius(),
                    blackAndWhite ? Color.WHITE : Color.BLACK,
                    null,
                    promptShapeP2.getLineWidth(),
                    promptShapeP2.getCoordinates());
            Shape promptShapeToAdd = new Shape(promptShapeP2.getShape(),
                    promptShapeP2.getRadius(),
                    backgroundColorShape.getColor(),
                    null,
                    promptShapeP2.getLineWidth(),
                    promptShapeP2.getCoordinates());
            if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeListP2.isEmpty())) {
                promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
            }
            promptShapeP2.setShape(MathUtils.random(game.base - 1));
            priorShapeListP2.add(promptShapeToAdd);
            priorShapeListP2.add(circleContainer);
            if (targetShapesMatchedP2 == 1) {
                currentTargetShapeP2.setColor(blackAndWhite ? Color.WHITE : priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShapeP2 = outsideTargetShapeP2;
            } else {
                targetShapesMatchedP2 = 0;
                if(!useSaturation) {
                    scoreP2 += multiplierP2;
                    if (multiplierP2 < MAX_MULTIPLIER) {
                        multiplierP2++;
                    }
                }
                targetShapeListP2.clear();
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle) {
                    //SQUIRGLE!!!
                    outsideTargetShapeP2.setShape(Shape.TRIANGLE);
                    outsideTargetShapeP2.setColor(Color.BLACK);
                    targetShapeListP2.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP2.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if(local && !game.desktop) {
                        targetArcStartP2 = 3 * Draw.NINETY_ONE_DEGREES;
                    } else {
                        targetArcStartP2 = Draw.NINETY_ONE_DEGREES;
                    }
                    targetArcColor = priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getColor();
                } else {
                    outsideTargetShapeP2.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShapeP2.setColor(Color.BLACK);
                    targetShapeListP2.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP2.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if (targetShapeListP2.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
                        while (outsideTargetShapeP2.getShape() == Shape.TRIANGLE) {
                            outsideTargetShapeP2.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE && admitsOfSquirgle) {
                    //SQUIRGLE MATCHED!!!
                    if(useSaturation) {
                        saturationP2 -= 5;
                        saturationP1 += 3;
                        squirgleOpacityP2 = 1;
                    }
                    game.stats.incrementNumSquirgles(gameplayType, game.base, game.difficulty);
                } else {
                    if(useSaturation) {
                        saturationP1++;
                    }
                }
                currentTargetShapeP2 = targetShapeListP2.get(0);
            }
        }
        keepSaturationsInBounds();
    }

    public void shapesMismatchedBehavior(String player) {
        //The wrong shape was touched
        if(player == null) {
            if(!blackAndWhite) {
                float radiusIncrease = game.widthOrHeightSmaller * ((backgroundColorShapeList.get(2).getCoordinates().y - backgroundColorShapeList.get(3).getCoordinates().y) / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT));

                if (promptShape.getRadius() + radiusIncrease > (game.widthOrHeightSmaller / 2)) {
                    promptShape.setRadius(game.widthOrHeightSmaller / 2);
                } else {
                    promptShape.setRadius(promptShape.getRadius() + radiusIncrease);
                }
            } else {
                if(score > 0) {
                    score--;
                }
            }
            multiplier = 1;
        } else if(player.equals(P1)) {
            if(useSaturation) {
                saturationP1++;
                keepSaturationsInBounds();
            } else {
                multiplierP1 = 1;
                if(scoreP1 > 0) {
                    scoreP1--;
                }
            }
        } else if(player.equals(P2)) {
            if(useSaturation) {
                saturationP2++;
                keepSaturationsInBounds();
            } else {
                multiplierP2 = 1;
                if(scoreP2 > 0) {
                    scoreP2--;
                }
            }
        }
    }

    public void keepSaturationsInBounds() {
        if(saturationP1 < 0) {
            saturationP1 = 0;
        } else if(saturationP1 > MAX_SATURATION) {
            saturationP1 = MAX_SATURATION;
        }
        if(saturationP2 < 0) {
            saturationP2 = 0;
        } else if(saturationP2 > MAX_SATURATION) {
            saturationP2 = MAX_SATURATION;
        }
    }

    public void executeOpponenentAI() {
        int turnTime = 0;
        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            turnTime = 3;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            turnTime = 2;
        } else {
            turnTime = 1;
        }
        if((System.currentTimeMillis() - opponentTime) / ONE_THOUSAND >= turnTime) {
            //50% chance the player's opponent will do anything
            if(MathUtils.random(1) > 0) {
                inputTouchedGameplayP2 = true;
                if(currentTargetShapeP2.getShape() > promptShapeP2.getShape()) {
                    if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 1) {
                        pointTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 2) {
                        lineTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 3) {
                        triangleTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 4) {
                        squareTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 5) {
                        pentagonTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 6) {
                        hexagonTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 7) {
                        septagonTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 8) {
                        octagonTouchedP2 = true;
                    }
                } else if(currentTargetShapeP2.getShape() < promptShapeP2.getShape()) {
                    if(promptShapeP2.getShape() + Shape.POINT - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        pointTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.LINE - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        lineTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.TRIANGLE - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        triangleTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.SQUARE - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        squareTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.PENTAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        pentagonTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.HEXAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        hexagonTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.SEPTAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        septagonTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.OCTAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        octagonTouchedP2 = true;
                    }
                } else {
                    if(game.base == 4) {
                        squareTouchedP2 = true;
                    } else if(game.base == 5) {
                        pentagonTouchedP2 = true;
                    } else if(game.base == 6) {
                        hexagonTouchedP2 = true;
                    } else if(game.base == 7) {
                        septagonTouchedP2 = true;
                    } else if(game.base == 8) {
                        octagonTouchedP2 = true;
                    } else if(game.base == 9) {
                        nonagonTouchedP2 = true;
                    }
                }
                handleInput(P2);
                resetOpponentTouch();
            }
            opponentTime = System.currentTimeMillis();
        }
    }

    public void resetOpponentTouch() {
        pointTouchedP2 = false;
        lineTouchedP2 = false;
        triangleTouchedP2 = false;
        squareTouchedP2 = false;
        pentagonTouchedP2 = false;
        hexagonTouchedP2 = false;
        septagonTouchedP2 = false;
        octagonTouchedP2 = false;
        nonagonTouchedP2 = false;
        inputTouchedGameplayP2 = false;
    }

    public void unlockNewBase() {
        //Unlock new base options
        game.maxBase++;
        game.updateSave(game.SAVE_MAX_BASE, game.maxBase);

        Color passedBackgroundColor = new Color();
        if(primaryShape.getShape() == Shape.POINT || primaryShape.getShape() == Shape.LINE) {
            passedBackgroundColor = primaryShape.getColor();
        } else {
            passedBackgroundColor = Color.BLACK;
        }
        game.setScreen(new BaseUnlockScreen(game, passedBackgroundColor));
    }

    public void setUpNonFinalStaticData() {
        INPUT_RADIUS = splitScreen && game.widthGreater ? game.camera.viewportWidth / 38 : game.camera.viewportWidth / 19;
        for(int i = 1; i <= game.base; i++) {
            //P
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

            //P1
            inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
            switch(i) {
                case 1 : INPUT_POINT_SPAWN_P1 = inputVector;
                case 2 : INPUT_LINE_SPAWN_P1 = inputVector;
                case 3 : INPUT_TRIANGLE_SPAWN_P1 = inputVector;
                case 4 : INPUT_SQUARE_SPAWN_P1 = inputVector;
                case 5 : INPUT_PENTAGON_SPAWN_P1 = inputVector;
                case 6 : INPUT_HEXAGON_SPAWN_P1 = inputVector;
                case 7 : INPUT_SEPTAGON_SPAWN_P1 = inputVector;
                case 8 : INPUT_OCTAGON_SPAWN_P1 = inputVector;
                case 9 : INPUT_NONAGON_SPAWN_P1 = inputVector;
            }

            //P2
            if(local && !game.desktop) {
                inputVector = new Vector2(game.camera.viewportWidth - ((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS)), game.camera.viewportHeight - (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
                switch (i) {
                    case 1:
                        INPUT_POINT_SPAWN_P2 = inputVector;
                    case 2:
                        INPUT_LINE_SPAWN_P2 = inputVector;
                    case 3:
                        INPUT_TRIANGLE_SPAWN_P2 = inputVector;
                    case 4:
                        INPUT_SQUARE_SPAWN_P2 = inputVector;
                    case 5:
                        INPUT_PENTAGON_SPAWN_P2 = inputVector;
                    case 6:
                        INPUT_HEXAGON_SPAWN_P2 = inputVector;
                    case 7:
                        INPUT_SEPTAGON_SPAWN_P2 = inputVector;
                    case 8:
                        INPUT_OCTAGON_SPAWN_P2 = inputVector;
                    case 9:
                        INPUT_NONAGON_SPAWN_P2 = inputVector;
                }
            } else {
                inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (game.camera.viewportHeight / 2) + (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
                switch (i) {
                    case 1:
                        INPUT_POINT_SPAWN_P2 = inputVector;
                    case 2:
                        INPUT_LINE_SPAWN_P2 = inputVector;
                    case 3:
                        INPUT_TRIANGLE_SPAWN_P2 = inputVector;
                    case 4:
                        INPUT_SQUARE_SPAWN_P2 = inputVector;
                    case 5:
                        INPUT_PENTAGON_SPAWN_P2 = inputVector;
                    case 6:
                        INPUT_HEXAGON_SPAWN_P2 = inputVector;
                    case 7:
                        INPUT_SEPTAGON_SPAWN_P2 = inputVector;
                    case 8:
                        INPUT_OCTAGON_SPAWN_P2 = inputVector;
                    case 9:
                        INPUT_NONAGON_SPAWN_P2 = inputVector;
                }
            }
        }
        TARGET_RADIUS = splitScreen && game.widthGreater ? game.camera.viewportWidth / 10.24f : game.camera.viewportWidth / 5.12f;
        PAUSE_INPUT_WIDTH = (game.camera.viewportWidth - (4 * game.partitionSize)) / 3;
        PAUSE_INPUT_HEIGHT = game.camera.viewportHeight - (2 * game.partitionSize);
        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS = TARGET_RADIUS / 12;
        if(splitScreen) {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_Y = (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
            BACKGROUND_COLOR_SHAPE_LIST_MIN_Y = (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
        } else {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_Y = ((game.camera.viewportHeight - TARGET_RADIUS) - (((game.camera.viewportHeight - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
            BACKGROUND_COLOR_SHAPE_LIST_MIN_Y = ((game.camera.viewportHeight - TARGET_RADIUS) - (((game.camera.viewportHeight - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
        }
        BACKGROUND_COLOR_SHAPE_LIST_HEIGHT = BACKGROUND_COLOR_SHAPE_LIST_MAX_Y - BACKGROUND_COLOR_SHAPE_LIST_MIN_Y;
        if(blackAndWhite) {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_X = -BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
            BACKGROUND_COLOR_SHAPE_LIST_MIN_X = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1));
        } else {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_X = BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
            BACKGROUND_COLOR_SHAPE_LIST_MIN_X = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1));
        }
        BACKGROUND_COLOR_SHAPE_LIST_WIDTH = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - BACKGROUND_COLOR_SHAPE_LIST_MIN_X;
        COLOR_LIST_SPEED_ADDITIVE =  BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / 5000;
        INIT_PROMPT_RADIUS = splitScreen ? game.widthOrHeightSmaller / 8 : game.widthOrHeightSmaller / 4;
        if(splitScreen && game.widthGreater) {
            FONT_SCORE_SIZE_DIVISOR = 30f;
            FONT_TARGET_SIZE_DIVISOR = 71f;
            FONT_SQUIRGLE_SIZE_DIVISOR = 10f;
        } else {
            FONT_SCORE_SIZE_DIVISOR = 11.1f;
            FONT_TARGET_SIZE_DIVISOR = 35.5f;
            FONT_SQUIRGLE_SIZE_DIVISOR = 5f;
        }
        FONT_SKIP_SIZE_DIVISOR = 30f;
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
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        blackAndWhite ? Color.BLACK : ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(BACKGROUND_COLOR_SHAPE_LIST_MAX_X,
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_Y)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        blackAndWhite ? Color.BLACK : ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(BACKGROUND_COLOR_SHAPE_LIST_MAX_X,
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_Y - (i * (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1))))));
            }
        }

        //Set prompt increase such that without player input, three passes of backgroundColorShapeList will
        //occur before game over
        promptIncrease = (game.widthOrHeightSmaller * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
        targetArcStart = -Draw.NINETY_ONE_DEGREES;
        targetArcStartP1 = -Draw.NINETY_ONE_DEGREES;
        targetArcStartP2 = local && !game.desktop ? Draw.NINETY_ONE_DEGREES : -Draw.NINETY_ONE_DEGREES;
        squirgleOpacity = 0;
        squirgleOpacityP1 = 0;
        squirgleOpacityP2 = 0;
        promptShape = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS, Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        promptShapeP1 = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS,
                Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 4));
        promptShapeP2 = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS,
                Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        (3 * game.camera.viewportHeight) / 4));
        dummyPromptForTimelines = new Shape(Shape.POINT,
                INIT_PROMPT_RADIUS * 2,
                Color.WHITE,
                null,
                (INIT_PROMPT_RADIUS * 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(2 * game.camera.viewportWidth,
                        2 * game.camera.viewportHeight));
        lastShapeTouched = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, GameplayScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        lastShapeTouchedP1 = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP1.getCoordinates());
        lastShapeTouchedP2 = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP2.getCoordinates());
        lastPromptShape = new Shape(Shape.POINT, promptShape.getRadius(), Color.BLACK, Color.BLACK, GameplayScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        lastPromptShapeP1 = new Shape(Shape.POINT, promptShapeP1.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP1.getCoordinates());
        lastPromptShapeP2 = new Shape(Shape.POINT, promptShapeP2.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP2.getCoordinates());
        outsideTargetShape = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        outsideTargetShapeP1 = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        outsideTargetShapeP2 = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(local && !game.desktop ? game.camera.viewportWidth - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) : TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        local && !game.desktop ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) : game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        priorShapeList = new ArrayList<Shape>();
        priorShapeListP1 = new ArrayList<Shape>();
        priorShapeListP2 = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
        targetShapeListP1 = new ArrayList<Shape>();
        targetShapeListP2 = new ArrayList<Shape>();
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
        targetShapeListP1.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2))));
        targetShapeListP1.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2))));
        targetShapeListP2.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        targetShapeListP2.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        if (targetShapeList.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                outsideTargetShape.setShape(MathUtils.random(game.base - 1));
            }
        }
        if (targetShapeListP1.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
            while (outsideTargetShapeP1.getShape() == Shape.TRIANGLE) {
                outsideTargetShapeP1.setShape(MathUtils.random(game.base - 1));
            }
        }
        if (targetShapeListP2.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
            while (outsideTargetShapeP2.getShape() == Shape.TRIANGLE) {
                outsideTargetShapeP2.setShape(MathUtils.random(game.base - 1));
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
        currentTargetShapeP1 = targetShapeListP1.get(0);
        currentTargetShapeP2 = targetShapeListP2.get(0);
        lastTargetShape = currentTargetShape;
        lastTargetShapeP1 = currentTargetShapeP1;
        lastTargetShapeP2 = currentTargetShapeP2;
        targetShapesMatched = 0;
        targetShapesMatchedP1 = 0;
        targetShapesMatchedP2 = 0;
        touchPoint = new Vector3();
        pointTouched = false;
        lineTouched = false;
        triangleTouched = false;
        squareTouched = false;
        pentagonTouched = false;
        hexagonTouched = false;
        septagonTouched = false;
        octagonTouched = false;
        nonagonTouched = false;
        pointTouchedP1 = false;
        lineTouchedP1 = false;
        triangleTouchedP1 = false;
        squareTouchedP1 = false;
        pentagonTouchedP1 = false;
        hexagonTouchedP1 = false;
        septagonTouchedP1 = false;
        octagonTouchedP1 = false;
        nonagonTouchedP1 = false;
        pointTouchedP2 = false;
        lineTouchedP2 = false;
        triangleTouchedP2 = false;
        squareTouchedP2 = false;
        pentagonTouchedP2 = false;
        hexagonTouchedP2 = false;
        septagonTouchedP2 = false;
        octagonTouchedP2 = false;
        nonagonTouchedP2 = false;
        playTouched = false;
        homeTouched = false;
        exitTouched = false;
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        inputTouchedGameplay = false;
        inputTouchedGameplayP1 = false;
        inputTouchedGameplayP2 = false;
        inputTouchedResults = false;
        targetArcColor = new Color();
        clearColor = new Color(backgroundColorShape.getColor());
        score = 0;
        scoreP1 = 0;
        scoreP2 = 0;
        multiplier = 1;
        multiplierP1 = 1;
        multiplierP2 = 1;
        gameOver = false;
        showResults = false;
        paused = false;
        newHighScore = false;
        newLongestRun = false;
        newFastestVictory = false;
        skipZoom = false;
        shouldUnlockNewBase = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
        lastSpeedIncreaseTime = System.currentTimeMillis();
        pauseStartTime = 0;
        timePaused = 0;
        opponentTime = System.currentTimeMillis();
        timeSinceTouched = SKIP_TEXT_DISAPPEARANCE_TIME;
        equationWidth = 0;
        equationWidthP1 = 0;
        equationWidthP2 = 0;
        destructionIndex = 1;
        firstPriorShapePreviousX = 0;
        firstPriorShapePreviousXP1 = 0;
        firstPriorShapePreviousXP2 = 0;
        resultsColor = new Color();
        primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        primaryShapeP1 = priorShapeListP1.size() > 0 ? priorShapeListP1.get(0) : promptShapeP1;
        primaryShapeP2 = priorShapeListP2.size() > 0 ? priorShapeListP2.get(0) : promptShapeP2;

        primaryShapeThreshold = game.widthOrHeightSmaller * game.draw.THRESHOLD_MULTIPLIER;

        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP1 = primaryShapeP1.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP2 = primaryShapeP2.getRadius() >= primaryShapeThreshold;

        veilColor = Color.WHITE;
        veilOpacity = 0;
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
