package com.mitjanaglic.alpha.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.mitjanaglic.alpha.game.Alpha;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.constants.ids;
import com.mitjanaglic.alpha.game.models.SpawnPoint;
import com.mitjanaglic.alpha.game.utils.EntityFactory;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 10.4.2013
 * Time: 12:54
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class SpawnDespawnSystem extends VoidEntitySystem {
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private LinkedList<SpawnPoint> spawnPoints;
    private CameraSystem cameraSystem;
    private int levelEnd;
    private Entity player;
    private Alpha alpha;
    private PositionComponent playerPosition;

    public SpawnDespawnSystem(Alpha alpha) {
        this.alpha = alpha;
    }

    @Override
    protected void initialize() {
        super.initialize();
        cameraSystem = world.getSystem(CameraSystem.class);
    }

    @Override
    protected void processSystem() {
        checkSpawns();
        player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        if (player != null) {
            playerPosition = positionM.get(player);
            checkLevelEnd();
            checkDespawns();
        }
    }

    //prever nasledn spawn v listu če je v bounds kamere, in če je se spawna objekt, ter remova iz lista
    private void checkSpawns() {
        if (spawnPoints != null && cameraSystem != null) {
            SpawnPoint spawnPoint = spawnPoints.peek();
            if (spawnPoint != null) {
                if (spawnPoint.getY() <= cameraSystem.getUpperBound()) {
                    EntityFactory.spawnEntity(world, spawnPoint);
                    spawnPoints.remove();
                }
            }
        }
    }

    private void checkDespawns() {
        ImmutableBag<Entity> entities = world.getManager(GroupManager.class).getEntities(ids.ENEMY);
        for (int i = 0; i < entities.size(); i++) {
            Entity enemy = entities.get(i);
            PositionComponent enemyPosition = positionM.get(enemy);
            if (playerPosition.getPosition().dst(enemyPosition.getPosition()) > 1500) {
                enemy.deleteFromWorld();
            }
        }
    }

    private void checkLevelEnd() {
        if (playerPosition.getPosition().y >= levelEnd) {
            alpha.setToLevelOverScreen();
        }
    }

    public LinkedList<SpawnPoint> getSpawnPoints() {
        return spawnPoints;
    }

    public void setSpawnPoints(LinkedList<SpawnPoint> spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    public int getLevelEnd() {
        return levelEnd;
    }

    public void setLevelEnd(int levelEnd) {
        this.levelEnd = levelEnd;
    }
}
