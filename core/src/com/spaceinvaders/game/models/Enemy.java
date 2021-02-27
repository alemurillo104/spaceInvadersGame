package com.spaceinvaders.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Object {

    private float vHeight;

    public Enemy(Texture texture, Vector2 position, Vector2 size, float dx, float timeL, SpriteBatch batch){
        this.batch = batch;
        this.texture = texture;
        this.position = position;
        this.sWidth = size.x;
        this.sHeight = size.y;
        this.dx = dx;
        this.timeL = timeL;
        this.movementSpeed = dx / timeL;
        System.out.println("dx: "+ dx +", time: "+ timeL +", vel: " + movementSpeed);
        this.isAlive = true;

        this.vHeight = Gdx.graphics.getHeight();
    }

    public Enemy(Texture texture) {
        this.texture = texture;
        this.position = Vector2.Zero;
        this.sWidth = 100f;
        this.sHeight = 120f;
        this.dx = 3;
        this.timeL = 500;
        this.movementSpeed = dx / timeL;
        this.isAlive = true;

        this.vHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void draw() {
        batch.draw(texture, position.x, position.y, sWidth, sHeight);
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void setIsAlive(boolean sw) {
        isAlive = sw;
    }

    @Override
    public float getTimeL() { return timeL; }

    @Override
    public void MoverY(boolean sw) {
        if (!sw){
            if (position.y > 0) position.y -= movementSpeed; // dx
        }else{
            if (position.y < vHeight) position.y += movementSpeed; // dx
        }
    }

    @Override
    public void MoveDown(float time) {
        if (isAlive){
            //MoveDown();
            MoverY(false);

            if (time >= timeL) { //se cumplio
                setIsAlive(false);
            }
        }
    }

    @Override
    public void RebotarY() {

        position.y += dx;

        if (position.y >= (vHeight - sHeight) || position.y <= 0){
            dx = dx * -1;
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
    }

}
