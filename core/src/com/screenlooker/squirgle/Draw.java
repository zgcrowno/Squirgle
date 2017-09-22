package com.screenlooker.squirgle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static com.badlogic.gdx.graphics.Color.BLACK;

public class Draw {
    public static final int INPUT_RADIUS = 40;
    public static final int TARGET_RADIUS = 150;
    public static final int THRESHOLD_MULTIPLIER = 4;
    public static final int LINE_WIDTH_DIVISOR = 8;
    public static final int NUM_BACKGROUND_COLOR_SHAPE_COLUMNS = 6;
    public static final float INPUT_DISTANCE_OFFSET = 1.5f;
    public static final float TARGET_DISTANCE_OFFSET = TARGET_RADIUS / 2.5f;

    private float radiusOffset;
    private float colorSpeed;
    private float colorListSpeed;
    private Vector2 targetSpawn;
    private Squirgle game;

    public Draw() {
        radiusOffset = 0;
        colorSpeed = 100;
        colorListSpeed = 0.5f;
        targetSpawn = new Vector2();
        game = new Squirgle();
    }

    public Draw(Squirgle game) {
        radiusOffset = 1.45f;
        colorSpeed = 100;
        colorListSpeed = 0.5f;
        targetSpawn = new Vector2(TARGET_DISTANCE_OFFSET, game.camera.viewportHeight - TARGET_DISTANCE_OFFSET);
        this.game = game;
    }

    public float getRadiusOffset() {
        return radiusOffset;
    }

    public void setRadiusOffset(float radiusOffset) {
        this.radiusOffset = radiusOffset;
    }

    public float getColorSpeed() {
        return colorSpeed;
    }

    public void setColorSpeed(float colorSpeed) {
        this.colorSpeed = colorSpeed;
    }

    public float getColorListSpeed() {
        return colorListSpeed;
    }

    public void setColorListSpeed(float colorListSpeed) {
        this.colorListSpeed = colorListSpeed;
    }

    public Vector2 getTargetSpawn() {
        return targetSpawn;
    }

    public void setTargetSpawn(Vector2 targetSpawn) {
        this.targetSpawn = targetSpawn;
    }

