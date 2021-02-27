package com.spaceinvaders.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.spaceinvaders.game.controllers.EnemyController;

public class MainGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture enemyTexture;
	EnemyController enemyController;

	@Override
	public void create () {
		batch = new SpriteBatch();
		enemyTexture = new Texture("enemy.png");

		enemyController = new EnemyController(enemyTexture, batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		enemyController.executeMovement();
		enemyController.draw();

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		enemyTexture.dispose();
	}
}

