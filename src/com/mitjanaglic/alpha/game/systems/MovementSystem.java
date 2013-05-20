package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.PositionComponent;
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
    private ComponentMapper<StateComponent> stateM;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private StateComponent stateComponent;

    public MovementSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, VelocityComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        positionComponent = positionM.get(entity);
        velocityComponent = velocityM.get(entity);
        stateComponent = stateM.get(entity);


        updateVelocity();
        updatePosition();
        updateHitbox();
        setState();
    }

    private void updateVelocity() {
        //diagonal movement too fast fix
        velocityComponent.getTargetVelocity().nor();
        velocityComponent.getTargetVelocity().scl(velocityComponent.getSpeed());
        //linear interpolation
        velocityComponent.getCurrentVelocity().lerp(velocityComponent.getTargetVelocity().cpy().scl(world.getDelta()), velocityComponent.getAcceleration());
    }

    private void updatePosition() {
        positionComponent.getPosition().add(velocityComponent.getCurrentVelocity());
        //forward inertia
        positionComponent.getPosition().add(0, velocityComponent.getForwardInertia() * world.getDelta());
    }

    private void updateHitbox() {
        positionComponent.getHitbox().setX(positionComponent.getPosition().x);
        positionComponent.getHitbox().setY(positionComponent.getPosition().y);
    }

    //not used anymore, poziciji se vsako iteracijo doda forwardInertia kot scalar
    private void calculateForwardMomentum() {
        //prever y komponento velocity vectorja, če je dodana forwardInertia...zna povzročat probleme če je speed večji kot inertia?
        if (Math.abs(velocityComponent.getTargetVelocity().y) - Math.abs(velocityComponent.getForwardInertia()) < 0) {
            velocityComponent.getTargetVelocity().y += velocityComponent.getForwardInertia();
        }
    }

    private void setState() {
        //sets state depending upon moving direction
        if (velocityComponent.getTargetVelocity().x < 0)
            stateComponent.setCurrentMovementState(StateComponent.movementState.MOVING_LEFT);
        else if (velocityComponent.getTargetVelocity().x > 0)
            stateComponent.setCurrentMovementState(StateComponent.movementState.MOVING_RIGHT);
        else stateComponent.setCurrentMovementState(StateComponent.movementState.IDLE);
    }

}
