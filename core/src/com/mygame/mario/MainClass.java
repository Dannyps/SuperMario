package com.mygame.mario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.Levels.Level1;
import com.mygame.mario.Levels.Level2;
import com.mygame.mario.Levels.Level3;

public class MainClass extends Game {

	public static final int V_WIDTH = 800; //400; //800
	public static final int V_HEIGTH = 400; //208; //400

	public static final float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short MARO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYRD_BIT = 16;

	public SpriteBatch batch;
	public static AssetManager manager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("Audio/music/mario_music.ogg",Music.class);
		manager.load("Audio/sounds/breakblock.wav",Sound.class);
		manager.load("Audio/sounds/bump.wav",Sound.class);
		manager.load("Audio/sounds/coin.wav",Sound.class);
		manager.load("Audio/sounds/mariodie.wav",Sound.class);
		manager.load("Audio/sounds/powerdown.wav",Sound.class);
		manager.load("Audio/sounds/powerup.wav",Sound.class);
		manager.load("Audio/sounds/powerup_spawn.wav",Sound.class);
		manager.load("Audio/sounds/stomp.wav",Sound.class);
		manager.finishLoading();
		setScreen(new BaseScreen(this));
		MainClass.manager.get("Audio/music/mario_music.ogg",Music.class).play();
		/*setScreen(new Level1(this));
		setScreen(new Level2(this));
		setScreen(new Level3(this));*/
	}

	@Override
	public void render () {
		super.render();
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		super.dispose();
		//batch.dispose();
	}
}
