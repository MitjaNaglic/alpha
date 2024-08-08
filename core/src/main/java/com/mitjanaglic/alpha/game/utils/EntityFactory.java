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
import com.mitjanaglic.alpha.game.models.SpawnPoint;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 16:10
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class EntityFactory {
    public static void createPlayer(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y, 91, 75, true);
        e.addComponent(new PlayerShipComponent());
        e.addComponent(new StateComponent(StateComponent.movementState.IDLE));
        e.addComponent(positionComponent);
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new VelocityComponent(0, 0, 0.2f, 500, forwardInertia));
        e.addComponent(new InputComponent());
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "laserGreen",
                "laserGreenShot",
                positionComponent.getHitbox().getWidth() / 2,
                positionComponent.getHitbox().getHeight(),
                0,
                1100,
                ids.PLAYER,
                10
        ));
        e.addComponent(new LifeComponent(1000));
        e.addComponent(new RenderableComponent("player", 1, 1, 0));
        e.addComponent(new ShieldComponent(200));
        world.addEntity(e);
        world.getManager(TagManager.class).register(ids.PLAYER, e);
    }

    public static void createDisc(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y, 91, 91, true);
        e.addComponent(positionComponent);
        e.addComponent(new DiscComponent(250));
        e.addComponent(new StateComponent(StateComponent.movementState.IDLE));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new VelocityComponent(0, 0, 0.2f, 300, forwardInertia));
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                "laserRedShot",
                positionComponent.getHitbox().getWidth() / 2,
                positionComponent.getHitbox().getHeight() / 2,
                0,
                800,
                ids.DISC,
                10
        ));
        e.addComponent(new RenderableComponent("Disc", 1, 1, 0));
        e.addComponent(new DiscAiComponent());
        e.addComponent(new LifeComponent(200));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createScarab(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y, 98, 50, true);
        e.addComponent(positionComponent);
        e.addComponent(new StateComponent(StateComponent.movementState.IDLE));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new VelocityComponent(0, 0, 0.2f, 80, forwardInertia - 40));
        WeaponsArrayComponent weaponsArrayComponent = new WeaponsArrayComponent();
        weaponsArrayComponent.getWeaponsArray().add(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                "laserRedShot",
                positionComponent.getHitbox().getWidth() / 4,
                0,
                180,
                400,
                ids.SCARAB,
                10
        ));
        weaponsArrayComponent.getWeaponsArray().add(new GunComponent(positionComponent.getPosition(),
                "laserRed",
                "laserRedShot",
                positionComponent.getHitbox().getWidth() / 1.50f,
                0,
                180,
                400,
                ids.SCARAB,
                10
        ));
        e.addComponent(weaponsArrayComponent);
        e.addComponent(new RenderableComponent("Scarab", 1, 1, 0));
        e.addComponent(new ScarabAiComponent());
        e.addComponent(new LifeComponent(100));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createBigMeteor(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new PositionComponent(x, y, 136, 111, true));
        e.addComponent(new VelocityComponent(0, 0, 0.2f, 0, 0));
        e.addComponent(new StateComponent(StateComponent.movementState.IDLE));
        e.addComponent(new RenderableComponent("meteorBig", 1, 1, 0));
        e.addComponent(new LifeComponent(200));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createSmallMeteor(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new PositionComponent(x, y, 44, 42, true));
        e.addComponent(new VelocityComponent(0, 0, 0.2f, 0, 0));
        e.addComponent(new StateComponent(StateComponent.movementState.IDLE));
        e.addComponent(new RenderableComponent("meteorSmall", 1, 1, 0));
        e.addComponent(new LifeComponent(100));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createMinos(World world, float x, float y) {
        Entity e = world.createEntity();
        PositionComponent positionComponent = new PositionComponent(x, y, 102, 122, true);
        e.addComponent(positionComponent);
        e.addComponent(new StateComponent(StateComponent.movementState.IDLE));
        float forwardInertia = world.getManager(TagManager.class).getEntity("camera").getComponent(CameraComponent.class).getCameraScrollSpeed();
        e.addComponent(new VelocityComponent(0, 0, 0.05f, 100, forwardInertia - 60));
        e.addComponent(new GunComponent(positionComponent.getPosition(),
                "ballBlue",
                "blueShot",
                positionComponent.getHitbox().getWidth() / 2,
                positionComponent.getHitbox().getHeight() / 2,
                0,
                100,
                ids.MINOS,
                50
        ));
        e.addComponent(new RenderableComponent("Minos", 1, 1, 0));
        e.addComponent(new DiscAiComponent());
        e.addComponent(new LifeComponent(300));
        world.addEntity(e);
        world.getManager(GroupManager.class).add(e, ids.ENEMY);
    }

    public static void createBullet(World world, GunComponent gunComponent) {
        Entity bullet = world.createEntity();
        bullet.addComponent(new PositionComponent(gunComponent.getGunPosition(), 9, 9, true));
        VelocityComponent velocityComponent = new VelocityComponent(0, gunComponent.getBulletSpeed(), 1f, gunComponent.getBulletSpeed(), 0);
        velocityComponent.getTargetVelocity().rotate(gunComponent.getAimAngle());
        bullet.addComponent(velocityComponent);
        bullet.addComponent(new StateComponent(StateComponent.movementState.MOVING));
        bullet.addComponent(new BulletComponent(bullet.getComponent(PositionComponent.class).getPosition(),
                gunComponent.getImpactTextureId(),
                gunComponent.getRange(),
                gunComponent.getBulletSpeed(),
                gunComponent.getGunDamage()
        ));
        bullet.addComponent(new RenderableComponent(gunComponent.getBulletTextureName(), 1, 1, gunComponent.getAimAngle()));
        world.addEntity(bullet);
        //ugotovi se taprav id na podlagi guncomponent ownerja
        String id;
        if (gunComponent.getOwnerId() == ids.PLAYER) id = ids.PLAYER_BULLETS;
        else id = ids.ENEMY_BULLETS;
        world.getManager(GroupManager.class).add(bullet, id);
    }

    public static void spawnEntity(World world, SpawnPoint spawnPoint) {
        if (spawnPoint.getEntityId().equals(ids.PLAYER)) createPlayer(world, spawnPoint.getX(), spawnPoint.getY());
        else if (spawnPoint.getEntityId().equals(ids.DISC)) createDisc(world, spawnPoint.getX(), spawnPoint.getY());
        else if (spawnPoint.getEntityId().equals(ids.METEOR))
            createBigMeteor(world, spawnPoint.getX(), spawnPoint.getY());
        else if (spawnPoint.getEntityId().equals(ids.METEOR_SMALL))
            createSmallMeteor(world, spawnPoint.getX(), spawnPoint.getY());
        else if (spawnPoint.getEntityId().equals(ids.MINOS))
            createMinos(world, spawnPoint.getX(), spawnPoint.getY());
        else if (spawnPoint.getEntityId().equals(ids.SCARAB))
            createScarab(world, spawnPoint.getX(), spawnPoint.getY());
    }
}
