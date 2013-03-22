package com.mitjanaglic.alpha.game.models.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.3.2013
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class HitMark extends Entity {
    private float liveTime = 0.1f;
    private boolean showing = true;

    public HitMark(Vector2 position) {
        super(position);
        setWidth(56);
        setHeight(54);
        updateBounds(getWidth(), getHeight());
        getPosition().x -= getWidth() / 2;       //centering
        getPosition().y -= getHeight() / 2;
    }

    @Override
    public void update(float delta) {
        if (liveTime > 0) {
            liveTime -= delta;
        } else {
            showing = false;
        }
    }

    /**
     * hitmark naj bi bil prikazan dokler showing timer ne poteče, takrat naj bi ga parent objekt odstranil
     */
    public boolean isShowing() {
        return showing;
    }
}
