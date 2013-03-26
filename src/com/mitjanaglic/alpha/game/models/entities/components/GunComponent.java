package com.mitjanaglic.alpha.game.models.entities.components;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 25.3.2013
 * Time: 20:43
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class GunComponent implements IComponent{
    private float gunDamage = 10;
    private float timeBetweenShots = 0.1f;
    private float shootCooldown = 0;
    private Entity owner;
    float offsetX;
    float offsetY;

    public GunComponent(Entity owner, float offsetX, float offsetY) {
        this.owner = owner;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Bullet shoot(float angle) {
        if (shootCooldown <= 0) {
            shootCooldown = timeBetweenShots;
            PositionComponent ownerPosition= (PositionComponent) owner.getComponents().get("position");
            Vector2 gunPos = new Vector2(ownerPosition.getPosition().cpy());
            gunPos.x += offsetX;
            gunPos.y += offsetY;
            return new Bullet(owner, gunPos, gunDamage, angle);
        } else return null;
    }

    @Override
    public void update(float delta) {
        if (shootCooldown > 0) {
            shootCooldown -= delta;
        }
    }

}
