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
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuHelpStatsGeneralScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int STATS = 0;
    private final static int BACK = 1;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;
    private final static int NUM_STATS_ELEMENTS = 5;

    private final static float FONT_STATS_SIZE_DIVISOR = 33.3f;

    private final static String TIME_PLAYED = "TIME PLAYED: ";
    private final static String NUM_SQUIRGLES = "SQUIRGLES: ";
    private final static String FAVORITE_BASE = "FAVORITE BASE: ";
    private final static String FAVORITE_MODE = "FAVORITE MODE: ";
    private final static String FAVORITE_TRACK = "FAVORITE TRACK: ";
    private final static String HOURS = "h";
    private final static String MINUTES = "m";
    private final static String SECONDS = "s";

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color statsColor;
    private Color backColor;

    private boolean backTouched;

    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    //TODO: Set up fontScore
    public MenuHelpStatsGeneralScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontStats(MathUtils.round(game.camera.viewportWidth / FONT_STATS_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;

        touchPoint = new Vector3();

        statsColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        backTouched = false;

        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_HELP_STATS_GENERAL_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
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

        drawInputRectangles();

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
                drawStatsText();
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

    public void drawInputRectangles() {
        drawTitle();
        drawStatsInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        switch(placement) {
            case STATS : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeight,
                        color);
            }
        }
    }

    public void drawStatsInput() {
        drawInputRectangle(STATS, statsColor);
    }

    public void drawStatsText() {
        long hoursPlayed = MathUtils.floor(game.stats.timePlayed / 1000 / 60 / 60);
        long minutesPlayed = MathUtils.floor(game.stats.timePlayed / 1000 / 60 - (hoursPlayed * 60));
        long secondsPlayed = MathUtils.floor(game.stats.timePlayed / 1000 - (minutesPlayed * 60) - (hoursPlayed * 60 * 60));
        String timePlayedString = TIME_PLAYED + hoursPlayed + HOURS + minutesPlayed + MINUTES + secondsPlayed + SECONDS;
        game.layout.setText(game.fontStats, timePlayedString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                timePlayedString,
                (2 * game.partitionSize) + inputWidth + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (inputHeight / (NUM_STATS_ELEMENTS + 1)),
                0,
                1);

        String numSquirglesString = NUM_SQUIRGLES + game.stats.numSquirgles;
        game.layout.setText(game.fontStats, numSquirglesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesString,
                (2 * game.partitionSize) + inputWidth + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * (inputHeight / (NUM_STATS_ELEMENTS + 1))),
                0,
                1);

        String favoriteBaseString = FAVORITE_BASE + (game.stats.favoriteBase == 0 ? game.stats.NA : game.stats.favoriteBase);
        game.layout.setText(game.fontStats, favoriteBaseString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                favoriteBaseString,
                (2 * game.partitionSize) + inputWidth + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (3 * (inputHeight / (NUM_STATS_ELEMENTS + 1))),
                0,
                1);

        String favoriteModeString = FAVORITE_MODE + game.stats.favoriteMode;
        game.layout.setText(game.fontStats, favoriteModeString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                favoriteModeString,
                (2 * game.partitionSize) + inputWidth + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * (inputHeight / (NUM_STATS_ELEMENTS + 1))),
                0,
                1);

        String favoriteTrackString = FAVORITE_TRACK + game.stats.favoriteTrack;
        game.layout.setText(game.fontStats, favoriteTrackString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                favoriteTrackString,
                (2 * game.partitionSize) + inputWidth + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (5 * (inputHeight / (NUM_STATS_ELEMENTS + 1))),
                0,
                1);
    }

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
        game.draw.drawModulo(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE);
        game.draw.drawSigma(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 4,
                symbolRadius / 3,
                Color.WHITE,
                Color.BLACK);
    }

}
