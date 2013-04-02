package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 1:41
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class SpeedComponent extends Component {
    private float speed;
    private float forwardInertia;

    public SpeedComponent(float speed, float forwardInertia) {
        this.speed = speed;
        this.forwardInertia = forwardInertia;
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
