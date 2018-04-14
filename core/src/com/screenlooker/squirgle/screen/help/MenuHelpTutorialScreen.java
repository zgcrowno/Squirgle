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

import java.util.ArrayList;
import java.util.List;

public class MenuHelpTutorialScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int SQUIRGLE = 0;
    private final static int BATTLE = 1;
    private final static int TIME_ATTACK = 2;
    private final static int TIME_BATTLE = 3;
    private final static int TRANCE = 4;
    private final static int BACK = 5;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 5;
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

    private boolean squirgleTouched;
    private boolean battleTouched;
    private boolean timeAttackTouched;
    private boolean timeBattleTouched;
    private boolean tranceTouched;
    private boolean backTouched;

    private Button squirgleButton;
    private Button battleButton;
    private Button timeAttackButton;
    private Button timeBattleButton;
    private Button tranceButton;
    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public MenuHelpTutorialScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightType = (game.camera.viewportHeight - (game.partitionSize * NUM_MIDDLE_PARTITIONS_VERTICAL)) / NUM_MIDDLE_INPUTS_VERTICAL;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightType ? (inputHeightType / 2) : (inputWidth / 2);
        squirgleHeightOffset = inputShapeRadius / 4;

        game.setUpFontButton(MathUtils.round(inputShapeRadius / 2.75f));

        touchPoint = new Vector3();

        squirgleColor = ColorUtils.COLOR_SKY_BLUE;
        battleColor = ColorUtils.COLOR_BLUE;
        timeAttackColor = ColorUtils.COLOR_ORANGE;
        timeBattleColor = ColorUtils.COLOR_VERMILLION;
        tranceColor = ColorUtils.COLOR_BLUISH_GREEN;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

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
                new Vector2(game.camera.viewportWidth / 2, ((9 * game.camera.viewportHeight) / 10) - squirgleHeightOffset));
        squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                inputShapeRadius / 2,
                triangleColor,
                null,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 2) - (inputWidth / 4), ((7 * game.camera.viewportHeight) / 10) + (inputHeightType / 4) - squirgleHeightOffset));
        squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                inputShapeRadius / 2,
                triangleColor,
                null,
                (inputShapeRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 2) + (inputWidth / 4), ((7 * game.camera.viewportHeight) / 10) - (inputHeightType / 4)));

        squirgleButton = new Button((2 * game.partitionSize) + inputWidth,
                (5 * game.partitionSize) + (4 * inputHeightType),
                inputWidth,
                inputHeightType,
                Button.BUTTON_HELP_TUTORIAL_SQUIRGLE,
                squirgleColor,
                Color.BLACK,
                game);
        battleButton = new Button((2 * game.partitionSize) + inputWidth,
                (4 * game.partitionSize) + (3 * inputHeightType),
                inputWidth,
                inputHeightType,
                Button.BUTTON_HELP_TUTORIAL_BATTLE,
                battleColor,
                Color.BLACK,
                game);
        timeAttackButton = new Button((2 * game.partitionSize) + inputWidth,
                (3 * game.partitionSize) + (2 * inputHeightType),
                inputWidth,
                inputHeightType,
                Button.BUTTON_HELP_TUTORIAL_TIME_ATTACK,
                timeAttackColor,
                Color.BLACK,
                game);
        timeBattleButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightType,
                inputWidth,
                inputHeightType,
                Button.BUTTON_HELP_TUTORIAL_TIME_BATTLE,
                timeBattleColor,
                Color.BLACK,
                game);
        tranceButton = new Button((2 * game.partitionSize) + inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightType,
                Button.BUTTON_HELP_TUTORIAL_TRANCE,
                tranceColor,
                Color.BLACK,
                game);
        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_HELP_TUTORIAL_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(squirgleButton);
        buttonList.add(battleButton);
        buttonList.add(timeAttackButton);
        buttonList.add(timeBattleButton);
        buttonList.add(tranceButton);
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

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 2,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
        game.draw.drawTutorialSymbol(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 4,
                symbolRadius / 2,
                Color.WHITE,
                Color.BLACK);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
