package com.mitjanaglic.alpha.worlds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.entities.Player;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Space implements Disposable {
    private Player player;

    public Space() {
        CreateTestSpace();
    }

    private void CreateTestSpace() {
        player = new Player(new Vector2(400, 200));
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void dispose() {
    }
}
