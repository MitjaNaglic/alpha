package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.components.SpeedComponent;
import com.mitjanaglic.alpha.game.components.StateComponent;
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
    @Mapper
    private ComponentMapper<StateComponent> stateM;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private SpeedComponent speedComponent;
    private StateComponent stateComponent;

    public MovementSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, VelocityComponent.class, SpeedComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        positionComponent = positionM.get(entity);
        velocityComponent = velocityM.get(entity);
        speedComponent = speedM.get(entity);
        stateComponent = stateM.get(entity);

        calculateForwardMomentum();
        positionComponent.getPosition().add(velocityComponent.getVelocity().cpy().scl(world.getDelta()));
        setState();

    }

    private void calculateForwardMomentum() {
        if (Math.abs(velocityComponent.getVelocity().y) - Math.abs(speedComponent.getForwardInertia()) < 0) {
            velocityComponent.getVelocity().y += speedComponent.getForwardInertia();
        }
    }

    private void setState() {
        //sets state depending upon moving direction
        if (velocityComponent.getVelocity().x < 0) stateComponent.setState(StateComponent.State.MOVING_LEFT);
        else if (velocityComponent.getVelocity().x > 0) stateComponent.setState(StateComponent.State.MOVING_RIGHT);
        else stateComponent.setState(StateComponent.State.IDLE);
    }

}
