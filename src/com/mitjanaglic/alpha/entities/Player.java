package com.mitjanaglic.alpha.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 22.2.2013
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Entity {
    private Camera camera;
    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();
    private State state = State.IDLE;
    private static final float SIZE = 1f;
    static final float SPEED = 2f;
    private Rectangle bounds = new Rectangle();

    public Player(Texture texture, Vector2 pos, Camera camera) {
        super(texture, pos);
        this.camera = camera;
        bounds.height = SIZE;
        bounds.width = SIZE;
    }

    @Override
    public void Draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Update() {
        Move();
    }

    private void Move() {
//        //touch, mouse
//        Vector3 touchPos;
//        if (Gdx.input.isTouched()) {
//            touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            player.x = touchPos.x - player.getWidth() / 2;
//            player.y = touchPos.y - player.getHeight() / 2;
//        }
//
//
//        //keyboard
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) player.x -= 400 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) player.x += 400 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) player.y += 400 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) player.y -= 400 * Gdx.graphics.getDeltaTime();
//
//        if (player.x < 0) player.x = 0;
//        if (player.x > 1280 - player.getWidth()) player.x = 1280 - player.getWidth();
//
//        if (player.y < 0) player.y = 0;
//        if (player.y > 720 - player.getHeight()) player.y = 720 - player.getHeight();

    }
}
