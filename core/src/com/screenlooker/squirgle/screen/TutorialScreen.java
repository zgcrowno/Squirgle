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
    public final Integer gameplayType;

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
    public static float PAUSE_INPUT_HEIGHT_MIDDLE;
    public static float PAUSE_INPUT_HEIGHT_BACK;
    public static float PAUSE_INPUT_RADIUS;
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
    private final static int PAUSE_REPLAY = 2;

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

    public final static String TAP = "Tap";
    public final static String CLICK = "Click";
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
    boolean playTouched;
    boolean homeTouched;
    boolean exitTouched;
    boolean pauseTouched;
    boolean pauseBackTouched;
    boolean pauseQuitTouched;
    boolean pauseReplayTouched;
    boolean inputTouchedGameplay;
    boolean inputTouchedGameplayP1;
    boolean inputTouchedGameplayP2;
    boolean inputTouchedResults;
    private Color clearColor;
    private int phase;
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
    private Stage stage;
    private Color veilColor;
    private float veilOpacity;

    private int numPhases;

    private Boolean correctInput;

    private String tapOrClick;

    private Label.LabelStyle labelStyle;
    private Label headerLabel;
    private Label footerLabel;

    private String squirgleHeaderCorrectPhase1;
    private String squirgleHeaderCorrectPhase2;
    private String squirgleHeaderCorrectPhase3;
    private String squirgleHeaderCorrectPhase4;
    private String squirgleHeaderCorrectPhase5;
    private String squirgleHeaderCorrectPhase6;
    private String squirgleHeaderCorrectPhase7;
    private String squirgleHeaderCorrectPhase8;
    private String squirgleHeaderCorrectPhase9;
    private String squirgleHeaderCorrectPhase10;
    private String squirgleHeaderCorrectPhase11;
    private String squirgleHeaderCorrectPhase12;
    private String squirgleHeaderCorrectPhase13;
    private String squirgleHeaderCorrectPhase14;
    private String squirgleHeaderCorrectPhase15;
    private String squirgleHeaderCorrectPhase16;
    private String squirgleHeaderCorrectPhase17;
    private String squirgleHeaderCorrectPhase18;
    private String squirgleHeaderCorrectPhase19;
    private String squirgleHeaderCorrectPhase20;
    private String squirgleHeaderCorrectPhase21;
    private String squirgleHeaderCorrectPhase22;
    private String squirgleHeaderCorrectPhase23;
    private String squirgleHeaderCorrectPhase24;
    private String squirgleHeaderCorrectPhase25;
    private String squirgleHeaderCorrectPhase26;
    private String squirgleHeaderCorrectPhase27;
    private String squirgleHeaderCorrectPhase28;
    private String squirgleHeaderCorrectPhase29;
    private String squirgleHeaderCorrectPhase30;
    private String squirgleHeaderCorrectPhase31;
    private String squirgleHeaderCorrectPhase32;
    private String squirgleHeaderCorrectPhase33;
    private String squirgleHeaderCorrectPhase34;
    private String squirgleHeaderCorrectPhase35;
    private String squirgleHeaderCorrectPhase36;
    private String squirgleHeaderCorrectPhase37;
    private String squirgleHeaderCorrectPhase38;
    private String squirgleHeaderCorrectPhase39;
    private String squirgleHeaderCorrectPhase40;
    private String squirgleHeaderCorrectPhase41;
    private String squirgleHeaderCorrectPhase42;
    private String squirgleHeaderCorrectPhase43;
    private String squirgleHeaderCorrectPhase44;
    private String squirgleHeaderCorrectPhase45;

    private String squirgleHeaderIncorrectPhase1;
    private String squirgleHeaderIncorrectPhase2;
    private String squirgleHeaderIncorrectPhase3;
    private String squirgleHeaderIncorrectPhase4;
    private String squirgleHeaderIncorrectPhase5;
    private String squirgleHeaderIncorrectPhase6;
    private String squirgleHeaderIncorrectPhase7;
    private String squirgleHeaderIncorrectPhase8;
    private String squirgleHeaderIncorrectPhase9;
    private String squirgleHeaderIncorrectPhase10;
    private String squirgleHeaderIncorrectPhase11;
    private String squirgleHeaderIncorrectPhase12;
    private String squirgleHeaderIncorrectPhase13;
    private String squirgleHeaderIncorrectPhase14;
    private String squirgleHeaderIncorrectPhase15;
    private String squirgleHeaderIncorrectPhase16;
    private String squirgleHeaderIncorrectPhase17;
    private String squirgleHeaderIncorrectPhase18;
    private String squirgleHeaderIncorrectPhase19;
    private String squirgleHeaderIncorrectPhase20;
    private String squirgleHeaderIncorrectPhase21;
    private String squirgleHeaderIncorrectPhase22;
    private String squirgleHeaderIncorrectPhase23;
    private String squirgleHeaderIncorrectPhase24;
    private String squirgleHeaderIncorrectPhase25;
    private String squirgleHeaderIncorrectPhase26;
    private String squirgleHeaderIncorrectPhase27;
    private String squirgleHeaderIncorrectPhase28;
    private String squirgleHeaderIncorrectPhase29;
    private String squirgleHeaderIncorrectPhase30;
    private String squirgleHeaderIncorrectPhase31;
    private String squirgleHeaderIncorrectPhase32;
    private String squirgleHeaderIncorrectPhase33;
    private String squirgleHeaderIncorrectPhase34;
    private String squirgleHeaderIncorrectPhase35;
    private String squirgleHeaderIncorrectPhase36;
    private String squirgleHeaderIncorrectPhase37;
    private String squirgleHeaderIncorrectPhase38;
    private String squirgleHeaderIncorrectPhase39;
    private String squirgleHeaderIncorrectPhase40;
    private String squirgleHeaderIncorrectPhase41;
    private String squirgleHeaderIncorrectPhase42;
    private String squirgleHeaderIncorrectPhase43;
    private String squirgleHeaderIncorrectPhase44;
    private String squirgleHeaderIncorrectPhase45;

    private String squirgleFooterPhase1;
    private String squirgleFooterPhase2;
    private String squirgleFooterPhase3;
    private String squirgleFooterPhase4;
    private String squirgleFooterPhase5;
    private String squirgleFooterPhase6;
    private String squirgleFooterPhase7;
    private String squirgleFooterPhase8;
    private String squirgleFooterPhase9;
    private String squirgleFooterPhase10;
    private String squirgleFooterPhase11;
    private String squirgleFooterPhase12;
    private String squirgleFooterPhase13;
    private String squirgleFooterPhase14;
    private String squirgleFooterPhase15;
    private String squirgleFooterPhase16;
    private String squirgleFooterPhase17;
    private String squirgleFooterPhase18;
    private String squirgleFooterPhase19;
    private String squirgleFooterPhase20;
    private String squirgleFooterPhase21;
    private String squirgleFooterPhase22;
    private String squirgleFooterPhase23;
    private String squirgleFooterPhase24;
    private String squirgleFooterPhase25;
    private String squirgleFooterPhase26;
    private String squirgleFooterPhase27;
    private String squirgleFooterPhase28;
    private String squirgleFooterPhase29;
    private String squirgleFooterPhase30;
    private String squirgleFooterPhase31;
    private String squirgleFooterPhase32;
    private String squirgleFooterPhase33;
    private String squirgleFooterPhase34;
    private String squirgleFooterPhase35;
    private String squirgleFooterPhase36;
    private String squirgleFooterPhase37;
    private String squirgleFooterPhase38;
    private String squirgleFooterPhase39;
    private String squirgleFooterPhase40;
    private String squirgleFooterPhase41;
    private String squirgleFooterPhase42;
    private String squirgleFooterPhase43;
    private String squirgleFooterPhase44;
    private String squirgleFooterPhase45;

    private String battleHeaderCorrectPhase1;
    private String battleHeaderCorrectPhase2;
    private String battleHeaderCorrectPhase3;
    private String battleHeaderCorrectPhase4;
    private String battleHeaderCorrectPhase5;
    private String battleHeaderCorrectPhase6;
    private String battleHeaderCorrectPhase7;
    private String battleHeaderCorrectPhase8;
    private String battleHeaderCorrectPhase9;
    private String battleHeaderCorrectPhase10;
    private String battleHeaderCorrectPhase11;
    private String battleHeaderCorrectPhase12;
    private String battleHeaderCorrectPhase13;
    private String battleHeaderCorrectPhase14;
    private String battleHeaderCorrectPhase15;
    private String battleHeaderCorrectPhase16;
    private String battleHeaderCorrectPhase17;
    private String battleHeaderCorrectPhase18;
    private String battleHeaderCorrectPhase19;
    private String battleHeaderCorrectPhase20;
    private String battleHeaderCorrectPhase21;
    private String battleHeaderCorrectPhase22;
    private String battleHeaderCorrectPhase23;
    private String battleHeaderCorrectPhase24;
    private String battleHeaderCorrectPhase25;
    private String battleHeaderCorrectPhase26;
    private String battleHeaderCorrectPhase27;
    private String battleHeaderCorrectPhase28;
    private String battleHeaderCorrectPhase29;
    private String battleHeaderCorrectPhase30;
    private String battleHeaderCorrectPhase31;
    private String battleHeaderCorrectPhase32;
    private String battleHeaderCorrectPhase33;
    private String battleHeaderCorrectPhase34;
    private String battleHeaderCorrectPhase35;
    private String battleHeaderCorrectPhase36;
    private String battleHeaderCorrectPhase37;
    private String battleHeaderCorrectPhase38;
    private String battleHeaderCorrectPhase39;
    private String battleHeaderCorrectPhase40;
    private String battleHeaderCorrectPhase41;
    private String battleHeaderCorrectPhase42;
    private String battleHeaderCorrectPhase43;
    private String battleHeaderCorrectPhase44;
    private String battleHeaderCorrectPhase45;

    private String battleHeaderIncorrectPhase1;
    private String battleHeaderIncorrectPhase2;
    private String battleHeaderIncorrectPhase3;
    private String battleHeaderIncorrectPhase4;
    private String battleHeaderIncorrectPhase5;
    private String battleHeaderIncorrectPhase6;
    private String battleHeaderIncorrectPhase7;
    private String battleHeaderIncorrectPhase8;
    private String battleHeaderIncorrectPhase9;
    private String battleHeaderIncorrectPhase10;
    private String battleHeaderIncorrectPhase11;
    private String battleHeaderIncorrectPhase12;
    private String battleHeaderIncorrectPhase13;
    private String battleHeaderIncorrectPhase14;
    private String battleHeaderIncorrectPhase15;
    private String battleHeaderIncorrectPhase16;
    private String battleHeaderIncorrectPhase17;
    private String battleHeaderIncorrectPhase18;
    private String battleHeaderIncorrectPhase19;
    private String battleHeaderIncorrectPhase20;
    private String battleHeaderIncorrectPhase21;
    private String battleHeaderIncorrectPhase22;
    private String battleHeaderIncorrectPhase23;
    private String battleHeaderIncorrectPhase24;
    private String battleHeaderIncorrectPhase25;
    private String battleHeaderIncorrectPhase26;
    private String battleHeaderIncorrectPhase27;
    private String battleHeaderIncorrectPhase28;
    private String battleHeaderIncorrectPhase29;
    private String battleHeaderIncorrectPhase30;
    private String battleHeaderIncorrectPhase31;
    private String battleHeaderIncorrectPhase32;
    private String battleHeaderIncorrectPhase33;
    private String battleHeaderIncorrectPhase34;
    private String battleHeaderIncorrectPhase35;
    private String battleHeaderIncorrectPhase36;
    private String battleHeaderIncorrectPhase37;
    private String battleHeaderIncorrectPhase38;
    private String battleHeaderIncorrectPhase39;
    private String battleHeaderIncorrectPhase40;
    private String battleHeaderIncorrectPhase41;
    private String battleHeaderIncorrectPhase42;
    private String battleHeaderIncorrectPhase43;
    private String battleHeaderIncorrectPhase44;
    private String battleHeaderIncorrectPhase45;

    private String battleFooterPhase1;
    private String battleFooterPhase2;
    private String battleFooterPhase3;
    private String battleFooterPhase4;
    private String battleFooterPhase5;
    private String battleFooterPhase6;
    private String battleFooterPhase7;
    private String battleFooterPhase8;
    private String battleFooterPhase9;
    private String battleFooterPhase10;
    private String battleFooterPhase11;
    private String battleFooterPhase12;
    private String battleFooterPhase13;
    private String battleFooterPhase14;
    private String battleFooterPhase15;
    private String battleFooterPhase16;
    private String battleFooterPhase17;
    private String battleFooterPhase18;
    private String battleFooterPhase19;
    private String battleFooterPhase20;
    private String battleFooterPhase21;
    private String battleFooterPhase22;
    private String battleFooterPhase23;
    private String battleFooterPhase24;
    private String battleFooterPhase25;
    private String battleFooterPhase26;
    private String battleFooterPhase27;
    private String battleFooterPhase28;
    private String battleFooterPhase29;
    private String battleFooterPhase30;
    private String battleFooterPhase31;
    private String battleFooterPhase32;
    private String battleFooterPhase33;
    private String battleFooterPhase34;
    private String battleFooterPhase35;
    private String battleFooterPhase36;
    private String battleFooterPhase37;
    private String battleFooterPhase38;
    private String battleFooterPhase39;
    private String battleFooterPhase40;
    private String battleFooterPhase41;
    private String battleFooterPhase42;
    private String battleFooterPhase43;
    private String battleFooterPhase44;
    private String battleFooterPhase45;

    private String timeAttackHeaderCorrectPhase1;
    private String timeAttackHeaderCorrectPhase2;
    private String timeAttackHeaderCorrectPhase3;
    private String timeAttackHeaderCorrectPhase4;
    private String timeAttackHeaderCorrectPhase5;
    private String timeAttackHeaderCorrectPhase6;
    private String timeAttackHeaderCorrectPhase7;
    private String timeAttackHeaderCorrectPhase8;
    private String timeAttackHeaderCorrectPhase9;
    private String timeAttackHeaderCorrectPhase10;
    private String timeAttackHeaderCorrectPhase11;
    private String timeAttackHeaderCorrectPhase12;
    private String timeAttackHeaderCorrectPhase13;
    private String timeAttackHeaderCorrectPhase14;
    private String timeAttackHeaderCorrectPhase15;
    private String timeAttackHeaderCorrectPhase16;
    private String timeAttackHeaderCorrectPhase17;
    private String timeAttackHeaderCorrectPhase18;
    private String timeAttackHeaderCorrectPhase19;
    private String timeAttackHeaderCorrectPhase20;
    private String timeAttackHeaderCorrectPhase21;
    private String timeAttackHeaderCorrectPhase22;
    private String timeAttackHeaderCorrectPhase23;
    private String timeAttackHeaderCorrectPhase24;
    private String timeAttackHeaderCorrectPhase25;
    private String timeAttackHeaderCorrectPhase26;
    private String timeAttackHeaderCorrectPhase27;
    private String timeAttackHeaderCorrectPhase28;
    private String timeAttackHeaderCorrectPhase29;
    private String timeAttackHeaderCorrectPhase30;
    private String timeAttackHeaderCorrectPhase31;
    private String timeAttackHeaderCorrectPhase32;
    private String timeAttackHeaderCorrectPhase33;
    private String timeAttackHeaderCorrectPhase34;
    private String timeAttackHeaderCorrectPhase35;
    private String timeAttackHeaderCorrectPhase36;

    private String timeAttackHeaderIncorrectPhase1;
    private String timeAttackHeaderIncorrectPhase2;
    private String timeAttackHeaderIncorrectPhase3;
    private String timeAttackHeaderIncorrectPhase4;
    private String timeAttackHeaderIncorrectPhase5;
    private String timeAttackHeaderIncorrectPhase6;
    private String timeAttackHeaderIncorrectPhase7;
    private String timeAttackHeaderIncorrectPhase8;
    private String timeAttackHeaderIncorrectPhase9;
    private String timeAttackHeaderIncorrectPhase10;
    private String timeAttackHeaderIncorrectPhase11;
    private String timeAttackHeaderIncorrectPhase12;
    private String timeAttackHeaderIncorrectPhase13;
    private String timeAttackHeaderIncorrectPhase14;
    private String timeAttackHeaderIncorrectPhase15;
    private String timeAttackHeaderIncorrectPhase16;
    private String timeAttackHeaderIncorrectPhase17;
    private String timeAttackHeaderIncorrectPhase18;
    private String timeAttackHeaderIncorrectPhase19;
    private String timeAttackHeaderIncorrectPhase20;
    private String timeAttackHeaderIncorrectPhase21;
    private String timeAttackHeaderIncorrectPhase22;
    private String timeAttackHeaderIncorrectPhase23;
    private String timeAttackHeaderIncorrectPhase24;
    private String timeAttackHeaderIncorrectPhase25;
    private String timeAttackHeaderIncorrectPhase26;
    private String timeAttackHeaderIncorrectPhase27;
    private String timeAttackHeaderIncorrectPhase28;
    private String timeAttackHeaderIncorrectPhase29;
    private String timeAttackHeaderIncorrectPhase30;
    private String timeAttackHeaderIncorrectPhase31;
    private String timeAttackHeaderIncorrectPhase32;
    private String timeAttackHeaderIncorrectPhase33;
    private String timeAttackHeaderIncorrectPhase34;
    private String timeAttackHeaderIncorrectPhase35;
    private String timeAttackHeaderIncorrectPhase36;

    private String timeAttackFooterPhase1;
    private String timeAttackFooterPhase2;
    private String timeAttackFooterPhase3;
    private String timeAttackFooterPhase4;
    private String timeAttackFooterPhase5;
    private String timeAttackFooterPhase6;
    private String timeAttackFooterPhase7;
    private String timeAttackFooterPhase8;
    private String timeAttackFooterPhase9;
    private String timeAttackFooterPhase10;
    private String timeAttackFooterPhase11;
    private String timeAttackFooterPhase12;
    private String timeAttackFooterPhase13;
    private String timeAttackFooterPhase14;
    private String timeAttackFooterPhase15;
    private String timeAttackFooterPhase16;
    private String timeAttackFooterPhase17;
    private String timeAttackFooterPhase18;
    private String timeAttackFooterPhase19;
    private String timeAttackFooterPhase20;
    private String timeAttackFooterPhase21;
    private String timeAttackFooterPhase22;
    private String timeAttackFooterPhase23;
    private String timeAttackFooterPhase24;
    private String timeAttackFooterPhase25;
    private String timeAttackFooterPhase26;
    private String timeAttackFooterPhase27;
    private String timeAttackFooterPhase28;
    private String timeAttackFooterPhase29;
    private String timeAttackFooterPhase30;
    private String timeAttackFooterPhase31;
    private String timeAttackFooterPhase32;
    private String timeAttackFooterPhase33;
    private String timeAttackFooterPhase34;
    private String timeAttackFooterPhase35;
    private String timeAttackFooterPhase36;

    private String timeBattleHeaderCorrectPhase1;
    private String timeBattleHeaderCorrectPhase2;
    private String timeBattleHeaderCorrectPhase3;
    private String timeBattleHeaderCorrectPhase4;
    private String timeBattleHeaderCorrectPhase5;
    private String timeBattleHeaderCorrectPhase6;
    private String timeBattleHeaderCorrectPhase7;
    private String timeBattleHeaderCorrectPhase8;
    private String timeBattleHeaderCorrectPhase9;
    private String timeBattleHeaderCorrectPhase10;
    private String timeBattleHeaderCorrectPhase11;
    private String timeBattleHeaderCorrectPhase12;
    private String timeBattleHeaderCorrectPhase13;
    private String timeBattleHeaderCorrectPhase14;
    private String timeBattleHeaderCorrectPhase15;
    private String timeBattleHeaderCorrectPhase16;
    private String timeBattleHeaderCorrectPhase17;
    private String timeBattleHeaderCorrectPhase18;
    private String timeBattleHeaderCorrectPhase19;
    private String timeBattleHeaderCorrectPhase20;
    private String timeBattleHeaderCorrectPhase21;
    private String timeBattleHeaderCorrectPhase22;
    private String timeBattleHeaderCorrectPhase23;
    private String timeBattleHeaderCorrectPhase24;
    private String timeBattleHeaderCorrectPhase25;
    private String timeBattleHeaderCorrectPhase26;
    private String timeBattleHeaderCorrectPhase27;
    private String timeBattleHeaderCorrectPhase28;
    private String timeBattleHeaderCorrectPhase29;
    private String timeBattleHeaderCorrectPhase30;
    private String timeBattleHeaderCorrectPhase31;
    private String timeBattleHeaderCorrectPhase32;
    private String timeBattleHeaderCorrectPhase33;
    private String timeBattleHeaderCorrectPhase34;
    private String timeBattleHeaderCorrectPhase35;
    private String timeBattleHeaderCorrectPhase36;
    private String timeBattleHeaderCorrectPhase37;
    private String timeBattleHeaderCorrectPhase38;
    private String timeBattleHeaderCorrectPhase39;
    private String timeBattleHeaderCorrectPhase40;
    private String timeBattleHeaderCorrectPhase41;
    private String timeBattleHeaderCorrectPhase42;

    private String timeBattleHeaderIncorrectPhase1;
    private String timeBattleHeaderIncorrectPhase2;
    private String timeBattleHeaderIncorrectPhase3;
    private String timeBattleHeaderIncorrectPhase4;
    private String timeBattleHeaderIncorrectPhase5;
    private String timeBattleHeaderIncorrectPhase6;
    private String timeBattleHeaderIncorrectPhase7;
    private String timeBattleHeaderIncorrectPhase8;
    private String timeBattleHeaderIncorrectPhase9;
    private String timeBattleHeaderIncorrectPhase10;
    private String timeBattleHeaderIncorrectPhase11;
    private String timeBattleHeaderIncorrectPhase12;
    private String timeBattleHeaderIncorrectPhase13;
    private String timeBattleHeaderIncorrectPhase14;
    private String timeBattleHeaderIncorrectPhase15;
    private String timeBattleHeaderIncorrectPhase16;
    private String timeBattleHeaderIncorrectPhase17;
    private String timeBattleHeaderIncorrectPhase18;
    private String timeBattleHeaderIncorrectPhase19;
    private String timeBattleHeaderIncorrectPhase20;
    private String timeBattleHeaderIncorrectPhase21;
    private String timeBattleHeaderIncorrectPhase22;
    private String timeBattleHeaderIncorrectPhase23;
    private String timeBattleHeaderIncorrectPhase24;
    private String timeBattleHeaderIncorrectPhase25;
    private String timeBattleHeaderIncorrectPhase26;
    private String timeBattleHeaderIncorrectPhase27;
    private String timeBattleHeaderIncorrectPhase28;
    private String timeBattleHeaderIncorrectPhase29;
    private String timeBattleHeaderIncorrectPhase30;
    private String timeBattleHeaderIncorrectPhase31;
    private String timeBattleHeaderIncorrectPhase32;
    private String timeBattleHeaderIncorrectPhase33;
    private String timeBattleHeaderIncorrectPhase34;
    private String timeBattleHeaderIncorrectPhase35;
    private String timeBattleHeaderIncorrectPhase36;
    private String timeBattleHeaderIncorrectPhase37;
    private String timeBattleHeaderIncorrectPhase38;
    private String timeBattleHeaderIncorrectPhase39;
    private String timeBattleHeaderIncorrectPhase40;
    private String timeBattleHeaderIncorrectPhase41;
    private String timeBattleHeaderIncorrectPhase42;

    private String timeBattleFooterPhase1;
    private String timeBattleFooterPhase2;
    private String timeBattleFooterPhase3;
    private String timeBattleFooterPhase4;
    private String timeBattleFooterPhase5;
    private String timeBattleFooterPhase6;
    private String timeBattleFooterPhase7;
    private String timeBattleFooterPhase8;
    private String timeBattleFooterPhase9;
    private String timeBattleFooterPhase10;
    private String timeBattleFooterPhase11;
    private String timeBattleFooterPhase12;
    private String timeBattleFooterPhase13;
    private String timeBattleFooterPhase14;
    private String timeBattleFooterPhase15;
    private String timeBattleFooterPhase16;
    private String timeBattleFooterPhase17;
    private String timeBattleFooterPhase18;
    private String timeBattleFooterPhase19;
    private String timeBattleFooterPhase20;
    private String timeBattleFooterPhase21;
    private String timeBattleFooterPhase22;
    private String timeBattleFooterPhase23;
    private String timeBattleFooterPhase24;
    private String timeBattleFooterPhase25;
    private String timeBattleFooterPhase26;
    private String timeBattleFooterPhase27;
    private String timeBattleFooterPhase28;
    private String timeBattleFooterPhase29;
    private String timeBattleFooterPhase30;
    private String timeBattleFooterPhase31;
    private String timeBattleFooterPhase32;
    private String timeBattleFooterPhase33;
    private String timeBattleFooterPhase34;
    private String timeBattleFooterPhase35;
    private String timeBattleFooterPhase36;
    private String timeBattleFooterPhase37;
    private String timeBattleFooterPhase38;
    private String timeBattleFooterPhase39;
    private String timeBattleFooterPhase40;
    private String timeBattleFooterPhase41;
    private String timeBattleFooterPhase42;

    private List<String> squirgleHeaderAndFooterCorrectPhase1;
    private List<String> squirgleHeaderAndFooterCorrectPhase2;
    private List<String> squirgleHeaderAndFooterCorrectPhase3;
    private List<String> squirgleHeaderAndFooterCorrectPhase4;
    private List<String> squirgleHeaderAndFooterCorrectPhase5;
    private List<String> squirgleHeaderAndFooterCorrectPhase6;
    private List<String> squirgleHeaderAndFooterCorrectPhase7;
    private List<String> squirgleHeaderAndFooterCorrectPhase8;
    private List<String> squirgleHeaderAndFooterCorrectPhase9;
    private List<String> squirgleHeaderAndFooterCorrectPhase10;
    private List<String> squirgleHeaderAndFooterCorrectPhase11;
    private List<String> squirgleHeaderAndFooterCorrectPhase12;
    private List<String> squirgleHeaderAndFooterCorrectPhase13;
    private List<String> squirgleHeaderAndFooterCorrectPhase14;
    private List<String> squirgleHeaderAndFooterCorrectPhase15;
    private List<String> squirgleHeaderAndFooterCorrectPhase16;
    private List<String> squirgleHeaderAndFooterCorrectPhase17;
    private List<String> squirgleHeaderAndFooterCorrectPhase18;
    private List<String> squirgleHeaderAndFooterCorrectPhase19;
    private List<String> squirgleHeaderAndFooterCorrectPhase20;
    private List<String> squirgleHeaderAndFooterCorrectPhase21;
    private List<String> squirgleHeaderAndFooterCorrectPhase22;
    private List<String> squirgleHeaderAndFooterCorrectPhase23;
    private List<String> squirgleHeaderAndFooterCorrectPhase24;
    private List<String> squirgleHeaderAndFooterCorrectPhase25;
    private List<String> squirgleHeaderAndFooterCorrectPhase26;
    private List<String> squirgleHeaderAndFooterCorrectPhase27;
    private List<String> squirgleHeaderAndFooterCorrectPhase28;
    private List<String> squirgleHeaderAndFooterCorrectPhase29;
    private List<String> squirgleHeaderAndFooterCorrectPhase30;
    private List<String> squirgleHeaderAndFooterCorrectPhase31;
    private List<String> squirgleHeaderAndFooterCorrectPhase32;
    private List<String> squirgleHeaderAndFooterCorrectPhase33;
    private List<String> squirgleHeaderAndFooterCorrectPhase34;
    private List<String> squirgleHeaderAndFooterCorrectPhase35;
    private List<String> squirgleHeaderAndFooterCorrectPhase36;
    private List<String> squirgleHeaderAndFooterCorrectPhase37;
    private List<String> squirgleHeaderAndFooterCorrectPhase38;
    private List<String> squirgleHeaderAndFooterCorrectPhase39;
    private List<String> squirgleHeaderAndFooterCorrectPhase40;
    private List<String> squirgleHeaderAndFooterCorrectPhase41;
    private List<String> squirgleHeaderAndFooterCorrectPhase42;
    private List<String> squirgleHeaderAndFooterCorrectPhase43;
    private List<String> squirgleHeaderAndFooterCorrectPhase44;
    private List<String> squirgleHeaderAndFooterCorrectPhase45;

    private List<String> squirgleHeaderAndFooterIncorrectPhase1;
    private List<String> squirgleHeaderAndFooterIncorrectPhase2;
    private List<String> squirgleHeaderAndFooterIncorrectPhase3;
    private List<String> squirgleHeaderAndFooterIncorrectPhase4;
    private List<String> squirgleHeaderAndFooterIncorrectPhase5;
    private List<String> squirgleHeaderAndFooterIncorrectPhase6;
    private List<String> squirgleHeaderAndFooterIncorrectPhase7;
    private List<String> squirgleHeaderAndFooterIncorrectPhase8;
    private List<String> squirgleHeaderAndFooterIncorrectPhase9;
    private List<String> squirgleHeaderAndFooterIncorrectPhase10;
    private List<String> squirgleHeaderAndFooterIncorrectPhase11;
    private List<String> squirgleHeaderAndFooterIncorrectPhase12;
    private List<String> squirgleHeaderAndFooterIncorrectPhase13;
    private List<String> squirgleHeaderAndFooterIncorrectPhase14;
    private List<String> squirgleHeaderAndFooterIncorrectPhase15;
    private List<String> squirgleHeaderAndFooterIncorrectPhase16;
    private List<String> squirgleHeaderAndFooterIncorrectPhase17;
    private List<String> squirgleHeaderAndFooterIncorrectPhase18;
    private List<String> squirgleHeaderAndFooterIncorrectPhase19;
    private List<String> squirgleHeaderAndFooterIncorrectPhase20;
    private List<String> squirgleHeaderAndFooterIncorrectPhase21;
    private List<String> squirgleHeaderAndFooterIncorrectPhase22;
    private List<String> squirgleHeaderAndFooterIncorrectPhase23;
    private List<String> squirgleHeaderAndFooterIncorrectPhase24;
    private List<String> squirgleHeaderAndFooterIncorrectPhase25;
    private List<String> squirgleHeaderAndFooterIncorrectPhase26;
    private List<String> squirgleHeaderAndFooterIncorrectPhase27;
    private List<String> squirgleHeaderAndFooterIncorrectPhase28;
    private List<String> squirgleHeaderAndFooterIncorrectPhase29;
    private List<String> squirgleHeaderAndFooterIncorrectPhase30;
    private List<String> squirgleHeaderAndFooterIncorrectPhase31;
    private List<String> squirgleHeaderAndFooterIncorrectPhase32;
    private List<String> squirgleHeaderAndFooterIncorrectPhase33;
    private List<String> squirgleHeaderAndFooterIncorrectPhase34;
    private List<String> squirgleHeaderAndFooterIncorrectPhase35;
    private List<String> squirgleHeaderAndFooterIncorrectPhase36;
    private List<String> squirgleHeaderAndFooterIncorrectPhase37;
    private List<String> squirgleHeaderAndFooterIncorrectPhase38;
    private List<String> squirgleHeaderAndFooterIncorrectPhase39;
    private List<String> squirgleHeaderAndFooterIncorrectPhase40;
    private List<String> squirgleHeaderAndFooterIncorrectPhase41;
    private List<String> squirgleHeaderAndFooterIncorrectPhase42;
    private List<String> squirgleHeaderAndFooterIncorrectPhase43;
    private List<String> squirgleHeaderAndFooterIncorrectPhase44;
    private List<String> squirgleHeaderAndFooterIncorrectPhase45;

    private List<String> battleHeaderAndFooterCorrectPhase1;
    private List<String> battleHeaderAndFooterCorrectPhase2;
    private List<String> battleHeaderAndFooterCorrectPhase3;
    private List<String> battleHeaderAndFooterCorrectPhase4;
    private List<String> battleHeaderAndFooterCorrectPhase5;
    private List<String> battleHeaderAndFooterCorrectPhase6;
    private List<String> battleHeaderAndFooterCorrectPhase7;
    private List<String> battleHeaderAndFooterCorrectPhase8;
    private List<String> battleHeaderAndFooterCorrectPhase9;
    private List<String> battleHeaderAndFooterCorrectPhase10;
    private List<String> battleHeaderAndFooterCorrectPhase11;
    private List<String> battleHeaderAndFooterCorrectPhase12;
    private List<String> battleHeaderAndFooterCorrectPhase13;
    private List<String> battleHeaderAndFooterCorrectPhase14;
    private List<String> battleHeaderAndFooterCorrectPhase15;
    private List<String> battleHeaderAndFooterCorrectPhase16;
    private List<String> battleHeaderAndFooterCorrectPhase17;
    private List<String> battleHeaderAndFooterCorrectPhase18;
    private List<String> battleHeaderAndFooterCorrectPhase19;
    private List<String> battleHeaderAndFooterCorrectPhase20;
    private List<String> battleHeaderAndFooterCorrectPhase21;
    private List<String> battleHeaderAndFooterCorrectPhase22;
    private List<String> battleHeaderAndFooterCorrectPhase23;
    private List<String> battleHeaderAndFooterCorrectPhase24;
    private List<String> battleHeaderAndFooterCorrectPhase25;
    private List<String> battleHeaderAndFooterCorrectPhase26;
    private List<String> battleHeaderAndFooterCorrectPhase27;
    private List<String> battleHeaderAndFooterCorrectPhase28;
    private List<String> battleHeaderAndFooterCorrectPhase29;
    private List<String> battleHeaderAndFooterCorrectPhase30;
    private List<String> battleHeaderAndFooterCorrectPhase31;
    private List<String> battleHeaderAndFooterCorrectPhase32;
    private List<String> battleHeaderAndFooterCorrectPhase33;
    private List<String> battleHeaderAndFooterCorrectPhase34;
    private List<String> battleHeaderAndFooterCorrectPhase35;
    private List<String> battleHeaderAndFooterCorrectPhase36;
    private List<String> battleHeaderAndFooterCorrectPhase37;
    private List<String> battleHeaderAndFooterCorrectPhase38;
    private List<String> battleHeaderAndFooterCorrectPhase39;
    private List<String> battleHeaderAndFooterCorrectPhase40;
    private List<String> battleHeaderAndFooterCorrectPhase41;
    private List<String> battleHeaderAndFooterCorrectPhase42;
    private List<String> battleHeaderAndFooterCorrectPhase43;
    private List<String> battleHeaderAndFooterCorrectPhase44;
    private List<String> battleHeaderAndFooterCorrectPhase45;

    private List<String> battleHeaderAndFooterIncorrectPhase1;
    private List<String> battleHeaderAndFooterIncorrectPhase2;
    private List<String> battleHeaderAndFooterIncorrectPhase3;
    private List<String> battleHeaderAndFooterIncorrectPhase4;
    private List<String> battleHeaderAndFooterIncorrectPhase5;
    private List<String> battleHeaderAndFooterIncorrectPhase6;
    private List<String> battleHeaderAndFooterIncorrectPhase7;
    private List<String> battleHeaderAndFooterIncorrectPhase8;
    private List<String> battleHeaderAndFooterIncorrectPhase9;
    private List<String> battleHeaderAndFooterIncorrectPhase10;
    private List<String> battleHeaderAndFooterIncorrectPhase11;
    private List<String> battleHeaderAndFooterIncorrectPhase12;
    private List<String> battleHeaderAndFooterIncorrectPhase13;
    private List<String> battleHeaderAndFooterIncorrectPhase14;
    private List<String> battleHeaderAndFooterIncorrectPhase15;
    private List<String> battleHeaderAndFooterIncorrectPhase16;
    private List<String> battleHeaderAndFooterIncorrectPhase17;
    private List<String> battleHeaderAndFooterIncorrectPhase18;
    private List<String> battleHeaderAndFooterIncorrectPhase19;
    private List<String> battleHeaderAndFooterIncorrectPhase20;
    private List<String> battleHeaderAndFooterIncorrectPhase21;
    private List<String> battleHeaderAndFooterIncorrectPhase22;
    private List<String> battleHeaderAndFooterIncorrectPhase23;
    private List<String> battleHeaderAndFooterIncorrectPhase24;
    private List<String> battleHeaderAndFooterIncorrectPhase25;
    private List<String> battleHeaderAndFooterIncorrectPhase26;
    private List<String> battleHeaderAndFooterIncorrectPhase27;
    private List<String> battleHeaderAndFooterIncorrectPhase28;
    private List<String> battleHeaderAndFooterIncorrectPhase29;
    private List<String> battleHeaderAndFooterIncorrectPhase30;
    private List<String> battleHeaderAndFooterIncorrectPhase31;
    private List<String> battleHeaderAndFooterIncorrectPhase32;
    private List<String> battleHeaderAndFooterIncorrectPhase33;
    private List<String> battleHeaderAndFooterIncorrectPhase34;
    private List<String> battleHeaderAndFooterIncorrectPhase35;
    private List<String> battleHeaderAndFooterIncorrectPhase36;
    private List<String> battleHeaderAndFooterIncorrectPhase37;
    private List<String> battleHeaderAndFooterIncorrectPhase38;
    private List<String> battleHeaderAndFooterIncorrectPhase39;
    private List<String> battleHeaderAndFooterIncorrectPhase40;
    private List<String> battleHeaderAndFooterIncorrectPhase41;
    private List<String> battleHeaderAndFooterIncorrectPhase42;
    private List<String> battleHeaderAndFooterIncorrectPhase43;
    private List<String> battleHeaderAndFooterIncorrectPhase44;
    private List<String> battleHeaderAndFooterIncorrectPhase45;

    private List<String> timeAttackHeaderAndFooterCorrectPhase1;
    private List<String> timeAttackHeaderAndFooterCorrectPhase2;
    private List<String> timeAttackHeaderAndFooterCorrectPhase3;
    private List<String> timeAttackHeaderAndFooterCorrectPhase4;
    private List<String> timeAttackHeaderAndFooterCorrectPhase5;
    private List<String> timeAttackHeaderAndFooterCorrectPhase6;
    private List<String> timeAttackHeaderAndFooterCorrectPhase7;
    private List<String> timeAttackHeaderAndFooterCorrectPhase8;
    private List<String> timeAttackHeaderAndFooterCorrectPhase9;
    private List<String> timeAttackHeaderAndFooterCorrectPhase10;
    private List<String> timeAttackHeaderAndFooterCorrectPhase11;
    private List<String> timeAttackHeaderAndFooterCorrectPhase12;
    private List<String> timeAttackHeaderAndFooterCorrectPhase13;
    private List<String> timeAttackHeaderAndFooterCorrectPhase14;
    private List<String> timeAttackHeaderAndFooterCorrectPhase15;
    private List<String> timeAttackHeaderAndFooterCorrectPhase16;
    private List<String> timeAttackHeaderAndFooterCorrectPhase17;
    private List<String> timeAttackHeaderAndFooterCorrectPhase18;
    private List<String> timeAttackHeaderAndFooterCorrectPhase19;
    private List<String> timeAttackHeaderAndFooterCorrectPhase20;
    private List<String> timeAttackHeaderAndFooterCorrectPhase21;
    private List<String> timeAttackHeaderAndFooterCorrectPhase22;
    private List<String> timeAttackHeaderAndFooterCorrectPhase23;
    private List<String> timeAttackHeaderAndFooterCorrectPhase24;
    private List<String> timeAttackHeaderAndFooterCorrectPhase25;
    private List<String> timeAttackHeaderAndFooterCorrectPhase26;
    private List<String> timeAttackHeaderAndFooterCorrectPhase27;
    private List<String> timeAttackHeaderAndFooterCorrectPhase28;
    private List<String> timeAttackHeaderAndFooterCorrectPhase29;
    private List<String> timeAttackHeaderAndFooterCorrectPhase30;
    private List<String> timeAttackHeaderAndFooterCorrectPhase31;
    private List<String> timeAttackHeaderAndFooterCorrectPhase32;
    private List<String> timeAttackHeaderAndFooterCorrectPhase33;
    private List<String> timeAttackHeaderAndFooterCorrectPhase34;
    private List<String> timeAttackHeaderAndFooterCorrectPhase35;
    private List<String> timeAttackHeaderAndFooterCorrectPhase36;

    private List<String> timeAttackHeaderAndFooterIncorrectPhase1;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase2;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase3;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase4;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase5;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase6;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase7;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase8;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase9;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase10;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase11;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase12;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase13;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase14;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase15;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase16;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase17;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase18;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase19;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase20;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase21;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase22;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase23;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase24;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase25;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase26;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase27;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase28;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase29;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase30;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase31;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase32;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase33;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase34;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase35;
    private List<String> timeAttackHeaderAndFooterIncorrectPhase36;

    private List<String> timeBattleHeaderAndFooterCorrectPhase1;
    private List<String> timeBattleHeaderAndFooterCorrectPhase2;
    private List<String> timeBattleHeaderAndFooterCorrectPhase3;
    private List<String> timeBattleHeaderAndFooterCorrectPhase4;
    private List<String> timeBattleHeaderAndFooterCorrectPhase5;
    private List<String> timeBattleHeaderAndFooterCorrectPhase6;
    private List<String> timeBattleHeaderAndFooterCorrectPhase7;
    private List<String> timeBattleHeaderAndFooterCorrectPhase8;
    private List<String> timeBattleHeaderAndFooterCorrectPhase9;
    private List<String> timeBattleHeaderAndFooterCorrectPhase10;
    private List<String> timeBattleHeaderAndFooterCorrectPhase11;
    private List<String> timeBattleHeaderAndFooterCorrectPhase12;
    private List<String> timeBattleHeaderAndFooterCorrectPhase13;
    private List<String> timeBattleHeaderAndFooterCorrectPhase14;
    private List<String> timeBattleHeaderAndFooterCorrectPhase15;
    private List<String> timeBattleHeaderAndFooterCorrectPhase16;
    private List<String> timeBattleHeaderAndFooterCorrectPhase17;
    private List<String> timeBattleHeaderAndFooterCorrectPhase18;
    private List<String> timeBattleHeaderAndFooterCorrectPhase19;
    private List<String> timeBattleHeaderAndFooterCorrectPhase20;
    private List<String> timeBattleHeaderAndFooterCorrectPhase21;
    private List<String> timeBattleHeaderAndFooterCorrectPhase22;
    private List<String> timeBattleHeaderAndFooterCorrectPhase23;
    private List<String> timeBattleHeaderAndFooterCorrectPhase24;
    private List<String> timeBattleHeaderAndFooterCorrectPhase25;
    private List<String> timeBattleHeaderAndFooterCorrectPhase26;
    private List<String> timeBattleHeaderAndFooterCorrectPhase27;
    private List<String> timeBattleHeaderAndFooterCorrectPhase28;
    private List<String> timeBattleHeaderAndFooterCorrectPhase29;
    private List<String> timeBattleHeaderAndFooterCorrectPhase30;
    private List<String> timeBattleHeaderAndFooterCorrectPhase31;
    private List<String> timeBattleHeaderAndFooterCorrectPhase32;
    private List<String> timeBattleHeaderAndFooterCorrectPhase33;
    private List<String> timeBattleHeaderAndFooterCorrectPhase34;
    private List<String> timeBattleHeaderAndFooterCorrectPhase35;
    private List<String> timeBattleHeaderAndFooterCorrectPhase36;
    private List<String> timeBattleHeaderAndFooterCorrectPhase37;
    private List<String> timeBattleHeaderAndFooterCorrectPhase38;
    private List<String> timeBattleHeaderAndFooterCorrectPhase39;
    private List<String> timeBattleHeaderAndFooterCorrectPhase40;
    private List<String> timeBattleHeaderAndFooterCorrectPhase41;
    private List<String> timeBattleHeaderAndFooterCorrectPhase42;

    private List<String> timeBattleHeaderAndFooterIncorrectPhase1;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase2;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase3;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase4;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase5;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase6;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase7;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase8;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase9;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase10;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase11;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase12;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase13;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase14;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase15;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase16;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase17;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase18;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase19;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase20;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase21;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase22;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase23;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase24;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase25;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase26;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase27;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase28;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase29;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase30;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase31;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase32;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase33;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase34;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase35;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase36;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase37;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase38;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase39;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase40;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase41;
    private List<String> timeBattleHeaderAndFooterIncorrectPhase42;

    private List<List<String>> squirgleHeadersAndFootersCorrect;
    private List<List<String>> squirgleHeadersAndFootersIncorrect;
    private List<List<String>> battleHeadersAndFootersCorrect;
    private List<List<String>> battleHeadersAndFootersIncorrect;
    private List<List<String>> timeAttackHeadersAndFootersCorrect;
    private List<List<String>> timeAttackHeadersAndFootersIncorrect;
    private List<List<String>> timeBattleHeadersAndFootersCorrect;
    private List<List<String>> timeBattleHeadersAndFootersIncorrect;

    private Map<Boolean, List<List<String>>> correctInputMapSquirgle;
    private Map<Boolean, List<List<String>>> correctInputMapBattle;
    private Map<Boolean, List<List<String>>> correctInputMapTimeAttack;
    private Map<Boolean, List<List<String>>> correctInputMapTimeBattle;
    private Map<Integer, Map<Boolean, List<List<String>>>> gameplayTypeMap;

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
        game.setUpFontButton(MathUtils.round(PAUSE_INPUT_WIDTH > PAUSE_INPUT_HEIGHT_MIDDLE ? (PAUSE_INPUT_HEIGHT_MIDDLE / 2) / 2.75f : (PAUSE_INPUT_WIDTH / 2) / 2.75f));

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
            if((showBackgroundColorShapeListAndTimelines() && promptShape.getRadius() <= game.thirdOfScreen) || unrestrictedPlay()) {
                increasePromptRadius();
            }
        } else {
            if((showBackgroundColorShapeListAndTimelines() && dummyPromptForTimelines.getRadius() <= game.thirdOfScreen) || unrestrictedPlay()) {
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
                    game.draw.drawScreenDivisionTutorial(blackAndWhite);
                }
                game.draw.drawTargetSemicirclesTutorial(splitScreen, local, showPlayerTargets(), unrestrictedPlay());
            }
            if(!splitScreen) {
                if(showPlayerHand()) {
                    game.draw.drawPrompt(false, promptShape, priorShapeList, 0, backgroundColorShape, false, false);
                    game.draw.orientShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
                }
            } else if(useSaturation) {
                if(!(gameOver && saturationP1 > saturationP2)) {
                    if(showPlayerHand()) {
                        game.draw.drawPrompt(false, promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                }
                if(!(gameOver && saturationP2 >= saturationP1)) {
                    if(showOpponentHand()) {
                        game.draw.drawPrompt(local, promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                }
            } else {
                if(!(gameOver && scoreP1 < scoreP2)) {
                    if(showPlayerHand()) {
                        game.draw.drawPrompt(false, promptShapeP1, priorShapeListP1, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                }
                if(!(gameOver && scoreP2 <= scoreP1)) {
                    if(showOpponentHand()) {
                        game.draw.drawPrompt(local, promptShapeP2, priorShapeListP2, 0, backgroundColorShape, false, false);
                        game.draw.orientShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                }
            }
        }

        if(showBackgroundColorShapeListAndTimelines()) {
            if (gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
                if(unrestrictedPlay()) {
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
                if(showPlayerHand()) {
                    game.draw.orientAndDrawShapes(false, priorShapeList, promptShape, primaryShapeAtThreshold);
                }
            } else if(useSaturation) {
                if(!(gameOver && saturationP1 > saturationP2)) {
                    if(showPlayerHand()) {
                        game.draw.orientAndDrawShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                }
                if(!(gameOver && saturationP2 >= saturationP1)) {
                    if(showOpponentHand()) {
                        game.draw.orientAndDrawShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                }
            } else {
                if(!(gameOver && scoreP1 < scoreP2)) {
                    if(showPlayerHand()) {
                        game.draw.orientAndDrawShapes(false, priorShapeListP1, promptShapeP1, primaryShapeAtThresholdP1);
                    }
                }
                if(!(gameOver && scoreP2 <= scoreP1)) {
                    if(showOpponentHand()) {
                        game.draw.orientAndDrawShapes(local, priorShapeListP2, promptShapeP2, primaryShapeAtThresholdP2);
                    }
                }
            }
        }

        if(unrestrictedPlay()) {
            if (splitScreen && !multiplayer && !paused) {
                executeOpponentAI();
            }
        }

        drawEquations();

        if(!paused) {
            if (!gameOver) {
                if (!splitScreen) {
                    if(showPlayerTargets()) {
                        game.draw.drawPrompt(false, outsideTargetShape, targetShapeList, targetShapesMatched, backgroundColorShape, false, true);
                        game.draw.orientShapes(false, targetShapeList, outsideTargetShape, false);
                        game.draw.drawShapes(false, targetShapeList);
                    }
                } else {
                    if(showPlayerTargets()) {
                        game.draw.drawPrompt(false, outsideTargetShapeP1, targetShapeListP1, targetShapesMatchedP1, backgroundColorShape, false, true);
                        game.draw.orientShapes(false, targetShapeListP1, outsideTargetShapeP1, false);
                        game.draw.drawShapes(false, targetShapeListP1);
                    }
                    if(unrestrictedPlay()) {
                        game.draw.drawPrompt(local, outsideTargetShapeP2, targetShapeListP2, targetShapesMatchedP2, backgroundColorShape, false, true);
                        game.draw.orientShapes(local, targetShapeListP2, outsideTargetShapeP2, false);
                        game.draw.drawShapes(local, targetShapeListP2);
                    }
                }
                drawTargetArcs();
                game.draw.drawInputButtonsTutorial(splitScreen, local, unrestrictedPlay(), numPlayerInputs(), backgroundColorShape.getColor(), game);
                if(showBackgroundColorShapeListAndTimelines()) {
                    //Ensuring that color needed to make squirgle is always in backgroundColorShapeList so player isn't waiting around in tutorial forever
                    Color backgroundColorShapeColorToAdd;
                    if(splitScreen) {
                        if(currentTargetShapeP1.equals(outsideTargetShapeP1) && !ColorUtils.listContainsShapeOfFillColor(backgroundColorShapeList, targetShapeListP1.get(0).getColor())) {
                            backgroundColorShapeColorToAdd = targetShapeListP1.get(0).getColor();
                        } else {
                            backgroundColorShapeColorToAdd = null;
                        }
                    } else {
                        if(currentTargetShape.equals(outsideTargetShape) && !ColorUtils.listContainsShapeOfFillColor(backgroundColorShapeList, targetShapeList.get(0).getColor())) {
                            backgroundColorShapeColorToAdd = targetShapeList.get(0).getColor();
                        } else {
                            backgroundColorShapeColorToAdd = null;
                        }
                    }
                    game.draw.drawBackgroundColorShapeListTutorial(splitScreen, blackAndWhite, local, backgroundColorShapeList, backgroundColorShape, backgroundColorShapeColorToAdd, clearColor);
                    game.draw.drawTimelines(splitScreen, local, splitScreen ? dummyPromptForTimelines : promptShape, backgroundColorShapeList);
                }
                game.draw.drawScoreTrianglesTutorial(splitScreen, local, showPlayerScore(), showOpponentScore(), backgroundColorShape.getColor());
                if(showPlayerScore()) {
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
                if(showPause()) {
                    game.draw.drawPauseInputTutorial(splitScreen, local, game);
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

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        gameOver();

        if(!paused && phase < numPhases) {
            stage.draw();
        }

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

        if(phase < numPhases) {
            updateLabels();
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

        if(phase < numPhases && tapAnywhereToProgress()) {
            phase++;
            correctInput = true;
            return true;
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
        drawPauseReplayInput();
    }

    public void drawInputRectangle(int placement) {
        switch(placement) {
            case PAUSE_BACK : {
                game.draw.rect(game.camera.viewportWidth - game.partitionSize - PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT_BACK,
                        Color.WHITE);
            }
            case PAUSE_QUIT : {
                game.draw.rect((2 * game.partitionSize) + PAUSE_INPUT_WIDTH,
                        game.partitionSize,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT_MIDDLE,
                        Color.WHITE);
            }
            case PAUSE_REPLAY : {
                game.draw.rect((2 * game.partitionSize) + PAUSE_INPUT_WIDTH,
                        (2 * game.partitionSize) + PAUSE_INPUT_HEIGHT_MIDDLE,
                        PAUSE_INPUT_WIDTH,
                        PAUSE_INPUT_HEIGHT_MIDDLE,
                        Color.WHITE);
            }
        }
    }

    public void drawPauseQuitInput() {
        game.layout.setText(game.fontButton, Button.QUIT_STRING);

        float symbolTextOverlap = (game.partitionSize + game.layout.height) - ((game.partitionSize + (PAUSE_INPUT_HEIGHT_MIDDLE / 2)) - PAUSE_INPUT_RADIUS);
        float symbolX = game.camera.viewportWidth / 2;
        float symbolY = symbolTextOverlap > 0 ? (game.partitionSize + (PAUSE_INPUT_HEIGHT_MIDDLE / 2)) + (symbolTextOverlap / 2) : (game.partitionSize + (PAUSE_INPUT_HEIGHT_MIDDLE / 2));
        float symbolRadius = symbolTextOverlap > 0 ? PAUSE_INPUT_RADIUS - (symbolTextOverlap / 2) : PAUSE_INPUT_RADIUS;

        drawInputRectangle(PAUSE_QUIT);
        game.draw.drawStopSymbol(symbolX,
                symbolY,
                symbolRadius,
                Color.BLACK);
    }

    public void drawPauseBackInput() {
        game.layout.setText(game.fontButton, Button.BACK_STRING);

        float symbolTextOverlap = (game.partitionSize + game.layout.height) - ((game.partitionSize + (PAUSE_INPUT_HEIGHT_BACK / 2)) - PAUSE_INPUT_RADIUS);
        float symbolX = game.camera.viewportWidth - game.partitionSize - (PAUSE_INPUT_WIDTH / 2);
        float symbolY = symbolTextOverlap > 0 ? (game.partitionSize + (PAUSE_INPUT_HEIGHT_BACK / 2)) + (symbolTextOverlap / 2) : (game.partitionSize + (PAUSE_INPUT_HEIGHT_BACK / 2));
        float symbolRadius = symbolTextOverlap > 0 ? PAUSE_INPUT_RADIUS - (symbolTextOverlap / 2) : PAUSE_INPUT_RADIUS;

        drawInputRectangle(PAUSE_BACK);
        game.draw.drawBackButton(symbolX,
                symbolY,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK);
    }

    public void drawPauseReplayInput() {
        game.layout.setText(game.fontButton, Button.RESTART_STRING);

        float symbolTextOverlap = (game.partitionSize + game.layout.height) - ((game.partitionSize + (PAUSE_INPUT_HEIGHT_MIDDLE / 2)) - PAUSE_INPUT_RADIUS);
        float symbolX = game.camera.viewportWidth / 2;
        float symbolY = symbolTextOverlap > 0 ? (((2 * game.partitionSize) + PAUSE_INPUT_HEIGHT_MIDDLE) + (PAUSE_INPUT_HEIGHT_MIDDLE / 2)) + (symbolTextOverlap / 2) : (((2 * game.partitionSize) + PAUSE_INPUT_HEIGHT_MIDDLE) + (PAUSE_INPUT_HEIGHT_MIDDLE / 2));
        float symbolRadius = symbolTextOverlap > 0 ? PAUSE_INPUT_RADIUS - (symbolTextOverlap / 2) : PAUSE_INPUT_RADIUS;

        drawInputRectangle(PAUSE_REPLAY);
        game.draw.drawReplaySymbol(symbolX,
                symbolY,
                symbolRadius,
                Color.BLACK,
                Color.WHITE);
    }

    public void playMusic() {
        if(game.usePhases) {
//            if(!game.trackMapPhase.get(game.track).get(0).isPlaying()) {
//                for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
//                    game.trackMapPhase.get(game.track).get(i).play();
//                }
//            }
        } else {
            if(!game.trackMapFull.get(game.track).isPlaying()) {
                game.trackMapFull.get(game.track).play();
            }
        }
    }

    public void stopMusic() {
        if(game.usePhases) {
//            for (int i = 0; i < NUM_MUSIC_PHASES; i++) {
//                game.trackMapPhase.get(game.track).get(i).stop();
//            }
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

            //Pause replay text
            game.layout.setText(game.fontButton, Button.RESTART_STRING);
            FontUtils.printText(game.batch,
                    game.fontButton,
                    game.layout,
                    backgroundColorShape.getColor(),
                    Button.RESTART_STRING,
                    game.camera.viewportWidth / 2,
                    (2 * game.partitionSize) + PAUSE_INPUT_HEIGHT_MIDDLE + ((2.7f * game.layout.height) / 4),
                    0,
                    1);
        }
    }

    public void drawScoreText() {
        float targetPolypRadius = (TARGET_RADIUS / 4);
        float targetPolypOffset = (float)(Math.sqrt(Math.pow(TARGET_RADIUS, 2) + Math.pow(TARGET_RADIUS / 4, 2)) - TARGET_RADIUS);
        if(!splitScreen) {
            if(showPlayerScore()) {
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
                if(showPlayerMultiplier()) {
                    FontUtils.printText(game.batch,
                            game.fontScore,
                            game.layout,
                            Color.WHITE,
                            X + String.valueOf(multiplier),
                            game.camera.viewportWidth - (TARGET_RADIUS / 4),
                            game.camera.viewportHeight - TARGET_RADIUS + (game.fontScore.getCapHeight() / 5.5f),
                            SCORE_ANGLE,
                            1);
                }

                //Prompt number
//                String promptNumber = String.valueOf(promptShape.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        backgroundColorShape.getColor(),
//                        promptNumber,
//                        game.camera.viewportWidth - TARGET_RADIUS,
//                        game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
//                        0,
//                        1);
            }

            //Target number
//            if(showPlayerTargets()) {
//                String targetNumber = String.valueOf(currentTargetShape.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        currentTargetShape.getColor(),
//                        targetNumber,
//                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
//                        game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
//                        0,
//                        1);
//            }
        } else if(blackAndWhite) {
            //We're in a time battle mode

            if(showPlayerScore()) {
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
//                game.layout.setText(game.fontTarget, P1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        Color.WHITE,
//                        P1,
//                        game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
//                        (game.camera.viewportHeight / 2) - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
//                        0,
//                        1);
//                game.layout.setText(game.fontTarget, P2);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        Color.WHITE,
//                        P2,
//                        local ? TARGET_RADIUS + targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
//                        local ? (game.camera.viewportHeight / 2) + (2 * targetPolypRadius) + ((3 * game.layout.height) / 4) : game.camera.viewportHeight - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
//                        local ? Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES : 0,
//                        1);

                //Multipliers
                if(showPlayerMultiplier()) {
                    FontUtils.printText(game.batch,
                            game.fontScore,
                            game.layout,
                            Color.WHITE,
                            X + multiplierP1,
                            game.camera.viewportWidth - (TARGET_RADIUS / 4) - (game.fontScore.getCapHeight() / 4.7f),
                            (game.camera.viewportHeight / 2) - ((3 * TARGET_RADIUS) / 4) - (game.fontScore.getCapHeight() / 4.7f),
                            SCORE_ANGLE,
                            1);
                }
                if(unrestrictedPlay()) {
                    FontUtils.printText(game.batch,
                            game.fontScore,
                            game.layout,
                            Color.WHITE,
                            X + multiplierP2,
                            local ? (TARGET_RADIUS / 4) + (game.fontScore.getCapHeight() / 4.7f) : game.camera.viewportWidth - (TARGET_RADIUS / 4) - (game.fontScore.getCapHeight() / 4.7f),
                            local ? (game.camera.viewportHeight / 2) + ((3 * TARGET_RADIUS) / 4) + (game.fontScore.getCapHeight() / 4.7f) : game.camera.viewportHeight - ((3 * TARGET_RADIUS) / 4) - (game.fontScore.getCapHeight() / 4.7f),
                            local ? 3 * -SCORE_ANGLE : SCORE_ANGLE,
                            1);
                }

                //Prompt numbers
//                String promptNumberP1 = String.valueOf(promptShapeP1.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        backgroundColorShape.getColor(),
//                        promptNumberP1,
//                        game.camera.viewportWidth - TARGET_RADIUS,
//                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
//                        0,
//                        1);
//                String promptNumberP2 = String.valueOf(promptShapeP2.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        backgroundColorShape.getColor(),
//                        promptNumberP2,
//                        local ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS,
//                        local ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
//                        local ? 180 : 0,
//                        1);
            }

            if(showPlayerTargets()) {
                //Target numbers
//                String targetNumberP1 = String.valueOf(currentTargetShapeP1.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        currentTargetShapeP1.getColor(),
//                        targetNumberP1,
//                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
//                        (game.camera.viewportHeight / 2) - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
//                        0,
//                        1);
//                String targetNumberP2 = String.valueOf(currentTargetShapeP2.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        currentTargetShapeP2.getColor(),
//                        targetNumberP2,
//                        local ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius + targetPolypOffset : TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
//                        local ? (game.camera.viewportHeight / 2) + targetPolypRadius - (game.fontScore.getCapHeight() / 5.5f) : game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
//                        local ? 180 : 0,
//                        1);
            }
        } else {
            //We're in a non-time battle mode

            if(showPlayerScore()) {
                //Player designations
//                game.layout.setText(game.fontTarget, P1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        Color.WHITE,
//                        P1,
//                        game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
//                        (game.camera.viewportHeight / 2) - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
//                        0,
//                        1);
//                game.layout.setText(game.fontTarget, P2);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        Color.WHITE,
//                        P2,
//                        local ? TARGET_RADIUS + targetPolypRadius : game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius,
//                        local ? (game.camera.viewportHeight / 2) + (2 * targetPolypRadius) + ((3 * game.layout.height) / 4) : game.camera.viewportHeight - (2 * targetPolypRadius) - ((3 * game.layout.height) / 4),
//                        local ? Draw.ONE_HUNDRED_AND_EIGHTY_DEGREES : 0,
//                        1);

                //Prompt numbers
//                String promptNumberP1 = String.valueOf(promptShapeP1.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        backgroundColorShape.getColor(),
//                        promptNumberP1,
//                        game.camera.viewportWidth - TARGET_RADIUS,
//                        (game.camera.viewportHeight / 2) - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
//                        0,
//                        1);
//                String promptNumberP2 = String.valueOf(promptShapeP2.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        backgroundColorShape.getColor(),
//                        promptNumberP2,
//                        local ? TARGET_RADIUS : game.camera.viewportWidth - TARGET_RADIUS,
//                        local ? (game.camera.viewportHeight / 2) + (TARGET_RADIUS / 2) - (game.fontScore.getCapHeight() / 1.48f) : game.camera.viewportHeight - (TARGET_RADIUS / 2) + (game.fontScore.getCapHeight() / 1.48f),
//                        local ? 180 : 0,
//                        1);
            }

            if(showPlayerTargets()) {
                //Target numbers
//                String targetNumberP1 = String.valueOf(currentTargetShapeP1.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        currentTargetShapeP1.getColor(),
//                        targetNumberP1,
//                        TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
//                        (game.camera.viewportHeight / 2) - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
//                        0,
//                        1);
//                String targetNumberP2 = String.valueOf(currentTargetShapeP2.getShape() + 1);
//                FontUtils.printText(game.batch,
//                        game.fontScore,
//                        game.layout,
//                        currentTargetShapeP2.getColor(),
//                        targetNumberP2,
//                        local ? game.camera.viewportWidth - TARGET_RADIUS - targetPolypRadius + targetPolypOffset : TARGET_RADIUS + targetPolypRadius - targetPolypOffset,
//                        local ? (game.camera.viewportHeight / 2) + targetPolypRadius - (game.fontScore.getCapHeight() / 5.5f) : game.camera.viewportHeight - targetPolypRadius + (game.fontScore.getCapHeight() / 5.5f),
//                        local ? 180 : 0,
//                        1);
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
            if(showPlayerTargets()) {
                game.draw.drawArcTutorial(0, game.camera.viewportHeight, targetArcStart, backgroundColorShape.getColor());
                if (targetArcStart > -Draw.NINETY_ONE_DEGREES) {
                    targetArcStart -= TARGET_ARC_SPEED;
                }
            }
        } else {
            if(showPlayerTargets()) {
                game.draw.drawArcTutorial(0, game.camera.viewportHeight / 2, targetArcStartP1, backgroundColorShape.getColor());
                if (targetArcStartP1 > -Draw.NINETY_ONE_DEGREES) {
                    targetArcStartP1 -= TARGET_ARC_SPEED;
                }
            }
            if(unrestrictedPlay()) {
                if (local) {
                    game.draw.drawArcTutorial(game.camera.viewportWidth, game.camera.viewportHeight / 2, targetArcStartP2, backgroundColorShape.getColor());
                    if (targetArcStartP2 > Draw.NINETY_ONE_DEGREES) {
                        targetArcStartP2 -= TARGET_ARC_SPEED;
                    }
                } else {
                    game.draw.drawArcTutorial(0, game.camera.viewportHeight, targetArcStartP2, backgroundColorShape.getColor());
                    if (targetArcStartP2 > -Draw.NINETY_ONE_DEGREES) {
                        targetArcStartP2 -= TARGET_ARC_SPEED;
                    }
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
                        game.draw.drawEquationTutorial(false, null, showPlayerTargets(), numPlayerInputs(), lastShapeTouched, lastPromptShape, lastTargetShape, equationWidth);
                        equationWidth -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidth = 0;
                    }
                } else {
                    if(equationWidthP1 > 0) {
                        game.draw.drawEquationTutorial(false, P1, showPlayerTargets(), numPlayerInputs(), lastShapeTouchedP1, lastPromptShapeP1, lastTargetShapeP1, equationWidthP1);
                        equationWidthP1 -= INPUT_RADIUS / EQUATION_WIDTH_DIVISOR;
                    } else {
                        equationWidthP1 = 0;
                    }
                    if(equationWidthP2 > 0) {
                        game.draw.drawEquationTutorial(local, P2, showPlayerTargets(), numPlayerInputs(), lastShapeTouchedP2, lastPromptShapeP2, lastTargetShapeP2, equationWidthP2);
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
                    && numPlayerInputs() >= 1;
            lineTouched = touchPoint.x > INPUT_LINE_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_LINE_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_LINE_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_LINE_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 2;
            triangleTouched = touchPoint.x > INPUT_TRIANGLE_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_TRIANGLE_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_TRIANGLE_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_TRIANGLE_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 3;
            squareTouched = touchPoint.x > INPUT_SQUARE_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SQUARE_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SQUARE_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SQUARE_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 4;
            pentagonTouched = touchPoint.x > INPUT_PENTAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_PENTAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_PENTAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_PENTAGON_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 5;
            hexagonTouched = touchPoint.x > INPUT_HEXAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_HEXAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_HEXAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_HEXAGON_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 6;
            septagonTouched = touchPoint.x > INPUT_SEPTAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SEPTAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SEPTAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SEPTAGON_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 7;
            octagonTouched = touchPoint.x > INPUT_OCTAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_OCTAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_OCTAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_OCTAGON_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 8;
            nonagonTouched = touchPoint.x > INPUT_NONAGON_SPAWN.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_NONAGON_SPAWN.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_NONAGON_SPAWN.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_NONAGON_SPAWN.y + INPUT_RADIUS
                    && numPlayerInputs() >= 9;
            pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                    && touchPoint.y > ((game.camera.viewportHeight - TARGET_RADIUS) - (((game.camera.viewportHeight - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (game.camera.viewportWidth / 20)
                    && touchPoint.y < ((game.camera.viewportHeight - TARGET_RADIUS) - (((game.camera.viewportHeight - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (game.camera.viewportWidth / 20)
                    && showPause();
        } else {
            pointTouchedP1 = touchPoint.x > INPUT_POINT_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_POINT_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_POINT_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 1;
            lineTouchedP1 = touchPoint.x > INPUT_LINE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_LINE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_LINE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_LINE_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 2;
            triangleTouchedP1 = touchPoint.x > INPUT_TRIANGLE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_TRIANGLE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_TRIANGLE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_TRIANGLE_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 3;
            squareTouchedP1 = touchPoint.x > INPUT_SQUARE_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SQUARE_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SQUARE_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SQUARE_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 4;
            pentagonTouchedP1 = touchPoint.x > INPUT_PENTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_PENTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_PENTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_PENTAGON_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 5;
            hexagonTouchedP1 = touchPoint.x > INPUT_HEXAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_HEXAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_HEXAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_HEXAGON_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 6;
            septagonTouchedP1 = touchPoint.x > INPUT_SEPTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_SEPTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_SEPTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_SEPTAGON_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 7;
            octagonTouchedP1 = touchPoint.x > INPUT_OCTAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_OCTAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_OCTAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_OCTAGON_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 8;
            nonagonTouchedP1 = touchPoint.x > INPUT_NONAGON_SPAWN_P1.x - INPUT_RADIUS
                    && touchPoint.x < INPUT_NONAGON_SPAWN_P1.x + INPUT_RADIUS
                    && touchPoint.y > INPUT_NONAGON_SPAWN_P1.y - INPUT_RADIUS
                    && touchPoint.y < INPUT_NONAGON_SPAWN_P1.y + INPUT_RADIUS
                    && numPlayerInputs() >= 9;
            if(game.widthGreater) {
                pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 40)
                        && touchPoint.y > (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (game.camera.viewportWidth / 40)
                        && touchPoint.y < (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (game.camera.viewportWidth / 40)
                        && showPause();
            } else {
                pauseTouched = touchPoint.x > game.camera.viewportWidth - (game.camera.viewportWidth / 20)
                        && touchPoint.y > (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) - (game.camera.viewportWidth / 20)
                        && touchPoint.y < (((game.camera.viewportHeight / 2) - TARGET_RADIUS) - ((((game.camera.viewportHeight / 2) - TARGET_RADIUS) - (INPUT_POINT_SPAWN.y + INPUT_RADIUS)) / 2)) + (game.camera.viewportWidth / 20)
                        && showPause();
            }
            if(multiplayer) {
                pointTouchedP2 = touchPoint.x > INPUT_POINT_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_POINT_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_POINT_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_POINT_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                lineTouchedP2 = touchPoint.x > INPUT_LINE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_LINE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_LINE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_LINE_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                triangleTouchedP2 = touchPoint.x > INPUT_TRIANGLE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_TRIANGLE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_TRIANGLE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_TRIANGLE_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                squareTouchedP2 = touchPoint.x > INPUT_SQUARE_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_SQUARE_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_SQUARE_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_SQUARE_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                pentagonTouchedP2 = touchPoint.x > INPUT_PENTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_PENTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_PENTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_PENTAGON_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                hexagonTouchedP2 = touchPoint.x > INPUT_HEXAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_HEXAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_HEXAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_HEXAGON_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                septagonTouchedP2 = touchPoint.x > INPUT_SEPTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_SEPTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_SEPTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_SEPTAGON_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                octagonTouchedP2 = touchPoint.x > INPUT_OCTAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_OCTAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_OCTAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_OCTAGON_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                nonagonTouchedP2 = touchPoint.x > INPUT_NONAGON_SPAWN_P2.x - INPUT_RADIUS
                        && touchPoint.x < INPUT_NONAGON_SPAWN_P2.x + INPUT_RADIUS
                        && touchPoint.y > INPUT_NONAGON_SPAWN_P2.y - INPUT_RADIUS
                        && touchPoint.y < INPUT_NONAGON_SPAWN_P2.y + INPUT_RADIUS
                        && unrestrictedPlay();
                inputTouchedGameplayP2 = pointTouchedP2 || lineTouchedP2 || triangleTouchedP2 || squareTouchedP2 || pentagonTouchedP2 || hexagonTouchedP2 || septagonTouchedP2 || octagonTouchedP2 || nonagonTouchedP2;
            }
        }
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
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT_BACK;
        pauseQuitTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.partitionSize + PAUSE_INPUT_HEIGHT_MIDDLE;
        pauseReplayTouched = touchPoint.x > (2 * game.partitionSize) + PAUSE_INPUT_WIDTH
                && touchPoint.x < (2 * game.partitionSize) + (2 * PAUSE_INPUT_WIDTH)
                && touchPoint.y > (2 * game.partitionSize) + PAUSE_INPUT_HEIGHT_MIDDLE
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        if(!splitScreen) {
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        } else {
            inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        }
        inputTouchedResults = playTouched || homeTouched || exitTouched;
    }

    public void determineKeyedInput(int keycode) {
        if(!splitScreen) {
            pointTouched = (keycode == Input.Keys.NUM_1 || keycode == Input.Keys.NUMPAD_1) && numPlayerInputs() >= 1;
            lineTouched = (keycode == Input.Keys.NUM_2 || keycode == Input.Keys.NUMPAD_2) && numPlayerInputs() >= 2;
            triangleTouched = (keycode == Input.Keys.NUM_3 || keycode == Input.Keys.NUMPAD_3) && numPlayerInputs() >= 3;
            squareTouched = (keycode == Input.Keys.NUM_4 || keycode == Input.Keys.NUMPAD_4) && numPlayerInputs() >= 4;
            pentagonTouched = (keycode == Input.Keys.NUM_5 || keycode == Input.Keys.NUMPAD_5) && numPlayerInputs() >= 5 && game.base >= 5;
            hexagonTouched = (keycode == Input.Keys.NUM_6 || keycode == Input.Keys.NUMPAD_6) && numPlayerInputs() >= 6 && game.base >= 6;
            septagonTouched = (keycode == Input.Keys.NUM_7 || keycode == Input.Keys.NUMPAD_7) && numPlayerInputs() >= 7 && game.base >= 7;
            octagonTouched = (keycode == Input.Keys.NUM_8 || keycode == Input.Keys.NUMPAD_8) && numPlayerInputs() >= 8 && game.base >= 8;
            nonagonTouched = (keycode == Input.Keys.NUM_9 || keycode == Input.Keys.NUMPAD_9) && numPlayerInputs() >= 9 && game.base >= 9;
            playTouched = pointTouched;
            homeTouched = lineTouched;
            exitTouched = triangleTouched;
        } else {
            pointTouchedP1 = (keycode == Input.Keys.NUM_1 || keycode == Input.Keys.NUMPAD_1) && numPlayerInputs() >= 1;
            lineTouchedP1 = (keycode == Input.Keys.NUM_2 || keycode == Input.Keys.NUMPAD_2) && numPlayerInputs() >= 2;
            triangleTouchedP1 = (keycode == Input.Keys.NUM_3 || keycode == Input.Keys.NUMPAD_3) && numPlayerInputs() >= 3;
            squareTouchedP1 = (keycode == Input.Keys.NUM_4 || keycode == Input.Keys.NUMPAD_4) && numPlayerInputs() >= 4;
            pentagonTouchedP1 = (keycode == Input.Keys.NUM_5 || keycode == Input.Keys.NUMPAD_5) && numPlayerInputs() >= 5 && game.base >= 5;
            hexagonTouchedP1 = (keycode == Input.Keys.NUM_6 || keycode == Input.Keys.NUMPAD_6) && numPlayerInputs() >= 6 && game.base >= 6;
            septagonTouchedP1 = (keycode == Input.Keys.NUM_7 || keycode == Input.Keys.NUMPAD_7) && numPlayerInputs() >= 7 && game.base >= 7;
            octagonTouchedP1 = (keycode == Input.Keys.NUM_8 || keycode == Input.Keys.NUMPAD_8) && numPlayerInputs() >= 8 && game.base >= 8;
            nonagonTouchedP1 = (keycode == Input.Keys.NUM_9 || keycode == Input.Keys.NUMPAD_9) && numPlayerInputs() >= 9 && game.base >= 9;
            playTouched = pointTouchedP1;
            homeTouched = lineTouchedP1;
            exitTouched = triangleTouchedP1;
        }
        pauseTouched = keycode == Input.Keys.ESCAPE;
        pauseBackTouched = pauseTouched;
        pauseQuitTouched = keycode == Input.Keys.X;
        pauseReplayTouched = keycode == Input.Keys.R;
        if(!splitScreen) {
            inputTouchedGameplay = pointTouched || lineTouched || triangleTouched || squareTouched || pentagonTouched || hexagonTouched || septagonTouched || octagonTouched || nonagonTouched;
        } else {
            inputTouchedGameplayP1 = pointTouchedP1 || lineTouchedP1 || triangleTouchedP1 || squareTouchedP1 || pentagonTouchedP1 || hexagonTouchedP1 || septagonTouchedP1 || octagonTouchedP1 || nonagonTouchedP1;
        }
        inputTouchedResults = playTouched || homeTouched || exitTouched;
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
                        pauseReplayTouched = false;
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
                        pauseReplayTouched = false;
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
            if(player == null || player.equals(P1)) {
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
        } else if(pauseReplayTouched) {
            game.confirmSound.play((float) (game.fxVolume / 10.0));
            endTime = System.currentTimeMillis();
            game.stats.updateTimePlayed(endTime - startTime, gameplayType);
            game.setScreen(new TutorialScreen(game, gameplayType));
            dispose();
        }
    }

    public void transitionShape(String player, int shapeAdded) {
        if(shapeIsAddable(player, shapeAdded)) {
            if (player == null) {
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
            } else if (player.equals("P1")) {
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
            if(showPlayerTargets()) {
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
            if(!showPlayerTargets()) {
                game.correctInputSound.play((float) (game.fxVolume / 10.0));
            }
            correctInput = true;
            transitionShapeIncrementPhase(player);
        } else {
            game.incorrectInputSound.play((float) (game.fxVolume / 10.0));
            correctInput = false;
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
                if(showPlayerScore()) {
                    score += multiplier;
                    if (multiplier < MAX_MULTIPLIER) {
                        multiplier++;
                    }
                }
                targetShapeList.clear();
                if (priorShapeList.get(priorShapeList.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeList.get(priorShapeList.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle && showBackgroundColorShapeListAndTimelines()) {
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
                if(showPlayerScore()) {
                    if (!useSaturation) {
                        scoreP1 += multiplierP1;
                        if (multiplierP1 < MAX_MULTIPLIER) {
                            multiplierP1++;
                        }
                    }
                }
                targetShapeListP1.clear();
                if (priorShapeListP1.get(priorShapeListP1.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP1.get(priorShapeListP1.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle && showBackgroundColorShapeListAndTimelines()) {
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
                    if(showPlayerScore()) {
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
                if(showPlayerScore()) {
                    if (!useSaturation) {
                        scoreP2 += multiplierP2;
                        if (multiplierP2 < MAX_MULTIPLIER) {
                            multiplierP2++;
                        }
                    }
                }
                targetShapeListP2.clear();
                if (priorShapeListP2.get(priorShapeListP2.size() - TWO_SHAPES_AGO).getColor().equals(priorShapeListP2.get(priorShapeListP2.size() - ONE_SHAPE_AGO).getColor()) && admitsOfSquirgle && showBackgroundColorShapeListAndTimelines()) {
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
                    if(showPlayerScore()) {
                        if (useSaturation) {
                            saturationP1++;
                        }
                    }
                }
                currentTargetShapeP2 = targetShapeListP2.get(0);
            }
        }
        shapesMatchedIncrementPhase(player);
        correctInput = true;
        keepSaturationsInBounds();
    }

    public void shapesMismatchedBehavior(String player) {
        //The wrong shape was touched
        game.incorrectInputSound.play((float) (game.fxVolume / 10.0));
        if(allowMistakes()) {
            if (player == null) {
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
                if (score > 0) {
                    score--;
                }
                multiplier = 1;
            } else if (player.equals(P1)) {
                if (useSaturation) {
                    saturationP1++;
                    keepSaturationsInBounds();
                } else {
                    multiplierP1 = 1;
                    if (scoreP1 > 0) {
                        scoreP1--;
                    }
                }
            } else if (player.equals(P2)) {
                if (useSaturation) {
                    saturationP2++;
                    keepSaturationsInBounds();
                } else {
                    multiplierP2 = 1;
                    if (scoreP2 > 0) {
                        scoreP2--;
                    }
                }
            }
        }
        correctInput = false;
    }

    public void keepSaturationsInBounds() {
        if(!unrestrictedPlay()) {
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

    public boolean shapeIsAddable(String player, int shape) {
        if(allowMistakes()) {
            return true;
        } else {
            Shape promptToUse;
            Shape currentTargetToUse;
            List<Shape> targetListToUse;
            int sum;

            if(player == null) {
                promptToUse = promptShape;
                currentTargetToUse = currentTargetShape;
                targetListToUse = targetShapeList;
            } else if(player.equals(P1)) {
                promptToUse = promptShapeP1;
                currentTargetToUse = currentTargetShapeP1;
                targetListToUse = targetShapeListP1;
            } else {
                promptToUse = promptShapeP2;
                currentTargetToUse = currentTargetShapeP2;
                targetListToUse = targetShapeListP2;
            }

            if (promptToUse.getShape() + (shape + 1) >= game.base) {
                sum = (promptToUse.getShape() + (shape + 1)) - game.base;
            } else {
                sum = promptToUse.getShape() + (shape + 1);
            }

            if(firstInputPhase() && sum == Shape.LINE) {
                return true;
            } else if(secondInputPhase() && sum == Shape.TRIANGLE) {
                return true;
            } else if(firstRolloverPhase() && ((promptToUse.getShape() + 1 < game.base && sum == promptToUse.getShape() + 1) || (promptToUse.getShape() + 1 == game.base && sum == (promptToUse.getShape() + (shape + 1)) - game.base))) {
                return true;
            } else if(thirdInputPhase() && shape == Shape.LINE) {
                return true;
            } else if(fourthInputPhase() && sum == Shape.SQUARE) {
                return true;
            } else if(fifthInputPhase() && sum == Shape.LINE) {
                return true;
            } else if(sixthInputPhase() && sum == Shape.LINE) {
                return true;
            } else if(firstTargetPhase() && sum == currentTargetToUse.getShape()) {
                return true;
            } else if(secondTargetPhase() && sum == currentTargetToUse.getShape()) {
                return true;
            } else if(firstScorePhase() && sum == currentTargetToUse.getShape()) {
                return true;
            } else if(firstMultiplierPhase() && sum == currentTargetToUse.getShape()) {
                return true;
            } else if(fourthTargetPhase() && sum == currentTargetToUse.getShape() && backgroundColorShape.getColor().equals(targetListToUse.get(0).getColor())) {
                return true;
            }

            return false;
        }
    }

    public void transitionShapeIncrementPhase(String player) {
        Shape promptToUse;

        if(player == null) {
            promptToUse = promptShape;
        } else if(player.equals(P1)) {
            promptToUse = promptShapeP1;
        } else {
            promptToUse = promptShapeP2;
        }

        if(firstInputPhase()) {
            if(promptToUse.getShape() == Shape.LINE) {
                phase++;
            }
        } else if(secondInputPhase()) {
            if(promptToUse.getShape() == Shape.TRIANGLE) {
                phase++;
            }
        } else if(firstRolloverPhase()) {
            if(promptToUse.getShape() == Shape.POINT) {
                phase++;
            }
        } else if(thirdInputPhase()) {
            if(promptToUse.getShape() == Shape.TRIANGLE) {
                phase++;
            }
        } else if(fourthInputPhase()) {
            if(promptToUse.getShape() == Shape.SQUARE) {
                phase++;
            }
        } else if(fifthInputPhase()) {
            if(promptToUse.getShape() == Shape.LINE) {
                phase++;
            }
        } else if(sixthInputPhase()) {
            if(promptToUse.getShape() == Shape.LINE) {
                phase++;
            }
        }
    }

    public void shapesMatchedIncrementPhase(String player) {
        Shape outsideTargetToUse;
        List<Shape> targetListToUse;
        Shape currentTargetToUse;
        int scoreToUse;
        int multiplierToUse;
        float squirgleOpacityToUse;

        if(player == null) {
            outsideTargetToUse = outsideTargetShape;
            targetListToUse = targetShapeList;
            currentTargetToUse = currentTargetShape;
            scoreToUse = score;
            multiplierToUse = multiplier;
            squirgleOpacityToUse = squirgleOpacity;
        } else if(player.equals(P1)) {
            outsideTargetToUse = outsideTargetShapeP1;
            targetListToUse = targetShapeListP1;
            currentTargetToUse = currentTargetShapeP1;
            scoreToUse = scoreP1;
            multiplierToUse = multiplierP1;
            squirgleOpacityToUse = squirgleOpacityP1;
        } else {
            outsideTargetToUse = outsideTargetShapeP2;
            targetListToUse = targetShapeListP2;
            currentTargetToUse = currentTargetShapeP2;
            scoreToUse = scoreP2;
            multiplierToUse = multiplierP2;
            squirgleOpacityToUse = squirgleOpacityP2;
        }

        if(firstTargetPhase()) {
            if(currentTargetToUse == outsideTargetToUse) {
                phase++;
            }
        } else if(secondTargetPhase()) {
            if(currentTargetToUse == targetListToUse.get(0)) {
                phase++;
            }
        } else if(firstScorePhase()) {
            System.out.println("here");
            if(useSaturation) {
                if(saturationP2 >= 5) {
                    phase++;
                }
            } else {
                if(scoreToUse >= 3) {
                    phase++;
                }
            }
        } else if(firstMultiplierPhase()) {
            if(multiplierToUse >= 5) {
                phase++;
            }
        } else if(secondScorePhase()) {
            if(scoreToUse >= 20) {
                phase++;
            }
        } else if(thirdTargetPhase()) {
            if(currentTargetToUse == outsideTargetToUse) {
                phase++;
            }
        } else if(fourthTargetPhase()) {
            if(outsideTargetToUse.getShape() == Shape.TRIANGLE && targetListToUse.get(0).getShape() == Shape.SQUARE) {
                phase++;
            }
        } else if(firstSquirglePhase()) {
            if(squirgleOpacityToUse >= 1) {
                phase++;
            }
        }
    }

    public boolean allowMistakes() {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            return phase >= 27 && phase != 35;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase >= 27 && phase != 36;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            return phase >= 27;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            return phase >= 32;
        }
        return false;
    }

    public int numPlayerInputs() {
        if(splitScreen) {
            if(phase < 5) {
                return 0;
            } else if(phase < 12) {
                return 1;
            } else {
                return game.base;
            }
        } else {
            if(phase < 4) {
                return 0;
            } else if(phase < 11) {
                return 1;
            } else {
                return game.base;
            }
        }
    }

    public boolean tapAnywhereToProgress() {
        return getCurrentFooterText().contains(tapOrClick + " anywhere");
    }

    public boolean unrestrictedPlay() {
        return phase >= numPhases;
    }

    public boolean showPlayerHand() {
        return phase >= 2;
    }

    public boolean showPause() {
        if(splitScreen) {
            return phase >= 4;
        } else {
            return phase >= 3;
        }
    }

    public boolean showPlayerTargets() {
        if(splitScreen) {
            return phase >= 17;
        } else {
            return phase >= 16;
        }
    }

    public boolean showPlayerScore() {
        if(splitScreen) {
            return phase >= 22;
        } else {
            return phase >= 21;
        }
    }

    public boolean showPlayerMultiplier() {
        if(splitScreen) {
            return phase >= 28;
        } else {
            return phase >= 23;
        }
    }

    public boolean showOpponentHand() {
        return phase >= 3;
    }

    public boolean showOpponentScore() {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase >= 22;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            return phase >= 24;
        }
        return false;
    }

    public boolean showBackgroundColorShapeListAndTimelines() {
        if(!splitScreen) {
            return phase >= 29;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase >= 30;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            return phase >= 34;
        }
        return false;
    }

    public boolean firstInputPhase() {
        if(!splitScreen) {
            return phase == 4;
        } else {
            return phase == 5;
        }
    }

    public boolean secondInputPhase() {
        if(!splitScreen) {
            return phase == 5;
        } else {
            return phase == 6;
        }
    }

    public boolean firstRolloverPhase() {
        if(!splitScreen) {
            return phase == 6;
        } else {
            return phase == 7;
        }
    }

    public boolean thirdInputPhase() {
        if(!splitScreen) {
            return phase == 11;
        } else {
            return phase == 12;
        }
    }

    public boolean fourthInputPhase() {
        if(!splitScreen) {
            return phase == 12;
        } else {
            return phase == 13;
        }
    }

    public boolean fifthInputPhase() {
        if(!splitScreen) {
            return phase == 13;
        } else {
            return phase == 14;
        }
    }

    public boolean sixthInputPhase() {
        if(!splitScreen) {
            return phase == 14;
        } else {
            return phase == 15;
        }
    }

    public boolean firstTargetPhase() {
        if(!splitScreen) {
            return phase == 17;
        } else {
            return phase == 18;
        }
    }

    public boolean secondTargetPhase() {
        if(!splitScreen) {
            return phase == 20;
        } else {
            return phase == 21;
        }
    }

    public boolean firstScorePhase() {
        if(!splitScreen) {
            return phase == 21;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase == 27;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            return phase == 26;
        }
        return false;
    }

    public boolean firstMultiplierPhase() {
        if(!splitScreen) {
            return phase == 25;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            return phase == 30;
        } else {
            return false;
        }
    }

    public boolean secondScorePhase() {
        if(!splitScreen) {
            return phase == 27;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            return phase == 32;
        } else {
            return false;
        }
    }

    public boolean thirdTargetPhase() {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            return phase == 34;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase == 35;
        } else {
            return false;
        }
    }

    public boolean fourthTargetPhase() {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            return phase == 35;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase == 36;
        } else {
            return false;
        }
    }

    public boolean firstSquirglePhase() {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            return phase == 37;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            return phase == 38;
        } else {
            return false;
        }
    }

    public String getCurrentHeaderText() {
        return gameplayTypeMap.get(gameplayType).get(correctInput).get(phase - 1).get(0);
    }

    public String getCurrentFooterText() {
        return gameplayTypeMap.get(gameplayType).get(correctInput).get(phase - 1).get(1);
    }

    public void updateLabels() {
        headerLabel.setText(getCurrentHeaderText());
        footerLabel.setText(getCurrentFooterText());

        if(game.widthGreater) {
            headerLabel.setSize(splitScreen ? promptShapeP1.getCoordinates().x - promptShapeP1.getRadius() - (8 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS) : promptShape.getCoordinates().x - promptShape.getRadius() - (8 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS), splitScreen ? (game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) : game.camera.viewportHeight - TARGET_RADIUS - (INPUT_POINT_SPAWN.y + INPUT_RADIUS));
            headerLabel.setPosition((8 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS), splitScreen ? INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS : INPUT_POINT_SPAWN.y + INPUT_RADIUS);
        } else {
            headerLabel.setSize(game.camera.viewportWidth - (2 * TARGET_RADIUS), splitScreen ? (game.camera.viewportHeight / 2) - (promptShapeP1.getCoordinates().y + promptShapeP1.getRadius()) : game.camera.viewportHeight - (promptShape.getCoordinates().y + promptShape.getRadius()));
            headerLabel.setPosition(TARGET_RADIUS, splitScreen ? promptShapeP1.getCoordinates().y + promptShapeP1.getRadius() : promptShape.getCoordinates().y + promptShape.getRadius());
        }
        if(game.widthGreater) {
            footerLabel.setSize(splitScreen ? game.camera.viewportWidth - INPUT_RADIUS - (promptShapeP1.getCoordinates().x + promptShapeP1.getRadius()) : game.camera.viewportWidth - INPUT_RADIUS - (promptShape.getCoordinates().x + promptShape.getRadius()), splitScreen ? (game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) : game.camera.viewportHeight - TARGET_RADIUS - (INPUT_POINT_SPAWN.y + INPUT_RADIUS));
            footerLabel.setPosition(splitScreen ? promptShapeP1.getCoordinates().x + promptShapeP1.getRadius() : promptShape.getCoordinates().x + promptShape.getRadius(), splitScreen ? INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS : INPUT_POINT_SPAWN.y + INPUT_RADIUS);
        } else {
            footerLabel.setSize(game.camera.viewportWidth - (2 * TARGET_RADIUS), splitScreen ? promptShapeP1.getCoordinates().y - promptShapeP1.getRadius() - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) : promptShape.getCoordinates().y - promptShape.getRadius() - (INPUT_POINT_SPAWN.y + INPUT_RADIUS));
            footerLabel.setPosition(TARGET_RADIUS, splitScreen ? INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS : INPUT_POINT_SPAWN.y + INPUT_RADIUS);
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
        PAUSE_INPUT_HEIGHT_BACK = game.camera.viewportHeight - (2 * game.partitionSize);
        PAUSE_INPUT_HEIGHT_MIDDLE = (game.camera.viewportHeight - (3 * game.partitionSize)) / 2;
        PAUSE_INPUT_RADIUS = PAUSE_INPUT_HEIGHT_MIDDLE > PAUSE_INPUT_WIDTH ? PAUSE_INPUT_WIDTH / 2 : PAUSE_INPUT_HEIGHT_MIDDLE / 2;
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
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 40f;
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
        promptShape = new Shape(Shape.POINT,
                INIT_PROMPT_RADIUS, Color.WHITE,
                null,
                INIT_PROMPT_RADIUS / Draw.LINE_WIDTH_DIVISOR,
                new Vector2(game.camera.viewportWidth / 2,
                        game.camera.viewportHeight / 2));
        promptShapeP1 = new Shape(Shape.POINT,
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
        playTouched = false;
        homeTouched = false;
        exitTouched = false;
        pauseTouched = false;
        pauseBackTouched = false;
        pauseQuitTouched = false;
        pauseReplayTouched = false;
        inputTouchedGameplay = false;
        inputTouchedGameplayP1 = false;
        inputTouchedGameplayP2 = false;
        inputTouchedResults = false;
        clearColor = new Color(backgroundColorShape.getColor());
        phase = 1;
        score = 1;
        scoreP1 = 1;
        scoreP2 = 0;
        saturationP1 = 0;
        saturationP2 = 1;
        multiplier = 2;
        multiplierP1 = 2;
        multiplierP2 = 1;
        gameOver = false;
        showResults = false;
        paused = false;
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

        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numPhases = 46;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            numPhases = 46;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numPhases = 37;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            numPhases = 43;
        }

        correctInput = true;

        tapOrClick = game.desktop ? CLICK : TAP;

        String baseName = "";
        if(game.base == 4) {
            baseName = "SQUARE";
        } else if(game.base == 5) {
            baseName = "PENTAGON";
        } else if(game.base == 6) {
            baseName = "HEXAGON";
        } else if(game.base == 7) {
            baseName = "SEPTAGON";
        } else if(game.base == 8) {
            baseName = "OCTAGON";
        } else if(game.base == 9) {
            baseName = "NONAGON";
        }

        squirgleHeaderCorrectPhase1 = "Welcome to the SQUIRGLE tutorial!";
        squirgleHeaderCorrectPhase2 = "That shape's your HAND";
        squirgleHeaderCorrectPhase3 = "On the right's your PAUSE button";
        squirgleHeaderCorrectPhase4 = "That new button's a POINT INPUT";
        squirgleHeaderCorrectPhase5 = "Your INPUTS change your HAND";
        squirgleHeaderCorrectPhase6 = "Killin' it";
        squirgleHeaderCorrectPhase7 = "Looks like your HAND ROLLED OVER to a smaller shape";
        squirgleHeaderCorrectPhase8 = "Seems you're in a BASE of " + game.base + ", or " + baseName;
        squirgleHeaderCorrectPhase9 = "You can't make your HAND bigger than your BASE!";
        squirgleHeaderCorrectPhase10 = "So your HAND ROLLS OVER when it surpasses " + baseName;
        squirgleHeaderCorrectPhase11 = "Speaking of " + baseName + ", you're ready for all the INPUTS";
        squirgleHeaderCorrectPhase12 = "POINT + LINE = TRIANGLE. Easy";
        squirgleHeaderCorrectPhase13 = "Bravo! Let's see if you can get this one";
        squirgleHeaderCorrectPhase14 = "Fantastic. Making use of the ROLLOVER. I like it";
        squirgleHeaderCorrectPhase15 = "Yep, Adding your BASE makes any shape from itself";
        squirgleHeaderCorrectPhase16 = "Time for a new element. Those shapes are your TARGETS";
        squirgleHeaderCorrectPhase17 = "Glowing things tend to be important in games";
        squirgleHeaderCorrectPhase18 = "The TARGET you just made is now next to your new, randomly generated HAND";
        squirgleHeaderCorrectPhase19 = "This gives you a good idea of your performance";
        squirgleHeaderCorrectPhase20 = "Looks like there's one more glowing TARGET, though";
        squirgleHeaderCorrectPhase21 = "Awesome! Making both of your TARGETS increased your SCORE";
        squirgleHeaderCorrectPhase22 = "Notice that your SCORE jumped from 1 to 3";
        squirgleHeaderCorrectPhase23 = "This is because your SCORE increases by your MULTIPLIER";
        squirgleHeaderCorrectPhase24 = "Your MULTIPLIER is the number underneath your SCORE (X#)";
        squirgleHeaderCorrectPhase25 = "Your MULTIPLIER also increases after making two TARGETS";
        squirgleHeaderCorrectPhase26 = "Brilliant! Note that your MULTIPLIER can't exceed 5";
        squirgleHeaderCorrectPhase27 = "I think we can loosen the reins a bit";
        squirgleHeaderCorrectPhase28 = "Wonderful! Remember that incorrect INPUTS lower your SCORE and MULTIPLIER";
        squirgleHeaderCorrectPhase29 = "Last lesson! See those white lines to the left?";
        squirgleHeaderCorrectPhase30 = "Those are your TIMELINES. When they deplete, it's GAME OVER";
        squirgleHeaderCorrectPhase31 = "While we're still learning, only the first TIMELINE depletes";
        squirgleHeaderCorrectPhase32 = "Now, note the color SWATCHES next to the TIMELINES";
        squirgleHeaderCorrectPhase33 = "They change the color of the screen";
        squirgleHeaderCorrectPhase34 = "I promise this is important";
        squirgleHeaderCorrectPhase35 = "When made, a TARGET's color is set to the screen's";
        squirgleHeaderCorrectPhase36 = "Make both TARGETS the same color, and your next TARGETS are a SQUIRGLE!";
        squirgleHeaderCorrectPhase37 = "SQUIRGLES are special TARGETS with special benefits";
        squirgleHeaderCorrectPhase38 = "Making a SQUIRGLE from your HAND resets your TIMELINES!";
        squirgleHeaderCorrectPhase39 = "Resetting your TIMELINES gives you more time to play";
        squirgleHeaderCorrectPhase40 = "More time = higher SCORE";
        squirgleHeaderCorrectPhase41 = "get a SCORE of " + Squirgle.SCORE_TO_UNLOCK_NEW_BASE + " in SQUIRGLE mode to unlock another BASE";
        squirgleHeaderCorrectPhase42 = "You have to be playing with your largest unlocked BASE, though";
        squirgleHeaderCorrectPhase43 = "You can always play this or other tutorials again";
        squirgleHeaderCorrectPhase44 = "They can be found under the main menu's [?] section";
        squirgleHeaderCorrectPhase45 = "I think you're ready to SQUIRGLE unfettered. Good luck!";

        squirgleHeaderIncorrectPhase1 = "";
        squirgleHeaderIncorrectPhase2 = "";
        squirgleHeaderIncorrectPhase3 = "";
        squirgleHeaderIncorrectPhase4 = "";
        squirgleHeaderIncorrectPhase5 = "";
        squirgleHeaderIncorrectPhase6 = "";
        squirgleHeaderIncorrectPhase7 = "";
        squirgleHeaderIncorrectPhase8 = "";
        squirgleHeaderIncorrectPhase9 = "";
        squirgleHeaderIncorrectPhase10 = "";
        squirgleHeaderIncorrectPhase11 = "Not that one; " + tapOrClick.toLowerCase() + " the LINE INPUT";
        squirgleHeaderIncorrectPhase12 = "Here's a hint: 3 + 1 = 4...TRIANGLE + ? = SQUARE";
        squirgleHeaderIncorrectPhase13 = "If LINE > HAND, add [LINE - HAND].\nOtherwise, add [" + baseName + " - HAND + LINE]";
        squirgleHeaderIncorrectPhase14 = "Here's a hint: shape + " + baseName + " = shape";
        squirgleHeaderIncorrectPhase15 = "";
        squirgleHeaderIncorrectPhase16 = "";
        squirgleHeaderIncorrectPhase17 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase18 = "";
        squirgleHeaderIncorrectPhase19 = "";
        squirgleHeaderIncorrectPhase20 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase21 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase22 = "";
        squirgleHeaderIncorrectPhase23 = "";
        squirgleHeaderIncorrectPhase24 = "";
        squirgleHeaderIncorrectPhase25 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase26 = "";
        squirgleHeaderIncorrectPhase27 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase28 = "";
        squirgleHeaderIncorrectPhase29 = "";
        squirgleHeaderIncorrectPhase30 = "";
        squirgleHeaderIncorrectPhase31 = "";
        squirgleHeaderIncorrectPhase32 = "";
        squirgleHeaderIncorrectPhase33 = "";
        squirgleHeaderIncorrectPhase34 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase35 = "Wait until the screen's color matches your first TARGET's, then add the correct INPUT";
        squirgleHeaderIncorrectPhase36 = "";
        squirgleHeaderIncorrectPhase37 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        squirgleHeaderIncorrectPhase38 = "";
        squirgleHeaderIncorrectPhase39 = "";
        squirgleHeaderIncorrectPhase40 = "";
        squirgleHeaderIncorrectPhase41 = "";
        squirgleHeaderIncorrectPhase42 = "";
        squirgleHeaderIncorrectPhase43 = "";
        squirgleHeaderIncorrectPhase44 = "";
        squirgleHeaderIncorrectPhase45 = "";

        squirgleFooterPhase1 = tapOrClick + " anywhere to begin";
        squirgleFooterPhase2 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase3 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase4 = "Press it";
        squirgleFooterPhase5 = "Add another POINT";
        squirgleFooterPhase6 = "Keep adding POINTS";
        squirgleFooterPhase7 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase8 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase9 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase10 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase11 = "Add a LINE";
        squirgleFooterPhase12 = "Make a SQUARE";
        squirgleFooterPhase13 = "Make a LINE";
        squirgleFooterPhase14 = "Make a LINE...again";
        squirgleFooterPhase15 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase16 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase17 = "Make the glowing TARGET from your HAND";
        squirgleFooterPhase18 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase19 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase20 = "Make the other glowing TARGET from your HAND";
        squirgleFooterPhase21 = "Get a SCORE of 3";
        squirgleFooterPhase22 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase23 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase24 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase25 = "Get a MULTIPLIER of 5";
        squirgleFooterPhase26 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase27 = "Reach a SCORE of 20. Mistakes are now permitted";
        squirgleFooterPhase28 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase29 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase30 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase31 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase32 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase33 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase34 = "Make your first TARGET from your HAND";
        squirgleFooterPhase35 = "Make your second TARGET the same color as the first";
        squirgleFooterPhase36 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase37 = "Make both TARGETS in the SQUIRGLE from your HAND";
        squirgleFooterPhase38 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase39 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase40 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase41 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase42 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase43 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase44 = tapOrClick + " anywhere to continue";
        squirgleFooterPhase45 = tapOrClick + " anywhere to SQUIRGLE without restrictions";

        battleHeaderCorrectPhase1 = "Welcome to the BATTLE tutorial!";
        battleHeaderCorrectPhase2 = "That shape's your HAND";
        battleHeaderCorrectPhase3 = "That top shape's your OPPONENT's HAND";
        battleHeaderCorrectPhase4 = "In the lower right's your PAUSE button";
        battleHeaderCorrectPhase5 = "That new button's a POINT INPUT";
        battleHeaderCorrectPhase6 = "Your INPUTS change your HAND";
        battleHeaderCorrectPhase7 = "Killin' it";
        battleHeaderCorrectPhase8 = "Looks like your HAND ROLLED OVER to a smaller shape";
        battleHeaderCorrectPhase9 = "Seems you're in a BASE of " + game.base + ", or " + baseName;
        battleHeaderCorrectPhase10 = "You can't make your HAND bigger than your BASE!";
        battleHeaderCorrectPhase11 = "So your HAND ROLLS OVER when it surpasses " + baseName;
        battleHeaderCorrectPhase12 = "Speaking of " + baseName + ", you're ready for all the INPUTS";
        battleHeaderCorrectPhase13 = "POINT + LINE = TRIANGLE. Easy";
        battleHeaderCorrectPhase14 = "Bravo! Let's see if you can get this one";
        battleHeaderCorrectPhase15 = "Fantastic. Making use of the ROLLOVER. I like it";
        battleHeaderCorrectPhase16 = "Yep, adding your BASE makes any shape from itself";
        battleHeaderCorrectPhase17 = "Time for a new element. Those shapes are your TARGETS";
        battleHeaderCorrectPhase18 = "Glowing things tend to be important in games";
        battleHeaderCorrectPhase19 = "The TARGET you just made is now next to your new, randomly generated HAND";
        battleHeaderCorrectPhase20 = "This gives you a good idea of your performance";
        battleHeaderCorrectPhase21 = "Looks like there's one more glowing TARGET, though";
        battleHeaderCorrectPhase22 = "Awesome! Making both of your TARGETS increased your OPPONENT's BURST";
        battleHeaderCorrectPhase23 = "The BURST meter is what determines victory in BATTLE";
        battleHeaderCorrectPhase24 = "If you fill up your OPPONENT's BURST, you win!";
        battleHeaderCorrectPhase25 = "If your OPPONENT fills up your BURST, you lose...";
        battleHeaderCorrectPhase26 = "If time runs out, the player with the lowest BURST wins";
        battleHeaderCorrectPhase27 = "I think we can loosen the reins a bit";
        battleHeaderCorrectPhase28 = "Wonderful! Remember that incorrect INPUTS actually increase YOUR BURST";
        battleHeaderCorrectPhase29 = "At this point in the tutorial, BURSTS can't exceed 5";
        battleHeaderCorrectPhase30 = "Last lesson! See those white lines in the lower left?";
        battleHeaderCorrectPhase31 = "Those are your TIMELINES. When they deplete, it's GAME OVER";
        battleHeaderCorrectPhase32 = "While we're still learning, only the first TIMELINE depletes";
        battleHeaderCorrectPhase33 = "Now, note the color SWATCHES next to the TIMELINES";
        battleHeaderCorrectPhase34 = "They change the color of the screen";
        battleHeaderCorrectPhase35 = "I promise this is important";
        battleHeaderCorrectPhase36 = "When made, a TARGET's color is set to the screen's";
        battleHeaderCorrectPhase37 = "Make both TARGETS the same color, and your next TARGETS are a SQUIRGLE!";
        battleHeaderCorrectPhase38 = "SQUIRGLES are special TARGETS with special benefits";
        battleHeaderCorrectPhase39 = "Making a SQUIRGLE from your HAND increases your OPPONENT's BURST by 3!";
        battleHeaderCorrectPhase40 = "And it also lowers your BURST by 5!";
        battleHeaderCorrectPhase41 = "SQUIRGLES can quickly turn the tide of a close BATTLE";
        battleHeaderCorrectPhase42 = "You can always play this or other tutorials again";
        battleHeaderCorrectPhase43 = "They can be found under the main menu's [?] section";
        battleHeaderCorrectPhase44 = "I think you're ready to BATTLE unfettered";
        battleHeaderCorrectPhase45 = "After this point, your OPPONENT will play as well. Good luck!";

        battleHeaderIncorrectPhase1 = "";
        battleHeaderIncorrectPhase2 = "";
        battleHeaderIncorrectPhase3 = "";
        battleHeaderIncorrectPhase4 = "";
        battleHeaderIncorrectPhase5 = "";
        battleHeaderIncorrectPhase6 = "";
        battleHeaderIncorrectPhase7 = "";
        battleHeaderIncorrectPhase8 = "";
        battleHeaderIncorrectPhase9 = "";
        battleHeaderIncorrectPhase10 = "";
        battleHeaderIncorrectPhase11 = "";
        battleHeaderIncorrectPhase12 = "Not that one; " + tapOrClick.toLowerCase() + " the LINE INPUT";
        battleHeaderIncorrectPhase13 = "Here's a hint: 3 + 1 = 4...TRIANGLE + ? = SQUARE";
        battleHeaderIncorrectPhase14 = "If LINE > HAND, add [LINE - HAND].\nOtherwise, add [" + baseName + " - HAND + LINE]";
        battleHeaderIncorrectPhase15 = "Here's a hint: shape + " + baseName + " = shape";
        battleHeaderIncorrectPhase16 = "";
        battleHeaderIncorrectPhase17 = "";
        battleHeaderIncorrectPhase18 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        battleHeaderIncorrectPhase19 = "";
        battleHeaderIncorrectPhase20 = "";
        battleHeaderIncorrectPhase21 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        battleHeaderIncorrectPhase22 = "";
        battleHeaderIncorrectPhase23 = "";
        battleHeaderIncorrectPhase24 = "";
        battleHeaderIncorrectPhase25 = "";
        battleHeaderIncorrectPhase26 = "";
        battleHeaderIncorrectPhase27 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        battleHeaderIncorrectPhase28 = "";
        battleHeaderIncorrectPhase29 = "";
        battleHeaderIncorrectPhase30 = "";
        battleHeaderIncorrectPhase31 = "";
        battleHeaderIncorrectPhase32 = "";
        battleHeaderIncorrectPhase33 = "";
        battleHeaderIncorrectPhase34 = "";
        battleHeaderIncorrectPhase35 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        battleHeaderIncorrectPhase36 = "Wait until the screen's color matches your first TARGET's, then add the correct INPUT";
        battleHeaderIncorrectPhase37 = "";
        battleHeaderIncorrectPhase38 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        battleHeaderIncorrectPhase39 = "";
        battleHeaderIncorrectPhase40 = "";
        battleHeaderIncorrectPhase41 = "";
        battleHeaderIncorrectPhase42 = "";
        battleHeaderIncorrectPhase43 = "";
        battleHeaderIncorrectPhase44 = "";
        battleHeaderIncorrectPhase45 = "";

        battleFooterPhase1 = tapOrClick + " anywhere to begin";
        battleFooterPhase2 = tapOrClick + " anywhere to continue";
        battleFooterPhase3 = tapOrClick + " anywhere to continue";
        battleFooterPhase4 = tapOrClick + " anywhere to continue";
        battleFooterPhase5 = "Press it";
        battleFooterPhase6 = "Add another POINT";
        battleFooterPhase7 = "Keep adding POINTS";
        battleFooterPhase8 = tapOrClick + " anywhere to continue";
        battleFooterPhase9 = tapOrClick + " anywhere to continue";
        battleFooterPhase10 = tapOrClick + " anywhere to continue";
        battleFooterPhase11 = tapOrClick + " anywhere to continue";
        battleFooterPhase12 = "Add a LINE";
        battleFooterPhase13 = "Make a SQUARE";
        battleFooterPhase14 = "Make a LINE";
        battleFooterPhase15 = "Make a LINE...again";
        battleFooterPhase16 = tapOrClick + " anywhere to continue";
        battleFooterPhase17 = tapOrClick + " anywhere to continue";
        battleFooterPhase18 = "Make the glowing TARGET from your HAND";
        battleFooterPhase19 = tapOrClick + " anywhere to continue";
        battleFooterPhase20 = tapOrClick + " anywhere to continue";
        battleFooterPhase21 = "Make the other glowing TARGET from your HAND";
        battleFooterPhase22 = tapOrClick + " anywhere to continue";
        battleFooterPhase23 = tapOrClick + " anywhere to continue";
        battleFooterPhase24 = tapOrClick + " anywhere to continue";
        battleFooterPhase25 = tapOrClick + " anywhere to continue";
        battleFooterPhase26 = tapOrClick + " anywhere to continue";
        battleFooterPhase27 = "Get your OPPONENT's BURST to 5 notches. Mistakes are now permitted";
        battleFooterPhase28 = tapOrClick + " anywhere to continue";
        battleFooterPhase29 = tapOrClick + " anywhere to continue";
        battleFooterPhase30 = tapOrClick + " anywhere to continue";
        battleFooterPhase31 = tapOrClick + " anywhere to continue";
        battleFooterPhase32 = tapOrClick + " anywhere to continue";
        battleFooterPhase33 = tapOrClick + " anywhere to continue";
        battleFooterPhase34 = tapOrClick + " anywhere to continue";
        battleFooterPhase35 = "Make your first TARGET from your HAND";
        battleFooterPhase36 = "Make your second TARGET the same color as the first";
        battleFooterPhase37 = tapOrClick + " anywhere to continue";
        battleFooterPhase38 = "Make both TARGETS in the SQUIRGLE from your HAND";
        battleFooterPhase39 = tapOrClick + " anywhere to continue";
        battleFooterPhase40 = tapOrClick + " anywhere to continue";
        battleFooterPhase41 = tapOrClick + " anywhere to continue";
        battleFooterPhase42 = tapOrClick + " anywhere to continue";
        battleFooterPhase43 = tapOrClick + " anywhere to continue";
        battleFooterPhase44 = tapOrClick + " anywhere to continue";
        battleFooterPhase45 = tapOrClick + " anywhere to BATTLE your OPPONENT without restrictions";

        timeAttackHeaderCorrectPhase1 = "Welcome to the TIME ATTACK tutorial!";
        timeAttackHeaderCorrectPhase2 = "That shape's your HAND";
        timeAttackHeaderCorrectPhase3 = "On the right's your PAUSE button";
        timeAttackHeaderCorrectPhase4 = "That new button's a POINT INPUT";
        timeAttackHeaderCorrectPhase5 = "Your INPUTS change your HAND";
        timeAttackHeaderCorrectPhase6 = "Killin' it";
        timeAttackHeaderCorrectPhase7 = "Looks like your HAND ROLLED OVER to a smaller shape";
        timeAttackHeaderCorrectPhase8 = "Seems you're in a BASE of " + game.base + ", or " + baseName;
        timeAttackHeaderCorrectPhase9 = "You can't make your HAND bigger than your BASE!";
        timeAttackHeaderCorrectPhase10 = "So your HAND ROLLS OVER when it surpasses " + baseName;
        timeAttackHeaderCorrectPhase11 = "Speaking of " + baseName + ", you're ready for all the INPUTS";
        timeAttackHeaderCorrectPhase12 = "POINT + LINE = TRIANGLE. Easy";
        timeAttackHeaderCorrectPhase13 = "Bravo! Let's see if you can get this one";
        timeAttackHeaderCorrectPhase14 = "Fantastic. Making use of the ROLLOVER. I like it";
        timeAttackHeaderCorrectPhase15 = "Yep, adding your BASE makes any shape from itself";
        timeAttackHeaderCorrectPhase16 = "Time for a new element. Those shapes are your TARGETS";
        timeAttackHeaderCorrectPhase17 = "Glowing things tend to be important in games";
        timeAttackHeaderCorrectPhase18 = "The TARGET you just made is now next to your new, randomly generated HAND";
        timeAttackHeaderCorrectPhase19 = "This gives you a good idea of your performance";
        timeAttackHeaderCorrectPhase20 = "Looks like there's one more glowing TARGET, though";
        timeAttackHeaderCorrectPhase21 = "Awesome! Making both of your TARGETS increased your SCORE";
        timeAttackHeaderCorrectPhase22 = "Notice that your SCORE jumped from 1 to 3";
        timeAttackHeaderCorrectPhase23 = "This is because your SCORE increases by your MULTIPLIER";
        timeAttackHeaderCorrectPhase24 = "Your MULTIPLIER is the number underneath your SCORE (X#)";
        timeAttackHeaderCorrectPhase25 = "Your MULTIPLIER also increases after making two TARGETS";
        timeAttackHeaderCorrectPhase26 = "Brilliant! Note that your MULTIPLIER can't exceed 5";
        timeAttackHeaderCorrectPhase27 = "I think we can loosen the reins a bit";
        timeAttackHeaderCorrectPhase28 = "Wonderful! Remember that incorrect INPUTS lower your SCORE and MULTIPLIER";
        timeAttackHeaderCorrectPhase29 = "Last lesson! See those white lines to the left?";
        timeAttackHeaderCorrectPhase30 = "Those are your TIMELINES. When they deplete, it's GAME OVER";
        timeAttackHeaderCorrectPhase31 = "While we're still learning, only the first TIMELINE depletes";
        timeAttackHeaderCorrectPhase32 = "In a full game, you set the play time";
        timeAttackHeaderCorrectPhase33 = "The TIMELINES will then deplete after 1, 3, or 5 minutes";
        timeAttackHeaderCorrectPhase34 = "You can always play this or other tutorials again";
        timeAttackHeaderCorrectPhase35 = "They can be found under the main menu's [?] section";
        timeAttackHeaderCorrectPhase36 = "I think you're ready to TIME ATTACK unfettered. Good luck!";

        timeAttackHeaderIncorrectPhase1 = "";
        timeAttackHeaderIncorrectPhase2 = "";
        timeAttackHeaderIncorrectPhase3 = "";
        timeAttackHeaderIncorrectPhase4 = "";
        timeAttackHeaderIncorrectPhase5 = "";
        timeAttackHeaderIncorrectPhase6 = "";
        timeAttackHeaderIncorrectPhase7 = "";
        timeAttackHeaderIncorrectPhase8 = "";
        timeAttackHeaderIncorrectPhase9 = "";
        timeAttackHeaderIncorrectPhase10 = "";
        timeAttackHeaderIncorrectPhase11 = "Not that one; " + tapOrClick.toLowerCase() + " the LINE INPUT";
        timeAttackHeaderIncorrectPhase12 = "Here's a hint: 3 + 1 = 4...TRIANGLE + ? = SQUARE";
        timeAttackHeaderIncorrectPhase13 = "If LINE > HAND, add [LINE - HAND].\nOtherwise, add [" + baseName + " - HAND + LINE]";
        timeAttackHeaderIncorrectPhase14 = "Here's a hint: shape + " + baseName + " = shape";
        timeAttackHeaderIncorrectPhase15 = "";
        timeAttackHeaderIncorrectPhase16 = "";
        timeAttackHeaderIncorrectPhase17 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeAttackHeaderIncorrectPhase18 = "";
        timeAttackHeaderIncorrectPhase19 = "";
        timeAttackHeaderIncorrectPhase20 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeAttackHeaderIncorrectPhase21 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeAttackHeaderIncorrectPhase22 = "";
        timeAttackHeaderIncorrectPhase23 = "";
        timeAttackHeaderIncorrectPhase24 = "";
        timeAttackHeaderIncorrectPhase25 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeAttackHeaderIncorrectPhase26 = "";
        timeAttackHeaderIncorrectPhase27 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeAttackHeaderIncorrectPhase28 = "";
        timeAttackHeaderIncorrectPhase29 = "";
        timeAttackHeaderIncorrectPhase30 = "";
        timeAttackHeaderIncorrectPhase31 = "";
        timeAttackHeaderIncorrectPhase32 = "";
        timeAttackHeaderIncorrectPhase33 = "";
        timeAttackHeaderIncorrectPhase34 = "";
        timeAttackHeaderIncorrectPhase35 = "";
        timeAttackHeaderIncorrectPhase36 = "";

        timeAttackFooterPhase1 = tapOrClick + " anywhere to begin";
        timeAttackFooterPhase2 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase3 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase4 = "Press it";
        timeAttackFooterPhase5 = "Add another POINT";
        timeAttackFooterPhase6 = "Keep adding POINTS";
        timeAttackFooterPhase7 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase8 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase9 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase10 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase11 = "Add a LINE";
        timeAttackFooterPhase12 = "Make a SQUARE";
        timeAttackFooterPhase13 = "Make a LINE";
        timeAttackFooterPhase14 = "Make a LINE...again";
        timeAttackFooterPhase15 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase16 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase17 = "Make the glowing TARGET from your HAND";
        timeAttackFooterPhase18 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase19 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase20 = "Make the other glowing TARGET from your HAND";
        timeAttackFooterPhase21 = "Get a SCORE of 3";
        timeAttackFooterPhase22 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase23 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase24 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase25 = "Get a MULTIPLIER of 5";
        timeAttackFooterPhase26 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase27 = "Reach a SCORE of 20. Mistakes are now permitted";
        timeAttackFooterPhase28 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase29 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase30 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase31 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase32 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase33 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase34 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase35 = tapOrClick + " anywhere to continue";
        timeAttackFooterPhase36 = tapOrClick + " anywhere to TIME ATTACK without restrictions";

        timeBattleHeaderCorrectPhase1 = "Welcome to the TIME BATTLE tutorial!";
        timeBattleHeaderCorrectPhase2 = "That shape's your HAND";
        timeBattleHeaderCorrectPhase3 = "That top shape's your OPPONENT's HAND";
        timeBattleHeaderCorrectPhase4 = "In the lower right's your PAUSE button";
        timeBattleHeaderCorrectPhase5 = "That new button's a POINT INPUT";
        timeBattleHeaderCorrectPhase6 = "Your INPUTS change your HAND";
        timeBattleHeaderCorrectPhase7 = "Killin' it";
        timeBattleHeaderCorrectPhase8 = "Looks like your HAND ROLLED OVER to a smaller shape";
        timeBattleHeaderCorrectPhase9 = "Seems you're in a BASE of " + game.base + ", or " + baseName;
        timeBattleHeaderCorrectPhase10 = "You can't make your HAND bigger than your BASE!";
        timeBattleHeaderCorrectPhase11 = "So your HAND ROLLS OVER when it surpasses " + baseName;
        timeBattleHeaderCorrectPhase12 = "Speaking of " + baseName + ", you're ready for all the INPUTS";
        timeBattleHeaderCorrectPhase13 = "POINT + LINE = TRIANGLE. Easy";
        timeBattleHeaderCorrectPhase14 = "Bravo! Let's see if you can get this one";
        timeBattleHeaderCorrectPhase15 = "Fantastic. Making use of the ROLLOVER. I like it";
        timeBattleHeaderCorrectPhase16 = "Yep, adding your BASE makes any shape from itself";
        timeBattleHeaderCorrectPhase17 = "Time for a new element. Those shapes are your TARGETS";
        timeBattleHeaderCorrectPhase18 = "Glowing things tend to be important in games";
        timeBattleHeaderCorrectPhase19 = "The TARGET you just made is now next to your new, randomly generated HAND";
        timeBattleHeaderCorrectPhase20 = "This gives you a good idea of your performance";
        timeBattleHeaderCorrectPhase21 = "Looks like there's one more glowing TARGET, though";
        timeBattleHeaderCorrectPhase22 = "Awesome! Making both of your TARGETS increased your SCORE";
        timeBattleHeaderCorrectPhase23 = "Your SCORE is what determines victory in TIME BATTLE";
        timeBattleHeaderCorrectPhase24 = "When time runs out, your SCORE's compared with your OPPONENT's";
        timeBattleHeaderCorrectPhase25 = "If your SCORE's higher, you win!";
        timeBattleHeaderCorrectPhase26 = "If your SCORE's lower, you lose...";
        timeBattleHeaderCorrectPhase27 = "Notice that your SCORE jumped from 1 to 3";
        timeBattleHeaderCorrectPhase28 = "This is because your SCORE increases by your MULTIPLIER";
        timeBattleHeaderCorrectPhase29 = "Your MULTIPLIER is the number underneath your SCORE (X#)";
        timeBattleHeaderCorrectPhase30 = "Your MULTIPLIER also increases after making two TARGETS";
        timeBattleHeaderCorrectPhase31 = "Brilliant! Note that your MULTIPLIER can't exceed 5";
        timeBattleHeaderCorrectPhase32 = "I think we can loosen the reins a bit";
        timeBattleHeaderCorrectPhase33 = "Wonderful! Remember that incorrect INPUTS lower your SCORE and MULTIPLIER";
        timeBattleHeaderCorrectPhase34 = "Last lesson! See those white lines to the left?";
        timeBattleHeaderCorrectPhase35 = "Those are your TIMELINES. When they deplete, it's GAME OVER";
        timeBattleHeaderCorrectPhase36 = "While we're still learning, only the first TIMELINE depletes";
        timeBattleHeaderCorrectPhase37 = "In a full game, you set the play time";
        timeBattleHeaderCorrectPhase38 = "The TIMELINES will then deplete after 1, 3, or 5 minutes";
        timeBattleHeaderCorrectPhase39 = "You can always play this or other tutorials again";
        timeBattleHeaderCorrectPhase40 = "They can be found under the main menu's [?] section";
        timeBattleHeaderCorrectPhase41 = "I think you're ready to TIME BATTLE unfettered";
        timeBattleHeaderCorrectPhase42 = "After this point, your OPPONENT will play as well. Good luck!";

        timeBattleHeaderIncorrectPhase1 = "";
        timeBattleHeaderIncorrectPhase2 = "";
        timeBattleHeaderIncorrectPhase3 = "";
        timeBattleHeaderIncorrectPhase4 = "";
        timeBattleHeaderIncorrectPhase5 = "";
        timeBattleHeaderIncorrectPhase6 = "";
        timeBattleHeaderIncorrectPhase7 = "";
        timeBattleHeaderIncorrectPhase8 = "";
        timeBattleHeaderIncorrectPhase9 = "";
        timeBattleHeaderIncorrectPhase10 = "";
        timeBattleHeaderIncorrectPhase11 = "";
        timeBattleHeaderIncorrectPhase12 = "Not that one; " + tapOrClick.toLowerCase() + " the LINE INPUT";
        timeBattleHeaderIncorrectPhase13 = "Here's a hint: 3 + 1 = 4...TRIANGLE + ? = SQUARE";
        timeBattleHeaderIncorrectPhase14 = "If LINE > HAND, add [LINE - HAND].\nOtherwise, add [" + baseName + " - HAND + LINE]";
        timeBattleHeaderIncorrectPhase15 = "Here's a hint: shape + " + baseName + " = shape";
        timeBattleHeaderIncorrectPhase16 = "";
        timeBattleHeaderIncorrectPhase17 = "";
        timeBattleHeaderIncorrectPhase18 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeBattleHeaderIncorrectPhase19 = "";
        timeBattleHeaderIncorrectPhase20 = "";
        timeBattleHeaderIncorrectPhase21 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeBattleHeaderIncorrectPhase22 = "";
        timeBattleHeaderIncorrectPhase23 = "";
        timeBattleHeaderIncorrectPhase24 = "";
        timeBattleHeaderIncorrectPhase25 = "";
        timeBattleHeaderIncorrectPhase26 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeBattleHeaderIncorrectPhase27 = "";
        timeBattleHeaderIncorrectPhase28 = "";
        timeBattleHeaderIncorrectPhase29 = "";
        timeBattleHeaderIncorrectPhase30 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeBattleHeaderIncorrectPhase31 = "";
        timeBattleHeaderIncorrectPhase32 = "If TARGET > HAND, add [TARGET - HAND].\nOtherwise, add [" + baseName + " - HAND + TARGET]";
        timeBattleHeaderIncorrectPhase33 = "";
        timeBattleHeaderIncorrectPhase34 = "";
        timeBattleHeaderIncorrectPhase35 = "";
        timeBattleHeaderIncorrectPhase36 = "";
        timeBattleHeaderIncorrectPhase37 = "";
        timeBattleHeaderIncorrectPhase38 = "";
        timeBattleHeaderIncorrectPhase39 = "";
        timeBattleHeaderIncorrectPhase40 = "";
        timeBattleHeaderIncorrectPhase41 = "";
        timeBattleHeaderIncorrectPhase42 = "";

        timeBattleFooterPhase1 = tapOrClick + " anywhere to begin";
        timeBattleFooterPhase2 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase3 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase4 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase5 = "Press it";
        timeBattleFooterPhase6 = "Add another POINT";
        timeBattleFooterPhase7 = "Keep adding POINTS";
        timeBattleFooterPhase8 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase9 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase10 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase11 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase12 = "Add a LINE";
        timeBattleFooterPhase13 = "Make a SQUARE";
        timeBattleFooterPhase14 = "Make a LINE";
        timeBattleFooterPhase15 = "Make a LINE...again";
        timeBattleFooterPhase16 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase17 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase18 = "Make the glowing TARGET from your HAND";
        timeBattleFooterPhase19 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase20 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase21 = "Make the other glowing TARGET from your HAND";
        timeBattleFooterPhase22 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase23 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase24 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase25 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase26 = "Get a SCORE of 3";
        timeBattleFooterPhase27 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase28 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase29 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase30 = "Get a MULTIPLIER of 5";
        timeBattleFooterPhase31 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase32 = "Reach a SCORE of 20. Mistakes are now permitted";
        timeBattleFooterPhase33 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase34 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase35 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase36 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase37 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase38 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase39 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase40 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase41 = tapOrClick + " anywhere to continue";
        timeBattleFooterPhase42 = tapOrClick + " anywhere to TIME BATTLE without restrictions";

        squirgleHeaderAndFooterCorrectPhase1 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase2 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase3 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase4 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase5 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase6 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase7 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase8 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase9 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase10 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase11 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase12 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase13 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase14 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase15 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase16 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase17 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase18 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase19 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase20 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase21 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase22 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase23 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase24 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase25 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase26 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase27 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase28 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase29 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase30 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase31 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase32 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase33 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase34 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase35 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase36 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase37 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase38 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase39 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase40 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase41 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase42 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase43 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase44 = new ArrayList<String>();
        squirgleHeaderAndFooterCorrectPhase45 = new ArrayList<String>();

        squirgleHeaderAndFooterCorrectPhase1.add(squirgleHeaderCorrectPhase1);
        squirgleHeaderAndFooterCorrectPhase1.add(squirgleFooterPhase1);
        squirgleHeaderAndFooterCorrectPhase2.add(squirgleHeaderCorrectPhase2);
        squirgleHeaderAndFooterCorrectPhase2.add(squirgleFooterPhase2);
        squirgleHeaderAndFooterCorrectPhase3.add(squirgleHeaderCorrectPhase3);
        squirgleHeaderAndFooterCorrectPhase3.add(squirgleFooterPhase3);
        squirgleHeaderAndFooterCorrectPhase4.add(squirgleHeaderCorrectPhase4);
        squirgleHeaderAndFooterCorrectPhase4.add(squirgleFooterPhase4);
        squirgleHeaderAndFooterCorrectPhase5.add(squirgleHeaderCorrectPhase5);
        squirgleHeaderAndFooterCorrectPhase5.add(squirgleFooterPhase5);
        squirgleHeaderAndFooterCorrectPhase6.add(squirgleHeaderCorrectPhase6);
        squirgleHeaderAndFooterCorrectPhase6.add(squirgleFooterPhase6);
        squirgleHeaderAndFooterCorrectPhase7.add(squirgleHeaderCorrectPhase7);
        squirgleHeaderAndFooterCorrectPhase7.add(squirgleFooterPhase7);
        squirgleHeaderAndFooterCorrectPhase8.add(squirgleHeaderCorrectPhase8);
        squirgleHeaderAndFooterCorrectPhase8.add(squirgleFooterPhase8);
        squirgleHeaderAndFooterCorrectPhase9.add(squirgleHeaderCorrectPhase9);
        squirgleHeaderAndFooterCorrectPhase9.add(squirgleFooterPhase9);
        squirgleHeaderAndFooterCorrectPhase10.add(squirgleHeaderCorrectPhase10);
        squirgleHeaderAndFooterCorrectPhase10.add(squirgleFooterPhase10);
        squirgleHeaderAndFooterCorrectPhase11.add(squirgleHeaderCorrectPhase11);
        squirgleHeaderAndFooterCorrectPhase11.add(squirgleFooterPhase11);
        squirgleHeaderAndFooterCorrectPhase12.add(squirgleHeaderCorrectPhase12);
        squirgleHeaderAndFooterCorrectPhase12.add(squirgleFooterPhase12);
        squirgleHeaderAndFooterCorrectPhase13.add(squirgleHeaderCorrectPhase13);
        squirgleHeaderAndFooterCorrectPhase13.add(squirgleFooterPhase13);
        squirgleHeaderAndFooterCorrectPhase14.add(squirgleHeaderCorrectPhase14);
        squirgleHeaderAndFooterCorrectPhase14.add(squirgleFooterPhase14);
        squirgleHeaderAndFooterCorrectPhase15.add(squirgleHeaderCorrectPhase15);
        squirgleHeaderAndFooterCorrectPhase15.add(squirgleFooterPhase15);
        squirgleHeaderAndFooterCorrectPhase16.add(squirgleHeaderCorrectPhase16);
        squirgleHeaderAndFooterCorrectPhase16.add(squirgleFooterPhase16);
        squirgleHeaderAndFooterCorrectPhase17.add(squirgleHeaderCorrectPhase17);
        squirgleHeaderAndFooterCorrectPhase17.add(squirgleFooterPhase17);
        squirgleHeaderAndFooterCorrectPhase18.add(squirgleHeaderCorrectPhase18);
        squirgleHeaderAndFooterCorrectPhase18.add(squirgleFooterPhase18);
        squirgleHeaderAndFooterCorrectPhase19.add(squirgleHeaderCorrectPhase19);
        squirgleHeaderAndFooterCorrectPhase19.add(squirgleFooterPhase19);
        squirgleHeaderAndFooterCorrectPhase20.add(squirgleHeaderCorrectPhase20);
        squirgleHeaderAndFooterCorrectPhase20.add(squirgleFooterPhase20);
        squirgleHeaderAndFooterCorrectPhase21.add(squirgleHeaderCorrectPhase21);
        squirgleHeaderAndFooterCorrectPhase21.add(squirgleFooterPhase21);
        squirgleHeaderAndFooterCorrectPhase22.add(squirgleHeaderCorrectPhase22);
        squirgleHeaderAndFooterCorrectPhase22.add(squirgleFooterPhase22);
        squirgleHeaderAndFooterCorrectPhase23.add(squirgleHeaderCorrectPhase23);
        squirgleHeaderAndFooterCorrectPhase23.add(squirgleFooterPhase23);
        squirgleHeaderAndFooterCorrectPhase24.add(squirgleHeaderCorrectPhase24);
        squirgleHeaderAndFooterCorrectPhase24.add(squirgleFooterPhase24);
        squirgleHeaderAndFooterCorrectPhase25.add(squirgleHeaderCorrectPhase25);
        squirgleHeaderAndFooterCorrectPhase25.add(squirgleFooterPhase25);
        squirgleHeaderAndFooterCorrectPhase26.add(squirgleHeaderCorrectPhase26);
        squirgleHeaderAndFooterCorrectPhase26.add(squirgleFooterPhase26);
        squirgleHeaderAndFooterCorrectPhase27.add(squirgleHeaderCorrectPhase27);
        squirgleHeaderAndFooterCorrectPhase27.add(squirgleFooterPhase27);
        squirgleHeaderAndFooterCorrectPhase28.add(squirgleHeaderCorrectPhase28);
        squirgleHeaderAndFooterCorrectPhase28.add(squirgleFooterPhase28);
        squirgleHeaderAndFooterCorrectPhase29.add(squirgleHeaderCorrectPhase29);
        squirgleHeaderAndFooterCorrectPhase29.add(squirgleFooterPhase29);
        squirgleHeaderAndFooterCorrectPhase30.add(squirgleHeaderCorrectPhase30);
        squirgleHeaderAndFooterCorrectPhase30.add(squirgleFooterPhase30);
        squirgleHeaderAndFooterCorrectPhase31.add(squirgleHeaderCorrectPhase31);
        squirgleHeaderAndFooterCorrectPhase31.add(squirgleFooterPhase31);
        squirgleHeaderAndFooterCorrectPhase32.add(squirgleHeaderCorrectPhase32);
        squirgleHeaderAndFooterCorrectPhase32.add(squirgleFooterPhase32);
        squirgleHeaderAndFooterCorrectPhase33.add(squirgleHeaderCorrectPhase33);
        squirgleHeaderAndFooterCorrectPhase33.add(squirgleFooterPhase33);
        squirgleHeaderAndFooterCorrectPhase34.add(squirgleHeaderCorrectPhase34);
        squirgleHeaderAndFooterCorrectPhase34.add(squirgleFooterPhase34);
        squirgleHeaderAndFooterCorrectPhase35.add(squirgleHeaderCorrectPhase35);
        squirgleHeaderAndFooterCorrectPhase35.add(squirgleFooterPhase35);
        squirgleHeaderAndFooterCorrectPhase36.add(squirgleHeaderCorrectPhase36);
        squirgleHeaderAndFooterCorrectPhase36.add(squirgleFooterPhase36);
        squirgleHeaderAndFooterCorrectPhase37.add(squirgleHeaderCorrectPhase37);
        squirgleHeaderAndFooterCorrectPhase37.add(squirgleFooterPhase37);
        squirgleHeaderAndFooterCorrectPhase38.add(squirgleHeaderCorrectPhase38);
        squirgleHeaderAndFooterCorrectPhase38.add(squirgleFooterPhase38);
        squirgleHeaderAndFooterCorrectPhase39.add(squirgleHeaderCorrectPhase39);
        squirgleHeaderAndFooterCorrectPhase39.add(squirgleFooterPhase39);
        squirgleHeaderAndFooterCorrectPhase40.add(squirgleHeaderCorrectPhase40);
        squirgleHeaderAndFooterCorrectPhase40.add(squirgleFooterPhase40);
        squirgleHeaderAndFooterCorrectPhase41.add(squirgleHeaderCorrectPhase41);
        squirgleHeaderAndFooterCorrectPhase41.add(squirgleFooterPhase41);
        squirgleHeaderAndFooterCorrectPhase42.add(squirgleHeaderCorrectPhase42);
        squirgleHeaderAndFooterCorrectPhase42.add(squirgleFooterPhase42);
        squirgleHeaderAndFooterCorrectPhase43.add(squirgleHeaderCorrectPhase43);
        squirgleHeaderAndFooterCorrectPhase43.add(squirgleFooterPhase43);
        squirgleHeaderAndFooterCorrectPhase44.add(squirgleHeaderCorrectPhase44);
        squirgleHeaderAndFooterCorrectPhase44.add(squirgleFooterPhase44);
        squirgleHeaderAndFooterCorrectPhase45.add(squirgleHeaderCorrectPhase45);
        squirgleHeaderAndFooterCorrectPhase45.add(squirgleFooterPhase45);

        squirgleHeadersAndFootersCorrect = new ArrayList<List<String>>();

        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase1);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase2);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase3);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase4);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase5);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase6);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase7);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase8);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase9);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase10);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase11);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase12);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase13);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase14);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase15);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase16);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase17);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase18);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase19);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase20);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase21);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase22);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase23);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase24);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase25);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase26);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase27);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase28);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase29);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase30);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase31);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase32);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase33);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase34);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase35);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase36);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase37);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase38);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase39);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase40);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase41);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase42);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase43);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase44);
        squirgleHeadersAndFootersCorrect.add(squirgleHeaderAndFooterCorrectPhase45);

        squirgleHeaderAndFooterIncorrectPhase1 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase2 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase3 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase4 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase5 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase6 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase7 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase8 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase9 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase10 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase11 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase12 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase13 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase14 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase15 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase16 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase17 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase18 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase19 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase20 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase21 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase22 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase23 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase24 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase25 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase26 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase27 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase28 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase29 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase30 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase31 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase32 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase33 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase34 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase35 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase36 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase37 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase38 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase39 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase40 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase41 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase42 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase43 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase44 = new ArrayList<String>();
        squirgleHeaderAndFooterIncorrectPhase45 = new ArrayList<String>();

        squirgleHeaderAndFooterIncorrectPhase1.add(squirgleHeaderIncorrectPhase1);
        squirgleHeaderAndFooterIncorrectPhase1.add(squirgleFooterPhase1);
        squirgleHeaderAndFooterIncorrectPhase2.add(squirgleHeaderIncorrectPhase2);
        squirgleHeaderAndFooterIncorrectPhase2.add(squirgleFooterPhase2);
        squirgleHeaderAndFooterIncorrectPhase3.add(squirgleHeaderIncorrectPhase3);
        squirgleHeaderAndFooterIncorrectPhase3.add(squirgleFooterPhase3);
        squirgleHeaderAndFooterIncorrectPhase4.add(squirgleHeaderIncorrectPhase4);
        squirgleHeaderAndFooterIncorrectPhase4.add(squirgleFooterPhase4);
        squirgleHeaderAndFooterIncorrectPhase5.add(squirgleHeaderIncorrectPhase5);
        squirgleHeaderAndFooterIncorrectPhase5.add(squirgleFooterPhase5);
        squirgleHeaderAndFooterIncorrectPhase6.add(squirgleHeaderIncorrectPhase6);
        squirgleHeaderAndFooterIncorrectPhase6.add(squirgleFooterPhase6);
        squirgleHeaderAndFooterIncorrectPhase7.add(squirgleHeaderIncorrectPhase7);
        squirgleHeaderAndFooterIncorrectPhase7.add(squirgleFooterPhase7);
        squirgleHeaderAndFooterIncorrectPhase8.add(squirgleHeaderIncorrectPhase8);
        squirgleHeaderAndFooterIncorrectPhase8.add(squirgleFooterPhase8);
        squirgleHeaderAndFooterIncorrectPhase9.add(squirgleHeaderIncorrectPhase9);
        squirgleHeaderAndFooterIncorrectPhase9.add(squirgleFooterPhase9);
        squirgleHeaderAndFooterIncorrectPhase10.add(squirgleHeaderIncorrectPhase10);
        squirgleHeaderAndFooterIncorrectPhase10.add(squirgleFooterPhase10);
        squirgleHeaderAndFooterIncorrectPhase11.add(squirgleHeaderIncorrectPhase11);
        squirgleHeaderAndFooterIncorrectPhase11.add(squirgleFooterPhase11);
        squirgleHeaderAndFooterIncorrectPhase12.add(squirgleHeaderIncorrectPhase12);
        squirgleHeaderAndFooterIncorrectPhase12.add(squirgleFooterPhase12);
        squirgleHeaderAndFooterIncorrectPhase13.add(squirgleHeaderIncorrectPhase13);
        squirgleHeaderAndFooterIncorrectPhase13.add(squirgleFooterPhase13);
        squirgleHeaderAndFooterIncorrectPhase14.add(squirgleHeaderIncorrectPhase14);
        squirgleHeaderAndFooterIncorrectPhase14.add(squirgleFooterPhase14);
        squirgleHeaderAndFooterIncorrectPhase15.add(squirgleHeaderIncorrectPhase15);
        squirgleHeaderAndFooterIncorrectPhase15.add(squirgleFooterPhase15);
        squirgleHeaderAndFooterIncorrectPhase16.add(squirgleHeaderIncorrectPhase16);
        squirgleHeaderAndFooterIncorrectPhase16.add(squirgleFooterPhase16);
        squirgleHeaderAndFooterIncorrectPhase17.add(squirgleHeaderIncorrectPhase17);
        squirgleHeaderAndFooterIncorrectPhase17.add(squirgleFooterPhase17);
        squirgleHeaderAndFooterIncorrectPhase18.add(squirgleHeaderIncorrectPhase18);
        squirgleHeaderAndFooterIncorrectPhase18.add(squirgleFooterPhase18);
        squirgleHeaderAndFooterIncorrectPhase19.add(squirgleHeaderIncorrectPhase19);
        squirgleHeaderAndFooterIncorrectPhase19.add(squirgleFooterPhase19);
        squirgleHeaderAndFooterIncorrectPhase20.add(squirgleHeaderIncorrectPhase20);
        squirgleHeaderAndFooterIncorrectPhase20.add(squirgleFooterPhase20);
        squirgleHeaderAndFooterIncorrectPhase21.add(squirgleHeaderIncorrectPhase21);
        squirgleHeaderAndFooterIncorrectPhase21.add(squirgleFooterPhase21);
        squirgleHeaderAndFooterIncorrectPhase22.add(squirgleHeaderIncorrectPhase22);
        squirgleHeaderAndFooterIncorrectPhase22.add(squirgleFooterPhase22);
        squirgleHeaderAndFooterIncorrectPhase23.add(squirgleHeaderIncorrectPhase23);
        squirgleHeaderAndFooterIncorrectPhase23.add(squirgleFooterPhase23);
        squirgleHeaderAndFooterIncorrectPhase24.add(squirgleHeaderIncorrectPhase24);
        squirgleHeaderAndFooterIncorrectPhase24.add(squirgleFooterPhase24);
        squirgleHeaderAndFooterIncorrectPhase25.add(squirgleHeaderIncorrectPhase25);
        squirgleHeaderAndFooterIncorrectPhase25.add(squirgleFooterPhase25);
        squirgleHeaderAndFooterIncorrectPhase26.add(squirgleHeaderIncorrectPhase26);
        squirgleHeaderAndFooterIncorrectPhase26.add(squirgleFooterPhase26);
        squirgleHeaderAndFooterIncorrectPhase27.add(squirgleHeaderIncorrectPhase27);
        squirgleHeaderAndFooterIncorrectPhase27.add(squirgleFooterPhase27);
        squirgleHeaderAndFooterIncorrectPhase28.add(squirgleHeaderIncorrectPhase28);
        squirgleHeaderAndFooterIncorrectPhase28.add(squirgleFooterPhase28);
        squirgleHeaderAndFooterIncorrectPhase29.add(squirgleHeaderIncorrectPhase29);
        squirgleHeaderAndFooterIncorrectPhase29.add(squirgleFooterPhase29);
        squirgleHeaderAndFooterIncorrectPhase30.add(squirgleHeaderIncorrectPhase30);
        squirgleHeaderAndFooterIncorrectPhase30.add(squirgleFooterPhase30);
        squirgleHeaderAndFooterIncorrectPhase31.add(squirgleHeaderIncorrectPhase31);
        squirgleHeaderAndFooterIncorrectPhase31.add(squirgleFooterPhase31);
        squirgleHeaderAndFooterIncorrectPhase32.add(squirgleHeaderIncorrectPhase32);
        squirgleHeaderAndFooterIncorrectPhase32.add(squirgleFooterPhase32);
        squirgleHeaderAndFooterIncorrectPhase33.add(squirgleHeaderIncorrectPhase33);
        squirgleHeaderAndFooterIncorrectPhase33.add(squirgleFooterPhase33);
        squirgleHeaderAndFooterIncorrectPhase34.add(squirgleHeaderIncorrectPhase34);
        squirgleHeaderAndFooterIncorrectPhase34.add(squirgleFooterPhase34);
        squirgleHeaderAndFooterIncorrectPhase35.add(squirgleHeaderIncorrectPhase35);
        squirgleHeaderAndFooterIncorrectPhase35.add(squirgleFooterPhase35);
        squirgleHeaderAndFooterIncorrectPhase36.add(squirgleHeaderIncorrectPhase36);
        squirgleHeaderAndFooterIncorrectPhase36.add(squirgleFooterPhase36);
        squirgleHeaderAndFooterIncorrectPhase37.add(squirgleHeaderIncorrectPhase37);
        squirgleHeaderAndFooterIncorrectPhase37.add(squirgleFooterPhase37);
        squirgleHeaderAndFooterIncorrectPhase38.add(squirgleHeaderIncorrectPhase38);
        squirgleHeaderAndFooterIncorrectPhase38.add(squirgleFooterPhase38);
        squirgleHeaderAndFooterIncorrectPhase39.add(squirgleHeaderIncorrectPhase39);
        squirgleHeaderAndFooterIncorrectPhase39.add(squirgleFooterPhase39);
        squirgleHeaderAndFooterIncorrectPhase40.add(squirgleHeaderIncorrectPhase40);
        squirgleHeaderAndFooterIncorrectPhase40.add(squirgleFooterPhase40);
        squirgleHeaderAndFooterIncorrectPhase41.add(squirgleHeaderIncorrectPhase41);
        squirgleHeaderAndFooterIncorrectPhase41.add(squirgleFooterPhase41);
        squirgleHeaderAndFooterIncorrectPhase42.add(squirgleHeaderIncorrectPhase42);
        squirgleHeaderAndFooterIncorrectPhase42.add(squirgleFooterPhase42);
        squirgleHeaderAndFooterIncorrectPhase43.add(squirgleHeaderIncorrectPhase43);
        squirgleHeaderAndFooterIncorrectPhase43.add(squirgleFooterPhase43);
        squirgleHeaderAndFooterIncorrectPhase44.add(squirgleHeaderIncorrectPhase44);
        squirgleHeaderAndFooterIncorrectPhase44.add(squirgleFooterPhase44);
        squirgleHeaderAndFooterIncorrectPhase45.add(squirgleHeaderIncorrectPhase45);
        squirgleHeaderAndFooterIncorrectPhase45.add(squirgleFooterPhase45);

        squirgleHeadersAndFootersIncorrect = new ArrayList<List<String>>();

        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase1);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase2);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase3);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase4);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase5);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase6);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase7);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase8);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase9);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase10);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase11);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase12);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase13);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase14);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase15);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase16);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase17);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase18);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase19);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase20);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase21);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase22);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase23);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase24);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase25);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase26);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase27);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase28);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase29);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase30);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase31);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase32);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase33);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase34);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase35);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase36);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase37);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase38);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase39);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase40);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase41);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase42);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase43);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase44);
        squirgleHeadersAndFootersIncorrect.add(squirgleHeaderAndFooterIncorrectPhase45);

        battleHeaderAndFooterCorrectPhase1 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase2 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase3 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase4 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase5 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase6 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase7 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase8 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase9 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase10 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase11 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase12 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase13 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase14 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase15 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase16 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase17 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase18 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase19 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase20 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase21 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase22 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase23 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase24 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase25 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase26 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase27 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase28 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase29 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase30 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase31 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase32 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase33 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase34 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase35 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase36 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase37 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase38 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase39 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase40 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase41 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase42 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase43 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase44 = new ArrayList<String>();
        battleHeaderAndFooterCorrectPhase45 = new ArrayList<String>();

        battleHeaderAndFooterCorrectPhase1.add(battleHeaderCorrectPhase1);
        battleHeaderAndFooterCorrectPhase1.add(battleFooterPhase1);
        battleHeaderAndFooterCorrectPhase2.add(battleHeaderCorrectPhase2);
        battleHeaderAndFooterCorrectPhase2.add(battleFooterPhase2);
        battleHeaderAndFooterCorrectPhase3.add(battleHeaderCorrectPhase3);
        battleHeaderAndFooterCorrectPhase3.add(battleFooterPhase3);
        battleHeaderAndFooterCorrectPhase4.add(battleHeaderCorrectPhase4);
        battleHeaderAndFooterCorrectPhase4.add(battleFooterPhase4);
        battleHeaderAndFooterCorrectPhase5.add(battleHeaderCorrectPhase5);
        battleHeaderAndFooterCorrectPhase5.add(battleFooterPhase5);
        battleHeaderAndFooterCorrectPhase6.add(battleHeaderCorrectPhase6);
        battleHeaderAndFooterCorrectPhase6.add(battleFooterPhase6);
        battleHeaderAndFooterCorrectPhase7.add(battleHeaderCorrectPhase7);
        battleHeaderAndFooterCorrectPhase7.add(battleFooterPhase7);
        battleHeaderAndFooterCorrectPhase8.add(battleHeaderCorrectPhase8);
        battleHeaderAndFooterCorrectPhase8.add(battleFooterPhase8);
        battleHeaderAndFooterCorrectPhase9.add(battleHeaderCorrectPhase9);
        battleHeaderAndFooterCorrectPhase9.add(battleFooterPhase9);
        battleHeaderAndFooterCorrectPhase10.add(battleHeaderCorrectPhase10);
        battleHeaderAndFooterCorrectPhase10.add(battleFooterPhase10);
        battleHeaderAndFooterCorrectPhase11.add(battleHeaderCorrectPhase11);
        battleHeaderAndFooterCorrectPhase11.add(battleFooterPhase11);
        battleHeaderAndFooterCorrectPhase12.add(battleHeaderCorrectPhase12);
        battleHeaderAndFooterCorrectPhase12.add(battleFooterPhase12);
        battleHeaderAndFooterCorrectPhase13.add(battleHeaderCorrectPhase13);
        battleHeaderAndFooterCorrectPhase13.add(battleFooterPhase13);
        battleHeaderAndFooterCorrectPhase14.add(battleHeaderCorrectPhase14);
        battleHeaderAndFooterCorrectPhase14.add(battleFooterPhase14);
        battleHeaderAndFooterCorrectPhase15.add(battleHeaderCorrectPhase15);
        battleHeaderAndFooterCorrectPhase15.add(battleFooterPhase15);
        battleHeaderAndFooterCorrectPhase16.add(battleHeaderCorrectPhase16);
        battleHeaderAndFooterCorrectPhase16.add(battleFooterPhase16);
        battleHeaderAndFooterCorrectPhase17.add(battleHeaderCorrectPhase17);
        battleHeaderAndFooterCorrectPhase17.add(battleFooterPhase17);
        battleHeaderAndFooterCorrectPhase18.add(battleHeaderCorrectPhase18);
        battleHeaderAndFooterCorrectPhase18.add(battleFooterPhase18);
        battleHeaderAndFooterCorrectPhase19.add(battleHeaderCorrectPhase19);
        battleHeaderAndFooterCorrectPhase19.add(battleFooterPhase19);
        battleHeaderAndFooterCorrectPhase20.add(battleHeaderCorrectPhase20);
        battleHeaderAndFooterCorrectPhase20.add(battleFooterPhase20);
        battleHeaderAndFooterCorrectPhase21.add(battleHeaderCorrectPhase21);
        battleHeaderAndFooterCorrectPhase21.add(battleFooterPhase21);
        battleHeaderAndFooterCorrectPhase22.add(battleHeaderCorrectPhase22);
        battleHeaderAndFooterCorrectPhase22.add(battleFooterPhase22);
        battleHeaderAndFooterCorrectPhase23.add(battleHeaderCorrectPhase23);
        battleHeaderAndFooterCorrectPhase23.add(battleFooterPhase23);
        battleHeaderAndFooterCorrectPhase24.add(battleHeaderCorrectPhase24);
        battleHeaderAndFooterCorrectPhase24.add(battleFooterPhase24);
        battleHeaderAndFooterCorrectPhase25.add(battleHeaderCorrectPhase25);
        battleHeaderAndFooterCorrectPhase25.add(battleFooterPhase25);
        battleHeaderAndFooterCorrectPhase26.add(battleHeaderCorrectPhase26);
        battleHeaderAndFooterCorrectPhase26.add(battleFooterPhase26);
        battleHeaderAndFooterCorrectPhase27.add(battleHeaderCorrectPhase27);
        battleHeaderAndFooterCorrectPhase27.add(battleFooterPhase27);
        battleHeaderAndFooterCorrectPhase28.add(battleHeaderCorrectPhase28);
        battleHeaderAndFooterCorrectPhase28.add(battleFooterPhase28);
        battleHeaderAndFooterCorrectPhase29.add(battleHeaderCorrectPhase29);
        battleHeaderAndFooterCorrectPhase29.add(battleFooterPhase29);
        battleHeaderAndFooterCorrectPhase30.add(battleHeaderCorrectPhase30);
        battleHeaderAndFooterCorrectPhase30.add(battleFooterPhase30);
        battleHeaderAndFooterCorrectPhase31.add(battleHeaderCorrectPhase31);
        battleHeaderAndFooterCorrectPhase31.add(battleFooterPhase31);
        battleHeaderAndFooterCorrectPhase32.add(battleHeaderCorrectPhase32);
        battleHeaderAndFooterCorrectPhase32.add(battleFooterPhase32);
        battleHeaderAndFooterCorrectPhase33.add(battleHeaderCorrectPhase33);
        battleHeaderAndFooterCorrectPhase33.add(battleFooterPhase33);
        battleHeaderAndFooterCorrectPhase34.add(battleHeaderCorrectPhase34);
        battleHeaderAndFooterCorrectPhase34.add(battleFooterPhase34);
        battleHeaderAndFooterCorrectPhase35.add(battleHeaderCorrectPhase35);
        battleHeaderAndFooterCorrectPhase35.add(battleFooterPhase35);
        battleHeaderAndFooterCorrectPhase36.add(battleHeaderCorrectPhase36);
        battleHeaderAndFooterCorrectPhase36.add(battleFooterPhase36);
        battleHeaderAndFooterCorrectPhase37.add(battleHeaderCorrectPhase37);
        battleHeaderAndFooterCorrectPhase37.add(battleFooterPhase37);
        battleHeaderAndFooterCorrectPhase38.add(battleHeaderCorrectPhase38);
        battleHeaderAndFooterCorrectPhase38.add(battleFooterPhase38);
        battleHeaderAndFooterCorrectPhase39.add(battleHeaderCorrectPhase39);
        battleHeaderAndFooterCorrectPhase39.add(battleFooterPhase39);
        battleHeaderAndFooterCorrectPhase40.add(battleHeaderCorrectPhase40);
        battleHeaderAndFooterCorrectPhase40.add(battleFooterPhase40);
        battleHeaderAndFooterCorrectPhase41.add(battleHeaderCorrectPhase41);
        battleHeaderAndFooterCorrectPhase41.add(battleFooterPhase41);
        battleHeaderAndFooterCorrectPhase42.add(battleHeaderCorrectPhase42);
        battleHeaderAndFooterCorrectPhase42.add(battleFooterPhase42);
        battleHeaderAndFooterCorrectPhase43.add(battleHeaderCorrectPhase43);
        battleHeaderAndFooterCorrectPhase43.add(battleFooterPhase43);
        battleHeaderAndFooterCorrectPhase44.add(battleHeaderCorrectPhase44);
        battleHeaderAndFooterCorrectPhase44.add(battleFooterPhase44);
        battleHeaderAndFooterCorrectPhase45.add(battleHeaderCorrectPhase45);
        battleHeaderAndFooterCorrectPhase45.add(battleFooterPhase45);

        battleHeadersAndFootersCorrect = new ArrayList<List<String>>();

        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase1);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase2);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase3);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase4);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase5);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase6);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase7);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase8);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase9);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase10);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase11);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase12);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase13);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase14);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase15);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase16);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase17);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase18);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase19);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase20);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase21);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase22);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase23);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase24);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase25);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase26);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase27);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase28);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase29);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase30);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase31);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase32);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase33);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase34);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase35);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase36);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase37);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase38);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase39);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase40);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase41);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase42);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase43);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase44);
        battleHeadersAndFootersCorrect.add(battleHeaderAndFooterCorrectPhase45);

        battleHeaderAndFooterIncorrectPhase1 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase2 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase3 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase4 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase5 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase6 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase7 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase8 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase9 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase10 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase11 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase12 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase13 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase14 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase15 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase16 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase17 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase18 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase19 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase20 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase21 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase22 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase23 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase24 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase25 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase26 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase27 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase28 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase29 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase30 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase31 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase32 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase33 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase34 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase35 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase36 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase37 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase38 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase39 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase40 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase41 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase42 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase43 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase44 = new ArrayList<String>();
        battleHeaderAndFooterIncorrectPhase45 = new ArrayList<String>();

        battleHeaderAndFooterIncorrectPhase1.add(battleHeaderIncorrectPhase1);
        battleHeaderAndFooterIncorrectPhase1.add(battleFooterPhase1);
        battleHeaderAndFooterIncorrectPhase2.add(battleHeaderIncorrectPhase2);
        battleHeaderAndFooterIncorrectPhase2.add(battleFooterPhase2);
        battleHeaderAndFooterIncorrectPhase3.add(battleHeaderIncorrectPhase3);
        battleHeaderAndFooterIncorrectPhase3.add(battleFooterPhase3);
        battleHeaderAndFooterIncorrectPhase4.add(battleHeaderIncorrectPhase4);
        battleHeaderAndFooterIncorrectPhase4.add(battleFooterPhase4);
        battleHeaderAndFooterIncorrectPhase5.add(battleHeaderIncorrectPhase5);
        battleHeaderAndFooterIncorrectPhase5.add(battleFooterPhase5);
        battleHeaderAndFooterIncorrectPhase6.add(battleHeaderIncorrectPhase6);
        battleHeaderAndFooterIncorrectPhase6.add(battleFooterPhase6);
        battleHeaderAndFooterIncorrectPhase7.add(battleHeaderIncorrectPhase7);
        battleHeaderAndFooterIncorrectPhase7.add(battleFooterPhase7);
        battleHeaderAndFooterIncorrectPhase8.add(battleHeaderIncorrectPhase8);
        battleHeaderAndFooterIncorrectPhase8.add(battleFooterPhase8);
        battleHeaderAndFooterIncorrectPhase9.add(battleHeaderIncorrectPhase9);
        battleHeaderAndFooterIncorrectPhase9.add(battleFooterPhase9);
        battleHeaderAndFooterIncorrectPhase10.add(battleHeaderIncorrectPhase10);
        battleHeaderAndFooterIncorrectPhase10.add(battleFooterPhase10);
        battleHeaderAndFooterIncorrectPhase11.add(battleHeaderIncorrectPhase11);
        battleHeaderAndFooterIncorrectPhase11.add(battleFooterPhase11);
        battleHeaderAndFooterIncorrectPhase12.add(battleHeaderIncorrectPhase12);
        battleHeaderAndFooterIncorrectPhase12.add(battleFooterPhase12);
        battleHeaderAndFooterIncorrectPhase13.add(battleHeaderIncorrectPhase13);
        battleHeaderAndFooterIncorrectPhase13.add(battleFooterPhase13);
        battleHeaderAndFooterIncorrectPhase14.add(battleHeaderIncorrectPhase14);
        battleHeaderAndFooterIncorrectPhase14.add(battleFooterPhase14);
        battleHeaderAndFooterIncorrectPhase15.add(battleHeaderIncorrectPhase15);
        battleHeaderAndFooterIncorrectPhase15.add(battleFooterPhase15);
        battleHeaderAndFooterIncorrectPhase16.add(battleHeaderIncorrectPhase16);
        battleHeaderAndFooterIncorrectPhase16.add(battleFooterPhase16);
        battleHeaderAndFooterIncorrectPhase17.add(battleHeaderIncorrectPhase17);
        battleHeaderAndFooterIncorrectPhase17.add(battleFooterPhase17);
        battleHeaderAndFooterIncorrectPhase18.add(battleHeaderIncorrectPhase18);
        battleHeaderAndFooterIncorrectPhase18.add(battleFooterPhase18);
        battleHeaderAndFooterIncorrectPhase19.add(battleHeaderIncorrectPhase19);
        battleHeaderAndFooterIncorrectPhase19.add(battleFooterPhase19);
        battleHeaderAndFooterIncorrectPhase20.add(battleHeaderIncorrectPhase20);
        battleHeaderAndFooterIncorrectPhase20.add(battleFooterPhase20);
        battleHeaderAndFooterIncorrectPhase21.add(battleHeaderIncorrectPhase21);
        battleHeaderAndFooterIncorrectPhase21.add(battleFooterPhase21);
        battleHeaderAndFooterIncorrectPhase22.add(battleHeaderIncorrectPhase22);
        battleHeaderAndFooterIncorrectPhase22.add(battleFooterPhase22);
        battleHeaderAndFooterIncorrectPhase23.add(battleHeaderIncorrectPhase23);
        battleHeaderAndFooterIncorrectPhase23.add(battleFooterPhase23);
        battleHeaderAndFooterIncorrectPhase24.add(battleHeaderIncorrectPhase24);
        battleHeaderAndFooterIncorrectPhase24.add(battleFooterPhase24);
        battleHeaderAndFooterIncorrectPhase25.add(battleHeaderIncorrectPhase25);
        battleHeaderAndFooterIncorrectPhase25.add(battleFooterPhase25);
        battleHeaderAndFooterIncorrectPhase26.add(battleHeaderIncorrectPhase26);
        battleHeaderAndFooterIncorrectPhase26.add(battleFooterPhase26);
        battleHeaderAndFooterIncorrectPhase27.add(battleHeaderIncorrectPhase27);
        battleHeaderAndFooterIncorrectPhase27.add(battleFooterPhase27);
        battleHeaderAndFooterIncorrectPhase28.add(battleHeaderIncorrectPhase28);
        battleHeaderAndFooterIncorrectPhase28.add(battleFooterPhase28);
        battleHeaderAndFooterIncorrectPhase29.add(battleHeaderIncorrectPhase29);
        battleHeaderAndFooterIncorrectPhase29.add(battleFooterPhase29);
        battleHeaderAndFooterIncorrectPhase30.add(battleHeaderIncorrectPhase30);
        battleHeaderAndFooterIncorrectPhase30.add(battleFooterPhase30);
        battleHeaderAndFooterIncorrectPhase31.add(battleHeaderIncorrectPhase31);
        battleHeaderAndFooterIncorrectPhase31.add(battleFooterPhase31);
        battleHeaderAndFooterIncorrectPhase32.add(battleHeaderIncorrectPhase32);
        battleHeaderAndFooterIncorrectPhase32.add(battleFooterPhase32);
        battleHeaderAndFooterIncorrectPhase33.add(battleHeaderIncorrectPhase33);
        battleHeaderAndFooterIncorrectPhase33.add(battleFooterPhase33);
        battleHeaderAndFooterIncorrectPhase34.add(battleHeaderIncorrectPhase34);
        battleHeaderAndFooterIncorrectPhase34.add(battleFooterPhase34);
        battleHeaderAndFooterIncorrectPhase35.add(battleHeaderIncorrectPhase35);
        battleHeaderAndFooterIncorrectPhase35.add(battleFooterPhase35);
        battleHeaderAndFooterIncorrectPhase36.add(battleHeaderIncorrectPhase36);
        battleHeaderAndFooterIncorrectPhase36.add(battleFooterPhase36);
        battleHeaderAndFooterIncorrectPhase37.add(battleHeaderIncorrectPhase37);
        battleHeaderAndFooterIncorrectPhase37.add(battleFooterPhase37);
        battleHeaderAndFooterIncorrectPhase38.add(battleHeaderIncorrectPhase38);
        battleHeaderAndFooterIncorrectPhase38.add(battleFooterPhase38);
        battleHeaderAndFooterIncorrectPhase39.add(battleHeaderIncorrectPhase39);
        battleHeaderAndFooterIncorrectPhase39.add(battleFooterPhase39);
        battleHeaderAndFooterIncorrectPhase40.add(battleHeaderIncorrectPhase40);
        battleHeaderAndFooterIncorrectPhase40.add(battleFooterPhase40);
        battleHeaderAndFooterIncorrectPhase41.add(battleHeaderIncorrectPhase41);
        battleHeaderAndFooterIncorrectPhase41.add(battleFooterPhase41);
        battleHeaderAndFooterIncorrectPhase42.add(battleHeaderIncorrectPhase42);
        battleHeaderAndFooterIncorrectPhase42.add(battleFooterPhase42);
        battleHeaderAndFooterIncorrectPhase43.add(battleHeaderIncorrectPhase43);
        battleHeaderAndFooterIncorrectPhase43.add(battleFooterPhase43);
        battleHeaderAndFooterIncorrectPhase44.add(battleHeaderIncorrectPhase44);
        battleHeaderAndFooterIncorrectPhase44.add(battleFooterPhase44);
        battleHeaderAndFooterIncorrectPhase45.add(battleHeaderIncorrectPhase45);
        battleHeaderAndFooterIncorrectPhase45.add(battleFooterPhase45);

        battleHeadersAndFootersIncorrect = new ArrayList<List<String>>();

        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase1);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase2);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase3);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase4);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase5);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase6);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase7);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase8);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase9);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase10);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase11);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase12);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase13);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase14);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase15);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase16);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase17);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase18);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase19);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase20);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase21);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase22);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase23);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase24);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase25);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase26);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase27);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase28);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase29);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase30);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase31);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase32);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase33);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase34);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase35);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase36);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase37);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase38);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase39);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase40);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase41);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase42);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase43);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase44);
        battleHeadersAndFootersIncorrect.add(battleHeaderAndFooterIncorrectPhase45);

        timeAttackHeaderAndFooterCorrectPhase1 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase2 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase3 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase4 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase5 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase6 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase7 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase8 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase9 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase10 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase11 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase12 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase13 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase14 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase15 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase16 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase17 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase18 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase19 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase20 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase21 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase22 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase23 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase24 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase25 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase26 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase27 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase28 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase29 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase30 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase31 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase32 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase33 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase34 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase35 = new ArrayList<String>();
        timeAttackHeaderAndFooterCorrectPhase36 = new ArrayList<String>();

        timeAttackHeaderAndFooterCorrectPhase1.add(timeAttackHeaderCorrectPhase1);
        timeAttackHeaderAndFooterCorrectPhase1.add(timeAttackFooterPhase1);
        timeAttackHeaderAndFooterCorrectPhase2.add(timeAttackHeaderCorrectPhase2);
        timeAttackHeaderAndFooterCorrectPhase2.add(timeAttackFooterPhase2);
        timeAttackHeaderAndFooterCorrectPhase3.add(timeAttackHeaderCorrectPhase3);
        timeAttackHeaderAndFooterCorrectPhase3.add(timeAttackFooterPhase3);
        timeAttackHeaderAndFooterCorrectPhase4.add(timeAttackHeaderCorrectPhase4);
        timeAttackHeaderAndFooterCorrectPhase4.add(timeAttackFooterPhase4);
        timeAttackHeaderAndFooterCorrectPhase5.add(timeAttackHeaderCorrectPhase5);
        timeAttackHeaderAndFooterCorrectPhase5.add(timeAttackFooterPhase5);
        timeAttackHeaderAndFooterCorrectPhase6.add(timeAttackHeaderCorrectPhase6);
        timeAttackHeaderAndFooterCorrectPhase6.add(timeAttackFooterPhase6);
        timeAttackHeaderAndFooterCorrectPhase7.add(timeAttackHeaderCorrectPhase7);
        timeAttackHeaderAndFooterCorrectPhase7.add(timeAttackFooterPhase7);
        timeAttackHeaderAndFooterCorrectPhase8.add(timeAttackHeaderCorrectPhase8);
        timeAttackHeaderAndFooterCorrectPhase8.add(timeAttackFooterPhase8);
        timeAttackHeaderAndFooterCorrectPhase9.add(timeAttackHeaderCorrectPhase9);
        timeAttackHeaderAndFooterCorrectPhase9.add(timeAttackFooterPhase9);
        timeAttackHeaderAndFooterCorrectPhase10.add(timeAttackHeaderCorrectPhase10);
        timeAttackHeaderAndFooterCorrectPhase10.add(timeAttackFooterPhase10);
        timeAttackHeaderAndFooterCorrectPhase11.add(timeAttackHeaderCorrectPhase11);
        timeAttackHeaderAndFooterCorrectPhase11.add(timeAttackFooterPhase11);
        timeAttackHeaderAndFooterCorrectPhase12.add(timeAttackHeaderCorrectPhase12);
        timeAttackHeaderAndFooterCorrectPhase12.add(timeAttackFooterPhase12);
        timeAttackHeaderAndFooterCorrectPhase13.add(timeAttackHeaderCorrectPhase13);
        timeAttackHeaderAndFooterCorrectPhase13.add(timeAttackFooterPhase13);
        timeAttackHeaderAndFooterCorrectPhase14.add(timeAttackHeaderCorrectPhase14);
        timeAttackHeaderAndFooterCorrectPhase14.add(timeAttackFooterPhase14);
        timeAttackHeaderAndFooterCorrectPhase15.add(timeAttackHeaderCorrectPhase15);
        timeAttackHeaderAndFooterCorrectPhase15.add(timeAttackFooterPhase15);
        timeAttackHeaderAndFooterCorrectPhase16.add(timeAttackHeaderCorrectPhase16);
        timeAttackHeaderAndFooterCorrectPhase16.add(timeAttackFooterPhase16);
        timeAttackHeaderAndFooterCorrectPhase17.add(timeAttackHeaderCorrectPhase17);
        timeAttackHeaderAndFooterCorrectPhase17.add(timeAttackFooterPhase17);
        timeAttackHeaderAndFooterCorrectPhase18.add(timeAttackHeaderCorrectPhase18);
        timeAttackHeaderAndFooterCorrectPhase18.add(timeAttackFooterPhase18);
        timeAttackHeaderAndFooterCorrectPhase19.add(timeAttackHeaderCorrectPhase19);
        timeAttackHeaderAndFooterCorrectPhase19.add(timeAttackFooterPhase19);
        timeAttackHeaderAndFooterCorrectPhase20.add(timeAttackHeaderCorrectPhase20);
        timeAttackHeaderAndFooterCorrectPhase20.add(timeAttackFooterPhase20);
        timeAttackHeaderAndFooterCorrectPhase21.add(timeAttackHeaderCorrectPhase21);
        timeAttackHeaderAndFooterCorrectPhase21.add(timeAttackFooterPhase21);
        timeAttackHeaderAndFooterCorrectPhase22.add(timeAttackHeaderCorrectPhase22);
        timeAttackHeaderAndFooterCorrectPhase22.add(timeAttackFooterPhase22);
        timeAttackHeaderAndFooterCorrectPhase23.add(timeAttackHeaderCorrectPhase23);
        timeAttackHeaderAndFooterCorrectPhase23.add(timeAttackFooterPhase23);
        timeAttackHeaderAndFooterCorrectPhase24.add(timeAttackHeaderCorrectPhase24);
        timeAttackHeaderAndFooterCorrectPhase24.add(timeAttackFooterPhase24);
        timeAttackHeaderAndFooterCorrectPhase25.add(timeAttackHeaderCorrectPhase25);
        timeAttackHeaderAndFooterCorrectPhase25.add(timeAttackFooterPhase25);
        timeAttackHeaderAndFooterCorrectPhase26.add(timeAttackHeaderCorrectPhase26);
        timeAttackHeaderAndFooterCorrectPhase26.add(timeAttackFooterPhase26);
        timeAttackHeaderAndFooterCorrectPhase27.add(timeAttackHeaderCorrectPhase27);
        timeAttackHeaderAndFooterCorrectPhase27.add(timeAttackFooterPhase27);
        timeAttackHeaderAndFooterCorrectPhase28.add(timeAttackHeaderCorrectPhase28);
        timeAttackHeaderAndFooterCorrectPhase28.add(timeAttackFooterPhase28);
        timeAttackHeaderAndFooterCorrectPhase29.add(timeAttackHeaderCorrectPhase29);
        timeAttackHeaderAndFooterCorrectPhase29.add(timeAttackFooterPhase29);
        timeAttackHeaderAndFooterCorrectPhase30.add(timeAttackHeaderCorrectPhase30);
        timeAttackHeaderAndFooterCorrectPhase30.add(timeAttackFooterPhase30);
        timeAttackHeaderAndFooterCorrectPhase31.add(timeAttackHeaderCorrectPhase31);
        timeAttackHeaderAndFooterCorrectPhase31.add(timeAttackFooterPhase31);
        timeAttackHeaderAndFooterCorrectPhase32.add(timeAttackHeaderCorrectPhase32);
        timeAttackHeaderAndFooterCorrectPhase32.add(timeAttackFooterPhase32);
        timeAttackHeaderAndFooterCorrectPhase33.add(timeAttackHeaderCorrectPhase33);
        timeAttackHeaderAndFooterCorrectPhase33.add(timeAttackFooterPhase33);
        timeAttackHeaderAndFooterCorrectPhase34.add(timeAttackHeaderCorrectPhase34);
        timeAttackHeaderAndFooterCorrectPhase34.add(timeAttackFooterPhase34);
        timeAttackHeaderAndFooterCorrectPhase35.add(timeAttackHeaderCorrectPhase35);
        timeAttackHeaderAndFooterCorrectPhase35.add(timeAttackFooterPhase35);
        timeAttackHeaderAndFooterCorrectPhase36.add(timeAttackHeaderCorrectPhase36);
        timeAttackHeaderAndFooterCorrectPhase36.add(timeAttackFooterPhase36);

        timeAttackHeadersAndFootersCorrect = new ArrayList<List<String>>();

        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase1);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase2);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase3);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase4);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase5);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase6);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase7);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase8);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase9);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase10);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase11);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase12);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase13);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase14);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase15);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase16);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase17);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase18);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase19);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase20);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase21);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase22);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase23);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase24);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase25);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase26);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase27);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase28);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase29);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase30);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase31);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase32);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase33);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase34);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase35);
        timeAttackHeadersAndFootersCorrect.add(timeAttackHeaderAndFooterCorrectPhase36);

        timeAttackHeaderAndFooterIncorrectPhase1 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase2 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase3 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase4 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase5 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase6 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase7 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase8 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase9 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase10 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase11 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase12 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase13 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase14 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase15 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase16 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase17 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase18 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase19 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase20 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase21 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase22 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase23 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase24 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase25 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase26 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase27 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase28 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase29 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase30 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase31 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase32 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase33 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase34 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase35 = new ArrayList<String>();
        timeAttackHeaderAndFooterIncorrectPhase36 = new ArrayList<String>();

        timeAttackHeaderAndFooterIncorrectPhase1.add(timeAttackHeaderIncorrectPhase1);
        timeAttackHeaderAndFooterIncorrectPhase1.add(timeAttackFooterPhase1);
        timeAttackHeaderAndFooterIncorrectPhase2.add(timeAttackHeaderIncorrectPhase2);
        timeAttackHeaderAndFooterIncorrectPhase2.add(timeAttackFooterPhase2);
        timeAttackHeaderAndFooterIncorrectPhase3.add(timeAttackHeaderIncorrectPhase3);
        timeAttackHeaderAndFooterIncorrectPhase3.add(timeAttackFooterPhase3);
        timeAttackHeaderAndFooterIncorrectPhase4.add(timeAttackHeaderIncorrectPhase4);
        timeAttackHeaderAndFooterIncorrectPhase4.add(timeAttackFooterPhase4);
        timeAttackHeaderAndFooterIncorrectPhase5.add(timeAttackHeaderIncorrectPhase5);
        timeAttackHeaderAndFooterIncorrectPhase5.add(timeAttackFooterPhase5);
        timeAttackHeaderAndFooterIncorrectPhase6.add(timeAttackHeaderIncorrectPhase6);
        timeAttackHeaderAndFooterIncorrectPhase6.add(timeAttackFooterPhase6);
        timeAttackHeaderAndFooterIncorrectPhase7.add(timeAttackHeaderIncorrectPhase7);
        timeAttackHeaderAndFooterIncorrectPhase7.add(timeAttackFooterPhase7);
        timeAttackHeaderAndFooterIncorrectPhase8.add(timeAttackHeaderIncorrectPhase8);
        timeAttackHeaderAndFooterIncorrectPhase8.add(timeAttackFooterPhase8);
        timeAttackHeaderAndFooterIncorrectPhase9.add(timeAttackHeaderIncorrectPhase9);
        timeAttackHeaderAndFooterIncorrectPhase9.add(timeAttackFooterPhase9);
        timeAttackHeaderAndFooterIncorrectPhase10.add(timeAttackHeaderIncorrectPhase10);
        timeAttackHeaderAndFooterIncorrectPhase10.add(timeAttackFooterPhase10);
        timeAttackHeaderAndFooterIncorrectPhase11.add(timeAttackHeaderIncorrectPhase11);
        timeAttackHeaderAndFooterIncorrectPhase11.add(timeAttackFooterPhase11);
        timeAttackHeaderAndFooterIncorrectPhase12.add(timeAttackHeaderIncorrectPhase12);
        timeAttackHeaderAndFooterIncorrectPhase12.add(timeAttackFooterPhase12);
        timeAttackHeaderAndFooterIncorrectPhase13.add(timeAttackHeaderIncorrectPhase13);
        timeAttackHeaderAndFooterIncorrectPhase13.add(timeAttackFooterPhase13);
        timeAttackHeaderAndFooterIncorrectPhase14.add(timeAttackHeaderIncorrectPhase14);
        timeAttackHeaderAndFooterIncorrectPhase14.add(timeAttackFooterPhase14);
        timeAttackHeaderAndFooterIncorrectPhase15.add(timeAttackHeaderIncorrectPhase15);
        timeAttackHeaderAndFooterIncorrectPhase15.add(timeAttackFooterPhase15);
        timeAttackHeaderAndFooterIncorrectPhase16.add(timeAttackHeaderIncorrectPhase16);
        timeAttackHeaderAndFooterIncorrectPhase16.add(timeAttackFooterPhase16);
        timeAttackHeaderAndFooterIncorrectPhase17.add(timeAttackHeaderIncorrectPhase17);
        timeAttackHeaderAndFooterIncorrectPhase17.add(timeAttackFooterPhase17);
        timeAttackHeaderAndFooterIncorrectPhase18.add(timeAttackHeaderIncorrectPhase18);
        timeAttackHeaderAndFooterIncorrectPhase18.add(timeAttackFooterPhase18);
        timeAttackHeaderAndFooterIncorrectPhase19.add(timeAttackHeaderIncorrectPhase19);
        timeAttackHeaderAndFooterIncorrectPhase19.add(timeAttackFooterPhase19);
        timeAttackHeaderAndFooterIncorrectPhase20.add(timeAttackHeaderIncorrectPhase20);
        timeAttackHeaderAndFooterIncorrectPhase20.add(timeAttackFooterPhase20);
        timeAttackHeaderAndFooterIncorrectPhase21.add(timeAttackHeaderIncorrectPhase21);
        timeAttackHeaderAndFooterIncorrectPhase21.add(timeAttackFooterPhase21);
        timeAttackHeaderAndFooterIncorrectPhase22.add(timeAttackHeaderIncorrectPhase22);
        timeAttackHeaderAndFooterIncorrectPhase22.add(timeAttackFooterPhase22);
        timeAttackHeaderAndFooterIncorrectPhase23.add(timeAttackHeaderIncorrectPhase23);
        timeAttackHeaderAndFooterIncorrectPhase23.add(timeAttackFooterPhase23);
        timeAttackHeaderAndFooterIncorrectPhase24.add(timeAttackHeaderIncorrectPhase24);
        timeAttackHeaderAndFooterIncorrectPhase24.add(timeAttackFooterPhase24);
        timeAttackHeaderAndFooterIncorrectPhase25.add(timeAttackHeaderIncorrectPhase25);
        timeAttackHeaderAndFooterIncorrectPhase25.add(timeAttackFooterPhase25);
        timeAttackHeaderAndFooterIncorrectPhase26.add(timeAttackHeaderIncorrectPhase26);
        timeAttackHeaderAndFooterIncorrectPhase26.add(timeAttackFooterPhase26);
        timeAttackHeaderAndFooterIncorrectPhase27.add(timeAttackHeaderIncorrectPhase27);
        timeAttackHeaderAndFooterIncorrectPhase27.add(timeAttackFooterPhase27);
        timeAttackHeaderAndFooterIncorrectPhase28.add(timeAttackHeaderIncorrectPhase28);
        timeAttackHeaderAndFooterIncorrectPhase28.add(timeAttackFooterPhase28);
        timeAttackHeaderAndFooterIncorrectPhase29.add(timeAttackHeaderIncorrectPhase29);
        timeAttackHeaderAndFooterIncorrectPhase29.add(timeAttackFooterPhase29);
        timeAttackHeaderAndFooterIncorrectPhase30.add(timeAttackHeaderIncorrectPhase30);
        timeAttackHeaderAndFooterIncorrectPhase30.add(timeAttackFooterPhase30);
        timeAttackHeaderAndFooterIncorrectPhase31.add(timeAttackHeaderIncorrectPhase31);
        timeAttackHeaderAndFooterIncorrectPhase31.add(timeAttackFooterPhase31);
        timeAttackHeaderAndFooterIncorrectPhase32.add(timeAttackHeaderIncorrectPhase32);
        timeAttackHeaderAndFooterIncorrectPhase32.add(timeAttackFooterPhase32);
        timeAttackHeaderAndFooterIncorrectPhase33.add(timeAttackHeaderIncorrectPhase33);
        timeAttackHeaderAndFooterIncorrectPhase33.add(timeAttackFooterPhase33);
        timeAttackHeaderAndFooterIncorrectPhase34.add(timeAttackHeaderIncorrectPhase34);
        timeAttackHeaderAndFooterIncorrectPhase34.add(timeAttackFooterPhase34);
        timeAttackHeaderAndFooterIncorrectPhase35.add(timeAttackHeaderIncorrectPhase35);
        timeAttackHeaderAndFooterIncorrectPhase35.add(timeAttackFooterPhase35);
        timeAttackHeaderAndFooterIncorrectPhase36.add(timeAttackHeaderIncorrectPhase36);
        timeAttackHeaderAndFooterIncorrectPhase36.add(timeAttackFooterPhase36);

        timeAttackHeadersAndFootersIncorrect = new ArrayList<List<String>>();

        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase1);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase2);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase3);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase4);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase5);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase6);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase7);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase8);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase9);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase10);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase11);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase12);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase13);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase14);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase15);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase16);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase17);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase18);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase19);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase20);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase21);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase22);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase23);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase24);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase25);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase26);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase27);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase28);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase29);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase30);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase31);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase32);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase33);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase34);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase35);
        timeAttackHeadersAndFootersIncorrect.add(timeAttackHeaderAndFooterIncorrectPhase36);

        timeBattleHeaderAndFooterCorrectPhase1 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase2 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase3 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase4 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase5 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase6 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase7 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase8 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase9 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase10 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase11 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase12 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase13 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase14 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase15 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase16 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase17 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase18 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase19 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase20 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase21 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase22 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase23 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase24 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase25 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase26 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase27 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase28 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase29 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase30 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase31 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase32 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase33 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase34 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase35 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase36 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase37 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase38 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase39 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase40 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase41 = new ArrayList<String>();
        timeBattleHeaderAndFooterCorrectPhase42 = new ArrayList<String>();

        timeBattleHeaderAndFooterCorrectPhase1.add(timeBattleHeaderCorrectPhase1);
        timeBattleHeaderAndFooterCorrectPhase1.add(timeBattleFooterPhase1);
        timeBattleHeaderAndFooterCorrectPhase2.add(timeBattleHeaderCorrectPhase2);
        timeBattleHeaderAndFooterCorrectPhase2.add(timeBattleFooterPhase2);
        timeBattleHeaderAndFooterCorrectPhase3.add(timeBattleHeaderCorrectPhase3);
        timeBattleHeaderAndFooterCorrectPhase3.add(timeBattleFooterPhase3);
        timeBattleHeaderAndFooterCorrectPhase4.add(timeBattleHeaderCorrectPhase4);
        timeBattleHeaderAndFooterCorrectPhase4.add(timeBattleFooterPhase4);
        timeBattleHeaderAndFooterCorrectPhase5.add(timeBattleHeaderCorrectPhase5);
        timeBattleHeaderAndFooterCorrectPhase5.add(timeBattleFooterPhase5);
        timeBattleHeaderAndFooterCorrectPhase6.add(timeBattleHeaderCorrectPhase6);
        timeBattleHeaderAndFooterCorrectPhase6.add(timeBattleFooterPhase6);
        timeBattleHeaderAndFooterCorrectPhase7.add(timeBattleHeaderCorrectPhase7);
        timeBattleHeaderAndFooterCorrectPhase7.add(timeBattleFooterPhase7);
        timeBattleHeaderAndFooterCorrectPhase8.add(timeBattleHeaderCorrectPhase8);
        timeBattleHeaderAndFooterCorrectPhase8.add(timeBattleFooterPhase8);
        timeBattleHeaderAndFooterCorrectPhase9.add(timeBattleHeaderCorrectPhase9);
        timeBattleHeaderAndFooterCorrectPhase9.add(timeBattleFooterPhase9);
        timeBattleHeaderAndFooterCorrectPhase10.add(timeBattleHeaderCorrectPhase10);
        timeBattleHeaderAndFooterCorrectPhase10.add(timeBattleFooterPhase10);
        timeBattleHeaderAndFooterCorrectPhase11.add(timeBattleHeaderCorrectPhase11);
        timeBattleHeaderAndFooterCorrectPhase11.add(timeBattleFooterPhase11);
        timeBattleHeaderAndFooterCorrectPhase12.add(timeBattleHeaderCorrectPhase12);
        timeBattleHeaderAndFooterCorrectPhase12.add(timeBattleFooterPhase12);
        timeBattleHeaderAndFooterCorrectPhase13.add(timeBattleHeaderCorrectPhase13);
        timeBattleHeaderAndFooterCorrectPhase13.add(timeBattleFooterPhase13);
        timeBattleHeaderAndFooterCorrectPhase14.add(timeBattleHeaderCorrectPhase14);
        timeBattleHeaderAndFooterCorrectPhase14.add(timeBattleFooterPhase14);
        timeBattleHeaderAndFooterCorrectPhase15.add(timeBattleHeaderCorrectPhase15);
        timeBattleHeaderAndFooterCorrectPhase15.add(timeBattleFooterPhase15);
        timeBattleHeaderAndFooterCorrectPhase16.add(timeBattleHeaderCorrectPhase16);
        timeBattleHeaderAndFooterCorrectPhase16.add(timeBattleFooterPhase16);
        timeBattleHeaderAndFooterCorrectPhase17.add(timeBattleHeaderCorrectPhase17);
        timeBattleHeaderAndFooterCorrectPhase17.add(timeBattleFooterPhase17);
        timeBattleHeaderAndFooterCorrectPhase18.add(timeBattleHeaderCorrectPhase18);
        timeBattleHeaderAndFooterCorrectPhase18.add(timeBattleFooterPhase18);
        timeBattleHeaderAndFooterCorrectPhase19.add(timeBattleHeaderCorrectPhase19);
        timeBattleHeaderAndFooterCorrectPhase19.add(timeBattleFooterPhase19);
        timeBattleHeaderAndFooterCorrectPhase20.add(timeBattleHeaderCorrectPhase20);
        timeBattleHeaderAndFooterCorrectPhase20.add(timeBattleFooterPhase20);
        timeBattleHeaderAndFooterCorrectPhase21.add(timeBattleHeaderCorrectPhase21);
        timeBattleHeaderAndFooterCorrectPhase21.add(timeBattleFooterPhase21);
        timeBattleHeaderAndFooterCorrectPhase22.add(timeBattleHeaderCorrectPhase22);
        timeBattleHeaderAndFooterCorrectPhase22.add(timeBattleFooterPhase22);
        timeBattleHeaderAndFooterCorrectPhase23.add(timeBattleHeaderCorrectPhase23);
        timeBattleHeaderAndFooterCorrectPhase23.add(timeBattleFooterPhase23);
        timeBattleHeaderAndFooterCorrectPhase24.add(timeBattleHeaderCorrectPhase24);
        timeBattleHeaderAndFooterCorrectPhase24.add(timeBattleFooterPhase24);
        timeBattleHeaderAndFooterCorrectPhase25.add(timeBattleHeaderCorrectPhase25);
        timeBattleHeaderAndFooterCorrectPhase25.add(timeBattleFooterPhase25);
        timeBattleHeaderAndFooterCorrectPhase26.add(timeBattleHeaderCorrectPhase26);
        timeBattleHeaderAndFooterCorrectPhase26.add(timeBattleFooterPhase26);
        timeBattleHeaderAndFooterCorrectPhase27.add(timeBattleHeaderCorrectPhase27);
        timeBattleHeaderAndFooterCorrectPhase27.add(timeBattleFooterPhase27);
        timeBattleHeaderAndFooterCorrectPhase28.add(timeBattleHeaderCorrectPhase28);
        timeBattleHeaderAndFooterCorrectPhase28.add(timeBattleFooterPhase28);
        timeBattleHeaderAndFooterCorrectPhase29.add(timeBattleHeaderCorrectPhase29);
        timeBattleHeaderAndFooterCorrectPhase29.add(timeBattleFooterPhase29);
        timeBattleHeaderAndFooterCorrectPhase30.add(timeBattleHeaderCorrectPhase30);
        timeBattleHeaderAndFooterCorrectPhase30.add(timeBattleFooterPhase30);
        timeBattleHeaderAndFooterCorrectPhase31.add(timeBattleHeaderCorrectPhase31);
        timeBattleHeaderAndFooterCorrectPhase31.add(timeBattleFooterPhase31);
        timeBattleHeaderAndFooterCorrectPhase32.add(timeBattleHeaderCorrectPhase32);
        timeBattleHeaderAndFooterCorrectPhase32.add(timeBattleFooterPhase32);
        timeBattleHeaderAndFooterCorrectPhase33.add(timeBattleHeaderCorrectPhase33);
        timeBattleHeaderAndFooterCorrectPhase33.add(timeBattleFooterPhase33);
        timeBattleHeaderAndFooterCorrectPhase34.add(timeBattleHeaderCorrectPhase34);
        timeBattleHeaderAndFooterCorrectPhase34.add(timeBattleFooterPhase34);
        timeBattleHeaderAndFooterCorrectPhase35.add(timeBattleHeaderCorrectPhase35);
        timeBattleHeaderAndFooterCorrectPhase35.add(timeBattleFooterPhase35);
        timeBattleHeaderAndFooterCorrectPhase36.add(timeBattleHeaderCorrectPhase36);
        timeBattleHeaderAndFooterCorrectPhase36.add(timeBattleFooterPhase36);
        timeBattleHeaderAndFooterCorrectPhase37.add(timeBattleHeaderCorrectPhase37);
        timeBattleHeaderAndFooterCorrectPhase37.add(timeBattleFooterPhase37);
        timeBattleHeaderAndFooterCorrectPhase38.add(timeBattleHeaderCorrectPhase38);
        timeBattleHeaderAndFooterCorrectPhase38.add(timeBattleFooterPhase38);
        timeBattleHeaderAndFooterCorrectPhase39.add(timeBattleHeaderCorrectPhase39);
        timeBattleHeaderAndFooterCorrectPhase39.add(timeBattleFooterPhase39);
        timeBattleHeaderAndFooterCorrectPhase40.add(timeBattleHeaderCorrectPhase40);
        timeBattleHeaderAndFooterCorrectPhase40.add(timeBattleFooterPhase40);
        timeBattleHeaderAndFooterCorrectPhase41.add(timeBattleHeaderCorrectPhase41);
        timeBattleHeaderAndFooterCorrectPhase41.add(timeBattleFooterPhase41);
        timeBattleHeaderAndFooterCorrectPhase42.add(timeBattleHeaderCorrectPhase42);
        timeBattleHeaderAndFooterCorrectPhase42.add(timeBattleFooterPhase42);

        timeBattleHeadersAndFootersCorrect = new ArrayList<List<String>>();

        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase1);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase2);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase3);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase4);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase5);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase6);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase7);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase8);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase9);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase10);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase11);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase12);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase13);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase14);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase15);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase16);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase17);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase18);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase19);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase20);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase21);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase22);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase23);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase24);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase25);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase26);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase27);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase28);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase29);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase30);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase31);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase32);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase33);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase34);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase35);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase36);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase37);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase38);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase39);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase40);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase41);
        timeBattleHeadersAndFootersCorrect.add(timeBattleHeaderAndFooterCorrectPhase42);

        timeBattleHeaderAndFooterIncorrectPhase1 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase2 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase3 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase4 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase5 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase6 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase7 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase8 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase9 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase10 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase11 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase12 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase13 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase14 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase15 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase16 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase17 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase18 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase19 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase20 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase21 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase22 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase23 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase24 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase25 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase26 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase27 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase28 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase29 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase30 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase31 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase32 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase33 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase34 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase35 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase36 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase37 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase38 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase39 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase40 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase41 = new ArrayList<String>();
        timeBattleHeaderAndFooterIncorrectPhase42 = new ArrayList<String>();

        timeBattleHeaderAndFooterIncorrectPhase1.add(timeBattleHeaderIncorrectPhase1);
        timeBattleHeaderAndFooterIncorrectPhase1.add(timeBattleFooterPhase1);
        timeBattleHeaderAndFooterIncorrectPhase2.add(timeBattleHeaderIncorrectPhase2);
        timeBattleHeaderAndFooterIncorrectPhase2.add(timeBattleFooterPhase2);
        timeBattleHeaderAndFooterIncorrectPhase3.add(timeBattleHeaderIncorrectPhase3);
        timeBattleHeaderAndFooterIncorrectPhase3.add(timeBattleFooterPhase3);
        timeBattleHeaderAndFooterIncorrectPhase4.add(timeBattleHeaderIncorrectPhase4);
        timeBattleHeaderAndFooterIncorrectPhase4.add(timeBattleFooterPhase4);
        timeBattleHeaderAndFooterIncorrectPhase5.add(timeBattleHeaderIncorrectPhase5);
        timeBattleHeaderAndFooterIncorrectPhase5.add(timeBattleFooterPhase5);
        timeBattleHeaderAndFooterIncorrectPhase6.add(timeBattleHeaderIncorrectPhase6);
        timeBattleHeaderAndFooterIncorrectPhase6.add(timeBattleFooterPhase6);
        timeBattleHeaderAndFooterIncorrectPhase7.add(timeBattleHeaderIncorrectPhase7);
        timeBattleHeaderAndFooterIncorrectPhase7.add(timeBattleFooterPhase7);
        timeBattleHeaderAndFooterIncorrectPhase8.add(timeBattleHeaderIncorrectPhase8);
        timeBattleHeaderAndFooterIncorrectPhase8.add(timeBattleFooterPhase8);
        timeBattleHeaderAndFooterIncorrectPhase9.add(timeBattleHeaderIncorrectPhase9);
        timeBattleHeaderAndFooterIncorrectPhase9.add(timeBattleFooterPhase9);
        timeBattleHeaderAndFooterIncorrectPhase10.add(timeBattleHeaderIncorrectPhase10);
        timeBattleHeaderAndFooterIncorrectPhase10.add(timeBattleFooterPhase10);
        timeBattleHeaderAndFooterIncorrectPhase11.add(timeBattleHeaderIncorrectPhase11);
        timeBattleHeaderAndFooterIncorrectPhase11.add(timeBattleFooterPhase11);
        timeBattleHeaderAndFooterIncorrectPhase12.add(timeBattleHeaderIncorrectPhase12);
        timeBattleHeaderAndFooterIncorrectPhase12.add(timeBattleFooterPhase12);
        timeBattleHeaderAndFooterIncorrectPhase13.add(timeBattleHeaderIncorrectPhase13);
        timeBattleHeaderAndFooterIncorrectPhase13.add(timeBattleFooterPhase13);
        timeBattleHeaderAndFooterIncorrectPhase14.add(timeBattleHeaderIncorrectPhase14);
        timeBattleHeaderAndFooterIncorrectPhase14.add(timeBattleFooterPhase14);
        timeBattleHeaderAndFooterIncorrectPhase15.add(timeBattleHeaderIncorrectPhase15);
        timeBattleHeaderAndFooterIncorrectPhase15.add(timeBattleFooterPhase15);
        timeBattleHeaderAndFooterIncorrectPhase16.add(timeBattleHeaderIncorrectPhase16);
        timeBattleHeaderAndFooterIncorrectPhase16.add(timeBattleFooterPhase16);
        timeBattleHeaderAndFooterIncorrectPhase17.add(timeBattleHeaderIncorrectPhase17);
        timeBattleHeaderAndFooterIncorrectPhase17.add(timeBattleFooterPhase17);
        timeBattleHeaderAndFooterIncorrectPhase18.add(timeBattleHeaderIncorrectPhase18);
        timeBattleHeaderAndFooterIncorrectPhase18.add(timeBattleFooterPhase18);
        timeBattleHeaderAndFooterIncorrectPhase19.add(timeBattleHeaderIncorrectPhase19);
        timeBattleHeaderAndFooterIncorrectPhase19.add(timeBattleFooterPhase19);
        timeBattleHeaderAndFooterIncorrectPhase20.add(timeBattleHeaderIncorrectPhase20);
        timeBattleHeaderAndFooterIncorrectPhase20.add(timeBattleFooterPhase20);
        timeBattleHeaderAndFooterIncorrectPhase21.add(timeBattleHeaderIncorrectPhase21);
        timeBattleHeaderAndFooterIncorrectPhase21.add(timeBattleFooterPhase21);
        timeBattleHeaderAndFooterIncorrectPhase22.add(timeBattleHeaderIncorrectPhase22);
        timeBattleHeaderAndFooterIncorrectPhase22.add(timeBattleFooterPhase22);
        timeBattleHeaderAndFooterIncorrectPhase23.add(timeBattleHeaderIncorrectPhase23);
        timeBattleHeaderAndFooterIncorrectPhase23.add(timeBattleFooterPhase23);
        timeBattleHeaderAndFooterIncorrectPhase24.add(timeBattleHeaderIncorrectPhase24);
        timeBattleHeaderAndFooterIncorrectPhase24.add(timeBattleFooterPhase24);
        timeBattleHeaderAndFooterIncorrectPhase25.add(timeBattleHeaderIncorrectPhase25);
        timeBattleHeaderAndFooterIncorrectPhase25.add(timeBattleFooterPhase25);
        timeBattleHeaderAndFooterIncorrectPhase26.add(timeBattleHeaderIncorrectPhase26);
        timeBattleHeaderAndFooterIncorrectPhase26.add(timeBattleFooterPhase26);
        timeBattleHeaderAndFooterIncorrectPhase27.add(timeBattleHeaderIncorrectPhase27);
        timeBattleHeaderAndFooterIncorrectPhase27.add(timeBattleFooterPhase27);
        timeBattleHeaderAndFooterIncorrectPhase28.add(timeBattleHeaderIncorrectPhase28);
        timeBattleHeaderAndFooterIncorrectPhase28.add(timeBattleFooterPhase28);
        timeBattleHeaderAndFooterIncorrectPhase29.add(timeBattleHeaderIncorrectPhase29);
        timeBattleHeaderAndFooterIncorrectPhase29.add(timeBattleFooterPhase29);
        timeBattleHeaderAndFooterIncorrectPhase30.add(timeBattleHeaderIncorrectPhase30);
        timeBattleHeaderAndFooterIncorrectPhase30.add(timeBattleFooterPhase30);
        timeBattleHeaderAndFooterIncorrectPhase31.add(timeBattleHeaderIncorrectPhase31);
        timeBattleHeaderAndFooterIncorrectPhase31.add(timeBattleFooterPhase31);
        timeBattleHeaderAndFooterIncorrectPhase32.add(timeBattleHeaderIncorrectPhase32);
        timeBattleHeaderAndFooterIncorrectPhase32.add(timeBattleFooterPhase32);
        timeBattleHeaderAndFooterIncorrectPhase33.add(timeBattleHeaderIncorrectPhase33);
        timeBattleHeaderAndFooterIncorrectPhase33.add(timeBattleFooterPhase33);
        timeBattleHeaderAndFooterIncorrectPhase34.add(timeBattleHeaderIncorrectPhase34);
        timeBattleHeaderAndFooterIncorrectPhase34.add(timeBattleFooterPhase34);
        timeBattleHeaderAndFooterIncorrectPhase35.add(timeBattleHeaderIncorrectPhase35);
        timeBattleHeaderAndFooterIncorrectPhase35.add(timeBattleFooterPhase35);
        timeBattleHeaderAndFooterIncorrectPhase36.add(timeBattleHeaderIncorrectPhase36);
        timeBattleHeaderAndFooterIncorrectPhase36.add(timeBattleFooterPhase36);
        timeBattleHeaderAndFooterIncorrectPhase37.add(timeBattleHeaderIncorrectPhase37);
        timeBattleHeaderAndFooterIncorrectPhase37.add(timeBattleFooterPhase37);
        timeBattleHeaderAndFooterIncorrectPhase38.add(timeBattleHeaderIncorrectPhase38);
        timeBattleHeaderAndFooterIncorrectPhase38.add(timeBattleFooterPhase38);
        timeBattleHeaderAndFooterIncorrectPhase39.add(timeBattleHeaderIncorrectPhase39);
        timeBattleHeaderAndFooterIncorrectPhase39.add(timeBattleFooterPhase39);
        timeBattleHeaderAndFooterIncorrectPhase40.add(timeBattleHeaderIncorrectPhase40);
        timeBattleHeaderAndFooterIncorrectPhase40.add(timeBattleFooterPhase40);
        timeBattleHeaderAndFooterIncorrectPhase41.add(timeBattleHeaderIncorrectPhase41);
        timeBattleHeaderAndFooterIncorrectPhase41.add(timeBattleFooterPhase41);
        timeBattleHeaderAndFooterIncorrectPhase42.add(timeBattleHeaderIncorrectPhase42);
        timeBattleHeaderAndFooterIncorrectPhase42.add(timeBattleFooterPhase42);

        timeBattleHeadersAndFootersIncorrect = new ArrayList<List<String>>();

        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase1);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase2);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase3);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase4);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase5);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase6);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase7);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase8);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase9);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase10);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase11);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase12);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase13);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase14);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase15);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase16);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase17);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase18);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase19);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase20);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase21);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase22);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase23);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase24);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase25);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase26);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase27);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase28);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase29);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase30);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase31);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase32);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase33);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase34);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase35);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase36);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase37);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase38);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase39);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase40);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase41);
        timeBattleHeadersAndFootersIncorrect.add(timeBattleHeaderAndFooterIncorrectPhase42);

        correctInputMapSquirgle = new HashMap<Boolean, List<List<String>>>();
        correctInputMapBattle = new HashMap<Boolean, List<List<String>>>();
        correctInputMapTimeAttack = new HashMap<Boolean, List<List<String>>>();
        correctInputMapTimeBattle = new HashMap<Boolean, List<List<String>>>();
        gameplayTypeMap = new HashMap<Integer, Map<Boolean, List<List<String>>>>();

        correctInputMapSquirgle.put(true, squirgleHeadersAndFootersCorrect);
        correctInputMapSquirgle.put(false, squirgleHeadersAndFootersIncorrect);

        correctInputMapBattle.put(true, battleHeadersAndFootersCorrect);
        correctInputMapBattle.put(false, battleHeadersAndFootersIncorrect);

        correctInputMapTimeAttack.put(true, timeAttackHeadersAndFootersCorrect);
        correctInputMapTimeAttack.put(false, timeAttackHeadersAndFootersIncorrect);

        correctInputMapTimeBattle.put(true, timeBattleHeadersAndFootersCorrect);
        correctInputMapTimeBattle.put(false, timeBattleHeadersAndFootersIncorrect);

        gameplayTypeMap.put(Squirgle.GAMEPLAY_SQUIRGLE, correctInputMapSquirgle);
        gameplayTypeMap.put(Squirgle.GAMEPLAY_BATTLE, correctInputMapBattle);
        gameplayTypeMap.put(Squirgle.GAMEPLAY_TIME_ATTACK, correctInputMapTimeAttack);
        gameplayTypeMap.put(Squirgle.GAMEPLAY_TIME_BATTLE, correctInputMapTimeBattle);

        game.setUpFontTutorialHelp(MathUtils.round(game.ASPECT_RATIO * ((1920 / 1080) * FONT_TUTORIAL_HELP_SIZE_MULTIPLIER) / (1920 / 1080))); //Using 1920 / 1080 because that's the OG resolution--the one for which I originally developed--and I wish to scale it for other devices.

        labelStyle = new Label.LabelStyle();
        labelStyle.font = game.fontTutorialHelp;
        labelStyle.fontColor = blackAndWhite ? Color.WHITE : Color.BLACK;
        headerLabel = new Label(getCurrentHeaderText(), labelStyle);
        if(game.widthGreater) {
            headerLabel.setSize(splitScreen ? promptShapeP1.getCoordinates().x - promptShapeP1.getRadius() - (8 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS) : promptShape.getCoordinates().x - promptShape.getRadius() - (8 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS), splitScreen ? (game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) : game.camera.viewportHeight - TARGET_RADIUS - (INPUT_POINT_SPAWN.y + INPUT_RADIUS));
            headerLabel.setPosition((8 * BACKGROUND_COLOR_LIST_ELEMENT_RADIUS), splitScreen ? INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS : INPUT_POINT_SPAWN.y + INPUT_RADIUS);
        } else {
            headerLabel.setSize(game.camera.viewportWidth - (2 * TARGET_RADIUS), splitScreen ? (game.camera.viewportHeight / 2) - (promptShapeP1.getCoordinates().y + promptShapeP1.getRadius()) : game.camera.viewportHeight - (promptShape.getCoordinates().y + promptShape.getRadius()));
            headerLabel.setPosition(TARGET_RADIUS, splitScreen ? promptShapeP1.getCoordinates().y + promptShapeP1.getRadius() : promptShape.getCoordinates().y + promptShape.getRadius());
        }
        headerLabel.setAlignment(Align.center);
        headerLabel.setWrap(true);
        headerLabel.setVisible(true);
        footerLabel = new Label(getCurrentFooterText(), labelStyle);
        if(game.widthGreater) {
            footerLabel.setSize(splitScreen ? game.camera.viewportWidth - INPUT_RADIUS - (promptShapeP1.getCoordinates().x + promptShapeP1.getRadius()) : game.camera.viewportWidth - INPUT_RADIUS - (promptShape.getCoordinates().x + promptShape.getRadius()), splitScreen ? (game.camera.viewportHeight / 2) - TARGET_RADIUS - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) : game.camera.viewportHeight - TARGET_RADIUS - (INPUT_POINT_SPAWN.y + INPUT_RADIUS));
            footerLabel.setPosition(splitScreen ? promptShapeP1.getCoordinates().x + promptShapeP1.getRadius() : promptShape.getCoordinates().x + promptShape.getRadius(), splitScreen ? INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS : INPUT_POINT_SPAWN.y + INPUT_RADIUS);
        } else {
            footerLabel.setSize(game.camera.viewportWidth - (2 * TARGET_RADIUS), splitScreen ? promptShapeP1.getCoordinates().y - promptShapeP1.getRadius() - (INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS) : promptShape.getCoordinates().y - promptShape.getRadius() - (INPUT_POINT_SPAWN.y + INPUT_RADIUS));
            footerLabel.setPosition(TARGET_RADIUS, splitScreen ? INPUT_POINT_SPAWN_P1.y + INPUT_RADIUS : INPUT_POINT_SPAWN.y + INPUT_RADIUS);
        }
        footerLabel.setAlignment(Align.center);
        footerLabel.setWrap(true);
        footerLabel.setVisible(true);

        stage = new Stage(game.viewport);
        stage.addActor(headerLabel);
        stage.addActor(footerLabel);

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
