package com.mitjanaglic.alpha.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.constants.ids;
import com.mitjanaglic.alpha.game.utils.EntityFactory;

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
    private TiledMap levelMap;

    public SpawnDespawnSystem() {
    }

    @Override
    protected void processSystem() {
        checkSpawns();
        Entity player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        if (player != null) {
            checkDespawns(player);
        }
    }

    private void spawnPlayer() {
        MapObject playerSpawn = levelMap.getLayers().get("spawns").getObjects().get("player");
        Integer x = (Integer) playerSpawn.getProperties().get("x");
        Integer y = (Integer) playerSpawn.getProperties().get("y");
        EntityFactory.createPlayer(world, x, y);
    }

    private void checkSpawns() {
        if (levelMap != null) {
            MapObjects spawns = levelMap.getLayers().get("spawns").getObjects();
            for (MapObject spawn : spawns) {
                Integer x = (Integer) spawn.getProperties().get("x");
                Integer y = (Integer) spawn.getProperties().get("y");
                Integer entityType = Integer.valueOf((String) spawn.getProperties().get("entityType"));
                EntityFactory.spawnEntity(world, x, y, entityType);
                spawns.remove(spawn);
            }
        }
    }

    private void checkDespawns(Entity entity) {
        PositionComponent playerPosition = positionM.get(entity);
        ImmutableBag<Entity> entities = world.getManager(GroupManager.class).getEntities(ids.ENEMY);
        for (int i = 0; i < entities.size(); i++) {
            Entity enemy = entities.get(i);
            PositionComponent enemyPosition = positionM.get(enemy);
            if (playerPosition.getPosition().dst(enemyPosition.getPosition()) > 1500) {
                enemy.deleteFromWorld();
            }
        }
    }

    public TiledMap getLevelMap() {
        return levelMap;
    }

    public void setLevelMap(TiledMap levelMap) {
        this.levelMap = levelMap;
    }
}
