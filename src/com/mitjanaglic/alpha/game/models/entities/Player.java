package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.entities.components.*;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.SpeedComponent;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.VelocityComponent;
import com.mitjanaglic.alpha.game.models.worlds.Space;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Ship {
    private float speed = 400f;
    private int lives = 10;
    public Player(Space space, Vector2 pos) {
        components=new HashMap<String, IComponent>();

        StateComponent stateComponent=new StateComponent();
        components.put("state", stateComponent);

        VelocityComponent velocityComponent=new VelocityComponent(0,0);
        components.put("velocity",velocityComponent);

        SpeedComponent speedComponent=new SpeedComponent(speed);
        components.put("speed",speedComponent);

        PositionComponent positionComponent=new PositionComponent(pos, velocityComponent.getVelocity());
        components.put("position", positionComponent);

        setHeight(75);
        setWidth(99);

        HitboxComponent hitboxComponent=new HitboxComponent(positionComponent, getWidth(), getHeight());
        components.put("hitbox", hitboxComponent);

        GunComponent gunComponent=new GunComponent(this, getWidth() / 2, getHeight() / 2 + getHeight() / 5);
        components.put("gun", gunComponent);

        InputComponent inputComponent=new InputComponent(this, space);
        components.put("input", inputComponent);
    }


    @Override
    public void update(float delta) {
        for (IComponent component:components.values()){
            component.update(delta);
        }

        if (immunityCooldown > 0) {
            immunityCooldown -= delta;
        }
        super.update(delta);
    }

    private float immunityTime = 1.1f;
    private float immunityCooldown = 0;

    public void hit(HitMark hitMark) {
        super.hit(hitMark);
        if (immunityCooldown <= 0) {
            immunityCooldown = immunityTime;
            lives--;
        }
    }

    public int getLives() {
        return lives;
    }
}
