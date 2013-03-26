package com.mitjanaglic.alpha.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.mitjanaglic.alpha.game.Alpha;
import com.mitjanaglic.alpha.game.controllers.EnemyController;
import com.mitjanaglic.alpha.game.controllers.SpaceController;
import com.mitjanaglic.alpha.game.models.entities.components.InputComponent;
import com.mitjanaglic.alpha.game.view.WorldRenderer;
import com.mitjanaglic.alpha.game.models.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen, InputProcessor {
    private Alpha alpha;
    private Space space;
    private WorldRenderer renderer;
    private SpaceController spaceController;
    private EnemyController enemyController;
    private int height, width;
    private boolean isAccelometerAvailable;
    private AssetManager assetManager;
    private InputComponent inputComponent;

    public GameScreen(Alpha alpha) {
        this.alpha = alpha;
        assetManager = alpha.getAssetManager();
        space = new Space(assetManager);
        inputComponent=(InputComponent)space.getPlayer().getComponents().get("input");
        renderer = new WorldRenderer(assetManager, space);
        spaceController = new SpaceController(space);
        enemyController = new EnemyController(space);
        isAccelometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    public void render(float delta) {
        if (isAccelometerAvailable) checkTilt();
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
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        alpha.setToPauseScreen();
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        spaceController = null;
        enemyController = null;
        space.dispose();
        renderer.dispose();
        Gdx.input.setInputProcessor(null);
    }

    /*-------------------------------------INPUT PROCESSOR --------------------------*/

    public void checkTilt() {
        // points to the right (when in portrait orientation)
        if (Gdx.input.getAccelerometerX() > 1) {
            inputComponent.downPressed();
        } else if (Gdx.input.getAccelerometerX() < -1) {
            inputComponent.upPressed();
        } else {
            inputComponent.upReleased();
            inputComponent.downReleased();
        }
        // points upwards (when in portrait orientation)
        if (Gdx.input.getAccelerometerY() > 1) {
            inputComponent.rightPressed();
        } else if (Gdx.input.getAccelerometerY() < -1) {
            inputComponent.leftPressed();
        } else {
            inputComponent.leftReleased();
            inputComponent.rightReleased();
        }
        //Gdx.input.getAccelerometerZ(); // points to the front of the display (coming out of the screen)

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A)
            inputComponent.leftPressed();
        if (keycode == Input.Keys.D)
            inputComponent.rightPressed();
        if (keycode == Input.Keys.W)
            inputComponent.upPressed();
        if (keycode == Input.Keys.S)
            inputComponent.downPressed();
        if (keycode == Input.Keys.SPACE)
            inputComponent.firePressed();

        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
            this.pause();


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A)
            inputComponent.leftReleased();
        if (keycode == Input.Keys.D)
            inputComponent.rightReleased();
        if (keycode == Input.Keys.W)
            inputComponent.upReleased();
        if (keycode == Input.Keys.S)
            inputComponent.downReleased();
        if (keycode == Input.Keys.SPACE)
            inputComponent.fireReleased();

        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (x < width / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.leftPressed();
        }
        if (x > width / 2) {
            inputComponent.rightPressed();
        }
        if (y > height / 2) {
            inputComponent.downPressed();
        }
        if (y < height / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.upPressed();
        }
        if (y < height / 4 && x < width / 4) {
            inputComponent.firePressed();
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        inputComponent.leftReleased();
        inputComponent.rightReleased();
        inputComponent.downReleased();
        inputComponent.upReleased();
        inputComponent.fireReleased();
        return true;
    }


    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (x < width / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.leftPressed();
        }
        if (x > width / 2) {
            inputComponent.rightPressed();
        }
        if (y > height / 2) {
            inputComponent.downPressed();
        }
        if (y < height / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.upPressed();
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
