package com.mitjanaglic.alpha.game.systems.animations;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.mitjanaglic.alpha.game.components.RenderableComponent;
import com.mitjanaglic.alpha.game.components.ids.DiscComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 3.4.2013
 * Time: 19:03
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class DiscAnimationSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<RenderableComponent> renderableM;
    private RenderableComponent renderableComponent;
    @Mapper
    private ComponentMapper<DiscComponent> discM;
    private DiscComponent discComponent;

    public DiscAnimationSystem() {
        super(Aspect.getAspectForAll(DiscComponent.class, RenderableComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        renderableComponent = renderableM.get(entity);
        discComponent = discM.get(entity);

        rotate();
    }

    private void rotate() {
        if (renderableComponent.getRotationAngle() <= 360) {
            renderableComponent.setRotationAngle(renderableComponent.getRotationAngle()
                    + discComponent.getAngularVelocity() * world.getDelta());
            ;
        } else {
            renderableComponent.setRotationAngle(0);
        }
    }
}
