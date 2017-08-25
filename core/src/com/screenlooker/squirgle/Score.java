package com.screenlooker.squirgle;

public class Score {
    private int score;
    private int multiplier;

    public Score() {
        score = 0;
        multiplier = 1;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String scoreString() {
        return "Score: " + this.score;
    }

    public String multiplierString() {
        return "X" + this.multiplier;
    }
}
