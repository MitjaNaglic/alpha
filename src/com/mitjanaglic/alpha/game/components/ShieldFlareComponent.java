package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.4.2013
 * Time: 18:15
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ShieldFlareComponent extends Component {
    private float flareDuration = 1.3f;
    private float currentFlareDuration = 0f;

    public float getFlareDuration() {
        return flareDuration;
    }

    public float getCurrentFlareDuration() {
        return currentFlareDuration;
    }

    public void setCurrentFlareDuration(float currentFlareDuration) {
        this.currentFlareDuration = currentFlareDuration;
    }
}
