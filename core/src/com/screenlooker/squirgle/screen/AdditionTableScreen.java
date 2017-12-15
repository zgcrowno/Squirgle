package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

public class AdditionTableScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BACK = 0;

    private final static int NUM_LOWER_INPUTS_HORIZONTAL = 1;
    private final static int NUM_LOWER_PARTITIONS_HORIZONTAL = NUM_LOWER_INPUTS_HORIZONTAL + 1;

    private int numInputsVertical;
    private int numPartitionsVertical;
    private int numUpperInputsHorizontal;
    private int numUpperPartitionsHorizontal;

    private float inputWidthBack;
    private float inputWidthTable;
    private float inputHeight;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color backColor;

    private boolean backTouched;

    public AdditionTableScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numInputsVertical = game.base + 2; //Adding one for plus and one for back button
        numPartitionsVertical = numInputsVertical + 1;
        numUpperInputsHorizontal = game.base + 1;
        numUpperPartitionsHorizontal = numUpperInputsHorizontal + 1;

        inputWidthBack = game.camera.viewportWidth - (game.partitionSize * NUM_LOWER_PARTITIONS_HORIZONTAL);
        inputWidthTable = (game.camera.viewportWidth - (game.partitionSize * numUpperPartitionsHorizontal)) / numUpperInputsHorizontal;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * numPartitionsVertical)) / numInputsVertical;

        symbolRadius = inputWidthTable > inputHeight ? inputHeight / 2 : inputWidthTable / 2;

        touchPoint = new Vector3();

        backColor = ColorUtils.randomColor();

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

        drawAdditionTable();
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

        backTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new HelpScreen(game));
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

    public void drawAdditionTable() {
        for(int i = 1; i <= game.base + 1; i++) {
            for(int j = 1; j <= game.base + 1; j++) {
                game.shapeRendererFilled.setColor(Color.WHITE);
                game.shapeRendererFilled.rect((i * game.partitionSize) + ((i - 1) * inputWidthTable),
                        game.camera.viewportHeight - ((j * game.partitionSize) + (j * inputHeight)),
                        inputWidthTable,
                        inputHeight);
                game.shapeRendererFilled.setColor(Color.BLACK);
                if(i == 1 && j == 1) {
                    game.draw.drawPlus(game.partitionSize + (inputWidthTable / 2),
                            game.camera.viewportHeight - (game.partitionSize + (inputHeight / 2)),
                            symbolRadius / 2,
                            (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                            Color.BLACK,
                            game.shapeRendererFilled);
                } else {
                    game.draw.drawShape(new Shape(i + j - 3 <= (game.base - 1) ? i + j - 3 : (i + j - 3) - game.base,
                            symbolRadius / 2,
                            Color.BLACK,
                            null,
                                    (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(game.partitionSize + (inputWidthTable / 2) + ((i - 1) * game.partitionSize) + ((i - 1) * inputWidthTable),
                                    game.camera.viewportHeight - (game.partitionSize + (inputHeight / 2) + ((j - 1) * game.partitionSize) + ((j - 1) * inputHeight)))),
                            game.shapeRendererFilled);
                }
            }
        }
    }

    public void drawInputRectangles() {
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case BACK : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.partitionSize,
                        inputWidthBack,
                        inputHeight);
            }
        }
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth / 2,
                game.partitionSize + (inputHeight / 2),
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

}
