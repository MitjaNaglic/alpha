package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.4.2013
 * Time: 16:45
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ShieldComponent extends Component {
    private float maxShields;
    private float currentShields;
    private boolean requestFlare = false;
    private float angle = 0;
    private final float timeBeforeRegen = 1.5f;
    private float timeSinceLastHit = 0;

    public ShieldComponent(float maxShields) {
        this.maxShields = maxShields;
        this.currentShields = maxShields;
    }

    public float getTimeSinceLastHit() {
        return timeSinceLastHit;
    }

    public void setTimeSinceLastHit(float timeSinceLastHit) {
        this.timeSinceLastHit = timeSinceLastHit;
    }

    public float getTimeBeforeRegen() {
        return timeBeforeRegen;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isRequestFlare() {
        return requestFlare;
    }

    public void setRequestFlare(boolean requestFlare) {
        this.requestFlare = requestFlare;
    }

    public float getMaxShields() {
        return maxShields;
    }

    public void setMaxShields(float maxShields) {
        this.maxShields = maxShields;
    }

    public float getCurrentShields() {
        return currentShields;
    }

    public void setCurrentShields(float currentShields) {
        this.currentShields = currentShields;
    }
}
