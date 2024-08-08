package com.mitjanaglic.alpha.game.systems.ai;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.GunComponent;
import com.mitjanaglic.alpha.game.components.WeaponsArrayComponent;
import com.mitjanaglic.alpha.game.components.ai.ScarabAiComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 6:58
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class ScarabAiSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<GunComponent> gunM;
    private GunComponent gunComponent;
    @Mapper
    private ComponentMapper<WeaponsArrayComponent> weaponsArrayM;
    private WeaponsArrayComponent weaponsArrayComponent;

    public ScarabAiSystem() {
        super(Aspect.getAspectForAll(ScarabAiComponent.class, WeaponsArrayComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        weaponsArrayComponent = weaponsArrayM.get(entity);
        for (GunComponent gc : weaponsArrayComponent.getWeaponsArray()) {
            gunComponent = gc;
            gunComponent.setShootRequest(true);
        }
    }
}
