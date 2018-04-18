package com.screenlooker.squirgle.screen.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.GameplayScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

//TODO: Refactor all the music input behavior (create easier to read variables and such)
public class MenuTypeSinglePlayerTimeBattleScreen implements Screen, InputProcessor {

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
    private final static int TIME = 10;
    private final static int DIFFICULTY = 11;

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
    private Color musicColor;
    private Color timeColor;
    private Color difficultyColor;

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
    private boolean timeDownChevronTouched;
    private boolean timeUpChevronTouched;
    private boolean difficultyDownChevronTouched;
    private boolean difficultyUpChevronTouched;

    private Button musicButton;
    private Button musicFullButton;
    private Button musicSplitButton;
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

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public MenuTypeSinglePlayerTimeBattleScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numberOfBaseInputs = game.maxBase - game.minBase + 1;

        numMiddleInputsVertical = numberOfBaseInputs + 3; //Adding 3 to account for music, difficulty and time inputs
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightBase = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightBase ? (inputHeightBase / 2) : (inputWidth / 2);

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
        musicColor = ColorUtils.COLOR_BLUE;
        timeColor = ColorUtils.COLOR_VERMILLION;
        difficultyColor = ColorUtils.COLOR_ORANGE;

        base4Touched = false;
        base5Touched = false;
        base6Touched = false;
        base7Touched = false;
        base8Touched = false;
        base9Touched = false;
        backTouched = false;
        difficultyDownChevronTouched = false;
        difficultyUpChevronTouched = false;

        musicButton = new Button((2 * game.partitionSize) + inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBase,
                Button.BUTTON_MUSIC,
                musicColor,
                Color.BLACK,
                game);

        game.setUpFontTrackType(MathUtils.round(musicButton.symbolRadius / FONT_TRACK_TYPE_DIVISOR));
        game.setUpFontTrackName(MathUtils.round(musicButton.symbolRadius / FONT_TRACK_NAME_DIVISOR));

