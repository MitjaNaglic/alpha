package com.mitjanaglic.alpha.game.systems.ai;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.components.*;
import com.mitjanaglic.alpha.game.components.ai.DiscAiComponent;
import com.mitjanaglic.alpha.game.systems.CameraSystem;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 1:20
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class DiscAiSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<GunComponent> gunM;
    private GunComponent gunComponent;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private PositionComponent positionComponent;
    @Mapper
    private ComponentMapper<DiscAiComponent> discAiM;
    private DiscAiComponent discAiComponent;
    @Mapper
    private ComponentMapper<VelocityComponent> velocityM;
    private VelocityComponent velocityComponent;
    private Entity player;
    private CameraSystem cameraSystem;


    public DiscAiSystem() {
        super(Aspect.getAspectForAll(DiscAiComponent.class, GunComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        gunComponent = gunM.get(entity);
        positionComponent = positionM.get(entity);
        discAiComponent = discAiM.get(entity);
        velocityComponent = velocityM.get(entity);
        cameraSystem = world.getSystem(CameraSystem.class);
        player = world.getManager(TagManager.class).getEntity("player");
        if (player != null) {
            move();
            float bounds = cameraSystem.getUpperBound() - positionComponent.getHitbox().getHeight();
            if (positionComponent.getPosition().y < bounds) {
                aim(player);
                gunComponent.setShootRequest(true);
            }
        }
    }

    private void move() {
        if (discAiComponent.getWaypoint() != null) {
            //new waypoint if old is reached Or went out of bounds
            if (positionComponent.getPosition().dst(discAiComponent.getWaypoint()) < 1
                    || discAiComponent.getWaypoint().y < cameraSystem.getPosition().y) {
                setWaypoint();
            } else {
                Vector2 direction = new Vector2(discAiComponent.getWaypoint().cpy().sub(positionComponent.getPosition()));
                direction.nor();
                velocityComponent.setTargetVelocity(direction.scl(velocityComponent.getSpeed()));
            }
        } else {
            setWaypoint();
        }
    }

    private void setWaypoint() {
        Random rng = new Random();
        float screenwidth = cameraSystem.getWidth();
        float screenHeight = cameraSystem.getHeight();
        float maxX = screenwidth - positionComponent.getHitbox().getWidth();
        float minX = 0;
        float maxY = cameraSystem.getPosition().y + screenHeight - positionComponent.getHitbox().getHeight();
        float minY = cameraSystem.getPosition().y;
        float x = rng.nextInt((int) ((maxX - minX + 1))) + minX;
        float y = rng.nextInt((int) ((maxY - minY + 1))) + minY;

        discAiComponent.setWaypoint(new Vector2(x, y));
    }

    private void aim(Entity target) {
        PositionComponent targetPositionComponent = positionM.get(target);
        gunComponent.setAimAngle(targetPositionComponent.getCenter().sub(gunComponent.getGunPosition()).angle() - 90);
    }
}
