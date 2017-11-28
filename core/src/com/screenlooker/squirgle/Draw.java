package com.screenlooker.squirgle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static com.badlogic.gdx.graphics.Color.BLACK;

//TODO: Replace all instances of 2.575
public class Draw {
    public static final int THRESHOLD_MULTIPLIER = 4;
    public static final int LINE_WIDTH_DIVISOR = 8;
    public static final int NUM_BACKGROUND_COLOR_SHAPE_COLUMNS = 6;
    public static final float INPUT_DISTANCE_OFFSET = 1.5f;
    public static final float TARGET_DISTANCE_OFFSET = GameplayScreen.TARGET_RADIUS / 2.5f;

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
        colorListSpeed = game.camera.viewportWidth / 1536;
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

    public void drawPerimeter(Shape promptShape, ShapeRenderer shapeRenderer) {
        float opacity = 0f;
        float visibilityPoint = (3 * game.widthOrHeight) / 8;
        if(promptShape.getRadius() >= visibilityPoint) {
            opacity = (promptShape.getRadius() - visibilityPoint) / (game.widthOrHeight / 4);
        }
        shapeRenderer.setColor(new Color(0, 0, 0, opacity));
        shapeRenderer.circle(game.camera.viewportWidth / 2, game.camera.viewportHeight / 2, promptShape.getRadius());
    }

