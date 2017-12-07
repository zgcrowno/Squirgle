package com.screenlooker.squirgle.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.screenlooker.squirgle.Shape;

import java.awt.color.ColorSpace;

public class ColorUtils {

    public static final float MAX_RGB_VALUE = 255;
    public static final int COLOR_TRANSITION_SPEED = 5;

    public static Color randomColor() {
        final int orange = 0;
        final int skyBlue = 1;
        final int bluishGreen = 2;
        final int blue = 3;
        final int vermillion = 4;
        final int reddishPurple = 5;
        final int color = MathUtils.random(5);

        switch(color) {
            case orange : return new Color(230/MAX_RGB_VALUE, 159/MAX_RGB_VALUE, 0, 1);
            case skyBlue : return new Color(86/MAX_RGB_VALUE, 180/MAX_RGB_VALUE, 233/MAX_RGB_VALUE, 1);
            case bluishGreen : return new Color(0, 158/MAX_RGB_VALUE, 115/MAX_RGB_VALUE, 1);
            case blue : return new Color(0, 114/MAX_RGB_VALUE, 178/MAX_RGB_VALUE, 1);
            case vermillion : return new Color(213/MAX_RGB_VALUE, 94/MAX_RGB_VALUE, 0, 1);
            case reddishPurple : return new Color(204/MAX_RGB_VALUE, 121/MAX_RGB_VALUE, 167/MAX_RGB_VALUE, 1);
        }

        return null;
    }

    public static void transitionColor(Shape passedShape) {
        Color color = passedShape.getColor();

        //If passedShape's color is not transitionable, then generate one we can work with
        if(!isTransitionable(color)) {
            color = randomTransitionColor();
        }

        //Transition the RGB values
        if(color.b <= 0 && color.g >= 1) {
            if(color.r < 1) {
                color.r += COLOR_TRANSITION_SPEED/MAX_RGB_VALUE;
            }
        } else if(color.b >= 1 && color.g <= 0) {
            if(color.r > 0) {
                color.r -= COLOR_TRANSITION_SPEED/MAX_RGB_VALUE;
            }
        }
        if(color.r <= 0 && color.b >= 1) {
            if(color.g < 1) {
                color.g += COLOR_TRANSITION_SPEED/MAX_RGB_VALUE;
            }
        } else if(color.r >= 1 && color.b <= 0) {
            if(color.g > 0) {
                color.g -= COLOR_TRANSITION_SPEED/MAX_RGB_VALUE;
            }
        }
        if(color.r >= 1 && color.g <= 0) {
            if(color.b < 1) {
                color.b += COLOR_TRANSITION_SPEED/MAX_RGB_VALUE;
            }
        } else if(color.r <= 0 && color.g >= 1) {
            if(color.b > 0) {
                color.b -= COLOR_TRANSITION_SPEED/MAX_RGB_VALUE;
            }
        }

        //Prevent the RGB values from becoming too large or small
        if(color.r > 1) {
            color.r = 1;
        } else if(color.r < 0) {
            color.r = 0;
        }
        if(color.g > 1) {
            color.g = 1;
        } else if(color.g < 0) {
            color.g = 0;
        }
        if(color.b > 1) {
            color.b = 1;
        } else if(color.b < 0) {
            color.b = 0;
        }

        passedShape.setColor(color);
    }

    public static Color randomTransitionColor() {
        int random = MathUtils.random(5);

        switch(random) {
            case 0 : return new Color(MAX_RGB_VALUE/MAX_RGB_VALUE, 0/MAX_RGB_VALUE, 0/MAX_RGB_VALUE, 1);
            case 1 : return new Color(MAX_RGB_VALUE/MAX_RGB_VALUE, 0/MAX_RGB_VALUE, MAX_RGB_VALUE/MAX_RGB_VALUE, 1);
            case 2 : return new Color(0/MAX_RGB_VALUE, 0/MAX_RGB_VALUE, MAX_RGB_VALUE/MAX_RGB_VALUE, 1);
            case 3 : return new Color(0/MAX_RGB_VALUE, MAX_RGB_VALUE/MAX_RGB_VALUE, MAX_RGB_VALUE/MAX_RGB_VALUE, 1);
            case 4 : return new Color(0/MAX_RGB_VALUE, MAX_RGB_VALUE/MAX_RGB_VALUE, 0/MAX_RGB_VALUE, 1);
            case 5 : return new Color(MAX_RGB_VALUE/MAX_RGB_VALUE, MAX_RGB_VALUE/MAX_RGB_VALUE, 0/MAX_RGB_VALUE, 1);
        }

        return null;
    }

    public static boolean isTransitionable(Color color) {
        return (color.r == 0 || color.g == 0 || color.b == 0) && !(color.r == 0 && color.g == 0 && color.b == 0);
    }

}
