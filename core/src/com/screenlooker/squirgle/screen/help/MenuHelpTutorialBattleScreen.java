package com.screenlooker.squirgle.screen.help;

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
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

//TODO: Refactor all the music input behavior (create easier to read variables and such)
public class MenuHelpTutorialBattleScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BASE_4 = 0;
    private final static int BASE_5 = 1;
    private final static int BASE_6 = 2;
    private final static int BASE_7 = 3;
    private final static int BASE_8 = 4;
    private final static int BASE_9 = 5;
    private final static int BACK = 6;
    private final static int MUSIC = 7;
    private final static int MUSIC_TYPE = 8;
    private final static int MUSIC_NAME = 9;
    private final static int DIFFICULTY = 10;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_DIFFICULTY_INPUT_ELEMENTS = 4;

    private final static float FONT_DIFFICULTY_SIZE_DIVISOR = 35f;

    private final static float FONT_TRACK_NAME_DIVISOR = 6.5f;
    private final static float FONT_TRACK_TYPE_DIVISOR = 2f;

    private int numberOfBaseInputs;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightBase;
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
    private Color musicColor;
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;
    private Color difficultyColor;

    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;

    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;

    private boolean base4Touched;
    private boolean base5Touched;
    private boolean base6Touched;
    private boolean base7Touched;
    private boolean base8Touched;
    private boolean base9Touched;
    private boolean backTouched;
    private boolean musicTypeFullTouched;
    private boolean musicTypeSplitTouched;
    private boolean musicNamePointillismTouched;
    private boolean musicNameLineageTouched;
    private boolean musicNameTriTheWaltzTouched;
    private boolean musicNameSquaredOffTouched;
    private boolean musicNamePentUpTouched;
    private boolean musicNameHexidecibelTouched;
    private boolean musicNameInterseptorTouched;
    private boolean musicNameRoctopusTouched;
    private boolean musicNameNonplussedTouched;
    private boolean difficultyDownChevronTouched;
    private boolean difficultyUpChevronTouched;

    private Button backButton;

    private List<Button> buttonList;

    public MenuHelpTutorialBattleScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        //TODO: Make sure this is being determined by inputShapeRadius instead of viewport width in ALL screens
        game.setUpFontDifficulty(MathUtils.round(game.camera.viewportWidth / FONT_DIFFICULTY_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        numberOfBaseInputs = game.maxBase - game.minBase + 1;

        numMiddleInputsVertical = numberOfBaseInputs;
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightBase = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;
        squirgleHeightOffset = symbolRadius / 4;

        inputShapeRadius = inputWidth > inputHeightBase ? (inputHeightBase / 2) : (inputWidth / 2);

        game.setUpFontTrackName(MathUtils.round(inputShapeRadius / FONT_TRACK_NAME_DIVISOR));
        game.setUpFontTrackType(MathUtils.round(inputShapeRadius / FONT_TRACK_TYPE_DIVISOR));

        touchPoint = new Vector3();

        base4Color = ColorUtils.randomColor();
        base5Color = ColorUtils.randomColor();
        base6Color = ColorUtils.randomColor();
        base7Color = ColorUtils.randomColor();
        base8Color = ColorUtils.randomColor();
        base9Color = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();
        musicColor = ColorUtils.randomColor();
        difficultyColor = ColorUtils.randomColor();

        base4Touched = false;
        base5Touched = false;
        base6Touched = false;
        base7Touched = false;
        base8Touched = false;
        base9Touched = false;
        backTouched = false;
        difficultyDownChevronTouched = false;
        difficultyUpChevronTouched = false;

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
            buttonList.add(new Button(x,
                    y,
                    inputWidth,
                    inputHeightBase,
                    buttonType,
                    ColorUtils.randomColor(),
                    Color.BLACK,
                    game));
        }
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

        drawTitle();

        for(Button button : buttonList) {
            button.draw();
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
        game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePromptBattleOne);
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(0));
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(1));
    }
}
