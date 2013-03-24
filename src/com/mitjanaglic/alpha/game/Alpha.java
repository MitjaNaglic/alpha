package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mitjanaglic.alpha.game.screens.GameScreen;
import com.mitjanaglic.alpha.game.screens.MenuScreen;
import com.mitjanaglic.alpha.game.screens.PauseScreen;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class Alpha extends Game {


    private Music backgroundMusic;
    private GameScreen gameScreen;
    private MenuScreen mainMenu;
    private PauseScreen pauseScreen;
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        enqueueAssets();
        assetManager.finishLoading();
        mainMenu = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        pauseScreen = new PauseScreen(this);
        setScreen(mainMenu);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data\\music\\Magellan  - Orbyss.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
    }

    private void enqueueAssets() {
        getAssetManager().load("data/png/textures/textures.pack", TextureAtlas.class);
        getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        getAssetManager().load("data/levels/Level1/Level1.tmx", TiledMap.class);
    }

    public void setToGameScreen() {
        if (gameScreen == null) gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void setToMainMenuScreen() {
        gameScreen.dispose();
        gameScreen = null;
        setScreen(mainMenu);
    }

    public void setToPauseScreen() {
        setScreen(pauseScreen);
    }

    @Override
    public void dispose() {
        if (gameScreen != null) gameScreen.dispose();
        pauseScreen.dispose();
        mainMenu.dispose();
        getAssetManager().dispose();

        backgroundMusic.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}