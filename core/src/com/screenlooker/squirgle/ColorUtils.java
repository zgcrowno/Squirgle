package com.screenlooker.squirgle;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;

public class ColorUtils {

    //TODO: Use less harsh colors...colors that conflict less with black and white
    public static com.badlogic.gdx.graphics.Color randomPrimary() {
        final int blue = 0;
        final int green = 1;
        final int red = 2;
        final int yellow = 3;
        final int cyan = 4;
        final int magenta = 5;
        final int color = MathUtils.random(5);

        switch(color) {
            case blue : return Color.ROYAL;
            case green : return Color.LIME;
            case red : return Color.FIREBRICK;
            case yellow : return Color.ORANGE;
            case cyan : return Color.CYAN;
            case magenta : return Color.MAGENTA;
        }

        return null;
    }

}
