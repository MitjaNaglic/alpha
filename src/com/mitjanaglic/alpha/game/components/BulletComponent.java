package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 18:50
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class BulletComponent extends Component {
    private float range;
    private float bulletSpeed;
    private float damage;
    private Vector2 shotOrigin;
    private boolean hit = false;
    private String impactTextureId;

    public BulletComponent(Vector2 shotOrigin, String impactTextureId, float range, float bulletSpeed, float damage) {
        this.shotOrigin = shotOrigin.cpy();
        this.impactTextureId = impactTextureId;
        this.range = range;
        this.bulletSpeed = bulletSpeed;
        this.damage = damage;
    }

    public String getImpactTextureId() {
        return impactTextureId;
    }

    public void setImpactTextureId(String impactTextureId) {
        this.impactTextureId = impactTextureId;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public Vector2 getShotOrigin() {
        return shotOrigin;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
