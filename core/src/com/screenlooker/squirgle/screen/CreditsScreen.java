package com.screenlooker.squirgle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.codedisaster.steamworks.SteamAPI;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Shape;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class CreditsScreen implements Screen, InputProcessor {

    final Squirgle game;

    public static final int SKIP_TEXT_DISAPPEARANCE_TIME = 5;
    public static final int ONE_THOUSAND = 1000;
    public static final int FONT_SKIP_SIZE_DIVISOR = 30;

    public static final float FONT_CREDITS_SIZE_DIVISOR = 6.5f;

    private static final float RADIUS_INCREMENT = 1f;
    private static final float ROTATION_INCREMENT = 0.01f;
    private static final float OPACITY_INCREMENT = 0.05f;

    private static final String DESIGN_PROGRAMMING_ART = "DESIGN/PROGRAMMING/ART";
    private static final String MUSIC = "MUSIC";
    private static final String QA = "QA";
    private static final String MULTIMEDIA_PRODUCTION = "MULTIMEDIA PRODUCTION";
    private static final String ZACHARY_CROWNOVER = "ZACHARY CROWNOVER";
    private static final String NAN = "NaN";
    private static final String CARL_JOSEPH_EKSTAM = "CARL JOSEPH EKSTAM";
    private static final String AARON_HAMILTON = "AARON HAMILTON";
    private static final String DARRYL_LOYD = "DARRYL LOYD";
    private static final String IAN_MOLICA = "IAN MOLICA";
    private static final String BRETT_NECKERMANN = "BRETT NECKERMANN";
    private static final String DUSTIN_RAUCH = "DUSTIN RAUCH";
    private static final String ALLISON_WILLIAMS = "ALLISON WILLIAMS";
    private static final String JEFF_WINTERS = "JEFF WINTERS";
    private static final String CODY_WILLIAMS = "CODY WILLIAMS";
    private static final String DANE_WOMMACK = "DANE WOMMACK";
    private static final String HARRISON_PALMER = "HARRISON PALMER";
    private static final String SELAH_SNOWDEN = "SELAH SNOWDEN";
    private static final String SQUIRGLER_SUPREME = "SQUIRGLER SUPREME";
    private static final String YOU = "YOU!";
    private static final String SO_THANK_YOU = "SO THANK YOU";
    private static final String AND_KEEP_SQUIRGLIN = "AND KEEP SQUIRGLIN'";

    private static final String SKIP_TEXT = "TAP AGAIN TO SKIP";

    private Color backgroundColor;
    private Color veilColor;

    private Shape currentShape;

    private float shapeIncrease;
    private float shapeRadius;
    private float shapeRotation;
    private float shapePauseRadius;
    private float shapeResumeRadius;
    private float veilOpacity;
    private float textOpacity;

    private long timeSinceTouched;

    private List<String> stringList;
    private List<Shape> shapeList;

    public CreditsScreen(final Squirgle game, Color backgroundColor) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        this.backgroundColor = backgroundColor;
        this.veilColor = Color.BLACK;
        this.shapeIncrease = 1.1f;
        this.shapeRadius = 1;
        this.shapeRotation = 0;
        this.shapePauseRadius = game.widthOrHeightSmaller / 4;
        this.shapeResumeRadius = shapePauseRadius + ((RADIUS_INCREMENT * game.FPS) * 2);
        this.veilOpacity = 0;
        this.textOpacity = 0;
        this.timeSinceTouched = SKIP_TEXT_DISAPPEARANCE_TIME;
        this.stringList = new ArrayList<String>();
        this.shapeList = new ArrayList<Shape>();

        stringList.add(DESIGN_PROGRAMMING_ART);
        stringList.add(ZACHARY_CROWNOVER);
        stringList.add(MUSIC);
        stringList.add(NAN);
        stringList.add(QA);
        stringList.add(CARL_JOSEPH_EKSTAM);
        stringList.add(AARON_HAMILTON);
        stringList.add(DARRYL_LOYD);
        stringList.add(IAN_MOLICA);
        stringList.add(BRETT_NECKERMANN);
        stringList.add(DUSTIN_RAUCH);
        stringList.add(ALLISON_WILLIAMS);
        stringList.add(CODY_WILLIAMS);
        stringList.add(JEFF_WINTERS);
        stringList.add(DANE_WOMMACK);
        stringList.add(MULTIMEDIA_PRODUCTION);
        stringList.add(HARRISON_PALMER);
        stringList.add(SELAH_SNOWDEN);
        stringList.add(CODY_WILLIAMS);
        stringList.add(SQUIRGLER_SUPREME);
        stringList.add(YOU);
        stringList.add(SO_THANK_YOU);
        stringList.add(AND_KEEP_SQUIRGLIN);

        for(int i = 0; i < stringList.size(); i++) {
            shapeList.add(new Shape(MathUtils.random(Shape.PENTAGON, Shape.NONAGON),
                    shapeRadius,
                    i == 0 && backgroundColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK,
                    null,
                    shapeRadius / Draw.LINE_WIDTH_DIVISOR,
                    new Vector2(game.camera.viewportWidth / 2,
                            game.camera.viewportHeight / 2)));

            //TODO: Update this when updating the contributors list.
            if(stringList.get(i).equals(DESIGN_PROGRAMMING_ART)
                    || stringList.get(i).equals(MUSIC)
                    || stringList.get(i).equals(QA)
                    || stringList.get(i).equals(MULTIMEDIA_PRODUCTION)
                    || stringList.get(i).equals(SQUIRGLER_SUPREME)) {
                Color newBackgroundColor = new Color();
                if(stringList.get(i).equals(DESIGN_PROGRAMMING_ART)) {
                    newBackgroundColor = ColorUtils.COLOR_SKY_BLUE;
                } else if(stringList.get(i).equals(MUSIC)) {
                    newBackgroundColor = ColorUtils.COLOR_BLUE;
                } else if(stringList.get(i).equals(QA)) {
                    newBackgroundColor = ColorUtils.COLOR_ORANGE;
                } else if(stringList.get(i).equals(MULTIMEDIA_PRODUCTION)) {
                    newBackgroundColor = ColorUtils.COLOR_VERMILLION;
                } else if(stringList.get(i).equals(SQUIRGLER_SUPREME)) {
                    newBackgroundColor = ColorUtils.COLOR_BLUISH_GREEN;
                }
                shapeList.add(new Shape(Shape.CIRCLE,
                        shapeRadius,
                        newBackgroundColor,
                        null,
                        (shapeRadius - (shapeRadius / Draw.LINE_WIDTH_DIVISOR)) / Draw.LINE_WIDTH_DIVISOR,
                        new Vector2(game.camera.viewportWidth / 2,
                                game.camera.viewportHeight / 2)));
            }
        }

        this.currentShape = shapeList.get(0);

        game.setUpFontCredits(MathUtils.round(shapePauseRadius / FONT_CREDITS_SIZE_DIVISOR));
        game.setUpFontSkip(MathUtils.round(game.camera.viewportWidth / FONT_SKIP_SIZE_DIVISOR));

        playMusic();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        if(!shapeList.isEmpty()) {
            currentShape = shapeList.get(0);
            boolean displayText = currentShape.getRadius() > shapePauseRadius && currentShape.getRadius() < shapeResumeRadius;
            if (!displayText) {
                currentShape.setRadius(currentShape.getRadius() * shapeIncrease);
                if (textOpacity > 0) {
                    textOpacity -= OPACITY_INCREMENT;
                }
            } else {
                currentShape.setRadius(currentShape.getRadius() + RADIUS_INCREMENT);
                if (textOpacity < 1) {
                    textOpacity += OPACITY_INCREMENT;
                }
            }

            //TODO: Update this when updating the contributors list.
            if(stringList.get(0).equals(DESIGN_PROGRAMMING_ART)
                    || stringList.get(0).equals(MUSIC)
                    || stringList.get(0).equals(QA)
                    || stringList.get(0).equals(MULTIMEDIA_PRODUCTION)
                    || stringList.get(0).equals(SQUIRGLER_SUPREME)) {
                if(currentShape.getShape() < Shape.SEPTAGON) {
                    shapeList.get(1).setRadius(currentShape.getRadius() - (1.83f * (currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR)));
                } else if(currentShape.getShape() < Shape.NONAGON) {
                    shapeList.get(1).setRadius(currentShape.getRadius() - (1.75f * (currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR)));
                } else {
                    shapeList.get(1).setRadius(currentShape.getRadius() - (currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR));
                }
                game.draw.drawShape(false, shapeList.get(1));
            }

            if (currentShape.getShape() == Shape.PENTAGON) {
                game.draw.drawPentagon(currentShape.getCoordinates().x,
                        currentShape.getCoordinates().y,
                        currentShape.getRadius(),
                        currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR,
                        shapeRotation,
                        Color.BLACK);
            } else if (currentShape.getShape() == Shape.HEXAGON) {
                game.draw.drawHexagon(currentShape.getCoordinates().x,
                        currentShape.getCoordinates().y,
                        currentShape.getRadius(),
                        currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR,
                        shapeRotation,
                        Color.BLACK);
            } else if (currentShape.getShape() == Shape.SEPTAGON) {
                game.draw.drawSeptagon(currentShape.getCoordinates().x,
                        currentShape.getCoordinates().y,
                        currentShape.getRadius(),
                        currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR,
                        shapeRotation,
                        Color.BLACK);
            } else if (currentShape.getShape() == Shape.OCTAGON) {
                game.draw.drawOctagon(currentShape.getCoordinates().x,
                        currentShape.getCoordinates().y,
                        currentShape.getRadius(),
                        currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR,
                        shapeRotation,
                        Color.BLACK);
            } else if (currentShape.getShape() == Shape.NONAGON) {
                game.draw.drawNonagon(currentShape.getCoordinates().x,
                        currentShape.getCoordinates().y,
                        currentShape.getRadius(),
                        currentShape.getRadius() / Draw.LINE_WIDTH_DIVISOR,
                        shapeRotation,
                        Color.BLACK);
            }
        } else {
            fadeOutMusic();
            veilOpacity += OPACITY_INCREMENT;
        }

        game.draw.drawVeil(veilColor, veilOpacity);

        if((System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < SKIP_TEXT_DISAPPEARANCE_TIME) {
            drawSkipTextBox();
        }

        if(game.desktop) {
            game.draw.drawCursor();
        }

        game.shapeRendererFilled.end();

        drawText();

        shapeRotation += ROTATION_INCREMENT;

        destroyOversizedShapesAndAssociatedStrings();

        if(veilOpacity >= 1) {
            game.trackMapFull.get(game.MUSIC_SQUARED_OFF).stop();
            game.setScreen(new MainMenuScreen(game, veilColor));
        }

        InputUtils.keepCursorInBounds(game);

        //Steam callbacks
        if (SteamAPI.isSteamRunning()) {
            SteamAPI.runCallbacks();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        if((System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < SKIP_TEXT_DISAPPEARANCE_TIME) {
            game.trackMapFull.get(game.MUSIC_SQUARED_OFF).stop();
            game.setScreen(new MainMenuScreen(game, backgroundColor));
        }

        timeSinceTouched = System.currentTimeMillis();

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void drawText() {
        if(!stringList.isEmpty()) {
            FontUtils.printText(game.batch,
                    game.fontCredits,
                    game.layout,
                    Color.BLACK,
                    stringList.get(0),
                    game.camera.viewportWidth / 2,
                    game.camera.viewportHeight / 2,
                    0,
                    textOpacity);
        }
        if((System.currentTimeMillis() - timeSinceTouched) / ONE_THOUSAND < SKIP_TEXT_DISAPPEARANCE_TIME) {
            drawSkipText();
        }
    }

    public void destroyOversizedShapesAndAssociatedStrings() {
        //Prevent shapes from getting too large
        if(!shapeList.isEmpty()) {
            if (currentShape.getRadius() >= game.widthOrHeightBigger) {
                shapeList.remove(0);
                //TODO: Update this when updating the contributors list.
                if(stringList.get(0).equals(DESIGN_PROGRAMMING_ART)
                        || stringList.get(0).equals(MUSIC)
                        || stringList.get(0).equals(QA)
                        || stringList.get(0).equals(MULTIMEDIA_PRODUCTION)
                        || stringList.get(0).equals(SQUIRGLER_SUPREME)) {
                    backgroundColor = shapeList.get(0).getColor();
                    shapeList.remove(0);
                }
                stringList.remove(0);
                textOpacity = 0;
            }
        }
    }

    public void playMusic() {
        game.trackMapFull.get(game.MUSIC_SQUARED_OFF).setVolume((float) (game.volume / 10.0));
        game.trackMapFull.get(game.MUSIC_SQUARED_OFF).play();
    }

    public void fadeOutMusic() {
        Music track = game.trackMapFull.get(game.MUSIC_SQUARED_OFF);
        track.setVolume(track.getVolume() - (track.getVolume() * OPACITY_INCREMENT));
        track.dispose();
    }

    public void drawSkipTextBox() {
        game.draw.rect((3 * game.camera.viewportWidth) / 8,
                game.camera.viewportHeight / 4,
                game.camera.viewportWidth / 4,
                game.camera.viewportHeight / 10,
                Color.WHITE);
    }

    public void drawSkipText() {
        FontUtils.printText(game.batch,
                game.fontSkip,
                game.layout,
                Color.BLACK,
                SKIP_TEXT,
                game.camera.viewportWidth / 2,
                (game.camera.viewportHeight / 4) + (game.camera.viewportHeight / 20) + (game.fontSkip.getCapHeight() / 4),
                0,
                1);
    }
}
