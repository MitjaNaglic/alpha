package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 28.3.2013
 * Time: 1:33
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class VelocityComponent extends Component {
    private Vector2 targetVelocity;
    private Vector2 currentVelocity;
    private float acceleration;
    private float speed;
    private float forwardInertia;

    public VelocityComponent(float x, float y, float acceleration, float speed, float forwardInertia) {
        this.targetVelocity = new Vector2(x, y);
        this.currentVelocity = new Vector2();
        this.acceleration = acceleration;
        this.speed = speed;
        this.forwardInertia = forwardInertia;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(Vector2 currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public Vector2 getTargetVelocity() {
        return targetVelocity;
    }

    public void setTargetVelocity(Vector2 targetVelocity) {
        this.targetVelocity = targetVelocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getForwardInertia() {
        return forwardInertia;
    }

    public void setForwardInertia(float forwardInertia) {
        this.forwardInertia = forwardInertia;
    }
}
