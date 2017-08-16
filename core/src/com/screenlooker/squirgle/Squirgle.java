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
	private float targetRadius;
	private float promptIncrease;
	private Shape promptShape;
	private int inputRadius;
	private List<Shape> priorShapeList;
	private List<Shape> targetShapeList;
	private int currentTargetShape;
	private int targetShapesMatched;
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
		draw = new Draw(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		inputDistanceOffset = (float) 1.5;
		promptSize = 20;
		targetRadius = 150;
		promptIncrease = 1.0005f;
		promptShape = new Shape(MathUtils.random(Shape.SQUARE), promptSize, Color.BLACK, new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
		inputRadius = 50;
		priorShapeList = new ArrayList<Shape>();
		targetShapeList = new ArrayList<Shape>();
		targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, new Vector2(targetRadius / 2, targetRadius / 2)));
		targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, new Vector2(targetRadius / 2, targetRadius / 2)));
		targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, new Vector2(targetRadius / 2, targetRadius / 2)));
		currentTargetShape = targetShapeList.get(0).getShape();
		targetShapesMatched = 0;
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

		draw.drawPrompt(promptShapeSpawn.x, promptShapeSpawn.y, promptShape, promptSize, priorShapeList, Color.BLACK, shapeRenderer);

		draw.drawPriorShapes(priorShapeList, promptShape, promptShapeSpawn, shapeRenderer);

		draw.drawInputButtons(inputRadius, shapeRenderer);

		draw.drawTarget(targetShapeList, shapeRenderer);

		shapeRenderer.end();

		//TODO: remove this reference, and only set the radius of the prompt (will have to alter other methods as well)
		promptSize *= promptIncrease;

		promptShape.setRadius(promptSize *= promptIncrease);

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
			if(promptShape.getShape() == Shape.POINT) {
				promptShape.setShape(Shape.LINE);
			} else if(promptShape.getShape() == Shape.LINE) {
				promptShape.setShape(Shape.TRIANGLE);
			} else if(promptShape.getShape() == Shape.TRIANGLE) {
				promptShape.setShape(Shape.SQUARE);
			} else {
				promptShape.setShape(Shape.POINT);
			}
		} else if(lineTouched) {
			if(promptShape.getShape() == Shape.POINT) {
				promptShape.setShape(Shape.TRIANGLE);
			} else if(promptShape.getShape() == Shape.LINE) {
				promptShape.setShape(Shape.SQUARE);
			} else if(promptShape.getShape() == Shape.TRIANGLE) {
				promptShape.setShape(Shape.POINT);
			} else {
				promptShape.setShape(Shape.LINE);
			}
		} else if(triangleTouched) {
			if(promptShape.getShape() == Shape.POINT) {
				promptShape.setShape(Shape.SQUARE);
			} else if(promptShape.getShape() == Shape.LINE) {
				promptShape.setShape(Shape.POINT);
			} else if(promptShape.getShape() == Shape.TRIANGLE) {
				promptShape.setShape(Shape.LINE);
			} else {
				promptShape.setShape(Shape.TRIANGLE);
			}
		} else if(squareTouched) {
			if(promptShape.getShape() == Shape.POINT) {
				promptShape.setShape(Shape.POINT);
			} else if(promptShape.getShape() == Shape.LINE) {
				promptShape.setShape(Shape.LINE);
			} else if(promptShape.getShape() == Shape.TRIANGLE) {
				promptShape.setShape(Shape.TRIANGLE);
			} else {
				promptShape.setShape(Shape.SQUARE);
			}
		}
		if(promptShape.getShape() == currentTargetShape) {
			targetShapesMatched++;
			Shape circleContainer = new Shape(Shape.CIRCLE, promptSize, Color.BLACK, promptShape.getCoordinates());
			Shape promptShapeToAdd = new Shape(promptShape.getShape(), promptSize, Color.WHITE, promptShape.getCoordinates());
			if(promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
				promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
			}
			promptShape.setShape(MathUtils.random(Shape.SQUARE));
			priorShapeList.add(promptShapeToAdd);
			priorShapeList.add(circleContainer);
			if(targetShapesMatched == 1) {
				currentTargetShape = targetShapeList.get(2).getShape();
			} else {
				targetShapesMatched = 0;
				targetShapeList.clear();
				targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, promptShape.getCoordinates()));
				targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, promptShape.getCoordinates()));
				targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, promptShape.getCoordinates()));
				currentTargetShape = targetShapeList.get(0).getShape();
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
