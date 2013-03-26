package com.mitjanaglic.alpha.game.models.entities.components;

import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Entity;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.SpeedComponent;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.VelocityComponent;
import com.mitjanaglic.alpha.game.models.worlds.Space;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 25.3.2013
 * Time: 21:03
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class InputComponent implements IComponent {
    private Space space;
    private Entity entity;
    private VelocityComponent velocityComponent;
    private GunComponent gunComponent;
    private SpeedComponent speedComponent;
    private StateComponent stateComponent;
    public enum Keys {
        LEFT, RIGHT, UP, DOWN, FIRE
    }

    private static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.FIRE, false);
    }


    public InputComponent(Entity entity, Space space) {
        this.space = space;
        this.entity=entity;
        velocityComponent= (VelocityComponent) entity.getComponents().get("velocity");
        gunComponent= (GunComponent) entity.getComponents().get("gun");
        speedComponent= (SpeedComponent) entity.getComponents().get("speed");
        stateComponent= (StateComponent) entity.getComponents().get("state");
    }

    //pressed
    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void upPressed() {
        keys.get(keys.put(Keys.UP, true));
    }

    public void downPressed() {
        keys.get(keys.put(Keys.DOWN, true));
    }

    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, true));
    }

    //released
    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    public void upReleased() {
        keys.get(keys.put(Keys.UP, false));
    }

    public void downReleased() {
        keys.get(keys.put(Keys.DOWN, false));
    }

    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
    }


    /**
     * The main update method *
     */
    public void update(float delta) {
        processInput();

    }

    private void processInput() {

        if (keys.get(Keys.LEFT)) {
            velocityComponent.getVelocity().x = -speedComponent.getSpeed();
            stateComponent.setState(StateComponent.State.MOVING_LEFT);

        }
        if (keys.get(Keys.RIGHT)) {
            velocityComponent.getVelocity().x = speedComponent.getSpeed();
            stateComponent.setState(StateComponent.State.MOVING_RIGHT);

        }
        if (keys.get(Keys.UP)) {
            velocityComponent.getVelocity().y = speedComponent.getSpeed();

        }
        if (keys.get(Keys.DOWN)) {
            velocityComponent.getVelocity().y = -speedComponent.getSpeed();

        }
        if (keys.get(Keys.FIRE)) {
            Bullet bullet = gunComponent.shoot(0);
            if (bullet != null) {
                space.getBullets().add(bullet);
            }
        }

        // need to check if both or none direction are pressed, then is idle
        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
            stateComponent.setState(StateComponent.State.IDLE);

            // horizontal speed is 0
            velocityComponent.getVelocity().x = 0;
        }
        if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
                (!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {

            // horizontal speed is 0
            velocityComponent.getVelocity().y = 0;
        }


    }
}
