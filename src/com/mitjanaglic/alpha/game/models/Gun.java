package com.mitjanaglic.alpha.game.models;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 15.3.2013
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class Gun {
    private float gunDamage = 10;
    private float timeBetweenShots = 0.1f;
    private float shootCooldown = 0;
    private Entity owner;
    float offsetX;
    float offsetY;

    public Gun(Entity owner, float offsetX, float offsetY) {
        this.owner = owner;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Bullet shoot(float angle) {
        if (shootCooldown <= 0) {
            shootCooldown = timeBetweenShots;
            Vector2 gunPos = new Vector2(owner.getPosition().cpy());
            gunPos.x += offsetX;
            gunPos.y += offsetY;
            return new Bullet(owner, gunPos, gunDamage, angle);
        } else return null;
    }

    public void update(float delta) {
        if (shootCooldown > 0) {
            shootCooldown -= delta;
        }
    }

}
