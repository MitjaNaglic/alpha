package com.mitjanaglic.alpha.game.models.entities.components.velocity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 25.3.2013
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class VelocityComponent implements IVelocityComponent {
    private Vector2 velocity;
    private float scrollVelocity=200f;

    public VelocityComponent(float x, float y){
        velocity=new Vector2(x,y);
        velocity.y+= getScrollVelocity();
    }

    @Override
    public void update(float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    public float getScrollVelocity() {
        return scrollVelocity;
    }

    public void setScrollVelocity(float scrollVelocity) {
        this.scrollVelocity = scrollVelocity;
    }
}
