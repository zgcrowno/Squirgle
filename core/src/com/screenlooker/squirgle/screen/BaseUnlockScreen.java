package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseUnlockScreen implements Screen, InputProcessor {

    final Squirgle game;

    private static final float RADIUS_INCREMENT = 1f;
    private static final float ROTATION_INCREMENT = 0.01f;
    private static final float VEIL_OPACITY_INCREMENT = 0.05f;

    private Color backgroundColor;
    private Color veilColor;

    private float shapeRadius;
    private float shapeRotation;
    private float veilOpacity;

    private long startTime;

    public BaseUnlockScreen(final Squirgle game, Color backgroundColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        this.backgroundColor = backgroundColor;
        this.veilColor = Color.BLACK;
        this.shapeRadius = 0;
        this.shapeRotation = 0;
        this.veilOpacity = 0;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        if(game.maxBase == 5) {
            game.draw.drawPentagon(game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    shapeRadius,
                    shapeRadius / Draw.LINE_WIDTH_DIVISOR,
                    shapeRotation,
                    backgroundColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
        } else if(game.maxBase == 6) {
            game.draw.drawHexagon(game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    shapeRadius,
                    shapeRadius / Draw.LINE_WIDTH_DIVISOR,
                    shapeRotation,
                    backgroundColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
        } else if(game.maxBase == 7) {
            game.draw.drawSeptagon(game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    shapeRadius,
                    shapeRadius / Draw.LINE_WIDTH_DIVISOR,
                    shapeRotation,
                    backgroundColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
        } else if(game.maxBase == 8) {
            game.draw.drawOctagon(game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    shapeRadius,
                    shapeRadius / Draw.LINE_WIDTH_DIVISOR,
                    shapeRotation,
                    backgroundColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
        } else if(game.maxBase == 9) {
            game.draw.drawNonagon(game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    shapeRadius,
                    shapeRadius / Draw.LINE_WIDTH_DIVISOR,
                    shapeRotation,
                    backgroundColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
        }

        game.draw.drawVeil(veilColor, veilOpacity);

        if(game.desktop) {
            game.draw.drawCursor();
        }

        game.shapeRendererFilled.end();

        shapeRadius += RADIUS_INCREMENT;
        shapeRotation += ROTATION_INCREMENT;

        if((System.currentTimeMillis() - startTime) / 1000 > 5) {
            veilOpacity += VEIL_OPACITY_INCREMENT;
        }

        if(veilOpacity >= 1) {
            game.setScreen(new MainMenuScreen(game, veilColor));
        }

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
}
