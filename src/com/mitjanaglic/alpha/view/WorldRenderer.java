package com.mitjanaglic.alpha.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mitjanaglic.alpha.models.entities.Bullet;
import com.mitjanaglic.alpha.models.entities.EnemyDisc;
import com.mitjanaglic.alpha.models.entities.Entity;
import com.mitjanaglic.alpha.worlds.Space;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
    private Map<String, TextureRegion> textureMap;
    private Texture backgroundTexture;
    private TextureRegion background;
    private BitmapFont font;
    private TextureAtlas textureAtlas;
    private boolean drawHitboxes = false;
    private ShapeRenderer shapeRenderer;


    public WorldRenderer(Space space) {
        this.space = space;
        VIRTUAL_WIDTH = 1280;
        VIRTUAL_HEIGHT = 720;
        ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;
        camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        font = new BitmapFont();
        loadTextures();
        shapeRenderer = new ShapeRenderer();


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
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.begin();
        renderBackground();
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
        space.getCameraPosition().add(space.getCameraVelocity().cpy().mul(delta));
        camera.position.set(space.getCameraPosition().x, space.getCameraPosition().y, 0);
    }

    private void renderEnemies() {
        for (EnemyDisc enemy : space.getEnemies()) {
            batch.draw(textureMap.get("enemyUFO"),
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
        }
    }

    private void renderPlayer() {
        TextureRegion currentPlayerTexture;
        switch (space.getPlayer().getState()) {
            case IDLE:
                currentPlayerTexture = textureMap.get("player");
                break;
            case MOVING_LEFT:
                currentPlayerTexture = textureMap.get("playerLeft");
                break;
            case MOVING_RIGHT:
                currentPlayerTexture = textureMap.get("playerRight");
                break;
            default:
                currentPlayerTexture = textureMap.get("playerPwr2");
        }

        batch.draw(currentPlayerTexture,
                space.getPlayer().getPosition().x,
                space.getPlayer().getPosition().y,
                space.getPlayer().getWidth(),
                space.getPlayer().getHeight()
        );
    }

    private void renderBullets(LinkedList<Bullet> bullets) {
        TextureRegion texture;
        for (Bullet bullet : bullets) {
            if (bullet.getOwner() == space.getPlayer()) {   //player bullets are green
                texture = textureMap.get("laserGreen");
            } else {
                texture = textureMap.get("laserRed");
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
        batch.draw(background, 0, 0);
    }

    private void renderLives() {
        for (int i = 0; i < space.getPlayer().getLives(); i++) {
            uiBatch.draw(textureMap.get("life"),
                    viewport.x + 20 + i * 40,
                    viewport.y + 30
            );
        }
    }

    private void debugInfo() {
        font.draw(uiBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), viewport.x,
                viewport.y + viewport.height);

    }

    private void loadTextures() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("data\\png\\textures\\textures.pack"));
        textureMap = new HashMap<String, TextureRegion>();
        textureMap.put("player", textureAtlas.findRegion("player"));
        textureMap.put("playerLeft", textureAtlas.findRegion("playerLeft"));
        textureMap.put("playerRight", textureAtlas.findRegion("playerRight"));
        textureMap.put("laserRed", textureAtlas.findRegion("laserRed"));
        textureMap.put("laserGreen", textureAtlas.findRegion("laserGreen"));
        textureMap.put("enemyUFO", textureAtlas.findRegion("enemyUFO"));
        textureMap.put("life", textureAtlas.findRegion("life"));
        backgroundTexture = new Texture(Gdx.files.internal("data\\png\\Background\\starBackground.png"));  //TODO background texture atlas
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new TextureRegion(backgroundTexture, space.getLevel().getWidth(), space.getLevel().getHeight());
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
        textureAtlas.dispose();
        backgroundTexture.dispose();
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        uiBatch.dispose();
    }
}
