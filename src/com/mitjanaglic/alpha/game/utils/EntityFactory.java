package com.mitjanaglic.alpha.game.utils;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.mitjanaglic.alpha.game.components.*;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 16:10
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class EntityFactory {
    public static Entity createPlayer(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y);
        e.addComponent(new PlayerShipComponent());
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(positionComponent);
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(400, forwardInertia));
        HitboxComponent hitboxComponent = new HitboxComponent(positionComponent.getPosition().x, positionComponent.getPosition().y, 99, 75);
        e.addComponent(hitboxComponent);
        e.addComponent(new InputComponent());
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight()));
        e.addComponent(new LivesComponent(10));
        e.addComponent(new RenderableComponent("player", 1, 1, 0));
        return e;
    }

    public static Entity createDisc(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y);
        e.addComponent(positionComponent);
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(0, forwardInertia));
        HitboxComponent hitboxComponent = new HitboxComponent(positionComponent.getPosition().x, positionComponent.getPosition().y, 91, 91);
        e.addComponent(hitboxComponent);
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight() / 2));
        e.addComponent(new RenderableComponent("Disc", 1, 1, 0));
        return e;
    }

    public static Entity createBullet(World world, GunComponent gunComponent) {
        Entity bullet = world.createEntity();
        bullet.addComponent(new PositionComponent(gunComponent.getGunPosition()));
        bullet.addComponent(new VelocityComponent(0, gunComponent.getBulletSpeed()));
        bullet.addComponent(new SpeedComponent(gunComponent.getBulletSpeed(), 0));
        bullet.addComponent(new StateComponent(StateComponent.State.MOVING));
        bullet.addComponent(new BulletComponent(bullet.getComponent(PositionComponent.class).getPosition(),
                gunComponent.getRange(),
                gunComponent.getBulletSpeed(),
                gunComponent.getGunDamage()
        ));
        bullet.addComponent(new HitboxComponent(gunComponent.getOffsetX(), gunComponent.getOffsetY(), 9, 9));
        bullet.addComponent(new RenderableComponent("laserRed", 1, 1, 0));
        return bullet;
    }
}
