package com.screenlooker.squirgle;

import com.badlogic.gdx.math.MathUtils;

import java.util.List;

public class SoundUtils {

    //TODO: Maybe replace this with composed music as opposed to computer generated?
    public static void playMusic(Shape promptShape, Squirgle game) {
        boolean phaseOne = promptShape.getRadius() >= game.fourthOfScreen;
        boolean phaseTwo = promptShape.getRadius() >= game.thirdOfScreen;
        boolean phaseThree = promptShape.getRadius() >= game.fiveTwelfthsOfScreen;

        int phaseOneIndex = 0;
        int phaseTwoIndex = 1;
        int phaseThreeIndex = 2;

        float maxVolume = game.volume / 10;

        if(phaseOne) {
            game.trackMap.get(game.track).get(phaseOneIndex).setVolume(maxVolume);
        } else {
            game.trackMap.get(game.track).get(phaseOneIndex).setVolume(0);
        }

        if(phaseTwo) {
            //TODO: set the volume to maxVolume once I've got .wavs that are of the correct volume
            game.trackMap.get(game.track).get(phaseTwoIndex).setVolume(maxVolume / 4);
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
