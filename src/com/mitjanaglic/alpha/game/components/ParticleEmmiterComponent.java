package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.mitjanaglic.alpha.game.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 19.5.2013
 * Time: 19:21
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ParticleEmmiterComponent extends Component {
    private ParticleEffect effect;

    public ParticleEmmiterComponent(String effectName, float x, float y) {
        this.effect = Assets.getAssetManager().get("data/particles/" + effectName + ".p");
        effect.setPosition(x, y);
        effect.start();
    }

    public ParticleEffect getEffect() {
        return effect;
    }
}
