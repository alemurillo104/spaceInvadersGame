package com.spaceinvaders.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import java.util.Locale;

import static com.spaceinvaders.game.common.Constants.HEIGHT;
import static com.spaceinvaders.game.common.Constants.WIDTH;

public class ScoreLActor extends Actor {

    private BitmapFont font;
    private float posVerticalMargin, posLeftX, posRightX, posCentreX, posRow1Y, posRow2Y, posSectionWidth ;

    private PlayerShipActor player;
    private int score, playerLives;

    private Color fontColor, borderColor;
    private int fontSize;
    private float borderWidth, fontScale;

    private boolean detectedCollision, detectedCollisionPlayer;
    private int nivel;

    public ScoreLActor(PlayerShipActor player, int score, int nivel){

        this.player = player;
        this.score = score;
        this.playerLives = player.getLives();

        this.detectedCollision = false;
        this.detectedCollisionPlayer = false;

        this.fontSize = 35;
        this.borderWidth = 3.6f;
        this.fontScale = 0.8f; //0.08f
        this.fontColor = new Color(1,1,1,0.3f);
        this.borderColor = new Color(0,0,0,0.3f);

        this.nivel = nivel;
        //Crear BitmapFont del archivo font

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = fontSize;
        fontParameter.borderWidth = borderWidth;
        fontParameter.color = fontColor;
        fontParameter.borderColor = borderColor;

        font = fontGenerator.generateFont(fontParameter);

        font.getData().setScale(fontScale); //scale

        //calcular margenes
        posVerticalMargin = font.getCapHeight() / 2;
        posLeftX = posVerticalMargin;
        posRightX = (WIDTH) * 2 / 3 - posLeftX;
        posCentreX = (WIDTH) / 3;
        posRow1Y =  (HEIGHT) - posVerticalMargin ;
        posRow2Y =  posRow1Y - posVerticalMargin - font.getCapHeight();
        posSectionWidth = (WIDTH) / 3 ;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        font.draw(batch, "Score", posLeftX, posRow1Y, posSectionWidth, Align.left, false);
        font.draw(batch, "Level", posCentreX, posRow1Y, posSectionWidth, Align.center, false);
        font.draw(batch, "Lives", posRightX, posRow1Y, posSectionWidth, Align.right, false);

        //render the second row values

        font.draw(batch, String.format(Locale.getDefault(), "%06d", score), posLeftX, posRow2Y, posSectionWidth, Align.left, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", nivel),  posCentreX, posRow2Y, posSectionWidth, Align.center, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", playerLives), posRightX, posRow2Y, posSectionWidth, Align.right, false);

    }

    @Override
    public void act(float delta) {
        if(detectedCollision){
            score += 100;
            detectedCollision = false;
        }

        if(detectedCollisionPlayer){
            playerLives = player.decreaseLives2();
            //player.decreaseLives();
            //decreaseLives();
            detectedCollisionPlayer = false;
        }
    }

    public void decreaseLives(){
        //playerLives = player.decreaseLives2();
        if (player.isAlive()) {
            if (playerLives > 0) {
                playerLives--;

            } else {
                player.setAlive(false);
            }
        }
    }

    public boolean isDetectedCollision() {
        return detectedCollision;
    }

    public void setDetectedCollision(boolean detectedCollision) {
        this.detectedCollision = detectedCollision;
    }

    public void setDetectedCollisionPlayer(boolean detectedCollisionPlayer) {
        this.detectedCollisionPlayer = detectedCollisionPlayer;
    }

}
