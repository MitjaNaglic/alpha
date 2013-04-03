package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

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

    public RenderableComponent(String textureName, float scaleX, float scaleY, float rotationAngle) {
        this.spriteTextureName = textureName;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotationAngle = rotationAngle;
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
