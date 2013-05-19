package com.mitjanaglic.alpha.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mitjanaglic.alpha.game.components.CameraComponent;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 31.3.2013
 * Time: 0:59
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class CameraSystem extends EntityProcessingSystem {
    @Mapper
    private ComponentMapper<CameraComponent> cameraM;
    private CameraComponent cameraComponent;

    public CameraSystem(CameraComponent cameraComponent) {
        super(Aspect.getAspectForAll(CameraComponent.class));
        this.cameraComponent = cameraComponent;
    }

    @Override
    protected void process(Entity entity) {
        cameraComponent = cameraM.get(entity);
        cameraComponent.getCameraVelocity().set(0, cameraComponent.getCameraScrollSpeed());
        cameraComponent.getCameraPosition().add(cameraComponent.getCameraVelocity().cpy().scl(world.getDelta()));
        repositionCamera();
    }

    private void repositionCamera() {
        cameraComponent.getCamera().position.set(cameraComponent.getCameraPosition().x, cameraComponent.getCameraPosition().y, 0);
    }

    public void setSize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale;
        Vector2 crop = new Vector2(0f, 0f);
        if (aspectRatio > cameraComponent.getASPECT_RATIO()) {
            scale = (float) height / cameraComponent.getVIRTUAL_HEIGHT();
            crop.x = (width - cameraComponent.getVIRTUAL_WIDTH() * scale) / 2f;
        } else if (aspectRatio < cameraComponent.getASPECT_RATIO()) {
            scale = (float) width / cameraComponent.getVIRTUAL_WIDTH();
            crop.y = (height - cameraComponent.getVIRTUAL_HEIGHT() * scale) / 2f;
        } else {
            scale = (float) width / cameraComponent.getVIRTUAL_WIDTH();
        }

        float w = cameraComponent.getVIRTUAL_WIDTH() * scale;
        float h = cameraComponent.getVIRTUAL_HEIGHT() * scale;
        cameraComponent.setViewport(new Rectangle(crop.x, crop.y, w, h));

    }

    public float getWidth() {
        return cameraComponent.getCameraWidth();
    }

    public float getHeight() {
        return cameraComponent.getCameraHeight();
    }

    public Vector2 getPosition() {
        return cameraComponent.getCameraPosition();
    }

    public float getUpperBound() {
        return cameraComponent.getCameraPosition().y + cameraComponent.getCameraHeight() / 2;
    }

    public OrthographicCamera getCamera() {
        return cameraComponent.getCamera();
    }
}
