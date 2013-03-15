package com.mitjanaglic.alpha.models.entities;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.models.Gun;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Entity {

    public enum State {
        IDLE, DYING, MOVING_LEFT, MOVING_RIGHT
    }

    private State state = State.IDLE;
    private float speed = 400f;
    private float forwardInertia = 200f;
    private int lives = 10;
    private Gun gun;


    public Player(Vector2 pos) {
        super(pos);
        setHeight(128);
        setWidth(128);
        updateBounds();
        gun = new Gun(this);
    }


    @Override
    public void update(float delta) {
        getVelocity().y += getForwardInertia();
        getPosition().add(getVelocity().cpy().mul(delta));
        updateBounds();
        gun.update(delta);
        if (immunityCooldown > 0) {
            immunityCooldown -= delta;
        }
    }

    public Bullet shoot() {
        return gun.shoot(0);
    }

    private float immunityTime = 1.1f;
    private float immunityCooldown = 0;

    public void hit() {
        if (immunityCooldown <= 0) {
            immunityCooldown = immunityTime;
            lives--;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public float getForwardInertia() {
        return forwardInertia;
    }

    public void setForwardInertia(float forwardInertia) {
        this.forwardInertia = forwardInertia;
    }

    public int getLives() {
        return lives;
    }
}
