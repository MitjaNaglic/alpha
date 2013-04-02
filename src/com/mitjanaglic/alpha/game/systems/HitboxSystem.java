package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.HitboxComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 0:53
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class HitboxSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    @Mapper
    private ComponentMapper<HitboxComponent> hitboxM;

    public HitboxSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, HitboxComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent positionComponent = positionM.get(entity);
        HitboxComponent hitboxComponent = hitboxM.get(entity);

        hitboxComponent.getHitbox().setX(positionComponent.getPosition().x);
        hitboxComponent.getHitbox().setY(positionComponent.getPosition().y);
    }
}
