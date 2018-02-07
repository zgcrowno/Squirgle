package com.screenlooker.squirgle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.screenlooker.squirgle.screen.GameplayScreen;
import com.screenlooker.squirgle.screen.TimeAttackScreen;
import com.screenlooker.squirgle.screen.TutorialScreen;
import com.screenlooker.squirgle.util.ColorUtils;

import java.sql.Time;
import java.util.List;

//TODO: Consolidate all tutorial stuff into standard gameplay screen
//TODO: Maybe remove lineWidth as parameter from all methods, since it seems to be formulated the same way from radius every time.
public class Draw {
    public static final int THRESHOLD_MULTIPLIER = 4;
    public static final int LINE_WIDTH_DIVISOR = 8;
    public static final int NUM_BACKGROUND_COLOR_SHAPE_COLUMNS = 6;
    public static final int NUM_ARC_SEGMENTS = 90;
    public static final int SIXTY_DEGREES = 60;
    public static final int NINETY_ONE_DEGREES = 91;
    public static final int SOUND_WAVE_DISTANCE = 5;
    public static final int FORTY_FIVE_DEGREES = 45;
    public static final float INPUT_DISTANCE_OFFSET = 1.5f;
    public static final float SHAPE_VISIBLE_DIVISOR = 1000;
    public static final float TRANCE_ARC_RADIUS_DIVISOR = 40;
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
        float visibilityPoint = (3 * game.widthOrHeight) / 8; //The point at which the perimeter will start to become visible
        if(promptShape.getRadius() >= visibilityPoint) {
            opacity = (promptShape.getRadius() - visibilityPoint) / (game.widthOrHeight / 4);
        }
        shapeRenderer.setColor(new Color(0, 0, 0, opacity));
        shapeRenderer.circle(game.camera.viewportWidth / 2, game.camera.viewportHeight / 2, promptShape.getRadius());
    }

    public void drawPrompt(Shape promptShape, List<Shape> priorShapeList, int targetShapesMatched, Shape backgroundColorShape, boolean isNonGameplay, boolean isTarget, ShapeRenderer shapeRenderer) {
        float xOffset = 0;
        float radiusOffset = 1;
        switch(promptShape.getShape()) {
            case Shape.CIRCLE :
                drawPoint(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getColor(),
                        shapeRenderer);
                break;
            case Shape.POINT :
                if (!priorShapeList.isEmpty()) {
                    xOffset = promptShape.getRadius() / 2;
                    radiusOffset = 2;
                }
                drawPoint(promptShape.getCoordinates().x - xOffset,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() / radiusOffset,
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawPoint(promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawPoint(promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.LINE :
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
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawLine(promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawLine(promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.TRIANGLE :
                drawTriangle(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawTriangle(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawTriangle(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.SQUARE :
                drawSquare(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawSquare(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawSquare(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.PENTAGON :
                drawPentagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawPentagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawPentagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.HEXAGON :
                drawHexagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawHexagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawHexagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.SEPTAGON :
                drawSeptagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawSeptagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawSeptagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.OCTAGON :
                drawOctagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawOctagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawOctagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
            case Shape.NONAGON :
                drawNonagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor(),
                        shapeRenderer);
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawNonagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    } else if(targetShapesMatched == 1) {
                        drawNonagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor(),
                                shapeRenderer);
                    }
                }
                break;
        }
    }

    public void drawShape(Shape shape, ShapeRenderer shapeRenderer) {
        switch(shape.getShape()) {
            case Shape.CIRCLE :
                shapeRenderer.setColor(shape.getColor());
                shapeRenderer.circle(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius());
                break;
            case Shape.POINT :
                if (shape.getFillColor() != null) {
                    //Draw fill color for background color shape
                    drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getFillColor(), shapeRenderer);
                }
                drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getColor(), shapeRenderer);
                break;
            case Shape.LINE :
                drawLine(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.TRIANGLE :
                drawTriangle(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.SQUARE :
                if (shape.getFillColor() != null) {
                    //Draw fill color for background color shape list
                    shapeRenderer.setColor(shape.getFillColor());
                    shapeRenderer.rectLine(shape.getCoordinates().x,
                            shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                            shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                            2 * ((shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2)));
                }
                drawSquare(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.PENTAGON :
                drawPentagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.HEXAGON :
                drawHexagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.SEPTAGON :
                drawSeptagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.OCTAGON :
                drawOctagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor(),
                        shapeRenderer);
                break;
            case Shape.NONAGON :
                drawNonagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor(),
                        shapeRenderer);
                break;
        }
    }

    public void drawShapes(List<Shape> priorShapeList, Shape promptShape, boolean primaryShapeAtThreshold, ShapeRenderer shapeRenderer) {
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
                    shape.setRadius((priorShape.getRadius() * MathUtils.cos(MathUtils.PI / (priorShape.getShape() + 1))) - (1.4f * priorShape.getLineWidth()));
                }

                //Only draw the shape if it's large enough to be relevant
                if(shape.getRadius() >= game.widthOrHeight / SHAPE_VISIBLE_DIVISOR) {
                    drawShape(shape, shapeRenderer);
                }
            }
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
                x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine((float) (x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth))),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine((float) (x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth))),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                x,
                y + radius - lineWidth,
                lineWidth);
        drawPoint(x, y + radius - lineWidth, lineWidth, color, shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
    }

    //TODO: Consolidate square onward into single method
    public void drawSquare(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawPentagon(float x, float y, float radius, float lineWidth, float rotation, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 5);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 5; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
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

    public void drawHexagon(float x, float y, float radius, float lineWidth, float rotation, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 6);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 6; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
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

    public void drawSeptagon(float x, float y, float radius, float lineWidth, float rotation, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 7);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 7; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
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

    public void drawOctagon(float x, float y, float radius, float lineWidth, float rotation, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 8);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 8; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
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

    public void drawNonagon(float x, float y, float radius, float lineWidth, float rotation, Color color, ShapeRenderer shapeRenderer) {
        double theta = (float) (2 * Math.PI / 9);

        shapeRenderer.setColor(color);
        for(int i = 0; i < 9; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
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
            drawPentagon(GameplayScreen.INPUT_PENTAGON_SPAWN.x, GameplayScreen.INPUT_PENTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 6) {
            //Hexagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawHexagon(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 7) {
            //Septagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawSeptagon(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 8) {
            //Octagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawOctagon(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 9) {
            //Nonagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            drawNonagon(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }
    }

    public void drawInputButtonsTimeAttack(Squirgle game, ShapeRenderer shapeRenderer) {
        if(game.base >= 1) {
            //Point
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_POINT_SPAWN.x, TimeAttackScreen.INPUT_POINT_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawPoint(TimeAttackScreen.INPUT_POINT_SPAWN.x, TimeAttackScreen.INPUT_POINT_SPAWN.y, TimeAttackScreen.INPUT_RADIUS / 2, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 2) {
            //Line
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_LINE_SPAWN.x, TimeAttackScreen.INPUT_LINE_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawLine(TimeAttackScreen.INPUT_LINE_SPAWN.x, TimeAttackScreen.INPUT_LINE_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 3) {
            //Triangle
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_TRIANGLE_SPAWN.x, TimeAttackScreen.INPUT_TRIANGLE_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawTriangle(TimeAttackScreen.INPUT_TRIANGLE_SPAWN.x, TimeAttackScreen.INPUT_TRIANGLE_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 4) {
            //Square
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_SQUARE_SPAWN.x, TimeAttackScreen.INPUT_SQUARE_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawSquare(TimeAttackScreen.INPUT_SQUARE_SPAWN.x, TimeAttackScreen.INPUT_SQUARE_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 5) {
            //Pentagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_PENTAGON_SPAWN.x, TimeAttackScreen.INPUT_PENTAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawPentagon(TimeAttackScreen.INPUT_PENTAGON_SPAWN.x, TimeAttackScreen.INPUT_PENTAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 6) {
            //Hexagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_HEXAGON_SPAWN.x, TimeAttackScreen.INPUT_HEXAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawHexagon(TimeAttackScreen.INPUT_HEXAGON_SPAWN.x, TimeAttackScreen.INPUT_HEXAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 7) {
            //Septagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_SEPTAGON_SPAWN.x, TimeAttackScreen.INPUT_SEPTAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawSeptagon(TimeAttackScreen.INPUT_SEPTAGON_SPAWN.x, TimeAttackScreen.INPUT_SEPTAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 8) {
            //Octagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_OCTAGON_SPAWN.x, TimeAttackScreen.INPUT_OCTAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawOctagon(TimeAttackScreen.INPUT_OCTAGON_SPAWN.x, TimeAttackScreen.INPUT_OCTAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
        }

        if(game.base >= 9) {
            //Nonagon
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(TimeAttackScreen.INPUT_NONAGON_SPAWN.x, TimeAttackScreen.INPUT_NONAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS);
            drawNonagon(TimeAttackScreen.INPUT_NONAGON_SPAWN.x, TimeAttackScreen.INPUT_NONAGON_SPAWN.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK, shapeRenderer);
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
        drawPlayButton(inputPlaySpawn.x, inputPlaySpawn.y, GameplayScreen.INPUT_RADIUS / 2, (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);

        //Home
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputHomeSpawn.x, inputHomeSpawn.y, GameplayScreen.INPUT_RADIUS);
        drawBackButton(inputHomeSpawn.x, inputHomeSpawn.y, GameplayScreen.INPUT_RADIUS / 2, (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);

        //Exit
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputExitSpawn.x, inputExitSpawn.y, GameplayScreen.INPUT_RADIUS);
        drawX(inputExitSpawn.x, inputExitSpawn.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
    }

    public void drawResultsInputButtonsTimeAttack(Vector2 inputPlaySpawn, Vector2 inputHomeSpawn, Vector2 inputExitSpawn, List<Shape> priorShapeList, ShapeRenderer shapeRenderer) {
        Color buttonColor;
        Color symbolColor;

        if(priorShapeList.size() > 0 && (priorShapeList.get(0).getShape() != Shape.LINE && priorShapeList.get(0).getShape() != Shape.POINT)) {
            buttonColor = Color.BLACK;
            symbolColor = Color.WHITE;
        } else {
            buttonColor = Color.WHITE;
            symbolColor = Color.BLACK;
        }

        //Play
        shapeRenderer.setColor(buttonColor);
        shapeRenderer.circle(inputPlaySpawn.x, inputPlaySpawn.y, TimeAttackScreen.INPUT_RADIUS);
        drawPlayButton(inputPlaySpawn.x, inputPlaySpawn.y, TimeAttackScreen.INPUT_RADIUS / 2, (TimeAttackScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, symbolColor, shapeRenderer);

        //Home
        shapeRenderer.setColor(buttonColor);
        shapeRenderer.circle(inputHomeSpawn.x, inputHomeSpawn.y, TimeAttackScreen.INPUT_RADIUS);
        drawBackButton(inputHomeSpawn.x, inputHomeSpawn.y, TimeAttackScreen.INPUT_RADIUS / 2, (TimeAttackScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, symbolColor, shapeRenderer);

        //Exit
        shapeRenderer.setColor(buttonColor);
        shapeRenderer.circle(inputExitSpawn.x, inputExitSpawn.y, TimeAttackScreen.INPUT_RADIUS);
        drawX(inputExitSpawn.x, inputExitSpawn.y, TimeAttackScreen.INPUT_RADIUS, TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, symbolColor, shapeRenderer);
    }

    public void drawEquation(Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth, ShapeRenderer shapeRenderer) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, equationWidth, Color.BLACK, Color.BLACK, equationWidth / LINE_WIDTH_DIVISOR, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        switch(lastShapeTouched.getShape()) {
            case Shape.POINT :
                spawnBegin = GameplayScreen.INPUT_POINT_SPAWN;
                if (lastPromptShape.getShape() + 1 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 1);
                }
                break;
            case Shape.LINE :
                spawnBegin = GameplayScreen.INPUT_LINE_SPAWN;
                if (lastPromptShape.getShape() + 2 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 2);
                }
                break;
            case Shape.TRIANGLE :
                spawnBegin = GameplayScreen.INPUT_TRIANGLE_SPAWN;
                if (lastPromptShape.getShape() + 3 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 3);
                }
                break;
            case Shape.SQUARE :
                spawnBegin = GameplayScreen.INPUT_SQUARE_SPAWN;
                if (lastPromptShape.getShape() + 4 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 4);
                }
                break;
            case Shape.PENTAGON :
                spawnBegin = GameplayScreen.INPUT_PENTAGON_SPAWN;
                if (lastPromptShape.getShape() + 5 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 5);
                }
                break;
            case Shape.HEXAGON :
                spawnBegin = GameplayScreen.INPUT_HEXAGON_SPAWN;
                if (lastPromptShape.getShape() + 6 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 6);
                }
                break;
            case Shape.SEPTAGON :
                spawnBegin = GameplayScreen.INPUT_SEPTAGON_SPAWN;
                if (lastPromptShape.getShape() + 7 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 7);
                }
                break;
            case Shape.OCTAGON :
                spawnBegin = GameplayScreen.INPUT_OCTAGON_SPAWN;
                if (lastPromptShape.getShape() + 8 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 8);
                }
                break;
            case Shape.NONAGON :
                spawnBegin = GameplayScreen.INPUT_NONAGON_SPAWN;
                if (lastPromptShape.getShape() + 9 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 9);
                }
                break;
        }

        //Set up the ending coordinates
        switch(sum.getShape()) {
            case Shape.POINT :
                spawnEnd = GameplayScreen.INPUT_POINT_SPAWN;
                break;
            case Shape.LINE :
                spawnEnd = GameplayScreen.INPUT_LINE_SPAWN;
                break;
            case Shape.TRIANGLE :
                spawnEnd = GameplayScreen.INPUT_TRIANGLE_SPAWN;
                break;
            case Shape.SQUARE :
                spawnEnd = GameplayScreen.INPUT_SQUARE_SPAWN;
                break;
            case Shape.PENTAGON :
                spawnEnd = GameplayScreen.INPUT_PENTAGON_SPAWN;
                break;
            case Shape.HEXAGON :
                spawnEnd = GameplayScreen.INPUT_HEXAGON_SPAWN;
                break;
            case Shape.SEPTAGON :
                spawnEnd = GameplayScreen.INPUT_SEPTAGON_SPAWN;
                break;
            case Shape.OCTAGON :
                spawnEnd = GameplayScreen.INPUT_OCTAGON_SPAWN;
                break;
            case Shape.NONAGON :
                spawnEnd = GameplayScreen.INPUT_NONAGON_SPAWN;
                break;
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
        if(lastPromptShape.getShape() == Shape.POINT) {
            shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius() * 2);
        } else {
            shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius());
        }
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
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);

        //Draw the equals symbol(s)
        drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        if(sum.getShape() == lastTargetShape.getShape()) {
            drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        //Draw the last prompt shape
        lastPromptShape.setRadius(lastPromptShape.getRadius() - ((lastPromptShape.getRadius() * (GameplayScreen.INPUT_RADIUS / Squirgle.FPS)) / equationWidth));
        lastPromptShape.setLineWidth((lastPromptShape.getRadius() - (GameplayScreen.INPUT_RADIUS / Squirgle.FPS)) / LINE_WIDTH_DIVISOR);
        lastPromptShape.setFillColor(null);
        drawShape(lastPromptShape, shapeRenderer);
    }

    public void drawEquationTimeAttack(Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth, ShapeRenderer shapeRenderer) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, equationWidth, Color.BLACK, Color.BLACK, equationWidth / LINE_WIDTH_DIVISOR, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        switch(lastShapeTouched.getShape()) {
            case Shape.POINT :
                spawnBegin = TimeAttackScreen.INPUT_POINT_SPAWN;
                if (lastPromptShape.getShape() + 1 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 1);
                }
                break;
            case Shape.LINE :
                spawnBegin = TimeAttackScreen.INPUT_LINE_SPAWN;
                if (lastPromptShape.getShape() + 2 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 2);
                }
                break;
            case Shape.TRIANGLE :
                spawnBegin = TimeAttackScreen.INPUT_TRIANGLE_SPAWN;
                if (lastPromptShape.getShape() + 3 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 3);
                }
                break;
            case Shape.SQUARE :
                spawnBegin = TimeAttackScreen.INPUT_SQUARE_SPAWN;
                if (lastPromptShape.getShape() + 4 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 4);
                }
                break;
            case Shape.PENTAGON :
                spawnBegin = TimeAttackScreen.INPUT_PENTAGON_SPAWN;
                if (lastPromptShape.getShape() + 5 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 5);
                }
                break;
            case Shape.HEXAGON :
                spawnBegin = TimeAttackScreen.INPUT_HEXAGON_SPAWN;
                if (lastPromptShape.getShape() + 6 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 6);
                }
                break;
            case Shape.SEPTAGON :
                spawnBegin = TimeAttackScreen.INPUT_SEPTAGON_SPAWN;
                if (lastPromptShape.getShape() + 7 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 7);
                }
                break;
            case Shape.OCTAGON :
                spawnBegin = TimeAttackScreen.INPUT_OCTAGON_SPAWN;
                if (lastPromptShape.getShape() + 8 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 8);
                }
                break;
            case Shape.NONAGON :
                spawnBegin = TimeAttackScreen.INPUT_NONAGON_SPAWN;
                if (lastPromptShape.getShape() + 9 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 9);
                }
                break;
        }

        //Set up the ending coordinates
        switch(sum.getShape()) {
            case Shape.POINT :
                spawnEnd = TimeAttackScreen.INPUT_POINT_SPAWN;
                break;
            case Shape.LINE :
                spawnEnd = TimeAttackScreen.INPUT_LINE_SPAWN;
                break;
            case Shape.TRIANGLE :
                spawnEnd = TimeAttackScreen.INPUT_TRIANGLE_SPAWN;
                break;
            case Shape.SQUARE :
                spawnEnd = TimeAttackScreen.INPUT_SQUARE_SPAWN;
                break;
            case Shape.PENTAGON :
                spawnEnd = TimeAttackScreen.INPUT_PENTAGON_SPAWN;
                break;
            case Shape.HEXAGON :
                spawnEnd = TimeAttackScreen.INPUT_HEXAGON_SPAWN;
                break;
            case Shape.SEPTAGON :
                spawnEnd = TimeAttackScreen.INPUT_SEPTAGON_SPAWN;
                break;
            case Shape.OCTAGON :
                spawnEnd = TimeAttackScreen.INPUT_OCTAGON_SPAWN;
                break;
            case Shape.NONAGON :
                spawnEnd = TimeAttackScreen.INPUT_NONAGON_SPAWN;
                break;
        }

        //Set up the plusSpawn coordinates
        if(lastShapeTouched.getShape() == sum.getShape()) {
            plusSpawn.x = spawnBegin.x + ((lastPromptShape.getCoordinates().x - spawnBegin.x) / 2) - (TimeAttackScreen.INPUT_RADIUS);
        } else {
            plusSpawn.x = spawnBegin.x + ((lastPromptShape.getCoordinates().x - spawnBegin.x) / 2);
        }
        plusSpawn.y = spawnBegin.y + ((lastPromptShape.getCoordinates().y - spawnBegin.y) / 2);

        //Set up the equalsSpawn coordinates
        if(lastShapeTouched.getShape() == sum.getShape()) {
            equalsSpawn.x = spawnEnd.x + ((lastPromptShape.getCoordinates().x - spawnEnd.x) / 2) + (TimeAttackScreen.INPUT_RADIUS);
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
        if(lastPromptShape.getShape() == Shape.POINT) {
            shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius() * 2);
        } else {
            shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius());
        }
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
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);

        //Draw the equals symbol(s)
        drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        if(sum.getShape() == lastTargetShape.getShape()) {
            drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        //Draw the last prompt shape
        lastPromptShape.setRadius(lastPromptShape.getRadius() - ((lastPromptShape.getRadius() * (TimeAttackScreen.INPUT_RADIUS / Squirgle.FPS)) / equationWidth));
        lastPromptShape.setLineWidth((lastPromptShape.getRadius() - (TimeAttackScreen.INPUT_RADIUS / Squirgle.FPS)) / LINE_WIDTH_DIVISOR);
        lastPromptShape.setFillColor(null);
        drawShape(lastPromptShape, shapeRenderer);
    }

    public void drawEquationTutorial(Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth, int phase, ShapeRenderer shapeRenderer) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, equationWidth, Color.BLACK, Color.BLACK, equationWidth / LINE_WIDTH_DIVISOR, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        switch(lastShapeTouched.getShape()) {
            case Shape.POINT :
                spawnBegin = TutorialScreen.INPUT_POINT_SPAWN;
                if (lastPromptShape.getShape() + 1 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 1);
                }
                break;
            case Shape.LINE :
                spawnBegin = TutorialScreen.INPUT_LINE_SPAWN;
                if (lastPromptShape.getShape() + 2 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 2);
                }
                break;
            case Shape.TRIANGLE :
                spawnBegin = TutorialScreen.INPUT_TRIANGLE_SPAWN;
                if (lastPromptShape.getShape() + 3 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 3);
                }
                break;
            case Shape.SQUARE :
                spawnBegin = TutorialScreen.INPUT_SQUARE_SPAWN;
                if (lastPromptShape.getShape() + 4 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 4);
                }
                break;
            case Shape.PENTAGON :
                spawnBegin = TutorialScreen.INPUT_PENTAGON_SPAWN;
                if (lastPromptShape.getShape() + 5 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 5);
                }
                break;
            case Shape.HEXAGON :
                spawnBegin = TutorialScreen.INPUT_HEXAGON_SPAWN;
                if (lastPromptShape.getShape() + 6 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 6);
                }
                break;
            case Shape.SEPTAGON :
                spawnBegin = TutorialScreen.INPUT_SEPTAGON_SPAWN;
                if (lastPromptShape.getShape() + 7 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 7);
                }
                break;
            case Shape.OCTAGON :
                spawnBegin = TutorialScreen.INPUT_OCTAGON_SPAWN;
                if (lastPromptShape.getShape() + 8 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 8);
                }
                break;
            case Shape.NONAGON :
                spawnBegin = TutorialScreen.INPUT_NONAGON_SPAWN;
                if (lastPromptShape.getShape() + 9 >= game.base) {
                    sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                } else {
                    sum.setShape(lastPromptShape.getShape() + 9);
                }
                break;
        }

        //Set up the ending coordinates
        switch(sum.getShape()) {
            case Shape.POINT :
                spawnEnd = TutorialScreen.INPUT_POINT_SPAWN;
                break;
            case Shape.LINE :
                spawnEnd = TutorialScreen.INPUT_LINE_SPAWN;
                break;
            case Shape.TRIANGLE :
                spawnEnd = TutorialScreen.INPUT_TRIANGLE_SPAWN;
                break;
            case Shape.SQUARE :
                spawnEnd = TutorialScreen.INPUT_SQUARE_SPAWN;
                break;
            case Shape.PENTAGON :
                spawnEnd = TutorialScreen.INPUT_PENTAGON_SPAWN;
                break;
            case Shape.HEXAGON :
                spawnEnd = TutorialScreen.INPUT_HEXAGON_SPAWN;
                break;
            case Shape.SEPTAGON :
                spawnEnd = TutorialScreen.INPUT_SEPTAGON_SPAWN;
                break;
            case Shape.OCTAGON :
                spawnEnd = TutorialScreen.INPUT_OCTAGON_SPAWN;
                break;
            case Shape.NONAGON :
                spawnEnd = TutorialScreen.INPUT_NONAGON_SPAWN;
                break;
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
        if(lastPromptShape.getShape() == Shape.POINT) {
            shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius() * 2);
        } else {
            shapeRenderer.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius());
        }
        shapeRenderer.circle(equalsSpawn.x,
                equalsSpawn.y,
                equationWidth);
        if(sum.getShape() == lastTargetShape.getShape() && phase >= TutorialScreen.PHASE_SIX) {
            shapeRenderer.circle(equalsTargetSpawn.x,
                    equalsTargetSpawn.y,
                    equationWidth);
        }
        if(phase < TutorialScreen.PHASE_SIX) {
            shapeRenderer.circle(spawnEnd.x,
                    spawnEnd.y,
                    equationWidth);
            if(lastShapeTouched.getShape() + 1 != phase) {
                shapeRenderer.circle(spawnBegin.x,
                        spawnBegin.y,
                        equationWidth);
            }
        }

        //Draw the lines
        shapeRenderer.rectLine(spawnBegin, plusSpawn, equationWidth * 2);
        shapeRenderer.rectLine(plusSpawn, lastPromptShape.getCoordinates(), equationWidth * 2);
        shapeRenderer.rectLine(lastPromptShape.getCoordinates(), equalsSpawn, equationWidth * 2);
        shapeRenderer.rectLine(equalsSpawn, spawnEnd, equationWidth * 2);
        if(sum.getShape() == lastTargetShape.getShape() && phase >= TutorialScreen.PHASE_SIX) {
            shapeRenderer.rectLine(lastPromptShape.getCoordinates(), equalsTargetSpawn, equationWidth * 2);
            shapeRenderer.rectLine(equalsTargetSpawn, targetSpawn, equationWidth * 2);
        }

        //Draw the plus symbol
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);

        //Draw the equals symbol(s)
        drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        if(sum.getShape() == lastTargetShape.getShape() && phase >= TutorialScreen.PHASE_SIX) {
            drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK, shapeRenderer);
        }

        if(phase < TutorialScreen.PHASE_SIX) {
            //Draw the sum shape (if applicable)
            float sumRadius = sum.getShape() == Shape.POINT ? sum.getRadius() / 2 : sum.getRadius();
            sum.setRadius(sumRadius - ((sumRadius * (TutorialScreen.INPUT_RADIUS / (Squirgle.FPS * 4))) / equationWidth));
            sum.setLineWidth((sum.getRadius() - (TutorialScreen.INPUT_RADIUS / (Squirgle.FPS * 4))) / LINE_WIDTH_DIVISOR);
            sum.setFillColor(Color.WHITE);
            sum.setCoordinates(new Vector2(spawnEnd.x, spawnEnd.y));
            drawShape(sum, shapeRenderer);

            //Draw the last shape touched (if applicable)
            if(lastShapeTouched.getShape() + 1 != phase) {
                float lastShapeTouchedRadius = lastShapeTouched.getShape() == Shape.POINT ? lastShapeTouched.getRadius() / 2 : lastShapeTouched.getRadius();
                lastShapeTouched.setRadius(lastShapeTouchedRadius - ((lastShapeTouchedRadius * (TutorialScreen.INPUT_RADIUS / (Squirgle.FPS * 4))) / equationWidth));
                lastShapeTouched.setLineWidth((lastShapeTouched.getRadius() - (TutorialScreen.INPUT_RADIUS / (Squirgle.FPS * 4))) / LINE_WIDTH_DIVISOR);
                lastShapeTouched.setFillColor(Color.WHITE);
                lastShapeTouched.setCoordinates(new Vector2(spawnBegin.x, spawnBegin.y));
                drawShape(lastShapeTouched, shapeRenderer);
            }
        }

        //Draw the last prompt shape. Here, we're multiplying the FPS by 4 in order to give the player more time to understand what they're doing.
        lastPromptShape.setRadius(lastPromptShape.getRadius() - ((lastPromptShape.getRadius() * (TutorialScreen.INPUT_RADIUS / (Squirgle.FPS * 4))) / equationWidth));
        lastPromptShape.setLineWidth((lastPromptShape.getRadius() - (TutorialScreen.INPUT_RADIUS / (Squirgle.FPS * 4))) / LINE_WIDTH_DIVISOR);
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

    public void drawBackgroundColorShapeListTimeAttack(List<Shape> backgroundColorShapeList, Shape backgroundColorShape, Color clearColor, ShapeRenderer shapeRenderer) {
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
                backgroundColorShape.setRadius(game.camera.viewportWidth / 2);
                backgroundColorShape.setColor(backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor());
                backgroundColorShape.setFillColor(backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor());
                backgroundColorShape.setLineWidth(TimeAttackScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR);
                backgroundColorShape.setCoordinates(new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));

                backgroundColorShapeList.remove(backgroundColorShapeList.size() - 1);
                backgroundColorShapeList.add(0,
                        new Shape(Shape.SQUARE,
                                newRadius,
                                Color.BLACK,
                                Color.BLACK,
                                newRadius / LINE_WIDTH_DIVISOR,
                                new Vector2(TimeAttackScreen.TARGET_RADIUS + ((game.camera.viewportWidth - (TimeAttackScreen.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)),
                                        (game.camera.viewportHeight + TimeAttackScreen.BACKGROUND_COLOR_LIST_ELEMENT_RADIUS) + ((game.camera.viewportWidth - (TimeAttackScreen.TARGET_RADIUS * 2)) / (NUM_BACKGROUND_COLOR_SHAPE_COLUMNS + 1)))));
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
        shapeRenderer.setColor(Color.WHITE);
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
        int lineWidthMultiplier = 6;

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
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                    lowColorShape.getLineWidth() * lineWidthMultiplier,
                    shapeRenderer);
            drawTimeline(secondX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 4), //Multiplying by four to get desired distance from color shapes
                    lowColorShape.getLineWidth() * lineWidthMultiplier,
                    shapeRenderer);
            drawTimeline(thirdX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 6), //Multiplying by six to get desired distance from color shapes
                    lowColorShape.getLineWidth() * lineWidthMultiplier,
                    shapeRenderer);
        } else if(promptShape.getRadius() <= game.fiveTwelfthsOfScreen) {
            drawTimeline(secondX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                    lowColorShape.getLineWidth() * lineWidthMultiplier,
                    shapeRenderer);
            drawTimeline(thirdX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 4), //Multiplying by four to get desired distance from color shapes
                    lowColorShape.getLineWidth() * lineWidthMultiplier,
                    shapeRenderer);
        } else {
            drawTimeline(thirdX,
                    lastColorShape.getCoordinates().x,
                    lowColorShape.getCoordinates().y - (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                    lowColorShape.getLineWidth() * lineWidthMultiplier,
                    shapeRenderer);
        }
    }

    public void drawTargetSemicircle(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, game.camera.viewportHeight, GameplayScreen.TARGET_RADIUS);
    }

    public void drawTargetSemicircleTimeAttack(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, game.camera.viewportHeight, TimeAttackScreen.TARGET_RADIUS);
    }

    public void drawTargetSemicircleTutorial(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(0, game.camera.viewportHeight, TutorialScreen.TARGET_RADIUS);
    }

    public void drawArc(float start, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.arc(0, game.camera.viewportHeight, GameplayScreen.TARGET_RADIUS, start, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawArcTimeAttack(float start, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.arc(0, game.camera.viewportHeight, TimeAttackScreen.TARGET_RADIUS, start, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawArcTutorial(float start, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.arc(0, game.camera.viewportHeight, TutorialScreen.TARGET_RADIUS, start, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawScoreTriangle(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.triangle(game.camera.viewportWidth,
                game.camera.viewportHeight,
                game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
                game.camera.viewportHeight,
                game.camera.viewportWidth,
                game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS);
    }

    public void drawScoreTriangleTimeAttack(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.triangle(game.camera.viewportWidth,
                game.camera.viewportHeight,
                game.camera.viewportWidth - TimeAttackScreen.TARGET_RADIUS,
                game.camera.viewportHeight,
                game.camera.viewportWidth,
                game.camera.viewportHeight - TimeAttackScreen.TARGET_RADIUS);
    }

    public void drawScoreTriangleTutorial(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.triangle(game.camera.viewportWidth,
                game.camera.viewportHeight,
                game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
                game.camera.viewportHeight,
                game.camera.viewportWidth,
                game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS);
    }

    public void drawPauseInput(Squirgle game) {
        float inputRadius = game.camera.viewportWidth / 20;
        float lineRadius = inputRadius / 2;

        drawPoint(game.camera.viewportWidth, game.camera.viewportHeight / 2, inputRadius, Color.WHITE, game.shapeRendererFilled);
        drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), game.camera.viewportHeight / 2, inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, game.shapeRendererFilled);

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

    public void drawTouchDownPointsTimeAttack(List<Shape> touchDownShapeList, ShapeRenderer shapeRenderer) {
        for (int i = 0; i < touchDownShapeList.size(); i++) {
            Shape shape = touchDownShapeList.get(i);
            if (shape.getRadius() > TimeAttackScreen.INPUT_RADIUS) {
                touchDownShapeList.remove(shape);
            } else {
                drawShape(shape, shapeRenderer);
                shape.setRadius(shape.getRadius() + 1);
            }
        }
    }

    public void drawTouchDownPointsTutorial(List<Shape> touchDownShapeList, ShapeRenderer shapeRenderer) {
        for (int i = 0; i < touchDownShapeList.size(); i++) {
            Shape shape = touchDownShapeList.get(i);
            if (shape.getRadius() > TutorialScreen.INPUT_RADIUS) {
                touchDownShapeList.remove(shape);
            } else {
                drawShape(shape, shapeRenderer);
                shape.setRadius(shape.getRadius() + 1);
            }
        }
    }

    public void drawDash(float x, float y, float width, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);

        shapeRenderer.rect(x - (width / 2), y - (width / 4), width, width / 2);
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

    public void drawWrench(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(primaryColor);
        shapeRenderer.rectLine(x - (radius / 2), y - (radius / 2), x + (radius / 2), y + (radius / 2), lineWidth);
        shapeRenderer.circle(x - (radius / 2), y - (radius / 2), lineWidth);
        shapeRenderer.circle(x + (radius / 2), y + (radius / 2), lineWidth);

        shapeRenderer.setColor(secondaryColor);
        shapeRenderer.rect(x - (radius / 2) - (lineWidth / 2), y - (radius / 2) - lineWidth, lineWidth, lineWidth);
        shapeRenderer.rect(x + (radius / 2) - (lineWidth / 2), y + (radius / 2), lineWidth, lineWidth);
    }

    public void drawPlayButton(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                x + radius - lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x + radius - lineWidth,
                y,
                x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + radius - lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawPauseSymbol(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawLine(x - (radius / 4),
                y,
                radius / 2,
                lineWidth,
                color,
                shapeRenderer);
        drawLine(x + (radius / 4),
                y,
                radius / 2,
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawSoundSymbol(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawPoint(x - (radius / 2) + lineWidth, y, radius, Color.BLACK, shapeRenderer);
        drawPoint(x - (radius / 2) + lineWidth - SOUND_WAVE_DISTANCE, y, radius, color, shapeRenderer);

        drawPoint(x - (radius / 2) + lineWidth, y, (3 * radius) / 4, Color.BLACK, shapeRenderer);
        drawPoint(x - (radius / 2) + lineWidth - SOUND_WAVE_DISTANCE, y, (3 * radius) / 4, color, shapeRenderer);

        drawPoint(x - (radius / 2) + lineWidth, y, radius / 2, Color.BLACK, shapeRenderer);
        drawPoint(x - (radius / 2) + lineWidth - SOUND_WAVE_DISTANCE, y, radius / 2, color, shapeRenderer);

        drawPoint(x - (radius / 2) + lineWidth, y, radius / 4, Color.BLACK, shapeRenderer);
    }

    public void drawBackButton(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                x - radius + lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x - radius + lineWidth,
                y,
                x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth);
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth);
        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - radius + lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawX(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        //TODO: Maybe fashion this like square?
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
        shapeRenderer.rectLine(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                x - radius + lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x - radius + lineWidth,
                y,
                x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth);
        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - radius + lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawChevronRight(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                x + radius - lineWidth,
                y,
                lineWidth);
        shapeRenderer.rectLine(x + radius - lineWidth,
                y,
                x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
                lineWidth,
                color,
                shapeRenderer);
        drawPoint(x + radius - lineWidth,
                y,
                lineWidth,
                color,
                shapeRenderer);
    }

    public void drawQuestionMark(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(primaryColor);
        shapeRenderer.circle(x, y + (radius / 4), radius / 4);
        shapeRenderer.rectLine(x, y + (radius / 4), x, y - (radius / 4), lineWidth);
        shapeRenderer.rectLine(x, y - ((3 * radius) / 8), x, y - (radius / 2), lineWidth);

        shapeRenderer.setColor(secondaryColor);
        shapeRenderer.circle(x, y + (radius / 4), lineWidth);
        shapeRenderer.rectLine(x - ((5 * lineWidth) / 4), y + (radius / 4), x - ((5 * lineWidth) / 4), y, ((3 * lineWidth) / 2));
    }

    public void drawQuarterNote(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y - ((3 * radius) / 4), radius / 4);
        shapeRenderer.circle(x + (radius / 4) - (lineWidth / 2), y + radius - (lineWidth / 2), lineWidth / 2);
        shapeRenderer.rectLine(x + (radius / 4) - (lineWidth / 2), y + radius - (lineWidth / 2), x + (radius / 4) - (lineWidth / 2), y - ((3 * radius) / 4), lineWidth);
    }

    public void drawFace(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor, ShapeRenderer shapeRenderer) {
        drawPoint(x, y, radius, primaryColor, shapeRenderer);

        drawPoint(x - (radius / 2), y + (radius / 2), lineWidth, secondaryColor, shapeRenderer);
        drawPoint(x + (radius / 2), y +(radius / 2), lineWidth, secondaryColor, shapeRenderer);
    }

    public void drawColorSymbol(float x, float y, float radius, float lineWidth, Color color, ShapeRenderer shapeRenderer) {
        drawPoint(x, y, radius, color, shapeRenderer);

        drawPoint(x - (radius / 2), y + ((3 * radius) / 4), radius / 4, Color.WHITE, shapeRenderer);

        drawPoint(x, y - radius + lineWidth, lineWidth, Color.FIREBRICK, shapeRenderer);
        drawPoint(x + radius - (radius / 2.575f), y - radius + (radius / 2.575f), lineWidth, Color.CYAN, shapeRenderer);
        drawPoint(x + radius - lineWidth, y, lineWidth, Color.MAGENTA, shapeRenderer);
    }

    public void drawColorWheel(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);

        shapeRenderer.setColor(new Color(230/ColorUtils.MAX_RGB_VALUE, 159/ColorUtils.MAX_RGB_VALUE, 0, 1));
        shapeRenderer.arc(x, y, (9 * radius) / 10, SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(new Color(213/ColorUtils.MAX_RGB_VALUE, 94/ColorUtils.MAX_RGB_VALUE, 0, 1));
        shapeRenderer.arc(x, y, (9 * radius) / 10, 0, SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(new Color(204/ColorUtils.MAX_RGB_VALUE, 121/ColorUtils.MAX_RGB_VALUE, 167/ColorUtils.MAX_RGB_VALUE, 1));
        shapeRenderer.arc(x, y, (9 * radius) / 10, -SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(new Color(0, 114/ColorUtils.MAX_RGB_VALUE, 178/ColorUtils.MAX_RGB_VALUE, 1));
        shapeRenderer.arc(x, y, (9 * radius) / 10, -(2 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(new Color(86/ColorUtils.MAX_RGB_VALUE, 180/ColorUtils.MAX_RGB_VALUE, 233/ColorUtils.MAX_RGB_VALUE, 1));
        shapeRenderer.arc(x, y, (9 * radius) / 10, -(3 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(new Color(0, 158/ColorUtils.MAX_RGB_VALUE, 115/ColorUtils.MAX_RGB_VALUE, 1));
        shapeRenderer.arc(x, y, (9 * radius) / 10, -(4 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawColor(float x, float y, float radius, int color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(x, y, radius);

        switch(color) {
            case ColorUtils.ORANGE : {
                shapeRenderer.arc(x, y, radius, 0, -300, NUM_ARC_SEGMENTS);

                shapeRenderer.setColor(new Color(230/ColorUtils.MAX_RGB_VALUE, 159/ColorUtils.MAX_RGB_VALUE, 0, 1));
                shapeRenderer.arc(x, y, (9 * radius) / 10, SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.SKY_BLUE : {
                shapeRenderer.arc(x, y, radius, -60, -300, NUM_ARC_SEGMENTS);

                shapeRenderer.setColor(new Color(213/ColorUtils.MAX_RGB_VALUE, 94/ColorUtils.MAX_RGB_VALUE, 0, 1));
                shapeRenderer.arc(x, y, (9 * radius) / 10, 0, SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.BLUISH_GREEN : {
                shapeRenderer.arc(x, y, radius, -120, -300, NUM_ARC_SEGMENTS);

                shapeRenderer.setColor(new Color(204/ColorUtils.MAX_RGB_VALUE, 121/ColorUtils.MAX_RGB_VALUE, 167/ColorUtils.MAX_RGB_VALUE, 1));
                shapeRenderer.arc(x, y, (9 * radius) / 10, -SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.BLUE : {
                shapeRenderer.arc(x, y, radius, -180, -300, NUM_ARC_SEGMENTS);

                shapeRenderer.setColor(new Color(0, 114/ColorUtils.MAX_RGB_VALUE, 178/ColorUtils.MAX_RGB_VALUE, 1));
                shapeRenderer.arc(x, y, (9 * radius) / 10, -(2 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.VERMILLION : {
                shapeRenderer.arc(x, y, radius, -240, -300, NUM_ARC_SEGMENTS);

                shapeRenderer.setColor(new Color(86/ColorUtils.MAX_RGB_VALUE, 180/ColorUtils.MAX_RGB_VALUE, 233/ColorUtils.MAX_RGB_VALUE, 1));
                shapeRenderer.arc(x, y, (9 * radius) / 10, -(3 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.REDDISH_PURPLE : {
                shapeRenderer.arc(x, y, radius, -300, -300, NUM_ARC_SEGMENTS);

                shapeRenderer.setColor(new Color(0, 158/ColorUtils.MAX_RGB_VALUE, 115/ColorUtils.MAX_RGB_VALUE, 1));
                shapeRenderer.arc(x, y, (9 * radius) / 10, -(4 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
        }
    }

    public void drawWiFiSymbol(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor, ShapeRenderer shapeRenderer) {
        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), radius, primaryColor, shapeRenderer);
        drawPoint(x + (radius / 2.575f) - lineWidth + 5, y - (radius / 2.575f) - lineWidth, radius, secondaryColor, shapeRenderer);

        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), (3 * radius) / 4, primaryColor, shapeRenderer);
        drawPoint(x + (radius / 2.575f) - lineWidth + 5, y - (radius / 2.575f) - lineWidth, (3 * radius) / 4, secondaryColor, shapeRenderer);

        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), radius / 2, primaryColor, shapeRenderer);
        drawPoint(x + (radius / 2.575f) - lineWidth + 5, y - (radius / 2.575f) - lineWidth, radius / 2, secondaryColor, shapeRenderer);

        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), radius / 4, primaryColor, shapeRenderer);
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

    public void drawClock(float x, float y, float radius, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x, y, (9 * radius) / 10);

        //TODO: Add incremental notches along clock
        shapeRenderer.setColor(color);
        shapeRenderer.rectLine(x,
                y,
                x - (radius / 2),
                y + (radius / 2),
                radius / LINE_WIDTH_DIVISOR);
        shapeRenderer.rectLine(x,
                y,
                x + ((3 * radius) / 5),
                y + ((3 * radius) / 5),
                (radius / LINE_WIDTH_DIVISOR) / 2);
        shapeRenderer.circle(x, y, radius / LINE_WIDTH_DIVISOR);
    }

    public void drawTranceSymbol(float x, float y, float radius, Color primaryColor, Color secondaryColor, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(primaryColor);
        shapeRenderer.circle(x, y + radius, game.partitionSize / 2);
        shapeRenderer.rectLine(x, y + radius, x, y - (radius / 2), game.partitionSize);
        shapeRenderer.rectLine(x, y + radius, x - radius, y, game.partitionSize);
        shapeRenderer.rectLine(x, y + radius, x + radius, y, game.partitionSize);

        shapeRenderer.circle(x, y - (radius / 2), 2 * game.partitionSize);
        shapeRenderer.circle(x - radius, y, 2 * game.partitionSize);
        shapeRenderer.circle(x + radius, y, 2 * game.partitionSize);

        shapeRenderer.setColor(secondaryColor);
        shapeRenderer.circle(x, y - (radius / 2), game.partitionSize / 2);
        shapeRenderer.circle(x - radius, y, game.partitionSize / 2);
        shapeRenderer.circle(x + radius, y, game.partitionSize / 2);

        shapeRenderer.setColor(primaryColor);
        shapeRenderer.circle(x, y - (radius / 2), game.partitionSize / 4);
        shapeRenderer.circle(x - radius, y, game.partitionSize / 4);
        shapeRenderer.circle(x + radius, y, game.partitionSize / 4);

        shapeRenderer.arc(x - (radius / 2), y - (radius / 8), radius / 2, -157.5f, 90, NUM_ARC_SEGMENTS);
        shapeRenderer.arc(x + (radius / 2), y - (radius / 8), radius / 2, -112.5f, 90, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(secondaryColor);
        shapeRenderer.arc(x - (radius / 2) + (radius / TRANCE_ARC_RADIUS_DIVISOR), y - (radius / 8) + (radius / TRANCE_ARC_RADIUS_DIVISOR), radius / 2, -157.5f, 90, NUM_ARC_SEGMENTS);
        shapeRenderer.arc(x + (radius / 2) - (radius / TRANCE_ARC_RADIUS_DIVISOR), y - (radius / 8) + (radius / TRANCE_ARC_RADIUS_DIVISOR), radius / 2, -112.5f, 90, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(primaryColor);
        shapeRenderer.arc(x - (radius / 2) + (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 8) + (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 2, -157.5f, 90, NUM_ARC_SEGMENTS);
        shapeRenderer.arc(x + (radius / 2) - (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 8) + (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 2, -112.5f, 90, NUM_ARC_SEGMENTS);

        shapeRenderer.setColor(secondaryColor);
        shapeRenderer.arc(x - (radius / 2) + (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 8) + (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 2, -157.5f, 90, NUM_ARC_SEGMENTS);
        shapeRenderer.arc(x + (radius / 2) - (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 8) + (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 2, -112.5f, 90, NUM_ARC_SEGMENTS);
    }
}
