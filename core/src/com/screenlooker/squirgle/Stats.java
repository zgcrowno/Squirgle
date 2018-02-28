package com.screenlooker.squirgle;

import com.badlogic.gdx.Screen;
import com.screenlooker.squirgle.screen.GameplayScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Stats {
    public static final String NA = "N/A";
    public static final String MODE_SQUIRGLE = "SQUIRGLE";
    public static final String MODE_BATTLE = "BATTLE";
    public static final String MODE_TIME_ATTACK = "TIME ATTACK";
    public static final String MODE_TIME_BATTLE = "TIME BATTLE";
    public static final String MODE_TRANCE = "TRANCE";

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

    public Stats() {
        //General
        timePlayed = 0;
        numSquirgles = 0;
        favoriteMode = NA;
        favoriteBase = 0;
        favoriteTrack = NA;

        //Squirgle
        timePlayedSquirgle = 0;
        numTimesPlayedSquirgle = 0;
        numSquirglesSquirgle = 0;
        longestRunSquirgle = 0;
        highestScoreSquirgle = 0;
        numTimesPlayedSquareSquirgle = 0;
        numTimesPlayedPentagonSquirgle = 0;
        numTimesPlayedHexagonSquirgle = 0;
        numTimesPlayedSeptagonSquirgle = 0;
        numTimesPlayedOctagonSquirgle = 0;
        numTimesPlayedNonagonSquirgle = 0;
        numTimesPlayedPointillismSquirgle = 0;
        numTimesPlayedLineageSquirgle = 0;
        numTimesPlayedTriTheWaltzSquirgle = 0;
        numTimesPlayedSquaredOffSquirgle = 0;
        numTimesPlayedPentUpSquirgle = 0;
        numTimesPlayedHexidecibelSquirgle = 0;
        numTimesPlayedInterseptorSquirgle = 0;
        numTimesPlayedRoctopusSquirgle = 0;
        numTimesPlayedNonplussedSquirgle = 0;
        favoriteBaseSquirgle = 0;
        favoriteTrackSquirgle = NA;

        //Battle
        timePlayedBattle = 0;
        numTimesPlayedBattle = 0;
        numSquirglesBattle = 0;
        fastestVictory = 0;
        numTimesWonBattle = 0;
        numTimesLostBattle = 0;
        numTimesPlayedSquareBattle = 0;
        numTimesPlayedPentagonBattle = 0;
        numTimesPlayedHexagonBattle = 0;
        numTimesPlayedSeptagonBattle = 0;
        numTimesPlayedOctagonBattle = 0;
        numTimesPlayedNonagonBattle = 0;
        numTimesPlayedPointillismBattle = 0;
        numTimesPlayedLineageBattle = 0;
        numTimesPlayedTriTheWaltzBattle = 0;
        numTimesPlayedSquaredOffBattle = 0;
        numTimesPlayedPentUpBattle = 0;
        numTimesPlayedHexidecibelBattle = 0;
        numTimesPlayedInterseptorBattle = 0;
        numTimesPlayedRoctopusBattle = 0;
        numTimesPlayedNonplussedBattle = 0;
        numTimesPlayedEasyBattle = 0;
        numTimesPlayedMediumBattle = 0;
        numTimesPlayedHardBattle = 0;
        favoriteBaseBattle = 0;
        favoriteDifficultyBattle = NA;
        favoriteTrackBattle = NA;

        //Time Attack
        timePlayedTimeAttack = 0;
        numTimesPlayedTimeAttack = 0;
        highestScoreTimeAttack = 0;
        numTimesPlayedSquareTimeAttack = 0;
        numTimesPlayedPentagonTimeAttack = 0;
        numTimesPlayedHexagonTimeAttack = 0;
        numTimesPlayedSeptagonTimeAttack = 0;
        numTimesPlayedOctagonTimeAttack = 0;
        numTimesPlayedNonagonTimeAttack = 0;
        numTimesPlayedPointillismTimeAttack = 0;
        numTimesPlayedLineageTimeAttack = 0;
        numTimesPlayedTriTheWaltzTimeAttack = 0;
        numTimesPlayedSquaredOffTimeAttack = 0;
        numTimesPlayedPentUpTimeAttack = 0;
        numTimesPlayedHexidecibelTimeAttack = 0;
        numTimesPlayedInterseptorTimeAttack = 0;
        numTimesPlayedRoctopusTimeAttack = 0;
        numTimesPlayedNonplussedTimeAttack = 0;
        favoriteBaseTimeAttack = 0;
        favoriteGameLengthTimeAttack = 0;
        favoriteTrackTimeAttack = NA;

        //Time Battle
        timePlayedTimeBattle = 0;
        numTimesPlayedTimeBattle = 0;
        highestScoreTimeBattle = 0;
        numTimesWonTimeBattle = 0;
        numTimesLostTimeBattle = 0;
        numTimesPlayedSquareTimeBattle = 0;
        numTimesPlayedPentagonTimeBattle = 0;
        numTimesPlayedHexagonTimeBattle = 0;
        numTimesPlayedSeptagonTimeBattle = 0;
        numTimesPlayedOctagonTimeBattle = 0;
        numTimesPlayedNonagonTimeBattle = 0;
        numTimesPlayedPointillismTimeBattle = 0;
        numTimesPlayedLineageTimeBattle = 0;
        numTimesPlayedTriTheWaltzTimeBattle = 0;
        numTimesPlayedSquaredOffTimeBattle = 0;
        numTimesPlayedPentUpTimeBattle = 0;
        numTimesPlayedHexidecibelTimeBattle = 0;
        numTimesPlayedInterseptorTimeBattle = 0;
        numTimesPlayedRoctopusTimeBattle = 0;
        numTimesPlayedNonplussedTimeBattle = 0;
        numTimesPlayedEasyTimeBattle = 0;
        numTimesPlayedMediumTimeBattle = 0;
        numTimesPlayedHardTimeBattle = 0;
        favoriteBaseTimeBattle = 0;
        favoriteGameLengthTimeBattle = 0;
        favoriteDifficultyTimeBattle = NA;
        favoriteTrackTimeBattle = NA;

        //Trance
        timePlayedTrance = 0;
        numTimesPlayedTrance = 0;
        longestRunTrance = 0;
        numTimesPlayedPointillismTrance = 0;
        numTimesPlayedLineageTrance = 0;
        numTimesPlayedTriTheWaltzTrance = 0;
        numTimesPlayedSquaredOffTrance = 0;
        numTimesPlayedPentUpTrance = 0;
        numTimesPlayedHexidecibelTrance = 0;
        numTimesPlayedInterseptorTrance = 0;
        numTimesPlayedRoctopusTrance = 0;
        numTimesPlayedNonplussedTrance = 0;
        favoriteTrackTrance = NA;
    }

    public void updateTimePlayed(long amountToAdd, int gameplayType) {
        if (gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            timePlayedSquirgle += amountToAdd;
        } else if (gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            timePlayedBattle += amountToAdd;
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            timePlayedTimeAttack += amountToAdd;
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            timePlayedTimeBattle += amountToAdd;
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            timePlayedTrance += amountToAdd;
        }
        timePlayed = timePlayedSquirgle + timePlayedBattle + timePlayedTimeAttack + timePlayedTimeBattle + timePlayedTrance;
    }

    public void incrementNumTimesPlayed(int gameplayType) {
        if (gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numTimesPlayedSquirgle++;
        } else if (gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numTimesPlayedBattle++;
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numTimesPlayedTimeAttack++;
        } else if (gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            numTimesPlayedTimeBattle++;
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            numTimesPlayedTrance++;
        }
    }

    public void incrementNumSquirgles(int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numSquirglesSquirgle++;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numSquirglesBattle++;
        }
        numSquirgles = numSquirglesSquirgle + numSquirglesBattle;
    }

    public void updateLongestRun(long newAmount, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(newAmount < longestRunSquirgle || longestRunSquirgle == 0) {
                longestRunSquirgle = newAmount;
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            if(newAmount < longestRunTrance || longestRunTrance == 0) {
                longestRunTrance = newAmount;
            }
        }
    }

    public void updateFastestVictory(long newAmount) {
        if(newAmount < fastestVictory || fastestVictory == 0) {
            fastestVictory = newAmount;
        }
    }

    public void updateHighestScore(long newScore, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(newScore > highestScoreSquirgle) {
                highestScoreSquirgle = newScore;
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(newScore > highestScoreTimeAttack) {
                highestScoreTimeAttack = newScore;
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE) {
            if(newScore > highestScoreTimeBattle) {
                highestScoreTimeBattle = newScore;
            }
        }
    }

    public void incrementNumTimesWonOrLost(boolean won, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            if(won) {
                numTimesWonBattle++;
            } else {
                numTimesLostBattle++;
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(won) {
                numTimesWonTimeBattle++;
            } else {
                numTimesLostTimeBattle++;
            }
        }
    }

    public void incrementNumTimesPlayedDifficulty(String difficulty, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            if(difficulty == Squirgle.DIFFICULTY_EASY) {
                numTimesPlayedEasyBattle++;
            } else if(difficulty == Squirgle.DIFFICULTY_MEDIUM) {
                numTimesPlayedMediumBattle++;
            } else if(difficulty == Squirgle.DIFFICULTY_HARD) {
                numTimesPlayedHardBattle++;
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
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(difficulty == Squirgle.DIFFICULTY_EASY) {
                numTimesPlayedEasyTimeBattle++;
            } else if(difficulty == Squirgle.DIFFICULTY_MEDIUM) {
                numTimesPlayedMediumTimeBattle++;
            } else if(difficulty == Squirgle.DIFFICULTY_HARD) {
                numTimesPlayedHardTimeBattle++;
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
        }
    }

    public void incrementNumTimesPlayedGameLength(int numSeconds, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(numSeconds == Squirgle.ONE_MINUTE) {
                numTimesPlayedOneMinuteTimeAttack++;
            } else if(numSeconds == Squirgle.THREE_MINUTES) {
                numTimesPlayedThreeMinutesTimeAttack++;
            } else if(numSeconds == Squirgle.FIVE_MINUTES) {
                numTimesPlayedFiveMinutesTimeAttack++;
            } else if(numSeconds == Squirgle.TEN_MINUTES) {
                numTimesPlayedTenMinutesTimeAttack++;
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
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(numSeconds == Squirgle.ONE_MINUTE) {
                numTimesPlayedOneMinuteTimeBattle++;
            } else if(numSeconds == Squirgle.THREE_MINUTES) {
                numTimesPlayedThreeMinutesTimeBattle++;
            } else if(numSeconds == Squirgle.FIVE_MINUTES) {
                numTimesPlayedFiveMinutesTimeBattle++;
            } else if(numSeconds == Squirgle.TEN_MINUTES) {
                numTimesPlayedTenMinutesTimeBattle++;
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
        }
    }

    public void incrementNumTimesPlayedMode(int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            numTimesPlayedSquirgle++;
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            numTimesPlayedBattle++;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            numTimesPlayedTimeAttack++;
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            numTimesPlayedTimeBattle++;
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            numTimesPlayedTrance++;
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
    }

    public void incrementNumTimesPlayedBaseOrTrack(boolean base, int baseOrTrack, int gameplayType) {
        if(gameplayType == Squirgle.GAMEPLAY_SQUIRGLE) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareSquirgle++;
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonSquirgle++;
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonSquirgle++;
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonSquirgle++;
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonSquirgle++;
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonSquirgle++;
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
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismSquirgle++;
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageSquirgle++;
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzSquirgle++;
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffSquirgle++;
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpSquirgle++;
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelSquirgle++;
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorSquirgle++;
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusSquirgle++;
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedSquirgle++;
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
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_BATTLE || gameplayType == Squirgle.GAMEPLAY_BATTLE_LOCAL) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareBattle++;
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonBattle++;
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonBattle++;
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonBattle++;
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonBattle++;
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonBattle++;
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
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelBattle++;
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorBattle++;
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusBattle++;
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedBattle++;
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
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_ATTACK) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareTimeAttack++;
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonTimeAttack++;
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonTimeAttack++;
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonTimeAttack++;
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonTimeAttack++;
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonTimeAttack++;
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
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismTimeAttack++;
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageTimeAttack++;
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzTimeAttack++;
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffTimeAttack++;
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpTimeAttack++;
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelTimeAttack++;
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorTimeAttack++;
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusTimeAttack++;
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedTimeAttack++;
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
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE || gameplayType == Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL) {
            if(base) {
                if (baseOrTrack == Shape.SQUARE) {
                    numTimesPlayedSquareTimeBattle++;
                } else if (baseOrTrack == Shape.PENTAGON) {
                    numTimesPlayedPentagonTimeBattle++;
                } else if (baseOrTrack == Shape.HEXAGON) {
                    numTimesPlayedHexagonTimeBattle++;
                } else if (baseOrTrack == Shape.SEPTAGON) {
                    numTimesPlayedSeptagonTimeBattle++;
                } else if (baseOrTrack == Shape.OCTAGON) {
                    numTimesPlayedOctagonTimeBattle++;
                } else if (baseOrTrack == Shape.NONAGON) {
                    numTimesPlayedNonagonTimeBattle++;
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
            } else {
                if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                    numTimesPlayedPointillismTimeBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                    numTimesPlayedLineageTimeBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                    numTimesPlayedTriTheWaltzTimeBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                    numTimesPlayedSquaredOffTimeBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                    numTimesPlayedPentUpTimeBattle++;
                } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                    numTimesPlayedHexidecibelTimeBattle++;
                } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                    numTimesPlayedInterseptorTimeBattle++;
                } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                    numTimesPlayedRoctopusTimeBattle++;
                } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                    numTimesPlayedNonplussedTimeBattle++;
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
            }
        } else if(gameplayType == Squirgle.GAMEPLAY_TRANCE) {
            if (baseOrTrack == Squirgle.MUSIC_POINTILLISM) {
                numTimesPlayedPointillismTrance++;
            } else if (baseOrTrack == Squirgle.MUSIC_LINEAGE) {
                numTimesPlayedLineageTrance++;
            } else if (baseOrTrack == Squirgle.MUSIC_TRI_THE_WALTZ) {
                numTimesPlayedTriTheWaltzTrance++;
            } else if (baseOrTrack == Squirgle.MUSIC_SQUARED_OFF) {
                numTimesPlayedSquaredOffTrance++;
            } else if (baseOrTrack == Squirgle.MUSIC_PENT_UP) {
                numTimesPlayedPentUpTrance++;
            } else if (baseOrTrack == Squirgle.MUSIC_HEXIDECIBEL) {
                numTimesPlayedHexidecibelTrance++;
            } else if(baseOrTrack == Squirgle.MUSIC_INTERSEPTOR) {
                numTimesPlayedInterseptorTrance++;
            } else if(baseOrTrack == Squirgle.MUSIC_ROCTOPUS) {
                numTimesPlayedRoctopusTrance++;
            } else if(baseOrTrack == Squirgle.MUSIC_NONPLUSSED) {
                numTimesPlayedNonplussedTrance++;
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
        }
        if(base) {
            numTimesPlayedSquare = numTimesPlayedSquareSquirgle + numTimesPlayedSquareBattle + numTimesPlayedSquareTimeAttack + numTimesPlayedSquareTimeBattle;
            numTimesPlayedPentagon = numTimesPlayedPentagonSquirgle + numTimesPlayedPentagonBattle + numTimesPlayedPentagonTimeAttack + numTimesPlayedPentagonTimeBattle;
            numTimesPlayedHexagon = numTimesPlayedHexagonSquirgle + numTimesPlayedHexagonBattle + numTimesPlayedHexagonTimeAttack + numTimesPlayedHexagonTimeBattle;
            numTimesPlayedSeptagon = numTimesPlayedSeptagonSquirgle + numTimesPlayedSeptagonBattle + numTimesPlayedSeptagonTimeAttack + numTimesPlayedSeptagonTimeBattle;
            numTimesPlayedOctagon = numTimesPlayedOctagonSquirgle + numTimesPlayedOctagonBattle + numTimesPlayedOctagonTimeAttack + numTimesPlayedOctagonTimeBattle;
            numTimesPlayedNonagon = numTimesPlayedNonagonSquirgle + numTimesPlayedNonagonBattle + numTimesPlayedNonagonTimeAttack + numTimesPlayedNonagonTimeBattle;
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
        }
    }
}
