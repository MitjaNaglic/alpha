package com.mitjanaglic.alpha.game.controllers;

import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.EnemyDisc;
import com.mitjanaglic.alpha.game.models.entities.HitMark;
import com.mitjanaglic.alpha.game.worlds.Space;

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
    }

    private void checkPlayerBulletCollisions() {
        for (EnemyDisc enemy : space.getEnemies()) {
            for (Bullet bullet : space.getBullets()) {
                if (enemy.getBounds().overlaps(bullet.getBounds())) {
                    enemy.hit(bullet.getDamage(), new HitMark(bullet.getPosition().cpy()));
                    bullet.hit();
                }
            }
        }
    }

    private void checkEnemyBulletCollisions() {
        for (Bullet bullet : space.getEnemyBullets()) {
            if (space.getPlayer().getBounds().overlaps(bullet.getBounds())) {
                space.getPlayer().hit(new HitMark(bullet.getPosition().cpy()));
                bullet.hit();
            }
        }
    }

}
