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

    //Heads-Up Display, must be a class i think
    private BitmapFont font;
    private float hudVerticalMargin, hudLeftX, hudRightX, hudCentreX, hudRow1Y, hudRow2Y, hudSectionWidth ;

    private NaveActor player;
    private int score, playerLives;

    private Color fontColor, borderColor;
    private int fontSize;
    private float borderWidth, fontScale;

    private boolean detectedCollision, detectedCollisionPlayer;

    public ScoreLActor(NaveActor player, int score){

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
        //Create a BitmapFont from out font file

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = fontSize;
        fontParameter.borderWidth = borderWidth;
        fontParameter.color = fontColor;
        fontParameter.borderColor = borderColor;

        font = fontGenerator.generateFont(fontParameter);

        //scale the font to fit the world

        font.getData().setScale(fontScale);

        //calculate hud margins, etc.
        hudVerticalMargin = font.getCapHeight() / 2;
        hudLeftX = hudVerticalMargin;
        hudRightX = (WIDTH) * 2 / 3 - hudLeftX;
        hudCentreX = (WIDTH) / 3;
        hudRow1Y =  (HEIGHT) - hudVerticalMargin ;
        hudRow2Y =  hudRow1Y - hudVerticalMargin - font.getCapHeight();
        hudSectionWidth = (WIDTH) / 3 ;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        font.draw(batch, "Score", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false);
        //font.draw(batch, "Shield", hudCentreX, hudRow1Y, hudSectionWidth, Align.center, false);
        font.draw(batch, "Lives", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false);

        //render the second row values

        font.draw(batch, String.format(Locale.getDefault(), "%06d", score), hudLeftX, hudRow2Y, hudSectionWidth, Align.left, false);
        //font.draw(batch, String.format(Locale.getDefault(), "%02d", player.getLives()),  hudCentreX, hudRow2Y, hudSectionWidth, Align.center, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", playerLives), hudRightX, hudRow2Y, hudSectionWidth, Align.right, false);

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

    public void detach(){ }
}
