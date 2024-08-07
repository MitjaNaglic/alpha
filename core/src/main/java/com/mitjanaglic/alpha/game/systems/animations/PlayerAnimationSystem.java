package com.mitjanaglic.alpha.game.systems.animations;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.ids.PlayerShipComponent;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.components.StateComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 20:44
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class PlayerAnimationSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<RenderableComponent> renderableM;
    @Mapper
    private ComponentMapper<StateComponent> stateM;

    private RenderableComponent renderableComponent;
    private StateComponent stateComponent;

    public PlayerAnimationSystem() {
        super(Aspect.getAspectForAll(PlayerShipComponent.class, RenderableComponent.class, StateComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        renderableComponent = renderableM.get(entity);
        stateComponent = stateM.get(entity);

        setTextureName();
    }

    private void setTextureName() {
        switch (stateComponent.getCurrentMovementState()) {
            case IDLE:
                renderableComponent.setSpriteTextureName("player");
                break;
            case MOVING_LEFT:
                renderableComponent.setSpriteTextureName("playerLeft");
                break;
            case MOVING_RIGHT:
                renderableComponent.setSpriteTextureName("playerRight");
                break;
            case MOVING:
                break;
        }
    }
}
