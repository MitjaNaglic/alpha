package com.mitjanaglic.alpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.mitjanaglic.alpha.view.WorldRenderer;
import com.mitjanaglic.alpha.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen {
    private Space space;
    private WorldRenderer renderer;

    public GameScreen() {
        space = new Space();
        renderer = new WorldRenderer(space);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        renderer.render();
    }

    @Override
    public void resize(int x, int y) {
        renderer.setSize(x, y);
    }

    @Override
    public void show() {
        //To change body of implemented methods use File | Settings | File Templates.
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
    }
}
