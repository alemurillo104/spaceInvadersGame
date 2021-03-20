package com.spaceinvaders.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.spaceinvaders.game.screens.GameSOScreen;
import com.spaceinvaders.game.screens.GameScreen;

public class MainGame extends Game {

	private GameScreen gameScreen;
	public GameSOScreen gameSOScreen;

	private AssetManager assetManager;

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void resetGameScreen() {
		gameScreen = new GameScreen(this);
	}

	public void changeTextureScreen(boolean sw){
		gameSOScreen.setInicio(sw);
	}

	public AssetManager getManager() {
		return assetManager;
	}

	@Override
	public void create() {

		assetManager = new AssetManager();
		assetManager.load("nave.png", Texture.class);
		assetManager.load("enemy.png", Texture.class);
		assetManager.load("enemyS4.png", Texture.class);
		assetManager.load("sprite3.png", Texture.class);
		assetManager.load("balaPlayer.png", Texture.class);
		assetManager.load("balaEnemy.png", Texture.class);
		assetManager.load("gameover.png", Texture.class);
		assetManager.load("spriteN2.png", Texture.class);
		assetManager.load("title1.png", Texture.class);
		assetManager.finishLoading();

		gameScreen = new GameScreen(this);
		gameSOScreen = new GameSOScreen(this);

		setScreen(gameSOScreen);
	}
}

