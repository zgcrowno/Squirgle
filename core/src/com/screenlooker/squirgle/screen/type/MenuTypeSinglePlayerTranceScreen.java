package com.screenlooker.squirgle.screen.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.TranceScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

//TODO: Refactor all the music input behavior (create easier to read variables and such)
public class MenuTypeSinglePlayerTranceScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int PLAY = 0;
    private final static int MUSIC = 1;
    private final static int MUSIC_NAME = 2;
    private final static int BACK = 3;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 2;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_MIDDLE_PARTITIONS_VERTICAL = NUM_MIDDLE_INPUTS_VERTICAL + 1;

    private final static float FONT_TRACK_NAME_DIVISOR = 6.5f;

    private float inputWidth;
    private float inputHeightMiddle;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color playColor;
    private Color musicColor;
    private Color backColor;

    private boolean playTouched;
    private boolean backTouched;
    private boolean musicNamePointillismTouched;
    private boolean musicNameLineageTouched;
    private boolean musicNameTriTheWaltzTouched;
    private boolean musicNameSquaredOffTouched;
    private boolean musicNamePentUpTouched;
    private boolean musicNameHexidecibelTouched;
    private boolean musicNameInterseptorTouched;
    private boolean musicNameRoctopusTouched;
    private boolean musicNameNonplussedTouched;

    public MenuTypeSinglePlayerTranceScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightMiddle = (game.camera.viewportHeight - (game.partitionSize * NUM_MIDDLE_PARTITIONS_VERTICAL)) / NUM_MIDDLE_INPUTS_VERTICAL;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightMiddle ? (inputHeightMiddle / 2) : (inputWidth / 2);

        touchPoint = new Vector3();

        playColor = ColorUtils.randomColor();
        musicColor = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();

        playTouched = false;
        backTouched = false;

        game.setUpFontTrackName(MathUtils.round(inputShapeRadius / FONT_TRACK_NAME_DIVISOR));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);

        drawInputRectangles();

        game.shapeRendererFilled.end();

        drawMusicText();
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

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        playTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (2 * game.partitionSize) + inputHeightMiddle
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        musicNamePointillismTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2)
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameLineageTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - ((3 * game.fontTrackName.getCapHeight()) / 2)
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - ((3 * game.fontTrackName.getCapHeight()) / 2) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameTriTheWaltzTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (2 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (2 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameSquaredOffTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (3 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (3 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNamePentUpTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (4 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (4 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameHexidecibelTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (5 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (5 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameInterseptorTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (6 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (6 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameRoctopusTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (7 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (7 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameNonplussedTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (8 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (8 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;

        if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuTypeSinglePlayerScreen(game));
            dispose();
        } else if(musicNamePointillismTouched) {
            game.track = game.MUSIC_POINTILLISM;
        } else if(musicNameLineageTouched) {
            game.track = game.MUSIC_LINEAGE;
        } else if(musicNameTriTheWaltzTouched) {
            game.track = game.MUSIC_TRI_THE_WALTZ;
        } else if(musicNameSquaredOffTouched) {
            game.track = game.MUSIC_SQUARED_OFF;
        } else if(musicNamePentUpTouched) {
            if(game.maxBase > 4) {
                game.track = game.MUSIC_PENT_UP;
            }
        } else if(musicNameHexidecibelTouched) {
            if(game.maxBase > 5) {
                game.track = game.MUSIC_HEXIDECIBEL;
            }
        } else if(musicNameInterseptorTouched) {
            if(game.maxBase > 6) {
                game.track = game.MUSIC_INTERSEPTOR;
            }
        } else if(musicNameRoctopusTouched) {
            if(game.maxBase > 7) {
                game.track = game.MUSIC_ROCTOPUS;
            }
        } else if(musicNameNonplussedTouched) {
            if(game.maxBase > 8) {
                game.track = game.MUSIC_NONPLUSSED;
            }
        } else if(playTouched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.usePhases = false;
            game.setScreen(new TranceScreen(game));
        }

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

    public void drawInputRectangles() {
        drawTitle();
        drawPlayInput();
        drawBackInput();
        drawMusicInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case PLAY : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (2 * game.partitionSize) + inputHeightMiddle,
                        inputWidth,
                        inputHeightMiddle);
            }
            case BACK : {
                game.shapeRendererFilled.rect((3 * game.partitionSize) + (2 * inputWidth),
                        game.partitionSize,
                        inputWidth,
                        inputHeightBack);
            }
            case MUSIC : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        game.partitionSize,
                        inputWidth,
                        inputHeightMiddle);
            }
            case MUSIC_NAME : {
                for(int i = 0; i < game.maxBase; i++) {
                    if(game.track == i) {
                        game.shapeRendererFilled.rect(game.camera.viewportWidth / 2,
                                game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (i * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                                (inputWidth / 2) - game.partitionSize,
                                (7 * game.fontTrackName.getCapHeight()) / 4);
                    }
                }
            }
        }
    }

    public void drawPlayInput() {
        drawInputRectangle(PLAY, playColor);
        game.draw.drawPlayButton(game.camera.viewportWidth / 2,
                (3 * game.camera.viewportHeight) / 4,
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBackInput() {
        drawInputRectangle(BACK, backColor);
        game.draw.drawBackButton(game.camera.viewportWidth - game.partitionSize - (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawMusicInput() {
        drawInputRectangle(MUSIC, musicColor);
        game.draw.drawQuarterNote((game.camera.viewportWidth / 2) - (inputShapeRadius / 4) + ((inputShapeRadius / Draw.LINE_WIDTH_DIVISOR) / 2),
                game.partitionSize + (inputHeightMiddle / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
        drawMusicNameInput();
    }

    public void drawMusicNameInput() {
        drawInputRectangle(MUSIC_NAME, Color.BLACK);
    }

    public void drawMusicText() {
        for(int i = 0; i < game.maxBase; i++) {
            FontUtils.printText(game.batch,
                    game.fontTrackName,
                    game.layout,
                    game.track == i ? Color.WHITE : Color.BLACK,
                    game.musicTitleList.get(i),
                    (game.camera.viewportWidth / 2) + (inputWidth / 4),
                    game.partitionSize + inputHeightMiddle - ((inputHeightMiddle - (inputShapeRadius * 2)) / 2) - game.fontTrackName.getCapHeight() - (i * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                    0,
                    1);
        }
    }

    public void drawTitle() {
        game.draw.drawPlayButton(game.camera.viewportWidth / 6,
                (5 * game.camera.viewportHeight) / 6,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                game.shapeRendererFilled);

        game.draw.drawFace(game.camera.viewportWidth / 6,
                game.camera.viewportHeight / 2,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);

        game.draw.drawTranceSymbol(game.camera.viewportWidth / 6,
                game.camera.viewportHeight / 6,
                symbolRadius / 3,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
    }
}
