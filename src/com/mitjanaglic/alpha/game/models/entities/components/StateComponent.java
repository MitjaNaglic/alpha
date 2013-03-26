package com.mitjanaglic.alpha.game.models.entities.components;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 26.3.2013
 * Time: 0:43
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class StateComponent implements IComponent {
    public enum State {
        IDLE, DYING, MOVING_LEFT, MOVING_RIGHT
    }

    public StateComponent(){
        state=State.IDLE;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;

    @Override
    public void update(float delta) {

    }
}
