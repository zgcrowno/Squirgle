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

//TODO: Replace instances of "60" with gameLengthInSeconds variable.
public class BattleSinglePlayerScreen implements Screen, InputProcessor {
    final Squirgle game;

    public static float INIT_PROMPT_RADIUS;
    public static float BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT_P1;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT_P1;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT_P2;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT_P2;
    public static float BACKGROUND_COLOR_SHAPE_LIST_WIDTH;
    public static float INPUT_RADIUS;
    public static float TARGET_RADIUS;
    public static float PAUSE_INPUT_WIDTH;
    public static float PAUSE_INPUT_HEIGHT;
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
    private final static int PLAYER_ANGLE = -45;
    private final static int ONE_THOUSAND = 1000;
    private final static int TWO_SECONDS = 2;
    private final static int TEN_SECONDS = 10;
    private final static int TARGET_ARC_SPEED = 5;
    private final static int COLOR_SPEED_ADDITIVE = 20;
    private final static int EQUATION_WIDTH_DIVISOR = 60;
    private final static int SHAPE_SIZE_LIMIT_MULTIPLIER = 15;
    private final static int ONE_SHAPE_AGO = 2;
    private final static int TWO_SHAPES_AGO = 4;
    private final static int THIRTY_DEGREES = 30;
    private final static int TWO_HUNDRED_AND_SEVENTY_DEGREES = 270;
    private final static int THREE_HUNDRED_AND_THIRTY_DEGREES = 330;

    private final static float FONT_PLAYER_SIZE_DIVISOR = 22.2f;
    private final static float FONT_TARGET_SIZE_DIVISOR = 71f;
    private final static float FONT_SQUIRGLE_SIZE_DIVISOR = 10;
    private final static float TARGET_RADIUS_DIVISOR = 2.43f;
    private final static float PLAYER_X_DIVISOR = 2.68f;
    private final static float PLAYER_Y_DIVISOR = 1.25f;
    private final static float COLOR_LIST_SPEED_ADDITIVE = 0.1f;
    private final static float PROMPT_INCREASE_ADDITIVE = .0001f;
    private final static float SQUIRGLE_OPACITY_DECREMENT = .03f;

    public final static String P1 = "P1";
    public final static String P2 = "P2";
    public final static int MAX_SATURATION = 15;
    private final static String SQUIRGLE = "SQUIRGLE";

    private float promptIncrease;
    private float equationWidthP1;
    private float equationWidthP2;
    private float targetArcStartP1;
    private float targetArcStartP2;
    private float squirgleOpacityP1;
    private float squirgleOpacityP2;
    private Shape promptShapeP1;
    private Shape promptShapeP2;
    private Shape dummyPromptForTimelines;
    private Shape lastShapeTouchedP1;
    private Shape lastShapeTouchedP2;
    private Shape lastPromptShapeP1;
    private Shape lastPromptShapeP2;
    private Shape outsideTargetShapeP1;
    private Shape outsideTargetShapeP2;
    private List<Shape> priorShapeListP1;
    private List<Shape> priorShapeListP2;
    private List<Shape> targetShapeListP1;
    private List<Shape> targetShapeListP2;
    private List<Shape> backgroundColorShapeList;
    private List<Shape> touchDownShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShapeP1;
    private Shape currentTargetShapeP2;
    private Shape lastTargetShapeP1;
    private Shape lastTargetShapeP2;
    private int targetShapesMatchedP1;
    private int targetShapesMatchedP2;
    private Vector3 touchPoint;
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
    boolean inputTouchedGameplayP1;
    boolean inputTouchedGameplayP2;
    boolean inputTouchedResults;
    private Color targetArcColor;
    private Color clearColor;
    private boolean gameOver;
    private boolean showResults;
    private boolean paused;
    private long startTime;
    private long endTime;
    private long pauseStartTime;
    private long timePaused;
    private long opponentTime;
    private int destructionIndex;
    private float firstPriorShapePreviousXP1;
    private float firstPriorShapePreviousXP2;
    private Color resultsColor;
    Shape primaryShapeP1;
    Shape primaryShapeP2;
    float primaryShapeThreshold;
    boolean primaryShapeAtThresholdP1;
    boolean primaryShapeAtThresholdP2;
    private int saturationP1;
    private int saturationP2;

