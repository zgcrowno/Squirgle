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

//TODO: Refactor all the music input behavior (create easier to read variables and such)
public class MenuHelpTutorialBattleScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;

    private int numberOfBaseInputs;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightBase;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color base4Color;
    private Color base5Color;
    private Color base6Color;
    private Color base7Color;
    private Color base8Color;
    private Color base9Color;
    private Color backColor;
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;

    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;

    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;

    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public MenuHelpTutorialBattleScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numberOfBaseInputs = game.maxBase - game.minBase + 1;

        numMiddleInputsVertical = numberOfBaseInputs;
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightBase = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightBase ? (inputHeightBase / 2) : (inputWidth / 2);

        game.setUpFontButton(MathUtils.round(inputShapeRadius / 2.75f));

        touchPoint = new Vector3();

        base4Color = ColorUtils.COLOR_BLUISH_GREEN;
        base5Color = ColorUtils.COLOR_VERMILLION;
        base6Color = ColorUtils.COLOR_ORANGE;
        base7Color = ColorUtils.COLOR_BLUE;
        base8Color = ColorUtils.COLOR_SKY_BLUE;
        base9Color = ColorUtils.COLOR_REDDISH_PURPLE;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        squareColor = ColorUtils.randomTransitionColor();
        circleColor = ColorUtils.randomTransitionColor();
        triangleColor = ColorUtils.randomTransitionColor();
        while(circleColor.equals(squareColor)) {
            circleColor = ColorUtils.randomTransitionColor();
        }
        while(triangleColor.equals(circleColor) || triangleColor.equals(squareColor)) {
            triangleColor = ColorUtils.randomTransitionColor();
        }

        squirgleShapeListBattleOne = new ArrayList<Shape>();
        squirgleShapeListBattleOne.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeListBattleOne.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));

        squirgleShapeListBattleTwo = new ArrayList<Shape>();
        squirgleShapeListBattleTwo.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeListBattleTwo.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));

        squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                (symbolRadius / 2) / 3,
                triangleColor,
                null,
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 6) - (symbolRadius / 6), (game.camera.viewportHeight / 6) + (symbolRadius / 6)));
        squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                (symbolRadius / 2) / 3,
                triangleColor,
                null,
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 6) + (symbolRadius / 6), (game.camera.viewportHeight / 6) - (symbolRadius / 6)));

        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_HELP_TUTORIAL_BATTLE_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(backButton);

        for(int i = 0; i <= game.maxBase - game.minBase; i++) {
            float x = 2 * game.partitionSize + inputWidth;
            float y = game.partitionSize + (i * (game.partitionSize + inputHeightBase));
            int buttonType = Button.BUTTON_HELP_TUTORIAL_BATTLE_SQUARE + i;
            Color color = new Color();
            if(i == 0) {
                color = base4Color;
            } else if(i == 1) {
                color = base5Color;
            } else if(i ==2) {
                color = base6Color;
            } else if(i == 3) {
                color = base7Color;
            } else if(i == 4) {
                color = base8Color;
            } else if(i == 5) {
                color = base9Color;
            }
            buttonList.add(new Button(x,
                    y,
                    inputWidth,
                    inputHeightBase,
                    buttonType,
                    color,
                    Color.BLACK,
                    game));
        }

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

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
        }

        transitionSquirgleColors();

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

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);

        game.draw.drawTutorialSymbol(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius / 3,
                Color.WHITE,
                Color.BLACK);

        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererFilled.rectLine((game.camera.viewportWidth / 6) - (symbolRadius / 3),
                (game.camera.viewportHeight / 6) - (symbolRadius / 3),
                (game.camera.viewportWidth / 6) + (symbolRadius / 3),
                (game.camera.viewportHeight / 6) + (symbolRadius / 3),
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
        game.shapeRendererFilled.circle((game.camera.viewportWidth / 6) - (symbolRadius / 3), (game.camera.viewportHeight / 6) - (symbolRadius / 3), (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
        game.shapeRendererFilled.circle((game.camera.viewportWidth / 6) + (symbolRadius / 3), (game.camera.viewportHeight / 6) + (symbolRadius / 3), (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);

        game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
        game.draw.orientAndDrawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
        game.draw.orientAndDrawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePromptBattleOne);
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(0));
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(1));
    }
}
