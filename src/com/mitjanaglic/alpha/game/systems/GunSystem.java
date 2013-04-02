package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.GunComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.utils.EntityFactory;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 1:58
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class GunSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<GunComponent> gunM;
    private GunComponent gunComponent;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private PositionComponent positionComponent;

    public GunSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, GunComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        positionComponent = positionM.get(entity);
        gunComponent = gunM.get(entity);

        updatePosition();
        updateCooldown();
        if (gunComponent.shootRequested()) {
            if (gunComponent.getShootCooldown() <= 0) {
                gunComponent.setShootCooldown(gunComponent.getTimeBetweenShots());
                shoot();
                gunComponent.setShootRequest(false);
            }
        }
    }

    private void updateCooldown() {
        if (gunComponent.getShootCooldown() > 0) {
            gunComponent.setShootCooldown(gunComponent.getShootCooldown() - world.getDelta());
        }
    }

    private void shoot() {
        Entity bullet = EntityFactory.createBullet(world, gunComponent);
        world.addEntity(bullet);
        world.getManager(GroupManager.class).add(bullet, "bullets");
    }

    private void updatePosition() {
        gunComponent.setGunPosition(positionComponent.getPosition().cpy().add(gunComponent.getOffsetX(), gunComponent.getOffsetY()));
    }
}
