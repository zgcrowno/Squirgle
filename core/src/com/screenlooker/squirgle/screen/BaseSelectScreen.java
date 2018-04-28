package com.screenlooker.squirgle.screen;

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
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseSelectScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    public final static int NUM_TIME_INPUT_ELEMENTS = 4;
    public final static int NUM_DIFFICULTY_INPUT_ELEMENTS = 4;

    private final static float FONT_DIFFICULTY_SIZE_DIVISOR = 35f;
    private final static float FONT_TRACK_NAME_DIVISOR = 6.5f;
    private final static float FONT_TRACK_TYPE_DIVISOR = 2f;
    private final static float FONT_TIME_DIVISOR = 1.2f;
    private final static float FONT_OPTIONS_SIZE_DIVISOR = 15f;

    private int gameplayType;
    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightMiddle;
    private float inputHeightBack;

    private float symbolRadius;
    private float squirgleHeightOffset;

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

    private List<Shape> squirgleShapeList;
    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;

    private Shape squirglePrompt;
    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;

    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public BaseSelectScreen(final Squirgle game, Color veilColor, int gameplayType) {
        this.game = game;
        game.gameplayType = gameplayType;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        this.gameplayType = gameplayType;
        numMiddleInputsVertical = game.maxBase - game.minBase + 1;
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightMiddle = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;
        squirgleHeightOffset = (symbolRadius / 3) / 4;

        inputShapeRadius = inputWidth > inputHeightMiddle ? (inputHeightMiddle / 2) : (inputWidth / 2);

        game.setUpFontButton(MathUtils.round(inputShapeRadius / 2.75f));
        game.setUpFontOptions(MathUtils.round(inputWidth / FONT_OPTIONS_SIZE_DIVISOR));
        game.setUpFontNumPlayers(MathUtils.round(symbolRadius / 3));

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
                symbolRadius / 3,
                triangleColor,
                null,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.partitionSize + (inputWidth / 2),
                        (game.camera.viewportHeight / 4) - squirgleHeightOffset));
        squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                (symbolRadius / 2) / 3,
                triangleColor,
                null,
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 6) - (symbolRadius / 6),
                        (game.camera.viewportHeight / 4) + (symbolRadius / 6)));
        squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                (symbolRadius / 2) / 3,
                triangleColor,
                null,
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                        (game.camera.viewportHeight / 4) - (symbolRadius / 6)));

        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_BASE_SELECT_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(backButton);

        for(int i = 0; i <= game.maxBase - game.minBase; i++) {
            float x = 2 * game.partitionSize + inputWidth;
            float y = game.partitionSize + (i * (game.partitionSize + inputHeightMiddle));
            int buttonType = Button.BUTTON_SQUARE + i;
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
                    inputHeightMiddle,
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
                drawTitleText();
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
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeList, squirglePrompt, false);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
            game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);
            game.draw.drawClock(game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 4,
                    symbolRadius / 3,
                    Color.WHITE,
                    Color.BLACK);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawClock((game.camera.viewportWidth / 6) - (symbolRadius / 6),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 6),
                    (symbolRadius / 2) / 3,
                    Color.WHITE,
                    Color.BLACK);
            game.draw.drawClock((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 6),
                    (symbolRadius / 2) / 3,
                    Color.WHITE,
                    Color.BLACK);
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            game.draw.drawPlayButton(game.camera.viewportWidth / 6,
                    (5 * game.camera.viewportHeight) / 6,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.draw.drawTranceSymbol(game.camera.viewportWidth / 6,
                    game.camera.viewportHeight / 6,
                    symbolRadius / 3,
                    Color.WHITE,
                    Color.BLACK);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
            game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 3),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 3),
                    (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawClock((game.camera.viewportWidth / 6) - (symbolRadius / 6),
                    (game.camera.viewportHeight / 4) + (symbolRadius / 6),
                    (symbolRadius / 2) / 3,
                    Color.WHITE,
                    Color.BLACK);
            game.draw.drawClock((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                    (game.camera.viewportHeight / 4) - (symbolRadius / 6),
                    (symbolRadius / 2) / 3,
                    Color.WHITE,
                    Color.BLACK);
        }
    }

    public void drawTitleText() {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 2,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 2,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 2,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 2,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.MULTIPLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 2,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.MULTIPLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    game.camera.viewportHeight / 2,
                    0,
                    1);
        }
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
