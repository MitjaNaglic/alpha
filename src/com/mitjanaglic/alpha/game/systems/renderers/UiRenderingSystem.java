package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.LivesComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 14:30
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class UiRenderingSystem extends VoidEntitySystem implements Disposable {
    private AssetManager assetManager;
    private CameraComponent cameraComponent;
    private TextureAtlas textureAtlas;
    private SpriteBatch spriteBatch;
    private Entity player;
    private LivesComponent playerLives;
    private BitmapFont font;
    private boolean debugRendering = true;

    public UiRenderingSystem(AssetManager assetManager, CameraComponent cameraComponent) {
        this.assetManager = assetManager;
        this.cameraComponent = cameraComponent;
    }

    @Override
    protected void initialize() {
        textureAtlas = assetManager.get("data/png/textures/textures.pack", TextureAtlas.class);
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        player = world.getManager(TagManager.class).getEntity("player");
        playerLives = player.getComponent(LivesComponent.class);
    }

    @Override
    protected void begin() {
        spriteBatch.begin();
    }

    @Override
    protected void end() {
        spriteBatch.end();
    }

    @Override
    protected void processSystem() {
        renderLives();
        if (debugRendering) {
            renderFps();
        }
    }

    private void renderLives() {
        for (int i = 0; i < playerLives.getLives(); i++) {
            spriteBatch.draw(textureAtlas.findRegion("life"),
                    20 + i * 40,
                    30
            );
        }
    }

    private void renderFps() {
        font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0,
                cameraComponent.getVIRTUAL_HEIGHT());
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
    }
}
