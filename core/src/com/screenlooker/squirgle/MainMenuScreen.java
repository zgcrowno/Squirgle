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
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int OPTIONS = 0;
    private final static int PLAY = 1;
    private final static int STATS = 2;

    private final static int PARTITION_DIVISOR = 80;
    private final static int LINE_WIDTH = 20;

    private float partitionSize;
    private float inputWidth;
    private float inputHeight;

    private Vector3 touchPoint;

    private boolean optionsTouched;
    private boolean playTouched;
    private boolean statsTouched;

    public MainMenuScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        partitionSize = game.camera.viewportHeight / PARTITION_DIVISOR;
        inputWidth = game.camera.viewportWidth - (partitionSize * 2);
        inputHeight = (game.camera.viewportHeight - (partitionSize * 4)) / 3;

        touchPoint = new Vector3();

        optionsTouched = false;
        playTouched = false;
        statsTouched = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
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

        optionsTouched = touchPoint.x > partitionSize
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > game.camera.viewportHeight - partitionSize - inputHeight
                && touchPoint.y < game.camera.viewportHeight - partitionSize;
        playTouched = touchPoint.x > partitionSize
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > game.camera.viewportHeight - (2 * partitionSize) - (2 * inputHeight)
                && touchPoint.y < game.camera.viewportHeight - (2 * partitionSize) - inputHeight;
        statsTouched = touchPoint.x > partitionSize
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > partitionSize
                && touchPoint.y < partitionSize + inputHeight;

        if(optionsTouched) {
            game.setScreen(new OptionsScreen(game));
            dispose();
        } else if(playTouched) {
            game.setScreen(new GameplayScreen(game));
            dispose();
        } else if(statsTouched) {
            //TODO: redirect to stats select screen
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
        drawOptionsInput();
        drawPlayInput();
        drawStatsInput();
    }

    public void drawInputRectangle(int placement) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case OPTIONS : {
                game.shapeRendererFilled.rect(partitionSize,
                        game.camera.viewportHeight - partitionSize - inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case PLAY : {
                game.shapeRendererFilled.rect(partitionSize,
                        (game.camera.viewportHeight / 2) - (inputHeight / 2),
                        inputWidth,
                        inputHeight);
            }
            case STATS : {
                game.shapeRendererFilled.rect(partitionSize,
                        partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawOptionsInput() {
        drawInputRectangle(OPTIONS);
        game.draw.drawWrench(game.camera.viewportWidth / 2,
                game.camera.viewportHeight - (game.camera.viewportHeight / 6),
                game.camera.viewportHeight / 6,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawPlayInput() {
        drawInputRectangle(PLAY);
        game.draw.drawPlayButton(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 2,
                game.camera.viewportHeight / 6,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawStatsInput() {
        drawInputRectangle(STATS);
        game.draw.drawModulo(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 6,
                game.camera.viewportHeight / 6,
                LINE_WIDTH,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
