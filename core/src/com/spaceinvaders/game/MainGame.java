package com.spaceinvaders.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.spaceinvaders.game.screens.GameOverScreen;
import com.spaceinvaders.game.screens.GameScreen;

public class MainGame extends Game {

	private GameScreen gameScreen;
	public GameOverScreen gameOverScreen;

	private AssetManager assetManager;

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void resetGameScreen() {
		//this.gameScreen = gameScreen;
		gameScreen = new GameScreen(this);
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
		assetManager.finishLoading();

		gameScreen = new GameScreen(this);
		gameOverScreen = new GameOverScreen(this);

		setScreen(gameScreen);
	}
}

