package com.screenlooker.squirgle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.screenlooker.squirgle.screen.GameplayScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.screenlooker.squirgle.Squirgle.SAVE_NAME;

public class Stats {
    final Squirgle game;

    public static final String NA = "N/A";

    public static final String MODE_SQUIRGLE = "SQUIRGLE";
    public static final String MODE_BATTLE = "BATTLE";
    public static final String MODE_TIME_ATTACK = "TIME ATTACK";
    public static final String MODE_TIME_BATTLE = "TIME BATTLE";
    public static final String MODE_TRANCE = "TRANCE";

    public static final String SAVE_TIME_PLAYED = "timePlayed";
    public static final String SAVE_NUM_SQUIRGLES = "numSquirgles";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARE = "numTimesPlayedSquare";
    public static final String SAVE_NUM_TIMES_PLAYED_PENTAGON = "numTimesPlayedPentagon";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXAGON = "numTimesPlayedHexagon";
    public static final String SAVE_NUM_TIMES_PLAYED_SEPTAGON = "numTimesPlayedSeptagon";
    public static final String SAVE_NUM_TIMES_PLAYED_OCTAGON = "numTimesPlayedOctagon";
    public static final String SAVE_NUM_TIMES_PLAYED_NONAGON = "numTimesPlayedNonagon";
    public static final String SAVE_NUM_TIMES_PLAYED_POINTILLISM = "numTimesPlayedPointillism";
    public static final String SAVE_NUM_TIMES_PLAYED_LINEAGE = "numTimesPlayedLineage";
    public static final String SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ = "numTimesPlayedTriTheWaltz";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARED_OFF = "numTimesPlayedSquaredOff";
    public static final String SAVE_NUM_TIMES_PLAYED_PENT_UP = "numTimesPlayedPentUp";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL = "numTimesPlayedHexidecibel";
    public static final String SAVE_NUM_TIMES_PLAYED_INTERSEPTOR = "numTimesPlayedInterseptor";
    public static final String SAVE_NUM_TIMES_PLAYED_ROCTOPUS = "numTimesPlayedRoctopus";
    public static final String SAVE_NUM_TIMES_PLAYED_NONPLUSSED = "numTimesPlayedNonplussed";
    public static final String SAVE_FAVORITE_BASE = "favoriteBase";
    public static final String SAVE_FAVORITE_MODE = "favoriteMode";
    public static final String SAVE_FAVORITE_TRACK = "favoriteTrack";

    public static final String SAVE_TIME_PLAYED_SQUIRGLE = "timePlayedSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUIRGLE = "numTimesPlayedSquirgle";
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE = "numSquirglesSquirgle";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE = "longestRunSquirgle";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE = "highestScoreSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARE_SQUIRGLE = "numTimesPlayedSquareSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_PENTAGON_SQUIRGLE = "numTimesPlayedPentagonSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXAGON_SQUIRGLE = "numTimesPlayedHexagonSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_SEPTAGON_SQUIRGLE = "numTimesPlayedSeptagonSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_OCTAGON_SQUIRGLE = "numTimesPlayedOctagonSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_NONAGON_SQUIRGLE = "numTimesPlayedNonagonSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_POINTILLISM_SQUIRGLE = "numTimesPlayedPointillismSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_LINEAGE_SQUIRGLE = "numTimesPlayedLineageSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_SQUIRGLE = "numTimesPlayedTriTheWaltzSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_SQUIRGLE = "numTimesPlayedSquaredOffSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_PENT_UP_SQUIRGLE = "numTimesPlayedPentUpSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_SQUIRGLE = "numTimesPlayedHexidecibelSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_SQUIRGLE = "numTimesPlayedInterseptorSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_ROCTOPUS_SQUIRGLE = "numTimesPlayedRoctopusSquirgle";
    public static final String SAVE_NUM_TIMES_PLAYED_NONPLUSSED_SQUIRGLE = "numTimesPlayedNonplussedSquirgle";
    public static final String SAVE_FAVORITE_BASE_SQUIRGLE = "favoriteBaseSquirgle";
    public static final String SAVE_FAVORITE_TRACK_SQUIRGLE = "favoriteTrackSquirgle";

    public static final String SAVE_TIME_PLAYED_BATTLE = "timePlayedBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_BATTLE = "numTimesPlayedBattle";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE = "numSquirglesBattle";
    public static final String SAVE_FASTEST_VICTORY = "fastestVictory";
    public static final String SAVE_NUM_TIMES_WON_BATTLE = "numTimesWonBattle";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE = "numTimesLostBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARE_BATTLE = "numTimesPlayedSquareBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_PENTAGON_BATTLE = "numTimesPlayedPentagonBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXAGON_BATTLE = "numTimesPlayedHexagonBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_SEPTAGON_BATTLE = "numTimesPlayedSeptagonBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_OCTAGON_BATTLE = "numTimesPlayedOctagonBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_NONAGON_BATTLE = "numTimesPlayedNonagonBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_POINTILLISM_BATTLE = "numTimesPlayedPointillismBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_LINEAGE_BATTLE = "numTimesPlayedLineageBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_BATTLE = "numTimesPlayedTriTheWaltzBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_BATTLE = "numTimesPlayedSquaredOffBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_PENT_UP_BATTLE = "numTimesPlayedPentUpBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_BATTLE = "numTimesPlayedHexidecibelBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_BATTLE = "numTimesPlayedInterseptorBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_ROCTOPUS_BATTLE = "numTimesPlayedRoctopusBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_NONPLUSSED_BATTLE = "numTimesPlayedNonplussedBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_EASY_BATTLE = "numTimesPlayedEasyBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_MEDIUM_BATTLE = "numTimesPlayedMediumBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_HARD_BATTLE = "numTimesPlayedHardBattle";
    public static final String SAVE_FAVORITE_BASE_BATTLE = "favoriteBaseBattle";
    public static final String SAVE_FAVORITE_DIFFICULTY_BATTLE = "favoriteDifficultyBattle";
    public static final String SAVE_FAVORITE_TRACK_BATTLE = "favoriteTrackBattle";

    public static final String SAVE_TIME_PLAYED_TIME_ATTACK = "timePlayedTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_TIME_ATTACK = "numTimesPlayedTimeAttack";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK = "highestScoreTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARE_TIME_ATTACK = "numTimesPlayedSquareTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_PENTAGON_TIME_ATTACK = "numTimesPlayedPentagonTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXAGON_TIME_ATTACK = "numTimesPlayedHexagonTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_SEPTAGON_TIME_ATTACK = "numTimesPlayedSeptagonTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_OCTAGON_TIME_ATTACK = "numTimesPlayedOctagonTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_NONAGON_TIME_ATTACK = "numTimesPlayedNonagonTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_POINTILLISM_TIME_ATTACK = "numTimesPlayedPointillismTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_LINEAGE_TIME_ATTACK = "numTimesPlayedLineageTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TIME_ATTACK = "numTimesPlayedTriTheWaltzTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TIME_ATTACK = "numTimesPlayedSquaredOffTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_PENT_UP_TIME_ATTACK = "numTimesPlayedPentUpTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TIME_ATTACK = "numTimesPlayedHexidecibelTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TIME_ATTACK = "numTimesPlayedInterseptorTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TIME_ATTACK = "numTimesPlayedRoctopusTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TIME_ATTACK = "numTimesPlayedNonplussedTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_ONE_MINUTE_TIME_ATTACK = "numTimesPlayedOneMinuteTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_THREE_MINUTES_TIME_ATTACK = "numTimesPlayedThreeMinutesTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_FIVE_MINUTES_TIME_ATTACK = "numTimesPlayedFiveMinutesTimeAttack";
    public static final String SAVE_NUM_TIMES_PLAYED_TEN_MINUTES_TIME_ATTACK = "numTimesPlayedTenMinutesTimeAttack";
    public static final String SAVE_FAVORITE_BASE_TIME_ATTACK = "favoriteBaseTimeAttack";
    public static final String SAVE_FAVORITE_GAME_LENGTH_TIME_ATTACK = "favoriteGameLengthTimeAttack";
    public static final String SAVE_FAVORITE_TRACK_TIME_ATTACK = "favoriteTrackTimeAttack";

