package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.*;

import static com.mitjanaglic.alpha.game.components.InputComponent.Keys;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 1:10
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class InputSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<InputComponent> inputM;
    @Mapper
    private ComponentMapper<VelocityComponent> velocityM;
    @Mapper
    private ComponentMapper<StateComponent> stateM;
    @Mapper
    private ComponentMapper<GunComponent> gunM;
    private InputComponent inputComponent;
    private VelocityComponent velocityComponent;
    private StateComponent stateComponent;
    private GunComponent gunComponent;

    public InputSystem() {
        super(Aspect.getAspectForAll(InputComponent.class,
                VelocityComponent.class,
                StateComponent.class
        ));
    }

    @Override
    protected void process(Entity entity) {
        inputComponent = inputM.get(entity);
        velocityComponent = velocityM.get(entity);
        stateComponent = stateM.get(entity);
        gunComponent = gunM.get(entity);
        processInput();
    }

    private void processInput() {
        if (inputComponent.getKeys().get(Keys.LEFT)) {
            velocityComponent.getTargetVelocity().x = -velocityComponent.getSpeed();
            stateComponent.setCurrentMovementState(StateComponent.movementState.MOVING_LEFT);

        }
        if (inputComponent.getKeys().get(Keys.RIGHT)) {
            velocityComponent.getTargetVelocity().x = velocityComponent.getSpeed();
            stateComponent.setCurrentMovementState(StateComponent.movementState.MOVING_RIGHT);

        }
        if (inputComponent.getKeys().get(Keys.UP)) {
            velocityComponent.getTargetVelocity().y = velocityComponent.getSpeed();

        }
        if (inputComponent.getKeys().get(Keys.DOWN)) {
            velocityComponent.getTargetVelocity().y = -velocityComponent.getSpeed();

        }
        if (inputComponent.getKeys().get(Keys.FIRE)) {
            gunComponent.setShootRequest(true);
        } else {
            gunComponent.setShootRequest(false);
        }

        // need to check if both or none direction are pressed, then is idle
        if ((inputComponent.getKeys().get(Keys.LEFT) && inputComponent.getKeys().get(Keys.RIGHT)) ||
                (!inputComponent.getKeys().get(Keys.LEFT) && !(inputComponent.getKeys().get(Keys.RIGHT)))) {
            stateComponent.setCurrentMovementState(StateComponent.movementState.IDLE);

            // horizontal speed is 0
            velocityComponent.getTargetVelocity().x = 0;
        }
        if ((inputComponent.getKeys().get(Keys.UP) && inputComponent.getKeys().get(Keys.DOWN)) ||
                (!inputComponent.getKeys().get(Keys.UP) && !(inputComponent.getKeys().get(Keys.DOWN)))) {

            // horizontal speed is 0
            velocityComponent.getTargetVelocity().y = 0;
        }


    }
}
