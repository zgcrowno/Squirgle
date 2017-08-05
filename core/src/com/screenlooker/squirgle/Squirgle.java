package com.screenlooker.squirgle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Squirgle extends ApplicationAdapter {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Draw draw;
	private float inputDistanceOffset;
	private float promptSize;
	private int promptShape;
	private int inputRadius;
	private List<Integer> promptShapeList;
	private List<Double> promptSizeList;
	private List<Color> promptColorList;
	private Vector2 promptShapeSpawn;
	private Vector2 inputPointSpawn;
	private Vector2 inputLineSpawn;
	private Vector2 inputTriangleSpawn;
	private Vector2 inputSquareSpawn;
	private int point;
	private int line;
	private int triangle;
	private int square;
	private int red;
	private int blue;
	private int green;
	private int yellow;
	private int magenta;
	private int cyan;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 768, 1024);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		draw = new Draw(Gdx.graphics.getWidth());
		inputDistanceOffset = (float) 1.5;
		promptSize = 10;
		promptShape = MathUtils.random(3);
		inputRadius = 50;
		promptShapeList = new ArrayList<Integer>();
		promptSizeList = new ArrayList<Double>();
		promptColorList = new ArrayList<Color>();
		promptShapeSpawn = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		inputPointSpawn = new Vector2(Gdx.graphics.getWidth() / 5, (inputDistanceOffset * inputRadius));
		inputLineSpawn = new Vector2((2 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		inputTriangleSpawn = new Vector2((3 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		inputSquareSpawn = new Vector2((4 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		point = 0;
		line = 1;
		triangle = 2;
		square = 3;
		red = 0;
		blue = 1;
		green = 2;
		yellow = 3;
		magenta = 4;
		cyan = 5;
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		draw.drawPrompt(promptShapeSpawn.x, promptShapeSpawn.y, promptShape, promptSize, Color.BLACK, shapeRenderer);

		draw.drawInputButtons(inputRadius, shapeRenderer);

		shapeRenderer.end();

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
}
