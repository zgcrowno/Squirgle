package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class StatsPersonalScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int PERSONAL = 0;
    private final static int COMMUNAL = 1;
    private final static int BACK = 2;

    private final static int PARTITION_DIVISOR = 80;
    private final static int LINE_WIDTH = 20;

    private float partitionSize;
    private float inputWidth;
    private float inputHeight;

    private Vector3 touchPoint;

    private boolean personalTouched;
    private boolean communalTouched;
    private boolean backTouched;

    public StatsPersonalScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        partitionSize = game.camera.viewportHeight / PARTITION_DIVISOR;
        inputWidth = (game.camera.viewportWidth - (partitionSize * 4)) / 3;
        inputHeight = game.camera.viewportHeight - (partitionSize * 2);

        touchPoint = new Vector3();

        personalTouched = false;
        communalTouched = false;
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

        personalTouched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > partitionSize
                && touchPoint.y < game.camera.viewportHeight - partitionSize;
        communalTouched = touchPoint.x > (2 * partitionSize) + inputWidth
                && touchPoint.x < game.camera.viewportWidth - (2 *partitionSize) - inputWidth
                && touchPoint.y > partitionSize
                && touchPoint.y < game.camera.viewportHeight - partitionSize;
        backTouched = touchPoint.x > game.camera.viewportWidth - partitionSize - inputWidth
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > partitionSize
                && touchPoint.y < game.camera.viewportHeight - partitionSize;

        if(personalTouched) {
            //TODO: Redirect to personal stats screen
        } else if(communalTouched) {
            //TODO: Redirect to communal stats screen
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
        drawPersonalInput();
        drawCommunalInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case PERSONAL : {
                game.shapeRendererFilled.rect(partitionSize,
                        partitionSize,
                        inputWidth,
                        inputHeight);
            }
            case COMMUNAL : {
                game.shapeRendererFilled.rect((2 *partitionSize) + inputWidth,
                        partitionSize,
                        inputWidth,
                        inputHeight);
            }
            case BACK : {
                game.shapeRendererFilled.rect(game.camera.viewportWidth - partitionSize - inputWidth,
                        partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawPersonalInput() {
        drawInputRectangle(PERSONAL);
        game.draw.drawFace(game.camera.viewportWidth / 6,
                game.camera.viewportHeight - (game.camera.viewportHeight / 6),
                game.camera.viewportWidth / 12,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawCommunalInput() {
        drawInputRectangle(COMMUNAL);
        game.draw.drawFace(game.camera.viewportWidth / 2,
                (2 * game.camera.viewportHeight) / 6,
                game.camera.viewportWidth / 12,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawFace(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 2,
                game.camera.viewportWidth / 12,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawFace(game.camera.viewportWidth / 2,
                (2 * game.camera.viewportHeight) / 3,
                game.camera.viewportWidth / 12,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK);
        game.draw.drawBackButton((5 * game.camera.viewportWidth) / 6,
                game.camera.viewportHeight / 6,
                game.camera.viewportWidth / 12,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