    public static final String SAVE_TIME_PLAYED_TIME_BATTLE = "timePlayedTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_TIME_BATTLE = "numTimesPlayedTimeBattle";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE = "highestScoreTimeBattle";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE = "numTimesWonTimeBattle";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE = "numTimesLostTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARE_TIME_BATTLE = "numTimesPlayedSquareTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_PENTAGON_TIME_BATTLE = "numTimesPlayedPentagonTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXAGON_TIME_BATTLE = "numTimesPlayedHexagonTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_SEPTAGON_TIME_BATTLE = "numTimesPlayedSeptagonTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_OCTAGON_TIME_BATTLE = "numTimesPlayedOctagonTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_NONAGON_TIME_BATTLE = "numTimesPlayedNonagonTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_POINTILLISM_TIME_BATTLE = "numTimesPlayedPointillismTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_LINEAGE_TIME_BATTLE = "numTimesPlayedLineageTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TIME_BATTLE = "numTimesPlayedTriTheWaltzTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TIME_BATTLE = "numTimesPlayedSquaredOffTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_PENT_UP_TIME_BATTLE = "numTimesPlayedPentUpTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TIME_BATTLE = "numTimesPlayedHexidecibelTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TIME_BATTLE = "numTimesPlayedInterseptorTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TIME_BATTLE = "numTimesPlayedRoctopusTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TIME_BATTLE = "numTimesPlayedNonplussedTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_EASY_TIME_BATTLE = "numTimesPlayedEasyTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_MEDIUM_TIME_BATTLE = "numTimesPlayedMediumTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_HARD_TIME_BATTLE = "numTimesPlayedHardTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_ONE_MINUTE_TIME_BATTLE = "numTimesPlayedOneMinuteTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_THREE_MINUTES_TIME_BATTLE = "numTimesPlayedThreeMinutesTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_FIVE_MINUTES_TIME_BATTLE = "numTimesPlayedFiveMinutesTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_TEN_MINUTES_TIME_BATTLE = "numTimesPlayedTenMinutesTimeBattle";
    public static final String SAVE_FAVORITE_BASE_TIME_BATTLE = "favoriteBaseTimeBattle";
    public static final String SAVE_FAVORITE_GAME_LENGTH_TIME_BATTLE = "favoriteGameLengthTimeBattle";
    public static final String SAVE_FAVORITE_DIFFICULTY_TIME_BATTLE = "favoriteDifficultyTimeBattle";
    public static final String SAVE_FAVORITE_TRACK_TIME_BATTLE = "favoriteTrackTimeBattle";

    public static final String SAVE_TIME_PLAYED_TRANCE = "timePlayedTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_TRANCE = "numTimesPlayedTrance";
    public static final String SAVE_LONGEST_RUN_TRANCE = "longestRunTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_POINTILLISM_TRANCE = "numTimesPlayedPointillismTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_LINEAGE_TRANCE = "numTimesPlayedLineageTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TRANCE = "numTimesPlayedTriTheWaltzTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TRANCE = "numTimesPlayedSquaredOffTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_PENT_UP_TRANCE = "numTimesPlayedPentUpTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TRANCE = "numTimesPlayedHexidecibelTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TRANCE = "numTimesPlayedInterseptorTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TRANCE = "numTimesPlayedRoctopusTrance";
    public static final String SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TRANCE = "numTimesPlayedNonplussedTrance";
    public static final String SAVE_FAVORITE_TRACK_TRANCE = "favoriteTrackTrance";

    //General
    public long timePlayed;
    public long numSquirgles;
    public long numTimesPlayedSquare;
    public long numTimesPlayedPentagon;
    public long numTimesPlayedHexagon;
    public long numTimesPlayedSeptagon;
    public long numTimesPlayedOctagon;
    public long numTimesPlayedNonagon;
    public long numTimesPlayedPointillism;
    public long numTimesPlayedLineage;
    public long numTimesPlayedTriTheWaltz;
    public long numTimesPlayedSquaredOff;
    public long numTimesPlayedPentUp;
    public long numTimesPlayedHexidecibel;
    public long numTimesPlayedInterseptor;
    public long numTimesPlayedRoctopus;
    public long numTimesPlayedNonplussed;
    public int favoriteBase;
    public String favoriteMode;
    public String favoriteTrack;

    //Squirgle
    public long timePlayedSquirgle;
    public long numTimesPlayedSquirgle;
    public long numSquirglesSquirgle;
    public long longestRunSquirgle;
    public long highestScoreSquirgle;
    public long numTimesPlayedSquareSquirgle;
    public long numTimesPlayedPentagonSquirgle;
    public long numTimesPlayedHexagonSquirgle;
    public long numTimesPlayedSeptagonSquirgle;
    public long numTimesPlayedOctagonSquirgle;
    public long numTimesPlayedNonagonSquirgle;
    public long numTimesPlayedPointillismSquirgle;
    public long numTimesPlayedLineageSquirgle;
    public long numTimesPlayedTriTheWaltzSquirgle;
    public long numTimesPlayedSquaredOffSquirgle;
    public long numTimesPlayedPentUpSquirgle;
    public long numTimesPlayedHexidecibelSquirgle;
    public long numTimesPlayedInterseptorSquirgle;
    public long numTimesPlayedRoctopusSquirgle;
    public long numTimesPlayedNonplussedSquirgle;
    public int favoriteBaseSquirgle;
    public String favoriteTrackSquirgle;

    //Battle
    public long timePlayedBattle;
    public long numTimesPlayedBattle;
    public long numSquirglesBattle;
    public long fastestVictory;
    public long numTimesWonBattle;
    public long numTimesLostBattle;
    public long numTimesPlayedSquareBattle;
    public long numTimesPlayedPentagonBattle;
    public long numTimesPlayedHexagonBattle;
    public long numTimesPlayedSeptagonBattle;
    public long numTimesPlayedOctagonBattle;
    public long numTimesPlayedNonagonBattle;
    public long numTimesPlayedPointillismBattle;
    public long numTimesPlayedLineageBattle;
    public long numTimesPlayedTriTheWaltzBattle;
    public long numTimesPlayedSquaredOffBattle;
    public long numTimesPlayedPentUpBattle;
    public long numTimesPlayedHexidecibelBattle;
    public long numTimesPlayedInterseptorBattle;
    public long numTimesPlayedRoctopusBattle;
    public long numTimesPlayedNonplussedBattle;
    public long numTimesPlayedEasyBattle;
    public long numTimesPlayedMediumBattle;
    public long numTimesPlayedHardBattle;
    public int favoriteBaseBattle;
    public String favoriteDifficultyBattle;
    public String favoriteTrackBattle;

    //Time Attack
    public long timePlayedTimeAttack;
    public long numTimesPlayedTimeAttack;
    public long highestScoreTimeAttack;
    public long numTimesPlayedSquareTimeAttack;
    public long numTimesPlayedPentagonTimeAttack;
    public long numTimesPlayedHexagonTimeAttack;
    public long numTimesPlayedSeptagonTimeAttack;
    public long numTimesPlayedOctagonTimeAttack;
    public long numTimesPlayedNonagonTimeAttack;
    public long numTimesPlayedPointillismTimeAttack;
    public long numTimesPlayedLineageTimeAttack;
    public long numTimesPlayedTriTheWaltzTimeAttack;
    public long numTimesPlayedSquaredOffTimeAttack;
    public long numTimesPlayedPentUpTimeAttack;
    public long numTimesPlayedHexidecibelTimeAttack;
    public long numTimesPlayedInterseptorTimeAttack;
    public long numTimesPlayedRoctopusTimeAttack;
    public long numTimesPlayedNonplussedTimeAttack;
    public long numTimesPlayedOneMinuteTimeAttack;
    public long numTimesPlayedThreeMinutesTimeAttack;
    public long numTimesPlayedFiveMinutesTimeAttack;
    public long numTimesPlayedTenMinutesTimeAttack;
    public int favoriteBaseTimeAttack;
    public int favoriteGameLengthTimeAttack;
    public String favoriteTrackTimeAttack;

