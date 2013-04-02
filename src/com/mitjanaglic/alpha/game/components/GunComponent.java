package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

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
    private float timeBetweenShots = 0.1f;
    private float shootCooldown = 0;
    private float positionX;
    private float positionY;
    private boolean shootRequest = false;

    public GunComponent(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
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

    public float getPositionX() {
        return positionX;
    }

    public boolean shootRequested() {
        return shootRequest;
    }

    public void setShootRequest(boolean shootRequest) {
        this.shootRequest = shootRequest;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }
}
