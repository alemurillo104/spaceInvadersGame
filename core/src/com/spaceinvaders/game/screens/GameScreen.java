package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.SpaceShipActor;
import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BaseScreen {

    private Stage stage;
    private SpaceShipActor spaceShipActor;

    public GameScreen(MainGame mainGame) {
        super(mainGame);

        stage  = new Stage(new FitViewport(WIDTH, HEIGHT));
    }

    @Override
    public void show() {
        Texture spaceShipTexture = game.getManager().get("nave.png");
        spaceShipActor = new SpaceShipActor(spaceShipTexture, Vector2.Zero);
        stage.addActor(spaceShipActor);
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
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
