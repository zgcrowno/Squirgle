package com.screenlooker.squirgle;

import com.badlogic.gdx.*;
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
public class Squirgle extends Game {
	private static final int VIRTUAL_WIDTH = 768;
	private static final int VIRTUAL_HEIGHT = 1024;
	private static final float ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

	//Sound options
	public float volume;
	public int musicStyle;

	//Color options
	public boolean colorblind;

	//Connectivity options
	public boolean postToLeaderboards;

	public int base;
	public int maxBase;
	public int minBase;
	public SpriteBatch batch;
	public BitmapFont font;
	public GlyphLayout layout;
	public FreeTypeFontGenerator generator;
	public OrthographicCamera camera;
	public Viewport viewport;
	public ShapeRenderer shapeRendererFilled;
	public ShapeRenderer shapeRendererLine;
	public Draw draw;
	public Sound bassDrum;
	public Sound hiHat;
	public Sound snareDrum;
	public int key;
	public List<Sound> aMajorNotes;
	public List<Sound> aMinorNotes;
	public List<Sound> aSharpMajorNotes;
	public List<Sound> aSharpMinorNotes;
	public List<Sound> bMajorNotes;
	public List<Sound> bMinorNotes;
	public List<Sound> cMajorNotes;
	public List<Sound> cMinorNotes;
	public List<Sound> cSharpMajorNotes;
	public List<Sound> cSharpMinorNotes;
	public List<Sound> dMajorNotes;
	public List<Sound> dMinorNotes;
	public List<Sound> dSharpMajorNotes;
	public List<Sound> dSharpMinorNotes;
	public List<Sound> eMajorNotes;
	public List<Sound> eMinorNotes;
	public List<Sound> fMajorNotes;
	public List<Sound> fMinorNotes;
	public List<Sound> fSharpMajorNotes;
	public List<Sound> fSharpMinorNotes;
	public List<Sound> gMajorNotes;
	public List<Sound> gMinorNotes;
	public List<Sound> gSharpMajorNotes;
	public List<Sound> gSharpMinorNotes;
	public Map<Integer, List<Sound>> keyMap;

