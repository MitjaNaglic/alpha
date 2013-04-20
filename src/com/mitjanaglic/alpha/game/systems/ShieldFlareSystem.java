package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.components.ShieldFlareComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.4.2013
 * Time: 18:17
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ShieldFlareSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<ShieldFlareComponent> flareM;
    private ShieldFlareComponent flareComponent;
    @Mapper
    private ComponentMapper<RenderableComponent> renderableM;
    private RenderableComponent renderableComponent;

    public ShieldFlareSystem() {
        super(Aspect.getAspectForAll(ShieldFlareComponent.class, RenderableComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        flareComponent = flareM.get(entity);
        renderableComponent = renderableM.get(entity);
        animate();
        handleFlareDuration(entity);

    }

    private void handleFlareDuration(Entity entity) {
        if (flareComponent.getCurrentFlareDuration() < flareComponent.getFlareDuration()) {
            flareComponent.setCurrentFlareDuration(flareComponent.getCurrentFlareDuration() + world.getDelta());
        } else {
            entity.deleteFromWorld();
        }
    }

    private void animate() {
        if (renderableComponent.getA() - world.getDelta() > 0) {
            renderableComponent.setA(renderableComponent.getA() - world.getDelta());
        }
    }
}
