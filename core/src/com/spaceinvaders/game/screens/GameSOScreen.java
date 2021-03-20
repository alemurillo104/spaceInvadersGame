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

public class GameSOScreen extends BasicScreen{

    private Stage stage;
    private Viewport vp;
    private Texture texture;
    private Skin skin;
    private Image gameover;
    private TextButton retry;
    private float dx;
    private Vector2 size, retryButtonPos;
    private boolean inicio;
    private String texto;
    private Vector2 positionTexture, sizeTitle;

    public GameSOScreen(final MainGame game) {
        super(game);
        this.inicio = true;
        this.dx = 320;
        this.size = new Vector2(200,80);
        this.sizeTitle = new Vector2(450,225);

        vp = new FitViewport(WIDTH, HEIGHT);
        stage = new Stage(vp);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        init();
    }

    public void init(){
        if (inicio){
            texture = game.getManager().get("title1.png");
            texto = "Jugar";
        }else{
            texture = game.getManager().get("gameover.png");
            texto = "Reintentar";
        }

        gameover = new Image(texture);
        retry = new TextButton(texto, skin);

        this.retryButtonPos = new Vector2((WIDTH / 2) - (size.x / 2),60);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.resetGameScreen();
                game.setScreen(game.getGameScreen());

            }
        });

        if (inicio){
            gameover.setSize(sizeTitle.x, sizeTitle.y);
            positionTexture = new Vector2(dx - gameover.getWidth() / 2 , dx - (gameover.getHeight() / 2) - 30);
        } else {
            positionTexture = new Vector2(dx - gameover.getWidth() / 2 , dx - gameover.getHeight() );
        }

        gameover.setPosition(positionTexture.x, positionTexture.y);

        retry.setSize(size.x, size.y);

        retry.setPosition(retryButtonPos.x, retryButtonPos.y);

        stage.addActor(retry);
        stage.addActor(gameover);
    }


    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    @Override
    public void show() {
        init();
        Gdx.input.setInputProcessor(stage); //Procesa eventos de todas las entradas
    }

    @Override
    public void hide() {
        stage.clear();
        Gdx.input.setInputProcessor(null); //ya no trata las entradas cuando cambie la pantalla
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,0.3f,0.8f,1);
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
