package com.spaceinvaders.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.spaceinvaders.game.common.Constants.WIDTH;

public class SpaceShipActor extends Actor {

    private Texture spaceShipTexture;
    private Vector2 position;
    private float size;
    private boolean izquierda = false, derecha = true;
    private float valor = 3f;


    public SpaceShipActor(Texture spTexture, Vector2 position){
        this.spaceShipTexture = spTexture;
        this.position = position;
        this.size = 80;
        setSize(size, size);
    }

    public SpaceShipActor(Texture spTexture, Vector2 position, float size){
        this.spaceShipTexture = spTexture;
        this.position = position;
        this.size = size;
        setSize(size, size);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x, position.y);
        batch.draw(spaceShipTexture, position.x, position.y, size, size);
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

        System.out.println(posX);

        if (posX > (WIDTH - size)){
            derecha = false;
            izquierda = true;
        }
        if ( posX < (WIDTH - size) && posX < 0){
            izquierda = false;
            derecha = true;
        }
    }

    public void MoverX(float val){
        position.x += val;
    }

    public void detach(){
        spaceShipTexture.dispose();
    }

}
