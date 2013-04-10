package com.mitjanaglic.alpha.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.constants.ids;

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

    public SpawnDespawnSystem() {
    }

    @Override
    protected void processSystem() {
        Entity player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        if (player != null) {
            checkSpawns(player);
            checkDespawns(player);
        }
    }

    private void checkSpawns(Entity entity) {

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
}
