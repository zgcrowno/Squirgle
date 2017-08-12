package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static com.badlogic.gdx.graphics.Color.BLACK;

public class Draw {
    private float lineWidth;
    private int inputRadius;
    private int targetRadius;
    private float inputDistanceOffset;
    private float targetDistanceOffset;
    private float radiusOffset;
    private Vector2 targetSpawn;
    private Vector2 inputPointSpawn;
    private Vector2 inputLineSpawn;
    private Vector2 inputTriangleSpawn;
    private Vector2 inputSquareSpawn;

    public Draw() {
        lineWidth = 0;
        inputRadius = 0;
        targetRadius = 0;
        inputDistanceOffset = 0;
        targetDistanceOffset = 0;
        radiusOffset = 0;
        targetSpawn = new Vector2();
        inputPointSpawn = new Vector2();
        inputLineSpawn = new Vector2();
        inputTriangleSpawn = new Vector2();
        inputSquareSpawn = new Vector2();
    }

    public Draw(float screenWidth, float screenHeight) {
        lineWidth = 0;
        inputRadius = 50;
        targetRadius = 150;
        inputDistanceOffset = 1.5f;
        targetDistanceOffset = targetRadius / 2.5f;
        radiusOffset = 1.45f;
        targetSpawn = new Vector2(targetDistanceOffset, screenHeight - targetDistanceOffset);
        inputPointSpawn = new Vector2(screenWidth / 5, (inputDistanceOffset * inputRadius));
        inputLineSpawn = new Vector2((2 * screenWidth) / 5, (inputDistanceOffset * inputRadius));
        inputTriangleSpawn = new Vector2((3 * screenWidth) / 5, (inputDistanceOffset * inputRadius));
        inputSquareSpawn = new Vector2((4 * screenWidth) / 5, (inputDistanceOffset * inputRadius));
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getInputRadius() {
        return inputRadius;
    }

    public void setInputRadius(int inputRadius) {
        this.inputRadius = inputRadius;
    }

    public int getTargetRadius() {
        return targetRadius;
    }

    public void setTargetRadius(int targetRadius) {
        this.targetRadius = targetRadius;
    }

    public float getInputDistanceOffset() {
        return inputDistanceOffset;
    }

    public void setInputDistanceOffset(float inputDistanceOffset) {
        this.inputDistanceOffset = inputDistanceOffset;
    }

    public float getTargetDistanceOffset() {
        return targetDistanceOffset;
    }

    public void setTargetDistanceOffset(float targetDistanceOffset) {
        this.targetDistanceOffset = targetDistanceOffset;
    }

    public Vector2 getTargetSpawn() {
        return targetSpawn;
    }

    public void setTargetSpawn(Vector2 targetSpawn) {
        this.targetSpawn = targetSpawn;
    }

    public Vector2 getInputPointSpawn() {
        return inputPointSpawn;
    }

    public void setInputPointSpawn(Vector2 inputPointSpawn) {
        this.inputPointSpawn = inputPointSpawn;
    }

    public Vector2 getInputLineSpawn() {
        return inputLineSpawn;
    }

    public void setInputLineSpawn(Vector2 inputLineSpawn) {
        this.inputLineSpawn = inputLineSpawn;
    }

    public Vector2 getInputTriangleSpawn() {
        return inputTriangleSpawn;
    }

    public void setInputTriangleSpawn(Vector2 inputTriangleSpawn) {
        this.inputTriangleSpawn = inputTriangleSpawn;
    }

    public Vector2 getInputSquareSpawn() {
        return inputSquareSpawn;
    }

    public void setInputSquareSpawn(Vector2 inputSquareSpawn) {
        this.inputSquareSpawn = inputSquareSpawn;
    }

    public void drawPrompt(float x, float y, int promptShape, float promptSize, Color color, ShapeRenderer shapeRenderer) {
        if(promptShape == Shape.CIRCLE) {
            drawPoint(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape == Shape.POINT) {
            drawPoint(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape == Shape.LINE) {
            drawLine(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape == Shape.TRIANGLE) {
            drawTriangle(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape == Shape.SQUARE) {
            drawSquare(x, y, promptSize, color, shapeRenderer);
        }
    }

    public void drawPreviousPrompts() {

    }

    public void drawTarget(List<Shape> targetShapeList, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, Gdx.graphics.getHeight(), targetRadius);
        if(!targetShapeList.isEmpty()) {
            for(int i = targetShapeList.size() - 1; i >= 0; i--) {
                Shape shape = targetShapeList.get(i);
                Color color = null;
                float xOffset = 0;
                float yOffset = 0;
                if(i == 2) {
                    color = Color.BLACK;
                    if(shape.getShape() == Shape.POINT) {
                        shape.setRadius(targetRadius / 15);
                        xOffset = targetRadius / 5;
                    } else if(shape.getShape() == Shape.LINE) {
                        shape.setRadius(targetRadius / 10);
                        xOffset = targetRadius / 5;
                    } else if(shape.getShape() == Shape.TRIANGLE) {
                        shape.setRadius(targetRadius / 3);
                        yOffset = targetRadius / 15;
                    } else if(shape.getShape() == Shape.SQUARE) {
                        shape.setRadius(targetRadius / 3);
                    }
                } else if(i == 1) {
                    color = Color.BLACK;
                    shape.setRadius(targetRadius / 7);
                } else {
                    color = Color.WHITE;
                    if(shape.getShape() == Shape.POINT) {
                        shape.setRadius(targetRadius / 15);
                    } else {
                        shape.setRadius(targetRadius / 10);
                    }
                }
                drawShape(targetSpawn.x - xOffset, targetSpawn.y + yOffset, shape.getRadius(), color, shapeRenderer, shape);
            }
        }
    }

    public void drawShape(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer, Shape shape) {
        if(shape.getShape() == Shape.POINT) {
            drawPoint(x, y, radius, color, shapeRenderer);
        } else if(shape.getShape() == Shape.LINE) {
            drawLine(x, y, radius, color, shapeRenderer);
        } else if(shape.getShape() == Shape.TRIANGLE) {
            drawTriangle(x, y, radius, color, shapeRenderer);
        } else if(shape.getShape() == Shape.SQUARE) {
            drawSquare(x, y, radius, color, shapeRenderer);
        } else {
            shapeRenderer.circle(x, y, radius);
        }
    }

    public void drawPoint(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    public void drawLine(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        lineWidth = radius / 8;
        shapeRenderer.rectLine(x, y - radius + lineWidth, x, y + radius - lineWidth, lineWidth);
        shapeRenderer.circle(x, y - radius + lineWidth, lineWidth);
        shapeRenderer.circle(x, y + radius - lineWidth, lineWidth);
    }

    public void drawTriangle(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        lineWidth = radius / 8;
        shapeRenderer.rectLine(x, y + radius - lineWidth, (float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth);
        shapeRenderer.rectLine((float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), (float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth);
        shapeRenderer.rectLine((float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), x, y + radius - lineWidth, lineWidth);
        drawPoint(x, y + radius - lineWidth, lineWidth, color, shapeRenderer);
        drawPoint((float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth, color, shapeRenderer);
        drawPoint((float) (x + (radius / radiusOffset) - lineWidth / 2), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth, color, shapeRenderer);
    }

    public void drawSquare(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        lineWidth = radius / 8;
        shapeRenderer.rectLine((float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), (float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y + (radius / radiusOffset) - (lineWidth / 2)), lineWidth);
        shapeRenderer.rectLine((float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y + (radius / radiusOffset) - (lineWidth / 2)), (float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y + (radius / radiusOffset) - (lineWidth / 2)), lineWidth);
        shapeRenderer.rectLine((float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y + (radius / radiusOffset) - (lineWidth / 2)), (float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth);
        shapeRenderer.rectLine((float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), (float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth);
        drawPoint((float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth, color, shapeRenderer);
        drawPoint((float) (x - (radius / radiusOffset) + (lineWidth / 2)), (float) (y + (radius / radiusOffset) - (lineWidth / 2)), lineWidth, color, shapeRenderer);
        drawPoint((float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y + (radius / radiusOffset) - (lineWidth / 2)), lineWidth, color, shapeRenderer);
        drawPoint((float) (x + (radius / radiusOffset) - (lineWidth / 2)), (float) (y - (radius / radiusOffset) + (lineWidth / 2)), lineWidth, color, shapeRenderer);
    }

    public void drawInputButtons(float inputRadius, ShapeRenderer shapeRenderer) {
        //Point
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, inputRadius);
        drawPoint(inputPointSpawn.x, inputPointSpawn.y, inputRadius / 2, Color.BLACK, shapeRenderer);

        //Line
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y, inputRadius);
        drawLine(inputLineSpawn.x, inputLineSpawn.y, inputRadius, Color.BLACK, shapeRenderer);

        //Triangle
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputTriangleSpawn.x, inputTriangleSpawn.y, inputRadius);
        drawTriangle(inputTriangleSpawn.x, inputTriangleSpawn.y, inputRadius, Color.BLACK, shapeRenderer);

        //Square
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputSquareSpawn.x, inputSquareSpawn.y, inputRadius);
        drawSquare(inputSquareSpawn.x, inputSquareSpawn.y, inputRadius, Color.BLACK, shapeRenderer);
    }
}