    //Time Battle
    public long timePlayedTimeBattle;
    public long numTimesPlayedTimeBattle;
    public long highestScoreTimeBattle;
    public long numTimesWonTimeBattle;
    public long numTimesLostTimeBattle;
    public long numTimesPlayedSquareTimeBattle;
    public long numTimesPlayedPentagonTimeBattle;
    public long numTimesPlayedHexagonTimeBattle;
    public long numTimesPlayedSeptagonTimeBattle;
    public long numTimesPlayedOctagonTimeBattle;
    public long numTimesPlayedNonagonTimeBattle;
    public long numTimesPlayedPointillismTimeBattle;
    public long numTimesPlayedLineageTimeBattle;
    public long numTimesPlayedTriTheWaltzTimeBattle;
    public long numTimesPlayedSquaredOffTimeBattle;
    public long numTimesPlayedPentUpTimeBattle;
    public long numTimesPlayedHexidecibelTimeBattle;
    public long numTimesPlayedInterseptorTimeBattle;
    public long numTimesPlayedRoctopusTimeBattle;
    public long numTimesPlayedNonplussedTimeBattle;
    public long numTimesPlayedEasyTimeBattle;
    public long numTimesPlayedMediumTimeBattle;
    public long numTimesPlayedHardTimeBattle;
    public long numTimesPlayedOneMinuteTimeBattle;
    public long numTimesPlayedThreeMinutesTimeBattle;
    public long numTimesPlayedFiveMinutesTimeBattle;
    public long numTimesPlayedTenMinutesTimeBattle;
    public int favoriteBaseTimeBattle;
    public int favoriteGameLengthTimeBattle;
    public String favoriteDifficultyTimeBattle;
    public String favoriteTrackTimeBattle;

    //Trance
    public long timePlayedTrance;
    public long numTimesPlayedTrance;
    public long longestRunTrance;
    public long numTimesPlayedPointillismTrance;
    public long numTimesPlayedLineageTrance;
    public long numTimesPlayedTriTheWaltzTrance;
    public long numTimesPlayedSquaredOffTrance;
    public long numTimesPlayedPentUpTrance;
    public long numTimesPlayedHexidecibelTrance;
    public long numTimesPlayedInterseptorTrance;
    public long numTimesPlayedRoctopusTrance;
    public long numTimesPlayedNonplussedTrance;
    public String favoriteTrackTrance;