    public void drawPrompt(Shape promptShape, List<Shape> priorShapeList, boolean primaryShapeAtThreshold, ShapeRenderer shapeRenderer) {
        float xOffset = 0;
        float radiusOffset = 1;
        if (promptShape.getShape() == Shape.CIRCLE) {
            drawPoint(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if (promptShape.getShape() == Shape.POINT) {
            if (!priorShapeList.isEmpty()) {
                xOffset = promptShape.getRadius() / 2;
                radiusOffset = 2;
            }
            drawPoint(promptShape.getCoordinates().x - xOffset,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius() / radiusOffset,
                    promptShape.getColor(),
                    shapeRenderer);
        } else if (promptShape.getShape() == Shape.LINE) {
            if (!priorShapeList.isEmpty()) {
                xOffset = promptShape.getRadius();
            }
            drawLine(promptShape.getCoordinates().x - xOffset,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if (promptShape.getShape() == Shape.TRIANGLE) {
            drawTriangle(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if (promptShape.getShape() == Shape.SQUARE) {
            drawSquare(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if(promptShape.getShape() == Shape.PENTAGON) {
            drawPentagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if(promptShape.getShape() == Shape.HEXAGON) {
            drawHexagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if(promptShape.getShape() == Shape.SEPTAGON) {
            drawSeptagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if(promptShape.getShape() == Shape.OCTAGON) {
            drawOctagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        } else if(promptShape.getShape() == Shape.NONAGON) {
            drawNonagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
        }
    }

    public void drawShapes(List<Shape> priorShapeList, Shape promptShape, boolean primaryShapeAtThreshold, Squirgle game, ShapeRenderer shapeRenderer) {
        if (!priorShapeList.isEmpty()) {
            //We go through the priorShapeList "backwards" because we want to draw the smaller shapes (those added earliest) last
            //so that they'll overlay the larger shapes.
            for (int i = priorShapeList.size() - 1; i >= 0; i--) {
                Shape shape = priorShapeList.get(i);
                Shape priorShape;

                //Determine whether to compare current shape with prompt or next shape in list
                if (i == priorShapeList.size() - 1) {
                    //Shape is at end of list, so compare with prompt
                    priorShape = promptShape;
                } else {
                    //Shape is not at end of list, so compare with next shape in list
                    priorShape = priorShapeList.get(i + 1);
                }

                //Ensure that current element of list initially has same radius and coordinates as prior shape
                shape.setRadius(priorShape.getRadius());
                if (!primaryShapeAtThreshold) {
                    shape.setLineWidth(priorShape.getRadius() / LINE_WIDTH_DIVISOR);
                }
                shape.setCoordinates(priorShape.getCoordinates());

                //Lines and points must be shrunken and re-coordinated when encircled, except if the line is the first member of the list
                if (shape.getShape() == Shape.POINT) {
                    shape.setRadius(priorShape.getRadius() / 2);
                    if (i != 0) {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                } else if (shape.getShape() == Shape.LINE) {
                    if (i != 0) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                }

                //Determine radius/coordinates of current shape based on prior one
                //Also, current shape is guaranteed to be a circle if any of these conditions hold
                if (priorShape.getShape() == Shape.POINT) {
                    if (priorShape == promptShape) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + shape.getRadius(),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + (shape.getRadius() * 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.LINE) {
                    if (priorShape == promptShape) {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + (priorShape.getLineWidth() / 2),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + priorShape.getRadius() + (priorShape.getLineWidth() / 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.TRIANGLE) {
                    shape.setRadius((priorShape.getRadius() / 2) - priorShape.getLineWidth());
                } else if (priorShape.getShape() != Shape.CIRCLE) {
                    //The inradius of a regular polygon with n > 3 sides is equal to its apothem, which is defined by [apothem = radius * MathUtils.cos(MathUtils.PI / sides)]
                    shape.setRadius((float) (priorShape.getRadius() * MathUtils.cos(MathUtils.PI / (priorShape.getShape() + 1))) - (priorShape.getLineWidth()));
                }

                drawShape(shape, shapeRenderer);
            }
        }
    }

    public void drawShape(Shape shape, ShapeRenderer shapeRenderer) {
        //TODO: Fix fill colors for shapes past square
        if (shape.getShape() == Shape.POINT) {
            if (shape.getFillColor() != null) {
                drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getFillColor(), shapeRenderer);
            }
            drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getColor(), shapeRenderer);
        } else if (shape.getShape() == Shape.LINE) {
            if (shape.getFillColor() != null) {
                drawLine(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getFillColor(),
                        shapeRenderer);
            }
            drawLine(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if (shape.getShape() == Shape.TRIANGLE) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.triangle(shape.getCoordinates().x,
                        shape.getCoordinates().y + shape.getRadius() - shape.getLineWidth(),
                        shape.getCoordinates().x - (MathUtils.sinDeg(60) * shape.getRadius()),
                        shape.getCoordinates().y - (MathUtils.cosDeg(60) * shape.getRadius()),
                        shape.getCoordinates().x + (MathUtils.sinDeg(60) * shape.getRadius()),
                        shape.getCoordinates().y - (MathUtils.cosDeg(60) * shape.getRadius()));
            }
            drawTriangle(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if (shape.getShape() == Shape.SQUARE) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x,
                        shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                        shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                        shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawSquare(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if(shape.getShape() == Shape.PENTAGON) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x,
                        shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                        shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                        shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawPentagon(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if(shape.getShape() == Shape.HEXAGON) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x,
                        shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                        shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                        shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawHexagon(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if(shape.getShape() == Shape.SEPTAGON) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x,
                        shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                        shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                        shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawSeptagon(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if(shape.getShape() == Shape.OCTAGON) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x,
                        shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                        shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                        shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawOctagon(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else if(shape.getShape() == Shape.NONAGON) {
            if (shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x,
                        shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                        shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                        shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawNonagon(shape.getCoordinates().x,
                    shape.getCoordinates().y,
                    shape.getRadius(),
                    shape.getLineWidth(),
                    shape.getColor(),
                    shapeRenderer);
        } else {
            shapeRenderer.setColor(shape.getColor());
            shapeRenderer.circle(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius());
        }
    }

    public void drawPoint(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    public void drawLine(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x, y - radius + lineWidth, x, y + radius - lineWidth, lineWidth);
        shapeRenderer.circle(x, y - radius + lineWidth, lineWidth);
        shapeRenderer.circle(x, y + radius - lineWidth, lineWidth);
    }

    public void drawTriangle(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x,
                y + radius - lineWidth,
                x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth);
        shapeRenderer.rectLine((float) (x - (MathUtils.sinDeg(60) * radius)),
                y - (MathUtils.cosDeg(60) * radius),
                x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth);
        shapeRenderer.rectLine((float) (x + (MathUtils.sinDeg(60) * radius)),
                y - (MathUtils.cosDeg(60) * radius),
                x,
                y + radius - lineWidth,
                lineWidth);
        drawPoint(x, y + radius - lineWidth, lineWidth, color, shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
    }

    //TODO: Consolidate square onward into single method
    public void drawSquare(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (radius / radiusOffset) + (lineWidth / 2),
                y - (radius / radiusOffset) + (lineWidth / 2),
                x - (radius / radiusOffset) + (lineWidth / 2),
                y + (radius / radiusOffset) - (lineWidth / 2),
                lineWidth);
        shapeRenderer.rectLine(x - (radius / radiusOffset) + (lineWidth / 2),
                y + (radius / radiusOffset) - (lineWidth / 2),
                x + (radius / radiusOffset) - (lineWidth / 2),
                y + (radius / radiusOffset) - (lineWidth / 2),
                lineWidth);
        shapeRenderer.rectLine(x + (radius / radiusOffset) - (lineWidth / 2),
                y + (radius / radiusOffset) - (lineWidth / 2),
                x + (radius / radiusOffset) - (lineWidth / 2),
                y - (radius / radiusOffset) + (lineWidth / 2),
                lineWidth);
        shapeRenderer.rectLine(x + (radius / radiusOffset) - (lineWidth / 2),
                y - (radius / radiusOffset) + (lineWidth / 2),
                x - (radius / radiusOffset) + (lineWidth / 2),
                y - (radius / radiusOffset) + (lineWidth / 2),
                lineWidth);
        drawPoint(x - (radius / radiusOffset) + (lineWidth / 2),
                y - (radius / radiusOffset) + (lineWidth / 2),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (radius / radiusOffset) + (lineWidth / 2),
                y + (radius / radiusOffset) - (lineWidth / 2),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (radius / radiusOffset) - (lineWidth / 2),
                y + (radius / radiusOffset) - (lineWidth / 2),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (radius / radiusOffset) - (lineWidth / 2),
                y - (radius / radiusOffset) + (lineWidth / 2),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawPentagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 5);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 5; i++) {
            float firstXOffset = radius * MathUtils.cos((float)(theta * i));
            float secondXOffset = radius * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = radius * MathUtils.sin((float)(theta * i));
            float secondYOffset = radius * MathUtils.sin((float)(theta * (i + 1)));
            float firstXLineWidthOffset = 1;
            float secondXLineWidthOffset = 1;
            float firstYLineWidthOffset = 1;
            float secondYLineWidthOffset = 1;
            if(firstXOffset < 0) {
                firstXLineWidthOffset = -1;
            } else if(firstXOffset == 0) {
                firstXLineWidthOffset = 0;
            }
            if(secondXOffset < 0) {
                secondXLineWidthOffset = -1;
            } else if(secondXOffset == 0) {
                secondXLineWidthOffset = 0;
            }
            if(firstYOffset < 0) {
                firstYLineWidthOffset = -1;
            } else if(firstYOffset == 0) {
                firstYLineWidthOffset = 0;
            }
            if(secondYOffset < 0) {
                secondYLineWidthOffset = -1;
            } else if(secondYOffset == 0) {
                secondYLineWidthOffset = 0;
            }
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    x + secondXOffset - (secondXLineWidthOffset * (lineWidth / 2)),
                    y + secondYOffset - (secondYLineWidthOffset * (lineWidth / 2)),
                    lineWidth);
            drawPoint(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawHexagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 6);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 6; i++) {
            float firstXOffset = radius * MathUtils.cos((float)(theta * i));
            float secondXOffset = radius * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = radius * MathUtils.sin((float)(theta * i));
            float secondYOffset = radius * MathUtils.sin((float)(theta * (i + 1)));
            float firstXLineWidthOffset = 1;
            float secondXLineWidthOffset = 1;
            float firstYLineWidthOffset = 1;
            float secondYLineWidthOffset = 1;
            if(firstXOffset < 0) {
                firstXLineWidthOffset = -1;
            } else if(firstXOffset == 0) {
                firstXLineWidthOffset = 0;
            }
            if(secondXOffset < 0) {
                secondXLineWidthOffset = -1;
            } else if(secondXOffset == 0) {
                secondXLineWidthOffset = 0;
            }
            if(firstYOffset < 0) {
                firstYLineWidthOffset = -1;
            } else if(firstYOffset == 0) {
                firstYLineWidthOffset = 0;
            }
            if(secondYOffset < 0) {
                secondYLineWidthOffset = -1;
            } else if(secondYOffset == 0) {
                secondYLineWidthOffset = 0;
            }
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    x + secondXOffset - (secondXLineWidthOffset * (lineWidth / 2)),
                    y + secondYOffset - (secondYLineWidthOffset * (lineWidth / 2)),
                    lineWidth);
            drawPoint(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawSeptagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 7);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 7; i++) {
            float firstXOffset = radius * MathUtils.cos((float)(theta * i));
            float secondXOffset = radius * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = radius * MathUtils.sin((float)(theta * i));
            float secondYOffset = radius * MathUtils.sin((float)(theta * (i + 1)));
            float firstXLineWidthOffset = 1;
            float secondXLineWidthOffset = 1;
            float firstYLineWidthOffset = 1;
            float secondYLineWidthOffset = 1;
            if(firstXOffset < 0) {
                firstXLineWidthOffset = -1;
            } else if(firstXOffset == 0) {
                firstXLineWidthOffset = 0;
            }
            if(secondXOffset < 0) {
                secondXLineWidthOffset = -1;
            } else if(secondXOffset == 0) {
                secondXLineWidthOffset = 0;
            }
            if(firstYOffset < 0) {
                firstYLineWidthOffset = -1;
            } else if(firstYOffset == 0) {
                firstYLineWidthOffset = 0;
            }
            if(secondYOffset < 0) {
                secondYLineWidthOffset = -1;
            } else if(secondYOffset == 0) {
                secondYLineWidthOffset = 0;
            }
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    x + secondXOffset - (secondXLineWidthOffset * (lineWidth / 2)),
                    y + secondYOffset - (secondYLineWidthOffset * (lineWidth / 2)),
                    lineWidth);
            drawPoint(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawOctagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 8);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 8; i++) {
            float firstXOffset = radius * MathUtils.cos((float)(theta * i));
            float secondXOffset = radius * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = radius * MathUtils.sin((float)(theta * i));
            float secondYOffset = radius * MathUtils.sin((float)(theta * (i + 1)));
            float firstXLineWidthOffset = 1;
            float secondXLineWidthOffset = 1;
            float firstYLineWidthOffset = 1;
            float secondYLineWidthOffset = 1;
            if(firstXOffset < 0) {
                firstXLineWidthOffset = -1;
            } else if(firstXOffset == 0) {
                firstXLineWidthOffset = 0;
            }
            if(secondXOffset < 0) {
                secondXLineWidthOffset = -1;
            } else if(secondXOffset == 0) {
                secondXLineWidthOffset = 0;
            }
            if(firstYOffset < 0) {
                firstYLineWidthOffset = -1;
            } else if(firstYOffset == 0) {
                firstYLineWidthOffset = 0;
            }
            if(secondYOffset < 0) {
                secondYLineWidthOffset = -1;
            } else if(secondYOffset == 0) {
                secondYLineWidthOffset = 0;
            }
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    x + secondXOffset - (secondXLineWidthOffset * (lineWidth / 2)),
                    y + secondYOffset - (secondYLineWidthOffset * (lineWidth / 2)),
                    lineWidth);
            drawPoint(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawNonagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 9);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 9; i++) {
            float firstXOffset = radius * MathUtils.cos((float)(theta * i));
            float secondXOffset = radius * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = radius * MathUtils.sin((float)(theta * i));
            float secondYOffset = radius * MathUtils.sin((float)(theta * (i + 1)));
            float firstXLineWidthOffset = 1;
            float secondXLineWidthOffset = 1;
            float firstYLineWidthOffset = 1;
            float secondYLineWidthOffset = 1;
            if(firstXOffset < 0) {
                firstXLineWidthOffset = -1;
            } else if(firstXOffset == 0) {
                firstXLineWidthOffset = 0;
            }
            if(secondXOffset < 0) {
                secondXLineWidthOffset = -1;
            } else if(secondXOffset == 0) {
                secondXLineWidthOffset = 0;
            }
            if(firstYOffset < 0) {
                firstYLineWidthOffset = -1;
            } else if(firstYOffset == 0) {
                firstYLineWidthOffset = 0;
            }
            if(secondYOffset < 0) {
                secondYLineWidthOffset = -1;
            } else if(secondYOffset == 0) {
                secondYLineWidthOffset = 0;
            }
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    x + secondXOffset - (secondXLineWidthOffset * (lineWidth / 2)),
                    y + secondYOffset - (secondYLineWidthOffset * (lineWidth / 2)),
                    lineWidth);
            drawPoint(x + firstXOffset - (firstXLineWidthOffset * (lineWidth / 2)),
                    y + firstYOffset - (firstYLineWidthOffset * (lineWidth / 2)),
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawInputButtons(Squirgle game, Vector2 inputPointSpawn, Vector2 inputLineSpawn, Vector2 inputTriangleSpawn, Vector2 inputSquareSpawn, Vector2 inputPentagonSpawn, Vector2 inputHexagonSpawn, Vector2 inputSeptagonSpawn, Vector2 inputOctagonSpawn, Vector2 inputNonagonSpawn, ShapeRenderer shapeRenderer) {
        if(game.base >= 1) {
            //Point
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, INPUT_RADIUS);
            drawPoint(inputPointSpawn.x, inputPointSpawn.y, INPUT_RADIUS / 2, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 2) {
            //Line
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y, INPUT_RADIUS);
            drawLine(inputLineSpawn.x, inputLineSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 3) {
            //Triangle
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputTriangleSpawn.x, inputTriangleSpawn.y, INPUT_RADIUS);
            drawTriangle(inputTriangleSpawn.x, inputTriangleSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 4) {
            //Square
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputSquareSpawn.x, inputSquareSpawn.y, INPUT_RADIUS);
            drawSquare(inputSquareSpawn.x, inputSquareSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 5) {
            //Pentagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputPentagonSpawn.x, inputPentagonSpawn.y, INPUT_RADIUS);
            drawPentagon(inputPentagonSpawn.x, inputPentagonSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 6) {
            //Hexagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputHexagonSpawn.x, inputHexagonSpawn.y, INPUT_RADIUS);
            drawHexagon(inputHexagonSpawn.x, inputHexagonSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 7) {
            //Septagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputSeptagonSpawn.x, inputSeptagonSpawn.y, INPUT_RADIUS);
            drawSeptagon(inputSeptagonSpawn.x, inputSeptagonSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 8) {
            //Octagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputOctagonSpawn.x, inputOctagonSpawn.y, INPUT_RADIUS);
            drawOctagon(inputOctagonSpawn.x, inputOctagonSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 9) {
            //Nonagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(inputNonagonSpawn.x, inputNonagonSpawn.y, INPUT_RADIUS);
            drawNonagon(inputNonagonSpawn.x, inputNonagonSpawn.y, INPUT_RADIUS, INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }
    }

    public void drawResultsInputButtons(Vector2 inputPlaySpawn, Vector2 inputHomeSpawn, Vector2 inputExitSpawn, ShapeRenderer shapeRenderer) {
        //Play
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputPlaySpawn.x, inputPlaySpawn.y, INPUT_RADIUS);

        //TODO: Make this triangle equilateral (consult the drawTriangle method above)
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR);
        shapeRenderer.rectLine((float) (inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2))),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.x + (INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR);
        shapeRenderer.rectLine(inputPlaySpawn.x + (INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR);
        drawPoint(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);
        drawPoint(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);
        drawPoint(inputPlaySpawn.x + (INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);

        //Home
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputHomeSpawn.x, inputHomeSpawn.y, INPUT_RADIUS);
        drawTriangle(inputHomeSpawn.x,
                inputHomeSpawn.y + (INPUT_RADIUS / 3.6f),
                (INPUT_RADIUS / 2),
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);
        drawSquare(inputHomeSpawn.x,
                inputHomeSpawn.y - (INPUT_RADIUS / 3.6f),
                (INPUT_RADIUS / 2),
                (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);

        //Exit
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputExitSpawn.x, inputExitSpawn.y, INPUT_RADIUS);

        drawSquare(inputExitSpawn.x, inputExitSpawn.y, (INPUT_RADIUS / 2), (INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
    }

    public void drawBackgroundColorShape(Shape backgroundColorShape, ShapeRenderer shapeRenderer) {
        drawShape(backgroundColorShape, shapeRenderer);
        if (backgroundColorShape.getRadius() < game.camera.viewportHeight * THRESHOLD_MULTIPLIER) {
            backgroundColorShape.setRadius(backgroundColorShape.getRadius() + colorSpeed);
        }

    }

    public void drawBackgroundColorShapeList(List<Shape> backgroundColorShapeList, Shape backgroundColorShape, Color clearColor, ShapeRenderer shapeRenderer) {
        for (int i = 0; i < backgroundColorShapeList.size(); i++) {
            Shape shape = backgroundColorShapeList.get(i);
            drawShape(shape, shapeRenderer);
            if (i == 0) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y - colorListSpeed));
            } else if (i == backgroundColorShapeList.size() - 1) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y + colorListSpeed));
            } else {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x + colorListSpeed, shape.getCoordinates().y));
            }
            if (backgroundColorShapeList.get(0).getCoordinates().y <= backgroundColorShapeList.get(1).getCoordinates().y) {
                float newRadius = shape.getRadius();

                //Prevent backgroundColorShapeList.get(0) from going too low on screen
                backgroundColorShapeList.get(0).setCoordinates(new Vector2(backgroundColorShapeList.get(0).getCoordinates().x,
                        backgroundColorShapeList.get(1).getCoordinates().y));

                clearColor.set(backgroundColorShape.getColor().r,
                        backgroundColorShape.getColor().g,
                        backgroundColorShape.getColor().b,
                        backgroundColorShape.getColor().a);
                backgroundColorShape.setShape(Shape.randomBackgroundColorShape()); //TODO: Enable triangles to be drawn pointing downward, so they're identifiable here.
                backgroundColorShape.setRadius(game.camera.viewportWidth / 2);
                backgroundColorShape.setColor(backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor());
                backgroundColorShape.setFillColor(backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor());
                backgroundColorShape.setLineWidth(Draw.INPUT_RADIUS / LINE_WIDTH_DIVISOR);
                backgroundColorShape.setCoordinates(new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));

                backgroundColorShapeList.remove(backgroundColorShapeList.size() - 1);
                backgroundColorShapeList.add(0,
                        new Shape(Shape.SQUARE,
                                newRadius,
                                Color.WHITE,
                                ColorUtils.randomPrimary(),
                                newRadius / LINE_WIDTH_DIVISOR,
                                new Vector2(Draw.TARGET_RADIUS + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)),
                                        (game.camera.viewportHeight - (Draw.INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)))));
            }
        }
    }

    public void drawTargetSemicircle(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, game.camera.viewportHeight, TARGET_RADIUS);
    }

    public void drawScoreTriangle(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.triangle(game.camera.viewportWidth,
                game.camera.viewportHeight,
                game.camera.viewportWidth - TARGET_RADIUS, game.camera.viewportHeight,
                game.camera.viewportWidth,
                game.camera.viewportHeight - TARGET_RADIUS);
    }

    public void drawTouchDownPoints(List<Shape> touchDownShapeList, ShapeRenderer shapeRenderer) {
        for (int i = 0; i < touchDownShapeList.size(); i++) {
            Shape shape = touchDownShapeList.get(i);
            if (shape.getRadius() > INPUT_RADIUS) {
                touchDownShapeList.remove(shape);
            } else {
                drawShape(shape, shapeRenderer);
                shape.setRadius(shape.getRadius() + 1);
            }
        }
    }
}
