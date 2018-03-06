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
        subElementShapeRadius = (inputHeight / NUM_STATS_ELEMENTS) / 2;
        squirgleHeightOffset = symbolRadius / 4;

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

    public void drawStatsText() {
        //Square
        game.layout.setText(game.fontStats, numSquirglesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesSquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictorySquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictorySquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSquareString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Pentagon
        game.layout.setText(game.fontStats, numSquirglesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesPentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryPentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsPentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesPentagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (2 * subElementShapeRadius) - (4 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Hexagon
        game.layout.setText(game.fontStats, numSquirglesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesHexagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (4 * subElementShapeRadius) - (4 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Septagon
        game.layout.setText(game.fontStats, numSquirglesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesSeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictorySeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictorySeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsSeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesSeptagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (6 * subElementShapeRadius) - (4 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Octagon
        game.layout.setText(game.fontStats, numSquirglesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesOctagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (8 * subElementShapeRadius) - (4 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);

        //Nonagon
        game.layout.setText(game.fontStats, numSquirglesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1)),
                0,
                1);
        game.layout.setText(game.fontStats, fastestVictoryNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                fastestVictoryNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - (2 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numWinsNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numWinsNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - (3 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
                0,
                1);
        game.layout.setText(game.fontStats, numLossesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numLossesNonagonString,
                (2 * game.partitionSize) + inputWidth + (2 * subElementShapeRadius) + (game.layout.width / 2),
                game.camera.viewportHeight - game.partitionSize - (10 * subElementShapeRadius) - (4 * ((2 * subElementShapeRadius) / (NUM_STATS_SUB_ELEMENTS + 1))),
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

        game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false, game.shapeRendererFilled);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false, game.shapeRendererFilled);
    }

    public void setStatsStrings() {
        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            long minutesPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareEasy / 1000 / 60);
            long secondsPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareEasy / 1000 - (minutesPlayedSquare * 60));
            numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSquareEasy;
            fastestVictorySquareString = FASTEST_VICTORY + game.stats.fastestVictorySquareEasy;
            numWinsSquareString = NUM_WINS + game.stats.numTimesWonBattleSquareEasy;
            numLossesSquareString = NUM_LOSSES + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;

            long minutesPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonEasy / 1000 / 60);
            long secondsPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonEasy / 1000 - (minutesPlayedPentagon * 60));
            numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattlePentagonEasy;
            fastestVictoryPentagonString = FASTEST_VICTORY + game.stats.fastestVictoryPentagonEasy;
            numWinsPentagonString = NUM_WINS + game.stats.numTimesWonBattlePentagonEasy;
            numLossesPentagonString = NUM_LOSSES + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;

            long minutesPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonEasy / 1000 / 60);
            long secondsPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonEasy / 1000 - (minutesPlayedHexagon * 60));
            numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleHexagonEasy;
            fastestVictoryHexagonString = FASTEST_VICTORY + game.stats.fastestVictoryHexagonEasy;
            numWinsHexagonString = NUM_WINS + game.stats.numTimesWonBattleHexagonEasy;
            numLossesHexagonString = NUM_LOSSES + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;

            long minutesPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonEasy / 1000 / 60);
            long secondsPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonEasy / 1000 - (minutesPlayedSeptagon * 60));
            numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSeptagonEasy;
            fastestVictorySeptagonString = FASTEST_VICTORY + game.stats.fastestVictorySeptagonEasy;
            numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonBattleSeptagonEasy;
            numLossesSeptagonString = NUM_LOSSES + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;

            long minutesPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonEasy / 1000 / 60);
            long secondsPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonEasy / 1000 - (minutesPlayedOctagon * 60));
            numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleOctagonEasy;
            fastestVictoryOctagonString = FASTEST_VICTORY + game.stats.fastestVictoryOctagonEasy;
            numWinsOctagonString = NUM_WINS + game.stats.numTimesWonBattleOctagonEasy;
            numLossesOctagonString = NUM_LOSSES + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;

            long minutesPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonEasy / 1000 / 60);
            long secondsPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonEasy / 1000 - (minutesPlayedNonagon * 60));
            numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleNonagonEasy;
            fastestVictoryNonagonString = FASTEST_VICTORY + game.stats.fastestVictoryNonagonEasy;
            numWinsNonagonString = NUM_WINS + game.stats.numTimesWonBattleNonagonEasy;
            numLossesNonagonString = NUM_LOSSES + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            long minutesPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareMedium / 1000 / 60);
            long secondsPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareMedium / 1000 - (minutesPlayedSquare * 60));
            numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSquareMedium;
            fastestVictorySquareString = FASTEST_VICTORY + game.stats.fastestVictorySquareMedium;
            numWinsSquareString = NUM_WINS + game.stats.numTimesWonBattleSquareMedium;
            numLossesSquareString = NUM_LOSSES + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;

            long minutesPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonMedium / 1000 / 60);
            long secondsPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonMedium / 1000 - (minutesPlayedPentagon * 60));
            numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattlePentagonMedium;
            fastestVictoryPentagonString = FASTEST_VICTORY + game.stats.fastestVictoryPentagonMedium;
            numWinsPentagonString = NUM_WINS + game.stats.numTimesWonBattlePentagonMedium;
            numLossesPentagonString = NUM_LOSSES + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;

            long minutesPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonMedium / 1000 / 60);
            long secondsPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonMedium / 1000 - (minutesPlayedHexagon * 60));
            numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleHexagonMedium;
            fastestVictoryHexagonString = FASTEST_VICTORY + game.stats.fastestVictoryHexagonMedium;
            numWinsHexagonString = NUM_WINS + game.stats.numTimesWonBattleHexagonMedium;
            numLossesHexagonString = NUM_LOSSES + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;

            long minutesPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonMedium / 1000 / 60);
            long secondsPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonMedium / 1000 - (minutesPlayedSeptagon * 60));
            numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSeptagonMedium;
            fastestVictorySeptagonString = FASTEST_VICTORY + game.stats.fastestVictorySeptagonMedium;
            numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonBattleSeptagonMedium;
            numLossesSeptagonString = NUM_LOSSES + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;

            long minutesPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonMedium / 1000 / 60);
            long secondsPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonMedium / 1000 - (minutesPlayedOctagon * 60));
            numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleOctagonMedium;
            fastestVictoryOctagonString = FASTEST_VICTORY + game.stats.fastestVictoryOctagonMedium;
            numWinsOctagonString = NUM_WINS + game.stats.numTimesWonBattleOctagonMedium;
            numLossesOctagonString = NUM_LOSSES + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;

            long minutesPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonMedium / 1000 / 60);
            long secondsPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonMedium / 1000 - (minutesPlayedNonagon * 60));
            numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleNonagonMedium;
            fastestVictoryNonagonString = FASTEST_VICTORY + game.stats.fastestVictoryNonagonMedium;
            numWinsNonagonString = NUM_WINS + game.stats.numTimesWonBattleNonagonMedium;
            numLossesNonagonString = NUM_LOSSES + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
            long minutesPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareHard / 1000 / 60);
            long secondsPlayedSquare = MathUtils.floor(game.stats.fastestVictorySquareHard / 1000 - (minutesPlayedSquare * 60));
            numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSquareHard;
            fastestVictorySquareString = FASTEST_VICTORY + game.stats.fastestVictorySquareHard;
            numWinsSquareString = NUM_WINS + game.stats.numTimesWonBattleSquareHard;
            numLossesSquareString = NUM_LOSSES + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;

            long minutesPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonHard / 1000 / 60);
            long secondsPlayedPentagon = MathUtils.floor(game.stats.fastestVictoryPentagonHard / 1000 - (minutesPlayedPentagon * 60));
            numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattlePentagonHard;
            fastestVictoryPentagonString = FASTEST_VICTORY + game.stats.fastestVictoryPentagonHard;
            numWinsPentagonString = NUM_WINS + game.stats.numTimesWonBattlePentagonHard;
            numLossesPentagonString = NUM_LOSSES + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;

            long minutesPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonHard / 1000 / 60);
            long secondsPlayedHexagon = MathUtils.floor(game.stats.fastestVictoryHexagonHard / 1000 - (minutesPlayedHexagon * 60));
            numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleHexagonHard;
            fastestVictoryHexagonString = FASTEST_VICTORY + game.stats.fastestVictoryHexagonHard;
            numWinsHexagonString = NUM_WINS + game.stats.numTimesWonBattleHexagonHard;
            numLossesHexagonString = NUM_LOSSES + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;

            long minutesPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonHard / 1000 / 60);
            long secondsPlayedSeptagon = MathUtils.floor(game.stats.fastestVictorySeptagonHard / 1000 - (minutesPlayedSeptagon * 60));
            numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleSeptagonHard;
            fastestVictorySeptagonString = FASTEST_VICTORY + game.stats.fastestVictorySeptagonHard;
            numWinsSeptagonString = NUM_WINS + game.stats.numTimesWonBattleSeptagonHard;
            numLossesSeptagonString = NUM_LOSSES + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;

            long minutesPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonHard / 1000 / 60);
            long secondsPlayedOctagon = MathUtils.floor(game.stats.fastestVictoryOctagonHard / 1000 - (minutesPlayedOctagon * 60));
            numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleOctagonHard;
            fastestVictoryOctagonString = FASTEST_VICTORY + game.stats.fastestVictoryOctagonHard;
            numWinsOctagonString = NUM_WINS + game.stats.numTimesWonBattleOctagonHard;
            numLossesOctagonString = NUM_LOSSES + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;

            long minutesPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonHard / 1000 / 60);
            long secondsPlayedNonagon = MathUtils.floor(game.stats.fastestVictoryNonagonHard / 1000 - (minutesPlayedNonagon * 60));
            numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesBattleNonagonHard;
            fastestVictoryNonagonString = FASTEST_VICTORY + game.stats.fastestVictoryNonagonHard;
            numWinsNonagonString = NUM_WINS + game.stats.numTimesWonBattleNonagonHard;
            numLossesNonagonString = NUM_LOSSES + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
        }
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePromptBattleOne);
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(0));
        ColorUtils.transitionColor(squirgleShapeListBattleOne.get(1));
    }
}
