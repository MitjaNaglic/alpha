package com.mitjanaglic.alpha.game.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.postprocessing.effects.Bloom;
import com.bitfire.utils.ShaderLoader;
import com.mitjanaglic.alpha.game.Alpha;
import com.mitjanaglic.alpha.game.components.CameraComponent;
import com.mitjanaglic.alpha.game.components.InputComponent;
import com.mitjanaglic.alpha.game.models.Level;
import com.mitjanaglic.alpha.game.systems.*;
import com.mitjanaglic.alpha.game.systems.ai.DiscAiSystem;
import com.mitjanaglic.alpha.game.systems.ai.ScarabAiSystem;
import com.mitjanaglic.alpha.game.systems.animations.DiscAnimationSystem;
import com.mitjanaglic.alpha.game.systems.animations.PlayerAnimationSystem;
import com.mitjanaglic.alpha.game.systems.renderers.BackgroundRenderingSystem;
import com.mitjanaglic.alpha.game.systems.renderers.SpriteRenderingSystem;
import com.mitjanaglic.alpha.game.systems.renderers.UiRenderingSystem;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 6.3.2013
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen, InputProcessor {
    private Alpha alpha;
    private int height, width;
    private boolean isAccelometerAvailable;
    private AssetManager assetManager;
    private InputComponent inputComponent;
    private CameraComponent cameraComponent;
    private Level level;
    private SpriteRenderingSystem spriteRenderingSystem;
    private BackgroundRenderingSystem backgroundRenderingSystem;
    private UiRenderingSystem uiRenderingSystem;
    private CollisionSystem collisionSystem;
    private LifeSystem lifeSystem;
    private SpawnDespawnSystem spawnDespawnSystem;
    private PostProcessor postProcessor;
    private static final boolean isDesktop = (Gdx.app.getType() == Application.ApplicationType.Desktop);

    private World world;

    public GameScreen(Alpha alpha) {
        this.alpha = alpha;
        assetManager = alpha.getAssetManager();
        isAccelometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

        world = new World();
        world.setManager(new GroupManager());
        world.setManager(new TagManager());
        level = new Level(assetManager);

        initCamera();
        setSystems();
        spawnDespawnSystem.setSpawnPoints(level.getSpawnPoints());
        world.initialize();

        initShaders();
    }

    private void initShaders() {
        ShaderLoader.BasePath = "data/shaders/";
        postProcessor = new PostProcessor(false, false, isDesktop);
        Bloom bloom = new Bloom((int) (Gdx.graphics.getWidth() * 0.25f), (int) (Gdx.graphics.getHeight() * 0.25f));
        bloom.setBlurAmount(0.1f);
        bloom.setBaseIntesity(1f);
        bloom.setBaseSaturation(1f);
        bloom.setThreshold(0.7f);
        postProcessor.addEffect(bloom);
    }

    private void initCamera() {
        Entity camera = world.createEntity();
        cameraComponent = new CameraComponent(level.getLevelWidth() / 2, 400);
        camera.addComponent(cameraComponent);
        world.addEntity(camera);
        world.getManager(TagManager.class).register("camera", camera);
    }

    private void setSystems() {
        world.setSystem(new CameraSystem(cameraComponent));
        world.setSystem(new InputSystem());
        world.setSystem(new PlayerAnimationSystem());
        world.setSystem(new LevelBoundsSystem(cameraComponent));
        world.setSystem(new MovementSystem());
        spriteRenderingSystem = world.setSystem(new SpriteRenderingSystem(assetManager, cameraComponent), true);
        backgroundRenderingSystem = world.setSystem(new BackgroundRenderingSystem(cameraComponent, level), true);
        uiRenderingSystem = world.setSystem(new UiRenderingSystem(assetManager, cameraComponent), true);
        world.setSystem(new HitboxSystem());
        world.setSystem(new GunSystem());
        world.setSystem(new BulletSystem());
        world.setSystem(new DiscAnimationSystem());
        world.setSystem(new DiscAiSystem());
        world.setSystem(new ScarabAiSystem());
        world.setSystem(new HitmarkSystem());
        spawnDespawnSystem = world.setSystem(new SpawnDespawnSystem());
        lifeSystem = world.setSystem(new LifeSystem());
        collisionSystem = world.setSystem(new CollisionSystem(), true);
    }

    @Override
    public void render(float delta) {
        world.setDelta(delta);
        world.process();
        collisionSystem.process();
        lifeSystem.process();

        postProcessor.capture();
        backgroundRenderingSystem.process();
        spriteRenderingSystem.process();
        postProcessor.render();
        uiRenderingSystem.process();

        setInputComponent();
        if (isAccelometerAvailable) checkTilt();

    }

    //zagotavla da input processor dobi inputComponent
    private void setInputComponent() {
        if (inputComponent == null) {
            Gdx.input.setInputProcessor(null);
            TagManager manager = world.getManager(TagManager.class);
            Entity player = manager.getEntity("player");
            InputComponent newInputComponent;
            if (player != null) {
                newInputComponent = player.getComponent(InputComponent.class);
                inputComponent = newInputComponent;
                Gdx.input.setInputProcessor(this);
            }
        }
    }

    @Override
    public void resize(int x, int y) {
        width = x;
        height = y;
        world.getSystem(CameraSystem.class).setSize(width, height);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        alpha.setToPauseScreen();
    }

    @Override
    public void resume() {
        postProcessor.rebind();
    }

    @Override
    public void dispose() {
        world.getSystem(SpriteRenderingSystem.class).dispose();
        backgroundRenderingSystem.dispose();
        uiRenderingSystem.dispose();
        Gdx.input.setInputProcessor(null);
        postProcessor.dispose();
    }

    /*-------------------------------------INPUT PROCESSOR --------------------------*/

    public void checkTilt() {
        // points to the right (when in portrait orientation)
        if (Gdx.input.getAccelerometerX() > 0.5) {
            inputComponent.downPressed();
        } else if (Gdx.input.getAccelerometerX() < -0.5) {
            inputComponent.upPressed();
        } else {
            inputComponent.upReleased();
            inputComponent.downReleased();
        }
        // points upwards (when in portrait orientation)
        if (Gdx.input.getAccelerometerY() > 0.5) {
            inputComponent.rightPressed();
        } else if (Gdx.input.getAccelerometerY() < -0.5) {
            inputComponent.leftPressed();
        } else {
            inputComponent.leftReleased();
            inputComponent.rightReleased();
        }
        //Gdx.input.getAccelerometerZ(); // points to the front of the display (coming out of the screen)

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A)
            inputComponent.leftPressed();
        if (keycode == Input.Keys.D)
            inputComponent.rightPressed();
        if (keycode == Input.Keys.W)
            inputComponent.upPressed();
        if (keycode == Input.Keys.S)
            inputComponent.downPressed();
        if (keycode == Input.Keys.SPACE)
            inputComponent.firePressed();

        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
            this.pause();


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A)
            inputComponent.leftReleased();
        if (keycode == Input.Keys.D)
            inputComponent.rightReleased();
        if (keycode == Input.Keys.W)
            inputComponent.upReleased();
        if (keycode == Input.Keys.S)
            inputComponent.downReleased();
        if (keycode == Input.Keys.SPACE)
            inputComponent.fireReleased();

        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (x < width / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.leftPressed();
        }
        if (x > width / 2) {
            inputComponent.rightPressed();
        }
        if (y > height / 2) {
            inputComponent.downPressed();
        }
        if (y < height / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.upPressed();
        }
        if (y < height / 4 && x < width / 4) {
            inputComponent.firePressed();
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        inputComponent.leftReleased();
        inputComponent.rightReleased();
        inputComponent.downReleased();
        inputComponent.upReleased();
        inputComponent.fireReleased();
        return true;
    }


    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (x < width / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.leftPressed();
        }
        if (x > width / 2) {
            inputComponent.rightPressed();
        }
        if (y > height / 2) {
            inputComponent.downPressed();
        }
        if (y < height / 2 && !(y < height / 4 && x < width / 4)) {
            inputComponent.upPressed();
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scrolled(int i) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
