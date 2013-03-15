package com.mitjanaglic.alpha.models.entities;

import com.badlogic.gdx.math.Vector2;

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
    private float timeBetweenShots = 0.06f;
    private float shootCooldown = 0;
    private int lives = 10;
    private float gunDamage = 10;


    public Player(Vector2 pos) {
        super(pos);
        setHeight(128);
        setWidth(128);
        updateBounds();
    }

    public Bullet shoot() {
        if (shootCooldown <= 0) {
            shootCooldown = timeBetweenShots;
            Vector2 gunPos = new Vector2(getPosition().cpy());
            gunPos.x += getWidth() / 2;
            gunPos.y += getHeight() - getHeight() / 5;
            return new Bullet(gunPos, gunDamage);
        } else return null;
    }

    @Override
    public void update(float delta) {
        getVelocity().y += getForwardInertia();
        getPosition().add(getVelocity().cpy().mul(delta));
        updateBounds();
        if (shootCooldown > 0) {
            shootCooldown -= delta;
        }
        if (immunityCooldown > 0) {
            immunityCooldown -= delta;
        }
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
