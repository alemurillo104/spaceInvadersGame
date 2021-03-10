package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class BalaActor extends Actor {

    private Texture texture;

    private Vector2 position;
    private float sWidth, sHeight;
    private float dx, dy;
    private float timeL;
    private Vector2 movSpeed;
    private boolean isAlive;
    private float vWidth;

    private float vHeight;

    private long startTime;
    private float timeS;


    public BalaActor(Texture texture, Vector2 position, Vector2 size,float dy, float timeL) {
        this.texture = texture;
        this.position = position;
        this.sWidth = size.x;
        this.sHeight = size.y;
        this.dx = 6; this.dy = dy; //10
        this.timeL = timeL;
        this.isAlive = true;
        this.movSpeed = new Vector2(this.dx / timeL, dy / timeL); // 2*timeL

        this.vWidth = Gdx.graphics.getWidth();
        this.vHeight = Gdx.graphics.getHeight();

        startTime = System.currentTimeMillis();
        setSize(sWidth , sHeight);
    }

    public BalaActor(Texture texture, Vector2 position, float dy, float timeL) {

        this.texture = texture;
        this.position = position;
        this.sWidth = 7; this.sHeight = 12;
        this.dx = 6; this.dy = dy; //10
        this.timeL = timeL;
        this.isAlive = true;
        this.movSpeed = new Vector2(this.dx / timeL, dy / timeL); // 2*timeL

        this.vWidth = Gdx.graphics.getWidth();
        this.vHeight = Gdx.graphics.getHeight();

        startTime = System.currentTimeMillis();
        setSize(sWidth, sHeight);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x, position.y);
        if (isAlive){
            batch.draw(texture, position.x, position.y, sWidth, sHeight);
        }
    }

    @Override
    public void act(float delta) {

        timeS = actTime();
        executeMovement(timeS);
    }

    public void executeMovement(float time){
        if (isAlive){
            MoverY(true);

            if (position.y > vHeight){
                isAlive = false;
            }

            if (time >= timeL)  //se cumplio
                timeL = timeL + time;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private void MoverY(boolean sw) {
        if (!sw) {
            if (position.y > 0) position.y -= movSpeed.y; // dy
        } else {
            if (position.y < vHeight) position.y += movSpeed.y; // dy
        }
    }

    private float actTime(){
        return (System.currentTimeMillis() - startTime) / 1000;
    }


    public void detach(){
        texture.dispose();
    }
}
