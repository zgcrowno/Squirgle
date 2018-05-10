package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.GameplayScreen;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.InputUtils;
import com.screenlooker.squirgle.util.SoundUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TutorialScreen implements Screen, InputProcessor {
    final Squirgle game;
    public final int gameplayType;

    public static float INIT_PROMPT_RADIUS;
    public static float BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_X;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_X;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MAX_Y;
    public static float BACKGROUND_COLOR_SHAPE_LIST_MIN_Y;
    public static float BACKGROUND_COLOR_SHAPE_LIST_WIDTH;
    public static float BACKGROUND_COLOR_SHAPE_LIST_HEIGHT;
    public static float COLOR_LIST_SPEED_ADDITIVE;
    public static float INPUT_RADIUS;
    public static float TARGET_RADIUS;
    public static float PAUSE_INPUT_WIDTH;
    public static float PAUSE_INPUT_HEIGHT;
    public static float FONT_SCORE_SIZE_DIVISOR;
    public static float FONT_TARGET_SIZE_DIVISOR;
    public static float FONT_SQUIRGLE_SIZE_DIVISOR;
    public static float FONT_TUTORIAL_HELP_SIZE_MULTIPLIER;
    public static Vector2 INPUT_POINT_SPAWN;
    public static Vector2 INPUT_LINE_SPAWN;
    public static Vector2 INPUT_TRIANGLE_SPAWN;
    public static Vector2 INPUT_SQUARE_SPAWN;
    public static Vector2 INPUT_PENTAGON_SPAWN;
    public static Vector2 INPUT_HEXAGON_SPAWN;
    public static Vector2 INPUT_SEPTAGON_SPAWN;
    public static Vector2 INPUT_OCTAGON_SPAWN;
    public static Vector2 INPUT_NONAGON_SPAWN;
    public static Vector2 INPUT_POINT_SPAWN_P1;
    public static Vector2 INPUT_LINE_SPAWN_P1;
    public static Vector2 INPUT_TRIANGLE_SPAWN_P1;
    public static Vector2 INPUT_SQUARE_SPAWN_P1;
    public static Vector2 INPUT_PENTAGON_SPAWN_P1;
    public static Vector2 INPUT_HEXAGON_SPAWN_P1;
    public static Vector2 INPUT_SEPTAGON_SPAWN_P1;
    public static Vector2 INPUT_OCTAGON_SPAWN_P1;
    public static Vector2 INPUT_NONAGON_SPAWN_P1;
    public static Vector2 INPUT_POINT_SPAWN_P2;
    public static Vector2 INPUT_LINE_SPAWN_P2;
    public static Vector2 INPUT_TRIANGLE_SPAWN_P2;
    public static Vector2 INPUT_SQUARE_SPAWN_P2;
    public static Vector2 INPUT_PENTAGON_SPAWN_P2;
    public static Vector2 INPUT_HEXAGON_SPAWN_P2;
    public static Vector2 INPUT_SEPTAGON_SPAWN_P2;
    public static Vector2 INPUT_OCTAGON_SPAWN_P2;
    public static Vector2 INPUT_NONAGON_SPAWN_P2;
    public static Vector2 INPUT_PLAY_SPAWN;
    public static Vector2 INPUT_HOME_SPAWN;
    public static Vector2 INPUT_EXIT_SPAWN;

    private final static int PAUSE_BACK = 0;
    private final static int PAUSE_QUIT = 1;

    private final static int END_LINE_WIDTH_INCREASE = 2;
    private final static int NUM_MUSIC_PHASES = 3;
    private final static int NUM_TIMELINES = 3;
    private final static int SCORE_ANGLE = -45;
    private final static int ONE_THOUSAND = 1000;
    private final static int TWO_SECONDS = 2;
    private final static int TEN_SECONDS = 10;
    private final static int TARGET_ARC_SPEED = 5;
    private final static int COLOR_SPEED_ADDITIVE = 20;
    private final static int EQUATION_WIDTH_DIVISOR = 60;
    private final static int SHAPE_SIZE_LIMIT_MULTIPLIER = 15;
    private final static int MAX_MULTIPLIER = 5;
    private final static int ONE_SHAPE_AGO = 2;
    private final static int TWO_SHAPES_AGO = 4;
    private final static int THIRTY_DEGREES = 30;
    private final static int TWO_HUNDRED_AND_SEVENTY_DEGREES = 270;
    private final static int THREE_HUNDRED_AND_THIRTY_DEGREES = 330;

    private final static float FONT_MULTIPLIER_INPUT = 1.39f;
    private final static float SCORE_DIVISOR = 3.16f;
    private final static float TARGET_RADIUS_DIVISOR = 2.43f;
    private final static float MULTIPLIER_X_DIVISOR = 2.68f;
    private final static float MULTIPLIER_Y_DIVISOR = 1.25f;
    private final static float PROMPT_INCREASE_ADDITIVE = .0001f;
    private final static float SQUIRGLE_OPACITY_DECREMENT = .03f;

    /*
    -Screen displaying prompt shape and pause and help buttons
    -User presses next button in help window
    */
    public final static int PHASE_ONE = 0;

    /*
    -All shape inputs are now displayed at bottom of screen
    -User presses next button in help window
     */
    public final static int PHASE_TWO = 1;

    /*
    -Target is now displayed in upper left corner
    -User presses next button in help window
    */
    public final static int PHASE_THREE = 2;

    /*
    -Score/multiplier/burst meter are now displayed in upper right-hand corner of screen
    -User presses next button in help window
    */
    public final static int PHASE_FOUR = 3;

    /*
    -Colors are now displayed @ left of screen
    -Timelines are now displayed @ left of screen
    -Colors are now sifted through, affecting background, prompt shape & nested shapes
    -Prompt shape (& nested shapes) also begins to grow now, but it will stop doing so once it reaches 2/3 of its max size
    -The game remains in this phase until the user presses next button in help window
    */
    public final static int PHASE_FIVE = 4;

    /*
    -User simply plays w/ full mechanics until game over
    -Tutorial is over, & user chooses to replay, navigate to menu or quit
    */
    public final static int PHASE_SIX = 5;

    public final static String P1 = "P1";
    public final static String P2 = "P2";
    public final static int MAX_SATURATION = 15;
    private final static String X = "X";
    private final static String COLON = ":";
    private final static String SQUIRGLE = "SQUIRGLE";

    private float promptIncrease;
    private float equationWidth;
    private float equationWidthP1;
    private float equationWidthP2;
    private float targetArcStart;
    private float targetArcStartP1;
    private float targetArcStartP2;
    private float squirgleOpacity;
    private float squirgleOpacityP1;
    private float squirgleOpacityP2;
    private float helpInputGirth;
    private Shape promptShape;
    private Shape promptShapeP1;
    private Shape promptShapeP2;
    private Shape dummyPromptForTimelines;
    private Shape lastShapeTouched;
    private Shape lastShapeTouchedP1;
    private Shape lastShapeTouchedP2;
    private Shape lastPromptShape;
    private Shape lastPromptShapeP1;
    private Shape lastPromptShapeP2;
    private Shape outsideTargetShape;
    private Shape outsideTargetShapeP1;
    private Shape outsideTargetShapeP2;
    private List<Shape> priorShapeList;
    private List<Shape> priorShapeListP1;
    private List<Shape> priorShapeListP2;
    private List<Shape> targetShapeList;
    private List<Shape> targetShapeListP1;
    private List<Shape> targetShapeListP2;
    private List<Shape> backgroundColorShapeList;
    private Shape backgroundColorShape;
    private Shape currentTargetShape;
    private Shape currentTargetShapeP1;
    private Shape currentTargetShapeP2;
    private Shape lastTargetShape;
    private Shape lastTargetShapeP1;
    private Shape lastTargetShapeP2;
    private int targetShapesMatched;
    private int targetShapesMatchedP1;
    private int targetShapesMatchedP2;
    private Vector3 touchPoint;
    boolean pointTouched;
    boolean lineTouched;
    boolean triangleTouched;
    boolean squareTouched;
    boolean pentagonTouched;
    boolean hexagonTouched;
    boolean septagonTouched;
    boolean octagonTouched;
    boolean nonagonTouched;
    boolean pointTouchedP1;
    boolean lineTouchedP1;
    boolean triangleTouchedP1;
    boolean squareTouchedP1;
    boolean pentagonTouchedP1;
    boolean hexagonTouchedP1;
    boolean septagonTouchedP1;
    boolean octagonTouchedP1;
    boolean nonagonTouchedP1;
    boolean pointTouchedP2;
    boolean lineTouchedP2;
    boolean triangleTouchedP2;
    boolean squareTouchedP2;
    boolean pentagonTouchedP2;
    boolean hexagonTouchedP2;
    boolean septagonTouchedP2;
    boolean octagonTouchedP2;
    boolean nonagonTouchedP2;
    boolean helpTouched;
    boolean helpChevronDownTouched;
    boolean helpChevronUpTouched;
    boolean helpNextTouched;
    boolean playTouched;
    boolean homeTouched;
    boolean exitTouched;
    boolean pauseTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    boolean inputTouchedGameplay;
    boolean inputTouchedGameplayP1;
    boolean inputTouchedGameplayP2;
    boolean inputTouchedHelp;
    boolean inputTouchedResults;
    private Color targetArcColor;
    private Color clearColor;
    private int phase;
    private int currentHelpTextIndex;
    private int score;
    private int scoreP1;
    private int scoreP2;
    private boolean gameOver;
    private boolean showResults;
    private boolean paused;
    private int multiplier;
    private int multiplierP1;
    private int multiplierP2;
    public long startTime;
    public long endTime;
    private long lastSpeedIncreaseTime;
    public long pauseStartTime;
    public long timePaused;
    public long opponentTime;
    private int destructionIndex;
    private float firstPriorShapePreviousX;
    private float firstPriorShapePreviousXP1;
    private float firstPriorShapePreviousXP2;
    private Color resultsColor;
    Shape primaryShape;
    Shape primaryShapeP1;
    Shape primaryShapeP2;
    float primaryShapeThreshold;
    boolean primaryShapeAtThreshold;
    boolean primaryShapeAtThresholdP1;
    boolean primaryShapeAtThresholdP2;
    private int saturationP1;
    private int saturationP2;
    private boolean splitScreen;
    private boolean useSaturation;
    public boolean blackAndWhite;
    private boolean admitsOfSquirgle;
    private boolean multiplayer;
    private boolean local;
    private boolean online;
    private boolean helpTextVisible;
    public Label.LabelStyle helpLabelStyle;
    public Label helpLabel;
    private Stage stage;
    private Color veilColor;
    private float veilOpacity;

    private String squirglePhaseOneTextOne;
    private String squirglePhaseOneTextTwo;
    private String squirglePhaseOneTextThree;
    private String squirglePhaseOneTextFour;
    private String squirglePhaseOneTextFive;
    private String squirglePhaseOneTextSix;
    private String squirglePhaseOneTextSeven;
    private String squirglePhaseTwoTextOne;
    private String squirglePhaseTwoTextTwo;
    private String squirglePhaseTwoTextThree;
    private String squirglePhaseTwoTextFour;
    private String squirglePhaseTwoTextFive;
    private String squirglePhaseTwoTextSix;
    private String squirglePhaseTwoTextSeven;
    private String squirglePhaseTwoTextEight;
    private String squirglePhaseTwoTextNine;
    private String squirglePhaseTwoTextTen;
    private String squirglePhaseTwoTextEleven;
    private String squirglePhaseTwoTextTwelve;
    private String squirglePhaseTwoTextThirteen;
    private String squirglePhaseTwoTextFourteen;
    private String squirglePhaseTwoTextFifteen;
    private String squirglePhaseTwoTextSixteen;
    private String squirglePhaseTwoTextSeventeen;
    private String squirglePhaseTwoTextEighteen;
    private String squirglePhaseTwoTextNineteen;
    private String squirglePhaseTwoTextTwenty;
    private String squirglePhaseTwoTextTwentyOne;
    private String squirglePhaseTwoTextTwentyTwo;
    private String squirglePhaseTwoTextTwentyThree;
    private String squirglePhaseTwoTextTwentyFour;
    private String squirglePhaseThreeTextOne;
    private String squirglePhaseThreeTextTwo;
    private String squirglePhaseThreeTextThree;
    private String squirglePhaseThreeTextFour;
    private String squirglePhaseThreeTextFive;
    private String squirglePhaseThreeTextSix;
    private String squirglePhaseThreeTextSeven;
    private String squirglePhaseThreeTextEight;
    private String squirglePhaseThreeTextNine;
    private String squirglePhaseThreeTextTen;
    private String squirglePhaseThreeTextEleven;
    private String squirglePhaseThreeTextTwelve;
    private String squirglePhaseThreeTextThirteen;
    private String squirglePhaseThreeTextFourteen;
    private String squirglePhaseThreeTextFifteen;
    private String squirglePhaseThreeTextSixteen;
    private String squirglePhaseFourTextOne;
    private String squirglePhaseFourTextTwo;
    private String squirglePhaseFourTextThree;
    private String squirglePhaseFourTextFour;
    private String squirglePhaseFourTextFive;
    private String squirglePhaseFourTextSix;
    private String squirglePhaseFourTextSeven;
    private String squirglePhaseFourTextEight;
    private String squirglePhaseFourTextNine;
    private String squirglePhaseFourTextTen;
    private String squirglePhaseFourTextEleven;
    private String squirglePhaseFourTextTwelve;
    private String squirglePhaseFourTextThirteen;
    private String squirglePhaseFiveTextOne;
    private String squirglePhaseFiveTextTwo;
    private String squirglePhaseFiveTextThree;
    private String squirglePhaseFiveTextFour;
    private String squirglePhaseFiveTextFive;
    private String squirglePhaseFiveTextSix;
    private String squirglePhaseFiveTextSeven;
    private String squirglePhaseFiveTextEight;
    private String squirglePhaseFiveTextNine;
    private String squirglePhaseFiveTextTen;
    private String squirglePhaseFiveTextEleven;
    private String squirglePhaseFiveTextTwelve;
    private String squirglePhaseFiveTextThirteen;

    private String battlePhaseOneTextOne;
    private String battlePhaseOneTextTwo;
    private String battlePhaseOneTextThree;
    private String battlePhaseOneTextFour;
    private String battlePhaseOneTextFive;
    private String battlePhaseOneTextSix;
    private String battlePhaseOneTextSeven;
    private String battlePhaseOneTextEight;
    private String battlePhaseOneTextNine;
    private String battlePhaseOneTextTen;
    private String battlePhaseOneTextEleven;
    private String battlePhaseTwoTextOne;
    private String battlePhaseTwoTextTwo;
    private String battlePhaseTwoTextThree;
    private String battlePhaseTwoTextFour;
    private String battlePhaseTwoTextFive;
    private String battlePhaseTwoTextSix;
    private String battlePhaseTwoTextSeven;
    private String battlePhaseTwoTextEight;
    private String battlePhaseTwoTextNine;
    private String battlePhaseTwoTextTen;
    private String battlePhaseTwoTextEleven;
    private String battlePhaseTwoTextTwelve;
    private String battlePhaseTwoTextThirteen;
    private String battlePhaseTwoTextFourteen;
    private String battlePhaseTwoTextFifteen;
    private String battlePhaseTwoTextSixteen;
    private String battlePhaseTwoTextSeventeen;
    private String battlePhaseTwoTextEighteen;
    private String battlePhaseTwoTextNineteen;
    private String battlePhaseTwoTextTwenty;
    private String battlePhaseTwoTextTwentyOne;
    private String battlePhaseTwoTextTwentyTwo;
    private String battlePhaseTwoTextTwentyThree;
    private String battlePhaseTwoTextTwentyFour;
    private String battlePhaseThreeTextOne;
    private String battlePhaseThreeTextTwo;
    private String battlePhaseThreeTextThree;
    private String battlePhaseThreeTextFour;
    private String battlePhaseThreeTextFive;
    private String battlePhaseThreeTextSix;
    private String battlePhaseThreeTextSeven;
    private String battlePhaseThreeTextEight;
    private String battlePhaseThreeTextNine;
    private String battlePhaseThreeTextTen;
    private String battlePhaseThreeTextEleven;
    private String battlePhaseThreeTextTwelve;
    private String battlePhaseThreeTextThirteen;
    private String battlePhaseThreeTextFourteen;
    private String battlePhaseThreeTextFifteen;
    private String battlePhaseThreeTextSixteen;
    private String battlePhaseFourTextOne;
    private String battlePhaseFourTextTwo;
    private String battlePhaseFourTextThree;
    private String battlePhaseFourTextFour;
    private String battlePhaseFourTextFive;
    private String battlePhaseFourTextSix;
    private String battlePhaseFourTextSeven;
    private String battlePhaseFourTextEight;
    private String battlePhaseFourTextNine;
    private String battlePhaseFourTextTen;
    private String battlePhaseFourTextEleven;
    private String battlePhaseFourTextTwelve;
    private String battlePhaseFourTextThirteen;
    private String battlePhaseFourTextFourteen;
    private String battlePhaseFourTextFifteen;
    private String battlePhaseFourTextSixteen;
    private String battlePhaseFourTextSeventeen;
    private String battlePhaseFiveTextOne;
    private String battlePhaseFiveTextTwo;
    private String battlePhaseFiveTextThree;
    private String battlePhaseFiveTextFour;
    private String battlePhaseFiveTextFive;
    private String battlePhaseFiveTextSix;
    private String battlePhaseFiveTextSeven;
    private String battlePhaseFiveTextEight;
    private String battlePhaseFiveTextNine;
    private String battlePhaseFiveTextTen;
    private String battlePhaseFiveTextEleven;
    private String battlePhaseFiveTextTwelve;
    private String battlePhaseFiveTextThirteen;

    private String timeAttackPhaseOneTextOne;
    private String timeAttackPhaseOneTextTwo;
    private String timeAttackPhaseOneTextThree;
    private String timeAttackPhaseOneTextFour;
    private String timeAttackPhaseOneTextFive;
    private String timeAttackPhaseOneTextSix;
    private String timeAttackPhaseOneTextSeven;
    private String timeAttackPhaseTwoTextOne;
    private String timeAttackPhaseTwoTextTwo;
    private String timeAttackPhaseTwoTextThree;
    private String timeAttackPhaseTwoTextFour;
    private String timeAttackPhaseTwoTextFive;
    private String timeAttackPhaseTwoTextSix;
    private String timeAttackPhaseTwoTextSeven;
    private String timeAttackPhaseTwoTextEight;
    private String timeAttackPhaseTwoTextNine;
    private String timeAttackPhaseTwoTextTen;
    private String timeAttackPhaseTwoTextEleven;
    private String timeAttackPhaseTwoTextTwelve;
    private String timeAttackPhaseTwoTextThirteen;
    private String timeAttackPhaseTwoTextFourteen;
    private String timeAttackPhaseTwoTextFifteen;
    private String timeAttackPhaseTwoTextSixteen;
    private String timeAttackPhaseTwoTextSeventeen;
    private String timeAttackPhaseTwoTextEighteen;
    private String timeAttackPhaseTwoTextNineteen;
    private String timeAttackPhaseTwoTextTwenty;
    private String timeAttackPhaseTwoTextTwentyOne;
    private String timeAttackPhaseTwoTextTwentyTwo;
    private String timeAttackPhaseTwoTextTwentyThree;
    private String timeAttackPhaseTwoTextTwentyFour;
    private String timeAttackPhaseThreeTextOne;
    private String timeAttackPhaseThreeTextTwo;
    private String timeAttackPhaseThreeTextThree;
    private String timeAttackPhaseThreeTextFour;
    private String timeAttackPhaseThreeTextFive;
    private String timeAttackPhaseThreeTextSix;
    private String timeAttackPhaseThreeTextSeven;
    private String timeAttackPhaseThreeTextEight;
    private String timeAttackPhaseThreeTextNine;
    private String timeAttackPhaseThreeTextTen;
    private String timeAttackPhaseThreeTextEleven;
    private String timeAttackPhaseThreeTextTwelve;
    private String timeAttackPhaseThreeTextThirteen;
    private String timeAttackPhaseThreeTextFourteen;
    private String timeAttackPhaseThreeTextFifteen;
    private String timeAttackPhaseThreeTextSixteen;
    private String timeAttackPhaseFourTextOne;
    private String timeAttackPhaseFourTextTwo;
    private String timeAttackPhaseFourTextThree;
    private String timeAttackPhaseFourTextFour;
    private String timeAttackPhaseFourTextFive;
    private String timeAttackPhaseFourTextSix;
    private String timeAttackPhaseFourTextSeven;
    private String timeAttackPhaseFourTextEight;
    private String timeAttackPhaseFourTextNine;
    private String timeAttackPhaseFourTextTen;
    private String timeAttackPhaseFourTextEleven;
    private String timeAttackPhaseFourTextTwelve;
    private String timeAttackPhaseFourTextThirteen;
    private String timeAttackPhaseFiveTextOne;
    private String timeAttackPhaseFiveTextTwo;
    private String timeAttackPhaseFiveTextThree;
    private String timeAttackPhaseFiveTextFour;
    private String timeAttackPhaseFiveTextFive;
    private String timeAttackPhaseFiveTextSix;
    private String timeAttackPhaseFiveTextSeven;
    private String timeAttackPhaseFiveTextEight;

    private String timeBattlePhaseOneTextOne;
    private String timeBattlePhaseOneTextTwo;
    private String timeBattlePhaseOneTextThree;
    private String timeBattlePhaseOneTextFour;
    private String timeBattlePhaseOneTextFive;
    private String timeBattlePhaseOneTextSix;
    private String timeBattlePhaseOneTextSeven;
    private String timeBattlePhaseOneTextEight;
    private String timeBattlePhaseOneTextNine;
    private String timeBattlePhaseOneTextTen;
    private String timeBattlePhaseOneTextEleven;
    private String timeBattlePhaseTwoTextOne;
    private String timeBattlePhaseTwoTextTwo;
    private String timeBattlePhaseTwoTextThree;
    private String timeBattlePhaseTwoTextFour;
    private String timeBattlePhaseTwoTextFive;
    private String timeBattlePhaseTwoTextSix;
    private String timeBattlePhaseTwoTextSeven;
    private String timeBattlePhaseTwoTextEight;
    private String timeBattlePhaseTwoTextNine;
    private String timeBattlePhaseTwoTextTen;
    private String timeBattlePhaseTwoTextEleven;
    private String timeBattlePhaseTwoTextTwelve;
    private String timeBattlePhaseTwoTextThirteen;
    private String timeBattlePhaseTwoTextFourteen;
    private String timeBattlePhaseTwoTextFifteen;
    private String timeBattlePhaseTwoTextSixteen;
    private String timeBattlePhaseTwoTextSeventeen;
    private String timeBattlePhaseTwoTextEighteen;
    private String timeBattlePhaseTwoTextNineteen;
    private String timeBattlePhaseTwoTextTwenty;
    private String timeBattlePhaseTwoTextTwentyOne;
    private String timeBattlePhaseTwoTextTwentyTwo;
    private String timeBattlePhaseTwoTextTwentyThree;
    private String timeBattlePhaseTwoTextTwentyFour;
    private String timeBattlePhaseThreeTextOne;
    private String timeBattlePhaseThreeTextTwo;
    private String timeBattlePhaseThreeTextThree;
    private String timeBattlePhaseThreeTextFour;
    private String timeBattlePhaseThreeTextFive;
    private String timeBattlePhaseThreeTextSix;
    private String timeBattlePhaseThreeTextSeven;
    private String timeBattlePhaseThreeTextEight;
    private String timeBattlePhaseThreeTextNine;
    private String timeBattlePhaseThreeTextTen;
    private String timeBattlePhaseThreeTextEleven;
    private String timeBattlePhaseThreeTextTwelve;
    private String timeBattlePhaseThreeTextThirteen;
    private String timeBattlePhaseThreeTextFourteen;
    private String timeBattlePhaseThreeTextFifteen;
    private String timeBattlePhaseThreeTextSixteen;
    private String timeBattlePhaseFourTextOne;
    private String timeBattlePhaseFourTextTwo;
    private String timeBattlePhaseFourTextThree;
    private String timeBattlePhaseFourTextFour;
    private String timeBattlePhaseFourTextFive;
    private String timeBattlePhaseFourTextSix;
    private String timeBattlePhaseFourTextSeven;
    private String timeBattlePhaseFourTextEight;
    private String timeBattlePhaseFourTextNine;
    private String timeBattlePhaseFourTextTen;
    private String timeBattlePhaseFourTextEleven;
    private String timeBattlePhaseFourTextTwelve;
    private String timeBattlePhaseFourTextThirteen;
    private String timeBattlePhaseFourTextFourteen;
    private String timeBattlePhaseFourTextFifteen;
    private String timeBattlePhaseFourTextSixteen;
    private String timeBattlePhaseFiveTextOne;
    private String timeBattlePhaseFiveTextTwo;
    private String timeBattlePhaseFiveTextThree;
    private String timeBattlePhaseFiveTextFour;
    private String timeBattlePhaseFiveTextFive;
    private String timeBattlePhaseFiveTextSix;
    private String timeBattlePhaseFiveTextSeven;
    private String timeBattlePhaseFiveTextEight;

    private List<String> squirgleHelpTextPhaseOneList;
    private List<String> squirgleHelpTextPhaseTwoList;
    private List<String> squirgleHelpTextPhaseThreeList;
    private List<String> squirgleHelpTextPhaseFourList;
    private List<String> squirgleHelpTextPhaseFiveList;

    private List<String> battleHelpTextPhaseOneList;
    private List<String> battleHelpTextPhaseTwoList;
    private List<String> battleHelpTextPhaseThreeList;
    private List<String> battleHelpTextPhaseFourList;
    private List<String> battleHelpTextPhaseFiveList;

    private List<String> timeAttackHelpTextPhaseOneList;
    private List<String> timeAttackHelpTextPhaseTwoList;
    private List<String> timeAttackHelpTextPhaseThreeList;
    private List<String> timeAttackHelpTextPhaseFourList;
    private List<String> timeAttackHelpTextPhaseFiveList;

    private List<String> timeBattleHelpTextPhaseOneList;
    private List<String> timeBattleHelpTextPhaseTwoList;
    private List<String> timeBattleHelpTextPhaseThreeList;
    private List<String> timeBattleHelpTextPhaseFourList;
    private List<String> timeBattleHelpTextPhaseFiveList;

    private Map<Integer, List<String>> squirglePhaseMap;
    private Map<Integer, List<String>> battlePhaseMap;
    private Map<Integer, List<String>> timeAttackPhaseMap;
    private Map<Integer, List<String>> timeBattlePhaseMap;
    private Map<Integer, Map<Integer, List<String>>> helpTextMap;

    public TutorialScreen(final Squirgle game, final int gameplayType) {
        this.game = game;
        this.gameplayType = gameplayType;
        this.splitScreen = !(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE || gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK);
        this.useSaturation = gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_BATTLE_ONLINE;
        this.blackAndWhite = gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_ONLINE;
        this.admitsOfSquirgle = gameplayType == Squirgle.GAMEPLAY_SQUIRGLE || gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_BATTLE_ONLINE;
        this.multiplayer = gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_BATTLE_ONLINE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_ONLINE;
        this.local = gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL;
        this.online = multiplayer && !local;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        setUpNonFinalStaticData();

        //TODO: Eventually set this in render using delta? See maintainSpeed() in TimeAttackScreen
        game.draw.setColorListSpeed(((BACKGROUND_COLOR_SHAPE_LIST_HEIGHT * NUM_TIMELINES) / game.FPS) / game.ONE_MINUTE);

        setUpNonFinalNonStaticData();

        game.setUpFontScore(MathUtils.round(game.camera.viewportWidth / FONT_SCORE_SIZE_DIVISOR));
        game.setUpFontTarget(MathUtils.round(game.camera.viewportWidth / FONT_TARGET_SIZE_DIVISOR));
        game.setUpFontSquirgle(MathUtils.round(game.camera.viewportWidth / FONT_SQUIRGLE_SIZE_DIVISOR));
        game.setUpFontButton(MathUtils.round(PAUSE_INPUT_WIDTH > PAUSE_INPUT_HEIGHT ? (PAUSE_INPUT_HEIGHT / 2) / 2.75f : (PAUSE_INPUT_WIDTH / 2) / 2.75f));

        SoundUtils.setVolume(splitScreen ? dummyPromptForTimelines : promptShape, game);

        playMusic();

        game.stats.incrementNumTimesPlayedMode(gameplayType);
        game.stats.incrementNumTimesPlayedBaseOrTrack(true, game.base, gameplayType);
        game.stats.incrementNumTimesPlayedBaseOrTrack(false, game.track, gameplayType);

        game.playedBefore = true;
        game.updateSave(Squirgle.SAVE_PLAYED_BEFORE, game.playedBefore);
    }

    @Override
    public void render(float delta) {
        setUpGL();

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        primaryShapeP1 = priorShapeListP1.size() > 0 ? priorShapeListP1.get(0) : promptShapeP1;
        primaryShapeP2 = priorShapeListP2.size() > 0 ? priorShapeListP2.get(0) : promptShapeP2;

        primaryShapeThreshold = game.widthOrHeightSmaller * game.draw.THRESHOLD_MULTIPLIER;
        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP1 = primaryShapeP1.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP2 = primaryShapeP2.getRadius() >= primaryShapeThreshold;

        if (!splitScreen) {
            if((phase == PHASE_FIVE && promptShape.getRadius() <= game.thirdOfScreen) || phase >= PHASE_SIX) {
                increasePromptRadius();
            }
        } else {
            if((phase == PHASE_FIVE && dummyPromptForTimelines.getRadius() <= game.thirdOfScreen) || phase >= PHASE_SIX) {
                managePromptRadii();
                increaseDummyPromptRadius();
            }
        }

        managePrimaryShapeLineWidth();

        drawBackgroundColorShape();

        if(!paused) {
            if (!gameOver) {
                if(!splitScreen) {
                    game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                            game.camera.viewportHeight / 2,
                            blackAndWhite ? Color.WHITE : Color.BLACK,
                            (3 * game.widthOrHeightSmaller) / 8,
                            promptShape);
                } else {
                    game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                            (3 * game.camera.viewportHeight) / 4,
                            blackAndWhite ? Color.WHITE : Color.BLACK,
                            game.camera.viewportHeight / 2 > game.camera.viewportWidth ? (3 * game.camera.viewportWidth) / 8 : (3 * (game.camera.viewportHeight / 2)) / 8,
                            promptShapeP2);
                    game.draw.drawPerimeter(game.camera.viewportWidth / 2,
                            game.camera.viewportHeight / 4,
                            blackAndWhite ? Color.WHITE : Color.BLACK,
                            game.camera.viewportHeight / 2 > game.camera.viewportWidth ? (3 * game.camera.viewportWidth) / 8 : (3 * (game.camera.viewportHeight / 2)) / 8,
                            promptShapeP1);
                    game.draw.drawScreenDivisionTutorial(helpTextVisible, blackAndWhite);
                }
                if(phase >= PHASE_THREE) {
                    game.draw.drawTargetSemicirclesTutorial(splitScreen, local);
                }
            }
            if(!splitScreen) {
                game.draw.drawPrompt(false, promptShape, priorShapeList, 0, backgroundColorShape, false, false);
                game.draw.orientShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
            } else if(useSaturation) {
                if(!(gameOver && saturationP1 > saturationP2)) {
                    game.draw.drawPrompt(false, promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false);
                    game.draw.orientShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                }
                if(!(gameOver && saturationP2 >= saturationP1)) {
                    game.draw.drawPrompt(local, promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false);
                    game.draw.orientShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                }
            } else {
                if(!(gameOver && scoreP1 < scoreP2)) {
                    game.draw.drawPrompt(false, promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false);
                    game.draw.orientShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                }
                if(!(gameOver && scoreP2 <= scoreP1)) {
                    game.draw.drawPrompt(local, promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false);
                    game.draw.orientShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                }
            }
        }

        if(phase >= PHASE_FIVE) {
            if (gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                if(phase >= PHASE_SIX) {
                    increaseSpeed();
                }
            } else {
                maintainSpeed();
            }
        }
        if(!splitScreen) {
            decrementSquirgleOpacity();
        } else {
            decrementSquirgleOpacities();
        }
        zoomThroughShapes();

        if(!paused) {
            if(!splitScreen) {
                game.draw.orientAndDrawShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
            } else if(useSaturation) {
                if(!(gameOver && saturationP1 > saturationP2)) {
                    game.draw.orientAndDrawShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                }
                if(!(gameOver && saturationP2 >= saturationP1)) {
                    game.draw.orientAndDrawShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                }
            } else {
                if(!(gameOver && scoreP1 < scoreP2)) {
                    game.draw.orientAndDrawShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                }
                if(!(gameOver && scoreP2 <= scoreP1)) {
                    game.draw.orientAndDrawShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                }
            }
        }

        if(phase >= PHASE_FOUR) {
            if (splitScreen && !multiplayer && !paused) {
                executeOpponentAI();
            }
        }

        drawEquations();

        if(!paused) {
            if (!gameOver) {
                if(phase >= PHASE_THREE) {
                    if (!splitScreen) {
                        game.draw.drawPrompt(false, outsideTargetShape, targetShapeList, targetShapesMatched, backgroundColorShape, false, true);
                        game.draw.orientShapes(false, targetShapeList, outsideTargetShape, false);
                        game.draw.drawShapes(false, targetShapeList);
                    } else {
                        game.draw.drawPrompt(false, outsideTargetShapeP1, targetShapeListP1, targetShapesMatchedP1, backgroundColorShape, false, true);
                        game.draw.orientShapes(false, targetShapeListP1, outsideTargetShapeP1, false);
                        game.draw.drawShapes(false, targetShapeListP1);
                        game.draw.drawPrompt(local, outsideTargetShapeP2, targetShapeListP2, targetShapesMatchedP2, backgroundColorShape, false, true);
                        game.draw.orientShapes(local, targetShapeListP2, outsideTargetShapeP2, false);
                        game.draw.drawShapes(local, targetShapeListP2);
                    }
                    drawTargetArcs();
                }
                if(phase >= PHASE_TWO) {
                    game.draw.drawInputButtonsTutorial(splitScreen, local, backgroundColorShape.getColor(), game);
                }
                if(phase >= PHASE_FIVE) {
                    game.draw.drawBackgroundColorShapeListTutorial(splitScreen, blackAndWhite, local, backgroundColorShapeList, backgroundColorShape, clearColor);
                    game.draw.drawTimelines(splitScreen, local, splitScreen ? dummyPromptForTimelines : promptShape, backgroundColorShapeList);
                }
                if(phase >= PHASE_FOUR) {
                    game.draw.drawScoreTrianglesTutorial(splitScreen, local, backgroundColorShape.getColor());
                }
                if(phase >= PHASE_FOUR) {
                    if (useSaturation) {
                        if (saturationP1 > 0) {
                            game.draw.drawSaturationTrianglesTutorial(P1, local, saturationP1);
                        }
                        if (saturationP2 > 0) {
                            game.draw.drawSaturationTrianglesTutorial(P2, local, saturationP2);
                        }
                        game.draw.drawSaturationIncrementsTutorial(local, backgroundColorShape.getColor());
                    }
                }
                game.draw.drawPauseInputTutorial(splitScreen, local, game);
                if(phase < PHASE_SIX) {
                    game.draw.drawHelpInput(splitScreen);
                }
            }
        }

        destroyOversizedShapes();

        if(!paused) {
            if(!splitScreen) {
                firstPriorShapePreviousX = primaryShape.getCoordinates().x;
            } else {
                firstPriorShapePreviousXP1 = primaryShapeP1.getCoordinates().x;
                firstPriorShapePreviousXP2 = primaryShapeP2.getCoordinates().x;
            }
        }

        drawResultsInputButtons();

        if(paused) {
            //TODO: Configure this for local multiplayer
            drawInputRectangles();
        }

        game.draw.drawVeil(veilColor, veilOpacity);

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        SoundUtils.setVolume(splitScreen ? dummyPromptForTimelines : promptShape, game);

        drawText();

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        showHelpText(); //Doing this here to prevent any other game text from overlapping help box.

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        gameOver();

        stage.draw();

        showHelpTextFooter();

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
        }

        if(!splitScreen) {
            ColorUtils.transitionColor(currentTargetShape);
        } else {
            ColorUtils.transitionColor(currentTargetShapeP1);
            ColorUtils.transitionColor(currentTargetShapeP2);
        }

        if(veilOpacity > 0) {
            veilOpacity -= 0.01;
        }

        InputUtils.keepCursorInBounds(game);
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        pauseStartTime = System.currentTimeMillis();
        clearColor.set(backgroundColorShape.getColor().r,
                backgroundColorShape.getColor().g,
                backgroundColorShape.getColor().b,
                backgroundColorShape.getColor().a);
        paused = true;
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
        paused = false;
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //TODO: Maybe remove gameOver check once I add animations to screen switches
        if (button != Input.Buttons.LEFT || pointer > 0 || gameOver) {
            return false;
        }

        determineTouchedInput(screenX, screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        determineTouchedInput(screenX, screenY);

        if(!splitScreen) {
            handleInput(null);
        } else {
            handleInput(P1);
            if(multiplayer) {
                handleInput(P2);
            }
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        //TODO: Decide if I actually want this functionality
//        if(touchPoint.x > helpLabel.getX()
//                && touchPoint.x < helpLabel.getX() + helpLabel.getWidth()
//                && touchPoint.y > helpLabel.getY()
//                && touchPoint.y < helpLabel.getY() + helpLabel.getHeight()) {
//            helpLabel.setX(touchPoint.x - (helpLabel.getWidth() / 2));
//            helpLabel.setY(touchPoint.y - (helpLabel.getHeight() / 2));
//            return true;
//        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            //TODO: Somehow activate numlock so numpad is always available for use

            determineKeyedInput(keycode);

            if(!splitScreen) {
                handleInput(null);
            } else {
                handleInput(P1);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void drawInputRectangles() {
        game.draw.drawPauseSymbol(game.partitionSize + (PAUSE_INPUT_WIDTH / 2),
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                blackAndWhite ? Color.WHITE : Color.BLACK);
        drawPauseBackInput();
        drawPauseQuitInput();
    }

    public void drawInputRectangle(int placement) {
        switch(placement) {
            case PAUSE_BACK : {
                game.draw.rect(game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT,
                        Color.WHITE);
            }
            case PAUSE_QUIT : {
                game.draw.rect((2 * game.partitionSize) + PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT,
                        Color.WHITE);
            }
        }
    }

    public void drawPauseQuitInput() {
        drawInputRectangle(PAUSE_QUIT);
        game.draw.drawStopSymbol(game.camera.viewportWidth / 2,
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                Color.BLACK);
    }

    public void drawPauseBackInput() {
        drawInputRectangle(PAUSE_BACK);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (PAUSE_INPUT_WIDTH / 2),
                game.camera.viewportHeight / 2,
                PAUSE_INPUT_WIDTH / 2,
                (PAUSE_INPUT_WIDTH / 2) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void playMusic() {
        if(game.usePhases) {
            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
                game.trackMapPhase.get(game.track).get(i).play();
            }
        } else {
            game.trackMapFull.get(game.track).play();
        }
    }

    public void stopMusic() {
        if(game.usePhases) {
            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
                game.trackMapPhase.get(game.track).get(i).stop();
            }
        } else {
            game.trackMapFull.get(game.track).stop();
        }
    }

    public void drawText() {
        if(!paused) {
            if (!gameOver) {
                drawScoreText();

                //drawHandText();

                //drawTargetText();

                drawSquirgleText();

                //TODO: Decide if I actually want to instate this behavior, and if so, make a new BitmapFont object in Squirgle class
                //Input Numbers
                /*for(int i = 1; i <= game.base; i++) {
                    FontUtils.printText(game.batch,
                            game.font,
                            game.layout,
                            Color.WHITE,
                            String.valueOf(i),
                            ((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS)) + (INPUT_RADIUS * FONT_MULTIPLIER_INPUT),
                            (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS) + (INPUT_RADIUS * FONT_MULTIPLIER_INPUT),
                            SCORE_ANGLE,
                            1);
                }*/
            }

            if (showResults) {
                List<Shape> priorShapeListToUse = new ArrayList<Shape>();
                if(!splitScreen) {
                    priorShapeListToUse = priorShapeList;
                } else if(useSaturation) {
                    if(saturationP1 > saturationP2) {
                        priorShapeListToUse = priorShapeListP2;
                    } else {
                        priorShapeListToUse = priorShapeListP1;
                    }
                } else {
                    if(scoreP1 >= scoreP2) {
                        priorShapeListToUse = priorShapeListP1;
                    } else {
                        priorShapeListToUse = priorShapeListP2;
                    }
                }
                if(!blackAndWhite) {
                    if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() == Shape.LINE || priorShapeListToUse.get(0).getShape() == Shape.POINT)) {
                        resultsColor = Color.BLACK;
                    } else {
                        resultsColor = Color.WHITE;
                    }
                } else {
                    if (priorShapeListToUse.size() > 0 && (priorShapeListToUse.get(0).getShape() != Shape.LINE && priorShapeListToUse.get(0).getShape() != Shape.POINT)) {
                        resultsColor = Color.BLACK;
                    } else {
                        resultsColor = Color.WHITE;
                    }
                }
                if(!splitScreen) {
                    FontUtils.printText(game.batch,
                            game.fontScore,
                            game.layout,
                            resultsColor,
                            String.valueOf(score),
                            game.camera.viewportWidth / 2,
                            INPUT_POINT_SPAWN.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2),
                            0,
                            1);
                } else {
                    if(useSaturation) {
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                saturationP1 > saturationP2 ? Squirgle.RESULTS_DEFEAT : saturationP1 < saturationP2 ? Squirgle.RESULTS_VICTORY : Squirgle.RESULTS_TIE,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2),
                                0,
                                1);
                    } else {
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                scoreP1 < scoreP2 ? Squirgle.RESULTS_DEFEAT : scoreP1 > scoreP2 ? Squirgle.RESULTS_VICTORY : Squirgle.RESULTS_TIE,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2),
                                0,
                                1);
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                P1 + COLON + scoreP1,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 4),
                                0,
                                1);
                        FontUtils.printText(game.batch,
                                game.fontScore,
                                game.layout,
                                resultsColor,
                                P2 + COLON + scoreP2,
                                game.camera.viewportWidth / 2,
                                INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS + ((3 * (game.camera.viewportHeight - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS))) / 4),
                                0,
                                1);
                    }
                }
            }
        } else {
            //Pause quit text
            game.layout.setText(game.fontButton, Button.QUIT_STRING);
            FontUtils.printText(game.batch,
                    game.fontButton,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Button.QUIT_STRING,
                    game.camera.viewportWidth / 2,
                    game.partitionSize + ((2.7f * game.layout.height) / 4),
                    0,
                    1);

            //Pause back text
            game.layout.setText(game.fontButton, Button.BACK_STRING);
            FontUtils.printText(game.batch,
                    game.fontButton,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Button.BACK_STRING,
                    (5 * game.camera.viewportWidth) / 6,
                    game.partitionSize + ((2.7f * game.layout.height) / 4),
                    0,
                    1);
        }
    }

    public void drawScoreText() {
        float targetPolypRadius = (TARGET_RADIUS / 4);
        float targetPolypOffset = (float)(Math.sqrt(Math.pow(TARGET_RADIUS, 2) + Math.pow(TARGET_RADIUS / 4, 2)) - TARGET_RADIUS);
        if(!splitScreen) {
            if(phase >= PHASE_FOUR) {
                //Score
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        String.valueOf(score),
                        game.camera.viewportWidth - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                        game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                        SCORE_ANGLE,
                        1);

                //Multiplier
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        X + String.valueOf(multiplier),
                        game.camera.viewportWidth - (TARGET_RADIUS / 4),
                        game.camera.viewportHeight - TARGET_RADIUS + (game.fontScore.getCapHeight() / 5.5f),
                        SCORE_ANGLE,
                        1);

                //Prompt number
                String promptNumber = String.valueOf(promptShape.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumber,
                        game.camera.viewportWidth - TARGET_RADIUS,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        0,
                        1);
            }

            //Target number
            if(phase >= PHASE_THREE) {
                String targetNumber = String.valueOf(currentTargetShape.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShape.getColor(),
                        targetNumber,
                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        0,
                        1);
            }
        } else if(blackAndWhite) {
            //We're in a time battle mode

            if(phase >= PHASE_FOUR) {
                //Scores
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        String.valueOf(scoreP1),
                        game.camera.viewportWidth - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                        SCORE_ANGLE,
                        1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        String.valueOf(scoreP2),
                        local ? (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 2.1f) : game.camera.viewportWidth - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                        local ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 2.1f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 2.1f),
                        local ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                        1);

                //Player designations
                game.layout.setText(game.fontTarget, P1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        P1,
                        game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
                        (game.camera.viewportHeight / 2) - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
                        0,
                        1);
                game.layout.setText(game.fontTarget, P2);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        P2,
                        local ? TARGET_RADIUS + targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
                        local ? (game.camera.viewportHeight / 2) + (2 * targetPolypRadius) + ((3 * game.layout.height) / 4) : game.camera.viewportHeight - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
                        local ? Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES : 0,
                        1);

                //Multipliers
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        X + multiplierP1,
                        game.camera.viewportWidth - (TARGET_RADIUS / 4) - (game.fontScore.getCapHeight() / 4.7f),
                        (game.camera.viewportHeight / 2) - ((3 * TARGET_RADIUS) / 4) - (game.fontScore.getCapHeight() / 4.7f),
                        SCORE_ANGLE,
                        1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        X + multiplierP2,
                        local ? (TARGET_RADIUS / 4) + (game.fontScore.getCapHeight() / 4.7f) : game.camera.viewportWidth - (TARGET_RADIUS / 4) - (game.fontScore.getCapHeight() / 4.7f),
                        local ? (game.camera.viewportHeight / 2) + ((3 * TARGET_RADIUS) / 4) + (game.fontScore.getCapHeight() / 4.7f) : game.camera.viewportHeight - ((3 * TARGET_RADIUS) / 4) - (game.fontScore.getCapHeight() / 4.7f),
                        local ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                        1);

                //Prompt numbers
                String promptNumberP1 = String.valueOf(promptShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP1,
                        game.camera.viewportWidth - TARGET_RADIUS,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        0,
                        1);
                String promptNumberP2 = String.valueOf(promptShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP2,
                        local ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS,
                        local ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        local ? 180 : 0,
                        1);
            }

            if(phase >= PHASE_THREE) {
                //Target numbers
                String targetNumberP1 = String.valueOf(currentTargetShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP1.getColor(),
                        targetNumberP1,
                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        (game.camera.viewportHeight / 2) - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        0,
                        1);
                String targetNumberP2 = String.valueOf(currentTargetShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP2.getColor(),
                        targetNumberP2,
                        local ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius + targetPolypOffset : TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        local ? (game.camera.viewportHeight / 2) + targetPolypRadius - (game.fontScore.getCapHeight() / 5.5f) : game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        local ? 180 : 0,
                        1);
            }
        } else {
            //We're in a non-time battle mode

            if(phase >= PHASE_FOUR) {
                //Player designations
                game.layout.setText(game.fontTarget, P1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        P1,
                        game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
                        (game.camera.viewportHeight / 2) - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
                        0,
                        1);
                game.layout.setText(game.fontTarget, P2);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        Color.WHITE,
                        P2,
                        local ? TARGET_RADIUS + targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
                        local ? (game.camera.viewportHeight / 2) + (2 * targetPolypRadius) + ((3 * game.layout.height) / 4) : game.camera.viewportHeight - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
                        local ? Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES : 0,
                        1);

                //Prompt numbers
                String promptNumberP1 = String.valueOf(promptShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP1,
                        game.camera.viewportWidth - TARGET_RADIUS,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        0,
                        1);
                String promptNumberP2 = String.valueOf(promptShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        backgroundColorShape.getColor(),
                        promptNumberP2,
                        local ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS,
                        local ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
                        local ? 180 : 0,
                        1);
            }

            if(phase >= PHASE_THREE) {
                //Target numbers
                String targetNumberP1 = String.valueOf(currentTargetShapeP1.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP1.getColor(),
                        targetNumberP1,
                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        (game.camera.viewportHeight / 2) - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        0,
                        1);
                String targetNumberP2 = String.valueOf(currentTargetShapeP2.getShape() + 1);
                FontUtils.printText(game.batch,
                        game.fontScore,
                        game.layout,
                        currentTargetShapeP2.getColor(),
                        targetNumberP2,
                        local ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius + targetPolypOffset : TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
                        local ? (game.camera.viewportHeight / 2) + targetPolypRadius - (game.fontScore.getCapHeight() / 5.5f) : game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
                        local ? 180 : 0,
                        1);
            }
        }
    }

    public void drawHandText() {
        game.layout.setText(game.fontTarget, Squirgle.HAND);
        if(!splitScreen) {
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Squirgle.HAND,
                    game.camera.viewportWidth - (game.layout.width / 2),
                    game.camera.viewportHeight - TARGET_RADIUS + ((29 * game.layout.height) / 16),
                    SCORE_ANGLE,
                    1);
        } else {
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Squirgle.HAND,
                    game.camera.viewportWidth - (game.layout.width / 2),
                    (game.camera.viewportHeight / 2) - TARGET_RADIUS + ((29 * game.layout.height) / 16),
                    SCORE_ANGLE,
                    1);
            FontUtils.printText(game.batch,
                    game.fontTarget,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Squirgle.HAND,
                    local ? game.layout.width / 2 : game.camera.viewportWidth - (game.layout.width / 2),
                    local ? (game.camera.viewportHeight / 2) + TARGET_RADIUS - ((29 * game.layout.height) / 16) : game.camera.viewportHeight - TARGET_RADIUS + ((29 * game.layout.height) / 16),
                    local ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                    1);
        }
    }

    public void drawTargetText() {
        float degree = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degreeP1 = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degreeP2 = THIRTY_DEGREES / Squirgle.TARGET.length();
        float degrees = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        float degreesP1 = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        float degreesP2 = TWO_HUNDRED_AND_SEVENTY_DEGREES;
        String shapeText = targetShapeList.get(0).getPrefix() + targetShapeList.get(1).getBridge() + outsideTargetShape.getSuffix();
        String shapeTextP1 = targetShapeListP1.get(0).getPrefix() + targetShapeListP1.get(1).getBridge() + outsideTargetShapeP1.getSuffix();
        String shapeTextP2 = targetShapeListP2.get(0).getPrefix() + targetShapeListP2.get(1).getBridge() + outsideTargetShapeP2.getSuffix();

        //Target Text
        if(!splitScreen) {
            for (int i = 0; i < Squirgle.TARGET.length(); i++, degrees += degree) {
                FontUtils.printText(game.batch,
                        game.fontTarget,
                        game.layout,
                        backgroundColorShape.getColor(),
                        Squirgle.TARGET.substring(i, i + 1),
                        (float) (TARGET_RADIUS * Math.cos(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth())))),
                        (float) (game.camera.viewportHeight + ((2 * game.fontTarget.getCapHeight()) / 3) + (TARGET_RADIUS * Math.sin(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                        degrees - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                        1);
            }
        } else {
            for(int i = 0; i < Squirgle.TARGET.length(); i++, degreesP1 += degreeP1, degreesP2 += degreeP2) {
                FontUtils.printText(game.batch,
                        game.fontTarget,
                        game.layout,
                        backgroundColorShape.getColor(),
                        Squirgle.TARGET.substring(i, i + 1),
                        (float) (TARGET_RADIUS * Math.cos(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth())))),
                        (float) ((game.camera.viewportHeight / 2) + ((2 * game.fontTarget.getCapHeight()) / 3) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                        degreesP1 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                        1);
                if(local) {
                    FontUtils.printText(game.batch,
                            game.fontTarget,
                            game.layout,
                            backgroundColorShape.getColor(),
                            Squirgle.TARGET.substring(i, i + 1),
                            (float) (game.camera.viewportWidth - (TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                            (float) ((game.camera.viewportHeight / 2) - ((2 * game.fontTarget.getCapHeight()) / 3) - (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                            degreesP2 + TWO_HUNDRED_AND_SEVENTY_DEGREES,
                            1);
                } else {
                    FontUtils.printText(game.batch,
                            game.fontTarget,
                            game.layout,
                            backgroundColorShape.getColor(),
                            Squirgle.TARGET.substring(i, i + 1),
                            (float) (TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth())))),
                            (float) (game.camera.viewportHeight + ((2 * game.fontTarget.getCapHeight()) / 3) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / game.fontTarget.getSpaceWidth()))))),
                            degreesP2 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
                            1);
                }
            }
        }

