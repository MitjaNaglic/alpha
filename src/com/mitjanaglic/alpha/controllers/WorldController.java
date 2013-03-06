package com.mitjanaglic.alpha.controllers;

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

    static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

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
}
