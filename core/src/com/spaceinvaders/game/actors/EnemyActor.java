package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyActor extends Actor {

    private Texture enemyTexture;
    private Vector2 position;

    private float dx;
    private float timeL;
    private float movementSpeed; // velocidad
    private float sWidth, sHeight;
    private boolean isAlive;

    private float vHeight;

    private long startTime;
    private float timeS;

    public EnemyActor(Texture spTexture, Vector2 position, Vector2 size, float dx, float timeL){
        this.enemyTexture = spTexture;
        this.position = position;

        this.sWidth = size.x;
        this.sHeight = size.y;
        this.dx = dx;

        this.timeL = timeL;
        this.movementSpeed = dx / timeL;
        System.out.println("dx: "+ dx +", time: "+ timeL +", vel: " + movementSpeed);
        this.isAlive = true;

        this.vHeight = Gdx.graphics.getHeight();

        startTime = System.currentTimeMillis();
        setSize(sWidth,sHeight);
    }

    public EnemyActor(Texture texture){
        this.enemyTexture = texture;
        this.position = Vector2.Zero;
        this.sWidth = 100f;
        this.sHeight = 120f;
        this.dx = 3;
        this.timeL = 500;
        this.movementSpeed = dx / timeL;
        this.isAlive = true;

        this.vHeight = Gdx.graphics.getHeight();
        startTime = System.currentTimeMillis();

        setSize(sWidth,sHeight);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x, position.y);
        batch.draw(enemyTexture, position.x, position.y, sWidth, sHeight);
    }

    @Override
    public void act(float delta) {

        timeS = actTime();
        MoveDown(timeS);
    }


    public void MoveDown(float timeS){
        if (isAlive){
            MoverY(false);
            //RebotarY();

            if (timeS >= timeL){
                isAlive = false;
            }
        }
    }


    public void MoverY(boolean sw){
        if (!sw){
            if (position.y > 0) position.y -= movementSpeed;
        }else{
            if (position.y < Gdx.graphics.getHeight()) position.y += movementSpeed;
        }
    }

    public void RebotarY(){

        position.y += dx;

        if (position.y >= (Gdx.graphics.getHeight() - (enemyTexture.getHeight() + sHeight)) ||
            position.y <= 0){
            dx = dx * -1;
        }
    }


    private float actTime(){
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public void detach(){
        enemyTexture.dispose();
    }
}
