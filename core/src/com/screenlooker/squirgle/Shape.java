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

    //TODO: Determine whether or not these are actually going to be used, and if so, implement them. If not, get rid of the associated instance data.
    public static final String POINT_PREFIX = "P";
    public static final String POINT_SUFFIX = "T";
    public static final String LINE_PREFIX = "L";
    public static final String LINE_SUFFIX = "NE";
    public static final String TRIANGLE_PREFIX = "TR";
    public static final String TRIANGLE_SUFFIX = "GLE";
    public static final String SQUARE_PREFIX = "SQU";
    public static final String SQUARE_SUFFIX = "E";
    public static final String PENTAGON_PREFIX = "PENT";
    public static final String PENTAGON_SUFFIX = "TAGON";
    public static final String HEXAGON_PREFIX = "HEX";
    public static final String HEXAGON_SUFFIX = "XAGON";
    public static final String SEPTAGON_PREFIX = "SEPT";
    public static final String SEPTAGON_SUFFIX = "PTAGON";
    public static final String OCTAGON_PREFIX = "OCT";
    public static final String OCTAGON_SUFFIX = "CTAGON";
    public static final String NONAGON_PREFIX = "NON";
    public static final String NONAGON_SUFFIX = "NAGON";
    public static final String BRIDGE = "IR";

    private int shape;
    private String prefix;
    private String bridge;
    private String suffix;
    private float radius;
    private Color color;
    private Color fillColor;
    private float lineWidth;
    private Vector2 coordinates;

    public Shape() {
        this.shape = CIRCLE;
        this.prefix = "";
        this.bridge = "";
        this.suffix = "";
        this.radius = 10;
        this.color = Color.WHITE;
        this.fillColor = Color.WHITE;
        this.lineWidth = radius / Draw.LINE_WIDTH_DIVISOR;
        this.coordinates = new Vector2();
    }

    public Shape(int shape, float radius, Color color, Color fillColor, float lineWidth, Vector2 coordinates) {
        this.shape = shape;
        switch (shape) {
            case POINT : {
                this.prefix = POINT_PREFIX;
                this.suffix = POINT_SUFFIX;
            }
            case LINE : {
                this.prefix = LINE_PREFIX;
                this.suffix = LINE_SUFFIX;
            }
            case TRIANGLE : {
                this.prefix = TRIANGLE_PREFIX;
                this.suffix = TRIANGLE_SUFFIX;
            }
            case SQUARE : {
                this.prefix = SQUARE_PREFIX;
                this.suffix = SQUARE_SUFFIX;
            }
            case PENTAGON : {
                this.prefix = PENTAGON_PREFIX;
                this.suffix = PENTAGON_SUFFIX;
            }
            case HEXAGON : {
                this.prefix = HEXAGON_PREFIX;
                this.suffix = HEXAGON_SUFFIX;
            }
            case SEPTAGON : {
                this.prefix = SEPTAGON_PREFIX;
                this.suffix = SEPTAGON_SUFFIX;
            }
            case OCTAGON : {
                this.prefix = OCTAGON_PREFIX;
                this.suffix = OCTAGON_SUFFIX;
            }
            case NONAGON : {
                this.prefix = NONAGON_PREFIX;
                this.suffix = NONAGON_SUFFIX;
            }
        }
        this.bridge = BRIDGE;
        this.radius = radius;
        this.color = color;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;
        this.coordinates = coordinates;
    }

    public int getShape() { return shape; }

    public void setShape(int shape) { this.shape = shape; }

    public String getPrefix() { return prefix; }

    public void setPrefix(String prefix) { this.prefix = prefix; }

    public String getBridge() { return bridge; }

    public void setBridge(String bridge) { this.bridge = bridge; }

    public String getSuffix() { return suffix; }

    public void setSuffix(String suffix) { this.suffix = suffix; }

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
