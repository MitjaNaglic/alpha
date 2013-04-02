package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.BulletComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 18:53
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class BulletSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<BulletComponent> bulletM;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;

    public BulletSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, BulletComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        BulletComponent bulletComponent = bulletM.get(entity);
        PositionComponent positionComponent = positionM.get(entity);
        if (positionComponent.getPosition().cpy().dst(bulletComponent.getShotOrigin()) >= bulletComponent.getRange()) {
            entity.deleteFromWorld();
        }
    }
}
