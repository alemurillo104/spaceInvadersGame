package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.spaceinvaders.game.common.Constants.HEIGHT;
import static com.spaceinvaders.game.common.Constants.WIDTH;

public class EnemyActor extends Actor {

    private Texture texture;
    private Vector2 position;
    private float sWidth, sHeight;
    private float dx, dy;
    private float timeL;
    private Vector2 movSpeed;
    private boolean isAlive;
    private float vWidth, vHeight;

    private long startTime;
    private float timeS;
    private int mov;

    private Vector2  posInicial, posPlayer;

    //Añadiendo para el Sprite
    private Animation animation;
    private float tiempo;
    private TextureRegion [] regionsMovement;
    private TextureRegion frameActual;

    private int cantImg; //6
    private boolean start = false, iniciar = true;

    private long startTimeA;
    private float timeSA, frameDuration = 1/5f, timeObj = 0.5f;


    public EnemyActor(Texture texture, Vector2 position2, Vector2 size, float dy, float timeL, Vector2 posPlayer, int cimg) {

        this.texture = texture;
        this.posInicial = new Vector2(position2.x, position2.y);
        this.position = position2;
        this.sWidth = size.x; this.sHeight = size.y;
        this.dx = 6; this.dy = dy; //10
        this.timeL = timeL;
        this.movSpeed = new Vector2(this.dx / timeL, dy / timeL); // 2*timeL
        this.isAlive = true;
        this.cantImg = cimg;

        this.vWidth = Gdx.graphics.getWidth();
        this.vHeight = Gdx.graphics.getHeight();
        startTime = System.currentTimeMillis();

        cargarSpriteImage();

        this.posPlayer = posPlayer;

        setSize(sWidth, sHeight); //CREO

    }

    private void cargarSpriteImage(){

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / cantImg, texture.getHeight());
        regionsMovement = new TextureRegion[cantImg];

        for (int i = 0; i < cantImg; i++){
            regionsMovement[i] = tmp[0][i];
            regionsMovement[i].flip(false, true);
        }

        animation = new Animation(frameDuration, (Object[]) regionsMovement);
        tiempo = 0f;

        frameActual = (TextureRegion) animation.getKeyFrames()[0];
    }


    public void setAlive(boolean sw) { isAlive = sw; }
    public void setStart(boolean start) { this.start = start; }
    public void setMov(int mov) { this.mov = mov; }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x, position.y);

        if (isAlive) {
            batch.draw(frameActual, position.x, position.y, sWidth, sHeight);
        }
    }

    @Override
    public void act(float delta) {

        tiempo += delta;

        if (start){
            startAnimationTime();
            frameActual = (TextureRegion) animation.getKeyFrame(tiempo, true);
        }

        timeS = actTime();
        executeMovement(timeS);
    }

    private void startAnimationTime() {
        iniciarTiempo();
        timeSA = (System.currentTimeMillis() - startTimeA) /1000;

        if (timeSA >= timeObj) setAlive(false);
    }

    private void iniciarTiempo() {
        if (iniciar){
            startTimeA = System.currentTimeMillis();
            iniciar = false;
        }
    }

    public void executeMovement(float time) {
        if (isAlive){
            ejecutarMovement(mov);

            if (time >= timeL)  //se cumplio
                timeL = timeL + time;
        }
    }

    private void ejecutarMovement(int mov) {
        switch (mov){
            case 1 : MoverY(false); break;
            case 2 : RebotarY(); break;
            case 3 : FollowPlayer(); break;
            case 4 : MoverZigZag(); break;
        }
    }

    //Movimientos

    public void MoverY(boolean sw) {
        if (!sw) {
            if (position.y > 0) position.y -= movSpeed.y; // dy
        } else {
            if (position.y < vHeight) position.y += movSpeed.y; // dy
        }
    }

    private void FollowPlayer() {
        if (position.y > 0) {

            if (posPlayer.x > position.x) {
                float dif = posPlayer.x - position.x;
                if (dif > 0) position.x += (Math.sqrt(dif) / Math.PI);

            } else {
                float dif = position.x - posPlayer.x;
                if (dif > 0) position.x -= (Math.sqrt(dif) / Math.PI);
            }

            MoverY(false);
        }
    }

    public void RebotarY() {

        position.y += movSpeed.y; // dy

        if (position.y >= (HEIGHT - sHeight + 1) || position.y <= 0){
            movSpeed.y = movSpeed.y * -1;
        }
    }

    public void MoverX(boolean sw) {
        if (!sw) {
            if (position.x > 0) position.x -= movSpeed.x; // dx
        }else{
            if (position.x < vWidth) position.x += movSpeed.x; // dx
        }
    }

    public void MoverZigZag(){

        if (position.y > 0){
            position.x += movSpeed.x; // dy

            if (position.x >= ( posInicial.x + (WIDTH /4) - sWidth + 1) || position.x <= posInicial.x){
                movSpeed.x = movSpeed.x * -1;
            }
            MoverY(false);
        }
    }

    private float actTime(){
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public void detach(){
        texture.dispose();
    }
}
