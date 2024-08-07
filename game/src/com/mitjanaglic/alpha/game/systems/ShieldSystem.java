package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.PositionComponent;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.components.ShieldComponent;
import com.mitjanaglic.alpha.game.components.ShieldFlareComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.4.2013
 * Time: 16:47
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class ShieldSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<ShieldComponent> shieldM;
    private ShieldComponent shieldComponent;
    @Mapper
    private ComponentMapper<PositionComponent> positionM;
    private PositionComponent positionComponent;

    public ShieldSystem() {
        super(Aspect.getAspectForAll(ShieldComponent.class, PositionComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        shieldComponent = shieldM.get(entity);
        //position entitya s shieldom
        positionComponent = positionM.get(entity);
        regeneration();
        handleFlareSpawning();
    }

    private void regeneration() {
        shieldComponent.setTimeSinceLastHit(shieldComponent.getTimeSinceLastHit() + world.getDelta());
        if (shieldComponent.getTimeSinceLastHit() >= shieldComponent.getTimeBeforeRegen()) {
            float deltaShieldRegen = world.getDelta() * 100;
            if (shieldComponent.getCurrentShields() < shieldComponent.getMaxShields()) {
                shieldComponent.setCurrentShields(shieldComponent.getCurrentShields() + deltaShieldRegen);
            }
            //ensure upperbound ==max
            if (shieldComponent.getCurrentShields() > shieldComponent.getMaxShields()) {
                shieldComponent.setCurrentShields(shieldComponent.getMaxShields());
            }
        }
    }

    private void handleFlareSpawning() {
        if (shieldComponent.isRequestFlare() && shieldComponent.getCurrentShields() > 0) {
            Entity shieldFlare = world.createEntity();
            shieldFlare.addComponent(new ShieldFlareComponent());
            shieldFlare.addComponent(positionComponent);
            RenderableComponent r = new RenderableComponent("shield", 1f, 1f, shieldComponent.getAngle(), -26, -35);
            shieldFlare.addComponent(r);
            shieldFlare.addToWorld();
            shieldComponent.setRequestFlare(false);
        }
    }

    /**
     * Returns damage, left over after shield breaking
     */
    public float hitShields(Entity victim, float damage, float angle) {
        ShieldComponent victimShield = shieldM.get(victim);
        float leftoverDamage = 0;
        //ce victim nima shielda se vrne celoten dmg
        if (victimShield == null) {
            leftoverDamage = damage;
        } else {
            if (damage <= victimShield.getCurrentShields()) {
                victimShield.setCurrentShields(victimShield.getCurrentShields() - damage);
            } else {
                //ce je shieldov mn kot damaga, potem se odsteje dmg, leftoverdmg postane
                // abs vrednost shieldov, shieldi se nastavjo na 0 because -shields makes no sense
                victimShield.setCurrentShields(victimShield.getCurrentShields() - damage);
                leftoverDamage = Math.abs(victimShield.getCurrentShields());
                victimShield.setCurrentShields(0);
            }
            victimShield.setAngle(angle);
            victimShield.setRequestFlare(true);
            victimShield.setTimeSinceLastHit(0);
        }
        return leftoverDamage;
    }
}
