package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.GameplayScreen;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.SoundUtils;

import java.util.ArrayList;
import java.util.List;

public class TutorialScreen implements Screen, InputProcessor {
    final Squirgle game;

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
    private final static int NUM_TIMELINES = 3;
    private final static int TUTORIAL_BASE = 4;
    private final static int SCORE_ANGLE = -45;
    private final static int ONE_THOUSAND = 1000;
    private final static int TWO_SECONDS = 2;
    private final static int TEN_SECONDS = 10;
    private final static int TARGET_ARC_SPEED = 5;
    private final static int COLOR_SPEED_ADDITIVE = 20;
    private final static int EQUATION_WIDTH_DIVISOR = 240;
    private final static int SHAPE_SIZE_LIMIT_MULTIPLIER = 15;
    private final static int MAX_MULTIPLIER = 5;
    private final static int ONE_SHAPE_AGO = 2;
    private final static int TWO_SHAPES_AGO = 4;
    private final static int THIRTY_DEGREES = 30;
    private final static int TWO_HUNDRED_AND_SEVENTY_DEGREES = 270;
    private final static int THREE_HUNDRED_AND_THIRTY_DEGREES = 330;

    private final static float FONT_SCORE_SIZE_DIVISOR = 11.1f;
    private final static float FONT_TARGET_SIZE_DIVISOR = 35.5f;
    private final static float FONT_SQUIRGLE_SIZE_DIVISOR = 5;
    private final static float FONT_TUTORIAL_HELP_SIZE_DIVISOR = 71;
    private final static float FONT_MULTIPLIER_INPUT = 1.39f;
    private final static float SCORE_DIVISOR = 3.16f;
    private final static float TARGET_RADIUS_DIVISOR = 2.43f;
    private final static float MULTIPLIER_X_DIVISOR = 2.68f;
    private final static float MULTIPLIER_Y_DIVISOR = 1.25f;
    private final static float PROMPT_INCREASE_ADDITIVE = .0001f;
    private final static float SQUIRGLE_OPACITY_DECREMENT = .03f;

    private final static String X = "X";
    private final static String SQUIRGLE = "SQUIRGLE";

    public final static String PHASE_TWO_HELP_TEXT = "The shape in the center of the screen is the shape currently in your hand. The buttons at the bottom of the screen represent the different shapes you can add to your hand. Since there is no subtraction in Squirgle, though, to create a smaller shape from a larger one, we use remainders. For instance, if you need to make a point from a square, you just add a point. Need to make a point from a triangle? Add a line. To better understand this, play with the input buttons for a bit. The tutorial will proceed when you have exhausted all permutations.";
    public final static String PHASE_SIX_HELP_TEXT = "The flashing shape in the upper left corner of the screen is your target shape; this is what you want to make from the shape in your hand. The flashing number next to the target shape represents its number of vertices. Using the inputs at the bottom of the screen, you must add the correct number of vertices to the shape in your hand such that the sum equals the number of vertices in the target shape. The tutorial will proceed after you correctly match four shapes.";
    public final static String PHASE_SEVEN_HELP_TEXT = "In the upper right corner of the screen, you'll see three numbers. The leftmost one corresponds to the number of vertices had by the shape currently in your hand; the middle one represents your score (increase this by correctly matching the shape in your hand to the target shape); the one next to the \"X\" represents your current multiplier. The multiplier determines how many points you receive when correctly matching shapes, and is increased by gaining points with no mistakes. The tutorial will proceed when your multiplier reaches its maximum of 5.";
    public final static String PHASE_EIGHT_HELP_TEXT = "On the left side of the screen, you'll see a number of color swatches and three vertical lines. The swatches represent the forthcoming background colors, which themselves determine the color of the target shape once matched. The vertical lines represent how much time remains in your current game, and they correspond to the size of the shape in your hand. Should you succeed in matching two shapes within the same target group of the same color, you will have successfully SQUIRGLED, meaning that the shape in your hand reverts to its initial size, and you thus have more time to play and rack up points. Until you manage to get four SQUIRGLES, only the first of the three timelines will deplete, but afterwards, the game will play in its full form.";

    /*
    -All white or gray screen except for black outlined prompt shape (point)
    -User taps screen
     */
    public final static int PHASE_ONE = 0;

    /*
    -Single input appears (point)
    -User taps point until prompt shape rolls over from square to point (user has tapped it four times)
     */
    public final static int PHASE_TWO = 1;

