package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainMenuScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int OPTIONS = 0;
    private final static int PLAY = 1;
    private final static int TUTORIAL = 2;
    private final static int QUIT = 3;

    private final static int NUM_INPUTS_HORIZONTAL = 2;
    private final static int NUM_INPUTS_VERTICAL = 2;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_PARTITIONS_VERTICAL = NUM_INPUTS_VERTICAL + 1;

    private float inputWidth;
    private float inputHeight;

    private float symbolRadius;

    private float squirgleRadius;
    private float squirgleHeightOffset;

    private Vector3 touchPoint;

    private Color squareColor;
    private Color triangleColor;
    private Color optionsColor;
    private Color playColor;
    private Color tutorialColor;
    private Color quitColor;

    private List<Shape> squirgleShapeList;

    private Shape squirglePrompt;

    private boolean optionsTouched;
    private boolean playTouched;
    private boolean tutorialTouched;
    private boolean quitTouched;

    public MainMenuScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = ((game.camera.viewportHeight / 2) - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;

        squirgleRadius = game.camera.viewportHeight / 4;
        squirgleHeightOffset = game.camera.viewportHeight / 16;

        touchPoint = new Vector3();

        squareColor = ColorUtils.randomTransitionColor();
        triangleColor = ColorUtils.randomTransitionColor();
        optionsColor = ColorUtils.randomColor();
        playColor = ColorUtils.randomColor();
        tutorialColor = ColorUtils.randomColor();
        quitColor = ColorUtils.randomColor();

        squirgleShapeList = new ArrayList<Shape>();
        squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, null, 0, new Vector2()));

        squirglePrompt = new Shape(Shape.TRIANGLE,
                squirgleRadius,
                triangleColor,
                null,
                squirgleRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2, ((3 * game.camera.viewportHeight) / 4) - squirgleHeightOffset));

        optionsTouched = false;
        playTouched = false;
        tutorialTouched = false;
        quitTouched = false;
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

        game.draw.drawPrompt(squirglePrompt, squirgleShapeList, null, true, game.shapeRendererFilled);
        game.draw.drawShapes(squirgleShapeList, squirglePrompt, false, game.shapeRendererFilled);

        transitionSquirgleColors();

        drawInputRectangles();

        game.shapeRendererFilled.end();
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

        playTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.partitionSize + inputWidth
                && touchPoint.y > (2 * game.partitionSize) + inputHeight
                && touchPoint.y < (2 * game.partitionSize) + (2 * inputHeight);
        optionsTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > (2 * game.partitionSize) + inputHeight
                && touchPoint.y < (2 * game.partitionSize) + (2 * inputHeight);
        tutorialTouched = touchPoint.x > game.partitionSize
                && touchPoint.x < game.partitionSize + inputWidth
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;
        quitTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + inputHeight;

        if(optionsTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new OptionsScreen(game));
            dispose();
        } else if(playTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new BaseSelectScreen(game));
            dispose();
        } else if(tutorialTouched) {
            game.confirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new TutorialScreen(game));
            dispose();
        } else if(quitTouched) {
            System.exit(0);
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
        drawPlayInput();
        drawOptionsInput();
        drawTutorialInput();
        drawQuitInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case PLAY : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        (2 * game.partitionSize) + inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case OPTIONS : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (2 * game.partitionSize) + inputHeight,
                        inputWidth,
                        inputHeight);
            }
            case TUTORIAL : {
                game.shapeRendererFilled.rect(game.partitionSize,
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
            case QUIT : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeight);
            }
        }
    }

    public void drawPlayInput() {
        drawInputRectangle(PLAY, playColor);
        game.draw.drawPlayButton(game.camera.viewportWidth / 4,
                (3 * game.camera.viewportHeight) / 8,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawOptionsInput() {
        drawInputRectangle(OPTIONS, optionsColor);
        game.draw.drawWrench((3 * game.camera.viewportWidth) / 4,
                (3 * game.camera.viewportHeight) / 8,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                optionsColor,
                game.shapeRendererFilled);
    }

    public void drawTutorialInput() {
        drawInputRectangle(TUTORIAL, tutorialColor);
        game.draw.drawQuestionMark(game.camera.viewportWidth / 4,
                game.camera.viewportHeight / 8,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                tutorialColor,
                game.shapeRendererFilled);
    }

    public void drawQuitInput() {
        drawInputRectangle(QUIT, quitColor);
        game.draw.drawX((3 * game.camera.viewportWidth) / 4,
                game.camera.viewportHeight / 8,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
    }

}
