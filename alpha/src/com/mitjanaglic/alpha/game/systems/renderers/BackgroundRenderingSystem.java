package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.models.Level;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 14:06
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class BackgroundRenderingSystem extends VoidEntitySystem implements Disposable {
    private CameraComponent cameraComponent;
    private TiledMap levelMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Level level;
    private int[] backgroundLayers = {0, 1};

    public BackgroundRenderingSystem(CameraComponent cameraComponent, Level level) {
        this.cameraComponent = cameraComponent;
        this.level = level;
    }

    @Override
    protected void initialize() {
        levelMap = level.getMap();
        mapRenderer = new OrthogonalTiledMapRenderer(levelMap, 1);
    }

    @Override
    protected void processSystem() {
        cameraComponent.getCamera().update();
        //camera.apply(Gdx.gl10);


        // set viewport
        Gdx.gl.glViewport((int) cameraComponent.getViewport().x, (int) cameraComponent.getViewport().y,
                (int) cameraComponent.getViewport().width, (int) cameraComponent.getViewport().height);
        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(cameraComponent.getCamera());
        mapRenderer.render();
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
    }
}
