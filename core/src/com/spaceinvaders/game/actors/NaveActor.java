package com.spaceinvaders.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.spaceinvaders.game.common.Constants.WIDTH;

public class NaveActor extends Actor {

    public Texture texture;
    public Vector2 position;
    public float sWidth, sHeight;
    public float dx;
    public float timeL;
    public float movementSpeed;
    public boolean isAlive;

    private float vHeight;
    private long startTime;
    private float timeS;

    private boolean izquierda = false, derecha = true;
    private float valor = 3f;


    public NaveActor(Texture texture, Vector2 position) {
        this.texture = texture;
        this.position = position;
        this.sWidth = 50;
        this.sHeight = 50;
        this.dx = 5;
        this.timeL = 3;
        this.movementSpeed = dx / timeL;
        this.isAlive = true;

        setSize(sWidth,sHeight);
    }

    public NaveActor(Texture texture, Vector2 position, float sWidth, float sHeight, float dx, float timeL) {
        this.texture = texture;
        this.position = position;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.dx = dx;
        this.timeL = timeL;
        this.movementSpeed = dx / timeL;
        this.isAlive = true;

        setSize(sWidth,sHeight);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x,position.y);
        batch.draw(texture,position.x,position.y, sWidth, sHeight);
    }

    @Override
    public void act(float delta) {

        changeDir();

        if (derecha) MoverX(valor);
        if (izquierda) MoverX(-valor);

    }

    public void changeDir(){

        Vector2 pos = position;
        float posX = pos.x;

//        System.out.println(posX);

        if (posX > (WIDTH - sWidth)){
            derecha = false;
            izquierda = true;
        }
        if ( posX < (WIDTH - sWidth) && posX < 0){
            izquierda = false;
            derecha = true;
        }
    }

    public void MoverX(float val){
        position.x += val;
    }


    public void detach(){
        texture.dispose();
    }
}
