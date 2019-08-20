package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.mitjanaglic.alpha.game.components.BulletComponent;
import com.mitjanaglic.alpha.game.components.LifeComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.constants.ids;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 9:52
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class CollisionSystem extends EntitySystem {
    @Mapper
    private ComponentMapper<BulletComponent> bulletM;
    @Mapper
    private ComponentMapper<LifeComponent> lifeM;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;


    public CollisionSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class));
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        Entity player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        ImmutableBag<Entity> enemies = world.getManager(GroupManager.class).getEntities(ids.ENEMY);
        ImmutableBag<Entity> enemyBullets = world.getManager(GroupManager.class).getEntities(ids.ENEMY_BULLETS);
        ImmutableBag<Entity> playerBullets = world.getManager(GroupManager.class).getEntities(ids.PLAYER_BULLETS);

        playerObjectColissions(player, enemies);
        playerBulletCollisions(playerBullets, enemies);
        enemyBulletCollsiions(player, enemyBullets);
    }

    private void playerObjectColissions(Entity player, ImmutableBag<Entity> enemies) {
        if (player != null && enemies != null) {
            PositionComponent playerPosition = positionM.get(player);
            for (int i = 0; i < enemies.size(); i++) {
                PositionComponent enemyPosition = positionM.get(enemies.get(i));
                if (playerPosition.getHitbox().overlaps(enemyPosition.getHitbox())) {
                    damageDistribution(player, enemies.get(i), 100);
                    damageDistribution(enemies.get(i), player, 100);
                }
            }
        }
    }

    private void playerBulletCollisions(ImmutableBag<Entity> playerBullets, ImmutableBag<Entity> enemies) {
        if (playerBullets != null && enemies != null) {
            for (int i = 0; i < playerBullets.size(); i++) {
                for (int j = 0; j < enemies.size(); j++) {
                    PositionComponent playerBulletPosition = positionM.get(playerBullets.get(i));
                    PositionComponent enemyPosition = positionM.get(enemies.get(j));

                    if (playerBulletPosition.getHitbox().overlaps(enemyPosition.getHitbox())) {
                        handleCollision(enemies.get(j), playerBullets.get(i));
                        handleBulletCollision(playerBullets.get(i));
                    }
                }
            }
        }
    }

    private void enemyBulletCollsiions(Entity player, ImmutableBag<Entity> enemyBullets) {
        if (player != null && enemyBullets != null) {
            PositionComponent playerPosition = positionM.get(player);
            for (int i = 0; i < enemyBullets.size(); i++) {
                PositionComponent enemyBulletPosition = positionM.get(enemyBullets.get(i));

                if (enemyBulletPosition.getHitbox().overlaps(playerPosition.getHitbox())) {
                    handleCollision(player, enemyBullets.get(i));
                    handleBulletCollision(enemyBullets.get(i));
                }
            }
        }
    }

    private void handleCollision(Entity entity, Entity bullet) {
        BulletComponent bulletComponent = bulletM.get(bullet);
        damageDistribution(entity, bullet, bulletComponent.getDamage());
    }

    private void handleBulletCollision(Entity bullet) {
        BulletComponent bulletComponent = bulletM.get(bullet);
        bulletComponent.setHit(true);
    }

    private void damageDistribution(Entity victim, Entity culprit, float damage) {
        PositionComponent victimPosition = positionM.get(victim);
        PositionComponent culpritPosition = positionM.get(culprit);
        float angle = culpritPosition.getCenter().sub(victimPosition.getCenter()).angle() - 90;
        float leftoverDamage = world.getSystem(ShieldSystem.class).hitShields(victim, damage, angle);
        world.getSystem(LifeSystem.class).inflictDamage(victim, leftoverDamage);
    }


    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
