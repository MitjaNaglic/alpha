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
    private float range = 1000;
    private float speed = 800;
    private float damage;


    private float rangeRemaning = range;
    private boolean despawning = false;

    public Bullet(Vector2 position, float damage) {
        super(position);
        getVelocity().y = speed;
        this.damage = damage;
        setWidth(9f);
        setHeight(33f);
        updateBounds();
    }


    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().mul(delta));
        rangeRemaning -= getVelocity().cpy().mul(delta).y;  //decrement rangeremaning
        updateBounds();
        if (rangeRemaning <= 0) despawning = true;
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
}
