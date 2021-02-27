package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.spaceinvaders.game.models.Enemy;
import com.spaceinvaders.game.models.Object;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnemyController {

    public LinkedHashMap<String, Object> objects;
    private Texture texture;
    private float height;
    private Vector2 size;
    private SpriteBatch batch;

    private long startTime;
    private float timeS;

    public EnemyController(Texture texture, SpriteBatch batch){

        objects = new LinkedHashMap<String, Object>();

        this.texture = texture;
        this.size = new Vector2(50,50);
        this.batch = batch;

        height = Gdx.graphics.getHeight() - size.y;

        startTime = System.currentTimeMillis();

        cargarObjetos();
    }

    public EnemyController(Texture texture, Vector2 size, SpriteBatch batch){

        objects = new LinkedHashMap<String, Object>();

        this.texture = texture;
        this.size = size;
        this.batch = batch;
        height = Gdx.graphics.getHeight() - size.y;

        startTime = System.currentTimeMillis();

        cargarObjetos();
    }

    private void cargarObjetos() {

        Enemy e1 = new Enemy(texture, new Vector2(10 ,height), size, 2,3,batch);
        Enemy e2 = new Enemy(texture, new Vector2(100,height), size, 6,1,batch);
        Enemy e3 = new Enemy(texture, new Vector2(200,height), size, 4,2,batch);
        Enemy e4 = new Enemy(texture, new Vector2(300,height), size, 5,3,batch);
        Enemy e5 = new Enemy(texture, new Vector2(400,height), size, 3,2,batch);
        Enemy e6 = new Enemy(texture, new Vector2(500,height), size, 7,4,batch);
        Enemy e7 = new Enemy(texture, new Vector2(580,height), size, 1,1,batch);

        objects.put("e1",e1);
        objects.put("e2",e2);
        objects.put("e3",e3);
        objects.put("e4",e4);
        objects.put("e5",e5);
        objects.put("e6",e6);
        objects.put("e7",e7);

    }

    public void draw(){
        for (Map.Entry<String, Object> entry : objects.entrySet()) {
            Object obj = entry.getValue();
            obj.draw();
        }
    }

    public void executeMovement(){
        for (Map.Entry<String, Object> entry : objects.entrySet()) {

            Object obj = entry.getValue();

            timeS = actTime();

            if (obj.isAlive()){
                obj.MoverY(false);
                if (timeS >= obj.getTimeL()){
                    obj.setIsAlive(false);
                }
            }
        }
    }

    private float actTime() {
        float timee = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Time = " + timee);
        return timee;
    }

    public void dispose(){
        for (Map.Entry<String, Object> entry : objects.entrySet()) {
            Object obj = entry.getValue();
            obj.dispose();
        }
    }

}
