package com.mitjanaglic.alpha.game.components;


import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.4.2013
 * Time: 7:56
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class HitmarkComponent extends Component {
    private float lifeSpan = 0.1f;
    private float currentLifetime = 0;

    public float getCurrentLifetime() {
        return currentLifetime;
    }

    public void setCurrentLifetime(float currentLifetime) {
        this.currentLifetime = currentLifetime;
    }

    public float getLifeSpan() {
        return lifeSpan;
    }


}
