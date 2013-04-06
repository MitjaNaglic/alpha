package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.BulletComponent;
import com.mitjanaglic.alpha.game.components.HitmarkComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.constants.ids;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 18:53
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class BulletSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<BulletComponent> bulletM;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    BulletComponent bulletComponent;
    PositionComponent positionComponent;

    public BulletSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, BulletComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        bulletComponent = bulletM.get(entity);
        positionComponent = positionM.get(entity);

        handleRange(entity);
        handleHits(entity);
    }

    private void handleHits(Entity entity) {
        if (bulletComponent.isHit()) {
            Entity hitmark = world.createEntity();
            hitmark.addComponent(new PositionComponent(positionComponent.getPosition().cpy()));
            hitmark.addComponent(new HitmarkComponent());
            hitmark.addComponent(new RenderableComponent(bulletComponent.getImpactTextureId(), 0.5f, 0.5f, (float) Math.random() * 360));
            hitmark.addToWorld();
            world.getManager(GroupManager.class).add(hitmark, ids.HITMARK);
            entity.deleteFromWorld();
        }
    }

    private void handleRange(Entity entity) {
        if (positionComponent.getPosition().cpy().dst(bulletComponent.getShotOrigin()) >= bulletComponent.getRange()) {
            entity.deleteFromWorld();
        }
    }
}
