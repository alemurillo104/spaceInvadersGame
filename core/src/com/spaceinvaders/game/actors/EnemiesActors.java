package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class EnemiesActors {

    public LinkedHashMap<String, Actor> objects;
    private Texture texture;
    private float width, height;
    private Vector2 size;


    public EnemiesActors(Texture texture, Vector2 size){

        objects = new LinkedHashMap<String, Actor>();

        this.texture = texture;
        this.size = size;
        width  = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight() - 170;
        //height = Gdx.graphics.getHeight() - size.y;

        cargarObjetos();

    }

    private void cargarObjetos() {
        EnemyActor e1 = new EnemyActor(texture, new Vector2(10 ,height), size, 2,2);
        EnemyActor e2 = new EnemyActor(texture, new Vector2(100,height), size, 6,1);
        EnemyActor e3 = new EnemyActor(texture, new Vector2(200,height), size, 4,2);
        EnemyActor e4 = new EnemyActor(texture, new Vector2(300,height), size, 5,3);
        EnemyActor e5 = new EnemyActor(texture, new Vector2(400,height), size, 3,1);
        EnemyActor e6 = new EnemyActor(texture, new Vector2(500,height), size, 7,4);
        EnemyActor e7 = new EnemyActor(texture, new Vector2(580,height), size, 1,2);

        objects.put("e1",e1);
        objects.put("e2",e2);
        objects.put("e3",e3);
        objects.put("e4",e4);
        objects.put("e5",e5);
        objects.put("e6",e6);
        objects.put("e7",e7);

    }
}
