package com.spaceinvaders.game.screens;

import com.spaceinvaders.game.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen extends BasicScreen{

    private Stage stage;
    private Skin skin;
    private Image gameover;
    private TextButton retry, menu;

    public GameOverScreen(final MainGame game) {
        super(game);

        stage = new Stage(new FitViewport(640,360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Texture texture = game.getManager().get("gameover.png");
        //gameover = new Image(game.getManager().get("gameover.png", Texture.class));
        gameover = new Image(texture);
        retry = new TextButton("Retry", skin);
        menu = new TextButton("Menu", skin);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.resetGameScreen();
                game.setScreen(game.getGameScreen());

            }
        });

        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //game.setScreen(game.gameScreen); //idk
                System.out.println("Aun nada ñe");
            }
        });

        gameover.setPosition(320 - gameover.getWidth() / 2, 320 - gameover.getHeight());

        retry.setSize(200,80);
        menu.setSize(200,80);

        retry.setPosition(60,50);
        menu.setPosition(380,50);

        stage.addActor(retry);
        stage.addActor(menu);
        stage.addActor(gameover);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Procesa eventos de todas las entradas
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null); //para que ya no trate estas entradas cuando cambie la pantalla
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.1f,0.8f,1);
        //Gdx.gl.glClearColor(0.3f,0.1f,1f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(); //actualiza el stage
        stage.draw(); //lo dibuja vaya
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
