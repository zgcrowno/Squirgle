package com.screenlooker.squirgle.screen;

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

//TODO: Alter this class' constructor to allow it to direct to different screens
public class LoadingScreen implements Screen {

    final Squirgle game;

    public final static float FONT_LOADING_SIZE_DIVISOR = 10;
    public final static float FONT_LOADING_CAP_HEIGHT_OFFSET = 3.2f;
    public final static String LOADING = "LOADING";

    private float squirgleRadius;

    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;

    private List<Shape> squirgleShapeList;

    private Shape squirglePrompt;

    public LoadingScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        game.setUpFontLoading(MathUtils.round(game.widthOrHeight / FONT_LOADING_SIZE_DIVISOR));

        squirgleRadius = game.widthOrHeight / 4;

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
                squirgleRadius,
                triangleColor,
                null,
                squirgleRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2, game.camera.viewportHeight / 2));
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

        game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false, game.shapeRendererFilled);
        game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false, game.shapeRendererFilled);

        game.shapeRendererFilled.end();

        transitionSquirgleColors();

        FontUtils.printText(game.batch,
                game.fontLoading,
                game.layout,
                triangleColor,
                LOADING,
                game.camera.viewportWidth / 2,
                (game.camera.viewportHeight / 2) - (squirgleRadius / 2) - (game.fontLoading.getCapHeight() / FONT_LOADING_CAP_HEIGHT_OFFSET),
                0,
                1);

        if(game.manager.update()) {
            game.setScreen(new SplashScreen(game));
        }
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

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
