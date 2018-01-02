package com.screenlooker.squirgle.screen;

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
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

public class OptionsScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int SOUND = 0;
    private final static int BACK = 1;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;

    private final static int NUM_SOUND_INPUT_ELEMENTS = 4;

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color volumeColor;
    private Color backColor;

    private boolean volumeDownChevronTouched;
    private boolean volumeUpChevronTouched;
    private boolean backTouched;

    //TODO: Set up fontScore
    public OptionsScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;

        touchPoint = new Vector3();

        volumeColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        volumeDownChevronTouched = false;
        volumeUpChevronTouched = false;
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

        //Draw volume
        FontUtils.printText(game.batch,
                game.fontScore,
                game.layout,
                Color.BLACK,
                String.valueOf(game.volume),
                (2 * game.partitionSize) + inputWidth + ((3 * inputWidth) / 5),
                game.camera.viewportHeight / 2,
                0,
                1);
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

        volumeDownChevronTouched = touchPoint.x > ((2 * game.partitionSize) + inputWidth + ((2 * inputWidth) / 5)) - (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1))
                && touchPoint.x < ((2 * game.partitionSize) + inputWidth + ((2 * inputWidth) / 5)) + (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1))
                && touchPoint.y > (game.camera.viewportHeight / 2) - (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1))
                && touchPoint.y < (game.camera.viewportHeight / 2) + (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1));
        volumeUpChevronTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth + ((4 * inputWidth) / 5) - (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1))
                && touchPoint.x < (2 * game.partitionSize) + inputWidth + ((4 * inputWidth) / 5) + (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1))
                && touchPoint.y > (game.camera.viewportHeight / 2) - (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1))
                && touchPoint.y < (game.camera.viewportHeight / 2) + (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1));
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(volumeDownChevronTouched) {
            if(game.volume > 0) {
                game.volume -= 1;
            } else {
                game.volume = 10;
            }
            game.trackMap.get(game.MUSIC_THEME_FROM_SQUIRGLE).get(0).setVolume((float) (game.volume / 10.0));
            game.disconfirmSound.play((float) (game.volume / 10.0));
        } else if(volumeUpChevronTouched) {
            if(game.volume < 10) {
                game.volume += 1;
            } else {
                game.volume = 0;
            }
            game.trackMap.get(game.MUSIC_THEME_FROM_SQUIRGLE).get(0).setVolume((float) (game.volume / 10.0));
            game.confirmSound.play((float) (game.volume / 10.0));
        } else if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.updateSave(game.SAVE_VOLUME, game.volume);
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
        drawSoundInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case SOUND : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        game.camera.viewportHeight - game.partitionSize - inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case BACK : {
                game.shapeRendererFilled.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawSoundInput() {
        drawInputRectangle(SOUND, volumeColor);
        game.draw.drawSoundSymbol((2 * game.partitionSize) + inputWidth + (inputWidth / 5),
                game.camera.viewportHeight / 2,
                symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1),
                (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                volumeColor,
                game.shapeRendererFilled);
        game.draw.drawChevronLeft((2 * game.partitionSize) + inputWidth + ((2 * inputWidth) / 5),
                game.camera.viewportHeight / 2,
                symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1),
                (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawChevronRight((2 * game.partitionSize) + inputWidth + ((4 * inputWidth) / 5),
                game.camera.viewportHeight / 2,
                symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1),
                (symbolRadius / (NUM_SOUND_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawTitle() {
        game.draw.drawWrench(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
