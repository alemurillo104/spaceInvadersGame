package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.EnemyActor;
import com.spaceinvaders.game.actors.NaveActor;
import com.spaceinvaders.game.controllers.BalaController;
import com.spaceinvaders.game.controllers.EnemyController;
import com.spaceinvaders.game.controllers.InputController;

import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BasicScreen {

    public Stage stage;
    public Texture enemyTexture, naveTexture, balaTexture;
    public EnemyController enemies;
    public NaveActor naveActor;

    public BalaController balaController;

    public World world;

    public InputController processor;

    public GameScreen(MainGame game) {
        super(game);
        init();
    }

    public void init() {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        //stage.setDebugAll(true);
        world = new World(new Vector2(0, -10), true);

        processor = new InputController();
        Gdx.input.setInputProcessor(processor);


    }

    @Override
    public void show() {

        enemyTexture = game.getManager().get("sprite3.png");
        naveTexture = game.getManager().get("nave.png");
        balaTexture = game.getManager().get("bala.png");

        naveActor = new NaveActor(naveTexture, new Vector2(WIDTH /2, 0), world);
        enemies = new EnemyController(enemyTexture, naveActor.getPosition(), world);

        balaController = new BalaController(balaTexture, naveActor.getPosition(), naveActor.getWidth(), naveActor.getHeight(), enemies.objects, world, processor);

        stage.addActor(naveActor);
        for (EnemyActor enemy: enemies.objects)
            stage.addActor(enemy);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f,0.5f,0.8f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        balaController.dispararBala(stage);
        balaController.comprobarColision();

        stage.act();
        world.step(delta, 6,2);
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
        world.dispose();
        enemyTexture.dispose();
        naveTexture.dispose();
        balaTexture.dispose();
    }
}


//        balaActor = new BalaActor(balaTexture,Vector2.Zero, new Vector2(7,12),100, 1);
//        balaActor1 = new BalaActor(balaTexture,new Vector2(50, 0), new Vector2(7,12),100, 1);
//        balaActor2 = new BalaActor(balaTexture,new Vector2(100, 0), new Vector2(7,12),100, 1);
//        stage.addActor(balaActor);
