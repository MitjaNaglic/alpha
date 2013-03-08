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
    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();
    private State state = State.IDLE;
    private float size = 128f;
    private float speed = 400f;
    private float forwardInertia = 200f;
    private Rectangle bounds = new Rectangle();

    public Player(Vector2 pos) {
        super(pos);
        getBounds().height = getSize();
        getBounds().width = getSize();
        updatePosition();
    }

    @Override
    public void update(float delta) {
        velocity.y += getForwardInertia();
        getPosition().add(velocity.cpy().mul(delta));
        updatePosition();
    }

    private void updatePosition() {
        bounds.setX(getPosition().x);
        bounds.setY(getPosition().y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
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
