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
import com.screenlooker.squirgle.util.InputUtils;

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

    private float difficultyBlockHeight;
    private float difficultyBlockWidth;
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

    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;
    private float textOpacity;

    //TODO: Set up fontScore
    public MenuHelpStatsTimeBattleScreen(final Squirgle game, Color veilColor) {
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

        difficultyBlockHeight = (inputHeight - (4 * game.partitionSize)) / 3;
        difficultyBlockWidth = inputWidth / 4;
        gameLengthBlockWidth = (inputWidth / 4) / 3;
        gameLengthBlockHeight = ((inputHeight - (4 * game.partitionSize)) / 3) / 4;
        gameLengthMinY = 0;
        gameLengthMaxY = 0;

        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            gameLengthMinY = game.camera.viewportHeight - (2 * game.partitionSize) - difficultyBlockHeight;
            gameLengthMaxY = game.camera.viewportHeight - (2 * game.partitionSize) - difficultyBlockHeight + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            gameLengthMinY = game.camera.viewportHeight - (3 * game.partitionSize) - (2 * difficultyBlockHeight);
            gameLengthMaxY = game.camera.viewportHeight - (3 * game.partitionSize) - (2 * difficultyBlockHeight) + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            gameLengthMinY = game.camera.viewportHeight - (4 * game.partitionSize) - (3 * difficultyBlockHeight);
            gameLengthMaxY = game.camera.viewportHeight - (4 * game.partitionSize) - (3 * difficultyBlockHeight) + gameLengthBlockHeight;
        }

        touchPoint = new Vector3();

        statsColor = ColorUtils.COLOR_ORANGE;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        difficultyNameEasyTouched = false;
        difficultyNameMediumTouched = false;
        difficultyNameHardTouched = false;
        gameLength1MTouched = false;
        gameLength3MTouched = false;
        gameLength5MTouched = false;
        backTouched = false;

        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_HELP_STATS_TIME_BATTLE_BACK,
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
                drawDifficultyText();
                drawGameLengthText();
            }
        }

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
        }

        textOpacity = buttonList.get(0).textOpacity;

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
        gameLength1MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + gameLengthBlockWidth
                && touchPoint.y > gameLengthMinY
                && touchPoint.y < gameLengthMaxY;
        gameLength3MTouched = touchPoint.x > game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + gameLengthBlockWidth
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + (2 * gameLengthBlockWidth)
                && touchPoint.y > gameLengthMinY
                && touchPoint.y < gameLengthMaxY;
        gameLength5MTouched = touchPoint.x >  game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + (2 * gameLengthBlockWidth)
                && touchPoint.x < game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + (3 * gameLengthBlockWidth)
                && touchPoint.y > gameLengthMinY
                && touchPoint.y < gameLengthMaxY;

        if(difficultyNameEasyTouched) {
            if(!game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            game.difficulty = Squirgle.DIFFICULTY_EASY;
        } else if(difficultyNameMediumTouched) {
            if(!game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
        } else if(difficultyNameHardTouched) {
            if(!game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            game.difficulty = Squirgle.DIFFICULTY_HARD;
        }

        if(gameLength1MTouched) {
            if(game.timeAttackNumSeconds != Squirgle.ONE_MINUTE) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
        } else if(gameLength3MTouched) {
            if(game.timeAttackNumSeconds != Squirgle.THREE_MINUTES) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
        } else if(gameLength5MTouched) {
            if(game.timeAttackNumSeconds != Squirgle.FIVE_MINUTES) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
        }

        if(difficultyNameEasyTouched || difficultyNameMediumTouched || difficultyNameHardTouched) {
            setStatsStrings();
        }

        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            gameLengthMinY = game.camera.viewportHeight - (2 * game.partitionSize) - difficultyBlockHeight;
            gameLengthMaxY = game.camera.viewportHeight - (2 * game.partitionSize) - difficultyBlockHeight + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            gameLengthMinY = game.camera.viewportHeight - (3 * game.partitionSize) - (2 * difficultyBlockHeight);
            gameLengthMaxY = game.camera.viewportHeight - (3 * game.partitionSize) - (2 * difficultyBlockHeight) + gameLengthBlockHeight;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            gameLengthMinY = game.camera.viewportHeight - (4 * game.partitionSize) - (3 * difficultyBlockHeight);
            gameLengthMaxY = game.camera.viewportHeight - (4 * game.partitionSize) - (3 * difficultyBlockHeight) + gameLengthBlockHeight;
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
        drawDifficultyInput();
        drawGameLengthInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case STATS : {
                game.draw.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeight,
                        color);
            }
            case DIFFICULTY_NAME : {
                float blockHeight = (inputHeight - (4 * game.partitionSize)) / 3;
                float blockWidth = inputWidth / 4;
                if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    game.draw.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            game.camera.viewportHeight - (2 * game.partitionSize) - blockHeight,
                            blockWidth,
                            blockHeight,
                            color);
                } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    game.draw.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            (3 * game.partitionSize) + blockHeight,
                            blockWidth,
                            blockHeight,
                            color);
                } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    game.draw.rect(game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4),
                            (2 * game.partitionSize),
                            blockWidth,
                            blockHeight,
                            color);
                }
            }
            case GAME_LENGTH : {
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
                    rectX = game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4);
                } else if(game.timeAttackNumSeconds == Squirgle.THREE_MINUTES) {
                    rectX = game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + gameLengthBlockWidth;
                } else if(game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES) {
                    rectX = game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - gameLengthBlockWidth;
                }
                game.draw.rect(rectX,
                        rectY,
                        gameLengthBlockWidth,
                        gameLengthBlockHeight,
                        color);
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
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY + ((5 * game.layout.height) / 4),
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numWinsSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY,
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numLossesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY - ((5 * game.layout.height) / 4),
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
                pentagonY + ((5 * game.layout.height) / 4),
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numWinsPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY,
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numLossesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY - ((5 * game.layout.height) / 4),
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
                hexagonY + ((5 * game.layout.height) / 4),
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numWinsHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY,
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numLossesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY - ((5 * game.layout.height) / 4),
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
                septagonY + ((5 * game.layout.height) / 4),
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numWinsSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY,
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numLossesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY - ((5 * game.layout.height) / 4),
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
                octagonY + ((5 * game.layout.height) / 4),
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numWinsOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY,
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numLossesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY - ((5 * game.layout.height) / 4),
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
                nonagonY + ((5 * game.layout.height) / 4),
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numWinsNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY,
                0,
                textOpacity);
        game.layout.setText(game.fontStats, numLossesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY - ((5 * game.layout.height) / 4),
                0,
                textOpacity);
    }

    public void drawDifficultyText() {
        String easyString = Squirgle.DIFFICULTY_EASY;
        game.layout.setText(game.fontStats, easyString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.difficulty.equals(Squirgle.DIFFICULTY_EASY) ? Color.WHITE : Color.BLACK,
                easyString,
                game.difficulty.equals(Squirgle.DIFFICULTY_EASY) ? game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (difficultyBlockWidth / 2) : game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + ((5 * inputHeight) / 6),
                0,
                textOpacity);
        String mediumString = Squirgle.DIFFICULTY_MEDIUM;
        game.layout.setText(game.fontStats, mediumString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM) ? Color.WHITE : Color.BLACK,
                mediumString,
                game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM) ? game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (difficultyBlockWidth / 2) : game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + (inputHeight / 2),
                0,
                textOpacity);
        String hardString = Squirgle.DIFFICULTY_HARD;
        game.layout.setText(game.fontStats, hardString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.difficulty.equals(Squirgle.DIFFICULTY_HARD) ? Color.WHITE : Color.BLACK,
                hardString,
                game.difficulty.equals(Squirgle.DIFFICULTY_HARD) ? game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (difficultyBlockWidth / 2) : game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (game.layout.width / 2),
                game.partitionSize + (inputHeight / 6),
                0,
                textOpacity);
    }

    public void drawGameLengthText() {
        String oneMinuteString = ONE_MINUTE;
        game.layout.setText(game.fontStats, oneMinuteString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.BLACK : Color.WHITE,
                oneMinuteString,
                game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + (gameLengthBlockWidth / 2),
                gameLengthMinY + (gameLengthBlockHeight / 2),
                0,
                textOpacity);
        String threeMinutesString = THREE_MINUTES;
        game.layout.setText(game.fontStats, threeMinutesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.BLACK : Color.WHITE,
                threeMinutesString,
                game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (inputWidth / 4) + gameLengthBlockWidth + (gameLengthBlockWidth / 2),
                gameLengthMinY + (gameLengthBlockHeight / 2),
                0,
                textOpacity);
        String fiveMinutesString = FIVE_MINUTES;
        game.layout.setText(game.fontStats, fiveMinutesString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.BLACK : Color.WHITE,
                fiveMinutesString,
                game.camera.viewportWidth - (2 * game.partitionSize) - inputWidth - (gameLengthBlockWidth / 2),
                gameLengthMinY + (gameLengthBlockHeight / 2),
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
