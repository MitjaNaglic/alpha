package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 1:08
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class InputComponent extends Component {
    public enum Keys {
        LEFT, RIGHT, UP, DOWN, FIRE
    }

    private Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

    public InputComponent() {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.FIRE, false);
    }

    public Map<Keys, Boolean> getKeys() {
        return keys;
    }

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
}
