package com.screenlooker.squirgle.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class FontUtils {

    public static void printText(SpriteBatch spriteBatch,
                                 BitmapFont bitmapFont, GlyphLayout layout, Color color, String text, float posX, float posY, float angle)
    {
        Matrix4 oldTransformMatrix = spriteBatch.getTransformMatrix().cpy();

        Matrix4 mx4Font = new Matrix4();
        mx4Font.rotate(new Vector3(0, 0, 1), angle);
        mx4Font.trn(posX, posY, 0);
        spriteBatch.setTransformMatrix(mx4Font);
        layout.setText(bitmapFont, text);

        spriteBatch.begin();
        bitmapFont.setColor(color);
        bitmapFont.draw(spriteBatch, text, -(layout.width / 2), layout.height / 2);
        spriteBatch.end();

        spriteBatch.setTransformMatrix(oldTransformMatrix);
    }

}
