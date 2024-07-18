package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.4.2013
 * Time: 12:34
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class LifeComponent extends Component {
    private float maxLife;
    private float currentLife;
    private float damage = 0;

    public LifeComponent(float maxLife) {
        this.maxLife = maxLife;
        currentLife = this.maxLife;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void inflictDamage(float damage) {
        this.damage += damage;
        currentLife -= damage;
    }

    public float getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(float maxLife) {
        this.maxLife = maxLife;
    }

    public float getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(float currentLife) {
        this.currentLife = currentLife;
    }
}
