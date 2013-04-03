package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 31.3.2013
 * Time: 0:37
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class CameraComponent extends Component {
    private Vector2 cameraPosition;
    private Vector2 cameraVelocity;
    private float cameraWidth = 1280f;  //TODO vezat na real vrednosti
    private float cameraHeight = 720f;
    private final float cameraScrollSpeed = 400;

    private OrthographicCamera camera;
    private float VIRTUAL_WIDTH;
    private float VIRTUAL_HEIGHT;
    private float ASPECT_RATIO;
    private Rectangle viewport;

    public CameraComponent(float positionX, float positionY) {
        cameraPosition = new Vector2(positionX, positionY);
        cameraVelocity = new Vector2(0, cameraScrollSpeed);

        VIRTUAL_WIDTH = 1280;
        VIRTUAL_HEIGHT = 720;
        ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;
        camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
    }

    public void setViewport(Rectangle viewport) {
        this.viewport = viewport;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public float getVIRTUAL_WIDTH() {
        return VIRTUAL_WIDTH;
    }

    public float getVIRTUAL_HEIGHT() {
        return VIRTUAL_HEIGHT;
    }

    public float getASPECT_RATIO() {
        return ASPECT_RATIO;
    }

    public Rectangle getViewport() {
        return viewport;
    }

    public float getCameraScrollSpeed() {
        return cameraScrollSpeed;
    }

    public Vector2 getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Vector2 cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public Vector2 getCameraVelocity() {
        return cameraVelocity;
    }

    public void setCameraVelocity(Vector2 cameraVelocity) {
        this.cameraVelocity = cameraVelocity;
    }

    public float getCameraWidth() {
        return cameraWidth;
    }

    public void setCameraWidth(float cameraWidth) {
        this.cameraWidth = cameraWidth;
    }

    public float getCameraHeight() {
        return cameraHeight;
    }

    public void setCameraHeight(float cameraHeight) {
        this.cameraHeight = cameraHeight;
    }
}
