package com.mitjanaglic.alpha.worlds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.models.Level;
import com.mitjanaglic.alpha.models.entities.Bullet;
import com.mitjanaglic.alpha.models.entities.EnemyDisc;
import com.mitjanaglic.alpha.models.entities.Player;

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
    private LinkedList<Bullet> enemyBullets = new LinkedList<Bullet>();
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<EnemyDisc> enemies = new LinkedList<EnemyDisc>();

    public Space() {
        CreateTestSpace();
        setCameraPosition(new Vector2(level.getCameraWidth() / 2f, level.getCameraHeight() / 2f));
        setCameraVelocity(new Vector2());
        getCameraVelocity().y = player.getForwardInertia();
        getEnemies().add(new EnemyDisc(getCameraPosition().cpy(), player));
    }

    public void getDrawableBackground() {
    }

    public void getDrawableEntities() {

    }

    public void update(float delta) {
        updateBullets(delta);
        updateEnemyBullets(delta);
        removeDeadEnemies(delta);
    }

    private void removeDeadEnemies(float delta) {
        //nov linked list ki bo vseboval samo live enemies
        LinkedList<EnemyDisc> liveEnemyDisc = new LinkedList<EnemyDisc>();
        for (EnemyDisc enemyDisc : enemies) {
            //ce je enemy oznacen za despawn, se ga NE doda v nov list
            if (enemyDisc.getState() != EnemyDisc.State.DYING) {
                liveEnemyDisc.add(enemyDisc);
            }
        }
        //nov list overwrita star list
        enemies = liveEnemyDisc;
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
        System.out.println(enemyBullets.size());
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

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(LinkedList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public LinkedList<EnemyDisc> getEnemies() {
        return enemies;
    }

    public void setEnemies(LinkedList<EnemyDisc> enemies) {
        this.enemies = enemies;
    }

    public LinkedList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public void setEnemyBullets(LinkedList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }
}
