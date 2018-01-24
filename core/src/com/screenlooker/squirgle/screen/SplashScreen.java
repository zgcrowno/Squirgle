package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.screenlooker.squirgle.Squirgle;

public class SplashScreen implements Screen, InputProcessor {

    final Squirgle game;

    private Texture splashTexture;
    private long startTime;
    private float logoOriginX;
    private float logoOriginY;
    private float logoWidth;
    private float logoHeight;

    public SplashScreen(final Squirgle game) {
        this.game = game;
        this.splashTexture = new Texture(Gdx.files.internal("images/planarGazerLogo1920.png"));
        this.startTime = System.currentTimeMillis();
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

        game.batch.begin();
        game.batch.draw(splashTexture, 0, (game.camera.viewportHeight - (game.camera.viewportWidth / 2)) / 2, game.camera.viewportWidth, game.camera.viewportWidth / 2);
        game.batch.end();

        if((System.currentTimeMillis() - startTime) / 1000 > 5) {
            if(!game.playedBefore) {
                game.setScreen(new TutorialScreen(game));
            } else {
                game.setScreen(new MainMenuScreen(game));
            }
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
        splashTexture.dispose();
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
