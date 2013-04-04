package com.mitjanaglic.alpha.game.utils;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.mitjanaglic.alpha.game.components.*;
import com.mitjanaglic.alpha.game.components.ai.DiscAiComponent;
import com.mitjanaglic.alpha.game.components.ai.ScarabAiComponent;
import com.mitjanaglic.alpha.game.components.ids.DiscComponent;
import com.mitjanaglic.alpha.game.components.ids.PlayerShipComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 16:10
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class EntityFactory {
    public static void createPlayer(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y);
        e.addComponent(new PlayerShipComponent());
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(positionComponent);
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(500, forwardInertia));
        HitboxComponent hitboxComponent = new HitboxComponent(positionComponent.getPosition().x, positionComponent.getPosition().y, 91, 75);
        e.addComponent(hitboxComponent);
        e.addComponent(new InputComponent());
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "laserGreen",
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight()));
        e.addComponent(new LivesComponent(10));
        e.addComponent(new RenderableComponent("player", 1, 1, 0));
        world.addEntity(e);
        world.getManager(TagManager.class).register("player", e);
    }

    public static void createDisc(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y);
        e.addComponent(positionComponent);
        e.addComponent(new DiscComponent(250));
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(0, forwardInertia));
        HitboxComponent hitboxComponent = new HitboxComponent(positionComponent.getPosition().x, positionComponent.getPosition().y, 91, 91);
        e.addComponent(hitboxComponent);
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight() / 2));
        e.addComponent(new RenderableComponent("Disc", 1, 1, 0));
        e.addComponent(new DiscAiComponent());
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, "disc");
    }

    public static void createScarab(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y);
        e.addComponent(positionComponent);
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(0, forwardInertia));
        HitboxComponent hitboxComponent = new HitboxComponent(positionComponent.getPosition().x, positionComponent.getPosition().y, 98, 50);
        e.addComponent(hitboxComponent);
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                hitboxComponent.getHitbox().getWidth() / 4,
                0));
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                hitboxComponent.getHitbox().getWidth() / 2,
                0));
        e.addComponent(new RenderableComponent("Scarab", 1, 1, 0));
        e.addComponent(new ScarabAiComponent());
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, "scarab");
    }

    public static void createBullet(World world, GunComponent gunComponent) {
        Entity bullet = world.createEntity();
        bullet.addComponent(new PositionComponent(gunComponent.getGunPosition()));
        VelocityComponent velocityComponent = new VelocityComponent(0, gunComponent.getBulletSpeed());
        velocityComponent.getVelocity().rotate(gunComponent.getAimAngle());
        bullet.addComponent(velocityComponent);
        bullet.addComponent(new SpeedComponent(gunComponent.getBulletSpeed(), 0));
        bullet.addComponent(new StateComponent(StateComponent.State.MOVING));
        bullet.addComponent(new BulletComponent(bullet.getComponent(PositionComponent.class).getPosition(),
                gunComponent.getRange(),
                gunComponent.getBulletSpeed(),
                gunComponent.getGunDamage()
        ));
        bullet.addComponent(new HitboxComponent(gunComponent.getOffsetX(), gunComponent.getOffsetY(), 9, 9));
        bullet.addComponent(new RenderableComponent(gunComponent.getBulletTextureName(), 1, 1, gunComponent.getAimAngle()));
        world.addEntity(bullet);
        world.getManager(GroupManager.class).add(bullet, "bullets");
    }
}
