package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.screenlooker.squirgle.Squirgle;

import java.util.ArrayList;

public class SplashScreen implements Screen, InputProcessor {
    final Squirgle game;

    private TextureAtlas atlas;
    private Animation<TextureRegion> splashAnimation;
    private long startTime;
    private float stateTime;
    private float logoOriginX;
    private float logoOriginY;
    private float logoWidth;
    private float logoHeight;

    public SplashScreen(final Squirgle game) {
        this.game = game;
        this.atlas = game.manager.get("images/planarGazerLogoSpritesheet.atlas", TextureAtlas.class);
        this.splashAnimation = new Animation<TextureRegion>((float) 1 / Squirgle.FPS, atlas.findRegions("planarGazerLogoKeyframe1920"), Animation.PlayMode.NORMAL);
        this.startTime = System.currentTimeMillis();
        this.stateTime = 0f;
        this.logoOriginX = 0;
        this.logoOriginY = (game.camera.viewportHeight - (game.camera.viewportWidth / 2)) / 2;
        this.logoWidth = game.camera.viewportWidth;
        this.logoHeight = game.camera.viewportWidth / 2;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        stateTime += Gdx.graphics.getDeltaTime();

        game.batch.begin();
        game.batch.draw(splashAnimation.getKeyFrame(stateTime, false), logoOriginX, logoOriginY, logoWidth, logoHeight);
        game.batch.end();

        if((System.currentTimeMillis() - startTime) / 1000 > 5) {
            if(!game.playedBefore) {
                game.setScreen(new TutorialScreen(game));
            } else {
                game.setScreen(new MainMenuScreen(game));
            }
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
        atlas.dispose();
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

        if(!game.playedBefore) {
            game.setScreen(new TutorialScreen(game));
        } else {
            game.setScreen(new MainMenuScreen(game));
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
