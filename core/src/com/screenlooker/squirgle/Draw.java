package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.graphics.Color.BLACK;

public class Draw {
    private float pointRadius;
    private float lineWidth;
    private float inputDistanceOffset;
    private int inputRadius;
    private Vector2 inputPointSpawn;
    private Vector2 inputLineSpawn;
    private Vector2 inputTriangleSpawn;
    private Vector2 inputSquareSpawn;
    Vector2 inputTriangleTop;
    Vector2 inputTriangleLeft;
    Vector2 inputTriangleRight;
    Vector2 inputSquareTopLeft;
    Vector2 inputSquareBottomLeft;
    Vector2 inputSquareBottomRight;
    Vector2 inputSquareTopRight;
    Vector2 promptTriangleTop;
    Vector2 promptTriangleLeft;
    Vector2 promptTriangleRight;
    Vector2 promptSquareTopLeft;
    Vector2 promptSquareBottomLeft;
    Vector2 promptSquareBottomRight;
    Vector2 promptSquareTopRight;

    public Draw() {
        pointRadius = 0;
        lineWidth = 0;
        inputDistanceOffset = 0;
        inputRadius = 0;
        inputPointSpawn = new Vector2();
        inputLineSpawn = new Vector2();
        inputTriangleSpawn = new Vector2();
        inputSquareSpawn = new Vector2();
        inputTriangleTop = new Vector2();
        inputTriangleLeft = new Vector2();
        inputTriangleRight = new Vector2();
        inputSquareTopLeft = new Vector2();
        inputSquareBottomLeft = new Vector2();
        inputSquareBottomRight = new Vector2();
        inputSquareTopRight = new Vector2();
        promptTriangleTop = new Vector2();
        promptTriangleLeft = new Vector2();
        promptTriangleRight = new Vector2();
        promptSquareTopLeft = new Vector2();
        promptSquareBottomLeft = new Vector2();
        promptSquareBottomRight = new Vector2();
        promptSquareTopRight = new Vector2();
    }

    public Draw(float screenWidth) {
        pointRadius = 10;
        lineWidth = 12.5f;
        inputDistanceOffset = 1.5f;
        inputRadius = 50;
        inputPointSpawn = new Vector2(screenWidth / 5, (inputDistanceOffset * inputRadius));
        inputLineSpawn = new Vector2((2 * screenWidth) / 5, (inputDistanceOffset * inputRadius));
        inputTriangleSpawn = new Vector2((3 * screenWidth) / 5, (inputDistanceOffset * inputRadius));
        inputSquareSpawn = new Vector2((4 * screenWidth) / 5, (inputDistanceOffset * inputRadius));
        inputTriangleTop = new Vector2(inputTriangleSpawn.x, inputTriangleSpawn.y + (inputRadius / 2));
        inputTriangleLeft = new Vector2(inputTriangleSpawn.x - (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2));
        inputTriangleRight = new Vector2(inputTriangleSpawn.x + (inputRadius / 2), inputTriangleSpawn.y - (inputRadius / 2));
        inputSquareTopLeft = new Vector2(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2));
        inputSquareBottomLeft = new Vector2(inputSquareSpawn.x - (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2));
        inputSquareBottomRight = new Vector2(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y - (inputRadius / 2));
        inputSquareTopRight = new Vector2(inputSquareSpawn.x + (inputRadius / 2), inputSquareSpawn.y + (inputRadius / 2));
        promptTriangleTop = new Vector2();
        promptTriangleLeft = new Vector2();
        promptTriangleRight = new Vector2();
        promptSquareTopLeft = new Vector2();
        promptSquareBottomLeft = new Vector2();
        promptSquareBottomRight = new Vector2();
        promptSquareTopRight = new Vector2();
    }

    public float getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(float pointRadius) {
        this.pointRadius = pointRadius;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public float getInputDistanceOffset() {
        return inputDistanceOffset;
    }

    public void setInputDistanceOffset(float inputDistanceOffset) {
        this.inputDistanceOffset = inputDistanceOffset;
    }

    public int getInputRadius() {
        return inputRadius;
    }

    public void setInputRadius(int inputRadius) {
        this.inputRadius = inputRadius;
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
        if(promptShape == 0) {
            drawPoint(x, y, promptSize, color, shapeRenderer);
        } else if(promptShape == 1) {
            drawLine(x, y + (2 * promptSize), x, y - (2 * promptSize), promptSize, color, shapeRenderer);
        } else if(promptShape == 2) {
            promptTriangleTop.set(x, y + (1.5f * promptSize));
            promptTriangleLeft.set(x - (1.5f * promptSize), y - (1.5f * promptSize));
            promptTriangleRight.set(x + (1.5f * promptSize), y - (1.5f * promptSize));
            drawTriangle(promptTriangleTop, promptTriangleLeft, promptTriangleRight, promptSize, color, shapeRenderer);
        } else {
            promptSquareTopLeft.set(x - (1.5f * promptSize), y + (1.5f * promptSize));
            promptSquareBottomLeft.set(x - (1.5f * promptSize), y - (1.5f * promptSize));
            promptSquareBottomRight.set(x + (1.5f * promptSize), y - (1.5f * promptSize));
            promptSquareTopRight.set(x + (1.5f * promptSize), y + (1.5f * promptSize));
            drawSquare(promptSquareTopLeft, promptSquareBottomLeft, promptSquareBottomRight, promptSquareTopRight, promptSize, color, shapeRenderer);
        }
    }

    public void drawPreviousPrompts() {

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

        drawTriangle(inputTriangleTop, inputTriangleLeft, inputTriangleRight, inputRadius / 4, Color.BLACK, shapeRenderer);

        //Square
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputSquareSpawn.x, inputSquareSpawn.y, inputRadius);

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
