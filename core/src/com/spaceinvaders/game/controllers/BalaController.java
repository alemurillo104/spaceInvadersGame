package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.spaceinvaders.game.actors.BalaActor;
import com.spaceinvaders.game.actors.EnemyActor;

import java.util.LinkedList;

public class BalaController {

    private LinkedList<BalaActor> balas;
    private Texture texture;
    private Vector2 playerPos;
    private float dy, timeL, sWidth, sHeight;

    //Lista de enemigos
    private LinkedList<EnemyActor> enemies;

    private InputController inputController;

    private boolean start = false, iniciar = true;
    private long startTimeA;
    private float timeSA, timeLimite;


    public BalaController(Texture texture, Vector2 playerPos, float sWidth, float sHeight, LinkedList<EnemyActor> enemies, InputController inputCont){

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
    }

    public void shoot(Stage stage){

        if (inputController.teclas.get("SPACE_SHOOT")){

            startShootingTime(stage);

        }else{
            System.out.println("la solté");
            iniciar = true;
            timeLimite = 1;
        }
    }


    //Control de tiempo entre balas

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
        BalaActor balaActor = new BalaActor(texture, middle, dy, timeL);
        balas.add(balaActor);
        stage.addActor(balaActor);

    }

    public void dispararBala(Stage stage){

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)){
            Vector2 middle = new Vector2( playerPos.x + (sWidth /2), playerPos.y + sHeight);
            BalaActor balaActor = new BalaActor(texture, middle, dy, timeL);
            balas.add(balaActor);
            stage.addActor(balaActor);
        }

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

                    if (e != null){
                        e.setStart(true);
                        enemies.remove(j);
                    }
                    if (bala != null){
                        bala.setAlive(false);
                        balas.remove(i);
                    }
                    return true;
                }
                j++;
            }
            i++;
        }
        return false;
    }

}

