package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mitjanaglic.alpha.game.constants.Assets;
import com.mitjanaglic.alpha.game.screens.GameScreen;
import com.mitjanaglic.alpha.game.screens.MenuScreen;
import com.mitjanaglic.alpha.game.screens.OptionsScreen;
import com.mitjanaglic.alpha.game.screens.PauseScreen;
import com.mitjanaglic.alpha.game.utils.ParticleEffectLoader;

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
    private OptionsScreen optionsScreen;
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = Assets.getAssetManager();
        enqueueAssets();
        assetManager.finishLoading();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data\\music\\Magellan  - Orbyss.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.8f);
        backgroundMusic.play();
        mainMenu = new MenuScreen(this);
        pauseScreen = new PauseScreen(this);
        optionsScreen = new OptionsScreen(this);
        setScreen(mainMenu);
    }

    public void setSoundVolume(float volume) {
        backgroundMusic.setVolume(volume);
    }

    public float getSoundVolume() {
        return backgroundMusic.getVolume();
    }

    private void enqueueAssets() {
        assetManager.load("data/png/textures/textures.atlas", TextureAtlas.class);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.generateMipMaps = true;
//        parameters.textureMinFilter= Texture.TextureFilter.MipMapLinearNearest;
//        parameters.textureMagFilter= Texture.TextureFilter.Linear;
        assetManager.load("data/levels/Level1/Level1.tmx", TiledMap.class, parameters);
        //custom loader
        assetManager.setLoader(ParticleEffect.class, new ParticleEffectLoader(new InternalFileHandleResolver()));
        assetManager.load("data/particles/explosion.p", ParticleEffect.class);
    }

    public void setToGameScreen() {
        if (gameScreen == null) gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void setToMainMenuScreen() {
        if (gameScreen != null) {
            gameScreen.dispose();
            gameScreen = null;
        }
        setScreen(mainMenu);
    }

    public void setToOptionsScreen() {
        setScreen(optionsScreen);
    }

    public void setToPauseScreen() {
        setScreen(pauseScreen);
    }

    @Override
    public void dispose() {
        if (gameScreen != null) gameScreen.dispose();
        pauseScreen.dispose();
        mainMenu.dispose();
        optionsScreen.dispose();
        assetManager.dispose();

        backgroundMusic.dispose();
    }
}
