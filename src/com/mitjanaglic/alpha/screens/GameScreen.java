package com.mitjanaglic.alpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.mitjanaglic.alpha.controllers.WorldController;
import com.mitjanaglic.alpha.view.WorldRenderer;
import com.mitjanaglic.alpha.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen, InputProcessor {
    private Space space;
    private WorldRenderer renderer;
    private WorldController controller;

    public GameScreen() {
        space = new Space();
        renderer = new WorldRenderer(space);
        controller = new WorldController(space);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        controller.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int x, int y) {
        renderer.setSize(x, y);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        space.dispose();
        renderer.dispose();
    }

    /*-------------------------------------INPUT PROCESSOR --------------------------*/
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A)
            controller.leftPressed();
        if (keycode == Input.Keys.D)
            controller.rightPressed();
        if (keycode == Input.Keys.W)
            controller.upPressed();
        if (keycode == Input.Keys.S)
            controller.downPressed();
        if (keycode == Input.Keys.SPACE)
            controller.firePressed();

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A)
            controller.leftReleased();
        if (keycode == Input.Keys.D)
            controller.rightReleased();
        if (keycode == Input.Keys.W)
            controller.upReleased();
        if (keycode == Input.Keys.S)
            controller.downReleased();
        if (keycode == Input.Keys.SPACE)
            controller.fireReleased();

        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scrolled(int i) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
