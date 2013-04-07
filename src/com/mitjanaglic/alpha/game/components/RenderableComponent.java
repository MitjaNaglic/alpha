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
    private float r = 1;
    private float g = 1;
    private float b = 1;
    private float a = 1;

    public RenderableComponent(String textureName, float scaleX, float scaleY, float rotationAngle) {
        this.spriteTextureName = textureName;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotationAngle = rotationAngle;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
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
