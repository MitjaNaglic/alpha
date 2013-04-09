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
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class DiscAiSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<GunComponent> gunM;
    private GunComponent gunComponent;
    @Mapper
    private ComponentMapper<HitboxComponent> hitboxM;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private PositionComponent positionComponent;
    @Mapper
    private ComponentMapper<DiscAiComponent> discAiM;
    private DiscAiComponent discAiComponent;
    @Mapper
    private ComponentMapper<VelocityComponent> velocityM;
    private VelocityComponent velocityComponent;
    @Mapper
    private ComponentMapper<SpeedComponent> speedM;
    private SpeedComponent speedComponent;
    private Entity player;


    public DiscAiSystem() {
        super(Aspect.getAspectForAll(DiscAiComponent.class, GunComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        gunComponent = gunM.get(entity);
        positionComponent = positionM.get(entity);
        discAiComponent = discAiM.get(entity);
        velocityComponent = velocityM.get(entity);
        speedComponent = speedM.get(entity);
        player = world.getManager(TagManager.class).getEntity("player");
        if (player != null) {
            move();
            aim(player);
            gunComponent.setShootRequest(true);
        }
    }

    private void move() {
        if (discAiComponent.getWaypoint() != null) {
            if (positionComponent.getPosition().dst(discAiComponent.getWaypoint()) < 1) {
                setWaypoint();
            } else {
                Vector2 direction = new Vector2(discAiComponent.getWaypoint().cpy().sub(positionComponent.getPosition()));
                direction.nor();
                velocityComponent.setVelocity(direction.scl(speedComponent.getSpeed()));
            }
        } else {
            setWaypoint();
        }
    }

    private void setWaypoint() {
        Vector2 playerPos = hitboxM.get(player).getCenter();
        float range = gunComponent.getRange() - 500;
        CameraSystem cameraSystem = world.getSystem(CameraSystem.class);
        Random rng = new Random();
        float screenwidth = cameraSystem.getWidth();
        float screenHeight = cameraSystem.getHeight();
        float maxX = screenwidth;
        float minX = 0;
        float maxY = cameraSystem.getPosition().y + screenHeight;
        float minY = cameraSystem.getPosition().y;
        float x = rng.nextInt((int) ((maxX - minX + 1))) + minX;
        float y = rng.nextInt((int) ((maxY - minY + 1))) + minY;

        discAiComponent.setWaypoint(new Vector2(x, y));
    }

    private void aim(Entity target) {
        HitboxComponent targetHitboxComponent = hitboxM.get(target);
        gunComponent.setAimAngle(targetHitboxComponent.getCenter().sub(gunComponent.getGunPosition()).angle() - 90);
    }
}
