package com.mitjanaglic.alpha.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    private Vector2 position;

    public enum State {
        IDLE, DYING
    }


    public Entity() {

    }

    public Entity(Vector2 position) {
        this.setPosition(position);
    }

    public abstract void Update();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}