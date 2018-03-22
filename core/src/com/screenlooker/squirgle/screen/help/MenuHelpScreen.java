package com.screenlooker.squirgle.screen.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.screen.TutorialScreen;
import com.screenlooker.squirgle.util.ColorUtils;

public class MenuHelpScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BASE_4 = 0;
    private final static int BASE_5 = 1;
    private final static int BASE_6 = 2;
    private final static int BASE_7 = 3;
    private final static int BASE_8 = 4;
    private final static int BASE_9 = 5;
    private final static int COLOR = 6;
    private final static int STATS = 7;
    private final static int PLAY = 8;
    private final static int BACK = 9;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;

    private int numberOfBaseInputs;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightBase;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color base4Color;
    private Color base5Color;
    private Color base6Color;
    private Color base7Color;
    private Color base8Color;
    private Color base9Color;
    private Color colorColor;
    private Color statsColor;
    private Color playColor;
    private Color backColor;

    private boolean base4Touched;
    private boolean base5Touched;
    private boolean base6Touched;
    private boolean base7Touched;
    private boolean base8Touched;
    private boolean base9Touched;
    private boolean colorTouched;
    private boolean statsTouched;
    private boolean playTouched;
    private boolean backTouched;

    public MenuHelpScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numberOfBaseInputs = game.maxBase - game.minBase + 4; //Adding three more than expected here because of color, play and stats inputs

        numMiddleInputsVertical = numberOfBaseInputs;
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightBase = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightBase ? (inputHeightBase / 2) : (inputWidth / 2);

        touchPoint = new Vector3();

        base4Color = ColorUtils.randomColor();
        base5Color = ColorUtils.randomColor();
        base6Color = ColorUtils.randomColor();
        base7Color = ColorUtils.randomColor();
        base8Color = ColorUtils.randomColor();
        base9Color = ColorUtils.randomColor();
        colorColor = ColorUtils.randomColor();
        statsColor = ColorUtils.randomColor();
        playColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        base4Touched = false;
        base5Touched = false;
        base6Touched = false;
        base7Touched = false;
        base8Touched = false;
        base9Touched = false;
        colorTouched = false;
        statsTouched = false;
        playTouched = false;
        backTouched = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        drawInputRectangles();

        game.shapeRendererFilled.end();
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

    }

    @Override
    public void resume() {

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

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        playTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeightBase;
        statsTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (2 * game.partitionSize) + inputHeightBase
                && touchPoint.y < (2 * game.partitionSize) + (2 * inputHeightBase);
        colorTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (3 * game.partitionSize) + (2 * inputHeightBase)
                && touchPoint.y < (3 * game.partitionSize) + (3 * inputHeightBase);
        base4Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (4 * game.partitionSize) + (3 *inputHeightBase)
                && touchPoint.y < (4 * game.partitionSize) + (4 * inputHeightBase);
        base5Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (5 * game.partitionSize) + (4 *inputHeightBase)
                && touchPoint.y < (5 * game.partitionSize) + (5 * inputHeightBase);
        base6Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (6 * game.partitionSize) + (5 *inputHeightBase)
                && touchPoint.y < (6 * game.partitionSize) + (6 * inputHeightBase);
        base7Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (7 * game.partitionSize) + (6 *inputHeightBase)
                && touchPoint.y < (7 * game.partitionSize) + (7 * inputHeightBase);
        base8Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (8 * game.partitionSize) + (7 *inputHeightBase)
                && touchPoint.y < (8 * game.partitionSize) + (8 * inputHeightBase);
        base9Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (9 * game.partitionSize) + (8 *inputHeightBase)
                && touchPoint.y < (9 * game.partitionSize) + (9 * inputHeightBase);
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;

        if(playTouched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
            dispose();
        } else if(colorTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpAdditionColorScreen(game));
            dispose();
        } else if(base4Touched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 4;
            game.setScreen(new MenuHelpAdditionBaseScreen(game));
            dispose();
        } else if(base5Touched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 5;
            game.setScreen(new MenuHelpAdditionBaseScreen(game));
            dispose();
        } else if(base6Touched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 6;
            game.setScreen(new MenuHelpAdditionBaseScreen(game));
            dispose();
        } else if(base7Touched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 7;
            game.setScreen(new MenuHelpAdditionBaseScreen(game));
            dispose();
        } else if(base8Touched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 8;
            game.setScreen(new MenuHelpAdditionBaseScreen(game));
            dispose();
        } else if(base9Touched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 9;
            game.setScreen(new MenuHelpAdditionBaseScreen(game));
            dispose();
        } else if(statsTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsScreen(game));
            dispose();
        } else if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MainMenuScreen(game));
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
        drawTitle();
        drawPlayInput();
        drawStatsInput();
        drawColorInput();
        drawBase4Input();
        if(game.maxBase >= 5) {
            drawBase5Input();
        }
        if(game.maxBase >= 6) {
            drawBase6Input();
        }
        if(game.maxBase >= 7) {
            drawBase7Input();
        }
        if(game.maxBase >= 8) {
            drawBase8Input();
        }
        if(game.maxBase >= 9) {
            drawBase9Input();
        }
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        switch(placement) {
            case PLAY : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case STATS : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (2 * game.partitionSize) + inputHeightBase,
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case COLOR : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (3 * game.partitionSize) + (2 * inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BASE_4 : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (4 * game.partitionSize) + (3 *inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BASE_5 : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (5 * game.partitionSize) + (4 *inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BASE_6 : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (6 * game.partitionSize) + (5 *inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BASE_7 : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (7 * game.partitionSize) + (6 *inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BASE_8 : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (8 * game.partitionSize) + (7 *inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BASE_9 : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        (9 * game.partitionSize) + (8 *inputHeightBase),
                        inputWidth,
                        inputHeightBase,
                        color);
            }
            case BACK : {
                game.draw.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeightBack,
                        color);
            }
        }
    }

    public void drawPlayInput() {
        drawInputRectangle(PLAY, playColor);
        game.draw.drawPlayButton(game.camera.viewportWidth / 2,
                game.partitionSize + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawStatsInput() {
        drawInputRectangle(STATS, statsColor);
        game.draw.drawModulo((2 * game.partitionSize) + inputWidth + (inputWidth / 2),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawColorInput() {
        drawInputRectangle(COLOR, colorColor);
        game.draw.drawColorWheel(game.camera.viewportWidth / 2,
                (3 * game.partitionSize) + (2 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                Color.BLACK);
    }

    public void drawBase4Input() {
        drawInputRectangle(BASE_4, base4Color);
        game.draw.drawSquare(game.camera.viewportWidth / 2,
                (4 * game.partitionSize) + (3 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawBase5Input() {
        drawInputRectangle(BASE_5, base5Color);
        game.draw.drawPentagon(game.camera.viewportWidth / 2,
                (5 * game.partitionSize) + (4 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
    }

    public void drawBase6Input() {
        drawInputRectangle(BASE_6, base6Color);
        game.draw.drawHexagon(game.camera.viewportWidth / 2,
                (6 * game.partitionSize) + (5 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
    }

    public void drawBase7Input() {
        drawInputRectangle(BASE_7, base7Color);
        game.draw.drawSeptagon(game.camera.viewportWidth / 2,
                (7 * game.partitionSize) + (6 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
    }

    public void drawBase8Input() {
        drawInputRectangle(BASE_8, base8Color);
        game.draw.drawOctagon(game.camera.viewportWidth / 2,
                (8 * game.partitionSize) + (7 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
    }

    public void drawBase9Input() {
        drawInputRectangle(BASE_9, base9Color);
        game.draw.drawNonagon(game.camera.viewportWidth / 2,
                (9 * game.partitionSize) + (8 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
    }

}
