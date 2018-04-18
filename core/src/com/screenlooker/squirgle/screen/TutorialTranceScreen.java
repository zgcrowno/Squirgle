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
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.SoundUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TutorialTranceScreen implements Screen, InputProcessor {
    final Squirgle game;

    public static float INIT_PROMPT_RADIUS;
    public static float PAUSE_INPUT_WIDTH;
    public static float PAUSE_INPUT_HEIGHT;
    public static float FONT_TUTORIAL_HELP_SIZE_MULTIPLIER;

    private final static int PAUSE_BACK = 0;
    private final static int PAUSE_QUIT = 1;

    private final static int NUM_MUSIC_PHASES = 3;
    private final static int SHAPE_SIZE_LIMIT_MULTIPLIER = 15;
    private final static int PAUSE_INPUT_DISAPPEARANCE_TIME = 5;
    private final static int ONE_THOUSAND = 1000;

    /*
    -Screen displaying help button, and zooming through shapes
    -User presses next button in help window
    */
    public final static int PHASE_ONE = 0;

    /*
    -User simply plays w/ full mechanics until they decide to leave
    -Tutorial is over, & user chooses to replay, navigate to menu or quit
     */
    public final static int PHASE_TWO = 1;

    private float promptIncrease;
    private float helpInputGirth;
    private Shape promptShape;
    private Shape lastPromptShape;
    private List<Shape> priorShapeList;
    private Vector3 touchPoint;
    boolean helpTouched;
    boolean helpChevronDownTouched;
    boolean helpChevronUpTouched;
    boolean helpNextTouched;
    boolean pauseTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    boolean inputTouchedHelp;
    private int phase;
    private int currentHelpTextIndex;
    private boolean paused;
    private long startTime;
    private long endTime;
    private long pauseStartTime;
    private long timePaused;
    private long timeSinceTouched;
    private int destructionIndex;
    private float firstPriorShapePreviousX;
    Shape primaryShape;
    float primaryShapeThreshold;
    boolean primaryShapeAtThreshold;
    private boolean helpTextVisible;
    public Label.LabelStyle helpLabelStyle;
    public Label helpLabel;
    private Stage stage;

    private String phaseOneTextOne;
    private String phaseOneTextTwo;
    private String phaseOneTextThree;

    private List<String> helpTextPhaseOneList;

    private Map<Integer, List<String>> phaseMap;
    private Map<Integer, Map<Integer, List<String>>> helpTextMap;

    public TutorialTranceScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        setUpNonFinalNonStaticData();

        SoundUtils.setVolume(promptShape, game);

        playMusic();

        game.stats.incrementNumTimesPlayedBaseOrTrack(false, game.track, Squirgle.GAMEPLAY_TRANCE);
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
        primaryShapeThreshold = game.widthOrHeightSmaller * game.draw.THRESHOLD_MULTIPLIER;
        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;

        increasePromptRadius();

        managePrimaryShapeLineWidth();

        if(!paused) {
            game.draw.drawPrompt(false, promptShape, priorShapeList, 0, null, true, false);
            game.draw.orientShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
        }

        zoomThroughShapes();

        if(!paused) {
            game.draw.orientAndDrawShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
        }

        destroyOversizedShapesAndAddNewOnes();

        if(!paused) {
            firstPriorShapePreviousX = primaryShape.getCoordinates().x;
        }

        if(paused) {
            drawInputRectangles();
        }

        showHelpText();

        if(!paused) {
            if((System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < PAUSE_INPUT_DISAPPEARANCE_TIME) {
                game.draw.drawPauseInputTutorialTrance(game);
            }
            if(phase < PHASE_TWO) {
                game.draw.drawHelpInputTrance();
            }
        }

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        SoundUtils.setVolume(promptShape, game);

        stage.draw();

        showHelpTextFooter();
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

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        timeSinceTouched = System.currentTimeMillis();

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
                Color.WHITE);
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

    public void increasePromptRadius() {
        if(!paused) {
            promptShape.setRadius(promptShape.getRadius() * promptIncrease);
        }
    }

    public void managePrimaryShapeLineWidth() {
        //TODO: Maybe remove the conditionals that were here on other screens as well?
        promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
    }

    public void zoomThroughShapes() {
        if(!paused) {
            if (!primaryShapeAtThreshold) { //TODO: Also account for height (different screen orientations?)
                //firstPriorShapePreviousX will be equal to 0 on first call of render(); this will move all shapes to the left side of the screen
                if(firstPriorShapePreviousX != 0) {
                    //TODO: Maybe use this methodology in other screens as well?
                    promptShape.setCoordinates(new Vector2(promptShape.getCoordinates().x - (primaryShape.getCoordinates().x - (game.camera.viewportWidth / 2)), promptShape.getCoordinates().y));
                }
            }
        }
    }

    public void destroyOversizedShapesAndAddNewOnes() {
        if(!paused) {
            //Prevent shapes from getting too large
            //TODO: Account for radius getting larger than width or height. Do this on other relevant screens as well.
            if (promptShape.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                if (priorShapeList.size() > destructionIndex) {
                    promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                    for (int i = 0; i < destructionIndex; i++) {
                        priorShapeList.remove(priorShapeList.size() - 1);
                    }
                    destructionIndex = 2;
                    priorShapeList.add(0, new Shape(Shape.CIRCLE, 0, Color.BLACK, null, 0, new Vector2()));
                    priorShapeList.add(0, new Shape(MathUtils.random(Shape.NONAGON), 0, ColorUtils.randomColor(), null, 0, new Vector2()));
                }
            }
        }
    }

    public void determineTouchedInput(int screenX, int screenY) {
        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                && touchPoint.y > (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20) + (game.camera.viewportWidth / 20);
        pauseBackTouched = touchPoint.x > game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT;
        pauseQuitTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        helpTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20) - (game.camera.viewportWidth / 20)
                && touchPoint.y < (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20) + (game.camera.viewportWidth / 20)
                && phase < PHASE_TWO;
        helpChevronDownTouched = touchPoint.x > helpLabel.getX() - helpInputGirth
                && touchPoint.x < helpLabel.getX()
                && touchPoint.y > helpLabel.getY() - helpInputGirth
                && touchPoint.y < helpLabel.getY() + helpLabel.getHeight()
                && helpTextVisible;
        helpChevronUpTouched = touchPoint.x > helpLabel.getX() + helpLabel.getWidth()
                && touchPoint.x < helpLabel.getX() + helpLabel.getWidth() + helpInputGirth
                && touchPoint.y > helpLabel.getY() - helpInputGirth
                && touchPoint.y < helpLabel.getY() + helpLabel.getHeight()
                && helpTextVisible;
        helpNextTouched = touchPoint.x > helpLabel.getX() + (helpLabel.getWidth() / 2)
                && touchPoint.x < helpLabel.getX() + helpLabel.getWidth()
                && touchPoint.y > helpLabel.getY() - helpInputGirth
                && touchPoint.y < helpLabel.getY()
                && helpTextVisible
                && currentHelpTextIndex == getHelpTextMaxIndex();
        inputTouchedHelp = helpTouched || helpChevronDownTouched || helpChevronUpTouched || helpNextTouched;
    }

    public void determineKeyedInput(int keycode) {
        pauseTouched = keycode == Input.Keys.ESCAPE;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
        inputTouchedHelp = false;
    }

    public void handleInput() {
        if(!paused) {
            if (pauseTouched) {
                pause();
            } else {
                handleHelpInput();
            }
        } else {
            handlePauseInput();
        }
    }

    public void handlePauseInput() {
        if (pauseBackTouched) {
            timePaused += System.currentTimeMillis() - pauseStartTime;
            resume();
        } else if (pauseQuitTouched) {
            endTime = System.currentTimeMillis();
            stopMusic();
            game.stats.updateTimePlayed(endTime - startTime, Squirgle.GAMEPLAY_TRANCE);
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
            dispose();
        }
    }

    public void handleHelpInput() {
        if(helpTouched) {
            helpTextVisible = !helpTextVisible;
        }else if(helpChevronDownTouched) {
            if(currentHelpTextIndex > 0) {
                currentHelpTextIndex--;
            } else {
                currentHelpTextIndex = getHelpTextMaxIndex();
            }
        } else if(helpChevronUpTouched) {
            if(currentHelpTextIndex < getHelpTextMaxIndex()) {
                currentHelpTextIndex++;
            } else {
                currentHelpTextIndex = 0;
            }
        } else if(helpNextTouched) {
            phase++;
            currentHelpTextIndex = 0;
        }
        if(phase < PHASE_TWO) {
            helpLabel.setText(getCurrentHelpText());
        } else {
            helpTextVisible = false;
        }
    }

    public String getCurrentHelpText() {
        return helpTextMap.get(Squirgle.GAMEPLAY_TRANCE).get(phase).get(currentHelpTextIndex);
    }

    public int getHelpTextMaxIndex() {
        return helpTextMap.get(Squirgle.GAMEPLAY_TRANCE).get(phase).size() - 1;
    }

    public void showHelpText() {
        helpLabel.setVisible(helpTextVisible && !paused);
        if(helpTextVisible && !paused) {
            game.draw.rect(helpLabel.getX() - helpInputGirth,
                    helpLabel.getY() - helpInputGirth,
                    helpLabel.getWidth() + (game.camera.viewportWidth / 8),
                    helpLabel.getHeight() + helpInputGirth,
                    Color.WHITE);
            game.draw.drawChevronLeft(helpLabel.getX() - (helpInputGirth / 2),
                    helpLabel.getY() + (helpLabel.getHeight() / 2) - (helpInputGirth / 2),
                    helpInputGirth / 2,
                    (helpInputGirth / 2) / Draw.LINE_WIDTH_DIVISOR,
                    Color.BLACK);
            game.draw.drawChevronRight(helpLabel.getX() + helpLabel.getWidth() + (helpInputGirth / 2),
                    helpLabel.getY() + (helpLabel.getHeight() / 2) - (helpInputGirth / 2),
                    helpInputGirth / 2,
                    (helpInputGirth / 2) / Draw.LINE_WIDTH_DIVISOR,
                    Color.BLACK);
            game.shapeRendererLine.setColor(Color.BLACK);
            game.shapeRendererLine.line(helpLabel.getX(),
                    helpLabel.getY() + helpLabel.getHeight(),
                    helpLabel.getX(),
                    helpLabel.getY() - helpInputGirth);
            game.shapeRendererLine.line(helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY() + helpLabel.getHeight(),
                    helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY() - helpInputGirth);
            game.shapeRendererLine.line(helpLabel.getX(),
                    helpLabel.getY(),
                    helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY());
            if(currentHelpTextIndex == getHelpTextMaxIndex()) {
                game.shapeRendererLine.line(helpLabel.getX() + (helpLabel.getWidth() / 2),
                        helpLabel.getY(),
                        helpLabel.getX() + (helpLabel.getWidth() / 2),
                        helpLabel.getY() - helpInputGirth);
            }
        }
    }

    public void showHelpTextFooter() {
        if(helpTextVisible && !paused) {
            if(currentHelpTextIndex == getHelpTextMaxIndex()) {
                FontUtils.printText(game.batch,
                        game.fontTutorialHelp,
                        game.layout,
                        Color.BLACK,
                        (currentHelpTextIndex + 1) + "/" + (getHelpTextMaxIndex() + 1),
                        helpLabel.getX() + (helpLabel.getWidth() / 4),
                        helpLabel.getY() - (helpInputGirth / 2),
                        0,
                        1);
                FontUtils.printText(game.batch,
                        game.fontTutorialHelp,
                        game.layout,
                        Color.BLACK,
                        "NEXT",
                        helpLabel.getX() + ((3 * helpLabel.getWidth()) / 4),
                        helpLabel.getY() - (helpInputGirth / 2),
                        0,
                        1);
            } else {
                FontUtils.printText(game.batch,
                        game.fontTutorialHelp,
                        game.layout,
                        Color.BLACK,
                        (currentHelpTextIndex + 1) + "/" + (getHelpTextMaxIndex() + 1),
                        helpLabel.getX() + (helpLabel.getWidth() / 2),
                        helpLabel.getY() - (helpInputGirth / 2),
                        0,
                        1);
            }
        }
    }

    public void setUpNonFinalStaticData() {
        PAUSE_INPUT_WIDTH = (game.camera.viewportWidth - (4 * game.partitionSize)) / 3;
        PAUSE_INPUT_HEIGHT = game.camera.viewportHeight - (2 * game.partitionSize);
        INIT_PROMPT_RADIUS = game.widthOrHeightSmaller / 4;
        if(game.widthGreater) {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 20f;
        } else {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 28f;
        }
    }

    public void setUpNonFinalNonStaticData() {
        promptIncrease = 1.07f;
        helpInputGirth = game.camera.viewportWidth / 16;
        promptShape = new Shape(MathUtils.random(Shape.NONAGON),
                INIT_PROMPT_RADIUS,
                ColorUtils.randomColor(),
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        lastPromptShape = new Shape(Shape.POINT, promptShape.getRadius(), Color.BLACK, Color.BLACK, GameplayScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        priorShapeList = new ArrayList<Shape>();
        for(int i = 0; i < 40; i++) {
            if(i % 2 == 0) {
                priorShapeList.add(new Shape(MathUtils.random(Shape.NONAGON),
                        promptShape.getRadius(),
                        ColorUtils.randomColor(),
                        null,
                        promptShape.getLineWidth(),
                        new Vector2()));
            } else {
                priorShapeList.add(new Shape(Shape.CIRCLE,
                        promptShape.getRadius(),
                        Color.BLACK,
                        null,
                        promptShape.getLineWidth(),
                        new Vector2()));
            }
        }
        touchPoint = new Vector3();
        helpTouched = false;
        helpChevronDownTouched = false;
        helpChevronUpTouched = false;
        helpNextTouched = false;
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        phase = PHASE_ONE;
        currentHelpTextIndex = 0;
        paused = false;
        helpTextVisible = true;
        startTime = System.currentTimeMillis();
        endTime = 0;
        pauseStartTime = 0;
        timePaused = 0;
        timeSinceTouched = PAUSE_INPUT_DISAPPEARANCE_TIME;
        destructionIndex = 1;
        firstPriorShapePreviousX = 0;
        primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        primaryShapeThreshold = game.widthOrHeightSmaller * game.draw.THRESHOLD_MULTIPLIER;
        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;

        phaseOneTextOne = "Welcome to the TRANCE tutorial! Press the chevrons on either side of this text block to peruse the various instructional text that will help introduce you to the world of TRANCE.";
        phaseOneTextTwo = "On the right side of the screen, you will see the HELP [?] button. If, at any point in this tutorial, you wish to consult/dismiss instructional text such as this, just press the HELP [?] button. If you wish to navigate to the PAUSE menu, however, simply tap anywhere on your screen, and the PAUSE [||] input will appear next to the HELP [?] input for a few seconds. While the PAUSE [||] input is visible, you may press it to enter into the PAUSE menu, at which point you may press the BACK button to unpause or the STOP button to quit.";
        phaseOneTextThree = "The purpose of TRANCE mode is literally to listen to music while letting a dope series of SQUIRGLE shapes wash over you; that's it. There probably shouldn't even be a tutorial for this mode, as it's 99% passive. When you're ready to experience TRANCE mode in full, simply press the NEXT button to zone out until you're satisfied, at which point you ought to tap anywhere on the screen to bring up the PAUSE [||] button and quit. Note that when playing the non-tutorial version of this mode, you'll be able to choose the accompanying music track beforehand.";

        helpTextPhaseOneList = new ArrayList<String>();

        phaseMap = new HashMap<Integer, List<String>>();
        helpTextMap = new HashMap<Integer, Map<Integer, List<String>>>();

        helpTextPhaseOneList.add(phaseOneTextOne);
        helpTextPhaseOneList.add(phaseOneTextTwo);
        helpTextPhaseOneList.add(phaseOneTextThree);

        phaseMap.put(PHASE_ONE, helpTextPhaseOneList);
        helpTextMap.put(Squirgle.GAMEPLAY_TRANCE, phaseMap);

        game.setUpFontTutorialHelp(MathUtils.round(game.ASPECT_RATIO * ((1920 / 1080) * FONT_TUTORIAL_HELP_SIZE_MULTIPLIER) / (1920 / 1080))); //Using 1920 / 1080 because that's the OG resolution--the one for which I originally developed--and I wish to scale it for other devices.

        helpLabelStyle = new Label.LabelStyle();
        helpLabelStyle.font = game.fontTutorialHelp;
        helpLabelStyle.fontColor = Color.BLACK;
        helpLabel = new Label(getCurrentHelpText(), helpLabelStyle);
        helpLabel.setSize((3 * game.camera.viewportWidth) / 8, (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 16));
        helpLabel.setPosition((5 * game.camera.viewportWidth) / 16, (game.camera.viewportHeight / 4) + (game.camera.viewportWidth / 16));
        helpLabel.setAlignment(Align.topLeft);
        helpLabel.setWrap(true);
        helpLabel.setVisible(helpTextVisible && !paused);

        stage = new Stage(game.viewport);
        stage.addActor(helpLabel);
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
