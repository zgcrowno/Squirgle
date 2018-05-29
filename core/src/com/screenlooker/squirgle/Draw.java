package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.screenlooker.squirgle.screen.*;
import com.screenlooker.squirgle.util.ColorUtils;

import java.sql.Time;
import java.util.List;

//TODO: Consolidate all tutorial stuff into standard gameplay screen
//TODO: Maybe remove lineWidth as parameter from all methods, since it seems to be formulated the same way from radius every time.
public class Draw {
    public static final int THRESHOLD_MULTIPLIER = 8;
    public static final int LINE_WIDTH_DIVISOR = 8;
    public static final int NUM_BACKGROUND_COLOR_SHAPE_COLUMNS = 6;
    public static final int NUM_ARC_SEGMENTS = 90;
    public static final int SIXTY_DEGREES = 60;
    public static final int NINETY_ONE_DEGREES = 91;
    public static final int ONE_HUNDRED_AND_EIGHTY_DEGREES = 180;
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

    public void drawVeil(Color color, float opacity) {
        game.shapeRendererFilled.setColor(new Color(color.r, color.g, color.b, opacity));
        game.shapeRendererFilled.rect(0, 0, game.camera.viewportWidth, game.camera.viewportHeight);
    }

    public void drawPerimeter(float x, float y, Color color, float visibilityPoint, Shape promptShape) {
        float opacity = 0f;
        if(promptShape.getRadius() >= visibilityPoint) {
            opacity = (promptShape.getRadius() - visibilityPoint) / (game.widthOrHeightSmaller / 4);
        }
        game.shapeRendererLine.setColor(new Color(color.r, color.g, color.b, opacity));
        game.shapeRendererLine.circle(x, y, promptShape.getRadius());
    }

    public void drawScreenDivision(boolean blackAndWhite, boolean multiplayer) {
        game.shapeRendererLine.setColor(blackAndWhite ? Color.WHITE : Color.BLACK);
        game.shapeRendererLine.line(0, game.camera.viewportHeight / 2, game.camera.viewportWidth, game.camera.viewportHeight / 2);
        if(blackAndWhite && multiplayer) {
            game.shapeRendererLine.setColor(Color.BLACK);
            game.shapeRendererLine.line(0, game.camera.viewportHeight / 2, GameplayScreen.TARGET_RADIUS, game.camera.viewportHeight / 2);
            game.shapeRendererLine.line(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS, game.camera.viewportHeight / 2, game.camera.viewportWidth, game.camera.viewportHeight / 2);
        }
    }

    public void drawScreenDivisionTutorial(boolean blackAndWhite) {
        game.shapeRendererLine.setColor(blackAndWhite ? Color.WHITE : Color.BLACK);
        game.shapeRendererLine.line(0, game.camera.viewportHeight / 2, game.camera.viewportWidth, game.camera.viewportHeight / 2);
    }

