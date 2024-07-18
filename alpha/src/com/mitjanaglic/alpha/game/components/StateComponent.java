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

    public StateComponent(movementState currentMovementState) {
        this.currentMovementState = currentMovementState;
    }

    public movementState getCurrentMovementState() {
        return currentMovementState;
    }

    public void setCurrentMovementState(movementState currentMovementState) {
        this.currentMovementState = currentMovementState;
    }

    private movementState currentMovementState;
}
