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
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY = 41;
    public static final int BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK = 42;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE = 43;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE = 44;
    public static final int BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK = 45;
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
    public static final int BUTTON_FX_VOLUME = 165;
    public static final int BUTTON_FX_VOLUME_WAVES = 166;
    public static final int BUTTON_FX_VOLUME_CHEVRON_DOWN = 167;
    public static final int BUTTON_FX_VOLUME_CHEVRON_UP = 168;
    public static final int BUTTON_WIPE_DATA = 169;
    public static final int BUTTON_HARDCORE = 170;
    public static final int BUTTON_HARDCORE_SKULL = 171;
    public static final int BUTTON_HARDCORE_CHEVRON_DOWN = 172;
    public static final int BUTTON_HARDCORE_CHEVRON_UP = 173;
    public static final int BUTTON_PLAY = 174;
    public static final int BUTTON_SQUARE = 175;
    public static final int BUTTON_PENTAGON = 176;
    public static final int BUTTON_HEXAGON = 177;
    public static final int BUTTON_SEPTAGON = 178;
    public static final int BUTTON_OCTAGON = 179;
    public static final int BUTTON_NONAGON = 180;
    public static final int BUTTON_BASE_SELECT_BACK = 181;
    public static final int BUTTON_PRE_GAME_BACK = 182;

    public static final String SINGLE_PLAYER_SYMBOL_STRING = "1P";
    public static final String MULTIPLAYER_SYMBOL_STRING = "2P";

    public static final String QUESTION_MARK = "?";
    private final static String HIGHEST_SCORE = "HIGHEST SCORE: ";
    private final static String ONE_MINUTE = "1m";
    private final static String THREE_MINUTES = "3m";
    private final static String FIVE_MINUTES = "5m";
    private final static String MINUTES = "m";

    private static final String PLAY_STRING = "PLAY";
    private static final String OPTIONS_STRING = "OPTIONS";
    private static final String HELP_STRING = "HELP";
    private static final String QUIT_STRING = "QUIT";
    private static final String SINGLE_PLAYER_STRING = "SINGLE PLAYER";
    private static final String MULTIPLAYER_STRING = "MULTIPLAYER";
    private static final String BACK_STRING = "BACK";
    private static final String SQUIRGLE_STRING = "SQUIRGLE";
    private static final String BATTLE_STRING = "BATTLE";
    private static final String TIME_ATTACK_STRING = "TIME ATTACK";
    private static final String TIME_BATTLE_STRING = "TIME BATTLE";
    private static final String TRANCE_STRING = "TRANCE";
    private static final String MUSIC_STRING = "MUSIC";
    private static final String SQUARE_STRING = "SQUARE";
    private static final String PENTAGON_STRING = "PENTAGON";
    private static final String HEXAGON_STRING = "HEXAGON";
    private static final String SEPTAGON_STRING = "SEPTAGON";
    private static final String OCTAGON_STRING = "OCTAGON";
    private static final String NONAGON_STRING = "NONAGON";
    private static final String DIFFICULTY_STRING = "DIFFICULTY";
    private static final String TIME_LIMIT_STRING = "TIME LIMIT";
    private static final String VOLUME_STRING = "MUSIC VOLUME";
    private static final String FX_VOLUME_STRING = "FX VOLUME";
    private static final String ERASE_DATA_STRING = "ERASE DATA";
    private static final String ADDITION_TABLE_STRING = "ADDITION TABLES";
    private static final String STATS_STRING = "STATS";
    private static final String TUTORIAL_STRING = "TUTORIALS";
    private static final String CREDITS_STRING = "CREDITS";
    private static final String GENERAL_STRING = "GENERAL";
    private static final String HARDCORE_STRING = "HARDCORE MODE";

    public float x;
    public float y;
    public float width;
    public float height;
    public float radius;
    public float centerX;
    public float centerY;
    public float symbolX;
    public float symbolY;
    public float symbolRadius;
    public float symbolTextOverlap;
    public float squirgleHeightOffset;
    public float transitionThreshold;
    public float transitionIncrement;
    public float textOpacity;
    public int buttonType;
    public boolean touched;
    public Color containerColor;
    public Color containedColor;
    public Color originalContainerColor;
    public Color squareColor;
    public Color circleColor;
    public Color triangleColor;
    public List<Shape> transitionCirclesList;
    public List<Shape> squirgleShapeList;
    public List<Shape> squirgleShapeListBattleOne;
    public List<Shape> squirgleShapeListBattleTwo;
    public Shape squirglePrompt;
    public Shape squirglePromptBattleOne;
    public Shape squirglePromptBattleTwo;
    public Squirgle game;

    public Button() {
        game.layout.setText(game.fontButton, PLAY_STRING); //Setting this so I have access to the button's text's height

        this.game = new Squirgle();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.radius = 0;
        this.centerX = x + (width / 2);
        this.centerY = y + (height / 2);
        this.symbolTextOverlap = (y + game.layout.height) - (centerY - radius);
        this.symbolX = centerX;
        this.symbolY = symbolTextOverlap > 0 ? centerY + (symbolTextOverlap / 2) : centerY;
        this.symbolRadius = symbolTextOverlap > 0 ? radius - (symbolTextOverlap / 2) : radius;
        this.squirgleHeightOffset = symbolRadius / 4;
        this.transitionThreshold = (float) Math.sqrt(Math.pow(game.camera.viewportWidth, 2) + Math.pow(game.camera.viewportHeight, 2));
        this.transitionIncrement = game.widthOrHeightSmaller / 5;
        this.textOpacity = 0;
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
                symbolRadius,
                triangleColor,
                null,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(symbolX, symbolY - squirgleHeightOffset));
        this.squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                symbolRadius / 2,
                triangleColor,
                null,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + (width / 4), y + game.layout.height + ((3 * (height - game.layout.height)) / 4) - squirgleHeightOffset));
        this.squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                symbolRadius / 2,
                triangleColor,
                null,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + ((3 * width) / 4), y + game.layout.height + ((height - game.layout.height) / 4)));
    }

    public Button(float x, float y, float width, float height, int buttonType, Color containerColor, Color containedColor, Squirgle game) {
        game.layout.setText(game.fontButton, PLAY_STRING); //Setting this so I have access to the button's text's height

        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = width > height ? height / 2 : width / 2;
        this.centerX = x + (width / 2);
        this.centerY = y + (height / 2);
        this.symbolTextOverlap = (y + game.layout.height) - (centerY - radius);
        this.symbolX = centerX;
        this.symbolY = symbolTextOverlap > 0 ? centerY + (symbolTextOverlap / 2) : centerY;
        this.symbolRadius = symbolTextOverlap > 0 ? radius - (symbolTextOverlap / 2) : radius;
        this.squirgleHeightOffset = symbolRadius / 4;
        this.transitionThreshold = (float) Math.sqrt(Math.pow(game.camera.viewportWidth, 2) + Math.pow(game.camera.viewportHeight, 2));
        this.transitionIncrement = game.widthOrHeightSmaller / 5;
        this.textOpacity = 0;
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
                symbolRadius,
                triangleColor,
                null,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(symbolX, symbolY - squirgleHeightOffset));
        this.squirglePromptBattleOne = new Shape(Shape.TRIANGLE,
                symbolRadius / 2,
                triangleColor,
                null,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + (width / 4), y + game.layout.height + ((3 * (height - game.layout.height)) / 4) - squirgleHeightOffset));
        this.squirglePromptBattleTwo = new Shape(Shape.TRIANGLE,
                symbolRadius / 2,
                triangleColor,
                null,
                (symbolRadius / 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(x + ((3 * width) / 4), y + game.layout.height + ((height - game.layout.height) / 4)));
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
        switch(buttonType) {
            case BUTTON_TYPE : {
                game.draw.drawPlayButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_OPTIONS : {
                game.draw.drawWrench(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP : {
                game.draw.drawQuestionMark(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_QUIT : {
                game.draw.drawStopSymbol(centerX,
                        symbolY,
                        symbolRadius,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER : {
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL : {
                break;
            }
            case BUTTON_TYPE_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE : {
                game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
                game.draw.orientAndDrawShapes(false, squirgleShapeList, squirglePrompt, false);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE : {
                drawBattleSymbol();
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK : {
                game.draw.drawClock(centerX,
                        symbolY,
                        symbolRadius,
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
                        symbolY,
                        symbolRadius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY : {
                game.draw.drawPlayButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
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
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_OPTIONS_BACK : {
                game.draw.drawBackButton(x + (width / 2),
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION : {
                game.layout.setText(game.fontButton, ADDITION_TABLE_STRING);
                game.draw.drawPlus(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS : {
                game.draw.drawModulo(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL : {
                game.draw.drawTutorialSymbol(centerX,
                        symbolY,
                        symbolRadius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_CREDITS : {
                game.draw.drawCreditsSymbol(centerX,
                        symbolY,
                        symbolRadius,
                        containedColor);
                break;
            }
            case BUTTON_HELP_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR : {
                game.draw.drawColorWheel(centerX,
                        symbolY,
                        symbolRadius,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_SQUARE : {
                game.draw.drawSquare(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_NONAGON : {
                game.draw.drawNonagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_ADDITION_BASE_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_GENERAL : {
                game.draw.drawSigma(centerX,
                        symbolY,
                        symbolRadius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE : {
                game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
                game.draw.orientAndDrawShapes(false, squirgleShapeList, squirglePrompt, false);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE : {
                drawBattleSymbol();
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK : {
                game.draw.drawClock(centerX,
                        symbolY,
                        symbolRadius,
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
                        symbolY,
                        symbolRadius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_STATS_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_GENERAL_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
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
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
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
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
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
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_STATS_TRANCE_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE : {
                game.draw.drawPrompt(false, squirglePrompt, squirgleShapeList, 0, null, true, false);
                game.draw.orientAndDrawShapes(false, squirgleShapeList, squirglePrompt, false);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE : {
                drawBattleSymbol();
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK : {
                game.draw.drawClock(centerX,
                        symbolY,
                        symbolRadius,
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
                        symbolY,
                        symbolRadius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE : {
                game.draw.drawSquare(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON : {
                game.draw.drawNonagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE : {
                game.draw.drawSquare(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON : {
                game.draw.drawPentagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON : {
                game.draw.drawHexagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON : {
                game.draw.drawSeptagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON : {
                game.draw.drawOctagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON : {
                game.draw.drawNonagon(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK : {
                game.draw.drawBackButton(centerX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_MUSIC : {
                game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (symbolRadius / 4) + ((symbolRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
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
            case BUTTON_FX_VOLUME : {
                break;
            }
            case BUTTON_FX_VOLUME_WAVES : {
                game.draw.drawSoundSymbol(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containerColor);
                break;
            }
            case BUTTON_FX_VOLUME_CHEVRON_DOWN : {
                game.draw.drawChevronLeft(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_FX_VOLUME_CHEVRON_UP : {
                game.draw.drawChevronRight(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_WIPE_DATA : {
                game.draw.drawWipeDataSymbol(centerX,
                        symbolY,
                        symbolRadius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HARDCORE : {
                break;
            }
            case BUTTON_HARDCORE_SKULL : {
                game.draw.drawSkull(centerX,
                        centerY,
                        radius,
                        containedColor,
                        containerColor);
                break;
            }
            case BUTTON_HARDCORE_CHEVRON_DOWN : {
                game.draw.drawChevronLeft(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_HARDCORE_CHEVRON_UP : {
                game.draw.drawChevronRight(centerX,
                        centerY,
                        radius,
                        radius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_PLAY : {
                game.draw.drawPlayButton(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_SQUARE : {
                game.draw.drawSquare(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_PENTAGON : {
                game.draw.drawPentagon(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_HEXAGON : {
                game.draw.drawHexagon(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_SEPTAGON : {
                game.draw.drawSeptagon(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_OCTAGON : {
                game.draw.drawOctagon(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_NONAGON : {
                game.draw.drawNonagon(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        0,
                        containedColor);
                break;
            }
            case BUTTON_BASE_SELECT_BACK : {
                game.draw.drawBackButton(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                        containedColor);
                break;
            }
            case BUTTON_PRE_GAME_BACK : {
                game.draw.drawBackButton(symbolX,
                        symbolY,
                        symbolRadius,
                        symbolRadius / Draw.LINE_WIDTH_DIVISOR,
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
                game.layout.setText(game.fontButton, PLAY_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PLAY_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_OPTIONS : {
                game.layout.setText(game.fontButton, OPTIONS_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OPTIONS_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP : {
                game.layout.setText(game.fontButton, HELP_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HELP_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_QUIT : {
                game.layout.setText(game.fontButton, QUIT_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        QUIT_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER : {
                FontUtils.printText(game.batch,
                        game.fontNumPlayers,
                        game.layout,
                        Color.BLACK,
                        SINGLE_PLAYER_SYMBOL_STRING,
                        symbolX,
                        symbolY,
                        0,
                        1);
                game.layout.setText(game.fontButton, SINGLE_PLAYER_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SINGLE_PLAYER_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL : {
                FontUtils.printText(game.batch,
                        game.fontNumPlayers,
                        game.layout,
                        Color.BLACK,
                        MULTIPLAYER_SYMBOL_STRING,
                        symbolX,
                        symbolY,
                        0,
                        1);
                game.layout.setText(game.fontButton, MULTIPLAYER_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        MULTIPLAYER_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE : {
                game.layout.setText(game.fontButton, SQUIRGLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUIRGLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BATTLE : {
                game.layout.setText(game.fontButton, BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK : {
                game.layout.setText(game.fontButton, TIME_ATTACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_ATTACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE : {
                game.layout.setText(game.fontButton, TIME_BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE : {
                game.layout.setText(game.fontButton, TRANCE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TRANCE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY : {
                game.layout.setText(game.fontButton, PLAY_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PLAY_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE : {
                game.layout.setText(game.fontButton, BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE : {
                game.layout.setText(game.fontButton, TIME_BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_OPTIONS_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION : {
                game.layout.setText(game.fontButton, ADDITION_TABLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        ADDITION_TABLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS : {
                game.layout.setText(game.fontButton, STATS_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        STATS_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL : {
                game.layout.setText(game.fontButton, TUTORIAL_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TUTORIAL_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_CREDITS : {
                game.layout.setText(game.fontButton, CREDITS_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        CREDITS_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR : {
                game.layout.setText(game.fontButton, SQUIRGLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUIRGLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_SQUARE : {
                game.layout.setText(game.fontButton, SQUARE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUARE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_PENTAGON : {
                game.layout.setText(game.fontButton, PENTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PENTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_HEXAGON : {
                game.layout.setText(game.fontButton, HEXAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HEXAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_SEPTAGON : {
                game.layout.setText(game.fontButton, SEPTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SEPTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_OCTAGON : {
                game.layout.setText(game.fontButton, OCTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OCTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_NONAGON : {
                game.layout.setText(game.fontButton, NONAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        NONAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_COLOR_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_ADDITION_BASE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_GENERAL : {
                game.layout.setText(game.fontButton, GENERAL_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        GENERAL_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE : {
                game.layout.setText(game.fontButton, SQUIRGLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUIRGLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE : {
                game.layout.setText(game.fontButton, BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK : {
                game.layout.setText(game.fontButton, TIME_ATTACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_ATTACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE : {
                game.layout.setText(game.fontButton, TIME_BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_TRANCE : {
                game.layout.setText(game.fontButton, TRANCE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TRANCE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_GENERAL_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_SQUIRGLE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_BATTLE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_TIME_ATTACK_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_TIME_BATTLE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_STATS_TRANCE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE : {
                game.layout.setText(game.fontButton, SQUIRGLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUIRGLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE : {
                game.layout.setText(game.fontButton, BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK : {
                game.layout.setText(game.fontButton, TIME_ATTACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_ATTACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE : {
                game.layout.setText(game.fontButton, TIME_BATTLE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_BATTLE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TRANCE : {
                game.layout.setText(game.fontButton, TRANCE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TRANCE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE : {
                game.layout.setText(game.fontButton, SQUARE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUARE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON : {
                game.layout.setText(game.fontButton, PENTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PENTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON : {
                game.layout.setText(game.fontButton, HEXAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HEXAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON : {
                game.layout.setText(game.fontButton, SEPTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SEPTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON : {
                game.layout.setText(game.fontButton, OCTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OCTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON : {
                game.layout.setText(game.fontButton, NONAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        NONAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SQUARE : {
                game.layout.setText(game.fontButton, SQUARE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUARE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON : {
                game.layout.setText(game.fontButton, PENTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PENTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON : {
                game.layout.setText(game.fontButton, HEXAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HEXAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON : {
                game.layout.setText(game.fontButton, SEPTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SEPTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON : {
                game.layout.setText(game.fontButton, OCTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OCTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_NONAGON : {
                game.layout.setText(game.fontButton, NONAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        NONAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_BATTLE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE : {
                game.layout.setText(game.fontButton, SQUARE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUARE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON : {
                game.layout.setText(game.fontButton, PENTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PENTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON : {
                game.layout.setText(game.fontButton, HEXAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HEXAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON : {
                game.layout.setText(game.fontButton, SEPTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SEPTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON : {
                game.layout.setText(game.fontButton, OCTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OCTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON : {
                game.layout.setText(game.fontButton, NONAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        NONAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE : {
                game.layout.setText(game.fontButton, SQUARE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUARE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON : {
                game.layout.setText(game.fontButton, PENTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PENTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON : {
                game.layout.setText(game.fontButton, HEXAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HEXAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON : {
                game.layout.setText(game.fontButton, SEPTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SEPTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON : {
                game.layout.setText(game.fontButton, OCTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OCTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON : {
                game.layout.setText(game.fontButton, NONAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        NONAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_MUSIC : {
                game.layout.setText(game.fontButton, MUSIC_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        MUSIC_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
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
                        textOpacity);
                break;
            }
            case BUTTON_DIFFICULTY : {
                game.layout.setText(game.fontButton, DIFFICULTY_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        DIFFICULTY_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
                game.layout.setText(game.fontButton, TIME_LIMIT_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        TIME_LIMIT_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
                game.layout.setText(game.fontButton, VOLUME_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        VOLUME_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
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
            case BUTTON_FX_VOLUME : {
                game.layout.setText(game.fontButton, FX_VOLUME_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        FX_VOLUME_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                drawFxVolumeText();
                break;
            }
            case BUTTON_FX_VOLUME_WAVES : {
                break;
            }
            case BUTTON_FX_VOLUME_CHEVRON_DOWN : {
                break;
            }
            case BUTTON_FX_VOLUME_CHEVRON_UP : {
                break;
            }
            case BUTTON_WIPE_DATA : {
                game.layout.setText(game.fontButton, ERASE_DATA_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        ERASE_DATA_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HARDCORE : {
                game.layout.setText(game.fontButton, HARDCORE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HARDCORE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                drawHardcoreText();
                break;
            }
            case BUTTON_HARDCORE_SKULL : {
                break;
            }
            case BUTTON_HARDCORE_CHEVRON_DOWN : {
                break;
            }
            case BUTTON_HARDCORE_CHEVRON_UP : {
                break;
            }
            case BUTTON_PLAY : {
                game.layout.setText(game.fontButton, PLAY_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PLAY_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_SQUARE : {
                game.layout.setText(game.fontButton, SQUARE_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SQUARE_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_PENTAGON : {
                game.layout.setText(game.fontButton, PENTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        PENTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_HEXAGON : {
                game.layout.setText(game.fontButton, HEXAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        HEXAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_SEPTAGON : {
                game.layout.setText(game.fontButton, SEPTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        SEPTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_OCTAGON : {
                game.layout.setText(game.fontButton, OCTAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        OCTAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_NONAGON : {
                game.layout.setText(game.fontButton, NONAGON_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        NONAGON_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_BASE_SELECT_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
            case BUTTON_PRE_GAME_BACK : {
                game.layout.setText(game.fontButton, BACK_STRING);
                FontUtils.printText(game.batch,
                        game.fontButton,
                        game.layout,
                        Color.BLACK,
                        BACK_STRING,
                        centerX,
                        y + ((2.7f * game.layout.height) / 4),
                        0,
                        textOpacity);
                break;
            }
        }
        if(textOpacity < 1) {
            textOpacity += 0.1;
        }
    }

    public void drawBattleSymbol() {
        game.layout.setText(game.fontButton, PLAY_STRING);
        game.shapeRendererFilled.setColor(containedColor);
        game.shapeRendererFilled.rectLine(x - (game.partitionSize / 2),
                    y + game.layout.height + (game.partitionSize / 2),
                    x + width,
                    y + height,
                    game.partitionSize);
        game.draw.drawPrompt(false, squirglePromptBattleOne, squirgleShapeListBattleOne, 0, null, true, false);
        game.draw.orientAndDrawShapes(false, squirgleShapeListBattleOne, squirglePromptBattleOne, false);
        game.draw.drawPrompt(false, squirglePromptBattleTwo, squirgleShapeListBattleTwo, 0, null, true, false);
        game.draw.orientAndDrawShapes(false, squirgleShapeListBattleTwo, squirglePromptBattleTwo, false);
    }

    public void drawTimeBattleSymbol() {
        game.layout.setText(game.fontButton, PLAY_STRING);
        game.shapeRendererFilled.setColor(containedColor);
        game.shapeRendererFilled.rectLine(x - (game.partitionSize / 2),
                y + game.layout.height + (game.partitionSize / 2),
                x + width,
                y + height,
                game.partitionSize);
        game.draw.drawClock(x + (width / 4),
                    y + game.layout.height + ((height - game.layout.height) / 2) + ((height - game.layout.height) / 6),
                    symbolRadius / 2,
                    containedColor,
                    containerColor);
        game.draw.drawClock(x + (width / 2) + (width / 4),
                    y + game.layout.height + ((height - game.layout.height) / 2) - ((height - game.layout.height) / 6),
                    symbolRadius / 2,
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
                game.fontOptions,
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
                game.fontOptions,
                game.layout,
                Color.BLACK,
                (game.timeAttackNumSeconds / Squirgle.ONE_MINUTE) + MINUTES,
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
                game.fontOptions,
                game.layout,
                containedColor,
                String.valueOf(game.volume),
                x + ((3 * width) / 5),
                y + (height / 2),
                0,
                1);
    }

    public void drawFxVolumeText() {
        FontUtils.printText(game.batch,
                game.fontOptions,
                game.layout,
                containedColor,
                String.valueOf(game.fxVolume),
                x + ((3 * width) / 5),
                y + (height / 2),
                0,
                1);
    }

    public void drawHardcoreText() {
        FontUtils.printText(game.batch,
                game.fontOptions,
                game.layout,
                containedColor,
                game.hardcore ? game.HARDCORE_ENABLED : game.HARDCORE_DISABLED,
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
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuTypeScreen(game, containerColor));
                    return true;
                }
                case BUTTON_OPTIONS: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuOptionsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_QUIT: {
                    System.exit(0);
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuTypeMultiplayerLocalScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MainMenuScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_SQUIRGLE: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_SQUIRGLE;
                    game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BATTLE: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_BATTLE;
                    game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_ATTACK: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_TIME_ATTACK;
                    game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TIME_BATTLE: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_TIME_BATTLE;
                    game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_TRANCE;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuTypeScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_PLAY: {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.usePhases = false;
                    game.setScreen(new TranceScreen(game));
                    return true;
                }
                case BUTTON_TYPE_SINGLE_PLAYER_TRANCE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BATTLE: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_BATTLE_LOCAL;
                    game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_TIME_BATTLE: {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.gameplayType = Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL;
                    game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_TYPE_MULTIPLAYER_LOCAL_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuTypeScreen(game, containerColor));
                    return true;

                }
                case BUTTON_OPTIONS_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.updateSave(game.SAVE_VOLUME, game.volume);
                    game.updateSave(game.SAVE_FX_VOLUME, game.fxVolume);
                    game.updateSave(game.SAVE_HARDCORE, game.hardcore);
                    game.setScreen(new MainMenuScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpAdditionScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_CREDITS : {
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new CreditsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MainMenuScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_COLOR : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpAdditionColorScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_SQUARE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 4;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_PENTAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 5;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_HEXAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 6;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_SEPTAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 7;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_OCTAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 8;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_NONAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 9;
                    game.setScreen(new MenuHelpAdditionBaseScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_COLOR_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpAdditionScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_ADDITION_BASE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpAdditionScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_GENERAL : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsGeneralScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_SQUIRGLE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsSquirgleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_BATTLE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TIME_ATTACK : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsTimeAttackScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TIME_BATTLE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsTimeBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TRANCE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsTranceScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_GENERAL_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_SQUIRGLE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
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
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
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
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
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
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_STATS_TRANCE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpStatsScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialSquirgleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialTimeAttackScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialTimeBattleScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TRANCE : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.usePhases = false;
                    game.setScreen(new TutorialTranceScreen(game));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_SQUARE : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_PENTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_HEXAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_SEPTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_OCTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_NONAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_SQUIRGLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_SQUIRGLE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_SQUARE : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_PENTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_HEXAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_SEPTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_OCTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_NONAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SQUARE : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_PENTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_HEXAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_SEPTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_OCTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_NONAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_ATTACK));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_ATTACK_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SQUARE : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 4;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_PENTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 5;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_HEXAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 6;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_SEPTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 7;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_OCTAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 8;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_NONAGON : {
                    game.usePhases = false;
                    game.track = Squirgle.MUSIC_LINEAGE;
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 9;
                    game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
                    game.updateSave(game.SAVE_TRACK, game.track);
                    game.setScreen(new TutorialScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE));
                    return true;
                }
                case BUTTON_HELP_TUTORIAL_TIME_BATTLE_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    game.setScreen(new MenuHelpTutorialScreen(game, containerColor));
                    return true;
                }
                case BUTTON_MUSIC : {
                    return false;
                }
                case BUTTON_MUSIC_FULL : {
                    if(game.usePhases) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.usePhases = false;
                    return false;
                }
                case BUTTON_MUSIC_SPLIT : {
                    if(!game.usePhases) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.usePhases = true;
                    return false;
                }
                case BUTTON_MUSIC_POINTILLISM : {
                    if(game.track != game.MUSIC_POINTILLISM) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.track = game.MUSIC_POINTILLISM;
                    return false;
                }
                case BUTTON_MUSIC_LINEAGE : {
                    if(game.track != game.MUSIC_LINEAGE) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.track = game.MUSIC_LINEAGE;
                    return false;
                }
                case BUTTON_MUSIC_TRI_THE_WALTZ : {
                    if(game.track != game.MUSIC_TRI_THE_WALTZ) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.track = game.MUSIC_TRI_THE_WALTZ;
                    return false;
                }
                case BUTTON_MUSIC_SQUARED_OFF : {
                    if(game.track != game.MUSIC_SQUARED_OFF) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.track = game.MUSIC_SQUARED_OFF;
                    return false;
                }
                case BUTTON_MUSIC_PENT_UP : {
                    if(game.maxBase > 4) {
                        if(game.track != game.MUSIC_PENT_UP) {
                            game.confirmSound.play((float) (game.fxVolume / 10.0));
                        }
                        game.track = game.MUSIC_PENT_UP;
                    }
                    return false;
                }
                case BUTTON_MUSIC_HEXIDECIBEL : {
                    if(game.maxBase > 5) {
                        if(game.track != game.MUSIC_HEXIDECIBEL) {
                            game.confirmSound.play((float) (game.fxVolume / 10.0));
                        }
                        game.track = game.MUSIC_HEXIDECIBEL;
                    }
                    return false;
                }
                case BUTTON_MUSIC_INTERSEPTOR : {
                    if(game.maxBase > 6) {
                        if(game.track != game.MUSIC_INTERSEPTOR) {
                            game.confirmSound.play((float) (game.fxVolume / 10.0));
                        }
                        game.track = game.MUSIC_INTERSEPTOR;
                    }
                    return false;
                }
                case BUTTON_MUSIC_ROCTOPUS : {
                    if(game.maxBase > 7) {
                        if(game.track != game.MUSIC_ROCTOPUS) {
                            game.confirmSound.play((float) (game.fxVolume / 10.0));
                        }
                        game.track = game.MUSIC_ROCTOPUS;
                    }
                    return false;
                }
                case BUTTON_MUSIC_NONPLUSSED : {
                    if(game.maxBase > 8) {
                        if(game.track != game.MUSIC_NONPLUSSED) {
                            game.confirmSound.play((float) (game.fxVolume / 10.0));
                        }
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
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                        game.difficulty = Squirgle.DIFFICULTY_HARD;
                    } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                        game.difficulty = Squirgle.DIFFICULTY_EASY;
                    } else {
                        game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
                    }
                    return false;
                }
                case BUTTON_DIFFICULTY_CHEVRON_UP : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                        game.difficulty = Squirgle.DIFFICULTY_MEDIUM;
                    } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
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
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
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
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
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
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    return false;
                }
                case BUTTON_VOLUME_CHEVRON_UP : {
                    if(game.volume < 10) {
                        game.volume += 1;
                    } else {
                        game.volume = 0;
                    }
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).setVolume((float) (game.volume / 10.0));
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    return false;
                }
                case BUTTON_FX_VOLUME : {
                    return false;
                }
                case BUTTON_FX_VOLUME_WAVES : {
                    return false;
                }
                case BUTTON_FX_VOLUME_CHEVRON_DOWN : {
                    if(game.fxVolume > 0) {
                        game.fxVolume -= 1;
                    } else {
                        game.fxVolume = 10;
                    }
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    return false;
                }
                case BUTTON_FX_VOLUME_CHEVRON_UP : {
                    if(game.fxVolume < 10) {
                        game.fxVolume += 1;
                    } else {
                        game.fxVolume = 0;
                    }
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    return false;
                }
                case BUTTON_WIPE_DATA : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.showWipeDataPrompt = !game.showWipeDataPrompt;
                    return false;
                }
                case BUTTON_HARDCORE : {
                    return false;
                }
                case BUTTON_HARDCORE_SKULL : {
                    return false;
                }
                case BUTTON_HARDCORE_CHEVRON_DOWN : {
                    if(game.hardcore) {
                        game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    } else {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.hardcore = !game.hardcore;
                    return false;
                }
                case BUTTON_HARDCORE_CHEVRON_UP : {
                    if(game.hardcore) {
                        game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    } else {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                    }
                    game.hardcore = !game.hardcore;
                    return false;
                }
                case BUTTON_PLAY : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
                    if(game.gameplayType == Squirgle.GAMEPLAY_TRANCE) {
                        game.setScreen(new TranceScreen(game));
                    } else {
                        game.setScreen(new GameplayScreen(game, game.gameplayType));
                    }
                    return true;
                }
                case BUTTON_SQUARE : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 4;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_PENTAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 5;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_HEXAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 6;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_SEPTAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 7;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_OCTAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 8;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_NONAGON : {
                    game.confirmSound.play((float) (game.fxVolume / 10.0));
                    game.base = 9;
                    game.setScreen(new PreGameScreen(game, containerColor, game.gameplayType));
                    return true;
                }
                case BUTTON_BASE_SELECT_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    if(game.gameplayType == Squirgle.GAMEPLAY_SQUIRGLE
                            || game.gameplayType == Squirgle.GAMEPLAY_BATTLE
                            || game.gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK
                            || game.gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE
                            || game.gameplayType == Squirgle.GAMEPLAY_TRANCE) {
                        game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    } else {
                        game.setScreen(new MenuTypeMultiplayerLocalScreen(game, containerColor));
                    }
                    return true;
                }
                case BUTTON_PRE_GAME_BACK : {
                    game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                    if(game.gameplayType == Squirgle.GAMEPLAY_TRANCE) {
                        game.setScreen(new MenuTypeSinglePlayerScreen(game, containerColor));
                    } else {
                        game.setScreen(new BaseSelectScreen(game, containerColor, game.gameplayType));
                    }
                    return true;
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
                && buttonType != BUTTON_FX_VOLUME
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
                && buttonType != BUTTON_VOLUME_CHEVRON_UP
                && buttonType != BUTTON_FX_VOLUME_WAVES
                && buttonType != BUTTON_FX_VOLUME_CHEVRON_DOWN
                && buttonType != BUTTON_FX_VOLUME_CHEVRON_UP
                && buttonType != BUTTON_WIPE_DATA
                && buttonType != BUTTON_HARDCORE
                && buttonType != BUTTON_HARDCORE_SKULL
                && buttonType != BUTTON_HARDCORE_CHEVRON_DOWN
                && buttonType != BUTTON_HARDCORE_CHEVRON_UP;
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
