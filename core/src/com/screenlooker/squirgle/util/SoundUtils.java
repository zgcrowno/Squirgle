package com.screenlooker.squirgle.util;

import com.badlogic.gdx.math.MathUtils;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;

import java.util.List;

public class SoundUtils {

    public static void playMusic(Shape promptShape, Squirgle game) {
        boolean phaseOne = promptShape.getRadius() >= game.fourthOfScreen;
        boolean phaseTwo = promptShape.getRadius() >= game.thirdOfScreen;
        boolean phaseThree = promptShape.getRadius() >= game.fiveTwelfthsOfScreen;

        int phaseOneIndex = 0;
        int phaseTwoIndex = 1;
        int phaseThreeIndex = 2;

        float maxVolume = game.volume / 10f;

        if(phaseOne) {
            game.trackMap.get(game.track).get(phaseOneIndex).setVolume(maxVolume);
        } else {
            game.trackMap.get(game.track).get(phaseOneIndex).setVolume(0);
        }

        if(phaseTwo) {
            //TODO: set the volume to maxVolume once I've got .wavs that are of the correct volume
            game.trackMap.get(game.track).get(phaseTwoIndex).setVolume(maxVolume / 4f);
        } else {
            game.trackMap.get(game.track).get(phaseTwoIndex).setVolume(0);
        }

        if(phaseThree) {
            game.trackMap.get(game.track).get(phaseThreeIndex).setVolume(maxVolume);
        } else {
            game.trackMap.get(game.track).get(phaseThreeIndex).setVolume(0);
        }
    }

}
