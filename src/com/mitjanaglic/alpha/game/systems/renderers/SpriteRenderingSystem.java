package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.HitboxComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.constants.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 31.3.2013
 * Time: 15:09
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class SpriteRenderingSystem extends EntitySystem implements Disposable {
    @Mapper
    private ComponentMapper<RenderableComponent> renderableM;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    @Mapper
    private ComponentMapper<HitboxComponent> hitboxM;
    private RenderableComponent renderableComponent;
    private SpriteBatch batch;
    private CameraComponent cameraComponent;
    private AssetManager assetManager;
    private PositionComponent positionComponent;
    private TextureAtlas textureAtlas;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private boolean debugRendering = false;

    public SpriteRenderingSystem(CameraComponent cameraComponent, SpriteBatch batch) {
        super(Aspect.getAspectForAll(RenderableComponent.class));
        this.assetManager = Assets.getAssetManager();
        this.cameraComponent = cameraComponent;
        this.batch = batch;
        camera = cameraComponent.getCamera();
    }

    @Override
    protected void initialize() {
        textureAtlas = assetManager.get("data/png/textures/textures.atlas", TextureAtlas.class);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    protected void begin() {
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    }

    @Override
    protected void end() {
        batch.end();
        shapeRenderer.end();
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        for (int i = 0; i < entityImmutableBag.size(); i++) {
            process(entityImmutableBag.get(i));
            if (debugRendering) {
                renderHitboxes(entityImmutableBag.get(i));
            }
        }
    }

    private void process(Entity entity) {
        positionComponent = positionM.get(entity);
        renderableComponent = renderableM.get(entity);
        TextureAtlas.AtlasRegion atlasRegion = textureAtlas.findRegion(renderableComponent.getSpriteTextureName());
        batch.setColor(renderableComponent.getColor().r, renderableComponent.getColor().g, renderableComponent.getColor().b, renderableComponent.getColor().a);
        batch.draw(atlasRegion,
                positionComponent.getPosition().x + renderableComponent.getOffsetX(),
                positionComponent.getPosition().y + renderableComponent.getOffsetY(),
                atlasRegion.getRegionWidth() / 2,
                atlasRegion.getRegionHeight() / 2,
                atlasRegion.getRegionWidth(),
                atlasRegion.getRegionHeight(),
                renderableComponent.getScaleX(),
                renderableComponent.getScaleY(),
                renderableComponent.getRotationAngle()
        );
    }

    private void renderHitboxes(Entity entity) {
        HitboxComponent hitboxComponent = hitboxM.get(entity);
        if (hitboxComponent != null) {
            shapeRenderer.rect(hitboxComponent.getHitbox().getX(),
                    hitboxComponent.getHitbox().getY(),
                    hitboxComponent.getHitbox().getWidth(),
                    hitboxComponent.getHitbox().getHeight());
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