    public BattleSinglePlayerScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontPlayer(MathUtils.round(game.camera.viewportWidth / FONT_PLAYER_SIZE_DIVISOR));
        game.setUpFontTarget(MathUtils.round(game.camera.viewportWidth / FONT_TARGET_SIZE_DIVISOR));
        game.setUpFontSquirgle(MathUtils.round(game.camera.viewportWidth / FONT_SQUIRGLE_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        setUpNonFinalNonStaticData();

        //TODO: Eventually set this in render using delta? See maintainSpeed() in TimeAttackScreen
        game.draw.setColorListSpeed(game.camera.viewportWidth / 1536);

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

        primaryShapeP1 = priorShapeListP1.size() > 0 ? priorShapeListP1.get(0) : promptShapeP1;
        primaryShapeP2 = priorShapeListP2.size() > 0 ? priorShapeListP2.get(0) : promptShapeP2;

        //TODO: Update this when I split horizontally or vertically depending on screen dimensions
        primaryShapeThreshold = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth * game.draw.THRESHOLD_MULTIPLIER : (game.camera.viewportHeight / 2) * game.draw.THRESHOLD_MULTIPLIER;

        primaryShapeAtThresholdP1 = primaryShapeP1.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP2 = primaryShapeP2.getRadius() >= primaryShapeThreshold;

        managePromptRadii();

        increaseDummyPromptRadius();

        managePrimaryShapeLineWidth();

        drawBackgroundColorShape();

        if(!paused) {
            game.draw.drawPrompt(promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false, game.shapeRendererFilled);
            game.draw.drawShapes(priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1, game.shapeRendererFilled);

            game.draw.drawPrompt(promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false, game.shapeRendererFilled);
            game.draw.drawShapes(priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2, game.shapeRendererFilled);
        }

        maintainSpeed();
        decrementSquirgleOpacities();
        zoomThroughShapes();

        if(!paused) {
            //This code is being executed three times: once before setting the prompt's end game coordinates, and again afterwards.
            //This way, the shapes are drawn with their new values, and the first element in priorShapeList doesn't veer off
            //the screen to the right.
            //TODO: separate draw methods out into distinct ones, one of which assigns radii and coordinates, and the other of
            //TODO: which actually draws the shapes. It's overkill to draw the shapes multiple times.
            game.draw.drawShapes(priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1, game.shapeRendererFilled);
            game.draw.drawShapes(priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2, game.shapeRendererFilled);

            if (!gameOver) {
                game.draw.drawPerimeter(promptShapeP1, game.shapeRendererLine);
                game.draw.drawPerimeter(promptShapeP2, game.shapeRendererLine);
                game.draw.drawScreenDivision(game.shapeRendererLine);
                game.draw.drawBackgroundColorShapeListBattleSinglePlayer(backgroundColorShapeList, backgroundColorShape, clearColor, game.shapeRendererFilled);
                game.draw.drawTimelines(dummyPromptForTimelines, backgroundColorShapeList, game.shapeRendererFilled);
                SoundUtils.playMusic(promptShapeP1, game);
                game.draw.drawTargetSemicirclesBattleSinglePlayer(game.shapeRendererFilled);
            }
        }

        executeOpponenentAI();

        drawEquations();

        if(!paused) {
            if (!gameOver) {
                game.draw.drawInputButtonsBattleSinglePlayer(game, game.shapeRendererFilled);
                game.draw.drawSaturationTrianglesBattleSinglePlayer(game.shapeRendererFilled);
                if(saturationP1 > 0) {
                    game.draw.drawSaturationBattleSinglePlayer(P1, saturationP1, game.shapeRendererFilled);
                }
                if(saturationP2 > 0) {
                    game.draw.drawSaturationBattleSinglePlayer(P2, saturationP2, game.shapeRendererFilled);
                }
                game.draw.drawSaturationIncrementsBattleSinglePlayer(backgroundColorShape.getColor(), game.shapeRendererFilled);
                game.draw.drawPrompt(outsideTargetShapeP1, targetShapeListP1, targetShapesMatchedP1, backgroundColorShape, false, true, game.shapeRendererFilled);
                game.draw.drawShapes(targetShapeListP1, outsideTargetShapeP1, false, game.shapeRendererFilled);
                game.draw.drawPrompt(outsideTargetShapeP2, targetShapeListP2, targetShapesMatchedP2, backgroundColorShape, false, true, game.shapeRendererFilled);
                game.draw.drawShapes(targetShapeListP2, outsideTargetShapeP2, false, game.shapeRendererFilled);
                game.draw.drawPauseInputBattleSinglePlayer(game);
                drawTargetArcs();
            }
        }

        destroyOversizedShapes();

        if(!paused) {
            firstPriorShapePreviousXP1 = primaryShapeP1.getCoordinates().x;
            firstPriorShapePreviousXP2 = primaryShapeP2.getCoordinates().x;
        }

        drawResultsInputButtons();

        if(!paused) {
            game.draw.drawTouchDownPoints(touchDownShapeList, game.shapeRendererLine);
        } else {
            drawInputRectangles();
        }

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        drawText();

        gameOver();

        ColorUtils.transitionColor(currentTargetShapeP1);
        ColorUtils.transitionColor(currentTargetShapeP2);
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
        //TODO: Maybe remove gameOver check once I add animations to screen switches
        if (button != Input.Buttons.LEFT || pointer > 0 || gameOver) {
            return false;
        }

        determineTouchedInput(screenX, screenY);

        addTouchDownShapes();

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        determineTouchedInput(screenX, screenY);

        handleInput(P1);

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

            handleInput(P1);

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
                Color.BLACK,
                game.shapeRendererFilled);
        drawPauseBackInput();
        drawPauseQuitInput();
    }

