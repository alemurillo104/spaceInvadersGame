package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.spaceinvaders.game.actors.BalaActor;
import com.spaceinvaders.game.actors.EnemyActor;
import static com.spaceinvaders.game.common.Constants.HEIGHT;

import java.util.LinkedList;

public class EnemyController {

    public LinkedList<EnemyActor> objects;
    public LinkedList<BalaActor> balasEnemy;
    private Texture texture;
    private float height;
    private Vector2 size;
    private Vector2 posNave;
    private int cimg;

    private float dy, timeL;

    public EnemyController(Texture texture, Vector2 playerPosition){

        objects = new LinkedList<>();

        this.texture = texture;
        this.size = new Vector2(70,50);
        this.cimg = 7;

        height = HEIGHT - size.y;
        posNave = playerPosition;

        this.dy = 5;
        this.timeL = 2;

        cargarObjetos();
    }

    public EnemyController(Texture texture, Vector2 playerPosition, int nivel){

        objects = new LinkedList<>();

        this.texture = texture;
        this.size = new Vector2(70,50);
        this.cimg = 7;

        height = HEIGHT - size.y;
        posNave = playerPosition;

//        this.dy = 2;
//        this.timeL = 8; // y luego 5 o 6
//        cargarObjetosNivel();

        if (nivel == 2){
            this.timeL = 6;
        } else {
            this.timeL = 8;
        }

        cargarObjetosNivel();

//        if (nivel == 2){
//            this.dy = 2;
//            this.timeL = 6;
//            cargarObjetosNivel();
//        } else {
//            this.dy = 5;
//            this.timeL = 2;
//            cargarObjetos();
//        }
    }

    private void cargarObjetos() {

        EnemyActor e0 = new EnemyActor(texture, new Vector2(10 ,height), size, 2,6, posNave, cimg);
        EnemyActor e1 = new EnemyActor(texture, new Vector2(100,height), size, 6,4, posNave, cimg);
        EnemyActor e2 = new EnemyActor(texture, new Vector2(200,height), size, 4,5, posNave, cimg);
        EnemyActor e3 = new EnemyActor(texture, new Vector2(300,height), size, 5,6, posNave, cimg);
        EnemyActor e4 = new EnemyActor(texture, new Vector2(400,height), size, 3,5, posNave, cimg);
        EnemyActor e5 = new EnemyActor(texture, new Vector2(500,height), size, 7,7, posNave, cimg);
        EnemyActor e6 = new EnemyActor(texture, new Vector2(580,height), size, 1,4, posNave, cimg);

        EnemyActor e7  = new EnemyActor(texture, new Vector2(10 ,height - size.y - 10), size, 2,6, posNave, cimg);
        EnemyActor e8  = new EnemyActor(texture, new Vector2(100,height - size.y - 10), size, 6,4, posNave, cimg);
        EnemyActor e9  = new EnemyActor(texture, new Vector2(200,height - size.y - 10), size, 4,5, posNave, cimg);
        EnemyActor e10 = new EnemyActor(texture, new Vector2(300,height - size.y - 10), size, 5,6, posNave, cimg);
        EnemyActor e11 = new EnemyActor(texture, new Vector2(400,height - size.y - 10), size, 3,5, posNave, cimg);
        EnemyActor e12 = new EnemyActor(texture, new Vector2(500,height - size.y - 10), size, 7,7, posNave, cimg);
        EnemyActor e13 = new EnemyActor(texture, new Vector2(580,height - size.y - 10), size, 1,4, posNave, cimg);

        e0.setMov(3);
        e1.setMov(2);
        e2.setMov(4);
        e3.setMov(3);
        e4.setMov(1);
        e5.setMov(2);
        e6.setMov(1);

        e7.setMov(1);
        e8.setMov(1);
        e9.setMov(1);
        e10.setMov(1);
        e11.setMov(1);
        e12.setMov(1);
        e13.setMov(2);

        objects.add(e0);
        objects.add(e1);
        objects.add(e2);
        objects.add(e3);
        objects.add(e4);
        objects.add(e5);
        objects.add(e6);
        objects.add(e7);
        objects.add(e8);
        objects.add(e9);
        objects.add(e10);
        objects.add(e11);
        objects.add(e12);
        objects.add(e13);

    }