    /*
    -Second input appears (line), and first disappears
    -User taps line until prompt shape rolls over from triangle to point (user has tapped it twice)
     */
    public final static int PHASE_THREE = 2;

    /*
    -Third input appears (triangle), and second disappears
    -User taps triangle until prompt shape rolls back over to point (user has tapped it four times)
     */
    public final static int PHASE_FOUR = 3;

    /*
    -Fourth input appears (square), and third disappears
    -User taps square until prompt shape rolls back over to point (user has tapped it once)
     */
    public final static int PHASE_FIVE = 4;

    /*
    -Target is now displayed in upper left corner
    -If user hits wrong shape, disconfirmation sound is played, and phase resets
    -If user hits correct shape, confirmation sound is played, and new shape is targeted
    -User hits correct shape four times
     */
    public final static int PHASE_SIX = 5;

    /*
    -Score & multiplier are now displayed in upper right-hand corner of screen
    -Targets are now formed as per usual until multiplier reaches max
     */
    public final static int PHASE_SEVEN = 6;

    /*
    -Colors are now displayed @ left of screen
    -Timelines are now displayed @ left of screen
    -Pause input is now displayed @ right-hand side of screen
    -Colors are now sifted through, affecting background, prompt shape & nested shapes
    -Prompt shape (& nested shapes) also begins to grow now, but it will stop doing so once it reaches 2/3 of its max size
    -The game remains in this phase until the user gets four squirgles
     */
    public final static int PHASE_EIGHT = 7;

    /*
    -User simply plays w/ full mechanics until game over
    -Tutorial is over, & user is sent to menu
     */
    public final static int PHASE_NINE = 8;

    public final static int PHASE_SIX_REQUIRED_CORRECT_INPUTS = 4;
    public final static int PHASE_EIGHT_REQUIRED_SQUIRGLES = 4;

    private Stage stage;
    private int phase;
    private int phaseSixCorrectInputs = 0;
    private int phaseEightSquirgles = 0;
    private float promptIncrease;
    private float equationWidth;
    private float targetArcStart;
    private float squirgleOpacity;
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
    boolean helpTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    boolean inputTouchedGameplay;
    boolean inputTouchedResults;
    private Color targetArcColor;
    private Color clearColor;
    private int score;
    private boolean gameOver;
    private boolean showResults;
    private boolean paused;
    private boolean helpTextVisible;
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

