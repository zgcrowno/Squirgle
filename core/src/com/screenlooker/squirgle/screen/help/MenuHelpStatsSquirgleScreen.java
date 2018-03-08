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

public class MenuHelpStatsSquirgleScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int STATS = 0;
    private final static int BACK = 1;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;
    private final static int NUM_STATS_ELEMENTS = 6;
    private final static int NUM_STATS_SUB_ELEMENTS = 3;

    private final static float FONT_STATS_SIZE_DIVISOR = 55.5f;

    private final static String TIME_PLAYED = "TIME PLAYED: ";
    private final static String NUM_SQUIRGLES = "SQUIRGLES: ";
    private final static String LONGEST_RUN = "LONGEST RUN: ";
    private final static String HIGHEST_SCORE = "HIGHEST SCORE: ";
    private final static String FAVORITE_BASE = "FAVORITE BASE: ";
    private final static String FAVORITE_MODE = "FAVORITE MODE: ";
    private final static String FAVORITE_TRACK = "FAVORITE TRACK: ";
    private final static String HOURS = "H";
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

    private List<Shape> squirgleShapeList;

    private Shape squirglePrompt;

    private boolean backTouched;

    //TODO: Set up fontScore
    public MenuHelpStatsSquirgleScreen(final Squirgle game) {
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

        squirgleShapeList = new ArrayList<Shape>();
        squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));

        squirglePrompt = new Shape(Shape.TRIANGLE,
                symbolRadius / 3,
                triangleColor,
                null,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.partitionSize + (inputWidth / 2), (game.camera.viewportHeight / 4) - squirgleHeightOffset));
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

        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuHelpStatsScreen(game));
            dispose();
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
            case BACK : {
                game.shapeRendererFilled.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawSubElementShapes() {
        game.draw.drawSquare(subElementShapeX,
                squareY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawPentagon(subElementShapeX,
                pentagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawHexagon(subElementShapeX,
                hexagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawSeptagon(subElementShapeX,
                septagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawOctagon(subElementShapeX,
                octagonY,
                subElementShapeRadius,
                subElementShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawNonagon(subElementShapeX,
                nonagonY,
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

    public void drawStatsText() {
        //Square
        String numSquirglesSquareString = NUM_SQUIRGLES + game.stats.numSquirglesSquirgleSquare;
        game.layout.setText(game.fontStats, numSquirglesSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY + ((5 * game.layout.height) / 4),
                0,
                1);
        long minutesPlayedSquare = MathUtils.floor(game.stats.longestRunSquirgleSquare / 1000 / 60);
        long secondsPlayedSquare = MathUtils.floor(game.stats.longestRunSquirgleSquare / 1000 - (minutesPlayedSquare * 60));
        String longestRunSquareString = LONGEST_RUN + minutesPlayedSquare + MINUTES + secondsPlayedSquare + SECONDS;
        game.layout.setText(game.fontStats, longestRunSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                longestRunSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY,
                0,
                1);
        String highestScoreSquareString = HIGHEST_SCORE + game.stats.highestScoreSquirgleSquare;
        game.layout.setText(game.fontStats, highestScoreSquareString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreSquareString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                squareY - ((5 * game.layout.height) / 4),
                0,
                1);

        //Pentagon
        String numSquirglesPentagonString = NUM_SQUIRGLES + game.stats.numSquirglesSquirglePentagon;
        game.layout.setText(game.fontStats, numSquirglesPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY + ((5 * game.layout.height) / 4),
                0,
                1);
        long minutesPlayedPentagon = MathUtils.floor(game.stats.longestRunSquirglePentagon / 1000 / 60);
        long secondsPlayedPentagon = MathUtils.floor(game.stats.longestRunSquirglePentagon / 1000 - (minutesPlayedPentagon * 60));
        String longestRunPentagonString = LONGEST_RUN + minutesPlayedPentagon + MINUTES + secondsPlayedPentagon + SECONDS;
        game.layout.setText(game.fontStats, longestRunPentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                longestRunPentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY,
                0,
                1);
        String highestScorePentagonString = HIGHEST_SCORE + game.stats.highestScoreSquirglePentagon;
        game.layout.setText(game.fontStats, highestScorePentagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScorePentagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                pentagonY - ((5 * game.layout.height) / 4),
                0,
                1);

        //Hexagon
        String numSquirglesHexagonString = NUM_SQUIRGLES + game.stats.numSquirglesSquirgleHexagon;
        game.layout.setText(game.fontStats, numSquirglesHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY + ((5 * game.layout.height) / 4),
                0,
                1);
        long minutesPlayedHexagon = MathUtils.floor(game.stats.longestRunSquirgleHexagon / 1000 / 60);
        long secondsPlayedHexagon = MathUtils.floor(game.stats.longestRunSquirgleHexagon / 1000 - (minutesPlayedHexagon * 60));
        String longestRunHexagonString = LONGEST_RUN + minutesPlayedHexagon + MINUTES + secondsPlayedHexagon + SECONDS;
        game.layout.setText(game.fontStats, longestRunHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                longestRunHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY,
                0,
                1);
        String highestScoreHexagonString = HIGHEST_SCORE + game.stats.highestScoreSquirgleHexagon;
        game.layout.setText(game.fontStats, highestScoreHexagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreHexagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                hexagonY - ((5 * game.layout.height) / 4),
                0,
                1);

        //Septagon
        String numSquirglesSeptagonString = NUM_SQUIRGLES + game.stats.numSquirglesSquirgleSeptagon;
        game.layout.setText(game.fontStats, numSquirglesSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY + ((5 * game.layout.height) / 4),
                0,
                1);
        long minutesPlayedSeptagon = MathUtils.floor(game.stats.longestRunSquirgleSeptagon / 1000 / 60);
        long secondsPlayedSeptagon = MathUtils.floor(game.stats.longestRunSquirgleSeptagon / 1000 - (minutesPlayedSeptagon * 60));
        String longestRunSeptagonString = LONGEST_RUN + minutesPlayedSeptagon + MINUTES + secondsPlayedSeptagon + SECONDS;
        game.layout.setText(game.fontStats, longestRunSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                longestRunSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY,
                0,
                1);
        String highestScoreSeptagonString = HIGHEST_SCORE + game.stats.highestScoreSquirgleSeptagon;
        game.layout.setText(game.fontStats, highestScoreSeptagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreSeptagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                septagonY - ((5 * game.layout.height) / 4),
                0,
                1);

        //Octagon
        String numSquirglesOctagonString = NUM_SQUIRGLES + game.stats.numSquirglesSquirgleOctagon;
        game.layout.setText(game.fontStats, numSquirglesOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY + ((5 * game.layout.height) / 4),
                0,
                1);
        long minutesPlayedOctagon = MathUtils.floor(game.stats.longestRunSquirgleOctagon / 1000 / 60);
        long secondsPlayedOctagon = MathUtils.floor(game.stats.longestRunSquirgleOctagon / 1000 - (minutesPlayedOctagon * 60));
        String longestRunOctagonString = LONGEST_RUN + minutesPlayedOctagon + MINUTES + secondsPlayedOctagon + SECONDS;
        game.layout.setText(game.fontStats, longestRunOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                longestRunOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY,
                0,
                1);
        String highestScoreOctagonString = HIGHEST_SCORE + game.stats.highestScoreSquirgleOctagon;
        game.layout.setText(game.fontStats, highestScoreOctagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreOctagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                octagonY - ((5 * game.layout.height) / 4),
                0,
                1);

        //Nonagon
        String numSquirglesNonagonString = NUM_SQUIRGLES + game.stats.numSquirglesSquirgleNonagon;
        game.layout.setText(game.fontStats, numSquirglesNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                numSquirglesNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY + ((5 * game.layout.height) / 4),
                0,
                1);
        long minutesPlayedNonagon = MathUtils.floor(game.stats.longestRunSquirgleNonagon / 1000 / 60);
        long secondsPlayedNonagon = MathUtils.floor(game.stats.longestRunSquirgleNonagon / 1000 - (minutesPlayedNonagon * 60));
        String longestRunNonagonString = LONGEST_RUN + minutesPlayedNonagon + MINUTES + secondsPlayedNonagon + SECONDS;
        game.layout.setText(game.fontStats, longestRunNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                longestRunNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY,
                0,
                1);
        String highestScoreNonagonString = HIGHEST_SCORE + game.stats.highestScoreSquirgleNonagon;
        game.layout.setText(game.fontStats, highestScoreNonagonString);
        FontUtils.printText(game.batch,
                game.fontStats,
                game.layout,
                Color.BLACK,
                highestScoreNonagonString,
                subElementShapeX + subElementShapeRadius + (game.layout.width / 2),
                nonagonY - ((5 * game.layout.height) / 4),
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

        game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false, game.shapeRendererFilled);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
