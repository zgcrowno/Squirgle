package com.screenlooker.squirgle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class Squirgle extends ApplicationAdapter implements InputProcessor {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Draw draw;
	private float inputDistanceOffset;
	private float promptSize;
	private int promptShape;
	private int inputRadius;
	private List<Shape> priorShapeList;
	private Vector2 promptShapeSpawn;
	private Vector2 inputPointSpawn;
	private Vector2 inputLineSpawn;
	private Vector2 inputTriangleSpawn;
	private Vector2 inputSquareSpawn;
	private Vector3 touchPoint;
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
	boolean pointTouched;
	boolean lineTouched;
	boolean triangleTouched;
	boolean squareTouched;
	boolean dragging;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 768, 1024);
		Gdx.input.setInputProcessor(this);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		draw = new Draw(Gdx.graphics.getWidth());
		inputDistanceOffset = (float) 1.5;
		promptSize = 10;
		promptShape = MathUtils.random(3);
		inputRadius = 50;
		priorShapeList = new ArrayList<Shape>();
		promptShapeSpawn = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		inputPointSpawn = new Vector2(Gdx.graphics.getWidth() / 5, (inputDistanceOffset * inputRadius));
		inputLineSpawn = new Vector2((2 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		inputTriangleSpawn = new Vector2((3 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		inputSquareSpawn = new Vector2((4 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
		touchPoint = new Vector3();
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
		pointTouched = false;
		lineTouched = false;
		triangleTouched = false;
		squareTouched = false;
		dragging = false;
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

	@Override public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		if (button != Input.Buttons.LEFT || pointer > 0) return false;
		camera.unproject(touchPoint.set(screenX, screenY, 0));

		pointTouched = screenX > inputPointSpawn.x - inputRadius
				&& screenX < inputPointSpawn.x + inputRadius
				&& screenY > Gdx.graphics.getHeight() - inputPointSpawn.y - inputRadius
				&& screenY < Gdx.graphics.getHeight() - inputPointSpawn.y + inputRadius;
		lineTouched = screenX > inputLineSpawn.x - inputRadius
				&& screenX < inputLineSpawn.x + inputRadius
				&& screenY > Gdx.graphics.getHeight() - inputLineSpawn.y - inputRadius
				&& screenY < Gdx.graphics.getHeight() - inputLineSpawn.y + inputRadius;
		triangleTouched = screenX > inputTriangleSpawn.x - inputRadius
				&& screenX < inputTriangleSpawn.x + inputRadius
				&& screenY > Gdx.graphics.getHeight() - inputTriangleSpawn.y - inputRadius
				&& screenY < Gdx.graphics.getHeight() - inputTriangleSpawn.y + inputRadius;
		squareTouched = screenX > inputSquareSpawn.x - inputRadius
				&& screenX < inputSquareSpawn.x + inputRadius
				&& screenY > Gdx.graphics.getHeight() - inputSquareSpawn.y - inputRadius
				&& screenY < Gdx.graphics.getHeight() - inputSquareSpawn.y + inputRadius;

		if(pointTouched) {
			if(promptShape == Shape.POINT) {
				promptShape = Shape.LINE;
			} else if(promptShape == Shape.LINE) {
				promptShape = Shape.TRIANGLE;
			} else if(promptShape == Shape.TRIANGLE) {
				promptShape = Shape.SQUARE;
			} else {
				promptShape = Shape.POINT;
			}
		} else if(lineTouched) {
			if(promptShape == Shape.POINT) {
				promptShape = Shape.TRIANGLE;
			} else if(promptShape == Shape.LINE) {
				promptShape = Shape.SQUARE;
			} else if(promptShape == Shape.TRIANGLE) {
				promptShape = Shape.POINT;
			} else {
				promptShape = Shape.LINE;
			}
		} else if(triangleTouched) {
			if(promptShape == Shape.POINT) {
				promptShape = Shape.SQUARE;
			} else if(promptShape == Shape.LINE) {
				promptShape = Shape.POINT;
			} else if(promptShape == Shape.TRIANGLE) {
				promptShape = Shape.LINE;
			} else {
				promptShape = Shape.TRIANGLE;
			}
		} else if(squareTouched) {
			if(promptShape == Shape.POINT) {
				promptShape = Shape.POINT;
			} else if(promptShape == Shape.LINE) {
				promptShape = Shape.LINE;
			} else if(promptShape == Shape.TRIANGLE) {
				promptShape = Shape.TRIANGLE;
			} else {
				promptShape = Shape.SQUARE;
			}
		}
		return true;
	}

	@Override public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
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
