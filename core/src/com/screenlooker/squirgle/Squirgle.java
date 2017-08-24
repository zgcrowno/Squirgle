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

//TODO: Many of the variables throughout this entire game will have to be replaced with screen-size dependent alternatives
public class Squirgle extends ApplicationAdapter implements InputProcessor {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRendererFilled;
	private ShapeRenderer shapeRendererLine;
	private Draw draw;
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
	private Color clearColor;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 768, 1024);
		Gdx.input.setInputProcessor(this);
		shapeRendererFilled = new ShapeRenderer();
		shapeRendererFilled.setProjectionMatrix(camera.combined);
		shapeRendererLine = new ShapeRenderer();
		shapeRendererLine.setProjectionMatrix(camera.combined);
		draw = new Draw(Gdx.graphics.getHeight());
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
		clearColor = new Color();
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		//TODO: Maybe add another shapeRenderer of ShapeType.Unfilled for prompt?
		shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

		shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

		draw.drawBackgroundColorShape(backgroundColorShape, shapeRendererFilled);

		draw.drawPrompt(promptShape, priorShapeList, shapeRendererLine);

		draw.drawShapes(priorShapeList, promptShape, shapeRendererFilled);

		draw.drawBackgroundColorShapeList(backgroundColorShapeList, backgroundColorShape, clearColor, shapeRendererFilled);

		draw.drawInputButtons(inputPointSpawn, inputLineSpawn, inputTriangleSpawn, inputSquareSpawn, shapeRendererFilled);

		draw.drawTargetSemicircle(shapeRendererFilled);

		draw.drawScoreTriangle(shapeRendererFilled);

		draw.drawPrompt(outsideTargetShape, targetShapeList, shapeRendererFilled);

		draw.drawShapes(targetShapeList, outsideTargetShape, shapeRendererFilled);

		shapeRendererFilled.end();

		shapeRendererLine.end();

		promptShape.setRadius(promptShape.getRadius() * promptIncrease);

		promptShape.setLineWidth(promptShape.getRadius() / 8);

		currentTargetShape.setColor(ColorUtils.randomPrimary());

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
		if(promptShape.getShape() == currentTargetShape.getShape()) {
			targetShapesMatched++;
			Shape circleContainer = new Shape(Shape.CIRCLE, promptShape.getRadius(), Color.BLACK, null, promptShape.getLineWidth(), promptShape.getCoordinates());
			Shape promptShapeToAdd = new Shape(promptShape.getShape(), promptShape.getRadius(), backgroundColorShape.getColor(), null, promptShape.getLineWidth(), promptShape.getCoordinates());
			if(promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
				promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
			}
			promptShape.setShape(MathUtils.random(Shape.SQUARE));
			priorShapeList.add(promptShapeToAdd);
			priorShapeList.add(circleContainer);
			if(targetShapesMatched == 1) {
				currentTargetShape = outsideTargetShape;
			} else {
				targetShapesMatched = 0;
				targetShapeList.clear();
				outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
				outsideTargetShape.setColor(Color.BLACK);
				targetShapeList.add(new Shape(MathUtils.random(Shape.SQUARE), 0, Color.WHITE, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 2.5f, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2.5f))));
				targetShapeList.add(new Shape(Shape.CIRCLE, 0, Color.BLACK, null, Draw.INPUT_RADIUS / 8, new Vector2(Draw.TARGET_RADIUS / 3, Gdx.graphics.getHeight() - (Draw.TARGET_RADIUS / 2))));
				currentTargetShape = targetShapeList.get(0);
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
