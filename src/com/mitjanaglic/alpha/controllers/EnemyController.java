package com.mitjanaglic.alpha.controllers;

import com.mitjanaglic.alpha.models.entities.Bullet;
import com.mitjanaglic.alpha.models.entities.EnemyDisc;
import com.mitjanaglic.alpha.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 15.3.2013
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
public class EnemyController {
    private Space space;

    public EnemyController(Space space) {
        this.space = space;
    }

    public void update(float delta) {
        for (EnemyDisc enemy : space.getEnemies()) {
            enemy.update(delta);
            Bullet bullet = enemy.shoot();
            if (bullet != null) {
                space.getEnemyBullets().add(bullet);
            }
        }
    }
}
