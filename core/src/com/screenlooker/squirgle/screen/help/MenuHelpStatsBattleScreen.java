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

public class MenuHelpStatsBattleScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int STATS = 0;
    private final static int DIFFICULTY_NAME = 1;
    private final static int BACK = 2;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;
    private final static int NUM_STATS_ELEMENTS = 6;
    private final static int NUM_STATS_SUB_ELEMENTS = 4;

    private final static float FONT_STATS_SIZE_DIVISOR = 55.5f;

    private final static String NUM_SQUIRGLES = "SQUIRGLES: ";
    private final static String FASTEST_VICTORY = "FASTEST VICTORY: ";
    private final static String NUM_WINS = "WINS: ";
    private final static String NUM_LOSSES = "LOSSES: ";
    private final static String MINUTES = "M";
    private final static String SECONDS = "S";

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;
    private float subElementShapeRadius;

    private float squirgleHeightOffset;
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
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;

    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;

    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;

    private boolean difficultyNameEasyTouched;
    private boolean difficultyNameMediumTouched;
    private boolean difficultyNameHardTouched;
    private boolean backTouched;

    private String numSquirglesSquareString;
    private String fastestVictorySquareString;
    private String numWinsSquareString;
    private String numLossesSquareString;
    private String numSquirglesPentagonString;
    private String fastestVictoryPentagonString;
    private String numWinsPentagonString;
    private String numLossesPentagonString;
    private String numSquirglesHexagonString;
    private String fastestVictoryHexagonString;
    private String numWinsHexagonString;
    private String numLossesHexagonString;
    private String numSquirglesSeptagonString;
    private String fastestVictorySeptagonString;
    private String numWinsSeptagonString;
    private String numLossesSeptagonString;
    private String numSquirglesOctagonString;
    private String fastestVictoryOctagonString;
    private String numWinsOctagonString;
    private String numLossesOctagonString;
    private String numSquirglesNonagonString;
    private String fastestVictoryNonagonString;
    private String numWinsNonagonString;
    private String numLossesNonagonString;

    //TODO: Set up fontScore
    public MenuHelpStatsBattleScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontStats(MathUtils.round(game.camera.viewportWidth / FONT_STATS_SIZE_DIVISOR));

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = (game.camera.viewportHeight - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;
        subElementShapeRadius = inputWidth / 8;
        squirgleHeightOffset = symbolRadius / 4;

        subElementShapeX = (2 * game.partitionSize) + inputWidth + subElementShapeRadius;
        squareY = ((game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius)) / (NUM_STATS_ELEMENTS + 1)) + subElementShapeRadius;
        pentagonY = ((2 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (3 * subElementShapeRadius);
        hexagonY = ((3 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (5 * subElementShapeRadius);
        septagonY = ((4 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (7 * subElementShapeRadius);
        octagonY = ((5 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (9 * subElementShapeRadius);
        nonagonY = ((6 * (game.camera.viewportHeight - ((2 * NUM_STATS_ELEMENTS) * subElementShapeRadius))) / (NUM_STATS_ELEMENTS + 1)) + (11 * subElementShapeRadius);

        touchPoint = new Vector3();

        statsColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        difficultyNameEasyTouched = false;
        difficultyNameMediumTouched = false;
        difficultyNameHardTouched = false;
        backTouched = false;

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

        if(difficultyNameEasyTouched || difficultyNameMediumTouched || difficultyNameHardTouched) {
            setStatsStrings();
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
        drawBackInput();
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
            case BACK : {
                game.draw.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeight,
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

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawDifficultyInput() {
        drawInputRectangle(DIFFICULTY_NAME, Color.BLACK);
    }

    public void drawStatsText() {
        //Square
        game.layout.setText(game.fontStats, numSquirglesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY + ((15 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictorySquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictorySquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY + ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY - ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY - ((15 * game.layout.height) / 8),
                0,
                1);

        //Pentagon
        game.layout.setText(game.fontStats, numSquirglesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY + ((15 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY + ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY - ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY - ((15 * game.layout.height) / 8),
                0,
                1);

        //Hexagon
        game.layout.setText(game.fontStats, numSquirglesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY + ((15 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY + ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY - ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY - ((15 * game.layout.height) / 8),
                0,
                1);

        //Septagon
        game.layout.setText(game.fontStats, numSquirglesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY + ((15 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictorySeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictorySeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY + ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY - ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY - ((15 * game.layout.height) / 8),
                0,
                1);

        //Octagon
        game.layout.setText(game.fontStats, numSquirglesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY + ((15 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY + ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY - ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY - ((15 * game.layout.height) / 8),
                0,
                1);

        //Nonagon
        game.layout.setText(game.fontStats, numSquirglesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY + ((15 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY + ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY - ((5 * game.layout.height) / 8),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY - ((15 * game.layout.height) / 8),
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

        game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
    }

    public void setStatsStrings() {
        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            long minutesPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareEasy / 1000 / 60);
            long secondsPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareEasy / 1000 - (minutesPlayedSquare * 60));
            numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSquareEasy;
            fastestVictorySquareString = FASTEST_VICTORY + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;
            numWinsSquareString = NUM_WINS + game.stats.numTimesWonBattleSquareEasy;
            numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostBattleSquareEasy;

            long minutesPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonEasy / 1000 / 60);
            long secondsPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonEasy / 1000 - (minutesPlayedPentagon * 60));
            numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattlePentagonEasy;
            fastestVictoryPentagonString = FASTEST_VICTORY + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;
            numWinsPentagonString = NUM_WINS + game.stats.numTimesWonBattlePentagonEasy;
            numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostBattlePentagonEasy;

            long minutesPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonEasy / 1000 / 60);
            long secondsPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonEasy / 1000 - (minutesPlayedHexagon * 60));
            numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleHexagonEasy;
            fastestVictoryHexagonString = FASTEST_VICTORY + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;
            numWinsHexagonString = NUM_WINS + game.stats.numTimesWonBattleHexagonEasy;
            numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostBattleHexagonEasy;

            long minutesPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonEasy / 1000 / 60);
            long secondsPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonEasy / 1000 - (minutesPlayedSeptagon * 60));
            numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSeptagonEasy;
            fastestVictorySeptagonString = FASTEST_VICTORY + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;
            numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonBattleSeptagonEasy;
            numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostBattleSeptagonEasy;

            long minutesPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonEasy / 1000 / 60);
            long secondsPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonEasy / 1000 - (minutesPlayedOctagon * 60));
            numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleOctagonEasy;
            fastestVictoryOctagonString = FASTEST_VICTORY + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;
            numWinsOctagonString = NUM_WINS + game.stats.numTimesWonBattleOctagonEasy;
            numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostBattleOctagonEasy;

            long minutesPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonEasy / 1000 / 60);
            long secondsPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonEasy / 1000 - (minutesPlayedNonagon * 60));
            numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleNonagonEasy;
            fastestVictoryNonagonString = FASTEST_VICTORY + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
            numWinsNonagonString = NUM_WINS + game.stats.numTimesWonBattleNonagonEasy;
            numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostBattleNonagonEasy;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            long minutesPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareMedium / 1000 / 60);
            long secondsPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareMedium / 1000 - (minutesPlayedSquare * 60));
            numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSquareMedium;
            fastestVictorySquareString = FASTEST_VICTORY + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;
            numWinsSquareString = NUM_WINS + game.stats.numTimesWonBattleSquareMedium;
            numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostBattleSquareMedium;

            long minutesPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonMedium / 1000 / 60);
            long secondsPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonMedium / 1000 - (minutesPlayedPentagon * 60));
            numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattlePentagonMedium;
            fastestVictoryPentagonString = FASTEST_VICTORY + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;
            numWinsPentagonString = NUM_WINS + game.stats.numTimesWonBattlePentagonMedium;
            numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostBattlePentagonMedium;

            long minutesPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonMedium / 1000 / 60);
            long secondsPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonMedium / 1000 - (minutesPlayedHexagon * 60));
            numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleHexagonMedium;
            fastestVictoryHexagonString = FASTEST_VICTORY + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;
            numWinsHexagonString = NUM_WINS + game.stats.numTimesWonBattleHexagonMedium;
            numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostBattleHexagonMedium;

            long minutesPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonMedium / 1000 / 60);
            long secondsPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonMedium / 1000 - (minutesPlayedSeptagon * 60));
            numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSeptagonMedium;
            fastestVictorySeptagonString = FASTEST_VICTORY + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;
            numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonBattleSeptagonMedium;
            numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostBattleSeptagonMedium;

            long minutesPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonMedium / 1000 / 60);
            long secondsPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonMedium / 1000 - (minutesPlayedOctagon * 60));
            numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleOctagonMedium;
            fastestVictoryOctagonString = FASTEST_VICTORY + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;
            numWinsOctagonString = NUM_WINS + game.stats.numTimesWonBattleOctagonMedium;
            numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostBattleOctagonMedium;

            long minutesPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonMedium / 1000 / 60);
            long secondsPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonMedium / 1000 - (minutesPlayedNonagon * 60));
            numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleNonagonMedium;
            fastestVictoryNonagonString = FASTEST_VICTORY + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
            numWinsNonagonString = NUM_WINS + game.stats.numTimesWonBattleNonagonMedium;
            numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostBattleNonagonMedium;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            long minutesPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareHard / 1000 / 60);
            long secondsPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareHard / 1000 - (minutesPlayedSquare * 60));
            numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSquareHard;
            fastestVictorySquareString = FASTEST_VICTORY + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;
            numWinsSquareString = NUM_WINS + game.stats.numTimesWonBattleSquareHard;
            numLossesSquareString = NUM_LOSSES + game.stats.numTimesLostBattleSquareHard;

            long minutesPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonHard / 1000 / 60);
            long secondsPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonHard / 1000 - (minutesPlayedPentagon * 60));
            numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattlePentagonHard;
            fastestVictoryPentagonString = FASTEST_VICTORY + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;
            numWinsPentagonString = NUM_WINS + game.stats.numTimesWonBattlePentagonHard;
            numLossesPentagonString = NUM_LOSSES + game.stats.numTimesLostBattlePentagonHard;

            long minutesPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonHard / 1000 / 60);
            long secondsPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonHard / 1000 - (minutesPlayedHexagon * 60));
            numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleHexagonHard;
            fastestVictoryHexagonString = FASTEST_VICTORY + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;
            numWinsHexagonString = NUM_WINS + game.stats.numTimesWonBattleHexagonHard;
            numLossesHexagonString = NUM_LOSSES + game.stats.numTimesLostBattleHexagonHard;

            long minutesPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonHard / 1000 / 60);
            long secondsPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonHard / 1000 - (minutesPlayedSeptagon * 60));
            numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSeptagonHard;
            fastestVictorySeptagonString = FASTEST_VICTORY + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;
            numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonBattleSeptagonHard;
            numLossesSeptagonString = NUM_LOSSES + game.stats.numTimesLostBattleSeptagonHard;

            long minutesPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonHard / 1000 / 60);
            long secondsPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonHard / 1000 - (minutesPlayedOctagon * 60));
            numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleOctagonHard;
            fastestVictoryOctagonString = FASTEST_VICTORY + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;
            numWinsOctagonString = NUM_WINS + game.stats.numTimesWonBattleOctagonHard;
            numLossesOctagonString = NUM_LOSSES + game.stats.numTimesLostBattleOctagonHard;

            long minutesPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonHard / 1000 / 60);
            long secondsPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonHard / 1000 - (minutesPlayedNonagon * 60));
            numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleNonagonHard;
            fastestVictoryNonagonString = FASTEST_VICTORY + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
            numWinsNonagonString = NUM_WINS + game.stats.numTimesWonBattleNonagonHard;
            numLossesNonagonString = NUM_LOSSES + game.stats.numTimesLostBattleNonagonHard;
        }
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePromptBattleOne);
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(0));
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(1));
    }
}
