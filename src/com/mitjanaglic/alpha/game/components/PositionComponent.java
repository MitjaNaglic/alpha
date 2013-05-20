package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 28.3.2013
 * Time: 1:30
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class PositionComponent extends Component {
    private Vector2 position;
    private boolean isCollidable;

    public PositionComponent(Vector2 position, float width, float height, boolean isCollidable) {
        this.position = position;
        this.hitbox = new Rectangle(position.x, position.y, width, height);
        this.isCollidable = isCollidable;
    }

    public Vector2 getPosition() {
        return position;
    }

    private Rectangle hitbox;

    public PositionComponent(float x, float y, float width, float height, boolean isCollidable) {
        this.position = new Vector2(x, y);
        this.hitbox = new Rectangle(x, y, width, height);
        this.isCollidable = isCollidable;
    }

    public boolean isCollidable() {
        return isCollidable;
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
