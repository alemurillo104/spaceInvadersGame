package com.spaceinvaders.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.spaceinvaders.game.screens.GameScreen;

public class MainGame extends Game {

	public GameScreen gameScreen;

	private AssetManager assetManager;

	public AssetManager getManager() {
		return assetManager;
	}

	@Override
	public void create() {

		assetManager = new AssetManager();
		assetManager.load("nave.png", Texture.class);
		assetManager.finishLoading();

		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
}

