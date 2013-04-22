package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 15:14
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class RenderableComponent extends Component {
    private String spriteTextureName;
    private float scaleX;
    private float scaleY;
    private float rotationAngle;
    private Color color;
    private float offsetX = 0;
    private float offsetY = 0;

    public RenderableComponent(String textureName, float scaleX, float scaleY, float rotationAngle) {
        this.spriteTextureName = textureName;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotationAngle = rotationAngle;
        color = new Color(1, 1, 1, 1);
    }

    public RenderableComponent(String spriteTextureName, float scaleX, float scaleY, float rotationAngle, float offsetX, float offsetY) {
        this.spriteTextureName = spriteTextureName;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotationAngle = rotationAngle;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        color = new Color(1, 1, 1, 1);
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSpriteTextureName() {
        return spriteTextureName;
    }

    public void setSpriteTextureName(String spriteTextureName) {
        this.spriteTextureName = spriteTextureName;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
