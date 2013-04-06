package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.HitmarkComponent;
import com.mitjanaglic.alpha.game.components.RenderableComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.4.2013
 * Time: 7:50
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class HitmarkSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<HitmarkComponent> hitmarkM;
    private HitmarkComponent hitmarkComponent;
    @Mapper
    private ComponentMapper<RenderableComponent> renderableM;
    private RenderableComponent renderableComponent;

    public HitmarkSystem() {
        super(Aspect.getAspectForAll(HitmarkComponent.class, RenderableComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        hitmarkComponent = hitmarkM.get(entity);
        renderableComponent = renderableM.get(entity);
        animate();
        handleLifetime(entity);
    }

    private void animate() {
        renderableComponent.setScaleX(renderableComponent.getScaleX() + world.getDelta() * 10);
        renderableComponent.setScaleY(renderableComponent.getScaleY() + world.getDelta() * 10);
    }

    private void handleLifetime(Entity entity) {
        if (hitmarkComponent.getCurrentLifetime() < hitmarkComponent.getLifeSpan()) {
            hitmarkComponent.setCurrentLifetime(hitmarkComponent.getCurrentLifetime() + world.getDelta());
        } else {
            entity.deleteFromWorld();
        }
    }
}
