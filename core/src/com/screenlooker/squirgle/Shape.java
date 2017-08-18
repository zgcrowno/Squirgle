package com.screenlooker.squirgle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Shape {
    public static final int POINT = 0;
    public static final int LINE = 1;
    public static final int TRIANGLE = 2;
    public static final int SQUARE = 3;
    public static final int CIRCLE = 4;

    private int shape;
    private float radius;
    private Color color;
    private float lineWidth;
    private Vector2 coordinates;

    public Shape() {
        this.shape = this.CIRCLE;
        this.radius = 10;
        this.color = Color.WHITE;
        this.lineWidth = radius / 8;
        this.coordinates = new Vector2();
    }

    public Shape(int shape, float radius, Color color, float lineWidth, Vector2 coordinates) {
        this.shape = shape;
        this.radius = radius;
        this.color = color;
        this.lineWidth = lineWidth;
        this.coordinates = coordinates;
    }

    public int getShape() { return shape; }

    public void setShape(int shape) { this.shape = shape; }

    public float getRadius() { return radius; }

    public void setRadius(float radius) { this.radius = radius; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public float getLineWidth() { return this.lineWidth; }

    public void setLineWidth(float lineWidth) { this.lineWidth = lineWidth; }

    public Vector2 getCoordinates() { return coordinates; }

    public void setCoordinates(Vector2 coordinates) { this.coordinates = coordinates; }
}
