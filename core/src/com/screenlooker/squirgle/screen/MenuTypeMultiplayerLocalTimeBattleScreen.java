package com.screenlooker.squirgle.screen;

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
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

//TODO: Refactor all the music input behavior (create easier to read variables and such)
public class MenuTypeMultiplayerLocalTimeBattleScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static int BASE_4 = 0;
    private final static int BASE_5 = 1;
    private final static int BASE_6 = 2;
    private final static int BASE_7 = 3;
    private final static int BASE_8 = 4;
    private final static int BASE_9 = 5;
    private final static int BACK = 6;
    private final static int MUSIC = 7;
    private final static int MUSIC_TYPE = 8;
    private final static int MUSIC_NAME = 9;
    private final static int TIME = 10;

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_TIME_INPUT_ELEMENTS = 4;

    private final static float FONT_TRACK_NAME_DIVISOR = 6.5f;
    private final static float FONT_TRACK_TYPE_DIVISOR = 2f;
    private final static float FONT_TIME_DIVISOR = 1.2f;

    private int numberOfBaseInputs;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightBase;
    private float inputHeightBack;

    private float symbolRadius;

    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color base4Color;
    private Color base5Color;
    private Color base6Color;
    private Color base7Color;
    private Color base8Color;
    private Color base9Color;
    private Color backColor;
    private Color musicColor;
    private Color timeColor;

    private boolean base4Touched;
    private boolean base5Touched;
    private boolean base6Touched;
    private boolean base7Touched;
    private boolean base8Touched;
    private boolean base9Touched;
    private boolean backTouched;
    private boolean musicTypeFullTouched;
    private boolean musicTypeSplitTouched;
    private boolean musicNamePointillismTouched;
    private boolean musicNameLineageTouched;
    private boolean musicNameTriTheWaltzTouched;
    private boolean musicNameSquaredOffTouched;
    private boolean musicNamePentUpTouched;
    private boolean musicNameHexidecibelTouched;
    private boolean musicNameInterseptorTouched;
    private boolean musicNameRoctopusTouched;
    private boolean musicNameNonplussedTouched;
    private boolean timeDownChevronTouched;
    private boolean timeUpChevronTouched;

    public MenuTypeMultiplayerLocalTimeBattleScreen(final Squirgle game) {
        this.game = game;

        game.resetInstanceData();

        Gdx.input.setInputProcessor(this);

        numberOfBaseInputs = game.maxBase - game.minBase + 1;

        numMiddleInputsVertical = numberOfBaseInputs + 2; //Adding 2 to account for music and time inputs
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightBase = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL);

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightBase ? (inputHeightBase / 2) : (inputWidth / 2);

        touchPoint = new Vector3();

        base4Color = ColorUtils.randomColor();
        base5Color = ColorUtils.randomColor();
        base6Color = ColorUtils.randomColor();
        base7Color = ColorUtils.randomColor();
        base8Color = ColorUtils.randomColor();
        base9Color = ColorUtils.randomColor();
        backColor = ColorUtils.randomColor();
        musicColor = ColorUtils.randomColor();
        timeColor = ColorUtils.randomColor();

        base4Touched = false;
        base5Touched = false;
        base6Touched = false;
        base7Touched = false;
        base8Touched = false;
        base9Touched = false;
        backTouched = false;

        game.setUpFontTrackName(MathUtils.round(inputShapeRadius / FONT_TRACK_NAME_DIVISOR));
        game.setUpFontTrackType(MathUtils.round(inputShapeRadius / FONT_TRACK_TYPE_DIVISOR));
        game.setUpFontTime(MathUtils.round(inputShapeRadius / FONT_TIME_DIVISOR));
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
        drawTimeText();
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

        base4Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (3 * game.partitionSize) + (2 * inputHeightBase)
                && touchPoint.y < (3 * game.partitionSize) + (3 * inputHeightBase);
        base5Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (4 * game.partitionSize) + (3 *inputHeightBase)
                && touchPoint.y < (4 * game.partitionSize) + (4 * inputHeightBase);
        base6Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (5 * game.partitionSize) + (4 *inputHeightBase)
                && touchPoint.y < (5 * game.partitionSize) + (5 * inputHeightBase);
        base7Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (6 * game.partitionSize) + (5 *inputHeightBase)
                && touchPoint.y < (6 * game.partitionSize) + (6 * inputHeightBase);
        base8Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (7 * game.partitionSize) + (6 *inputHeightBase)
                && touchPoint.y < (7 * game.partitionSize) + (7 * inputHeightBase);
        base9Touched = touchPoint.x > (2 * game.partitionSize) + inputWidth
                && touchPoint.x < (2 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.y > (8 * game.partitionSize) + (57 *inputHeightBase)
                && touchPoint.y < (8 * game.partitionSize) + (8 * inputHeightBase);
        backTouched = touchPoint.x > (3 * game.partitionSize) + (2 * inputWidth)
                && touchPoint.x < game.camera.viewportWidth - game.partitionSize
                && touchPoint.y > game.partitionSize
                && touchPoint.y < game.camera.viewportHeight - game.partitionSize;
        musicTypeFullTouched = touchPoint.x > (3 * game.partitionSize) + inputWidth
                && touchPoint.x < (game.camera.viewportWidth / 2) - game.partitionSize
                && touchPoint.y > game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((3 * (inputShapeRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4)
                && touchPoint.y < game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((3 * (inputShapeRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4) + game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5);
        musicTypeSplitTouched = touchPoint.x > (3 * game.partitionSize) + inputWidth
                && touchPoint.x < (game.camera.viewportWidth / 2) - game.partitionSize
                && touchPoint.y > game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((2 * (inputShapeRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4)
                && touchPoint.y < game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((2 * (inputShapeRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4) + game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5);
        musicNamePointillismTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2)
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameLineageTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - ((3 * game.fontTrackName.getCapHeight()) / 2)
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - ((3 * game.fontTrackName.getCapHeight()) / 2) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameTriTheWaltzTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (2 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (2 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameSquaredOffTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (3 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (3 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNamePentUpTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (4 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (4 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameHexidecibelTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (5 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (5 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameInterseptorTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (6 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (6 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameRoctopusTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (7 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (7 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        musicNameNonplussedTouched = touchPoint.x > game.camera.viewportWidth / 2
                && touchPoint.x < game.partitionSize + (2 * inputWidth)
                && touchPoint.y > game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (8 * ((3 * game.fontTrackName.getCapHeight()) / 2))
                && touchPoint.y < game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (8 * ((3 * game.fontTrackName.getCapHeight()) / 2)) + ((7 * game.fontTrackName.getCapHeight()) / 4);
        timeDownChevronTouched = touchPoint.x > ((2 * game.partitionSize) + inputWidth + ((2 * inputWidth) / 5)) - (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1))
                && touchPoint.x < ((2 * game.partitionSize) + inputWidth + ((2 * inputWidth) / 5)) + (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1))
                && touchPoint.y > ((2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2)) - (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1))
                && touchPoint.y < ((2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2)) + (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1));
        timeUpChevronTouched = touchPoint.x > (2 * game.partitionSize) + inputWidth + ((4 * inputWidth) / 5) - (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1))
                && touchPoint.x < (2 * game.partitionSize) + inputWidth + ((4 * inputWidth) / 5) + (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1))
                && touchPoint.y > ((2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2)) - (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1))
                && touchPoint.y < ((2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2)) + (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1));

        if(base4Touched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 4;
            game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
            game.updateSave(game.SAVE_TRACK, game.track);
            game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
            dispose();
        } else if(base5Touched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 5;
            game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
            game.updateSave(game.SAVE_TRACK, game.track);
            game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
            dispose();
        } else if(base6Touched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 6;
            game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
            game.updateSave(game.SAVE_TRACK, game.track);
            game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
            dispose();
        } else if(base7Touched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 7;
            game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
            game.updateSave(game.SAVE_TRACK, game.track);
            game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
            dispose();
        } else if(base8Touched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 8;
            game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
            game.updateSave(game.SAVE_TRACK, game.track);
            game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
            dispose();
        } else if(base9Touched) {
            game.trackMapFull.get(game.MUSIC_THEME_FROM_SQUIRGLE).stop();
            game.confirmSound.play((float) (game.volume / 10.0));
            game.base = 9;
            game.updateSave(game.SAVE_USE_PHASES, game.usePhases);
            game.updateSave(game.SAVE_TRACK, game.track);
            game.setScreen(new GameplayScreen(game, Squirgle.GAMEPLAY_TIME_BATTLE_LOCAL));
            dispose();
        } else if(backTouched) {
            game.disconfirmSound.play((float) (game.volume / 10.0));
            game.setScreen(new MenuTypeMultiplayerLocalScreen(game));
            dispose();
        } else if(musicTypeFullTouched) {
            game.usePhases = false;
        } else if(musicTypeSplitTouched) {
            game.usePhases = true;
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
        } else if(timeDownChevronTouched) {
            if(game.timeAttackNumSeconds == game.ONE_MINUTE) {
                game.timeAttackNumSeconds = game.FIVE_MINUTES;
            } else if(game.timeAttackNumSeconds == game.THREE_MINUTES) {
                game.timeAttackNumSeconds = game.ONE_MINUTE;
            } else if(game.timeAttackNumSeconds == game.FIVE_MINUTES) {
                game.timeAttackNumSeconds = game.THREE_MINUTES;
            }
        } else if(timeUpChevronTouched) {
            if(game.timeAttackNumSeconds == game.ONE_MINUTE) {
                game.timeAttackNumSeconds = game.THREE_MINUTES;
            } else if(game.timeAttackNumSeconds == game.THREE_MINUTES) {
                game.timeAttackNumSeconds = game.FIVE_MINUTES;
            } else if(game.timeAttackNumSeconds == game.FIVE_MINUTES) {
                game.timeAttackNumSeconds = game.ONE_MINUTE;
            }
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
        drawBase4Input();
        if(game.maxBase >= 5) {
            drawBase5Input();
        }
        if(game.maxBase >= 6) {
            drawBase6Input();
        }
        if(game.maxBase >= 7) {
            drawBase7Input();
        }
        if(game.maxBase >= 8) {
            drawBase8Input();
        }
        if(game.maxBase >= 9) {
            drawBase9Input();
        }
        drawBackInput();
        drawMusicInput();
        drawTimeInput();
    }

    public void drawInputRectangle(int placement, Color color) {
        game.shapeRendererFilled.setColor(color);
        switch(placement) {
            case BASE_4 : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (3 * game.partitionSize) + (2 * inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_5 : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (4 * game.partitionSize) + (3 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_6 : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (5 * game.partitionSize) + (4 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_7 : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (6 * game.partitionSize) + (5 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_8 : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (7 * game.partitionSize) + (6 *inputHeightBase),
                        inputWidth,
                        inputHeightBase);
            }
            case BASE_9 : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (8 * game.partitionSize) + (7 * inputHeightBase),
                        inputWidth,
                        inputHeightBase);
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
                        inputHeightBase);
            }
            case MUSIC_TYPE : {
                if(game.usePhases) {
                    game.shapeRendererFilled.rect((3 * game.partitionSize) + inputWidth,
                            game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((2 * (inputShapeRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4),
                            (inputWidth / 2) - game.partitionSize,
                            game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5));
                } else {
                    game.shapeRendererFilled.rect((3 * game.partitionSize) + inputWidth,
                            game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((3 * (inputShapeRadius * 2)) / 4) - ((3 * game.fontTrackType.getCapHeight()) / 4),
                            (inputWidth / 2) - game.partitionSize,
                            game.fontTrackType.getCapHeight() + (game.fontTrackType.getCapHeight() / 5));
                }
            }
            case MUSIC_NAME : {
                for(int i = 0; i < game.maxBase; i++) {
                    if(game.track == i) {
                        game.shapeRendererFilled.rect(game.camera.viewportWidth / 2,
                                game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - (game.fontTrackName.getCapHeight() * 2) - (i * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                                (inputWidth / 2) - game.partitionSize,
                                (7 * game.fontTrackName.getCapHeight()) / 4);
                    }
                }
            }
            case TIME : {
                game.shapeRendererFilled.rect((2 * game.partitionSize) + inputWidth,
                        (2 * game.partitionSize) + inputHeightBase,
                        inputWidth,
                        inputHeightBase);
            }
        }
    }

    public void drawBase4Input() {
        drawInputRectangle(BASE_4, base4Color);
        game.draw.drawSquare(game.camera.viewportWidth / 2,
                (3 * game.partitionSize) + (2 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase5Input() {
        drawInputRectangle(BASE_5, base5Color);
        game.draw.drawPentagon(game.camera.viewportWidth / 2,
                (4 * game.partitionSize) + (3 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase6Input() {
        drawInputRectangle(BASE_6, base6Color);
        game.draw.drawHexagon(game.camera.viewportWidth / 2,
                (5 * game.partitionSize) + (4 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase7Input() {
        drawInputRectangle(BASE_7, base7Color);
        game.draw.drawSeptagon(game.camera.viewportWidth / 2,
                (6 * game.partitionSize) + (5 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase8Input() {
        drawInputRectangle(BASE_8, base8Color);
        game.draw.drawOctagon(game.camera.viewportWidth / 2,
                (7 * game.partitionSize) + (6 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawBase9Input() {
        drawInputRectangle(BASE_9, base9Color);
        game.draw.drawNonagon(game.camera.viewportWidth / 2,
                (8 * game.partitionSize) + (7 * inputHeightBase) + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                0,
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
                game.partitionSize + (inputHeightBase / 2),
                inputShapeRadius,
                inputShapeRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
        drawMusicTypeInput();
        drawMusicNameInput();
    }

    public void drawMusicTypeInput() {
        drawInputRectangle(MUSIC_TYPE, Color.BLACK);
    }

    public void drawMusicNameInput() {
        drawInputRectangle(MUSIC_NAME, Color.BLACK);
    }

    public void drawTimeInput() {
        drawInputRectangle(TIME, timeColor);
        game.draw.drawClock((2 * game.partitionSize) + inputWidth + (inputWidth / 5),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2),
                symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1),
                Color.BLACK,
                timeColor,
                game.shapeRendererFilled);
        game.draw.drawChevronLeft((2 * game.partitionSize) + inputWidth + ((2 * inputWidth) / 5),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2),
                symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1),
                (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawChevronRight((2 * game.partitionSize) + inputWidth + ((4 * inputWidth) / 5),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2),
                symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1),
                (symbolRadius / (NUM_TIME_INPUT_ELEMENTS + 1)) / Draw.LINE_WIDTH_DIVISOR,
                Color.BLACK,
                game.shapeRendererFilled);
    }

    public void drawMusicText() {
        FontUtils.printText(game.batch,
                game.fontTrackType,
                game.layout,
                game.usePhases ? Color.BLACK : Color.WHITE,
                game.MUSIC_TYPE_FULL,
                (game.camera.viewportWidth / 2) - (inputWidth / 4),
                game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((3 * (inputShapeRadius * 2)) / 4),
                0,
                1);
        FontUtils.printText(game.batch,
                game.fontTrackType,
                game.layout,
                game.usePhases ? Color.WHITE : Color.BLACK,
                game.MUSIC_TYPE_SPLIT,
                (game.camera.viewportWidth / 2) - (inputWidth / 4),
                game.partitionSize + ((inputHeightBase - (inputShapeRadius * 2)) / 2) + ((2 * (inputShapeRadius * 2)) / 4),
                0,
                1);
        for(int i = 0; i < game.maxBase; i++) {
            FontUtils.printText(game.batch,
                    game.fontTrackName,
                    game.layout,
                    game.track == i ? Color.WHITE : Color.BLACK,
                    game.musicTitleList.get(i),
                    (game.camera.viewportWidth / 2) + (inputWidth / 4),
                    game.partitionSize + inputHeightBase - ((inputHeightBase - (inputShapeRadius * 2)) / 2) - game.fontTrackName.getCapHeight() - (i * ((3 * game.fontTrackName.getCapHeight()) / 2)),
                    0,
                    1);
        }
    }

    public void drawTimeText() {
        FontUtils.printText(game.batch,
                game.fontTime,
                game.layout,
                Color.BLACK,
                String.valueOf(game.timeAttackNumSeconds / Squirgle.ONE_MINUTE),
                (2 * game.partitionSize) + inputWidth + ((3 * inputWidth) / 5),
                (2 * game.partitionSize) + inputHeightBase + (inputHeightBase / 2),
                0,
                1);
    }

    public void drawTitle() {
        game.draw.drawPlayButton(game.partitionSize + (inputWidth / 2),
                (3 * game.camera.viewportHeight) / 4,
                symbolRadius / 3,
                (symbolRadius / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                game.shapeRendererFilled);

        game.draw.drawFace((game.camera.viewportWidth / 6) - (symbolRadius / 3) + ((symbolRadius / 3) / 3),
                game.camera.viewportHeight / 2,
                (symbolRadius / 3) / 3,
                ((symbolRadius / 3) / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawFace((game.camera.viewportWidth / 6) + (symbolRadius / 3) - ((symbolRadius / 3) / 3),
                game.camera.viewportHeight / 2,
                (symbolRadius / 3) / 3,
                ((symbolRadius / 3) / 3) / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererFilled.rectLine((game.camera.viewportWidth / 6) - (symbolRadius / 3) + ((symbolRadius / 3) / 3),
                game.camera.viewportHeight / 2,
                (game.camera.viewportWidth / 6) + (symbolRadius / 3) - ((symbolRadius / 3) / 3),
                game.camera.viewportHeight / 2,
                ((symbolRadius / 3) / 3) / Draw.LINE_WIDTH_DIVISOR);

        game.shapeRendererFilled.setColor(Color.WHITE);
        game.shapeRendererFilled.rectLine((game.camera.viewportWidth / 6) - (symbolRadius / 3),
                (game.camera.viewportHeight / 6) - (symbolRadius / 3),
                (game.camera.viewportWidth / 6) + (symbolRadius / 3),
                (game.camera.viewportHeight / 6) + (symbolRadius / 3),
                ((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR);
        game.shapeRendererFilled.circle((game.camera.viewportWidth / 6) - (symbolRadius / 3), (game.camera.viewportHeight / 6) - (symbolRadius / 3), (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);
        game.shapeRendererFilled.circle((game.camera.viewportWidth / 6) + (symbolRadius / 3), (game.camera.viewportHeight / 6) + (symbolRadius / 3), (((symbolRadius / 2) / 3) / Draw.LINE_WIDTH_DIVISOR) / 2);

        game.draw.drawClock((game.camera.viewportWidth / 6) - (symbolRadius / 6),
                (game.camera.viewportHeight / 6) + (symbolRadius / 6),
                (symbolRadius / 2) / 3,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
        game.draw.drawClock((game.camera.viewportWidth / 6) + (symbolRadius / 6),
                (game.camera.viewportHeight / 6) - (symbolRadius / 6),
                (symbolRadius / 2) / 3,
                Color.WHITE,
                Color.BLACK,
                game.shapeRendererFilled);
    }
}