    public Stats(final Squirgle game) {
        this.game = game;

        //General
        timePlayed = game.save.getLong(SAVE_TIME_PLAYED, 0);
        numSquirgles = game.save.getLong(SAVE_NUM_SQUIRGLES, 0);
        favoriteMode = game.save.getString(SAVE_FAVORITE_MODE, NA);
        favoriteBase = game.save.getInteger(SAVE_FAVORITE_BASE, 0);
        favoriteTrack = game.save.getString(SAVE_FAVORITE_TRACK, NA);

        //Squirgle
        timePlayedSquirgle = game.save.getLong(SAVE_TIME_PLAYED_SQUIRGLE, 0);
        numTimesPlayedSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUIRGLE, 0);
        numSquirglesSquirgle = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE, 0);
        longestRunSquirgle = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE, 0);
        highestScoreSquirgle = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE, 0);
        numTimesPlayedSquareSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARE_SQUIRGLE, 0);
        numTimesPlayedPentagonSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENTAGON_SQUIRGLE, 0);
        numTimesPlayedHexagonSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXAGON_SQUIRGLE, 0);
        numTimesPlayedSeptagonSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SEPTAGON_SQUIRGLE, 0);
        numTimesPlayedOctagonSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_OCTAGON_SQUIRGLE, 0);
        numTimesPlayedNonagonSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONAGON_SQUIRGLE, 0);
        numTimesPlayedPointillismSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_POINTILLISM_SQUIRGLE, 0);
        numTimesPlayedLineageSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_LINEAGE_SQUIRGLE, 0);
        numTimesPlayedTriTheWaltzSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_SQUIRGLE, 0);
        numTimesPlayedSquaredOffSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_SQUIRGLE, 0);
        numTimesPlayedPentUpSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENT_UP_SQUIRGLE, 0);
        numTimesPlayedHexidecibelSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_SQUIRGLE, 0);
        numTimesPlayedInterseptorSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_SQUIRGLE, 0);
        numTimesPlayedRoctopusSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_SQUIRGLE, 0);
        numTimesPlayedNonplussedSquirgle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_SQUIRGLE, 0);
        favoriteBaseSquirgle = game.save.getInteger(SAVE_FAVORITE_BASE_SQUIRGLE, 0);
        favoriteTrackSquirgle = game.save.getString(SAVE_FAVORITE_TRACK_SQUIRGLE, NA);

        //Battle
        timePlayedBattle = game.save.getLong(SAVE_TIME_PLAYED_BATTLE, 0);
        numTimesPlayedBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_BATTLE, 0);
        numSquirglesBattle = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE, 0);
        fastestVictory = game.save.getLong(SAVE_FASTEST_VICTORY, 0);
        numTimesWonBattle = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE, 0);
        numTimesLostBattle = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE, 0);
        numTimesPlayedSquareBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARE_BATTLE, 0);
        numTimesPlayedPentagonBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENTAGON_BATTLE, 0);
        numTimesPlayedHexagonBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXAGON_BATTLE, 0);
        numTimesPlayedSeptagonBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SEPTAGON_BATTLE, 0);
        numTimesPlayedOctagonBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_OCTAGON_BATTLE, 0);
        numTimesPlayedNonagonBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONAGON_BATTLE, 0);
        numTimesPlayedPointillismBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_POINTILLISM_BATTLE, 0);
        numTimesPlayedLineageBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_LINEAGE_BATTLE, 0);
        numTimesPlayedTriTheWaltzBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_BATTLE, 0);
        numTimesPlayedSquaredOffBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_BATTLE, 0);
        numTimesPlayedPentUpBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENT_UP_BATTLE, 0);
        numTimesPlayedHexidecibelBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_BATTLE, 0);
        numTimesPlayedInterseptorBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_BATTLE, 0);
        numTimesPlayedRoctopusBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_BATTLE, 0);
        numTimesPlayedNonplussedBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_BATTLE, 0);
        numTimesPlayedEasyBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_EASY_BATTLE, 0);
        numTimesPlayedMediumBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_MEDIUM_BATTLE, 0);
        numTimesPlayedHardBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HARD_BATTLE, 0);
        favoriteBaseBattle = game.save.getInteger(SAVE_FAVORITE_BASE_BATTLE, 0);
        favoriteDifficultyBattle = game.save.getString(SAVE_FAVORITE_DIFFICULTY_BATTLE, NA);
        favoriteTrackBattle = game.save.getString(SAVE_FAVORITE_TRACK_BATTLE, NA);

        //Time Attack
        timePlayedTimeAttack = game.save.getLong(SAVE_TIME_PLAYED_TIME_ATTACK, 0);
        numTimesPlayedTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TIME_ATTACK, 0);
        highestScoreTimeAttack = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK, 0);
        numTimesPlayedSquareTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARE_TIME_ATTACK, 0);
        numTimesPlayedPentagonTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENTAGON_TIME_ATTACK, 0);
        numTimesPlayedHexagonTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXAGON_TIME_ATTACK, 0);
        numTimesPlayedSeptagonTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SEPTAGON_TIME_ATTACK, 0);
        numTimesPlayedOctagonTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_OCTAGON_TIME_ATTACK, 0);
        numTimesPlayedNonagonTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONAGON_TIME_ATTACK, 0);
        numTimesPlayedPointillismTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_POINTILLISM_TIME_ATTACK, 0);
        numTimesPlayedLineageTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_LINEAGE_TIME_ATTACK, 0);
        numTimesPlayedTriTheWaltzTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TIME_ATTACK, 0);
        numTimesPlayedSquaredOffTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TIME_ATTACK, 0);
        numTimesPlayedPentUpTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENT_UP_TIME_ATTACK, 0);
        numTimesPlayedHexidecibelTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TIME_ATTACK, 0);
        numTimesPlayedInterseptorTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TIME_ATTACK, 0);
        numTimesPlayedRoctopusTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TIME_ATTACK, 0);
        numTimesPlayedNonplussedTimeAttack = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TIME_ATTACK, 0);
        favoriteBaseTimeAttack = game.save.getInteger(SAVE_FAVORITE_BASE_TIME_ATTACK, 0);
        favoriteGameLengthTimeAttack = game.save.getInteger(SAVE_FAVORITE_GAME_LENGTH_TIME_ATTACK, 0);
        favoriteTrackTimeAttack = game.save.getString(SAVE_FAVORITE_TRACK_TIME_ATTACK, NA);

        //Time Battle
        timePlayedTimeBattle = game.save.getLong(SAVE_TIME_PLAYED_TIME_BATTLE, 0);
        numTimesPlayedTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TIME_BATTLE, 0);
        highestScoreTimeBattle = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE, 0);
        numTimesWonTimeBattle = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE, 0);
        numTimesLostTimeBattle = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE, 0);
        numTimesPlayedSquareTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARE_TIME_BATTLE, 0);
        numTimesPlayedPentagonTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENTAGON_TIME_BATTLE, 0);
        numTimesPlayedHexagonTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXAGON_TIME_BATTLE, 0);
        numTimesPlayedSeptagonTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SEPTAGON_TIME_BATTLE, 0);
        numTimesPlayedOctagonTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_OCTAGON_TIME_BATTLE, 0);
        numTimesPlayedNonagonTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONAGON_TIME_BATTLE, 0);
        numTimesPlayedPointillismTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_POINTILLISM_TIME_BATTLE, 0);
        numTimesPlayedLineageTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_LINEAGE_TIME_BATTLE, 0);
        numTimesPlayedTriTheWaltzTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TIME_BATTLE, 0);
        numTimesPlayedSquaredOffTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TIME_BATTLE, 0);
        numTimesPlayedPentUpTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENT_UP_TIME_BATTLE, 0);
        numTimesPlayedHexidecibelTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TIME_BATTLE, 0);
        numTimesPlayedInterseptorTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TIME_BATTLE, 0);
        numTimesPlayedRoctopusTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TIME_BATTLE, 0);
        numTimesPlayedNonplussedTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TIME_BATTLE, 0);
        numTimesPlayedEasyTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_EASY_TIME_BATTLE, 0);
        numTimesPlayedMediumTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_MEDIUM_TIME_BATTLE, 0);
        numTimesPlayedHardTimeBattle = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HARD_TIME_BATTLE, 0);
        favoriteBaseTimeBattle = game.save.getInteger(SAVE_FAVORITE_BASE_TIME_BATTLE, 0);
        favoriteGameLengthTimeBattle = game.save.getInteger(SAVE_FAVORITE_GAME_LENGTH_TIME_BATTLE, 0);
        favoriteDifficultyTimeBattle = game.save.getString(SAVE_FAVORITE_DIFFICULTY_TIME_BATTLE, NA);
        favoriteTrackTimeBattle = game.save.getString(SAVE_FAVORITE_TRACK_TIME_BATTLE, NA);

        //Trance
        timePlayedTrance = game.save.getLong(SAVE_TIME_PLAYED_TRANCE, 0);
        numTimesPlayedTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TRANCE, 0);
        longestRunTrance = game.save.getLong(SAVE_LONGEST_RUN_TRANCE, 0);
        numTimesPlayedPointillismTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_POINTILLISM_TRANCE, 0);
        numTimesPlayedLineageTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_LINEAGE_TRANCE, 0);
        numTimesPlayedTriTheWaltzTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TRANCE, 0);
        numTimesPlayedSquaredOffTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TRANCE, 0);
        numTimesPlayedPentUpTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_PENT_UP_TRANCE, 0);
        numTimesPlayedHexidecibelTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TRANCE, 0);
        numTimesPlayedInterseptorTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TRANCE, 0);
        numTimesPlayedRoctopusTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TRANCE, 0);
        numTimesPlayedNonplussedTrance = game.save.getLong(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TRANCE, 0);
        favoriteTrackTrance = game.save.getString(SAVE_FAVORITE_TRACK_TRANCE, NA);
    }



    public void updateTimePlayed(long amountToAdd, int gameplayType) {
        if (gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            timePlayedSquirgle += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_SQUIRGLE, timePlayedSquirgle);
        } else if (gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            timePlayedBattle += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_BATTLE, timePlayedBattle);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            timePlayedTimeAttack += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_TIME_ATTACK, timePlayedTimeAttack);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            timePlayedTimeBattle += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_TIME_BATTLE, timePlayedTimeBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            timePlayedTrance += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_TRANCE, timePlayedTrance);
        }
        timePlayed = timePlayedSquirgle + timePlayedBattle + timePlayedTimeAttack + timePlayedTimeBattle + timePlayedTrance;
        game.updateSave(SAVE_TIME_PLAYED, timePlayed);
    }

    public void incrementNumTimesPlayed(int gameplayType) {
        if (gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numTimesPlayedSquirgle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUIRGLE, numTimesPlayedSquirgle);
        } else if (gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numTimesPlayedBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_BATTLE, numTimesPlayedBattle);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numTimesPlayedTimeAttack++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_ATTACK, numTimesPlayedTimeAttack);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            numTimesPlayedTimeBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_BATTLE, numTimesPlayedTimeBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            numTimesPlayedTrance++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TRANCE, numTimesPlayedTrance);
        }
    }

    public void incrementNumSquirgles(int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numSquirglesSquirgle++;
            game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE, numSquirglesSquirgle);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numSquirglesBattle++;
            game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE, numSquirglesBattle);
        }
        numSquirgles = numSquirglesSquirgle + numSquirglesBattle;
        game.updateSave(SAVE_NUM_SQUIRGLES, numSquirgles);
    }

    public void updateLongestRun(long newAmount, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(newAmount < longestRunSquirgle || longestRunSquirgle == 0) {
                longestRunSquirgle = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE, longestRunSquirgle);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            if(newAmount < longestRunTrance || longestRunTrance == 0) {
                longestRunTrance = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_TRANCE, longestRunTrance);
            }
        }
    }

    public void updateFastestVictory(long newAmount) {
        if(newAmount < fastestVictory || fastestVictory == 0) {
            fastestVictory = newAmount;
            game.updateSave(SAVE_FASTEST_VICTORY, fastestVictory);
        }
    }

    public void updateHighestScore(long newScore, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(newScore > highestScoreSquirgle) {
                highestScoreSquirgle = newScore;
                game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE, highestScoreSquirgle);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(newScore > highestScoreTimeAttack) {
                highestScoreTimeAttack = newScore;
                game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK, highestScoreTimeAttack);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            if(newScore > highestScoreTimeBattle) {
                highestScoreTimeBattle = newScore;
                game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE, highestScoreTimeBattle);
            }
        }
    }

    public void incrementNumTimesWonOrLost(boolean won, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            if(won) {
                numTimesWonBattle++;
                game.updateSave(SAVE_NUM_TIMES_WON_BATTLE, numTimesWonBattle);
            } else {
                numTimesLostBattle++;
                game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE, numTimesLostBattle);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(won) {
                numTimesWonTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE, numTimesWonTimeBattle);
            } else {
                numTimesLostTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE, numTimesLostTimeBattle);
            }
        }
    }

    public void incrementNumTimesPlayedDifficulty(String difficulty, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            if(difficulty == Squirgle.DIFFICULTY_EASY) {
                numTimesPlayedEasyBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_EASY_BATTLE, numTimesPlayedEasyBattle);
            } else if(difficulty == Squirgle.DIFFICULTY_MEDIUM) {
                numTimesPlayedMediumBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_MEDIUM_BATTLE, numTimesPlayedMediumBattle);
            } else if(difficulty == Squirgle.DIFFICULTY_HARD) {
                numTimesPlayedHardBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_HARD_BATTLE, numTimesPlayedHardBattle);
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedEasyBattle,
                    numTimesPlayedMediumBattle,
                    numTimesPlayedHardBattle));
            if(numTimesPlayedEasyBattle == largestNumber) {
                favoriteDifficultyBattle = Squirgle.DIFFICULTY_EASY;
            } else if(numTimesPlayedMediumBattle == largestNumber) {
                favoriteDifficultyBattle = Squirgle.DIFFICULTY_MEDIUM;
            } else if(numTimesPlayedHardBattle == largestNumber) {
                favoriteDifficultyBattle = Squirgle.DIFFICULTY_HARD;
            }
            game.updateSave(SAVE_FAVORITE_DIFFICULTY_BATTLE, favoriteDifficultyBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(difficulty == Squirgle.DIFFICULTY_EASY) {
                numTimesPlayedEasyTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_EASY_TIME_BATTLE, numTimesPlayedEasyTimeBattle);
            } else if(difficulty == Squirgle.DIFFICULTY_MEDIUM) {
                numTimesPlayedMediumTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_MEDIUM_TIME_BATTLE, numTimesPlayedMediumTimeBattle);
            } else if(difficulty == Squirgle.DIFFICULTY_HARD) {
                numTimesPlayedHardTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_HARD_TIME_BATTLE, numTimesPlayedHardTimeBattle);
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedEasyTimeBattle,
                    numTimesPlayedMediumTimeBattle,
                    numTimesPlayedHardTimeBattle));
            if(numTimesPlayedEasyTimeBattle == largestNumber) {
                favoriteDifficultyTimeBattle = Squirgle.DIFFICULTY_EASY;
            } else if(numTimesPlayedMediumTimeBattle == largestNumber) {
                favoriteDifficultyTimeBattle = Squirgle.DIFFICULTY_MEDIUM;
            } else if(numTimesPlayedHardTimeBattle == largestNumber) {
                favoriteDifficultyTimeBattle = Squirgle.DIFFICULTY_HARD;
            }
            game.updateSave(SAVE_FAVORITE_DIFFICULTY_TIME_BATTLE, favoriteDifficultyTimeBattle);
        }
    }

    public void incrementNumTimesPlayedGameLength(int numSeconds, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(numSeconds == Squirgle.ONE_MINUTE) {
                numTimesPlayedOneMinuteTimeAttack++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_ONE_MINUTE_TIME_ATTACK, numTimesPlayedOneMinuteTimeAttack);
            } else if(numSeconds == Squirgle.THREE_MINUTES) {
                numTimesPlayedThreeMinutesTimeAttack++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_THREE_MINUTES_TIME_ATTACK, numTimesPlayedThreeMinutesTimeAttack);
            } else if(numSeconds == Squirgle.FIVE_MINUTES) {
                numTimesPlayedFiveMinutesTimeAttack++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_FIVE_MINUTES_TIME_ATTACK, numTimesPlayedFiveMinutesTimeAttack);
            } else if(numSeconds == Squirgle.TEN_MINUTES) {
                numTimesPlayedTenMinutesTimeAttack++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_TEN_MINUTES_TIME_ATTACK, numTimesPlayedTenMinutesTimeAttack);
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedOneMinuteTimeAttack,
                    numTimesPlayedThreeMinutesTimeAttack,
                    numTimesPlayedFiveMinutesTimeAttack,
                    numTimesPlayedTenMinutesTimeAttack));
            if(numTimesPlayedOneMinuteTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.ONE_MINUTE;
            } else if(numTimesPlayedThreeMinutesTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.THREE_MINUTES;
            } else if(numTimesPlayedFiveMinutesTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.FIVE_MINUTES;
            } else if(numTimesPlayedTenMinutesTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.TEN_MINUTES;
            }
            game.updateSave(SAVE_FAVORITE_GAME_LENGTH_TIME_ATTACK, favoriteGameLengthTimeAttack);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(numSeconds == Squirgle.ONE_MINUTE) {
                numTimesPlayedOneMinuteTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_ONE_MINUTE_TIME_BATTLE, numTimesPlayedOneMinuteTimeBattle);
            } else if(numSeconds == Squirgle.THREE_MINUTES) {
                numTimesPlayedThreeMinutesTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_THREE_MINUTES_TIME_BATTLE, numTimesPlayedThreeMinutesTimeBattle);
            } else if(numSeconds == Squirgle.FIVE_MINUTES) {
                numTimesPlayedFiveMinutesTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_FIVE_MINUTES_TIME_BATTLE, numTimesPlayedFiveMinutesTimeBattle);
            } else if(numSeconds == Squirgle.TEN_MINUTES) {
                numTimesPlayedTenMinutesTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_TEN_MINUTES_TIME_BATTLE, numTimesPlayedTenMinutesTimeBattle);
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedOneMinuteTimeBattle,
                    numTimesPlayedThreeMinutesTimeBattle,
                    numTimesPlayedFiveMinutesTimeBattle,
                    numTimesPlayedTenMinutesTimeBattle));
            if(numTimesPlayedOneMinuteTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.ONE_MINUTE;
            } else if(numTimesPlayedThreeMinutesTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.THREE_MINUTES;
            } else if(numTimesPlayedFiveMinutesTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.FIVE_MINUTES;
            } else if(numTimesPlayedTenMinutesTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.TEN_MINUTES;
            }
            game.updateSave(SAVE_FAVORITE_GAME_LENGTH_TIME_BATTLE, favoriteGameLengthTimeBattle);
        }
    }

    public void incrementNumTimesPlayedMode(int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numTimesPlayedSquirgle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUIRGLE, numTimesPlayedSquirgle);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numTimesPlayedBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_BATTLE, numTimesPlayedBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numTimesPlayedTimeAttack++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_ATTACK, numTimesPlayedTimeAttack);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            numTimesPlayedTimeBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_BATTLE, numTimesPlayedTimeBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            numTimesPlayedTrance++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TRANCE, numTimesPlayedTrance);
        }
        long largestNumber = Collections.max(Arrays.asList(numTimesPlayedSquirgle,
                numTimesPlayedBattle,
                numTimesPlayedTimeAttack,
                numTimesPlayedTimeBattle,
                numTimesPlayedTrance));
        if(numTimesPlayedSquirgle == largestNumber) {
            favoriteMode = MODE_SQUIRGLE;
        } else if(numTimesPlayedBattle == largestNumber) {
            favoriteMode = MODE_BATTLE;
        } else if(numTimesPlayedTimeAttack == largestNumber) {
            favoriteMode = MODE_TIME_ATTACK;
        } else if(numTimesPlayedTimeBattle == largestNumber) {
            favoriteMode = MODE_TIME_BATTLE;
        } else if(numTimesPlayedTrance == largestNumber) {
            favoriteMode = MODE_TRANCE;
        }
        game.updateSave(SAVE_FAVORITE_MODE, favoriteMode);
    }

    public void incrementNumTimesPlayedBaseOrTrack(boolean base, int baseOrTrack, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARE_SQUIRGLE, numTimesPlayedSquareSquirgle);
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENTAGON_SQUIRGLE, numTimesPlayedPentagonSquirgle);
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXAGON_SQUIRGLE, numTimesPlayedHexagonSquirgle);
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SEPTAGON_SQUIRGLE, numTimesPlayedSeptagonSquirgle);
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_OCTAGON_SQUIRGLE, numTimesPlayedOctagonSquirgle);
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONAGON_SQUIRGLE, numTimesPlayedNonagonSquirgle);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedSquareSquirgle,
                        numTimesPlayedPentagonSquirgle,
                        numTimesPlayedHexagonSquirgle,
                        numTimesPlayedSeptagonSquirgle,
                        numTimesPlayedOctagonSquirgle,
                        numTimesPlayedNonagonSquirgle));
                if (numTimesPlayedSquareSquirgle == largestNumber) {
                    favoriteBaseSquirgle = Shape.SQUARE;
                } else if (numTimesPlayedPentagonSquirgle == largestNumber) {
                    favoriteBaseSquirgle = Shape.PENTAGON;
                } else if (numTimesPlayedHexagonSquirgle == largestNumber) {
                    favoriteBaseSquirgle = Shape.HEXAGON;
                } else if (numTimesPlayedSeptagonSquirgle == largestNumber) {
                    favoriteBaseSquirgle = Shape.SEPTAGON;
                } else if (numTimesPlayedOctagonSquirgle == largestNumber) {
                    favoriteBaseSquirgle = Shape.OCTAGON;
                } else if (numTimesPlayedNonagonSquirgle == largestNumber) {
                    favoriteBaseSquirgle = Shape.NONAGON;
                }
                game.updateSave(SAVE_FAVORITE_BASE_SQUIRGLE, favoriteBaseSquirgle);
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_POINTILLISM_SQUIRGLE, numTimesPlayedPointillismSquirgle);
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_LINEAGE_SQUIRGLE, numTimesPlayedLineageSquirgle);
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_SQUIRGLE, numTimesPlayedTriTheWaltzSquirgle);
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_SQUIRGLE, numTimesPlayedSquaredOffSquirgle);
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENT_UP_SQUIRGLE, numTimesPlayedPentUpSquirgle);
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_SQUIRGLE, numTimesPlayedHexidecibelSquirgle);
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_SQUIRGLE, numTimesPlayedInterseptorSquirgle);
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_SQUIRGLE, numTimesPlayedRoctopusSquirgle);
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedSquirgle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_SQUIRGLE, numTimesPlayedNonplussedSquirgle);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedPointillismSquirgle,
                        numTimesPlayedLineageSquirgle,
                        numTimesPlayedTriTheWaltzSquirgle,
                        numTimesPlayedSquaredOffSquirgle,
                        numTimesPlayedPentUpSquirgle,
                        numTimesPlayedHexidecibelSquirgle,
                        numTimesPlayedInterseptorSquirgle,
                        numTimesPlayedRoctopusSquirgle,
                        numTimesPlayedNonplussedSquirgle));
                if (numTimesPlayedPointillismSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_POINTILLISM;
                } else if (numTimesPlayedLineageSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_LINEAGE;
                } else if (numTimesPlayedTriTheWaltzSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_TRI_THE_WALTZ;
                } else if (numTimesPlayedSquaredOffSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_SQUARED_OFF;
                } else if (numTimesPlayedPentUpSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_PENT_UP;
                } else if (numTimesPlayedHexidecibelSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_HEXIDECIBEL;
                } else if (numTimesPlayedInterseptorSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_INTERSEPTOR;
                } else if (numTimesPlayedRoctopusSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_ROCTOPUS;
                } else if (numTimesPlayedNonplussedSquirgle == largestNumber) {
                    favoriteTrackSquirgle = Squirgle.MUSIC_TITLE_NONPLUSSED;
                }
                game.updateSave(SAVE_FAVORITE_TRACK_SQUIRGLE, favoriteTrackSquirgle);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARE_BATTLE, numTimesPlayedSquareBattle);
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENTAGON_BATTLE, numTimesPlayedPentagonBattle);
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXAGON_BATTLE, numTimesPlayedHexagonBattle);
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SEPTAGON_BATTLE, numTimesPlayedSeptagonBattle);
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_OCTAGON_BATTLE, numTimesPlayedOctagonBattle);
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONAGON_BATTLE, numTimesPlayedNonagonBattle);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedSquareBattle,
                        numTimesPlayedPentagonBattle,
                        numTimesPlayedHexagonBattle,
                        numTimesPlayedSeptagonBattle,
                        numTimesPlayedOctagonBattle,
                        numTimesPlayedNonagonBattle));
                if (numTimesPlayedSquareBattle == largestNumber) {
                    favoriteBaseBattle = Shape.SQUARE;
                } else if (numTimesPlayedPentagonBattle == largestNumber) {
                    favoriteBaseBattle = Shape.PENTAGON;
                } else if (numTimesPlayedHexagonBattle == largestNumber) {
                    favoriteBaseBattle = Shape.HEXAGON;
                } else if (numTimesPlayedSeptagonBattle == largestNumber) {
                    favoriteBaseBattle = Shape.SEPTAGON;
                } else if (numTimesPlayedOctagonBattle == largestNumber) {
                    favoriteBaseBattle = Shape.OCTAGON;
                } else if (numTimesPlayedNonagonBattle == largestNumber) {
                    favoriteBaseBattle = Shape.NONAGON;
                }
                game.updateSave(SAVE_FAVORITE_BASE_BATTLE, favoriteBaseBattle);
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_POINTILLISM_BATTLE, numTimesPlayedPointillismBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_LINEAGE_BATTLE, numTimesPlayedLineageBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_BATTLE, numTimesPlayedTriTheWaltzBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_BATTLE, numTimesPlayedSquaredOffBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENT_UP_BATTLE, numTimesPlayedPentUpBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_BATTLE, numTimesPlayedHexidecibelBattle);
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_BATTLE, numTimesPlayedInterseptorBattle);
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_BATTLE, numTimesPlayedRoctopusBattle);
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_BATTLE, numTimesPlayedNonplussedBattle);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedPointillismBattle,
                        numTimesPlayedLineageBattle,
                        numTimesPlayedTriTheWaltzBattle,
                        numTimesPlayedSquaredOffBattle,
                        numTimesPlayedPentUpBattle,
                        numTimesPlayedHexidecibelBattle,
                        numTimesPlayedInterseptorBattle,
                        numTimesPlayedRoctopusBattle,
                        numTimesPlayedNonplussedBattle));
                if (numTimesPlayedPointillismBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_POINTILLISM;
                } else if (numTimesPlayedLineageBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_LINEAGE;
                } else if (numTimesPlayedTriTheWaltzBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_TRI_THE_WALTZ;
                } else if (numTimesPlayedSquaredOffBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_SQUARED_OFF;
                } else if (numTimesPlayedPentUpBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_PENT_UP;
                } else if (numTimesPlayedHexidecibelBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_HEXIDECIBEL;
                } else if (numTimesPlayedInterseptorBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_INTERSEPTOR;
                } else if (numTimesPlayedRoctopusBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_ROCTOPUS;
                } else if (numTimesPlayedNonplussedBattle == largestNumber) {
                    favoriteTrackBattle = Squirgle.MUSIC_TITLE_NONPLUSSED;
                }
                game.updateSave(SAVE_FAVORITE_TRACK_BATTLE, favoriteTrackBattle);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARE_TIME_ATTACK, numTimesPlayedSquareTimeAttack);
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENTAGON_TIME_ATTACK, numTimesPlayedPentagonTimeAttack);
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXAGON_TIME_ATTACK, numTimesPlayedHexagonTimeAttack);
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SEPTAGON_TIME_ATTACK, numTimesPlayedSeptagonTimeAttack);
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_OCTAGON_TIME_ATTACK, numTimesPlayedOctagonTimeAttack);
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONAGON_TIME_ATTACK, numTimesPlayedNonagonTimeAttack);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedSquareTimeAttack,
                        numTimesPlayedPentagonTimeAttack,
                        numTimesPlayedHexagonTimeAttack,
                        numTimesPlayedSeptagonTimeAttack,
                        numTimesPlayedOctagonTimeAttack,
                        numTimesPlayedNonagonTimeAttack));
                if (numTimesPlayedSquareTimeAttack == largestNumber) {
                    favoriteBaseTimeAttack = Shape.SQUARE;
                } else if (numTimesPlayedPentagonTimeAttack == largestNumber) {
                    favoriteBaseTimeAttack = Shape.PENTAGON;
                } else if (numTimesPlayedHexagonTimeAttack == largestNumber) {
                    favoriteBaseTimeAttack = Shape.HEXAGON;
                } else if (numTimesPlayedSeptagonTimeAttack == largestNumber) {
                    favoriteBaseTimeAttack = Shape.SEPTAGON;
                } else if (numTimesPlayedOctagonTimeAttack == largestNumber) {
                    favoriteBaseTimeAttack = Shape.OCTAGON;
                } else if (numTimesPlayedNonagonTimeAttack == largestNumber) {
                    favoriteBaseTimeAttack = Shape.NONAGON;
                }
                game.updateSave(SAVE_FAVORITE_BASE_TIME_ATTACK, favoriteBaseTimeAttack);
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_POINTILLISM_TIME_ATTACK, numTimesPlayedPointillismTimeAttack);
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_LINEAGE_TIME_ATTACK, numTimesPlayedLineageTimeAttack);
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TIME_ATTACK, numTimesPlayedTriTheWaltzTimeAttack);
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TIME_ATTACK, numTimesPlayedSquaredOffTimeAttack);
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENT_UP_TIME_ATTACK, numTimesPlayedPentUpTimeAttack);
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TIME_ATTACK, numTimesPlayedHexidecibelTimeAttack);
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TIME_ATTACK, numTimesPlayedInterseptorTimeAttack);
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TIME_ATTACK, numTimesPlayedRoctopusTimeAttack);
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedTimeAttack++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TIME_ATTACK, numTimesPlayedNonplussedTimeAttack);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedPointillismTimeAttack,
                        numTimesPlayedLineageTimeAttack,
                        numTimesPlayedTriTheWaltzTimeAttack,
                        numTimesPlayedSquaredOffTimeAttack,
                        numTimesPlayedPentUpTimeAttack,
                        numTimesPlayedHexidecibelTimeAttack,
                        numTimesPlayedInterseptorTimeAttack,
                        numTimesPlayedRoctopusTimeAttack,
                        numTimesPlayedNonplussedTimeAttack));
                if (numTimesPlayedPointillismTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_POINTILLISM;
                } else if (numTimesPlayedLineageTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_LINEAGE;
                } else if (numTimesPlayedTriTheWaltzTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_TRI_THE_WALTZ;
                } else if (numTimesPlayedSquaredOffTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_SQUARED_OFF;
                } else if (numTimesPlayedPentUpTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_PENT_UP;
                } else if (numTimesPlayedHexidecibelTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_HEXIDECIBEL;
                } else if (numTimesPlayedInterseptorTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_INTERSEPTOR;
                } else if (numTimesPlayedRoctopusTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_ROCTOPUS;
                } else if (numTimesPlayedNonplussedTimeAttack == largestNumber) {
                    favoriteTrackTimeAttack = Squirgle.MUSIC_TITLE_NONPLUSSED;
                }
                game.updateSave(SAVE_FAVORITE_TRACK_TIME_ATTACK, favoriteTrackTimeAttack);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARE_TIME_BATTLE, numTimesPlayedSquareTimeBattle);
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENTAGON_TIME_BATTLE, numTimesPlayedPentagonTimeBattle);
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXAGON_TIME_BATTLE, numTimesPlayedHexagonTimeBattle);
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SEPTAGON_TIME_BATTLE, numTimesPlayedSeptagonTimeBattle);
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_OCTAGON_TIME_BATTLE, numTimesPlayedOctagonTimeBattle);
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONAGON_TIME_BATTLE, numTimesPlayedNonagonTimeBattle);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedSquareTimeBattle,
                        numTimesPlayedPentagonTimeBattle,
                        numTimesPlayedHexagonTimeBattle,
                        numTimesPlayedSeptagonTimeBattle,
                        numTimesPlayedOctagonTimeBattle,
                        numTimesPlayedNonagonTimeBattle));
                if (numTimesPlayedSquareTimeBattle == largestNumber) {
                    favoriteBaseTimeBattle = Shape.SQUARE;
                } else if (numTimesPlayedPentagonTimeBattle == largestNumber) {
                    favoriteBaseTimeBattle = Shape.PENTAGON;
                } else if (numTimesPlayedHexagonTimeBattle == largestNumber) {
                    favoriteBaseTimeBattle = Shape.HEXAGON;
                } else if (numTimesPlayedSeptagonTimeBattle == largestNumber) {
                    favoriteBaseTimeBattle = Shape.SEPTAGON;
                } else if (numTimesPlayedOctagonTimeBattle == largestNumber) {
                    favoriteBaseTimeBattle = Shape.OCTAGON;
                } else if (numTimesPlayedNonagonTimeBattle == largestNumber) {
                    favoriteBaseTimeBattle = Shape.NONAGON;
                }
                game.updateSave(SAVE_FAVORITE_BASE_TIME_BATTLE, favoriteBaseTimeBattle);
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_POINTILLISM_TIME_BATTLE, numTimesPlayedPointillismTimeBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_LINEAGE_TIME_BATTLE, numTimesPlayedLineageTimeBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TIME_BATTLE, numTimesPlayedTriTheWaltzTimeBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TIME_BATTLE, numTimesPlayedSquaredOffTimeBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_PENT_UP_TIME_BATTLE, numTimesPlayedPentUpTimeBattle);
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TIME_BATTLE, numTimesPlayedHexidecibelTimeBattle);
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TIME_BATTLE, numTimesPlayedInterseptorTimeBattle);
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TIME_BATTLE, numTimesPlayedRoctopusTimeBattle);
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedTimeBattle++;
                    game.updateSave(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TIME_BATTLE, numTimesPlayedNonplussedTimeBattle);
                }
                long largestNumber = Collections.max(Arrays.asList(numTimesPlayedPointillismTimeBattle,
                        numTimesPlayedLineageTimeBattle,
                        numTimesPlayedTriTheWaltzTimeBattle,
                        numTimesPlayedSquaredOffTimeBattle,
                        numTimesPlayedPentUpTimeBattle,
                        numTimesPlayedHexidecibelTimeBattle,
                        numTimesPlayedInterseptorTimeBattle,
                        numTimesPlayedRoctopusTimeBattle,
                        numTimesPlayedNonplussedTimeBattle));
                if (numTimesPlayedPointillismTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_POINTILLISM;
                } else if (numTimesPlayedLineageTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_LINEAGE;
                } else if (numTimesPlayedTriTheWaltzTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_TRI_THE_WALTZ;
                } else if (numTimesPlayedSquaredOffTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_SQUARED_OFF;
                } else if (numTimesPlayedPentUpTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_PENT_UP;
                } else if (numTimesPlayedHexidecibelTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_HEXIDECIBEL;
                } else if (numTimesPlayedInterseptorTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_INTERSEPTOR;
                } else if (numTimesPlayedRoctopusTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_ROCTOPUS;
                } else if (numTimesPlayedNonplussedTimeBattle == largestNumber) {
                    favoriteTrackTimeBattle = Squirgle.MUSIC_TITLE_NONPLUSSED;
                }
                game.updateSave(SAVE_FAVORITE_TRACK_TIME_BATTLE, favoriteTrackTimeBattle);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                numTimesPlayedPointillismTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_POINTILLISM_TRANCE, numTimesPlayedPointillismTrance);
            } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                numTimesPlayedLineageTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_LINEAGE_TRANCE, numTimesPlayedLineageTrance);
            } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                numTimesPlayedTriTheWaltzTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ_TRANCE, numTimesPlayedTriTheWaltzTrance);
            } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                numTimesPlayedSquaredOffTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF_TRANCE, numTimesPlayedSquaredOffTrance);
            } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                numTimesPlayedPentUpTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_PENT_UP_TRANCE, numTimesPlayedPentUpTrance);
            } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                numTimesPlayedHexidecibelTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL_TRANCE, numTimesPlayedHexidecibelTrance);
            } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                numTimesPlayedInterseptorTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR_TRANCE, numTimesPlayedInterseptorTrance);
            } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                numTimesPlayedRoctopusTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_ROCTOPUS_TRANCE, numTimesPlayedRoctopusTrance);
            } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                numTimesPlayedNonplussedTrance++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_NONPLUSSED_TRANCE, numTimesPlayedNonplussedTrance);
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedPointillismTrance,
                    numTimesPlayedLineageTrance,
                    numTimesPlayedTriTheWaltzTrance,
                    numTimesPlayedSquaredOffTrance,
                    numTimesPlayedPentUpTrance,
                    numTimesPlayedHexidecibelTrance,
                    numTimesPlayedInterseptorTrance,
                    numTimesPlayedRoctopusTrance,
                    numTimesPlayedNonplussedTrance));
            if (numTimesPlayedPointillismTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_POINTILLISM;
            } else if (numTimesPlayedLineageTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_LINEAGE;
            } else if (numTimesPlayedTriTheWaltzTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_TRI_THE_WALTZ;
            } else if (numTimesPlayedSquaredOffTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_SQUARED_OFF;
            } else if (numTimesPlayedPentUpTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_PENT_UP;
            } else if (numTimesPlayedHexidecibelTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_HEXIDECIBEL;
            } else if (numTimesPlayedInterseptorTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_INTERSEPTOR;
            } else if (numTimesPlayedRoctopusTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_ROCTOPUS;
            } else if (numTimesPlayedNonplussedTrance == largestNumber) {
                favoriteTrackTrance = Squirgle.MUSIC_TITLE_NONPLUSSED;
            }
            game.updateSave(SAVE_FAVORITE_TRACK_TRANCE, favoriteTrackTrance);
        }
        if(base) {
            numTimesPlayedSquare = numTimesPlayedSquareSquirgle + numTimesPlayedSquareBattle + numTimesPlayedSquareTimeAttack + numTimesPlayedSquareTimeBattle;
            numTimesPlayedPentagon = numTimesPlayedPentagonSquirgle + numTimesPlayedPentagonBattle + numTimesPlayedPentagonTimeAttack + numTimesPlayedPentagonTimeBattle;
            numTimesPlayedHexagon = numTimesPlayedHexagonSquirgle + numTimesPlayedHexagonBattle + numTimesPlayedHexagonTimeAttack + numTimesPlayedHexagonTimeBattle;
            numTimesPlayedSeptagon = numTimesPlayedSeptagonSquirgle + numTimesPlayedSeptagonBattle + numTimesPlayedSeptagonTimeAttack + numTimesPlayedSeptagonTimeBattle;
            numTimesPlayedOctagon = numTimesPlayedOctagonSquirgle + numTimesPlayedOctagonBattle + numTimesPlayedOctagonTimeAttack + numTimesPlayedOctagonTimeBattle;
            numTimesPlayedNonagon = numTimesPlayedNonagonSquirgle + numTimesPlayedNonagonBattle + numTimesPlayedNonagonTimeAttack + numTimesPlayedNonagonTimeBattle;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARE, numTimesPlayedSquare);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_PENTAGON, numTimesPlayedPentagon);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXAGON, numTimesPlayedHexagon);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_SEPTAGON, numTimesPlayedSeptagon);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_OCTAGON, numTimesPlayedOctagon);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_NONAGON, numTimesPlayedNonagon);
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedSquare,
                    numTimesPlayedPentagon,
                    numTimesPlayedHexagon,
                    numTimesPlayedSeptagon,
                    numTimesPlayedOctagon,
                    numTimesPlayedNonagon));
            if (numTimesPlayedSquare == largestNumber) {
                favoriteBase = Shape.SQUARE;
            } else if (numTimesPlayedPentagon == largestNumber) {
                favoriteBase = Shape.PENTAGON;
            } else if (numTimesPlayedHexagon == largestNumber) {
                favoriteBase = Shape.HEXAGON;
            } else if (numTimesPlayedSeptagon == largestNumber) {
                favoriteBase = Shape.SEPTAGON;
            } else if (numTimesPlayedOctagon == largestNumber) {
                favoriteBase = Shape.OCTAGON;
            } else if (numTimesPlayedNonagon == largestNumber) {
                favoriteBase = Shape.NONAGON;
            }
            game.updateSave(SAVE_FAVORITE_BASE, favoriteBase);
        } else {
            numTimesPlayedPointillism = numTimesPlayedPointillismSquirgle + numTimesPlayedPointillismBattle + numTimesPlayedPointillismTimeAttack + numTimesPlayedPointillismTimeBattle + numTimesPlayedPointillismTrance;
            numTimesPlayedLineage = numTimesPlayedLineageSquirgle + numTimesPlayedLineageBattle + numTimesPlayedLineageTimeAttack + numTimesPlayedLineageTimeBattle + numTimesPlayedLineageTrance;
            numTimesPlayedTriTheWaltz = numTimesPlayedTriTheWaltzSquirgle + numTimesPlayedTriTheWaltzBattle + numTimesPlayedTriTheWaltzTimeAttack + numTimesPlayedTriTheWaltzTimeBattle + numTimesPlayedTriTheWaltzTrance;
            numTimesPlayedSquaredOff = numTimesPlayedSquaredOffSquirgle + numTimesPlayedSquaredOffBattle + numTimesPlayedSquaredOffTimeAttack + numTimesPlayedSquaredOffTimeBattle + numTimesPlayedSquaredOffTrance;
            numTimesPlayedPentUp = numTimesPlayedPentUpSquirgle + numTimesPlayedPentUpBattle + numTimesPlayedPentUpTimeAttack + numTimesPlayedPentUpTimeBattle + numTimesPlayedPentUpTrance;
            numTimesPlayedHexidecibel = numTimesPlayedHexidecibelSquirgle + numTimesPlayedHexidecibelBattle + numTimesPlayedHexidecibelTimeAttack + numTimesPlayedHexidecibelTimeBattle + numTimesPlayedHexidecibelTrance;
            numTimesPlayedInterseptor = numTimesPlayedInterseptorSquirgle + numTimesPlayedInterseptorBattle + numTimesPlayedInterseptorTimeAttack + numTimesPlayedInterseptorTimeBattle + numTimesPlayedInterseptorTrance;
            numTimesPlayedRoctopus = numTimesPlayedRoctopusSquirgle + numTimesPlayedRoctopusBattle + numTimesPlayedRoctopusTimeAttack + numTimesPlayedRoctopusTimeBattle + numTimesPlayedRoctopusTrance;
            numTimesPlayedNonplussed = numTimesPlayedNonplussedSquirgle + numTimesPlayedNonplussedBattle + numTimesPlayedNonplussedTimeAttack + numTimesPlayedNonplussedTimeBattle + numTimesPlayedNonplussedTrance;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_POINTILLISM, numTimesPlayedPointillism);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_LINEAGE, numTimesPlayedLineage);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TRI_THE_WALTZ, numTimesPlayedTriTheWaltz);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUARED_OFF, numTimesPlayedSquaredOff);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_PENT_UP, numTimesPlayedPentUp);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_HEXIDECIBEL, numTimesPlayedHexidecibel);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_INTERSEPTOR, numTimesPlayedInterseptor);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_ROCTOPUS, numTimesPlayedRoctopus);
            game.updateSave(SAVE_NUM_TIMES_PLAYED_NONPLUSSED, numTimesPlayedNonplussed);
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedPointillism,
                    numTimesPlayedLineage,
                    numTimesPlayedTriTheWaltz,
                    numTimesPlayedSquaredOff,
                    numTimesPlayedPentUp,
                    numTimesPlayedHexidecibel,
                    numTimesPlayedInterseptor,
                    numTimesPlayedRoctopus,
                    numTimesPlayedNonplussed));
            if (numTimesPlayedPointillism == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_POINTILLISM;
            } else if (numTimesPlayedLineage == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_LINEAGE;
            } else if (numTimesPlayedTriTheWaltz == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_TRI_THE_WALTZ;
            } else if (numTimesPlayedSquaredOff == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_SQUARED_OFF;
            } else if (numTimesPlayedPentUp == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_PENT_UP;
            } else if (numTimesPlayedHexidecibel == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_HEXIDECIBEL;
            } else if (numTimesPlayedInterseptor == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_INTERSEPTOR;
            } else if (numTimesPlayedRoctopus == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_ROCTOPUS;
            } else if (numTimesPlayedNonplussed == largestNumber) {
                favoriteTrack = Squirgle.MUSIC_TITLE_NONPLUSSED;
            }
            game.updateSave(SAVE_FAVORITE_TRACK, favoriteTrack);
        }
    }
}
