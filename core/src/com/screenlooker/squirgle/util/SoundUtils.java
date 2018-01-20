package com.screenlooker.squirgle.util;

import com.badlogic.gdx.math.MathUtils;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;

import java.util.List;

public class SoundUtils {

    public static void playMusic(Shape promptShape, Squirgle game) {
        float maxVolume = game.volume / 10f;

        if(game.usePhases) {
            boolean phaseOne = promptShape.getRadius() >= game.fourthOfScreen;
            boolean phaseTwo = promptShape.getRadius() >= game.thirdOfScreen;
            boolean phaseThree = promptShape.getRadius() >= game.fiveTwelfthsOfScreen;

            int phaseOneIndex = 0;
            int phaseTwoIndex = 1;
            int phaseThreeIndex = 2;

            if (phaseOne) {
                game.trackMapPhase.get(game.track).get(phaseOneIndex).setVolume(maxVolume);
            } else {
                game.trackMapPhase.get(game.track).get(phaseOneIndex).setVolume(0);
            }

            if (phaseTwo) {
                //TODO: set the volume to maxVolume once I've got .wavs that are of the correct volume
                game.trackMapPhase.get(game.track).get(phaseTwoIndex).setVolume(maxVolume / 4f);
            } else {
                game.trackMapPhase.get(game.track).get(phaseTwoIndex).setVolume(0);
            }

            if (phaseThree) {
                game.trackMapPhase.get(game.track).get(phaseThreeIndex).setVolume(maxVolume);
            } else {
                game.trackMapPhase.get(game.track).get(phaseThreeIndex).setVolume(0);
            }
        } else {
            game.trackMapFull.get(game.track).setVolume(maxVolume);
        }
    }

}
