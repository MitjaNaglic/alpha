package com.mitjanaglic.alpha.worlds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.models.entities.Player;
import com.mitjanaglic.alpha.models.Level;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Space implements Disposable {
    private Player player;
    private Level level;
    private Vector2 cameraPosition;
    private Vector2 cameraVelocity;

    public Space() {
        CreateTestSpace();
        setCameraPosition(new Vector2(level.getCameraWidth() / 2f, level.getCameraHeight() / 2f));
        setCameraVelocity(new Vector2());
        getCameraVelocity().y = player.getForwardInertia();
    }

    public void getDrawableBackground() {
    }

    public void getDrawableEntities() {

    }

    private void CreateTestSpace() {
        player = new Player(new Vector2(400, 200));
        level = new Level();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void dispose() {
    }

    public Level getLevel() {
        return level;
    }

    public Vector2 getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Vector2 cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public Vector2 getCameraVelocity() {
        return cameraVelocity;
    }

    public void setCameraVelocity(Vector2 cameraVelocity) {
        this.cameraVelocity = cameraVelocity;
    }
}
