package com.screenlooker.squirgle.screen.help;

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
import com.screenlooker.squirgle.screen.type.*;
import com.screenlooker.squirgle.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuHelpStatsScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int GENERAL = 0;
    private final static int SQUIRGLE = 1;
    private final static int BATTLE = 2;
    private final static int TIME_ATTACK = 3;
    private final static int TIME_BATTLE = 4;
    private final static int TRANCE = 5;
    private final static int BACK = 6;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 6;
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

    private Color generalColor;
    private Color squirgleColor;
    private Color battleColor;
    private Color timeAttackColor;
    private Color timeBattleColor;
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

    private boolean generalTouched;
    private boolean squirgleTouched;
    private boolean battleTouched;
    private boolean timeAttackTouched;
    private boolean timeBattleTouched;
    private boolean tranceTouched;
    private boolean backTouched;

    public MenuHelpStatsScreen(final Squirgle game) {
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

        generalColor = ColorUtils.randomColor();
        squirgleColor = ColorUtils.randomColor();
        battleColor = ColorUtils.randomColor();
        timeAttackColor = ColorUtils.randomColor();
        timeBattleColor = ColorUtils.randomColor();
        tranceColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        generalTouched = false;
        squirgleTouched = false;
        battleTouched = false;
        timeAttackTouched = false;
        timeBattleTouched = false;
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
                new Vector2(game.camera.viewportWidth / 2, ((9 * game.camera.viewportHeight) / 12) - squirgleHeightOffset));
        squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                inputShapeRadius / 2,
                triangleColor,
                null,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 2) - (inputWidth / 4), ((7 * game.camera.viewportHeight) / 12) + (inputHeightType / 4) - squirgleHeightOffset));
        squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                inputShapeRadius / 2,
                triangleColor,
                null,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 2) + (inputWidth / 4), ((7 * game.camera.viewportHeight) / 12) - (inputHeightType / 4)));
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

        game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false);
        game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);

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

        generalTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (6 * game.partitionSize) + (5 * inputHeightType)
                && touchPoint.y < (6 * game.partitionSize) + (6 * inputHeightType);
        squirgleTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (5 * game.partitionSize) + (4 * inputHeightType)
                && touchPoint.y < (5 * game.partitionSize) + (5 * inputHeightType);
        battleTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (4 * game.partitionSize) + (3 * inputHeightType)
                && touchPoint.y < (4 * game.partitionSize) + (4 * inputHeightType);
        timeAttackTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (3 * game.partitionSize) + (2 * inputHeightType)
                && touchPoint.y < (3 * game.partitionSize) + (3 * inputHeightType);
        timeBattleTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
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

        if(generalTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsGeneralScreen(game));
            dispose();
        } else if(squirgleTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsSquirgleScreen(game));
            dispose();
        } else if(battleTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsBattleScreen(game));
            dispose();
        } else if(timeAttackTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsTimeAttackScreen(game));
            dispose();
        } else if(timeBattleTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsTimeBattleScreen(game));
            dispose();
        } else if(tranceTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsTranceScreen(game));
            dispose();
        } else if(backTouched) {
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

    public void drawInputRectangles() {
        drawTitle();
        drawGeneralInput();
        drawSquirgleInput();
        drawBattleInput();
        drawTimeAttackInput();
        drawTimeBattleInput();
        drawTranceInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case GENERAL : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (6 * game.partitionSize) + (5 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case SQUIRGLE : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (5 * game.partitionSize) + (4 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case BATTLE : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (4 * game.partitionSize) + (3 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case TIME_ATTACK : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (3 * game.partitionSize) + (2 * inputHeightType),
                        inputWidth,
                        inputHeightType);
            }
            case TIME_BATTLE : {
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

    public void drawGeneralInput() {
        drawInputRectangle(GENERAL, generalColor);
        game.draw.drawSigma(game.camera.viewportWidth / 2,
                (6 * game.partitionSize) + (5 * inputHeightType) + (inputHeightType / 2),
                inputShapeRadius,
                Color.BLACK,
                generalColor);
    }

    public void drawSquirgleInput() {
        drawInputRectangle(SQUIRGLE, squirgleColor);
    }

    public void drawBattleInput() {
        drawInputRectangle(BATTLE, battleColor);
        game.shapeRendererFilled.setColor(Color.BLACK);
        game.shapeRendererFilled.rectLine((2 * game.partitionSize) + inputWidth,
                game.camera.viewportHeight - (3 * game.partitionSize) - (3 * inputHeightType),
                (2 * game.partitionSize) + (2 * inputWidth),
                game.camera.viewportHeight - (3 * game.partitionSize) - (2 * inputHeightType),
                game.partitionSize);
    }

    public void drawTimeAttackInput() {
        drawInputRectangle(TIME_ATTACK, timeAttackColor);
        game.draw.drawClock(game.camera.viewportWidth / 2,
                (5 * game.camera.viewportHeight) / 12,
                inputShapeRadius,
                Color.BLACK,
                timeAttackColor);
    }

    public void drawTimeBattleInput() {
        drawInputRectangle(TIME_BATTLE, timeBattleColor);
        game.shapeRendererFilled.setColor(Color.BLACK);
        game.shapeRendererFilled.rectLine((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightType,
                (2 * game.partitionSize) + (2 * inputWidth),
                (2 * game.partitionSize) + (2 * inputHeightType),
                game.partitionSize);
        game.draw.drawClock((game.camera.viewportWidth / 2) - (inputWidth / 4),
                ((3 * game.camera.viewportHeight) / 12) + (inputHeightType / 6),
                inputShapeRadius / 2,
                Color.BLACK,
                timeBattleColor);
        game.draw.drawClock((game.camera.viewportWidth / 2) + (inputWidth / 4),
                ((3 * game.camera.viewportHeight) / 12) - (inputHeightType / 6),
                inputShapeRadius / 2,
                Color.BLACK,
                timeBattleColor);
    }

    public void drawTranceInput() {
        drawInputRectangle(TRANCE, tranceColor);
        game.draw.drawTranceSymbol(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 12,
                inputShapeRadius,
                Color.BLACK,
                tranceColor);
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
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 2,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
        game.draw.drawModulo(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 4,
                symbolRadius / 2,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
