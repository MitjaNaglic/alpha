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
    private Vector2 velocity;

    public VelocityComponent(float x, float y) {
        this.velocity = new Vector2(x, y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
