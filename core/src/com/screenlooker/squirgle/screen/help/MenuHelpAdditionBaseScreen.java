package com.screenlooker.squirgle.screen.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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

public class MenuHelpAdditionBaseScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BACK = 0;

    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;
    private int numInputsHorizontal;
    private int numPartitionsHorizontal;

    private float inputWidth;
    private float inputHeightBack;
    private float inputHeightTable;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color backColor;

    private boolean backTouched;

    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public MenuHelpAdditionBaseScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numMiddleInputsVertical = game.base + 1; //Adding one for plus
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;
        numInputsHorizontal = game.base + 3; //Adding one for plus, one for title, and one for back
        numPartitionsHorizontal = numInputsHorizontal + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * numPartitionsHorizontal)) / numInputsHorizontal;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;
        inputHeightTable = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;

        symbolRadius = inputWidth > inputHeightTable ? inputHeightTable / 2 : inputWidth / 2;

        game.setUpFontButton(MathUtils.round(symbolRadius / 2.75f));

        touchPoint = new Vector3();

        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        backTouched = false;

        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_HELP_ADDITION_BASE_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(backButton);

        this.veilColor = veilColor;
        veilOpacity = 1;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        drawAdditionTable();
        drawTitle();

        for(Button button : buttonList) {
            button.draw();
        }

        for(Button button : buttonList) {
            button.drawTransitionCircles(this);
        }

        game.draw.drawVeil(veilColor, veilOpacity);

        game.shapeRendererFilled.end();

        if(veilOpacity > 0) {
            veilOpacity -= 0.1;
        } else {
            if(!buttonTouched()) {
                for (Button button : buttonList) {
                    button.drawText();
                }
            }
        }

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
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

    public boolean buttonTouched() {
        for(Button btn : buttonList) {
            if(btn.touched) {
                return true;
            }
        }
        return false;
    }

    public void drawAdditionTable() {
        for(int i = 1; i <= game.base + 1; i++) {
            for(int j = 1; j <= game.base + 1; j++) {
                game.draw.rect(game.partitionSize + inputWidth + (i * game.partitionSize) + ((i - 1) * inputWidth),
                        game.camera.viewportHeight - ((j * game.partitionSize) + (j * inputHeightTable)),
                        inputWidth,
                        inputHeightTable,
                        Color.WHITE);
                game.shapeRendererFilled.setColor(Color.BLACK);
                if(i == 1 && j == 1) {
                    game.draw.drawPlus(game.partitionSize + inputWidth + game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight - (game.partitionSize + (inputHeightTable / 2)),
                            symbolRadius / 2,
                            (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                            Color.BLACK);
                } else {
                    game.draw.drawShape(false,
                            new Shape(i + j - 3 <= (game.base - 1) ? i + j - 3 : (i + j - 3) - game.base,
                            symbolRadius / 2,
                            Color.BLACK,
                            null,
                                    (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(game.partitionSize + inputWidth + (inputWidth / 2) + (i * game.partitionSize) + ((i - 1) * inputWidth),
                                    game.camera.viewportHeight - (game.partitionSize + (inputHeightTable / 2) + ((j - 1) * game.partitionSize) + ((j - 1) * inputHeightTable)))));
                }
            }
        }
    }

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
        game.draw.drawPlus(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE);
        game.draw.drawShape(false,
                new Shape(game.base - 1,
                        symbolRadius / 3,
                        Color.WHITE,
                        null,
                        (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(game.partitionSize + (inputWidth / 2),
                                game.camera.viewportHeight / 4)));
    }

}
