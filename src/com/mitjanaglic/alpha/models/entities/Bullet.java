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
    private float width = 9f;
    private float height = 33f;
    private Vector2 velocity;
    private float rangeRemaning = range;
    private boolean despawning = false;

    public Bullet(Vector2 position) {
        super(position);
        velocity = new Vector2();
        velocity.y = speed;
    }


    @Override
    public void update(float delta) {
        getPosition().add(velocity.cpy().mul(delta));
        rangeRemaning -= velocity.cpy().mul(delta).y;  //decrement rangeremaning
        if (rangeRemaning <= 0) despawning = true;
    }

    public boolean isDespawning() {
        return despawning;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
