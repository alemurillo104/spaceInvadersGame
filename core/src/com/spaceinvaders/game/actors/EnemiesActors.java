package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class EnemiesActors {

    public LinkedList<EnemyActor> enemies;
    private Texture texture;
    private float height;
    private float size;

    public EnemiesActors(Texture texture, float size, float height ){
        this.texture = texture;
        this.size = size;
        this.height = height;

        cargarEnemies();
    }

    public EnemiesActors(Texture texture ){
//        this.batch = batch;
        enemies = new LinkedList<>();

        this.texture = texture;
        this.size = 50; // 50
//        this.size = texture.getHeight(); // 50

//        height = 280;
//        height = Gdx.graphics.getHeight() - 100;
        height = Gdx.graphics.getHeight()- 170;
        //height = Gdx.graphics.getHeight() - (texture.getHeight() * 3f);
//        height = Gdx.graphics.getHeight() - (texture.getHeight() * 2.7f);
//        height = Gdx.graphics.getHeight() - (texture.getHeight() * 2 );
        System.out.println(height);

        cargarEnemies();
    }

    public void cargarEnemies(){ //dx=10 y la bala dx=1 (?
        EnemyActor e1 = new EnemyActor(texture, new Vector2(10 ,height), size, 4);
        EnemyActor e2 = new EnemyActor(texture, new Vector2(100,height), size, 5);
        EnemyActor e3 = new EnemyActor(texture, new Vector2(200,height), size, 3);
        EnemyActor e4 = new EnemyActor(texture, new Vector2(300,height), size, 2.5f);
        EnemyActor e5 = new EnemyActor(texture, new Vector2(400,height), size, 1);
        EnemyActor e6 = new EnemyActor(texture, new Vector2(500,height), size, 2);
        EnemyActor e7 = new EnemyActor(texture, new Vector2(580,height), size, 6);

        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(e7);
    }

    public void draw(){
        for (EnemyActor enemy : enemies) {
//            enemy.draw();
        }
    }
}
