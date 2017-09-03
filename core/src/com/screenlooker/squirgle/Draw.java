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
    public static final int INPUT_RADIUS = 50;
    public static final int TARGET_RADIUS = 150;
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

    public float getRadiusOffset() { return radiusOffset; }

    public void setRadiusOffset(float radiusOffset) { this.radiusOffset = radiusOffset; }

    public float getColorSpeed() { return colorSpeed; }

    public void setColorSpeed(float colorSpeed) { this.colorSpeed = colorSpeed; }

    public float getColorListSpeed() { return colorListSpeed; }

    public void setColorListSpeed(float colorListSpeed) { this.colorListSpeed = colorListSpeed; }

    public Vector2 getTargetSpawn() { return targetSpawn; }

    public void setTargetSpawn(Vector2 targetSpawn) { this.targetSpawn = targetSpawn; }

    public void drawPrompt(Shape promptShape, List<Shape> priorShapeList, ShapeRenderer shapeRenderer) {
        float xOffset = 0;
        float radiusOffset = 1;
        if(promptShape.getShape() == Shape.CIRCLE) {
            drawPoint(promptShape.getCoordinates().x, promptShape.getCoordinates().y, promptShape.getRadius(), promptShape.getColor(), shapeRenderer);
        } else if(promptShape.getShape() == Shape.POINT) {
            if(!priorShapeList.isEmpty()) {
                xOffset = promptShape.getRadius() / 2;
                radiusOffset = 2;
            }
            drawPoint(promptShape.getCoordinates().x - xOffset, promptShape.getCoordinates().y, promptShape.getRadius() / radiusOffset, promptShape.getColor(), shapeRenderer);
        } else if(promptShape.getShape() == Shape.LINE) {
            if(!priorShapeList.isEmpty()) {
                xOffset = promptShape.getRadius();
            }
            drawLine(promptShape.getCoordinates().x - xOffset, promptShape.getCoordinates().y, promptShape.getRadius(), promptShape.getLineWidth(), promptShape.getColor(), shapeRenderer);
        } else if(promptShape.getShape() == Shape.TRIANGLE) {
            drawTriangle(promptShape.getCoordinates().x, promptShape.getCoordinates().y, promptShape.getRadius(), promptShape.getLineWidth(), promptShape.getColor(), shapeRenderer);
        } else if(promptShape.getShape() == Shape.SQUARE) {
            drawSquare(promptShape.getCoordinates().x, promptShape.getCoordinates().y, promptShape.getRadius(), promptShape.getLineWidth(), promptShape.getColor(), shapeRenderer);
        }
    }

    public void drawShapes(List<Shape> priorShapeList, Shape promptShape, ShapeRenderer shapeRenderer) {
        if(!priorShapeList.isEmpty()) {
            for(int i = priorShapeList.size() - 1; i >= 0; i--) {
                Shape shape = priorShapeList.get(i);
                Shape priorShape;

                //Determine whether to compare current shape with prompt or next shape in list
                if(i == priorShapeList.size() - 1) {
                    //Shape is at end of list, so compare with prompt
                    priorShape = promptShape;
                } else {
                    //Shape is not at end of list, so compare with next shape in list
                    priorShape = priorShapeList.get(i + 1);
                }

                //Ensure that current element of list initially has same radius and coordinates as prior shape
                shape.setRadius(priorShape.getRadius());
                shape.setCoordinates(priorShape.getCoordinates());

                //Lines and points must be shrunken and re-coordinated when encircled, except if the line is the first member of the list
                if(shape.getShape() == Shape.POINT) {
                    shape.setRadius(priorShape.getRadius() / 2);
                    if(i != 0) {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                } else if(shape.getShape() == Shape.LINE) {
                    if(i != 0) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x - shape.getRadius(), shape.getCoordinates().y));
                    }
                }

                //Determine radius/coordinates of current shape based on prior one
                //Also, current shape is guaranteed to be a circle if any of these conditions hold
                if(priorShape.getShape() == Shape.POINT) {
                    if(priorShape == promptShape) {
                        shape.setRadius(priorShape.getRadius() / 2);
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + shape.getRadius(), priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + (shape.getRadius() * 2), priorShape.getCoordinates().y));
                    }
                } else if(priorShape.getShape() == Shape.LINE) {
                    if(priorShape == promptShape) {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + (priorShape.getLineWidth() / 2), priorShape.getCoordinates().y));
                    } else {
                        shape.setCoordinates(new Vector2(priorShape.getCoordinates().x + priorShape.getRadius() + (priorShape.getLineWidth() / 2), priorShape.getCoordinates().y));
                    }
                } else if(priorShape.getShape() == Shape.TRIANGLE) {
                    shape.setRadius((priorShape.getRadius() / 2) - priorShape.getLineWidth());
                } else if(priorShape.getShape() == Shape.SQUARE) {
                    shape.setRadius((float) (priorShape.getRadius() / Math.sqrt(2)) - (priorShape.getLineWidth() * 2));
                }

                drawShape(shape, shapeRenderer);
            }
        }
    }

    public void drawShape(Shape shape, ShapeRenderer shapeRenderer) {
        if(shape.getShape() == Shape.POINT) {
            //TODO: Move these null checks into each shape's respective draw method.
            if(shape.getFillColor() != null) {
                drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getFillColor(), shapeRenderer);
            }
            drawPoint(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getColor(), shapeRenderer);
        } else if(shape.getShape() == Shape.LINE) {
            if(shape.getFillColor() != null) {
                drawLine(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getLineWidth(), shape.getFillColor(), shapeRenderer);
            }
            drawLine(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getLineWidth(), shape.getColor(), shapeRenderer);
        } else if(shape.getShape() == Shape.TRIANGLE) {
            if(shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.triangle(shape.getCoordinates().x,
                                       shape.getCoordinates().y + shape.getRadius() - shape.getLineWidth(),
                                       shape.getCoordinates().x - (MathUtils.sinDeg(60) * shape.getRadius()),
                                       shape.getCoordinates().y - (MathUtils.cosDeg(60) * shape.getRadius()),
                                       shape.getCoordinates().x + (MathUtils.sinDeg(60) * shape.getRadius()),
                                       shape.getCoordinates().y - (MathUtils.cosDeg(60) * shape.getRadius()));
            }
            drawTriangle(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getLineWidth(), shape.getColor(), shapeRenderer);
        } else if(shape.getShape() == Shape.SQUARE) {
            if(shape.getFillColor() != null) {
                shapeRenderer.setColor(shape.getFillColor());
                shapeRenderer.rectLine(shape.getCoordinates().x, shape.getCoordinates().y + (shape.getRadius() / radiusOffset) - (shape.getLineWidth() / 2),
                                       shape.getCoordinates().x, shape.getCoordinates().y - (shape.getRadius() / radiusOffset) + (shape.getLineWidth() / 2),
                                       shape.getRadius() + radiusOffset); //TODO: Figure out why radius offset is required.
            }
            drawSquare(shape.getCoordinates().x, shape.getCoordinates().y, shape.getRadius(), shape.getLineWidth(), shape.getColor(), shapeRenderer);
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
        drawPoint(x - (MathUtils.sinDeg(60) * radius), y - (MathUtils.cosDeg(60) * radius), lineWidth, color, shapeRenderer);
        drawPoint(x + (MathUtils.sinDeg(60) * radius), y - (MathUtils.cosDeg(60) * radius), lineWidth, color, shapeRenderer);
    }

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
        drawPoint(x - (radius / radiusOffset) + (lineWidth / 2), y - (radius / radiusOffset) + (lineWidth / 2), lineWidth, color, shapeRenderer);
        drawPoint(x - (radius / radiusOffset) + (lineWidth / 2), y + (radius / radiusOffset) - (lineWidth / 2), lineWidth, color, shapeRenderer);
        drawPoint(x + (radius / radiusOffset) - (lineWidth / 2), y + (radius / radiusOffset) - (lineWidth / 2), lineWidth, color, shapeRenderer);
        drawPoint(x + (radius / radiusOffset) - (lineWidth / 2), y - (radius / radiusOffset) + (lineWidth / 2), lineWidth, color, shapeRenderer);
    }

    public void drawInputButtons(Vector2 inputPointSpawn, Vector2 inputLineSpawn, Vector2 inputTriangleSpawn, Vector2 inputSquareSpawn, ShapeRenderer shapeRenderer) {
        //Point
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputPointSpawn.x, inputPointSpawn.y, INPUT_RADIUS);
        drawPoint(inputPointSpawn.x, inputPointSpawn.y, INPUT_RADIUS / 2, Color.BLACK, shapeRenderer);

        //Line
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputLineSpawn.x, inputLineSpawn.y, INPUT_RADIUS);
        drawLine(inputLineSpawn.x, inputLineSpawn.y, INPUT_RADIUS, INPUT_RADIUS / 8, Color.BLACK, shapeRenderer);

        //Triangle
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputTriangleSpawn.x, inputTriangleSpawn.y, INPUT_RADIUS);
        drawTriangle(inputTriangleSpawn.x, inputTriangleSpawn.y, INPUT_RADIUS,INPUT_RADIUS / 8, Color.BLACK, shapeRenderer);

        //Square
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputSquareSpawn.x, inputSquareSpawn.y, INPUT_RADIUS);
        drawSquare(inputSquareSpawn.x, inputSquareSpawn.y, INPUT_RADIUS,INPUT_RADIUS / 8, Color.BLACK, shapeRenderer);
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
                (INPUT_RADIUS / 2) / 8);
        shapeRenderer.rectLine((float) (inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2))),
                inputPlaySpawn.y - (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.x + (INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                (INPUT_RADIUS / 2) / 8);
        shapeRenderer.rectLine(inputPlaySpawn.x + (INPUT_RADIUS / 2),
                inputPlaySpawn.y,
                inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)),
                inputPlaySpawn.y + (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)),
                (INPUT_RADIUS / 2) / 8);
        drawPoint(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)), inputPlaySpawn.y + (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)), (INPUT_RADIUS / 2) / 8, Color.BLACK, shapeRenderer);
        drawPoint(inputPlaySpawn.x - (MathUtils.sinDeg(60) * (INPUT_RADIUS / 2)), inputPlaySpawn.y - (MathUtils.cosDeg(60) * (INPUT_RADIUS / 2)), (INPUT_RADIUS / 2) / 8, Color.BLACK, shapeRenderer);
        drawPoint(inputPlaySpawn.x + (INPUT_RADIUS / 2), inputPlaySpawn.y, (INPUT_RADIUS / 2) / 8, Color.BLACK, shapeRenderer);

        //Home
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputHomeSpawn.x, inputHomeSpawn.y, INPUT_RADIUS);
        drawTriangle(inputHomeSpawn.x, inputHomeSpawn.y + (INPUT_RADIUS / 3.6f), (INPUT_RADIUS / 2), (INPUT_RADIUS / 2) / 8, Color.BLACK, shapeRenderer);
        drawSquare(inputHomeSpawn.x, inputHomeSpawn.y - (INPUT_RADIUS / 3.6f), (INPUT_RADIUS / 2), (INPUT_RADIUS / 2) / 8, Color.BLACK, shapeRenderer);

        //Exit
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(inputExitSpawn.x, inputExitSpawn.y, INPUT_RADIUS);

        drawSquare(inputExitSpawn.x, inputExitSpawn.y, (INPUT_RADIUS / 2), (INPUT_RADIUS / 2) / 8, Color.BLACK, shapeRenderer);
    }

    public void drawBackgroundColorShape(Shape backgroundColorShape, ShapeRenderer shapeRenderer) {
        //TODO: Write and implement method
        drawShape(backgroundColorShape, shapeRenderer);
        if(backgroundColorShape.getRadius() < game.camera.viewportHeight * 4) {
            backgroundColorShape.setRadius(backgroundColorShape.getRadius() + colorSpeed);
        }

    }

    public void drawBackgroundColorShapeList(List<Shape> backgroundColorShapeList, Shape backgroundColorShape, Color clearColor, ShapeRenderer shapeRenderer) {
        for(int i = 0; i < backgroundColorShapeList.size(); i++) {
            Shape shape = backgroundColorShapeList.get(i);
            drawShape(shape, shapeRenderer);
            if(i == 0) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y - colorListSpeed));
            } else if(i == backgroundColorShapeList.size() - 1) {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x, shape.getCoordinates().y + colorListSpeed));
            } else {
                shape.setCoordinates(new Vector2(shape.getCoordinates().x + colorListSpeed, shape.getCoordinates().y));
            }
            if(backgroundColorShapeList.get(0).getCoordinates().y <= backgroundColorShapeList.get(1).getCoordinates().y) {
                float newRadius = shape.getRadius();
                backgroundColorShapeList.get(0).setCoordinates(new Vector2(backgroundColorShapeList.get(0).getCoordinates().x, backgroundColorShapeList.get(1).getCoordinates().y));

                clearColor.set(backgroundColorShape.getColor().r, backgroundColorShape.getColor().g, backgroundColorShape.getColor().b, backgroundColorShape.getColor().a);
                backgroundColorShape.setShape(Shape.randomBackgroundColorShape()); //TODO: Enable triangles to be drawn pointing downward, so they're identifiable here.
                backgroundColorShape.setRadius(game.camera.viewportWidth / 2);
                backgroundColorShape.setColor(backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor());
                backgroundColorShape.setFillColor(backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor());
                backgroundColorShape.setLineWidth(Draw.INPUT_RADIUS / 8);
                backgroundColorShape.setCoordinates(new Vector2(game.camera.viewportWidth / 2, game.camera.viewportHeight + (game.camera.viewportWidth / 2)));

                backgroundColorShapeList.remove(backgroundColorShapeList.size() - 1);
                backgroundColorShapeList.add(0, new Shape(Shape.SQUARE,
                        newRadius,
                        Color.WHITE,
                        ColorUtils.randomPrimary(),
                        newRadius / 8,
                        new Vector2(Draw.TARGET_RADIUS + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7),
                                (game.camera.viewportHeight - (Draw.INPUT_RADIUS / 2)) + ((game.camera.viewportWidth - (Draw.TARGET_RADIUS * 2)) / 7))));
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
}
