package com.screenlooker.squirgle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Shape {
    public static final int POINT = 0;
    public static final int LINE = 1;
    public static final int TRIANGLE = 2;
    public static final int SQUARE = 3;
    public static final int PENTAGON = 4;
    public static final int HEXAGON = 5;
    public static final int SEPTAGON = 6;
    public static final int OCTAGON = 7;
    public static final int NONAGON = 8;
    public static final int CIRCLE = 9;

    //TODO: Add an opacity datum for displaying to the user when they're approaching game over
    private int shape;
    private float radius;
    private Color color;
    private Color fillColor;
    private float lineWidth;
    private Vector2 coordinates;

    public Shape() {
        this.shape = CIRCLE;
        this.radius = 10;
        this.color = Color.WHITE;
        this.fillColor = Color.WHITE;
        this.lineWidth = radius / Draw.LINE_WIDTH_DIVISOR;
        this.coordinates = new Vector2();
    }

    public Shape(int shape, float radius, Color color, Color fillColor, float lineWidth, Vector2 coordinates) {
        this.shape = shape;
        this.radius = radius;
        this.color = color;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;
        this.coordinates = coordinates;
    }

    public int getShape() { return shape; }

    public void setShape(int shape) { this.shape = shape; }

    public float getRadius() { return radius; }

    public void setRadius(float radius) { this.radius = radius; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public Color getFillColor() { return fillColor; }

    public void setFillColor(Color fillColor) { this.fillColor = fillColor; }

    public float getLineWidth() { return this.lineWidth; }

    public void setLineWidth(float lineWidth) { this.lineWidth = lineWidth; }

    public Vector2 getCoordinates() { return coordinates; }

    public void setCoordinates(Vector2 coordinates) { this.coordinates = coordinates; }

    public static int randomBackgroundColorShape() {
        int val = MathUtils.random(Shape.SQUARE);
        while(val == Shape.LINE) {
            val = MathUtils.random(Shape.SQUARE);
        }
        return val;
    }
}
