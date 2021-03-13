package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.EnemyActor;
import com.spaceinvaders.game.actors.ScoreLActor;
import com.spaceinvaders.game.actors.NaveActor;
import com.spaceinvaders.game.controllers.BalaController;
import com.spaceinvaders.game.controllers.EnemyController;
import com.spaceinvaders.game.controllers.InputController;

import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BasicScreen {

    public Stage stage;
    public Texture enemyTexture, naveTexture, balaPlayerTexture, balaEnemyTexture;
    public EnemyController enemies;
    public NaveActor naveActor;

    public BalaController balaController;

    public InputController inputController;

    public int score = 0;
    public ScoreLActor scoreLActor;

    public GameScreen(MainGame game) {
        super(game);
        init();
    }

    public void init() {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        //stage.setDebugAll(true);

        inputController = new InputController();
        Gdx.input.setInputProcessor(inputController);

        enemyTexture = game.getManager().get("sprite3.png");
        naveTexture = game.getManager().get("nave.png");
        balaPlayerTexture = game.getManager().get("balaPlayer.png");
        balaEnemyTexture = game.getManager().get("balaEnemy.png");

        naveActor = new NaveActor(naveTexture, new Vector2(WIDTH /2, 0));
        enemies = new EnemyController(enemyTexture, naveActor.getPosition());

        scoreLActor = new ScoreLActor(naveActor, score);

        balaController = new BalaController(balaPlayerTexture, balaEnemyTexture, naveActor.getPosition(), naveActor.getWidth(), naveActor.getHeight(), enemies.objects, inputController, score, scoreLActor);

        stage.addActor(scoreLActor);

        stage.addActor(naveActor);
        for (EnemyActor enemy: enemies.objects)
            stage.addActor(enemy);

    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f,0.5f,0.8f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //hud rendering
        //updateAndRenderHUD();

        balaController.shootBalaEnemy(stage);
        balaController.shoot(stage);
        balaController.comprobarColision();
        balaController.comprobarColision2Player();
        //balaController.comprobarColisionToPlayer();

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        stage.clear();

        for (EnemyActor enemy: enemies.objects)
            enemy.remove();

        naveActor.remove();
        scoreLActor.remove();
    }

    @Override
    public void dispose() {
        stage.dispose();
        enemyTexture.dispose();
        naveTexture.dispose();
        balaPlayerTexture.dispose();
        balaEnemyTexture.dispose();
    }
}

