package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.entities.components.GunComponent;
import com.mitjanaglic.alpha.game.models.entities.components.HitboxComponent;
import com.mitjanaglic.alpha.game.models.entities.components.IComponent;
import com.mitjanaglic.alpha.game.models.entities.components.PositionComponent;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.VelocityComponent;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 14.3.2013
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class Disc extends Ship {
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        IDLE, DYING
    }

    private float rotationAngle = 0;
    private float rotationVelocity = 250;
    private float health = 100;
    private State state = State.IDLE;
    private Player player;
    private HitboxComponent hitboxComponent;

    public Disc(Vector2 position, Player player) {
        this.player = player;

        components=new HashMap<String, IComponent>();

        VelocityComponent velocityComponent=new VelocityComponent(0,0);
        components.put("velocity",velocityComponent);

        PositionComponent positionComponent=new PositionComponent(position, velocityComponent.getVelocity());
        components.put("position", positionComponent);

        setHeight(91);
        setWidth(91);

        hitboxComponent=new HitboxComponent(positionComponent, getWidth(), getHeight());
        components.put("hitbox", hitboxComponent);

        GunComponent gunComponent=new GunComponent(this, getWidth() / 2, getHeight() / 2 - getHeight() / 5);
        components.put("gun", gunComponent);
    }

    @Override
    public void update(float delta) {
        for (IComponent component:components.values()){
            component.update(delta);
        }

        //getPosition().add(getVelocity().cpy().mul(delta));
        rotate(delta);
    }

    public void hit(float damage, HitMark hitMark) {
        super.hit(hitMark);
        health -= damage;
        if (health <= 0) {
            setState(State.DYING);
        }
    }

    private void rotate(float delta) {
        if (getRotationAngle() <= 360) {
            rotationAngle = getRotationAngle() + rotationVelocity * delta;
        } else {
            rotationAngle = 0;
        }
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public float getHealth() {
        return health;
    }
}
