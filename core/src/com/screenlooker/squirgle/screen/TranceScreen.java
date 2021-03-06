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

public class TranceScreen implements Screen, InputProcessor {
    final Squirgle game;

    public static float INIT_PROMPT_RADIUS;
    public static float PAUSE_INPUT_WIDTH;
    public static float PAUSE_INPUT_HEIGHT;

    private final static int PAUSE_BACK = 0;
    private final static int PAUSE_QUIT = 1;

    private final static int SHAPE_SIZE_LIMIT_MULTIPLIER = 15;
    private final static int PAUSE_INPUT_DISAPPEARANCE_TIME = 5;
    private final static int ONE_THOUSAND = 1000;

    private float promptIncrease;
    private Shape promptShape;
    private List<Shape> priorShapeList;
    private Vector3 touchPoint;
    boolean pauseTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    private boolean paused;
    public long startTime;
    public long endTime;
    public long pauseStartTime;
    public long timePaused;
    private long timeSinceTouched;
    private int destructionIndex;
    private float firstPriorShapePreviousX;
    Shape primaryShape;
    float primaryShapeThreshold;
    boolean primaryShapeAtThreshold;

    public TranceScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        setUpNonFinalNonStaticData();

        game.setUpFontButton(MathUtils.round(PAUSE_INPUT_WIDTH > PAUSE_INPUT_HEIGHT ? (PAUSE_INPUT_HEIGHT / 2) / 2.75f : (PAUSE_INPUT_WIDTH / 2) / 2.75f));

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

        if(!paused && (System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < PAUSE_INPUT_DISAPPEARANCE_TIME) {
            game.draw.drawPauseInputTrance(Color.BLACK, game);
        }

        if(game.desktop) {
            game.draw.drawCursor();
        }

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        SoundUtils.setVolume(promptShape, game);

        drawText();

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
        paused = true;
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
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

    public void drawText() {
        if(paused) {
            //Pause quit text
            game.layout.setText(game.fontButton, Button.QUIT_STRING);
            FontUtils.printText(game.batch,
                    game.fontButton,
                    game.layout,
                    Color.BLACK,
                    Button.QUIT_STRING,
                    game.camera.viewportWidth / 2,
                    game.partitionSize + ((2.7f * game.layout.height) / 4),
                    0,
                    1);

            //Pause back text
            game.layout.setText(game.fontButton, Button.BACK_STRING);
            FontUtils.printText(game.batch,
                    game.fontButton,
                    game.layout,
                    Color.BLACK,
                    Button.BACK_STRING,
                    (5 * game.camera.viewportWidth) / 6,
                    game.partitionSize + ((2.7f * game.layout.height) / 4),
                    0,
                    1);
        }
    }

    public void playMusic() {
        if(game.usePhases) {
//            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
//                game.trackMapPhase.get(game.track).get(i).play();
//            }
        } else {
            game.trackMapFull.get(game.track).play();
        }
    }

    public void stopMusic() {
        if(game.usePhases) {
//            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
//                game.trackMapPhase.get(game.track).get(i).stop();
//            }
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
    }

    public void determineKeyedInput(int keycode) {
        pauseTouched = keycode == Input.Keys.ESCAPE;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
    }

    public void handleInput() {
        if(!paused) {
            if (pauseTouched) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
                pause();
            }
        } else {
            handlePauseInput();
        }
    }

    public void handlePauseInput() {
        if (pauseBackTouched) {
            game.disconfirmSound.play((float) (game.fxVolume / 10.0));
            timePaused += System.currentTimeMillis() - pauseStartTime;
            resume();
        } else if (pauseQuitTouched) {
            game.confirmSound.play((float) (game.fxVolume / 10.0));
            endTime = System.currentTimeMillis();
            stopMusic();
            game.stats.updateTimePlayed(endTime - startTime, Squirgle.GAMEPLAY_TRANCE);
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
            dispose();
        }
    }

    public void setUpNonFinalStaticData() {
        PAUSE_INPUT_WIDTH = (game.camera.viewportWidth - (4 * game.partitionSize)) / 3;
        PAUSE_INPUT_HEIGHT = game.camera.viewportHeight - (2 * game.partitionSize);
        INIT_PROMPT_RADIUS = game.widthOrHeightSmaller / 4;
    }

    public void setUpNonFinalNonStaticData() {
        promptIncrease = 1.03f;
        promptShape = new Shape(MathUtils.random(Shape.NONAGON),
                INIT_PROMPT_RADIUS,
                ColorUtils.randomColor(),
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
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
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        paused = false;
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
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
