package com.screenlooker.squirgle.screen.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.codedisaster.steamworks.SteamAPI;
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;
import com.screenlooker.squirgle.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuOptionsScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static String WIPE_DATA_STRING = "Are you sure you wish to delete all of your save data?";

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;

    private final static float FONT_OPTIONS_SIZE_DIVISOR = 7f;
    private static float FONT_TUTORIAL_HELP_SIZE_MULTIPLIER;

    private int numMiddleInputsVertical;
    private int numMiddlePartitionsVertical;

    private float inputWidth;
    private float inputHeightMiddle;
    private float inputHeightBack;
    private float helpInputGirth;

    private float symbolRadius;
    private float inputShapeRadius;

    private Vector3 touchPoint;

    private Color volumeColor;
    private Color fxVolumeColor;
    private Color p2ControlsColor;
    private Color wipeDataColor;
    private Color backColor;

    private boolean helpConfirmTouched;
    private boolean helpDisconfirmTouched;
    private boolean nonHelpTouched;

    private Button volumeButton;
    private Button volumeWavesButton;
    private Button volumeChevronDownButton;
    private Button volumeChevronUpButton;
    private Button fxVolumeButton;
    private Button fxVolumeWavesButton;
    private Button fxVolumeChevronDownButton;
    private Button fxVolumeChevronUpButton;
    private Button p2ControlsButton;
    private Button p2ControlsDPadButton;
    private Button p2ControlsChevronDownButton;
    private Button p2ControlsChevronUpButton;
    private Button wipeDataButton;
    private Button backButton;

    private List<Button> buttonList;

    private Color veilColor;
    private float veilOpacity;

    public Label.LabelStyle helpLabelStyle;
    public Label helpLabel;
    private Stage stage;

    //TODO: Set up fontScore
    public MenuOptionsScreen(final Squirgle game, Color veilColor) {
        this.game = game;

        game.resetInstanceData();

        if(game.widthGreater) {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 25.875f;
        } else {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 31.5f;
        }

        game.setUpFontTutorialHelp(MathUtils.round(game.ASPECT_RATIO * FONT_TUTORIAL_HELP_SIZE_MULTIPLIER));

        Gdx.input.setInputProcessor(this);

        numMiddleInputsVertical = game.desktop ? 4 : 3;
        numMiddlePartitionsVertical = numMiddleInputsVertical + 1;

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightMiddle = (game.camera.viewportHeight - (game.partitionSize * numMiddlePartitionsVertical)) / numMiddleInputsVertical;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        inputShapeRadius = inputWidth > inputHeightMiddle ? (inputHeightMiddle / 2) : (inputWidth / 2);

        helpInputGirth = inputHeightMiddle / 4;

        game.setUpFontOptions(MathUtils.round(inputWidth / FONT_OPTIONS_SIZE_DIVISOR));
        game.setUpFontButton(MathUtils.round(inputShapeRadius / 2.75f));

        touchPoint = new Vector3();

        volumeColor = ColorUtils.COLOR_ORANGE;
        fxVolumeColor = ColorUtils.COLOR_VERMILLION;
        p2ControlsColor = ColorUtils.COLOR_BLUISH_GREEN;
        wipeDataColor = ColorUtils.COLOR_BLUE;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        volumeButton = new Button((2 * game.partitionSize) + inputWidth,
                game.camera.viewportHeight - game.partitionSize - inputHeightMiddle,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_VOLUME,
                volumeColor,
                Color.BLACK,
                game);
        volumeWavesButton = new Button(volumeButton.symbolX - (((2 * volumeButton.symbolRadius) / 3) / 2),
                volumeButton.symbolY + volumeButton.symbolRadius - ((2 * volumeButton.symbolRadius) / 3),
                (2 * volumeButton.symbolRadius) / 3,
                (2 * volumeButton.symbolRadius) / 3,
                Button.BUTTON_VOLUME_WAVES,
                volumeColor,
                Color.BLACK,
                game);
        volumeChevronDownButton = new Button(volumeButton.x,
                volumeButton.symbolY + volumeButton.symbolRadius - ((5 * volumeButton.symbolRadius) / 3),
                (2 * volumeButton.symbolRadius) / 3,
                (2 * volumeButton.symbolRadius) / 3,
                Button.BUTTON_VOLUME_CHEVRON_DOWN,
                volumeColor,
                Color.BLACK,
                game);
        volumeChevronUpButton = new Button(volumeButton.x + volumeButton.width - ((2 * volumeButton.symbolRadius) / 3),
                volumeButton.symbolY + volumeButton.symbolRadius - ((5 * volumeButton.symbolRadius) / 3),
                (2 * volumeButton.symbolRadius) / 3,
                (2 * volumeButton.symbolRadius) / 3,
                Button.BUTTON_VOLUME_CHEVRON_UP,
                volumeColor,
                Color.BLACK,
                game);
        fxVolumeButton = new Button((2 * game.partitionSize) + inputWidth,
                game.camera.viewportHeight - (2 * game.partitionSize) - (2 * inputHeightMiddle),
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_FX_VOLUME,
                fxVolumeColor,
                Color.BLACK,
                game);
        fxVolumeWavesButton = new Button(fxVolumeButton.symbolX - (((2 * fxVolumeButton.symbolRadius) / 3) / 2),
                fxVolumeButton.symbolY + fxVolumeButton.symbolRadius - ((2 * fxVolumeButton.symbolRadius) / 3),
                (2 * fxVolumeButton.symbolRadius) / 3,
                (2 * fxVolumeButton.symbolRadius) / 3,
                Button.BUTTON_FX_VOLUME_WAVES,
                fxVolumeColor,
                Color.BLACK,
                game);
        fxVolumeChevronDownButton = new Button(fxVolumeButton.x,
                fxVolumeButton.symbolY + fxVolumeButton.symbolRadius - ((5 * fxVolumeButton.symbolRadius) / 3),
                (2 * fxVolumeButton.symbolRadius) / 3,
                (2 * fxVolumeButton.symbolRadius) / 3,
                Button.BUTTON_FX_VOLUME_CHEVRON_DOWN,
                fxVolumeColor,
                Color.BLACK,
                game);
        fxVolumeChevronUpButton = new Button(fxVolumeButton.x + fxVolumeButton.width - ((2 * fxVolumeButton.symbolRadius) / 3),
                fxVolumeButton.symbolY + fxVolumeButton.symbolRadius - ((5 * fxVolumeButton.symbolRadius) / 3),
                (2 * fxVolumeButton.symbolRadius) / 3,
                (2 * fxVolumeButton.symbolRadius) / 3,
                Button.BUTTON_FX_VOLUME_CHEVRON_UP,
                fxVolumeColor,
                Color.BLACK,
                game);
        p2ControlsButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightMiddle,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_P2_CONTROLS,
                p2ControlsColor,
                Color.BLACK,
                game);
        p2ControlsDPadButton = new Button(p2ControlsButton.symbolX - (((2 * p2ControlsButton.symbolRadius) / 3) / 2),
                p2ControlsButton.symbolY + p2ControlsButton.symbolRadius - ((2 * p2ControlsButton.symbolRadius) / 3),
                (2 * p2ControlsButton.symbolRadius) / 3,
                (2 * p2ControlsButton.symbolRadius) / 3,
                Button.BUTTON_P2_CONTROLS_DPAD,
                p2ControlsColor,
                Color.BLACK,
                game);
        p2ControlsChevronDownButton = new Button(p2ControlsButton.x,
                p2ControlsButton.symbolY + p2ControlsButton.symbolRadius - ((5 * p2ControlsButton.symbolRadius) / 3),
                (2 * p2ControlsButton.symbolRadius) / 3,
                (2 * p2ControlsButton.symbolRadius) / 3,
                Button.BUTTON_P2_CONTROLS_CHEVRON_DOWN,
                p2ControlsColor,
                Color.BLACK,
                game);
        p2ControlsChevronUpButton = new Button(p2ControlsButton.x + p2ControlsButton.width - ((2 * p2ControlsButton.symbolRadius) / 3),
                p2ControlsButton.symbolY + p2ControlsButton.symbolRadius - ((5 * p2ControlsButton.symbolRadius) / 3),
                (2 * p2ControlsButton.symbolRadius) / 3,
                (2 * p2ControlsButton.symbolRadius) / 3,
                Button.BUTTON_P2_CONTROLS_CHEVRON_UP,
                p2ControlsColor,
                Color.BLACK,
                game);
        wipeDataButton = new Button((2 * game.partitionSize) + inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_WIPE_DATA,
                wipeDataColor,
                Color.BLACK,
                game);
        backButton = new Button(game.camera.viewportWidth - game.partitionSize - inputWidth,
                game.partitionSize,
                inputWidth,
                inputHeightBack,
                Button.BUTTON_OPTIONS_BACK,
                backColor,
                Color.BLACK,
                game);

        buttonList = new ArrayList<Button>();
        buttonList.add(volumeButton);
        buttonList.add(volumeWavesButton);
        buttonList.add(volumeChevronDownButton);
        buttonList.add(volumeChevronUpButton);
        buttonList.add(fxVolumeButton);
        buttonList.add(fxVolumeWavesButton);
        buttonList.add(fxVolumeChevronDownButton);
        buttonList.add(fxVolumeChevronUpButton);
        if(game.desktop) {
            buttonList.add(p2ControlsButton);
            buttonList.add(p2ControlsDPadButton);
            buttonList.add(p2ControlsChevronDownButton);
            buttonList.add(p2ControlsChevronUpButton);
        }
        buttonList.add(wipeDataButton);
        buttonList.add(backButton);

        this.veilColor = veilColor;
        veilOpacity = 1;

        helpLabelStyle = new Label.LabelStyle();
        helpLabelStyle.font = game.fontTutorialHelp;
        helpLabelStyle.fontColor = Color.BLACK;
        helpLabel = new Label(WIPE_DATA_STRING, helpLabelStyle);
        helpLabel.setSize(inputWidth, inputHeightMiddle - helpInputGirth);
        helpLabel.setPosition((2 * game.partitionSize) + inputWidth, game.partitionSize + helpInputGirth);
        helpLabel.setAlignment(Align.center);
        helpLabel.setWrap(true);
        helpLabel.setVisible(game.showWipeDataPrompt);

        stage = new Stage(game.viewport);
        stage.addActor(helpLabel);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.shapeRendererFilled.setProjectionMatrix(game.camera.combined);
        game.shapeRendererLine.setProjectionMatrix(game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);

        game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRendererLine.begin(ShapeRenderer.ShapeType.Line);

        drawTitle();

        for(Button button : buttonList) {
            button.draw();
        }

        for(Button button : buttonList) {
            button.drawTransitionCircles(this);
        }

        showHelpText();

        game.draw.drawVeil(veilColor, veilOpacity);

        game.shapeRendererFilled.end();
        game.shapeRendererLine.end();

        stage.draw();

        showHelpTextFooter();

        if(veilOpacity > 0) {
            veilOpacity -= 0.1;
        } else {
            if(!buttonTouched()) {
                for (Button button : buttonList) {
                    if(!(button.buttonType == Button.BUTTON_WIPE_DATA && game.showWipeDataPrompt)) {
                        button.drawText();
                    }
                }
            }
        }

        if(game.desktop) {
            game.shapeRendererFilled.begin(ShapeRenderer.ShapeType.Filled);
            game.draw.drawCursor();
            game.shapeRendererFilled.end();
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

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        if(!game.showWipeDataPrompt) {
            for (Button btn : buttonList) {
                btn.touchDown(touchPoint);
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }

        game.camera.unproject(touchPoint.set(screenX, screenY, 0));

        if(!game.showWipeDataPrompt) {
            for (Button btn : buttonList) {
                btn.touchUp(touchPoint);
            }
        } else {
            helpConfirmTouched = touchPoint.x > helpLabel.getX() + (helpLabel.getWidth() / 2)
                    && touchPoint.x < helpLabel.getX() + helpLabel.getWidth()
                    && touchPoint.y > helpLabel.getY() - helpInputGirth
                    && touchPoint.y < helpLabel.getY();
            helpDisconfirmTouched = touchPoint.x > helpLabel.getX()
                    && touchPoint.x < helpLabel.getX() + (helpLabel.getWidth() / 2)
                    && touchPoint.y > helpLabel.getY() - helpInputGirth
                    && touchPoint.y < helpLabel.getY();
            nonHelpTouched = touchPoint.x > (2 * game.partitionSize) + (2 * inputWidth)
                    || touchPoint.x < (2 * game.partitionSize) + inputWidth
                    || touchPoint.y > game.partitionSize + inputHeightMiddle
                    || touchPoint.y < game.partitionSize;

            if(helpConfirmTouched) {
                game.confirmSound.play((float) (game.fxVolume / 10.0));
                game.wipeSave();
                game.showWipeDataPrompt = false;
//                game.clearAllAchievements();
//                game.steamUserStats.storeStats();
            } else if(helpDisconfirmTouched || nonHelpTouched) {
                game.disconfirmSound.play((float) (game.fxVolume / 10.0));
                game.showWipeDataPrompt = false;
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

    public boolean buttonTouched() {
        for(Button btn : buttonList) {
            if(btn.touched) {
                return true;
            }
        }
        return false;
    }

    public void showHelpText() {
        helpLabel.setVisible(game.showWipeDataPrompt);
        if(game.showWipeDataPrompt) {
            game.draw.rect(helpLabel.getX(),
                    helpLabel.getY() - helpInputGirth,
                    inputWidth,
                    inputHeightMiddle,
                    wipeDataColor);
            game.shapeRendererLine.setColor(Color.BLACK);
            game.shapeRendererLine.line(helpLabel.getX(),
                    helpLabel.getY(),
                    helpLabel.getX() + helpLabel.getWidth(),
                    helpLabel.getY());
            game.shapeRendererLine.line(helpLabel.getX() + (helpLabel.getWidth() / 2),
                    helpLabel.getY(),
                    helpLabel.getX() + (helpLabel.getWidth() / 2),
                    helpLabel.getY() - helpInputGirth);
        }
    }

    public void showHelpTextFooter() {
        if(game.showWipeDataPrompt) {
            FontUtils.printText(game.batch,
                    game.fontTutorialHelp,
                    game.layout,
                    Color.BLACK,
                    "NO",
                    helpLabel.getX() + (helpLabel.getWidth() / 4),
                    helpLabel.getY() - (helpInputGirth / 2),
                    0,
                    1);
            FontUtils.printText(game.batch,
                    game.fontTutorialHelp,
                    game.layout,
                    Color.BLACK,
                    "YES",
                    helpLabel.getX() + ((3 * helpLabel.getWidth()) / 4),
                    helpLabel.getY() - (helpInputGirth / 2),
                    0,
                    1);
        }
    }

    public void drawTitle() {
        game.draw.drawWrench(game.partitionSize + (inputWidth / 2),
                game.camera.viewportHeight / 2,
                symbolRadius,
                symbolRadius / Draw.LINE_WIDTH_DIVISOR,
                Color.WHITE,
                Color.BLACK);
    }

}
