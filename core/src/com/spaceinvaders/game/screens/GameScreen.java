package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.EnemyActor;
import com.spaceinvaders.game.actors.NaveActor;
import com.spaceinvaders.game.controllers.EnemyController;

import java.util.Map;

import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BasicScreen {

    public Stage stage;
    public Texture enemyTexture, naveTexture;
    public EnemyController enemies;
    public NaveActor naveActor;

    public GameScreen(MainGame game) {
        super(game);
        init();
    }

    public void init(){
        stage = new Stage(new StretchViewport(WIDTH,HEIGHT));
        enemyTexture = game.getManager().get("enemyS2.png");
        naveTexture = game.getManager().get("nave.png");
        enemies = new EnemyController(enemyTexture);
        naveActor = new NaveActor(naveTexture, Vector2.Zero);

        for (EnemyActor enemy: enemies.objects)
            stage.addActor(enemy);

        stage.addActor(naveActor);
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f,0.5f,0.8f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        enemies.delEnemy();

        stage.act();
        stage.draw();

    }

    @Override
    public void hide() {
        stage.clear();

        for (EnemyActor enemy: enemies.objects)
            enemy.remove();

        naveActor.remove();
    }

    @Override
    public void dispose() {
        stage.dispose();
        enemyTexture.dispose();
        naveTexture.dispose();
    }
}
