package com.screenlooker.squirgle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.*;
import com.screenlooker.squirgle.screen.MainMenuScreen;
import com.screenlooker.squirgle.screen.TutorialScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Squirgle extends Game {
	private static int VIRTUAL_WIDTH;
	private static int VIRTUAL_HEIGHT;
	private static float ASPECT_RATIO;

	public final static int PARTITION_DIVISOR = 80;
	public final static int LINE_WIDTH = 20;
	public final static int END_LINE_WIDTH_INCREASE = 2;
	public final static int FPS = 60;
	public final static int MAX_POSSIBLE_BASE = 9;
	public final static int SCORE_TO_UNLOCK_NEW_BASE = 150;

	public final static int MUSIC_THEME_FROM_SQUIRGLE = 0;
	public final static int MUSIC_THE_POINT = 1;
	public final static int MUSIC_THE_LINE = 2;
	public final static int MUSIC_NO_TRI = 3;
	public final static int MUSIC_SQUARE_OFF = 4;
	public final static int MUSIC_PENT_UP = 5;
	public final static int MUSIC_HEX = 6;
	public final static int MUSIC_EXSEPTION_TO_THE_COOL = 7;
	public final static int MUSIC_ROCTOPUS = 8;
	public final static int MUSIC_NON_PLUSSED = 9;
	public final static int MUSIC_THE_LEARNED_AND_THE_DEAD = 10;

	public final static String SAVE_NAME = "Squirgle Save";

	public final static String SAVE_VOLUME = "volume";
	public final static String SAVE_TRACK = "track";
	public final static String SAVE_PLAYED_BEFORE = "playedBefore";
	public final static String SAVE_MAX_BASE = "maxBase";
	public final static String TARGET = "TARGET";

	//Saved data
	public Preferences save;

	//Options
	public int volume;
	public int track;

	public boolean playedBefore;

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
	public BitmapFont fontScore;
	public BitmapFont fontTarget;
	public BitmapFont fontSquirgle;
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
	public List<Music> squareOffPhaseList;
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

		save = Gdx.app.getPreferences(SAVE_NAME);

		volume = save.getInteger(SAVE_VOLUME, 10);
		track = save.getInteger(SAVE_TRACK, MUSIC_EXSEPTION_TO_THE_COOL);

		playedBefore = save.getBoolean(SAVE_PLAYED_BEFORE, false);

		base = 4;
		maxBase = save.getInteger(SAVE_MAX_BASE, 4);
		minBase = 4;
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		setUpFontScore(72);
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
		partitionSize = widthOrHeight / PARTITION_DIVISOR;
		shapeRendererFilled = new ShapeRenderer();
		shapeRendererLine = new ShapeRenderer();
		draw = new Draw(this);
		confirmSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/confirm.wav"));
		disconfirmSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/disconfirm.wav"));
		thePointPhaseList = new ArrayList<Music>();
		theLinePhaseList = new ArrayList<Music>();
		noTriPhaseList = new ArrayList<Music>();
		squareOffPhaseList = new ArrayList<Music>();
		pentUpPhaseList = new ArrayList<Music>();
		hexPhaseList = new ArrayList<Music>();
		exseptionToTheCoolPhaseList = new ArrayList<Music>();
		roctopusPhaseList = new ArrayList<Music>();
		nonPlussedPhaseList = new ArrayList<Music>();
		trackMap = new HashMap<Integer, List<Music>>();

		setUpTracks();

		generator.dispose();

		if(!playedBefore) {
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
		fontScore.dispose();
		fontTarget.dispose();
		fontSquirgle.dispose();
		generator.dispose();
		shapeRendererFilled.dispose();
		shapeRendererLine.dispose();
		confirmSound.dispose();
		disconfirmSound.dispose();
		//TODO: dispose of all music assets
		generator.dispose();
	}

	public void resetInstanceData() {
		draw = new Draw(this);
	}

	public void setUpFontScore(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()><?:";
		fontScore = generator.generateFont(parameter);
	}

	public void setUpFontTarget(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()><?:";
		fontTarget = generator.generateFont(parameter);
	}

	public void setUpFontSquirgle(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()><?:";
		fontSquirgle = generator.generateFont(parameter);
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

	public void updateSave(String key, Object val) {
		//Using if-thens because switch statement is incompatible with "class" type
		if(val.getClass().equals(Boolean.class)) {
			save.putBoolean(key, (Boolean) val);
		} else if(val.getClass().equals(Float.class)) {
			save.putFloat(key, (Float) val);
		} else if(val.getClass().equals(Integer.class)) {
			save.putInteger(key, (Integer) val);
		} else if(val.getClass().equals(Long.class)) {
			save.putLong(key, (Long) val);
		} else if(val.getClass().equals(String.class)) {
			save.putString(key, (String) val);
		}
		save.flush();
	}
}
