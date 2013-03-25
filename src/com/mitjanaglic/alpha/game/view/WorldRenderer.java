package com.mitjanaglic.alpha.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.game.models.entities.Bullet;
import com.mitjanaglic.alpha.game.models.entities.Disc;
import com.mitjanaglic.alpha.game.models.entities.Entity;
import com.mitjanaglic.alpha.game.models.entities.Ship;
import com.mitjanaglic.alpha.game.models.worlds.Space;

import java.util.LinkedList;

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
    private float VIRTUAL_WIDTH;
    private float VIRTUAL_HEIGHT;
    private float ASPECT_RATIO;
    private Rectangle viewport;
    private SpriteBatch batch = new SpriteBatch();
    private SpriteBatch uiBatch = new SpriteBatch();
    private BitmapFont font;
    private TextureAtlas textureAtlas;
    private boolean drawHitboxes = false;
    private ShapeRenderer shapeRenderer;
    private TiledMap levelMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private AssetManager assetManager;


    public WorldRenderer(AssetManager assetManager, Space space) {
        this.space = space;
        this.assetManager = assetManager;
        VIRTUAL_WIDTH = 1280;
        VIRTUAL_HEIGHT = 720;
        ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;
        camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();


        initTextures();

    }

    public void render(float delta) {
        moveCameraWithPlayer(delta);
        // update camera
        camera.update();
        //camera.apply(Gdx.gl10);


        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        renderBackground();
        batch.begin();

        renderEnemies();
        renderBullets(space.getBullets());
        renderBullets(space.getEnemyBullets());
        renderPlayer();
        batch.end();


        uiBatch.begin();
        debugInfo();
        renderLives();
        uiBatch.end();

        if (drawHitboxes) {
            drawCollisionBoxes();
        }


    }

    private void drawCollisionBoxes() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(space.getPlayer().getBounds().getX(),
                space.getPlayer().getBounds().getY(),
                space.getPlayer().getBounds().getWidth(),
                space.getPlayer().getBounds().getHeight());
        for (Entity enemy : space.getEnemies()) {
            shapeRenderer.rect(enemy.getBounds().getX(),
                    enemy.getBounds().getY(),
                    enemy.getBounds().getWidth(),
                    enemy.getBounds().getHeight()
            );
        }
        for (Entity bullet : space.getBullets()) {
            shapeRenderer.rect(bullet.getBounds().getX(),
                    bullet.getBounds().getY(),
                    bullet.getBounds().getWidth(),
                    bullet.getBounds().getHeight()
            );
        }
        for (Entity enemyBullet : space.getEnemyBullets()) {
            shapeRenderer.rect(enemyBullet.getBounds().getX(),
                    enemyBullet.getBounds().getY(),
                    enemyBullet.getBounds().getWidth(),
                    enemyBullet.getBounds().getHeight()
            );
        }

        shapeRenderer.end();
    }

    private void moveCameraWithPlayer(float delta) {
        space.getCameraPosition().add(space.getCameraVelocity().cpy().scl(delta));
        camera.position.set(space.getCameraPosition().x, space.getCameraPosition().y, 0);
    }

    private void renderEnemies() {
        for (Disc enemy : space.getEnemies()) {
            batch.draw(textureAtlas.findRegion("Disc"),
                    enemy.getPosition().x,
                    enemy.getPosition().y,
                    enemy.getWidth() / 2,
                    enemy.getHeight() / 2,
                    enemy.getWidth(),
                    enemy.getHeight(),
                    1.0f,
                    1.0f,
                    enemy.getRotationAngle()
            );
            renderHitMark(enemy);
        }
    }

    private void renderPlayer() {
        TextureRegion currentPlayerTexture;
        switch (space.getPlayer().getState()) {
            case IDLE:
                currentPlayerTexture = textureAtlas.findRegion("player");
                break;
            case MOVING_LEFT:
                currentPlayerTexture = textureAtlas.findRegion("playerLeft");
                break;
            case MOVING_RIGHT:
                currentPlayerTexture = textureAtlas.findRegion("playerRight");
                break;
            default:
                currentPlayerTexture = textureAtlas.findRegion("player");
        }

        batch.draw(currentPlayerTexture,
                space.getPlayer().getPosition().x,
                space.getPlayer().getPosition().y,
                space.getPlayer().getWidth(),
                space.getPlayer().getHeight()
        );

        renderHitMark(space.getPlayer());
    }

    private void renderHitMark(Ship owner) {
        TextureRegion texture;
        if (owner.getHitMark() != null) {
            if (owner == space.getPlayer()) {
                texture = textureAtlas.findRegion("laserRedShot");
            } else {
                texture = textureAtlas.findRegion("laserGreenShot");
            }
            batch.draw(texture,
                    owner.getHitMark().getPosition().x,
                    owner.getHitMark().getPosition().y,
                    owner.getHitMark().getWidth(),
                    owner.getHitMark().getHeight());
        }
    }

    private void renderBullets(LinkedList<Bullet> bullets) {
        TextureRegion texture;
        for (Bullet bullet : bullets) {
            if (bullet.getOwner() == space.getPlayer()) {   //player bullets are green
                texture = textureAtlas.findRegion("laserGreen");
            } else {
                texture = textureAtlas.findRegion("laserRed");
            }
            batch.draw(texture,
                    bullet.getPosition().x,
                    bullet.getPosition().y,
                    bullet.getWidth() / 2,
                    bullet.getHeight() / 2,
                    bullet.getWidth(),
                    bullet.getHeight(),
                    1.0f,
                    1.0f,
                    bullet.getAngle()
            );
        }

    }

    private void renderBackground() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    private void renderLives() {
        for (int i = 0; i < space.getPlayer().getLives(); i++) {
            uiBatch.draw(textureAtlas.findRegion("life"),
                    20 + i * 40,
                    30
            );
        }
    }

    private void debugInfo() {
        font.draw(uiBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0,
                VIRTUAL_HEIGHT);

    }

    private void initTextures() {
        textureAtlas = assetManager.get("data/png/textures/textures.pack", TextureAtlas.class);
        levelMap = space.getCurrentMap();
        levelMap.getTileSets().getTile(1).getTextureRegion().getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Nearest);
        mapRenderer = new OrthogonalTiledMapRenderer(levelMap, 1);
    }

    public void setSize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale;
        Vector2 crop = new Vector2(0f, 0f);
        if (aspectRatio > ASPECT_RATIO) {
            scale = (float) height / VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
        } else if (aspectRatio < ASPECT_RATIO) {
            scale = (float) width / VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
        } else {
            scale = (float) width / VIRTUAL_WIDTH;
        }

        float w = VIRTUAL_WIDTH * scale;
        float h = VIRTUAL_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);

    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        uiBatch.dispose();
    }
}
