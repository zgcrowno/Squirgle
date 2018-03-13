package com.screenlooker.squirgle.screen.type;

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
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;

public class MenuTypeScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int SINGLE_PLAYER = 0;
    private final static int MULTIPLAYER_LOCAL = 1;
    private final static int MULTIPLAYER_ONLINE = 2;
    private final static int BACK = 3;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 3;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_MIDDLE_PARTITIONS_VERTICAL = NUM_MIDDLE_INPUTS_VERTICAL + 1;

    private float inputWidth;
    private float inputHeightType;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color singlePlayerColor;
    private Color multiplayerLocalColor;
    private Color multiplayerOnlineColor;
    private Color backColor;

    private boolean singlePlayerTouched;
    private boolean multiplayerLocalTouched;
    private boolean multiplayerOnlineTouched;
    private boolean backTouched;

    public MenuTypeScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightType = (game.camera.viewportHeight - (game.partitionSize * NUM_MIDDLE_PARTITIONS_VERTICAL)) / NUM_MIDDLE_INPUTS_VERTICAL;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightType ? (inputHeightType / 2) : (inputWidth / 2);

        touchPoint = new Vector3();

        singlePlayerColor = ColorUtils.randomColor();
        multiplayerLocalColor = ColorUtils.randomColor();
        multiplayerOnlineColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        singlePlayerTouched = false;
        multiplayerLocalTouched = false;
        multiplayerOnlineTouched = false;
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

        singlePlayerTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (3 * game.partitionSize) + (2 * inputHeightType)
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        multiplayerLocalTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (2 * game.partitionSize) + inputHeightType
                && touchPoint.y < (2 * game.partitionSize) + (2 * inputHeightType);
        multiplayerOnlineTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeightType;
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeightBack;

        if(singlePlayerTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuTypeSinglePlayerScreen(game));
            dispose();
        } else if(multiplayerLocalTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuTypeMultiplayerLocalScreen(game));
            dispose();
        } else if(multiplayerOnlineTouched) {
            //TODO: Set to correct screen once said screen is coded.
        } else if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
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
        drawTitle();
        drawSinglePlayerInput();
        drawMultiplayerLocalInput();
        drawMultiplayerOnlineInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case SINGLE_PLAYER : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (3 * game.partitionSize) + (2 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case MULTIPLAYER_LOCAL : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (2 * game.partitionSize) + inputHeightType,
                        inputWidth,
                        inputHeightType);
            }
            case MULTIPLAYER_ONLINE : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeightType);
            }
            case BACK : {
                game.shapeRendererFilled.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeightBack);
            }
        }
    }

    public void drawSinglePlayerInput() {
        drawInputRectangle(SINGLE_PLAYER, singlePlayerColor);
        game.draw.drawFace(game.camera.viewportWidth / 2,
                (5 * game.camera.viewportHeight) / 6,
                inputShapeRadius / 3,
                (inputShapeRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                singlePlayerColor);
    }

    public void drawMultiplayerLocalInput() {
        drawInputRectangle(MULTIPLAYER_LOCAL, multiplayerLocalColor);
        game.draw.drawFace((game.camera.viewportWidth / 2) - inputShapeRadius + (inputShapeRadius / 3),
                game.camera.viewportHeight / 2,
                inputShapeRadius / 3,
                (inputShapeRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                multiplayerLocalColor);
        game.draw.drawFace((game.camera.viewportWidth / 2) + inputShapeRadius - (inputShapeRadius / 3),
                game.camera.viewportHeight / 2,
                inputShapeRadius / 3,
                (inputShapeRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                multiplayerLocalColor);
        game.shapeRendererFilled.setColor(Color.BLACK);
        game.shapeRendererFilled.rectLine((game.camera.viewportWidth / 2) - inputShapeRadius,
                game.camera.viewportHeight / 2,
                (game.camera.viewportWidth / 2) + inputShapeRadius,
                game.camera.viewportHeight / 2,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR);
    }

    public void drawMultiplayerOnlineInput() {
        drawInputRectangle(MULTIPLAYER_ONLINE, multiplayerOnlineColor);
        game.draw.drawWiFiSymbol((game.camera.viewportWidth / 2) - (inputShapeRadius / (6 * NUM_MIDDLE_INPUTS_VERTICAL)),
                game.camera.viewportHeight / 6,
                inputShapeRadius / 3,
                (inputShapeRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                multiplayerOnlineColor);
        game.draw.drawFace((game.camera.viewportWidth / 2) - inputShapeRadius + (inputShapeRadius / 3),
                game.camera.viewportHeight / 6,
                inputShapeRadius / 3,
                (inputShapeRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                multiplayerOnlineColor);
        game.draw.drawFace((game.camera.viewportWidth / 2) + inputShapeRadius - (inputShapeRadius / 3),
                game.camera.viewportHeight / 6,
                inputShapeRadius / 3,
                (inputShapeRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                multiplayerOnlineColor);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawTitle() {
        game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE);
    }
}
