package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.HitboxComponent;
import com.mitjanaglic.alpha.game.components.PlayerShipComponent;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.components.StateComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 20:44
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class PlayerAnimationSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<RenderableComponent> renderableM;
    @Mapper
    private ComponentMapper<StateComponent> stateM;
    @Mapper
    private ComponentMapper<HitboxComponent> hitboxM;
    private RenderableComponent renderableComponent;
    private StateComponent stateComponent;
    private HitboxComponent hitboxComponent;

    public PlayerAnimationSystem() {
        super(Aspect.getAspectForAll(PlayerShipComponent.class, RenderableComponent.class, StateComponent.class, HitboxComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        renderableComponent = renderableM.get(entity);
        stateComponent = stateM.get(entity);
        hitboxComponent = hitboxM.get(entity);

        setTextureName();
    }

    private void setTextureName() {
        switch (stateComponent.getState()) {
            case IDLE:
                renderableComponent.setSpriteTextureName("player");
                break;
            case DYING:
                break;
            case MOVING_LEFT:
                renderableComponent.setSpriteTextureName("playerLeft");
                break;
            case MOVING_RIGHT:
                renderableComponent.setSpriteTextureName("playerRight");
                break;
            case MOVING:
                break;
            case DESPAWNING:
                break;
        }
    }
}
