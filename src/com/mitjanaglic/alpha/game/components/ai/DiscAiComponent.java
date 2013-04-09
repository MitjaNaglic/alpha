package com.mitjanaglic.alpha.game.components.ai;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 1:19
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class DiscAiComponent extends Component {
    private Vector2 waypoint;

    public Vector2 getWaypoint() {
        return waypoint;
    }

    public void setWaypoint(Vector2 waypoint) {
        this.waypoint = waypoint;
    }
}
