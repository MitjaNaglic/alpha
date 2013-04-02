package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.components.SpeedComponent;
import com.mitjanaglic.alpha.game.components.VelocityComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 28.3.2013
 * Time: 1:39
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class MovementSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    @Mapper
    private ComponentMapper<VelocityComponent> velocityM;
    @Mapper
    private ComponentMapper<SpeedComponent> speedM;

    public MovementSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, VelocityComponent.class, SpeedComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent positionComponent = positionM.get(entity);
        VelocityComponent velocityComponent = velocityM.get(entity);
        SpeedComponent speedComponent = speedM.get(entity);

        velocityComponent.getVelocity().y += speedComponent.getForwardInertia();

        positionComponent.getPosition().add(velocityComponent.getVelocity().cpy().scl(world.getDelta()));
    }

}
