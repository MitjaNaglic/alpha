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
    public enum movementState {
        IDLE, MOVING_LEFT, MOVING_RIGHT, MOVING
    }

    public StateComponent(movementState movementState) {
        this.movementState = movementState;
    }

    public movementState getMovementState() {
        return movementState;
    }

    public void setMovementState(movementState movementState) {
        this.movementState = movementState;
    }

    private movementState movementState;
}