	public void create() {
		base = 4;
		maxBase = 9;
		minBase = 4;
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UltraCondensedSansSerif.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 72;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
		font = generator.generateFont(parameter);
		layout = new GlyphLayout();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
		camera.position.set(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2,0);
		shapeRendererFilled = new ShapeRenderer();
		shapeRendererLine = new ShapeRenderer();
		draw = new Draw(this);
		bassDrum = Gdx.audio.newSound(Gdx.files.internal("sounds/percussion/bassDrum.wav"));
		hiHat = Gdx.audio.newSound(Gdx.files.internal("sounds/percussion/hiHat.wav"));
		snareDrum = Gdx.audio.newSound(Gdx.files.internal("sounds/percussion/snareDrum.wav"));
		key = MathUtils.random(SoundUtils.G_SHARP_MINOR);

		//aMajor
		aMajorNotes = new ArrayList<Sound>();
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		aMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));

		//aMinor
		aMinorNotes = new ArrayList<Sound>();
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		aMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));

		//aSharpMajor
		aSharpMajorNotes = new ArrayList<Sound>();
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		aSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));

		//aSharpMinor
		aSharpMinorNotes = new ArrayList<Sound>();
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		aSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));

		//bMajor
		bMajorNotes = new ArrayList<Sound>();
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		bMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));

		//bMinor
		bMinorNotes = new ArrayList<Sound>();
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		bMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));

		//cMajor
		cMajorNotes = new ArrayList<Sound>();
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		cMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));

		//cMinor
		cMinorNotes = new ArrayList<Sound>();
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		cMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));

		//cSharpMajor
		cSharpMajorNotes = new ArrayList<Sound>();
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		cSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));

		//cSharpMinor
		cSharpMinorNotes = new ArrayList<Sound>();
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		cSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));

		//dMajor
		dMajorNotes = new ArrayList<Sound>();
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		dMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));

		//dMinor
		dMinorNotes = new ArrayList<Sound>();
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		dMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));

		//dSharpMajor
		dSharpMajorNotes = new ArrayList<Sound>();
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		dSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));

		//dSharpMinor
		dSharpMinorNotes = new ArrayList<Sound>();
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		dSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));

		//eMajor
		eMajorNotes = new ArrayList<Sound>();
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		eMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));

		//eMinor
		eMinorNotes = new ArrayList<Sound>();
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		eMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));

		//fMajor
		fMajorNotes = new ArrayList<Sound>();
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		fMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));

		//fMinor
		fMinorNotes = new ArrayList<Sound>();
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		fMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));

		//fSharpMajor
		fSharpMajorNotes = new ArrayList<Sound>();
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		fSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));

		//fSharpMinor
		fSharpMinorNotes = new ArrayList<Sound>();
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		fSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));

		//gMajor
		gMajorNotes = new ArrayList<Sound>();
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		gMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));

		//gMinor
		gMinorNotes = new ArrayList<Sound>();
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/a3.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/d3.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		gMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));

		//gSharpMajor
		gSharpMajorNotes = new ArrayList<Sound>();
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c3.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/c4.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/f3.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g1.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g2.wav")));
		gSharpMajorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/g3.wav")));

		//gSharpMinor
		gSharpMinorNotes = new ArrayList<Sound>();
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/gSharp3.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/aSharp3.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/b3.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/cSharp3.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/dSharp3.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/e3.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp1.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp2.wav")));
		gSharpMinorNotes.add(Gdx.audio.newSound(Gdx.files.internal("sounds/notes/fSharp3.wav")));

		keyMap = new HashMap<Integer, List<Sound>>();
		keyMap.put(SoundUtils.A_MAJOR, aMajorNotes);
		keyMap.put(SoundUtils.A_MINOR, aMinorNotes);
		keyMap.put(SoundUtils.A_SHARP_MAJOR, aSharpMajorNotes);
		keyMap.put(SoundUtils.A_SHARP_MINOR, aSharpMinorNotes);
		keyMap.put(SoundUtils.B_MAJOR, bMajorNotes);
		keyMap.put(SoundUtils.B_MINOR, bMinorNotes);
		keyMap.put(SoundUtils.C_MAJOR, cMajorNotes);
		keyMap.put(SoundUtils.C_MINOR, cMinorNotes);
		keyMap.put(SoundUtils.C_SHARP_MAJOR, cSharpMajorNotes);
		keyMap.put(SoundUtils.C_SHARP_MINOR, cSharpMinorNotes);
		keyMap.put(SoundUtils.D_MAJOR, dMajorNotes);
		keyMap.put(SoundUtils.D_MINOR, dMinorNotes);
		keyMap.put(SoundUtils.D_SHARP_MAJOR, dSharpMajorNotes);
		keyMap.put(SoundUtils.D_SHARP_MINOR, dSharpMinorNotes);
		keyMap.put(SoundUtils.E_MAJOR, eMajorNotes);
		keyMap.put(SoundUtils.E_MINOR, eMinorNotes);
		keyMap.put(SoundUtils.F_MAJOR, fMajorNotes);
		keyMap.put(SoundUtils.F_MINOR, fMinorNotes);
		keyMap.put(SoundUtils.F_SHARP_MAJOR, fSharpMajorNotes);
		keyMap.put(SoundUtils.F_SHARP_MINOR, fSharpMinorNotes);
		keyMap.put(SoundUtils.G_MAJOR, gMajorNotes);
		keyMap.put(SoundUtils.G_MINOR, gMinorNotes);
		keyMap.put(SoundUtils.G_SHARP_MAJOR, gSharpMajorNotes);
		keyMap.put(SoundUtils.G_SHARP_MINOR, gSharpMinorNotes);

		generator.dispose();

		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		bassDrum.dispose();
		hiHat.dispose();
		snareDrum.dispose();
		for(int i = 0; i < keyMap.size(); i++) {
			for(int j = 0; j < keyMap.get(i).size(); j++) {
				keyMap.get(i).get(j).dispose();
			}
		}
		generator.dispose();
	}

	public void resetInstanceData() {
		draw = new Draw(this);
	}

}
