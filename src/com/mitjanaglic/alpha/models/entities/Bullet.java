package com.mitjanaglic.alpha.models.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 13.3.2013
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class Bullet extends Entity {
    private float range = 150;
    private float speed = 700;
    private float damage;
    private float angle;
    private Entity owner;


    private Vector2 rangeRemaning = new Vector2(0, 0);
    private boolean despawning = false;

    public Bullet(Entity owner, Vector2 position, float damage, float angle) {
        super(position);
        getVelocity().y = speed;
        this.angle = angle;
        getVelocity().rotate(angle);
        this.owner = owner;
        this.damage = damage;
        setWidth(9f);
        setHeight(33f);
        updateBounds();
    }


    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().mul(delta));//TODO fix bullet range, angle
        rangeRemaning.add(getVelocity().cpy().mul(delta));  //decrement rangeremaning
        updateBounds();
        if (rangeRemaning.x >= range || rangeRemaning.y >= range) {
            despawning = true;
            System.out.println("bullet despawning");
        }
    }

    public void hit() {
        despawning = true;
    }

    public boolean isDespawning() {
        return despawning;
    }

    public float getDamage() {
        return damage;
    }

    public float getAngle() {
        return angle;
    }

    public Entity getOwner() {
        return owner;
    }
}
