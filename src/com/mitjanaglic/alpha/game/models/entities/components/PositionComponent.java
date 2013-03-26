package com.mitjanaglic.alpha.game.models.entities.components;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 25.3.2013
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class PositionComponent implements IPositionComponent {
    private Vector2 position;
    private Vector2 velocity;
    public PositionComponent(Vector2 position, Vector2 velocity){
        this.position=position;
        this.velocity=velocity;
    }

    @Override
    public void update(float delta) {
        if(velocity!=null){
        position.add(velocity.cpy().scl(delta));
        }
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
