package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.*;
import com.mitjanaglic.alpha.game.components.ids.PlayerShipComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 21:07
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class LevelBoundsSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<VelocityComponent> velocityM;
    private VelocityComponent velocityComponent;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private PositionComponent positionComponent;
    CameraComponent cameraComponent;

    public LevelBoundsSystem(CameraComponent cameraComponent) {
        super(Aspect.getAspectForAll(PlayerShipComponent.class, VelocityComponent.class, PositionComponent.class));
        this.cameraComponent = cameraComponent;
    }

    @Override
    protected void process(Entity entity) {
        velocityComponent = velocityM.get(entity);
        positionComponent = positionM.get(entity);

        checkLevelBoundCollision();
    }

    private void checkLevelBoundCollision() {
        if (velocityComponent.getCurrentVelocity().x < 0 || velocityComponent.getTargetVelocity().x < 0) {
            if (positionComponent.getPosition().x <= 0) {
                velocityComponent.getTargetVelocity().x = 0;
                velocityComponent.getCurrentVelocity().x = 0;
            }
        }
        if (velocityComponent.getCurrentVelocity().x > 0 || velocityComponent.getTargetVelocity().x > 0) {
            if (positionComponent.getPosition().x + positionComponent.getHitbox().getWidth() >= cameraComponent.getCameraWidth()) {
                velocityComponent.getTargetVelocity().x = 0;
                velocityComponent.getCurrentVelocity().x = 0;
            }
        }
        if (velocityComponent.getTargetVelocity().y <= 0) {
            if (positionComponent.getPosition().y <= cameraComponent.getCameraPosition().y - cameraComponent.getCameraHeight() / 2 + 50) {
                velocityComponent.getTargetVelocity().y = 0;
            }
        }
        if (velocityComponent.getCurrentVelocity().y > 0 || velocityComponent.getTargetVelocity().y > 0) {
            if (positionComponent.getPosition().y + positionComponent.getHitbox().getHeight() >= cameraComponent.getCameraPosition().y
                    + cameraComponent.getCameraHeight() / 2) {
                velocityComponent.getTargetVelocity().y = 0;
                velocityComponent.getCurrentVelocity().y = 0;
            }
        }
    }
}
