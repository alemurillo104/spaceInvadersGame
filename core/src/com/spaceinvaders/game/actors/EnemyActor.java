package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyActor extends Actor {

    private Texture enemyTexture;
    private Vector2 position;
    private float size;
    private float dx;
    private float time;
    private float movementSpeed; // velocidad

    public EnemyActor(Texture spTexture, Vector2 position, float size, float dx){
        this.enemyTexture = spTexture;
        this.position = position;
        this.size = size;
        this.dx = dx;
        setSize(size, size);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x, position.y);
        batch.draw(enemyTexture, position.x, position.y, size, size);
    }

    @Override
    public void act(float delta) {
        MoverY(false);
//        RebotarY();
    }

    public void MoverY(boolean sw){
        if (!sw){
            if (position.y > 0) position.y -= dx;
        }else{
            if (position.y < Gdx.graphics.getHeight()) position.y += dx;
        }
    }

    public void RebotarY(){

        position.y += dx;

        if (position.y >= (Gdx.graphics.getHeight() - (enemyTexture.getHeight() + size)) ||
            position.y <= 0){
            dx = dx * -1;
        }
    }

    public void detach(){
        enemyTexture.dispose();
    }
}