    public void drawPrompt(boolean localMobilePlayer2, Shape promptShape, List<Shape> priorShapeList, int targetShapesMatched, Shape backgroundColorShape, boolean isNonGameplay, boolean isTarget) {
        float xOffset = 0;
        float radiusOffset = 1;
        switch(promptShape.getShape()) {
            case Shape.CIRCLE :
                drawPoint(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getColor());
                break;
            case Shape.POINT :
                if (!priorShapeList.isEmpty()) {
                    xOffset = promptShape.getRadius() / 2;
                    radiusOffset = 2;
                }
                drawPoint(localMobilePlayer2 ? promptShape.getCoordinates().x + xOffset : promptShape.getCoordinates().x - xOffset,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() / radiusOffset,
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawPoint(localMobilePlayer2 ? promptShape.getCoordinates().x + xOffset : promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) / 2,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawPoint(localMobilePlayer2 ? promptShape.getCoordinates().x + xOffset : promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) / 2,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.LINE :
                if (!priorShapeList.isEmpty()) {
                    xOffset = promptShape.getRadius() - (promptShape.getLineWidth() * 1.7f);
                    radiusOffset = 2;
                }
                drawLine(localMobilePlayer2 ? promptShape.getCoordinates().x + xOffset : promptShape.getCoordinates().x - xOffset,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius() / radiusOffset,
                        promptShape.getLineWidth(),
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawLine(localMobilePlayer2 ? promptShape.getCoordinates().x + xOffset : promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawLine(localMobilePlayer2 ? promptShape.getCoordinates().x + xOffset : promptShape.getCoordinates().x - xOffset,
                                promptShape.getCoordinates().y,
                                (promptShape.getRadius() / radiusOffset) - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.TRIANGLE :
                drawTriangle(localMobilePlayer2,
                        promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawTriangle(localMobilePlayer2,
                                promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawTriangle(localMobilePlayer2,
                                promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.SQUARE :
                drawSquare(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawSquare(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawSquare(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.PENTAGON :
                drawPentagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawPentagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawPentagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.HEXAGON :
                drawHexagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawHexagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawHexagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.SEPTAGON :
                drawSeptagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawSeptagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawSeptagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.OCTAGON :
                drawOctagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawOctagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawOctagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    }
                }
                break;
            case Shape.NONAGON :
                drawNonagon(promptShape.getCoordinates().x,
                        promptShape.getCoordinates().y,
                        promptShape.getRadius(),
                        promptShape.getLineWidth(),
                        0,
                        promptShape.getColor());
                if(!isNonGameplay) {
                    if(!isTarget) {
                        //We're dealing with center prompt shape, so we must set up its inner color
                        drawNonagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    } else if(targetShapesMatched == 1) {
                        drawNonagon(promptShape.getCoordinates().x,
                                promptShape.getCoordinates().y,
                                promptShape.getRadius() - (promptShape.getLineWidth() / 2),
                                promptShape.getLineWidth() / 2,
                                0,
                                backgroundColorShape.getColor());
                    }
                }
                break;
        }
    }

    public void drawShape(boolean localMobilePlayer2, Shape shape) {
        switch(shape.getShape()) {
            case Shape.CIRCLE :
                game.shapeRendererFilled.setColor(shape.getColor());
                game.shapeRendererFilled.circle(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius());
                break;
            case Shape.POINT :
                if (shape.getFillColor() != null) {
                    //Draw fill color for background color shape
                    drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getFillColor());
                }
                drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getColor());
                break;
            case Shape.LINE :
                drawLine(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getColor());
                break;
            case Shape.TRIANGLE :
                drawTriangle(localMobilePlayer2,
                        shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getColor());
                break;
            case Shape.SQUARE :
                if (shape.getFillColor() != null) {
                    //Draw fill color for background color shape list
                    game.shapeRendererFilled.setColor(shape.getFillColor());
                    game.shapeRendererFilled.rectLine(shape.getCoordinates().x,
                            shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                            shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                            2 * ((shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2)));
                }
                drawSquare(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        shape.getColor());
                break;
            case Shape.PENTAGON :
                drawPentagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor());
                break;
            case Shape.HEXAGON :
                drawHexagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor());
                break;
            case Shape.SEPTAGON :
                drawSeptagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor());
                break;
            case Shape.OCTAGON :
                drawOctagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor());
                break;
            case Shape.NONAGON :
                drawNonagon(shape.getCoordinates().x,
                        shape.getCoordinates().y,
                        shape.getRadius(),
                        shape.getLineWidth(),
                        0,
                        shape.getColor());
                break;
        }
    }

    public void orientShapes(boolean localMobilePlayer2, List<Shape> priorShapeList, Shape promptShape, boolean primaryShapeAtThreshold) {
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
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x + shape.getRadius() : priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                } else if (shape.getShape() == Shape.LINE) {
                    if (i != 0) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x + shape.getRadius() : priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                }

                //Determine radius/coordinates of current shape based on prior one
                //Also, current shape is guaranteed to be a circle if any of these conditions hold
                if (priorShape.getShape() == Shape.POINT) {
                    if (priorShape == promptShape) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - shape.getRadius() : priorShape.getCoordinates().x + shape.getRadius(),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - (shape.getRadius() * 2) : priorShape.getCoordinates().x + (shape.getRadius() * 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.LINE) {
                    if (priorShape == promptShape) {
                        shape.setRadius(priorShape.getRadius() - (priorShape.getLineWidth() * 1.1f));
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - (priorShape.getLineWidth() * 1.7f) - (priorShape.getLineWidth() / 2) + (priorShape.getRadius() - shape.getRadius()) : priorShape.getCoordinates().x + (priorShape.getLineWidth() * 1.7f) + (priorShape.getLineWidth() / 2) - (priorShape.getRadius() - shape.getRadius()),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - priorShape.getRadius() - (priorShape.getLineWidth() / 2) : priorShape.getCoordinates().x + priorShape.getRadius() + (priorShape.getLineWidth() / 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.TRIANGLE) {
                    shape.setRadius((priorShape.getRadius() / 2) - priorShape.getLineWidth());
                } else if (priorShape.getShape() != Shape.CIRCLE) {
                    //The inradius of a regular polygon with n > 3 sides is equal to its apothem, which is defined by [apothem = radius * MathUtils.cos(MathUtils.PI / sides)]
                    shape.setRadius((priorShape.getRadius() * MathUtils.cos(MathUtils.PI / (priorShape.getShape() + 1))) - (1.4f * priorShape.getLineWidth()));
                }
            }
        }
    }

    public void drawShapes(boolean localMobilePlayer2, List<Shape> priorShapeList) {
        for (int i = priorShapeList.size() - 1; i >= 0; i--) {
            Shape shape = priorShapeList.get(i);

            //Only draw the shape if it's large enough to be relevant
            if(shape.getRadius() >= game.widthOrHeightSmaller / SHAPE_VISIBLE_DIVISOR) {
                drawShape(localMobilePlayer2, shape);
            }
        }
    }

    public void orientAndDrawShapes(boolean localMobilePlayer2, List<Shape> priorShapeList, Shape promptShape, boolean primaryShapeAtThreshold) {
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
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x + shape.getRadius() : priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                } else if (shape.getShape() == Shape.LINE) {
                    if (i != 0) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x + shape.getRadius() : priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                }

                //Determine radius/coordinates of current shape based on prior one
                //Also, current shape is guaranteed to be a circle if any of these conditions hold
                if (priorShape.getShape() == Shape.POINT) {
                    if (priorShape == promptShape) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - shape.getRadius() : priorShape.getCoordinates().x + shape.getRadius(),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - (shape.getRadius() * 2) : priorShape.getCoordinates().x + (shape.getRadius() * 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.LINE) {
                    if (priorShape == promptShape) {
                        shape.setRadius(priorShape.getRadius() - (priorShape.getLineWidth() * 1.1f));
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - (priorShape.getLineWidth() * 1.7f) - (priorShape.getLineWidth() / 2) + (priorShape.getRadius() - shape.getRadius()) : priorShape.getCoordinates().x + (priorShape.getLineWidth() * 1.7f) + (priorShape.getLineWidth() / 2) - (priorShape.getRadius() - shape.getRadius()),
                                priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(localMobilePlayer2 ? priorShape.getCoordinates().x - priorShape.getRadius() - (priorShape.getLineWidth() / 2) : priorShape.getCoordinates().x + priorShape.getRadius() + (priorShape.getLineWidth() / 2),
                                priorShape.getCoordinates().y));
                    }
                } else if (priorShape.getShape() == Shape.TRIANGLE) {
                    shape.setRadius((priorShape.getRadius() / 2) - priorShape.getLineWidth());
                } else if (priorShape.getShape() != Shape.CIRCLE) {
                    //The inradius of a regular polygon with n > 3 sides is equal to its apothem, which is defined by [apothem = radius * MathUtils.cos(MathUtils.PI / sides)]
                    shape.setRadius((priorShape.getRadius() * MathUtils.cos(MathUtils.PI / (priorShape.getShape() + 1))) - (1.4f * priorShape.getLineWidth()));
                }

                //Only draw the shape if it's large enough to be relevant
                if(shape.getRadius() >= game.widthOrHeightSmaller / SHAPE_VISIBLE_DIVISOR) {
                    drawShape(localMobilePlayer2, shape);
                }
            }
        }
    }

    public void drawPoint(float x, float y, float radius, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.circle(x, y, radius);
    }

    public void drawLine(float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rectLine(x, y - radius + lineWidth, x, y + radius - lineWidth, lineWidth);
        game.shapeRendererFilled.circle(x, y - radius + lineWidth, lineWidth);
        game.shapeRendererFilled.circle(x, y + radius - lineWidth, lineWidth);
    }

    public void drawTriangle(boolean inverted, float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);
        if(inverted) {
            game.shapeRendererFilled.rectLine(x,
                    y - radius + lineWidth,
                    x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth);
            game.shapeRendererFilled.rectLine((float) (x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth))),
                    y + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth);
            game.shapeRendererFilled.rectLine((float) (x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth))),
                    y + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    x,
                    y - radius + lineWidth,
                    lineWidth);
            drawPoint(x, y - radius + lineWidth, lineWidth, color);
            drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth,
                    color);
            drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth,
                    color);
        } else {
            game.shapeRendererFilled.rectLine(x,
                    y + radius - lineWidth,
                    x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth);
            game.shapeRendererFilled.rectLine((float) (x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth))),
                    y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth);
            game.shapeRendererFilled.rectLine((float) (x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth))),
                    y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    x,
                    y + radius - lineWidth,
                    lineWidth);
            drawPoint(x, y + radius - lineWidth, lineWidth, color);
            drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth,
                    color);
            drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    y - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius - lineWidth)),
                    lineWidth,
                    color);
        }
    }

    //TODO: Consolidate square onward into single method
    public void drawSquare(float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rectLine(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        game.shapeRendererFilled.rectLine(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        game.shapeRendererFilled.rectLine(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        game.shapeRendererFilled.rectLine(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth);
        drawPoint(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color);
        drawPoint(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color);
        drawPoint(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y + (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color);
        drawPoint(x + (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * (radius - lineWidth)),
                lineWidth,
                color);
    }

    public void drawPentagon(float x, float y, float radius, float lineWidth, float rotation, Color color) {
        double theta = (float) (2 * Math.PI / 5);

        game.shapeRendererFilled.setColor(color);
        for(int i = 0; i < 5; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
            game.shapeRendererFilled.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color);
        }
    }

    public void drawHexagon(float x, float y, float radius, float lineWidth, float rotation, Color color) {
        double theta = (float) (2 * Math.PI / 6);

        game.shapeRendererFilled.setColor(color);
        for(int i = 0; i < 6; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
            game.shapeRendererFilled.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color);
        }
    }

    public void drawSeptagon(float x, float y, float radius, float lineWidth, float rotation, Color color) {
        double theta = (float) (2 * Math.PI / 7);

        game.shapeRendererFilled.setColor(color);
        for(int i = 0; i < 7; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
            game.shapeRendererFilled.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color);
        }
    }

    public void drawOctagon(float x, float y, float radius, float lineWidth, float rotation, Color color) {
        double theta = (float) (2 * Math.PI / 8);

        game.shapeRendererFilled.setColor(color);
        for(int i = 0; i < 8; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
            game.shapeRendererFilled.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color);
        }
    }

    public void drawNonagon(float x, float y, float radius, float lineWidth, float rotation, Color color) {
        double theta = (float) (2 * Math.PI / 9);

        game.shapeRendererFilled.setColor(color);
        for(int i = 0; i < 9; i++) {
            float firstXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * i) + rotation);
            float secondXOffset = (radius - lineWidth) * MathUtils.cos((float)(theta * (i + 1)) + rotation);
            float firstYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * i) + rotation);
            float secondYOffset = (radius - lineWidth) * MathUtils.sin((float)(theta * (i + 1)) + rotation);
            game.shapeRendererFilled.rectLine(x + firstXOffset,
                    y + firstYOffset,
                    x + secondXOffset,
                    y + secondYOffset,
                    lineWidth);
            drawPoint(x + firstXOffset,
                    y + firstYOffset,
                    lineWidth,
                    color);
        }
    }

    public void drawInputButtons(boolean splitScreen, boolean localMobilePlayer2, Color perimeterColor, Squirgle game) {
        game.shapeRendererLine.setColor(perimeterColor);

        if(game.base >= 1) {
            //Point
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_POINT_SPAWN.x, GameplayScreen.INPUT_POINT_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawPoint(GameplayScreen.INPUT_POINT_SPAWN.x, GameplayScreen.INPUT_POINT_SPAWN.y, GameplayScreen.INPUT_RADIUS / 2, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_POINT_SPAWN.x, GameplayScreen.INPUT_POINT_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_POINT_SPAWN_P1.x, GameplayScreen.INPUT_POINT_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_POINT_SPAWN_P2.x, GameplayScreen.INPUT_POINT_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawPoint(GameplayScreen.INPUT_POINT_SPAWN_P1.x, GameplayScreen.INPUT_POINT_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS / 2, Color.BLACK);
                drawPoint(GameplayScreen.INPUT_POINT_SPAWN_P2.x, GameplayScreen.INPUT_POINT_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS / 2, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_POINT_SPAWN_P1.x, GameplayScreen.INPUT_POINT_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_POINT_SPAWN_P2.x, GameplayScreen.INPUT_POINT_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 2) {
            //Line
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_LINE_SPAWN.x, GameplayScreen.INPUT_LINE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawLine(GameplayScreen.INPUT_LINE_SPAWN.x, GameplayScreen.INPUT_LINE_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_LINE_SPAWN.x, GameplayScreen.INPUT_LINE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_LINE_SPAWN_P1.x, GameplayScreen.INPUT_LINE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_LINE_SPAWN_P2.x, GameplayScreen.INPUT_LINE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawLine(GameplayScreen.INPUT_LINE_SPAWN_P1.x, GameplayScreen.INPUT_LINE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                drawLine(GameplayScreen.INPUT_LINE_SPAWN_P2.x, GameplayScreen.INPUT_LINE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_LINE_SPAWN_P1.x, GameplayScreen.INPUT_LINE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_LINE_SPAWN_P2.x, GameplayScreen.INPUT_LINE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 3) {
            //Triangle
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN.x, GameplayScreen.INPUT_TRIANGLE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawTriangle(false, GameplayScreen.INPUT_TRIANGLE_SPAWN.x, GameplayScreen.INPUT_TRIANGLE_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN.x, GameplayScreen.INPUT_TRIANGLE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN_P1.x, GameplayScreen.INPUT_TRIANGLE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN_P2.x, GameplayScreen.INPUT_TRIANGLE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawTriangle(false, GameplayScreen.INPUT_TRIANGLE_SPAWN_P1.x, GameplayScreen.INPUT_TRIANGLE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                drawTriangle(localMobilePlayer2, GameplayScreen.INPUT_TRIANGLE_SPAWN_P2.x, GameplayScreen.INPUT_TRIANGLE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN_P1.x, GameplayScreen.INPUT_TRIANGLE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_TRIANGLE_SPAWN_P2.x, GameplayScreen.INPUT_TRIANGLE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 4) {
            //Square
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_SQUARE_SPAWN.x, GameplayScreen.INPUT_SQUARE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawSquare(GameplayScreen.INPUT_SQUARE_SPAWN.x, GameplayScreen.INPUT_SQUARE_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_SQUARE_SPAWN.x, GameplayScreen.INPUT_SQUARE_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_SQUARE_SPAWN_P1.x, GameplayScreen.INPUT_SQUARE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_SQUARE_SPAWN_P2.x, GameplayScreen.INPUT_SQUARE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawSquare(GameplayScreen.INPUT_SQUARE_SPAWN_P1.x, GameplayScreen.INPUT_SQUARE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                drawSquare(GameplayScreen.INPUT_SQUARE_SPAWN_P2.x, GameplayScreen.INPUT_SQUARE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_SQUARE_SPAWN_P1.x, GameplayScreen.INPUT_SQUARE_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_SQUARE_SPAWN_P2.x, GameplayScreen.INPUT_SQUARE_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 5) {
            //Pentagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_PENTAGON_SPAWN.x, GameplayScreen.INPUT_PENTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawPentagon(GameplayScreen.INPUT_PENTAGON_SPAWN.x, GameplayScreen.INPUT_PENTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_PENTAGON_SPAWN.x, GameplayScreen.INPUT_PENTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_PENTAGON_SPAWN_P1.x, GameplayScreen.INPUT_PENTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_PENTAGON_SPAWN_P2.x, GameplayScreen.INPUT_PENTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawPentagon(GameplayScreen.INPUT_PENTAGON_SPAWN_P1.x, GameplayScreen.INPUT_PENTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                drawPentagon(GameplayScreen.INPUT_PENTAGON_SPAWN_P2.x, GameplayScreen.INPUT_PENTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_PENTAGON_SPAWN_P1.x, GameplayScreen.INPUT_PENTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_PENTAGON_SPAWN_P2.x, GameplayScreen.INPUT_PENTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 6) {
            //Hexagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawHexagon(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_HEXAGON_SPAWN.x, GameplayScreen.INPUT_HEXAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_HEXAGON_SPAWN_P1.x, GameplayScreen.INPUT_HEXAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_HEXAGON_SPAWN_P2.x, GameplayScreen.INPUT_HEXAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawHexagon(GameplayScreen.INPUT_HEXAGON_SPAWN_P1.x, GameplayScreen.INPUT_HEXAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                drawHexagon(GameplayScreen.INPUT_HEXAGON_SPAWN_P2.x, GameplayScreen.INPUT_HEXAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_HEXAGON_SPAWN_P1.x, GameplayScreen.INPUT_HEXAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_HEXAGON_SPAWN_P2.x, GameplayScreen.INPUT_HEXAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 7) {
            //Septagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawSeptagon(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN.x, GameplayScreen.INPUT_SEPTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN_P1.x, GameplayScreen.INPUT_SEPTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN_P2.x, GameplayScreen.INPUT_SEPTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawSeptagon(GameplayScreen.INPUT_SEPTAGON_SPAWN_P1.x, GameplayScreen.INPUT_SEPTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                drawSeptagon(GameplayScreen.INPUT_SEPTAGON_SPAWN_P2.x, GameplayScreen.INPUT_SEPTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN_P1.x, GameplayScreen.INPUT_SEPTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_SEPTAGON_SPAWN_P2.x, GameplayScreen.INPUT_SEPTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 8) {
            //Octagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawOctagon(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_OCTAGON_SPAWN.x, GameplayScreen.INPUT_OCTAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_OCTAGON_SPAWN_P1.x, GameplayScreen.INPUT_OCTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_OCTAGON_SPAWN_P2.x, GameplayScreen.INPUT_OCTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawOctagon(GameplayScreen.INPUT_OCTAGON_SPAWN_P1.x, GameplayScreen.INPUT_OCTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                drawOctagon(GameplayScreen.INPUT_OCTAGON_SPAWN_P2.x, GameplayScreen.INPUT_OCTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_OCTAGON_SPAWN_P1.x, GameplayScreen.INPUT_OCTAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_OCTAGON_SPAWN_P2.x, GameplayScreen.INPUT_OCTAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }

        if(game.base >= 9) {
            //Nonagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
                drawNonagon(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_NONAGON_SPAWN.x, GameplayScreen.INPUT_NONAGON_SPAWN.y, GameplayScreen.INPUT_RADIUS);
            } else {
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_NONAGON_SPAWN_P1.x, GameplayScreen.INPUT_NONAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererFilled.circle(GameplayScreen.INPUT_NONAGON_SPAWN_P2.x, GameplayScreen.INPUT_NONAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
                drawNonagon(GameplayScreen.INPUT_NONAGON_SPAWN_P1.x, GameplayScreen.INPUT_NONAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                drawNonagon(GameplayScreen.INPUT_NONAGON_SPAWN_P2.x, GameplayScreen.INPUT_NONAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS, GameplayScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_NONAGON_SPAWN_P1.x, GameplayScreen.INPUT_NONAGON_SPAWN_P1.y, GameplayScreen.INPUT_RADIUS);
                game.shapeRendererLine.circle(GameplayScreen.INPUT_NONAGON_SPAWN_P2.x, GameplayScreen.INPUT_NONAGON_SPAWN_P2.y, GameplayScreen.INPUT_RADIUS);
            }
        }
    }

    public void drawInputButtonsTutorial(boolean splitScreen, boolean localPlayer2, boolean unrestrictedPlay, int numPlayerInputs, Color perimeterColor, Squirgle game) {
        game.shapeRendererLine.setColor(perimeterColor);

        if(game.base >= 1) {
            //Point
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 0) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_POINT_SPAWN.x, TutorialScreen.INPUT_POINT_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawPoint(TutorialScreen.INPUT_POINT_SPAWN.x, TutorialScreen.INPUT_POINT_SPAWN.y, TutorialScreen.INPUT_RADIUS / 2, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_POINT_SPAWN.x, TutorialScreen.INPUT_POINT_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 0) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_POINT_SPAWN_P1.x, TutorialScreen.INPUT_POINT_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawPoint(TutorialScreen.INPUT_POINT_SPAWN_P1.x, TutorialScreen.INPUT_POINT_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS / 2, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_POINT_SPAWN_P1.x, TutorialScreen.INPUT_POINT_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_POINT_SPAWN_P2.x, TutorialScreen.INPUT_POINT_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawPoint(TutorialScreen.INPUT_POINT_SPAWN_P2.x, TutorialScreen.INPUT_POINT_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS / 2, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_POINT_SPAWN_P2.x, TutorialScreen.INPUT_POINT_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 2) {
            //Line
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 1) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_LINE_SPAWN.x, TutorialScreen.INPUT_LINE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawLine(TutorialScreen.INPUT_LINE_SPAWN.x, TutorialScreen.INPUT_LINE_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_LINE_SPAWN.x, TutorialScreen.INPUT_LINE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 1) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_LINE_SPAWN_P1.x, TutorialScreen.INPUT_LINE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawLine(TutorialScreen.INPUT_LINE_SPAWN_P1.x, TutorialScreen.INPUT_LINE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_LINE_SPAWN_P1.x, TutorialScreen.INPUT_LINE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_LINE_SPAWN_P2.x, TutorialScreen.INPUT_LINE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawLine(TutorialScreen.INPUT_LINE_SPAWN_P2.x, TutorialScreen.INPUT_LINE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_LINE_SPAWN_P2.x, TutorialScreen.INPUT_LINE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 3) {
            //Triangle
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 2) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN.x, TutorialScreen.INPUT_TRIANGLE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawTriangle(false, TutorialScreen.INPUT_TRIANGLE_SPAWN.x, TutorialScreen.INPUT_TRIANGLE_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN.x, TutorialScreen.INPUT_TRIANGLE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 2) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN_P1.x, TutorialScreen.INPUT_TRIANGLE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawTriangle(false, TutorialScreen.INPUT_TRIANGLE_SPAWN_P1.x, TutorialScreen.INPUT_TRIANGLE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN_P1.x, TutorialScreen.INPUT_TRIANGLE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN_P2.x, TutorialScreen.INPUT_TRIANGLE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawTriangle(localPlayer2, TutorialScreen.INPUT_TRIANGLE_SPAWN_P2.x, TutorialScreen.INPUT_TRIANGLE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_TRIANGLE_SPAWN_P2.x, TutorialScreen.INPUT_TRIANGLE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 4) {
            //Square
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 3) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_SQUARE_SPAWN.x, TutorialScreen.INPUT_SQUARE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawSquare(TutorialScreen.INPUT_SQUARE_SPAWN.x, TutorialScreen.INPUT_SQUARE_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_SQUARE_SPAWN.x, TutorialScreen.INPUT_SQUARE_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 3) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_SQUARE_SPAWN_P1.x, TutorialScreen.INPUT_SQUARE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawSquare(TutorialScreen.INPUT_SQUARE_SPAWN_P1.x, TutorialScreen.INPUT_SQUARE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_SQUARE_SPAWN_P1.x, TutorialScreen.INPUT_SQUARE_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_SQUARE_SPAWN_P2.x, TutorialScreen.INPUT_SQUARE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawSquare(TutorialScreen.INPUT_SQUARE_SPAWN_P2.x, TutorialScreen.INPUT_SQUARE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_SQUARE_SPAWN_P2.x, TutorialScreen.INPUT_SQUARE_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 5) {
            //Pentagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 4) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_PENTAGON_SPAWN.x, TutorialScreen.INPUT_PENTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawPentagon(TutorialScreen.INPUT_PENTAGON_SPAWN.x, TutorialScreen.INPUT_PENTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_PENTAGON_SPAWN.x, TutorialScreen.INPUT_PENTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 4) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_PENTAGON_SPAWN_P1.x, TutorialScreen.INPUT_PENTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawPentagon(TutorialScreen.INPUT_PENTAGON_SPAWN_P1.x, TutorialScreen.INPUT_PENTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_PENTAGON_SPAWN_P1.x, TutorialScreen.INPUT_PENTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_PENTAGON_SPAWN_P2.x, TutorialScreen.INPUT_PENTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawPentagon(TutorialScreen.INPUT_PENTAGON_SPAWN_P2.x, TutorialScreen.INPUT_PENTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_PENTAGON_SPAWN_P2.x, TutorialScreen.INPUT_PENTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 6) {
            //Hexagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 5) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_HEXAGON_SPAWN.x, TutorialScreen.INPUT_HEXAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawHexagon(TutorialScreen.INPUT_HEXAGON_SPAWN.x, TutorialScreen.INPUT_HEXAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_HEXAGON_SPAWN.x, TutorialScreen.INPUT_HEXAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 5) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_HEXAGON_SPAWN_P1.x, TutorialScreen.INPUT_HEXAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawHexagon(TutorialScreen.INPUT_HEXAGON_SPAWN_P1.x, TutorialScreen.INPUT_HEXAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_HEXAGON_SPAWN_P1.x, TutorialScreen.INPUT_HEXAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_HEXAGON_SPAWN_P2.x, TutorialScreen.INPUT_HEXAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawHexagon(TutorialScreen.INPUT_HEXAGON_SPAWN_P2.x, TutorialScreen.INPUT_HEXAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_HEXAGON_SPAWN_P2.x, TutorialScreen.INPUT_HEXAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 7) {
            //Septagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 6) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_SEPTAGON_SPAWN.x, TutorialScreen.INPUT_SEPTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawSeptagon(TutorialScreen.INPUT_SEPTAGON_SPAWN.x, TutorialScreen.INPUT_SEPTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_SEPTAGON_SPAWN.x, TutorialScreen.INPUT_SEPTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 6) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_SEPTAGON_SPAWN_P1.x, TutorialScreen.INPUT_SEPTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawSeptagon(TutorialScreen.INPUT_SEPTAGON_SPAWN_P1.x, TutorialScreen.INPUT_SEPTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_SEPTAGON_SPAWN_P1.x, TutorialScreen.INPUT_SEPTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_SEPTAGON_SPAWN_P2.x, TutorialScreen.INPUT_SEPTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawSeptagon(TutorialScreen.INPUT_SEPTAGON_SPAWN_P2.x, TutorialScreen.INPUT_SEPTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_SEPTAGON_SPAWN_P2.x, TutorialScreen.INPUT_SEPTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 8) {
            //Octagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 7) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_OCTAGON_SPAWN.x, TutorialScreen.INPUT_OCTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawOctagon(TutorialScreen.INPUT_OCTAGON_SPAWN.x, TutorialScreen.INPUT_OCTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_OCTAGON_SPAWN.x, TutorialScreen.INPUT_OCTAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 7) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_OCTAGON_SPAWN_P1.x, TutorialScreen.INPUT_OCTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawOctagon(TutorialScreen.INPUT_OCTAGON_SPAWN_P1.x, TutorialScreen.INPUT_OCTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_OCTAGON_SPAWN_P1.x, TutorialScreen.INPUT_OCTAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_OCTAGON_SPAWN_P2.x, TutorialScreen.INPUT_OCTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawOctagon(TutorialScreen.INPUT_OCTAGON_SPAWN_P2.x, TutorialScreen.INPUT_OCTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_OCTAGON_SPAWN_P2.x, TutorialScreen.INPUT_OCTAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }

        if(game.base >= 9) {
            //Nonagon
            game.shapeRendererFilled.setColor(Color.WHITE);
            if(!splitScreen) {
                if(numPlayerInputs > 8) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_NONAGON_SPAWN.x, TutorialScreen.INPUT_NONAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                    drawNonagon(TutorialScreen.INPUT_NONAGON_SPAWN.x, TutorialScreen.INPUT_NONAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_NONAGON_SPAWN.x, TutorialScreen.INPUT_NONAGON_SPAWN.y, TutorialScreen.INPUT_RADIUS);
                }
            } else {
                if(numPlayerInputs > 8) {
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_NONAGON_SPAWN_P1.x, TutorialScreen.INPUT_NONAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                    drawNonagon(TutorialScreen.INPUT_NONAGON_SPAWN_P1.x, TutorialScreen.INPUT_NONAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_NONAGON_SPAWN_P1.x, TutorialScreen.INPUT_NONAGON_SPAWN_P1.y, TutorialScreen.INPUT_RADIUS);
                }
                if(unrestrictedPlay) {
                    game.shapeRendererFilled.setColor(Color.WHITE);
                    game.shapeRendererFilled.circle(TutorialScreen.INPUT_NONAGON_SPAWN_P2.x, TutorialScreen.INPUT_NONAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                    drawNonagon(TutorialScreen.INPUT_NONAGON_SPAWN_P2.x, TutorialScreen.INPUT_NONAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS, TutorialScreen.INPUT_RADIUS / LINE_WIDTH_DIVISOR, 0, Color.BLACK);
                    game.shapeRendererLine.circle(TutorialScreen.INPUT_NONAGON_SPAWN_P2.x, TutorialScreen.INPUT_NONAGON_SPAWN_P2.y, TutorialScreen.INPUT_RADIUS);
                }
            }
        }
    }

    public void drawResultsInputButtons(Color resultsColor, Vector2 inputPlaySpawn, Vector2 inputHomeSpawn, Vector2 inputExitSpawn) {
        Color symbolColor = resultsColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;

        //Replay
        drawReplaySymbol(inputPlaySpawn.x, inputPlaySpawn.y, GameplayScreen.INPUT_RADIUS, symbolColor, resultsColor);

        //Home
        game.shapeRendererFilled.setColor(resultsColor);
        game.shapeRendererFilled.circle(inputHomeSpawn.x, inputHomeSpawn.y, GameplayScreen.INPUT_RADIUS);
        drawBackButton(inputHomeSpawn.x, inputHomeSpawn.y, GameplayScreen.INPUT_RADIUS / 2, (GameplayScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, symbolColor);

        //Exit
        game.shapeRendererFilled.setColor(resultsColor);
        game.shapeRendererFilled.circle(inputExitSpawn.x, inputExitSpawn.y, GameplayScreen.INPUT_RADIUS);
        drawStopSymbol(inputExitSpawn.x, inputExitSpawn.y, GameplayScreen.INPUT_RADIUS, symbolColor);
    }

    public void drawResultsInputButtonsTutorial(Color resultsColor, Vector2 inputPlaySpawn, Vector2 inputHomeSpawn, Vector2 inputExitSpawn) {
        Color symbolColor = resultsColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;

        //Replay
        drawReplaySymbol(inputPlaySpawn.x, inputPlaySpawn.y, TutorialScreen.INPUT_RADIUS, symbolColor, resultsColor);

        //Home
        game.shapeRendererFilled.setColor(resultsColor);
        game.shapeRendererFilled.circle(inputHomeSpawn.x, inputHomeSpawn.y, TutorialScreen.INPUT_RADIUS);
        drawBackButton(inputHomeSpawn.x, inputHomeSpawn.y, TutorialScreen.INPUT_RADIUS / 2, (TutorialScreen.INPUT_RADIUS / 2) / LINE_WIDTH_DIVISOR, symbolColor);

        //Exit
        game.shapeRendererFilled.setColor(resultsColor);
        game.shapeRendererFilled.circle(inputExitSpawn.x, inputExitSpawn.y, TutorialScreen.INPUT_RADIUS);
        drawStopSymbol(inputExitSpawn.x, inputExitSpawn.y, TutorialScreen.INPUT_RADIUS, symbolColor);
    }

    public void drawEquation(boolean localMobilePlayer2, String player, Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, equationWidth, Color.BLACK, Color.BLACK, equationWidth / LINE_WIDTH_DIVISOR, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        if(player == null) {
            switch (lastShapeTouched.getShape()) {
                case Shape.POINT:
                    spawnBegin = GameplayScreen.INPUT_POINT_SPAWN;
                    if (lastPromptShape.getShape() + 1 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 1);
                    }
                    break;
                case Shape.LINE:
                    spawnBegin = GameplayScreen.INPUT_LINE_SPAWN;
                    if (lastPromptShape.getShape() + 2 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 2);
                    }
                    break;
                case Shape.TRIANGLE:
                    spawnBegin = GameplayScreen.INPUT_TRIANGLE_SPAWN;
                    if (lastPromptShape.getShape() + 3 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 3);
                    }
                    break;
                case Shape.SQUARE:
                    spawnBegin = GameplayScreen.INPUT_SQUARE_SPAWN;
                    if (lastPromptShape.getShape() + 4 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 4);
                    }
                    break;
                case Shape.PENTAGON:
                    spawnBegin = GameplayScreen.INPUT_PENTAGON_SPAWN;
                    if (lastPromptShape.getShape() + 5 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 5);
                    }
                    break;
                case Shape.HEXAGON:
                    spawnBegin = GameplayScreen.INPUT_HEXAGON_SPAWN;
                    if (lastPromptShape.getShape() + 6 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 6);
                    }
                    break;
                case Shape.SEPTAGON:
                    spawnBegin = GameplayScreen.INPUT_SEPTAGON_SPAWN;
                    if (lastPromptShape.getShape() + 7 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 7);
                    }
                    break;
                case Shape.OCTAGON:
                    spawnBegin = GameplayScreen.INPUT_OCTAGON_SPAWN;
                    if (lastPromptShape.getShape() + 8 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 8);
                    }
                    break;
                case Shape.NONAGON:
                    spawnBegin = GameplayScreen.INPUT_NONAGON_SPAWN;
                    if (lastPromptShape.getShape() + 9 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 9);
                    }
                    break;
            }

            //Set up the ending coordinates
            switch (sum.getShape()) {
                case Shape.POINT:
                    spawnEnd = GameplayScreen.INPUT_POINT_SPAWN;
                    break;
                case Shape.LINE:
                    spawnEnd = GameplayScreen.INPUT_LINE_SPAWN;
                    break;
                case Shape.TRIANGLE:
                    spawnEnd = GameplayScreen.INPUT_TRIANGLE_SPAWN;
                    break;
                case Shape.SQUARE:
                    spawnEnd = GameplayScreen.INPUT_SQUARE_SPAWN;
                    break;
                case Shape.PENTAGON:
                    spawnEnd = GameplayScreen.INPUT_PENTAGON_SPAWN;
                    break;
                case Shape.HEXAGON:
                    spawnEnd = GameplayScreen.INPUT_HEXAGON_SPAWN;
                    break;
                case Shape.SEPTAGON:
                    spawnEnd = GameplayScreen.INPUT_SEPTAGON_SPAWN;
                    break;
                case Shape.OCTAGON:
                    spawnEnd = GameplayScreen.INPUT_OCTAGON_SPAWN;
                    break;
                case Shape.NONAGON:
                    spawnEnd = GameplayScreen.INPUT_NONAGON_SPAWN;
                    break;
            }
        } else if(player.equals(GameplayScreen.P1)) {
            switch (lastShapeTouched.getShape()) {
                case Shape.POINT:
                    spawnBegin = GameplayScreen.INPUT_POINT_SPAWN_P1;
                    if (lastPromptShape.getShape() + 1 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 1);
                    }
                    break;
                case Shape.LINE:
                    spawnBegin = GameplayScreen.INPUT_LINE_SPAWN_P1;
                    if (lastPromptShape.getShape() + 2 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 2);
                    }
                    break;
                case Shape.TRIANGLE:
                    spawnBegin = GameplayScreen.INPUT_TRIANGLE_SPAWN_P1;
                    if (lastPromptShape.getShape() + 3 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 3);
                    }
                    break;
                case Shape.SQUARE:
                    spawnBegin = GameplayScreen.INPUT_SQUARE_SPAWN_P1;
                    if (lastPromptShape.getShape() + 4 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 4);
                    }
                    break;
                case Shape.PENTAGON:
                    spawnBegin = GameplayScreen.INPUT_PENTAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 5 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 5);
                    }
                    break;
                case Shape.HEXAGON:
                    spawnBegin = GameplayScreen.INPUT_HEXAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 6 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 6);
                    }
                    break;
                case Shape.SEPTAGON:
                    spawnBegin = GameplayScreen.INPUT_SEPTAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 7 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 7);
                    }
                    break;
                case Shape.OCTAGON:
                    spawnBegin = GameplayScreen.INPUT_OCTAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 8 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 8);
                    }
                    break;
                case Shape.NONAGON:
                    spawnBegin = GameplayScreen.INPUT_NONAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 9 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 9);
                    }
                    break;
            }

            //Set up the ending coordinates
            switch (sum.getShape()) {
                case Shape.POINT:
                    spawnEnd = GameplayScreen.INPUT_POINT_SPAWN_P1;
                    break;
                case Shape.LINE:
                    spawnEnd = GameplayScreen.INPUT_LINE_SPAWN_P1;
                    break;
                case Shape.TRIANGLE:
                    spawnEnd = GameplayScreen.INPUT_TRIANGLE_SPAWN_P1;
                    break;
                case Shape.SQUARE:
                    spawnEnd = GameplayScreen.INPUT_SQUARE_SPAWN_P1;
                    break;
                case Shape.PENTAGON:
                    spawnEnd = GameplayScreen.INPUT_PENTAGON_SPAWN_P1;
                    break;
                case Shape.HEXAGON:
                    spawnEnd = GameplayScreen.INPUT_HEXAGON_SPAWN_P1;
                    break;
                case Shape.SEPTAGON:
                    spawnEnd = GameplayScreen.INPUT_SEPTAGON_SPAWN_P1;
                    break;
                case Shape.OCTAGON:
                    spawnEnd = GameplayScreen.INPUT_OCTAGON_SPAWN_P1;
                    break;
                case Shape.NONAGON:
                    spawnEnd = GameplayScreen.INPUT_NONAGON_SPAWN_P1;
                    break;
            }
        } else {
            switch (lastShapeTouched.getShape()) {
                case Shape.POINT:
                    spawnBegin = GameplayScreen.INPUT_POINT_SPAWN_P2;
                    if (lastPromptShape.getShape() + 1 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 1);
                    }
                    break;
                case Shape.LINE:
                    spawnBegin = GameplayScreen.INPUT_LINE_SPAWN_P2;
                    if (lastPromptShape.getShape() + 2 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 2);
                    }
                    break;
                case Shape.TRIANGLE:
                    spawnBegin = GameplayScreen.INPUT_TRIANGLE_SPAWN_P2;
                    if (lastPromptShape.getShape() + 3 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 3);
                    }
                    break;
                case Shape.SQUARE:
                    spawnBegin = GameplayScreen.INPUT_SQUARE_SPAWN_P2;
                    if (lastPromptShape.getShape() + 4 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 4);
                    }
                    break;
                case Shape.PENTAGON:
                    spawnBegin = GameplayScreen.INPUT_PENTAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 5 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 5);
                    }
                    break;
                case Shape.HEXAGON:
                    spawnBegin = GameplayScreen.INPUT_HEXAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 6 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 6);
                    }
                    break;
                case Shape.SEPTAGON:
                    spawnBegin = GameplayScreen.INPUT_SEPTAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 7 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 7);
                    }
                    break;
                case Shape.OCTAGON:
                    spawnBegin = GameplayScreen.INPUT_OCTAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 8 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 8);
                    }
                    break;
                case Shape.NONAGON:
                    spawnBegin = GameplayScreen.INPUT_NONAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 9 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 9);
                    }
                    break;
            }

            //Set up the ending coordinates
            switch (sum.getShape()) {
                case Shape.POINT:
                    spawnEnd = GameplayScreen.INPUT_POINT_SPAWN_P2;
                    break;
                case Shape.LINE:
                    spawnEnd = GameplayScreen.INPUT_LINE_SPAWN_P2;
                    break;
                case Shape.TRIANGLE:
                    spawnEnd = GameplayScreen.INPUT_TRIANGLE_SPAWN_P2;
                    break;
                case Shape.SQUARE:
                    spawnEnd = GameplayScreen.INPUT_SQUARE_SPAWN_P2;
                    break;
                case Shape.PENTAGON:
                    spawnEnd = GameplayScreen.INPUT_PENTAGON_SPAWN_P2;
                    break;
                case Shape.HEXAGON:
                    spawnEnd = GameplayScreen.INPUT_HEXAGON_SPAWN_P2;
                    break;
                case Shape.SEPTAGON:
                    spawnEnd = GameplayScreen.INPUT_SEPTAGON_SPAWN_P2;
                    break;
                case Shape.OCTAGON:
                    spawnEnd = GameplayScreen.INPUT_OCTAGON_SPAWN_P2;
                    break;
                case Shape.NONAGON:
                    spawnEnd = GameplayScreen.INPUT_NONAGON_SPAWN_P2;
                    break;
            }
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
            if(player == null) {
                equalsTargetSpawn.x = (targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().x - (targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2))) / 2);
                equalsTargetSpawn.y = (targetSpawn.y - (GameplayScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().y - (targetSpawn.y - (GameplayScreen.TARGET_RADIUS / 2))) / 2);
            } else if(player.equals(GameplayScreen.P1)) {
                equalsTargetSpawn.x = targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2) + ((lastPromptShape.getCoordinates().x - (targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2))) / 2);
                equalsTargetSpawn.y = (targetSpawn.y / 2) - (GameplayScreen.TARGET_RADIUS / 2) + ((lastPromptShape.getCoordinates().y - ((targetSpawn.y / 2) - (GameplayScreen.TARGET_RADIUS / 2))) / 2);
            } else {
                if(localMobilePlayer2) {
                    equalsTargetSpawn.x = game.camera.viewportWidth - (GameplayScreen.TARGET_RADIUS / 2) - ((lastPromptShape.getCoordinates().x - (GameplayScreen.TARGET_RADIUS / 2)) / 2);
                    equalsTargetSpawn.y = (game.camera.viewportHeight / 2) + (GameplayScreen.TARGET_RADIUS / 2) + ((lastPromptShape.getCoordinates().y - ((game.camera.viewportHeight / 2) + (GameplayScreen.TARGET_RADIUS / 2))) / 2);
                } else {
                    equalsTargetSpawn.x = (targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().x - (targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2))) / 2);
                    equalsTargetSpawn.y = (targetSpawn.y - (GameplayScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().y - (targetSpawn.y - (GameplayScreen.TARGET_RADIUS / 2))) / 2);
                }
            }
        }

        game.shapeRendererFilled.setColor(Color.WHITE);

        //Draw the circles
        game.shapeRendererFilled.circle(plusSpawn.x,
                plusSpawn.y,
                equationWidth);
        if(lastPromptShape.getShape() == Shape.POINT) {
            game.shapeRendererFilled.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius() * 2);
        } else {
            game.shapeRendererFilled.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius());
        }
        game.shapeRendererFilled.circle(equalsSpawn.x,
                equalsSpawn.y,
                equationWidth);
        if(sum.getShape() == lastTargetShape.getShape()) {
            game.shapeRendererFilled.circle(equalsTargetSpawn.x,
                    equalsTargetSpawn.y,
                    equationWidth);
        }

        //Draw the lines
        game.shapeRendererFilled.rectLine(spawnBegin, plusSpawn, equationWidth * 2);
        game.shapeRendererFilled.rectLine(plusSpawn, lastPromptShape.getCoordinates(), equationWidth * 2);
        game.shapeRendererFilled.rectLine(lastPromptShape.getCoordinates(), equalsSpawn, equationWidth * 2);
        game.shapeRendererFilled.rectLine(equalsSpawn, spawnEnd, equationWidth * 2);
        if(sum.getShape() == lastTargetShape.getShape()) {
            game.shapeRendererFilled.rectLine(lastPromptShape.getCoordinates(), equalsTargetSpawn, equationWidth * 2);
            if(player != null && player.equals(GameplayScreen.P1)) {
                game.shapeRendererFilled.rectLine(equalsTargetSpawn.x, equalsTargetSpawn.y, targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2), (targetSpawn.y / 2) - (GameplayScreen.TARGET_RADIUS / 2), equationWidth * 2);
            } else if(player != null && player.equals(GameplayScreen.P2) && localMobilePlayer2) {
                game.shapeRendererFilled.rectLine(equalsTargetSpawn.x, equalsTargetSpawn.y, game.camera.viewportWidth - (GameplayScreen.TARGET_RADIUS / 2), (game.camera.viewportHeight / 2) + (GameplayScreen.TARGET_RADIUS / 2), equationWidth * 2);
            } else {
                game.shapeRendererFilled.rectLine(equalsTargetSpawn.x, equalsTargetSpawn.y, targetSpawn.x + (GameplayScreen.TARGET_RADIUS / 2), targetSpawn.y - (GameplayScreen.TARGET_RADIUS / 2), equationWidth * 2);
            }
        }

        //Draw the plus symbol
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK);

        //Draw the equals symbol(s)
        drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK);
        if(sum.getShape() == lastTargetShape.getShape()) {
            drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK);
        }

        //Draw the last prompt shape
        lastPromptShape.setRadius(lastPromptShape.getRadius() - ((lastPromptShape.getRadius() * (GameplayScreen.INPUT_RADIUS / Squirgle.FPS)) / equationWidth));
        lastPromptShape.setLineWidth((lastPromptShape.getRadius() - (GameplayScreen.INPUT_RADIUS / Squirgle.FPS)) / LINE_WIDTH_DIVISOR);
        lastPromptShape.setFillColor(null);
        drawShape(localMobilePlayer2, lastPromptShape);
    }

    public void drawEquationTutorial(boolean localPlayer2, String player, boolean showPlayerTargets, int numPlayerInputs, Shape lastShapeTouched, Shape lastPromptShape, Shape lastTargetShape, float equationWidth) {
        Vector2 spawnBegin = new Vector2();
        Vector2 spawnEnd = new Vector2();
        Vector2 plusSpawn = new Vector2();
        Vector2 equalsSpawn = new Vector2();
        Vector2 equalsTargetSpawn = new Vector2();
        Shape sum = new Shape(Shape.POINT, equationWidth, Color.BLACK, Color.BLACK, equationWidth / LINE_WIDTH_DIVISOR, lastPromptShape.getCoordinates());

        //Set up the beginning coordinates and sum shape
        if(player == null) {
            switch (lastShapeTouched.getShape()) {
                case Shape.POINT:
                    spawnBegin = TutorialScreen.INPUT_POINT_SPAWN;
                    if (lastPromptShape.getShape() + 1 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 1);
                    }
                    break;
                case Shape.LINE:
                    spawnBegin = TutorialScreen.INPUT_LINE_SPAWN;
                    if (lastPromptShape.getShape() + 2 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 2);
                    }
                    break;
                case Shape.TRIANGLE:
                    spawnBegin = TutorialScreen.INPUT_TRIANGLE_SPAWN;
                    if (lastPromptShape.getShape() + 3 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 3);
                    }
                    break;
                case Shape.SQUARE:
                    spawnBegin = TutorialScreen.INPUT_SQUARE_SPAWN;
                    if (lastPromptShape.getShape() + 4 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 4);
                    }
                    break;
                case Shape.PENTAGON:
                    spawnBegin = TutorialScreen.INPUT_PENTAGON_SPAWN;
                    if (lastPromptShape.getShape() + 5 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 5);
                    }
                    break;
                case Shape.HEXAGON:
                    spawnBegin = TutorialScreen.INPUT_HEXAGON_SPAWN;
                    if (lastPromptShape.getShape() + 6 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 6);
                    }
                    break;
                case Shape.SEPTAGON:
                    spawnBegin = TutorialScreen.INPUT_SEPTAGON_SPAWN;
                    if (lastPromptShape.getShape() + 7 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 7);
                    }
                    break;
                case Shape.OCTAGON:
                    spawnBegin = TutorialScreen.INPUT_OCTAGON_SPAWN;
                    if (lastPromptShape.getShape() + 8 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 8);
                    }
                    break;
                case Shape.NONAGON:
                    spawnBegin = TutorialScreen.INPUT_NONAGON_SPAWN;
                    if (lastPromptShape.getShape() + 9 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 9);
                    }
                    break;
            }

            //Set up the ending coordinates
            switch (sum.getShape()) {
                case Shape.POINT:
                    spawnEnd = TutorialScreen.INPUT_POINT_SPAWN;
                    break;
                case Shape.LINE:
                    spawnEnd = TutorialScreen.INPUT_LINE_SPAWN;
                    break;
                case Shape.TRIANGLE:
                    spawnEnd = TutorialScreen.INPUT_TRIANGLE_SPAWN;
                    break;
                case Shape.SQUARE:
                    spawnEnd = TutorialScreen.INPUT_SQUARE_SPAWN;
                    break;
                case Shape.PENTAGON:
                    spawnEnd = TutorialScreen.INPUT_PENTAGON_SPAWN;
                    break;
                case Shape.HEXAGON:
                    spawnEnd = TutorialScreen.INPUT_HEXAGON_SPAWN;
                    break;
                case Shape.SEPTAGON:
                    spawnEnd = TutorialScreen.INPUT_SEPTAGON_SPAWN;
                    break;
                case Shape.OCTAGON:
                    spawnEnd = TutorialScreen.INPUT_OCTAGON_SPAWN;
                    break;
                case Shape.NONAGON:
                    spawnEnd = TutorialScreen.INPUT_NONAGON_SPAWN;
                    break;
            }
        } else if(player.equals(TutorialScreen.P1)) {
            switch (lastShapeTouched.getShape()) {
                case Shape.POINT:
                    spawnBegin = TutorialScreen.INPUT_POINT_SPAWN_P1;
                    if (lastPromptShape.getShape() + 1 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 1);
                    }
                    break;
                case Shape.LINE:
                    spawnBegin = TutorialScreen.INPUT_LINE_SPAWN_P1;
                    if (lastPromptShape.getShape() + 2 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 2);
                    }
                    break;
                case Shape.TRIANGLE:
                    spawnBegin = TutorialScreen.INPUT_TRIANGLE_SPAWN_P1;
                    if (lastPromptShape.getShape() + 3 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 3);
                    }
                    break;
                case Shape.SQUARE:
                    spawnBegin = TutorialScreen.INPUT_SQUARE_SPAWN_P1;
                    if (lastPromptShape.getShape() + 4 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 4);
                    }
                    break;
                case Shape.PENTAGON:
                    spawnBegin = TutorialScreen.INPUT_PENTAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 5 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 5);
                    }
                    break;
                case Shape.HEXAGON:
                    spawnBegin = TutorialScreen.INPUT_HEXAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 6 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 6);
                    }
                    break;
                case Shape.SEPTAGON:
                    spawnBegin = TutorialScreen.INPUT_SEPTAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 7 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 7);
                    }
                    break;
                case Shape.OCTAGON:
                    spawnBegin = TutorialScreen.INPUT_OCTAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 8 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 8);
                    }
                    break;
                case Shape.NONAGON:
                    spawnBegin = TutorialScreen.INPUT_NONAGON_SPAWN_P1;
                    if (lastPromptShape.getShape() + 9 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 9);
                    }
                    break;
            }

            //Set up the ending coordinates
            switch (sum.getShape()) {
                case Shape.POINT:
                    spawnEnd = TutorialScreen.INPUT_POINT_SPAWN_P1;
                    break;
                case Shape.LINE:
                    spawnEnd = TutorialScreen.INPUT_LINE_SPAWN_P1;
                    break;
                case Shape.TRIANGLE:
                    spawnEnd = TutorialScreen.INPUT_TRIANGLE_SPAWN_P1;
                    break;
                case Shape.SQUARE:
                    spawnEnd = TutorialScreen.INPUT_SQUARE_SPAWN_P1;
                    break;
                case Shape.PENTAGON:
                    spawnEnd = TutorialScreen.INPUT_PENTAGON_SPAWN_P1;
                    break;
                case Shape.HEXAGON:
                    spawnEnd = TutorialScreen.INPUT_HEXAGON_SPAWN_P1;
                    break;
                case Shape.SEPTAGON:
                    spawnEnd = TutorialScreen.INPUT_SEPTAGON_SPAWN_P1;
                    break;
                case Shape.OCTAGON:
                    spawnEnd = TutorialScreen.INPUT_OCTAGON_SPAWN_P1;
                    break;
                case Shape.NONAGON:
                    spawnEnd = TutorialScreen.INPUT_NONAGON_SPAWN_P1;
                    break;
            }
        } else {
            switch (lastShapeTouched.getShape()) {
                case Shape.POINT:
                    spawnBegin = TutorialScreen.INPUT_POINT_SPAWN_P2;
                    if (lastPromptShape.getShape() + 1 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 1) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 1);
                    }
                    break;
                case Shape.LINE:
                    spawnBegin = TutorialScreen.INPUT_LINE_SPAWN_P2;
                    if (lastPromptShape.getShape() + 2 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 2) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 2);
                    }
                    break;
                case Shape.TRIANGLE:
                    spawnBegin = TutorialScreen.INPUT_TRIANGLE_SPAWN_P2;
                    if (lastPromptShape.getShape() + 3 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 3) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 3);
                    }
                    break;
                case Shape.SQUARE:
                    spawnBegin = TutorialScreen.INPUT_SQUARE_SPAWN_P2;
                    if (lastPromptShape.getShape() + 4 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 4) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 4);
                    }
                    break;
                case Shape.PENTAGON:
                    spawnBegin = TutorialScreen.INPUT_PENTAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 5 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 5) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 5);
                    }
                    break;
                case Shape.HEXAGON:
                    spawnBegin = TutorialScreen.INPUT_HEXAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 6 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 6) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 6);
                    }
                    break;
                case Shape.SEPTAGON:
                    spawnBegin = TutorialScreen.INPUT_SEPTAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 7 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 7) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 7);
                    }
                    break;
                case Shape.OCTAGON:
                    spawnBegin = TutorialScreen.INPUT_OCTAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 8 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 8) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 8);
                    }
                    break;
                case Shape.NONAGON:
                    spawnBegin = TutorialScreen.INPUT_NONAGON_SPAWN_P2;
                    if (lastPromptShape.getShape() + 9 >= game.base) {
                        sum.setShape((lastPromptShape.getShape() + 9) - game.base);
                    } else {
                        sum.setShape(lastPromptShape.getShape() + 9);
                    }
                    break;
            }

            //Set up the ending coordinates
            switch (sum.getShape()) {
                case Shape.POINT:
                    spawnEnd = TutorialScreen.INPUT_POINT_SPAWN_P2;
                    break;
                case Shape.LINE:
                    spawnEnd = TutorialScreen.INPUT_LINE_SPAWN_P2;
                    break;
                case Shape.TRIANGLE:
                    spawnEnd = TutorialScreen.INPUT_TRIANGLE_SPAWN_P2;
                    break;
                case Shape.SQUARE:
                    spawnEnd = TutorialScreen.INPUT_SQUARE_SPAWN_P2;
                    break;
                case Shape.PENTAGON:
                    spawnEnd = TutorialScreen.INPUT_PENTAGON_SPAWN_P2;
                    break;
                case Shape.HEXAGON:
                    spawnEnd = TutorialScreen.INPUT_HEXAGON_SPAWN_P2;
                    break;
                case Shape.SEPTAGON:
                    spawnEnd = TutorialScreen.INPUT_SEPTAGON_SPAWN_P2;
                    break;
                case Shape.OCTAGON:
                    spawnEnd = TutorialScreen.INPUT_OCTAGON_SPAWN_P2;
                    break;
                case Shape.NONAGON:
                    spawnEnd = TutorialScreen.INPUT_NONAGON_SPAWN_P2;
                    break;
            }
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
            if(player == null) {
                equalsTargetSpawn.x = (targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().x - (targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2))) / 2);
                equalsTargetSpawn.y = (targetSpawn.y - (TutorialScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().y - (targetSpawn.y - (TutorialScreen.TARGET_RADIUS / 2))) / 2);
            } else if(player.equals(TutorialScreen.P1)) {
                equalsTargetSpawn.x = targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2) + ((lastPromptShape.getCoordinates().x - (targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2))) / 2);
                equalsTargetSpawn.y = (targetSpawn.y / 2) - (TutorialScreen.TARGET_RADIUS / 2) + ((lastPromptShape.getCoordinates().y - ((targetSpawn.y / 2) - (TutorialScreen.TARGET_RADIUS / 2))) / 2);
            } else {
                if(localPlayer2) {
                    equalsTargetSpawn.x = game.camera.viewportWidth - (TutorialScreen.TARGET_RADIUS / 2) - ((lastPromptShape.getCoordinates().x - (TutorialScreen.TARGET_RADIUS / 2)) / 2);
                    equalsTargetSpawn.y = (game.camera.viewportHeight / 2) + (TutorialScreen.TARGET_RADIUS / 2) + ((lastPromptShape.getCoordinates().y - ((game.camera.viewportHeight / 2) + (TutorialScreen.TARGET_RADIUS / 2))) / 2);
                } else {
                    equalsTargetSpawn.x = (targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().x - (targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2))) / 2);
                    equalsTargetSpawn.y = (targetSpawn.y - (TutorialScreen.TARGET_RADIUS / 2)) + ((lastPromptShape.getCoordinates().y - (targetSpawn.y - (TutorialScreen.TARGET_RADIUS / 2))) / 2);
                }
            }
        }

        game.shapeRendererFilled.setColor(Color.WHITE);

        //Draw the circles
        game.shapeRendererFilled.circle(plusSpawn.x,
                plusSpawn.y,
                equationWidth);
        if(lastPromptShape.getShape() == Shape.POINT) {
            game.shapeRendererFilled.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius() * 2);
        } else {
            game.shapeRendererFilled.circle(lastPromptShape.getCoordinates().x,
                    lastPromptShape.getCoordinates().y,
                    lastPromptShape.getRadius());
        }
        if(numPlayerInputs > 1) {
            game.shapeRendererFilled.circle(equalsSpawn.x,
                    equalsSpawn.y,
                    equationWidth);
        }
        if(showPlayerTargets) {
            if (sum.getShape() == lastTargetShape.getShape()) {
                game.shapeRendererFilled.circle(equalsTargetSpawn.x,
                        equalsTargetSpawn.y,
                        equationWidth);
            }
        }

        //Draw the lines
        game.shapeRendererFilled.rectLine(spawnBegin, plusSpawn, equationWidth * 2);
        game.shapeRendererFilled.rectLine(plusSpawn, lastPromptShape.getCoordinates(), equationWidth * 2);
        if(numPlayerInputs > 1) {
            game.shapeRendererFilled.rectLine(lastPromptShape.getCoordinates(), equalsSpawn, equationWidth * 2);
            game.shapeRendererFilled.rectLine(equalsSpawn, spawnEnd, equationWidth * 2);
        }
        if(showPlayerTargets) {
            if (sum.getShape() == lastTargetShape.getShape()) {
                game.shapeRendererFilled.rectLine(lastPromptShape.getCoordinates(), equalsTargetSpawn, equationWidth * 2);
                if (player != null && player.equals(TutorialScreen.P1)) {
                    game.shapeRendererFilled.rectLine(equalsTargetSpawn.x, equalsTargetSpawn.y, targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2), (targetSpawn.y / 2) - (TutorialScreen.TARGET_RADIUS / 2), equationWidth * 2);
                } else if (player != null && player.equals(TutorialScreen.P2) && localPlayer2) {
                    game.shapeRendererFilled.rectLine(equalsTargetSpawn.x, equalsTargetSpawn.y, game.camera.viewportWidth - (TutorialScreen.TARGET_RADIUS / 2), (game.camera.viewportHeight / 2) + (TutorialScreen.TARGET_RADIUS / 2), equationWidth * 2);
                } else {
                    game.shapeRendererFilled.rectLine(equalsTargetSpawn.x, equalsTargetSpawn.y, targetSpawn.x + (TutorialScreen.TARGET_RADIUS / 2), targetSpawn.y - (TutorialScreen.TARGET_RADIUS / 2), equationWidth * 2);
                }
            }
        }

        //Draw the plus symbol
        drawPlus(plusSpawn.x, plusSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK);

        //Draw the equals symbol(s)
        if(numPlayerInputs > 1) {
            drawEquals(equalsSpawn.x, equalsSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK);
        }
        if(showPlayerTargets) {
            if (sum.getShape() == lastTargetShape.getShape()) {
                drawEquals(equalsTargetSpawn.x, equalsTargetSpawn.y, equationWidth, equationWidth / LINE_WIDTH_DIVISOR, Color.BLACK);
            }
        }

        //Draw the last prompt shape
        lastPromptShape.setRadius(lastPromptShape.getRadius() - ((lastPromptShape.getRadius() * (TutorialScreen.INPUT_RADIUS / Squirgle.FPS)) / equationWidth));
        lastPromptShape.setLineWidth((lastPromptShape.getRadius() - (TutorialScreen.INPUT_RADIUS / Squirgle.FPS)) / LINE_WIDTH_DIVISOR);
        lastPromptShape.setFillColor(null);
        drawShape(localPlayer2, lastPromptShape);
    }

    public void drawBackgroundColorShape(Shape backgroundColorShape) {
        drawShape(false, backgroundColorShape);
        if (backgroundColorShape.getRadius() < game.camera.viewportHeight * THRESHOLD_MULTIPLIER) {
            backgroundColorShape.setRadius(backgroundColorShape.getRadius() + colorSpeed);
        }
    }

    //TODO: Clean this up such that the timelines always have the same max length
    public void drawBackgroundColorShapeList(boolean splitScreen, boolean blackAndWhite, boolean localMobile, List<Shape> backgroundColorShapeList, Shape backgroundColorShape, Color clearColor) {
        for (int i = 0; i < backgroundColorShapeList.size(); i++) {
            Shape shape = backgroundColorShapeList.get(i);
            drawShape(false, shape);
            if(splitScreen) {
                Shape p2Shape = new Shape(shape.getShape(), shape.getRadius(), shape.getColor(), shape.getFillColor(), shape.getLineWidth(), shape.getCoordinates());
                if(localMobile) {
                    p2Shape.setCoordinates(new Vector2((game.camera.viewportWidth / 2) - (shape.getCoordinates().x - (game.camera.viewportWidth / 2)), (game.camera.viewportHeight / 2) - (shape.getCoordinates().y - (game.camera.viewportHeight / 2))));
                } else {
                    p2Shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y + game.camera.viewportHeight / 2));
                }
                drawShape(false, p2Shape);
            }
            if (i == 0) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x + colorListSpeed, shape.getCoordinates().y));
            } else if (i == backgroundColorShapeList.size() - 1) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x - colorListSpeed, shape.getCoordinates().y));
            } else {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y - colorListSpeed));
            }
            if (backgroundColorShapeList.get(0).getCoordinates().x >= backgroundColorShapeList.get(1).getCoordinates().x) {
                float newRadius = shape.getRadius();

                //Prevent backgroundColorShapeList.get(0) from going too far to the left or right
                backgroundColorShapeList.get(0).setCoordinates(new Vector2(backgroundColorShapeList.get(1).getCoordinates().x,
                        backgroundColorShapeList.get(0).getCoordinates().y));

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
                                blackAndWhite ? Color.BLACK : Color.WHITE,
                                blackAndWhite ? Color.BLACK : ColorUtils.randomColor(),
                                newRadius / LINE_WIDTH_DIVISOR,
                                new Vector2(GameplayScreen.BACKGROUND_COLOR_SHAPE_LIST_MIN_X,
                                        GameplayScreen.BACKGROUND_COLOR_SHAPE_LIST_MAX_Y)));
            }
        }
    }

    public void drawBackgroundColorShapeListTutorial(boolean splitScreen, boolean blackAndWhite, boolean localMobile, List<Shape> backgroundColorShapeList, Shape backgroundColorShape, Color backgroundColorShapeColorToAdd, Color clearColor) {
        for (int i = 0; i < backgroundColorShapeList.size(); i++) {
            Shape shape = backgroundColorShapeList.get(i);
            drawShape(false, shape);
            if(splitScreen) {
                Shape p2Shape = new Shape(shape.getShape(), shape.getRadius(), shape.getColor(), shape.getFillColor(), shape.getLineWidth(), shape.getCoordinates());
                if(localMobile) {
                    p2Shape.setCoordinates(new Vector2((game.camera.viewportWidth / 2) - (shape.getCoordinates().x - (game.camera.viewportWidth / 2)), (game.camera.viewportHeight / 2) - (shape.getCoordinates().y - (game.camera.viewportHeight / 2))));
                } else {
                    p2Shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y + game.camera.viewportHeight / 2));
                }
                drawShape(false, p2Shape);
            }
            if (i == 0) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x + colorListSpeed, shape.getCoordinates().y));
            } else if (i == backgroundColorShapeList.size() - 1) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x - colorListSpeed, shape.getCoordinates().y));
            } else {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y - colorListSpeed));
            }
            if (backgroundColorShapeList.get(0).getCoordinates().x >= backgroundColorShapeList.get(1).getCoordinates().x) {
                float newRadius = shape.getRadius();

                //Prevent backgroundColorShapeList.get(0) from going too far to the left or right
                backgroundColorShapeList.get(0).setCoordinates(new Vector2(backgroundColorShapeList.get(1).getCoordinates().x,
                        backgroundColorShapeList.get(0).getCoordinates().y));

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
                                blackAndWhite ? Color.BLACK : Color.WHITE,
                                blackAndWhite ? Color.BLACK : backgroundColorShapeColorToAdd != null ? backgroundColorShapeColorToAdd : ColorUtils.randomColor(),
                                newRadius / LINE_WIDTH_DIVISOR,
                                new Vector2(TutorialScreen.BACKGROUND_COLOR_SHAPE_LIST_MIN_X,
                                        TutorialScreen.BACKGROUND_COLOR_SHAPE_LIST_MAX_Y)));
            }
        }
    }

    public void drawTimeline(float x, float topY, float bottomY, float lineWidth) {
        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererFilled.rectLine(x, topY, x, bottomY, lineWidth);

        drawPoint(x, topY, lineWidth, Color.WHITE);
        drawPoint(x, bottomY, lineWidth, Color.WHITE);
    }

    //TODO: Configure this for local multiplayer
    public void drawTimelines(boolean splitScreen, boolean localMobile, Shape promptShape, List<Shape> backgroundColorShapeList) {
        Shape firstColorShape = backgroundColorShapeList.get(0);
        Shape lastColorShape = backgroundColorShapeList.get(backgroundColorShapeList.size() - 1);
        Shape lowColorShape = backgroundColorShapeList.get(3);
        float firstY = firstColorShape.getCoordinates().y;
        float secondY = firstColorShape.getCoordinates().y;
        float thirdY = firstColorShape.getCoordinates().y;
        int lineWidthMultiplier = 6;

        if(promptShape.getRadius() <= game.fourthOfScreen) {
            firstY = firstColorShape.getCoordinates().y;
        } else {
            firstY = firstColorShape.getCoordinates().y + ((lastColorShape.getCoordinates().y - firstColorShape.getCoordinates().y) * ((promptShape.getRadius() - game.fourthOfScreen) / (game.thirdOfScreen - game.fourthOfScreen)));
        }

        if(promptShape.getRadius() <= game.thirdOfScreen) {
            secondY = firstColorShape.getCoordinates().y;
        } else {
            secondY = firstColorShape.getCoordinates().y + ((lastColorShape.getCoordinates().y - firstColorShape.getCoordinates().y) * ((promptShape.getRadius() - game.thirdOfScreen) / (game.fiveTwelfthsOfScreen - game.thirdOfScreen)));
        }

        if(promptShape.getRadius() <= game.fiveTwelfthsOfScreen) {
            thirdY = firstColorShape.getCoordinates().y;
        } else {
            thirdY = firstColorShape.getCoordinates().y + ((lastColorShape.getCoordinates().y - firstColorShape.getCoordinates().y) * ((promptShape.getRadius() - game.fiveTwelfthsOfScreen) / ((game.widthOrHeightSmaller / 2) - game.fiveTwelfthsOfScreen)));
        }

        if(promptShape.getRadius() <= game.thirdOfScreen) {
            drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                    firstY,
                    lastColorShape.getCoordinates().y,
                    lowColorShape.getLineWidth() * lineWidthMultiplier);
            drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 4), //Multiplying by two to get desired distance from color shapes
                    secondY,
                    lastColorShape.getCoordinates().y,
                    lowColorShape.getLineWidth() * lineWidthMultiplier);
            drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 6), //Multiplying by two to get desired distance from color shapes
                    thirdY,
                    lastColorShape.getCoordinates().y,
                    lowColorShape.getLineWidth() * lineWidthMultiplier);
            if(splitScreen) {
                if(localMobile) {
                    drawTimeline(game.camera.viewportWidth - lowColorShape.getCoordinates().x - (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + ((game.camera.viewportHeight / 2) - firstY),
                            game.camera.viewportHeight - lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                    drawTimeline(game.camera.viewportWidth - lowColorShape.getCoordinates().x - (lowColorShape.getRadius() * 4), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + ((game.camera.viewportHeight / 2) - secondY),
                            game.camera.viewportHeight - lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                    drawTimeline(game.camera.viewportWidth - lowColorShape.getCoordinates().x - (lowColorShape.getRadius() * 6), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + ((game.camera.viewportHeight / 2) - thirdY),
                            game.camera.viewportHeight - lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                } else {
                    drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + firstY,
                            (game.camera.viewportHeight / 2) + lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                    drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 4), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + secondY,
                            (game.camera.viewportHeight / 2) + lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                    drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 6), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + thirdY,
                            (game.camera.viewportHeight / 2) + lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                }
            }
        } else if(promptShape.getRadius() <= game.fiveTwelfthsOfScreen) {
            drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                    secondY,
                    lastColorShape.getCoordinates().y,
                    lowColorShape.getLineWidth() * lineWidthMultiplier);
            drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 4), //Multiplying by two to get desired distance from color shapes
                    thirdY,
                    lastColorShape.getCoordinates().y,
                    lowColorShape.getLineWidth() * lineWidthMultiplier);
            if(splitScreen) {
                if(localMobile) {
                    drawTimeline(game.camera.viewportWidth - lowColorShape.getCoordinates().x - (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + ((game.camera.viewportHeight / 2) - secondY),
                            game.camera.viewportHeight - lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                    drawTimeline(game.camera.viewportWidth - lowColorShape.getCoordinates().x - (lowColorShape.getRadius() * 4), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + ((game.camera.viewportHeight / 2) - thirdY),
                            game.camera.viewportHeight - lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                } else {
                    drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + secondY,
                            (game.camera.viewportHeight / 2) + lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                    drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 4), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + thirdY,
                            (game.camera.viewportHeight / 2) + lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                }
            }
        } else {
            drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                    thirdY,
                    lastColorShape.getCoordinates().y,
                    lowColorShape.getLineWidth() * lineWidthMultiplier);
            if(splitScreen) {
                if(localMobile) {
                    drawTimeline(game.camera.viewportWidth - lowColorShape.getCoordinates().x - (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + ((game.camera.viewportHeight / 2) - thirdY),
                            game.camera.viewportHeight - lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                } else {
                    drawTimeline(lowColorShape.getCoordinates().x + (lowColorShape.getRadius() * 2), //Multiplying by two to get desired distance from color shapes
                            (game.camera.viewportHeight / 2) + thirdY,
                            (game.camera.viewportHeight / 2) + lastColorShape.getCoordinates().y,
                            lowColorShape.getLineWidth() * lineWidthMultiplier);
                }
            }
        }
    }

    public void drawTargetSemicircles(boolean splitScreen, boolean localMobile) {
        float polypRadius = (GameplayScreen.TARGET_RADIUS / 4);
        float polypOffset = (float)(Math.sqrt(Math.pow(GameplayScreen.TARGET_RADIUS, 2) + Math.pow(GameplayScreen.TARGET_RADIUS / 4, 2)) - GameplayScreen.TARGET_RADIUS);

        game.shapeRendererFilled.setColor(Color.WHITE);
        if(!localMobile) {
            game.shapeRendererFilled.circle(0, game.camera.viewportHeight, GameplayScreen.TARGET_RADIUS);
//            if(!game.hardcore) {
//                game.shapeRendererFilled.circle(GameplayScreen.TARGET_RADIUS + polypRadius - polypOffset, game.camera.viewportHeight - polypRadius, polypRadius);
//            }
        } else {
            game.shapeRendererFilled.arc(game.camera.viewportWidth, game.camera.viewportHeight / 2, GameplayScreen.TARGET_RADIUS, -ONE_HUNDRED_AND_EIGHTY_DEGREES, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
//            if(!game.hardcore) {
//                game.shapeRendererFilled.circle(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS - polypRadius + polypOffset, (game.camera.viewportHeight / 2) + polypRadius, polypRadius);
//            }
        }
        if(splitScreen) {
            game.shapeRendererFilled.arc(0, game.camera.viewportHeight / 2, GameplayScreen.TARGET_RADIUS, 0, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
//            if(!game.hardcore) {
//                game.shapeRendererFilled.circle(GameplayScreen.TARGET_RADIUS + polypRadius - polypOffset, (game.camera.viewportHeight / 2) - polypRadius, polypRadius);
//            }
        }
    }

    public void drawTargetSemicirclesTutorial(boolean splitScreen, boolean local, boolean showPlayerTargets, boolean unrestrictedPlay) {
        float polypRadius = (TutorialScreen.TARGET_RADIUS / 4);
        float polypOffset = (float)(Math.sqrt(Math.pow(TutorialScreen.TARGET_RADIUS, 2) + Math.pow(TutorialScreen.TARGET_RADIUS / 4, 2)) - TutorialScreen.TARGET_RADIUS);

        game.shapeRendererFilled.setColor(Color.WHITE);
        if((!splitScreen && showPlayerTargets) || (splitScreen && unrestrictedPlay)) {
            game.shapeRendererFilled.circle(0, game.camera.viewportHeight, TutorialScreen.TARGET_RADIUS);
            //game.shapeRendererFilled.circle(TutorialScreen.TARGET_RADIUS + polypRadius - polypOffset, game.camera.viewportHeight - polypRadius, polypRadius);
        }
        if(splitScreen && showPlayerTargets) {
            game.shapeRendererFilled.arc(0, game.camera.viewportHeight / 2, TutorialScreen.TARGET_RADIUS, 0, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
            //game.shapeRendererFilled.circle(TutorialScreen.TARGET_RADIUS + polypRadius - polypOffset, (game.camera.viewportHeight / 2) - polypRadius, polypRadius);
        }
    }

    public void drawArc(float x, float y, float start, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.arc(x, y, GameplayScreen.TARGET_RADIUS, start, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawArcTutorial(float x, float y, float start, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.arc(x, y, TutorialScreen.TARGET_RADIUS, start, -NINETY_ONE_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawScoreTriangles(boolean splitScreen, boolean localMobile, Color polypDividerColor) {
        float polypRadius = (GameplayScreen.TARGET_RADIUS / 2);

        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererLine.setColor(polypDividerColor);
        if(!localMobile) {
            game.shapeRendererFilled.triangle(game.camera.viewportWidth,
                    game.camera.viewportHeight,
                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
                    game.camera.viewportHeight,
                    game.camera.viewportWidth,
                    game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS);
//            if(splitScreen) {
//                game.shapeRendererFilled.triangle(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
//                        game.camera.viewportHeight,
//                        game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS - polypRadius,
//                        game.camera.viewportHeight - polypRadius,
//                        game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + polypRadius,
//                        game.camera.viewportHeight - polypRadius);
//            }
//            game.shapeRendererLine.line(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight,
//                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + polypRadius,
//                    game.camera.viewportHeight - polypRadius);
        } else {
            game.shapeRendererFilled.triangle(0,
                    game.camera.viewportHeight / 2,
                    GameplayScreen.TARGET_RADIUS,
                    game.camera.viewportHeight / 2,
                    0,
                    (game.camera.viewportHeight / 2) + GameplayScreen.TARGET_RADIUS);
//            if(splitScreen) {
//                game.shapeRendererFilled.triangle(GameplayScreen.TARGET_RADIUS,
//                        game.camera.viewportHeight / 2,
//                        GameplayScreen.TARGET_RADIUS + polypRadius,
//                        (game.camera.viewportHeight / 2) + polypRadius,
//                        GameplayScreen.TARGET_RADIUS - polypRadius,
//                        (game.camera.viewportHeight / 2) + polypRadius);
//            }
//            game.shapeRendererLine.line(GameplayScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight / 2,
//                    GameplayScreen.TARGET_RADIUS - polypRadius,
//                    (game.camera.viewportHeight / 2) + polypRadius);
        }
        if(splitScreen) {
            game.shapeRendererFilled.triangle(game.camera.viewportWidth,
                    game.camera.viewportHeight / 2,
                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
                    game.camera.viewportHeight / 2,
                    game.camera.viewportWidth,
                    (game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS);
//            game.shapeRendererFilled.triangle(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight / 2,
//                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS - polypRadius,
//                    (game.camera.viewportHeight / 2) - polypRadius,
//                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + polypRadius,
//                    (game.camera.viewportHeight / 2) - polypRadius);
//            game.shapeRendererLine.line(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight / 2,
//                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + polypRadius,
//                    (game.camera.viewportHeight / 2) - polypRadius);
        }
    }

    public void drawScoreTrianglesTutorial(boolean splitScreen, boolean local, boolean showPlayerScore, boolean showOpponentScore, Color polypDividerColor) {
        float polypRadius = (TutorialScreen.TARGET_RADIUS / 2);

        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererLine.setColor(polypDividerColor);
        if((!splitScreen && showPlayerScore) || (splitScreen && showOpponentScore)) {
            game.shapeRendererFilled.triangle(game.camera.viewportWidth,
                    game.camera.viewportHeight,
                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
                    game.camera.viewportHeight,
                    game.camera.viewportWidth,
                    game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS);
//            game.shapeRendererFilled.triangle(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight,
//                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS - polypRadius,
//                    game.camera.viewportHeight - polypRadius,
//                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + polypRadius,
//                    game.camera.viewportHeight - polypRadius);
//            game.shapeRendererLine.line(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight,
//                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + polypRadius,
//                    game.camera.viewportHeight - polypRadius);
        }
        if(splitScreen && showPlayerScore) {
            game.shapeRendererFilled.triangle(game.camera.viewportWidth,
                    game.camera.viewportHeight / 2,
                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
                    game.camera.viewportHeight / 2,
                    game.camera.viewportWidth,
                    (game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS);
//            game.shapeRendererFilled.triangle(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight / 2,
//                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS - polypRadius,
//                    (game.camera.viewportHeight / 2) - polypRadius,
//                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + polypRadius,
//                    (game.camera.viewportHeight / 2) - polypRadius);
//            game.shapeRendererLine.line(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
//                    game.camera.viewportHeight / 2,
//                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + polypRadius,
//                    (game.camera.viewportHeight / 2) - polypRadius);
        }
    }

    public void drawSaturationIncrements(boolean localMobile, Color color) {
        game.shapeRendererFilled.setColor(color);
        for(int i = 1; i < GameplayScreen.MAX_SATURATION; i++) {
            //P1
            game.shapeRendererFilled.rectLine(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + (i * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    (game.camera.viewportHeight / 2) - (i * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + ((i + 0.5f) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    (game.camera.viewportHeight / 2) - ((i - 0.5f) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION) / 4);

            //P2
            if(localMobile) {
                game.shapeRendererFilled.rectLine(GameplayScreen.TARGET_RADIUS - (i * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        (game.camera.viewportHeight / 2) + (i * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        GameplayScreen.TARGET_RADIUS - ((i + 0.5f) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        (game.camera.viewportHeight / 2) + ((i - 0.5f) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION) / 4);
            } else {
                game.shapeRendererFilled.rectLine(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + (i * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportHeight - (i * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + ((i + 0.5f) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportHeight - ((i - 0.5f) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION) / 4);
            }
        }
    }

    public void drawSaturationIncrementsTutorial(boolean local, Color color) {
        game.shapeRendererFilled.setColor(color);
        for(int i = 1; i < TutorialScreen.MAX_SATURATION; i++) {
            //P1
            game.shapeRendererFilled.rectLine(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + (i * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    (game.camera.viewportHeight / 2) - (i * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + ((i + 0.5f) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    (game.camera.viewportHeight / 2) - ((i - 0.5f) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION) / 4);

            //P2
            if(local) {
                game.shapeRendererFilled.rectLine(TutorialScreen.TARGET_RADIUS - (i * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        (game.camera.viewportHeight / 2) + (i * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        TutorialScreen.TARGET_RADIUS - ((i + 0.5f) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        (game.camera.viewportHeight / 2) + ((i - 0.5f) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION) / 4);
            } else {
                game.shapeRendererFilled.rectLine(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + (i * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportHeight - (i * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + ((i + 0.5f) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportHeight - ((i - 0.5f) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION) / 4);
            }
        }
    }

    public void drawSaturationTriangles(String player, boolean localMobile, int saturation) {
        game.shapeRendererFilled.setColor(Color.BLACK);
        if(player.equals(GameplayScreen.P1)) {
            game.shapeRendererFilled.triangle(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
                    game.camera.viewportHeight / 2,
                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + (saturation * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    (game.camera.viewportHeight / 2) - (saturation * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + ((2 * saturation) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                    game.camera.viewportHeight / 2);
        } else {
            if(localMobile) {
                game.shapeRendererFilled.triangle(GameplayScreen.TARGET_RADIUS,
                        game.camera.viewportHeight / 2,
                        GameplayScreen.TARGET_RADIUS - (saturation * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        (game.camera.viewportHeight / 2) + (saturation * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        GameplayScreen.TARGET_RADIUS - ((2 * saturation) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportHeight / 2);
            } else {
                game.shapeRendererFilled.triangle(game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS,
                        game.camera.viewportHeight,
                        game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + (saturation * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportHeight - (saturation * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportWidth - GameplayScreen.TARGET_RADIUS + ((2 * saturation) * (GameplayScreen.TARGET_RADIUS / GameplayScreen.MAX_SATURATION)),
                        game.camera.viewportHeight);
            }
        }
    }

    public void drawSaturationTrianglesTutorial(String player, boolean local, int saturation) {
        game.shapeRendererFilled.setColor(Color.BLACK);
        if(player.equals(TutorialScreen.P1)) {
            game.shapeRendererFilled.triangle(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
                    game.camera.viewportHeight / 2,
                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + (saturation * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    (game.camera.viewportHeight / 2) - (saturation * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + ((2 * saturation) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                    game.camera.viewportHeight / 2);
        } else {
            if(local) {
                game.shapeRendererFilled.triangle(TutorialScreen.TARGET_RADIUS,
                        game.camera.viewportHeight / 2,
                        TutorialScreen.TARGET_RADIUS - (saturation * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        (game.camera.viewportHeight / 2) + (saturation * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        TutorialScreen.TARGET_RADIUS - ((2 * saturation) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportHeight / 2);
            } else {
                game.shapeRendererFilled.triangle(game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS,
                        game.camera.viewportHeight,
                        game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + (saturation * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportHeight - (saturation * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportWidth - TutorialScreen.TARGET_RADIUS + ((2 * saturation) * (TutorialScreen.TARGET_RADIUS / TutorialScreen.MAX_SATURATION)),
                        game.camera.viewportHeight);
            }
        }
    }

    public void drawPauseInput(boolean splitScreen, boolean localMobile, Squirgle game) {
        float inputRadius = splitScreen && game.widthGreater ? game.camera.viewportWidth / 40 : game.camera.viewportWidth / 20;
        float lineRadius = inputRadius / 2;

        if(!splitScreen) {
            drawPoint(game.camera.viewportWidth, (game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (((game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2), inputRadius, Color.WHITE);
            drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), (game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (((game.camera.viewportHeight - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2), inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK);
        } else {
            drawPoint(game.camera.viewportWidth, ((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2), inputRadius, Color.WHITE);
            drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), ((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - GameplayScreen.TARGET_RADIUS) - (GameplayScreen.INPUT_POINT_SPAWN.y + GameplayScreen.INPUT_RADIUS)) / 2), inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK);
        }

    }

    public void drawPauseInputTutorial(boolean splitScreen, boolean local, Squirgle game) {
        float inputRadius = splitScreen && game.widthGreater ? game.camera.viewportWidth / 40 : game.camera.viewportWidth / 20;

        if(!splitScreen) {
            drawPoint(game.camera.viewportWidth, (game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS) - (((game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS) - (TutorialScreen.INPUT_POINT_SPAWN.y + TutorialScreen.INPUT_RADIUS)) / 2), inputRadius, Color.WHITE);
            drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), (game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS) - (((game.camera.viewportHeight - TutorialScreen.TARGET_RADIUS) - (TutorialScreen.INPUT_POINT_SPAWN.y + TutorialScreen.INPUT_RADIUS)) / 2), inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK);
        } else {
            drawPoint(game.camera.viewportWidth, ((game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS) - (TutorialScreen.INPUT_POINT_SPAWN.y + TutorialScreen.INPUT_RADIUS)) / 2), inputRadius, Color.WHITE);
            drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), ((game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS) - (TutorialScreen.INPUT_POINT_SPAWN.y + TutorialScreen.INPUT_RADIUS)) / 2), inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK);
        }

    }

    public void drawPauseInputTrance(Squirgle game) {
        float inputRadius = game.camera.viewportWidth / 20;

        drawPoint(game.camera.viewportWidth, game.camera.viewportHeight / 2, inputRadius, Color.WHITE);
        drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), game.camera.viewportHeight / 2, inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK);
    }

    public void drawPauseInputTutorialTrance(Squirgle game) {
        float inputRadius = game.camera.viewportWidth / 20;

        drawPoint(game.camera.viewportWidth, (game.camera.viewportHeight / 2) + inputRadius, inputRadius, Color.WHITE);
        drawPauseSymbol(game.camera.viewportWidth - (inputRadius / 2), (game.camera.viewportHeight / 2) + inputRadius, inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK);

    }

    public void drawHelpInput(boolean splitScreen) {
        float inputRadius = splitScreen && game.widthGreater ? game.camera.viewportWidth / 40 : game.camera.viewportWidth / 20;

        if(!splitScreen) {
            drawPoint(game.camera.viewportWidth, (game.camera.viewportHeight / 2) - (2 * inputRadius), inputRadius, Color.WHITE);
            drawQuestionMark(game.camera.viewportWidth - (inputRadius / 2), (game.camera.viewportHeight / 2) - (2 * inputRadius), inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, Color.WHITE);
        } else {
            drawPoint(game.camera.viewportWidth, (TutorialScreen.INPUT_POINT_SPAWN_P1.y + TutorialScreen.INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS - (TutorialScreen.INPUT_POINT_SPAWN_P1.y + TutorialScreen.INPUT_RADIUS)) / 2) - inputRadius, inputRadius, Color.WHITE);
            drawQuestionMark(game.camera.viewportWidth - (inputRadius / 2), (TutorialScreen.INPUT_POINT_SPAWN_P1.y + TutorialScreen.INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TutorialScreen.TARGET_RADIUS - (TutorialScreen.INPUT_POINT_SPAWN_P1.y + TutorialScreen.INPUT_RADIUS)) / 2) - inputRadius, inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, Color.WHITE);
        }
    }

    public void drawHelpInputTrance() {
        float inputRadius = game.camera.viewportWidth / 20;

        drawPoint(game.camera.viewportWidth, (game.camera.viewportHeight / 2) - inputRadius, inputRadius, Color.WHITE);
        drawQuestionMark(game.camera.viewportWidth - (inputRadius / 2), (game.camera.viewportHeight / 2) - inputRadius, inputRadius / 2, (inputRadius / 2) / LINE_WIDTH_DIVISOR, Color.BLACK, Color.WHITE);
    }

    public void drawTouchDownPointsTutorial(List<Shape> touchDownShapeList) {
        for (int i = 0; i < touchDownShapeList.size(); i++) {
            Shape shape = touchDownShapeList.get(i);
            if (shape.getRadius() > TutorialScreen.INPUT_RADIUS) {
                touchDownShapeList.remove(shape);
            } else {
                drawShape(false, shape);
                shape.setRadius(shape.getRadius() + 1);
            }
        }
    }

    public void drawReplaySymbol(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        float outerRadius = radius;
        float innerBlackRadius = radius / 2;
        float innerWhiteRadius = radius / 3;
        float innerRadiusDifference = innerBlackRadius - innerWhiteRadius;
        float triangleRadius = innerBlackRadius / 3;

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y, outerRadius);

        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y, innerBlackRadius);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y, innerWhiteRadius);
        triangle(x + innerBlackRadius - ((23 * innerRadiusDifference) / 32),
                y + (2 * triangleRadius),
                x + innerBlackRadius - ((23 * innerRadiusDifference) / 32) - (2.5f * triangleRadius),
                y - triangleRadius,
                x + innerBlackRadius - ((23 * innerRadiusDifference) / 32) + (2.5f * triangleRadius),
                y - triangleRadius,
                triangleRadius / 20,
                secondaryColor);

        triangle(x + innerBlackRadius - ((23 * innerRadiusDifference) / 32),
                y + (triangleRadius / 2),
                x + innerBlackRadius - ((23 * innerRadiusDifference) / 32) - triangleRadius,
                y - triangleRadius,
                x + innerBlackRadius - ((23 * innerRadiusDifference) / 32) + triangleRadius,
                y - triangleRadius,
                triangleRadius / 20,
                primaryColor);

    }

    public void drawDash(float x, float y, float width, Color color) {
        game.shapeRendererFilled.setColor(color);

        game.shapeRendererFilled.rect(x - (width / 2), y - (width / 4), width, width / 2);
    }

    public void drawPlus(float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);

        //Draw circular edges
        game.shapeRendererFilled.circle(x - radius + (lineWidth / 2), y, lineWidth / 2);
        game.shapeRendererFilled.circle(x + radius - (lineWidth / 2), y, lineWidth / 2);
        game.shapeRendererFilled.circle(x, y - radius + (lineWidth / 2), lineWidth / 2);
        game.shapeRendererFilled.circle(x, y + radius - (lineWidth / 2), lineWidth / 2);

        //Draw rectangles
        game.shapeRendererFilled.rectLine(x - radius + (lineWidth / 2), y, x + radius - (lineWidth / 2), y, lineWidth);
        game.shapeRendererFilled.rectLine(x, y - radius + (lineWidth / 2), x, y + radius - (lineWidth / 2), lineWidth);
    }

    public void drawEquals(float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);

        //Draw circular edges
        game.shapeRendererFilled.circle(x - (radius / 2) + (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), lineWidth / 2);
        game.shapeRendererFilled.circle(x - (radius / 2) + (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), lineWidth / 2);
        game.shapeRendererFilled.circle(x + (radius / 2) - (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), lineWidth / 2);
        game.shapeRendererFilled.circle(x + (radius / 2) - (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), lineWidth / 2);

        //Draw rectangles
        game.shapeRendererFilled.rectLine(x - (radius / 2) + (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), x + (radius / 2) - (lineWidth / 2), y - (radius / 2) + (lineWidth / 2), lineWidth);
        game.shapeRendererFilled.rectLine(x - (radius / 2) + (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), x + (radius / 2) - (lineWidth / 2), y + (radius / 2) - (lineWidth / 2), lineWidth);
    }

    public void drawWrench(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rectLine(x - (radius / 2), y - (radius / 2), x + (radius / 2), y + (radius / 2), lineWidth);
        game.shapeRendererFilled.circle(x - (radius / 2), y - (radius / 2), lineWidth);
        game.shapeRendererFilled.circle(x + (radius / 2), y + (radius / 2), lineWidth);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.rect(x - (radius / 2) - (lineWidth / 2), y - (radius / 2) - lineWidth, lineWidth, lineWidth);
        game.shapeRendererFilled.rect(x + (radius / 2) - (lineWidth / 2), y + (radius / 2), lineWidth, lineWidth);
    }

    public void drawPlayButton(float x, float y, float radius, float lineWidth, Color color) {
        float xOffset = radius / 4;
//        game.shapeRendererFilled.rectLine(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth);
//        game.shapeRendererFilled.rectLine(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                x + radius - lineWidth,
//                y,
//                lineWidth);
//        game.shapeRendererFilled.rectLine(x + radius - lineWidth,
//                y,
//                x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth);
//        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
//        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
//        drawPoint(x + radius - lineWidth,
//                y,
//                lineWidth,
//                color);
        triangle(x + radius - xOffset,
                y,
                x - (MathUtils.cosDeg(SIXTY_DEGREES) * radius) - xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                x - (MathUtils.cosDeg(SIXTY_DEGREES) * radius) - xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
                radius / 20,
                color);
    }

    public void drawPauseSymbol(float x, float y, float radius, float lineWidth, Color color) {
//        drawLine(x - (radius / 4),
//                y,
//                radius / 2,
//                lineWidth,
//                color);
//        drawLine(x + (radius / 4),
//                y,
//                radius / 2,
//                lineWidth,
//                color);
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rect(x - (radius / 4) - (lineWidth / 2),
                y - (radius / 2),
                lineWidth,
                radius);
        game.shapeRendererFilled.rect(x + (radius / 4) - (lineWidth / 2),
                y - (radius / 2),
                lineWidth,
                radius);
        game.shapeRendererFilled.circle(x - (radius / 4),
                y - (radius / 2),
                lineWidth / 2);
        game.shapeRendererFilled.circle(x + (radius / 4),
                y - (radius / 2),
                lineWidth / 2);
        game.shapeRendererFilled.circle(x - (radius / 4),
                y + (radius / 2),
                lineWidth / 2);
        game.shapeRendererFilled.circle(x + (radius / 4),
                y + (radius / 2),
                lineWidth / 2);
    }

    public void drawSoundSymbol(float x, float y, float radius, float lineWidth, Color color) {
        drawPoint(x - (radius / 2) + lineWidth, y, radius, Color.BLACK);
        drawPoint(x - (radius / 2) + lineWidth - SOUND_WAVE_DISTANCE, y, radius, color);

        drawPoint(x - (radius / 2) + lineWidth, y, (3 * radius) / 4, Color.BLACK);
        drawPoint(x - (radius / 2) + lineWidth - SOUND_WAVE_DISTANCE, y, (3 * radius) / 4, color);

        drawPoint(x - (radius / 2) + lineWidth, y, radius / 2, Color.BLACK);
        drawPoint(x - (radius / 2) + lineWidth - SOUND_WAVE_DISTANCE, y, radius / 2, color);

        drawPoint(x - (radius / 2) + lineWidth, y, radius / 4, Color.BLACK);
    }

    public void drawBackButton(float x, float y, float radius, float lineWidth, Color color) {
        float xOffset = radius / 3;
//        game.shapeRendererFilled.rectLine(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                x - radius + lineWidth,
//                y,
//                lineWidth);
//        game.shapeRendererFilled.rectLine(x - radius + lineWidth,
//                y,
//                x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth);
//        game.shapeRendererFilled.rectLine(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth);
//        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
//        drawPoint(x - radius + lineWidth,
//                y,
//                lineWidth,
//                color);
//        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
        triangle(x - radius + xOffset,
                y,
                x - (radius / 2) + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                x - (radius / 2) + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                radius / 20,
                color);
        triangle(x - (radius / 2) + xOffset,
                y,
                x + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                x + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                radius / 20,
                color);
    }

    public void drawX(float x, float y, float radius, float lineWidth, Color color) {
        //TODO: Maybe fashion this like square?
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rectLine(x + (radius / 2.575f),
                y + (radius / 2.575f),
                (float) (x - (radius / 2.575)),
                (float) (y - (radius / 2.575)),
                lineWidth);
        game.shapeRendererFilled.rectLine((float) (x - (radius / 2.575f)),
                y + (radius / 2.575f),
                x + (radius / 2.575f),
                (float) (y - (radius / 2.575f)),
                lineWidth);

        drawPoint(x + (radius / 2.575f), y + (radius / 2.575f), lineWidth, color);
        drawPoint(x - (radius / 2.575f), y + (radius / 2.575f), lineWidth, color);
        drawPoint(x - (radius / 2.575f), y - (radius / 2.575f), lineWidth, color);
        drawPoint(x + (radius / 2.575f), y - (radius / 2.575f), lineWidth, color);
    }

    public void drawStopSymbol(float x, float y, float radius, Color color) {
        rect(x - (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * radius),
                y - (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * radius),
                2 * (MathUtils.sinDeg(FORTY_FIVE_DEGREES) * radius),
                2 * (MathUtils.cosDeg(FORTY_FIVE_DEGREES) * radius),
                color);
    }

    public void drawChevronLeft(float x, float y, float radius, float lineWidth, Color color) {
        float xOffset = radius / 3;
//        game.shapeRendererFilled.rectLine(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                x - radius + lineWidth,
//                y,
//                lineWidth);
//        game.shapeRendererFilled.rectLine(x - radius + lineWidth,
//                y,
//                x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth);
//        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
//        drawPoint(x - radius + lineWidth,
//                y,
//                lineWidth,
//                color);
//        drawPoint(x + (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);

        triangle(x - radius + xOffset,
                y,
                x - (radius / 2) + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                x - (radius / 2) + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                radius / 20,
                color);
        triangle(x - (radius / 2) + xOffset,
                y,
                x + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                x + (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) + xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                radius / 20,
                color);
    }

    public void drawChevronRight(float x, float y, float radius, float lineWidth, Color color) {
        float xOffset = radius / 3;
//        game.shapeRendererFilled.rectLine(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                x + radius - lineWidth,
//                y,
//                lineWidth);
//        game.shapeRendererFilled.rectLine(x + radius - lineWidth,
//                y,
//                x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth);
//        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y + (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
//        drawPoint(x - (MathUtils.sinDeg(SIXTY_DEGREES) * radius),
//                y - (MathUtils.cosDeg(SIXTY_DEGREES) * radius),
//                lineWidth,
//                color);
//        drawPoint(x + radius - lineWidth,
//                y,
//                lineWidth,
//                color);

        triangle(x + radius - xOffset,
                y,
                x + (radius / 2) - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) - xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                x + (radius / 2) - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) - xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                radius / 20,
                color);
        triangle(x + (radius / 2) - xOffset,
                y,
                x - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) - xOffset,
                y - (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                x - (MathUtils.cosDeg(SIXTY_DEGREES) * (radius / 2)) - xOffset,
                y + (MathUtils.sinDeg(SIXTY_DEGREES) * (radius / 2)),
                radius / 20,
                color);
    }

    public void drawQuestionMark(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y + (radius / 4), radius / 4);
        game.shapeRendererFilled.rectLine(x, y + (radius / 4), x, y - (radius / 4), lineWidth);
        game.shapeRendererFilled.rectLine(x, y - ((3 * radius) / 8), x, y - (radius / 2), lineWidth);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y + (radius / 4), lineWidth);
        game.shapeRendererFilled.rectLine(x - ((5 * lineWidth) / 4), y + (radius / 4), x - ((5 * lineWidth) / 4), y, ((3 * lineWidth) / 2));
    }

    public void drawQuarterNote(float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.circle(x, y - ((3 * radius) / 4), radius / 4);
        game.shapeRendererFilled.circle(x + (radius / 4) - (lineWidth / 2), y + radius - (lineWidth / 2), lineWidth / 2);
        game.shapeRendererFilled.rectLine(x + (radius / 4) - (lineWidth / 2), y + radius - (lineWidth / 2), x + (radius / 4) - (lineWidth / 2), y - ((3 * radius) / 4), lineWidth);
    }

    public void drawFace(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor) {
        drawPoint(x, y, radius, primaryColor);

        drawPoint(x - (radius / 2), y + (radius / 2), lineWidth, secondaryColor);
        drawPoint(x + (radius / 2), y +(radius / 2), lineWidth, secondaryColor);
    }

    public void drawColorSymbol(float x, float y, float radius, float lineWidth, Color color) {
        drawPoint(x, y, radius, color);

        drawPoint(x - (radius / 2), y + ((3 * radius) / 4), radius / 4, Color.WHITE);

        drawPoint(x, y - radius + lineWidth, lineWidth, Color.FIREBRICK);
        drawPoint(x + radius - (radius / 2.575f), y - radius + (radius / 2.575f), lineWidth, Color.CYAN);
        drawPoint(x + radius - lineWidth, y, lineWidth, Color.MAGENTA);
    }

    public void drawColorWheel(float x, float y, float radius, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.circle(x, y, radius);

        game.shapeRendererFilled.setColor(new Color(230/ColorUtils.MAX_RGB_VALUE, 159/ColorUtils.MAX_RGB_VALUE, 0, 1));
        game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(new Color(213/ColorUtils.MAX_RGB_VALUE, 94/ColorUtils.MAX_RGB_VALUE, 0, 1));
        game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, 0, SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(new Color(204/ColorUtils.MAX_RGB_VALUE, 121/ColorUtils.MAX_RGB_VALUE, 167/ColorUtils.MAX_RGB_VALUE, 1));
        game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(new Color(0, 114/ColorUtils.MAX_RGB_VALUE, 178/ColorUtils.MAX_RGB_VALUE, 1));
        game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -(2 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(new Color(86/ColorUtils.MAX_RGB_VALUE, 180/ColorUtils.MAX_RGB_VALUE, 233/ColorUtils.MAX_RGB_VALUE, 1));
        game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -(3 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(new Color(0, 158/ColorUtils.MAX_RGB_VALUE, 115/ColorUtils.MAX_RGB_VALUE, 1));
        game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -(4 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
    }

    public void drawColor(float x, float y, float radius, int color) {
        game.shapeRendererFilled.setColor(Color.BLACK);
        game.shapeRendererFilled.circle(x, y, radius);

        switch(color) {
            case ColorUtils.ORANGE : {
                game.shapeRendererFilled.arc(x, y, radius, 0, -300, NUM_ARC_SEGMENTS);

                game.shapeRendererFilled.setColor(new Color(230/ColorUtils.MAX_RGB_VALUE, 159/ColorUtils.MAX_RGB_VALUE, 0, 1));
                game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.SKY_BLUE : {
                game.shapeRendererFilled.arc(x, y, radius, -60, -300, NUM_ARC_SEGMENTS);

                game.shapeRendererFilled.setColor(new Color(213/ColorUtils.MAX_RGB_VALUE, 94/ColorUtils.MAX_RGB_VALUE, 0, 1));
                game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, 0, SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.BLUISH_GREEN : {
                game.shapeRendererFilled.arc(x, y, radius, -120, -300, NUM_ARC_SEGMENTS);

                game.shapeRendererFilled.setColor(new Color(204/ColorUtils.MAX_RGB_VALUE, 121/ColorUtils.MAX_RGB_VALUE, 167/ColorUtils.MAX_RGB_VALUE, 1));
                game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -SIXTY_DEGREES, SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.BLUE : {
                game.shapeRendererFilled.arc(x, y, radius, -180, -300, NUM_ARC_SEGMENTS);

                game.shapeRendererFilled.setColor(new Color(0, 114/ColorUtils.MAX_RGB_VALUE, 178/ColorUtils.MAX_RGB_VALUE, 1));
                game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -(2 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.VERMILLION : {
                game.shapeRendererFilled.arc(x, y, radius, -240, -300, NUM_ARC_SEGMENTS);

                game.shapeRendererFilled.setColor(new Color(86/ColorUtils.MAX_RGB_VALUE, 180/ColorUtils.MAX_RGB_VALUE, 233/ColorUtils.MAX_RGB_VALUE, 1));
                game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -(3 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
            case ColorUtils.REDDISH_PURPLE : {
                game.shapeRendererFilled.arc(x, y, radius, -300, -300, NUM_ARC_SEGMENTS);

                game.shapeRendererFilled.setColor(new Color(0, 158/ColorUtils.MAX_RGB_VALUE, 115/ColorUtils.MAX_RGB_VALUE, 1));
                game.shapeRendererFilled.arc(x, y, (9 * radius) / 10, -(4 * SIXTY_DEGREES), SIXTY_DEGREES, NUM_ARC_SEGMENTS);
                break;
            }
        }
    }

    public void drawWiFiSymbol(float x, float y, float radius, float lineWidth, Color primaryColor, Color secondaryColor) {
        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), radius, primaryColor);
        drawPoint(x + (radius / 2.575f) - lineWidth + 5, y - (radius / 2.575f) - lineWidth, radius, secondaryColor);

        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), (3 * radius) / 4, primaryColor);
        drawPoint(x + (radius / 2.575f) - lineWidth + 5, y - (radius / 2.575f) - lineWidth, (3 * radius) / 4, secondaryColor);

        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), radius / 2, primaryColor);
        drawPoint(x + (radius / 2.575f) - lineWidth + 5, y - (radius / 2.575f) - lineWidth, radius / 2, secondaryColor);

        drawPoint(x + (radius / 2.575f) - lineWidth, y - (radius / 2.575f), radius / 4, primaryColor);
    }

    public void drawModulo(float x, float y, float radius, float lineWidth, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rectLine(x - (radius / 2), y - (radius / 2), x + (radius / 2), y + (radius / 2), lineWidth);
        game.shapeRendererFilled.circle(x - (radius / 2), y - (radius / 2), lineWidth);
        game.shapeRendererFilled.circle(x + (radius / 2), y + (radius / 2), lineWidth);

        drawPoint(x - (radius / 3),
                y + (radius / 3),
                lineWidth,
                color);
        drawPoint(x + (radius / 3),
                y - (radius / 3),
                lineWidth,
                color);
    }

    public void drawSigma(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.triangle(x - (radius / 2),
                y + ((3 * radius) / 4),
                x - (radius / 2),
                y - ((3 * radius) / 4),
                x + (radius / 4),
                y);
        game.shapeRendererFilled.rect(x - (radius / 2),
                y + (radius / 2),
                radius,
                radius / 4);
        game.shapeRendererFilled.rect(x - (radius / 2),
                y - ((3 * radius) / 4),
                radius,
                radius / 4);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.triangle(x - (radius / 2),
                y + (radius / 2),
                x - (radius / 2),
                y - (radius / 2),
                x,
                y);
    }

    public void drawClock(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y, radius);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y, (9 * radius) / 10);

        //TODO: Add incremental notches along clock
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rectLine(x,
                y,
                x - (radius / 2),
                y + (radius / 2),
                radius / LINE_WIDTH_DIVISOR);
        game.shapeRendererFilled.rectLine(x,
                y,
                x + ((3 * radius) / 5),
                y + ((3 * radius) / 5),
                (radius / LINE_WIDTH_DIVISOR) / 2);
        game.shapeRendererFilled.circle(x, y, radius / LINE_WIDTH_DIVISOR);
    }

    //TODO: Clean up this method
    public void drawTranceSymbol(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y + radius, radius / 20);
        game.shapeRendererFilled.rectLine(x, y + radius, x, y - (radius / 2), radius / 10);
        game.shapeRendererFilled.rectLine(x, y + radius, x - radius + (radius / 5), y - (radius / 5), radius / 10);
        game.shapeRendererFilled.rectLine(x, y + radius, x + radius - (radius / 5), y - (radius / 5), radius / 10);

        game.shapeRendererFilled.circle(x, y - (radius / 2), radius / 5);
        game.shapeRendererFilled.circle(x - radius + (radius / 5), y - (radius / 5), radius / 5);
        game.shapeRendererFilled.circle(x + radius - (radius / 5), y - (radius / 5), radius / 5);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y - (radius / 2), radius / 20);
        game.shapeRendererFilled.circle(x - radius + (radius / 5), y - (radius / 5), radius / 20);
        game.shapeRendererFilled.circle(x + radius - (radius / 5), y - (radius / 5), radius / 20);

        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y - (radius / 2), radius / 40);
        game.shapeRendererFilled.circle(x - radius + (radius / 5), y - (radius / 5), radius / 40);
        game.shapeRendererFilled.circle(x + radius - (radius / 5), y - (radius / 5), radius / 40);

        game.shapeRendererFilled.arc(x - (radius / 2.5f), y - (radius / 3), radius / 4, -157.5f, 90, NUM_ARC_SEGMENTS);
        game.shapeRendererFilled.arc(x + (radius / 2.5f), y - (radius / 3), radius / 4, -112.5f, 90, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.arc(x - (radius / 2.5f) + (radius / TRANCE_ARC_RADIUS_DIVISOR), y - (radius / 3) + (radius / TRANCE_ARC_RADIUS_DIVISOR), radius / 4, -157.5f, 90, NUM_ARC_SEGMENTS);
        game.shapeRendererFilled.arc(x + (radius / 2.5f) - (radius / TRANCE_ARC_RADIUS_DIVISOR), y - (radius / 3) + (radius / TRANCE_ARC_RADIUS_DIVISOR), radius / 4, -112.5f, 90, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.arc(x - (radius / 2.5f) + (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 3) + (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 4, -157.5f, 90, NUM_ARC_SEGMENTS);
        game.shapeRendererFilled.arc(x + (radius / 2.5f) - (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 3) + (2 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 4, -112.5f, 90, NUM_ARC_SEGMENTS);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.arc(x - (radius / 2.5f) + (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 3) + (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 4, -157.5f, 90, NUM_ARC_SEGMENTS);
        game.shapeRendererFilled.arc(x + (radius / 2.5f) - (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), y - (radius / 3) + (3 * (radius / TRANCE_ARC_RADIUS_DIVISOR)), radius / 4, -112.5f, 90, NUM_ARC_SEGMENTS);
    }

    public void drawDifficultySymbol(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y - (radius / 2), radius);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y - (radius / 2), (9 * radius) / 10);

        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y - (radius / 2), radius / 5);
        game.shapeRendererFilled.triangle(x - (radius / 10), y - (radius / 2) - (radius / 10), x + (radius / 10), y - (radius / 2) + (radius / 10), x - (radius / 2), y - (radius / 2) + (radius / 2));

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.rect(x - radius, y - (radius / 2) - radius, radius * 2, radius);
    }

    public void drawTutorialSymbol(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.triangle(x - radius, y + radius - (radius / 3) - (radius / 4), x, y + radius - (radius / 4), x + radius, y + radius - (radius / 3) - (radius / 4));
        game.shapeRendererFilled.triangle(x - radius, y + radius - (radius / 3) - (radius / 4), x, y + radius - ((2 * radius) / 3) - (radius / 4), x + radius, y + radius - (radius / 3) - (radius / 4));
        game.shapeRendererFilled.rect(x - (radius / 2), y - (radius / 4), radius, radius / 2);
        game.shapeRendererFilled.rectLine(x + ((3 * radius) / 4), y + radius - (radius / 3) - (radius / 4), x + ((3 * radius) / 4), y - (radius / 2) - (radius / 4), radius / 30);
        game.shapeRendererFilled.circle(x + ((3 * radius) / 4), y - (radius / 4) - (radius / 4), radius / 15);
        game.shapeRendererFilled.triangle(x + ((3 * radius) / 4), y - (radius / 4) - (radius / 4), x + ((3 * radius) / 4) - (radius / 7.5f), y - (radius / 2) - (radius / 4), x + ((3 * radius) / 4) + (radius / 7.5f), y - (radius / 2) - (radius / 4));
        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.rectLine(x - radius, y + radius - (radius / 3) - (radius / 4), x, y + (radius / 3) - (radius / 4), radius / 30);
        game.shapeRendererFilled.rectLine(x, y + (radius / 3) - (radius / 4), x + radius, y + radius - (radius / 3) - (radius / 4), radius / 30);
        game.shapeRendererFilled.circle(x, y + (radius / 3) - (radius / 4), radius / 60);
    }

    public void drawCreditsSymbol(float x, float y, float radius, Color color) {
        float twoTimesRadius = 2 * radius;
        float firstHeight = radius / 20;
        float secondHeight = radius / 15;
        float thirdHeight = radius / 10;
        float fourthHeight = radius / 5;
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rect(x - (radius / 5), y + radius - (twoTimesRadius / 5) - (firstHeight / 2), (2 * radius) / 5, firstHeight);
        game.shapeRendererFilled.rect(x - ((2 * radius) / 5), y + radius - ((2 * twoTimesRadius) / 5) - (secondHeight / 2), (4 * radius) / 5, firstHeight);
        game.shapeRendererFilled.rect(x - ((3 * radius) / 5), y - radius + ((2 * twoTimesRadius) / 5) - (thirdHeight / 2), (6 * radius) / 5, firstHeight);
        game.shapeRendererFilled.rect(x - ((4 * radius) / 5), y - radius + (twoTimesRadius / 5) - (fourthHeight / 2), (8 * radius) / 5, firstHeight);
    }

    public void drawWipeDataSymbol(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(Color.FIREBRICK);
        game.shapeRendererFilled.circle(x, y, radius);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y, (9 * radius) / 10);

        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rect(x - (radius / 2),
                y - (radius / 2),
                radius,
                radius);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.rect(x - (radius / 4),
                y + (radius / 4),
                radius / 2,
                radius / 2);

        rect(x + (radius / 20),
                y + (radius / 4) + (radius / 20),
                radius / 12,
                radius / 6,
                primaryColor);

        game.shapeRendererFilled.setColor(Color.FIREBRICK);
        game.shapeRendererFilled.rectLine(x - ((2 * radius) / 3),
                y - ((2 * radius) / 3),
                x + ((2 * radius) / 3),
                y + ((2 * radius) / 3),
                radius / 20);
    }

    public void drawSkull(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, (y + radius / 8), (3 * radius) / 4);
        game.shapeRendererFilled.rect(x - (radius / 3), y - radius, (2 * radius) / 3, radius / 2);

        game.shapeRendererFilled.setColor(secondaryColor);
        drawX(x - (radius / 3), y + (radius / 6), radius / 4, (radius / 4) / LINE_WIDTH_DIVISOR, secondaryColor);
        drawX(x + (radius / 3), y + (radius / 6), radius / 4, (radius / 4) / LINE_WIDTH_DIVISOR, secondaryColor);
        game.shapeRendererFilled.triangle(x - (radius / 12), y - (radius / 12) - (radius / 6), x + (radius / 12), y - (radius / 12) - (radius / 6), x, y + (radius / 12) - (radius / 6));
        rect(x - (radius / 5), y - radius, (2 * radius) / 15, radius / 3, secondaryColor);
        rect(x + (radius / 15), y - radius, (2 * radius) / 15, radius / 3, secondaryColor);
    }

    public void drawDPad(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.circle(x, y, radius);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.circle(x, y, (7 * radius) / 8);

        rect(x - ((3 * radius) / 4),
                y - (((3 * (radius * 2)) / 4) / 8),
                ((3 * (radius * 2)) / 4),
                (((3 * (radius * 2)) / 4) / 4),
                primaryColor);
        rect(x - (((3 * (radius * 2)) / 4) / 8),
                y - ((3 * radius) / 4),
                (((3 * (radius * 2)) / 4) / 4),
                ((3 * (radius * 2)) / 4),
                primaryColor);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.triangle(x,
                y + ((3 * radius) / 4) - (radius / 16),
                x - (((3 * (radius * 2)) / 4) / 16),
                y + (radius / 2),
                x + (((3 * (radius * 2)) / 4) / 16),
                y + (radius / 2));
        game.shapeRendererFilled.triangle(x - ((3 * radius) / 4) + (radius / 16),
                y,
                x - (radius / 2),
                y - (((3 * (radius * 2)) / 4) / 16),
                x - (radius / 2),
                y + (((3 * (radius * 2)) / 4) / 16));
        game.shapeRendererFilled.triangle(x,
                y - ((3 * radius) / 4) + (radius / 16),
                x - (((3 * (radius * 2)) / 4) / 16),
                y - (radius / 2),
                x + (((3 * (radius * 2)) / 4) / 16),
                y - (radius / 2));
        game.shapeRendererFilled.triangle(x + ((3 * radius) / 4) - (radius / 16),
                y,
                x + (radius / 2),
                y - (((3 * (radius * 2)) / 4) / 16),
                x + (radius / 2),
                y + (((3 * (radius * 2)) / 4) / 16));
    }

    public void drawPoundSymbol(float x, float y, float radius, Color color) {
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rectLine(x - (radius / 2),
                y + ((radius / 2) / 3),
                x + (radius / 2),
                y + ((radius / 2) / 3),
                radius / 20);
        game.shapeRendererFilled.rectLine(x - (radius / 2),
                y - ((radius / 2) / 3),
                x + (radius / 2),
                y - ((radius / 2) / 3),
                radius / 20);
        game.shapeRendererFilled.rectLine(x - ((radius / 2) / 3),
                y + (radius / 2),
                x - ((radius / 2) / 3),
                y - (radius / 2),
                radius / 20);
        game.shapeRendererFilled.rectLine(x + ((radius / 2) / 3),
                y + (radius / 2),
                x + ((radius / 2) / 3),
                y - (radius / 2),
                radius / 20);
    }

    public void drawMouse(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rect(x - (radius / 2),
                y - (radius / 2),
                radius,
                radius);
        game.shapeRendererFilled.circle(x,
                y + (radius / 2),
                radius / 2);
        game.shapeRendererFilled.circle(x,
                y - (radius / 2),
                radius / 2);

        game.shapeRendererFilled.setColor(secondaryColor);
        game.shapeRendererFilled.rect(x - ((9 * (radius / 2)) / 10),
                y - ((9 * (radius / 2)) / 10),
                ((9 * radius) / 10),
                ((9 * radius) / 10));
        game.shapeRendererFilled.circle(x,
                y + ((9 * (radius / 2)) / 10),
                ((9 * (radius / 2)) / 10));
        game.shapeRendererFilled.circle(x,
                y - ((9 * (radius / 2)) / 10),
                ((9 * (radius / 2)) / 10));

        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rect(x - (radius / 20),
                y + (radius / 4),
                radius / 10,
                radius / 4);
        game.shapeRendererFilled.circle(x,
                y + (radius / 2),
                radius / 20);
        game.shapeRendererFilled.circle(x,
                y + (radius / 4),
                radius / 20);

        game.shapeRendererFilled.rectLine(x - (radius / 2),
                y + ((3 * radius) / 8),
                x + (radius / 2),
                y + ((3 * radius) / 8),
                radius / 40);

        game.shapeRendererFilled.rectLine(x,
                y + ((9 * (radius / 2)) / 5),
                x,
                y + ((3 * radius) / 8),
                radius / 40);
    }

    public void drawNumPad(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rect(x - radius,
                y - radius,
                radius * 2,
                radius * 2);

        drawPoundSymbol(x, y, radius * 2, secondaryColor);

        float startingX = x - ((2 * radius) / 3);
        float startingY = y + ((2 * radius) / 3);
        float xAdditive = x - startingX;
        float yAdditive = startingY - y;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                drawPoundSymbol(startingX + (i * xAdditive),
                        startingY - (j * yAdditive),
                        xAdditive / 2,
                        secondaryColor);
            }
        }
    }

    public void drawNumbers(float x, float y, float radius, Color primaryColor, Color secondaryColor) {
        game.shapeRendererFilled.setColor(primaryColor);
        game.shapeRendererFilled.rect(x - radius,
                y - (radius / 3),
                radius * 2,
                (2 * radius) / 3);

        drawPoundSymbol(x, y, radius * 2, secondaryColor);

        float xAdditive = (radius * 2) / 3;
        float startingX = x - radius + (xAdditive / 2);
        for(int i = 0; i < 3; i++) {
            drawPoundSymbol(startingX + (i * xAdditive),
                    y,
                    xAdditive / 2,
                    secondaryColor);
        }
    }

    public void rect(float x, float y, float width, float height, Color color) {
        float cornerRadius = width > height ? height / 10 : width / 10;

        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rect(x, y + cornerRadius, width, height - (2 * cornerRadius));
        game.shapeRendererFilled.rect(x + cornerRadius, y, width - (2 * cornerRadius), height);
        game.shapeRendererFilled.circle(x + cornerRadius, y + height - cornerRadius, cornerRadius);
        game.shapeRendererFilled.circle(x + cornerRadius, y + cornerRadius, cornerRadius);
        game.shapeRendererFilled.circle(x + width - cornerRadius, y + cornerRadius, cornerRadius);
        game.shapeRendererFilled.circle(x + width - cornerRadius, y + height - cornerRadius, cornerRadius);
    }

    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3, float cornerRadius, Color color) {
        //TODO: Be careful with this method, as the way its written could (possibly?) result in overstepping radius bounds.
        game.shapeRendererFilled.setColor(color);
        game.shapeRendererFilled.rectLine(x1, y1, x2, y2, 2 * cornerRadius);
        game.shapeRendererFilled.rectLine(x2, y2, x3, y3, 2 * cornerRadius);
        game.shapeRendererFilled.rectLine(x3, y3, x1, y1, 2 * cornerRadius);
        game.shapeRendererFilled.circle(x1, y1, cornerRadius);
        game.shapeRendererFilled.circle(x2, y2, cornerRadius);
        game.shapeRendererFilled.circle(x3, y3, cornerRadius);
        game.shapeRendererFilled.triangle(x1, y1, x2, y2, x3, y3);
    }

    public void drawCursor() {
        float outerRadius = game.widthOrHeightSmaller / 30;
        float innerRadius = outerRadius / 1.3f;
        float innerOffset = innerRadius / outerRadius;

        triangle(Gdx.input.getX(),
                game.camera.viewportHeight - Gdx.input.getY(),
                Gdx.input.getX() + (outerRadius / 8),
                game.camera.viewportHeight - (Gdx.input.getY() + ((3 * outerRadius) / 4)),
                Gdx.input.getX() + ((2 * outerRadius) / 3),
                game.camera.viewportHeight - (Gdx.input.getY() + ((5 * outerRadius) / 12)),
                outerRadius / 20,
                Color.WHITE);
        triangle(Gdx.input.getX() + innerOffset,
                game.camera.viewportHeight - (Gdx.input.getY() + innerOffset),
                Gdx.input.getX() + (innerRadius / 8) + innerOffset,
                game.camera.viewportHeight - (Gdx.input.getY() + ((3 * innerRadius) / 4) + innerOffset),
                Gdx.input.getX() + ((2 * innerRadius) / 3) + innerOffset,
                game.camera.viewportHeight - (Gdx.input.getY() + ((5 * innerRadius) / 12) + innerOffset),
                innerRadius / 20,
                Color.BLACK);
    }
}
