package com.screenlooker.squirgle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.screen.GameplayScreen;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.screen.TranceScreen;
import com.screenlooker.squirgle.screen.help.MenuHelpScreen;
import com.screenlooker.squirgle.screen.options.MenuOptionsScreen;
import com.screenlooker.squirgle.screen.type.*;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class Button {
    public static final int BUTTON_TYPE = 0;
    public static final int BUTTON_OPTIONS = 1;
    public static final int BUTTON_HELP = 2;
    public static final int BUTTON_QUIT = 3;
    public static final int BUTTON_TYPE_SINGLE_PLAYER = 4;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL = 5;
    public static final int BUTTON_TYPE_BACK = 6;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE = 7;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE = 8;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK = 9;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE = 10;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE = 11;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BACK = 12;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SQUARE = 13;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_PENTAGON = 14;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_HEXAGON = 15;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SEPTAGON = 16;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_OCTAGON = 17;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_NONAGON = 18;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_MUSIC = 19;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_BACK = 20;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SQUARE = 21;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_PENTAGON = 22;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_HEXAGON = 23;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SEPTAGON = 24;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_OCTAGON = 25;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_NONAGON = 26;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_DIFFICULTY = 27;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_MUSIC = 28;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_BACK = 29;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SQUARE = 30;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_PENTAGON = 31;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_HEXAGON = 32;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SEPTAGON = 33;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_OCTAGON = 34;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_NONAGON = 35;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_TIME = 36;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_MUSIC = 37;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_BACK = 38;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SQUARE = 39;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_PENTAGON = 40;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_HEXAGON = 41;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SEPTAGON = 42;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_OCTAGON = 43;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_NONAGON = 44;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_DIFFICULTY = 45;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_TIME = 46;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_MUSIC = 47;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK = 48;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY = 49;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_MUSIC = 50;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK = 51;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE = 52;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE = 53;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK = 54;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SQUARE = 55;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_PENTAGON = 56;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_HEXAGON = 57;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SEPTAGON = 58;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_OCTAGON = 59;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_NONAGON = 60;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_MUSIC = 61;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_BACK = 62;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SQUARE = 63;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_PENTAGON = 64;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_HEXAGON = 65;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SEPTAGON = 66;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_OCTAGON = 67;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_NONAGON = 68;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_TIME = 69;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_MUSIC = 70;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK = 71;
    public static final int BUTTON_OPTIONS_VOLUME = 72;
    public static final int BUTTON_OPTIONS_BACK = 73;
    //TODO: Add values for help screen once we've ironed out exactly how that screen should look/work

    private float x;
    private float y;
    private float width;
    private float height;
    private float radius;
    private float squirgleHeightOffset;
    private int buttonType;
    private boolean touched;
    private Color containerColor;
    private Color containedColor;
    private Color originalContainerColor;
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;
    private List<Shape> squirgleShapeList;
    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;
    private Shape squirglePrompt;
    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;
    private Squirgle game;

    public Button() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.radius = 0;
        this.squirgleHeightOffset = radius / 4;
        this.buttonType = 0;
        this.touched = false;
        this.containerColor = Color.BLACK;
        this.containedColor = Color.BLACK;
        this.originalContainerColor = containerColor;
        this.squareColor = ColorUtils.randomTransitionColor();
        this.circleColor = ColorUtils.randomTransitionColor();
        this.triangleColor = ColorUtils.randomTransitionColor();
        while(circleColor.equals(squareColor)) {
            circleColor = ColorUtils.randomTransitionColor();
        }
        while(triangleColor.equals(circleColor) || triangleColor.equals(squareColor)) {
            triangleColor = ColorUtils.randomTransitionColor();
        }
        this.squirgleShapeList = new ArrayList<Shape>();
        this.squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        this.squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleOne = new ArrayList<Shape>();
        this.squirgleShapeListBattleOne.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleOne.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleTwo = new ArrayList<Shape>();
        this.squirgleShapeListBattleTwo.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleTwo.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        this.squirglePrompt = new Shape(Shape.TRIANGLE,
                radius,
                triangleColor,
                null,
                radius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + (width / 2), (y + (height / 2)) - squirgleHeightOffset));
        this.squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                radius / 2,
                triangleColor,
                null,
                (radius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + (width / 4), y + ((3 *height) / 4)));
        this.squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                radius / 2,
                triangleColor,
                null,
                (radius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + ((3 * width) / 4), y + (height / 4)));
        this.game = new Squirgle();
    }

    public Button(float x, float y, float width, float height, int buttonType, Color containerColor, Color containedColor, Squirgle game) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = width > height ? height / 2 : width / 2;
        this.squirgleHeightOffset = radius / 4;
        this.buttonType = buttonType;
        this.touched = false;
        this.containerColor = containerColor;
        this.containedColor = containedColor;
        this.originalContainerColor = containerColor;
        this.squareColor = ColorUtils.randomTransitionColor();
        this.circleColor = ColorUtils.randomTransitionColor();
        this.triangleColor = ColorUtils.randomTransitionColor();
        while(circleColor.equals(squareColor)) {
            circleColor = ColorUtils.randomTransitionColor();
        }
        while(triangleColor.equals(circleColor) || triangleColor.equals(squareColor)) {
            triangleColor = ColorUtils.randomTransitionColor();
        }
        this.squirgleShapeList = new ArrayList<Shape>();
        this.squirgleShapeList.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        this.squirgleShapeList.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleOne = new ArrayList<Shape>();
        this.squirgleShapeListBattleOne.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleOne.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleTwo = new ArrayList<Shape>();
        this.squirgleShapeListBattleTwo.add(new Shape(Shape.SQUARE, 0, squareColor, null, 0, new Vector2()));
        this.squirgleShapeListBattleTwo.add(new Shape(Shape.CIRCLE, 0, circleColor, null, 0, new Vector2()));
        this.squirglePrompt = new Shape(Shape.TRIANGLE,
                radius,
                triangleColor,
                null,
                radius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + (width / 2), (y + (height / 2)) - squirgleHeightOffset));
        this.squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                radius / 2,
                triangleColor,
                null,
                (radius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + (width / 4), y + ((3 *height) / 4)));
        this.squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                radius / 2,
                triangleColor,
                null,
                (radius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + ((3 * width) / 4), y + (height / 4)));
        this.game = game;
    }

    public void draw() {
        drawContainer();
        drawSymbol();
        drawText();
        transitionSquirgleColors();
    }

    public void drawContainer() {
        game.draw.rect(x,
                y,
                width,
                height,
                containerColor);
    }

    public void drawSymbol() {
        float centerX = x + (width / 2);
        float centerY = y + (height / 2);

        switch(buttonType) {
            case BUTTON_TYPE : {
                game.draw.drawPlayButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_OPTIONS : {
                game.draw.drawWrench(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP : {
                game.draw.drawQuestionMark(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_QUIT : {
                game.draw.drawX(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER : {
                game.draw.drawFace(centerX,
                        centerY,
                        radius / 3,
                        (radius / 3) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL : {
                game.draw.drawFace((game.camera.viewportWidth / 2) - radius + (radius / 3),
                        game.camera.viewportHeight / 4,
                        radius / 3,
                        (radius / 3) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                game.draw.drawFace((game.camera.viewportWidth / 2) + radius - (radius / 3),
                        game.camera.viewportHeight / 4,
                        radius / 3,
                        (radius / 3) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                game.shapeRendererFilled.setColor(containedColor);
                game.shapeRendererFilled.rectLine((game.camera.viewportWidth / 2) - radius,
                        game.camera.viewportHeight / 4,
                        (game.camera.viewportWidth / 2) + radius,
                        game.camera.viewportHeight / 4,
                        (radius / 2) / Draw.LINE_WIDTH_DIVISOR);
                break;
            }
            case BUTTON_TYPE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE : {
                game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE : {
                game.shapeRendererFilled.setColor(containedColor);
                game.shapeRendererFilled.rectLine((2 * game.partitionSize) + width,
                        game.camera.viewportHeight - (2 * game.partitionSize) - (2 * height),
                        (2 * game.partitionSize) + (2 * width),
                        game.camera.viewportHeight - (2 * game.partitionSize) - height,
                        game.partitionSize);
                game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
                game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK : {
                game.draw.drawClock(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE : {
                game.shapeRendererFilled.setColor(Color.BLACK);
                game.shapeRendererFilled.rectLine((2 * game.partitionSize) + width,
                        (2 * game.partitionSize) + height,
                        (2 * game.partitionSize) + (2 * width),
                        (2 * game.partitionSize) + (2 * height),
                        game.partitionSize);
                game.draw.drawClock((game.camera.viewportWidth / 2) - (width / 4),
                        ((3 * game.camera.viewportHeight) / 10) + (height / 6),
                        radius / 2,
                        containedColor,
                        containerColor);
                game.draw.drawClock((game.camera.viewportWidth / 2) + (width / 4),
                        ((3 * game.camera.viewportHeight) / 10) - (height / 6),
                        radius / 2,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE : {
                game.draw.drawTranceSymbol(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicTypeSelection();
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_DIFFICULTY : {
                game.draw.drawDifficultySymbol((2 * game.partitionSize) + width + (width / 5),
                        (3 * game.partitionSize) + (2 * height) + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1),
                        containedColor,
                        containerColor);
                game.draw.drawChevronLeft((2 * game.partitionSize) + width + ((2 * width) / 5),
                        (3 * game.partitionSize) + (2 * height) + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                game.draw.drawChevronRight((2 * game.partitionSize) + width + ((4 * width) / 5),
                        (3 * game.partitionSize) + (2 * height) + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicTypeSelection();
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_TIME : {
                game.draw.drawClock((2 * game.partitionSize) + width + (width / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        containedColor,
                        containerColor);
                game.draw.drawChevronLeft((2 * game.partitionSize) + width + ((2 * width) / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                game.draw.drawChevronRight((2 * game.partitionSize) + width + ((4 * width) / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicTypeSelection();
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_DIFFICULTY : {
                game.draw.drawDifficultySymbol((2 * game.partitionSize) + width + (width / 5),
                        (3 * game.partitionSize) + (2 * height) + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1),
                        containedColor,
                        containerColor);
                game.draw.drawChevronLeft((2 * game.partitionSize) + width + ((2 * width) / 5),
                        (3 * game.partitionSize) + (2 * height) + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                game.draw.drawChevronRight((2 * game.partitionSize) + width + ((4 * width) / 5),
                        (3 * game.partitionSize) + (2 * height) + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_DIFFICULTY_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_TIME : {
                game.draw.drawClock((2 * game.partitionSize) + width + (width / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        containedColor,
                        containerColor);
                game.draw.drawChevronLeft((2 * game.partitionSize) + width + ((2 * width) / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                game.draw.drawChevronRight((2 * game.partitionSize) + width + ((4 * width) / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicTypeSelection();
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY : {
                game.draw.drawPlayButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE : {
                game.shapeRendererFilled.setColor(containedColor);
                game.shapeRendererFilled.rectLine((2 * game.partitionSize) + width,
                        game.camera.viewportHeight - (2 * game.partitionSize) - (2 * height),
                        (2 * game.partitionSize) + (2 * width),
                        game.camera.viewportHeight - (2 * game.partitionSize) - height,
                        game.partitionSize);
                game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
                game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE : {
                game.shapeRendererFilled.setColor(Color.BLACK);
                game.shapeRendererFilled.rectLine((2 * game.partitionSize) + width,
                        (2 * game.partitionSize) + height,
                        (2 * game.partitionSize) + (2 * width),
                        (2 * game.partitionSize) + (2 * height),
                        game.partitionSize);
                game.draw.drawClock((game.camera.viewportWidth / 2) - (width / 4),
                        ((3 * game.camera.viewportHeight) / 10) + (height / 6),
                        radius / 2,
                        containedColor,
                        containerColor);
                game.draw.drawClock((game.camera.viewportWidth / 2) + (width / 4),
                        ((3 * game.camera.viewportHeight) / 10) - (height / 6),
                        radius / 2,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicTypeSelection();
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_TIME : {
                game.draw.drawClock((2 * game.partitionSize) + width + (width / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        containedColor,
                        containerColor);
                game.draw.drawChevronLeft((2 * game.partitionSize) + width + ((2 * width) / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                game.draw.drawChevronRight((2 * game.partitionSize) + width + ((4 * width) / 5),
                        (2 * game.partitionSize) + height + (height / 2),
                        radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1),
                        (radius / (MenuTypeSinglePlayerTimeBattleScreen.NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                drawMusicTypeSelection();
                drawMusicNameSelection();
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_OPTIONS_VOLUME : {
                game.draw.drawSoundSymbol((2 * game.partitionSize) + width + (width / 5),
                        game.camera.viewportHeight / 2,
                        radius / (MenuOptionsScreen.NUM_SOUND_INPUT_ELEMENTS + 1),
                        (radius / (MenuOptionsScreen.NUM_SOUND_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        containerColor);
                game.draw.drawChevronLeft((2 * game.partitionSize) + width + ((2 * width) / 5),
                        game.camera.viewportHeight / 2,
                        radius / (MenuOptionsScreen.NUM_SOUND_INPUT_ELEMENTS + 1),
                        (radius / (MenuOptionsScreen.NUM_SOUND_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                game.draw.drawChevronRight((2 * game.partitionSize) + width + ((4 * width) / 5),
                        game.camera.viewportHeight / 2,
                        radius / (MenuOptionsScreen.NUM_SOUND_INPUT_ELEMENTS + 1),
                        (radius / (MenuOptionsScreen.NUM_SOUND_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                        Color.BLACK);
                break;
            }
            case BUTTON_OPTIONS_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            //TODO: Add values for help screen once we've ironed out exactly how that screen should look/work
        }
    }

    public void drawText() {
        float centerX = x + (width / 2);
        float centerY = y + (height / 2);

        switch(buttonType) {
            case BUTTON_TYPE : {
                break;
            }
            case BUTTON_OPTIONS : {
                break;
            }
            case BUTTON_HELP : {
                break;
            }
            case BUTTON_QUIT : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL : {
                break;
            }
            case BUTTON_TYPE_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SQUARE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_PENTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_HEXAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SEPTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_OCTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_NONAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_MUSIC : {
                drawMusicTypeText();
                drawMusicNameText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SQUARE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_PENTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_HEXAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SEPTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_OCTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_NONAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_DIFFICULTY : {
                drawDifficultyText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_MUSIC : {
                drawMusicTypeText();
                drawMusicNameText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SQUARE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_PENTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_HEXAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SEPTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_OCTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_NONAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_TIME : {
                drawTimeText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_MUSIC : {
                drawMusicTypeText();
                drawMusicNameText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SQUARE : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_PENTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_HEXAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SEPTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_OCTAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_NONAGON : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_DIFFICULTY : {
                drawDifficultyText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_TIME : {
                drawTimeText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_MUSIC : {
                drawMusicTypeText();
                drawMusicNameText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_MUSIC : {
                drawMusicNameText();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {

            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SQUARE : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_PENTAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_HEXAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SEPTAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_OCTAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_NONAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_MUSIC : {
                drawMusicTypeText();
                drawMusicNameText();
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_BACK : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SQUARE : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_PENTAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_HEXAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SEPTAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_OCTAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_NONAGON : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_TIME : {
                drawTimeText();
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_MUSIC : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK : {
                break;
            }
            case BUTTON_OPTIONS_VOLUME : {
                break;
            }
            case BUTTON_OPTIONS_BACK : {
                break;
            }
            //TODO: Add values for help screen once we've ironed out exactly how that screen should look/work
        }
    }

    public void drawMusicTypeSelection() {
        if(game.usePhases) {
            game.draw.rect((3 * game.partitionSize) + width,
                    game.partitionSize + ((height - (radius * 2)) / 2) + ((2 * (radius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4),
                    (width / 2) - game.partitionSize,
                    game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5),
                    containedColor);
        } else {
            game.draw.rect((3 * game.partitionSize) + width,
                    game.partitionSize + ((height - (radius * 2)) / 2) + ((3 * (radius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4),
                    (width / 2) - game.partitionSize,
                    game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5),
                    containedColor);
        }
    }

    public void drawMusicNameSelection() {
        for(int i = 0; i < game.maxBase; i++) {
            if(game.track == i) {
                game.draw.rect(game.camera.viewportWidth / 2,
                        game.partitionSize + height - ((height - (radius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (i * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                        (width / 2) - game.partitionSize,
                        (7 * game.fontTrackName.getCapHeight()) / 4,
                        containedColor);
            }
        }
    }

    public void drawDifficultyText() {
        FontUtils.printText(game.batch,
                game.fontDifficulty,
                game.layout,
                Color.BLACK,
                game.difficulty,
                x + ((3 * width) / 5),
                y + (height / 2),
                0,
                1);
    }

    public void drawTimeText() {
        FontUtils.printText(game.batch,
                game.fontTime,
                game.layout,
                Color.BLACK,
                String.valueOf(game.timeAttackNumSeconds / Squirgle.ONE_MINUTE),
                x + ((3 * width) / 5),
                y + (height / 2),
                0,
                1);
    }

    public void drawMusicTypeText() {
        FontUtils.printText(game.batch,
                game.fontTrackType,
                game.layout,
                game.usePhases ? Color.BLACK : Color.WHITE,
                game.MUSIC_TYPE_FULL,
                (game.camera.viewportWidth / 2) - (width / 4),
                game.partitionSize + ((height - (radius * 2)) / 2) + ((3 * (radius * 2)) / 4),
                0,
                1);
        FontUtils.printText(game.batch,
                game.fontTrackType,
                game.layout,
                game.usePhases ? Color.WHITE : Color.BLACK,
                game.MUSIC_TYPE_SPLIT,
                (game.camera.viewportWidth / 2) - (width / 4),
                game.partitionSize + ((height - (radius * 2)) / 2) + ((2 * (radius * 2)) / 4),
                0,
                1);
    }

    public void drawMusicNameText() {
        for(int i = 0; i < game.maxBase; i++) {
            FontUtils.printText(game.batch,
                    game.fontTrackName,
                    game.layout,
                    game.track == i ? Color.WHITE : Color.BLACK,
                    game.musicTitleList.get(i),
                    (game.camera.viewportWidth / 2) + (width / 4),
                    game.partitionSize + height - ((height - (radius * 2)) / 2) - game.fontTrackName.getCapHeight() - (i * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                    0,
                    1);
        }
    }

    public void touchDown(Vector3 touchPoint) {
        boolean xInBounds = touchPoint.x > x
                && touchPoint.x < x + width;
        boolean yInBounds = touchPoint.y > y
                && touchPoint.y < y + height;

        if(xInBounds && yInBounds) {
            containerColor = Color.WHITE;
        }
    }

    //TODO: Figure out and take care of music, difficulty, time and volume behavior
    //The boolean returned here is used by the calling Screen object to determine whether or not to call its dispose method
    public boolean touchUp(Vector3 touchPoint) {
        containerColor = originalContainerColor;

        boolean xInBounds = touchPoint.x > x
                && touchPoint.x < x + width;
        boolean yInBounds = touchPoint.y > y
                && touchPoint.y < y + height;

        if(xInBounds && yInBounds) {
            switch(buttonType) {
                case BUTTON_TYPE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeScreen(game));
                    return true;
                }
                case BUTTON_OPTIONS: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuOptionsScreen(game));
                    return true;
                }
                case BUTTON_HELP: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpScreen(game));
                    return true;
                }
                case BUTTON_QUIT: {
                    System.exit(0);
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game));
                    return true;
                }
                case BUTTON_TYPE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MainMenuScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerSquirgleScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerBattleScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerTimeAttackScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerTimeBattleScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerTranceScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SQUARE: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_PENTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_HEXAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_SEPTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_OCTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_NONAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_MUSIC: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SQUARE: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.updateSave(game.SAVE_DIFFICULTY, game.difficulty);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_PENTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.updateSave(game.SAVE_DIFFICULTY, game.difficulty);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_HEXAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.updateSave(game.SAVE_DIFFICULTY, game.difficulty);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SEPTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.updateSave(game.SAVE_DIFFICULTY, game.difficulty);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_OCTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.updateSave(game.SAVE_DIFFICULTY, game.difficulty);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_NONAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.updateSave(game.SAVE_DIFFICULTY, game.difficulty);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_DIFFICULTY: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_MUSIC: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SQUARE: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_PENTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_HEXAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SEPTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_OCTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_NONAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_TIME: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_MUSIC: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SQUARE: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_PENTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_HEXAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SEPTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_OCTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_NONAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_DIFFICULTY: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_TIME: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_MUSIC: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.usePhases = false;
                    game.setScreen(new TranceScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_MUSIC: {

                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalBattleScreen(game));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalTimeBattleScreen(game));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeScreen(game));
                    return true;

                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SQUARE: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_PENTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_HEXAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SEPTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_OCTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_NONAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_MUSIC: {

                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SQUARE: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_PENTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_HEXAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SEPTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_OCTAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_NONAGON: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_TIME: {

                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_MUSIC: {

                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game));
                    return true;
                }
                case BUTTON_OPTIONS_VOLUME: {

                }
                case BUTTON_OPTIONS_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.updateSave(game.SAVE_VOLUME, game.volume);
                    game.setScreen(new MainMenuScreen(game));
                    return true;
                }
                //TODO: Add values for help screen once we've ironed out exactly how that screen should look/work
            }
        }

        return false;
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
