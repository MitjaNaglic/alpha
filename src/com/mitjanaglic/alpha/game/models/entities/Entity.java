package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    private Vector2 position;
    private Vector2 velocity;
    /**
     * width, height texture, should be changed somehow
     */
    private float width;
    private float height;
    /**
     * hitbox
     */
    private Rectangle bounds;

    public Entity(Vector2 position) {
        this.setPosition(position);
        setVelocity(new Vector2());
        bounds = new Rectangle();
    }

    public abstract void update(float delta);

    /**
     * Updata hitbox vsak frame, sprejme velikost boxa
     */
    protected void updateBounds(float width, float height) {
        getBounds().setX(getPosition().x);
        getBounds().setY(getPosition().y);
        getBounds().setWidth(width);
        getBounds().setHeight(height);
    }

    /**
     * @return sredina hitboxa
     */
    public Vector2 getCenter() {
        float x = getBounds().x + getBounds().getWidth() / 2;
        float y = getBounds().y + getBounds().getHeight() / 2;
        return new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    protected void setWidth(float width) {
        this.width = width;
    }

    protected void setHeight(float height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
