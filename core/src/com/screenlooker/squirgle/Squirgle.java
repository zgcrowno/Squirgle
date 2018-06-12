package com.screenlooker.squirgle;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.*;
import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUserStatsCallback;
import com.screenlooker.squirgle.screen.*;
import com.screenlooker.squirgle.steam.Achievements;
import com.screenlooker.squirgle.steam.SquirgleUserStatsCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Squirgle extends Game {
	public static int VIRTUAL_WIDTH;
	public static int VIRTUAL_HEIGHT;
	public static float ASPECT_RATIO;

	public final static int PARTITION_DIVISOR = 80;
	public final static int FPS = 60;
	public final static int MAX_POSSIBLE_BASE = 9;
	public final static int SCORE_TO_UNLOCK_NEW_BASE = 50;
	public final static int THIRTY_SECONDS = 30;
	public final static int ONE_MINUTE = 60;
	public final static int THREE_MINUTES = 180;
	public final static int FIVE_MINUTES = 300;

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

	public final static int GAMEPLAY_SQUIRGLE = 0;
	public final static int GAMEPLAY_BATTLE = 1;
	public final static int GAMEPLAY_TIME_ATTACK = 2;
	public final static int GAMEPLAY_TIME_BATTLE = 3;
	public final static int GAMEPLAY_BATTLE_LOCAL = 4;
	public final static int GAMEPLAY_TIME_BATTLE_LOCAL = 5;
	public final static int GAMEPLAY_BATTLE_ONLINE = 6;
	public final static int GAMEPLAY_TIME_BATTLE_ONLINE = 7;
	public final static int GAMEPLAY_TRANCE = 8;
	public final static int GAMEPLAY_GENERAL = 9; //This is for the general stats category

	public final static int P2_CONTROLS_MOUSE = 0;
	public final static int P2_CONTROLS_NUMPAD = 1;
	public final static int P2_CONTROLS_NUMBERS = 2;

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

	public final static String DIFFICULTY_EASY = "POP";
	public final static String DIFFICULTY_MEDIUM = "ROCK";
	public final static String DIFFICULTY_HARD = "JAZZ";

	public final static String HARDCORE_ENABLED = "ENABLED";
	public final static String HARDCORE_DISABLED = "DISABLED";

	public final static String RESULTS_VICTORY = "VICTORY";
	public final static String RESULTS_DEFEAT = "DEFEAT";
	public final static String RESULTS_TIE = "TIE";

	public final static String SAVE_NAME = "Squirgle Save";
	public final static String SAVE_VOLUME = "volume";
	public final static String SAVE_FX_VOLUME = "fxVolume";
	public final static String SAVE_P2_CONTROLS = "p2Controls";
	public final static String SAVE_TRACK = "track";
	public final static String SAVE_PLAYED_BEFORE = "playedBefore";
	public final static String SAVE_BEATEN_BEFORE = "beatenBefore";
	public final static String SAVE_USE_PHASES = "usePhases";
	public final static String SAVE_MAX_BASE = "maxBase";
	public final static String SAVE_TIME_ATTACK_NUM_SECONDS = "timeAttackNumSeconds";
	public final static String SAVE_DIFFICULTY = "difficulty";
	public final static String SAVE_HARDCORE = "hardcore";

	public final static String HAND = "HAND";

	public AssetManager manager;

	//Saved data
	public Preferences save;

	//Options
	public int volume;
	public int fxVolume;
	public int p2Controls;
	public int track;
	public String difficulty;
	public boolean hardcore;

	public boolean playedBefore;
	public boolean beatenBefore;
	public boolean usePhases;

	public boolean widthGreater;
	public boolean showWipeDataPrompt;
	public float widthOrHeightSmaller;
	public float widthOrHeightBigger;
	public float fourthOfScreen;
	public float thirdOfScreen;
	public float fiveTwelfthsOfScreen;
	public int gameplayType;
	public int base;
	public int maxBase;
	public int minBase;
	public int timeAttackNumSeconds;
	public float partitionSize;
	public boolean desktop;
	public SpriteBatch batch;
	public BitmapFont fontLoading;
	public BitmapFont fontVolume;
	public BitmapFont fontScore;
	public BitmapFont fontTarget;
	public BitmapFont fontSquirgle;
	public BitmapFont fontTrackName;
	public BitmapFont fontTrackType;
	public BitmapFont fontTime;
	public BitmapFont fontDifficulty;
	public BitmapFont fontPlayer;
	public BitmapFont fontStats;
	public BitmapFont fontTutorialHelp;
	public BitmapFont fontSkip;
	public BitmapFont fontButton;
	public BitmapFont fontHardcore;
	public BitmapFont fontOptions;
	public BitmapFont fontNumPlayers;
	public BitmapFont fontSquirgleMainMenu;
	public BitmapFont fontWarning;
	public BitmapFont fontWarningContent;
	public BitmapFont fontCredits;
	public GlyphLayout layout;
	public FreeTypeFontGenerator generator;
	public OrthographicCamera camera;
	public Viewport viewport;
	public ShapeRenderer shapeRendererFilled;
	public ShapeRenderer shapeRendererLine;
	public Draw draw;
	public Stats stats;
	public Sound confirmSound;
	public Sound disconfirmSound;
	public Sound correctInputSound;
	public Sound incorrectInputSound;
	public Sound gameOverSound;
	public List<String> musicTitleList;
	public Map<Integer, Music> trackMapFull;

	public SteamUserStats steamUserStats;

	public void create() {
		//Initialize the Steam API
		try {
			if (!SteamAPI.init()) {
				// Steamworks initialization error, e.g. Steam client not running
				Gdx.app.log("failure", "Steamworks initialization error, e.g. Steam client not running");
			}
		} catch (SteamException e) {
			// Error extracting or loading native libraries
			Gdx.app.log("error", e.getMessage());
		}
		steamUserStats = new SteamUserStats(new SquirgleUserStatsCallback());
		//steamUserStats.setAchievement("Squared Off");
		//steamUserStats.clearAchievement("Squared Off");


		//These catches prevent the back and menu keys from interfering with the game
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);

		VIRTUAL_WIDTH = Gdx.graphics.getWidth();
		VIRTUAL_HEIGHT = Gdx.graphics.getHeight();

		manager = new AssetManager();

		save = Gdx.app.getPreferences(SAVE_NAME);

		volume = save.getInteger(SAVE_VOLUME, 10);
		fxVolume = save.getInteger(SAVE_FX_VOLUME, 3);
		p2Controls = save.getInteger(SAVE_P2_CONTROLS, P2_CONTROLS_MOUSE);
		track = save.getInteger(SAVE_TRACK, MUSIC_POINTILLISM);
		difficulty = save.getString(SAVE_DIFFICULTY, DIFFICULTY_MEDIUM);
		hardcore = save.getBoolean(SAVE_HARDCORE, false);

		playedBefore = save.getBoolean(SAVE_PLAYED_BEFORE, false);
		beatenBefore = save.getBoolean(SAVE_BEATEN_BEFORE, false);
		usePhases = false; //Removed Full/Split music functionality; now always Full.

		gameplayType = GAMEPLAY_SQUIRGLE;
		base = 4;
		maxBase = save.getInteger(SAVE_MAX_BASE, 4);
		minBase = 4;
		timeAttackNumSeconds = save.getInteger(SAVE_TIME_ATTACK_NUM_SECONDS, ONE_MINUTE);
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		layout = new GlyphLayout();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
		camera.position.set(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2,0);
		widthGreater = camera.viewportWidth > camera.viewportHeight;
		showWipeDataPrompt = false;
		widthOrHeightSmaller = widthGreater ? camera.viewportHeight : camera.viewportWidth;
		widthOrHeightBigger = widthGreater ? camera.viewportWidth : camera.viewportHeight;

		ASPECT_RATIO = widthOrHeightBigger / widthOrHeightSmaller;

		fourthOfScreen = widthOrHeightSmaller / 4;
		thirdOfScreen = widthOrHeightSmaller / 3;
		fiveTwelfthsOfScreen = (5 * widthOrHeightSmaller) / 12;
		partitionSize = widthOrHeightSmaller / PARTITION_DIVISOR;
		desktop = Gdx.app.getType().equals(Application.ApplicationType.Desktop);
		shapeRendererFilled = new ShapeRenderer();
		shapeRendererLine = new ShapeRenderer();
		draw = new Draw(this);
		stats = new Stats(this);
		confirmSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/Correct Input Sound.ogg"));
		disconfirmSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/Incorrect Input Sound.ogg"));
		correctInputSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/Correct Input Sound.ogg"));
		incorrectInputSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/Incorrect Input Sound.ogg"));
		gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fx/Game Over Sound.ogg"));
		musicTitleList = new ArrayList<String>();
		trackMapFull = new HashMap<Integer, Music>();

		//This hides the cursor (so I can replace it with a custom one) on desktop
		Gdx.input.setCursorCatched(true);

		setUpMusicTitleList();

		setUpTracks();

		generator.dispose();

		this.setScreen(new SplashScreen(this));
	}

	public void render() { super.render(); }

	public void dispose() {
		batch.dispose();
		if(fontLoading != null)
			fontLoading.dispose();
		if(fontVolume != null)
			fontVolume.dispose();
		if(fontScore != null)
			fontScore.dispose();
		if(fontTarget != null)
			fontTarget.dispose();
		if(fontSquirgle != null)
			fontSquirgle.dispose();
		if(fontTrackName != null)
			fontTrackName.dispose();
		if(fontTrackType != null)
			fontTrackType.dispose();
		if(fontTime != null)
			fontTime.dispose();
		if(fontDifficulty != null)
			fontDifficulty.dispose();
		if(fontPlayer != null)
			fontPlayer.dispose();
		if(fontStats != null)
			fontStats.dispose();
		if(fontTutorialHelp != null)
			fontTutorialHelp.dispose();
		if(fontSkip != null)
			fontSkip.dispose();
		if(fontButton != null)
			fontButton.dispose();
		if(fontHardcore != null)
			fontHardcore.dispose();
		if(fontOptions != null)
			fontOptions.dispose();
		if(fontNumPlayers != null)
			fontNumPlayers.dispose();
		if(fontSquirgleMainMenu != null)
			fontSquirgleMainMenu.dispose();
		if(fontWarning != null)
			fontWarning.dispose();
		if(fontWarningContent != null)
			fontWarningContent.dispose();
		if(fontCredits != null)
			fontCredits.dispose();
		generator.dispose();
		shapeRendererFilled.dispose();
		shapeRendererLine.dispose();
		confirmSound.dispose();
		correctInputSound.dispose();
		incorrectInputSound.dispose();
		disconfirmSound.dispose();
		gameOverSound.dispose();
		generator.dispose();
		for(int i = 0; i < trackMapFull.size(); i++) {
			trackMapFull.get(i).dispose();
		}
		steamUserStats.dispose();
		SteamAPI.shutdown();
	}

	public void resetInstanceData() {
		draw = new Draw(this);
	}

	//TODO: Collapse all of these font methods into one
	public void setUpFontLoading(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontLoading = generator.generateFont(parameter);
	}

	public void setUpFontScore(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontScore = generator.generateFont(parameter);
	}

	public void setUpFontSquirgle(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontSquirgle = generator.generateFont(parameter);
	}

	public void setUpFontTrackName(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontTrackName = generator.generateFont(parameter);
	}

	public void setUpFontTrackType(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontTrackType = generator.generateFont(parameter);
	}

	public void setUpFontStats(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontStats = generator.generateFont(parameter);
	}

	public void setUpFontTutorialHelp(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontTutorialHelp = generator.generateFont(parameter);
	}

	public void setUpFontSkip(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontSkip = generator.generateFont(parameter);
	}

	public void setUpFontButton(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontButton = generator.generateFont(parameter);
	}

	public void setUpFontOptions(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontOptions = generator.generateFont(parameter);
	}

	public void setUpFontNumPlayers(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontNumPlayers = generator.generateFont(parameter);
	}

	public void setUpFontSquirgleMainMenu(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontSquirgleMainMenu = generator.generateFont(parameter);
	}

	public void setUpFontWarning(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontWarning = generator.generateFont(parameter);
	}

	public void setUpFontWarningContent(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontWarningContent = generator.generateFont(parameter);
	}

	public void setUpFontCredits(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!',()><?:;/-[]|=%+#\"";
		fontCredits = generator.generateFont(parameter);
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
		Music themeFromSquirgle = Gdx.audio.newMusic(Gdx.files.internal("music/Theme from Squirgle.ogg"));
		themeFromSquirgle.setLooping(true);
		trackMapFull.put(MUSIC_THEME_FROM_SQUIRGLE, themeFromSquirgle);

		//pointillism (full)
		Music pointillism = Gdx.audio.newMusic(Gdx.files.internal("music/Pointillism (Full).ogg"));
		pointillism.setLooping(true);
		trackMapFull.put(MUSIC_POINTILLISM, pointillism);

		//lineage (full)
		Music lineage = Gdx.audio.newMusic(Gdx.files.internal("music/Lineage (Full).ogg"));
		lineage.setLooping(true);
		trackMapFull.put(MUSIC_LINEAGE, lineage);

		//triTheWaltz (full)
		Music triTheWaltz = Gdx.audio.newMusic(Gdx.files.internal("music/Tri the Waltz (Full).ogg"));
		triTheWaltz.setLooping(true);
		trackMapFull.put(MUSIC_TRI_THE_WALTZ, triTheWaltz);

		//squaredOff (full)
		Music squaredOff = Gdx.audio.newMusic(Gdx.files.internal("music/Squared Off (Full).ogg"));
		squaredOff.setLooping(true);
		trackMapFull.put(MUSIC_SQUARED_OFF, squaredOff);

		//pentUp (full)
		Music pentUp = Gdx.audio.newMusic(Gdx.files.internal("music/Pent Up (Full).ogg"));
		pentUp.setLooping(true);
		trackMapFull.put(MUSIC_PENT_UP, pentUp);

		//hexidecibel (full)
		Music hexidecibel = Gdx.audio.newMusic(Gdx.files.internal("music/Hexidecibel (Full).ogg"));
		hexidecibel.setLooping(true);
		trackMapFull.put(MUSIC_HEXIDECIBEL, hexidecibel);

		//interseptor (full)
		Music interseptor = Gdx.audio.newMusic(Gdx.files.internal("music/Interseptor (Full).ogg"));
		interseptor.setLooping(true);
		trackMapFull.put(MUSIC_INTERSEPTOR, interseptor);

		//roctopus (full)
		Music roctopus = Gdx.audio.newMusic(Gdx.files.internal("music/Roctopus (Full).ogg"));
		roctopus.setLooping(true);
		trackMapFull.put(MUSIC_ROCTOPUS, roctopus);

		//nonplussed (full)
		Music nonplussed = Gdx.audio.newMusic(Gdx.files.internal("music/Nonplussed (Full).ogg"));
		nonplussed.setLooping(true);
		trackMapFull.put(MUSIC_NONPLUSSED, nonplussed);
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

	public void wipeSave() {
		base = 4;
		maxBase = base;
		track = MUSIC_POINTILLISM;
		updateSave(SAVE_TRACK, track);
		updateSave(SAVE_MAX_BASE, base);

		stats.wipeSave();
	}

	public void clearAllAchievements() {
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_SQUARED_OFF);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_PENT_UP);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_HEXIDECIBEL);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_INTERSEPTOR);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_ROCTOPUS);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_NONPLUSSED);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_QUADRIRADERAL);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_FOUR_THE_MEMORIES);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_FIVE_FINGERED_VISCOUNT);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_THE_SACRAPENT);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_SIX_SHOOTER);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_OEDIPUS_HEX);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_EXSEPTION_TO_THE_COOL);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_SEPTER_OF_WEALTH_AND_POWER);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_OCT_IN);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_INSEIGHTABLE);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_NONARYDICULOUS);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_WHATS_YOURS_IS_NINE);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_HUMBLE_BEGINNINGS);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_EARLY_APPOINTMENT);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_ON_DOWN_THE_LINE);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_TRI_HARD);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_SQUARE_BREED);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_STAYING_AFTER_5);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_BETTER_THAN_HEX);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_THE_SEVENTERTAINER);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_EIGHT_FOR_LIFE);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_NUMBER_9);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_CANT_REPLACE_THE_FEAR);
		steamUserStats.clearAchievement(Achievements.ACHIEVEMENT_MAKE_NAN_CRY);
	}
}
