package com.mitjanaglic.alpha.game.models.entities;

import com.mitjanaglic.alpha.game.models.entities.components.IComponent;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    /**
     * width, height texture, should be changed somehow
     */
    private float width;
    private float height;

    protected Map<String,IComponent> components;

    public abstract void update(float delta);

    public Map<String, IComponent> getComponents() {
        return components;
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
}
