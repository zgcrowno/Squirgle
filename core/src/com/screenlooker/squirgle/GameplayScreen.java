package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen implements Screen, InputProcessor {
    final Squirgle game;

    private static final float SQUIRGLE_RADIUS_DECREMENT = 0.8f;

    private int timeSignature;
    private float initPromptRadius;
    private float backgroundColorListElementRadius;
    private float promptIncrease;
    private float endLineWidthIncrease;
    private float backgroundColorShapeListMaxHeight;
    private float backgroundColorShapeListMinHeight;
    private Shape promptShape;
    private Shape outsideTargetShape;
    private List<Shape> priorShapeList;
    private List<Shape> targetShapeList;
    private List<Shape> backgroundColorShapeList;
    private List<Shape> touchDownShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShape;
    private int targetShapesMatched;
    private Vector2 inputPointSpawn;
    private Vector2 inputLineSpawn;
    private Vector2 inputTriangleSpawn;
    private Vector2 inputSquareSpawn;
    private Vector2 inputPentagonSpawn;
    private Vector2 inputHexagonSpawn;
    private Vector2 inputSeptagonSpawn;
    private Vector2 inputOctagonSpawn;
    private Vector2 inputNonagonSpawn;
    private Vector2 inputPlaySpawn;
    private Vector2 inputHomeSpawn;
    private Vector2 inputExitSpawn;
    private Vector3 touchPoint;
    boolean pointTouched;
    boolean lineTouched;
    boolean triangleTouched;
    boolean squareTouched;
    boolean pentagonTouched;
    boolean hexagonTouched;
    boolean septagonTouched;
    boolean octagonTouched;
    boolean nonagonTouched;
    boolean playTouched;
    boolean homeTouched;
    boolean exitTouched;
    boolean inputTouchedGameplay;
    boolean inputTouchedResults;
    private Color clearColor;
    private int score;
    private boolean gameOver;
    private boolean showResults;
    private int multiplier;
    private long startTime;
    private long endTime;
    private int destructionIndex;
    private float firstPriorShapePreviousX;
    private Color resultsColor;

    public GameplayScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);
        timeSignature = 4; //This represents the number of quarter notes per background color change (4 = 4/4, 5 = 5/4, etc.)
        initPromptRadius = 20;
        backgroundColorListElementRadius = 15;
        promptIncrease = 1.0005f;
        endLineWidthIncrease = 2;
        backgroundColorShapeListMaxHeight = (game.camera.viewportHeight - (Draw.INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7);
        backgroundColorShapeListMinHeight = game.camera.viewportHeight - (Draw.INPUT_RADIUS / 2);
        promptShape = new Shape(MathUtils.random(Shape.NONAGON),
                initPromptRadius, Color.BLACK,
                null,
                initPromptRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        outsideTargetShape = new Shape(MathUtils.random(Shape.NONAGON),
                Draw.INPUT_RADIUS,
                Color.BLACK, null,
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(Draw.TARGET_RADIUS / 2.5f,
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2.5f)));
        priorShapeList = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
        backgroundColorShapeList = new ArrayList<Shape>();
        touchDownShapeList = new ArrayList<Shape>();
        targetShapeList.add(new Shape(MathUtils.random(Shape.NONAGON),
                0, Color.WHITE,
                null,
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(Draw.TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
        targetShapeList.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(Draw.TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
        if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                outsideTargetShape.setShape(MathUtils.random(Shape.NONAGON));
            }
        }
        for (int i = 0; i <= 6; i++) {
            if (i == 0) {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        backgroundColorListElementRadius,
                        Color.WHITE,
                        ColorUtils.randomPrimary(),
                        backgroundColorListElementRadius / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(Draw.TARGET_RADIUS + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7),
                                backgroundColorShapeListMaxHeight)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        backgroundColorListElementRadius,
                        Color.WHITE,
                        ColorUtils.randomPrimary(),
                        backgroundColorListElementRadius / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(Draw.TARGET_RADIUS + (i * ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7)),
                                backgroundColorShapeListMinHeight)));
            }
        }
        backgroundColorShape = new Shape(Shape.randomBackgroundColorShape(),
                game.camera.viewportWidth / 2,
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));
        currentTargetShape = targetShapeList.get(0);
        targetShapesMatched = 0;
        inputPointSpawn = new Vector2(game.camera.viewportWidth / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputLineSpawn = new Vector2((2 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputTriangleSpawn = new Vector2((3 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputSquareSpawn = new Vector2((4 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputPentagonSpawn = new Vector2((5 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputHexagonSpawn = new Vector2((6 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputSeptagonSpawn = new Vector2((7 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputOctagonSpawn = new Vector2((8 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputNonagonSpawn = new Vector2((9 * game.camera.viewportWidth) / 10, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputPlaySpawn = new Vector2(game.camera.viewportWidth / 4, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputHomeSpawn = new Vector2((2 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputExitSpawn = new Vector2((3 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        touchPoint = new Vector3();
        pointTouched = false;
        lineTouched = false;
        triangleTouched = false;
        squareTouched = false;
        playTouched = false;
        homeTouched = false;
        exitTouched = false;
        inputTouchedGameplay = false;
        inputTouchedResults = false;
        clearColor = new Color();
        score = 0;
        multiplier = 1;
        gameOver = false;
        showResults = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
        destructionIndex = 1;
        firstPriorShapePreviousX = 0;
        resultsColor = new Color();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);

        float primaryShapeThreshold = game.camera.viewportWidth * game.draw.THRESHOLD_MULTIPLIER;
        Shape primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        boolean primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        promptShape.setRadius(promptShape.getRadius() * promptIncrease);

        if (!primaryShapeAtThreshold) {
            promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
        }

        if (!gameOver) {
            game.draw.drawBackgroundColorShape(backgroundColorShape, game.shapeRendererFilled);
        }

        game.draw.drawPrompt(promptShape, priorShapeList, primaryShapeAtThreshold, game.shapeRendererLine);

        game.draw.drawShapes(priorShapeList, promptShape, primaryShapeAtThreshold, game, game.shapeRendererFilled);

        //TODO: Make sure you execute the inverse of this given certain player input...Also adjust all of this
        //TODO: so that the promptIncrease, colorListSpeed and colorSpeed are all increasing by proportional rates.
        if (!gameOver) {
            if ((System.currentTimeMillis() - startTime) / 1000 > 10) {
                promptIncrease += .0005;
                startTime = System.currentTimeMillis();
                game.draw.setColorListSpeed(game.draw.getColorListSpeed() + 0.1f);
                game.draw.setColorSpeed(game.draw.getColorSpeed() + 20);
            }
        } else {
            if ((System.currentTimeMillis() - endTime) / 1000 > 2) {
                if (!primaryShapeAtThreshold) { //TODO: Also account for height (different screen orientations?)
                    promptIncrease += .0001;
                    promptShape.setCoordinates(new Vector2(promptShape.getCoordinates().x - (primaryShape.getCoordinates().x - firstPriorShapePreviousX),
                            promptShape.getCoordinates().y));
                } else {
                    promptIncrease = 1;
                    if (primaryShape.getShape() == Shape.LINE && primaryShape.getLineWidth() < (game.camera.viewportWidth * 4)) {
                        primaryShape.setLineWidth(primaryShape.getLineWidth() * endLineWidthIncrease);
                    } else if (primaryShape.getShape() == Shape.LINE) {
                        //Prevent shape lines from being visible in the event that primaryShape is promptShape
                        primaryShape.setColor(clearColor);
                    }
                    if (primaryShape.getShape() != Shape.LINE || primaryShape.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                        showResults = true;
                    }
                }
            }
        }

        //This code is being executed twice: once before setting the prompt's end game coordinates, and again afterwards.
        //This way, the shapes are drawn with their new values, and the first element in priorShapeList doesn't veer off
        //the screen to the right.
        //TODO: separate draw methods out into distinct ones, one of which assigns radii and coordinates, and the other of
        //TODO: which actually draws the shapes. It's overkill to draw the shapes multiple times.
        game.draw.drawShapes(priorShapeList, promptShape, primaryShapeAtThreshold, game, game.shapeRendererFilled);

        if (!gameOver) {
            game.draw.drawBackgroundColorShapeList(backgroundColorShapeList, backgroundColorShape, clearColor, game.shapeRendererFilled);
            SoundUtils.playMusic(timeSignature,
                    backgroundColorShapeListMaxHeight - backgroundColorShapeListMinHeight,
                    game.draw.getColorListSpeed(),
                    backgroundColorShapeList,
                    game);
            game.draw.drawInputButtons(inputPointSpawn, inputLineSpawn, inputTriangleSpawn, inputSquareSpawn, inputPentagonSpawn, inputHexagonSpawn, inputSeptagonSpawn, inputOctagonSpawn, inputNonagonSpawn, game.shapeRendererFilled);
            game.draw.drawTargetSemicircle(game.shapeRendererFilled);
            game.draw.drawScoreTriangle(game.shapeRendererFilled);
            game.draw.drawPrompt(outsideTargetShape, targetShapeList, false, game.shapeRendererFilled);
            game.draw.drawShapes(targetShapeList, outsideTargetShape, false, game, game.shapeRendererFilled);
        }

        //Prevent shapes from getting too large
        if (promptShape.getRadius() >= game.camera.viewportWidth * 15) {
            if (priorShapeList.size() > destructionIndex) {
                promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                for (int i = 0; i < destructionIndex; i++) {
                    priorShapeList.remove(priorShapeList.size() - 1);
                }
                destructionIndex = 2;
            } else {
                //TODO: Account for game ending with empty priorShapeList
            }
        }

        if (priorShapeList.size() > 0) {
            firstPriorShapePreviousX = priorShapeList.get(0).getCoordinates().x;
        } else {
            firstPriorShapePreviousX = promptShape.getCoordinates().x;
        }

        if (showResults) {
            game.draw.drawResultsInputButtons(inputPlaySpawn, inputHomeSpawn, inputExitSpawn, game.shapeRendererFilled);
        }

        game.draw.drawTouchDownPoints(touchDownShapeList, game.shapeRendererLine);

        game.shapeRendererFilled.end();

        game.shapeRendererLine.end();

        if (!gameOver) {
            FontUtils.printText(game.batch,
                    game.font,
                    game.layout,
                    backgroundColorShape.getColor(),
                    String.valueOf(score),
                    game.camera.viewportWidth - (Draw.TARGET_RADIUS / 3.2f),
                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 3.2f),
                    -45);
            FontUtils.printText(game.batch,
                    game.font,
                    game.layout,
                    Color.WHITE,
                    "X" + String.valueOf(multiplier),
                    game.camera.viewportWidth - (Draw.TARGET_RADIUS / 2.58f),
                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 1.25f),
                    -45);
        }

        if (showResults) {
            if (priorShapeList.size() > 0 && (priorShapeList.get(0).getShape() == Shape.LINE || priorShapeList.get(0).getShape() == Shape.POINT)) {
                resultsColor = Color.BLACK;
            } else {
                resultsColor = Color.WHITE;
            }
            FontUtils.printText(game.batch,
                    game.font,
                    game.layout,
                    resultsColor,
                    String.valueOf(score),
                    game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    0);
        }

        //Game over condition
        if ((promptShape.getRadius() >= game.camera.viewportWidth / 2 || promptShape.getRadius() >= game.camera.viewportHeight / 2) && !gameOver) {
            gameOver = true;
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
        }

        currentTargetShape.setColor(ColorUtils.randomPrimary());
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

        pointTouched = touchPoint.x > inputPointSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPointSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPointSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPointSpawn.y + Draw.INPUT_RADIUS;
        lineTouched = touchPoint.x > inputLineSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputLineSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputLineSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputLineSpawn.y + Draw.INPUT_RADIUS;
        triangleTouched = touchPoint.x > inputTriangleSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputTriangleSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputTriangleSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputTriangleSpawn.y + Draw.INPUT_RADIUS;
        squareTouched = touchPoint.x > inputSquareSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSquareSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSquareSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSquareSpawn.y + Draw.INPUT_RADIUS;
        pentagonTouched = touchPoint.x > inputPentagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPentagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPentagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPentagonSpawn.y + Draw.INPUT_RADIUS;
        hexagonTouched = touchPoint.x > inputHexagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHexagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHexagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHexagonSpawn.y + Draw.INPUT_RADIUS;
        septagonTouched = touchPoint.x > inputSeptagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSeptagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSeptagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSeptagonSpawn.y + Draw.INPUT_RADIUS;
        octagonTouched = touchPoint.x > inputOctagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputOctagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputOctagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputOctagonSpawn.y + Draw.INPUT_RADIUS;
        nonagonTouched = touchPoint.x > inputNonagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputNonagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputNonagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputNonagonSpawn.y + Draw.INPUT_RADIUS;
        playTouched = touchPoint.x > inputPlaySpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPlaySpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPlaySpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPlaySpawn.y + Draw.INPUT_RADIUS;
        homeTouched = touchPoint.x > inputHomeSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHomeSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHomeSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHomeSpawn.y + Draw.INPUT_RADIUS;
        exitTouched = touchPoint.x > inputExitSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputExitSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputExitSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputExitSpawn.y + Draw.INPUT_RADIUS;

        for (int i = 1; i < 20; i++) {
            if (!gameOver) {
                if (pointTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputPointSpawn.x,
                                    inputPointSpawn.y)));
                } else if (lineTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputLineSpawn.x,
                                    inputTriangleSpawn.y)));
                } else if (triangleTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputTriangleSpawn.x,
                                    inputTriangleSpawn.y)));
                } else if (squareTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputSquareSpawn.x,
                                    inputSquareSpawn.y)));
                } else if(pentagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputPentagonSpawn.x,
                                    inputPentagonSpawn.y)));
                } else if(hexagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputHexagonSpawn.x,
                                    inputHexagonSpawn.y)));
                } else if(septagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputSeptagonSpawn.x,
                                    inputSeptagonSpawn.y)));
                } else if(octagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputOctagonSpawn.x,
                                    inputOctagonSpawn.y)));
                } else if(nonagonTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputNonagonSpawn.x,
                                    inputNonagonSpawn.y)));
                }
            } else if (showResults) {
                if (playTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputPlaySpawn.x,
                                    inputPlaySpawn.y)));
                } else if (homeTouched) {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i, ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputHomeSpawn.x,
                                    inputHomeSpawn.y)));
                } else {
                    touchDownShapeList.add(new Shape(Shape.POINT,
                            i,
                            ColorUtils.randomPrimary(),
                            null,
                            1,
                            new Vector2(inputExitSpawn.x,
                                    inputExitSpawn.y)));
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        pointTouched = touchPoint.x > inputPointSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPointSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPointSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPointSpawn.y + Draw.INPUT_RADIUS;
        lineTouched = touchPoint.x > inputLineSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputLineSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputLineSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputLineSpawn.y + Draw.INPUT_RADIUS;
        triangleTouched = touchPoint.x > inputTriangleSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputTriangleSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputTriangleSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputTriangleSpawn.y + Draw.INPUT_RADIUS;
        squareTouched = touchPoint.x > inputSquareSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSquareSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSquareSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSquareSpawn.y + Draw.INPUT_RADIUS;
        pentagonTouched = touchPoint.x > inputPentagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPentagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPentagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPentagonSpawn.y + Draw.INPUT_RADIUS;
        hexagonTouched = touchPoint.x > inputHexagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHexagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHexagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHexagonSpawn.y + Draw.INPUT_RADIUS;
        septagonTouched = touchPoint.x > inputSeptagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputSeptagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputSeptagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputSeptagonSpawn.y + Draw.INPUT_RADIUS;
        octagonTouched = touchPoint.x > inputOctagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputOctagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputOctagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputOctagonSpawn.y + Draw.INPUT_RADIUS;
        nonagonTouched = touchPoint.x > inputNonagonSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputNonagonSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputNonagonSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputNonagonSpawn.y + Draw.INPUT_RADIUS;
        playTouched = touchPoint.x > inputPlaySpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputPlaySpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputPlaySpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputPlaySpawn.y + Draw.INPUT_RADIUS;
        homeTouched = touchPoint.x > inputHomeSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputHomeSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputHomeSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputHomeSpawn.y + Draw.INPUT_RADIUS;
        exitTouched = touchPoint.x > inputExitSpawn.x - Draw.INPUT_RADIUS
                && touchPoint.x < inputExitSpawn.x + Draw.INPUT_RADIUS
                && touchPoint.y > inputExitSpawn.y - Draw.INPUT_RADIUS
                && touchPoint.y < inputExitSpawn.y + Draw.INPUT_RADIUS;
        inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        inputTouchedResults = playTouched || homeTouched || exitTouched;

        if (!gameOver) {
            if (pointTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.LINE);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.SQUARE);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.NONAGON);
                } else {
                    promptShape.setShape(Shape.POINT);
                }
            } else if (lineTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.SQUARE);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.NONAGON);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.POINT);
                } else {
                    promptShape.setShape(Shape.LINE);
                }
            } else if (triangleTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.SQUARE);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.NONAGON);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.POINT);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.LINE);
                } else {
                    promptShape.setShape(Shape.TRIANGLE);
                }
            } else if (squareTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.NONAGON);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.POINT);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.LINE);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else {
                    promptShape.setShape(Shape.SQUARE);
                }
            } else if(pentagonTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.NONAGON);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.POINT);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.LINE);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.SQUARE);
                } else {
                    promptShape.setShape(Shape.PENTAGON);
                }
            } else if(hexagonTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.NONAGON);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.POINT);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.LINE);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.SQUARE);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.PENTAGON);
                } else {
                    promptShape.setShape(Shape.HEXAGON);
                }
            } else if(septagonTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.OCTAGON);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.NONAGON);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.POINT);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.LINE);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.SQUARE);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.HEXAGON);
                } else {
                    promptShape.setShape(Shape.SEPTAGON);
                }
            } else if(octagonTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.NONAGON);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.POINT);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.LINE);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.SQUARE);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else {
                    promptShape.setShape(Shape.OCTAGON);
                }
            } else if(nonagonTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.POINT);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.LINE);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if(promptShape.getShape() == Shape.SQUARE) {
                    promptShape.setShape(Shape.SQUARE);
                } else if(promptShape.getShape() == Shape.PENTAGON) {
                    promptShape.setShape(Shape.PENTAGON);
                } else if(promptShape.getShape() == Shape.HEXAGON) {
                    promptShape.setShape(Shape.HEXAGON);
                } else if(promptShape.getShape() == Shape.SEPTAGON) {
                    promptShape.setShape(Shape.SEPTAGON);
                } else if(promptShape.getShape() == Shape.OCTAGON) {
                    promptShape.setShape(Shape.OCTAGON);
                } else {
                    promptShape.setShape(Shape.NONAGON);
                }
            }
        }
        if (inputTouchedGameplay && !gameOver && promptShape.getShape() == currentTargetShape.getShape()) {
            System.out.println(promptShape.getShape());
            System.out.println(currentTargetShape.getShape());
            targetShapesMatched++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShape.getRadius(),
                    Color.BLACK,
                    null,
                    promptShape.getLineWidth(),
                    promptShape.getCoordinates());
            Shape promptShapeToAdd = new Shape(promptShape.getShape(),
                    promptShape.getRadius(),
                    backgroundColorShape.getColor(),
                    null,
                    promptShape.getLineWidth(),
                    promptShape.getCoordinates());
            if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
                promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
            }
            promptShape.setShape(MathUtils.random(Shape.NONAGON));
            priorShapeList.add(promptShapeToAdd);
            priorShapeList.add(circleContainer);
            if (targetShapesMatched == 1) {
                currentTargetShape = outsideTargetShape;
            } else {
                targetShapesMatched = 0;
                score += multiplier;
                if (multiplier < 5) {
                    multiplier++;
                }
                targetShapeList.clear();
                if (priorShapeList.get(priorShapeList.size() - 4).getColor() == priorShapeList.get(priorShapeList.size() - 2).getColor()) {
                    //SQUIRGLE!!!
                    outsideTargetShape.setShape(Shape.TRIANGLE);
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 2.5f,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2.5f))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 3,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
                } else {
                    outsideTargetShape.setShape(MathUtils.random(Shape.NONAGON));
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(MathUtils.random(Shape.NONAGON),
                            0,
                            Color.WHITE,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 2.5f,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2.5f))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            Draw.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(Draw.TARGET_RADIUS / 3,
                                    game.camera.viewportHeight - (Draw.TARGET_RADIUS / 2))));
                    if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
                        while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                            outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeList.get(priorShapeList.size() - 4).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - 2).getShape() == Shape.TRIANGLE) {
                    promptShape.setRadius(promptShape.getRadius() * SQUIRGLE_RADIUS_DECREMENT);
                }
                currentTargetShape = targetShapeList.get(0);
            }
        } else {
            //The wrong shape was touched
            //TODO: Add wrong shape penalization here.
            multiplier = 1;
        }
        if (inputTouchedResults && showResults) {
            if (playTouched) {
                game.setScreen(new GameplayScreen(game));
            } else if (homeTouched) {
                game.setScreen(new MainMenuScreen(game));
            } else {
                dispose();
                System.exit(0);
            }
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

}
