package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class BaseSelectScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BASE_4 = 0;
    private final static int BASE_5 = 1;
    private final static int BASE_6 = 2;
    private final static int BASE_7 = 3;
    private final static int BASE_8 = 4;
    private final static int BASE_9 = 5;
    private final static int BACK = 6;

    private final static int PARTITION_DIVISOR = 80;
    private final static int LINE_WIDTH = 20;

    private int numberOfBaseInputs;

    private float partitionSize;
    private float inputWidth;
    private float inputHeightBase;
    private float inputHeightBack;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private boolean base4Touched;
    private boolean base5Touched;
    private boolean base6Touched;
    private boolean base7Touched;
    private boolean base8Touched;
    private boolean base9Touched;
    private boolean backTouched;

    public BaseSelectScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numberOfBaseInputs = game.maxBase - game.minBase + 1;

        partitionSize = game.camera.viewportHeight / PARTITION_DIVISOR;
        inputWidth = (game.camera.viewportWidth - (partitionSize * 3)) / 2;
        inputHeightBase = (game.camera.viewportHeight - ((numberOfBaseInputs + 1) * partitionSize)) / numberOfBaseInputs;
        inputHeightBack = game.camera.viewportHeight - (partitionSize * 2);

        inputShapeRadius = inputWidth > inputHeightBase ? (inputHeightBase / 4) : (inputWidth / 4);

        touchPoint = new Vector3();

        base4Touched = false;
        base5Touched = false;
        base6Touched = false;
        base7Touched = false;
        base8Touched = false;
        base9Touched = false;
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

        base4Touched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > partitionSize
                && touchPoint.y < partitionSize + inputHeightBase;
        base5Touched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > (2 * partitionSize) + inputHeightBase
                && touchPoint.y < (2 * partitionSize) + (2 * inputHeightBase);
        base6Touched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > (3 * partitionSize) + (2 *inputHeightBase)
                && touchPoint.y < (3 * partitionSize) + (3 * inputHeightBase);
        base7Touched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > (4 * partitionSize) + (3 *inputHeightBase)
                && touchPoint.y < (4 * partitionSize) + (4 * inputHeightBase);
        base8Touched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > (5 * partitionSize) + (4 *inputHeightBase)
                && touchPoint.y < (5 * partitionSize) + (5 * inputHeightBase);
        base9Touched = touchPoint.x > partitionSize
                && touchPoint.x < partitionSize + inputWidth
                && touchPoint.y > (6 * partitionSize) + (5 *inputHeightBase)
                && touchPoint.y < (6 * partitionSize) + (6 * inputHeightBase);
        backTouched = touchPoint.x > (2 * partitionSize) + inputWidth
                && touchPoint.x < game.camera.viewportWidth - partitionSize
                && touchPoint.y > partitionSize
                && touchPoint.y < game.camera.viewportHeight - partitionSize;

        if(base4Touched) {
            game.base = 4;
            game.setScreen(new GameplayScreen(game));
            dispose();
        } else if(base5Touched) {
            game.base = 5;
            game.setScreen(new GameplayScreen(game));
            dispose();
        } else if(base6Touched) {
            game.base = 6;
            game.setScreen(new GameplayScreen(game));
            dispose();
        } else if(base7Touched) {
            game.base = 7;
            game.setScreen(new GameplayScreen(game));
            dispose();
        } else if(base8Touched) {
            game.base = 8;
            game.setScreen(new GameplayScreen(game));
            dispose();
        } else if(base9Touched) {
            game.base = 9;
            game.setScreen(new GameplayScreen(game));
            dispose();
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

    public void drawInputRectangle(int placement) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        switch(placement) {
            case BASE_4 : {
                game.shapeRendererFilled.rect(partitionSize,
                        partitionSize,
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_5 : {
                game.shapeRendererFilled.rect(partitionSize,
                        (2 * partitionSize) + inputHeightBase,
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_6 : {
                game.shapeRendererFilled.rect(partitionSize,
                        (3 * partitionSize) + (2 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_7 : {
                game.shapeRendererFilled.rect(partitionSize,
                        (4 * partitionSize) + (3 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_8 : {
                game.shapeRendererFilled.rect(partitionSize,
                        (5 * partitionSize) + (4 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_9 : {
                game.shapeRendererFilled.rect(partitionSize,
                        (6 * partitionSize) + (5 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BACK : {
                game.shapeRendererFilled.rect((2 * partitionSize) + inputWidth,
                        partitionSize,
                        inputWidth,
                        inputHeightBack);
            }
        }
    }

    public void drawBase4Input() {
        drawInputRectangle(BASE_4);
        game.draw.drawSquare(game.camera.viewportWidth / 4,
                partitionSize + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase5Input() {
        drawInputRectangle(BASE_5);
        game.draw.drawPentagon(game.camera.viewportWidth / 4,
                (2 * partitionSize) + inputHeightBase + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase6Input() {
        drawInputRectangle(BASE_6);
        game.draw.drawHexagon(game.camera.viewportWidth / 4,
                (3 * partitionSize) + (2 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase7Input() {
        drawInputRectangle(BASE_7);
        game.draw.drawSeptagon(game.camera.viewportWidth / 4,
                (4 * partitionSize) + (3 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase8Input() {
        drawInputRectangle(BASE_8);
        game.draw.drawOctagon(game.camera.viewportWidth / 4,
                (5 * partitionSize) + (4 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / 8,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase9Input() {
        drawInputRectangle(BASE_9);
        game.draw.drawNonagon(game.camera.viewportWidth / 4,
                (6 * partitionSize) + (5 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / 8,
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
