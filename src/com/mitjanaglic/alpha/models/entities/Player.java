package com.mitjanaglic.alpha.models.entities;

import com.badlogic.gdx.math.Rectangle;
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
    private float size = 128f;
    private float speed = 400f;
    private float forwardInertia = 200f;
    private float timeBetweenShots = 0.06f;
    private float shootCooldown = 0;

    private Rectangle bounds = new Rectangle();

    public Player(Vector2 pos) {
        super(pos);
        getBounds().height = getSize();
        getBounds().width = getSize();
        updatePosition();
    }

    public Bullet shoot() {
        if (shootCooldown <= 0) {
            shootCooldown = timeBetweenShots;
            Vector2 gunPos = new Vector2(getPosition().cpy());
            gunPos.x += size / 2;
            gunPos.y += size - size / 5;
            return new Bullet(gunPos);
        } else return null;
    }

    @Override
    public void update(float delta) {
        getVelocity().y += getForwardInertia();
        getPosition().add(getVelocity().cpy().mul(delta));
        updatePosition();
        if (shootCooldown > 0) {
            shootCooldown -= delta;
        }
    }

    private void updatePosition() {
        bounds.setX(getPosition().x);
        bounds.setY(getPosition().y);
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

    public float getSize() {
        return size;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
