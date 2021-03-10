package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
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

    //para World
    private World world;

    public BalaController(Texture texture, Vector2 playerPos, float sWidth, float sHeight, LinkedList<EnemyActor> enemies, World world, InputController inputController){

        this.balas = new LinkedList<>();
        this.playerPos = playerPos;
        this.texture = texture;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.dy = 5;
        this.timeL = 2;
        this.enemies = enemies;
        this.world = world;
    }

    public void dispararBala(Stage stage){

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)){
            Vector2 middle = new Vector2( playerPos.x + (sWidth /2), playerPos.y + sHeight);
            BalaActor balaActor = new BalaActor(texture, middle, dy, timeL, world);
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
                        e.setStart(true); //inicio la explosion y lo mato
                        enemies.remove(j); //remuevo al enemy de la lista
                    }
                    if (bala != null){
                        bala.setAlive(false); //lo mato igual
                        balas.remove(i); //tamb lo saco de la lista
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

