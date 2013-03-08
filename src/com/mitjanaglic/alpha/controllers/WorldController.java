package com.mitjanaglic.alpha.controllers;

import com.mitjanaglic.alpha.entities.Entity;
import com.mitjanaglic.alpha.entities.Player;
import com.mitjanaglic.alpha.worlds.Space;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class WorldController {
    private Space space;
    private Player player;

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

    ;

    public WorldController(Space space) {
        this.space = space;
        player = space.getPlayer();
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
        player.update(delta);
    }

    private void processInput() {
        if (keys.get(Keys.LEFT)) {
            player.getVelocity().x = -player.getSpeed();

        }
        if (keys.get(Keys.RIGHT)) {
            player.getVelocity().x = player.getSpeed();

        }
        if (keys.get(Keys.UP)) {
            player.getVelocity().y = player.getSpeed();

        }
        if (keys.get(Keys.DOWN)) {
            player.getVelocity().y = -player.getSpeed();

        }

        // need to check if both or none direction are pressed, then Bob is idle
        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
            player.setState(Entity.State.IDLE);
            // acceleration is 0 on the x
            player.getAcceleration().x = 0;
            // horizontal speed is 0
            player.getVelocity().x = 0;
        }
        if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
                (!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {
            player.setState(Entity.State.IDLE);
            // acceleration is 0 on the x
            player.getAcceleration().y = 0;
            // horizontal speed is 0
            player.getVelocity().y = 0;
        }

    }

}
