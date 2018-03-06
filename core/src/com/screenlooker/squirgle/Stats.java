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

    public static final String NA = "NA";

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
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE_SQUARE = "numSquirglesSquirgleSquare";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE_SQUARE = "longestRunSquirgleSquare";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE_SQUARE = "highestScoreSquirgleSquare";
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE_PENTAGON = "numSquirglesSquirglePentagon";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE_PENTAGON = "longestRunSquirglePentagon";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE_PENTAGON = "highestScoreSquirglePentagon";
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE_HEXAGON = "numSquirglesSquirgleHexagon";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE_HEXAGON = "longestRunSquirgleHexagon";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE_HEXAGON = "highestScoreSquirgleHexagon";
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE_SEPTAGON = "numSquirglesSquirgleSeptagon";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE_SEPTAGON = "longestRunSquirgleSeptagon";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE_SEPTAGON = "highestScoreSquirgleSeptagon";
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE_OCTAGON = "numSquirglesSquirgleOctagon";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE_OCTAGON = "longestRunSquirgleOctagon";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE_OCTAGON = "highestScoreSquirgleOctagon";
    public static final String SAVE_NUM_SQUIRGLES_SQUIRGLE_NONAGON = "numSquirglesSquirgleNonagon";
    public static final String SAVE_LONGEST_RUN_SQUIRGLE_NONAGON = "longestRunSquirgleNonagon";
    public static final String SAVE_HIGHEST_SCORE_SQUIRGLE_NONAGON = "highestScoreSquirgleNonagon";
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
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_EASY = "numSquirglesBattleSquareEasy";
    public static final String SAVE_FASTEST_VICTORY_SQUARE_EASY = "fastestVictorySquareEasy";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_SQUARE_EASY = "numTimesWonBattleSquareEasy";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_EASY = "numTimesLostBattleSquareEasy";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_EASY = "numSquirglesBattlePentagonEasy";
    public static final String SAVE_FASTEST_VICTORY_PENTAGON_EASY = "fastestVictoryPentagonEasy";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_EASY = "numTimesWonBattlePentagonEasy";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_EASY = "numTimesLostBattlePentagonEasy";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_EASY = "numSquirglesBattleHexagonEasy";
    public static final String SAVE_FASTEST_VICTORY_HEXAGON_EASY = "fastestVictoryHexagonEasy";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_EASY = "numTimesWonBattleHexagonEasy";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_EASY = "numTimesLostBattleHexagonEasy";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_EASY = "numSquirglesBattleSeptagonEasy";
    public static final String SAVE_FASTEST_VICTORY_SEPTAGON_EASY = "fastestVictorySeptagonEasy";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_EASY = "numTimesWonBattleSeptagonEasy";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_EASY = "numTimesLostBattleSeptagonEasy";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_EASY = "numSquirglesBattleOctagonEasy";
    public static final String SAVE_FASTEST_VICTORY_OCTAGON_EASY = "fastestVictoryOctagonEasy";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_EASY = "numTimesWonBattleOctagonEasy";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_EASY = "numTimesLostBattleOctagonEasy";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_EASY = "numSquirglesBattleNonagonEasy";
    public static final String SAVE_FASTEST_VICTORY_NONAGON_EASY = "fastestVictoryNonagonEasy";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_NONAGON_EASY = "numTimesWonBattleNonagonEasy";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_EASY = "numTimesLostBattleNonagonEasy";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_MEDIUM = "numSquirglesBattleSquareMedium";
    public static final String SAVE_FASTEST_VICTORY_SQUARE_MEDIUM = "fastestVictorySquareMedium";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_SQUARE_MEDIUM = "numTimesWonBattleSquareMedium";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_MEDIUM = "numTimesLostBattleSquareMedium";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_MEDIUM = "numSquirglesBattlePentagonMedium";
    public static final String SAVE_FASTEST_VICTORY_PENTAGON_MEDIUM = "fastestVictoryPentagonMedium";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_MEDIUM = "numTimesWonBattlePentagonMedium";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_MEDIUM = "numTimesLostBattlePentagonMedium";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_MEDIUM = "numSquirglesBattleHexagonMedium";
    public static final String SAVE_FASTEST_VICTORY_HEXAGON_MEDIUM = "fastestVictoryHexagonMedium";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_MEDIUM = "numTimesWonBattleHexagonMedium";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_MEDIUM = "numTimesLostBattleHexagonMedium";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_MEDIUM = "numSquirglesBattleSeptagonMedium";
    public static final String SAVE_FASTEST_VICTORY_SEPTAGON_MEDIUM = "fastestVictorySeptagonMedium";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_MEDIUM = "numTimesWonBattleSeptagonMedium";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_MEDIUM = "numTimesLostBattleSeptagonMedium";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_MEDIUM = "numSquirglesBattleOctagonMedium";
    public static final String SAVE_FASTEST_VICTORY_OCTAGON_MEDIUM = "fastestVictoryOctagonMedium";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_MEDIUM = "numTimesWonBattleOctagonMedium";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_MEDIUM = "numTimesLostBattleOctagonMedium";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_MEDIUM = "numSquirglesBattleNonagonMedium";
    public static final String SAVE_FASTEST_VICTORY_NONAGON_MEDIUM = "fastestVictoryNonagonMedium";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_NONAGON_MEDIUM = "numTimesWonBattleNonagonMedium";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_MEDIUM = "numTimesLostBattleNonagonMedium";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_HARD = "numSquirglesBattleSquareHard";
    public static final String SAVE_FASTEST_VICTORY_SQUARE_HARD = "fastestVictorySquareHard";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_SQUARE_HARD = "numTimesWonBattleSquareHard";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_HARD = "numTimesLostBattleSquareHard";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_HARD = "numSquirglesBattlePentagonHard";
    public static final String SAVE_FASTEST_VICTORY_PENTAGON_HARD = "fastestVictoryPentagonHard";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_HARD = "numTimesWonBattlePentagonHard";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_HARD = "numTimesLostBattlePentagonHard";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_HARD = "numSquirglesBattleHexagonHard";
    public static final String SAVE_FASTEST_VICTORY_HEXAGON_HARD = "fastestVictoryHexagonHard";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_HARD = "numTimesWonBattleHexagonHard";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_HARD = "numTimesLostBattleHexagonHard";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_HARD = "numSquirglesBattleSeptagonHard";
    public static final String SAVE_FASTEST_VICTORY_SEPTAGON_HARD = "fastestVictorySeptagonHard";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_HARD = "numTimesWonBattleSeptagonHard";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_HARD = "numTimesLostBattleSeptagonHard";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_HARD = "numSquirglesBattleOctagonHard";
    public static final String SAVE_FASTEST_VICTORY_OCTAGON_HARD = "fastestVictoryOctagonHard";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_HARD = "numTimesWonBattleOctagonHard";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_HARD = "numTimesLostBattleOctagonHard";
    public static final String SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_HARD = "numSquirglesBattleNonagonHard";
    public static final String SAVE_FASTEST_VICTORY_NONAGON_HARD = "fastestVictoryNonagonHard";
    public static final String SAVE_NUM_TIMES_WON_BATTLE_NONAGON_HARD = "numTimesWonBattleNonagonHard";
    public static final String SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_HARD = "numTimesLostBattleNonagonHard";
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
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_ONE_MINUTE = "highestScoreTimeAttackSquareOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_ONE_MINUTE = "highestScoreTimeAttackPentagonOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_ONE_MINUTE = "highestScoreTimeAttackHexagonOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_ONE_MINUTE = "highestScoreTimeAttackSeptagonOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_ONE_MINUTE = "highestScoreTimeAttackOctagonOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_ONE_MINUTE = "highestScoreTimeAttackNonagonOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_THREE_MINUTES = "highestScoreTimeAttackSquareThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_THREE_MINUTES = "highestScoreTimeAttackPentagonThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_THREE_MINUTES = "highestScoreTimeAttackHexagonThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_THREE_MINUTES = "highestScoreTimeAttackSeptagonThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_THREE_MINUTES = "highestScoreTimeAttackOctagonThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_THREE_MINUTES = "highestScoreTimeAttackNonagonThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_FIVE_MINUTES = "highestScoreTimeAttackSquareFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_FIVE_MINUTES = "highestScoreTimeAttackPentagonFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_FIVE_MINUTES = "highestScoreTimeAttackHexagonFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_FIVE_MINUTES = "highestScoreTimeAttackSeptagonFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_FIVE_MINUTES = "highestScoreTimeAttackOctagonFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_FIVE_MINUTES = "highestScoreTimeAttackNonagonFiveMinutes";
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
    public static final String SAVE_FAVORITE_BASE_TIME_ATTACK = "favoriteBaseTimeAttack";
    public static final String SAVE_FAVORITE_GAME_LENGTH_TIME_ATTACK = "favoriteGameLengthTimeAttack";
    public static final String SAVE_FAVORITE_TRACK_TIME_ATTACK = "favoriteTrackTimeAttack";

    public static final String SAVE_TIME_PLAYED_TIME_BATTLE = "timePlayedTimeBattle";
    public static final String SAVE_NUM_TIMES_PLAYED_TIME_BATTLE = "numTimesPlayedTimeBattle";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE = "highestScoreTimeBattleSquareEasyOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE = "numTimesWonTimeBattleSquareEasyOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE = "numTimesLostTimeBattleSquareEasyOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE = "highestScoreTimeBattlePentagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE = "numTimesWonTimeBattlePentagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE = "numTimesLostTimeBattlePentagonEasyOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE = "highestScoreTimeBattleHexagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE = "numTimesWonTimeBattleHexagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE = "numTimesLostTimeBattleHexagonEasyOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE = "highestScoreTimeBattleSeptagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE = "numTimesWonTimeBattleSeptagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE = "numTimesLostTimeBattleSeptagonEasyOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE = "highestScoreTimeBattleOctagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE = "numTimesWonTimeBattleOctagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE = "numTimesLostTimeBattleOctagonEasyOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE = "highestScoreTimeBattleNonagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE = "numTimesWonTimeBattleNonagonEasyOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE = "numTimesLostTimeBattleNonagonEasyOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE = "highestScoreTimeBattleSquareMediumOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE = "numTimesWonTimeBattleSquareMediumOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE = "numTimesLostTimeBattleSquareMediumOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE = "highestScoreTimeBattlePentagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE = "numTimesWonTimeBattlePentagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE = "numTimesLostTimeBattlePentagonMediumOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE = "highestScoreTimeBattleHexagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE = "numTimesWonTimeBattleHexagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE = "numTimesLostTimeBattleHexagonMediumOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE = "highestScoreTimeBattleSeptagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE = "numTimesWonTimeBattleSeptagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE = "numTimesLostTimeBattleSeptagonMediumOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE = "highestScoreTimeBattleOctagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE = "numTimesWonTimeBattleOctagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE = "numTimesLostTimeBattleOctagonMediumOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE = "highestScoreTimeBattleNonagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE = "numTimesWonTimeBattleNonagonMediumOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE = "numTimesLostTimeBattleNonagonMediumOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE = "highestScoreTimeBattleSquareHardOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE = "numTimesWonTimeBattleSquareHardOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE = "numTimesLostTimeBattleSquareHardOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE = "highestScoreTimeBattlePentagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE = "numTimesWonTimeBattlePentagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE = "numTimesLostTimeBattlePentagonHardOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE = "highestScoreTimeBattleHexagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE = "numTimesWonTimeBattleHexagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE = "numTimesLostTimeBattleHexagonHardOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE = "highestScoreTimeBattleSeptagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE = "numTimesWonTimeBattleSeptagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE = "numTimesLostTimeBattleSeptagonHardOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE = "highestScoreTimeBattleOctagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE = "numTimesWonTimeBattleOctagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE = "numTimesLostTimeBattleOctagonHardOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE = "highestScoreTimeBattleNonagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE = "numTimesWonTimeBattleNonagonHardOneMinute";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE = "numTimesLostTimeBattleNonagonHardOneMinute";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES = "highestScoreTimeBattleSquareEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES = "numTimesWonTimeBattleSquareEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES = "numTimesLostTimeBattleSquareEasyThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES = "highestScoreTimeBattlePentagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES = "numTimesWonTimeBattlePentagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES = "numTimesLostTimeBattlePentagonEasyThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES = "highestScoreTimeBattleHexagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES = "numTimesWonTimeBattleHexagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES = "numTimesLostTimeBattleHexagonEasyThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES = "highestScoreTimeBattleSeptagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES = "numTimesWonTimeBattleSeptagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES = "numTimesLostTimeBattleSeptagonEasyThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES = "highestScoreTimeBattleOctagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES = "numTimesWonTimeBattleOctagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES = "numTimesLostTimeBattleOctagonEasyThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES = "highestScoreTimeBattleNonagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES = "numTimesWonTimeBattleNonagonEasyThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES = "numTimesLostTimeBattleNonagonEasyThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES = "highestScoreTimeBattleSquareMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES = "numTimesWonTimeBattleSquareMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES = "numTimesLostTimeBattleSquareMediumThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES = "highestScoreTimeBattlePentagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES = "numTimesWonTimeBattlePentagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES = "numTimesLostTimeBattlePentagonMediumThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES = "highestScoreTimeBattleHexagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES = "numTimesWonTimeBattleHexagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES = "numTimesLostTimeBattleHexagonMediumThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES = "highestScoreTimeBattleSeptagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES = "numTimesWonTimeBattleSeptagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES = "numTimesLostTimeBattleSeptagonMediumThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES = "highestScoreTimeBattleOctagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES = "numTimesWonTimeBattleOctagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES = "numTimesLostTimeBattleOctagonMediumThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES = "highestScoreTimeBattleNonagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES = "numTimesWonTimeBattleNonagonMediumThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES = "numTimesLostTimeBattleNonagonMediumThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES = "highestScoreTimeBattleSquareHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES = "numTimesWonTimeBattleSquareHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES = "numTimesLostTimeBattleSquareHardThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES = "highestScoreTimeBattlePentagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES = "numTimesWonTimeBattlePentagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES = "numTimesLostTimeBattlePentagonHardThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES = "highestScoreTimeBattleHexagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES = "numTimesWonTimeBattleHexagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES = "numTimesLostTimeBattleHexagonHardThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES = "highestScoreTimeBattleSeptagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES = "numTimesWonTimeBattleSeptagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES = "numTimesLostTimeBattleSeptagonHardThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES = "highestScoreTimeBattleOctagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES = "numTimesWonTimeBattleOctagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES = "numTimesLostTimeBattleOctagonHardThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES = "highestScoreTimeBattleNonagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES = "numTimesWonTimeBattleNonagonHardThreeMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES = "numTimesLostTimeBattleNonagonHardThreeMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES = "highestScoreTimeBattleSquareEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES = "numTimesWonTimeBattleSquareEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES = "numTimesLostTimeBattleSquareEasyFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES = "highestScoreTimeBattlePentagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES = "numTimesWonTimeBattlePentagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES = "numTimesLostTimeBattlePentagonEasyFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES = "highestScoreTimeBattleHexagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES = "numTimesWonTimeBattleHexagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES = "numTimesLostTimeBattleHexagonEasyFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES = "highestScoreTimeBattleSeptagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES = "numTimesWonTimeBattleSeptagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES = "numTimesLostTimeBattleSeptagonEasyFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES = "highestScoreTimeBattleOctagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES = "numTimesWonTimeBattleOctagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES = "numTimesLostTimeBattleOctagonEasyFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES = "highestScoreTimeBattleNonagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES = "numTimesWonTimeBattleNonagonEasyFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES = "numTimesLostTimeBattleNonagonEasyFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES = "highestScoreTimeBattleSquareMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES = "numTimesWonTimeBattleSquareMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES = "numTimesLostTimeBattleSquareMediumFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES = "highestScoreTimeBattlePentagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES = "numTimesWonTimeBattlePentagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES = "numTimesLostTimeBattlePentagonMediumFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES = "highestScoreTimeBattleHexagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES = "numTimesWonTimeBattleHexagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES = "numTimesLostTimeBattleHexagonMediumFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES = "highestScoreTimeBattleSeptagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES = "numTimesWonTimeBattleSeptagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES = "numTimesLostTimeBattleSeptagonMediumFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES = "highestScoreTimeBattleOctagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES = "numTimesWonTimeBattleOctagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES = "numTimesLostTimeBattleOctagonMediumFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES = "highestScoreTimeBattleNonagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES = "numTimesWonTimeBattleNonagonMediumFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES = "numTimesLostTimeBattleNonagonMediumFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES = "highestScoreTimeBattleSquareHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES = "numTimesWonTimeBattleSquareHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES = "numTimesLostTimeBattleSquareHardFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES = "highestScoreTimeBattlePentagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES = "numTimesWonTimeBattlePentagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES = "numTimesLostTimeBattlePentagonHardFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES = "highestScoreTimeBattleHexagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES = "numTimesWonTimeBattleHexagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES = "numTimesLostTimeBattleHexagonHardFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES = "highestScoreTimeBattleSeptagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES = "numTimesWonTimeBattleSeptagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES = "numTimesLostTimeBattleSeptagonHardFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES = "highestScoreTimeBattleOctagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES = "numTimesWonTimeBattleOctagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES = "numTimesLostTimeBattleOctagonHardFiveMinutes";
    public static final String SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES = "highestScoreTimeBattleNonagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES = "numTimesWonTimeBattleNonagonHardFiveMinutes";
    public static final String SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES = "numTimesLostTimeBattleNonagonHardFiveMinutes";
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
    public long numSquirglesSquirgleSquare;
    public long longestRunSquirgleSquare;
    public long highestScoreSquirgleSquare;
    public long numSquirglesSquirglePentagon;
    public long longestRunSquirglePentagon;
    public long highestScoreSquirglePentagon;
    public long numSquirglesSquirgleHexagon;
    public long longestRunSquirgleHexagon;
    public long highestScoreSquirgleHexagon;
    public long numSquirglesSquirgleSeptagon;
    public long longestRunSquirgleSeptagon;
    public long highestScoreSquirgleSeptagon;
    public long numSquirglesSquirgleOctagon;
    public long longestRunSquirgleOctagon;
    public long highestScoreSquirgleOctagon;
    public long numSquirglesSquirgleNonagon;
    public long longestRunSquirgleNonagon;
    public long highestScoreSquirgleNonagon;
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
    public long numSquirglesBattleSquareEasy;
    public long fastestVictorySquareEasy;
    public long numTimesWonBattleSquareEasy;
    public long numTimesLostBattleSquareEasy;
    public long numSquirglesBattlePentagonEasy;
    public long fastestVictoryPentagonEasy;
    public long numTimesWonBattlePentagonEasy;
    public long numTimesLostBattlePentagonEasy;
    public long numSquirglesBattleHexagonEasy;
    public long fastestVictoryHexagonEasy;
    public long numTimesWonBattleHexagonEasy;
    public long numTimesLostBattleHexagonEasy;
    public long numSquirglesBattleSeptagonEasy;
    public long fastestVictorySeptagonEasy;
    public long numTimesWonBattleSeptagonEasy;
    public long numTimesLostBattleSeptagonEasy;
    public long numSquirglesBattleOctagonEasy;
    public long fastestVictoryOctagonEasy;
    public long numTimesWonBattleOctagonEasy;
    public long numTimesLostBattleOctagonEasy;
    public long numSquirglesBattleNonagonEasy;
    public long fastestVictoryNonagonEasy;
    public long numTimesWonBattleNonagonEasy;
    public long numTimesLostBattleNonagonEasy;
    public long numSquirglesBattleSquareMedium;
    public long fastestVictorySquareMedium;
    public long numTimesWonBattleSquareMedium;
    public long numTimesLostBattleSquareMedium;
    public long numSquirglesBattlePentagonMedium;
    public long fastestVictoryPentagonMedium;
    public long numTimesWonBattlePentagonMedium;
    public long numTimesLostBattlePentagonMedium;
    public long numSquirglesBattleHexagonMedium;
    public long fastestVictoryHexagonMedium;
    public long numTimesWonBattleHexagonMedium;
    public long numTimesLostBattleHexagonMedium;
    public long numSquirglesBattleSeptagonMedium;
    public long fastestVictorySeptagonMedium;
    public long numTimesWonBattleSeptagonMedium;
    public long numTimesLostBattleSeptagonMedium;
    public long numSquirglesBattleOctagonMedium;
    public long fastestVictoryOctagonMedium;
    public long numTimesWonBattleOctagonMedium;
    public long numTimesLostBattleOctagonMedium;
    public long numSquirglesBattleNonagonMedium;
    public long fastestVictoryNonagonMedium;
    public long numTimesWonBattleNonagonMedium;
    public long numTimesLostBattleNonagonMedium;
    public long numSquirglesBattleSquareHard;
    public long fastestVictorySquareHard;
    public long numTimesWonBattleSquareHard;
    public long numTimesLostBattleSquareHard;
    public long numSquirglesBattlePentagonHard;
    public long fastestVictoryPentagonHard;
    public long numTimesWonBattlePentagonHard;
    public long numTimesLostBattlePentagonHard;
    public long numSquirglesBattleHexagonHard;
    public long fastestVictoryHexagonHard;
    public long numTimesWonBattleHexagonHard;
    public long numTimesLostBattleHexagonHard;
    public long numSquirglesBattleSeptagonHard;
    public long fastestVictorySeptagonHard;
    public long numTimesWonBattleSeptagonHard;
    public long numTimesLostBattleSeptagonHard;
    public long numSquirglesBattleOctagonHard;
    public long fastestVictoryOctagonHard;
    public long numTimesWonBattleOctagonHard;
    public long numTimesLostBattleOctagonHard;
    public long numSquirglesBattleNonagonHard;
    public long fastestVictoryNonagonHard;
    public long numTimesWonBattleNonagonHard;
    public long numTimesLostBattleNonagonHard;
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
    public long highestScoreTimeAttackSquareOneMinute;
    public long highestScoreTimeAttackPentagonOneMinute;
    public long highestScoreTimeAttackHexagonOneMinute;
    public long highestScoreTimeAttackSeptagonOneMinute;
    public long highestScoreTimeAttackOctagonOneMinute;
    public long highestScoreTimeAttackNonagonOneMinute;
    public long highestScoreTimeAttackSquareThreeMinutes;
    public long highestScoreTimeAttackPentagonThreeMinutes;
    public long highestScoreTimeAttackHexagonThreeMinutes;
    public long highestScoreTimeAttackSeptagonThreeMinutes;
    public long highestScoreTimeAttackOctagonThreeMinutes;
    public long highestScoreTimeAttackNonagonThreeMinutes;
    public long highestScoreTimeAttackSquareFiveMinutes;
    public long highestScoreTimeAttackPentagonFiveMinutes;
    public long highestScoreTimeAttackHexagonFiveMinutes;
    public long highestScoreTimeAttackSeptagonFiveMinutes;
    public long highestScoreTimeAttackOctagonFiveMinutes;
    public long highestScoreTimeAttackNonagonFiveMinutes;
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
    public int favoriteBaseTimeAttack;
    public int favoriteGameLengthTimeAttack;
    public String favoriteTrackTimeAttack;

    //Time Battle
    public long timePlayedTimeBattle;
    public long numTimesPlayedTimeBattle;
    public long highestScoreTimeBattleSquareEasyOneMinute;
    public long numTimesWonTimeBattleSquareEasyOneMinute;
    public long numTimesLostTimeBattleSquareEasyOneMinute;
    public long highestScoreTimeBattlePentagonEasyOneMinute;
    public long numTimesWonTimeBattlePentagonEasyOneMinute;
    public long numTimesLostTimeBattlePentagonEasyOneMinute;
    public long highestScoreTimeBattleHexagonEasyOneMinute;
    public long numTimesWonTimeBattleHexagonEasyOneMinute;
    public long numTimesLostTimeBattleHexagonEasyOneMinute;
    public long highestScoreTimeBattleSeptagonEasyOneMinute;
    public long numTimesWonTimeBattleSeptagonEasyOneMinute;
    public long numTimesLostTimeBattleSeptagonEasyOneMinute;
    public long highestScoreTimeBattleOctagonEasyOneMinute;
    public long numTimesWonTimeBattleOctagonEasyOneMinute;
    public long numTimesLostTimeBattleOctagonEasyOneMinute;
    public long highestScoreTimeBattleNonagonEasyOneMinute;
    public long numTimesWonTimeBattleNonagonEasyOneMinute;
    public long numTimesLostTimeBattleNonagonEasyOneMinute;
    public long highestScoreTimeBattleSquareMediumOneMinute;
    public long numTimesWonTimeBattleSquareMediumOneMinute;
    public long numTimesLostTimeBattleSquareMediumOneMinute;
    public long highestScoreTimeBattlePentagonMediumOneMinute;
    public long numTimesWonTimeBattlePentagonMediumOneMinute;
    public long numTimesLostTimeBattlePentagonMediumOneMinute;
    public long highestScoreTimeBattleHexagonMediumOneMinute;
    public long numTimesWonTimeBattleHexagonMediumOneMinute;
    public long numTimesLostTimeBattleHexagonMediumOneMinute;
    public long highestScoreTimeBattleSeptagonMediumOneMinute;
    public long numTimesWonTimeBattleSeptagonMediumOneMinute;
    public long numTimesLostTimeBattleSeptagonMediumOneMinute;
    public long highestScoreTimeBattleOctagonMediumOneMinute;
    public long numTimesWonTimeBattleOctagonMediumOneMinute;
    public long numTimesLostTimeBattleOctagonMediumOneMinute;
    public long highestScoreTimeBattleNonagonMediumOneMinute;
    public long numTimesWonTimeBattleNonagonMediumOneMinute;
    public long numTimesLostTimeBattleNonagonMediumOneMinute;
    public long highestScoreTimeBattleSquareHardOneMinute;
    public long numTimesWonTimeBattleSquareHardOneMinute;
    public long numTimesLostTimeBattleSquareHardOneMinute;
    public long highestScoreTimeBattlePentagonHardOneMinute;
    public long numTimesWonTimeBattlePentagonHardOneMinute;
    public long numTimesLostTimeBattlePentagonHardOneMinute;
    public long highestScoreTimeBattleHexagonHardOneMinute;
    public long numTimesWonTimeBattleHexagonHardOneMinute;
    public long numTimesLostTimeBattleHexagonHardOneMinute;
    public long highestScoreTimeBattleSeptagonHardOneMinute;
    public long numTimesWonTimeBattleSeptagonHardOneMinute;
    public long numTimesLostTimeBattleSeptagonHardOneMinute;
    public long highestScoreTimeBattleOctagonHardOneMinute;
    public long numTimesWonTimeBattleOctagonHardOneMinute;
    public long numTimesLostTimeBattleOctagonHardOneMinute;
    public long highestScoreTimeBattleNonagonHardOneMinute;
    public long numTimesWonTimeBattleNonagonHardOneMinute;
    public long numTimesLostTimeBattleNonagonHardOneMinute;
    public long highestScoreTimeBattleSquareEasyThreeMinutes;
    public long numTimesWonTimeBattleSquareEasyThreeMinutes;
    public long numTimesLostTimeBattleSquareEasyThreeMinutes;
    public long highestScoreTimeBattlePentagonEasyThreeMinutes;
    public long numTimesWonTimeBattlePentagonEasyThreeMinutes;
    public long numTimesLostTimeBattlePentagonEasyThreeMinutes;
    public long highestScoreTimeBattleHexagonEasyThreeMinutes;
    public long numTimesWonTimeBattleHexagonEasyThreeMinutes;
    public long numTimesLostTimeBattleHexagonEasyThreeMinutes;
    public long highestScoreTimeBattleSeptagonEasyThreeMinutes;
    public long numTimesWonTimeBattleSeptagonEasyThreeMinutes;
    public long numTimesLostTimeBattleSeptagonEasyThreeMinutes;
    public long highestScoreTimeBattleOctagonEasyThreeMinutes;
    public long numTimesWonTimeBattleOctagonEasyThreeMinutes;
    public long numTimesLostTimeBattleOctagonEasyThreeMinutes;
    public long highestScoreTimeBattleNonagonEasyThreeMinutes;
    public long numTimesWonTimeBattleNonagonEasyThreeMinutes;
    public long numTimesLostTimeBattleNonagonEasyThreeMinutes;
    public long highestScoreTimeBattleSquareMediumThreeMinutes;
    public long numTimesWonTimeBattleSquareMediumThreeMinutes;
    public long numTimesLostTimeBattleSquareMediumThreeMinutes;
    public long highestScoreTimeBattlePentagonMediumThreeMinutes;
    public long numTimesWonTimeBattlePentagonMediumThreeMinutes;
    public long numTimesLostTimeBattlePentagonMediumThreeMinutes;
    public long highestScoreTimeBattleHexagonMediumThreeMinutes;
    public long numTimesWonTimeBattleHexagonMediumThreeMinutes;
    public long numTimesLostTimeBattleHexagonMediumThreeMinutes;
    public long highestScoreTimeBattleSeptagonMediumThreeMinutes;
    public long numTimesWonTimeBattleSeptagonMediumThreeMinutes;
    public long numTimesLostTimeBattleSeptagonMediumThreeMinutes;
    public long highestScoreTimeBattleOctagonMediumThreeMinutes;
    public long numTimesWonTimeBattleOctagonMediumThreeMinutes;
    public long numTimesLostTimeBattleOctagonMediumThreeMinutes;
    public long highestScoreTimeBattleNonagonMediumThreeMinutes;
    public long numTimesWonTimeBattleNonagonMediumThreeMinutes;
    public long numTimesLostTimeBattleNonagonMediumThreeMinutes;
    public long highestScoreTimeBattleSquareHardThreeMinutes;
    public long numTimesWonTimeBattleSquareHardThreeMinutes;
    public long numTimesLostTimeBattleSquareHardThreeMinutes;
    public long highestScoreTimeBattlePentagonHardThreeMinutes;
    public long numTimesWonTimeBattlePentagonHardThreeMinutes;
    public long numTimesLostTimeBattlePentagonHardThreeMinutes;
    public long highestScoreTimeBattleHexagonHardThreeMinutes;
    public long numTimesWonTimeBattleHexagonHardThreeMinutes;
    public long numTimesLostTimeBattleHexagonHardThreeMinutes;
    public long highestScoreTimeBattleSeptagonHardThreeMinutes;
    public long numTimesWonTimeBattleSeptagonHardThreeMinutes;
    public long numTimesLostTimeBattleSeptagonHardThreeMinutes;
    public long highestScoreTimeBattleOctagonHardThreeMinutes;
    public long numTimesWonTimeBattleOctagonHardThreeMinutes;
    public long numTimesLostTimeBattleOctagonHardThreeMinutes;
    public long highestScoreTimeBattleNonagonHardThreeMinutes;
    public long numTimesWonTimeBattleNonagonHardThreeMinutes;
    public long numTimesLostTimeBattleNonagonHardThreeMinutes;
    public long highestScoreTimeBattleSquareEasyFiveMinutes;
    public long numTimesWonTimeBattleSquareEasyFiveMinutes;
    public long numTimesLostTimeBattleSquareEasyFiveMinutes;
    public long highestScoreTimeBattlePentagonEasyFiveMinutes;
    public long numTimesWonTimeBattlePentagonEasyFiveMinutes;
    public long numTimesLostTimeBattlePentagonEasyFiveMinutes;
    public long highestScoreTimeBattleHexagonEasyFiveMinutes;
    public long numTimesWonTimeBattleHexagonEasyFiveMinutes;
    public long numTimesLostTimeBattleHexagonEasyFiveMinutes;
    public long highestScoreTimeBattleSeptagonEasyFiveMinutes;
    public long numTimesWonTimeBattleSeptagonEasyFiveMinutes;
    public long numTimesLostTimeBattleSeptagonEasyFiveMinutes;
    public long highestScoreTimeBattleOctagonEasyFiveMinutes;
    public long numTimesWonTimeBattleOctagonEasyFiveMinutes;
    public long numTimesLostTimeBattleOctagonEasyFiveMinutes;
    public long highestScoreTimeBattleNonagonEasyFiveMinutes;
    public long numTimesWonTimeBattleNonagonEasyFiveMinutes;
    public long numTimesLostTimeBattleNonagonEasyFiveMinutes;
    public long highestScoreTimeBattleSquareMediumFiveMinutes;
    public long numTimesWonTimeBattleSquareMediumFiveMinutes;
    public long numTimesLostTimeBattleSquareMediumFiveMinutes;
    public long highestScoreTimeBattlePentagonMediumFiveMinutes;
    public long numTimesWonTimeBattlePentagonMediumFiveMinutes;
    public long numTimesLostTimeBattlePentagonMediumFiveMinutes;
    public long highestScoreTimeBattleHexagonMediumFiveMinutes;
    public long numTimesWonTimeBattleHexagonMediumFiveMinutes;
    public long numTimesLostTimeBattleHexagonMediumFiveMinutes;
    public long highestScoreTimeBattleSeptagonMediumFiveMinutes;
    public long numTimesWonTimeBattleSeptagonMediumFiveMinutes;
    public long numTimesLostTimeBattleSeptagonMediumFiveMinutes;
    public long highestScoreTimeBattleOctagonMediumFiveMinutes;
    public long numTimesWonTimeBattleOctagonMediumFiveMinutes;
    public long numTimesLostTimeBattleOctagonMediumFiveMinutes;
    public long highestScoreTimeBattleNonagonMediumFiveMinutes;
    public long numTimesWonTimeBattleNonagonMediumFiveMinutes;
    public long numTimesLostTimeBattleNonagonMediumFiveMinutes;
    public long highestScoreTimeBattleSquareHardFiveMinutes;
    public long numTimesWonTimeBattleSquareHardFiveMinutes;
    public long numTimesLostTimeBattleSquareHardFiveMinutes;
    public long highestScoreTimeBattlePentagonHardFiveMinutes;
    public long numTimesWonTimeBattlePentagonHardFiveMinutes;
    public long numTimesLostTimeBattlePentagonHardFiveMinutes;
    public long highestScoreTimeBattleHexagonHardFiveMinutes;
    public long numTimesWonTimeBattleHexagonHardFiveMinutes;
    public long numTimesLostTimeBattleHexagonHardFiveMinutes;
    public long highestScoreTimeBattleSeptagonHardFiveMinutes;
    public long numTimesWonTimeBattleSeptagonHardFiveMinutes;
    public long numTimesLostTimeBattleSeptagonHardFiveMinutes;
    public long highestScoreTimeBattleOctagonHardFiveMinutes;
    public long numTimesWonTimeBattleOctagonHardFiveMinutes;
    public long numTimesLostTimeBattleOctagonHardFiveMinutes;
    public long highestScoreTimeBattleNonagonHardFiveMinutes;
    public long numTimesWonTimeBattleNonagonHardFiveMinutes;
    public long numTimesLostTimeBattleNonagonHardFiveMinutes;
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
    public int favoriteBaseTimeBattle;
    public int favoriteGameLengthTimeBattle;
    public String favoriteDifficultyTimeBattle;
    public String favoriteTrackTimeBattle;

    //Trance
    public long timePlayedTrance;
    public long numTimesPlayedTrance;
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
        numSquirglesSquirgleSquare = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE_SQUARE, 0);
        longestRunSquirgleSquare = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE_SQUARE, 0);
        highestScoreSquirgleSquare = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE_SQUARE, 0);
        numSquirglesSquirglePentagon = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE_PENTAGON, 0);
        longestRunSquirglePentagon = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE_PENTAGON, 0);
        highestScoreSquirglePentagon = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE_PENTAGON, 0);
        numSquirglesSquirgleHexagon = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE_HEXAGON, 0);
        longestRunSquirgleHexagon = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE_HEXAGON, 0);
        highestScoreSquirgleHexagon = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE_HEXAGON, 0);
        numSquirglesSquirgleSeptagon = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE_SEPTAGON, 0);
        longestRunSquirgleSeptagon = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE_SEPTAGON, 0);
        highestScoreSquirgleSeptagon = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE_SEPTAGON, 0);
        numSquirglesSquirgleOctagon = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE_OCTAGON, 0);
        longestRunSquirgleOctagon = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE_OCTAGON, 0);
        highestScoreSquirgleOctagon = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE_OCTAGON, 0);
        numSquirglesSquirgleNonagon = game.save.getLong(SAVE_NUM_SQUIRGLES_SQUIRGLE_NONAGON, 0);
        longestRunSquirgleNonagon = game.save.getLong(SAVE_LONGEST_RUN_SQUIRGLE_NONAGON, 0);
        highestScoreSquirgleNonagon = game.save.getLong(SAVE_HIGHEST_SCORE_SQUIRGLE_NONAGON, 0);
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
        numSquirglesBattleSquareEasy = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_EASY, 0);
        fastestVictorySquareEasy = game.save.getLong(SAVE_FASTEST_VICTORY_SQUARE_EASY, 0);
        numTimesWonBattleSquareEasy = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_SQUARE_EASY, 0);
        numTimesLostBattleSquareEasy = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_EASY, 0);
        numSquirglesBattlePentagonEasy = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_EASY, 0);
        fastestVictoryPentagonEasy = game.save.getLong(SAVE_FASTEST_VICTORY_PENTAGON_EASY, 0);
        numTimesWonBattlePentagonEasy = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_EASY, 0);
        numTimesLostBattlePentagonEasy = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_EASY, 0);
        numSquirglesBattleHexagonEasy = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_EASY, 0);
        fastestVictoryHexagonEasy = game.save.getLong(SAVE_FASTEST_VICTORY_HEXAGON_EASY, 0);
        numTimesWonBattleHexagonEasy = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_EASY, 0);
        numTimesLostBattleHexagonEasy = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_EASY, 0);
        numSquirglesBattleSeptagonEasy = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_EASY, 0);
        fastestVictorySeptagonEasy = game.save.getLong(SAVE_FASTEST_VICTORY_SEPTAGON_EASY, 0);
        numTimesWonBattleSeptagonEasy = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_EASY, 0);
        numTimesLostBattleSeptagonEasy = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_EASY, 0);
        numSquirglesBattleOctagonEasy = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_EASY, 0);
        fastestVictoryOctagonEasy = game.save.getLong(SAVE_FASTEST_VICTORY_OCTAGON_EASY, 0);
        numTimesWonBattleOctagonEasy = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_EASY, 0);
        numTimesLostBattleOctagonEasy = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_EASY, 0);
        numSquirglesBattleNonagonEasy = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_EASY, 0);
        fastestVictoryNonagonEasy = game.save.getLong(SAVE_FASTEST_VICTORY_NONAGON_EASY, 0);
        numTimesWonBattleNonagonEasy = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_NONAGON_EASY, 0);
        numTimesLostBattleNonagonEasy = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_EASY, 0);
        numSquirglesBattleSquareMedium = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_MEDIUM, 0);
        fastestVictorySquareMedium = game.save.getLong(SAVE_FASTEST_VICTORY_SQUARE_MEDIUM, 0);
        numTimesWonBattleSquareMedium = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_SQUARE_MEDIUM, 0);
        numTimesLostBattleSquareMedium = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_MEDIUM, 0);
        numSquirglesBattlePentagonMedium = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_MEDIUM, 0);
        fastestVictoryPentagonMedium = game.save.getLong(SAVE_FASTEST_VICTORY_PENTAGON_MEDIUM, 0);
        numTimesWonBattlePentagonMedium = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_MEDIUM, 0);
        numTimesLostBattlePentagonMedium = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_MEDIUM, 0);
        numSquirglesBattleHexagonMedium = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_MEDIUM, 0);
        fastestVictoryHexagonMedium = game.save.getLong(SAVE_FASTEST_VICTORY_HEXAGON_MEDIUM, 0);
        numTimesWonBattleHexagonMedium = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_MEDIUM, 0);
        numTimesLostBattleHexagonMedium = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_MEDIUM, 0);
        numSquirglesBattleSeptagonMedium = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_MEDIUM, 0);
        fastestVictorySeptagonMedium = game.save.getLong(SAVE_FASTEST_VICTORY_SEPTAGON_MEDIUM, 0);
        numTimesWonBattleSeptagonMedium = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_MEDIUM, 0);
        numTimesLostBattleSeptagonMedium = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_MEDIUM, 0);
        numSquirglesBattleOctagonMedium = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_MEDIUM, 0);
        fastestVictoryOctagonMedium = game.save.getLong(SAVE_FASTEST_VICTORY_OCTAGON_MEDIUM, 0);
        numTimesWonBattleOctagonMedium = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_MEDIUM, 0);
        numTimesLostBattleOctagonMedium = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_MEDIUM, 0);
        numSquirglesBattleNonagonMedium = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_MEDIUM, 0);
        fastestVictoryNonagonMedium = game.save.getLong(SAVE_FASTEST_VICTORY_NONAGON_MEDIUM, 0);
        numTimesWonBattleNonagonMedium = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_NONAGON_MEDIUM, 0);
        numTimesLostBattleNonagonMedium = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_MEDIUM, 0);
        numSquirglesBattleSquareHard = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_HARD, 0);
        fastestVictorySquareHard = game.save.getLong(SAVE_FASTEST_VICTORY_SQUARE_HARD, 0);
        numTimesWonBattleSquareHard = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_SQUARE_HARD, 0);
        numTimesLostBattleSquareHard = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_HARD, 0);
        numSquirglesBattlePentagonHard = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_HARD, 0);
        fastestVictoryPentagonHard = game.save.getLong(SAVE_FASTEST_VICTORY_PENTAGON_HARD, 0);
        numTimesWonBattlePentagonHard = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_HARD, 0);
        numTimesLostBattlePentagonHard = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_HARD, 0);
        numSquirglesBattleHexagonHard = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_HARD, 0);
        fastestVictoryHexagonHard = game.save.getLong(SAVE_FASTEST_VICTORY_HEXAGON_HARD, 0);
        numTimesWonBattleHexagonHard = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_HARD, 0);
        numTimesLostBattleHexagonHard = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_HARD, 0);
        numSquirglesBattleSeptagonHard = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_HARD, 0);
        fastestVictorySeptagonHard = game.save.getLong(SAVE_FASTEST_VICTORY_SEPTAGON_HARD, 0);
        numTimesWonBattleSeptagonHard = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_HARD, 0);
        numTimesLostBattleSeptagonHard = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_HARD, 0);
        numSquirglesBattleOctagonHard = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_HARD, 0);
        fastestVictoryOctagonHard = game.save.getLong(SAVE_FASTEST_VICTORY_OCTAGON_HARD, 0);
        numTimesWonBattleOctagonHard = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_HARD, 0);
        numTimesLostBattleOctagonHard = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_HARD, 0);
        numSquirglesBattleNonagonHard = game.save.getLong(SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_HARD, 0);
        fastestVictoryNonagonHard = game.save.getLong(SAVE_FASTEST_VICTORY_NONAGON_HARD, 0);
        numTimesWonBattleNonagonHard = game.save.getLong(SAVE_NUM_TIMES_WON_BATTLE_NONAGON_HARD, 0);
        numTimesLostBattleNonagonHard = game.save.getLong(SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_HARD, 0);
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
        highestScoreTimeAttackSquareOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_ONE_MINUTE, 0);
        highestScoreTimeAttackPentagonOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_ONE_MINUTE, 0);
        highestScoreTimeAttackHexagonOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_ONE_MINUTE, 0);
        highestScoreTimeAttackSeptagonOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_ONE_MINUTE, 0);
        highestScoreTimeAttackOctagonOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_ONE_MINUTE, 0);
        highestScoreTimeAttackNonagonOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_ONE_MINUTE, 0);
        highestScoreTimeAttackSquareThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_THREE_MINUTES, 0);
        highestScoreTimeAttackPentagonThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_THREE_MINUTES, 0);
        highestScoreTimeAttackHexagonThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_THREE_MINUTES, 0);
        highestScoreTimeAttackSeptagonThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_THREE_MINUTES, 0);
        highestScoreTimeAttackOctagonThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_THREE_MINUTES, 0);
        highestScoreTimeAttackNonagonThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_THREE_MINUTES, 0);
        highestScoreTimeAttackSquareFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_FIVE_MINUTES, 0);
        highestScoreTimeAttackPentagonFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_FIVE_MINUTES, 0);
        highestScoreTimeAttackHexagonFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_FIVE_MINUTES, 0);
        highestScoreTimeAttackSeptagonFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_FIVE_MINUTES, 0);
        highestScoreTimeAttackOctagonFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_FIVE_MINUTES, 0);
        highestScoreTimeAttackNonagonFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_FIVE_MINUTES, 0);
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
        highestScoreTimeBattleSquareEasyOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE, 0);
        numTimesWonTimeBattleSquareEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE, 0);
        numTimesLostTimeBattleSquareEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE, 0);
        highestScoreTimeBattlePentagonEasyOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE, 0);
        numTimesWonTimeBattlePentagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE, 0);
        numTimesLostTimeBattlePentagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE, 0);
        highestScoreTimeBattleHexagonEasyOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE, 0);
        numTimesWonTimeBattleHexagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE, 0);
        numTimesLostTimeBattleHexagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE, 0);
        highestScoreTimeBattleSeptagonEasyOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE, 0);
        numTimesWonTimeBattleSeptagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE, 0);
        numTimesLostTimeBattleSeptagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE, 0);
        highestScoreTimeBattleOctagonEasyOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE, 0);
        numTimesWonTimeBattleOctagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE, 0);
        numTimesLostTimeBattleOctagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE, 0);
        highestScoreTimeBattleNonagonEasyOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE, 0);
        numTimesWonTimeBattleNonagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE, 0);
        numTimesLostTimeBattleNonagonEasyOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE, 0);
        highestScoreTimeBattleSquareMediumOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE, 0);
        numTimesWonTimeBattleSquareMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE, 0);
        numTimesLostTimeBattleSquareMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE, 0);
        highestScoreTimeBattlePentagonMediumOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesWonTimeBattlePentagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesLostTimeBattlePentagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE, 0);
        highestScoreTimeBattleHexagonMediumOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesWonTimeBattleHexagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesLostTimeBattleHexagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE, 0);
        highestScoreTimeBattleSeptagonMediumOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesWonTimeBattleSeptagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesLostTimeBattleSeptagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE, 0);
        highestScoreTimeBattleOctagonMediumOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesWonTimeBattleOctagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesLostTimeBattleOctagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE, 0);
        highestScoreTimeBattleNonagonMediumOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesWonTimeBattleNonagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE, 0);
        numTimesLostTimeBattleNonagonMediumOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE, 0);
        highestScoreTimeBattleSquareHardOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE, 0);
        numTimesWonTimeBattleSquareHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE, 0);
        numTimesLostTimeBattleSquareHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE, 0);
        highestScoreTimeBattlePentagonHardOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE, 0);
        numTimesWonTimeBattlePentagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE, 0);
        numTimesLostTimeBattlePentagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE, 0);
        highestScoreTimeBattleHexagonHardOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE, 0);
        numTimesWonTimeBattleHexagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE, 0);
        numTimesLostTimeBattleHexagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE, 0);
        highestScoreTimeBattleSeptagonHardOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE, 0);
        numTimesWonTimeBattleSeptagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE, 0);
        numTimesLostTimeBattleSeptagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE, 0);
        highestScoreTimeBattleOctagonHardOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE, 0);
        numTimesWonTimeBattleOctagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE, 0);
        numTimesLostTimeBattleOctagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE, 0);
        highestScoreTimeBattleNonagonHardOneMinute = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE, 0);
        numTimesWonTimeBattleNonagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE, 0);
        numTimesLostTimeBattleNonagonHardOneMinute = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE, 0);
        highestScoreTimeBattleSquareEasyThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES, 0);
        numTimesWonTimeBattleSquareEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES, 0);
        numTimesLostTimeBattleSquareEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES, 0);
        highestScoreTimeBattlePentagonEasyThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES, 0);
        numTimesWonTimeBattlePentagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES, 0);
        numTimesLostTimeBattlePentagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES, 0);
        highestScoreTimeBattleHexagonEasyThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES, 0);
        numTimesWonTimeBattleHexagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES, 0);
        numTimesLostTimeBattleHexagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES, 0);
        highestScoreTimeBattleSeptagonEasyThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES, 0);
        numTimesWonTimeBattleSeptagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES, 0);
        numTimesLostTimeBattleSeptagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES, 0);
        highestScoreTimeBattleOctagonEasyThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES, 0);
        numTimesWonTimeBattleOctagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES, 0);
        numTimesLostTimeBattleOctagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES, 0);
        highestScoreTimeBattleNonagonEasyThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES, 0);
        numTimesWonTimeBattleNonagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES, 0);
        numTimesLostTimeBattleNonagonEasyThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES, 0);
        highestScoreTimeBattleSquareMediumThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES, 0);
        numTimesWonTimeBattleSquareMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES, 0);
        numTimesLostTimeBattleSquareMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES, 0);
        highestScoreTimeBattlePentagonMediumThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesWonTimeBattlePentagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesLostTimeBattlePentagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES, 0);
        highestScoreTimeBattleHexagonMediumThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesWonTimeBattleHexagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesLostTimeBattleHexagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES, 0);
        highestScoreTimeBattleSeptagonMediumThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesWonTimeBattleSeptagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesLostTimeBattleSeptagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES, 0);
        highestScoreTimeBattleOctagonMediumThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesWonTimeBattleOctagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesLostTimeBattleOctagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES, 0);
        highestScoreTimeBattleNonagonMediumThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesWonTimeBattleNonagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES, 0);
        numTimesLostTimeBattleNonagonMediumThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES, 0);
        highestScoreTimeBattleSquareHardThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES, 0);
        numTimesWonTimeBattleSquareHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES, 0);
        numTimesLostTimeBattleSquareHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES, 0);
        highestScoreTimeBattlePentagonHardThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES, 0);
        numTimesWonTimeBattlePentagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES, 0);
        numTimesLostTimeBattlePentagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES, 0);
        highestScoreTimeBattleHexagonHardThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES, 0);
        numTimesWonTimeBattleHexagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES, 0);
        numTimesLostTimeBattleHexagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES, 0);
        highestScoreTimeBattleSeptagonHardThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES, 0);
        numTimesWonTimeBattleSeptagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES, 0);
        numTimesLostTimeBattleSeptagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES, 0);
        highestScoreTimeBattleOctagonHardThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES, 0);
        numTimesWonTimeBattleOctagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES, 0);
        numTimesLostTimeBattleOctagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES, 0);
        highestScoreTimeBattleNonagonHardThreeMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES, 0);
        numTimesWonTimeBattleNonagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES, 0);
        numTimesLostTimeBattleNonagonHardThreeMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES, 0);
        highestScoreTimeBattleSquareEasyFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES, 0);
        numTimesWonTimeBattleSquareEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES, 0);
        numTimesLostTimeBattleSquareEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES, 0);
        highestScoreTimeBattlePentagonEasyFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES, 0);
        numTimesWonTimeBattlePentagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES, 0);
        numTimesLostTimeBattlePentagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES, 0);
        highestScoreTimeBattleHexagonEasyFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES, 0);
        numTimesWonTimeBattleHexagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES, 0);
        numTimesLostTimeBattleHexagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES, 0);
        highestScoreTimeBattleSeptagonEasyFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES, 0);
        numTimesWonTimeBattleSeptagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES, 0);
        numTimesLostTimeBattleSeptagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES, 0);
        highestScoreTimeBattleOctagonEasyFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES, 0);
        numTimesWonTimeBattleOctagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES, 0);
        numTimesLostTimeBattleOctagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES, 0);
        highestScoreTimeBattleNonagonEasyFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES, 0);
        numTimesWonTimeBattleNonagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES, 0);
        numTimesLostTimeBattleNonagonEasyFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES, 0);
        highestScoreTimeBattleSquareMediumFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES, 0);
        numTimesWonTimeBattleSquareMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES, 0);
        numTimesLostTimeBattleSquareMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES, 0);
        highestScoreTimeBattlePentagonMediumFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesWonTimeBattlePentagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesLostTimeBattlePentagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES, 0);
        highestScoreTimeBattleHexagonMediumFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesWonTimeBattleHexagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesLostTimeBattleHexagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES, 0);
        highestScoreTimeBattleSeptagonMediumFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesWonTimeBattleSeptagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesLostTimeBattleSeptagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES, 0);
        highestScoreTimeBattleOctagonMediumFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesWonTimeBattleOctagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesLostTimeBattleOctagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES, 0);
        highestScoreTimeBattleNonagonMediumFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesWonTimeBattleNonagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES, 0);
        numTimesLostTimeBattleNonagonMediumFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES, 0);
        highestScoreTimeBattleSquareHardFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES, 0);
        numTimesWonTimeBattleSquareHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES, 0);
        numTimesLostTimeBattleSquareHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES, 0);
        highestScoreTimeBattlePentagonHardFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES, 0);
        numTimesWonTimeBattlePentagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES, 0);
        numTimesLostTimeBattlePentagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES, 0);
        highestScoreTimeBattleHexagonHardFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES, 0);
        numTimesWonTimeBattleHexagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES, 0);
        numTimesLostTimeBattleHexagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES, 0);
        highestScoreTimeBattleSeptagonHardFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES, 0);
        numTimesWonTimeBattleSeptagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES, 0);
        numTimesLostTimeBattleSeptagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES, 0);
        highestScoreTimeBattleOctagonHardFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES, 0);
        numTimesWonTimeBattleOctagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES, 0);
        numTimesLostTimeBattleOctagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES, 0);
        highestScoreTimeBattleNonagonHardFiveMinutes = game.save.getLong(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES, 0);
        numTimesWonTimeBattleNonagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES, 0);
        numTimesLostTimeBattleNonagonHardFiveMinutes = game.save.getLong(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES, 0);
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
        } else if (gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            timePlayedBattle += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_BATTLE, timePlayedBattle);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            timePlayedTimeAttack += amountToAdd;
            game.updateSave(SAVE_TIME_PLAYED_TIME_ATTACK, timePlayedTimeAttack);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
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
        } else if (gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            numTimesPlayedBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_BATTLE, numTimesPlayedBattle);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numTimesPlayedTimeAttack++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_ATTACK, numTimesPlayedTimeAttack);
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            numTimesPlayedTimeBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_BATTLE, numTimesPlayedTimeBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            numTimesPlayedTrance++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TRANCE, numTimesPlayedTrance);
        }
    }

    public void incrementNumSquirgles(int gameplayType, int base, String difficulty) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(base == 4) {
                numSquirglesSquirgleSquare++;
                game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE_SQUARE, numSquirglesSquirgleSquare);
            } else if(base == 5) {
                numSquirglesSquirglePentagon++;
                game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE_PENTAGON, numSquirglesSquirglePentagon);
            } else if(base == 6) {
                numSquirglesSquirgleHexagon++;
                game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE_HEXAGON, numSquirglesSquirgleHexagon);
            } else if(base == 7) {
                numSquirglesSquirgleSeptagon++;
                game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE_SEPTAGON, numSquirglesSquirgleSeptagon);
            } else if(base == 8) {
                numSquirglesSquirgleOctagon++;
                game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE_OCTAGON, numSquirglesSquirgleOctagon);
            } else if(base == 9) {
                numSquirglesSquirgleNonagon++;
                game.updateSave(SAVE_NUM_SQUIRGLES_SQUIRGLE_NONAGON, numSquirglesSquirgleNonagon);
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            if(base == 4) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    numSquirglesBattleSquareEasy++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_EASY, numSquirglesBattleSquareEasy);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    numSquirglesBattleSquareMedium++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_MEDIUM, numSquirglesBattleSquareMedium);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    numSquirglesBattleSquareHard++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_SQUARE_HARD, numSquirglesBattleSquareHard);
                }
            } else if(base == 5) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    numSquirglesBattlePentagonEasy++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_EASY, numSquirglesBattlePentagonEasy);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    numSquirglesBattlePentagonMedium++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_MEDIUM, numSquirglesBattlePentagonMedium);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    numSquirglesBattlePentagonHard++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_PENTAGON_HARD, numSquirglesBattlePentagonHard);
                }
            } else if(base == 6) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    numSquirglesBattleHexagonEasy++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_EASY, numSquirglesBattleHexagonEasy);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    numSquirglesBattleHexagonMedium++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_MEDIUM, numSquirglesBattleHexagonMedium);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    numSquirglesBattleHexagonHard++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_HEXAGON_HARD, numSquirglesBattleHexagonHard);
                }
            } else if(base == 7) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    numSquirglesBattleSeptagonEasy++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_EASY, numSquirglesBattleSeptagonEasy);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    numSquirglesBattleSeptagonMedium++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_MEDIUM, numSquirglesBattleSeptagonMedium);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    numSquirglesBattleSeptagonHard++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_SEPTAGON_HARD, numSquirglesBattleSeptagonHard);
                }
            } else if(base == 8) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    numSquirglesBattleOctagonEasy++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_EASY, numSquirglesBattleOctagonEasy);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    numSquirglesBattleOctagonMedium++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_MEDIUM, numSquirglesBattleOctagonMedium);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    numSquirglesBattleOctagonHard++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_OCTAGON_HARD, numSquirglesBattleOctagonHard);
                }
            } else if(base == 9) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    numSquirglesBattleNonagonEasy++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_EASY, numSquirglesBattleNonagonEasy);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    numSquirglesBattleNonagonMedium++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_MEDIUM, numSquirglesBattleNonagonMedium);
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    numSquirglesBattleNonagonHard++;
                    game.updateSave(SAVE_NUM_SQUIRGLES_BATTLE_NONAGON_HARD, numSquirglesBattleNonagonHard);
                }
            }
        }
        numSquirgles = numSquirglesSquirgleSquare
                + numSquirglesSquirglePentagon
                + numSquirglesSquirgleHexagon
                + numSquirglesSquirgleSeptagon
                + numSquirglesSquirgleOctagon
                + numSquirglesSquirgleNonagon
                + numSquirglesBattleSquareEasy
                + numSquirglesBattleSquareMedium
                + numSquirglesBattleSquareHard
                + numSquirglesBattlePentagonEasy
                + numSquirglesBattlePentagonMedium
                + numSquirglesBattlePentagonHard
                + numSquirglesBattleHexagonEasy
                + numSquirglesBattleHexagonMedium
                + numSquirglesBattleHexagonHard
                + numSquirglesBattleSeptagonEasy
                + numSquirglesBattleSeptagonMedium
                + numSquirglesBattleSeptagonHard
                + numSquirglesBattleOctagonEasy
                + numSquirglesBattleOctagonMedium
                + numSquirglesBattleOctagonHard
                + numSquirglesBattleNonagonEasy
                + numSquirglesBattleNonagonMedium
                + numSquirglesBattleNonagonHard;
        game.updateSave(SAVE_NUM_SQUIRGLES, numSquirgles);
    }

    public void updateLongestRun(long newAmount, int base) {
        if(base == 4) {
            if(newAmount > longestRunSquirgleSquare) {
                longestRunSquirgleSquare = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE_SQUARE, longestRunSquirgleSquare);
            }
        } else if(base == 5) {
            if(newAmount > longestRunSquirglePentagon) {
                longestRunSquirglePentagon = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE_PENTAGON, longestRunSquirglePentagon);
            }
        } else if(base == 6) {
            if(newAmount > longestRunSquirgleHexagon) {
                longestRunSquirgleHexagon = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE_HEXAGON, longestRunSquirgleHexagon);
            }
        } else if(base == 7) {
            if(newAmount > longestRunSquirgleSeptagon) {
                longestRunSquirgleSeptagon = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE_SEPTAGON, longestRunSquirgleSeptagon);
            }
        } else if(base == 8) {
            if(newAmount > longestRunSquirgleOctagon) {
                longestRunSquirgleOctagon = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE_OCTAGON, longestRunSquirgleOctagon);
            }
        } else if(base == 9) {
            if(newAmount > longestRunSquirgleNonagon) {
                longestRunSquirgleNonagon = newAmount;
                game.updateSave(SAVE_LONGEST_RUN_SQUIRGLE_NONAGON, longestRunSquirgleNonagon);
            }
        }
    }

    public void updateFastestVictory(long newAmount, int base, String difficulty) {
        if(base == 4) {
            if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                if(newAmount < fastestVictorySquareEasy || fastestVictorySquareEasy == 0) {
                    fastestVictorySquareEasy = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_SQUARE_EASY, fastestVictorySquareEasy);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                if(newAmount < fastestVictorySquareMedium || fastestVictorySquareMedium == 0) {
                    fastestVictorySquareMedium = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_SQUARE_MEDIUM, fastestVictorySquareMedium);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                if(newAmount < fastestVictorySquareHard || fastestVictorySquareHard == 0) {
                    fastestVictorySquareHard = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_SQUARE_HARD, fastestVictorySquareHard);
                }
            }
        } else if(base == 5) {
            if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                if(newAmount < fastestVictoryPentagonEasy || fastestVictoryPentagonEasy == 0) {
                    fastestVictoryPentagonEasy = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_PENTAGON_EASY, fastestVictoryPentagonEasy);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                if(newAmount < fastestVictoryPentagonMedium || fastestVictoryPentagonMedium == 0) {
                    fastestVictoryPentagonMedium = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_PENTAGON_MEDIUM, fastestVictoryPentagonMedium);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                if(newAmount < fastestVictoryPentagonHard || fastestVictoryPentagonHard == 0) {
                    fastestVictoryPentagonHard = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_PENTAGON_HARD, fastestVictoryPentagonHard);
                }
            }
        } else if(base == 6) {
            if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                if(newAmount < fastestVictoryHexagonEasy || fastestVictoryHexagonEasy == 0) {
                    fastestVictoryHexagonEasy = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_HEXAGON_EASY, fastestVictoryHexagonEasy);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                if(newAmount < fastestVictoryHexagonMedium || fastestVictoryHexagonMedium == 0) {
                    fastestVictoryHexagonMedium = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_HEXAGON_MEDIUM, fastestVictoryHexagonMedium);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                if(newAmount < fastestVictoryHexagonHard || fastestVictoryHexagonHard == 0) {
                    fastestVictoryHexagonHard = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_HEXAGON_HARD, fastestVictoryHexagonHard);
                }
            }
        } else if(base == 7) {
            if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                if(newAmount < fastestVictorySeptagonEasy || fastestVictorySeptagonEasy == 0) {
                    fastestVictorySeptagonEasy = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_SEPTAGON_EASY, fastestVictorySeptagonEasy);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                if(newAmount < fastestVictorySeptagonMedium || fastestVictorySeptagonMedium == 0) {
                    fastestVictorySeptagonMedium = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_SEPTAGON_MEDIUM, fastestVictorySeptagonMedium);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                if(newAmount < fastestVictorySeptagonHard || fastestVictorySeptagonHard == 0) {
                    fastestVictorySeptagonHard = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_SEPTAGON_HARD, fastestVictorySeptagonHard);
                }
            }
        } else if(base == 8) {
            if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                if(newAmount < fastestVictoryOctagonEasy || fastestVictoryOctagonEasy == 0) {
                    fastestVictoryOctagonEasy = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_OCTAGON_EASY, fastestVictoryOctagonEasy);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                if(newAmount < fastestVictoryOctagonMedium || fastestVictoryOctagonMedium == 0) {
                    fastestVictoryOctagonMedium = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_OCTAGON_MEDIUM, fastestVictoryOctagonMedium);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                if(newAmount < fastestVictoryOctagonHard || fastestVictoryOctagonHard == 0) {
                    fastestVictoryOctagonHard = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_OCTAGON_HARD, fastestVictoryOctagonHard);
                }
            }
        } else if(base == 9) {
            if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                if(newAmount < fastestVictoryNonagonEasy || fastestVictoryNonagonEasy == 0) {
                    fastestVictoryNonagonEasy = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_NONAGON_EASY, fastestVictoryNonagonEasy);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                if(newAmount < fastestVictoryNonagonMedium || fastestVictoryNonagonMedium == 0) {
                    fastestVictoryNonagonMedium = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_NONAGON_MEDIUM, fastestVictoryNonagonMedium);
                }
            } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                if(newAmount < fastestVictoryNonagonHard || fastestVictoryNonagonHard == 0) {
                    fastestVictoryNonagonHard = newAmount;
                    game.updateSave(SAVE_FASTEST_VICTORY_NONAGON_HARD, fastestVictoryNonagonHard);
                }
            }
        }
    }

    public void updateHighestScore(long newScore, int gameplayType, int base, int gameLength, String difficulty) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(base == 4) {
                if(newScore > highestScoreSquirgleSquare) {
                    highestScoreSquirgleSquare = newScore;
                    game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE_SQUARE, highestScoreSquirgleSquare);
                }
            } else if(base == 5) {
                if(newScore > highestScoreSquirglePentagon) {
                    highestScoreSquirglePentagon = newScore;
                    game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE_PENTAGON, highestScoreSquirglePentagon);
                }
            } else if(base == 6) {
                if(newScore > highestScoreSquirgleHexagon) {
                    highestScoreSquirgleHexagon = newScore;
                    game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE_HEXAGON, highestScoreSquirgleHexagon);
                }
            } else if(base == 7) {
                if(newScore > highestScoreSquirgleSeptagon) {
                    highestScoreSquirgleSeptagon = newScore;
                    game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE_SEPTAGON, highestScoreSquirgleSeptagon);
                }
            } else if(base == 8) {
                if(newScore > highestScoreSquirgleOctagon) {
                    highestScoreSquirgleOctagon = newScore;
                    game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE_OCTAGON, highestScoreSquirgleOctagon);
                }
            } else if(base == 9) {
                if(newScore > highestScoreSquirgleNonagon) {
                    highestScoreSquirgleNonagon = newScore;
                    game.updateSave(SAVE_HIGHEST_SCORE_SQUIRGLE_NONAGON, highestScoreSquirgleNonagon);
                }
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(base == 4) {
                if(gameLength == Squirgle.ONE_MINUTE) {
                    if(newScore > highestScoreTimeAttackSquareOneMinute) {
                        highestScoreTimeAttackSquareOneMinute = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_ONE_MINUTE, highestScoreTimeAttackSquareOneMinute);
                    }
                } else if(gameLength == Squirgle.THREE_MINUTES) {
                    if(newScore > highestScoreTimeAttackSquareThreeMinutes) {
                        highestScoreTimeAttackSquareThreeMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_THREE_MINUTES, highestScoreTimeAttackSquareThreeMinutes);
                    }
                } else if(gameLength == Squirgle.FIVE_MINUTES) {
                    if(newScore > highestScoreTimeAttackSquareFiveMinutes) {
                        highestScoreTimeAttackSquareFiveMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_SQUARE_FIVE_MINUTES, highestScoreTimeAttackSquareFiveMinutes);
                    }
                }
            } else if(base == 5) {
                if(gameLength == Squirgle.ONE_MINUTE) {
                    if(newScore > highestScoreTimeAttackPentagonOneMinute) {
                        highestScoreTimeAttackPentagonOneMinute = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_ONE_MINUTE, highestScoreTimeAttackPentagonOneMinute);
                    }
                } else if(gameLength == Squirgle.THREE_MINUTES) {
                    if(newScore > highestScoreTimeAttackPentagonThreeMinutes) {
                        highestScoreTimeAttackPentagonThreeMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_THREE_MINUTES, highestScoreTimeAttackPentagonThreeMinutes);
                    }
                } else if(gameLength == Squirgle.FIVE_MINUTES) {
                    if(newScore > highestScoreTimeAttackPentagonFiveMinutes) {
                        highestScoreTimeAttackPentagonFiveMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_PENTAGON_FIVE_MINUTES, highestScoreTimeAttackPentagonFiveMinutes);
                    }
                }
            } else if(base == 6) {
                if(gameLength == Squirgle.ONE_MINUTE) {
                    if(newScore > highestScoreTimeAttackHexagonOneMinute) {
                        highestScoreTimeAttackHexagonOneMinute = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_ONE_MINUTE, highestScoreTimeAttackHexagonOneMinute);
                    }
                } else if(gameLength == Squirgle.THREE_MINUTES) {
                    if(newScore > highestScoreTimeAttackHexagonThreeMinutes) {
                        highestScoreTimeAttackHexagonThreeMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_THREE_MINUTES, highestScoreTimeAttackHexagonThreeMinutes);
                    }
                } else if(gameLength == Squirgle.FIVE_MINUTES) {
                    if(newScore > highestScoreTimeAttackHexagonFiveMinutes) {
                        highestScoreTimeAttackHexagonFiveMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_HEXAGON_FIVE_MINUTES, highestScoreTimeAttackHexagonFiveMinutes);
                    }
                }
            } else if(base == 7) {
                if(gameLength == Squirgle.ONE_MINUTE) {
                    if(newScore > highestScoreTimeAttackSeptagonOneMinute) {
                        highestScoreTimeAttackSeptagonOneMinute = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_ONE_MINUTE, highestScoreTimeAttackSeptagonOneMinute);
                    }
                } else if(gameLength == Squirgle.THREE_MINUTES) {
                    if(newScore > highestScoreTimeAttackSeptagonThreeMinutes) {
                        highestScoreTimeAttackSeptagonThreeMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_THREE_MINUTES, highestScoreTimeAttackSeptagonThreeMinutes);
                    }
                } else if(gameLength == Squirgle.FIVE_MINUTES) {
                    if(newScore > highestScoreTimeAttackSeptagonFiveMinutes) {
                        highestScoreTimeAttackSeptagonFiveMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_SEPTAGON_FIVE_MINUTES, highestScoreTimeAttackSeptagonFiveMinutes);
                    }
                }
            } else if(base == 8) {
                if(gameLength == Squirgle.ONE_MINUTE) {
                    if(newScore > highestScoreTimeAttackOctagonOneMinute) {
                        highestScoreTimeAttackOctagonOneMinute = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_ONE_MINUTE, highestScoreTimeAttackOctagonOneMinute);
                    }
                } else if(gameLength == Squirgle.THREE_MINUTES) {
                    if(newScore > highestScoreTimeAttackOctagonThreeMinutes) {
                        highestScoreTimeAttackOctagonThreeMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_THREE_MINUTES, highestScoreTimeAttackOctagonThreeMinutes);
                    }
                } else if(gameLength == Squirgle.FIVE_MINUTES) {
                    if(newScore > highestScoreTimeAttackOctagonFiveMinutes) {
                        highestScoreTimeAttackOctagonFiveMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_OCTAGON_FIVE_MINUTES, highestScoreTimeAttackOctagonFiveMinutes);
                    }
                }
            } else if(base == 9) {
                if(gameLength == Squirgle.ONE_MINUTE) {
                    if(newScore > highestScoreTimeAttackNonagonOneMinute) {
                        highestScoreTimeAttackNonagonOneMinute = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_ONE_MINUTE, highestScoreTimeAttackNonagonOneMinute);
                    }
                } else if(gameLength == Squirgle.THREE_MINUTES) {
                    if(newScore > highestScoreTimeAttackNonagonThreeMinutes) {
                        highestScoreTimeAttackNonagonThreeMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_THREE_MINUTES, highestScoreTimeAttackNonagonThreeMinutes);
                    }
                } else if(gameLength == Squirgle.FIVE_MINUTES) {
                    if(newScore > highestScoreTimeAttackNonagonFiveMinutes) {
                        highestScoreTimeAttackNonagonFiveMinutes = newScore;
                        game.updateSave(SAVE_HIGHEST_SCORE_TIME_ATTACK_NONAGON_FIVE_MINUTES, highestScoreTimeAttackNonagonFiveMinutes);
                    }
                }
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            if(base == 4) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleSquareEasyOneMinute) {
                            highestScoreTimeBattleSquareEasyOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE, highestScoreTimeBattleSquareEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSquareEasyThreeMinutes) {
                            highestScoreTimeBattleSquareEasyThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES, highestScoreTimeBattleSquareEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSquareEasyFiveMinutes) {
                            highestScoreTimeBattleSquareEasyFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES, highestScoreTimeBattleSquareEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleSquareMediumOneMinute) {
                            highestScoreTimeBattleSquareMediumOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE, highestScoreTimeBattleSquareMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSquareMediumThreeMinutes) {
                            highestScoreTimeBattleSquareMediumThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES, highestScoreTimeBattleSquareMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSquareMediumFiveMinutes) {
                            highestScoreTimeBattleSquareMediumFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES, highestScoreTimeBattleSquareMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleSquareHardOneMinute) {
                            highestScoreTimeBattleSquareHardOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE, highestScoreTimeBattleSquareHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSquareHardThreeMinutes) {
                            highestScoreTimeBattleSquareHardThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES, highestScoreTimeBattleSquareHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSquareHardFiveMinutes) {
                            highestScoreTimeBattleSquareHardFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES, highestScoreTimeBattleSquareHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 5) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattlePentagonEasyOneMinute) {
                            highestScoreTimeBattlePentagonEasyOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE, highestScoreTimeBattlePentagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattlePentagonEasyThreeMinutes) {
                            highestScoreTimeBattlePentagonEasyThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES, highestScoreTimeBattlePentagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattlePentagonEasyFiveMinutes) {
                            highestScoreTimeBattlePentagonEasyFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES, highestScoreTimeBattlePentagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattlePentagonMediumOneMinute) {
                            highestScoreTimeBattlePentagonMediumOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE, highestScoreTimeBattlePentagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattlePentagonMediumThreeMinutes) {
                            highestScoreTimeBattlePentagonMediumThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES, highestScoreTimeBattlePentagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattlePentagonMediumFiveMinutes) {
                            highestScoreTimeBattlePentagonMediumFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES, highestScoreTimeBattlePentagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattlePentagonHardOneMinute) {
                            highestScoreTimeBattlePentagonHardOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE, highestScoreTimeBattlePentagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattlePentagonHardThreeMinutes) {
                            highestScoreTimeBattlePentagonHardThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES, highestScoreTimeBattlePentagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattlePentagonHardFiveMinutes) {
                            highestScoreTimeBattlePentagonHardFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES, highestScoreTimeBattlePentagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 6) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleHexagonEasyOneMinute) {
                            highestScoreTimeBattleHexagonEasyOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE, highestScoreTimeBattleHexagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleHexagonEasyThreeMinutes) {
                            highestScoreTimeBattleHexagonEasyThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES, highestScoreTimeBattleHexagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleHexagonEasyFiveMinutes) {
                            highestScoreTimeBattleHexagonEasyFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES, highestScoreTimeBattleHexagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleHexagonMediumOneMinute) {
                            highestScoreTimeBattleHexagonMediumOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE, highestScoreTimeBattleHexagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleHexagonMediumThreeMinutes) {
                            highestScoreTimeBattleHexagonMediumThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES, highestScoreTimeBattleHexagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleHexagonMediumFiveMinutes) {
                            highestScoreTimeBattleHexagonMediumFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES, highestScoreTimeBattleHexagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleHexagonHardOneMinute) {
                            highestScoreTimeBattleHexagonHardOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE, highestScoreTimeBattleHexagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleHexagonHardThreeMinutes) {
                            highestScoreTimeBattleHexagonHardThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES, highestScoreTimeBattleHexagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleHexagonHardFiveMinutes) {
                            highestScoreTimeBattleHexagonHardFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES, highestScoreTimeBattleHexagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 7) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleSeptagonEasyOneMinute) {
                            highestScoreTimeBattleSeptagonEasyOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE, highestScoreTimeBattleSeptagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSeptagonEasyThreeMinutes) {
                            highestScoreTimeBattleSeptagonEasyThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES, highestScoreTimeBattleSeptagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSeptagonEasyFiveMinutes) {
                            highestScoreTimeBattleSeptagonEasyFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES, highestScoreTimeBattleSeptagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleSeptagonMediumOneMinute) {
                            highestScoreTimeBattleSeptagonMediumOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE, highestScoreTimeBattleSeptagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSeptagonMediumThreeMinutes) {
                            highestScoreTimeBattleSeptagonMediumThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES, highestScoreTimeBattleSeptagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSeptagonMediumFiveMinutes) {
                            highestScoreTimeBattleSeptagonMediumFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES, highestScoreTimeBattleSeptagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleSeptagonHardOneMinute) {
                            highestScoreTimeBattleSeptagonHardOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE, highestScoreTimeBattleSeptagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSeptagonHardThreeMinutes) {
                            highestScoreTimeBattleSeptagonHardThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES, highestScoreTimeBattleSeptagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleSeptagonHardFiveMinutes) {
                            highestScoreTimeBattleSeptagonHardFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES, highestScoreTimeBattleSeptagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 8) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleOctagonEasyOneMinute) {
                            highestScoreTimeBattleOctagonEasyOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE, highestScoreTimeBattleOctagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleOctagonEasyThreeMinutes) {
                            highestScoreTimeBattleOctagonEasyThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES, highestScoreTimeBattleOctagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleOctagonEasyFiveMinutes) {
                            highestScoreTimeBattleOctagonEasyFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES, highestScoreTimeBattleOctagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleOctagonMediumOneMinute) {
                            highestScoreTimeBattleOctagonMediumOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE, highestScoreTimeBattleOctagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleOctagonMediumThreeMinutes) {
                            highestScoreTimeBattleOctagonMediumThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES, highestScoreTimeBattleOctagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleOctagonMediumFiveMinutes) {
                            highestScoreTimeBattleOctagonMediumFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES, highestScoreTimeBattleOctagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleOctagonHardOneMinute) {
                            highestScoreTimeBattleOctagonHardOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE, highestScoreTimeBattleOctagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleOctagonHardThreeMinutes) {
                            highestScoreTimeBattleOctagonHardThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES, highestScoreTimeBattleOctagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleOctagonHardFiveMinutes) {
                            highestScoreTimeBattleOctagonHardFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES, highestScoreTimeBattleOctagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 9) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleNonagonEasyOneMinute) {
                            highestScoreTimeBattleNonagonEasyOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE, highestScoreTimeBattleNonagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleNonagonEasyThreeMinutes) {
                            highestScoreTimeBattleNonagonEasyThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES, highestScoreTimeBattleNonagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleNonagonEasyFiveMinutes) {
                            highestScoreTimeBattleNonagonEasyFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES, highestScoreTimeBattleNonagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleNonagonMediumOneMinute) {
                            highestScoreTimeBattleNonagonMediumOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE, highestScoreTimeBattleNonagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleNonagonMediumThreeMinutes) {
                            highestScoreTimeBattleNonagonMediumThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES, highestScoreTimeBattleNonagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleNonagonMediumFiveMinutes) {
                            highestScoreTimeBattleNonagonMediumFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES, highestScoreTimeBattleNonagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(newScore > highestScoreTimeBattleNonagonHardOneMinute) {
                            highestScoreTimeBattleNonagonHardOneMinute = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE, highestScoreTimeBattleNonagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(newScore > highestScoreTimeBattleNonagonHardThreeMinutes) {
                            highestScoreTimeBattleNonagonHardThreeMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES, highestScoreTimeBattleNonagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(newScore > highestScoreTimeBattleNonagonHardFiveMinutes) {
                            highestScoreTimeBattleNonagonHardFiveMinutes = newScore;
                            game.updateSave(SAVE_HIGHEST_SCORE_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES, highestScoreTimeBattleNonagonHardFiveMinutes);
                        }
                    }
                }
            }
        }
    }

    public void incrementNumTimesWonOrLost(boolean won, int gameplayType, int base, int gameLength, String difficulty) {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            if(base == 4) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(won) {
                        numTimesWonBattleSquareEasy++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_SQUARE_EASY, numTimesWonBattleSquareEasy);
                    } else {
                        numTimesLostBattleSquareEasy++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_EASY, numTimesLostBattleSquareEasy);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(won) {
                        numTimesWonBattleSquareMedium++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_SQUARE_MEDIUM, numTimesWonBattleSquareMedium);
                    } else {
                        numTimesLostBattleSquareMedium++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_MEDIUM, numTimesLostBattleSquareMedium);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(won) {
                        numTimesWonBattleSquareHard++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_SQUARE_HARD, numTimesWonBattleSquareHard);
                    } else {
                        numTimesLostBattleSquareHard++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_SQUARE_HARD, numTimesLostBattleSquareHard);
                    }
                }
            } else if(base == 5) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(won) {
                        numTimesWonBattlePentagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_EASY, numTimesWonBattlePentagonEasy);
                    } else {
                        numTimesLostBattlePentagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_EASY, numTimesLostBattlePentagonEasy);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(won) {
                        numTimesWonBattlePentagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_MEDIUM, numTimesWonBattlePentagonMedium);
                    } else {
                        numTimesLostBattlePentagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_MEDIUM, numTimesLostBattlePentagonMedium);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(won) {
                        numTimesWonBattlePentagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_PENTAGON_HARD, numTimesWonBattlePentagonHard);
                    } else {
                        numTimesLostBattlePentagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_PENTAGON_HARD, numTimesLostBattlePentagonHard);
                    }
                }
            } else if(base == 6) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(won) {
                        numTimesWonBattleHexagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_EASY, numTimesWonBattleHexagonEasy);
                    } else {
                        numTimesLostBattleHexagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_EASY, numTimesLostBattleHexagonEasy);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(won) {
                        numTimesWonBattleHexagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_MEDIUM, numTimesWonBattleHexagonMedium);
                    } else {
                        numTimesLostBattleHexagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_MEDIUM, numTimesLostBattleHexagonMedium);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(won) {
                        numTimesWonBattleHexagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_HEXAGON_HARD, numTimesWonBattleHexagonHard);
                    } else {
                        numTimesLostBattleHexagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_HEXAGON_HARD, numTimesLostBattleHexagonHard);
                    }
                }
            } else if(base == 7) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(won) {
                        numTimesWonBattleSeptagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_EASY, numTimesWonBattleSeptagonEasy);
                    } else {
                        numTimesLostBattleSeptagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_EASY, numTimesLostBattleSeptagonEasy);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(won) {
                        numTimesWonBattleSeptagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_MEDIUM, numTimesWonBattleSeptagonMedium);
                    } else {
                        numTimesLostBattleSeptagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_MEDIUM, numTimesLostBattleSeptagonMedium);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(won) {
                        numTimesWonBattleSeptagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_SEPTAGON_HARD, numTimesWonBattleSeptagonHard);
                    } else {
                        numTimesLostBattleSeptagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_SEPTAGON_HARD, numTimesLostBattleSeptagonHard);
                    }
                }
            } else if(base == 8) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(won) {
                        numTimesWonBattleOctagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_EASY, numTimesWonBattleOctagonEasy);
                    } else {
                        numTimesLostBattleOctagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_EASY, numTimesLostBattleOctagonEasy);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(won) {
                        numTimesWonBattleOctagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_MEDIUM, numTimesWonBattleOctagonMedium);
                    } else {
                        numTimesLostBattleOctagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_MEDIUM, numTimesLostBattleOctagonMedium);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(won) {
                        numTimesWonBattleOctagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_OCTAGON_HARD, numTimesWonBattleOctagonHard);
                    } else {
                        numTimesLostBattleOctagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_OCTAGON_HARD, numTimesLostBattleOctagonHard);
                    }
                }
            } else if(base == 9) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(won) {
                        numTimesWonBattleNonagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_NONAGON_EASY, numTimesWonBattleNonagonEasy);
                    } else {
                        numTimesLostBattleNonagonEasy++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_EASY, numTimesLostBattleNonagonEasy);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(won) {
                        numTimesWonBattleNonagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_NONAGON_MEDIUM, numTimesWonBattleNonagonMedium);
                    } else {
                        numTimesLostBattleNonagonMedium++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_MEDIUM, numTimesLostBattleNonagonMedium);
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(won) {
                        numTimesWonBattleNonagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_WON_BATTLE_NONAGON_HARD, numTimesWonBattleNonagonHard);
                    } else {
                        numTimesLostBattleNonagonHard++;
                        game.updateSave(SAVE_NUM_TIMES_LOST_BATTLE_NONAGON_HARD, numTimesLostBattleNonagonHard);
                    }
                }
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            if(base == 4) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleSquareEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE, numTimesWonTimeBattleSquareEasyOneMinute);
                        } else {
                            numTimesLostTimeBattleSquareEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_ONE_MINUTE, numTimesLostTimeBattleSquareEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSquareEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES, numTimesWonTimeBattleSquareEasyThreeMinutes);
                        } else {
                            numTimesLostTimeBattleSquareEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_THREE_MINUTES, numTimesLostTimeBattleSquareEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSquareEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES, numTimesWonTimeBattleSquareEasyFiveMinutes);
                        } else {
                            numTimesLostTimeBattleSquareEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_EASY_FIVE_MINUTES, numTimesLostTimeBattleSquareEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleSquareMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE, numTimesWonTimeBattleSquareMediumOneMinute);
                        } else {
                            numTimesLostTimeBattleSquareMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_ONE_MINUTE, numTimesLostTimeBattleSquareMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSquareMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES, numTimesWonTimeBattleSquareMediumThreeMinutes);
                        } else {
                            numTimesLostTimeBattleSquareMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_THREE_MINUTES, numTimesLostTimeBattleSquareMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSquareMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES, numTimesWonTimeBattleSquareMediumFiveMinutes);
                        } else {
                            numTimesLostTimeBattleSquareMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_MEDIUM_FIVE_MINUTES, numTimesLostTimeBattleSquareMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleSquareHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE, numTimesWonTimeBattleSquareHardOneMinute);
                        } else {
                            numTimesLostTimeBattleSquareHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_ONE_MINUTE, numTimesLostTimeBattleSquareHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSquareHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES, numTimesWonTimeBattleSquareHardThreeMinutes);
                        } else {
                            numTimesLostTimeBattleSquareHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_THREE_MINUTES, numTimesLostTimeBattleSquareHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSquareHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES, numTimesWonTimeBattleSquareHardFiveMinutes);
                        } else {
                            numTimesLostTimeBattleSquareHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SQUARE_HARD_FIVE_MINUTES, numTimesLostTimeBattleSquareHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 5) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattlePentagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE, numTimesWonTimeBattlePentagonEasyOneMinute);
                        } else {
                            numTimesLostTimeBattlePentagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_ONE_MINUTE, numTimesLostTimeBattlePentagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattlePentagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES, numTimesWonTimeBattlePentagonEasyThreeMinutes);
                        } else {
                            numTimesLostTimeBattlePentagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_THREE_MINUTES, numTimesLostTimeBattlePentagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattlePentagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES, numTimesWonTimeBattlePentagonEasyFiveMinutes);
                        } else {
                            numTimesLostTimeBattlePentagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_EASY_FIVE_MINUTES, numTimesLostTimeBattlePentagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattlePentagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE, numTimesWonTimeBattlePentagonMediumOneMinute);
                        } else {
                            numTimesLostTimeBattlePentagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_ONE_MINUTE, numTimesLostTimeBattlePentagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattlePentagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES, numTimesWonTimeBattlePentagonMediumThreeMinutes);
                        } else {
                            numTimesLostTimeBattlePentagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_THREE_MINUTES, numTimesLostTimeBattlePentagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattlePentagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES, numTimesWonTimeBattlePentagonMediumFiveMinutes);
                        } else {
                            numTimesLostTimeBattlePentagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_MEDIUM_FIVE_MINUTES, numTimesLostTimeBattlePentagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattlePentagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE, numTimesWonTimeBattlePentagonHardOneMinute);
                        } else {
                            numTimesLostTimeBattlePentagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_ONE_MINUTE, numTimesLostTimeBattlePentagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattlePentagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES, numTimesWonTimeBattlePentagonHardThreeMinutes);
                        } else {
                            numTimesLostTimeBattlePentagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_THREE_MINUTES, numTimesLostTimeBattlePentagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattlePentagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES, numTimesWonTimeBattlePentagonHardFiveMinutes);
                        } else {
                            numTimesLostTimeBattlePentagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_PENTAGON_HARD_FIVE_MINUTES, numTimesLostTimeBattlePentagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 6) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleHexagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE, numTimesWonTimeBattleHexagonEasyOneMinute);
                        } else {
                            numTimesLostTimeBattleHexagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_ONE_MINUTE, numTimesLostTimeBattleHexagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleHexagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES, numTimesWonTimeBattleHexagonEasyThreeMinutes);
                        } else {
                            numTimesLostTimeBattleHexagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_THREE_MINUTES, numTimesLostTimeBattleHexagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleHexagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES, numTimesWonTimeBattleHexagonEasyFiveMinutes);
                        } else {
                            numTimesLostTimeBattleHexagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_EASY_FIVE_MINUTES, numTimesLostTimeBattleHexagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleHexagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE, numTimesWonTimeBattleHexagonMediumOneMinute);
                        } else {
                            numTimesLostTimeBattleHexagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_ONE_MINUTE, numTimesLostTimeBattleHexagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleHexagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES, numTimesWonTimeBattleHexagonMediumThreeMinutes);
                        } else {
                            numTimesLostTimeBattleHexagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_THREE_MINUTES, numTimesLostTimeBattleHexagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleHexagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES, numTimesWonTimeBattleHexagonMediumFiveMinutes);
                        } else {
                            numTimesLostTimeBattleHexagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_MEDIUM_FIVE_MINUTES, numTimesLostTimeBattleHexagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleHexagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE, numTimesWonTimeBattleHexagonHardOneMinute);
                        } else {
                            numTimesLostTimeBattleHexagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_ONE_MINUTE, numTimesLostTimeBattleHexagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleHexagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES, numTimesWonTimeBattleHexagonHardThreeMinutes);
                        } else {
                            numTimesLostTimeBattleHexagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_THREE_MINUTES, numTimesLostTimeBattleHexagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleHexagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES, numTimesWonTimeBattleHexagonHardFiveMinutes);
                        } else {
                            numTimesLostTimeBattleHexagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_HEXAGON_HARD_FIVE_MINUTES, numTimesLostTimeBattleHexagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 7) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE, numTimesWonTimeBattleSeptagonEasyOneMinute);
                        } else {
                            numTimesLostTimeBattleSeptagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_ONE_MINUTE, numTimesLostTimeBattleSeptagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES, numTimesWonTimeBattleSeptagonEasyThreeMinutes);
                        } else {
                            numTimesLostTimeBattleSeptagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_THREE_MINUTES, numTimesLostTimeBattleSeptagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES, numTimesWonTimeBattleSeptagonEasyFiveMinutes);
                        } else {
                            numTimesLostTimeBattleSeptagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_EASY_FIVE_MINUTES, numTimesLostTimeBattleSeptagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE, numTimesWonTimeBattleSeptagonMediumOneMinute);
                        } else {
                            numTimesLostTimeBattleSeptagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_ONE_MINUTE, numTimesLostTimeBattleSeptagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES, numTimesWonTimeBattleSeptagonMediumThreeMinutes);
                        } else {
                            numTimesLostTimeBattleSeptagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_THREE_MINUTES, numTimesLostTimeBattleSeptagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES, numTimesWonTimeBattleSeptagonMediumFiveMinutes);
                        } else {
                            numTimesLostTimeBattleSeptagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_MEDIUM_FIVE_MINUTES, numTimesLostTimeBattleSeptagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE, numTimesWonTimeBattleSeptagonHardOneMinute);
                        } else {
                            numTimesLostTimeBattleSeptagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_ONE_MINUTE, numTimesLostTimeBattleSeptagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES, numTimesWonTimeBattleSeptagonHardThreeMinutes);
                        } else {
                            numTimesLostTimeBattleSeptagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_THREE_MINUTES, numTimesLostTimeBattleSeptagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleSeptagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES, numTimesWonTimeBattleSeptagonHardFiveMinutes);
                        } else {
                            numTimesLostTimeBattleSeptagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_SEPTAGON_HARD_FIVE_MINUTES, numTimesLostTimeBattleSeptagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 8) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleOctagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE, numTimesWonTimeBattleOctagonEasyOneMinute);
                        } else {
                            numTimesLostTimeBattleOctagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_ONE_MINUTE, numTimesLostTimeBattleOctagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleOctagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES, numTimesWonTimeBattleOctagonEasyThreeMinutes);
                        } else {
                            numTimesLostTimeBattleOctagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_THREE_MINUTES, numTimesLostTimeBattleOctagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleOctagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES, numTimesWonTimeBattleOctagonEasyFiveMinutes);
                        } else {
                            numTimesLostTimeBattleOctagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_EASY_FIVE_MINUTES, numTimesLostTimeBattleOctagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleOctagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE, numTimesWonTimeBattleOctagonMediumOneMinute);
                        } else {
                            numTimesLostTimeBattleOctagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_ONE_MINUTE, numTimesLostTimeBattleOctagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleOctagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES, numTimesWonTimeBattleOctagonMediumThreeMinutes);
                        } else {
                            numTimesLostTimeBattleOctagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_THREE_MINUTES, numTimesLostTimeBattleOctagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleOctagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES, numTimesWonTimeBattleOctagonMediumFiveMinutes);
                        } else {
                            numTimesLostTimeBattleOctagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_MEDIUM_FIVE_MINUTES, numTimesLostTimeBattleOctagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleOctagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE, numTimesWonTimeBattleOctagonHardOneMinute);
                        } else {
                            numTimesLostTimeBattleOctagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_ONE_MINUTE, numTimesLostTimeBattleOctagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleOctagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES, numTimesWonTimeBattleOctagonHardThreeMinutes);
                        } else {
                            numTimesLostTimeBattleOctagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_THREE_MINUTES, numTimesLostTimeBattleOctagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleOctagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES, numTimesWonTimeBattleOctagonHardFiveMinutes);
                        } else {
                            numTimesLostTimeBattleOctagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_OCTAGON_HARD_FIVE_MINUTES, numTimesLostTimeBattleOctagonHardFiveMinutes);
                        }
                    }
                }
            } else if(base == 9) {
                if(difficulty.equals(Squirgle.DIFFICULTY_EASY)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleNonagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE, numTimesWonTimeBattleNonagonEasyOneMinute);
                        } else {
                            numTimesLostTimeBattleNonagonEasyOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_ONE_MINUTE, numTimesLostTimeBattleNonagonEasyOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleNonagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES, numTimesWonTimeBattleNonagonEasyThreeMinutes);
                        } else {
                            numTimesLostTimeBattleNonagonEasyThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_THREE_MINUTES, numTimesLostTimeBattleNonagonEasyThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleNonagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES, numTimesWonTimeBattleNonagonEasyFiveMinutes);
                        } else {
                            numTimesLostTimeBattleNonagonEasyFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_EASY_FIVE_MINUTES, numTimesLostTimeBattleNonagonEasyFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_MEDIUM)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleNonagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE, numTimesWonTimeBattleNonagonMediumOneMinute);
                        } else {
                            numTimesLostTimeBattleNonagonMediumOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_ONE_MINUTE, numTimesLostTimeBattleNonagonMediumOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleNonagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES, numTimesWonTimeBattleNonagonMediumThreeMinutes);
                        } else {
                            numTimesLostTimeBattleNonagonMediumThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_THREE_MINUTES, numTimesLostTimeBattleNonagonMediumThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleNonagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES, numTimesWonTimeBattleNonagonMediumFiveMinutes);
                        } else {
                            numTimesLostTimeBattleNonagonMediumFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_MEDIUM_FIVE_MINUTES, numTimesLostTimeBattleNonagonMediumFiveMinutes);
                        }
                    }
                } else if(difficulty.equals(Squirgle.DIFFICULTY_HARD)) {
                    if(gameLength == Squirgle.ONE_MINUTE) {
                        if(won) {
                            numTimesWonTimeBattleNonagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE, numTimesWonTimeBattleNonagonHardOneMinute);
                        } else {
                            numTimesLostTimeBattleNonagonHardOneMinute++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_ONE_MINUTE, numTimesLostTimeBattleNonagonHardOneMinute);
                        }
                    } else if(gameLength == Squirgle.THREE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleNonagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES, numTimesWonTimeBattleNonagonHardThreeMinutes);
                        } else {
                            numTimesLostTimeBattleNonagonHardThreeMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_THREE_MINUTES, numTimesLostTimeBattleNonagonHardThreeMinutes);
                        }
                    } else if(gameLength == Squirgle.FIVE_MINUTES) {
                        if(won) {
                            numTimesWonTimeBattleNonagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_WON_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES, numTimesWonTimeBattleNonagonHardFiveMinutes);
                        } else {
                            numTimesLostTimeBattleNonagonHardFiveMinutes++;
                            game.updateSave(SAVE_NUM_TIMES_LOST_TIME_BATTLE_NONAGON_HARD_FIVE_MINUTES, numTimesLostTimeBattleNonagonHardFiveMinutes);
                        }
                    }
                }
            }
        }
    }

    public void incrementNumTimesPlayedDifficulty(String difficulty, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
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
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
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
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedOneMinuteTimeAttack,
                    numTimesPlayedThreeMinutesTimeAttack,
                    numTimesPlayedFiveMinutesTimeAttack));
            if(numTimesPlayedOneMinuteTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.ONE_MINUTE;
            } else if(numTimesPlayedThreeMinutesTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.THREE_MINUTES;
            } else if(numTimesPlayedFiveMinutesTimeAttack == largestNumber) {
                favoriteGameLengthTimeAttack = Squirgle.FIVE_MINUTES;
            }
            game.updateSave(SAVE_FAVORITE_GAME_LENGTH_TIME_ATTACK, favoriteGameLengthTimeAttack);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            if(numSeconds == Squirgle.ONE_MINUTE) {
                numTimesPlayedOneMinuteTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_ONE_MINUTE_TIME_BATTLE, numTimesPlayedOneMinuteTimeBattle);
            } else if(numSeconds == Squirgle.THREE_MINUTES) {
                numTimesPlayedThreeMinutesTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_THREE_MINUTES_TIME_BATTLE, numTimesPlayedThreeMinutesTimeBattle);
            } else if(numSeconds == Squirgle.FIVE_MINUTES) {
                numTimesPlayedFiveMinutesTimeBattle++;
                game.updateSave(SAVE_NUM_TIMES_PLAYED_FIVE_MINUTES_TIME_BATTLE, numTimesPlayedFiveMinutesTimeBattle);
            }
            long largestNumber = Collections.max(Arrays.asList(numTimesPlayedOneMinuteTimeBattle,
                    numTimesPlayedThreeMinutesTimeBattle,
                    numTimesPlayedFiveMinutesTimeBattle));
            if(numTimesPlayedOneMinuteTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.ONE_MINUTE;
            } else if(numTimesPlayedThreeMinutesTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.THREE_MINUTES;
            } else if(numTimesPlayedFiveMinutesTimeBattle == largestNumber) {
                favoriteGameLengthTimeBattle = Squirgle.FIVE_MINUTES;
            }
            game.updateSave(SAVE_FAVORITE_GAME_LENGTH_TIME_BATTLE, favoriteGameLengthTimeBattle);
        }
    }

    public void incrementNumTimesPlayedMode(int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numTimesPlayedSquirgle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_SQUIRGLE, numTimesPlayedSquirgle);
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
            numTimesPlayedBattle++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_BATTLE, numTimesPlayedBattle);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numTimesPlayedTimeAttack++;
            game.updateSave(SAVE_NUM_TIMES_PLAYED_TIME_ATTACK, numTimesPlayedTimeAttack);
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
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
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE) {
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
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
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
