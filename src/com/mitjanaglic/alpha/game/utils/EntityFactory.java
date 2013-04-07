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
import com.mitjanaglic.alpha.game.constants.ids;

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
                "laserGreenShot",
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight(),
                0,
                1100,
                ids.PLAYER));
        e.addComponent(new LivesComponent(10));
        e.addComponent(new RenderableComponent("player", 1, 1, 0));
        world.addEntity(e);
        world.getManager(TagManager.class).register(ids.PLAYER, e);
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
                "laserRedShot",
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight() / 2,
                0,
                800,
                ids.DISC
        ));
        e.addComponent(new RenderableComponent("Disc", 1, 1, 0));
        e.addComponent(new DiscAiComponent());
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
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
        WeaponsArrayComponent weaponsArrayComponent = new WeaponsArrayComponent();
        weaponsArrayComponent.getWeaponsArray().add(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                "laserRedShot",
                hitboxComponent.getHitbox().getWidth() / 4,
                0,
                180,
                900,
                ids.SCARAB));
        weaponsArrayComponent.getWeaponsArray().add(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                "laserRedShot",
                hitboxComponent.getHitbox().getWidth() / 1.50f,
                0,
                180,
                900,
                ids.SCARAB));
        e.addComponent(weaponsArrayComponent);
        e.addComponent(new RenderableComponent("Scarab", 1, 1, 0));
        e.addComponent(new ScarabAiComponent());
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createBigMeteor(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new PositionComponent(x, y));
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(0, forwardInertia));
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(new HitboxComponent(x, y, 136, 111));
        e.addComponent(new RenderableComponent("meteorBig", 1, 1, 0));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createSmallMeteor(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new PositionComponent(x, y));
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(0, forwardInertia));
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(new HitboxComponent(x, y, 44, 42));
        e.addComponent(new RenderableComponent("meteorSmall", 1, 1, 0));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createMinos(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y);
        e.addComponent(positionComponent);
        e.addComponent(new StateComponent(StateComponent.State.IDLE));
        e.addComponent(new VelocityComponent(0, 0));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new SpeedComponent(0, forwardInertia));
        HitboxComponent hitboxComponent = new HitboxComponent(positionComponent.getPosition().x, positionComponent.getPosition().y, 102, 122);
        e.addComponent(hitboxComponent);
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "ballBlue",
                "blueShot",
                hitboxComponent.getHitbox().getWidth() / 2,
                hitboxComponent.getHitbox().getHeight() / 2,
                0,
                600,
                ids.MINOS
        ));
        e.addComponent(new RenderableComponent("Minos", 1, 1, 0));
        e.addComponent(new DiscAiComponent());
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
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
                gunComponent.getImpactTextureId(),
                gunComponent.getRange(),
                gunComponent.getBulletSpeed(),
                gunComponent.getGunDamage()
        ));
        bullet.addComponent(new HitboxComponent(gunComponent.getOffsetX(), gunComponent.getOffsetY(), 9, 9));
        bullet.addComponent(new RenderableComponent(gunComponent.getBulletTextureName(), 1, 1, gunComponent.getAimAngle()));
        world.addEntity(bullet);
        //ugotovi se taprav id na podlagi guncomponent ownerja
        String id;
        if (gunComponent.getOwnerId() == ids.PLAYER) id = ids.PLAYER_BULLETS;
        else id = ids.ENEMY_BULLETS;
        world.getManager(GroupManager.class).add(bullet, id);
    }
}
