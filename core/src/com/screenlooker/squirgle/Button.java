package com.screenlooker.squirgle;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.screen.*;
import com.screenlooker.squirgle.screen.help.*;
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
    public static final int BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_BACK = 19;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SQUARE = 20;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_PENTAGON = 21;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_HEXAGON = 22;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_SEPTAGON = 23;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_OCTAGON = 24;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_NONAGON = 25;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_BATTLE_BACK = 26;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SQUARE = 27;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_PENTAGON = 28;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_HEXAGON = 29;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_SEPTAGON = 30;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_OCTAGON = 31;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_NONAGON = 32;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_BACK = 33;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SQUARE = 34;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_PENTAGON = 35;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_HEXAGON = 36;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_SEPTAGON = 37;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_OCTAGON = 38;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_NONAGON = 39;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK = 40;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY = 41;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK = 42;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE = 43;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE = 44;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK = 45;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SQUARE = 46;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_PENTAGON = 47;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_HEXAGON = 48;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_SEPTAGON = 49;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_OCTAGON = 50;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_NONAGON = 51;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_BACK = 52;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SQUARE = 53;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_PENTAGON = 54;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_HEXAGON = 55;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_SEPTAGON = 56;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_OCTAGON = 57;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_NONAGON = 58;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK = 59;
    public static final int BUTTON_OPTIONS_BACK = 60;
    public static final int BUTTON_HELP_ADDITION = 61;
    public static final int BUTTON_HELP_STATS = 62;
    public static final int BUTTON_HELP_TUTORIAL = 63;
    public static final int BUTTON_HELP_CREDITS = 64;
    public static final int BUTTON_HELP_BACK = 65;
    public static final int BUTTON_HELP_ADDITION_COLOR = 66;
    public static final int BUTTON_HELP_ADDITION_SQUARE = 67;
    public static final int BUTTON_HELP_ADDITION_PENTAGON = 68;
    public static final int BUTTON_HELP_ADDITION_HEXAGON = 69;
    public static final int BUTTON_HELP_ADDITION_SEPTAGON = 70;
    public static final int BUTTON_HELP_ADDITION_OCTAGON = 71;
    public static final int BUTTON_HELP_ADDITION_NONAGON = 72;
    public static final int BUTTON_HELP_ADDITION_BACK = 73;
    public static final int BUTTON_HELP_ADDITION_COLOR_BACK = 74;
    public static final int BUTTON_HELP_ADDITION_BASE_BACK = 75;
    public static final int BUTTON_HELP_STATS_GENERAL = 76;
    public static final int BUTTON_HELP_STATS_SQUIRGLE = 77;
    public static final int BUTTON_HELP_STATS_BATTLE = 78;
    public static final int BUTTON_HELP_STATS_TIME_ATTACK = 79;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE = 80;
    public static final int BUTTON_HELP_STATS_TRANCE = 81;
    public static final int BUTTON_HELP_STATS_BACK = 82;
    public static final int BUTTON_HELP_STATS_GENERAL_BACK = 83;
    public static final int BUTTON_HELP_STATS_SQUIRGLE_BACK = 84;
    public static final int BUTTON_HELP_STATS_BATTLE_EASY = 85;
    public static final int BUTTON_HELP_STATS_BATTLE_MEDIUM = 86;
    public static final int BUTTON_HELP_STATS_BATTLE_HARD = 87;
    public static final int BUTTON_HELP_STATS_BATTLE_BACK = 88;
    public static final int BUTTON_HELP_STATS_TIME_ATTACK_ONE_MINUTE = 89;
    public static final int BUTTON_HELP_STATS_TIME_ATTACK_THREE_MINUTES = 90;
    public static final int BUTTON_HELP_STATS_TIME_ATTACK_FIVE_MINUTES = 91;
    public static final int BUTTON_HELP_STATS_TIME_ATTACK_BACK = 92;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_EASY = 93;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM = 94;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_HARD = 95;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_EASY_ONE_MINUTE = 96;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_EASY_THREE_MINUTES = 97;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_EASY_FIVE_MINUTES = 98;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_ONE_MINUTE = 99;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_THREE_MINUTES = 100;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_FIVE_MINUTES = 101;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_HARD_ONE_MINUTE = 102;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_HARD_THREE_MINUTES = 103;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_HARD_FIVE_MINUTES = 104;
    public static final int BUTTON_HELP_STATS_TIME_BATTLE_BACK = 105;
    public static final int BUTTON_HELP_STATS_TRANCE_BACK = 106;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE = 107;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE = 108;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK = 109;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE = 110;
    public static final int BUTTON_HELP_TUTORIAL_TRANCE = 111;
    public static final int BUTTON_HELP_TUTORIAL_BACK = 112;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE = 113;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON = 114;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON = 115;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON = 116;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON = 117;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON = 118;
    public static final int BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK = 119;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_SQUARE = 120;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON = 121;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON = 122;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON = 123;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON = 124;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_NONAGON = 125;
    public static final int BUTTON_HELP_TUTORIAL_BATTLE_BACK = 126;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE = 127;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON = 128;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON = 129;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON = 130;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON = 131;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON = 132;
    public static final int BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK = 133;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE = 134;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON = 135;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON = 136;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON = 137;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON = 138;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON = 139;
    public static final int BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK = 140;
    public static final int BUTTON_MUSIC = 141;
    public static final int BUTTON_MUSIC_FULL = 142;
    public static final int BUTTON_MUSIC_SPLIT = 143;
    public static final int BUTTON_MUSIC_POINTILLISM = 144;
    public static final int BUTTON_MUSIC_LINEAGE = 145;
    public static final int BUTTON_MUSIC_TRI_THE_WALTZ = 146;
    public static final int BUTTON_MUSIC_SQUARED_OFF = 147;
    public static final int BUTTON_MUSIC_PENT_UP = 148;
    public static final int BUTTON_MUSIC_HEXIDECIBEL = 149;
    public static final int BUTTON_MUSIC_INTERSEPTOR = 150;
    public static final int BUTTON_MUSIC_ROCTOPUS = 151;
    public static final int BUTTON_MUSIC_NONPLUSSED = 152;
    public static final int BUTTON_DIFFICULTY = 153;
    public static final int BUTTON_DIFFICULTY_DIAL = 154;
    public static final int BUTTON_DIFFICULTY_CHEVRON_DOWN = 155;
    public static final int BUTTON_DIFFICULTY_CHEVRON_UP = 156;
    public static final int BUTTON_TIME = 157;
    public static final int BUTTON_TIME_CLOCK = 158;
    public static final int BUTTON_TIME_CHEVRON_DOWN = 159;
    public static final int BUTTON_TIME_CHEVRON_UP = 160;
    public static final int BUTTON_VOLUME = 161;
    public static final int BUTTON_VOLUME_WAVES = 162;
    public static final int BUTTON_VOLUME_CHEVRON_DOWN = 163;
    public static final int BUTTON_VOLUME_CHEVRON_UP = 164;

    public static final String QUESTION_MARK = "?";
    private final static String HIGHEST_SCORE = "HIGHEST SCORE: ";
    private final static String ONE_MINUTE = "1M";
    private final static String THREE_MINUTES = "3M";
    private final static String FIVE_MINUTES = "5M";

    private float x;
    private float y;
    private float width;
    private float height;
    private float radius;
    private float squirgleHeightOffset;
    private float transitionThreshold;
    private float transitionIncrement;
    private int buttonType;
    public boolean touched;
    private Color containerColor;
    private Color containedColor;
    private Color originalContainerColor;
    private Color squareColor;
    private Color circleColor;
    private Color triangleColor;
    private List<Shape> transitionCirclesList;
    private List<Shape> squirgleShapeList;
    private List<Shape> squirgleShapeListBattleOne;
    private List<Shape> squirgleShapeListBattleTwo;
    private Shape squirglePrompt;
    private Shape squirglePromptBattleOne;
    private Shape squirglePromptBattleTwo;
    private Squirgle game;

    public Button() {
        this.game = new Squirgle();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.radius = 0;
        this.squirgleHeightOffset = radius / 4;
        this.transitionThreshold = (float) Math.sqrt(Math.pow(game.camera.viewportWidth, 2) + Math.pow(game.camera.viewportHeight, 2));
        this.transitionIncrement = game.widthOrHeight / 5;
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
        this.transitionCirclesList = new ArrayList<Shape>();
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + (width / 4), y + ((3 * height) / 4))));
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + (width / 4), y + (height / 4))));
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + ((3 * width) / 4), y + (height / 4))));
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + ((3 * width) / 4), y + ((3 * height) / 4))));
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
    }

    public Button(float x, float y, float width, float height, int buttonType, Color containerColor, Color containedColor, Squirgle game) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = width > height ? height / 2 : width / 2;
        this.squirgleHeightOffset = radius / 4;
        this.transitionThreshold = (float) Math.sqrt(Math.pow(game.camera.viewportWidth, 2) + Math.pow(game.camera.viewportHeight, 2));
        this.transitionIncrement = game.widthOrHeight / 5;
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
        this.transitionCirclesList = new ArrayList<Shape>();
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + (width / 4), y + ((3 * height) / 4))));
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + (width / 4), y + (height / 4))));
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + ((3 * width) / 4), y + (height / 4))));
        this.transitionCirclesList.add(new Shape(Shape.POINT,
                0,
                containerColor,
                containerColor,
                0,
                new Vector2(x + ((3 * width) / 4), y + ((3 * height) / 4))));
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
                new Vector2(x + (width / 4), y + ((3 *height) / 4) - squirgleHeightOffset));
        this.squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                radius / 2,
                triangleColor,
                null,
                (radius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + ((3 * width) / 4), y + (height / 4)));
    }

    public void draw() {
        drawContainer();
        drawSymbol();
        transitionSquirgleColors();
    }

    public void drawContainer() {
        if(isMusicTypeButton()) {
            if((buttonType == BUTTON_MUSIC_FULL && !game.usePhases)
                    || buttonType == BUTTON_MUSIC_SPLIT && game.usePhases) {
                game.draw.rect(x,
                        y,
                        width,
                        height,
                        Color.BLACK);
            }
        } else if(isMusicNameButton()) {
            if((buttonType == BUTTON_MUSIC_POINTILLISM && game.track == game.MUSIC_POINTILLISM)
                    || (buttonType == BUTTON_MUSIC_LINEAGE && game.track == game.MUSIC_LINEAGE)
                    || (buttonType == BUTTON_MUSIC_TRI_THE_WALTZ && game.track == game.MUSIC_TRI_THE_WALTZ)
                    || (buttonType == BUTTON_MUSIC_SQUARED_OFF && game.track == game.MUSIC_SQUARED_OFF)
                    || (buttonType == BUTTON_MUSIC_PENT_UP && game.track == game.MUSIC_PENT_UP)
                    || (buttonType == BUTTON_MUSIC_HEXIDECIBEL && game.track == game.MUSIC_HEXIDECIBEL)
                    || (buttonType == BUTTON_MUSIC_INTERSEPTOR && game.track == game.MUSIC_INTERSEPTOR)
                    || (buttonType == BUTTON_MUSIC_ROCTOPUS && game.track == game.MUSIC_ROCTOPUS)
                    || (buttonType == BUTTON_MUSIC_NONPLUSSED && game.track == game.MUSIC_NONPLUSSED)) {
                game.draw.rect(x,
                        y,
                        width,
                        height,
                        Color.BLACK);
            }
        } else {
            game.draw.rect(x,
                    y,
                    width,
                    height,
                    containerColor);
        }
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
                drawBattleSymbol();
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
                drawTimeBattleSymbol();
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
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE : {
                drawBattleSymbol();
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE : {
                drawTimeBattleSymbol();
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
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        y + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
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
            case BUTTON_HELP_ADDITION : {
                game.draw.drawPlus(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS : {
                game.draw.drawModulo(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL : {
                game.draw.drawTutorialSymbol(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_CREDITS : {
                game.draw.drawCreditsSymbol(centerX,
                        centerY,
                        radius,
                        containedColor);
                break;
            }
            case BUTTON_HELP_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR : {
                game.draw.drawColorWheel(centerX,
                        centerY,
                        radius,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_BASE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_GENERAL : {
                game.draw.drawSigma(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE : {
                game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE : {
                drawBattleSymbol();
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK : {
                game.draw.drawClock(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE : {
                drawTimeBattleSymbol();
                break;
            }
            case BUTTON_HELP_STATS_TRANCE : {
                game.draw.drawTranceSymbol(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_STATS_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_GENERAL_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_EASY : {
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_MEDIUM : {
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_HARD : {
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_ONE_MINUTE : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_THREE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_FIVE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY_ONE_MINUTE : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY_THREE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY_FIVE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_ONE_MINUTE : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_THREE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_FIVE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD_ONE_MINUTE : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD_THREE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD_FIVE_MINUTES : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_TRANCE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE : {
                game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
                game.draw.drawShapes(false, squirgleShapeList, squirglePrompt, false);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE : {
                drawBattleSymbol();
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK : {
                game.draw.drawClock(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE : {
                drawTimeBattleSymbol();
                break;
            }
            case BUTTON_HELP_TUTORIAL_TRANCE : {
                game.draw.drawTranceSymbol(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK : {
                game.draw.drawBackButton(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (radius / 4) + ((radius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        game.partitionSize + (height / 2),
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_MUSIC_FULL : {
                break;
            }
            case BUTTON_MUSIC_SPLIT : {
                break;
            }
            case BUTTON_MUSIC_POINTILLISM : {
                break;
            }
            case BUTTON_MUSIC_LINEAGE : {
                break;
            }
            case BUTTON_MUSIC_TRI_THE_WALTZ : {
                break;
            }
            case BUTTON_MUSIC_SQUARED_OFF : {
                break;
            }
            case BUTTON_MUSIC_PENT_UP : {
                break;
            }
            case BUTTON_MUSIC_HEXIDECIBEL : {
                break;
            }
            case BUTTON_MUSIC_INTERSEPTOR : {
                break;
            }
            case BUTTON_MUSIC_ROCTOPUS : {
                break;
            }
            case BUTTON_MUSIC_NONPLUSSED : {
                break;
            }
            case BUTTON_DIFFICULTY : {
                break;
            }
            case BUTTON_DIFFICULTY_DIAL : {
                game.draw.drawDifficultySymbol(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_DIFFICULTY_CHEVRON_DOWN : {
                game.draw.drawChevronLeft(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_DIFFICULTY_CHEVRON_UP : {
                game.draw.drawChevronRight(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TIME : {
                break;
            }
            case BUTTON_TIME_CLOCK : {
                game.draw.drawClock(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TIME_CHEVRON_DOWN : {
                game.draw.drawChevronLeft(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TIME_CHEVRON_UP : {
                game.draw.drawChevronRight(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_VOLUME : {
                break;
            }
            case BUTTON_VOLUME_WAVES : {
                game.draw.drawSoundSymbol(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containerColor);
                break;
            }
            case BUTTON_VOLUME_CHEVRON_DOWN : {
                game.draw.drawChevronLeft(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_VOLUME_CHEVRON_UP : {
                game.draw.drawChevronRight(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
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
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY : {
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                break;
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
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK : {
                break;
            }
            case BUTTON_OPTIONS_BACK : {
                break;
            }
            case BUTTON_HELP_ADDITION : {
                break;
            }
            case BUTTON_HELP_STATS : {
                break;
            }
            case BUTTON_HELP_TUTORIAL : {
                break;
            }
            case BUTTON_HELP_CREDITS : {
                break;
            }
            case BUTTON_HELP_BACK : {
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR : {
                break;
            }
            case BUTTON_HELP_ADDITION_SQUARE : {
                break;
            }
            case BUTTON_HELP_ADDITION_PENTAGON : {
                break;
            }
            case BUTTON_HELP_ADDITION_HEXAGON : {
                break;
            }
            case BUTTON_HELP_ADDITION_SEPTAGON : {
                break;
            }
            case BUTTON_HELP_ADDITION_OCTAGON : {
                break;
            }
            case BUTTON_HELP_ADDITION_NONAGON : {
                break;
            }
            case BUTTON_HELP_ADDITION_BACK : {
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR_BACK : {
                break;
            }
            case BUTTON_HELP_ADDITION_BASE_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_GENERAL : {
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE : {
                break;
            }
            case BUTTON_HELP_STATS_BATTLE : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE : {
                break;
            }
            case BUTTON_HELP_STATS_TRANCE : {
                break;
            }
            case BUTTON_HELP_STATS_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_GENERAL_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_EASY : {
                game.layout.setText(game.fontStats, Squirgle.DIFFICULTY_EASY);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.difficulty.equals(Squirgle.DIFFICULTY_EASY) ? Color.WHITE : Color.BLACK,
                        Squirgle.DIFFICULTY_EASY,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_MEDIUM : {
                game.layout.setText(game.fontStats, Squirgle.DIFFICULTY_MEDIUM);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM) ? Color.WHITE : Color.BLACK,
                        Squirgle.DIFFICULTY_MEDIUM,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_HARD : {
                game.layout.setText(game.fontStats, Squirgle.DIFFICULTY_HARD);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.difficulty.equals(Squirgle.DIFFICULTY_HARD) ? Color.WHITE : Color.BLACK,
                        Squirgle.DIFFICULTY_HARD,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_ONE_MINUTE : {
                game.layout.setText(game.fontStats, ONE_MINUTE);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.WHITE : Color.BLACK,
                        ONE_MINUTE,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_THREE_MINUTES : {
                game.layout.setText(game.fontStats, THREE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.WHITE : Color.BLACK,
                        THREE_MINUTES,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_FIVE_MINUTES : {
                game.layout.setText(game.fontStats, FIVE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.WHITE : Color.BLACK,
                        FIVE_MINUTES,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY : {
                game.layout.setText(game.fontStats, Squirgle.DIFFICULTY_EASY);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.difficulty.equals(Squirgle.DIFFICULTY_EASY) ? Color.WHITE : Color.BLACK,
                        Squirgle.DIFFICULTY_EASY,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM : {
                game.layout.setText(game.fontStats, Squirgle.DIFFICULTY_MEDIUM);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM) ? Color.WHITE : Color.BLACK,
                        Squirgle.DIFFICULTY_MEDIUM,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD : {
                game.layout.setText(game.fontStats, Squirgle.DIFFICULTY_HARD);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.difficulty.equals(Squirgle.DIFFICULTY_HARD) ? Color.WHITE : Color.BLACK,
                        Squirgle.DIFFICULTY_HARD,
                        x + width - (game.layout.width / 2),
                        y + (height / 2) - (game.layout.height / 2),
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY_ONE_MINUTE : {
                game.layout.setText(game.fontStats, ONE_MINUTE);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.BLACK : Color.WHITE,
                        ONE_MINUTE,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY_THREE_MINUTES : {
                game.layout.setText(game.fontStats, THREE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.BLACK : Color.WHITE,
                        THREE_MINUTES,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_EASY_FIVE_MINUTES : {
                game.layout.setText(game.fontStats, FIVE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.BLACK : Color.WHITE,
                        FIVE_MINUTES,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_ONE_MINUTE : {
                game.layout.setText(game.fontStats, ONE_MINUTE);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.BLACK : Color.WHITE,
                        ONE_MINUTE,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_THREE_MINUTES : {
                game.layout.setText(game.fontStats, THREE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.BLACK : Color.WHITE,
                        THREE_MINUTES,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_FIVE_MINUTES : {
                game.layout.setText(game.fontStats, FIVE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.BLACK : Color.WHITE,
                        FIVE_MINUTES,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD_ONE_MINUTE : {
                game.layout.setText(game.fontStats, ONE_MINUTE);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.ONE_MINUTE ? Color.BLACK : Color.WHITE,
                        ONE_MINUTE,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD_THREE_MINUTES : {
                game.layout.setText(game.fontStats, THREE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.THREE_MINUTES ? Color.BLACK : Color.WHITE,
                        THREE_MINUTES,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_HARD_FIVE_MINUTES : {
                game.layout.setText(game.fontStats, FIVE_MINUTES);
                FontUtils.printText(game.batch,
                        game.fontStats,
                        game.layout,
                        game.timeAttackNumSeconds == Squirgle.FIVE_MINUTES ? Color.BLACK : Color.WHITE,
                        FIVE_MINUTES,
                        centerX,
                        centerY,
                        0,
                        1);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_BACK : {
                break;
            }
            case BUTTON_HELP_STATS_TRANCE_BACK : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TRANCE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BACK : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SQUARE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_NONAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_BACK : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON : {
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK : {
                break;
            }
            case BUTTON_MUSIC : {
                break;
            }
            case BUTTON_MUSIC_FULL : {
                game.layout.setText(game.fontTrackType, game.MUSIC_TYPE_FULL);
                FontUtils.printText(game.batch,
                        game.fontTrackType,
                        game.layout,
                        game.usePhases ? Color.BLACK : Color.WHITE,
                        game.MUSIC_TYPE_FULL,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_SPLIT : {
                game.layout.setText(game.fontTrackType, game.MUSIC_TYPE_SPLIT);
                FontUtils.printText(game.batch,
                        game.fontTrackType,
                        game.layout,
                        game.usePhases ? Color.WHITE : Color.BLACK,
                        game.MUSIC_TYPE_SPLIT,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_POINTILLISM : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_POINTILLISM));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_POINTILLISM ? Color.WHITE : Color.BLACK,
                        game.musicTitleList.get(game.MUSIC_POINTILLISM),
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_LINEAGE : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_LINEAGE));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_LINEAGE ? Color.WHITE : Color.BLACK,
                        game.musicTitleList.get(game.MUSIC_LINEAGE),
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_TRI_THE_WALTZ : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_TRI_THE_WALTZ));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_TRI_THE_WALTZ ? Color.WHITE : Color.BLACK,
                        game.musicTitleList.get(game.MUSIC_TRI_THE_WALTZ),
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_SQUARED_OFF : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_SQUARED_OFF));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_SQUARED_OFF ? Color.WHITE : Color.BLACK,
                        game.musicTitleList.get(game.MUSIC_SQUARED_OFF),
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_PENT_UP : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_PENT_UP));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_PENT_UP ? Color.WHITE : Color.BLACK,
                        game.maxBase > 4 ? game.musicTitleList.get(game.MUSIC_PENT_UP) : QUESTION_MARK,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_HEXIDECIBEL : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_HEXIDECIBEL));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_HEXIDECIBEL ? Color.WHITE : Color.BLACK,
                        game.maxBase > 5 ? game.musicTitleList.get(game.MUSIC_HEXIDECIBEL) : QUESTION_MARK,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_INTERSEPTOR : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_INTERSEPTOR));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_INTERSEPTOR ? Color.WHITE : Color.BLACK,
                        game.maxBase > 6 ? game.musicTitleList.get(game.MUSIC_INTERSEPTOR) : QUESTION_MARK,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_ROCTOPUS : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_ROCTOPUS));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_ROCTOPUS ? Color.WHITE : Color.BLACK,
                        game.maxBase > 7 ? game.musicTitleList.get(game.MUSIC_ROCTOPUS) : QUESTION_MARK,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_MUSIC_NONPLUSSED : {
                game.layout.setText(game.fontTrackName, game.musicTitleList.get(game.MUSIC_NONPLUSSED));
                FontUtils.printText(game.batch,
                        game.fontTrackName,
                        game.layout,
                        game.track == game.MUSIC_NONPLUSSED ? Color.WHITE : Color.BLACK,
                        game.maxBase > 8 ? game.musicTitleList.get(game.MUSIC_NONPLUSSED) : QUESTION_MARK,
                        centerX,
                        centerY + game.layout.height / 6,
                        0,
                        1);
                break;
            }
            case BUTTON_DIFFICULTY : {
                drawDifficultyText();
                break;
            }
            case BUTTON_DIFFICULTY_DIAL : {
                break;
            }
            case BUTTON_DIFFICULTY_CHEVRON_DOWN : {
                break;
            }
            case BUTTON_DIFFICULTY_CHEVRON_UP : {
                break;
            }
            case BUTTON_TIME : {
                drawTimeText();
                break;
            }
            case BUTTON_TIME_CLOCK : {
                break;
            }
            case BUTTON_TIME_CHEVRON_DOWN : {
                break;
            }
            case BUTTON_TIME_CHEVRON_UP : {
                break;
            }
            case BUTTON_VOLUME : {
                drawVolumeText();
                break;
            }
            case BUTTON_VOLUME_WAVES : {
                break;
            }
            case BUTTON_VOLUME_CHEVRON_DOWN : {
                break;
            }
            case BUTTON_VOLUME_CHEVRON_UP : {
                break;
            }
        }
    }

    public void drawBattleSymbol() {
        game.shapeRendererFilled.setColor(containedColor);
        game.shapeRendererFilled.rectLine(x,
                    y,
                    x + width,
                    y + height,
                    game.partitionSize);
        game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
        game.draw.drawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
    }

    public void drawTimeBattleSymbol() {
        game.shapeRendererFilled.setColor(containedColor);
        game.shapeRendererFilled.rectLine(x,
                y,
                x + width,
                y + height,
                game.partitionSize);
        game.draw.drawClock(x + (width / 4),
                    y + (height / 2) + (height / 6),
                    radius / 2,
                    containedColor,
                    containerColor);
        game.draw.drawClock(x + (width / 2) + (width / 4),
                    y + (height / 2) - (height / 6),
                    radius / 2,
                    containedColor,
                    containerColor);
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

    public void drawVolumeText() {
        FontUtils.printText(game.batch,
                game.fontVolume,
                game.layout,
                containedColor,
                String.valueOf(game.volume),
                x + ((3 * width) / 5),
                y + (height / 2),
                0,
                1);
    }

    public void touchDown(Vector3 touchPoint) {
        if(isFeedbackButton()) {
            boolean xInBounds = touchPoint.x > x
                    && touchPoint.x < x + width;
            boolean yInBounds = touchPoint.y > y
                    && touchPoint.y < y + height;

            if (xInBounds && yInBounds) {
                containerColor = Color.WHITE;
            }
        }
    }

    public void touchUp(Vector3 touchPoint) {
        containerColor = originalContainerColor;

        boolean xInBounds = touchPoint.x > x
                && touchPoint.x < x + width;
        boolean yInBounds = touchPoint.y > y
                && touchPoint.y < y + height;

        if(xInBounds && yInBounds) {
            if(isFeedbackButton()) {
                touched = true;
            } else {
                endTransitionBehavior();
            }
        }
    }

    //The boolean returned here is used by the calling Screen object to determine whether or not to call its dispose method
    public boolean endTransitionBehavior() {
            switch(buttonType) {
                case BUTTON_TYPE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeScreen(game, containerColor));
                    return true;
                }
                case BUTTON_OPTIONS: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuOptionsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_QUIT: {
                    System.exit(0);
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MainMenuScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerSquirgleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerTimeAttackScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerTimeBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerTranceScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeScreen(game, containerColor));
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
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
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
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
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
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
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
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.usePhases = false;
                    game.setScreen(new TranceScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE: {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalTimeBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeScreen(game, containerColor));
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
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game, containerColor));
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
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game, containerColor));
                    return true;
                }
                case BUTTON_OPTIONS_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.updateSave(game.SAVE_VOLUME, game.volume);
                    game.setScreen(new MainMenuScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpAdditionScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_CREDITS : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new CreditsScreen(game));
                    return true;
                }
                case BUTTON_HELP_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MainMenuScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_COLOR : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpAdditionColorScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_SQUARE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_PENTAGON : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_HEXAGON : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_SEPTAGON : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_OCTAGON : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_NONAGON : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_COLOR_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpAdditionScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_BASE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpAdditionScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_GENERAL : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsGeneralScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_SQUIRGLE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsSquirgleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_BATTLE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TIME_ATTACK : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsTimeAttackScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsTimeBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TRANCE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsTranceScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_GENERAL_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_SQUIRGLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_BATTLE_EASY : {
                    game.difficulty = Squirgle.DIFFICULTY_EASY;
                    return false;
                }
                case BUTTON_HELP_STATS_BATTLE_MEDIUM : {
                    game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
                    return false;
                }
                case BUTTON_HELP_STATS_BATTLE_HARD : {
                    game.difficulty = Squirgle.DIFFICULTY_HARD;
                    return false;
                }
                case BUTTON_HELP_STATS_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TIME_ATTACK_ONE_MINUTE : {
                    game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_ATTACK_THREE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_ATTACK_FIVE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_ATTACK_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_EASY : {
                    game.difficulty = Squirgle.DIFFICULTY_EASY;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM : {
                    game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_HARD : {
                    game.difficulty = Squirgle.DIFFICULTY_HARD;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_EASY_ONE_MINUTE : {
                    game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_EASY_THREE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_EASY_FIVE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_ONE_MINUTE : {
                    game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_THREE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_MEDIUM_FIVE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_HARD_ONE_MINUTE : {
                    game.timeAttackNumSeconds = Squirgle.ONE_MINUTE;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_HARD_THREE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.THREE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_HARD_FIVE_MINUTES : {
                    game.timeAttackNumSeconds = Squirgle.FIVE_MINUTES;
                    return false;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TRANCE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialSquirgleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialTimeAttackScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE : {
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialTimeBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TRANCE : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.usePhases = false;
                    game.setScreen(new TutorialTranceScreen(game));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_SQUARE : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_NONAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.volume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_MUSIC : {
                    return false;
                }
                case BUTTON_MUSIC_FULL : {
                    game.usePhases = false;
                    return false;
                }
                case BUTTON_MUSIC_SPLIT : {
                    game.usePhases = true;
                    return false;
                }
                case BUTTON_MUSIC_POINTILLISM : {
                    game.track = game.MUSIC_POINTILLISM;
                    return false;
                }
                case BUTTON_MUSIC_LINEAGE : {
                    game.track = game.MUSIC_LINEAGE;
                    return false;
                }
                case BUTTON_MUSIC_TRI_THE_WALTZ : {
                    game.track = game.MUSIC_TRI_THE_WALTZ;
                    return false;
                }
                case BUTTON_MUSIC_SQUARED_OFF : {
                    game.track = game.MUSIC_SQUARED_OFF;
                    return false;
                }
                case BUTTON_MUSIC_PENT_UP : {
                    if(game.maxBase > 4) {
                        game.track = game.MUSIC_PENT_UP;
                    }
                    return false;
                }
                case BUTTON_MUSIC_HEXIDECIBEL : {
                    if(game.maxBase > 5) {
                        game.track = game.MUSIC_HEXIDECIBEL;
                    }
                    return false;
                }
                case BUTTON_MUSIC_INTERSEPTOR : {
                    if(game.maxBase > 6) {
                        game.track = game.MUSIC_INTERSEPTOR;
                    }
                    return false;
                }
                case BUTTON_MUSIC_ROCTOPUS : {
                    if(game.maxBase > 7) {
                        game.track = game.MUSIC_ROCTOPUS;
                    }
                    return false;
                }
                case BUTTON_MUSIC_NONPLUSSED : {
                    if(game.maxBase > 8) {
                        game.track = game.MUSIC_NONPLUSSED;
                    }
                    return false;
                }
                case BUTTON_DIFFICULTY : {
                    return false;
                }
                case BUTTON_DIFFICULTY_DIAL : {
                    return false;
                }
                case BUTTON_DIFFICULTY_CHEVRON_DOWN : {
                    if(game.difficulty == Squirgle.DIFFICULTY_EASY) {
                        game.difficulty = Squirgle.DIFFICULTY_HARD;
                    } else if(game.difficulty == Squirgle.DIFFICULTY_MEDIUM) {
                        game.difficulty = Squirgle.DIFFICULTY_EASY;
                    } else {
                        game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
                    }
                    return false;
                }
                case BUTTON_DIFFICULTY_CHEVRON_UP : {
                    if(game.difficulty == Squirgle.DIFFICULTY_EASY) {
                        game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
                    } else if(game.difficulty == Squirgle.DIFFICULTY_MEDIUM) {
                        game.difficulty = Squirgle.DIFFICULTY_HARD;
                    } else {
                        game.difficulty = Squirgle.DIFFICULTY_EASY;
                    }
                    return false;
                }
                case BUTTON_TIME : {
                    return false;
                }
                case BUTTON_TIME_CLOCK : {
                    return false;
                }
                case BUTTON_TIME_CHEVRON_DOWN : {
                    if(game.timeAttackNumSeconds == game.ONE_MINUTE) {
                        game.timeAttackNumSeconds = game.FIVE_MINUTES;
                    } else if(game.timeAttackNumSeconds == game.THREE_MINUTES) {
                        game.timeAttackNumSeconds = game.ONE_MINUTE;
                    } else if(game.timeAttackNumSeconds == game.FIVE_MINUTES) {
                        game.timeAttackNumSeconds = game.THREE_MINUTES;
                    }
                    return false;
                }
                case BUTTON_TIME_CHEVRON_UP : {
                    if(game.timeAttackNumSeconds == game.ONE_MINUTE) {
                        game.timeAttackNumSeconds = game.THREE_MINUTES;
                    } else if(game.timeAttackNumSeconds == game.THREE_MINUTES) {
                        game.timeAttackNumSeconds = game.FIVE_MINUTES;
                    } else if(game.timeAttackNumSeconds == game.FIVE_MINUTES) {
                        game.timeAttackNumSeconds = game.ONE_MINUTE;
                    }
                    return false;
                }
                case BUTTON_VOLUME : {
                    return false;
                }
                case BUTTON_VOLUME_WAVES : {
                    return false;
                }
                case BUTTON_VOLUME_CHEVRON_DOWN : {
                    if(game.volume > 0) {
                        game.volume -= 1;
                    } else {
                        game.volume = 10;
                    }
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).setVolume((float) (game.volume / 10.0));
                    game.disconfirmSound.play((float) (game.volume / 10.0));
                    return false;
                }
                case BUTTON_VOLUME_CHEVRON_UP : {
                    if(game.volume < 10) {
                        game.volume += 1;
                    } else {
                        game.volume = 0;
                    }
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).setVolume((float) (game.volume / 10.0));
                    game.confirmSound.play((float) (game.volume / 10.0));
                    return false;
                }
            }
        return false;
    }

    //TODO: Keep updating this method as needed
    public boolean isFeedbackButton() {
        return buttonType != BUTTON_MUSIC
                && buttonType != BUTTON_DIFFICULTY
                && buttonType != BUTTON_TIME
                && buttonType != BUTTON_VOLUME
                && !isMusicTypeButton()
                && !isMusicNameButton()
                && buttonType != BUTTON_DIFFICULTY_DIAL
                && buttonType != BUTTON_DIFFICULTY_CHEVRON_DOWN
                && buttonType != BUTTON_DIFFICULTY_CHEVRON_UP
                && buttonType != BUTTON_TIME_CLOCK
                && buttonType != BUTTON_TIME_CHEVRON_DOWN
                && buttonType != BUTTON_TIME_CHEVRON_UP
                && buttonType != BUTTON_VOLUME_WAVES
                && buttonType != BUTTON_VOLUME_CHEVRON_DOWN
                && buttonType != BUTTON_VOLUME_CHEVRON_UP;
    }

    public boolean isMusicTypeButton() {
        return buttonType == BUTTON_MUSIC_FULL
                || buttonType == BUTTON_MUSIC_SPLIT;
    }

    public boolean isMusicNameButton() {
        return buttonType == BUTTON_MUSIC_POINTILLISM
                || buttonType == BUTTON_MUSIC_LINEAGE
                || buttonType == BUTTON_MUSIC_TRI_THE_WALTZ
                || buttonType == BUTTON_MUSIC_SQUARED_OFF
                || buttonType == BUTTON_MUSIC_PENT_UP
                || buttonType == BUTTON_MUSIC_HEXIDECIBEL
                || buttonType == BUTTON_MUSIC_INTERSEPTOR
                || buttonType == BUTTON_MUSIC_ROCTOPUS
                || buttonType == BUTTON_MUSIC_NONPLUSSED;
    }

    public void drawTransitionCircles(Screen screen) {
        for(Shape circle : transitionCirclesList) {
            game.draw.drawPoint(circle.getCoordinates().x, circle.getCoordinates().y, circle.getRadius(), circle.getColor());
            if (touched) {
                circle.setRadius(circle.getRadius() + transitionIncrement);
                if (circle.getRadius() > transitionThreshold) {
                    if (endTransitionBehavior()) {
                        screen.dispose();
                        break;
                    }
                }
            }
        }
    }

    public void transitionSquirgleColors() {
        ColorUtils.transitionColor(squirglePrompt);
        ColorUtils.transitionColor(squirgleShapeList.get(0));
        ColorUtils.transitionColor(squirgleShapeList.get(1));
    }
}
