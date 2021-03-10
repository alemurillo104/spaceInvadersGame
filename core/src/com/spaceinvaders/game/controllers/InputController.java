package com.spaceinvaders.game.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.Map;

public class InputController implements InputProcessor {

    Map<String, Boolean> teclas;
//    public boolean keyPressed;

    public InputController() {
        teclas = new HashMap<>();
        teclas.put("SPACE_SHOOT", false);
        teclas.put("PLAYER_LEFT", false);
        teclas.put("PLAYER_RIGHT", false);
    }

//    public void executeActions(){
//        if (teclas.get("SPACE_SHOOT")){
//
//        }
//    }

    @Override
    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.T) {
//            keyPressed = true;
//        }
        if (keycode == Input.Keys.NUMPAD_0){
            teclas.put("SPACE_SHOOT", true);

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
//        if (keycode == Input.Keys.T) {
//            keyPressed = false;
//        }

        if (keycode == Input.Keys.NUMPAD_0){
            teclas.put("SPACE_SHOOT", false);
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
