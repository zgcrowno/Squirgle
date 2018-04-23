package com.screenlooker.squirgle.util;

import com.badlogic.gdx.Gdx;
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

}
