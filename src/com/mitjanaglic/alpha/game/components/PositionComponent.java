package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 28.3.2013
 * Time: 1:30
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class PositionComponent extends Component {
    private Vector2 position;

    public PositionComponent(float x, float y) {
        this.position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


}
