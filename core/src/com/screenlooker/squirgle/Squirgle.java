package com.screenlooker.squirgle;

import com.badlogic.gdx.*;
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
import org.jfugue.player.Player;

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
	public Player fugue;

	public void create() {
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("UltraCondensedSansSerif.ttf"));
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
		fugue = new Player();

		generator.dispose();

		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		generator.dispose();
	}

	public void resetInstanceData() {
		draw = new Draw(this);
	}

}
