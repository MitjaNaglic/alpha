package com.mitjanaglic.alpha.models.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 14.3.2013
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class EnemyDisc extends Entity {
    public EnemyDisc(Vector2 position) {
        super(position);
        setHeight(91);
        setWidth(91);
    }

    @Override
    public void update(float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
