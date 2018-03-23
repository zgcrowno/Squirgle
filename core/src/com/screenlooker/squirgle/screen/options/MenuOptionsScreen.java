package com.screenlooker.squirgle.screen.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuOptionsScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;
    public final static int NUM_SOUND_INPUT_ELEMENTS = 4;

    private final static float FONT_VOLUME_SIZE_DIVISOR = 11.1f;

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color volumeColor;
    private Color backColor;

    private boolean volumeDownChevronTouched;
    private boolean volumeUpChevronTouched;
    private boolean backTouched;

    private Button volumeButton;
    private Button volumeWavesButton;
    private Button volumeChevronDownButton;
    private Button volumeChevronUpButton;
    private Button backButton;

    private List<Button> buttonList;

    //TODO: Set up fontScore
    public MenuOptionsScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontVolume(MathUtils.round(game.camera.viewportWidth / FONT_VOLUME_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;

        touchPoint = new Vector3();

        volumeColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        volumeDownChevronTouched = false;
        volumeUpChevronTouched = false;
        backTouched = false;

        volumeButton = new Button((2 * game.partitionSize) + inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_VOLUME,
                volumeColor,
                Color.BLACK,
                game);
        volumeWavesButton = new Button((2 * game.partitionSize) + inputWidth + (inputWidth / 10),
                game.partitionSize + (inputHeight / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_VOLUME_WAVES,
                volumeColor,
                Color.BLACK,
                game);
        volumeChevronDownButton = new Button((2 * game.partitionSize) + inputWidth + ((3 * inputWidth) / 10),
                game.partitionSize + (inputHeight / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_VOLUME_CHEVRON_DOWN,
                volumeColor,
                Color.BLACK,
                game);
        volumeChevronUpButton = new Button((2 * game.partitionSize) + inputWidth + ((7 * inputWidth) / 10),
                game.partitionSize + (inputHeight / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_VOLUME_CHEVRON_UP,
                volumeColor,
                Color.BLACK,
                game);
        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_OPTIONS_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(volumeButton);
        buttonList.add(volumeWavesButton);
        buttonList.add(volumeChevronDownButton);
        buttonList.add(volumeChevronUpButton);
        buttonList.add(backButton);
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

        for(Button button : buttonList) {
            button.draw();
        }

        for(Button button : buttonList) {
            button.drawTransitionCircles(this);
        }

        game.shapeRendererFilled.end();

        for(Button button : buttonList) {
            button.drawText();
        }

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        for(Button button : buttonList) {
            button.drawTransitionCircles(this);
        }

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

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        for(Button btn : buttonList) {
            btn.touchDown(touchPoint);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        for(Button btn : buttonList) {
            btn.touchUp(touchPoint);
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

    public void drawTitle() {
        game.draw.drawWrench(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
    }

}
