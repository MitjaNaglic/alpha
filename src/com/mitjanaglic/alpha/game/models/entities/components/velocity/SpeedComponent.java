package com.mitjanaglic.alpha.game.models.entities.components.velocity;

import com.mitjanaglic.alpha.game.models.entities.components.IComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 26.3.2013
 * Time: 0:33
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class SpeedComponent implements IComponent {
    private float speed;

    public SpeedComponent(float speed){
        this.speed=speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void update(float delta) {

    }
}
