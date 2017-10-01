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
    private final static int COLOR = 1;
    private final static int CONNECTIVITY = 2;
    private final static int BACK = 3;

    private final static int PARTITION_DIVISOR = 80;
    private final static int LINE_WIDTH = 20;

    private float partitionSize;
    private float inputWidth;
    private float inputHeight;

    private Vector3 touchPoint;

    private boolean soundTouched;
    private boolean colorTouched;
    private boolean connectivityTouched;
    private boolean backTouched;

    public OptionsScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        partitionSize = game.camera.viewportHeight / PARTITION_DIVISOR;
        inputWidth = (game.camera.viewportWidth - (partitionSize * 3)) / 2;
        inputHeight = (game.camera.viewportHeight - (partitionSize * 3)) / 2;

        touchPoint = new Vector3();

        soundTouched = false;
        colorTouched = false;
        connectivityTouched = false;
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

        soundTouched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > (2 * partitionSize) + inputHeight
                && touchPoint.y < partitionSize;
        colorTouched = touchPoint.x > (2 * partitionSize) + inputWidth
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > (2 * partitionSize) + inputHeight
                && touchPoint.y < partitionSize;
        connectivityTouched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > partitionSize
                && touchPoint.y < partitionSize + inputHeight;
        backTouched = touchPoint.x > (2 * partitionSize) + inputWidth
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > partitionSize
                && touchPoint.y < partitionSize + inputHeight;

        if(soundTouched) {
            //TODO: redirect to sound screen
        } else if(colorTouched) {
            //TODO: redirect to color screen
        } else if(connectivityTouched) {
            //TODO: redirect to connectivity screen
        } else if(backTouched) {
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
        drawColorInput();
        drawConnectivityInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case SOUND : {
                game.shapeRendererFilled.rect(partitionSize,
                        game.camera.viewportHeight - partitionSize - inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case COLOR : {
                game.shapeRendererFilled.rect((2 * partitionSize) + inputWidth,
                        game.camera.viewportHeight - partitionSize - inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case CONNECTIVITY : {
                game.shapeRendererFilled.rect(partitionSize,
                        partitionSize,
                        inputWidth,
                        inputHeight);
            }
            case BACK : {
                game.shapeRendererFilled.rect((2 * partitionSize) + inputWidth,
                        partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawSoundInput() {
        float radius = game.camera.viewportWidth > game.camera.viewportHeight ? game.camera.viewportHeight / 8 : game.camera.viewportWidth / 8;

        drawInputRectangle(SOUND);
        game.draw.drawSoundSymbol(game.camera.viewportWidth / 4,
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                radius,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawColorInput() {
        float radius = game.camera.viewportWidth > game.camera.viewportHeight ? game.camera.viewportHeight / 8 : game.camera.viewportWidth / 8;

        drawInputRectangle(COLOR);
        game.draw.drawColorSymbol(game.camera.viewportWidth - (game.camera.viewportWidth / 4),
                game.camera.viewportHeight - (game.camera.viewportHeight / 4),
                radius,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawConnectivityInput() {
        float radius = game.camera.viewportWidth > game.camera.viewportHeight ? game.camera.viewportHeight / 8 : game.camera.viewportWidth / 8;

        drawInputRectangle(CONNECTIVITY);
        game.draw.drawWiFiSymbol(game.camera.viewportWidth / 4,
                game.camera.viewportHeight / 4,
                radius,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBackInput() {
        float radius = game.camera.viewportWidth > game.camera.viewportHeight ? game.camera.viewportHeight / 8 : game.camera.viewportWidth / 8;

        drawInputRectangle(BACK);
        game.draw.drawBackButton(game.camera.viewportWidth - (game.camera.viewportWidth / 4),
                game.camera.viewportHeight / 4,
                radius,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
