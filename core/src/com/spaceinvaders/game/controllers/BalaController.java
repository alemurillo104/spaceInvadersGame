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

    private Texture texture;
    private Vector2 playerPos;
    private float dy, timeL, sWidth, sHeight;

    //Lista de enemigos
    private LinkedList<EnemyActor> enemies;

    public BalaController(Texture texture, Vector2 playerPos, float sWidth, float sHeight, LinkedList<EnemyActor> enemies){
        this.playerPos = playerPos;
        this.texture = texture;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.dy = 5;
        this.timeL = 2;
        this.enemies = enemies;
    }

    public void dispararBala(Stage stage){

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)){
            float middleX = playerPos.x + (sWidth / 2 );
            float middleY = playerPos.y + sHeight;
            stage.addActor( new BalaActor(texture, new Vector2(middleX, middleY), dy, timeL) );
            System.out.println("Nueva bala dentro");
        }
    }



}
