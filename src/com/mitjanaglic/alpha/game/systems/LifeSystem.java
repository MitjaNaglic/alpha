package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.mitjanaglic.alpha.game.components.LifeComponent;

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

    public LifeSystem() {
        super(Aspect.getAspectForAll(LifeComponent.class));
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
            damageDecay();
            if (lifeComponent.getCurrentLife() <= 0) {
                lifeComponent.setCurrentLife(0);
                entityImmutableBag.get(i).deleteFromWorld();
            }
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
