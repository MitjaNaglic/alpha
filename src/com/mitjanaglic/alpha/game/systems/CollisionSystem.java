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
import com.mitjanaglic.alpha.game.components.HitboxComponent;
import com.mitjanaglic.alpha.game.components.LifeComponent;
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
    private ComponentMapper<HitboxComponent> hitboxM;
    @Mapper
    private ComponentMapper<BulletComponent> bulletM;
    @Mapper
    private ComponentMapper<LifeComponent> lifeM;


    public CollisionSystem() {
        super(Aspect.getAspectForAll(HitboxComponent.class));
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
            HitboxComponent playerhitbox = hitboxM.get(player);
            for (int i = 0; i < enemies.size(); i++) {
                HitboxComponent enemyHitbox = hitboxM.get(enemies.get(i));
                if (playerhitbox.getHitbox().overlaps(enemyHitbox.getHitbox())) {
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
                    HitboxComponent playerBulletHitbox = hitboxM.get(playerBullets.get(i));
                    HitboxComponent enemyHitbox = hitboxM.get(enemies.get(j));

                    if (playerBulletHitbox.getHitbox().overlaps(enemyHitbox.getHitbox())) {
                        handleCollision(enemies.get(j), playerBullets.get(i));
                        handleBulletCollision(playerBullets.get(i));
                    }
                }
            }
        }
    }

    private void enemyBulletCollsiions(Entity player, ImmutableBag<Entity> enemyBullets) {
        if (player != null && enemyBullets != null) {
            HitboxComponent playerHitbox = hitboxM.get(player);
            for (int i = 0; i < enemyBullets.size(); i++) {
                HitboxComponent enemyBulletHitbox = hitboxM.get(enemyBullets.get(i));

                if (enemyBulletHitbox.getHitbox().overlaps(playerHitbox.getHitbox())) {
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
        HitboxComponent victimHitbox = hitboxM.get(victim);
        HitboxComponent culpritHitbox = hitboxM.get(culprit);
        float angle = culpritHitbox.getCenter().sub(victimHitbox.getCenter()).angle() - 90;
        float leftoverDamage = world.getSystem(ShieldSystem.class).hitShields(victim, damage, angle);
        world.getSystem(LifeSystem.class).inflictDamage(victim, leftoverDamage);
    }


    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
