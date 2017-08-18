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
	private float initPromptRadius;
	private float promptIncrease;
	private Shape promptShape;
	private Shape outsideTargetShape;
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
		draw = new Draw(Gdx.graphics.getHeight());
		initPromptRadius = 20;
		promptIncrease = 1.0005f;
		promptShape = new Shape(MathUtils.random(Shape.SQUARE), initPromptRadius, Color.BLACK, initPromptRadius / 8, new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
		outsideTargetShape = new Shape(MathUtils.random(Shape.SQUARE), Draw.INPUT_RADIUS, Color.BLACK, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2)));
		priorShapeList = new ArrayList<Shape>();
		targetShapeList = new ArrayList<Shape>();
		targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
		targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
		currentTargetShape = targetShapeList.get(0).getShape();
		targetShapesMatched = 0;
		promptShapeSpawn = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		inputPointSpawn = new Vector2(Gdx.graphics.getWidth() / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
		inputLineSpawn = new Vector2((2 * Gdx.graphics.getWidth()) / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
		inputTriangleSpawn = new Vector2((3 * Gdx.graphics.getWidth()) / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
		inputSquareSpawn = new Vector2((4 * Gdx.graphics.getWidth()) / 5, (Draw.INPUT_DISTANCE_OFFSET * Draw.INPUT_RADIUS));
		touchPoint = new Vector3();
		pointTouched = false;
		lineTouched = false;
		triangleTouched = false;
		squareTouched = false;
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		//TODO: Maybe add another shapeRenderer of ShapeType.Unfilled for prompt?
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		draw.drawPrompt(promptShape, priorShapeList, shapeRenderer);

		draw.drawShapes(priorShapeList, promptShape, shapeRenderer);

		draw.drawInputButtons(inputPointSpawn, inputLineSpawn, inputTriangleSpawn, inputSquareSpawn, shapeRenderer);

		draw.drawTargetSemicircle(shapeRenderer);

		draw.drawPrompt(outsideTargetShape, targetShapeList, shapeRenderer);

		draw.drawShapes(targetShapeList, outsideTargetShape, shapeRenderer);

		shapeRenderer.end();

		promptShape.setRadius(promptShape.getRadius() * promptIncrease);

		promptShape.setLineWidth(promptShape.getRadius() / 8);

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
			Shape circleContainer = new Shape(Shape.CIRCLE, promptShape.getRadius(), Color.BLACK, promptShape.getLineWidth(), promptShape.getCoordinates());
			Shape promptShapeToAdd = new Shape(promptShape.getShape(), promptShape.getRadius(), Color.WHITE, promptShape.getLineWidth(), promptShape.getCoordinates());
			if(promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
				promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
			}
			promptShape.setShape(MathUtils.random(Shape.SQUARE));
			priorShapeList.add(promptShapeToAdd);
			priorShapeList.add(circleContainer);
			if(targetShapesMatched == 1) {
				currentTargetShape = outsideTargetShape.getShape();
			} else {
				targetShapesMatched = 0;
				targetShapeList.clear();
				outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
				targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
				targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
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
