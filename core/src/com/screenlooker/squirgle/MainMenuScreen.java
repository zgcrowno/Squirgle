package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

//TODO: Eventually implement InputProcessor
public class MainMenuScreen implements Screen {

    final Squirgle game;

    public MainMenuScreen(final Squirgle game) {
        this.game = game;

        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false, 768, 1024);

        game.resetInstanceData();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        FontUtils.printText(game.batch, game.font, game.layout, Color.WHITE, "Tap anywhere to Squirgle", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameplayScreen(game));
            dispose();
        }
    }

    @Override
    public void resize (int width, int height) {

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

}
