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
import com.screenlooker.squirgle.Button;
import com.screenlooker.squirgle.Draw;
import com.screenlooker.squirgle.Squirgle;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.util.ColorUtils;
import com.screenlooker.squirgle.util.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuOptionsScreen implements Screen, InputProcessor {

    final Squirgle game;

    private final static String WIPE_DATA_STRING = "Are you sure you wish to delete all of your save data?";

    private final static int NUM_INPUTS_HORIZONTAL = 3;
    private final static int NUM_LEFT_INPUTS_VERTICAL = 1;
    private final static int NUM_RIGHT_INPUTS_VERTICAL = 1;
    private final static int NUM_MIDDLE_INPUTS_VERTICAL = 2;
    private final static int NUM_PARTITIONS_HORIZONTAL = NUM_INPUTS_HORIZONTAL + 1;
    private final static int NUM_LEFT_PARTITIONS_VERTICAL = NUM_LEFT_INPUTS_VERTICAL + 1;
    private final static int NUM_RIGHT_PARTITIONS_VERTICAL = NUM_RIGHT_INPUTS_VERTICAL + 1;
    private final static int NUM_MIDDLE_PARTITIONS_VERTICAL = NUM_MIDDLE_INPUTS_VERTICAL + 1;
    public final static int NUM_SOUND_INPUT_ELEMENTS = 4;

    private final static float FONT_VOLUME_SIZE_DIVISOR = 11.1f;
    private static float FONT_TUTORIAL_HELP_SIZE_MULTIPLIER;

    private float inputWidth;
    private float inputHeightMiddle;
    private float inputHeightBack;
    private float helpInputGirth;

    private float symbolRadius;

    private Vector3 touchPoint;

    private Color volumeColor;
    private Color wipeDataColor;
    private Color backColor;

    private boolean volumeDownChevronTouched;
    private boolean volumeUpChevronTouched;
    private boolean helpConfirmTouched;
    private boolean helpDisconfirmTouched;
    private boolean nonHelpTouched;
    private boolean backTouched;

    private Button volumeButton;
    private Button volumeWavesButton;
    private Button volumeChevronDownButton;
    private Button volumeChevronUpButton;
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
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 35.5f;
        } else {
            FONT_TUTORIAL_HELP_SIZE_MULTIPLIER = 42f;
        }

        game.setUpFontVolume(MathUtils.round(game.camera.viewportWidth / FONT_VOLUME_SIZE_DIVISOR));
        game.setUpFontTutorialHelp(MathUtils.round(game.ASPECT_RATIO * FONT_TUTORIAL_HELP_SIZE_MULTIPLIER));

        Gdx.input.setInputProcessor(this);

        inputWidth = (game.camera.viewportWidth - (game.partitionSize * NUM_PARTITIONS_HORIZONTAL)) / NUM_INPUTS_HORIZONTAL;
        inputHeightMiddle = (game.camera.viewportHeight - (game.partitionSize * NUM_MIDDLE_PARTITIONS_VERTICAL)) / NUM_MIDDLE_INPUTS_VERTICAL;
        inputHeightBack = (game.camera.viewportHeight - (game.partitionSize * NUM_RIGHT_PARTITIONS_VERTICAL)) / NUM_RIGHT_INPUTS_VERTICAL;
        helpInputGirth = game.camera.viewportWidth / 16;

        symbolRadius = inputWidth > inputHeightBack ? inputHeightBack / 2 : inputWidth / 2;

        game.setUpFontButton(MathUtils.round(symbolRadius / 2.75f));

        touchPoint = new Vector3();

        volumeColor = ColorUtils.COLOR_SKY_BLUE;
        wipeDataColor = ColorUtils.COLOR_BLUE;
        backColor = ColorUtils.COLOR_REDDISH_PURPLE;

        volumeDownChevronTouched = false;
        volumeUpChevronTouched = false;
        backTouched = false;

        volumeButton = new Button((2 * game.partitionSize) + inputWidth,
                (2 * game.partitionSize) + inputHeightMiddle,
                inputWidth,
                inputHeightMiddle,
                Button.BUTTON_VOLUME,
                volumeColor,
                Color.BLACK,
                game);
        volumeWavesButton = new Button((2 * game.partitionSize) + inputWidth + (inputWidth / 10),
                (2 * game.partitionSize) + inputHeightMiddle + (inputHeightMiddle / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_VOLUME_WAVES,
                volumeColor,
                Color.BLACK,
                game);
        volumeChevronDownButton = new Button((2 * game.partitionSize) + inputWidth + ((3 * inputWidth) / 10),
                (2 * game.partitionSize) + inputHeightMiddle + (inputHeightMiddle / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_VOLUME_CHEVRON_DOWN,
                volumeColor,
                Color.BLACK,
                game);
        volumeChevronUpButton = new Button((2 * game.partitionSize) + inputWidth + ((7 * inputWidth) / 10),
                (2 * game.partitionSize) + inputHeightMiddle + (inputHeightMiddle / 2) - (inputWidth / 10),
                inputWidth / 5,
                inputWidth / 5,
                Button.BUTTON_VOLUME_CHEVRON_UP,
                volumeColor,
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
                game.wipeSave();
                game.showWipeDataPrompt = false;
            } else if(helpDisconfirmTouched || nonHelpTouched) {
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
