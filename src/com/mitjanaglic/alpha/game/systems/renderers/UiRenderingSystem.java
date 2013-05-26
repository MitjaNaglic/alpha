package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.Assets;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.LifeComponent;
import com.mitjanaglic.alpha.game.components.ShieldComponent;
import com.mitjanaglic.alpha.game.constants.ids;
import com.mitjanaglic.alpha.game.utils.Fonts;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 14:30
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class UiRenderingSystem extends VoidEntitySystem implements Disposable {
    private CameraComponent cameraComponent;
    private TextureAtlas textureAtlas;
    private SpriteBatch spriteBatch;
    private Entity player;
    @Mapper
    private ComponentMapper<LifeComponent> lifeM;
    private LifeComponent playerLife;
    @Mapper
    private ComponentMapper<ShieldComponent> shieldM;
    private ShieldComponent shieldComponent;
    private Fonts fonts;
    private boolean debugRendering = true;

    public UiRenderingSystem(CameraComponent cameraComponent, SpriteBatch batch) {
        this.cameraComponent = cameraComponent;
        spriteBatch = batch;
    }

    @Override
    protected void initialize() {
        textureAtlas = Assets.getAssetManager().get("data/png/textures/textures.atlas", TextureAtlas.class);
        fonts = Assets.getAssetManager().get("data/font/NEUROPOL.ttf");
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
        spriteBatch.setProjectionMatrix(cameraComponent.getCamera().projection);
        spriteBatch.setColor(1, 1, 1, 1);
        player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        if (player != null) {
            playerLife = lifeM.get(player);
            shieldComponent = shieldM.get(player);
        }
        renderLives();
        if (debugRendering) {
            renderFps();
        }
    }

    private void renderLives() {
        float x = 20 - cameraComponent.getCameraWidth() / 2;
        float y = 20 - cameraComponent.getCameraHeight() / 2;
        float currentLife;
        float maxLife;
        float currentShields;
        float maxShields;
        if (playerLife != null) {
            currentLife = playerLife.getCurrentLife();
            maxLife = playerLife.getMaxLife();
            currentShields = shieldComponent.getCurrentShields();
            maxShields = shieldComponent.getMaxShields();
        } else {
            currentLife = 0;
            maxLife = 0;
            currentShields = 0;
            maxShields = 0;
        }

        spriteBatch.draw(textureAtlas.findRegion("life"),
                x,
                y
        );

        float lifebarWidth = currentLife / maxLife * 300;
        float lifebarX = x + 50;
        float shieldbarWidth = currentShields / maxShields * 200;
        float shieldbarX = lifebarX;
        float shieldbarY = y + 40;
        if (lifebarWidth > 0) {
            renderBar(lifebarX, y, lifebarWidth, "lifeBarBit");
        }
        if (shieldbarWidth > 0) {
            renderBar(shieldbarX, shieldbarY, shieldbarWidth, "shieldBarBit");
        }
        float damagebarWidth = 0;
        float shieldDamageBarWidth = 0;
        if (playerLife != null) {
            damagebarWidth = playerLife.getDamage() / maxLife * 300;
        }
        if (currentLife > 0 && damagebarWidth > 0) {
            renderBar(lifebarX + lifebarWidth, y, damagebarWidth, "damageBarBit");
        }

        //font.draw(spriteBatch, String.valueOf(currentLife) + " / " + maxLife, x + 50, y + 20);
    }

    private void renderBar(float x, float y, float width, String textureName) {
        TextureRegion textureRegion = textureAtlas.findRegion(textureName);
        spriteBatch.draw(textureRegion,
                x,
                y,
                0,
                0,
                width,
                textureRegion.getRegionHeight()
                , 1
                , 1
                , 0
        );
    }

    private void renderFps() {
        if (debugRendering) {
            fonts.getDebugFont().draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0 - cameraComponent.getCameraWidth() / 2,
                    cameraComponent.getCameraHeight() / 2);
            fonts.getDebugFont().draw(spriteBatch, "Current entities: " + world.getEntityManager().getActiveEntityCount(), 0 - cameraComponent.getCameraWidth() / 2,
                    cameraComponent.getCameraHeight() / 2 - 20);
            fonts.getDebugFont().draw(spriteBatch, "Java heap: " + Gdx.app.getJavaHeap() / 1048576, 0 - cameraComponent.getCameraWidth() / 2,
                    cameraComponent.getCameraHeight() / 2 - 40);
        }
    }

    @Override
    public void dispose() {
    }
}
