package com.mitjanaglic.alpha.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
    private TextureRegion playerTexture;
    private Texture backgroundTexture;
    private TextureRegion background;
    private BitmapFont font;
    private Vector2 cameraPosition;
    private Vector2 cameraVelocity;
    private TextureAtlas textureAtlas;


    public WorldRenderer(Space space) {
        this.space = space;
        font = new BitmapFont();
        loadTextures();
        cameraPosition = new Vector2(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        cameraVelocity = new Vector2();
        cameraVelocity.y = space.getPlayer().getForwardInertia();

    }

    public void render(float delta) {
        moveCameraWithPlayer(delta);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        renderBackground();
        debugInfo();
        batch.draw(playerTexture,
                space.getPlayer().getPosition().x * ppuX,
                space.getPlayer().getPosition().y * ppuY,
                space.getPlayer().getSize() * ppuX,
                space.getPlayer().getSize() * ppuY
        );
        batch.end();
    }

    private void moveCameraWithPlayer(float delta) {
        cameraPosition.add(cameraVelocity.cpy().mul(delta));
        camera.position.set(cameraPosition.x * ppuX, cameraPosition.y * ppuY, 0);
        camera.update();
    }

    private void renderBackground() {
        batch.draw(background, 0, 0);
    }

    private void debugInfo() {
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), cameraPosition.x * ppuX - CAMERA_WIDTH / 2,
                cameraPosition.y * ppuY + CAMERA_HEIGHT / 2);

    }

    private void loadTextures() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("data\\png\\textures\\textures.pack"));
        playerTexture = textureAtlas.findRegion("playerPwr2");
        backgroundTexture = new Texture(Gdx.files.internal("data\\png\\Background\\starBackground.png"));  //TODO background texture atlas
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new TextureRegion(backgroundTexture, (int) space.getBounds().getWidth(), (int) space.getBounds().getHeight());
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
        camera = new OrthographicCamera(CAMERA_WIDTH * ppuX, CAMERA_HEIGHT * ppuY);
        camera.position.set(cameraPosition.x, cameraPosition.y, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        //backgroundTexture.dispose();
        font.dispose();
        batch.dispose();
    }
}
