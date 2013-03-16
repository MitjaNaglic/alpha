package com.mitjanaglic.alpha.models.entities;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.models.Gun;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 14.3.2013
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class EnemyDisc extends Entity {
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        IDLE, DYING
    }

    private float forwardInertia = 200f;
    private float rotationAngle = 0;
    private float rotationVelocity = 250;
    private float health = 100;
    private State state = State.IDLE;
    private Player player;
    private Gun gun;

    public EnemyDisc(Vector2 position, Player player) {
        super(position);
        this.player = player;
        setHeight(91);
        setWidth(91);
        updateBounds(getWidth(), getHeight());
        gun = new Gun(this, getWidth() / 2, getHeight() / 2 - getHeight() / 5);
        getVelocity().y = forwardInertia;
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().mul(delta));
        rotate(delta);
        updateBounds(getWidth(), getHeight());
        gun.update(delta);
    }

    public Bullet shoot() {

        return gun.shoot(player.getCenter().sub(this.getCenter()).angle() - 90); //smer playerja
    }

    public void hit(float damage) {
        health -= damage;
        if (health <= 0) {
            setState(State.DYING);
        }
    }

    private void rotate(float delta) {
        if (getRotationAngle() <= 360) {
            rotationAngle = getRotationAngle() + rotationVelocity * delta;
        } else {
            rotationAngle = 0;
        }
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public float getHealth() {
        return health;
    }
}
