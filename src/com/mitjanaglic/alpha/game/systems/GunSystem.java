package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.*;

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
        super(Aspect.getAspectForAll(PositionComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        positionComponent = positionM.get(entity);
        gunComponent = gunM.get(entity);

        updatePosition();
        if (gunComponent.shootRequested()) {
            //TODO if cooled down
            shoot();
        }
    }

    private void shoot() {
        Entity bullet = world.createEntity();
        bullet.addComponent(new PositionComponent(gunComponent.getPositionX(), gunComponent.getPositionY()));
        bullet.addComponent(new VelocityComponent(0, 1500));
        bullet.addComponent(new StateComponent(StateComponent.State.MOVING));
        bullet.addComponent(new HitboxComponent(gunComponent.getPositionX(), gunComponent.getPositionY(), 9, 9));
        bullet.addComponent(new RenderableComponent("laserRed", 1, 1, 0));
        world.getManager(GroupManager.class).add(bullet, "bullets");
    }

    private void updatePosition() {
        gunComponent.setPositionX(positionComponent.getPosition().x);
        gunComponent.setPositionY(positionComponent.getPosition().y);
    }
}
