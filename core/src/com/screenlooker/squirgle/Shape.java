package com.screenlooker.squirgle;

import com.badlogic.gdx.graphics.Color;

public class Shape {
    public static final int POINT = 0;
    public static final int LINE = 1;
    public static final int TRIANGLE = 2;
    public static final int SQUARE = 3;
    public static final int CIRCLE = 4;

    private int shape;
    private float radius;
    private Color color;

    public Shape() {
        this.shape = this.CIRCLE;
        this.radius = 10;
        this.color = Color.WHITE;
    }

    public Shape(int shape, int size, Color color) {
        this.shape = shape;
        this.radius = size;
        this.color = color;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(int size) {
        this.radius = size;
    }
}
