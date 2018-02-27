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

import java.util.ArrayList;
import java.util.List;

public class MenuHelpColorScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BACK = 0;

    private final static int NUM_INPUTS_HORIZONTAL = 9;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_COLORS = 6;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightTable;
    private float inputHeightBack;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;
    private Color backColor;

    private List<Shape> squirgleShapeList;

    private Shape squirglePrompt;

    private boolean backTouched;

    public MenuHelpColorScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numMiddleInputsVertical = NUM_COLORS + 1; //Adding one for plus
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightTable = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeightTable ? inputHeightTable / 2 : inputWidth / 2;

        touchPoint = new Vector3();

        squareColor = ColorUtils.randomTransitionColor();
        circleColor = ColorUtils.randomTransitionColor();
        triangleColor = ColorUtils.randomTransitionColor();
        while(circleColor.equals(squareColor)) {
            circleColor = ColorUtils.randomTransitionColor();
        }
        while(triangleColor.equals(circleColor) || triangleColor.equals(squareColor)) {
            triangleColor = ColorUtils.randomTransitionColor();
        }
        backColor = ColorUtils.randomColor();

        squirgleShapeList = new ArrayList<Shape>();
        squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));

        squirglePrompt = new Shape(Shape.TRIANGLE,
                symbolRadius,
                triangleColor,
                null,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2());

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

        transitionSquirgleColors();

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

        backTouched = touchPoint.x > game.camera.viewportWidth - game.partitionSize - inputWidth
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeightBack;

        if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpScreen(game));
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
        for(int i = 1; i <= NUM_COLORS + 1; i++) {
            for(int j = 1; j <= NUM_COLORS + 1; j++) {
                game.shapeRendererFilled.setColor(Color.WHITE);
                game.shapeRendererFilled.rect(inputWidth + game.partitionSize + (i * game.partitionSize) + ((i - 1) * inputWidth),
                        game.camera.viewportHeight - ((j * game.partitionSize) + (j * inputHeightTable)),
                        inputWidth,
                        inputHeightTable);
                game.shapeRendererFilled.setColor(Color.BLACK);
                if(i == 1 && j == 1) {
                    //Draw plus symbol
                    game.draw.drawPlus(inputWidth + (2 * game.partitionSize) + (inputWidth / 2),
                            game.camera.viewportHeight - (game.partitionSize + (inputHeightTable / 2)),
                            symbolRadius / 2,
                            (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                            Color.BLACK,
                            game.shapeRendererFilled);
                } else if(i == 1 || j == 1) {
                    //Draw color
                    int color = 0;
                    if(i == 1) {
                        color = j - 2;
                    } else {
                        //j == 1
                        color = i - 2;
                    }
                    game.draw.drawColor(inputWidth + (2 * game.partitionSize) + (inputWidth / 2) + ((i - 1) * game.partitionSize) + ((i - 1) * inputWidth),
                            game.camera.viewportHeight - (game.partitionSize + (inputHeightTable / 2) + ((j - 1) * game.partitionSize) + ((j - 1) * inputHeightTable)),
                            symbolRadius / 2,
                    color,
                    game.shapeRendererFilled);
                } else if(i == j) {
                    //Draw squirgles
                    squirglePrompt.setCoordinates(new Vector2(inputWidth + (2 * game.partitionSize) + (inputWidth / 2) + ((i - 1) * game.partitionSize) + ((i - 1) * inputWidth),
                            game.camera.viewportHeight - (game.partitionSize + (inputHeightTable / 2) + ((j - 1) * game.partitionSize) + ((j - 1) * inputHeightTable)) - (symbolRadius / 4)));
                    game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false, game.shapeRendererFilled);
                    game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false, game.shapeRendererFilled);
                } else {
                    //Draw dash
                    game.draw.drawDash(inputWidth + (2 * game.partitionSize) + (inputWidth / 2) + ((i - 1) * game.partitionSize) + ((i - 1) * inputWidth),
                            game.camera.viewportHeight - (game.partitionSize + (inputHeightTable / 2) + ((j - 1) * game.partitionSize) + ((j - 1) * inputHeightTable)),
                            symbolRadius / 4,
                            Color.BLACK,
                            game.shapeRendererFilled);
                }
            }
        }
    }

    public void drawInputRectangles() {
        drawTitle();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case BACK : {
                game.shapeRendererFilled.rect(game.camera.viewportWidth - game.partitionSize - inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeightBack);
            }
        }
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                inputHeightBack / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 2,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawColorWheel(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 4,
                symbolRadius / 2,
                Color.WHITE,
                game.shapeRendererFilled);
    }

}
