package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.spaceinvaders.game.actors.EnemyActor;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

import java.util.LinkedList;
import java.util.Random;

public class EnemyController {

    public LinkedList<EnemyActor> objects;
    private Texture texture;
    private float height;
    private Vector2 size;
    private Vector2 posNave;
    private int cimg;

    public EnemyController(Texture texture){

        objects = new LinkedList<>();

        this.texture = texture;
        this.size = new Vector2(70,50);
        this.cimg = 7;

        height = HEIGHT - size.y;

        cargarObjetos();
    }

    public EnemyController(Texture texture, Vector2 playerPosition){

        objects = new LinkedList<>();

        this.texture = texture;
        this.size = new Vector2(70,50);
        this.cimg = 7;

        height = HEIGHT - size.y;
        posNave = playerPosition;

        cargarObjetos();
    }

    private void cargarObjetos() {

        //  +3
        EnemyActor e0 = new EnemyActor(texture, new Vector2(10 ,height), size, 2,6, posNave, cimg);
        EnemyActor e1 = new EnemyActor(texture, new Vector2(100,height), size, 6,4, posNave, cimg); //1 6
        EnemyActor e2 = new EnemyActor(texture, new Vector2(200,height), size, 4,5, posNave, cimg);
        EnemyActor e3 = new EnemyActor(texture, new Vector2(300,height), size, 5,6, posNave, cimg);//5   2
        EnemyActor e4 = new EnemyActor(texture, new Vector2(400,height), size, 3,5, posNave, cimg);
        EnemyActor e5 = new EnemyActor(texture, new Vector2(500,height), size, 7,7, posNave, cimg);
        EnemyActor e6 = new EnemyActor(texture, new Vector2(580,height), size, 1,4, posNave, cimg);

        e0.setMov(3); //2 3
        e1.setMov(2); // 2 4
        e2.setMov(4); //1 3
        e3.setMov(3); //2 4
        e4.setMov(1);
        e5.setMov(2);
        e6.setMov(1); //1

        objects.add(e0);
        objects.add(e1);
        objects.add(e2);
        objects.add(e3);
        objects.add(e4);
        objects.add(e5);
        objects.add(e6);

    }

    public void getDelEnemy(){

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){

            if (objects.size() > 0){
                int i = new Random().nextInt(objects.size());
                EnemyActor em = objects.get(i);

                if (em != null){
                    em.setStart(true);
                    objects.remove(i);
                }
            }
        }

    }
}