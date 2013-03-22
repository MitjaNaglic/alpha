package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.3.2013
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
public abstract class Ship extends Entity {

    private HitMark hitMark;

    public Ship(Vector2 position) {
        super(position);
    }

    /**
     * ne pozabt klicat update iz child classov
     *
     * @param delta
     */
    @Override
    public void update(float delta) {
        //hitmark se rendera če ni null
        if (getHitMark() != null) {
            getHitMark().update(delta);
            if (!getHitMark().isShowing()) hitMark = null;
        }
    }

    public void hit(HitMark hitMark) {
        this.hitMark = hitMark;
    }

    public HitMark getHitMark() {
        return hitMark;
    }

}
