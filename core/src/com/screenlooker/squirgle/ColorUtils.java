package com.screenlooker.squirgle;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;

public class ColorUtils {

    //TODO: Use less harsh colors...colors that conflict less with black and white
    public static com.badlogic.gdx.graphics.Color randomColor() {
        final int orange = 0;
        final int skyBlue = 1;
        final int bluishGreen = 2;
        final int blue = 3;
        final int vermillion = 4;
        final int reddishPurple = 5;
        final int color = MathUtils.random(5);

        switch(color) {
            case orange : return new Color(230/255f, 159/255f, 0, 1);
            case skyBlue : return new Color(86/255f, 180/255f, 233/255f, 1);
            case bluishGreen : return new Color(0, 158/255f, 115/255f, 1);
            case blue : return new Color(0, 114/255f, 178/255f, 1);
            case vermillion : return new Color(213/255f, 94/255f, 0, 1);
            case reddishPurple : return new Color(204/255f, 121/255f, 167/255f, 1);
        }

        return null;
    }

}
