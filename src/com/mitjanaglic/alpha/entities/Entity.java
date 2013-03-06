package com.mitjanaglic.alpha.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity implements Disposable {
    private Texture texture;
    private Vector2 position;

    public enum State {
        IDLE, DYING
    }


    public Entity() {

    }

    public Entity(Texture texture, Vector2 position) {
        this.setTexture(texture);
        this.setPosition(position);
    }

    public abstract void Draw();

    public abstract void Update();

    @Override
    public void dispose() {
        if (getTexture() != null) getTexture().dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
