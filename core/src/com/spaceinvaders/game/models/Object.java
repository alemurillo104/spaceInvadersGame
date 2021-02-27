package com.spaceinvaders.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Object{

    public SpriteBatch batch;

    public Texture texture;
    public Vector2 position;
    public float sWidth, sHeight;
    public float dx;
    public float timeL;
    public float movementSpeed;

    public boolean isAlive;

    public abstract void draw();

    public abstract boolean isAlive();
    public abstract void setIsAlive(boolean sw);

    public abstract float getTimeL();

    public abstract void MoverY(boolean sw);
    public abstract void MoveDown(float time);
    public abstract void RebotarY();

    public abstract void dispose();
}

