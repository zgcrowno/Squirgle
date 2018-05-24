package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.InputUtils;

public class EpilepsyWarningScreen implements Screen, InputProcessor {
    final Squirgle game;

    private final static String WARNING = "WARNING:";
    private final static String WARNING_CONTENT = "IF YOU HAVE A HISTORY OF EPILEPSY OR SEIZURES, CONSULT A DOCTOR BEFORE USE. CERTAIN PATTERNS MAY TRIGGER SEIZURES WITH NO PRIOR HISTORY.";

    private float fontWarningSizeDivisor = 10;
    private float fontWarningContentSizeDivisor = 30;

    private long startTime;
    private Color veilColor;
    private float veilOpacity;
    private Label.LabelStyle warningLabelStyle;
    private Label warningLabel;
    private Label.LabelStyle warningContentLabelStyle;
    private Label warningContentLabel;
    private Stage stage;

    public EpilepsyWarningScreen(final Squirgle game) {
        this.game = game;
        this.startTime = System.currentTimeMillis();
        this.veilColor = Color.BLACK;
        this.veilOpacity = 1;

        fontWarningSizeDivisor = 10;
        fontWarningContentSizeDivisor = game.widthGreater ? 30 : 20;

        game.setUpFontWarning(MathUtils.round(game.camera.viewportWidth / fontWarningSizeDivisor));
        game.setUpFontWarningContent(MathUtils.round(game.camera.viewportWidth / fontWarningContentSizeDivisor));

        warningLabelStyle = new Label.LabelStyle();
        warningLabelStyle.font = game.fontWarning;
        warningLabelStyle.fontColor = Color.WHITE;
        warningLabel = new Label(WARNING, warningLabelStyle);
        warningLabel.setSize(game.camera.viewportWidth / 2, game.camera.viewportHeight / 6);
        warningLabel.setPosition(game.camera.viewportWidth / 4, (7 * game.camera.viewportHeight) / 12);
        warningLabel.setAlignment(Align.bottom);
        warningLabel.setWrap(true);
        warningLabel.setVisible(true);

        warningContentLabelStyle = new Label.LabelStyle();
        warningContentLabelStyle.font = game.fontWarningContent;
        warningContentLabelStyle.fontColor = Color.WHITE;
        warningContentLabel = new Label(WARNING_CONTENT, warningContentLabelStyle);
        warningContentLabel.setSize(game.camera.viewportWidth / 2, game.camera.viewportHeight / 3);
        warningContentLabel.setPosition(game.camera.viewportWidth / 4, game.camera.viewportHeight / 4);
        warningContentLabel.setAlignment(Align.top);
        warningContentLabel.setWrap(true);
        warningContentLabel.setVisible(true);

        stage = new Stage(game.viewport);
        stage.addActor(warningLabel);
        stage.addActor(warningContentLabel);

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        stage.draw();

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
            game.setScreen(new HeadphonesRecommendationScreen(game));
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
        //atlas.dispose();
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

        game.setScreen(new HeadphonesRecommendationScreen(game));

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
