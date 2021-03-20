package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.spaceinvaders.game.MainGame;
import com.spaceinvaders.game.actors.EnemyActor;
import com.spaceinvaders.game.actors.ScoreLActor;
import com.spaceinvaders.game.actors.PlayerShipActor;
import com.spaceinvaders.game.controllers.BalaController;
import com.spaceinvaders.game.controllers.EnemyController;
import com.spaceinvaders.game.controllers.InputController;

import static com.spaceinvaders.game.common.Constants.WIDTH;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class GameScreen extends BasicScreen {

    public Stage stage;
    public Texture enemyTexture, playerShipTexture, balaPlayerTexture, balaEnemyTexture;
    public EnemyController enemies;
    public PlayerShipActor playerShipActor;

    public BalaController balaController;
    public InputController inputController;

    public int score = 0;
    public ScoreLActor scoreLActor;

    public int nivel;
    public boolean subiNivel;

    public GameScreen(MainGame game) {
        super(game);

        nivel = 1;
        subiNivel = false;

        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        //stage.setDebugAll(true);

        inputController = new InputController();
        Gdx.input.setInputProcessor(inputController);

        playerShipTexture = game.getManager().get("nave.png");
        balaPlayerTexture = game.getManager().get("balaPlayer.png");
        balaEnemyTexture = game.getManager().get("balaEnemy.png");

        cargarEntitiesInit();
    }

    private void cargarEntitiesInit(){

        if(nivel == 1){
            enemyTexture = game.getManager().get("sprite3.png");
        }else{
            enemyTexture = game.getManager().get("spriteN2.png");
        }

        playerShipActor = new PlayerShipActor(playerShipTexture, new Vector2(WIDTH /2, 0));

        enemies = new EnemyController(enemyTexture, playerShipActor.getPosition(), nivel);

        scoreLActor = new ScoreLActor(playerShipActor, score, nivel);

        balaController = new BalaController(balaPlayerTexture, balaEnemyTexture, playerShipActor.getPosition(), playerShipActor.getWidth(), playerShipActor.getHeight(), enemies.objects, inputController, score, scoreLActor);

        stage.addActor(scoreLActor);

        stage.addActor(playerShipActor);
        for (EnemyActor enemy: enemies.objects)
            stage.addActor(enemy);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputController); //creo
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f,0.1f,1f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameOverControl();

        subioNivel();

        balaController.shoot(stage);
        balaController.shootBalaEnemyT(stage);
        balaController.comprobarColision();
        balaController.comprobarColisionToPlayer();

        stage.act();
        stage.draw();
    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);//CREO
        stage.clear();

        for (EnemyActor enemy: enemies.objects)
            enemy.remove();

        playerShipActor.remove();
        scoreLActor.remove();
    }

    public void gameOverControl(){
        if (playerShipActor.getLives() == 0){
            System.out.println("==========GAME OVER===========");

            stage.addAction(Actions.sequence(
                    Actions.delay(0.5f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.changeTextureScreen(false);
                            game.setScreen(game.gameSOScreen);
                        }
                    })
            ));
        }
    }

    public void subioNivel(){
        //sube de nivel
        if (enemies.objects.size() == 0 || enemies.allDied()){
            if (!subiNivel){
                subiNivel = true;
                nivel = nivel + 1;
                //reinicio el juego con mas velocidad

                //limpio de pantalla
                stage.clear();
                playerShipActor.remove();

                cargarEntitiesInit();

            }else{
                if (nivel == 2){ //si sera nivel 2
                    System.out.println("Juego terminado! Felicidades es el ganador! ");
                    stage.addAction(Actions.sequence(
                            Actions.delay(0.5f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    game.changeTextureScreen(true);
                                    game.setScreen(game.gameSOScreen);
                                }
                            })
                    ));
                }
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        enemyTexture.dispose();
        playerShipTexture.dispose();
        balaPlayerTexture.dispose();
        balaEnemyTexture.dispose();
    }
}
