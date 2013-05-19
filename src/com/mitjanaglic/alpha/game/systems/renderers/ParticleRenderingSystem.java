package com.mitjanaglic.alpha.game.systems.renderers;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.ParticleEmmiterComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 19.5.2013
 * Time: 19:19
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ParticleRenderingSystem extends EntitySystem implements Disposable {
    @Mapper
    private ComponentMapper<ParticleEmmiterComponent> emmiterM;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public ParticleRenderingSystem(CameraComponent cameraComponent) {
        super(Aspect.getAspectForAll(ParticleEmmiterComponent.class));
        camera = cameraComponent.getCamera();
        batch = new SpriteBatch();
    }

    @Override
    protected void begin() {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        for (int i = 0; i < entityImmutableBag.size(); i++) {
            ParticleEmmiterComponent particleEmmiterComponent = emmiterM.get(entityImmutableBag.get(i));
            particleEmmiterComponent.getEffect().draw(batch, world.getDelta());
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
