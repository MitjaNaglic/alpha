package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.GunComponent;
import com.mitjanaglic.alpha.game.components.HitboxComponent;
import com.mitjanaglic.alpha.game.components.ai.DiscAiComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 1:20
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class DiscAiSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<GunComponent> gunM;
    private GunComponent gunComponent;
    @Mapper
    ComponentMapper<HitboxComponent> hitboxM;

    public DiscAiSystem() {
        super(Aspect.getAspectForAll(DiscAiComponent.class, GunComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        gunComponent = gunM.get(entity);
        aim(world.getManager(TagManager.class).getEntity("player"));
        gunComponent.setShootRequest(true);
    }

    private void aim(Entity target) {
        HitboxComponent targetHitboxComponent = hitboxM.get(target);
        gunComponent.setAimAngle(targetHitboxComponent.getCenter().sub(gunComponent.getGunPosition()).angle() - 90);
    }
}
