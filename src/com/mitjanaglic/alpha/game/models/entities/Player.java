package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.Gun;

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
        setHeight(75);
        setWidth(99);
        updateBounds(getWidth(), getHeight());
        gun = new Gun(this, getWidth() / 2, getHeight() / 2 + getHeight() / 5);
    }


    @Override
    public void update(float delta) {
        getVelocity().y += getForwardInertia();
        getPosition().add(getVelocity().cpy().mul(delta));
        updateBounds(getWidth(), getHeight());
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
