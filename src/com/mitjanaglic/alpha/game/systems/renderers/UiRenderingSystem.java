package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.LifeComponent;
import com.mitjanaglic.alpha.game.constants.ids;

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
    @Mapper
    private ComponentMapper<LifeComponent> lifeM;
    private LifeComponent playerLife;
    private BitmapFont font;
    private boolean debugRendering = true;

    public UiRenderingSystem(AssetManager assetManager, CameraComponent cameraComponent) {
        this.assetManager = assetManager;
        this.cameraComponent = cameraComponent;
    }

    @Override
    protected void initialize() {
        textureAtlas = assetManager.get("data/png/textures/textures.atlas", TextureAtlas.class);
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        if (player != null) {
            playerLife = lifeM.get(player);
        }
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
        float x = 20;
        float y = 20;
        String currentLife;
        String maxLife;
        if (playerLife != null) {
            currentLife = String.valueOf(playerLife.getCurrentLife());
            maxLife = String.valueOf(playerLife.getMaxLife());
        } else {
            currentLife = "0";
            maxLife = "-";
        }

        spriteBatch.draw(textureAtlas.findRegion("life"),
                x,
                y
        );
        font.draw(spriteBatch, String.valueOf(currentLife) + " / " + maxLife, x + 50, y + 20);
    }

    private void renderFps() {
        if (debugRendering) {
            font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0,
                    cameraComponent.getVIRTUAL_HEIGHT());
            font.draw(spriteBatch, "Current entities: " + world.getEntityManager().getActiveEntityCount(), 0,
                    cameraComponent.getVIRTUAL_HEIGHT() - 20);
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
    }
}
