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
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuHelpStatsTimeAttackScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int STATS = 0;
    private final static int GAME_LENGTH = 1;
    private final static int BACK = 2;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;
    private final static int NUM_STATS_ELEMENTS = 6;
    private final static int NUM_STATS_SUB_ELEMENTS = 1;

    private final static float FONT_STATS_SIZE_DIVISOR = 55.5f;

    private final static String HIGHEST_SCORE = "HIGHEST SCORE: ";
    private final static String ONE_MINUTE = "1m";
    private final static String THREE_MINUTES = "3m";
    private final static String FIVE_MINUTES = "5m";

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;
    private float subElementShapeRadius;

    private float subElementShapeX;
    private float squareY;
    private float pentagonY;
    private float hexagonY;
    private float septagonY;
    private float octagonY;
    private float nonagonY;

    private Vector3 touchPoint;

    private Color statsColor;
    private Color backColor;

    private boolean gameLength1MTouched;
    private boolean gameLength3MTouched;
    private boolean gameLength5MTouched;
    private boolean backTouched;

    private String highestScoreSquareString;
    private String highestScorePentagonString;
    private String highestScoreHexagonString;
    private String highestScoreSeptagonString;
    private String highestScoreOctagonString;
    private String highestScoreNonagonString;

    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;
    private float textOpacity;

    //TODO: Set up fontScore
    public MenuHelpStatsTimeAttackScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontStats(MathUtils.round(game.camera.viewportWidth / FONT_STATS_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;
        subElementShapeRadius = inputWidth / 8;

        subElementShapeX = (2 * game.partitionSize) + inputWidth + subElementShapeRadius;
        squareY = ((game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius)) / (NUM_STATS_ELEMENTS + 1)) + subElementShapeRadius;
        pentagonY = ((2 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (3 * subElementShapeRadius);
        hexagonY = ((3 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (5 * subElementShapeRadius);
        septagonY = ((4 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (7 * subElementShapeRadius);
        octagonY = ((5 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (9 * subElementShapeRadius);
        nonagonY = ((6 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (11 * subElementShapeRadius);

        game.setUpFontButton(MathUtils.round(symbolRadius / 2.75f));

        touchPoint = new Vector3();

        statsColor = ColorUtils.COLOR_ORANGE;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        gameLength1MTouched = false;
        gameLength3MTouched = false;
        gameLength5MTouched = false;
        backTouched = false;

        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_HELP_STATS_TIME_ATTACK_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(backButton);

        this.veilColor = veilColor;
        veilOpacity = 1;
        textOpacity = 0;

        setStatsStrings();
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
        drawSubElementShapes();

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
                drawGameLengthText();
            }
        }

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
        }

        textOpacity = buttonList.get(0).textOpacity;
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

        gameLength1MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth
                && touchPoint.y > game.partitionSize + ((2 * inputHeight) / 3)
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        gameLength3MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth
                && touchPoint.y > game.partitionSize + (inputHeight / 3)
                && touchPoint.y < game.partitionSize + ((2 * inputHeight) / 3);
        gameLength5MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + (inputHeight / 3);

        if(gameLength1MTouched) {
            game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
        } else if(gameLength3MTouched) {
            game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
        } else if(gameLength5MTouched) {
            game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
        }

        if(gameLength1MTouched || gameLength3MTouched || gameLength5MTouched) {
            setStatsStrings();
        }

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
        drawGameLengthInput();
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
            case GAME_LENGTH : {
                float blockHeight = (inputHeight - (4 * game.partitionSize)) / 3;
                float blockWidth = inputWidth / 4;
                if(game.timeAttackNumSeconds == Squirgle.ONE_MINUTE) {
                    game.draw.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            game.camera.viewportHeight - (2 * game.partitionSize) - blockHeight,
                            blockWidth,
                            blockHeight,
                            color);
                } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
                    game.draw.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            (3 * game.partitionSize) + blockHeight,
                            blockWidth,
                            blockHeight,
                            color);
                } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
                    game.draw.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            (2 * game.partitionSize),
                            blockWidth,
                            blockHeight,
                            color);
                }
            }
        }
    }

    public void drawSubElementShapes() {
        game.draw.drawSquare(subElementShapeX,
                squareY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
        game.draw.drawPentagon(subElementShapeX,
                pentagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
        game.draw.drawHexagon(subElementShapeX,
                hexagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
        game.draw.drawSeptagon(subElementShapeX,
                septagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
        game.draw.drawOctagon(subElementShapeX,
                octagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
        game.draw.drawNonagon(subElementShapeX,
                nonagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK);
    }

    public void drawStatsInput() {
        drawInputRectangle(STATS, statsColor);
    }

    public void drawGameLengthInput() {
        drawInputRectangle(GAME_LENGTH, Color.BLACK);
    }

    public void drawStatsText() {
        //Square
        game.layout.setText(game.fontStats, highestScoreSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY,
                0,
                textOpacity);

        //Pentagon
        game.layout.setText(game.fontStats, highestScorePentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScorePentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY,
                0,
                textOpacity);

        //Hexagon
        game.layout.setText(game.fontStats, highestScoreHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY,
                0,
                textOpacity);

        //Septagon
        game.layout.setText(game.fontStats, highestScoreSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY,
                0,
                textOpacity);

        //Octagon
        game.layout.setText(game.fontStats, highestScoreOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY,
                0,
                textOpacity);

        //Nonagon
        game.layout.setText(game.fontStats, highestScoreNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY,
                0,
                textOpacity);
    }

    public void drawGameLengthText() {
        String oneMinuteString = ONE_MINUTE;
        game.layout.setText(game.fontStats, oneMinuteString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.WHITE : Color.BLACK,
                oneMinuteString,
                game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 8) : game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + ((5 * inputHeight) / 6),
                0,
                textOpacity);
        String threeMinutesString = THREE_MINUTES;
        game.layout.setText(game.fontStats, threeMinutesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.WHITE : Color.BLACK,
                threeMinutesString,
                game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 8) : game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + (inputHeight / 2),
                0,
                textOpacity);
        String fiveMinutesString = FIVE_MINUTES;
        game.layout.setText(game.fontStats, fiveMinutesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.WHITE : Color.BLACK,
                fiveMinutesString,
                game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 8) : game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + (inputHeight / 6),
                0,
                textOpacity);
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
        game.draw.drawClock(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 4,
                symbolRadius / 3,
                Color.WHITE,
                Color.BLACK);
    }

    public void setStatsStrings() {
        if(game.timeAttackNumSeconds == Squirgle.ONE_MINUTE) {
            highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackSquareOneMinute;
            highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackPentagonOneMinute;
            highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackHexagonOneMinute;
            highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackSeptagonOneMinute;
            highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackOctagonOneMinute;
            highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackNonagonOneMinute;
        } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
            highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackSquareThreeMinutes;
            highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackPentagonThreeMinutes;
            highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackHexagonThreeMinutes;
            highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackSeptagonThreeMinutes;
            highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackOctagonThreeMinutes;
            highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackNonagonThreeMinutes;
        } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
            highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackSquareFiveMinutes;
            highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackPentagonFiveMinutes;
            highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackHexagonFiveMinutes;
            highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackSeptagonFiveMinutes;
            highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackOctagonFiveMinutes;
            highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeAttackNonagonFiveMinutes;
        }
    }
}