    //TODO: update as I make individual draw[Shape] methods more accurate
    public void drawPrompt(Shape promptShape, List<Shape> priorShapeList, boolean primaryShapeAtThreshold, Shape backgroundColorShape, boolean isTarget, ShapeRenderer shapeRenderer) {
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
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawPoint(promptShape.getCoordinates().x - xOffset,
                        promptShape.getCoordinates().y,
                        (promptShape.getRadius() / radiusOffset) / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if (promptShape.getShape() == Shape.LINE) {
            if (!priorShapeList.isEmpty()) {
                xOffset = promptShape.getRadius() - (promptShape.getLineWidth() * 1.7f);
                radiusOffset = 2;
            }
            drawLine(promptShape.getCoordinates().x - xOffset,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius() / radiusOffset,
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawLine(promptShape.getCoordinates().x - xOffset,
                        promptShape.getCoordinates().y,
                        (promptShape.getRadius() / radiusOffset) - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if (promptShape.getShape() == Shape.TRIANGLE) {
            drawTriangle(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawTriangle(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if (promptShape.getShape() == Shape.SQUARE) {
            drawSquare(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawSquare(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if(promptShape.getShape() == Shape.PENTAGON) {
            drawPentagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawPentagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if(promptShape.getShape() == Shape.HEXAGON) {
            drawHexagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawHexagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if(promptShape.getShape() == Shape.SEPTAGON) {
            drawSeptagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawSeptagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if(promptShape.getShape() == Shape.OCTAGON) {
            drawOctagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawOctagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
        } else if(promptShape.getShape() == Shape.NONAGON) {
            drawNonagon(promptShape.getCoordinates().x,
                    promptShape.getCoordinates().y,
                    promptShape.getRadius(),
                    promptShape.getLineWidth(),
                    promptShape.getColor(),
                    shapeRenderer);
            if(!isTarget) {
                //We're dealing with center prompt shape, so we must set up its inner color
                drawNonagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                        promptShape.getLineWidth() / 2,
                        backgroundColorShape.getColor(),
                        shapeRenderer);
            }
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
                        shape.setRadius(priorShape.getRadius() - (priorShape.getLineWidth() * 1.1f));
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + (priorShape.getLineWidth() * 1.7f) + (priorShape.getLineWidth() / 2) - (priorShape.getRadius() - shape.getRadius()),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + priorShape.getRadius() + (priorShape.getLineWidth() / 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.TRIANGLE) {
                    shape.setRadius((priorShape.getRadius() / 2) - priorShape.getLineWidth());
                } else if (priorShape.getShape() != Shape.CIRCLE) {
                    //The inradius of a regular polygon with n > 3 sides is equal to its apothem, which is defined by [apothem = radius * MathUtils.cos(MathUtils.PI / sides)]
                    //TODO: make this more accurate. 1.4f isn't cutting it for all shapes.
                    shape.setRadius((float) (priorShape.getRadius() * MathUtils.cos(MathUtils.PI / (priorShape.getShape() + 1))) - (1.4f * priorShape.getLineWidth()));
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
                x - (MathUtils.sinDeg(60) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(60) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine((float) (x - (MathUtils.sinDeg(60) * (radius - lineWidth))),
                y - (MathUtils.cosDeg(60) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(60) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(60) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine((float) (x + (MathUtils.sinDeg(60) * (radius - lineWidth))),
                y - (MathUtils.cosDeg(60) * (radius - lineWidth)),
                x,
                y + radius - lineWidth,
                lineWidth);
        drawPoint(x, y + radius - lineWidth, lineWidth, color, shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(60) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(60) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(60) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(60) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
    }

    //TODO: Consolidate square onward into single method
    public void drawSquare(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(45) * (radius - lineWidth)),
                x - (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(45) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(45) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(45) * (radius - lineWidth)),
                x - (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(45) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(45) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawPentagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 5);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 5; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i));
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i));
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)));
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawHexagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 6);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 6; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i));
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i));
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)));
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawSeptagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 7);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 7; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i));
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i));
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)));
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawOctagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 8);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 8; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i));
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i));
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)));
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawNonagon(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 9);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 9; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i));
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)));
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i));
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)));
            //TODO: Consider adding a constant to the (theta * i) portion to alter the rotation of the shape
            shapeRenderer.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color,
                    shapeRenderer);
        }
    }

    public void drawInputButtons(Squirgle game, ShapeRenderer shapeRenderer) {
        if(game.base >= 1) {
            //Point
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_POINT_SPAWN.x, GameplayScreen.INPUT_POINT_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawPoint(GameplayScreen.INPUT_POINT_SPAWN.x, GameplayScreen.INPUT_POINT_SPAWN.y, GameplayScreen.INPUT_RADIUS / 2, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 2) {
            //Line
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_LINE_SPAWN.x, GameplayScreen.INPUT_LINE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawLine(GameplayScreen.INPUT_LINE_SPAWN.x, GameplayScreen.INPUT_LINE_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 3) {
            //Triangle
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN.x, GameplayScreen.INPUT_TRIANGLE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawTriangle(GameplayScreen.INPUT_TRIANGLE_SPAWN.x, GameplayScreen.INPUT_TRIANGLE_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 4) {
            //Square
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_SQUARE_SPAWN.x, GameplayScreen.INPUT_SQUARE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawSquare(GameplayScreen.INPUT_SQUARE_SPAWN.x, GameplayScreen.INPUT_SQUARE_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 5) {
            //Pentagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_PENTAGON_SPAWN.x, GameplayScreen.INPUT_PENTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawPentagon(GameplayScreen.INPUT_PENTAGON_SPAWN.x, GameplayScreen.INPUT_PENTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 6) {
            //Hexagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawHexagon(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 7) {
            //Septagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawSeptagon(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 8) {
            //Octagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawOctagon(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 9) {
            //Nonagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawNonagon(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }
    }

    public void drawInputButtonsTutorial(int phase, ShapeRenderer shapeRenderer) {
        boolean allInputsVisible = phase > TutorialScreen.PHASE_FIVE;

        if(phase == TutorialScreen.PHASE_TWO || allInputsVisible) {
            //Point
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TutorialScreen.INPUT_POINT_SPAWN.x, TutorialScreen.INPUT_POINT_SPAWN.y, TutorialScreen.INPUT_RADIUS);
            drawPoint(TutorialScreen.INPUT_POINT_SPAWN.x, TutorialScreen.INPUT_POINT_SPAWN.y, TutorialScreen.INPUT_RADIUS / 2, Color.BLACK, shapeRenderer);
        }

        if(phase == TutorialScreen.PHASE_THREE || allInputsVisible) {
            //Line
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TutorialScreen.INPUT_LINE_SPAWN.x, TutorialScreen.INPUT_LINE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
            drawLine(TutorialScreen.INPUT_LINE_SPAWN.x, TutorialScreen.INPUT_LINE_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(phase == TutorialScreen.PHASE_FOUR || allInputsVisible) {
            //Triangle
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN.x, TutorialScreen.INPUT_TRIANGLE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
            drawTriangle(TutorialScreen.INPUT_TRIANGLE_SPAWN.x, TutorialScreen.INPUT_TRIANGLE_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(phase == TutorialScreen.PHASE_FIVE || allInputsVisible) {
            //Square
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TutorialScreen.INPUT_SQUARE_SPAWN.x, TutorialScreen.INPUT_SQUARE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
            drawSquare(TutorialScreen.INPUT_SQUARE_SPAWN.x, TutorialScreen.INPUT_SQUARE_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }
    }

    public void drawResultsInputButtons(Vector2 inputPlaySpawn, Vector2 inputHomeSpawn, Vector2 inputExitSpawn, ShapeRenderer shapeRenderer) {
        //Play
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputPlaySpawn.x, inputPlaySpawn.y, GameplayScreen.INPUT_RADIUS);

        //TODO: Make this triangle equilateral (consult the drawTriangle method above)
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.x - (MathUtils.sinDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR);
        shapeRenderer.rectLine((float) (inputPlaySpawn.x - (MathUtils.sinDeg(60) * (GameplayScreen.INPUT_RADIUS / 2))),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.x + (GameplayScreen.INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR);
        shapeRenderer.rectLine(inputPlaySpawn.x + (GameplayScreen.INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                inputPlaySpawn.x - (MathUtils.sinDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR);
        drawPoint(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);
        drawPoint(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (GameplayScreen.INPUT_RADIUS / 2)),
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);
        drawPoint(inputPlaySpawn.x + (GameplayScreen.INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);

        //Home
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputHomeSpawn.x, inputHomeSpawn.y, GameplayScreen.INPUT_RADIUS);
        drawTriangle(inputHomeSpawn.x,
                inputHomeSpawn.y + (GameplayScreen.INPUT_RADIUS / 3.6f),
                (GameplayScreen.INPUT_RADIUS / 2),
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);
        drawSquare(inputHomeSpawn.x,
                inputHomeSpawn.y - (GameplayScreen.INPUT_RADIUS / 3.6f),
                (GameplayScreen.INPUT_RADIUS / 2),
                (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR,
                Color.BLACK,
                shapeRenderer);

        //Exit
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputExitSpawn.x, inputExitSpawn.y, GameplayScreen.INPUT_RADIUS);

        drawSquare(inputExitSpawn.x, inputExitSpawn.y, (GameplayScreen.INPUT_RADIUS / 2), (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
    }

    public void drawEquation(Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth, ShapeRenderer shapeRenderer) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, GameplayScreen.INPUT_RADIUS, Color.BLACK, Color.BLACK, GameplayScreen.INPUT_RADIUS / 8, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        if(lastShapeTouched.getShape() == Shape.POINT) {
            spawnBegin = GameplayScreen.INPUT_POINT_SPAWN;
            if (lastPromptShape.getShape() + 1 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 1) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 1);
            }
        } else if(lastShapeTouched.getShape() == Shape.LINE) {
            spawnBegin = GameplayScreen.INPUT_LINE_SPAWN;
            if (lastPromptShape.getShape() + 2 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 2) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 2);
            }
        } else if(lastShapeTouched.getShape() == Shape.TRIANGLE) {
            spawnBegin = GameplayScreen.INPUT_TRIANGLE_SPAWN;
            if (lastPromptShape.getShape() + 3 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 3) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 3);
            }
        } else if(lastShapeTouched.getShape() == Shape.SQUARE) {
            spawnBegin = GameplayScreen.INPUT_SQUARE_SPAWN;
            if (lastPromptShape.getShape() + 4 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 4) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 4);
            }
        } else if(lastShapeTouched.getShape() == Shape.PENTAGON) {
            spawnBegin = GameplayScreen.INPUT_PENTAGON_SPAWN;
            if (lastPromptShape.getShape() + 5 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 5) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 5);
            }
        } else if(lastShapeTouched.getShape() == Shape.HEXAGON) {
            spawnBegin = GameplayScreen.INPUT_HEXAGON_SPAWN;
            if (lastPromptShape.getShape() + 6 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 6) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 6);
            }
        } else if(lastShapeTouched.getShape() == Shape.SEPTAGON) {
            spawnBegin = GameplayScreen.INPUT_SEPTAGON_SPAWN;
            if (lastPromptShape.getShape() + 7 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 7) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 7);
            }
        } else if(lastShapeTouched.getShape() == Shape.OCTAGON) {
            spawnBegin = GameplayScreen.INPUT_OCTAGON_SPAWN;
            if (lastPromptShape.getShape() + 8 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 8) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 8);
            }
        } else if(lastShapeTouched.getShape() == Shape.NONAGON) {
            spawnBegin = GameplayScreen.INPUT_NONAGON_SPAWN;
            if (lastPromptShape.getShape() + 9 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 9) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 9);
            }
        }

        //Set up the ending coordinates
        if(sum.getShape() == Shape.POINT) {
            spawnEnd = GameplayScreen.INPUT_POINT_SPAWN;
        } else if(sum.getShape() == Shape.LINE) {
            spawnEnd = GameplayScreen.INPUT_LINE_SPAWN;
        } else if(sum.getShape() == Shape.TRIANGLE) {
            spawnEnd = GameplayScreen.INPUT_TRIANGLE_SPAWN;
        } else if(sum.getShape() == Shape.SQUARE) {
            spawnEnd = GameplayScreen.INPUT_SQUARE_SPAWN;
        } else if(sum.getShape() == Shape.PENTAGON) {
            spawnEnd = GameplayScreen.INPUT_PENTAGON_SPAWN;
        } else if(sum.getShape() == Shape.HEXAGON) {
            spawnEnd = GameplayScreen.INPUT_HEXAGON_SPAWN;
        } else if(sum.getShape() == Shape.SEPTAGON) {
            spawnEnd = GameplayScreen.INPUT_SEPTAGON_SPAWN;
        } else if(sum.getShape() == Shape.OCTAGON) {
            spawnEnd = GameplayScreen.INPUT_OCTAGON_SPAWN;
        } else if(sum.getShape() == Shape.NONAGON) {
            spawnEnd = GameplayScreen.INPUT_NONAGON_SPAWN;
        }

        //Set up the plusSpawn coordinates
        if(lastShapeTouched.getShape() == sum.getShape()) {
            plusSpawn.x = spawnBegin.x + ((lastPromptShape.getCoordinates().x - spawnBegin.x) / 2) - (GameplayScreen.INPUT_RADIUS);
        } else {
            plusSpawn.x = spawnBegin.x + ((lastPromptShape.getCoordinates().x - spawnBegin.x) / 2);
        }
        plusSpawn.y = spawnBegin.y + ((lastPromptShape.getCoordinates().y - spawnBegin.y) / 2);

        //Set up the equalsSpawn coordinates
        if(lastShapeTouched.getShape() == sum.getShape()) {
            equalsSpawn.x = spawnEnd.x + ((lastPromptShape.getCoordinates().x - spawnEnd.x) / 2) + (GameplayScreen.INPUT_RADIUS);
        } else {
            equalsSpawn.x = spawnEnd.x + ((lastPromptShape.getCoordinates().x - spawnEnd.x) / 2);
        }
        equalsSpawn.y = spawnEnd.y + ((lastPromptShape.getCoordinates().y - spawnEnd.y) / 2);

        //Set up the equalsTargetSpawn coordinates
        if(sum.getShape() == lastTargetShape.getShape()) {
            equalsTargetSpawn.x = targetSpawn.x + ((lastPromptShape.getCoordinates().x - targetSpawn.x) / 2);
            equalsTargetSpawn.y = targetSpawn.y + ((lastPromptShape.getCoordinates().y - targetSpawn.y) / 2);
        }

        shapeRenderer.setColor(Color.WHITE);

        //Draw the circles
        shapeRenderer.circle(plusSpawn.x,
                plusSpawn.y,
                equationWidth);
        shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                lastPromptShape.getCoordinates().y,
                equationWidth);
        shapeRenderer.circle(equalsSpawn.x,
                equalsSpawn.y,
                equationWidth);
        if(sum.getShape() == lastTargetShape.getShape()) {
            shapeRenderer.circle(equalsTargetSpawn.x,
                    equalsTargetSpawn.y,
                    equationWidth);
        }

        //Draw the lines
        shapeRenderer.rectLine(spawnBegin, plusSpawn, equationWidth * 2);
        shapeRenderer.rectLine(plusSpawn, lastPromptShape.getCoordinates(), equationWidth * 2);
        shapeRenderer.rectLine(lastPromptShape.getCoordinates(), equalsSpawn, equationWidth * 2);
        shapeRenderer.rectLine(equalsSpawn, spawnEnd, equationWidth * 2);
        if(sum.getShape() == lastTargetShape.getShape()) {
            shapeRenderer.rectLine(lastPromptShape.getCoordinates(), equalsTargetSpawn, equationWidth * 2);
            shapeRenderer.rectLine(equalsTargetSpawn, targetSpawn, equationWidth * 2);
        }

        //Draw the plus symbol
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / 8, Color.BLACK, shapeRenderer);

        //Draw the equals symbol(s)
        drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / 8, Color.BLACK, shapeRenderer);
        if(sum.getShape() == lastTargetShape.getShape()) {
            drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / 8, Color.BLACK, shapeRenderer);
        }

        //Draw the last prompt shape
        lastPromptShape.setRadius(equationWidth);
        lastPromptShape.setLineWidth(equationWidth / 8);
        lastPromptShape.setFillColor(Color.WHITE);
        drawShape(lastPromptShape, shapeRenderer);
    }

    public void drawEquationTutorial(Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth, ShapeRenderer shapeRenderer) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, TutorialScreen.INPUT_RADIUS, Color.BLACK, Color.BLACK, TutorialScreen.INPUT_RADIUS / 8, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        if(lastShapeTouched.getShape() == Shape.POINT) {
            spawnBegin = TutorialScreen.INPUT_POINT_SPAWN;
            if (lastPromptShape.getShape() + 1 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 1) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 1);
            }
        } else if(lastShapeTouched.getShape() == Shape.LINE) {
            spawnBegin = TutorialScreen.INPUT_LINE_SPAWN;
            if (lastPromptShape.getShape() + 2 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 2) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 2);
            }
        } else if(lastShapeTouched.getShape() == Shape.TRIANGLE) {
            spawnBegin = TutorialScreen.INPUT_TRIANGLE_SPAWN;
            if (lastPromptShape.getShape() + 3 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 3) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 3);
            }
        } else if(lastShapeTouched.getShape() == Shape.SQUARE) {
            spawnBegin = TutorialScreen.INPUT_SQUARE_SPAWN;
            if (lastPromptShape.getShape() + 4 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 4) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 4);
            }
        } else if(lastShapeTouched.getShape() == Shape.PENTAGON) {
            spawnBegin = TutorialScreen.INPUT_PENTAGON_SPAWN;
            if (lastPromptShape.getShape() + 5 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 5) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 5);
            }
        } else if(lastShapeTouched.getShape() == Shape.HEXAGON) {
            spawnBegin = TutorialScreen.INPUT_HEXAGON_SPAWN;
            if (lastPromptShape.getShape() + 6 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 6) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 6);
            }
        } else if(lastShapeTouched.getShape() == Shape.SEPTAGON) {
            spawnBegin = TutorialScreen.INPUT_SEPTAGON_SPAWN;
            if (lastPromptShape.getShape() + 7 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 7) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 7);
            }
        } else if(lastShapeTouched.getShape() == Shape.OCTAGON) {
            spawnBegin = TutorialScreen.INPUT_OCTAGON_SPAWN;
            if (lastPromptShape.getShape() + 8 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 8) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 8);
            }
        } else if(lastShapeTouched.getShape() == Shape.NONAGON) {
            spawnBegin = TutorialScreen.INPUT_NONAGON_SPAWN;
            if (lastPromptShape.getShape() + 9 >= game.base) {
                sum.setShape((lastPromptShape.getShape() + 9) - game.base);
            } else {
                sum.setShape(lastPromptShape.getShape() + 9);
            }
        }

        //Set up the ending coordinates
        if(sum.getShape() == Shape.POINT) {
            spawnEnd = TutorialScreen.INPUT_POINT_SPAWN;
        } else if(sum.getShape() == Shape.LINE) {
            spawnEnd = TutorialScreen.INPUT_LINE_SPAWN;
        } else if(sum.getShape() == Shape.TRIANGLE) {
            spawnEnd = TutorialScreen.INPUT_TRIANGLE_SPAWN;
        } else if(sum.getShape() == Shape.SQUARE) {
            spawnEnd = TutorialScreen.INPUT_SQUARE_SPAWN;
        } else if(sum.getShape() == Shape.PENTAGON) {
            spawnEnd = TutorialScreen.INPUT_PENTAGON_SPAWN;
        } else if(sum.getShape() == Shape.HEXAGON) {
            spawnEnd = TutorialScreen.INPUT_HEXAGON_SPAWN;
        } else if(sum.getShape() == Shape.SEPTAGON) {
            spawnEnd = TutorialScreen.INPUT_SEPTAGON_SPAWN;
        } else if(sum.getShape() == Shape.OCTAGON) {
            spawnEnd = TutorialScreen.INPUT_OCTAGON_SPAWN;
        } else if(sum.getShape() == Shape.NONAGON) {
            spawnEnd = TutorialScreen.INPUT_NONAGON_SPAWN;
        }

        //Set up the plusSpawn coordinates
        if(lastShapeTouched.getShape() == sum.getShape()) {
            plusSpawn.x = spawnBegin.x + ((lastPromptShape.getCoordinates().x - spawnBegin.x) / 2) - (TutorialScreen.INPUT_RADIUS);
        } else {
            plusSpawn.x = spawnBegin.x + ((lastPromptShape.getCoordinates().x - spawnBegin.x) / 2);
        }
        plusSpawn.y = spawnBegin.y + ((lastPromptShape.getCoordinates().y - spawnBegin.y) / 2);

        //Set up the equalsSpawn coordinates
        if(lastShapeTouched.getShape() == sum.getShape()) {
            equalsSpawn.x = spawnEnd.x + ((lastPromptShape.getCoordinates().x - spawnEnd.x) / 2) + (TutorialScreen.INPUT_RADIUS);
        } else {
            equalsSpawn.x = spawnEnd.x + ((lastPromptShape.getCoordinates().x - spawnEnd.x) / 2);
        }
        equalsSpawn.y = spawnEnd.y + ((lastPromptShape.getCoordinates().y - spawnEnd.y) / 2);

        //Set up the equalsTargetSpawn coordinates
        if(sum.getShape() == lastTargetShape.getShape()) {
            equalsTargetSpawn.x = targetSpawn.x + ((lastPromptShape.getCoordinates().x - targetSpawn.x) / 2);
            equalsTargetSpawn.y = targetSpawn.y + ((lastPromptShape.getCoordinates().y - targetSpawn.y) / 2);
        }

        shapeRenderer.setColor(Color.WHITE);

        //Draw the circles
        shapeRenderer.circle(plusSpawn.x,
                plusSpawn.y,
                equationWidth);
        shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                lastPromptShape.getCoordinates().y,
                equationWidth);
        shapeRenderer.circle(equalsSpawn.x,
                equalsSpawn.y,
                equationWidth);
        if(sum.getShape() == lastTargetShape.getShape()) {
            shapeRenderer.circle(equalsTargetSpawn.x,
                    equalsTargetSpawn.y,
                    equationWidth);
        }

        //Draw the lines
        shapeRenderer.rectLine(spawnBegin, plusSpawn, equationWidth * 2);
        shapeRenderer.rectLine(plusSpawn, lastPromptShape.getCoordinates(), equationWidth * 2);
        shapeRenderer.rectLine(lastPromptShape.getCoordinates(), equalsSpawn, equationWidth * 2);
        shapeRenderer.rectLine(equalsSpawn, spawnEnd, equationWidth * 2);
        if(sum.getShape() == lastTargetShape.getShape()) {
            shapeRenderer.rectLine(lastPromptShape.getCoordinates(), equalsTargetSpawn, equationWidth * 2);
            shapeRenderer.rectLine(equalsTargetSpawn, targetSpawn, equationWidth * 2);
        }

        //Draw the plus symbol
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / 8, Color.BLACK, shapeRenderer);

        //Draw the equals symbol(s)
        drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / 8, Color.BLACK, shapeRenderer);
        if(sum.getShape() == lastTargetShape.getShape()) {
            drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / 8, Color.BLACK, shapeRenderer);
        }

        //Draw the last prompt shape
        lastPromptShape.setRadius(equationWidth);
        lastPromptShape.setLineWidth(equationWidth / 8);
        lastPromptShape.setFillColor(Color.WHITE);
        drawShape(lastPromptShape, shapeRenderer);
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
                backgroundColorShape.setLineWidth(GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR);
                backgroundColorShape.setCoordinates(new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));

                backgroundColorShapeList.remove(backgroundColorShapeList.size() - 1);
                backgroundColorShapeList.add(0,
                        new Shape(Shape.SQUARE,
                                newRadius,
                                Color.WHITE,
                                ColorUtils.randomColor(),
                                newRadius / LINE_WIDTH_DIVISOR,
                                new Vector2(GameplayScreen.TARGET_RADIUS + ((game.camera.viewportWidth - (GameplayScreen.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)),
                                        (game.camera.viewportHeight - (GameplayScreen.INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (GameplayScreen.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)))));
            }
        }
    }
    public void drawBackgroundColorShapeListTutorial(List<Shape> backgroundColorShapeList, Shape backgroundColorShape, Color clearColor, ShapeRenderer shapeRenderer) {
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
                backgroundColorShape.setLineWidth(TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR);
                backgroundColorShape.setCoordinates(new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));

                backgroundColorShapeList.remove(backgroundColorShapeList.size() - 1);
                backgroundColorShapeList.add(0,
                        new Shape(Shape.SQUARE,
                                newRadius,
                                Color.WHITE,
                                ColorUtils.randomColor(),
                                newRadius / LINE_WIDTH_DIVISOR,
                                new Vector2(TutorialScreen.TARGET_RADIUS + ((game.camera.viewportWidth - (TutorialScreen.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)),
                                        (game.camera.viewportHeight - (TutorialScreen.INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (TutorialScreen.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)))));
            }
        }
    }

    public void drawTimeline(float leftX, float rightX, float y, float lineWidth, ShapeRenderer shapeRenderer) {
        shapeRenderer.rectLine(leftX, y, rightX, y, lineWidth);

        drawPoint(leftX, y, lineWidth, Color.WHITE, shapeRenderer);
        drawPoint(rightX, y, lineWidth, Color.WHITE, shapeRenderer);
    }

    public void drawTimelines(Shape promptShape, List<Shape> backgroundColorShapeList, ShapeRenderer shapeRenderer) {
        Shape firstColorShape = backgroundColorShapeList.get(0);
        Shape lastColorShape = backgroundColorShapeList.get(backgroundColorShapeList.size() - 1);
        Shape lowColorShape = backgroundColorShapeList.get(3);
        float firstX = firstColorShape.getCoordinates().x;
        float secondX = firstColorShape.getCoordinates().x;
        float thirdX = firstColorShape.getCoordinates().x;

        if(promptShape.getRadius() <= game.fourthOfScreen) {
            firstX = firstColorShape.getCoordinates().x;
        } else {
            firstX = firstColorShape.getCoordinates().x + ((lastColorShape.getCoordinates().x - firstColorShape.getCoordinates().x) * ((promptShape.getRadius() - game.fourthOfScreen) / (game.thirdOfScreen - game.fourthOfScreen)));
        }

        if(promptShape.getRadius() <= game.thirdOfScreen) {
            secondX = firstColorShape.getCoordinates().x;
        } else {
            secondX = firstColorShape.getCoordinates().x + ((lastColorShape.getCoordinates().x - firstColorShape.getCoordinates().x) * ((promptShape.getRadius() - game.thirdOfScreen) / (game.fiveTwelfthsOfScreen - game.thirdOfScreen)));
        }

        if(promptShape.getRadius() <= game.fiveTwelfthsOfScreen) {
            thirdX = firstColorShape.getCoordinates().x;
        } else {
            thirdX = firstColorShape.getCoordinates().x + ((lastColorShape.getCoordinates().x - firstColorShape.getCoordinates().x) * ((promptShape.getRadius() - game.fiveTwelfthsOfScreen) / ((game.widthOrHeight / 2) - game.fiveTwelfthsOfScreen)));
        }

        if(promptShape.getRadius() <= game.thirdOfScreen) {
            drawTimeline(firstX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 2),
                    lowColorShape.getLineWidth() * 6,
                    shapeRenderer);
            drawTimeline(secondX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 4),
                    lowColorShape.getLineWidth() * 6,
                    shapeRenderer);
            drawTimeline(thirdX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 6),
                    lowColorShape.getLineWidth() * 6,
                    shapeRenderer);
        } else if(promptShape.getRadius() <= game.fiveTwelfthsOfScreen) {
            drawTimeline(secondX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 2),
                    lowColorShape.getLineWidth() * 6,
                    shapeRenderer);
            drawTimeline(thirdX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 4),
                    lowColorShape.getLineWidth() * 6,
                    shapeRenderer);
        } else {
            drawTimeline(thirdX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 2),
                    lowColorShape.getLineWidth() * 6,
                    shapeRenderer);
        }
    }

    public void drawTargetSemicircle(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, game.camera.viewportHeight, GameplayScreen.TARGET_RADIUS);
    }

    public void drawTargetSemicircleTutorial(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, game.camera.viewportHeight, TutorialScreen.TARGET_RADIUS);
    }

    public void drawScoreTriangle(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.triangle(game.camera.viewportWidth,
                game.camera.viewportHeight,
                game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS, game.camera.viewportHeight,
                game.camera.viewportWidth,
                game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS);
    }

    public void drawScoreTriangleTutorial(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.triangle(game.camera.viewportWidth,
                game.camera.viewportHeight,
                game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS, game.camera.viewportHeight,
                game.camera.viewportWidth,
                game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS);
    }

    public void drawPauseInput(Squirgle game) {
        float inputRadius = game.camera.viewportWidth / 20;
        float lineRadius = inputRadius / 2;

        drawPoint(game.camera.viewportWidth, game.camera.viewportHeight / 2, inputRadius, Color.BLACK, game.shapeRendererFilled);
        drawLine(game.camera.viewportWidth - (2 * (inputRadius / 3)),
                game.camera.viewportHeight / 2,
                lineRadius,
                lineRadius / 8,
                Color.WHITE,
                game.shapeRendererFilled);
        drawLine(game.camera.viewportWidth - (inputRadius / 3),
                game.camera.viewportHeight / 2,
                lineRadius,
                lineRadius / 8,
                Color.WHITE,
                game.shapeRendererFilled);

    }

    public void drawTouchDownPoints(List<Shape> touchDownShapeList, ShapeRenderer shapeRenderer) {
        for (int i = 0; i < touchDownShapeList.size(); i++) {
            Shape shape = touchDownShapeList.get(i);
            if (shape.getRadius() > GameplayScreen.INPUT_RADIUS) {
                touchDownShapeList.remove(shape);
            } else {
                drawShape(shape, shapeRenderer);
                shape.setRadius(shape.getRadius() + 1);
            }
        }
    }

    public void drawPlus(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);

        //Draw circular edges
        shapeRenderer.circle(x - radius + (lineWidth / 2), y, lineWidth / 2);
        shapeRenderer.circle(x + radius - (lineWidth / 2), y, lineWidth / 2);
        shapeRenderer.circle(x, y - radius + (lineWidth / 2), lineWidth / 2);
        shapeRenderer.circle(x, y + radius - (lineWidth / 2), lineWidth / 2);

        //Draw rectangles
        shapeRenderer.rectLine(x - radius + (lineWidth / 2), y, x + radius - (lineWidth / 2), y, lineWidth);
        shapeRenderer.rectLine(x, y - radius + (lineWidth / 2), x, y + radius - (lineWidth / 2), lineWidth);
    }

    public void drawEquals(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);

        //Draw circular edges
        shapeRenderer.circle(x - (radius / 2) + (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), lineWidth / 2);
        shapeRenderer.circle(x - (radius / 2) + (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), lineWidth / 2);
        shapeRenderer.circle(x + (radius / 2) - (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), lineWidth / 2);
        shapeRenderer.circle(x + (radius / 2) - (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), lineWidth / 2);

        //Draw rectangles
        shapeRenderer.rectLine(x - (radius / 2) + (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), x + (radius / 2) - (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), lineWidth);
        shapeRenderer.rectLine(x - (radius / 2) + (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), x + (radius / 2) - (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), lineWidth);
    }

    public void drawWrench(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(x - (radius / 2), y - (radius / 2), x + (radius / 2), y + (radius / 2), lineWidth);
        shapeRenderer.circle(x - (radius / 2), y - (radius / 2), lineWidth);
        shapeRenderer.circle(x + (radius / 2), y + (radius / 2), lineWidth);

        shapeRenderer.setColor(color);
        shapeRenderer.rect(x - (radius / 2) - (lineWidth / 2), y - (radius / 2) - lineWidth, lineWidth, lineWidth);
        shapeRenderer.rect(x + (radius / 2) - (lineWidth / 2), y + (radius / 2), lineWidth, lineWidth);
    }

    public void drawPlayButton(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                x + radius - lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x + radius - lineWidth,
                y,
                x - (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + radius - lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawModulo(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (radius / 2), y - (radius / 2), x + (radius / 2), y + (radius / 2), lineWidth);
        shapeRenderer.circle(x - (radius / 2), y - (radius / 2), lineWidth);
        shapeRenderer.circle(x + (radius / 2), y + (radius / 2), lineWidth);

        drawPoint(x - (radius / 4),
                y + (radius / 4),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (radius / 4),
                y - (radius / 4),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawSoundSymbol(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawPoint(x - (radius / 2) + (radius / 8), y, radius, Color.BLACK, shapeRenderer);
        drawPoint(x - (radius / 2) + (radius / 8) - 5, y, radius, color, shapeRenderer);

        drawPoint(x - (radius / 2) + (radius / 8), y, (3 * radius) / 4, Color.BLACK, shapeRenderer);
        drawPoint(x - (radius / 2) + (radius / 8) - 5, y, (3 * radius) / 4, color, shapeRenderer);

        drawPoint(x - (radius / 2) + (radius / 8), y, radius / 2, Color.BLACK, shapeRenderer);
        drawPoint(x - (radius / 2) + (radius / 8) - 5, y, radius / 2, color, shapeRenderer);

        drawPoint(x - (radius / 2) + (radius / 8), y, radius / 4, Color.BLACK, shapeRenderer);
    }

    public void drawColorSymbol(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawPoint(x, y, radius, color, shapeRenderer);

        drawPoint(x - (radius / 2), y + ((3 * radius) / 4), radius / 4, Color.WHITE, shapeRenderer);

        drawPoint(x, y - radius + (radius / 8), radius / 8, Color.FIREBRICK, shapeRenderer);
        drawPoint(x + radius - (radius / 2.575f), y - radius + (radius / 2.575f), radius / 8, Color.CYAN, shapeRenderer); //TODO: Make this one more accurate
        drawPoint(x + radius - (radius / 8), y, radius / 8, Color.MAGENTA, shapeRenderer);
    }

    public void drawWiFiSymbol(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawPoint(x + (radius / 2.575f) - (radius / 8), y - (radius / 2.575f), radius, color, shapeRenderer);
        drawPoint(x + (radius / 2.575f) - (radius / 8) + 5, y - (radius / 2.575f) - (radius / 8), radius, Color.WHITE, shapeRenderer);

        drawPoint(x + (radius / 2.575f) - (radius / 8), y - (radius / 2.575f), (3 * radius) / 4, color, shapeRenderer);
        drawPoint(x + (radius / 2.575f) - (radius / 8) + 5, y - (radius / 2.575f) - (radius / 8), (3 * radius) / 4, Color.WHITE, shapeRenderer);

        drawPoint(x + (radius / 2.575f) - (radius / 8), y - (radius / 2.575f), radius / 2, color, shapeRenderer);
        drawPoint(x + (radius / 2.575f) - (radius / 8) + 5, y - (radius / 2.575f) - (radius / 8), radius / 2, Color.WHITE, shapeRenderer);

        drawPoint(x + (radius / 2.575f) - (radius / 8), y - (radius / 2.575f), radius / 4, color, shapeRenderer);
    }

    public void drawBackButton(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                x - radius + lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x - radius + lineWidth,
                y,
                x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                x + (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth);
        drawPoint(x + (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - radius + lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawFace(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawPoint(x, y, radius, color, shapeRenderer);

        drawPoint(x - (radius / 2), y + (radius / 2), radius / 8, Color.WHITE, shapeRenderer);
        drawPoint(x + (radius / 2), y +(radius / 2), radius / 8, Color.WHITE, shapeRenderer);
    }

    public void drawX(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x + (radius / 2.575f),
                y + (radius / 2.575f),
                (float) (x - (radius / 2.575)),
                (float) (y - (radius / 2.575)),
                lineWidth);
        shapeRenderer.rectLine((float) (x - (radius / 2.575f)),
                y + (radius / 2.575f),
                x + (radius / 2.575f),
                (float) (y - (radius / 2.575f)),
                lineWidth);

        drawPoint(x + (radius / 2.575f), y + (radius / 2.575f), lineWidth, color, shapeRenderer);
        drawPoint(x - (radius / 2.575f), y + (radius / 2.575f), lineWidth, color, shapeRenderer);
        drawPoint(x - (radius / 2.575f), y - (radius / 2.575f), lineWidth, color, shapeRenderer);
        drawPoint(x + (radius / 2.575f), y - (radius / 2.575f), lineWidth, color, shapeRenderer);
    }

    public void drawChevronLeft(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                x - radius + lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x - radius + lineWidth,
                y,
                x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth);
        drawPoint(x + (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - radius + lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawChevronRight(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                x + radius - lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x + radius - lineWidth,
                y,
                x - (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(60) * radius),
                y + (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(60) * radius),
                y - (MathUtils.cosDeg(60) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + radius - lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawQuestionMark(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(x, y + (radius / 4), radius / 4);
        shapeRenderer.rectLine(x, y + (radius / 4), x, y - (radius / 4), lineWidth);
        shapeRenderer.rectLine(x, y - ((3 * radius) / 8), x, y - (radius / 2), lineWidth);

        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y + (radius / 4), radius / 8);
        shapeRenderer.rectLine(x - ((5 * lineWidth) / 4), y + (radius / 4), x - ((5 * lineWidth) / 4), y, ((3 * lineWidth) / 2));
    }
}
