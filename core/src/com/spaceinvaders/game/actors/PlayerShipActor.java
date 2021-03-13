package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.spaceinvaders.game.common.Constants.WIDTH;

public class PlayerShipActor extends Actor {

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

    private int lives;

    public PlayerShipActor(Texture texture, Vector2 position) {
        this.texture = texture;
        this.position = position;
        this.sWidth = 50;
        this.sHeight = 50;
        this.dx = 5; this.dy = 5;
        this.timeL = 3;
        this.movementSpeed =  new Vector2( dx / timeL, dy/ timeL);
        this.isAlive = true;

        this.lives = 3;

        setSize(sWidth, sHeight);
    }

    public PlayerShipActor(Texture texture, Vector2 position, float sWidth, float sHeight, float dx, float timeL) {
        this.texture = texture;
        this.position = position;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.dx = dx; this.dy = 5;
        this.timeL = timeL;
        this.movementSpeed = new Vector2(dx / timeL, dy / timeL);
        this.isAlive = true;

        this.lives = 3;

        setSize(sWidth,sHeight);
    }

    public Vector2 getPosition() { return position; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x , position.y);
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

    }

    public void changeDir(){

        Vector2 pos = position;
        float posX = pos.x;

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

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void decreaseLives() {
        if (isAlive) {
            if (this.lives > 0) {
                this.lives--;
            } else {
                isAlive = false;
            }
        }
    }

    public int decreaseLives2() {
        if (isAlive) {
            if (lives > 0) {
                lives = lives - 1;
            }
//            } else {
//                isAlive = false;
//            }
        }
        return lives;
    }

    public void detach(){
        texture.dispose();
    }
}
