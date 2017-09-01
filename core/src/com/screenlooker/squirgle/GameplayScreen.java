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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen implements Screen, InputProcessor {
    final Squirgle game;

    private float initPromptRadius;
    private float backgroundColorListElementRadius;
    private float promptIncrease;
    private Shape promptShape;
    private Shape outsideTargetShape;
    private List<Shape> priorShapeList;
    private List<Shape> targetShapeList;
    private List<Shape> backgroundColorShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShape;
    private int targetShapesMatched;
    private Vector2 inputPointSpawn;
    private Vector2 inputLineSpawn;
    private Vector2 inputTriangleSpawn;
    private Vector2 inputSquareSpawn;
    private Vector3 touchPoint;
    boolean pointTouched;
    boolean lineTouched;
    boolean triangleTouched;
    boolean squareTouched;
    boolean inputTouched;
    private Color clearColor;
    private int score;
    private boolean gameOver;
    private int multiplier;
    private long startTime;
    private long endTime;
    private int destructionIndex;

    public GameplayScreen(final Squirgle game) {
        this.game = game;

        Gdx.input.setInputProcessor(this);
        initPromptRadius = 20;
        backgroundColorListElementRadius = 15;
        promptIncrease = 1.0005f;
        promptShape = new Shape(MathUtils.random(Shape.SQUARE), initPromptRadius, Color.BLACK, null, initPromptRadius / 8, new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        outsideTargetShape = new Shape(MathUtils.random(Shape.SQUARE), Draw.INPUT_RADIUS, Color.BLACK, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 2.5f, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2.5f)));
        priorShapeList = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
        backgroundColorShapeList = new ArrayList<Shape>();
        targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
        targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
        if(targetShapeList.get(0).getShape() == Shape.SQUARE) {
            while(outsideTargetShape.getShape() == Shape.TRIANGLE) {
                outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
            }
        }
        for(int i = 0; i <= 6; i++) {
            if(i == 0) {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        backgroundColorListElementRadius,
                        Color.WHITE,
                        ColorUtils.randomPrimary(),
                        backgroundColorListElementRadius / 8,
                        new Vector2(Draw.TARGET_RADIUS + ((Gdx.graphics.getWidth() - (Draw.TARGET_RADIUS * 2)) / 7),
                                (Gdx.graphics.getHeight() - (Draw.INPUT_RADIUS / 2)) + ((Gdx.graphics.getWidth() - (Draw.TARGET_RADIUS * 2)) / 7))));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        backgroundColorListElementRadius,
                        Color.WHITE,
                        ColorUtils.randomPrimary(),
                        backgroundColorListElementRadius / 8,
                        new Vector2(Draw.TARGET_RADIUS + (i * ((Gdx.graphics.getWidth() - (Draw.TARGET_RADIUS * 2)) / 7)),
                                Gdx.graphics.getHeight() - (Draw.INPUT_RADIUS / 2))));
            }
        }
        backgroundColorShape = new Shape(Shape.randomBackgroundColorShape(),
                Gdx.graphics.getWidth() / 2,
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                Draw.INPUT_RADIUS / 8,
                new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() + (Gdx.graphics.getWidth() / 2)));
        currentTargetShape = targetShapeList.get(0);
        targetShapesMatched = 0;
        inputPointSpawn = new Vector2(Gdx.graphics.getWidth() / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputLineSpawn = new Vector2((2 * Gdx.graphics.getWidth()) / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputTriangleSpawn = new Vector2((3 * Gdx.graphics.getWidth()) / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        inputSquareSpawn = new Vector2((4 * Gdx.graphics.getWidth()) / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
        touchPoint = new Vector3();
        pointTouched = false;
        lineTouched = false;
        triangleTouched = false;
        squareTouched = false;
        inputTouched = false;
        clearColor = new Color();
        score = 0;
        multiplier = 1;
        gameOver = false;
        startTime = System.currentTimeMillis();
        endTime = 0;
        destructionIndex = 1;
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        promptShape.setRadius(promptShape.getRadius() * promptIncrease);

        promptShape.setLineWidth(promptShape.getRadius() / 8);

        game.draw.drawBackgroundColorShape(backgroundColorShape, game.shapeRendererFilled);

        game.draw.drawPrompt(promptShape, priorShapeList, game.shapeRendererLine);

        game.draw.drawShapes(priorShapeList, promptShape, game.shapeRendererFilled);

        //TODO: Make sure you execute the inverse of this given certain player input...Also adjust all of this
        //TODO: so that the promptIncrease, colorListSpeed and colorSpeed are all increasing by proportional rates.
        if(!gameOver) {
            if ((System.currentTimeMillis() - startTime) / 1000 > 10) {
                promptIncrease += .0005;
                startTime = System.currentTimeMillis();
                game.draw.setColorListSpeed(game.draw.getColorListSpeed() + 0.1f);
                game.draw.setColorSpeed(game.draw.getColorSpeed() + 20);
            }
        } else {
            if((System.currentTimeMillis() - endTime) / 1000 > 2) {
                if(priorShapeList.size() > 0) {
                    if(priorShapeList.get(0).getRadius() < (Gdx.graphics.getWidth() * 3)) { //TODO: Also account for height (different screen orientations?)
                        promptIncrease += .0001;
                        System.out.println(priorShapeList.get(0).getCoordinates().x);
                        promptShape.setCoordinates(new Vector2(promptShape.getCoordinates().x - (priorShapeList.get(0).getCoordinates().x - (Gdx.graphics.getWidth() / 2)), promptShape.getCoordinates().y));
                    } else {
                        promptIncrease = 1;
                    }
                } else {
                    //TODO: Account for game ending with empty priorShapeList
                }
            }
        }

        //This code is being executed twice: once before setting the prompt's end game coordinates, and again afterwards.
        //This way, the shapes are drawn with their new values, and the first element in priorShapeList doesn't veer off
        //the screen to the right.
        //TODO: separate draw methods out into distinct ones, one of which assigns radii and coordinates, and the other of
        //TODO: which actually draws the shapes. It's overkill to draw the shapes multiple times.
        game.draw.drawShapes(priorShapeList, promptShape, game.shapeRendererFilled);

        game.draw.drawBackgroundColorShapeList(backgroundColorShapeList, backgroundColorShape, clearColor, game.shapeRendererFilled);

        game.draw.drawInputButtons(inputPointSpawn, inputLineSpawn, inputTriangleSpawn, inputSquareSpawn, game.shapeRendererFilled);

        game.draw.drawTargetSemicircle(game.shapeRendererFilled);

        game.draw.drawScoreTriangle(game.shapeRendererFilled);

        game.draw.drawPrompt(outsideTargetShape, targetShapeList, game.shapeRendererFilled);

        game.draw.drawShapes(targetShapeList, outsideTargetShape, game.shapeRendererFilled);

        //Prevent shapes from getting too large
        if(promptShape.getRadius() >= Gdx.graphics.getWidth() * 10) {
            if(priorShapeList.size() > destructionIndex) {
                promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                for(int i = 0; i < destructionIndex; i++) {
                    priorShapeList.remove(priorShapeList.size() - 1);
                }
                destructionIndex = 2;
            } else {
                //TODO: Account for game ending with empty priorShapeList
            }
        }

        game.shapeRendererFilled.end();

        game.shapeRendererLine.end();

        FontUtils.printText(game.batch, game.font, game.layout, backgroundColorShape.getColor(), String.valueOf(score), Gdx.graphics.getWidth() - (Draw.TARGET_RADIUS / 3.2f), Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 3.2f), -45);
        FontUtils.printText(game.batch, game.font, game.layout, Color.WHITE, "X" + String.valueOf(multiplier), Gdx.graphics.getWidth() - (Draw.TARGET_RADIUS / 2.58f), Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 1.25f), -45);

        //Game over condition
        if((promptShape.getRadius() >= Gdx.graphics.getWidth() / 2 || promptShape.getRadius() >= Gdx.graphics.getHeight() / 2) && !gameOver) {
            gameOver = true;
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
        }

        currentTargetShape.setColor(ColorUtils.randomPrimary());
    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void dispose () {

    }

    @Override public boolean mouseMoved (int screenX, int screenY) {
        return false;
    }

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        if(!gameOver) {
            game.camera.unproject(touchPoint.set(screenX, screenY, 0));

            pointTouched = screenX > inputPointSpawn.x - Draw.INPUT_RADIUS
                    && screenX < inputPointSpawn.x + Draw.INPUT_RADIUS
                    && screenY > Gdx.graphics.getHeight() - inputPointSpawn.y - Draw.INPUT_RADIUS
                    && screenY < Gdx.graphics.getHeight() - inputPointSpawn.y + Draw.INPUT_RADIUS;
            lineTouched = screenX > inputLineSpawn.x - Draw.INPUT_RADIUS
                    && screenX < inputLineSpawn.x + Draw.INPUT_RADIUS
                    && screenY > Gdx.graphics.getHeight() - inputLineSpawn.y - Draw.INPUT_RADIUS
                    && screenY < Gdx.graphics.getHeight() - inputLineSpawn.y + Draw.INPUT_RADIUS;
            triangleTouched = screenX > inputTriangleSpawn.x - Draw.INPUT_RADIUS
                    && screenX < inputTriangleSpawn.x + Draw.INPUT_RADIUS
                    && screenY > Gdx.graphics.getHeight() - inputTriangleSpawn.y - Draw.INPUT_RADIUS
                    && screenY < Gdx.graphics.getHeight() - inputTriangleSpawn.y + Draw.INPUT_RADIUS;
            squareTouched = screenX > inputSquareSpawn.x - Draw.INPUT_RADIUS
                    && screenX < inputSquareSpawn.x + Draw.INPUT_RADIUS
                    && screenY > Gdx.graphics.getHeight() - inputSquareSpawn.y - Draw.INPUT_RADIUS
                    && screenY < Gdx.graphics.getHeight() - inputSquareSpawn.y + Draw.INPUT_RADIUS;
            inputTouched = pointTouched || lineTouched || triangleTouched || squareTouched;

            if (pointTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.LINE);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.SQUARE);
                } else {
                    promptShape.setShape(Shape.POINT);
                }
            } else if (lineTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.SQUARE);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.POINT);
                } else {
                    promptShape.setShape(Shape.LINE);
                }
            } else if (triangleTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.SQUARE);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.POINT);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.LINE);
                } else {
                    promptShape.setShape(Shape.TRIANGLE);
                }
            } else if (squareTouched) {
                if (promptShape.getShape() == Shape.POINT) {
                    promptShape.setShape(Shape.POINT);
                } else if (promptShape.getShape() == Shape.LINE) {
                    promptShape.setShape(Shape.LINE);
                } else if (promptShape.getShape() == Shape.TRIANGLE) {
                    promptShape.setShape(Shape.TRIANGLE);
                } else {
                    promptShape.setShape(Shape.SQUARE);
                }
            }
            if (inputTouched && promptShape.getShape() == currentTargetShape.getShape()) {
                targetShapesMatched++;
                Shape circleContainer = new Shape(Shape.CIRCLE, promptShape.getRadius(), Color.BLACK, null, promptShape.getLineWidth(), promptShape.getCoordinates());
                Shape promptShapeToAdd = new Shape(promptShape.getShape(), promptShape.getRadius(), backgroundColorShape.getColor(), null, promptShape.getLineWidth(), promptShape.getCoordinates());
                if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
                    promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
                }
                promptShape.setShape(MathUtils.random(Shape.SQUARE));
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
                        targetShapeList.add(new Shape(Shape.SQUARE, 0, Color.WHITE, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 2.5f, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2.5f))));
                        targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
                    } else {
                        outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                        outsideTargetShape.setColor(Color.BLACK);
                        targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 2.5f, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2.5f))));
                        targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
                        if (targetShapeList.get(0).getShape() == Shape.SQUARE) {
                            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                                outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                            }
                        }
                    }
                    if (priorShapeList.get(priorShapeList.size() - 4).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - 2).getShape() == Shape.TRIANGLE) {
                        promptShape.setRadius(promptShape.getRadius() * 0.8f);
                    }
                    currentTargetShape = targetShapeList.get(0);
                }
            } else {
                //The wrong shape was touched
                multiplier = 1;
            }
        }
        return true;
    }

    @Override public boolean touchDragged (int screenX, int screenY, int pointer) {
        return false;
    }

    @Override public boolean keyDown (int keycode) {
        return false;
    }

    @Override public boolean keyUp (int keycode) {
        return false;
    }

    @Override public boolean keyTyped (char character) {
        return false;
    }

    @Override public boolean scrolled (int amount) {
        return false;
    }

}
