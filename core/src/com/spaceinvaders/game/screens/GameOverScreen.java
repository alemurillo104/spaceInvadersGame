package com.spaceinvaders.game.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
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

import static com.spaceinvaders.game.common.Constants.HEIGHT;
import static com.spaceinvaders.game.common.Constants.WIDTH;

public class GameOverScreen extends BasicScreen{

    private Stage stage;
    private Viewport vp;
    private Skin skin;
    private Image gameover;
    private TextButton retry, menu;
    private float dx;
    private Vector2 size, retryButtonPos;

    public GameOverScreen(final MainGame game) {
        super(game);
        this.dx = 320;
        this.size = new Vector2(200,80);

        //this.retryButtonPos = new Vector2(WIDTH / 2 - retry.getWidth(),60);
        //this.retryButtonPos = new Vector2(60,60);

        init();
    }

    public void init(){
        vp = new FitViewport(WIDTH, HEIGHT);
        stage = new Stage(vp);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Texture texture = game.getManager().get("gameover.png");
        gameover = new Image(texture);
        retry = new TextButton("Retry", skin);

        this.retryButtonPos = new Vector2((WIDTH / 2) - (size.x / 2),60);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.resetGameScreen();
                game.setScreen(game.getGameScreen());

            }
        });

        //gameover.setPosition(gameover.getWidth() / 6 , gameover.getHeight() * 4);
        gameover.setPosition(dx - gameover.getWidth() / 2 , dx - gameover.getHeight() );

        retry.setSize(size.x, size.y);

        retry.setPosition(retryButtonPos.x, retryButtonPos.y);

        stage.addActor(retry);
        stage.addActor(gameover);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        //Gdx.input.setInputProcessor(stage); //Procesa eventos de todas las entradas
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
