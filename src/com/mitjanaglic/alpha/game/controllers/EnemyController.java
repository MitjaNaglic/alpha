package com.mitjanaglic.alpha.game.controllers;

import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Entity;
import com.mitjanaglic.alpha.game.models.entities.components.GunComponent;
import com.mitjanaglic.alpha.game.models.entities.components.HitboxComponent;
import com.mitjanaglic.alpha.game.models.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 15.3.2013
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
public class EnemyController {
    private Space space;
    private GunComponent gunComponent;

    public EnemyController(Space space) {
        this.space = space;
    }

    public void update(float delta) {
        for (Entity enemy : space.getEnemies()) {
            gunComponent=(GunComponent) enemy.getComponents().get("gun");//TODO to se pomakne v AI component
            HitboxComponent enemyHitbox= (HitboxComponent) enemy.getComponents().get("hitbox");
            HitboxComponent playerHitbox= (HitboxComponent) space.getPlayer().getComponents().get("hitbox");

            enemy.update(delta);
            Bullet bullet = gunComponent.shoot(playerHitbox.getCenter().sub(enemyHitbox.getCenter()).angle() - 90);
            if (bullet != null) {
                space.getEnemyBullets().add(bullet);
            }
        }
    }
}
