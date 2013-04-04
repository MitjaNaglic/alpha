package com.mitjanaglic.alpha.game.systems.ai;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.GunComponent;
import com.mitjanaglic.alpha.game.components.ai.ScarabAiComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 6:58
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ScarabAiSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<GunComponent> gunM;

    public ScarabAiSystem() {
        super(Aspect.getAspectForAll(ScarabAiComponent.class, GunComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        GunComponent gunComponent = gunM.get(entity);
        gunComponent.setAimAngle(180);
        gunComponent.setShootRequest(true);
    }
}