    public void drawInputRectangle(int placement) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case PAUSE_BACK : {
                game.shapeRendererFilled.rect(game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT);
            }
            case PAUSE_QUIT : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT);
            }
        }
    }

    public void drawPauseQuitInput() {
        drawInputRectangle(PAUSE_QUIT);
        game.draw.drawX(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawPauseBackInput() {
        drawInputRectangle(PAUSE_BACK);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (PAUSE_INPUT_WIDTH / 2),
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
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

    public void drawText() {
        if(!paused) {
            if (!gameOver) {
                drawPlayerText();

                drawTargetText();

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
            }

            if (showResults) {
                List<Shape> priorShapeListToUse = new ArrayList<Shape>();
                if(saturationP1 > saturationP2) {
                    priorShapeListToUse = priorShapeListP2;
                } else if(saturationP1 < saturationP2 || saturationP1 == saturationP2) {
                    priorShapeListToUse = priorShapeListP1;
                }
                if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() == Shape.LINE || priorShapeListToUse.get(0).getShape() == Shape.POINT)) {
                    resultsColor = Color.BLACK;
                } else {
                    resultsColor = Color.WHITE;
                }
                FontUtils.printText(game.batch,
                        game.fontPlayer,
                        game.layout,
                        resultsColor,
                        saturationP1 > saturationP2 ? Squirgle.RESULTS_DEFEAT : saturationP1 < saturationP2 ? Squirgle.RESULTS_VICTORY : Squirgle.RESULTS_TIE,
                        game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2,
                        0,
                        1);
            }
        }
    }

    public void drawPlayerText() {
        //Player Designations
        FontUtils.printText(game.batch,
                game.fontPlayer,
                game.layout,
                Color.WHITE,
                P2,
                game.camera.viewportWidth - (TARGET_RADIUS / PLAYER_X_DIVISOR),
                game.camera.viewportHeight - (TARGET_RADIUS / PLAYER_Y_DIVISOR),
                PLAYER_ANGLE,
                1);
        FontUtils.printText(game.batch,
                game.fontPlayer,
                game.layout,
                Color.WHITE,
                P1,
                game.camera.viewportWidth - (TARGET_RADIUS / PLAYER_X_DIVISOR),
                (game.camera.viewportHeight / 2) - (TARGET_RADIUS / PLAYER_Y_DIVISOR),
                PLAYER_ANGLE,
                1);
    }

    public void drawTargetText() {
        float degreeP1 = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degreeP2 = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degreesP1 = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        float degreesP2 = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        String shapeTextP1 = targetShapeListP1.get(0).getPrefix() + targetShapeListP1.get(1).getBridge() + outsideTargetShapeP1.getSuffix();
        String shapeTextP2 = targetShapeListP2.get(0).getPrefix() + targetShapeListP2.get(1).getBridge() + outsideTargetShapeP2.getSuffix();

        //Target Text
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

        //Shape Text
        degreeP1 = THIRTY_DEGREES / shapeTextP1.length();
        degreeP2 = THIRTY_DEGREES / shapeTextP2.length();
        degreesP1 = THREE_HUNDRED_AND_THIRTY_DEGREES;
        degreesP2 = THREE_HUNDRED_AND_THIRTY_DEGREES;
        for(int i = 0; i < shapeTextP1.length(); i++, degreesP1 += degreeP1) {
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    Color.WHITE,
                    shapeTextP1.substring(i, i + 1),
                    (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
                    (float) ((game.camera.viewportHeight / 2) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
                    degreesP1 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                    1);
        }
        for(int i = 0; i < shapeTextP2.length(); i++, degreesP2 += degreeP2) {
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    Color.WHITE,
                    shapeTextP2.substring(i, i + 1),
                    (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
                    (float) (game.camera.viewportHeight + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
                    degreesP2 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                    1);
        }
    }

    public void drawSquirgleText() {
        for(int i = 1; i <= SQUIRGLE.length(); i++) {
            FontUtils.printText(game.batch,
                    game.fontSquirgle,
                    game.layout,
                    Color.WHITE,
                    SQUIRGLE.substring(i - 1, i),
                    (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                    (3 * game.camera.viewportHeight) / 4,
                    0,
                    squirgleOpacityP2);
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

    public void drawTargetArcs() {
        game.draw.drawArcBattleSinglePlayer(game.camera.viewportHeight / 2, targetArcStartP1, targetArcColor, game.shapeRendererFilled);
        if(targetArcStartP1 > -Draw.NINETY_ONE_DEGREES) {
            targetArcStartP1 -= TARGET_ARC_SPEED;
        }
        game.draw.drawArcBattleSinglePlayer(game.camera.viewportHeight, targetArcStartP2, targetArcColor, game.shapeRendererFilled);
        if(targetArcStartP2 > -Draw.NINETY_ONE_DEGREES) {
            targetArcStartP2 -= TARGET_ARC_SPEED;
        }
    }

    public void managePromptRadii() {
        if(!paused) {
            float screenRemainderP1 = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth - (2 * promptShapeP1.getRadius()) : (game.camera.viewportHeight / 2) - (2 * promptShapeP1.getRadius());
            float screenRemainderP2 = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth - (2 * promptShapeP2.getRadius()) : (game.camera.viewportHeight / 2) - (2 * promptShapeP2.getRadius());
            promptShapeP1.setRadius(gameOver ? promptShapeP1.getRadius() * promptIncrease : INIT_PROMPT_RADIUS + (((saturationP1 / MAX_SATURATION) * screenRemainderP1) / 2));
            promptShapeP2.setRadius(gameOver ? promptShapeP2.getRadius() * promptIncrease : INIT_PROMPT_RADIUS + (((saturationP2 / MAX_SATURATION) * screenRemainderP2) / 2));
        }
    }

    public void increaseDummyPromptRadius() {
        if(!paused && !gameOver) {
            dummyPromptForTimelines.setRadius(dummyPromptForTimelines.getRadius() + (promptIncrease / 2));
        }
    }

    public void managePrimaryShapeLineWidth() {
        if(!paused) {
            if (!primaryShapeAtThresholdP1) {
                promptShapeP1.setLineWidth(promptShapeP1.getRadius() / Draw.LINE_WIDTH_DIVISOR);
            }
            if (!primaryShapeAtThresholdP2) {
                promptShapeP2.setLineWidth(promptShapeP2.getRadius() / Draw.LINE_WIDTH_DIVISOR);
            }
        }
    }

    public void drawBackgroundColorShape() {
        if(!paused) {
            if (!gameOver) {
                game.draw.drawBackgroundColorShape(backgroundColorShape, game.shapeRendererFilled);
            }
        }
    }

    public void maintainSpeed() {
        if(!gameOver) {
            if(!paused) {
                float actualFPS = Gdx.graphics.getRawDeltaTime() * game.FPS;
                game.draw.setColorListSpeed((NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_WIDTH) / (60 * actualFPS * game.FPS));
                promptIncrease = (game.widthOrHeight * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))) / 2;
            }
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
                    if(saturationP1 > saturationP2) {
                        promptShapeToUse = promptShapeP2;
                    } else if(saturationP1 < saturationP2) {
                        promptShapeToUse = promptShapeP1;
                    } else {
                        promptShapeToUse = promptShapeP1;
                    }
                    if(promptShapeToUse == promptShapeP1) {
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
                                primaryShapeP1.setColor(clearColor);
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
                                primaryShapeP2.setColor(clearColor);
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
                if(equationWidthP1 > 0) {
                    game.draw.drawEquationBattleSinglePlayer(P1, lastShapeTouchedP1, lastPromptShapeP1, lastTargetShapeP1, equationWidthP1, game.shapeRendererFilled);
                    equationWidthP1 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                } else {
                    equationWidthP1 = 0;
                }
                if(equationWidthP2 > 0) {
                    game.draw.drawEquationBattleSinglePlayer(P2, lastShapeTouchedP2, lastPromptShapeP2, lastTargetShapeP2, equationWidthP2, game.shapeRendererFilled);
                    equationWidthP2 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                } else {
                    equationWidthP2 = 0;
                }
            }
        }
    }

    public void destroyOversizedShapes() {
        if(!paused) {
            //Prevent shapes from getting too large
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

    public void drawResultsInputButtons() {
        if(!paused) {
            if (showResults) {
                game.draw.drawResultsInputButtons(INPUT_PLAY_SPAWN, INPUT_HOME_SPAWN, INPUT_EXIT_SPAWN, game.shapeRendererFilled);
            }
        }
    }

    public void gameOver() {
        //Game over condition
        //TODO: Update these conditionals when horizontal or vertical splitscreen is an option
        if ((promptShapeP1.getRadius() >= game.widthOrHeight / 4 || promptShapeP2.getRadius() >= game.widthOrHeight / 4 || dummyPromptForTimelines.getRadius() >= game.widthOrHeight / 2) && !gameOver) {
            gameOver = true;
            stopMusic();
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
        }
    }

    public void addTouchDownShapes() {
        for (int i = 1; i < 20; i++) {
            if (!gameOver) {
                if (pointTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_POINT_SPAWN_P1.x,
                                    INPUT_POINT_SPAWN_P1.y)));
                } else if (lineTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_LINE_SPAWN_P1.x,
                                    INPUT_TRIANGLE_SPAWN_P1.y)));
                } else if (triangleTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_TRIANGLE_SPAWN_P1.x,
                                    INPUT_TRIANGLE_SPAWN_P1.y)));
                } else if (squareTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_SQUARE_SPAWN_P1.x,
                                    INPUT_SQUARE_SPAWN_P1.y)));
                } else if(pentagonTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_PENTAGON_SPAWN_P1.x,
                                    INPUT_PENTAGON_SPAWN_P1.y)));
                } else if(hexagonTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_HEXAGON_SPAWN_P1.x,
                                    INPUT_HEXAGON_SPAWN_P1.y)));
                } else if(septagonTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_SEPTAGON_SPAWN_P1.x,
                                    INPUT_SEPTAGON_SPAWN_P1.y)));
                } else if(octagonTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_OCTAGON_SPAWN_P1.x,
                                    INPUT_OCTAGON_SPAWN_P1.y)));
                } else if(nonagonTouchedP1) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_NONAGON_SPAWN_P1.x,
                                    INPUT_NONAGON_SPAWN_P1.y)));
                }
                if (pointTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_POINT_SPAWN_P2.x,
                                    INPUT_POINT_SPAWN_P2.y)));
                } else if (lineTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_LINE_SPAWN_P2.x,
                                    INPUT_TRIANGLE_SPAWN_P2.y)));
                } else if (triangleTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_TRIANGLE_SPAWN_P2.x,
                                    INPUT_TRIANGLE_SPAWN_P2.y)));
                } else if (squareTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_SQUARE_SPAWN_P2.x,
                                    INPUT_SQUARE_SPAWN_P2.y)));
                } else if(pentagonTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_PENTAGON_SPAWN_P2.x,
                                    INPUT_PENTAGON_SPAWN_P2.y)));
                } else if(hexagonTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_HEXAGON_SPAWN_P2.x,
                                    INPUT_HEXAGON_SPAWN_P2.y)));
                } else if(septagonTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_SEPTAGON_SPAWN_P2.x,
                                    INPUT_SEPTAGON_SPAWN_P2.y)));
                } else if(octagonTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_OCTAGON_SPAWN_P2.x,
                                    INPUT_OCTAGON_SPAWN_P2.y)));
                } else if(nonagonTouchedP2) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_NONAGON_SPAWN_P2.x,
                                    INPUT_NONAGON_SPAWN_P2.y)));
                }
            } else if (showResults) {
                if (playTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
                            null,
                            1,
                            new Vector2(INPUT_PLAY_SPAWN.x,
                                    INPUT_PLAY_SPAWN.y)));
                } else if (homeTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomColor(),
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
    }

    public void determineTouchedInput(int screenX, int screenY) {
        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

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
        pauseTouched = touchPoint.x > game.camera.viewportWidth - (2 *(game.camera.viewportWidth / 20))
                && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20);
        pauseBackTouched = touchPoint.x > game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT;
        pauseQuitTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void determineKeyedInput(int keycode) {
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
        pauseTouched = keycode == Input.Keys.ESCAPE;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
        inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void handleInput(String player) {
        if(!paused) {
            if (!gameOver) {
                if(player.equals(P1)) {
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
                    }
                } else {
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
                    }
                }
            }
            if(player.equals(P1)) {
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
            if (inputTouchedResults && showResults) {
                handleResultsInput();
            }
        } else {
            handlePauseInput();
        }
    }

    public void handleResultsInput() {
        if (playTouched) {
            game.setScreen(new BattleSinglePlayerScreen(game));
        } else if (homeTouched) {
            game.setScreen(new MainMenuScreen(game));
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
            stopMusic();
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    public void transitionShape(String player, int shapeAdded) {
        if(player.equals(P1)) {
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
        if(player.equals(P1)) {
            targetShapesMatchedP1++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShapeP1.getRadius(),
                    Color.BLACK,
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
                currentTargetShapeP1.setColor(priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShapeP1 = outsideTargetShapeP1;
            } else {
                targetShapesMatchedP1 = 0;
                targetShapeListP1.clear();
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor())) {
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
                    if (targetShapeListP1.get(0).getShape() == Shape.SQUARE) {
                        while (outsideTargetShapeP1.getShape() == Shape.TRIANGLE) {
                            outsideTargetShapeP1.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE) {
                    //SQUIRGLE MATCHED!!!
                    saturationP1 -= 5;
                    saturationP2 += 3;
                    squirgleOpacityP1 = 1;
                } else {
                    saturationP2++;
                }
                currentTargetShapeP1 = targetShapeListP1.get(0);
            }
        } else {
            targetShapesMatchedP2++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShapeP2.getRadius(),
                    Color.BLACK,
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
                currentTargetShapeP2.setColor(priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShapeP2 = outsideTargetShapeP2;
            } else {
                targetShapesMatchedP2 = 0;
                targetShapeListP2.clear();
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor())) {
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
                    targetArcStartP2 = Draw.NINETY_ONE_DEGREES;
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
                    if (targetShapeListP2.get(0).getShape() == Shape.SQUARE) {
                        while (outsideTargetShapeP2.getShape() == Shape.TRIANGLE) {
                            outsideTargetShapeP2.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE) {
                    //SQUIRGLE MATCHED!!!
                    saturationP2 -= 5;
                    saturationP1 += 3;
                    squirgleOpacityP2 = 1;
                } else {
                    saturationP1++;
                }
                currentTargetShapeP2 = targetShapeListP2.get(0);
            }
        }
        keepSaturationsInBounds();
    }

    public void shapesMismatchedBehavior(String player) {
        //The wrong shape was touched
        if(player.equals(P1)) {
            saturationP1++;
        } else {
            saturationP2++;
        }
        keepSaturationsInBounds();
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

    public void setUpNonFinalStaticData() {
        INPUT_RADIUS = game.camera.viewportWidth / 38;
        TARGET_RADIUS = game.camera.viewportWidth / 10.24f;
        PAUSE_INPUT_WIDTH = (game.camera.viewportWidth - (4 * game.partitionSize)) / 3;
        PAUSE_INPUT_HEIGHT = game.camera.viewportHeight - (2 * game.partitionSize);
        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS = game.camera.viewportHeight / 68;
        BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT_P1 = ((game.camera.viewportHeight / 2) - (INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7);
        BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT_P1 = (game.camera.viewportHeight / 2) - (INPUT_RADIUS / 2);
        BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT_P2 = (game.camera.viewportHeight - (INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7);
        BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT_P2 = game.camera.viewportHeight - (INPUT_RADIUS / 2);
        BACKGROUND_COLOR_SHAPE_LIST_WIDTH = (TARGET_RADIUS + (6 * ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7))) - (TARGET_RADIUS + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7));
        INIT_PROMPT_RADIUS = game.widthOrHeight / 8;
        for(int i = 1; i <= game.base; i++) {
            //P1
            Vector2 inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
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
            inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (game.camera.viewportHeight / 2) + (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
            switch(i) {
                case 1 : INPUT_POINT_SPAWN_P2 = inputVector;
                case 2 : INPUT_LINE_SPAWN_P2 = inputVector;
                case 3 : INPUT_TRIANGLE_SPAWN_P2 = inputVector;
                case 4 : INPUT_SQUARE_SPAWN_P2 = inputVector;
                case 5 : INPUT_PENTAGON_SPAWN_P2 = inputVector;
                case 6 : INPUT_HEXAGON_SPAWN_P2 = inputVector;
                case 7 : INPUT_SEPTAGON_SPAWN_P2 = inputVector;
                case 8 : INPUT_OCTAGON_SPAWN_P2 = inputVector;
                case 9 : INPUT_NONAGON_SPAWN_P2 = inputVector;
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
                        ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(TARGET_RADIUS + ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7),
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_HEIGHT_P2)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        Color.WHITE,
                        ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(TARGET_RADIUS + (i * ((game.camera.viewportWidth - (TARGET_RADIUS * 2)) / 7)),
                                BACKGROUND_COLOR_SHAPE_LIST_MIN_HEIGHT_P2)));
            }
        }

        //Set prompt increase such that without player input, three passes of backgroundColorShapeList will
        //occur before game over
        promptIncrease = (game.widthOrHeight * (game.draw.getColorListSpeed() / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH))) / 2;
        targetArcStartP1 = -Draw.NINETY_ONE_DEGREES;
        targetArcStartP2 = -Draw.NINETY_ONE_DEGREES;
        squirgleOpacityP1 = 0;
        squirgleOpacityP2 = 0;
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
        lastShapeTouchedP1 = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP1.getCoordinates());
        lastShapeTouchedP2 = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP2.getCoordinates());
        lastPromptShapeP1 = new Shape(Shape.POINT, promptShapeP1.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP1.getCoordinates());
        lastPromptShapeP2 = new Shape(Shape.POINT, promptShapeP2.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP2.getCoordinates());
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
                new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        priorShapeListP1 = new ArrayList<Shape>();
        priorShapeListP2 = new ArrayList<Shape>();
        targetShapeListP1 = new ArrayList<Shape>();
        targetShapeListP2 = new ArrayList<Shape>();
        touchDownShapeList = new ArrayList<Shape>();
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
        if (targetShapeListP1.get(0).getShape() == Shape.SQUARE) {
            while (outsideTargetShapeP1.getShape() == Shape.TRIANGLE) {
                outsideTargetShapeP1.setShape(MathUtils.random(game.base - 1));
            }
        }
        if (targetShapeListP2.get(0).getShape() == Shape.SQUARE) {
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
        currentTargetShapeP1 = targetShapeListP1.get(0);
        currentTargetShapeP2 = targetShapeListP2.get(0);
        lastTargetShapeP1 = currentTargetShapeP1;
        lastTargetShapeP2 = currentTargetShapeP2;
        targetShapesMatchedP1 = 0;
        targetShapesMatchedP2 = 0;
        touchPoint = new Vector3();
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
        inputTouchedGameplayP1 = false;
        inputTouchedGameplayP2 = false;
        inputTouchedResults = false;
        targetArcColor = new Color();
        clearColor = new Color(backgroundColorShape.getColor());
        gameOver = false;
        showResults = false;
        paused = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
        pauseStartTime = 0;
        timePaused = 0;
        opponentTime = System.currentTimeMillis();
        equationWidthP1 = 0;
        equationWidthP2 = 0;
        destructionIndex = 1;
        firstPriorShapePreviousXP1 = 0;
        firstPriorShapePreviousXP2 = 0;
        resultsColor = new Color();
        primaryShapeP1 = priorShapeListP1.size() > 0 ? priorShapeListP1.get(0) : promptShapeP1;
        primaryShapeP2 = priorShapeListP2.size() > 0 ? priorShapeListP2.get(0) : promptShapeP2;

        //TODO: Update this when I split horizontally or vertically depending on screen dimensions
        primaryShapeThreshold = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth * game.draw.THRESHOLD_MULTIPLIER : (game.camera.viewportHeight / 2) * game.draw.THRESHOLD_MULTIPLIER;

        primaryShapeAtThresholdP1 = primaryShapeP1.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP2 = primaryShapeP2.getRadius() >= primaryShapeThreshold;
        saturationP1 = 0;
        saturationP2 = 0;
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
