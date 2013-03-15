package com.mitjanaglic.alpha.controllers;

import com.mitjanaglic.alpha.models.entities.Bullet;
import com.mitjanaglic.alpha.models.entities.EnemyDisc;
import com.mitjanaglic.alpha.worlds.Space;

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
    }

    private void checkPlayerBulletCollisions() {
        for (EnemyDisc enemy : space.getEnemies()) {
            for (Bullet bullet : space.getBullets()) {
                if (enemy.getBounds().overlaps(bullet.getBounds())) {
                    enemy.hit(bullet.getDamage());
                    bullet.hit();
                }
            }
        }
    }

}
