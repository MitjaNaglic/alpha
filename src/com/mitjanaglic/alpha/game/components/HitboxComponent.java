package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 0:48
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class HitboxComponent extends Component {
    private Rectangle hitbox;

    public HitboxComponent(float x, float y, float width, float height) {
        this.hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * @return sredina hitboxa
     */
    public Vector2 getCenter() {
        float x = getHitbox().x + getHitbox().getWidth() / 2;
        float y = getHitbox().y + getHitbox().getHeight() / 2;
        return new Vector2(x, y);
    }
}
