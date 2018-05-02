package com.screenlooker.squirgle.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.screenlooker.squirgle.Squirgle;

public class InputUtils {

    public static void keepCursorInBounds(Squirgle game) {
        if(Gdx.input.getX() > game.camera.viewportWidth) {
            Gdx.input.setCursorPosition(MathUtils.round(game.camera.viewportWidth), Gdx.input.getY());
        } else if(Gdx.input.getX() < 0) {
            Gdx.input.setCursorPosition(0, Gdx.input.getY());
        }
        if(Gdx.input.getY() > game.camera.viewportHeight) {
            Gdx.input.setCursorPosition(Gdx.input.getX(), MathUtils.round(game.camera.viewportHeight));
        } else if(Gdx.input.getY() < 0) {
            Gdx.input.setCursorPosition(Gdx.input.getX(), 0);
        }
    }

    public static boolean isNumberKeycode(int keycode) {
        return keycode == Input.Keys.NUM_0
                || keycode == Input.Keys.NUM_1
                || keycode == Input.Keys.NUM_2
                || keycode == Input.Keys.NUM_3
                || keycode == Input.Keys.NUM_4
                || keycode == Input.Keys.NUM_5
                || keycode == Input.Keys.NUM_6
                || keycode == Input.Keys.NUM_7
                || keycode == Input.Keys.NUM_8
                || keycode == Input.Keys.NUM_9;
    }

    public static boolean isNumpadKeycode(int keycode) {
        return keycode == Input.Keys.NUMPAD_0
                || keycode == Input.Keys.NUMPAD_1
                || keycode == Input.Keys.NUMPAD_2
                || keycode == Input.Keys.NUMPAD_3
                || keycode == Input.Keys.NUMPAD_4
                || keycode == Input.Keys.NUMPAD_5
                || keycode == Input.Keys.NUMPAD_6
                || keycode == Input.Keys.NUMPAD_7
                || keycode == Input.Keys.NUMPAD_8
                || keycode == Input.Keys.NUMPAD_9;
    }

}
