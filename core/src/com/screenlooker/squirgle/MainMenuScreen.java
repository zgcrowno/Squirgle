package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MainMenuScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int TOP = 0;
    private final static int MIDDLE = 1;
    private final static int BOTTOM = 2;

    private final static int NUMBER_OF_INPUTS = 3;
    private final static int PARTITION_DIVISOR = 80;
    private final static int LINE_WIDTH = 20;

    public MainMenuScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        float partitionSize = game.camera.viewportHeight / PARTITION_DIVISOR;

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        drawInputRectangles(partitionSize);

        game.shapeRendererFilled.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameplayScreen(game));
            dispose();
        }
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

    public void drawInputs() {

    }

    public void drawInputRectangles(float partitionSize) {
        drawOptionsInput(partitionSize);
        drawPlayInput(partitionSize);
        drawStatsInput(partitionSize);
    }

    public void drawInputRectangle(int placement, float partitionSize) {
        float width = game.camera.viewportWidth - (partitionSize * 2);
        float height = (game.camera.viewportHeight - (partitionSize * 4)) / 3;

        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case TOP : {
                game.shapeRendererFilled.rect(partitionSize,
                        game.camera.viewportHeight - partitionSize - height,
                        width,
                        height);
            }
            case MIDDLE : {
                game.shapeRendererFilled.rect(partitionSize,
                        (game.camera.viewportHeight / 2) - (height / 2),
                        width,
                        height);
            }
            case BOTTOM : {
                game.shapeRendererFilled.rect(partitionSize,
                        partitionSize,
                        width,
                        height);
            }
        }
    }

    public void drawOptionsInput(float partitionSize) {
        drawInputRectangle(TOP, partitionSize);
        game.draw.drawWrench(game.camera.viewportWidth / 2,
                game.camera.viewportHeight - (game.camera.viewportHeight / 6),
                game.camera.viewportHeight / 6,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawPlayInput(float partitionSize) {
        drawInputRectangle(MIDDLE, partitionSize);
    }

    public void drawStatsInput(float partitionSize) {
        drawInputRectangle(BOTTOM, partitionSize);
    }

}
