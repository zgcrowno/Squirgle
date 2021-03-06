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

    public static final float PENTAGON_ROTATION = 0.315f;
    public static final float SEPTAGON_ROTATION = 0.675f;
    public static final float OCTAGON_ROTATION = 0.393f;
    public static final float NONAGON_ROTATION = 0.173f;

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
    private float rotation;

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
        this.rotation = 0;
    }

    public Shape(int shape, float radius, Color color, Color fillColor, float lineWidth, Vector2 coordinates) {
        this.shape = shape;
        determineName(shape);
        this.bridge = BRIDGE;
        this.radius = radius;
        this.color = color;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;
        this.coordinates = coordinates;
        if(shape == PENTAGON) {
            this.rotation = PENTAGON_ROTATION;
        } else if(shape == SEPTAGON) {
            this.rotation = SEPTAGON_ROTATION;
        } else if(shape == OCTAGON) {
            this.rotation = OCTAGON_ROTATION;
        } else if(shape == NONAGON) {
            this.rotation = NONAGON_ROTATION;
        } else {
            this.rotation = 0;
        }
    }

    public int getShape() { return shape; }

    public void setShape(int shape) {
        this.shape = shape;
        determineName(shape);
    }

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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void determineName(int shape) {
        switch (shape) {
            case POINT : {
                this.prefix = POINT_PREFIX;
                this.suffix = POINT_SUFFIX;
                break;
            }
            case LINE : {
                this.prefix = LINE_PREFIX;
                this.suffix = LINE_SUFFIX;
                break;
            }
            case TRIANGLE : {
                this.prefix = TRIANGLE_PREFIX;
                this.suffix = TRIANGLE_SUFFIX;
                break;
            }
            case SQUARE : {
                this.prefix = SQUARE_PREFIX;
                this.suffix = SQUARE_SUFFIX;
                break;
            }
            case PENTAGON : {
                this.prefix = PENTAGON_PREFIX;
                this.suffix = PENTAGON_SUFFIX;
                break;
            }
            case HEXAGON : {
                this.prefix = HEXAGON_PREFIX;
                this.suffix = HEXAGON_SUFFIX;
                break;
            }
            case SEPTAGON : {
                this.prefix = SEPTAGON_PREFIX;
                this.suffix = SEPTAGON_SUFFIX;
                break;
            }
            case OCTAGON : {
                this.prefix = OCTAGON_PREFIX;
                this.suffix = OCTAGON_SUFFIX;
                break;
            }
            case NONAGON : {
                this.prefix = NONAGON_PREFIX;
                this.suffix = NONAGON_SUFFIX;
                break;
            }
        }
    }

    public static int randomBackgroundColorShape() {
        int val = MathUtils.random(Shape.SQUARE);
        while(val == Shape.LINE) {
            val = MathUtils.random(Shape.SQUARE);
        }
        return val;
    }
}
