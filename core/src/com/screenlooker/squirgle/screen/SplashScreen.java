package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.InputUtils;

import java.util.ArrayList;

public class SplashScreen implements Screen, InputProcessor {
    final Squirgle game;

    private Texture splashTexture;
    private long startTime;
    private float logoOriginX;
    private float logoOriginY;
    private float logoWidth;
    private float logoHeight;
    private Color veilColor;
    private float veilOpacity;

    public SplashScreen(final Squirgle game) {
        this.game = game;
        this.splashTexture = new Texture(Gdx.files.internal("images/planarGazerLogo1920.png"));
        this.startTime = System.currentTimeMillis();
        this.logoOriginX = 0;
        this.logoOriginY = (game.camera.viewportHeight - (game.camera.viewportWidth / 2)) / 2;
        this.logoWidth = game.camera.viewportWidth;
        this.logoHeight = game.camera.viewportWidth / 2;
        this.veilColor = Color.BLACK;
        this.veilOpacity = 1;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.batch.draw(splashTexture, logoOriginX, logoOriginY, logoWidth, logoHeight);

        game.batch.end();

        //NOTE: Have to enable and disable blending very deliberately here so batch and shaperenderer don't conflict.
        Gdx.gl.glEnable(GL30.GL_BLEND);
        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        game.draw.drawVeil(veilColor, veilOpacity);

        game.shapeRendererFilled.end();
        Gdx.gl.glDisable(GL30.GL_BLEND);

        if((System.currentTimeMillis() - startTime) / 1000 > 5) {
            veilOpacity += 0.05f;
        } else if(veilOpacity > 0) {
            veilOpacity -= 0.05f;
        }

        if(veilOpacity >= 1) {
            game.setScreen(new EpilepsyWarningScreen(game));
            dispose();
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

        game.setScreen(new EpilepsyWarningScreen(game));

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
