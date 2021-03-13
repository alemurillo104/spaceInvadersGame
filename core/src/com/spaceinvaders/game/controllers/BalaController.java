package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.spaceinvaders.game.actors.BalaActor;
import com.spaceinvaders.game.actors.EnemyActor;
import com.spaceinvaders.game.actors.ScoreLActor;

import java.util.LinkedList;
import java.util.Random;

public class BalaController {

    private LinkedList<BalaActor> balas;
    private LinkedList<BalaActor> balasEnemy;
    private Texture texture, etexture;
    private Vector2 playerPos;
    private float dy, timeL, sWidth, sHeight;

    //Lista de enemigos
    private LinkedList<EnemyActor> enemies;

    private InputController inputController;

    private boolean iniciar = true, iniciarSE = true;
    private long startTimeA, startTimeE;
    private float timeSA, timeLimite, timeSE, timeLimiteSE;

    private ScoreLActor scoreLActor;

    public BalaController(Texture texture, Texture etexture, Vector2 playerPos, float sWidth, float sHeight, LinkedList<EnemyActor> enemies, InputController inputCont, int score, ScoreLActor scoreLActor){

        this.balas = new LinkedList<>();
        this.playerPos = playerPos;
        this.texture = texture;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.dy = 5;
        this.timeL = 2;
        this.enemies = enemies;
        this.inputController = inputCont;
        this.timeLimite = 1;

        this.scoreLActor = scoreLActor;

        this.etexture = etexture;
        this.balasEnemy = new LinkedList<>();
    }

    public void shoot(Stage stage){

        if (inputController.teclas.get("SPACE_SHOOT")){

            startShootingTime(stage);

        }else{
            System.out.println("la solte");
            iniciar = true;
            timeLimite = 1;
        }
    }


    //-----------------Control de tiempo entre balas Player-----------------

    private void startShootingTime(Stage stage) {
        iniciarTiempo(stage);
        timeSA = (System.currentTimeMillis() - startTimeA) /1000;

        if (timeSA >= timeLimite) {

            System.out.println("DO SOME STUFF: "+timeLimite);
            shootBala(stage);
            timeLimite = timeLimite + 1;
        }
    }

    private void iniciarTiempo(Stage stage) {
        if (iniciar){
            startTimeA = System.currentTimeMillis();
            shootBala(stage);
            iniciar = false;
        }
    }

    //------------------------------
    public void shootBala(Stage stage){

        Vector2 middle = new Vector2( playerPos.x + (sWidth /2), playerPos.y + sHeight);
        BalaActor balaActor = new BalaActor(texture, middle, dy, timeL, true);
        balas.add(balaActor);
        stage.addActor(balaActor);

    }

    public boolean comprobarColision(){
        int i = 0;
        while(i < balas.size()){
            BalaActor bala = balas.get(i);
            int j = 0;
            while (j < enemies.size()){
                EnemyActor e = enemies.get(j);
                if ( (bala.getX() > e.getX() && bala.getX() <= (e.getX() + e.getWidth())  ) &&
                     (bala.getY() > e.getY() && bala.getY() <= (e.getY() + e.getHeight()) )   ){

                    System.out.println("hubo colision");

                    scoreLActor.setDetectedCollision(true);

                    e.setStart(true);
                    enemies.remove(j);

                    bala.setAlive(false);
                    balas.remove(i);
                    return true;
                }
                j++;
            }
            i++;
        }
        return false;
    }



    //-------------Control de tiempo entre balas Enemy----------------

    public void shootBalaEnemyT(Stage stage) {
        iniciarTiempoSE(stage);
        timeSE = (System.currentTimeMillis() - startTimeE) /1000;

        if (timeSE >= timeLimiteSE) {

            System.out.println("DO SOME STUFF SE: "+ timeLimiteSE);
            shootBalaEnemy(stage);
            timeLimiteSE = timeLimiteSE + 1;
        }
    }

    private void iniciarTiempoSE(Stage stage) {
        if (iniciarSE){
            startTimeE = System.currentTimeMillis();
            shootBalaEnemy(stage);
            iniciarSE = false;
        }
    }

    private void shootBalaEnemy(Stage stage){

        if (enemies.size() > 0){
            int i = new Random().nextInt(enemies.size());
            EnemyActor em = enemies.get(i);

            if (em != null){

                //disparo la bala
                Vector2 middle = new Vector2( em.getX() + (em.getWidth() /2), em.getY() - em.getHeight());
                BalaActor balaActor = new BalaActor(etexture, middle, dy, timeL, false);
                balasEnemy.add(balaActor);
                stage.addActor(balaActor);

                System.out.println("enemy shoot");
            }
        }
    }

    //Collision para el player desde el enemigo

    public boolean comprobarColisionToPlayer(){
        int i = 0;
        while(i < balasEnemy.size()) {
            BalaActor bala = balasEnemy.get(i);
            if ( (bala.getX() > playerPos.x && bala.getX() <= (playerPos.x + sWidth) ) &&
                 (bala.getY() > playerPos.y && bala.getY() <= (playerPos.y + sHeight)) ){

                System.out.println("colision del player y la bala del enemy");

                scoreLActor.setDetectedCollisionPlayer(true); //para decrementar las vidas

                bala.setAlive(false);
                balasEnemy.remove(i);
                return true;
            }
            i++;
        }

        return false;
    }
}