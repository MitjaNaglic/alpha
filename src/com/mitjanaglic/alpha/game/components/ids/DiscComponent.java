package com.mitjanaglic.alpha.game.components.ids;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 3.4.2013
 * Time: 19:02
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class DiscComponent extends Component {
    private float angularVelocity;

    public DiscComponent(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
}