    private void cargarObjetosNivel() {

        //float dy = 2, timeL = 6;
        EnemyActor e0 = new EnemyActor(texture, new Vector2(10 ,height), size, 2, timeL, posNave, cimg);
        EnemyActor e1 = new EnemyActor(texture, new Vector2(100,height), size, 6, timeL, posNave, cimg);
        EnemyActor e2 = new EnemyActor(texture, new Vector2(200,height), size, 4, timeL, posNave, cimg);
        EnemyActor e3 = new EnemyActor(texture, new Vector2(300,height), size, 5, timeL, posNave, cimg);
        EnemyActor e4 = new EnemyActor(texture, new Vector2(400,height), size, 3, timeL, posNave, cimg);
        EnemyActor e5 = new EnemyActor(texture, new Vector2(500,height), size, 7, timeL, posNave, cimg);
        EnemyActor e6 = new EnemyActor(texture, new Vector2(580,height), size, 1, timeL, posNave, cimg);

        EnemyActor e7  = new EnemyActor(texture, new Vector2(10 ,height - size.y - 10), size, 2, timeL, posNave, cimg);
        EnemyActor e8  = new EnemyActor(texture, new Vector2(100,height - size.y - 10), size, 6, timeL, posNave, cimg);
        EnemyActor e9  = new EnemyActor(texture, new Vector2(200,height - size.y - 10), size, 4, timeL, posNave, cimg);
        EnemyActor e10 = new EnemyActor(texture, new Vector2(300,height - size.y - 10), size, 5, timeL, posNave, cimg);
        EnemyActor e11 = new EnemyActor(texture, new Vector2(400,height - size.y - 10), size, 3, timeL, posNave, cimg);
        EnemyActor e12 = new EnemyActor(texture, new Vector2(500,height - size.y - 10), size, 7, timeL, posNave, cimg);
        EnemyActor e13 = new EnemyActor(texture, new Vector2(580,height - size.y - 10), size, 1, timeL, posNave, cimg);

        e0.setMov(3);
        e1.setMov(2);
        e2.setMov(4);
        e3.setMov(3);
        e4.setMov(1);
        e5.setMov(2);
        e6.setMov(1);

        e7.setMov(1);
        e8.setMov(1);
        e9.setMov(1);
        e10.setMov(1);
        e11.setMov(1);
        e12.setMov(1);
        e13.setMov(2);

        objects.add(e0);
        objects.add(e1);
        objects.add(e2);
        objects.add(e3);
        objects.add(e4);
        objects.add(e5);
        objects.add(e6);
        objects.add(e7);
        objects.add(e8);
        objects.add(e9);
        objects.add(e10);
        objects.add(e11);
        objects.add(e12);
        objects.add(e13);

    }
}

//
//    private void cargarObjetosNivel() {
//
//        //float dy = 2, timeL = 6;
//        EnemyActor e0 = new EnemyActor(texture, new Vector2(10 ,height), size, dy, timeL, posNave, cimg);
//        EnemyActor e1 = new EnemyActor(texture, new Vector2(100,height), size, dy, timeL, posNave, cimg);
//        EnemyActor e2 = new EnemyActor(texture, new Vector2(200,height), size, dy, timeL, posNave, cimg);
//        EnemyActor e3 = new EnemyActor(texture, new Vector2(300,height), size, dy, timeL, posNave, cimg);
//        EnemyActor e4 = new EnemyActor(texture, new Vector2(400,height), size, dy, timeL, posNave, cimg);
//        EnemyActor e5 = new EnemyActor(texture, new Vector2(500,height), size, dy, timeL, posNave, cimg);
//        EnemyActor e6 = new EnemyActor(texture, new Vector2(580,height), size, dy, timeL, posNave, cimg);
//
//        EnemyActor e7  = new EnemyActor(texture, new Vector2(10 ,height - size.y - 10), size, dy, timeL, posNave, cimg);
//        EnemyActor e8  = new EnemyActor(texture, new Vector2(100,height - size.y - 10), size, dy, timeL, posNave, cimg);
//        EnemyActor e9  = new EnemyActor(texture, new Vector2(200,height - size.y - 10), size, dy, timeL, posNave, cimg);
//        EnemyActor e10 = new EnemyActor(texture, new Vector2(300,height - size.y - 10), size, dy, timeL, posNave, cimg);
//        EnemyActor e11 = new EnemyActor(texture, new Vector2(400,height - size.y - 10), size, dy, timeL, posNave, cimg);
//        EnemyActor e12 = new EnemyActor(texture, new Vector2(500,height - size.y - 10), size, dy, timeL, posNave, cimg);
//        EnemyActor e13 = new EnemyActor(texture, new Vector2(580,height - size.y - 10), size, dy, timeL, posNave, cimg);
//
//        e0.setMov(3);
//        e1.setMov(2);
//        e2.setMov(4);
//        e3.setMov(3);
//        e4.setMov(1);
//        e5.setMov(2);
//        e6.setMov(1);
//
//        e7.setMov(1);
//        e8.setMov(1);
//        e9.setMov(1);
//        e10.setMov(1);
//        e11.setMov(1);
//        e12.setMov(1);
//        e13.setMov(2);
//
//        objects.add(e0);
//        objects.add(e1);
//        objects.add(e2);
//        objects.add(e3);
//        objects.add(e4);
//        objects.add(e5);
//        objects.add(e6);
//        objects.add(e7);
//        objects.add(e8);
//        objects.add(e9);
//        objects.add(e10);
//        objects.add(e11);
//        objects.add(e12);
//        objects.add(e13);
//
//    }
