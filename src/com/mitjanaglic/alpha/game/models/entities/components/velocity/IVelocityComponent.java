package com.mitjanaglic.alpha.game.models.entities.components.velocity;

import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.models.entities.components.IComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 25.3.2013
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public interface IVelocityComponent extends IComponent {
    public Vector2 getVelocity();
}
