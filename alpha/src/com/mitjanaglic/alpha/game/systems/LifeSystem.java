package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.mitjanaglic.alpha.game.Alpha;
import com.mitjanaglic.alpha.game.components.LifeComponent;
import com.mitjanaglic.alpha.game.components.ParticleEmmiterComponent;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.constants.ids;

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
    private Entity player;
    private LifeComponent playerLife;
    private float timeToGameOverScreen = 3f;
    private float timeToGameOverScreenPassed = 0;
    private boolean timeToGameOverScreenCountdown = false;
    private Alpha alpha;

    public LifeSystem(Alpha alpha) {
        super(Aspect.getAspectForAll(LifeComponent.class, PositionComponent.class));
        this.alpha = alpha;
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
        checkPlayerHealth();
        if (timeToGameOverScreenCountdown) gameOverScreenCountdown();
    }

    private void checkPlayerHealth() {
        player = world.getManager(TagManager.class).getEntity(ids.PLAYER);
        if (player != null) {
            playerLife = lifeM.get(player);
            if (playerLife.getCurrentLife() <= 0) {
                timeToGameOverScreenCountdown = true;
            }
        }
    }

    private void gameOverScreenCountdown() {
        timeToGameOverScreenPassed += world.getDelta();
        if (timeToGameOverScreenPassed >= timeToGameOverScreen) {
            alpha.setToGameOverScreen();
            timeToGameOverScreenPassed = 0;
            timeToGameOverScreenCountdown = false;
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
