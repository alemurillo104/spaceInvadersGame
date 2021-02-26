package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.EnemiesActors;
import com.spaceinvaders.game.actors.EnemyActor;
import com.spaceinvaders.game.actors.SpaceShipActor;
import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BasicScreen {

    private Stage stage;
    private SpaceShipActor spaceShipActor;
    private EnemiesActors enemiesActors;
    private Viewport viewport;

    public GameScreen(MainGame mainGame) {
        super(mainGame);
        viewport = new FitViewport(WIDTH, HEIGHT);
        stage  = new Stage(viewport);
    }

    @Override
    public void show() {
        Texture spaceShipTexture = game.getManager().get("nave.png");
        Texture enemyTexture = game.getManager().get("enemy.png");
        spaceShipActor = new SpaceShipActor(spaceShipTexture, Vector2.Zero);
        enemiesActors = new EnemiesActors(enemyTexture);

//        stage.addActor(spaceShipActor);

        for (EnemyActor enemy : enemiesActors.enemies) {
            stage.addActor(enemy);
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        stage.clear();
        spaceShipActor.detach();
        spaceShipActor.remove();
        for (EnemyActor enemy : enemiesActors.enemies) {
            enemy.detach();
            enemy.remove();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
