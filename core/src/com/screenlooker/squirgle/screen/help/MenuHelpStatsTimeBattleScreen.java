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
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuHelpStatsTimeBattleScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int STATS = 0;
    private final static int DIFFICULTY_NAME = 1;
    private final static int GAME_LENGTH = 2;
    private final static int BACK = 3;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;
    private final static int NUM_STATS_ELEMENTS = 6;
    private final static int NUM_STATS_SUB_ELEMENTS = 3;

    private final static float FONT_STATS_SIZE_DIVISOR = 55.5f;

    private final static String HIGHEST_SCORE = "HIGHEST SCORE: ";
    private final static String NUM_WINS = "WINS: ";
    private final static String NUM_LOSSES = "LOSSES: ";
    private final static String ONE_MINUTE = "1M";
    private final static String THREE_MINUTES = "3M";
    private final static String FIVE_MINUTES = "5M";

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;
    private float subElementShapeRadius;

    private float gameLengthBlockWidth;
    private float gameLengthBlockHeight;
    private float gameLengthMinY;
    private float gameLengthMaxY;

    private Vector3 touchPoint;

    private Color statsColor;
    private Color backColor;

    private boolean difficultyNameEasyTouched;
    private boolean difficultyNameMediumTouched;
    private boolean difficultyNameHardTouched;
    private boolean gameLength1MTouched;
    private boolean gameLength3MTouched;
    private boolean gameLength5MTouched;
    private boolean backTouched;

    private String highestScoreSquareString;
    private String numWinsSquareString;
    private String numLossesSquareString;
    private String highestScorePentagonString;
    private String numWinsPentagonString;
    private String numLossesPentagonString;
    private String highestScoreHexagonString;
    private String numWinsHexagonString;
    private String numLossesHexagonString;
    private String highestScoreSeptagonString;
    private String numWinsSeptagonString;
    private String numLossesSeptagonString;
    private String highestScoreOctagonString;
    private String numWinsOctagonString;
    private String numLossesOctagonString;
    private String highestScoreNonagonString;
    private String numWinsNonagonString;
    private String numLossesNonagonString;

    //TODO: Set up fontScore
    public MenuHelpStatsTimeBattleScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontStats(MathUtils.round(game.camera.viewportWidth / FONT_STATS_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;
        subElementShapeRadius = (inputHeight / NUM_STATS_ELEMENTS) / 2;

        gameLengthBlockWidth = ((inputWidth / 4) - (4 * game.partitionSize)) / 3;
        gameLengthBlockHeight = ((inputHeight - (4 * game.partitionSize)) / 3) / 4;
        gameLengthMinY = 0;
        gameLengthMaxY = 0;

        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            gameLengthMinY = game.partitionSize + ((2 * inputHeight) / 3);
            gameLengthMaxY = game.partitionSize + ((2 * inputHeight) / 3) + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            gameLengthMinY = game.partitionSize + (inputHeight / 3);
            gameLengthMaxY = game.partitionSize + (inputHeight / 3) + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            gameLengthMinY = game.partitionSize;
            gameLengthMaxY = game.partitionSize + gameLengthBlockHeight;
        }

        touchPoint = new Vector3();

        statsColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        difficultyNameEasyTouched = false;
        difficultyNameMediumTouched = false;
        difficultyNameHardTouched = false;
        gameLength1MTouched = false;
        gameLength3MTouched = false;
        gameLength5MTouched = false;
        backTouched = false;

        setStatsStrings();
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
        drawSubElementShapes();

        game.shapeRendererFilled.end();

        drawStatsText();
        drawDifficultyText();
        drawGameLengthText();
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

        difficultyNameEasyTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth
                && touchPoint.y > game.partitionSize + ((2 * inputHeight) / 3)
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        difficultyNameMediumTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth
                && touchPoint.y > game.partitionSize + (inputHeight / 3)
                && touchPoint.y < game.partitionSize + ((2 * inputHeight) / 3);
        difficultyNameHardTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + (inputHeight / 3);
        gameLength1MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + game.partitionSize
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + game.partitionSize + gameLengthBlockWidth
                && touchPoint.y > gameLengthMinY
                && touchPoint.y < gameLengthMaxY;
        gameLength3MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + (2 * game.partitionSize) + gameLengthBlockWidth
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + (2 * game.partitionSize) + (2 * gameLengthBlockWidth)
                && touchPoint.y > gameLengthMinY
                && touchPoint.y < gameLengthMaxY;
        gameLength5MTouched = touchPoint.x >  game.camera.viewportWidth - (3 * game.partitionSize) - inputWidth - gameLengthBlockWidth
                && touchPoint.x < game.camera.viewportWidth - (3 * game.partitionSize) - inputWidth
                && touchPoint.y > gameLengthMinY
                && touchPoint.y < gameLengthMaxY;
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(difficultyNameEasyTouched) {
            game.difficulty = Squirgle.DIFFICULTY_EASY;
        } else if(difficultyNameMediumTouched) {
            game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
        } else if(difficultyNameHardTouched) {
            game.difficulty = Squirgle.DIFFICULTY_HARD;
        } else if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsScreen(game));
            dispose();
        }

        if(gameLength1MTouched) {
            game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
        } else if(gameLength3MTouched) {
            game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
        } else if(gameLength5MTouched) {
            game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
        }

        if(difficultyNameEasyTouched || difficultyNameMediumTouched || difficultyNameHardTouched) {
            setStatsStrings();
        }

        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            gameLengthMinY = game.partitionSize + ((2 * inputHeight) / 3);
            gameLengthMaxY = game.partitionSize + ((2 * inputHeight) / 3) + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            gameLengthMinY = game.partitionSize + (inputHeight / 3);
            gameLengthMaxY = game.partitionSize + (inputHeight / 3) + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            gameLengthMinY = game.partitionSize;
            gameLengthMaxY = game.partitionSize + gameLengthBlockHeight;
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
        drawStatsInput();
        drawDifficultyInput();
        drawGameLengthInput();
        drawBackInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case STATS : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
            case DIFFICULTY_NAME : {
                float blockHeight = (inputHeight - (4 * game.partitionSize)) / 3;
                float blockWidth = inputWidth / 4;
                if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    game.shapeRendererFilled.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            game.camera.viewportHeight - (2 * game.partitionSize) - blockHeight,
                            blockWidth,
                            blockHeight);
                } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    game.shapeRendererFilled.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            (3 * game.partitionSize) + blockHeight,
                            blockWidth,
                            blockHeight);
                } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    game.shapeRendererFilled.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            (2 * game.partitionSize),
                            blockWidth,
                            blockHeight);
                }
            }
            case GAME_LENGTH : {
                float difficultyBlockHeight = (inputHeight - (4 * game.partitionSize)) / 3;
                float difficultyBlockWidth = inputWidth / 4;
                float rectY = 0;
                float rectX = 0;
                if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    rectY = game.camera.viewportHeight - (2 * game.partitionSize) - difficultyBlockHeight;
                } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    rectY = (3 * game.partitionSize) + difficultyBlockHeight;
                } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    rectY = (2 * game.partitionSize);
                }
                if(game.timeAttackNumSeconds == Squirgle.ONE_MINUTE) {
                    rectX = game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + game.partitionSize;
                } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
                    rectX = game.camera.viewportWidth - inputWidth - (inputWidth / 4) + gameLengthBlockWidth;
                } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
                    rectX = game.camera.viewportWidth - (3 * game.partitionSize) - inputWidth - gameLengthBlockWidth;
                }
                game.shapeRendererFilled.rect(rectX,
                        rectY,
                        gameLengthBlockWidth,
                        gameLengthBlockHeight);
            }
            case BACK : {
                game.shapeRendererFilled.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawSubElementShapes() {
        game.draw.drawSquare((2 * game.partitionSize) + inputWidth + subElementShapeRadius,
                game.camera.viewportHeight - game.partitionSize - subElementShapeRadius,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawPentagon((2 * game.partitionSize) + inputWidth + subElementShapeRadius,
                game.camera.viewportHeight - game.partitionSize - (3 * subElementShapeRadius),
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawHexagon((2 * game.partitionSize) + inputWidth + subElementShapeRadius,
                game.camera.viewportHeight - game.partitionSize - (5 * subElementShapeRadius),
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawSeptagon((2 * game.partitionSize) + inputWidth + subElementShapeRadius,
                game.camera.viewportHeight - game.partitionSize - (7 * subElementShapeRadius),
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawOctagon((2 * game.partitionSize) + inputWidth + subElementShapeRadius,
                game.camera.viewportHeight - game.partitionSize - (9 * subElementShapeRadius),
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawNonagon((2 * game.partitionSize) + inputWidth + subElementShapeRadius,
                game.camera.viewportHeight - game.partitionSize - (11 * subElementShapeRadius),
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawStatsInput() {
        drawInputRectangle(STATS, statsColor);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawDifficultyInput() {
        drawInputRectangle(DIFFICULTY_NAME, Color.BLACK);
    }

    public void drawGameLengthInput() { drawInputRectangle(GAME_LENGTH, statsColor); }

    public void drawStatsText() {
        //Square
        game.layout.setText(game.fontStats, highestScoreSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreSquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Pentagon
        game.layout.setText(game.fontStats, highestScorePentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScorePentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsPentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesPentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Hexagon
        game.layout.setText(game.fontStats, highestScoreHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Septagon
        game.layout.setText(game.fontStats, highestScoreSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreSeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Octagon
        game.layout.setText(game.fontStats, highestScoreOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Nonagon
        game.layout.setText(game.fontStats, highestScoreNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
    }

    public void drawDifficultyText() {
        String easyString = Squirgle.DIFFICULTY_EASY;
        game.layout.setText(game.fontStats, easyString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.difficulty.equals(Squirgle.DIFFICULTY_EASY) ? Color.WHITE : Color.BLACK,
                easyString,
                game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + ((5 * inputHeight) / 6),
                0,
                1);
        String mediumString = Squirgle.DIFFICULTY_MEDIUM;
        game.layout.setText(game.fontStats, mediumString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM) ? Color.WHITE : Color.BLACK,
                mediumString,
                game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + (inputHeight / 2),
                0,
                1);
        String hardString = Squirgle.DIFFICULTY_HARD;
        game.layout.setText(game.fontStats, hardString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.difficulty.equals(Squirgle.DIFFICULTY_HARD) ? Color.WHITE : Color.BLACK,
                hardString,
                game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + (inputHeight / 6),
                0,
                1);
    }

    public void drawGameLengthText() {
        String oneMinuteString = ONE_MINUTE;
        game.layout.setText(game.fontStats, oneMinuteString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.BLACK : Color.WHITE,
                oneMinuteString,
                game.camera.viewportWidth - game.partitionSize - inputWidth - (inputWidth / 4) + (gameLengthBlockWidth / 2),
                gameLengthMinY + (gameLengthBlockHeight / 2),
                0,
                1);
        String threeMinutesString = THREE_MINUTES;
        game.layout.setText(game.fontStats, threeMinutesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.BLACK : Color.WHITE,
                threeMinutesString,
                game.camera.viewportWidth - inputWidth - (inputWidth / 4) + gameLengthBlockWidth + (gameLengthBlockWidth / 2),
                gameLengthMinY + (gameLengthBlockHeight / 2),
                0,
                1);
        String fiveMinutesString = FIVE_MINUTES;
        game.layout.setText(game.fontStats, fiveMinutesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.BLACK : Color.WHITE,
                fiveMinutesString,
                game.camera.viewportWidth - (3 * game.partitionSize) - inputWidth - (gameLengthBlockWidth / 2),
                gameLengthMinY + (gameLengthBlockHeight / 2),
                0,
                1);
    }

    public void drawTitle() {
        game.draw.drawQuestionMark(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawModulo(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                game.shapeRendererFilled);

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
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawClock((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                (game.camera.viewportHeight / 6) - (symbolRadius / 6),
                (symbolRadius / 2) / 3,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void setStatsStrings() {
        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            if(game.timeAttackNumSeconds == Squirgle.ONE_MINUTE) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareEasyOneMinute;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareEasyOneMinute;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareEasyOneMinute;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonEasyOneMinute;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonEasyOneMinute;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonEasyOneMinute;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonEasyOneMinute;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonEasyOneMinute;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonEasyOneMinute;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonEasyOneMinute;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonEasyOneMinute;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonEasyOneMinute;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonEasyOneMinute;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonEasyOneMinute;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonEasyOneMinute;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonEasyOneMinute;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonEasyOneMinute;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonEasyOneMinute;
            } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareEasyThreeMinutes;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareEasyThreeMinutes;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareEasyThreeMinutes;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonEasyThreeMinutes;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonEasyThreeMinutes;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonEasyThreeMinutes;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonEasyThreeMinutes;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonEasyThreeMinutes;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonEasyThreeMinutes;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonEasyThreeMinutes;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonEasyThreeMinutes;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonEasyThreeMinutes;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonEasyThreeMinutes;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonEasyThreeMinutes;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonEasyThreeMinutes;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonEasyThreeMinutes;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonEasyThreeMinutes;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonEasyThreeMinutes;
            } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareEasyFiveMinutes;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareEasyFiveMinutes;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareEasyFiveMinutes;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonEasyFiveMinutes;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonEasyFiveMinutes;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonEasyFiveMinutes;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonEasyFiveMinutes;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonEasyFiveMinutes;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonEasyFiveMinutes;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonEasyFiveMinutes;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonEasyFiveMinutes;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonEasyFiveMinutes;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonEasyFiveMinutes;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonEasyFiveMinutes;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonEasyFiveMinutes;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonEasyFiveMinutes;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonEasyFiveMinutes;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonEasyFiveMinutes;
            }
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            if(game.timeAttackNumSeconds == Squirgle.ONE_MINUTE) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareMediumOneMinute;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareMediumOneMinute;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareMediumOneMinute;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonMediumOneMinute;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonMediumOneMinute;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonMediumOneMinute;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonMediumOneMinute;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonMediumOneMinute;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonMediumOneMinute;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonMediumOneMinute;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonMediumOneMinute;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonMediumOneMinute;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonMediumOneMinute;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonMediumOneMinute;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonMediumOneMinute;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonMediumOneMinute;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonMediumOneMinute;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonMediumOneMinute;
            } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareMediumThreeMinutes;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareMediumThreeMinutes;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareMediumThreeMinutes;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonMediumThreeMinutes;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonMediumThreeMinutes;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonMediumThreeMinutes;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonMediumThreeMinutes;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonMediumThreeMinutes;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonMediumThreeMinutes;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonMediumThreeMinutes;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonMediumThreeMinutes;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonMediumThreeMinutes;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonMediumThreeMinutes;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonMediumThreeMinutes;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonMediumThreeMinutes;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonMediumThreeMinutes;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonMediumThreeMinutes;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonMediumThreeMinutes;
            } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareMediumFiveMinutes;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareMediumFiveMinutes;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareMediumFiveMinutes;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonMediumFiveMinutes;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonMediumFiveMinutes;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonMediumFiveMinutes;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonMediumFiveMinutes;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonMediumFiveMinutes;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonMediumFiveMinutes;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonMediumFiveMinutes;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonMediumFiveMinutes;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonMediumFiveMinutes;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonMediumFiveMinutes;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonMediumFiveMinutes;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonMediumFiveMinutes;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonMediumFiveMinutes;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonMediumFiveMinutes;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonMediumFiveMinutes;
            }
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            if(game.timeAttackNumSeconds == Squirgle.ONE_MINUTE) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareHardOneMinute;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareHardOneMinute;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareHardOneMinute;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonHardOneMinute;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonHardOneMinute;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonHardOneMinute;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonHardOneMinute;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonHardOneMinute;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonHardOneMinute;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonHardOneMinute;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonHardOneMinute;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonHardOneMinute;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonHardOneMinute;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonHardOneMinute;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonHardOneMinute;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonHardOneMinute;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonHardOneMinute;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonHardOneMinute;
            } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareHardThreeMinutes;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareHardThreeMinutes;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareHardThreeMinutes;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonHardThreeMinutes;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonHardThreeMinutes;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonHardThreeMinutes;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonHardThreeMinutes;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonHardThreeMinutes;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonHardThreeMinutes;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonHardThreeMinutes;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonHardThreeMinutes;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonHardThreeMinutes;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonHardThreeMinutes;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonHardThreeMinutes;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonHardThreeMinutes;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonHardThreeMinutes;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonHardThreeMinutes;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonHardThreeMinutes;
            } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
                highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSquareHardFiveMinutes;
                numWinsSquareString = NUM_WINS + game.stats.numTimesWonTimeBattleSquareHardFiveMinutes;
                numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSquareHardFiveMinutes;
                highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattlePentagonHardFiveMinutes;
                numWinsPentagonString = NUM_WINS + game.stats.numTimesWonTimeBattlePentagonHardFiveMinutes;
                numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattlePentagonHardFiveMinutes;
                highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleHexagonHardFiveMinutes;
                numWinsHexagonString = NUM_WINS + game.stats.numTimesWonTimeBattleHexagonHardFiveMinutes;
                numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleHexagonHardFiveMinutes;
                highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleSeptagonHardFiveMinutes;
                numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonTimeBattleSeptagonHardFiveMinutes;
                numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleSeptagonHardFiveMinutes;
                highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleOctagonHardFiveMinutes;
                numWinsOctagonString = NUM_WINS + game.stats.numTimesWonTimeBattleOctagonHardFiveMinutes;
                numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleOctagonHardFiveMinutes;
                highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreTimeBattleNonagonHardFiveMinutes;
                numWinsNonagonString = NUM_WINS + game.stats.numTimesWonTimeBattleNonagonHardFiveMinutes;
                numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostTimeBattleNonagonHardFiveMinutes;
            }
        }
    }
}
