package com.mitjanaglic.alpha.game.controllers;

import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Disc;
import com.mitjanaglic.alpha.game.models.entities.HitMark;
import com.mitjanaglic.alpha.game.models.entities.components.HitboxComponent;
import com.mitjanaglic.alpha.game.models.entities.components.PositionComponent;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.VelocityComponent;
import com.mitjanaglic.alpha.game.models.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 15.3.2013
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class SpaceController {
    private Space space;

    public SpaceController(Space space) {
        this.space = space;
    }

    public void update(float delta) {
        checkPlayerBulletCollisions();
        checkEnemyBulletCollisions();
        entityCollisions();
        checkLevelBoundCollision();
    }

    private void checkPlayerBulletCollisions() {
        HitboxComponent enemyHitboxComponent;
        HitboxComponent bulletHitboxComponent;
        for (Disc enemy : space.getEnemies()) {
            enemyHitboxComponent= (HitboxComponent) enemy.getComponents().get("hitbox");
            for (Bullet bullet : space.getBullets()) {
                bulletHitboxComponent=(HitboxComponent) bullet.getComponents().get("hitbox");
                if (enemyHitboxComponent.getHitbox().overlaps(bulletHitboxComponent.getHitbox())) {
                    PositionComponent positionComponent=(PositionComponent)bullet.getComponents().get("position");
                    enemy.hit(bullet.getDamage(), new HitMark(positionComponent.getPosition().cpy()));
                    bullet.hit();
                }
            }
        }
    }

    private void checkEnemyBulletCollisions() {
        HitboxComponent playerHitboxComponent=(HitboxComponent) space.getPlayer().getComponents().get("hitbox");
        HitboxComponent bulletHitboxComponent;
        for (Bullet bullet : space.getEnemyBullets()) {
            bulletHitboxComponent=(HitboxComponent) bullet.getComponents().get("hitbox");
            if (playerHitboxComponent.getHitbox().overlaps(bulletHitboxComponent.getHitbox())) {
                PositionComponent positionComponent= (PositionComponent) bullet.getComponents().get("position");
                space.getPlayer().hit(new HitMark(positionComponent.getPosition().cpy()));
                bullet.hit();
            }
        }
    }

    private void entityCollisions() {
        HitboxComponent hitboxComponent= (HitboxComponent) space.getPlayer().getComponents().get("hitbox");
        for (Disc enemy : space.getEnemies()) {
            HitboxComponent enemyHitboxComponent= (HitboxComponent) enemy.getComponents().get("hitbox");
            if (hitboxComponent.getHitbox().overlaps(enemyHitboxComponent.getHitbox())) {
                space.getPlayer().hit(null);
            }
        }
    }

    private void checkLevelBoundCollision() {
        HitboxComponent hitboxComponent= (HitboxComponent) space.getPlayer().getComponents().get("hitbox");
        VelocityComponent velocityComponent=(VelocityComponent) space.getPlayer().getComponents().get("velocity");
        PositionComponent positionComponent=(PositionComponent) space.getPlayer().getComponents().get("position");
        if (velocityComponent.getVelocity().x < 0) {
            if (positionComponent.getPosition().x <= 0) {
                velocityComponent.getVelocity().x = 0;
            }
        }
        if (velocityComponent.getVelocity().x > 0) {
            if (positionComponent.getPosition().x + hitboxComponent.getHitbox().getWidth() >= space.getLevel().getWidth()) {
                velocityComponent.getVelocity().x = 0;
            }
        }
        if (velocityComponent.getVelocity().y < 0) {
            if (positionComponent.getPosition().y <= space.getCameraPosition().y - space.getLevel().getCameraHeight() / 2) {
                velocityComponent.getVelocity().y = 0;
            }
        }
        if (velocityComponent.getVelocity().y > 0) {
            if (positionComponent.getPosition().y + hitboxComponent.getHitbox().getHeight() >= space.getCameraPosition().y + space.getLevel().getCameraHeight() / 2) {
                velocityComponent.getVelocity().y = 0;
            }
        }
    }

}
