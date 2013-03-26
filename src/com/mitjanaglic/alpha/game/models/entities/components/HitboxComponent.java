package com.mitjanaglic.alpha.game.models.entities.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 25.3.2013
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class HitboxComponent implements IComponent {
    private Rectangle hitbox;
    private IPositionComponent positionComponent;
    private float width;
    private float height;
    public HitboxComponent(IPositionComponent positionComponent, float width, float height){
        this.positionComponent=positionComponent;
        hitbox = new Rectangle();
        this.setWidth(width);
        this.setHeight(height);
        updateHitbox();
    }

    @Override
    public void update(float delta){
        updateHitbox();
    }

    /**
     * Updata hitbox vsak frame, sprejme velikost boxa
     */
    private void updateHitbox() {
        hitbox.setX(positionComponent.getPosition().x);
        hitbox.setY(positionComponent.getPosition().y);
        hitbox.setWidth(getWidth());
        hitbox.setHeight(getHeight());
    }

    /**
     * @return sredina hitboxa
     */
    public Vector2 getCenter() {
        float x = getHitbox().x + getHitbox().getWidth() / 2;
        float y = getHitbox().y + getHitbox().getHeight() / 2;
        return new Vector2(x, y);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * WARNING: returns value before update, use getHitbox().getWidth for current hitbox width
     * @return
     */
    public float getWidth() {
        return width;
    }
    /**
     * WARNING: returns value before update, use getHitbox().getHeight for current hitbox height
     * @return
     */
    public float getHeight() {
        return height;
    }
}
