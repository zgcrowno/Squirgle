package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.graphics.Color.BLACK;

public class Draw {
    private float pointRadius = 10;
    private float lineWidth = 12.5f;
    private float inputDistanceOffset = (float) 1.5;
    private int inputRadius = 50;
    private Vector2 inputPointSpawn = new Vector2(Gdx.graphics.getWidth() / 5, (inputDistanceOffset * inputRadius));
    private Vector2 inputLineSpawn = new Vector2((2 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
    private Vector2 inputTriangleSpawn = new Vector2((3 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));
    private Vector2 inputSquareSpawn = new Vector2((4 * Gdx.graphics.getWidth()) / 5, (inputDistanceOffset * inputRadius));

    public void drawPrompt(float x, float y, int shape, float initShapeSize, ShapeRenderer shapeRenderer) {
        if(shape == 0) {

        } else if(shape == 1) {

        } else if(shape == 2) {

        } else {

        }
    }

    public void drawPoint(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    public void drawLine(float xStart, float yStart, float xEnd, float yEnd, float width, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(xStart, yStart, xEnd, yEnd, width);
        shapeRenderer.circle(xStart, yStart, width);
        shapeRenderer.circle(xEnd, yEnd, width);
    }

    public void drawTriangle(Vector2 top, Vector2 left, Vector2 right, float width, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(top.x, top.y, left.x, left.y, width);
        shapeRenderer.rectLine(left.x, left.y, right.x, right.y, width);
        shapeRenderer.rectLine(right.x, right.y, top.x, top.y, width);
        drawPoint(top.x, top.y, width, color, shapeRenderer);
        drawPoint(left.x, left.y, width, color, shapeRenderer);
        drawPoint(right.x, right.y, width, color, shapeRenderer);
    }

    public void drawSquare(Vector2 topLeft, Vector2 bottomLeft, Vector2 bottomRight, Vector2 topRight, float width, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y, width);
        shapeRenderer.rectLine(bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y, width);
        shapeRenderer.rectLine(bottomRight.x, bottomRight.y, topRight.x, topRight.y, width);
        shapeRenderer.rectLine(topRight.x, topRight.y, topLeft.x, topLeft.y, width);
        drawPoint(topLeft.x, topLeft.y, width, color, shapeRenderer);
        drawPoint(bottomLeft.x, bottomLeft.y, width, color, shapeRenderer);
        drawPoint(bottomRight.x, bottomRight.y, width, color, shapeRenderer);
        drawPoint(topRight.x, topRight.y, width, color, shapeRenderer);
    }

    public void drawInputButtons(float inputRadius, ShapeRenderer shapeRenderer) {
        //Point
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, inputRadius);
        shapeRenderer.setColor(BLACK);
        shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, inputRadius / 4);

        //Line
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y, inputRadius);
        drawLine(inputLineSpawn.x, inputLineSpawn.y - (inputRadius / 2), inputLineSpawn.x, inputLineSpawn.y + (inputRadius / 2), inputRadius / 4, BLACK, shapeRenderer);

        //Triangle
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputTriangleSpawn.x, inputTriangleSpawn.y, inputRadius);

        Vector2 inputTriangleTop = new Vector2(inputTriangleSpawn.x, inputTriangleSpawn.y + (inputRadius / 2));
        Vector2 inputTriangleLeft = new Vector2(inputTriangleSpawn.x - (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2));
        Vector2 inputTriangleRight = new Vector2(inputTriangleSpawn.x + (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2));

        drawTriangle(inputTriangleTop, inputTriangleLeft, inputTriangleRight, inputRadius / 4, Color.BLACK, shapeRenderer);

        //Square
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputSquareSpawn.x, inputSquareSpawn.y, inputRadius);

        Vector2 inputSquareTopLeft = new Vector2(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2));
        Vector2 inputSquareBottomLeft = new Vector2(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2));
        Vector2 inputSquareBottomRight = new Vector2(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2));
        Vector2 inputSquareTopRight = new Vector2(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2));

        shapeRenderer.rectLine(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);
        shapeRenderer.rectLine(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
        shapeRenderer.rectLine(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
        shapeRenderer.rectLine(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);
        shapeRenderer.circle(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);
        shapeRenderer.circle(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
        shapeRenderer.circle(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2), inputRadius / 4);
        shapeRenderer.circle(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2), inputRadius / 4);

        drawSquare(inputSquareTopLeft, inputSquareBottomLeft, inputSquareBottomRight, inputSquareTopRight, inputRadius / 4, Color.BLACK, shapeRenderer);
    }
}
