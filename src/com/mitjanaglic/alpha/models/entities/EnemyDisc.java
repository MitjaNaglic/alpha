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
    private float forwardInertia = 200f;
    private float rotationAngle = 0;
    private float rotationVelocity = 250;

    public EnemyDisc(Vector2 position) {
        super(position);
        setHeight(91);
        setWidth(91);
        updateBounds();
        getVelocity().y = forwardInertia;
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().mul(delta));
        rotate(delta);
        updateBounds();
    }

    private void rotate(float delta) {
        if (getRotationAngle() <= 360) {
            rotationAngle = getRotationAngle() + rotationVelocity * delta;
        } else {
            rotationAngle = 0;
        }
    }

    public float getRotationAngle() {
        return rotationAngle;
    }
}
