package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.spaceinvaders.game.common.Constants.HEIGHT;

public class EnemyActor extends Actor {

    public Texture texture;
    public Vector2 position;
    public float sWidth, sHeight;
    public float dx, dy;
    public float timeL;
    public Vector2 movementSpeed;
    public boolean isAlive;

    private float vWidth, vHeight;
    private long startTime;
    private float timeS;

    private int mov;

    //Añadiendo para el Sprite
    private Animation animation;
    private float tiempo;
    private TextureRegion [] regionsMovement;
    private TextureRegion frameActual;

    private int cantImg = 6;
    private boolean start = false, iniciar = true;

    private long startTimeA;
    private float timeSA;
    private float frameDuration = 1/5f, timeObj = 0.5f;


    public EnemyActor(Texture texture, Vector2 position, Vector2 size, float dy, float timeL) {
        this.texture = texture;
        this.position = position;
        this.sWidth = size.x; this.sHeight = size.y;
        this.dx = 6; this.dy = dy;
        this.timeL = timeL;
        this.movementSpeed = new Vector2(this.dx / timeL, dy / timeL);
        //System.out.println("dy: " + dy + ", time: " + timeL + ", vel: " + movementSpeed.y);
        this.isAlive = true;

        this.vWidth = Gdx.graphics.getWidth();
        this.vHeight = Gdx.graphics.getHeight();
        startTime = System.currentTimeMillis();

        //cargar la imagen
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / cantImg, texture.getHeight());
        regionsMovement = new TextureRegion[cantImg];
        for (int i = 0; i < cantImg; i++){
            regionsMovement[i] = tmp[0][i];
            regionsMovement[i].flip(false, true);
        }

        animation = new Animation(frameDuration, (Object[]) regionsMovement);
        tiempo = 0f;

        frameActual = (TextureRegion) animation.getKeyFrames()[0];
        setSize(sWidth, sHeight);
    }

    public EnemyActor(Texture texture) {
        this.texture = texture;
        this.position = Vector2.Zero;
        this.sWidth = 100f;
        this.sHeight = 120f;
        this.dx = 6;
        this.dy = 3;
        this.timeL = 500;
        this.movementSpeed = new Vector2(this.dx / timeL, dy / timeL);
        this.isAlive = true;

        this.vHeight = Gdx.graphics.getHeight();
        startTime = System.currentTimeMillis();
        setSize(sWidth,sHeight);
    }

    public boolean isAlive() { return isAlive; }
    public boolean isStart() { return start; }
    public int     getMov()  { return mov; }

    public void setAlive(boolean sw) { isAlive = sw; }
    public void setStart(boolean start) { this.start = start; }
    public void setMov(int mov) { this.mov = mov; }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(position.x, position.y);
        if (isAlive){
            batch.draw(frameActual, position.x, position.y, sWidth, sHeight);
        }
    }

    @Override
    public void act(float delta) {
        tiempo += delta;

        if (start){
            startAnimation();
            frameActual = (TextureRegion) animation.getKeyFrame(tiempo, true);
        }

        timeS = actTime();
        MoveDown(timeS);
    }

    private void startAnimation() {
        iniciarTiempo();
        timeSA = (System.currentTimeMillis() - startTimeA) /1000;
        if (timeSA >= timeObj){
            setAlive(false);
        }
    }

    private void iniciarTiempo() {
        if (iniciar){
            startTimeA = System.currentTimeMillis();
            iniciar = false;
        }
    }

    public void MoverY(boolean sw) {
        if (!sw){
            if (position.y > 0) position.y -= movementSpeed.y; // dy
        }else{
            if (position.y < vHeight) position.y += movementSpeed.y; // dy
        }
    }

    public void MoverX(boolean sw) {
        if (!sw){
            if (position.x > 0) position.x -= movementSpeed.x; // dx
        }else{
            if (position.x < vWidth) position.x += movementSpeed.x; // dx
        }
    }

    public void MoveDown(float time) {
        if (isAlive){
            //MoveDown();
            MoverY(false);
//            RebotarY();
//            abajoIzqDer();

            if (time >= timeL) { //se cumplio
                timeL = timeL + time;
            }
        }
    }

    public void RebotarY() {

        position.y += movementSpeed.y; // dy

        if (position.y >= (HEIGHT - sHeight + 1) || position.y <= 0){
            movementSpeed.y = movementSpeed.y * -1;
        }
    }

    private float actTime(){
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    //Movimientos

    public void abajoIzqDer(){
            MoverY(false);
            MoverX(false);
//            MoverY(false);
            MoverX(true);
        System.out.println("ejecuntanod...................");
    }

    public void detach(){
        texture.dispose();
    }

}

//        tiempo += Gdx.graphics.getDeltaTime();
//
//        frameActual = (start)
//                ? (TextureRegion) animation.getKeyFrame(tiempo, true)
//                : (TextureRegion) animation.getKeyFrames()[0];