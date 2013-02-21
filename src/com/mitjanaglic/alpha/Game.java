package com.mitjanaglic.alpha;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.Input.Keys;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class Game implements ApplicationListener {

    private Texture playerTexture;
    private Music backgroundMusic;
    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle player;

    @Override
    public void create() {
        playerTexture = new Texture("data\\png\\player.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data\\music\\Magellan  - Orbyss.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch = new SpriteBatch();

        player = new Rectangle();
        player.x = 1280 / 2 - 48 / 2;
        player.y = 400;
        player.width = 48;
        player.height = 48;
    }

    @Override
    public void resize(int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playerTexture, player.x, player.y);
        batch.end();

        Vector3 touchPos;
        if (Gdx.input.isTouched()) {
            touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.x = touchPos.x - 48 / 2;
            player.y = touchPos.y - 48 / 2;
        }

        if (Gdx.input.isKeyPressed(Keys.A)) player.x -= 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.D)) player.x += 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.W)) player.y += 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.S)) player.y -= 400 * Gdx.graphics.getDeltaTime();

        if (player.x < 0) player.x = 0;
        if (player.x > 1280 - 48) player.x = 1280 - 48;

        if (player.y < 0) player.y = 0;
        if (player.y > 720 - 48) player.y = 720 - 48;
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
        playerTexture.dispose();
        backgroundMusic.dispose();
        batch.dispose();
    }
}
