package com.mitjanaglic.alpha.game.view;

import com.badlogic.gdx.utils.Disposable;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class WorldRenderer implements Disposable {
//    private OrthographicCamera camera;
//    private float VIRTUAL_WIDTH;
//    private float VIRTUAL_HEIGHT;
//    private float ASPECT_RATIO;
//    private Rectangle viewport;
//    private SpriteBatch batch = new SpriteBatch();
//    private SpriteBatch uiBatch = new SpriteBatch();
//    private BitmapFont font;
//    private TextureAtlas textureAtlas;
//    private boolean drawHitboxes = true;
//    private ShapeRenderer shapeRenderer;
//    private TiledMap levelMap;
//    private OrthogonalTiledMapRenderer mapRenderer;
//    private AssetManager assetManager;
//    private World world;
//    private ComponentMapper<CameraComponent> cameraM;
//    private CameraComponent cameraComponent;
//
//
//    public WorldRenderer(AssetManager assetManager, World world) {
//        this.assetManager = assetManager;
//        this.world=world;
//        cameraM=world.getMapper(CameraComponent.class);
//        cameraComponent=cameraM.get(world.getManager(TagManager.class).getEntity("camera"));
//        VIRTUAL_WIDTH = 1280;
//        VIRTUAL_HEIGHT = 720;
//        ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;
//        camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
//        font = new BitmapFont();
//        shapeRenderer = new ShapeRenderer();
//
//
//        initTextures();
//
//    }
//
//    public void render(float delta) {
//        repositionCamera();
//        // update camera
//        camera.update();
//        //camera.apply(Gdx.gl10);
//
//
//        // set viewport
//        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
//                (int) viewport.width, (int) viewport.height);
//        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//
//        batch.setProjectionMatrix(camera.combined);
//        shapeRenderer.setProjectionMatrix(camera.combined);
//        renderBackground();
//        batch.begin();
//
//        renderEnemies();
//        renderBullets(space.getBullets());
//        renderBullets(space.getEnemyBullets());
//        renderPlayer();
//        batch.end();
//
//
//        uiBatch.begin();
//        debugInfo();
//        renderLives();
//        uiBatch.end();
//
//        if (drawHitboxes) {
//            drawCollisionBoxes();
//        }
//
//
//    }
//
//    private void drawCollisionBoxes() {
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        HitboxComponent hitboxComponent=(HitboxComponent)space.getPlayer().getComponents().get("hitbox");
//        Rectangle hitbox=hitboxComponent.getHitbox();
//        shapeRenderer.rect(hitbox.getX(),
//                hitbox.getY(),
//                hitbox.getWidth(),
//                hitbox.getHeight());
//        for (Entity enemy : space.getEnemies()) {
//            hitboxComponent= (HitboxComponent) enemy.getComponents().get("hitbox");
//            hitbox=hitboxComponent.getHitbox();
//            shapeRenderer.rect(hitbox.getX(),
//                    hitbox.getY(),
//                    hitbox.getWidth(),
//                    hitbox.getHeight()
//            );
//        }
//        for (Entity bullet : space.getBullets()) {
//            hitboxComponent= (HitboxComponent) bullet.getComponents().get("hitbox");
//            hitbox=hitboxComponent.getHitbox();
//            shapeRenderer.rect(hitbox.getX(),
//                    hitbox.getY(),
//                    hitbox.getWidth(),
//                    hitbox.getHeight()
//            );
//        }
//        for (Entity enemyBullet : space.getEnemyBullets()) {
//            hitboxComponent= (HitboxComponent) enemyBullet.getComponents().get("hitbox");
//            hitbox=hitboxComponent.getHitbox();
//            shapeRenderer.rect(hitbox.getX(),
//                    hitbox.getY(),
//                    hitbox.getWidth(),
//                    hitbox.getHeight()
//            );
//        }
//
//        shapeRenderer.end();
//    }
//
//    private void repositionCamera() {
//        camera.position.set(cameraComponent.getCameraPosition().x,
//                cameraComponent.getCameraPosition().y, 0);
//    }
//
//    private void renderEnemies() {
//        for (Disc enemy : space.getEnemies()) {
//            PositionComponent positionComponent= (PositionComponent) enemy.getComponents().get("position");
//            HitboxComponent hitboxComponent=(HitboxComponent)enemy.getComponents().get("hitbox"); //TODO drawable component, zdej se uporabla hitbox za width/height
//            batch.draw(textureAtlas.findRegion("Disc"),
//                    positionComponent.getPosition().x,
//                    positionComponent.getPosition().y,
//                    hitboxComponent.getWidth() / 2,
//                    hitboxComponent.getHeight() / 2,
//                    hitboxComponent.getWidth(),
//                    hitboxComponent.getHeight(),
//                    1.0f,
//                    1.0f,
//                    enemy.getRotationAngle()
//            );
//            //renderHitMark(enemy);
//        }
//    }
//
//    private void renderPlayer() {
//        TextureRegion currentPlayerTexture;
//        StateComponent stateComponent= (StateComponent) space.getPlayer().getComponents().get("state");
//        switch (stateComponent.getState()) {
//            case IDLE:
//                currentPlayerTexture = textureAtlas.findRegion("player");
//                break;
//            case MOVING_LEFT:
//                currentPlayerTexture = textureAtlas.findRegion("playerLeft");
//                break;
//            case MOVING_RIGHT:
//                currentPlayerTexture = textureAtlas.findRegion("playerRight");
//                break;
//            default:
//                currentPlayerTexture = textureAtlas.findRegion("player");
//        }
//        PositionComponent positionComponent= (PositionComponent) space.getPlayer().getComponents().get("position");
//        HitboxComponent hitboxComponent=(HitboxComponent)space.getPlayer().getComponents().get("hitbox");
//        batch.draw(currentPlayerTexture,
//                positionComponent.getPosition().x,
//                positionComponent.getPosition().y,
//                hitboxComponent.getWidth(),
//                hitboxComponent.getHeight()
//        );
//
////        renderHitMark(space.getPlayer());
//    }
//
////    private void renderHitMark(Ship owner) {
////        TextureRegion texture;
////        if (owner.getHitMark() != null) {
////            if (owner == space.getPlayer()) {
////                texture = textureAtlas.findRegion("laserRedShot");
////            } else {
////                texture = textureAtlas.findRegion("laserGreenShot");
////            }
////            batch.draw(texture,
////                    owner.getHitMark().getPosition().x,
////                    owner.getHitMark().getPosition().y,
////                    owner.getHitMark().getWidth(),
////                    owner.getHitMark().getHeight());
////        }
////    }
//
//    private void renderBullets(LinkedList<Bullet> bullets) {
//        TextureRegion texture;
//        for (Bullet bullet : bullets) {
//            if (bullet.getOwner() == space.getPlayer()) {   //player bullets are green
//                texture = textureAtlas.findRegion("laserGreen");
//            } else {
//                texture = textureAtlas.findRegion("laserRed");
//            }
//            PositionComponent positionComponent= (PositionComponent) bullet.getComponents().get("position");
//            HitboxComponent hitboxComponent=(HitboxComponent)bullet.getComponents().get("hitbox");
//            batch.draw(texture,
//                    positionComponent.getPosition().x,
//                    positionComponent.getPosition().y,
//                    hitboxComponent.getWidth() / 2,
//                    hitboxComponent.getHeight() / 2,
//                    hitboxComponent.getWidth(),
//                    hitboxComponent.getHeight(),
//                    1.0f,
//                    1.0f,
//                    bullet.getAngle()
//            );
//        }
//
//    }
//
//    private void renderBackground() {
//        mapRenderer.setView(camera);
//        mapRenderer.render();
//    }
//
//    private void renderLives() {
//        for (int i = 0; i < space.getPlayer().getLives(); i++) {
//            uiBatch.draw(textureAtlas.findRegion("life"),
//                    20 + i * 40,
//                    30
//            );
//        }
//    }
//
//    private void debugInfo() {
//        font.draw(uiBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0,
//                VIRTUAL_HEIGHT);
//
//    }
//
//    private void initTextures() {
//        textureAtlas = assetManager.get("data/png/textures/textures.pack", TextureAtlas.class);
//        levelMap = space.getCurrentMap();
//        levelMap.getTileSets().getTile(1).getTextureRegion().getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Nearest);
//        mapRenderer = new OrthogonalTiledMapRenderer(levelMap, 1);
//    }
//
//    public void setSize(int width, int height) {
//        float aspectRatio = (float) width / (float) height;
//        float scale;
//        Vector2 crop = new Vector2(0f, 0f);
//        if (aspectRatio > ASPECT_RATIO) {
//            scale = (float) height / VIRTUAL_HEIGHT;
//            crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
//        } else if (aspectRatio < ASPECT_RATIO) {
//            scale = (float) width / VIRTUAL_WIDTH;
//            crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
//        } else {
//            scale = (float) width / VIRTUAL_WIDTH;
//        }
//
//        float w = VIRTUAL_WIDTH * scale;
//        float h = VIRTUAL_HEIGHT * scale;
//        viewport = new Rectangle(crop.x, crop.y, w, h);
//
//    }

    @Override
    public void dispose() {
//        mapRenderer.dispose();
//        font.dispose();
//        batch.dispose();
//        shapeRenderer.dispose();
//        uiBatch.dispose();
    }
}
