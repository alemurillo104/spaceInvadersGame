package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.spaceinvaders.game.common.Constants.WIDTH;

public class NaveActor extends Actor {

    private Texture texture;
    private Vector2 position;
    private float sWidth, sHeight;
    private float dx, dy;
    private float timeL;
    private Vector2 movementSpeed;

    private boolean isAlive;

    private long startTime;
    private float timeS;

    private boolean izquierda = false, derecha = true;
    private float valor = 3f;


    public NaveActor(Texture texture, Vector2 position) {
        this.texture = texture;
        this.position = position;
        this.sWidth = 50;
        this.sHeight = 50;
        this.dx = 5; this.dy = 5;
        this.timeL = 3;
        this.movementSpeed =  new Vector2( dx / timeL, dy/ timeL);
        this.isAlive = true;

        setSize(sWidth,sHeight);
    }

    public Vector2 getPosition() { return position; }

    public NaveActor(Texture texture, Vector2 position, float sWidth, float sHeight, float dx, float timeL) {
        this.texture = texture;
        this.position = position;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.dx = dx; this.dy = 5;
        this.timeL = timeL;
        this.movementSpeed = new Vector2(dx / timeL, dy / timeL);
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

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            MoverX(valor);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            MoverX(-valor);
        }

        //changeDir();
        //if (derecha) MoverX(valor);
        //if (izquierda) MoverX(-valor);

    }

    public void changeDir(){

        Vector2 pos = position;
        float posX = pos.x;

        //System.out.println(posX);

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
