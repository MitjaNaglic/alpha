package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 30.3.2013
 * Time: 1:53
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class GunComponent extends Component {
    private float range = 1000;
    private float bulletSpeed = 1500;
    private float gunDamage = 10;
    private float timeBetweenShots;
    private float shootCooldown = 0;
    private Vector2 gunPosition;
    private float offsetX;
    private float offsetY;
    private boolean shootRequest = false;
    private float defaultAimAngle;
    private float aimAngle = 0;
    private String bulletTextureName;
    private String impactTextureId;
    private String ownerId;

    public GunComponent(Vector2 gunPosition, String bulletTextureName, String impactTextureId,
                        float offsetX, float offsetY, float defaultAimAngle, float fireRate, String ownerId, float damage) {
        this.gunPosition = gunPosition;
        this.bulletTextureName = bulletTextureName;
        this.impactTextureId = impactTextureId;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.defaultAimAngle = defaultAimAngle;
        aimAngle = defaultAimAngle;
        this.timeBetweenShots = 100 / fireRate;
        this.ownerId = ownerId;
        this.gunDamage = damage;

        gunPosition.add(offsetX, offsetY);
    }

    public String getImpactTextureId() {
        return impactTextureId;
    }

    public void setImpactTextureId(String impactTextureId) {
        this.impactTextureId = impactTextureId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getBulletTextureName() {
        return bulletTextureName;
    }

    public void setBulletTextureName(String bulletTextureName) {
        this.bulletTextureName = bulletTextureName;
    }

    public float getAimAngle() {
        return aimAngle;
    }

    public void setAimAngle(float aimAngle) {
        this.aimAngle = aimAngle;
    }

    public void setGunPosition(Vector2 gunPosition) {
        this.gunPosition = gunPosition;
    }

    public Vector2 getGunPosition() {
        return gunPosition;
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

    public void setGunDamage(float gunDamage) {
        this.gunDamage = gunDamage;
    }

    public void setTimeBetweenShots(float timeBetweenShots) {
        this.timeBetweenShots = timeBetweenShots;
    }

    public void setShootCooldown(float shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

    public float getShootCooldown() {
        return shootCooldown;
    }

    public float getTimeBetweenShots() {
        return timeBetweenShots;
    }

    public float getGunDamage() {
        return gunDamage;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public boolean shootRequested() {
        return shootRequest;
    }

    public void setShootRequest(boolean shootRequest) {
        this.shootRequest = shootRequest;
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
}
