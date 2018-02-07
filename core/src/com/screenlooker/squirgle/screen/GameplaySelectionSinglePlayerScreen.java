package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class GameplaySelectionSinglePlayerScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int SQUIRGLE = 0;
    private final static int BATTLE = 1;
    private final static int TIME_ATTACK = 2;
    private final static int TRANCE = 3;
    private final static int BACK = 4;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 4;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_MIDDLE_PARTITIONS_VERTICAL = NUM_MIDDLE_INPUTS_VERTICAL + 1;

    private float inputWidth;
    private float inputHeightType;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;
    private float squirgleHeightOffset;

    private Vector3 touchPoint;

    private Color squirgleColor;
    private Color battleColor;
    private Color timeAttackColor;
    private Color tranceColor;
    private Color backColor;
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;

    private List<Shape> squirgleShapeList;
    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;

    private Shape squirglePrompt;
    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;

    private boolean squirgleTouched;
    private boolean battleTouched;
    private boolean timeAttackTouched;
    private boolean tranceTouched;
    private boolean backTouched;

    public GameplaySelectionSinglePlayerScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightType = (game.camera.viewportHeight - (game.partitionSize * NUM_MIDDLE_PARTITIONS_VERTICAL)) / NUM_MIDDLE_INPUTS_VERTICAL;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightType ? (inputHeightType / 2) : (inputWidth / 2);
        squirgleHeightOffset = inputShapeRadius / 4;

        touchPoint = new Vector3();

        squirgleColor = ColorUtils.randomColor();
        battleColor = ColorUtils.randomColor();
        timeAttackColor = ColorUtils.randomColor();
        tranceColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        squirgleTouched = false;
        battleTouched = false;
        timeAttackTouched = false;
        tranceTouched = false;
        backTouched = false;

        squareColor = ColorUtils.randomTransitionColor();
        circleColor = ColorUtils.randomTransitionColor();
        triangleColor = ColorUtils.randomTransitionColor();
        while(circleColor.equals(squareColor)) {
            circleColor = ColorUtils.randomTransitionColor();
        }
        while(triangleColor.equals(circleColor) || triangleColor.equals(squareColor)) {
            triangleColor = ColorUtils.randomTransitionColor();
        }

        squirgleShapeList = new ArrayList<Shape>();
        squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        squirgleShapeListBattleOne = new ArrayList<Shape>();
        squirgleShapeListBattleOne.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeListBattleOne.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        squirgleShapeListBattleTwo = new ArrayList<Shape>();
        squirgleShapeListBattleTwo.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeListBattleTwo.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));

        squirglePrompt = new Shape(Shape.TRIANGLE,
                inputShapeRadius,
                triangleColor,
                null,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2, ((7 * game.camera.viewportHeight) / 8) - squirgleHeightOffset));
        squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                inputShapeRadius / 2,
                triangleColor,
                null,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 2) - (inputWidth / 4), ((5 * game.camera.viewportHeight) / 8) + (inputHeightType / 4) - squirgleHeightOffset));
        squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                inputShapeRadius / 2,
                triangleColor,
                null,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 2) + (inputWidth / 4), ((5 * game.camera.viewportHeight) / 8) - (inputHeightType / 4)));
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

        game.draw.drawPrompt(squirglePrompt, squirgleShapeList, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(squirgleShapeList, squirglePrompt, false, game.shapeRendererFilled);
        game.draw.drawPrompt(squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(squirgleShapeListBattleOne, squirglePromptBattleOne, false, game.shapeRendererFilled);
        game.draw.drawPrompt(squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(squirgleShapeListBattleTwo, squirglePromptBattleTwo, false, game.shapeRendererFilled);

        game.shapeRendererFilled.end();

        transitionSquirgleColors();
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

        squirgleTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (4 * game.partitionSize) + (3 * inputHeightType)
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        battleTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (3 * game.partitionSize) + (2 * inputHeightType)
                && touchPoint.y < (3 * game.partitionSize) + (3 * inputHeightType);
        timeAttackTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (2 * game.partitionSize) + inputHeightType
                && touchPoint.y < (2 * game.partitionSize) + (2 * inputHeightType);
        tranceTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeightType;
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeightBack;

        if(squirgleTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new BaseSelectScreen(game));
            dispose();
        } else if(battleTouched) {
            //TODO: Set screen and whatnot once screen is coded
        } else if(timeAttackTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new TimeAttackBaseSelectScreen(game));
            dispose();
        } else if(tranceTouched) {
            //TODO: Set screen and whatnot once screen is coded
        } else if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new GameplaySelectionScreen(game));
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
        drawSquirgleInput();
        drawBattleInput();
        drawTimeAttackInput();
        drawTranceInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case SQUIRGLE : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (4 * game.partitionSize) + (3 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case BATTLE : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (3 * game.partitionSize) + (2 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case TIME_ATTACK : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (2 * game.partitionSize) + inputHeightType,
                        inputWidth,
                        inputHeightType);
            }
            case TRANCE : {
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

    public void drawSquirgleInput() {
        drawInputRectangle(SQUIRGLE, squirgleColor);
    }

    public void drawBattleInput() {
        drawInputRectangle(BATTLE, battleColor);
        game.shapeRendererFilled.setColor(Color.BLACK);
        game.shapeRendererFilled.rectLine((2 * game.partitionSize) + inputWidth,
                game.camera.viewportHeight - (2 * game.partitionSize) - (2 * inputHeightType),
                (2 * game.partitionSize) + (2 * inputWidth),
                game.camera.viewportHeight - (2 * game.partitionSize) - inputHeightType,
                game.partitionSize);
    }

    public void drawTimeAttackInput() {
        drawInputRectangle(TIME_ATTACK, timeAttackColor);
        game.draw.drawClock(game.camera.viewportWidth / 2,
                (3 * game.camera.viewportHeight) / 8,
                inputShapeRadius,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawTranceInput() {
        drawInputRectangle(TRANCE, tranceColor);
        game.draw.drawTranceSymbol(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 8,
                inputShapeRadius,
                Color.BLACK,
                tranceColor,
                game.shapeRendererFilled);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawTitle() {
        game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 2,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                game.shapeRendererFilled);
        game.draw.drawFace(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 4,
                symbolRadius / 2,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}