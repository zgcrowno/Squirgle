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
import java.util.List;

//TODO: Many of the variables throughout this entire game will have to be replaced with screen-size dependent alternatives
public class Squirgle extends Game {
	private static final int VIRTUAL_WIDTH = 768;
	private static final int VIRTUAL_HEIGHT = 1024;
	private static final float ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

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

	public void create() {
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

		//aMajor
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

		//aSharpMajor
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

		//bMajor
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

		//cMajor
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

		//cSharpMajor
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
		//TODO: Make sure I dispose of any sounds I add
		generator.dispose();
	}

	public void resetInstanceData() {
		draw = new Draw(this);
	}

}
