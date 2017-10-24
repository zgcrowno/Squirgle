package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class OptionsScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int SOUND = 0;
    private final static int BACK = 1;

    private float inputWidth;
    private float inputHeight;
    private float symbolRadius;

    private Vector3 touchPoint;

    private Color volumeColor;
    private Color backColor;

    private boolean volumeDownChevronTouched;
    private boolean volumeUpChevronTouched;
    private boolean backTouched;

    public OptionsScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = game.camera.viewportWidth - (game.partitionSize * 2);
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * 3)) / 2;
        symbolRadius = inputWidth > inputHeight ? inputHeight / 8 : inputWidth / 8;

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
                game.font,
                game.layout,
                Color.BLACK,
                String.valueOf(game.volume),
                (4 * game.camera.viewportWidth) / 6,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                0);
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

        volumeDownChevronTouched = touchPoint.x > ((3 * game.camera.viewportWidth) / 6) - symbolRadius
                && touchPoint.x < ((3 * game.camera.viewportWidth) / 6) + symbolRadius
                && touchPoint.y > (game.camera.viewportHeight - (game.camera.viewportHeight / 4)) - symbolRadius
                && touchPoint.y < (game.camera.viewportHeight - (game.camera.viewportHeight / 4)) + symbolRadius;
        volumeUpChevronTouched = touchPoint.x > ((5 * game.camera.viewportWidth) / 6) - symbolRadius
                && touchPoint.x < ((5 * game.camera.viewportWidth) / 6) + symbolRadius
                && touchPoint.y > (game.camera.viewportHeight - (game.camera.viewportHeight / 4)) - symbolRadius
                && touchPoint.y < (game.camera.viewportHeight - (game.camera.viewportHeight / 4)) + symbolRadius;
        backTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(volumeDownChevronTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            if(game.volume > 0) {
                game.volume -= 1;
            } else {
                game.volume = 1;
            }
        } else if(volumeUpChevronTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            if(game.volume < 10) {
                game.volume += 1;
            } else {
                game.volume = 0;
            }
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
        drawSoundInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case SOUND : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.camera.viewportHeight - game.partitionSize - inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case BACK : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawSoundInput() {
        drawInputRectangle(SOUND, volumeColor);
        game.draw.drawSoundSymbol(game.camera.viewportWidth / 6,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                symbolRadius,
                symbolRadius / 8,
                volumeColor,
                game.shapeRendererFilled);
        game.draw.drawLine(game.camera.viewportWidth / 3,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                symbolRadius,
                symbolRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawChevronLeft((3 * game.camera.viewportWidth) / 6,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                symbolRadius,
                symbolRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawChevronRight((5 * game.camera.viewportWidth) / 6,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                symbolRadius,
                symbolRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 4,
                symbolRadius * 2,
                symbolRadius / 4,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
