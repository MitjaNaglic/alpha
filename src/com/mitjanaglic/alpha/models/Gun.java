package com.mitjanaglic.alpha.models;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.models.entities.Bullet;
import com.mitjanaglic.alpha.models.entities.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 15.3.2013
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class Gun {
    private float gunDamage = 10;
    private float timeBetweenShots = 0.06f;
    private float shootCooldown = 0;
    private Entity owner;

    public Gun(Entity owner) {
        this.owner = owner;
    }

    public Bullet shoot(float angle) {
        if (shootCooldown <= 0) {
            shootCooldown = timeBetweenShots;
            Vector2 gunPos = new Vector2(owner.getPosition().cpy());
            gunPos.x += owner.getWidth() / 2;
            gunPos.y += owner.getHeight() - owner.getHeight() / 5;
            return new Bullet(owner, gunPos, gunDamage, angle);
        } else return null;
    }

    public void update(float delta) {
        if (shootCooldown > 0) {
            shootCooldown -= delta;
        }
    }

}
