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

public class PreGameScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;

    private final static float FONT_TRACK_NAME_DIVISOR = 6.5f;
    private final static float FONT_TRACK_TYPE_DIVISOR = 2f;
    private final static float FONT_OPTIONS_SIZE_DIVISOR = 7f;

    private int gameplayType;
    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightMiddle;
    private float inputHeightBack;

    private float symbolRadius;
    private float squirgleHeightOffset;

    private float inputShapeRadius;

    private boolean difficultyScreen;
    private boolean timeScreen;

    private Vector3 touchPoint;

    private Color backColor;
    private Color musicColor;
    private Color timeColor;
    private Color difficultyColor;
    private Color playColor;
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;

    private List<Shape> squirgleShapeList;
    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;

    private Shape squirglePrompt;
    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;

    private Button musicButton;
    private Button musicPointillismButton;
    private Button musicLineageButton;
    private Button musicTriTheWaltzButton;
    private Button musicSquaredOffButton;
    private Button musicPentUpButton;
    private Button musicHexidecibelButton;
    private Button musicInterseptorButton;
    private Button musicRoctopusButton;
    private Button musicNonplussedButton;
    private Button difficultyButton;
    private Button difficultyDialButton;
    private Button difficultyChevronDownButton;
    private Button difficultyChevronUpButton;
    private Button timeButton;
    private Button timeClockButton;
    private Button timeChevronDownButton;
    private Button timeChevronUpButton;
    private Button backButton;
    private Button playButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public PreGameScreen(final Squirgle game, Color veilColor, int gameplayType) {
        this.game = game;
        game.gameplayType = gameplayType;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        this.gameplayType = gameplayType;
        numMiddleInputsVertical = 0;
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numMiddleInputsVertical = 2;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            numMiddleInputsVertical = 3;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numMiddleInputsVertical = 3;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            numMiddleInputsVertical = 4;
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            numMiddleInputsVertical = 2;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numMiddleInputsVertical = 2;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            numMiddleInputsVertical = 3;
        }
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightMiddle = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;
        squirgleHeightOffset = (symbolRadius / 4) / 4;

        inputShapeRadius = inputWidth > inputHeightMiddle ? (inputHeightMiddle / 2) : (inputWidth / 2);

        difficultyScreen = gameplayType == Squirgle.GAMEPLAY_BATTLE
                || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE;
        timeScreen = gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK
                || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE
                || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL;

        game.setUpFontButton(MathUtils.round(inputShapeRadius / 2.75f));
        game.setUpFontOptions(MathUtils.round(inputWidth / FONT_OPTIONS_SIZE_DIVISOR));
        game.setUpFontNumPlayers(MathUtils.round(symbolRadius / 3));

        touchPoint = new Vector3();

        backColor = ColorUtils.COLOR_REDDISH_PURPLE;
        musicColor = ColorUtils.COLOR_BLUE;
        timeColor = ColorUtils.COLOR_VERMILLION;
        difficultyColor = ColorUtils.COLOR_ORANGE;
        playColor = ColorUtils.COLOR_BLUISH_GREEN;

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
                symbolRadius / 4,
                triangleColor,
                null,
                (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.partitionSize + (inputWidth / 2),
                        ((2 * game.camera.viewportHeight) / 5) - squirgleHeightOffset));
        squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                (symbolRadius / 2) / 4,
                triangleColor,
                null,
                ((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 6) - (symbolRadius / 6),
                        ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 6)));
        squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                (symbolRadius / 2) / 4,
                triangleColor,
                null,
                ((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                        ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 6)));

        playButton = new Button((2 * game.partitionSize) + inputWidth,
                game.camera.viewportHeight - game.partitionSize - inputHeightMiddle,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_PLAY,
                playColor,
                Color.BLACK,
                game);
        musicButton = new Button((2 * game.partitionSize) + inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_MUSIC,
                musicColor,
                Color.BLACK,
                game);

        game.setUpFontTrackType(MathUtils.round(musicButton.symbolRadius / FONT_TRACK_TYPE_DIVISOR));
        game.setUpFontTrackName(MathUtils.round(musicButton.symbolRadius / FONT_TRACK_NAME_DIVISOR));

        musicPointillismButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_POINTILLISM,
                musicColor,
                Color.BLACK,
                game);
        musicLineageButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (2 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_LINEAGE,
                musicColor,
                Color.BLACK,
                game);
        musicTriTheWaltzButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (3 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_TRI_THE_WALTZ,
                musicColor,
                Color.BLACK,
                game);
        musicSquaredOffButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (4 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_SQUARED_OFF,
                musicColor,
                Color.BLACK,
                game);
        musicPentUpButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (5 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_PENT_UP,
                musicColor,
                Color.BLACK,
                game);
        musicHexidecibelButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (6 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_HEXIDECIBEL,
                musicColor,
                Color.BLACK,
                game);
        musicInterseptorButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (7 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_INTERSEPTOR,
                musicColor,
                Color.BLACK,
                game);
        musicRoctopusButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (8 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_ROCTOPUS,
                musicColor,
                Color.BLACK,
                game);
        musicNonplussedButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2) - (9 * (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (((musicButton.symbolY + musicButton.symbolRadius - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2)) - (musicButton.symbolY - ((3 * musicButton.symbolRadius) / 4))) / 9),
                Button.BUTTON_MUSIC_NONPLUSSED,
                musicColor,
                Color.BLACK,
                game);
        difficultyButton = new Button((2 * game.partitionSize) + inputWidth,
                gameplayType == Squirgle.GAMEPLAY_BATTLE ? (2 * game.partitionSize) + inputHeightMiddle : (3 * game.partitionSize) + (2 * inputHeightMiddle),
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_DIFFICULTY,
                difficultyColor,
                Color.BLACK,
                game);
        difficultyDialButton = new Button(difficultyButton.symbolX - (((2 * difficultyButton.symbolRadius) / 3) / 2),
                difficultyButton.symbolY + difficultyButton.symbolRadius - ((2 * difficultyButton.symbolRadius) / 3),
                (2 * difficultyButton.symbolRadius) / 3,
                (2 * difficultyButton.symbolRadius) / 3,
                Button.BUTTON_DIFFICULTY_DIAL,
                difficultyColor,
                Color.BLACK,
                game);
        difficultyChevronDownButton = new Button(difficultyButton.x,
                difficultyButton.symbolY + difficultyButton.symbolRadius - ((5 * difficultyButton.symbolRadius) / 3),
                (2 * difficultyButton.symbolRadius) / 3,
                (2 * difficultyButton.symbolRadius) / 3,
                Button.BUTTON_DIFFICULTY_CHEVRON_DOWN,
                difficultyColor,
                Color.BLACK,
                game);
        difficultyChevronUpButton = new Button(difficultyButton.x + difficultyButton.width - ((2 * difficultyButton.symbolRadius) / 3),
                difficultyButton.symbolY + difficultyButton.symbolRadius - ((5 * difficultyButton.symbolRadius) / 3),
                (2 * difficultyButton.symbolRadius) / 3,
                (2 * difficultyButton.symbolRadius) / 3,
                Button.BUTTON_DIFFICULTY_CHEVRON_UP,
                difficultyColor,
                Color.BLACK,
                game);
        timeButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightMiddle,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_TIME,
                timeColor,
                Color.BLACK,
                game);
        timeClockButton = new Button(timeButton.symbolX - (((2 * timeButton.symbolRadius) / 3) / 2),
                timeButton.symbolY + timeButton.symbolRadius - ((2 * timeButton.symbolRadius) / 3),
                (2 * timeButton.symbolRadius) / 3,
                (2 * timeButton.symbolRadius) / 3,
                Button.BUTTON_TIME_CLOCK,
                timeColor,
                Color.BLACK,
                game);
        timeChevronDownButton = new Button(timeButton.x,
                timeButton.symbolY + timeButton.symbolRadius - ((5 * timeButton.symbolRadius) / 3),
                (2 * timeButton.symbolRadius) / 3,
                (2 * timeButton.symbolRadius) / 3,
                Button.BUTTON_TIME_CHEVRON_DOWN,
                timeColor,
                Color.BLACK,
                game);
        timeChevronUpButton = new Button(timeButton.x + timeButton.width - ((2 * timeButton.symbolRadius) / 3),
                timeButton.symbolY + timeButton.symbolRadius - ((5 * timeButton.symbolRadius) / 3),
                (2 * timeButton.symbolRadius) / 3,
                (2 * timeButton.symbolRadius) / 3,
                Button.BUTTON_TIME_CHEVRON_UP,
                timeColor,
                Color.BLACK,
                game);
        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_PRE_GAME_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(playButton);
        buttonList.add(musicButton);
        buttonList.add(musicPointillismButton);
        buttonList.add(musicLineageButton);
        buttonList.add(musicTriTheWaltzButton);
        buttonList.add(musicSquaredOffButton);
        buttonList.add(musicPentUpButton);
        buttonList.add(musicHexidecibelButton);
        buttonList.add(musicInterseptorButton);
        buttonList.add(musicRoctopusButton);
        buttonList.add(musicNonplussedButton);
        if(difficultyScreen) {
            buttonList.add(difficultyButton);
            buttonList.add(difficultyDialButton);
            buttonList.add(difficultyChevronDownButton);
            buttonList.add(difficultyChevronUpButton);
        }
        if(timeScreen) {
            buttonList.add(timeButton);
            buttonList.add(timeClockButton);
            buttonList.add(timeChevronDownButton);
            buttonList.add(timeChevronUpButton);
        }
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
                    (4 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeList, squirglePrompt, false);

            game.draw.drawShape(false, new Shape(game.base - 1,
                    symbolRadius / 4,
                    Color.WHITE,
                    null,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight / 5)));
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (4 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    ((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
            game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);

            game.draw.drawShape(false, new Shape(game.base - 1,
                    symbolRadius / 4,
                    Color.WHITE,
                    null,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight / 5)));
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (4 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.draw.drawClock(game.partitionSize + (inputWidth / 2),
                    (2 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    Color.WHITE,
                    Color.BLACK);

            game.draw.drawShape(false, new Shape(game.base - 1,
                    symbolRadius / 4,
                    Color.WHITE,
                    null,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight / 5)));
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (4 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    ((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawClock(game.partitionSize + (inputWidth / 2) - (symbolRadius / 6),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 8),
                    (symbolRadius / 2) / 4,
                    Color.WHITE,
                    Color.BLACK);
            game.draw.drawClock(game.partitionSize + (inputWidth / 2) + (symbolRadius / 6),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 8),
                    (symbolRadius / 2) / 4,
                    Color.WHITE,
                    Color.BLACK);

            game.draw.drawShape(false, new Shape(game.base - 1,
                    symbolRadius / 4,
                    Color.WHITE,
                    null,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight / 5)));
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            game.draw.drawPlayButton(game.camera.viewportWidth / 6,
                    (3 * game.camera.viewportHeight) / 4,
                    symbolRadius / 3,
                    (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.draw.drawTranceSymbol(game.camera.viewportWidth / 6,
                    game.camera.viewportHeight / 4,
                    symbolRadius / 3,
                    Color.WHITE,
                    Color.BLACK);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (4 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    ((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);

            game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
            game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
            game.draw.orientAndDrawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);

            game.draw.drawShape(false, new Shape(game.base - 1,
                    symbolRadius / 4,
                    Color.WHITE,
                    null,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight / 5)));
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                    (4 * game.camera.viewportHeight) / 5,
                    symbolRadius / 4,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    Color.WHITE);

            game.shapeRendererFilled.setColor(Color.WHITE);
            game.shapeRendererFilled.rectLine(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    ((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) - (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.shapeRendererFilled.circle(game.partitionSize + (inputWidth / 2) + (symbolRadius / 4),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 4),
                    (((symbolRadius / 2) / 4) / Draw.LINE_WIDTH_DIVISOR) / 2);
            game.draw.drawClock(game.partitionSize + (inputWidth / 2) - (symbolRadius / 6),
                    ((2 * game.camera.viewportHeight) / 5) + (symbolRadius / 8),
                    (symbolRadius / 2) / 4,
                    Color.WHITE,
                    Color.BLACK);
            game.draw.drawClock(game.partitionSize + (inputWidth / 2) + (symbolRadius / 6),
                    ((2 * game.camera.viewportHeight) / 5) - (symbolRadius / 8),
                    (symbolRadius / 2) / 4,
                    Color.WHITE,
                    Color.BLACK);

            game.draw.drawShape(false, new Shape(game.base - 1,
                    symbolRadius / 4,
                    Color.WHITE,
                    null,
                    (symbolRadius / 4) / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.partitionSize + (inputWidth / 2),
                            game.camera.viewportHeight / 5)));
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
                    (3 * game.camera.viewportHeight) / 5,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 5,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 5,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.SINGLE_PLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 5,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
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
                    (3 * game.camera.viewportHeight) / 5,
                    0,
                    1);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            FontUtils.printText(game.batch,
                    game.fontNumPlayers,
                    game.layout,
                    Color.WHITE,
                    Button.MULTIPLAYER_SYMBOL_STRING,
                    game.partitionSize + (inputWidth / 2),
                    (3 * game.camera.viewportHeight) / 5,
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
