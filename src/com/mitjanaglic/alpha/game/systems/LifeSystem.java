package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.LifeComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 7.4.2013
 * Time: 19:22
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class LifeSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<LifeComponent> lifeM;
    private LifeComponent lifeComponent;

    public LifeSystem() {
        super(Aspect.getAspectForAll(LifeComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        lifeComponent = lifeM.get(entity);
        if (lifeComponent.getCurrentLife() <= 0) {
            entity.deleteFromWorld();
        }
    }
}
