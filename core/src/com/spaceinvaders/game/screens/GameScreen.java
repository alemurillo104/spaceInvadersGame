package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.EnemiesActors;
import com.spaceinvaders.game.actors.SpaceShipActor;
import java.util.Map;

import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BasicScreen {

    private Stage stage;
    private SpaceShipActor spaceShipActor;
    private EnemiesActors enemies;
    private Viewport viewport;

    public GameScreen(MainGame mainGame) {
        super(mainGame);
        viewport = new StretchViewport(WIDTH,HEIGHT);
        stage  = new Stage(viewport);
    }

    @Override
    public void show() {
        Texture spaceShipTexture = game.getManager().get("nave.png");
        Texture enemyTexture = game.getManager().get("enemy.png");
        spaceShipActor = new SpaceShipActor(spaceShipTexture, Vector2.Zero);
        enemies = new EnemiesActors(enemyTexture, new Vector2(50,50)); //idk

        //stage.addActor(spaceShipActor);

        for (Map.Entry<String, Actor> entry : enemies.objects.entrySet()) {
            Actor enemy = entry.getValue();
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
        for (Map.Entry<String, Actor> entry : enemies.objects.entrySet()) {
            Actor enemy = entry.getValue();
            enemy.remove();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
