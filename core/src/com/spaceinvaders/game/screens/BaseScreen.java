package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Screen;
import com.spaceinvaders.game.MainGame;

public class BaseScreen implements Screen {

    protected MainGame game;

    public BaseScreen(MainGame mainGame){
        this.game = mainGame;
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) { }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