//        //Shape Text
//        degree = THIRTY_DEGREES / shapeText.length();
//        degreeP1 = THIRTY_DEGREES / shapeTextP1.length();
//        degreeP2 = THIRTY_DEGREES / shapeTextP2.length();
//        degrees = THREE_HUNDRED_AND_THIRTY_DEGREES;
//        degreesP1 = THREE_HUNDRED_AND_THIRTY_DEGREES;
//        degreesP2 = THREE_HUNDRED_AND_THIRTY_DEGREES;
//        if(!splitScreen) {
//            for (int i = 0; i < shapeText.length(); i++, degrees += degree) {
//                FontUtils.printText(game.batch,
//                        game.fontTarget,
//                        game.layout,
//                        Color.WHITE,
//                        shapeText.substring(i, i + 1),
//                        (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                        (float) (game.camera.viewportHeight + (TARGET_RADIUS * Math.sin(Math.toRadians(degrees + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                        degrees - TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                        1);
//            }
//        } else {
//            for(int i = 0; i < shapeTextP1.length(); i++, degreesP1 += degreeP1) {
//                FontUtils.printText(game.batch,
//                        game.fontTarget,
//                        game.layout,
//                        Color.WHITE,
//                        shapeTextP1.substring(i, i + 1),
//                        (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                        (float) ((game.camera.viewportHeight / 2) + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP1 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                        degreesP1 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                        1);
//            }
//            for(int i = 0; i < shapeTextP2.length(); i++, degreesP2 += degreeP2) {
//                if(local) {
//                    FontUtils.printText(game.batch,
//                            game.fontTarget,
//                            game.layout,
//                            Color.WHITE,
//                            shapeTextP2.substring(i, i + 1),
//                            (float) (game.camera.viewportWidth - (game.fontTarget.getCapHeight() / 3) - TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                            (float) ((game.camera.viewportHeight / 2) - (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                            degreesP2 + TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                            1);
//                } else {
//                    FontUtils.printText(game.batch,
//                            game.fontTarget,
//                            game.layout,
//                            Color.WHITE,
//                            shapeTextP2.substring(i, i + 1),
//                            (float) ((game.fontTarget.getCapHeight() / 3) + TARGET_RADIUS * Math.cos(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3))))),
//                            (float) (game.camera.viewportHeight + (TARGET_RADIUS * Math.sin(Math.toRadians(degreesP2 + Math.atan(TARGET_RADIUS / (game.fontTarget.getCapHeight() / 3)))))),
//                            degreesP2 - TWO_HUNDRED_AND_SEVENTY_DEGREES,
//                            1);
//                }
//            }
//        }
    }

    public void drawSquirgleText() {
        for(int i = 1; i <= SQUIRGLE.length(); i++) {
            if(!splitScreen) {
                FontUtils.printText(game.batch,
                        game.fontSquirgle,
                        game.layout,
                        Color.WHITE,
                        SQUIRGLE.substring(i - 1, i),
                        (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                        game.camera.viewportHeight / 2,
                        0,
                        squirgleOpacity);
            } else {
                if(local) {
                    FontUtils.printText(game.batch,
                            game.fontSquirgle,
                            game.layout,
                            Color.WHITE,
                            SQUIRGLE.substring(i - 1, i),
                            game.camera.viewportWidth - ((i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS)),
                            (3 * game.camera.viewportHeight) / 4,
                            Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES,
                            squirgleOpacityP2);
                } else {
                    FontUtils.printText(game.batch,
                            game.fontSquirgle,
                            game.layout,
                            Color.WHITE,
                            SQUIRGLE.substring(i - 1, i),
                            (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                            (3 * game.camera.viewportHeight) / 4,
                            0,
                            squirgleOpacityP2);
                }
                FontUtils.printText(game.batch,
                        game.fontSquirgle,
                        game.layout,
                        Color.WHITE,
                        SQUIRGLE.substring(i - 1, i),
                        (i * (game.camera.viewportWidth - ((2 * SQUIRGLE.length()) * INPUT_RADIUS))) / (SQUIRGLE.length() + 1) + ((i + i - 1) * INPUT_RADIUS),
                        game.camera.viewportHeight / 4,
                        0,
                        squirgleOpacityP1);
            }
        }
    }

    //TODO: Configure this for local multiplayer
    public void drawTargetArcs() {
        if(!splitScreen) {
            game.draw.drawArcTutorial(0, game.camera.viewportHeight, targetArcStart, targetArcColor);
            if (targetArcStart > -Draw.NINETY_ONE_DEGREES) {
                targetArcStart -= TARGET_ARC_SPEED;
            }
        } else {
            game.draw.drawArcTutorial(0, game.camera.viewportHeight / 2, targetArcStartP1, targetArcColor);
            if(targetArcStartP1 > -Draw.NINETY_ONE_DEGREES) {
                targetArcStartP1 -= TARGET_ARC_SPEED;
            }
            if(local) {
                game.draw.drawArcTutorial(game.camera.viewportWidth, game.camera.viewportHeight / 2, targetArcStartP2, targetArcColor);
                if(targetArcStartP2 > Draw.NINETY_ONE_DEGREES) {
                    targetArcStartP2 -= TARGET_ARC_SPEED;
                }
            } else {
                game.draw.drawArcTutorial(0, game.camera.viewportHeight, targetArcStartP2, targetArcColor);
                if(targetArcStartP2 > -Draw.NINETY_ONE_DEGREES) {
                    targetArcStartP2 -= TARGET_ARC_SPEED;
                }
            }
        }
    }

    public void increasePromptRadius() {
        if(!paused) {
            promptShape.setRadius(gameOver ? promptShape.getRadius() * promptIncrease : promptShape.getRadius() + (promptIncrease / 2));
        }
    }

    public void managePromptRadii() {
        if(!paused) {
            if(useSaturation) {
                float screenRemainderP1 = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth / 4 : (game.camera.viewportHeight / 8);
                float screenRemainderP2 = game.camera.viewportHeight / 2 > game.camera.viewportWidth ? game.camera.viewportWidth / 4 : (game.camera.viewportHeight / 8);
                promptShapeP1.setRadius(gameOver ? promptShapeP1.getRadius() * promptIncrease : INIT_PROMPT_RADIUS + ((float) saturationP1 / MAX_SATURATION) * screenRemainderP1);
                promptShapeP2.setRadius(gameOver ? promptShapeP2.getRadius() * promptIncrease : INIT_PROMPT_RADIUS + ((float) saturationP2 / MAX_SATURATION) * screenRemainderP2);
            } else {
                promptShapeP1.setRadius(gameOver ? promptShapeP1.getRadius() * promptIncrease : promptShapeP1.getRadius() + (promptIncrease / 4));
                promptShapeP2.setRadius(gameOver ? promptShapeP2.getRadius() * promptIncrease : promptShapeP2.getRadius() + (promptIncrease / 4));
            }
        }
    }

    public void increaseDummyPromptRadius() {
        if(!paused && !gameOver) {
            dummyPromptForTimelines.setRadius(dummyPromptForTimelines.getRadius() + (promptIncrease / 2));
        }
    }

    public void managePrimaryShapeLineWidth() {
        if(!paused) {
            if(!splitScreen) {
                if (!primaryShapeAtThreshold) {
                    promptShape.setLineWidth(promptShape.getRadius() / Draw.LINE_WIDTH_DIVISOR);
                }
            } else {
                if (!primaryShapeAtThresholdP1) {
                    promptShapeP1.setLineWidth(promptShapeP1.getRadius() / Draw.LINE_WIDTH_DIVISOR);
                }
                if (!primaryShapeAtThresholdP2) {
                    promptShapeP2.setLineWidth(promptShapeP2.getRadius() / Draw.LINE_WIDTH_DIVISOR);
                }
            }
        }
    }

    public void drawBackgroundColorShape() {
        if(!paused) {
            if (!gameOver) {
                game.draw.drawBackgroundColorShape(backgroundColorShape);
            }
        }
    }

    public void increaseSpeed() {
        if(!gameOver) {
            if(!paused) {
                if ((System.currentTimeMillis() - lastSpeedIncreaseTime - timePaused) / ONE_THOUSAND > TEN_SECONDS) {
                    lastSpeedIncreaseTime = System.currentTimeMillis();
                    game.draw.setColorListSpeed(game.draw.getColorListSpeed() + COLOR_LIST_SPEED_ADDITIVE);
                    game.draw.setColorSpeed(game.draw.getColorSpeed() + COLOR_SPEED_ADDITIVE);
                    promptIncrease = (game.widthOrHeightSmaller * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
                }
            }
        }
    }

    public void maintainSpeed() {
        if(!gameOver) {
            if(!paused) {
                float actualFPS = Gdx.graphics.getRawDeltaTime() * game.FPS;
                if(blackAndWhite) {
                    game.draw.setColorListSpeed((NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT) / (game.timeAttackNumSeconds * actualFPS * game.FPS));
                } else {
                    //We're in Battle mode here
                    game.draw.setColorListSpeed((NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT) / (game.ONE_MINUTE * actualFPS * game.FPS));
                }
                promptIncrease = (game.widthOrHeightSmaller * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
            }
        }
    }

    public void decrementSquirgleOpacity() {
        if(squirgleOpacity > 0) {
            squirgleOpacity -= SQUIRGLE_OPACITY_DECREMENT;
        } else {
            squirgleOpacity = 0;
        }
    }

    public void decrementSquirgleOpacities() {
        if(squirgleOpacityP1 > 0) {
            squirgleOpacityP1 -= SQUIRGLE_OPACITY_DECREMENT;
        } else {
            squirgleOpacityP1 = 0;
        }
        if(squirgleOpacityP2 > 0) {
            squirgleOpacityP2 -= SQUIRGLE_OPACITY_DECREMENT;
        } else {
            squirgleOpacityP2 = 0;
        }
    }

    public void zoomThroughShapes() {
        if(!paused) {
            if(gameOver) {
                if ((System.currentTimeMillis() - endTime) / ONE_THOUSAND > TWO_SECONDS) {
                    Shape promptShapeToUse = new Shape();
                    if(!splitScreen) {
                        promptShapeToUse = promptShape;
                    } else if(useSaturation) {
                        if(saturationP1 > saturationP2) {
                            promptShapeToUse = promptShapeP2;
                        } else {
                            promptShapeToUse = promptShapeP1;
                        }
                    } else {
                        if(scoreP1 >= scoreP2) {
                            promptShapeToUse = promptShapeP1;
                        } else {
                            promptShapeToUse = promptShapeP2;
                        }
                    }
                    if(promptShapeToUse == promptShape) {
                        if (!primaryShapeAtThreshold) { //TODO: Also account for height (different screen orientations?)
                            promptIncrease += PROMPT_INCREASE_ADDITIVE;
                            promptShape.setCoordinates(new Vector2(promptShape.getCoordinates().x - (primaryShape.getCoordinates().x - firstPriorShapePreviousX),
                                    promptShape.getCoordinates().y));
                        } else {
                            promptIncrease = 1;
                            if (primaryShape.getShape() == Shape.LINE && primaryShape.getLineWidth() < (game.camera.viewportWidth * 4)) {
                                primaryShape.setLineWidth(primaryShape.getLineWidth() * END_LINE_WIDTH_INCREASE);
                            } else if (primaryShape.getShape() == Shape.LINE) {
                                //Prevent shape lines from being visible in the event that primaryShape is promptShape
                                clearColor = primaryShape.getColor();
                            }
                            if (primaryShape.getShape() != Shape.LINE || primaryShape.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                                showResults = true;
                            }
                        }
                    } else if(promptShapeToUse == promptShapeP1) {
                        if (!primaryShapeAtThresholdP1) { //TODO: Also account for height (different screen orientations?)
                            promptIncrease += PROMPT_INCREASE_ADDITIVE;
                            promptShapeP1.setCoordinates(new Vector2(promptShapeP1.getCoordinates().x - (primaryShapeP1.getCoordinates().x - firstPriorShapePreviousXP1),
                                    promptShapeP1.getCoordinates().y));
                        } else {
                            promptIncrease = 1;
                            if (primaryShapeP1.getShape() == Shape.LINE && primaryShapeP1.getLineWidth() < (game.camera.viewportWidth * 4)) {
                                primaryShapeP1.setLineWidth(primaryShapeP1.getLineWidth() * END_LINE_WIDTH_INCREASE);
                            } else if (primaryShapeP1.getShape() == Shape.LINE) {
                                //Prevent shape lines from being visible in the event that primaryShape is promptShape
                                clearColor = primaryShape.getColor();
                            }
                            if (primaryShapeP1.getShape() != Shape.LINE || primaryShapeP1.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                                showResults = true;
                            }
                        }
                    } else {
                        if (!primaryShapeAtThresholdP2) { //TODO: Also account for height (different screen orientations?)
                            promptIncrease += PROMPT_INCREASE_ADDITIVE;
                            promptShapeP2.setCoordinates(new Vector2(promptShapeP2.getCoordinates().x - (primaryShapeP2.getCoordinates().x - firstPriorShapePreviousXP2),
                                    promptShapeP2.getCoordinates().y));
                        } else {
                            promptIncrease = 1;
                            if (primaryShapeP2.getShape() == Shape.LINE && primaryShapeP2.getLineWidth() < (game.camera.viewportWidth * 4)) {
                                primaryShapeP2.setLineWidth(primaryShapeP2.getLineWidth() * END_LINE_WIDTH_INCREASE);
                            } else if (primaryShapeP2.getShape() == Shape.LINE) {
                                //Prevent shape lines from being visible in the event that primaryShape is promptShape
                                clearColor = primaryShape.getColor();
                            }
                            if (primaryShapeP2.getShape() != Shape.LINE || primaryShapeP2.getLineWidth() >= (game.camera.viewportWidth * 4)) {
                                showResults = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void drawEquations() {
        if(!paused) {
            if(!gameOver) {
                if(!splitScreen) {
                    if (equationWidth > 0) {
                        game.draw.drawEquationTutorial(false, null, phase, lastShapeTouched, lastPromptShape, lastTargetShape, equationWidth);
                        equationWidth -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidth = 0;
                    }
                } else {
                    if(equationWidthP1 > 0) {
                        game.draw.drawEquationTutorial(false, P1, phase, lastShapeTouchedP1, lastPromptShapeP1, lastTargetShapeP1, equationWidthP1);
                        equationWidthP1 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidthP1 = 0;
                    }
                    if(equationWidthP2 > 0) {
                        game.draw.drawEquationTutorial(local, P2, phase, lastShapeTouchedP2, lastPromptShapeP2, lastTargetShapeP2, equationWidthP2);
                        equationWidthP2 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidthP2 = 0;
                    }
                }
            }
        }
    }

    public void destroyOversizedShapes() {
        if(!paused) {
            //Prevent shapes from getting too large
            if(!splitScreen) {
                if (promptShape.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                    if (priorShapeList.size() > destructionIndex) {
                        promptShape = priorShapeList.get(priorShapeList.size() - destructionIndex);
                        for (int i = 0; i < destructionIndex; i++) {
                            priorShapeList.remove(priorShapeList.size() - 1);
                        }
                        destructionIndex = 2;
                    }
                }
            } else {
                if (promptShapeP1.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                    if (priorShapeListP1.size() > destructionIndex) {
                        promptShapeP1 = priorShapeListP1.get(priorShapeListP1.size() - destructionIndex);
                        for (int i = 0; i < destructionIndex; i++) {
                            priorShapeListP1.remove(priorShapeListP1.size() - 1);
                        }
                        destructionIndex = 2;
                    }
                }
                if (promptShapeP2.getRadius() >= game.camera.viewportWidth * SHAPE_SIZE_LIMIT_MULTIPLIER) {
                    if (priorShapeListP2.size() > destructionIndex) {
                        promptShapeP2 = priorShapeListP2.get(priorShapeListP2.size() - destructionIndex);
                        for (int i = 0; i < destructionIndex; i++) {
                            priorShapeListP2.remove(priorShapeListP2.size() - 1);
                        }
                        destructionIndex = 2;
                    }
                }
            }
        }
    }

    public void drawResultsInputButtons() {
        if(!paused) {
            if (showResults) {
                game.draw.drawResultsInputButtonsTutorial(resultsColor, INPUT_PLAY_SPAWN, INPUT_HOME_SPAWN, INPUT_EXIT_SPAWN);
            }
        }
    }

    public void gameOver() {
        //Game over condition
        boolean gameOverCondition = false;
        if(!splitScreen) {
            gameOverCondition = promptShape.getRadius() >= game.widthOrHeightSmaller / 2 && !gameOver;
        } else {
            gameOverCondition = (dummyPromptForTimelines.getRadius() >= game.widthOrHeightSmaller / 2
                    || saturationP1 >= MAX_SATURATION || saturationP2 >= MAX_SATURATION)
                    && !gameOver;
        }
        if (gameOverCondition) {
            game.gameOverSound.play((float) (game.fxVolume / 10.0));
            gameOver = true;
            promptIncrease = 1;
            endTime = System.currentTimeMillis();
            veilOpacity = 1;
            clearColor.set(backgroundColorShape.getColor().r,
                    backgroundColorShape.getColor().g,
                    backgroundColorShape.getColor().b,
                    backgroundColorShape.getColor().a);
            game.stats.updateTimePlayed(endTime - startTime, gameplayType);
            if(splitScreen) {
                //We have to set the radii here to prevent stuttering when zooming through the shapes.
                if (useSaturation) {
                    if (saturationP1 <= saturationP2) {
                        promptShapeP1.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP1.setCoordinates(new Vector2(promptShapeP1.getCoordinates().x, game.camera.viewportHeight / 2));
                    } else {
                        promptShapeP2.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP2.setCoordinates(new Vector2(promptShapeP2.getCoordinates().x, game.camera.viewportHeight / 2));
                    }
                } else {
                    if (scoreP1 >= scoreP2) {
                        promptShapeP1.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP1.setCoordinates(new Vector2(promptShapeP1.getCoordinates().x, game.camera.viewportHeight / 2));
                    } else {
                        promptShapeP2.setRadius(game.camera.viewportWidth < (game.camera.viewportHeight / 2) ? game.camera.viewportWidth / 2 : game.camera.viewportHeight / 4);
                        promptShapeP2.setCoordinates(new Vector2(promptShapeP2.getCoordinates().x, game.camera.viewportHeight / 2));
                    }
                }
            }
        }
    }

    public void determineTouchedInput(int screenX, int screenY) {
        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        if(!splitScreen) {
            pointTouched = touchPoint.x > INPUT_POINT_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_POINT_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_POINT_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_POINT_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            lineTouched = touchPoint.x > INPUT_LINE_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_LINE_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_LINE_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_LINE_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            triangleTouched = touchPoint.x > INPUT_TRIANGLE_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_TRIANGLE_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_TRIANGLE_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_TRIANGLE_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            squareTouched = touchPoint.x > INPUT_SQUARE_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SQUARE_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SQUARE_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SQUARE_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            pentagonTouched = touchPoint.x > INPUT_PENTAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_PENTAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_PENTAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_PENTAGON_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            hexagonTouched = touchPoint.x > INPUT_HEXAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_HEXAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_HEXAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_HEXAGON_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            septagonTouched = touchPoint.x > INPUT_SEPTAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SEPTAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SEPTAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SEPTAGON_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            octagonTouched = touchPoint.x > INPUT_OCTAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_OCTAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_OCTAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_OCTAGON_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            nonagonTouched = touchPoint.x > INPUT_NONAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_NONAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_NONAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_NONAGON_SPAWN.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                    && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 20)
                    && touchPoint.y < (game.camera.viewportHeight / 2) + (game.camera.viewportWidth / 20);
            helpTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                    && touchPoint.y > (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 10) - (game.camera.viewportWidth / 20)
                    && touchPoint.y < (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 10) + (game.camera.viewportWidth / 20)
                    && phase < PHASE_SIX;
        } else {
            pointTouchedP1 = touchPoint.x > INPUT_POINT_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_POINT_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_POINT_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            lineTouchedP1 = touchPoint.x > INPUT_LINE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_LINE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_LINE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_LINE_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            triangleTouchedP1 = touchPoint.x > INPUT_TRIANGLE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_TRIANGLE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_TRIANGLE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_TRIANGLE_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            squareTouchedP1 = touchPoint.x > INPUT_SQUARE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SQUARE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SQUARE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SQUARE_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            pentagonTouchedP1 = touchPoint.x > INPUT_PENTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_PENTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_PENTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_PENTAGON_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            hexagonTouchedP1 = touchPoint.x > INPUT_HEXAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_HEXAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_HEXAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_HEXAGON_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            septagonTouchedP1 = touchPoint.x > INPUT_SEPTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SEPTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SEPTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SEPTAGON_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            octagonTouchedP1 = touchPoint.x > INPUT_OCTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_OCTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_OCTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_OCTAGON_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            nonagonTouchedP1 = touchPoint.x > INPUT_NONAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_NONAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_NONAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_NONAGON_SPAWN_P1.y + INPUT_RADIUS
                    && phase >= PHASE_TWO;
            if(game.widthGreater) {
                pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 40)
                        && touchPoint.y > (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) + (game.camera.viewportWidth / 40) - (game.camera.viewportWidth / 40)
                        && touchPoint.y < (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) + (game.camera.viewportWidth / 40) + (game.camera.viewportWidth / 40);
                helpTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 40)
                        && touchPoint.y > (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) - (game.camera.viewportWidth / 40) - (game.camera.viewportWidth / 40)
                        && touchPoint.y < (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) - (game.camera.viewportWidth / 40) + (game.camera.viewportWidth / 40)
                        && phase < PHASE_SIX;
            } else {
                pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                        && touchPoint.y > (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) + (game.camera.viewportWidth / 20) - (game.camera.viewportWidth / 20)
                        && touchPoint.y < (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) + (game.camera.viewportWidth / 20) + (game.camera.viewportWidth / 20);
                helpTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                        && touchPoint.y > (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) - (game.camera.viewportWidth / 20) - (game.camera.viewportWidth / 20)
                        && touchPoint.y < (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) + (((game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS)) / 2) - (game.camera.viewportWidth / 20) + (game.camera.viewportWidth / 20)
                        && phase < PHASE_SIX;
            }
            if(multiplayer) {
                pointTouchedP2 = touchPoint.x > INPUT_POINT_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_POINT_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_POINT_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_POINT_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                lineTouchedP2 = touchPoint.x > INPUT_LINE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_LINE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_LINE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_LINE_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                triangleTouchedP2 = touchPoint.x > INPUT_TRIANGLE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_TRIANGLE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_TRIANGLE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_TRIANGLE_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                squareTouchedP2 = touchPoint.x > INPUT_SQUARE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_SQUARE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_SQUARE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_SQUARE_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                pentagonTouchedP2 = touchPoint.x > INPUT_PENTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_PENTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_PENTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_PENTAGON_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                hexagonTouchedP2 = touchPoint.x > INPUT_HEXAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_HEXAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_HEXAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_HEXAGON_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                septagonTouchedP2 = touchPoint.x > INPUT_SEPTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_SEPTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_SEPTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_SEPTAGON_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                octagonTouchedP2 = touchPoint.x > INPUT_OCTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_OCTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_OCTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_OCTAGON_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                nonagonTouchedP2 = touchPoint.x > INPUT_NONAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_NONAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_NONAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_NONAGON_SPAWN_P2.y + INPUT_RADIUS
                        && phase >= PHASE_TWO;
                inputTouchedGameplayP2 = pointTouchedP2 || lineTouchedP2 || triangleTouchedP2 || squareTouchedP2 || pentagonTouchedP2 || hexagonTouchedP2 || septagonTouchedP2 || octagonTouchedP2 || nonagonTouchedP2;
            }
        }
        helpChevronDownTouched = touchPoint.x > helpLabel.getX() - helpInputGirth
                && touchPoint.x < helpLabel.getX()
                && touchPoint.y > helpLabel.getY() - helpInputGirth
                && touchPoint.y < helpLabel.getY() + helpLabel.getHeight()
                && helpTextVisible;
        helpChevronUpTouched = touchPoint.x > helpLabel.getX() + helpLabel.getWidth()
                && touchPoint.x < helpLabel.getX() + helpLabel.getWidth() + helpInputGirth
                && touchPoint.y > helpLabel.getY() - helpInputGirth
                && touchPoint.y < helpLabel.getY() + helpLabel.getHeight()
                && helpTextVisible;
        helpNextTouched = touchPoint.x > helpLabel.getX() + (helpLabel.getWidth() / 2)
                && touchPoint.x < helpLabel.getX() + helpLabel.getWidth()
                && touchPoint.y > helpLabel.getY() - helpInputGirth
                && touchPoint.y < helpLabel.getY()
                && helpTextVisible
                && currentHelpTextIndex == getHelpTextMaxIndex();
        playTouched = touchPoint.x > INPUT_PLAY_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_PLAY_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_PLAY_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_PLAY_SPAWN.y + INPUT_RADIUS;
        homeTouched = touchPoint.x > INPUT_HOME_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_HOME_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_HOME_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_HOME_SPAWN.y + INPUT_RADIUS;
        exitTouched = touchPoint.x > INPUT_EXIT_SPAWN.x - INPUT_RADIUS
                && touchPoint.x < INPUT_EXIT_SPAWN.x + INPUT_RADIUS
                && touchPoint.y > INPUT_EXIT_SPAWN.y - INPUT_RADIUS
                && touchPoint.y < INPUT_EXIT_SPAWN.y + INPUT_RADIUS;
        pauseBackTouched = touchPoint.x > game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT;
        pauseQuitTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        if(!splitScreen) {
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        } else {
            inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        }
        inputTouchedHelp = helpTouched || helpChevronDownTouched || helpChevronUpTouched || helpNextTouched;
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void determineKeyedInput(int keycode) {
        if(!splitScreen) {
            pointTouched = (keycode == Input.Keys.NUM_1 || keycode == Input.Keys.NUMPAD_1) && phase >= PHASE_TWO;
            lineTouched = (keycode == Input.Keys.NUM_2 || keycode == Input.Keys.NUMPAD_2) && phase >= PHASE_TWO;
            triangleTouched = (keycode == Input.Keys.NUM_3 || keycode == Input.Keys.NUMPAD_3) && phase >= PHASE_TWO;
            squareTouched = (keycode == Input.Keys.NUM_4 || keycode == Input.Keys.NUMPAD_4) && phase >= PHASE_TWO;
            pentagonTouched = (keycode == Input.Keys.NUM_5 || keycode == Input.Keys.NUMPAD_5) && phase >= PHASE_TWO && game.base >= 5;
            hexagonTouched = (keycode == Input.Keys.NUM_6 || keycode == Input.Keys.NUMPAD_6) && phase >= PHASE_TWO && game.base >= 6;
            septagonTouched = (keycode == Input.Keys.NUM_7 || keycode == Input.Keys.NUMPAD_7) && phase >= PHASE_TWO && game.base >= 7;
            octagonTouched = (keycode == Input.Keys.NUM_8 || keycode == Input.Keys.NUMPAD_8) && phase >= PHASE_TWO && game.base >= 8;
            nonagonTouched = (keycode == Input.Keys.NUM_9 || keycode == Input.Keys.NUMPAD_9) && phase >= PHASE_TWO && game.base >= 9;
            playTouched = pointTouched;
            homeTouched = lineTouched;
            exitTouched = triangleTouched;
        } else {
            pointTouchedP1 = (keycode == Input.Keys.NUM_1 || keycode == Input.Keys.NUMPAD_1) && phase >= PHASE_TWO;
            lineTouchedP1 = (keycode == Input.Keys.NUM_2 || keycode == Input.Keys.NUMPAD_2) && phase >= PHASE_TWO;
            triangleTouchedP1 = (keycode == Input.Keys.NUM_3 || keycode == Input.Keys.NUMPAD_3) && phase >= PHASE_TWO;
            squareTouchedP1 = (keycode == Input.Keys.NUM_4 || keycode == Input.Keys.NUMPAD_4) && phase >= PHASE_TWO;
            pentagonTouchedP1 = (keycode == Input.Keys.NUM_5 || keycode == Input.Keys.NUMPAD_5) && phase >= PHASE_TWO && game.base >= 5;
            hexagonTouchedP1 = (keycode == Input.Keys.NUM_6 || keycode == Input.Keys.NUMPAD_6) && phase >= PHASE_TWO && game.base >= 6;
            septagonTouchedP1 = (keycode == Input.Keys.NUM_7 || keycode == Input.Keys.NUMPAD_7) && phase >= PHASE_TWO && game.base >= 7;
            octagonTouchedP1 = (keycode == Input.Keys.NUM_8 || keycode == Input.Keys.NUMPAD_8) && phase >= PHASE_TWO && game.base >= 8;
            nonagonTouchedP1 = (keycode == Input.Keys.NUM_9 || keycode == Input.Keys.NUMPAD_9) && phase >= PHASE_TWO && game.base >= 9;
            playTouched = pointTouchedP1;
            homeTouched = lineTouchedP1;
            exitTouched = triangleTouchedP1;
        }
        pauseTouched = keycode == Input.Keys.ESCAPE;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
        if(!splitScreen) {
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        } else {
            inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        }
        inputTouchedResults = playTouched || homeTouched || exitTouched;
        inputTouchedHelp = false;
    }

    public void handleInput(String player) {
        if(!paused) {
            if (!gameOver) {
                if(player == null) {
                    if (pointTouched) {
                        transitionShape(null, Shape.POINT);
                    } else if (lineTouched) {
                        transitionShape(null, Shape.LINE);
                    } else if (triangleTouched) {
                        transitionShape(null, Shape.TRIANGLE);
                    } else if (squareTouched) {
                        transitionShape(null, Shape.SQUARE);
                    } else if (pentagonTouched) {
                        transitionShape(null, Shape.PENTAGON);
                    } else if (hexagonTouched) {
                        transitionShape(null, Shape.HEXAGON);
                    } else if (septagonTouched) {
                        transitionShape(null, Shape.SEPTAGON);
                    } else if (octagonTouched) {
                        transitionShape(null, Shape.OCTAGON);
                    } else if (nonagonTouched) {
                        transitionShape(null, Shape.NONAGON);
                    } else if (pauseTouched) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                        pause();
                        pauseTouched = false;
                        pauseBackTouched = false;
                        pauseQuitTouched = false;
                    }
                } else if(player.equals(P1)) {
                    if (pointTouchedP1) {
                        transitionShape(P1, Shape.POINT);
                    } else if (lineTouchedP1) {
                        transitionShape(P1, Shape.LINE);
                    } else if (triangleTouchedP1) {
                        transitionShape(P1, Shape.TRIANGLE);
                    } else if (squareTouchedP1) {
                        transitionShape(P1, Shape.SQUARE);
                    } else if (pentagonTouchedP1) {
                        transitionShape(P1, Shape.PENTAGON);
                    } else if (hexagonTouchedP1) {
                        transitionShape(P1, Shape.HEXAGON);
                    } else if (septagonTouchedP1) {
                        transitionShape(P1, Shape.SEPTAGON);
                    } else if (octagonTouchedP1) {
                        transitionShape(P1, Shape.OCTAGON);
                    } else if (nonagonTouchedP1) {
                        transitionShape(P1, Shape.NONAGON);
                    } else if (pauseTouched) {
                        game.confirmSound.play((float) (game.fxVolume / 10.0));
                        pause();
                        pauseTouched = false;
                        pauseBackTouched = false;
                        pauseQuitTouched = false;
                    }
                } else if(player.equals(P2)) {
                    if (pointTouchedP2) {
                        transitionShape(P2, Shape.POINT);
                    } else if (lineTouchedP2) {
                        transitionShape(P2, Shape.LINE);
                    } else if (triangleTouchedP2) {
                        transitionShape(P2, Shape.TRIANGLE);
                    } else if (squareTouchedP2) {
                        transitionShape(P2, Shape.SQUARE);
                    } else if (pentagonTouchedP2) {
                        transitionShape(P2, Shape.PENTAGON);
                    } else if (hexagonTouchedP2) {
                        transitionShape(P2, Shape.HEXAGON);
                    } else if (septagonTouchedP2) {
                        transitionShape(P2, Shape.SEPTAGON);
                    } else if (octagonTouchedP2) {
                        transitionShape(P2, Shape.OCTAGON);
                    } else if (nonagonTouchedP2) {
                        transitionShape(P2, Shape.NONAGON);
                    }
                }
            }
            if(phase >= PHASE_THREE) {
                if (player == null) {
                    if (inputTouchedGameplay && !gameOver && promptShape.getShape() == currentTargetShape.getShape()) {
                        shapesMatchedBehavior(null);
                    } else if (!gameOver && inputTouchedGameplay) {
                        shapesMismatchedBehavior(null);
                    }
                } else if (player.equals(P1)) {
                    if (inputTouchedGameplayP1 && !gameOver && promptShapeP1.getShape() == currentTargetShapeP1.getShape()) {
                        shapesMatchedBehavior(P1);
                    } else if (!gameOver && inputTouchedGameplayP1) {
                        shapesMismatchedBehavior(P1);
                    }
                } else {
                    if (inputTouchedGameplayP2 && !gameOver && promptShapeP2.getShape() == currentTargetShapeP2.getShape()) {
                        shapesMatchedBehavior(P2);
                    } else if (!gameOver && inputTouchedGameplayP2) {
                        shapesMismatchedBehavior(P2);
                    }
                }
            }
            if(player == null || player.equals(P1)) {
                if(inputTouchedHelp) {
                    handleHelpInput();
                }
                if (inputTouchedResults && showResults) {
                    handleResultsInput();
                }
            }
        } else {
            handlePauseInput();
        }
    }

    public void handleResultsInput() {
        if (playTouched) {
            stopMusic();
            game.setScreen(new TutorialScreen(game, gameplayType));
        } else if (homeTouched) {
            stopMusic();
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
        } else {
            dispose();
            System.exit(0);
        }
        dispose();
    }

    public void handlePauseInput() {
        if (pauseBackTouched) {
            game.disconfirmSound.play((float) (game.fxVolume / 10.0));
            timePaused += System.currentTimeMillis() - pauseStartTime;
            opponentTime += System.currentTimeMillis() - pauseStartTime;
            resume();
        } else if (pauseQuitTouched) {
            game.confirmSound.play((float) (game.fxVolume / 10.0));
            endTime = System.currentTimeMillis();
            game.stats.updateTimePlayed(endTime - startTime, gameplayType);
            stopMusic();
            game.setScreen(new MainMenuScreen(game, Color.BLACK));
            dispose();
        }
    }

    public void handleHelpInput() {
        if(helpTouched) {
            if(helpTextVisible) {
                game.disconfirmSound.play((float) (game.fxVolume / 10.0));
            } else {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
            }
            helpTextVisible = !helpTextVisible;
        }else if(helpChevronDownTouched) {
            game.disconfirmSound.play((float) (game.fxVolume / 10.0));
            if(currentHelpTextIndex > 0) {
                currentHelpTextIndex--;
            } else {
                currentHelpTextIndex = getHelpTextMaxIndex();
            }
        } else if(helpChevronUpTouched) {
            game.confirmSound.play((float) (game.fxVolume / 10.0));
            if(currentHelpTextIndex < getHelpTextMaxIndex()) {
                currentHelpTextIndex++;
            } else {
                currentHelpTextIndex = 0;
            }
        } else if(helpNextTouched) {
            game.confirmSound.play((float) (game.fxVolume / 10.0));
            phase++;
            currentHelpTextIndex = 0;
        }
        if(phase < PHASE_SIX) {
            helpLabel.setText(getCurrentHelpText());
        } else {
            helpTextVisible = false;
        }
    }

    public void transitionShape(String player, int shapeAdded) {
        if(player == null) {
            lastShapeTouched.setShape(shapeAdded);
            lastPromptShape.setShape(promptShape.getShape());
            if (lastPromptShape.getShape() == Shape.POINT) {
                lastPromptShape.setRadius(promptShape.getRadius() / 2);
            } else {
                lastPromptShape.setRadius(promptShape.getRadius());
            }
            lastTargetShape.setShape(currentTargetShape.getShape());
            equationWidth = INPUT_RADIUS;
            if (promptShape.getShape() + (shapeAdded + 1) >= game.base) {
                promptShape.setShape((promptShape.getShape() + (shapeAdded + 1)) - game.base);
            } else {
                promptShape.setShape(promptShape.getShape() + (shapeAdded + 1));
            }
        } else if(player.equals("P1")) {
            lastShapeTouchedP1.setShape(shapeAdded);
            lastPromptShapeP1.setShape(promptShapeP1.getShape());
            if (lastPromptShapeP1.getShape() == Shape.POINT) {
                lastPromptShapeP1.setRadius(promptShapeP1.getRadius() / 2);
            } else {
                lastPromptShapeP1.setRadius(promptShapeP1.getRadius());
            }
            lastTargetShapeP1.setShape(currentTargetShapeP1.getShape());
            equationWidthP1 = INPUT_RADIUS;
            if (promptShapeP1.getShape() + (shapeAdded + 1) >= game.base) {
                promptShapeP1.setShape((promptShapeP1.getShape() + (shapeAdded + 1)) - game.base);
            } else {
                promptShapeP1.setShape(promptShapeP1.getShape() + (shapeAdded + 1));
            }
        } else {
            lastShapeTouchedP2.setShape(shapeAdded);
            lastPromptShapeP2.setShape(promptShapeP2.getShape());
            if (lastPromptShapeP2.getShape() == Shape.POINT) {
                lastPromptShapeP2.setRadius(promptShapeP2.getRadius() / 2);
            } else {
                lastPromptShapeP2.setRadius(promptShapeP2.getRadius());
            }
            lastTargetShapeP2.setShape(currentTargetShapeP2.getShape());
            equationWidthP2 = INPUT_RADIUS;
            if (promptShapeP2.getShape() + (shapeAdded + 1) >= game.base) {
                promptShapeP2.setShape((promptShapeP2.getShape() + (shapeAdded + 1)) - game.base);
            } else {
                promptShapeP2.setShape(promptShapeP2.getShape() + (shapeAdded + 1));
            }
        }
    }

    public void shapesMatchedBehavior(String player) {
        game.correctInputSound.play((float) (game.fxVolume / 10.0));
        if(player == null) {
            targetShapesMatched++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShape.getRadius(),
                    blackAndWhite ? Color.WHITE : Color.BLACK,
                    null,
                    promptShape.getLineWidth(),
                    promptShape.getCoordinates());
            Shape promptShapeToAdd = new Shape(promptShape.getShape(),
                    promptShape.getRadius(),
                    backgroundColorShape.getColor(),
                    null,
                    promptShape.getLineWidth(),
                    promptShape.getCoordinates());
            if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeList.isEmpty())) {
                promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
            }
            promptShape.setShape(MathUtils.random(game.base - 1));
            priorShapeList.add(promptShapeToAdd);
            priorShapeList.add(circleContainer);
            if (targetShapesMatched == 1) {
                currentTargetShape.setColor(blackAndWhite ? Color.WHITE : priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShape = outsideTargetShape;
            } else {
                targetShapesMatched = 0;
                if(phase >= PHASE_FOUR) {
                    score += multiplier;
                    if (multiplier < MAX_MULTIPLIER) {
                        multiplier++;
                    }
                }
                targetShapeList.clear();
                if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle && phase >= PHASE_FIVE) {
                    //SQUIRGLE!!!
                    outsideTargetShape.setShape(Shape.TRIANGLE);
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetArcStart = Draw.NINETY_ONE_DEGREES;
                    targetArcColor = priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor();
                } else {
                    outsideTargetShape.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShape.setColor(Color.BLACK);
                    targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeList.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if (targetShapeList.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
                        while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                            outsideTargetShape.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE && admitsOfSquirgle) {
                    //SQUIRGLE MATCHED!!!
                    if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                        promptShape.setRadius(INIT_PROMPT_RADIUS);
                        squirgleOpacity = 1;
                    }
                    game.stats.incrementNumSquirgles(gameplayType, game.base, game.difficulty);
                }
                currentTargetShape = targetShapeList.get(0);
            }
        } else if(player.equals(P1)) {
            targetShapesMatchedP1++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShapeP1.getRadius(),
                    blackAndWhite ? Color.WHITE : Color.BLACK,
                    null,
                    promptShapeP1.getLineWidth(),
                    promptShapeP1.getCoordinates());
            Shape promptShapeToAdd = new Shape(promptShapeP1.getShape(),
                    promptShapeP1.getRadius(),
                    backgroundColorShape.getColor(),
                    null,
                    promptShapeP1.getLineWidth(),
                    promptShapeP1.getCoordinates());
            if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeListP1.isEmpty())) {
                promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
            }
            promptShapeP1.setShape(MathUtils.random(game.base - 1));
            priorShapeListP1.add(promptShapeToAdd);
            priorShapeListP1.add(circleContainer);
            if (targetShapesMatchedP1 == 1) {
                currentTargetShapeP1.setColor(blackAndWhite ? Color.WHITE : priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShapeP1 = outsideTargetShapeP1;
            } else {
                targetShapesMatchedP1 = 0;
                if(phase >= PHASE_FOUR) {
                    if (!useSaturation) {
                        scoreP1 += multiplierP1;
                        if (multiplierP1 < MAX_MULTIPLIER) {
                            multiplierP1++;
                        }
                    }
                }
                targetShapeListP1.clear();
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle && phase >= PHASE_FIVE) {
                    //SQUIRGLE!!!
                    outsideTargetShapeP1.setShape(Shape.TRIANGLE);
                    outsideTargetShapeP1.setColor(Color.BLACK);
                    targetShapeListP1.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP1.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetArcStartP1 = Draw.NINETY_ONE_DEGREES;
                    targetArcColor = priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getColor();
                } else {
                    outsideTargetShapeP1.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShapeP1.setColor(Color.BLACK);
                    targetShapeListP1.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP1.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if (targetShapeListP1.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
                        while (outsideTargetShapeP1.getShape() == Shape.TRIANGLE) {
                            outsideTargetShapeP1.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE && admitsOfSquirgle) {
                    //SQUIRGLE MATCHED!!!
                    if(useSaturation) {
                        saturationP1 -= 5;
                        saturationP2 += 3;
                        squirgleOpacityP1 = 1;
                    }
                    game.stats.incrementNumSquirgles(gameplayType, game.base, game.difficulty);
                } else if(useSaturation) {
                    if(phase >= PHASE_FOUR) {
                        saturationP2++;
                    }
                }
                currentTargetShapeP1 = targetShapeListP1.get(0);
            }
        } else {
            targetShapesMatchedP2++;
            Shape circleContainer = new Shape(Shape.CIRCLE,
                    promptShapeP2.getRadius(),
                    blackAndWhite ? Color.WHITE : Color.BLACK,
                    null,
                    promptShapeP2.getLineWidth(),
                    promptShapeP2.getCoordinates());
            Shape promptShapeToAdd = new Shape(promptShapeP2.getShape(),
                    promptShapeP2.getRadius(),
                    backgroundColorShape.getColor(),
                    null,
                    promptShapeP2.getLineWidth(),
                    promptShapeP2.getCoordinates());
            if (promptShapeToAdd.getShape() == Shape.POINT || (promptShapeToAdd.getShape() == Shape.LINE && !priorShapeListP2.isEmpty())) {
                promptShapeToAdd.setRadius(circleContainer.getRadius() / 2);
            }
            promptShapeP2.setShape(MathUtils.random(game.base - 1));
            priorShapeListP2.add(promptShapeToAdd);
            priorShapeListP2.add(circleContainer);
            if (targetShapesMatchedP2 == 1) {
                currentTargetShapeP2.setColor(blackAndWhite ? Color.WHITE : priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor());
                currentTargetShapeP2 = outsideTargetShapeP2;
            } else {
                targetShapesMatchedP2 = 0;
                if(phase >= PHASE_FOUR) {
                    if (!useSaturation) {
                        scoreP2 += multiplierP2;
                        if (multiplierP2 < MAX_MULTIPLIER) {
                            multiplierP2++;
                        }
                    }
                }
                targetShapeListP2.clear();
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle && phase >= PHASE_FIVE) {
                    //SQUIRGLE!!!
                    outsideTargetShapeP2.setShape(Shape.TRIANGLE);
                    outsideTargetShapeP2.setColor(Color.BLACK);
                    targetShapeListP2.add(new Shape(Shape.SQUARE,
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP2.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if(local) {
                        targetArcStartP2 = 3 * Draw.NINETY_ONE_DEGREES;
                    } else {
                        targetArcStartP2 = Draw.NINETY_ONE_DEGREES;
                    }
                    targetArcColor = priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getColor();
                } else {
                    outsideTargetShapeP2.setShape(MathUtils.random(game.base - 1));
                    outsideTargetShapeP2.setColor(Color.BLACK);
                    targetShapeListP2.add(new Shape(MathUtils.random(game.base - 1),
                            0,
                            Color.WHITE,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    targetShapeListP2.add(new Shape(Shape.CIRCLE,
                            0,
                            Color.BLACK,
                            null,
                            INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                            new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                                    game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR))));
                    if (targetShapeListP2.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
                        while (outsideTargetShapeP2.getShape() == Shape.TRIANGLE) {
                            outsideTargetShapeP2.setShape(MathUtils.random(Shape.SQUARE));
                        }
                    }
                }
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getShape() == Shape.SQUARE && priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getShape() == Shape.TRIANGLE && admitsOfSquirgle) {
                    //SQUIRGLE MATCHED!!!
                    if(useSaturation) {
                        saturationP2 -= 5;
                        saturationP1 += 3;
                        squirgleOpacityP2 = 1;
                    }
                    game.stats.incrementNumSquirgles(gameplayType, game.base, game.difficulty);
                } else {
                    if(phase >= PHASE_FOUR) {
                        if (useSaturation) {
                            saturationP1++;
                        }
                    }
                }
                currentTargetShapeP2 = targetShapeListP2.get(0);
            }
        }
        keepSaturationsInBounds();
    }

    public void shapesMismatchedBehavior(String player) {
        //The wrong shape was touched
        game.incorrectInputSound.play((float) (game.fxVolume / 10.0));
        if(player == null) {
//            if(!blackAndWhite) {
//                if(phase >= PHASE_FIVE) {
//                    float radiusIncrease = game.widthOrHeightSmaller * ((backgroundColorShapeList.get(2).getCoordinates().y - backgroundColorShapeList.get(3).getCoordinates().y) / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT));
//
//                    if (phase < PHASE_SIX && promptShape.getRadius() + radiusIncrease > game.thirdOfScreen) {
//                        promptShape.setRadius(game.thirdOfScreen);
//                    } else if (promptShape.getRadius() + radiusIncrease > (game.widthOrHeightSmaller / 2)) {
//                        promptShape.setRadius(game.widthOrHeightSmaller / 2);
//                    } else {
//                        promptShape.setRadius(promptShape.getRadius() + radiusIncrease);
//                    }
//                }
//            } else {
//                if(score > 0) {
//                    score--;
//                }
//            }
            if(score > 0) {
                score--;
            }
            multiplier = 1;
        } else if(player.equals(P1)) {
            if(useSaturation) {
                saturationP1++;
                keepSaturationsInBounds();
            } else {
                multiplierP1 = 1;
                if(scoreP1 > 0) {
                    scoreP1--;
                }
            }
        } else if(player.equals(P2)) {
            if(useSaturation) {
                saturationP2++;
                keepSaturationsInBounds();
            } else {
                multiplierP2 = 1;
                if(scoreP2 > 0) {
                    scoreP2--;
                }
            }
        }
    }

    public void keepSaturationsInBounds() {
        if(phase < PHASE_SIX) {
            if(saturationP1 > 5) {
                saturationP1 = 5;
            }
            if(saturationP2 > 5) {
                saturationP2 = 5;
            }
        }
        if(saturationP1 < 0) {
            saturationP1 = 0;
        } else if(saturationP1 > MAX_SATURATION) {
            saturationP1 = MAX_SATURATION;
        }
        if(saturationP2 < 0) {
            saturationP2 = 0;
        } else if(saturationP2 > MAX_SATURATION) {
            saturationP2 = MAX_SATURATION;
        }
    }

    public void executeOpponentAI() {
        int turnTime = 0;
        if(game.difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
            turnTime = 3;
        } else if(game.difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
            turnTime = 2;
        } else {
            turnTime = 1;
        }
        if((System.currentTimeMillis() - opponentTime) / ONE_THOUSAND >= turnTime) {
            //50% chance the player's opponent will do anything
            if(MathUtils.random(1) > 0) {
                inputTouchedGameplayP2 = true;
                if(currentTargetShapeP2.getShape() > promptShapeP2.getShape()) {
                    if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 1) {
                        pointTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 2) {
                        lineTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 3) {
                        triangleTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 4) {
                        squareTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 5) {
                        pentagonTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 6) {
                        hexagonTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 7) {
                        septagonTouchedP2 = true;
                    } else if(currentTargetShapeP2.getShape() - promptShapeP2.getShape() == 8) {
                        octagonTouchedP2 = true;
                    }
                } else if(currentTargetShapeP2.getShape() < promptShapeP2.getShape()) {
                    if(promptShapeP2.getShape() + Shape.POINT - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        pointTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.LINE - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        lineTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.TRIANGLE - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        triangleTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.SQUARE - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        squareTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.PENTAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        pentagonTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.HEXAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        hexagonTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.SEPTAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        septagonTouchedP2 = true;
                    } else if(promptShapeP2.getShape() + Shape.OCTAGON - (game.base - 1) == currentTargetShapeP2.getShape()) {
                        octagonTouchedP2 = true;
                    }
                } else {
                    if(game.base == 4) {
                        squareTouchedP2 = true;
                    } else if(game.base == 5) {
                        pentagonTouchedP2 = true;
                    } else if(game.base == 6) {
                        hexagonTouchedP2 = true;
                    } else if(game.base == 7) {
                        septagonTouchedP2 = true;
                    } else if(game.base == 8) {
                        octagonTouchedP2 = true;
                    } else if(game.base == 9) {
                        nonagonTouchedP2 = true;
                    }
                }
                handleInput(P2);
                resetOpponentTouch();
            }
            opponentTime = System.currentTimeMillis();
        }
    }

    public void resetOpponentTouch() {
        pointTouchedP2 = false;
        lineTouchedP2 = false;
        triangleTouchedP2 = false;
        squareTouchedP2 = false;
        pentagonTouchedP2 = false;
        hexagonTouchedP2 = false;
        septagonTouchedP2 = false;
        octagonTouchedP2 = false;
        nonagonTouchedP2 = false;
        inputTouchedGameplayP2 = false;
    }

    public String getCurrentHelpText() {
        return helpTextMap.get(gameplayType).get(phase).get(currentHelpTextIndex);
    }

    public int getHelpTextMaxIndex() {
        return helpTextMap.get(gameplayType).get(phase).size() - 1;
    }

    public void showHelpText() {
        helpLabel.setVisible(helpTextVisible && !paused);
        if(helpTextVisible && !paused) {
            game.draw.rect(helpLabel.getX() - helpInputGirth,
                    helpLabel.getY() - helpInputGirth,
                    helpLabel.getWidth() + (game.camera.viewportWidth / 8),
                    helpLabel.getHeight() + helpInputGirth,
                    blackAndWhite ? Color.WHITE : Color.BLACK);
            game.draw.drawChevronLeft(helpLabel.getX() - (helpInputGirth / 2),
                    helpLabel.getY() + (helpLabel.getHeight() / 2) - (helpInputGirth / 2),
                    helpInputGirth / 2,
                    (helpInputGirth / 2) / Draw.LINE_WIDTH_DIVISOR,
                    blackAndWhite ? Color.BLACK : Color.WHITE);
            game.draw.drawChevronRight(helpLabel.getX() + helpLabel.getWidth() + (helpInputGirth / 2),
                    helpLabel.getY() + (helpLabel.getHeight() / 2) - (helpInputGirth / 2),
                    helpInputGirth / 2,
                    (helpInputGirth / 2) / Draw.LINE_WIDTH_DIVISOR,
                    blackAndWhite ? Color.BLACK : Color.WHITE);
            game.shapeRendererLine.setColor(blackAndWhite ? Color.BLACK : Color.WHITE);
            game.shapeRendererLine.line(helpLabel.getX(),
                    helpLabel.getY() + helpLabel.getHeight(),
                    helpLabel.getX(),
                    helpLabel.getY() - helpInputGirth);
            game.shapeRendererLine.line(helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY() + helpLabel.getHeight(),
                    helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY() - helpInputGirth);
            game.shapeRendererLine.line(helpLabel.getX(),
                    helpLabel.getY(),
                    helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY());
            if(currentHelpTextIndex == getHelpTextMaxIndex()) {
                game.shapeRendererLine.line(helpLabel.getX() + (helpLabel.getWidth() / 2),
                        helpLabel.getY(),
                        helpLabel.getX() + (helpLabel.getWidth() / 2),
                        helpLabel.getY() - helpInputGirth);
            }
        }
    }

    public void showHelpTextFooter() {
        if(helpTextVisible && !paused) {
            if(currentHelpTextIndex == getHelpTextMaxIndex()) {
                FontUtils.printText(game.batch,
                        game.fontTutorialHelp,
                        game.layout,
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        (currentHelpTextIndex + 1) + "/" + (getHelpTextMaxIndex() + 1),
                        helpLabel.getX() + (helpLabel.getWidth() / 4),
                        helpLabel.getY() - (helpInputGirth / 2),
                        0,
                        1);
                FontUtils.printText(game.batch,
                        game.fontTutorialHelp,
                        game.layout,
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        "NEXT",
                        helpLabel.getX() + ((3 * helpLabel.getWidth()) / 4),
                        helpLabel.getY() - (helpInputGirth / 2),
                        0,
                        1);
            } else {
                FontUtils.printText(game.batch,
                        game.fontTutorialHelp,
                        game.layout,
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        (currentHelpTextIndex + 1) + "/" + (getHelpTextMaxIndex() + 1),
                        helpLabel.getX() + (helpLabel.getWidth() / 2),
                        helpLabel.getY() - (helpInputGirth / 2),
                        0,
                        1);
            }
        }
    }

    public void setUpNonFinalStaticData() {
        INPUT_RADIUS = splitScreen && game.widthGreater ? game.camera.viewportWidth / 38 : game.camera.viewportWidth / 19;
        for(int i = 1; i <= game.base; i++) {
            //P
            Vector2 inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
            switch(i) {
                case 1 : INPUT_POINT_SPAWN = inputVector;
                case 2 : INPUT_LINE_SPAWN = inputVector;
                case 3 : INPUT_TRIANGLE_SPAWN = inputVector;
                case 4 : INPUT_SQUARE_SPAWN = inputVector;
                case 5 : INPUT_PENTAGON_SPAWN = inputVector;
                case 6 : INPUT_HEXAGON_SPAWN = inputVector;
                case 7 : INPUT_SEPTAGON_SPAWN = inputVector;
                case 8 : INPUT_OCTAGON_SPAWN = inputVector;
                case 9 : INPUT_NONAGON_SPAWN = inputVector;
            }

            //P1
            inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
            switch(i) {
                case 1 : INPUT_POINT_SPAWN_P1 = inputVector;
                case 2 : INPUT_LINE_SPAWN_P1 = inputVector;
                case 3 : INPUT_TRIANGLE_SPAWN_P1 = inputVector;
                case 4 : INPUT_SQUARE_SPAWN_P1 = inputVector;
                case 5 : INPUT_PENTAGON_SPAWN_P1 = inputVector;
                case 6 : INPUT_HEXAGON_SPAWN_P1 = inputVector;
                case 7 : INPUT_SEPTAGON_SPAWN_P1 = inputVector;
                case 8 : INPUT_OCTAGON_SPAWN_P1 = inputVector;
                case 9 : INPUT_NONAGON_SPAWN_P1 = inputVector;
            }

            //P2
            if(local) {
                inputVector = new Vector2(game.camera.viewportWidth - ((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS)), game.camera.viewportHeight - (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
                switch (i) {
                    case 1:
                        INPUT_POINT_SPAWN_P2 = inputVector;
                    case 2:
                        INPUT_LINE_SPAWN_P2 = inputVector;
                    case 3:
                        INPUT_TRIANGLE_SPAWN_P2 = inputVector;
                    case 4:
                        INPUT_SQUARE_SPAWN_P2 = inputVector;
                    case 5:
                        INPUT_PENTAGON_SPAWN_P2 = inputVector;
                    case 6:
                        INPUT_HEXAGON_SPAWN_P2 = inputVector;
                    case 7:
                        INPUT_SEPTAGON_SPAWN_P2 = inputVector;
                    case 8:
                        INPUT_OCTAGON_SPAWN_P2 = inputVector;
                    case 9:
                        INPUT_NONAGON_SPAWN_P2 = inputVector;
                }
            } else {
                inputVector = new Vector2((i * (game.camera.viewportWidth - ((2 * game.base) * INPUT_RADIUS))) / (game.base + 1) + ((i + i - 1) * INPUT_RADIUS), (game.camera.viewportHeight / 2) + (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
                switch (i) {
                    case 1:
                        INPUT_POINT_SPAWN_P2 = inputVector;
                    case 2:
                        INPUT_LINE_SPAWN_P2 = inputVector;
                    case 3:
                        INPUT_TRIANGLE_SPAWN_P2 = inputVector;
                    case 4:
                        INPUT_SQUARE_SPAWN_P2 = inputVector;
                    case 5:
                        INPUT_PENTAGON_SPAWN_P2 = inputVector;
                    case 6:
                        INPUT_HEXAGON_SPAWN_P2 = inputVector;
                    case 7:
                        INPUT_SEPTAGON_SPAWN_P2 = inputVector;
                    case 8:
                        INPUT_OCTAGON_SPAWN_P2 = inputVector;
                    case 9:
                        INPUT_NONAGON_SPAWN_P2 = inputVector;
                }
            }
        }
        TARGET_RADIUS = splitScreen && game.widthGreater ? game.camera.viewportWidth / 10.24f : game.camera.viewportWidth / 5.12f;
        PAUSE_INPUT_WIDTH = (game.camera.viewportWidth - (4 * game.partitionSize)) / 3;
        PAUSE_INPUT_HEIGHT = game.camera.viewportHeight - (2 * game.partitionSize);
        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS = TARGET_RADIUS / 12;
        if(splitScreen) {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_Y = (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
            BACKGROUND_COLOR_SHAPE_LIST_MIN_Y = (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
        } else {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_Y = ((game.camera.viewportHeight - TARGET_RADIUS) - (((game.camera.viewportHeight - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
            BACKGROUND_COLOR_SHAPE_LIST_MIN_Y = ((game.camera.viewportHeight - TARGET_RADIUS) - (((game.camera.viewportHeight - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (BACKGROUND_COLOR_LIST_ELEMENT_RADIUS * 6);
        }
        BACKGROUND_COLOR_SHAPE_LIST_HEIGHT = BACKGROUND_COLOR_SHAPE_LIST_MAX_Y - BACKGROUND_COLOR_SHAPE_LIST_MIN_Y;
        if(blackAndWhite) {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_X = -BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
            BACKGROUND_COLOR_SHAPE_LIST_MIN_X = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1));
        } else {
            BACKGROUND_COLOR_SHAPE_LIST_MAX_X = BACKGROUND_COLOR_LIST_ELEMENT_RADIUS;
            BACKGROUND_COLOR_SHAPE_LIST_MIN_X = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1));
        }
        BACKGROUND_COLOR_SHAPE_LIST_WIDTH = BACKGROUND_COLOR_SHAPE_LIST_MAX_X - BACKGROUND_COLOR_SHAPE_LIST_MIN_X;
        COLOR_LIST_SPEED_ADDITIVE =  BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / 5000;
        INIT_PROMPT_RADIUS = splitScreen ? game.widthOrHeightSmaller / 8 : game.widthOrHeightSmaller / 4;
        if(splitScreen && game.widthGreater) {
            FONT_SCORE_SIZE_DIVISOR = 30f;
            FONT_TARGET_SIZE_DIVISOR = 71f;
            FONT_SQUIRGLE_SIZE_DIVISOR = 10f;
        } else {
            FONT_SCORE_SIZE_DIVISOR = 11.1f;
            FONT_TARGET_SIZE_DIVISOR = 35.5f;
            FONT_SQUIRGLE_SIZE_DIVISOR = 5f;
        }
        if(game.widthGreater) {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 17f;
        } else {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 24f;
        }
        INPUT_PLAY_SPAWN = new Vector2(game.camera.viewportWidth / 4, (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
        INPUT_HOME_SPAWN = new Vector2((2 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
        INPUT_EXIT_SPAWN = new Vector2((3 * game.camera.viewportWidth) / 4, (Draw.INPUT_DISTANCE_OFFSET * INPUT_RADIUS));
    }

    public void setUpNonFinalNonStaticData() {
        backgroundColorShapeList = new ArrayList<Shape>();
        for (int i = 0; i <= 6; i++) {
            if (i == 0) {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        blackAndWhite ? Color.BLACK : ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(BACKGROUND_COLOR_SHAPE_LIST_MAX_X,
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_Y)));
            } else {
                backgroundColorShapeList.add(new Shape(Shape.SQUARE,
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS,
                        blackAndWhite ? Color.BLACK : Color.WHITE,
                        blackAndWhite ? Color.BLACK : ColorUtils.randomColor(),
                        BACKGROUND_COLOR_LIST_ELEMENT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(BACKGROUND_COLOR_SHAPE_LIST_MAX_X,
                                BACKGROUND_COLOR_SHAPE_LIST_MAX_Y - (i * (BACKGROUND_COLOR_SHAPE_LIST_HEIGHT / (Draw.NUM_BACKGROUND_COLOR_SHAPE_COLUMNS - 1))))));
            }
        }

        //Set prompt increase such that without player input, three passes of backgroundColorShapeList will
        //occur before game over
        promptIncrease = (game.widthOrHeightSmaller * (game.draw.getColorListSpeed() / (NUM_TIMELINES * BACKGROUND_COLOR_SHAPE_LIST_HEIGHT))) / 2;
        targetArcStart = -Draw.NINETY_ONE_DEGREES;
        targetArcStartP1 = -Draw.NINETY_ONE_DEGREES;
        targetArcStartP2 = local ? Draw.NINETY_ONE_DEGREES : -Draw.NINETY_ONE_DEGREES;
        squirgleOpacity = 0;
        squirgleOpacityP1 = 0;
        squirgleOpacityP2 = 0;
        helpInputGirth = game.camera.viewportWidth / 16;
        promptShape = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS, Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        promptShapeP1 = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS,
                Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 4));
        promptShapeP2 = new Shape(MathUtils.random(game.base - 1),
                INIT_PROMPT_RADIUS,
                Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        (3 * game.camera.viewportHeight) / 4));
        dummyPromptForTimelines = new Shape(Shape.POINT,
                INIT_PROMPT_RADIUS * 2,
                Color.WHITE,
                null,
                (INIT_PROMPT_RADIUS * 2) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(2 * game.camera.viewportWidth,
                        2 * game.camera.viewportHeight));
        lastShapeTouched = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, TutorialScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        lastShapeTouchedP1 = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP1.getCoordinates());
        lastShapeTouchedP2 = new Shape(Shape.POINT, INPUT_RADIUS, Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP2.getCoordinates());
        lastPromptShape = new Shape(Shape.POINT, promptShape.getRadius(), Color.BLACK, Color.BLACK, TutorialScreen.INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShape.getCoordinates());
        lastPromptShapeP1 = new Shape(Shape.POINT, promptShapeP1.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP1.getCoordinates());
        lastPromptShapeP2 = new Shape(Shape.POINT, promptShapeP2.getRadius(), Color.BLACK, Color.BLACK, INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR, promptShapeP2.getCoordinates());
        outsideTargetShape = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        outsideTargetShapeP1 = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        outsideTargetShapeP2 = new Shape(MathUtils.random(game.base - 1),
                TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                Color.BLACK,
                null,
                (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(local ? game.camera.viewportWidth - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) : TARGET_RADIUS / TARGET_RADIUS_DIVISOR,
                        local ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / TARGET_RADIUS_DIVISOR) : game.camera.viewportHeight - (TARGET_RADIUS / TARGET_RADIUS_DIVISOR)));
        priorShapeList = new ArrayList<Shape>();
        priorShapeListP1 = new ArrayList<Shape>();
        priorShapeListP2 = new ArrayList<Shape>();
        targetShapeList = new ArrayList<Shape>();
        targetShapeListP1 = new ArrayList<Shape>();
        targetShapeListP2 = new ArrayList<Shape>();
        targetShapeList.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        targetShapeList.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        targetShapeListP1.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2))));
        targetShapeListP1.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2))));
        targetShapeListP2.add(new Shape(MathUtils.random(game.base - 1),
                0, Color.WHITE,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        targetShapeListP2.add(new Shape(Shape.CIRCLE,
                0, Color.BLACK,
                null,
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(TARGET_RADIUS / 3,
                        game.camera.viewportHeight - (TARGET_RADIUS / 2))));
        if (targetShapeList.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
            while (outsideTargetShape.getShape() == Shape.TRIANGLE) {
                outsideTargetShape.setShape(MathUtils.random(game.base - 1));
            }
        }
        if (targetShapeListP1.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
            while (outsideTargetShapeP1.getShape() == Shape.TRIANGLE) {
                outsideTargetShapeP1.setShape(MathUtils.random(game.base - 1));
            }
        }
        if (targetShapeListP2.get(0).getShape() == Shape.SQUARE && admitsOfSquirgle) {
            while (outsideTargetShapeP2.getShape() == Shape.TRIANGLE) {
                outsideTargetShapeP2.setShape(MathUtils.random(game.base - 1));
            }
        }
        backgroundColorShape = new Shape(Shape.POINT,
                game.camera.viewportWidth / 2,
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                backgroundColorShapeList.get(backgroundColorShapeList.size() - 1).getFillColor(),
                INPUT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight + (game.camera.viewportWidth / 2)));
        currentTargetShape = targetShapeList.get(0);
        currentTargetShapeP1 = targetShapeListP1.get(0);
        currentTargetShapeP2 = targetShapeListP2.get(0);
        lastTargetShape = currentTargetShape;
        lastTargetShapeP1 = currentTargetShapeP1;
        lastTargetShapeP2 = currentTargetShapeP2;
        targetShapesMatched = 0;
        targetShapesMatchedP1 = 0;
        targetShapesMatchedP2 = 0;
        touchPoint = new Vector3();
        pointTouched = false;
        lineTouched = false;
        triangleTouched = false;
        squareTouched = false;
        pentagonTouched = false;
        hexagonTouched = false;
        septagonTouched = false;
        octagonTouched = false;
        nonagonTouched = false;
        pointTouchedP1 = false;
        lineTouchedP1 = false;
        triangleTouchedP1 = false;
        squareTouchedP1 = false;
        pentagonTouchedP1 = false;
        hexagonTouchedP1 = false;
        septagonTouchedP1 = false;
        octagonTouchedP1 = false;
        nonagonTouchedP1 = false;
        pointTouchedP2 = false;
        lineTouchedP2 = false;
        triangleTouchedP2 = false;
        squareTouchedP2 = false;
        pentagonTouchedP2 = false;
        hexagonTouchedP2 = false;
        septagonTouchedP2 = false;
        octagonTouchedP2 = false;
        nonagonTouchedP2 = false;
        helpTouched = false;
        helpChevronDownTouched = false;
        helpChevronUpTouched = false;
        helpNextTouched = false;
        playTouched = false;
        homeTouched = false;
        exitTouched = false;
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        inputTouchedGameplay = false;
        inputTouchedGameplayP1 = false;
        inputTouchedGameplayP2 = false;
        inputTouchedHelp = false;
        inputTouchedResults = false;
        targetArcColor = new Color();
        clearColor = new Color(backgroundColorShape.getColor());
        phase = PHASE_ONE;
        currentHelpTextIndex = 0;
        score = 0;
        scoreP1 = 0;
        scoreP2 = 0;
        multiplier = 1;
        multiplierP1 = 1;
        multiplierP2 = 1;
        gameOver = false;
        showResults = false;
        paused = false;
        helpTextVisible = true;
        startTime = System.currentTimeMillis();
        endTime = 0;
        lastSpeedIncreaseTime = System.currentTimeMillis();
        pauseStartTime = 0;
        timePaused = 0;
        opponentTime = System.currentTimeMillis();
        equationWidth = 0;
        equationWidthP1 = 0;
        equationWidthP2 = 0;
        destructionIndex = 1;
        firstPriorShapePreviousX = 0;
        firstPriorShapePreviousXP1 = 0;
        firstPriorShapePreviousXP2 = 0;
        resultsColor = new Color();
        primaryShape = priorShapeList.size() > 0 ? priorShapeList.get(0) : promptShape;
        primaryShapeP1 = priorShapeListP1.size() > 0 ? priorShapeListP1.get(0) : promptShapeP1;
        primaryShapeP2 = priorShapeListP2.size() > 0 ? priorShapeListP2.get(0) : promptShapeP2;

        primaryShapeThreshold = game.widthOrHeightSmaller * game.draw.THRESHOLD_MULTIPLIER;

        primaryShapeAtThreshold = primaryShape.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP1 = primaryShapeP1.getRadius() >= primaryShapeThreshold;
        primaryShapeAtThresholdP2 = primaryShapeP2.getRadius() >= primaryShapeThreshold;

        squirglePhaseOneTextOne = "Welcome to SQUIRGLE's tutorial! Please press the right arrow.";
        squirglePhaseOneTextTwo = "Look at the right side of the screen...";
        squirglePhaseOneTextThree = "...You may press [||] to pause or [?] to consult/dismiss help text.";
        squirglePhaseOneTextFour = "Press [?] to dismiss this text, and note the shape behind it...";
        squirglePhaseOneTextFive = "...That white, hollow shape is your HAND...";
        squirglePhaseOneTextSix = "...You must manipulate your HAND to progress through SQUIRGLE.";
        squirglePhaseOneTextSeven = "To proceed, press the NEXT button below.";
        squirglePhaseTwoTextOne = "At the bottom of the screen are your inputs...";
        squirglePhaseTwoTextTwo = "...These are the buttons that manipulate your HAND...";
        squirglePhaseTwoTextThree = "...You tap an input to add its VERTICES to your HAND.";
        squirglePhaseTwoTextFour = "VERTICES are a shape's circular corners...";
        squirglePhaseTwoTextFive = "...A POINT has 1, a LINE has 2, etc...";
        squirglePhaseTwoTextSix = "...Therefore, POINT + LINE = TRIANGLE.";
        squirglePhaseTwoTextSeven = "The goal of SQUIRGLE is to create TARGET shapes from your HAND...";
        squirglePhaseTwoTextEight = "...This is called MATCHING, and is done using addition...";
        squirglePhaseTwoTextNine = "...But the way addition behaves depends on your BASE...";
        squirglePhaseTwoTextTen = "...Right now, you're operating in a BASE of " + game.base + "...";
        squirglePhaseTwoTextEleven = "...Hence, " + game.base + " is the number of inputs you have...";
        squirglePhaseTwoTextTwelve = "...And you'll never encounter a shape with more than " + game.base + " VERTICES...";
        squirglePhaseTwoTextThirteen = "...4 is the default BASE, but others are unlockable...";
        squirglePhaseTwoTextFourteen = "...New BASES are unlocked by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in SQUIRGLE mode with your maximum BASE...";
        squirglePhaseTwoTextFifteen = "...Unlock BASE 7 by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in BASE 6, etc.";
        squirglePhaseTwoTextSixteen = "Note that there is no subtraction in SQUIRGLE, however...";
        squirglePhaseTwoTextSeventeen = "...so you use REMAINDERS to MATCH shapes smaller than your HAND.";
        squirglePhaseTwoTextEighteen = "A REMAINDER is what's leftover after EXCEEDING your BASE...";
        squirglePhaseTwoTextNineteen = "...Assume your BASE = 4, and your HAND = SQUARE...";
        squirglePhaseTwoTextTwenty = "...Next, assume you add a POINT to your HAND...";
        squirglePhaseTwoTextTwentyOne = "...You have now EXCEEDED your BASE by 1...";
        squirglePhaseTwoTextTwentyTwo = "...And your HAND now becomes that REMAINDER of 1...";
        squirglePhaseTwoTextTwentyThree = "...In other words, your HAND becomes a POINT.";
        squirglePhaseTwoTextTwentyFour = "Experiment with your inputs. Then, press NEXT when ready.";
        squirglePhaseThreeTextOne = "Look in the upper left corner of the screen...";
        squirglePhaseThreeTextTwo = "...You'll see a group of shapes and a number...";
        squirglePhaseThreeTextThree = "...The shape within the black circle is your FIRST TARGET...";
        squirglePhaseThreeTextFour = "...The shape outside the black circle is your SECOND TARGET...";
        squirglePhaseThreeTextFive = "...Together, these are called a TARGET SERIES...";
        squirglePhaseThreeTextSix = "...Whichever shape's alternating between colors is your CURRENT TARGET...";
        squirglePhaseThreeTextSeven = "...That's the one you currently need to make from your HAND...";
        squirglePhaseThreeTextEight = "...You'll do this by adding with the appropriate input.";
        squirglePhaseThreeTextNine = "The number represents your CURRENT TARGET's number of VERTICES...";
        squirglePhaseThreeTextTen = "...For clarity, this number also alternates between various colors...";
        squirglePhaseThreeTextEleven = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number.";
        squirglePhaseThreeTextTwelve = "Experiment with your inputs and TARGETS until you feel confident...";
        squirglePhaseThreeTextThirteen = "...Notice the equality between HAND and TARGETS upon correct input...";
        squirglePhaseThreeTextFourteen = "...Also notice the previously MATCHED shapes within or beside your HAND...";
        squirglePhaseThreeTextFifteen = "...These give you a better idea of your performance.";
        squirglePhaseThreeTextSixteen = "Press NEXT when ready.";
        squirglePhaseFourTextOne = "Look in the upper right corner of the screen...";
        squirglePhaseFourTextTwo = "...You'll see three numbers...";
        squirglePhaseFourTextThree = "...The leftmost number represents your HAND's number of VERTICES...";
        squirglePhaseFourTextFour = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number...";
        squirglePhaseFourTextFive = "...The middle number represents your SCORE...";
        squirglePhaseFourTextSix = "...The rightmost number (accompanied by an \"X\") represents your MULTIPLIER.";
        squirglePhaseFourTextSeven = "Your SCORE increases by your MULTIPLIER when MATCHING a full TARGET SERIES...";
        squirglePhaseFourTextEight = "...but pressing an incorrect input decreases your SCORE by 1.";
        squirglePhaseFourTextNine = "Your MULTIPLIER increases by 1 when MATCHING a full TARGET SERIES...";
        squirglePhaseFourTextTen = "...But it may only reach a maximum of 5...";
        squirglePhaseFourTextEleven = "...And pressing an incorrect input reverts your MULTIPLIER to 1.";
        squirglePhaseFourTextTwelve = "Experiment with how different inputs affect your SCORE and MULTIPLIER.";
        squirglePhaseFourTextThirteen = "Press NEXT when ready.";
        squirglePhaseFiveTextOne = "Look at the left side of the screen...";
        squirglePhaseFiveTextTwo = "...You'll see a group of SQUARES and LINES...";
        squirglePhaseFiveTextThree = "...The SQUARES (or SWATCHES) represent the forthcoming BACKGROUND colors...";
        squirglePhaseFiveTextFour = "...The three white LINES (or TIMELINES) represent remaining playtime...";
        squirglePhaseFiveTextFive = "...In this tutorial phase, only the first TIMELINE depletes.";
        squirglePhaseFiveTextSix = "When a SWATCH leaves the screen, its color becomes the new BACKGROUND...";
        squirglePhaseFiveTextSeven = "...When you MATCH a TARGET, that TARGET'S color becomes the BACKGROUND's...";
        squirglePhaseFiveTextEight = "...MATCH 2 TARGETS of the same color in a SERIES, and your next SERIES will be a SQUIRGLE.";
        squirglePhaseFiveTextNine = "The SQUIRGLE SERIES consists of a SQUARE and TRIANGLE separated by a CIRCLE...";
        squirglePhaseFiveTextTen = "...This special SERIES resets your TIMELINES when fully MATCHED...";
        squirglePhaseFiveTextEleven = "...Thus giving you more time to rack up points!";
        squirglePhaseFiveTextTwelve = "Practice using SWATCHES to get and MATCH SQUIRGLES.";
        squirglePhaseFiveTextThirteen = "Press NEXT to play the game in full.";

        battlePhaseOneTextOne = "Welcome to BATTLE's tutorial! Please press the right arrow.";
        battlePhaseOneTextTwo = "Look in the lower right corner of the screen...";
        battlePhaseOneTextThree = "...You may press [||] to pause or [?] to consult/dismiss help text.";
        battlePhaseOneTextFour = "Press [?] to dismiss this text, and survey the screen...";
        battlePhaseOneTextFive = "BATTLE's screen is divided between PLAYER 1 and PLAYER 2...";
        battlePhaseOneTextSix = "...The screen's bottom half is your PLAYER 1 section...";
        battlePhaseOneTextSeven = "...The screen's top half is your opponent's PLAYER 2 section.";
        battlePhaseOneTextEight = "Note the shape in the center of your PLAYER 1 section...";
        battlePhaseOneTextNine = "...That white, hollow shape is your HAND...";
        battlePhaseOneTextTen = "...You must manipulate your HAND to progress through BATTLE.";
        battlePhaseOneTextEleven = "To proceed, press the NEXT button below.";
        battlePhaseTwoTextOne = "At the bottom of the screen are your inputs...";
        battlePhaseTwoTextTwo = "...These are the buttons that manipulate your HAND...";
        battlePhaseTwoTextThree = "...You tap an input to add its VERTICES to your HAND.";
        battlePhaseTwoTextFour = "VERTICES are a shape's circular corners...";
        battlePhaseTwoTextFive = "...A POINT has 1, a LINE has 2, etc...";
        battlePhaseTwoTextSix = "...Therefore, POINT + LINE = TRIANGLE.";
        battlePhaseTwoTextSeven = "The goal of BATTLE is to create TARGET shapes from your HAND...";
        battlePhaseTwoTextEight = "...This is called MATCHING, and is done using addition...";
        battlePhaseTwoTextNine = "...But the way addition behaves depends on your BASE...";
        battlePhaseTwoTextTen = "...Right now, you're operating in a BASE of " + game.base + "...";
        battlePhaseTwoTextEleven = "...Hence, " + game.base + " is the number of inputs you have...";
        battlePhaseTwoTextTwelve = "...And you'll never encounter a shape with more than " + game.base + " VERTICES...";
        battlePhaseTwoTextThirteen = "...4 is the default BASE, but others are unlockable...";
        battlePhaseTwoTextFourteen = "...New BASES are unlocked by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in SQUIRGLE mode with your maximum BASE...";
        battlePhaseTwoTextFifteen = "...Unlock BASE 7 by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in BASE 6, etc.";
        battlePhaseTwoTextSixteen = "Note that there is no subtraction in BATTLE, however...";
        battlePhaseTwoTextSeventeen = "...so you use REMAINDERS to MATCH shapes smaller than your HAND.";
        battlePhaseTwoTextEighteen = "A REMAINDER is what's leftover after EXCEEDING your BASE...";
        battlePhaseTwoTextNineteen = "...Assume your BASE = 4, and your HAND = SQUARE...";
        battlePhaseTwoTextTwenty = "...Next, assume you add a POINT to your HAND...";
        battlePhaseTwoTextTwentyOne = "...You have now EXCEEDED your BASE by 1...";
        battlePhaseTwoTextTwentyTwo = "...And your HAND now becomes that REMAINDER of 1...";
        battlePhaseTwoTextTwentyThree = "...In other words, your HAND becomes a POINT.";
        battlePhaseTwoTextTwentyFour = "Experiment with your inputs. Then, press NEXT when ready.";
        battlePhaseThreeTextOne = "Look in the upper left corner of the PLAYER 1 section...";
        battlePhaseThreeTextTwo = "...You'll see a group of shapes and a number...";
        battlePhaseThreeTextThree = "...The shape within the black circle is your FIRST TARGET...";
        battlePhaseThreeTextFour = "...The shape outside the black circle is your SECOND TARGET...";
        battlePhaseThreeTextFive = "...Together, these are called a TARGET SERIES...";
        battlePhaseThreeTextSix = "...Whichever shape's alternating between colors is your CURRENT TARGET...";
        battlePhaseThreeTextSeven = "...That's the one you currently need to make from your HAND...";
        battlePhaseThreeTextEight = "...You'll do this by adding with the appropriate input.";
        battlePhaseThreeTextNine = "The number represents your CURRENT TARGET's number of VERTICES...";
        battlePhaseThreeTextTen = "...For clarity, this number also alternates between various colors...";
        battlePhaseThreeTextEleven = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number.";
        battlePhaseThreeTextTwelve = "Experiment with your inputs and TARGETS until you feel confident...";
        battlePhaseThreeTextThirteen = "...Notice the equality between HAND and TARGETS upon correct input...";
        battlePhaseThreeTextFourteen = "...Also notice the previously MATCHED shapes within or beside your HAND...";
        battlePhaseThreeTextFifteen = "...These give you a better idea of your performance.";
        battlePhaseThreeTextSixteen = "Press NEXT when ready.";
        battlePhaseFourTextOne = "Look in the upper right corner of the PLAYER 1 section...";
        battlePhaseFourTextTwo = "...You'll see \"P1\", a number, and a TRIANGLE with small notches...";
        battlePhaseFourTextThree = "...\"P1\" represents the player (P1 = PLAYER 1)";
        battlePhaseFourTextFour = "...The number represents your HAND's number of VERTICES...";
        battlePhaseFourTextFive = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number...";
        battlePhaseFourTextSix = "...The notched TRIANGLE is your BURST.";
        battlePhaseFourTextSeven = "MATCHING does not net you points in BATTLE mode...";
        battlePhaseFourTextEight = "...Instead, it fills your opponent's BURST...";
        battlePhaseFourTextNine = "...MATCHING a regular TARGET SERIES adds 1 to your opponent's BURST...";
        battlePhaseFourTextTen = "...But if you press an incorrect input, your BURST increases by 1.";
        battlePhaseFourTextEleven = "If your BURST reaches its threshold, you lose...";
        battlePhaseFourTextTwelve = "...If your opponent's BURST reaches its threshold, you win...";
        battlePhaseFourTextThirteen = "...If time runs out, the player with the lowest BURST wins...";
        battlePhaseFourTextFourteen = "...Equal BURSTS result in a tie.";
        battlePhaseFourTextFifteen = "...In this tutorial phase, neither player's BURST may exceed 5.";
        battlePhaseFourTextSixteen = "Experiment with how different inputs affect your BURST and your opponent's.";
        battlePhaseFourTextSeventeen = "Press NEXT when ready.";
        battlePhaseFiveTextOne = "Look at the left side of the PLAYER 1 section...";
        battlePhaseFiveTextTwo = "...You'll see a group of SQUARES and LINES...";
        battlePhaseFiveTextThree = "...The SQUARES (or SWATCHES) represent the forthcoming BACKGROUND colors...";
        battlePhaseFiveTextFour = "...The three white LINES (or TIMELINES) represent remaining playtime...";
        battlePhaseFiveTextFive = "...In this tutorial phase, only the first TIMELINE depletes.";
        battlePhaseFiveTextSix = "When a SWATCH leaves the screen, its color becomes the new BACKGROUND...";
        battlePhaseFiveTextSeven = "...When you MATCH a TARGET, that TARGET'S color becomes the BACKGROUND's...";
        battlePhaseFiveTextEight = "...MATCH 2 TARGETS of the same color in a SERIES, and your next SERIES will be a SQUIRGLE.";
        battlePhaseFiveTextNine = "The SQUIRGLE SERIES consists of a SQUARE and TRIANGLE separated by a CIRCLE...";
        battlePhaseFiveTextTen = "...MATCHING a SQUIRGLE adds 3 to your opponent's BURST...";
        battlePhaseFiveTextEleven = "...But it also removes 5 from your own!";
        battlePhaseFiveTextTwelve = "Practice using SWATCHES to get and MATCH SQUIRGLES.";
        battlePhaseFiveTextThirteen = "Press NEXT to play the game in full.";

        timeAttackPhaseOneTextOne = "Welcome to TIME ATTACK's tutorial! Please press the right arrow.";
        timeAttackPhaseOneTextTwo = "Look at the right side of the screen...";
        timeAttackPhaseOneTextThree = "...You may press [||] to pause or [?] to consult/dismiss help text.";
        timeAttackPhaseOneTextFour = "Press [?] to dismiss this text, and note the shape behind it...";
        timeAttackPhaseOneTextFive = "...That white, hollow shape is your HAND...";
        timeAttackPhaseOneTextSix = "...You must manipulate your HAND to progress through TIME ATTACK.";
        timeAttackPhaseOneTextSeven = "To proceed, press the NEXT button below.";
        timeAttackPhaseTwoTextOne = "At the bottom of the screen are your inputs...";
        timeAttackPhaseTwoTextTwo = "...These are the buttons that manipulate your HAND...";
        timeAttackPhaseTwoTextThree = "...You tap an input to add its VERTICES to your HAND.";
        timeAttackPhaseTwoTextFour = "VERTICES are a shape's circular corners...";
        timeAttackPhaseTwoTextFive = "...A POINT has 1, a LINE has 2, etc...";
        timeAttackPhaseTwoTextSix = "...Therefore, POINT + LINE = TRIANGLE.";
        timeAttackPhaseTwoTextSeven = "The goal of TIME ATTACK is to create TARGET shapes from your HAND...";
        timeAttackPhaseTwoTextEight = "...This is called MATCHING, and is done using addition...";
        timeAttackPhaseTwoTextNine = "...But the way addition behaves depends on your BASE...";
        timeAttackPhaseTwoTextTen = "...Right now, you're operating in a BASE of " + game.base + "...";
        timeAttackPhaseTwoTextEleven = "...Hence, " + game.base + " is the number of inputs you have...";
        timeAttackPhaseTwoTextTwelve = "...And you'll never encounter a shape with more than " + game.base + " VERTICES...";
        timeAttackPhaseTwoTextThirteen = "...4 is the default BASE, but others are unlockable...";
        timeAttackPhaseTwoTextFourteen = "...New BASES are unlocked by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in SQUIRGLE mode with your maximum BASE...";
        timeAttackPhaseTwoTextFifteen = "...Unlock BASE 7 by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in BASE 6, etc.";
        timeAttackPhaseTwoTextSixteen = "Note that there is no subtraction in TIME ATTACK, however...";
        timeAttackPhaseTwoTextSeventeen = "...so you use REMAINDERS to MATCH shapes smaller than your HAND.";
        timeAttackPhaseTwoTextEighteen = "A REMAINDER is what's leftover after EXCEEDING your BASE...";
        timeAttackPhaseTwoTextNineteen = "...Assume your BASE = 4, and your HAND = SQUARE...";
        timeAttackPhaseTwoTextTwenty = "...Next, assume you add a POINT to your HAND...";
        timeAttackPhaseTwoTextTwentyOne = "...You have now EXCEEDED your BASE by 1...";
        timeAttackPhaseTwoTextTwentyTwo = "...And your HAND now becomes that REMAINDER of 1...";
        timeAttackPhaseTwoTextTwentyThree = "...In other words, your HAND becomes a POINT.";
        timeAttackPhaseTwoTextTwentyFour = "Experiment with your inputs. Then, press NEXT when ready.";
        timeAttackPhaseThreeTextOne = "Look in the upper left corner of the screen...";
        timeAttackPhaseThreeTextTwo = "...You'll see a group of shapes and a number...";
        timeAttackPhaseThreeTextThree = "...The shape within the black circle is your FIRST TARGET...";
        timeAttackPhaseThreeTextFour = "...The shape outside the black circle is your SECOND TARGET...";
        timeAttackPhaseThreeTextFive = "...Together, these are called a TARGET SERIES...";
        timeAttackPhaseThreeTextSix = "...Whichever shape's alternating between colors is your CURRENT TARGET...";
        timeAttackPhaseThreeTextSeven = "...That's the one you currently need to make from your HAND...";
        timeAttackPhaseThreeTextEight = "...You'll do this by adding with the appropriate input.";
        timeAttackPhaseThreeTextNine = "The number represents your CURRENT TARGET's number of VERTICES...";
        timeAttackPhaseThreeTextTen = "...For clarity, this number also alternates between various colors...";
        timeAttackPhaseThreeTextEleven = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number.";
        timeAttackPhaseThreeTextTwelve = "Experiment with your inputs and TARGETS until you feel confident...";
        timeAttackPhaseThreeTextThirteen = "...Notice the equality between HAND and TARGETS upon correct input...";
        timeAttackPhaseThreeTextFourteen = "...Also notice the previously MATCHED shapes within or beside your HAND...";
        timeAttackPhaseThreeTextFifteen = "...These give you a better idea of your performance.";
        timeAttackPhaseThreeTextSixteen = "Press NEXT when ready.";
        timeAttackPhaseFourTextOne = "Look in the upper right corner of the screen...";
        timeAttackPhaseFourTextTwo = "...You'll see three numbers...";
        timeAttackPhaseFourTextThree = "...The leftmost number represents your HAND's number of VERTICES...";
        timeAttackPhaseFourTextFour = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number...";
        timeAttackPhaseFourTextFive = "...The middle number represents your SCORE...";
        timeAttackPhaseFourTextSix = "...The rightmost number (accompanied by an \"X\") represents your MULTIPLIER.";
        timeAttackPhaseFourTextSeven = "Your SCORE increases by your MULTIPLIER when MATCHING a full TARGET SERIES...";
        timeAttackPhaseFourTextEight = "...but pressing an incorrect input decreases your SCORE by 1.";
        timeAttackPhaseFourTextNine = "Your MULTIPLIER increases by 1 when MATCHING a full TARGET SERIES...";
        timeAttackPhaseFourTextTen = "...But it may only reach a maximum of 5...";
        timeAttackPhaseFourTextEleven = "...And pressing an incorrect input reverts your MULTIPLIER to 1.";
        timeAttackPhaseFourTextTwelve = "Experiment with how different inputs affect your SCORE and MULTIPLIER.";
        timeAttackPhaseFourTextThirteen = "Press NEXT when ready.";
        timeAttackPhaseFiveTextOne = "Look at the left side of the screen...";
        timeAttackPhaseFiveTextTwo = "...You'll see a group of LINES...";
        timeAttackPhaseFiveTextThree = "...These three white LINES (or TIMELINES) represent remaining playtime...";
        timeAttackPhaseFiveTextFour = "...In this tutorial phase, only the first TIMELINE depletes.";
        timeAttackPhaseFiveTextFive = "Note that there are no SWATCHES in TIME ATTACK...";
        timeAttackPhaseFiveTextSix = "...Thus, SQUIRGLES are not special SERIES...";
        timeAttackPhaseFiveTextSeven = "...In fact, they'll appear and behave like any other.";
        timeAttackPhaseFiveTextEight = "Press NEXT to play the game in full.";

        timeBattlePhaseOneTextOne = "Welcome to TIME BATTLE's tutorial! Please press the right arrow.";
        timeBattlePhaseOneTextTwo = "Look in the lower right corner of the screen...";
        timeBattlePhaseOneTextThree = "...You may press [||] to pause or [?] to consult/dismiss help text.";
        timeBattlePhaseOneTextFour = "Press [?] to dismiss this text, and survey the screen...";
        timeBattlePhaseOneTextFive = "TIME BATTLE's screen is divided between PLAYER 1 and PLAYER 2...";
        timeBattlePhaseOneTextSix = "...The screen's bottom half is your PLAYER 1 section...";
        timeBattlePhaseOneTextSeven = "...The screen's top half is your opponent's PLAYER 2 section.";
        timeBattlePhaseOneTextEight = "Note the shape in the center of your PLAYER 1 section...";
        timeBattlePhaseOneTextNine = "...That white, hollow shape is your HAND...";
        timeBattlePhaseOneTextTen = "...You must manipulate your HAND to progress through BATTLE.";
        timeBattlePhaseOneTextEleven = "To proceed, press the NEXT button below.";
        timeBattlePhaseTwoTextOne = "At the bottom of the screen are your inputs...";
        timeBattlePhaseTwoTextTwo = "...These are the buttons that manipulate your HAND...";
        timeBattlePhaseTwoTextThree = "...You tap an input to add its VERTICES to your HAND.";
        timeBattlePhaseTwoTextFour = "VERTICES are a shape's circular corners...";
        timeBattlePhaseTwoTextFive = "...A POINT has 1, a LINE has 2, etc...";
        timeBattlePhaseTwoTextSix = "...Therefore, POINT + LINE = TRIANGLE.";
        timeBattlePhaseTwoTextSeven = "The goal of TIME BATTLE is to create TARGET shapes from your HAND...";
        timeBattlePhaseTwoTextEight = "...This is called MATCHING, and is done using addition...";
        timeBattlePhaseTwoTextNine = "...But the way addition behaves depends on your BASE...";
        timeBattlePhaseTwoTextTen = "...Right now, you're operating in a BASE of " + game.base + "...";
        timeBattlePhaseTwoTextEleven = "...Hence, " + game.base + " is the number of inputs you have...";
        timeBattlePhaseTwoTextTwelve = "...And you'll never encounter a shape with more than " + game.base + " VERTICES...";
        timeBattlePhaseTwoTextThirteen = "...4 is the default BASE, but others are unlockable...";
        timeBattlePhaseTwoTextFourteen = "...New BASES are unlocked by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in SQUIRGLE mode with your maximum BASE...";
        timeBattlePhaseTwoTextFifteen = "...Unlock BASE 7 by scoring " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " points in BASE 6, etc.";
        timeBattlePhaseTwoTextSixteen = "Note that there is no subtraction in TIME BATTLE, however...";
        timeBattlePhaseTwoTextSeventeen = "...so you use REMAINDERS to MATCH shapes smaller than your HAND.";
        timeBattlePhaseTwoTextEighteen = "A REMAINDER is what's leftover after EXCEEDING your BASE...";
        timeBattlePhaseTwoTextNineteen = "...Assume your BASE = 4, and your HAND = SQUARE...";
        timeBattlePhaseTwoTextTwenty = "...Next, assume you add a POINT to your HAND...";
        timeBattlePhaseTwoTextTwentyOne = "...You have now EXCEEDED your BASE by 1...";
        timeBattlePhaseTwoTextTwentyTwo = "...And your HAND now becomes that REMAINDER of 1...";
        timeBattlePhaseTwoTextTwentyThree = "...In other words, your HAND becomes a POINT.";
        timeBattlePhaseTwoTextTwentyFour = "Experiment with your inputs. Then, press NEXT when ready.";
        timeBattlePhaseThreeTextOne = "Look in the upper left corner of the PLAYER 1 section...";
        timeBattlePhaseThreeTextTwo = "...You'll see a group of shapes and a number...";
        timeBattlePhaseThreeTextThree = "...The shape within the black circle is your FIRST TARGET...";
        timeBattlePhaseThreeTextFour = "...The shape outside the black circle is your SECOND TARGET...";
        timeBattlePhaseThreeTextFive = "...Together, these are called a TARGET SERIES...";
        timeBattlePhaseThreeTextSix = "...Whichever shape's alternating between colors is your CURRENT TARGET...";
        timeBattlePhaseThreeTextSeven = "...That's the one you currently need to make from your HAND...";
        timeBattlePhaseThreeTextEight = "...You'll do this by adding with the appropriate input.";
        timeBattlePhaseThreeTextNine = "The number represents your CURRENT TARGET's number of VERTICES...";
        timeBattlePhaseThreeTextTen = "...For clarity, this number also alternates between various colors...";
        timeBattlePhaseThreeTextEleven = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number.";
        timeBattlePhaseThreeTextTwelve = "Experiment with your inputs and TARGETS until you feel confident...";
        timeBattlePhaseThreeTextThirteen = "...Notice the equality between HAND and TARGETS upon correct input...";
        timeBattlePhaseThreeTextFourteen = "...Also notice the previously MATCHED shapes within or beside your HAND...";
        timeBattlePhaseThreeTextFifteen = "...These give you a better idea of your performance.";
        timeBattlePhaseThreeTextSixteen = "Press NEXT when ready.";
        timeBattlePhaseFourTextOne = "Look in the upper right corner of the PLAYER 1 section...";
        timeBattlePhaseFourTextTwo = "...You'll see \"P1\" and three numbers...";
        timeBattlePhaseFourTextThree = "...\"P1\" represents the player (P1 = PLAYER 1)";
        timeBattlePhaseFourTextFour = "...The leftmost number represents your HAND's number of VERTICES...";
        timeBattlePhaseFourTextFive = "...You may activate HARDCORE mode from the OPTIONS menu to hide this number...";
        timeBattlePhaseFourTextSix = "...The middle number represents your SCORE...";
        timeBattlePhaseFourTextSeven = "...The rightmost number (accompanied by an \"X\") represents your MULTIPLIER.";
        timeBattlePhaseFourTextEight = "Your SCORE increases by your MULTIPLIER when MATCHING a full TARGET SERIES...";
        timeBattlePhaseFourTextNine = "...but pressing an incorrect input decreases your SCORE by 1.";
        timeBattlePhaseFourTextTen = "Your MULTIPLIER increases by 1 when MATCHING a full TARGET SERIES...";
        timeBattlePhaseFourTextEleven = "...But it may only reach a maximum of 5...";
        timeBattlePhaseFourTextTwelve = "...And pressing an incorrect input reverts your MULTIPLIER to 1.";
        timeBattlePhaseFourTextThirteen = "Whichever player has the highest SCORE when time runs out wins...";
        timeBattlePhaseFourTextFourteen = "...Equal SCORES result in a tie.";
        timeBattlePhaseFourTextFifteen = "Experiment with how different inputs affect your SCORE and MULTIPLIER.";
        timeBattlePhaseFourTextSixteen = "Press NEXT when ready.";
        timeBattlePhaseFiveTextOne = "Look at the left side of the PLAYER 1 section...";
        timeBattlePhaseFiveTextTwo = "...You'll see a group of LINES...";
        timeBattlePhaseFiveTextThree = "...These three white LINES (or TIMELINES) represent remaining playtime...";
        timeBattlePhaseFiveTextFour = "...In this tutorial phase, only the first TIMELINE depletes.";
        timeBattlePhaseFiveTextFive = "Note that there are no SWATCHES in TIME ATTACK...";
        timeBattlePhaseFiveTextSix = "...Thus, SQUIRGLES are not special SERIES...";
        timeBattlePhaseFiveTextSeven = "...In fact, they'll appear and behave like any other.";
        timeBattlePhaseFiveTextEight = "Press NEXT to play the game in full.";

        squirgleHelpTextPhaseOneList = new ArrayList<String>();
        squirgleHelpTextPhaseTwoList = new ArrayList<String>();
        squirgleHelpTextPhaseThreeList = new ArrayList<String>();
        squirgleHelpTextPhaseFourList = new ArrayList<String>();
        squirgleHelpTextPhaseFiveList = new ArrayList<String>();

        battleHelpTextPhaseOneList = new ArrayList<String>();
        battleHelpTextPhaseTwoList = new ArrayList<String>();
        battleHelpTextPhaseThreeList = new ArrayList<String>();
        battleHelpTextPhaseFourList = new ArrayList<String>();
        battleHelpTextPhaseFiveList = new ArrayList<String>();

        timeAttackHelpTextPhaseOneList = new ArrayList<String>();
        timeAttackHelpTextPhaseTwoList = new ArrayList<String>();
        timeAttackHelpTextPhaseThreeList = new ArrayList<String>();
        timeAttackHelpTextPhaseFourList = new ArrayList<String>();
        timeAttackHelpTextPhaseFiveList = new ArrayList<String>();

        timeBattleHelpTextPhaseOneList = new ArrayList<String>();
        timeBattleHelpTextPhaseTwoList = new ArrayList<String>();
        timeBattleHelpTextPhaseThreeList = new ArrayList<String>();
        timeBattleHelpTextPhaseFourList = new ArrayList<String>();
        timeBattleHelpTextPhaseFiveList = new ArrayList<String>();

        squirglePhaseMap = new HashMap<Integer, List<String>>();
        battlePhaseMap = new HashMap<Integer, List<String>>();
        timeAttackPhaseMap = new HashMap<Integer, List<String>>();
        timeBattlePhaseMap = new HashMap<Integer, List<String>>();
        helpTextMap = new HashMap<Integer, Map<Integer, List<String>>>();

        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextOne);
        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextTwo);
        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextThree);
        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextFour);
        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextFive);
        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextSix);
        squirgleHelpTextPhaseOneList.add(squirglePhaseOneTextSeven);

        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextOne);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwo);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextThree);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextFour);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextFive);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextSix);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextSeven);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextEight);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextNine);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextEleven);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwelve);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextThirteen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextFourteen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextFifteen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextSixteen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextSeventeen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextEighteen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextNineteen);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwenty);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwentyOne);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwentyTwo);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwentyThree);
        squirgleHelpTextPhaseTwoList.add(squirglePhaseTwoTextTwentyFour);

        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextOne);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextTwo);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextThree);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextFour);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextFive);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextSix);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextSeven);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextEight);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextNine);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextTen);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextEleven);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextTwelve);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextThirteen);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextFourteen);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextFifteen);
        squirgleHelpTextPhaseThreeList.add(squirglePhaseThreeTextSixteen);

        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextOne);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextTwo);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextThree);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextFour);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextFive);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextSix);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextSeven);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextEight);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextNine);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextTen);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextEleven);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextTwelve);
        squirgleHelpTextPhaseFourList.add(squirglePhaseFourTextThirteen);

        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextOne);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextTwo);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextThree);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextFour);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextFive);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextSix);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextSeven);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextEight);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextNine);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextTen);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextEleven);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextTwelve);
        squirgleHelpTextPhaseFiveList.add(squirglePhaseFiveTextThirteen);

        battleHelpTextPhaseOneList.add(battlePhaseOneTextOne);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextTwo);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextThree);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextFour);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextFive);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextSix);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextSeven);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextEight);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextNine);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextTen);
        battleHelpTextPhaseOneList.add(battlePhaseOneTextEleven);

        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextOne);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwo);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextThree);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextFour);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextFive);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextSix);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextSeven);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextEight);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextNine);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextEleven);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwelve);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextThirteen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextFourteen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextFifteen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextSixteen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextSeventeen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextEighteen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextNineteen);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwenty);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwentyOne);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwentyTwo);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwentyThree);
        battleHelpTextPhaseTwoList.add(battlePhaseTwoTextTwentyFour);

        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextOne);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextTwo);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextThree);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextFour);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextFive);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextSix);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextSeven);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextEight);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextNine);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextTen);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextEleven);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextTwelve);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextThirteen);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextFourteen);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextFifteen);
        battleHelpTextPhaseThreeList.add(battlePhaseThreeTextSixteen);

        battleHelpTextPhaseFourList.add(battlePhaseFourTextOne);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextTwo);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextThree);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextFour);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextFive);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextSix);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextSeven);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextEight);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextNine);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextTen);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextEleven);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextTwelve);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextThirteen);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextFourteen);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextFifteen);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextSixteen);
        battleHelpTextPhaseFourList.add(battlePhaseFourTextSeventeen);

        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextOne);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextTwo);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextThree);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextFour);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextFive);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextSix);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextSeven);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextEight);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextNine);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextTen);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextEleven);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextTwelve);
        battleHelpTextPhaseFiveList.add(battlePhaseFiveTextThirteen);

        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextOne);
        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextTwo);
        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextThree);
        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextFour);
        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextFive);
        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextSix);
        timeAttackHelpTextPhaseOneList.add(timeAttackPhaseOneTextSeven);

        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextOne);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwo);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextThree);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextFour);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextFive);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextSix);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextSeven);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextEight);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextNine);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextEleven);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwelve);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextThirteen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextFourteen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextFifteen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextSixteen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextSeventeen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextEighteen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextNineteen);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwenty);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwentyOne);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwentyTwo);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwentyThree);
        timeAttackHelpTextPhaseTwoList.add(timeAttackPhaseTwoTextTwentyFour);

        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextOne);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextTwo);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextThree);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextFour);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextFive);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextSix);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextSeven);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextEight);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextNine);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextTen);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextEleven);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextTwelve);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextThirteen);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextFourteen);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextFifteen);
        timeAttackHelpTextPhaseThreeList.add(timeAttackPhaseThreeTextSixteen);

        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextOne);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextTwo);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextThree);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextFour);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextFive);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextSix);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextSeven);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextEight);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextNine);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextTen);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextEleven);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextTwelve);
        timeAttackHelpTextPhaseFourList.add(timeAttackPhaseFourTextThirteen);

        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextOne);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextTwo);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextThree);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextFour);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextFive);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextSix);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextSeven);
        timeAttackHelpTextPhaseFiveList.add(timeAttackPhaseFiveTextEight);

        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextOne);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextTwo);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextThree);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextFour);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextFive);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextSix);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextSeven);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextEight);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextNine);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextTen);
        timeBattleHelpTextPhaseOneList.add(timeBattlePhaseOneTextEleven);

        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextOne);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwo);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextThree);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextFour);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextFive);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextSix);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextSeven);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextEight);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextNine);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextEleven);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwelve);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextThirteen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextFourteen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextFifteen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextSixteen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextSeventeen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextEighteen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextNineteen);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwenty);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwentyOne);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwentyTwo);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwentyThree);
        timeBattleHelpTextPhaseTwoList.add(timeBattlePhaseTwoTextTwentyFour);

        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextOne);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextTwo);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextThree);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextFour);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextFive);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextSix);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextSeven);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextEight);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextNine);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextTen);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextEleven);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextTwelve);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextThirteen);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextFourteen);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextFifteen);
        timeBattleHelpTextPhaseThreeList.add(timeBattlePhaseThreeTextSixteen);

        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextOne);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextTwo);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextThree);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextFour);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextFive);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextSix);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextSeven);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextEight);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextNine);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextTen);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextEleven);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextTwelve);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextThirteen);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextFourteen);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextFifteen);
        timeBattleHelpTextPhaseFourList.add(timeBattlePhaseFourTextSixteen);

        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextOne);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextTwo);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextThree);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextFour);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextFive);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextSix);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextSeven);
        timeBattleHelpTextPhaseFiveList.add(timeBattlePhaseFiveTextEight);

        squirglePhaseMap.put(PHASE_ONE, squirgleHelpTextPhaseOneList);
        squirglePhaseMap.put(PHASE_TWO, squirgleHelpTextPhaseTwoList);
        squirglePhaseMap.put(PHASE_THREE, squirgleHelpTextPhaseThreeList);
        squirglePhaseMap.put(PHASE_FOUR, squirgleHelpTextPhaseFourList);
        squirglePhaseMap.put(PHASE_FIVE, squirgleHelpTextPhaseFiveList);

        battlePhaseMap.put(PHASE_ONE, battleHelpTextPhaseOneList);
        battlePhaseMap.put(PHASE_TWO, battleHelpTextPhaseTwoList);
        battlePhaseMap.put(PHASE_THREE, battleHelpTextPhaseThreeList);
        battlePhaseMap.put(PHASE_FOUR, battleHelpTextPhaseFourList);
        battlePhaseMap.put(PHASE_FIVE, battleHelpTextPhaseFiveList);

        timeAttackPhaseMap.put(PHASE_ONE, timeAttackHelpTextPhaseOneList);
        timeAttackPhaseMap.put(PHASE_TWO, timeAttackHelpTextPhaseTwoList);
        timeAttackPhaseMap.put(PHASE_THREE, timeAttackHelpTextPhaseThreeList);
        timeAttackPhaseMap.put(PHASE_FOUR, timeAttackHelpTextPhaseFourList);
        timeAttackPhaseMap.put(PHASE_FIVE, timeAttackHelpTextPhaseFiveList);

        timeBattlePhaseMap.put(PHASE_ONE, timeBattleHelpTextPhaseOneList);
        timeBattlePhaseMap.put(PHASE_TWO, timeBattleHelpTextPhaseTwoList);
        timeBattlePhaseMap.put(PHASE_THREE, timeBattleHelpTextPhaseThreeList);
        timeBattlePhaseMap.put(PHASE_FOUR, timeBattleHelpTextPhaseFourList);
        timeBattlePhaseMap.put(PHASE_FIVE, timeBattleHelpTextPhaseFiveList);

        helpTextMap.put(Squirgle.GAMEPLAY_SQUIRGLE, squirglePhaseMap);
        helpTextMap.put(Squirgle.GAMEPLAY_BATTLE, battlePhaseMap);
        helpTextMap.put(Squirgle.GAMEPLAY_TIME_ATTACK, timeAttackPhaseMap);
        helpTextMap.put(Squirgle.GAMEPLAY_TIME_BATTLE, timeBattlePhaseMap);

        game.setUpFontTutorialHelp(MathUtils.round(game.ASPECT_RATIO * ((1920 / 1080) * FONT_TUTORIAL_HELP_SIZE_MULTIPLIER) / (1920 / 1080))); //Using 1920 / 1080 because that's the OG resolution--the one for which I originally developed--and I wish to scale it for other devices.

        helpLabelStyle = new Label.LabelStyle();
        helpLabelStyle.font = game.fontTutorialHelp;
        helpLabelStyle.fontColor = blackAndWhite ? Color.BLACK : Color.WHITE;
        helpLabel = new Label(getCurrentHelpText(), helpLabelStyle);
        helpLabel.setSize((3 * game.camera.viewportWidth) / 8, (game.camera.viewportHeight / 2) - (game.camera.viewportWidth / 16));
        helpLabel.setPosition((5 * game.camera.viewportWidth) / 16, (game.camera.viewportHeight / 4) + (game.camera.viewportWidth / 16));
        helpLabel.setAlignment(Align.topLeft);
        helpLabel.setWrap(true);
        helpLabel.setVisible(helpTextVisible && !paused);

        stage = new Stage(game.viewport);
        stage.addActor(helpLabel);

        veilColor = Color.WHITE;
        veilOpacity = 0;
    }

    public void setUpGL() {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
