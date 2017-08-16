package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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

    public void drawPrompt(float x, float y, Shape promptShape, float promptSize, List<Shape> priorShapeList, Color color, ShapeRenderer shapeRenderer) {
        float xOffset = 0;
        lineWidth = promptSize / 8;
        if(promptShape.getShape() == Shape.CIRCLE) {
            drawPoint(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape.getShape() == Shape.POINT) {
            if(!priorShapeList.isEmpty()) {
                xOffset = priorShapeList.get(priorShapeList.size() - 1).getRadius() + promptSize;
                drawPoint(x - xOffset, y, promptSize, color, shapeRenderer);
            }
            drawPoint(x - xOffset, y, promptSize, color, shapeRenderer);
        } else if(promptShape.getShape() == Shape.LINE) {
            if(!priorShapeList.isEmpty()) {
                xOffset = priorShapeList.get(priorShapeList.size() - 1).getRadius() + (lineWidth / 2);
            }
            drawLine(x - xOffset, y, promptSize, color, shapeRenderer);
        } else if(promptShape.getShape() == Shape.TRIANGLE) {
            drawTriangle(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape.getShape() == Shape.SQUARE) {
            drawSquare(x, y, promptSize, color, shapeRenderer);
        }
    }

    public void drawPriorShapes(List<Shape> priorShapeList, Shape promptShape, Vector2 promptShapeSpawn, ShapeRenderer shapeRenderer) {
        if(!priorShapeList.isEmpty()) {
            if(!priorShapeList.isEmpty()) {
                for(int i = priorShapeList.size() - 1; i >= 0; i--) {
                    Shape shape = priorShapeList.get(i);
                    Shape priorShape = null;

                    //Set the color by determining whether shape is circle or otherwise
                    if(i % 2 == 0) {
                        shape.setColor(Color.WHITE);
                    } else {
                        shape.setColor(Color.BLACK);
                    }

                    //Determine whether to compare current shape with prompt or next shape in list
                    if(i == priorShapeList.size() - 1) {
                        //Shape is at end of list, so compare with prompt
                        priorShape = promptShape;
                    } else {
                        //Shape is not at end of list, so compare with next shape in list
                        priorShape = priorShapeList.get(i + 1);
                    }

                    //Ensure that current element of list initially has same radius and coordinates as prior shape
                    shape.setRadius(priorShape.getRadius());
                    shape.setCoordinates(priorShape.getCoordinates());

                    //Lines and points must be shrunken and re-coordinated when encircled, except if the line is the first member of the list
                    if(shape.getShape() == Shape.POINT || shape.getShape() == Shape.LINE) {
                        if(!(shape.getShape() == Shape.LINE && i == 0)) {
                            shape.setRadius(priorShape.getRadius() / 2);
                            if(shape.getShape() == Shape.POINT && i != 0) {
                                //shape.setCoordinates(priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y);
                                shape.setCoordinates(new Vector2(priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                            } else {
                                //shape.setCoordinates(priorShape.getCoordinates().x - (lineWidth / 2), shape.getCoordinates().y);
                                shape.setCoordinates(new Vector2(priorShape.getCoordinates().x - (lineWidth / 2), shape.getCoordinates().y));
                            }
                        }
                    }

                    //Determine radius/coordinates of current shape based on prior one
                    //Also, current shape is guaranteed to be a circle if any of these conditions hold
                    if(priorShape.getShape() == Shape.POINT) {
                        //shape.setCoordinates(priorShape.getCoordinates().x + (shape.getRadius() * 2), priorShape.getCoordinates().y);
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + (shape.getRadius() * 2), priorShape.getCoordinates().y));
                    } else if(priorShape.getShape() == Shape.LINE) {
                        //shape.setCoordinates(priorShape.getCoordinates().x + shape.getRadius() + (lineWidth / 2), priorShape.getCoordinates().y);
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + shape.getRadius() + (lineWidth / 2), priorShape.getCoordinates().y));
                    } else if(priorShape.getShape() == Shape.TRIANGLE) {
                        shape.setRadius(priorShape.getRadius() / 2);
                    } else if(priorShape.getShape() == Shape.SQUARE) {
                        shape.setRadius((float) (priorShape.getRadius() / Math.sqrt(2)));
                    }

                    drawShape(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getColor(), shapeRenderer, shape);
                }
            }
        }
    }

    public void drawTarget(List<Shape> targetShapeList, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, Gdx.graphics.getHeight(), targetRadius);
        if(!targetShapeList.isEmpty()) {
            for(int i = targetShapeList.size() - 1; i >= 0; i--) {
                Shape shape = targetShapeList.get(i);
                Color color = null;
                float xOffset = 0;
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
                drawShape(targetSpawn.x - xOffset, targetSpawn.y, shape.getRadius(), color, shapeRenderer, shape);
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
            shapeRenderer.setColor(color);
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
        shapeRenderer.rectLine(x,
                y + radius - lineWidth,
                (float) (x - (MathUtils.sinDeg(60) * radius)),
                (float) (y - (MathUtils.cosDeg(60) * radius)),
                lineWidth);
        shapeRenderer.rectLine((float) (x - (MathUtils.sinDeg(60) * radius)),
                (float) (y - (MathUtils.cosDeg(60) * radius)),
                (float) (x + (MathUtils.sinDeg(60) * radius)),
                (float) (y - (MathUtils.cosDeg(60) * radius)),
                lineWidth);
        shapeRenderer.rectLine((float) (x + (MathUtils.sinDeg(60) * radius)),
                (float) (y - (MathUtils.cosDeg(60) * radius)),
                x,
                y + radius - lineWidth,
                lineWidth);
        drawPoint(x, y + radius - lineWidth, lineWidth, color, shapeRenderer);
        drawPoint((float) (x - (MathUtils.sinDeg(60) * radius)), (float) (y - (MathUtils.cosDeg(60) * radius)), lineWidth, color, shapeRenderer);
        drawPoint((float) (x + (MathUtils.sinDeg(60) * radius)), (float) (y - (MathUtils.cosDeg(60) * radius)), lineWidth, color, shapeRenderer);
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
