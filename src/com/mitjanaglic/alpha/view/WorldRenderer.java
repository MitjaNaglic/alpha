package com.mitjanaglic.alpha.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.models.entities.Bullet;
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
    private TextureRegion playerRightTexture;
    private TextureRegion playerLeftTexture;
    private Texture backgroundTexture;
    private TextureRegion background;
    private BitmapFont font;
    private TextureAtlas textureAtlas;
    private TextureRegion bulletTexture;


    public WorldRenderer(Space space) {
        this.space = space;
        font = new BitmapFont();
        loadTextures();


    }

    public void render(float delta) {
        moveCameraWithPlayer(delta);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        renderBackground();
        debugInfo();
        renderPlayer();
        renderBullets();
        batch.end();
    }

    private void moveCameraWithPlayer(float delta) {
        space.getCameraPosition().add(space.getCameraVelocity().cpy().mul(delta));
        camera.position.set(space.getCameraPosition().x * ppuX, space.getCameraPosition().y * ppuY, 0);
        camera.update();
    }

    private void renderPlayer() {
        TextureRegion currentPlayerTexture;
        switch (space.getPlayer().getState()) {
            case IDLE:
                currentPlayerTexture = playerTexture;
                break;
            case MOVING_LEFT:
                currentPlayerTexture = playerLeftTexture;
                break;
            case MOVING_RIGHT:
                currentPlayerTexture = playerRightTexture;
                break;
            default:
                currentPlayerTexture = playerTexture;
        }

        batch.draw(currentPlayerTexture,
                space.getPlayer().getPosition().x * ppuX,
                space.getPlayer().getPosition().y * ppuY,
                space.getPlayer().getSize() * ppuX,
                space.getPlayer().getSize() * ppuY
        );

    }

    private void renderBullets() {
        if (space.getBullets().size() > 0) {
            for (Bullet bullet : space.getBullets()) {
                batch.draw(bulletTexture,
                        bullet.getPosition().x * ppuX,
                        bullet.getPosition().y * ppuY,
                        bullet.getWidth() * ppuX,
                        bullet.getHeight() * ppuY
                );
            }
        }
    }

    private void renderBackground() {
        batch.draw(background, 0, 0);
    }

    private void debugInfo() {
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), space.getCameraPosition().x * ppuX - width / 2,
                space.getCameraPosition().y * ppuY + height / 2);

    }

    private void loadTextures() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("data\\png\\textures\\textures.pack"));
        playerTexture = textureAtlas.findRegion("playerPwr2");
        playerLeftTexture = textureAtlas.findRegion("playerLeft");
        playerRightTexture = textureAtlas.findRegion("playerRight");
        bulletTexture = textureAtlas.findRegion("laserRed");
        backgroundTexture = new Texture(Gdx.files.internal("data\\png\\Background\\starBackground.png"));  //TODO background texture atlas
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new TextureRegion(backgroundTexture, space.getLevel().getWidth(), space.getLevel().getHeight());
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float) width / space.getLevel().getCameraWidth();
        ppuY = (float) height / space.getLevel().getCameraHeight();
        camera = new OrthographicCamera(space.getLevel().getCameraWidth() * ppuX, space.getLevel().getCameraHeight() * ppuY);
        camera.position.set(space.getCameraPosition().x, space.getCameraPosition().y, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        backgroundTexture.dispose();
        font.dispose();
        batch.dispose();
    }
}
