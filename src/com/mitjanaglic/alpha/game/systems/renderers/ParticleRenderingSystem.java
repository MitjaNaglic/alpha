package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.ParticleEmmiterComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 19.5.2013
 * Time: 19:19
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ParticleRenderingSystem extends EntitySystem {
    @Mapper
    private ComponentMapper<ParticleEmmiterComponent> emmiterM;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public ParticleRenderingSystem(CameraComponent cameraComponent, SpriteBatch batch) {
        super(Aspect.getAspectForAll(ParticleEmmiterComponent.class));
        camera = cameraComponent.getCamera();
        this.batch = batch;
    }

    @Override
    protected void begin() {
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        batch.setProjectionMatrix(camera.combined);
        for (int i = 0; i < entityImmutableBag.size(); i++) {
            Entity e = entityImmutableBag.get(i);
            ParticleEmmiterComponent particleEmmiterComponent = emmiterM.get(e);
            particleEmmiterComponent.getEffect().draw(batch, world.getDelta());
            if (particleEmmiterComponent.getEffect().isComplete()) {
                particleCleanup(e);
            }
        }
    }

    private void particleCleanup(Entity e) {
        e.deleteFromWorld();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
