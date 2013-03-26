package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.entities.components.HitboxComponent;
import com.mitjanaglic.alpha.game.models.entities.components.IComponent;
import com.mitjanaglic.alpha.game.models.entities.components.PositionComponent;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.VelocityComponent;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 13.3.2013
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class Bullet extends Entity {
    private float range = 1000;
    private float speed = 1500;
    private float damage;
    private float angle;
    private Entity owner;
    private Vector2 shotOrigin;
    private boolean despawning = false;
    PositionComponent positionComponent;

    public Bullet(Entity owner, Vector2 position, float damage, float angle) {

        components=new HashMap<String, IComponent>();

        VelocityComponent velocityComponent=new VelocityComponent(0,speed);
        components.put("velocity",velocityComponent);

        positionComponent=new PositionComponent(position, velocityComponent.getVelocity());
        components.put("position", positionComponent);

        setWidth(9f);
        setHeight(33f);

        HitboxComponent hitboxComponent=new HitboxComponent(positionComponent, 9, 9);
        components.put("hitbox", hitboxComponent);


        this.angle = angle;
        velocityComponent.getVelocity().rotate(angle);
        this.owner = owner;
        this.damage = damage;


        shotOrigin = new Vector2(positionComponent.getPosition());
    }


    @Override
    public void update(float delta) {


        if (positionComponent.getPosition().cpy().dst(shotOrigin) >= range) {
            despawning = true;
        }
    }

    public void hit() {
        despawning = true;
    }

    public boolean isDespawning() {
        return despawning;
    }

    public float getDamage() {
        return damage;
    }

    public float getAngle() {
        return angle;
    }

    public Entity getOwner() {
        return owner;
    }
}
