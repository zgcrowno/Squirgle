package com.screenlooker.squirgle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.*;
import com.sun.prism.image.ViewPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: Many of the variables throughout this entire game will have to be replaced with screen-size dependent alternatives

//TODO: Often, when working with game.camera.viewportWidth/Height, we'll want to use one or the other based upon whether or
//TODO: not the screen widht or screen height is greater.
public class Squirgle extends Game {
	private static int VIRTUAL_WIDTH;
	private static int VIRTUAL_HEIGHT;
	private static float ASPECT_RATIO;

	public final static int PARTITION_DIVISOR = 80;
	public final static int LINE_WIDTH = 20;

	public final static int MUSIC_THEME_FROM_SQUIRGLE = 0;
	public final static int MUSIC_THE_POINT = 1;
	public final static int MUSIC_THE_LINE = 2;
	public final static int MUSIC_NO_TRI = 3;
	public final static int MUSIC_SQUARE_UP = 4;
	public final static int MUSIC_PENT_UP = 5;
	public final static int MUSIC_HEX = 6;
	public final static int MUSIC_EXSEPTION_TO_THE_COOL = 7;
	public final static int MUSIC_ROCTOPUS = 8;
	public final static int MUSIC_NON_PLUSSED = 9;

	//Options
	public int volume;
	public int track;

	public boolean neverPlayed;

	public boolean widthGreater;
	public float widthOrHeight;
	public float fourthOfScreen;
	public float thirdOfScreen;
	public float fiveTwelfthsOfScreen;
	public int base;
	public int maxBase;
	public int minBase;
	public float partitionSize;
	public SpriteBatch batch;
	public BitmapFont font;
	public GlyphLayout layout;
	public FreeTypeFontGenerator generator;
	public OrthographicCamera camera;
	public Viewport viewport;
	public ShapeRenderer shapeRendererFilled;
	public ShapeRenderer shapeRendererLine;
	public Draw draw;
	public Sound confirmSound;
	public Sound disconfirmSound;
	public List<Music> thePointPhaseList;
	public List<Music> theLinePhaseList;
	public List<Music> noTriPhaseList;
	public List<Music> squareUpPhaseList;
	public List<Music> pentUpPhaseList;
	public List<Music> hexPhaseList;
	public List<Music> exseptionToTheCoolPhaseList;
	public List<Music> roctopusPhaseList;
	public List<Music> nonPlussedPhaseList;
	public Map<Integer, List<Music>> trackMap;

	public void create() {
		VIRTUAL_WIDTH = Gdx.graphics.getWidth();
		VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
		ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

		volume = 10;
		track = MUSIC_EXSEPTION_TO_THE_COOL;

		neverPlayed = true;

		base = 4;
		maxBase = 9;
		minBase = 4;
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		setUpFont(72);
		layout = new GlyphLayout();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
		camera.position.set(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2,0);
		widthGreater = camera.viewportWidth > camera.viewportHeight;
		widthOrHeight = widthGreater ? camera.viewportHeight : camera.viewportWidth;
		fourthOfScreen = widthOrHeight / 4;
		thirdOfScreen = widthOrHeight / 3;
		fiveTwelfthsOfScreen = (5 * widthOrHeight) / 12;
		partitionSize = camera.viewportHeight / PARTITION_DIVISOR;
		shapeRendererFilled = new ShapeRenderer();
		shapeRendererLine = new ShapeRenderer();
		draw = new Draw(this);
		confirmSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/confirm.wav"));
		disconfirmSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/disconfirm.wav"));
		thePointPhaseList = new ArrayList<Music>();
		theLinePhaseList = new ArrayList<Music>();
		noTriPhaseList = new ArrayList<Music>();
		squareUpPhaseList = new ArrayList<Music>();
		pentUpPhaseList = new ArrayList<Music>();
		hexPhaseList = new ArrayList<Music>();
		exseptionToTheCoolPhaseList = new ArrayList<Music>();
		roctopusPhaseList = new ArrayList<Music>();
		nonPlussedPhaseList = new ArrayList<Music>();
		trackMap = new HashMap<Integer, List<Music>>();

		setUpTracks();

		generator.dispose();

		if(neverPlayed) {
			this.setScreen(new TutorialScreen(this));
		} else {
			this.setScreen(new MainMenuScreen(this));
		}
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		confirmSound.dispose();
		disconfirmSound.dispose();
		//TODO: dispose of all music assets
		generator.dispose();
	}

	public void resetInstanceData() {
		draw = new Draw(this);
	}

	public void setUpFont(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()><?:";
		font = generator.generateFont(parameter);
	}

	public void setUpTracks() {
		//TODO: Add the rest of the tracks once they're written/recorded
		//exseptionToTheCool
		Music exseptionToTheCoolPhase1 = Gdx.audio.newMusic(Gdx.files.internal("music/Squirgle - Exseption to the Cool (Phase 1).wav"));
		Music exseptionToTheCoolPhase2 = Gdx.audio.newMusic(Gdx.files.internal("music/Squirgle - Exseption to the Cool (Phase 2).wav"));
		Music exseptionToTheCoolPhase3 = Gdx.audio.newMusic(Gdx.files.internal("music/Squirgle - Exseption to the Cool (Phase 3).wav"));
		exseptionToTheCoolPhase1.setLooping(true);
		exseptionToTheCoolPhase2.setLooping(true);
		exseptionToTheCoolPhase3.setLooping(true);
		exseptionToTheCoolPhaseList.add(exseptionToTheCoolPhase1);
		exseptionToTheCoolPhaseList.add(exseptionToTheCoolPhase2);
		exseptionToTheCoolPhaseList.add(exseptionToTheCoolPhase3);
		trackMap.put(MUSIC_EXSEPTION_TO_THE_COOL, exseptionToTheCoolPhaseList);
	}

}
