package com.mitjanaglic.alpha.game.models.worlds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.models.Level;
import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Disc;
import com.mitjanaglic.alpha.game.models.entities.Player;
import com.mitjanaglic.alpha.game.models.entities.components.velocity.VelocityComponent;

import java.util.LinkedList;

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
    private AssetManager assetManager;
    private LinkedList<Bullet> enemyBullets = new LinkedList<Bullet>();
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<Disc> enemies = new LinkedList<Disc>();
    private TiledMap currentMap;

    public Space(AssetManager assetManager) {
        this.assetManager = assetManager;
        CreateTestSpace();
//        setCameraPosition(new Vector2(level.getCameraWidth() / 2f, level.getCameraHeight() / 2f));
        setCameraVelocity(new Vector2());
        VelocityComponent velocityComponent = (VelocityComponent) player.getComponents().get("velocity");
        getCameraVelocity().y = velocityComponent.getScrollVelocity();
        getEnemies().add(new Disc(getCameraPosition().cpy(), player));
    }

    public void update(float delta) {
        player.update(delta);
        updateBullets(delta);
        updateEnemyBullets(delta);
        removeDeadEnemies();
    }

    private void removeDeadEnemies() {
        //nov linked list ki bo vseboval samo live enemies
        LinkedList<Disc> liveDisc = new LinkedList<Disc>();
        for (Disc disc : enemies) {
            //ce je enemy oznacen za despawn, se ga NE doda v nov list
            if (disc.getState() != Disc.State.DYING) {
                liveDisc.add(disc);
            }
        }
        //nov list overwrita star list
        enemies = liveDisc;
    }

    private void updateBullets(float delta) {
        //nov linked list ki bo vseboval samo live bullete
        LinkedList<Bullet> liveBullets = new LinkedList<Bullet>();
        for (Bullet bullet : bullets) {
            //ce je bullet oznacen za despawn, se ga NE doda v nov list
            if (!bullet.isDespawning()) {
                liveBullets.add(bullet);
                bullet.update(delta);
            }
        }
        //nov list overwrita star list
        bullets = liveBullets;
    }

    private void updateEnemyBullets(float delta) {
        //nov linked list ki bo vseboval samo live bullete
        LinkedList<Bullet> liveBullets = new LinkedList<Bullet>();
        for (Bullet bullet : getEnemyBullets()) {
            //ce je bullet oznacen za despawn, se ga NE doda v nov list
            if (!bullet.isDespawning()) {
                liveBullets.add(bullet);
                bullet.update(delta);
            }
        }
        //nov list overwrita star list
        setEnemyBullets(liveBullets);
    }

    private void CreateTestSpace() {
        player = new Player(this, new Vector2(400, 200));
        currentMap = assetManager.get("data/levels/Level1/Level1.tmx", TiledMap.class);
//        level = new Level(getCurrentMap());
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

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public LinkedList<Disc> getEnemies() {
        return enemies;
    }

    public LinkedList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public void setEnemyBullets(LinkedList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }
}
