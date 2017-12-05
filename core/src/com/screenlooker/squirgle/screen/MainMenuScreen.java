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
import com.screenlooker.squirgle.util.ColorUtils;

public class MainMenuScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int OPTIONS = 0;
    private final static int PLAY = 1;
    private final static int TUTORIAL = 2;
    private final static int QUIT = 3;

    private float inputWidth;
    private float inputHeight;

    private Vector3 touchPoint;

    private Color optionsColor;
    private Color playColor;
    private Color tutorialColor;
    private Color quitColor;

    private boolean optionsTouched;
    private boolean playTouched;
    private boolean tutorialTouched;
    private boolean quitTouched;

    public MainMenuScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = game.camera.viewportWidth - (game.partitionSize * 2);
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * 5)) / 4;

        touchPoint = new Vector3();

        optionsColor = com.screenlooker.squirgle.util.ColorUtils.randomColor();
        playColor = com.screenlooker.squirgle.util.ColorUtils.randomColor();
        tutorialColor = com.screenlooker.squirgle.util.ColorUtils.randomColor();
        quitColor = ColorUtils.randomColor();

        optionsTouched = false;
        playTouched = false;
        tutorialTouched = false;
        quitTouched = false;
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

        optionsTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.camera.viewportHeight - game.partitionSize - inputHeight
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        playTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.camera.viewportHeight - (2 * game.partitionSize) - (2 * inputHeight)
                && touchPoint.y < game.camera.viewportHeight - (2 * game.partitionSize) - inputHeight;
        tutorialTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > (2 * game.partitionSize) + inputHeight
                && touchPoint.y < (2 * game.partitionSize) + (2 * inputHeight);
        quitTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(optionsTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new OptionsScreen(game));
            dispose();
        } else if(playTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new BaseSelectScreen(game));
            dispose();
        } else if(tutorialTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new TutorialScreen(game));
            dispose();
        } else if(quitTouched) {
            System.exit(0);
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
        drawOptionsInput();
        drawPlayInput();
        drawTutorialInput();
        drawQuitInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case OPTIONS : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.camera.viewportHeight - game.partitionSize - inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case PLAY : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.camera.viewportHeight - (2 * game.partitionSize) - (2 * inputHeight),
                        inputWidth,
                        inputHeight);
            }
            case TUTORIAL : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        (2 * game.partitionSize) + inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case QUIT : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawOptionsInput() {
        float radius = game.camera.viewportHeight / Draw.LINE_WIDTH_DIVISOR;

        drawInputRectangle(OPTIONS, optionsColor);
        game.draw.drawWrench(game.camera.viewportWidth / 2,
                game.camera.viewportHeight - (game.camera.viewportHeight / 8),
                radius,
                radius / Draw.LINE_WIDTH_DIVISOR,
                optionsColor,
                game.shapeRendererFilled);
    }

    public void drawPlayInput() {
        float radius = game.camera.viewportHeight / 8;

        drawInputRectangle(PLAY, playColor);
        game.draw.drawPlayButton(game.camera.viewportWidth / 2,
                game.camera.viewportHeight - ((3 * game.camera.viewportHeight) / 8),
                radius,
                radius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawTutorialInput() {
        float radius = inputHeight;

        drawInputRectangle(TUTORIAL, tutorialColor);
        game.draw.drawQuestionMark(game.camera.viewportWidth / 2,
                (2 * game.partitionSize) + (inputHeight / 2) + inputHeight,
                radius,
                radius / Draw.LINE_WIDTH_DIVISOR,
                tutorialColor,
                game.shapeRendererFilled);
    }

    public void drawQuitInput() {
        float radius = game.camera.viewportHeight / 8;

        drawInputRectangle(QUIT, quitColor);
        game.draw.drawX(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 8,
                radius,
                radius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
