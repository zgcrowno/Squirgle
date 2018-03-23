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
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.help.MenuHelpScreen;
import com.screenlooker.squirgle.screen.options.MenuOptionsScreen;
import com.screenlooker.squirgle.screen.type.MenuTypeScreen;
import com.screenlooker.squirgle.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainMenuScreen implements Screen, InputProcessor {

    final Squirgle game;

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
    private Color circleColor;
    private Color triangleColor;
    private Color optionsColor;
    private Color playColor;
    private Color tutorialColor;
    private Color quitColor;

    private List<Shape> squirgleShapeList;

    private Shape squirglePrompt;

    private boolean optionsTouched;
    private boolean playTouched;
    private boolean helpTouched;
    private boolean quitTouched;

    private Button playButton;
    private Button optionsButton;
    private Button helpButton;
    private Button quitButton;

    private List<Button> buttonList;

    public MainMenuScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeight = ((game.camera.viewportHeight / 2) - (game.partitionSize * NUM_PARTITIONS_VERTICAL)) / NUM_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeight ? inputHeight / 2 : inputWidth / 2;

        squirgleRadius = game.camera.viewportHeight / 4;
        squirgleHeightOffset = squirgleRadius / 4;

        touchPoint = new Vector3();

        squareColor = ColorUtils.randomTransitionColor();
        circleColor = ColorUtils.randomTransitionColor();
        triangleColor = ColorUtils.randomTransitionColor();
        while(circleColor.equals(squareColor)) {
            circleColor = ColorUtils.randomTransitionColor();
        }
        while(triangleColor.equals(circleColor) || triangleColor.equals(squareColor)) {
            triangleColor = ColorUtils.randomTransitionColor();
        }
        optionsColor = ColorUtils.randomColor();
        playColor = ColorUtils.randomColor();
        tutorialColor = ColorUtils.randomColor();
        quitColor = ColorUtils.randomColor();

        squirgleShapeList = new ArrayList<Shape>();
        squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));

        squirglePrompt = new Shape(Shape.TRIANGLE,
                squirgleRadius,
                triangleColor,
                null,
                squirgleRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2, ((3 * game.camera.viewportHeight) / 4) - squirgleHeightOffset));

        optionsTouched = false;
        playTouched = false;
        helpTouched = false;
        quitTouched = false;

        playButton = new Button(game.partitionSize,
                (2 * game.partitionSize) + inputHeight,
                inputWidth,
                inputHeight,
                Button.BUTTON_TYPE,
                playColor,
                Color.BLACK,
                game);
        optionsButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeight,
                inputWidth,
                inputHeight,
                Button.BUTTON_OPTIONS,
                optionsColor,
                Color.BLACK,
                game);
        helpButton = new Button(game.partitionSize,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_HELP,
                tutorialColor,
                Color.BLACK,
                game);
        quitButton = new Button((2 * game.partitionSize) + inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeight,
                Button.BUTTON_QUIT,
                quitColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(playButton);
        buttonList.add(optionsButton);
        buttonList.add(helpButton);
        buttonList.add(quitButton);

        playMusic();
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

        game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false);

        transitionSquirgleColors();

        for(Button button : buttonList) {
            button.draw();
        }

        for(Button button : buttonList) {
            button.drawTransitionCircles(this);
        }

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

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }

    public void playMusic() {
        game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).setVolume((float) (game.volume / 10.0));
        game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).play();
    }

}
