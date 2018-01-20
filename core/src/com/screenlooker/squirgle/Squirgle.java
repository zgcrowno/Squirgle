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
	public final static int SCORE_TO_UNLOCK_NEW_BASE = 120;

	public final static int MUSIC_POINTILLISM = 0;
	public final static int MUSIC_LINEAGE = 1;
	public final static int MUSIC_TRI_THE_WALTZ = 2;
	public final static int MUSIC_SQUARED_OFF = 3;
	public final static int MUSIC_PENT_UP = 4;
	public final static int MUSIC_HEXIDECIBEL = 5;
	public final static int MUSIC_INTERSEPTOR = 6;
	public final static int MUSIC_ROCTOPUS = 7;
	public final static int MUSIC_NONPLUSSED = 8;
	public final static int MUSIC_THEME_FROM_SQUIRGLE = 9;
	public final static int MUSIC_EXSEPTION_TO_THE_COOL = 10;

	public final static String MUSIC_TYPE_FULL = "FULL";
	public final static String MUSIC_TYPE_SPLIT = "SPLIT";
	public final static String MUSIC_TITLE_POINTILLISM = "POINTILLISM";
	public final static String MUSIC_TITLE_LINEAGE = "LINEAGE";
	public final static String MUSIC_TITLE_TRI_THE_WALTZ = "TRI THE WALTZ";
	public final static String MUSIC_TITLE_SQUARED_OFF = "SQUARED OFF";
	public final static String MUSIC_TITLE_PENT_UP = "PENT UP";
	public final static String MUSIC_TITLE_HEXIDECIBEL = "HEXIDECIBEL";
	public final static String MUSIC_TITLE_INTERSEPTOR = "INTERSEPTOR";
	public final static String MUSIC_TITLE_ROCTOPUS = "ROCTOPUS";
	public final static String MUSIC_TITLE_NONPLUSSED = "NONPLUSSED";

	public final static String SAVE_NAME = "Squirgle Save";

	public final static String SAVE_VOLUME = "volume";
	public final static String SAVE_TRACK = "track";
	public final static String SAVE_PLAYED_BEFORE = "playedBefore";
	public final static String SAVE_USE_PHASES = "usePhases";
	public final static String SAVE_MAX_BASE = "maxBase";
	public final static String TARGET = "TARGET";

	//Saved data
	public Preferences save;

	//Options
	public int volume;
	public int track;

	public boolean playedBefore;
	public boolean usePhases;

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
	public BitmapFont fontTrackName;
	public BitmapFont fontTrackType;
	public GlyphLayout layout;
	public FreeTypeFontGenerator generator;
	public OrthographicCamera camera;
	public Viewport viewport;
	public ShapeRenderer shapeRendererFilled;
	public ShapeRenderer shapeRendererLine;
	public Draw draw;
	public Sound confirmSound;
	public Sound disconfirmSound;
	public List<Music> pointillismPhaseList;
	public List<Music> lineagePhaseList;
	public List<Music> triTheWaltzPhaseList;
	public List<Music> squaredOffPhaseList;
	public List<Music> pentUpPhaseList;
	public List<Music> hexidecibelPhaseList;
	public List<Music> exseptionToTheCoolPhaseList;
	public List<Music> roctopusPhaseList;
	public List<Music> nonplussedPhaseList;
	public List<String> musicTitleList;
	public Map<Integer, Music> trackMapFull;
	public Map<Integer, List<Music>> trackMapPhase;

	public void create() {
		VIRTUAL_WIDTH = Gdx.graphics.getWidth();
		VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
		ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

		save = Gdx.app.getPreferences(SAVE_NAME);

		volume = save.getInteger(SAVE_VOLUME, 10);
		track = save.getInteger(SAVE_TRACK, MUSIC_EXSEPTION_TO_THE_COOL);

		playedBefore = save.getBoolean(SAVE_PLAYED_BEFORE, false);
		usePhases = save.getBoolean(SAVE_USE_PHASES, true);

		base = 4;
		maxBase = save.getInteger(SAVE_MAX_BASE, 4);
		minBase = 4;
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
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
		pointillismPhaseList = new ArrayList<Music>();
		lineagePhaseList = new ArrayList<Music>();
		triTheWaltzPhaseList = new ArrayList<Music>();
		squaredOffPhaseList = new ArrayList<Music>();
		pentUpPhaseList = new ArrayList<Music>();
		hexidecibelPhaseList = new ArrayList<Music>();
		exseptionToTheCoolPhaseList = new ArrayList<Music>();
		roctopusPhaseList = new ArrayList<Music>();
		nonplussedPhaseList = new ArrayList<Music>();
		musicTitleList = new ArrayList<String>();
		trackMapFull = new HashMap<Integer, Music>();
		trackMapPhase = new HashMap<Integer, List<Music>>();

		setUpMusicTitleList();

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
		fontTrackName.dispose();
		fontTrackType.dispose();
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

	public void setUpFontTrackName(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()><?:";
		fontTrackName = generator.generateFont(parameter);
	}

	public void setUpFontTrackType(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()><?:";
		fontTrackType = generator.generateFont(parameter);
	}

	public void setUpMusicTitleList() {
		musicTitleList.add(MUSIC_TITLE_POINTILLISM);
		musicTitleList.add(MUSIC_TITLE_LINEAGE);
		musicTitleList.add(MUSIC_TITLE_TRI_THE_WALTZ);
		musicTitleList.add(MUSIC_TITLE_SQUARED_OFF);
		musicTitleList.add(MUSIC_TITLE_PENT_UP);
		musicTitleList.add(MUSIC_TITLE_HEXIDECIBEL);
		musicTitleList.add(MUSIC_TITLE_INTERSEPTOR);
		musicTitleList.add(MUSIC_TITLE_ROCTOPUS);
		musicTitleList.add(MUSIC_TITLE_NONPLUSSED);
	}

	public void setUpTracks() {
		//TODO: Add the rest of the tracks once they're written/recorded

		//themeFromSquirgle
		Music themeFromSquirgle = Gdx.audio.newMusic(Gdx.files.internal("music/Theme from Squirgle.wav"));
		themeFromSquirgle.setLooping(true);
		trackMapFull.put(MUSIC_THEME_FROM_SQUIRGLE, themeFromSquirgle);

		//pointillism (full)
		Music pointillism = Gdx.audio.newMusic(Gdx.files.internal("music/Pointillism (Full).wav"));
		pointillism.setLooping(true);
		trackMapFull.put(MUSIC_POINTILLISM, pointillism);

		//lineage (full)
		Music lineage = Gdx.audio.newMusic(Gdx.files.internal("music/Lineage (Full).wav"));
		lineage.setLooping(true);
		trackMapFull.put(MUSIC_LINEAGE, lineage);

		//triTheWaltz (full)
		Music triTheWaltz = Gdx.audio.newMusic(Gdx.files.internal("music/Tri, the Waltz (Full).wav"));
		triTheWaltz.setLooping(true);
		trackMapFull.put(MUSIC_TRI_THE_WALTZ, triTheWaltz);

		//squaredOff (full)
		Music squaredOff = Gdx.audio.newMusic(Gdx.files.internal("music/Squared Off (Full).wav"));
		squaredOff.setLooping(true);
		trackMapFull.put(MUSIC_SQUARED_OFF, squaredOff);

		//pentUp (full)
		Music pentUp = Gdx.audio.newMusic(Gdx.files.internal("music/Pent Up (Full).wav"));
		pentUp.setLooping(true);
		trackMapFull.put(MUSIC_PENT_UP, pentUp);

		//hexidecibel (full)
		Music hexidecibel = Gdx.audio.newMusic(Gdx.files.internal("music/Hexidecibel (Full).wav"));
		hexidecibel.setLooping(true);
		trackMapFull.put(MUSIC_HEXIDECIBEL, hexidecibel);

		//interseptor (full)
		Music interseptor = Gdx.audio.newMusic(Gdx.files.internal("music/Interseptor (Full).wav"));
		interseptor.setLooping(true);
		trackMapFull.put(MUSIC_INTERSEPTOR, interseptor);

		//roctopus (full)
		Music roctopus = Gdx.audio.newMusic(Gdx.files.internal("music/Roctopus (Full).wav"));
		roctopus.setLooping(true);
		trackMapFull.put(MUSIC_ROCTOPUS, roctopus);

		//nonplussed (full)
		Music nonplussed = Gdx.audio.newMusic(Gdx.files.internal("music/Nonplussed (Full).wav"));
		nonplussed.setLooping(true);
		trackMapFull.put(MUSIC_NONPLUSSED, nonplussed);

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
		trackMapPhase.put(MUSIC_EXSEPTION_TO_THE_COOL, exseptionToTheCoolPhaseList);
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
