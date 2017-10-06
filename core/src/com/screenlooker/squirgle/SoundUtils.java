package com.screenlooker.squirgle;

import com.badlogic.gdx.math.MathUtils;

import java.util.List;

public class SoundUtils {

    public static final int A_MAJOR = 0;
    public static final int A_MINOR = 1;
    public static final int A_SHARP_MAJOR = 2;
    public static final int A_SHARP_MINOR = 3;
    public static final int B_MAJOR = 4;
    public static final int B_MINOR = 5;
    public static final int C_MAJOR = 6;
    public static final int C_MINOR = 7;
    public static final int C_SHARP_MAJOR = 8;
    public static final int C_SHARP_MINOR = 9;
    public static final int D_MAJOR = 10;
    public static final int D_MINOR = 11;
    public static final int D_SHARP_MAJOR = 12;
    public static final int D_SHARP_MINOR = 13;
    public static final int E_MAJOR = 14;
    public static final int E_MINOR = 15;
    public static final int F_MAJOR = 16;
    public static final int F_MINOR = 17;
    public static final int F_SHARP_MAJOR = 18;
    public static final int F_SHARP_MINOR = 19;
    public static final int G_MAJOR = 20;
    public static final int G_MINOR = 21;
    public static final int G_SHARP_MAJOR = 22;
    public static final int G_SHARP_MINOR = 23;
    public static final int THIRTY_SECOND_MULTIPLIER = 8; //This is multiplied by the time signature in order to allow for 32nd notes

    public static void playMusic(int timeSignature, float measureLength, float colorListSpeed, List<Shape> backgroundColorShapeList, Squirgle game) {
        int numThirtySeconds = timeSignature * THIRTY_SECOND_MULTIPLIER;
        int wholeMeasure = numThirtySeconds;
        int halfMeasure = numThirtySeconds / 2;
        int quarterMeasure = numThirtySeconds / timeSignature;
        int eighthMeasure = numThirtySeconds / (timeSignature * 2);
        int sixteenthMeasure = numThirtySeconds / (timeSignature * 4);
        int thirtySecondMeasure = numThirtySeconds / (timeSignature * 8);

        for (int i = 1; i <= numThirtySeconds; i++) {
            boolean atMeasureStart = backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y > 0 - colorListSpeed
                    && backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y < 0 + colorListSpeed;
            boolean atThirtySecondInterval = backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y > ((i * measureLength) / numThirtySeconds) - colorListSpeed
                    && backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y < ((i * measureLength) / numThirtySeconds) + colorListSpeed
                    && !(backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y - colorListSpeed > ((i * measureLength) / numThirtySeconds) - colorListSpeed);

            //Bass drum
            //TODO: Figure out why this isn't working correctly on Android (also not working very well on PC)
//            if (atMeasureStart) {
//                game.bassDrum.play((float) (game.volume / 10.0));
//            }

            //Hi-hat
            if (atThirtySecondInterval && i % quarterMeasure == 0) {
                game.hiHat.play((float) (game.volume / 10.0));
            }

            //Snare
            if (atThirtySecondInterval && i % halfMeasure == 0) {
                game.snareDrum.play((float) (game.volume / 10.0));
            }

            //Notes
            if (atThirtySecondInterval && i % eighthMeasure == 0) {
                game.keyMap.get(game.key).get(MathUtils.random(game.keyMap.get(game.key).size() - 1)).play((float) (game.volume / 10.0));
            }
        }
    }

}
