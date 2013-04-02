package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 1:34
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class StateComponent extends Component {
    public enum State {
        IDLE, DYING, MOVING_LEFT, MOVING_RIGHT, MOVING, DESPAWNING
    }

    public StateComponent(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;
}
