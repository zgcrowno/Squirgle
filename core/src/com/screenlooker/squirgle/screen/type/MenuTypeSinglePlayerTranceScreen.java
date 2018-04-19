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
import com.screenlooker.squirgle.screen.TranceScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

//TODO: Refactor all the music input behavior (create easier to read variables and such)
public class MenuTypeSinglePlayerTranceScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int PLAY = 0;
    private final static int MUSIC = 1;
    private final static int MUSIC_NAME = 2;
    private final static int BACK = 3;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 2;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_MIDDLE_PARTITIONS_VERTICAL = NUM_MIDDLE_INPUTS_VERTICAL + 1;

    private final static float FONT_TRACK_NAME_DIVISOR = 6.5f;

    private float inputWidth;
    private float inputHeightMiddle;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color playColor;
    private Color musicColor;
    private Color backColor;

    private boolean playTouched;
    private boolean backTouched;
    private boolean musicNamePointillismTouched;
    private boolean musicNameLineageTouched;
    private boolean musicNameTriTheWaltzTouched;
    private boolean musicNameSquaredOffTouched;
    private boolean musicNamePentUpTouched;
    private boolean musicNameHexidecibelTouched;
    private boolean musicNameInterseptorTouched;
    private boolean musicNameRoctopusTouched;
    private boolean musicNameNonplussedTouched;

    private Button playButton;
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
    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public MenuTypeSinglePlayerTranceScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightMiddle = (game.camera.viewportHeight - (game.partitionSize * NUM_MIDDLE_PARTITIONS_VERTICAL)) / NUM_MIDDLE_INPUTS_VERTICAL;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightMiddle ? (inputHeightMiddle / 2) : (inputWidth / 2);

        game.setUpFontButton(MathUtils.round(inputShapeRadius / 2.75f));
        game.setUpFontNumPlayers(MathUtils.round(symbolRadius / 3));

        touchPoint = new Vector3();

        playColor = ColorUtils.COLOR_BLUISH_GREEN;
        musicColor = ColorUtils.COLOR_BLUE;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        playTouched = false;
        backTouched = false;

        playButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightMiddle,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY,
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

        game.setUpFontTrackName(MathUtils.round(musicButton.symbolRadius / FONT_TRACK_NAME_DIVISOR));

        musicPointillismButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (0 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_POINTILLISM,
                musicColor,
                Color.BLACK,
                game);
        musicLineageButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (1 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_LINEAGE,
                musicColor,
                Color.BLACK,
                game);
        musicTriTheWaltzButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (2 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_TRI_THE_WALTZ,
                musicColor,
                Color.BLACK,
                game);
        musicSquaredOffButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (3 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_SQUARED_OFF,
                musicColor,
                Color.BLACK,
                game);
        musicPentUpButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (4 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_PENT_UP,
                musicColor,
                Color.BLACK,
                game);
        musicHexidecibelButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (5 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_HEXIDECIBEL,
                musicColor,
                Color.BLACK,
                game);
        musicInterseptorButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (6 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_INTERSEPTOR,
                musicColor,
                Color.BLACK,
                game);
        musicRoctopusButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (7 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_ROCTOPUS,
                musicColor,
                Color.BLACK,
                game);
        musicNonplussedButton = new Button(game.camera.viewportWidth / 2 + ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (8 * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                (inputWidth / 2) - game.partitionSize - ((musicButton.symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                (7 * game.fontTrackName.getCapHeight()) / 4,
                Button.BUTTON_MUSIC_NONPLUSSED,
                musicColor,
                Color.BLACK,
                game);
        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK,
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
    }

    public void drawTitleText() {
        FontUtils.printText(game.batch,
                game.fontNumPlayers,
                game.layout,
                Color.WHITE,
                Button.SINGLE_PLAYER_SYMBOL_STRING,
                game.camera.viewportWidth / 6,
                game.camera.viewportHeight / 2,
                0,
                1);
    }
}
