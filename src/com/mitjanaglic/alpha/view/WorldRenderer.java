package com.mitjanaglic.alpha.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.worlds.Space;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class WorldRenderer implements Disposable {
    private Space space;
    private OrthographicCamera camera;
    private static final float CAMERA_WIDTH = 1280f;
    private static final float CAMERA_HEIGHT = 720f;
    private int width;
    private int height;
    private float ppuX; // pixels per unit on the X axis
    private float ppuY; // pixels per unit on the Y axis
    private SpriteBatch batch = new SpriteBatch();
    private Texture playerTexture;
    private Texture backgroundTexture;


    public WorldRenderer(Space space) {
        this.space = space;
        loadTextures();

    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        batch.draw(playerTexture,
                space.getPlayer().getPosition().x * ppuX,
                space.getPlayer().getPosition().y * ppuY);
        batch.end();

    }

    private void loadTextures() {
        playerTexture = new Texture(Gdx.files.internal("data\\png\\playerPwr2.png"));
        backgroundTexture = new Texture(Gdx.files.internal("data\\png\\Background\\starBackground.png"));
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }
}
