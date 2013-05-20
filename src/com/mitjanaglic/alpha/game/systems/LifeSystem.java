package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.mitjanaglic.alpha.game.components.LifeComponent;
import com.mitjanaglic.alpha.game.components.ParticleEmmiterComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 7.4.2013
 * Time: 19:22
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class LifeSystem extends EntitySystem {
    @Mapper
    private ComponentMapper<LifeComponent> lifeM;
    private LifeComponent lifeComponent;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private PositionComponent positionComponent;

    public LifeSystem() {
        super(Aspect.getAspectForAll(LifeComponent.class, PositionComponent.class));
    }

    public void inflictDamage(Entity entity, float damage) {
        lifeComponent = lifeM.get(entity);
        if (lifeComponent != null) {
            if (lifeComponent.getCurrentLife() - damage > 0) {
                lifeComponent.inflictDamage(damage);
            } else {
                lifeComponent.inflictDamage(lifeComponent.getCurrentLife());
            }
        }
    }

    private void damageDecay() {
        if (lifeComponent.getDamage() > 0) {
            lifeComponent.setDamage(lifeComponent.getDamage() - world.getDelta() * 150);
        }
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        for (int i = 0; i < entityImmutableBag.size(); i++) {
            lifeComponent = lifeM.get(entityImmutableBag.get(i));
            positionComponent = positionM.get(entityImmutableBag.get(i));
            damageDecay();
            if (lifeComponent.getCurrentLife() <= 0) {
                lifeComponent.setCurrentLife(0);
                spawnExplosion();
                entityImmutableBag.get(i).deleteFromWorld();
            }
        }
    }

    private void spawnExplosion() {
        Entity e = world.createEntity();
        e.addComponent(new ParticleEmmiterComponent("explosion", positionComponent.getCenter().x, positionComponent.getCenter().y));
        e.addToWorld();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
