package com.mitjanaglic.alpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.mitjanaglic.alpha.controllers.EnemyController;
import com.mitjanaglic.alpha.controllers.PlayerController;
import com.mitjanaglic.alpha.controllers.SpaceController;
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
    private PlayerController controller;
    private SpaceController spaceController;
    private EnemyController enemyController;
    private int height, width;

    public GameScreen() {
        space = new Space();
        renderer = new WorldRenderer(space);
        controller = new PlayerController(space);
        spaceController = new SpaceController(space);
        enemyController = new EnemyController(space);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        spaceController.update(delta);
        enemyController.update(delta);
        space.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int x, int y) {
        renderer.setSize(x, y);
        width = x;
        height = y;
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
        controller = null;
        spaceController = null;
        enemyController = null;
        space.dispose();
        renderer.dispose();
        Gdx.input.setInputProcessor(null);
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
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (x < width / 2 && !(y < height / 4 && x < width / 4)) {
            controller.leftPressed();
        }
        if (x > width / 2) {
            controller.rightPressed();
        }
        if (y > height / 2) {
            controller.downPressed();
        }
        if (y < height / 2 && !(y < height / 4 && x < width / 4)) {
            controller.upPressed();
        }
        if (y < height / 4 && x < width / 4) {
            controller.firePressed();
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        controller.leftReleased();
        controller.rightReleased();
        controller.downReleased();
        controller.upReleased();
        controller.fireReleased();
        return true;
    }


    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (x < width / 2 && !(y < height / 4 && x < width / 4)) {
            controller.leftPressed();
        }
        if (x > width / 2) {
            controller.rightPressed();
        }
        if (y > height / 2) {
            controller.downPressed();
        }
        if (y < height / 2 && !(y < height / 4 && x < width / 4)) {
            controller.upPressed();
        }
        return true;
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
