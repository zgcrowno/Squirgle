package com.screenlooker.squirgle;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;

public class ColorUtils {

    public static com.badlogic.gdx.graphics.Color randomPrimary() {
        int blue = 0;
        int green = 1;
        int red = 2;
        int yellow = 3;
        int cyan = 4;
        int magenta = 5;
        int color = MathUtils.random(5);

        if(color == blue) {
            return Color.BLUE;
        } else if(color == green) {
            return Color.GREEN;
        } else if(color == red) {
            return Color.RED;
        } else if(color == yellow) {
            return Color.YELLOW;
        } else if(color == cyan) {
            return Color.CYAN;
        } else {
            return Color.MAGENTA;
        }
    }

}