    public TutorialScreen(final Squirgle game, final int gameplayType) {
        this.game = game;

        game.base = TUTORIAL_BASE;

        game.resetInstanceData();

        game.setUpFontScore(MathUtils.round(game.camera.viewportWidth / FONT_SCORE_SIZE_DIVISOR));
        game.setUpFontTarget(MathUtils.round(game.camera.viewportWidth / FONT_TARGET_SIZE_DIVISOR));
        game.setUpFontSquirgle(MathUtils.round(game.camera.viewportWidth / FONT_SQUIRGLE_SIZE_DIVISOR));
        game.setUpFontTutorialHelp(MathUtils.round(game.widthGreater ? game.camera.viewportWidth / FONT_TUTORIAL_HELP_SIZE_DIVISOR : game.camera.viewportHeight / FONT_TUTORIAL_HELP_SIZE_DIVISOR));
        game.setUpLabelHelp();

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        //TODO: Eventually set this in render using delta? See maintainSpeed() in TimeAttackScreen
        game.draw.setColorListSpeed(((BACKGROUND_COLOR_SHAPE_LIST_HEIGHT * NUM_TIMELINES) / game.FPS) / game.ONE_MINUTE);

        setUpNonFinalNonStaticData();
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

        increasePromptRadius();

        managePrimaryShapeLineWidth();

        drawBackgroundColorShape();

        if(!paused) {
            game.draw.drawPrompt(false, promptShape, priorShapeList, 0, backgroundColorShape, false, false);
            game.draw.drawShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
        }

        increaseSpeed();
        decrementSquirgleOpacity();
        zoomThroughShapes();

        if(!paused) {
            //This code is being executed three times: once before setting the prompt's end game coordinates, and again afterwards.
            //This way, the shapes are drawn with their new values, and the first element in priorShapeList doesn't veer off
            //the screen to the right.
            //TODO: separate draw methods out into distinct ones, one of which assigns radii and coordinates, and the other of
            //TODO: which actually draws the shapes. It's overkill to draw the shapes multiple times.
            game.draw.drawShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);

            if (!gameOver) {
                game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2,
                        Color.BLACK,
                        (3 * game.widthOrHeight) / 8,
                        promptShape);
                if (phase >= PHASE_EIGHT) {
                    game.draw.drawBackgroundColorShapeListTutorial(backgroundColorShapeList, backgroundColorShape, clearColor);
                    game.draw.drawTimelines(false, false, promptShape, backgroundColorShapeList);
                }
                if (phase >= PHASE_SIX) {
                    SoundUtils.playMusic(promptShape, game);
                    game.draw.drawTargetSemicirclesTutorial();
                }
            }
        }

        drawEquation();

        if(!paused) {
            if (!gameOver) {
                game.draw.drawInputButtonsTutorial(phase);
                if (phase >= PHASE_SEVEN) {
                    game.draw.drawScoreTrianglesTutorial(backgroundColorShape.getColor());
                }
                if (phase >= PHASE_SIX) {
                    game.draw.drawPrompt(false, outsideTargetShape, targetShapeList, targetShapesMatched, backgroundColorShape, false, true);
                    game.draw.drawShapes(false, targetShapeList, outsideTargetShape, false);
                }
                if(phase >= PHASE_TWO) {
                    game.draw.drawPauseInput(false, false, game);
                    game.draw.drawHelpInput();
                }
                drawTargetArc();
            }
        }

        destroyOversizedShapes();

        if(!paused) {
            firstPriorShapePreviousX = primaryShape.getCoordinates().x;
        }

        saveAndEnd();

        if(paused) {
            drawInputRectangles();
        }

        showHelpText();

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        drawText();

        gameOver();

        //Transition color to distinguish current target shape from other
        ColorUtils.transitionColor(currentTargetShape);

        stage.draw();
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
        stage.dispose();
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

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        if(phase == PHASE_ONE) {
            game.confirmSound.play((float) (game.volume / 10.0));
            phase = PHASE_TWO;
            return true;
        }

        determineTouchedInput(screenX, screenY);

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

            if(phase == PHASE_ONE) {
                game.confirmSound.play((float) (game.volume / 10.0));
                phase = PHASE_TWO;
                return true;
            }

            determineKeyedInput(keycode);

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
        game.draw.drawPauseSymbol(game.partitionSize + (PAUSE_INPUT_WIDTH / 2),
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
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
        game.draw.drawX(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
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

    public void drawText() {
        if(!paused) {
            if (!gameOver) {
                if(phase >= PHASE_SEVEN) {
                    drawScoreText();
                }
                if(phase >= PHASE_SIX) {
                    drawTargetText();
                }
                drawSquirgleText();
                //TODO: Decide if I actually want to instate this behavior, and if so, make a new BitmapFont object in Squirgle class
                /*if(phase > PHASE_ONE) {
                    //Input Numbers
                    for (int i = 1; i <= game.base; i++) {
                        if(phase >= PHASE_SIX || phase == i) {
                            FontUtils.printText(game.batch,
                                    game.font,
                                    game.layout,
                                    Color.WHITE,
                                    String.valueOf(i),
                                    ((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS)) + (INPUT_RADIUS * FONT_MULTIPLIER_INPUT),
                                    (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS) + (INPUT_RADIUS * FONT_MULTIPLIER_INPUT),
                                    SCORE_ANGLE,
                                    1);
                        }
                    }
                }*/
            }

            if (showResults) {
                if (priorShapeList.size() > 0 && (priorShapeList.get(0).getShape() == Shape.LINE || priorShapeList.get(0).getShape() == Shape.POINT)) {
                    resultsColor = Color.BLACK;
                } else {
                    resultsColor = Color.WHITE;
                }
            }
        }
    }

    public void drawScoreText() {
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
    }

    public void drawTargetText() {
        float targetPolypRadius = (TARGET_RADIUS / 4);
        float targetPolypOffset = (float)(Math.sqrt(Math.pow(TARGET_RADIUS, 2) + Math.pow(TARGET_RADIUS / 4, 2)) - TARGET_RADIUS);

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

    public void drawSquirgleText() {
        for(int i = 1; i <= SQUIRGLE.length(); i++) {
            FontUtils.printText(game.batch,
                    game.fontSquirgle,
                    game.layout,
                    Color.WHITE,
                    SQUIRGLE.substring(i - 1, i),
                    (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                    game.camera.viewportHeight / 2,
                    0,
                    squirgleOpacity);
        }
    }

    public void drawTargetArc() {
        game.draw.drawArcTutorial(targetArcStart, targetArcColor);
        if(targetArcStart > -Draw.NINETY_ONE_DEGREES) {
            targetArcStart -= TARGET_ARC_SPEED;
        }
    }

    public void increasePromptRadius() {
        if(!paused) {
            if (phase >= PHASE_EIGHT) {
                if (!gameOver) {
                    if (phase == PHASE_EIGHT) {
                        if (promptShape.getRadius() < game.widthOrHeight / 3) {
                            promptShape.setRadius(promptShape.getRadius() + (promptIncrease / 2));
                        }
                    } else {
                        promptShape.setRadius(promptShape.getRadius() + (promptIncrease / 2));
                    }
                } else {
                    promptShape.setRadius(promptShape.getRadius() * promptIncrease);
                }
            }
        }
    }

    public void managePrimaryShapeLineWidth() {
        if(!paused) {
            if (!primaryShapeAtThreshold) {
                promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
            }
        }
    }

    public void drawBackgroundColorShape() {
        if(!paused) {
            if (!gameOver && phase >= PHASE_EIGHT) {
                game.draw.drawBackgroundColorShape(backgroundColorShape);
            }
        }
    }

    public void increaseSpeed() {
        if(!paused) {
            if (!gameOver) {
                if (phase >= PHASE_EIGHT && (System.currentTimeMillis() - startTime - timePaused) / ONE_THOUSAND > TEN_SECONDS) {
                    startTime = System.currentTimeMillis();
                    game.draw.setColorListSpeed(game.draw.getColorListSpeed() + COLOR_LIST_SPEED_ADDITIVE);
                    game.draw.setColorSpeed(game.draw.getColorSpeed() + COLOR_SPEED_ADDITIVE);
                    promptIncrease = (game.widthOrHeight * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
                }
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

    public void zoomThroughShapes() {
        if(!paused) {
            if(gameOver) {
                if ((System.currentTimeMillis() - endTime) / ONE_THOUSAND > TWO_SECONDS) {
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
                            primaryShape.setColor(clearColor);
                        }
                        if (primaryShape.getShape() != Shape.LINE || primaryShape.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                            showResults = true;
                        }
                    }
                }
            }
        }
    }

    public void drawEquation() {
        if(!paused) {
            if(!gameOver) {
                if (equationWidth > 0) {
                    game.draw.drawEquationTutorial(lastShapeTouched, lastPromptShape, lastTargetShape, equationWidth, phase);
                    equationWidth -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                } else {
                    equationWidth = 0;
                }
            }
        }
    }

    public void destroyOversizedShapes() {
        if(!paused) {
            //Prevent shapes from getting too large
            if (promptShape.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                if (priorShapeList.size() > destructionIndex) {
                    promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                    for (int i = 0; i < destructionIndex; i++) {
                        priorShapeList.remove(priorShapeList.size() - 1);
                    }
                    destructionIndex = 2;
                }
            }
        }
    }

    public void saveAndEnd() {
        if(!paused) {
            if (showResults) {
                game.updateSave(game.SAVE_PLAYED_BEFORE, true);
                game.setScreen(new MainMenuScreen(game, Color.BLACK));
                dispose();
            }
        }
    }

    public void gameOver() {
        //Game over condition
        if (promptShape.getRadius() >= game.widthOrHeight / 2 && !gameOver) {
            gameOver = true;
            stopMusic();
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
        }
    }

    public void determineTouchedInput(int screenX, int screenY) {
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
        pauseTouched = touchPoint.x > game.camera.viewportWidth - (2 *(game.camera.viewportWidth / 20))
                && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20);
        helpTouched = touchPoint.x > game.camera.viewportWidth - (2 *(game.camera.viewportWidth / 20))
                && touchPoint.y > (game.camera.viewportHeight / 4) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 4) + (game.camera.viewportWidth / 20);
        pauseBackTouched = touchPoint.x > game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT;
        pauseQuitTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void determineKeyedInput(int keycode) {
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
        pauseTouched = keycode == Input.Keys.ESCAPE;
        helpTouched = keycode == Input.Keys.SLASH;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
        inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void handleInput() {
        if(!paused && !gameOver) {
            if (pointTouched) {
                if(phase == PHASE_TWO || phase >= PHASE_SIX) {
                    transitionShape(Shape.POINT);
                }
            } else if (lineTouched) {
                if(phase == PHASE_THREE || phase >= PHASE_SIX) {
                    transitionShape(Shape.LINE);
                }
            } else if (triangleTouched) {
                if(phase == PHASE_FOUR || phase >= PHASE_SIX) {
                    transitionShape(Shape.TRIANGLE);
                }
            } else if (squareTouched) {
                if(phase >= PHASE_FIVE) {
                    //boolean value to determine whether or not disconfirm sound should be played upon mismatch, or if we should just go ahead and return
                    boolean shouldReturn = phase == PHASE_FIVE;
                    transitionShape(Shape.SQUARE);
                    if(shouldReturn) {
                        return;
                    }
                }
            } else if(helpTouched) {
                helpTextVisible = !helpTextVisible;
            }
        }

        if(phase >= PHASE_SIX) {
            if (!paused) {
                if (inputTouchedGameplay && !gameOver && promptShape.getShape() == currentTargetShape.getShape()) {
                    shapesMatchedBehavior();
                } else if (!gameOver && inputTouchedGameplay) {
                    shapesMismatchedBehavior();
                }
            }
        }

        if(paused) {
            handlePauseInput();
        }

        if (pauseTouched && phase >= PHASE_TWO) {
            pause();
        }
    }

    public void handlePauseInput() {
        if (pauseBackTouched) {
            timePaused += System.currentTimeMillis() - pauseStartTime;
            resume();
        } else if (pauseQuitTouched) {
            stopMusic();
            game.updateSave(game.SAVE_PLAYED_BEFORE, true);
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
            dispose();
        }
    }

    public void transitionShape(int shapeAdded) {
        if(phase > PHASE_ONE && phase < PHASE_SIX) {
            game.confirmSound.play((float) (game.volume / 10.0));
        }
        lastShapeTouched.setShape(shapeAdded);
        lastShapeTouched.setRadius(INPUT_RADIUS);
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
            if(promptShape.getShape() == Shape.POINT && phase > PHASE_ONE && phase < PHASE_SIX) {
                phase++;
                if(phase == PHASE_SIX) {
                    game.helpLabel.setText(PHASE_SIX_HELP_TEXT);
                }
            }
        } else {
            promptShape.setShape(promptShape.getShape() + (shapeAdded + 1));
        }
    }

    public void shapesMatchedBehavior() {
        game.confirmSound.play((float) (game.volume / 10.0));
        phaseSixCorrectInputs++;
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
            currentTargetShape.setColor(priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor());
            currentTargetShape = outsideTargetShape;
        } else {
            if(phase == PHASE_SIX && phaseSixCorrectInputs >= PHASE_SIX_REQUIRED_CORRECT_INPUTS) {
                game.confirmSound.play((float) (game.volume / 10.0));
                phase = PHASE_SEVEN;
                multiplier = 0;
                score = 0;
                game.helpLabel.setText(PHASE_SEVEN_HELP_TEXT);
            }
            targetShapesMatched = 0;
            score += multiplier;
            if (multiplier < 5) {
                multiplier++;
                if(multiplier >= MAX_MULTIPLIER) {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    phase = PHASE_EIGHT;
                    game.helpLabel.setText(PHASE_EIGHT_HELP_TEXT);
                }
            }
            targetShapeList.clear();
            if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor()) && priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor() != Color.GRAY) {
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
                        new Vector2(TARGET_RADIUS / 3,
                                game.camera.viewportHeight - (TARGET_RADIUS / 2))));
                if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
                    while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                        outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                    }
                }
            }
            if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE) {
                //SQUIRGLE MATCHED!!!
                promptShape.setRadius(INIT_PROMPT_RADIUS);
                phaseEightSquirgles++;
                if(phaseEightSquirgles >= PHASE_EIGHT_REQUIRED_SQUIRGLES) {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    phase = PHASE_NINE;
                }
                squirgleOpacity = 1;
            }
            currentTargetShape = targetShapeList.get(0);
        }
    }

    public void shapesMismatchedBehavior() {
        //The wrong shape was touched
        game.disconfirmSound.play((float) (game.volume / 10.0));
        multiplier = 1;

        if (phase == PHASE_SIX) {
            phaseSixCorrectInputs = 0;
        }else if(phase == PHASE_EIGHT) {
            float radiusIncrease = game.widthOrHeight * ((backgroundColorShapeList.get(3).getCoordinates().x - backgroundColorShapeList.get(2).getCoordinates().x) / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH));

            if(promptShape.getRadius() + radiusIncrease > (game.widthOrHeight / 3)) {
                promptShape.setRadius(game.widthOrHeight / 3);
            } else {
                promptShape.setRadius(promptShape.getRadius() + radiusIncrease);
            }
        }else if(phase == PHASE_NINE) {
            float radiusIncrease = game.widthOrHeight * ((backgroundColorShapeList.get(3).getCoordinates().x - backgroundColorShapeList.get(2).getCoordinates().x) / (3 * BACKGROUND_COLOR_SHAPE_LIST_WIDTH));

            if(promptShape.getRadius() + radiusIncrease > (game.widthOrHeight / 2)) {
                promptShape.setRadius(game.widthOrHeight / 2);
            } else {
                promptShape.setRadius(promptShape.getRadius() + radiusIncrease);
            }
        }
    }

    public void showHelpText() {
        game.helpLabel.setVisible(helpTextVisible && !paused);
        if(helpTextVisible && !paused) {
            game.draw.rect((game.camera.viewportWidth / 2) - (game.helpLabel.getWidth() / 2),
                    (game.camera.viewportHeight / 2) - (game.helpLabel.getHeight() / 2),
                    game.helpLabel.getWidth(),
                    game.helpLabel.getHeight(),
                    Color.BLACK);
        }
    }

    public void setUpNonFinalStaticData() {
        INPUT_RADIUS = game.camera.viewportWidth / 19;
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
        TARGET_RADIUS = game.camera.viewportWidth / 5.12f;
        PAUSE_INPUT_WIDTH = (game.camera.viewportWidth - (4 * game.partitionSize)) / 3;
        PAUSE_INPUT_HEIGHT = game.camera.viewportHeight - (2 * game.partitionSize);
        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS = TARGET_RADIUS / 12;
        BACKGROUND_COLOR_SHAPE_LIST_MAX_Y = game.camera.viewportHeight - TARGET_RADIUS - (2 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS);
        BACKGROUND_COLOR_SHAPE_LIST_MIN_Y = INPUT_POINT_SPAWN.y + INPUT_RADIUS + (2 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS);
        BACKGROUND_COLOR_SHAPE_LIST_HEIGHT = BACKGROUND_COLOR_SHAPE_LIST_MAX_Y - BACKGROUND_COLOR_SHAPE_LIST_MIN_Y;
        BACKGROUND_COLOR_SHAPE_LIST_MAX_X = -BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
        BACKGROUND_COLOR_SHAPE_LIST_MIN_X = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1));
        BACKGROUND_COLOR_SHAPE_LIST_MAX_X = BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
        BACKGROUND_COLOR_SHAPE_LIST_MIN_X = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1));
        BACKGROUND_COLOR_SHAPE_LIST_WIDTH = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - BACKGROUND_COLOR_SHAPE_LIST_MIN_X;
        COLOR_LIST_SPEED_ADDITIVE =  BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / 5000;
        INIT_PROMPT_RADIUS = game.widthOrHeight / 4;
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
                        new Vector2(BACKGROUND_COLOR_SHAPE_LIST_MAX_X,
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_Y)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        Color.WHITE,
                        ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(BACKGROUND_COLOR_SHAPE_LIST_MAX_X,
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_Y - (i * (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1))))));
            }
        }

        //Set prompt increase such that without player input, three passes of backgroundColorShapeList will
        //occur before game over
        promptIncrease = (game.widthOrHeight * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
        targetArcStart = -Draw.NINETY_ONE_DEGREES;
        squirgleOpacity = 0;
        promptShape = new Shape(Shape.POINT,
                INIT_PROMPT_RADIUS,
                Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        lastShapeTouched = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        lastPromptShape = new Shape(Shape.POINT, promptShape.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        outsideTargetShape = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        priorShapeList = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
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
                Color.GRAY,
                Color.GRAY,
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
        pentagonTouched = false;
        hexagonTouched = false;
        septagonTouched = false;
        octagonTouched = false;
        nonagonTouched = false;
        playTouched = false;
        homeTouched = false;
        exitTouched = false;
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        inputTouchedGameplay = false;
        inputTouchedResults = false;
        targetArcColor = new Color();
        clearColor = new Color(backgroundColorShape.getColor());
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
        stage = new Stage(game.viewport);
        stage.addActor(game.helpLabel);
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
