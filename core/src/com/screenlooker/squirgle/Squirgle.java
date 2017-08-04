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
import com.badlogic.gdx.math.Vector2;

public class Squirgle extends ApplicationAdapter {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Vector2 promptShapeSpawn;
	private Vector2 inputPointSpawn;
	private Vector2 inputLineSpawn;
	private Vector2 inputTriangleSpawn;
	private Vector2 inputSquareSpawn;
	private float inputDistanceOffset = (float) 1.5;
	private float initShapeSize = 10;
	private int inputRadius = 50;
	private int point = 0;
	private int line = 1;
	private int triangle = 2;
	private int square = 3;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 768, 1024);
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		promptShapeSpawn = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		inputPointSpawn = new Vector2(Gdx.graphics.getWidth() / 5, (inputDistanceOffset * inputRadius));
		inputLineSpawn = new Vector2((2 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		inputTriangleSpawn = new Vector2((3 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		inputSquareSpawn = new Vector2((4 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

		//Draw the prompt shape
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(promptShapeSpawn.x, promptShapeSpawn.y, initShapeSize);

		//Draw the input buttons

		//Point
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, inputRadius);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, inputRadius / 4);

		//Line
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y, inputRadius);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rectLine(inputLineSpawn.x, inputLineSpawn.y - (inputRadius / 2), inputLineSpawn.x, inputLineSpawn.y + (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y + (inputRadius / 2), inputRadius / 4);

		//Triangle
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(inputTriangleSpawn.x, inputTriangleSpawn.y, inputRadius);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rectLine(inputTriangleSpawn.x, inputTriangleSpawn.y + (inputRadius / 2), inputTriangleSpawn.x - (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.rectLine(inputTriangleSpawn.x - (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2), inputTriangleSpawn.x + (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.rectLine(inputTriangleSpawn.x, inputTriangleSpawn.y + (inputRadius / 2), inputTriangleSpawn.x + (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputTriangleSpawn.x, inputTriangleSpawn.y + (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputTriangleSpawn.x - (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputTriangleSpawn.x + (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2), inputRadius / 4);

		//Square
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(inputSquareSpawn.x, inputSquareSpawn.y, inputRadius);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rectLine(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);
		shapeRenderer.rectLine(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.rectLine(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.rectLine(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
		shapeRenderer.circle(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);

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