        musicFullButton = new Button((3 * game.partitionSize) + inputWidth,
                game.partitionSize + game.layout.height + (((inputHeightBase - game.layout.height) - (musicButton.symbolRadius * 2)) / 2) + ((3 * (musicButton.symbolRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5),
                Button.BUTTON_MUSIC_FULL,
                musicColor,
                Color.BLACK,
                game);
        musicSplitButton = new Button((3 * game.partitionSize) + inputWidth,
                game.partitionSize + game.layout.height + (((inputHeightBase - game.layout.height) - (musicButton.symbolRadius * 2)) / 2) + ((2 * (musicButton.symbolRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5),
                Button.BUTTON_MUSIC_SPLIT,
                musicColor,
                Color.BLACK,
                game);
        musicPointillismButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (0 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_POINTILLISM,
                musicColor,
                Color.BLACK,
                game);
        musicLineageButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (1 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_LINEAGE,
                musicColor,
                Color.BLACK,
                game);
        musicTriTheWaltzButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (2 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_TRI_THE_WALTZ,
                musicColor,
                Color.BLACK,
                game);
        musicSquaredOffButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (3 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_SQUARED_OFF,
                musicColor,
                Color.BLACK,
                game);
        musicPentUpButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (4 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_PENT_UP,
                musicColor,
                Color.BLACK,
                game);
        musicHexidecibelButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (5 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_HEXIDECIBEL,
                musicColor,
                Color.BLACK,
                game);
        musicInterseptorButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (6 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_INTERSEPTOR,
                musicColor,
                Color.BLACK,
                game);
        musicRoctopusButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (7 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_ROCTOPUS,
                musicColor,
                Color.BLACK,
                game);
        musicNonplussedButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (8 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_NONPLUSSED,
                musicColor,
                Color.BLACK,
                game);
        difficultyButton = new Button((2 * game.partitionSize) + inputWidth,
                (3 * game.partitionSize) + (2 * inputHeightBase),
                inputWidth,
                inputHeightBase,
                Button.BUTTON_DIFFICULTY,
                difficultyColor,
                Color.BLACK,
                game);
        difficultyDialButton = new Button((2 * game.partitionSize) + inputWidth + (inputWidth / 10),
                (3 * game.partitionSize) + (2 * inputHeightBase) + (inputHeightBase / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_DIFFICULTY_DIAL,
                difficultyColor,
                Color.BLACK,
                game);
        difficultyChevronDownButton = new Button((2 * game.partitionSize) + inputWidth + ((3 * inputWidth) / 10),
                (3 * game.partitionSize) + (2 * inputHeightBase) + (inputHeightBase / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_DIFFICULTY_CHEVRON_DOWN,
                difficultyColor,
                Color.BLACK,
                game);
        difficultyChevronUpButton = new Button((2 * game.partitionSize) + inputWidth + ((7 * inputWidth) / 10),
                (3 * game.partitionSize) + (2 * inputHeightBase) + (inputHeightBase / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_DIFFICULTY_CHEVRON_UP,
                difficultyColor,
                Color.BLACK,
                game);
        timeButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightBase,
                inputWidth,
                inputHeightBase,
                Button.BUTTON_TIME,
                timeColor,
                Color.BLACK,
                game);
        timeClockButton = new Button((2 * game.partitionSize) + inputWidth + (inputWidth / 10),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_TIME_CLOCK,
                timeColor,
                Color.BLACK,
                game);
        timeChevronDownButton = new Button((2 * game.partitionSize) + inputWidth + ((3 * inputWidth) / 10),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_TIME_CHEVRON_DOWN,
                timeColor,
                Color.BLACK,
                game);
        timeChevronUpButton = new Button((2 * game.partitionSize) + inputWidth + ((7 * inputWidth) / 10),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_TIME_CHEVRON_UP,
                timeColor,
                Color.BLACK,
                game);
        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(musicButton);
        buttonList.add(musicFullButton);
        buttonList.add(musicSplitButton);
        buttonList.add(musicPointillismButton);
        buttonList.add(musicLineageButton);
        buttonList.add(musicTriTheWaltzButton);
        buttonList.add(musicSquaredOffButton);
        buttonList.add(musicPentUpButton);
        buttonList.add(musicHexidecibelButton);
        buttonList.add(musicInterseptorButton);
        buttonList.add(musicRoctopusButton);
        buttonList.add(musicNonplussedButton);
        buttonList.add(difficultyButton);
        buttonList.add(difficultyDialButton);
        buttonList.add(difficultyChevronDownButton);
        buttonList.add(difficultyChevronUpButton);
        buttonList.add(timeButton);
        buttonList.add(timeClockButton);
        buttonList.add(timeChevronDownButton);
        buttonList.add(timeChevronUpButton);
        buttonList.add(backButton);

        for(int i = 0; i <= game.maxBase - game.minBase; i++) {
            float x = 2 * game.partitionSize + inputWidth;
            float y = ((4 * game.partitionSize) + (3 * inputHeightBase)) + (i * (game.partitionSize + inputHeightBase));
            int buttonType = Button.BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SQUARE + i;
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
                drawTitleText();
            }
        }
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
        game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE);

        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererFilled.rectLine((game.camera.viewportWidth / 6) - (symbolRadius / 3),
                (game.camera.viewportHeight / 6) - (symbolRadius / 3),
                (game.camera.viewportWidth / 6) + (symbolRadius / 3),
                (game.camera.viewportHeight / 6) + (symbolRadius / 3),
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
        game.shapeRendererFilled.circle((game.camera.viewportWidth / 6) - (symbolRadius / 3), (game.camera.viewportHeight / 6) - (symbolRadius / 3), (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
        game.shapeRendererFilled.circle((game.camera.viewportWidth / 6) + (symbolRadius / 3), (game.camera.viewportHeight / 6) + (symbolRadius / 3), (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);

        game.draw.drawClock((game.camera.viewportWidth / 6) - (symbolRadius / 6),
                (game.camera.viewportHeight / 6) + (symbolRadius / 6),
                (symbolRadius / 2) / 3,
                Color.WHITE,
                Color.BLACK);
        game.draw.drawClock((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                (game.camera.viewportHeight / 6) - (symbolRadius / 6),
                (symbolRadius / 2) / 3,
                Color.WHITE,
                Color.BLACK);
    }

    public void drawTitleText() {
        FontUtils.printText(game.batch,
                game.fontNumPlayers,
                game.layout,
                Color.WHITE,
                Button.SINGLE_PLAYER_SYMBOL_STRING,
                game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                0,
                1);
    }
}
