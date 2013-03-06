package com.mitjanaglic.alpha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mitjanaglic.alpha.entities.Player;
import com.mitjanaglic.alpha.screens.GameScreen;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class Alpha extends Game {


    private Music backgroundMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Integer resX = 1280;
    private Integer resY = 720;
    private Texture playerTexture;
    private GameScreen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 1280, 720);
//        playerTexture = new Texture("data\\png\\player.png");
//        player = new Player(playerTexture,
//                new Vector2(resX / 2 - playerTexture.getWidth() / 2, resY / 6 - playerTexture.getHeight() / 2));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data\\music\\Magellan  - Orbyss.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
//
//
//        batch = new SpriteBatch();
    }

    //    @Override
//    public void resize(int i, int i2) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
//        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//        camera.update();
//
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
//        batch.end();
//
//        Update();
//    }
//
//    @Override
//    public void pause() {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void resume() {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    private void Update() {
//        player.Update();
//    }
//
    @Override
    public void dispose() {
        gameScreen.dispose();
//        playerTexture.dispose();
        backgroundMusic.dispose();
//        batch.dispose();
    }
}
