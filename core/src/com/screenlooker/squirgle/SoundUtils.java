package com.screenlooker.squirgle;

import java.util.List;

public class SoundUtils {

    public static void playMusic(int timeSignature, float measureLength, float colorListSpeed, List<Shape> backgroundColorShapeList, Squirgle game) {
        //TODO: Maybe move the playing of the bass drum (currently in Draw) here
        for(int i = 1; i <= timeSignature; i++) {
            if(backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y > 0 - colorListSpeed
                    && backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y < 0 + colorListSpeed) {
                game.bassDrum.play();
            }
            if(backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y > ((i * measureLength) / timeSignature) - colorListSpeed
                    && backgroundColorShapeList.get(0).getCoordinates().y - backgroundColorShapeList.get(1).getCoordinates().y < ((i * measureLength) / timeSignature) + colorListSpeed) {
                game.hiHat.play();
            }
        }
    }

}
