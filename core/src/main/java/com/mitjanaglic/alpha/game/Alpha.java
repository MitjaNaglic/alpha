package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.mitjanaglic.alpha.game.screens.*;

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
    private LevelOverScreen levelOverScreen;
    private GameOverScreen gameOverScreen;

    @Override
    public void create() {
        Assets.Load();
        backgroundMusic = Assets.getAssetManager().get("data/music/Magellan  - Orbyss.ogg");
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.8f);
        backgroundMusic.play();
        mainMenu = new MenuScreen(this);
        pauseScreen = new PauseScreen(this);
        optionsScreen = new OptionsScreen(this);
        levelOverScreen = new LevelOverScreen(this);
        gameOverScreen = new GameOverScreen(this);
        setScreen(mainMenu);
    }

    public void setSoundVolume(float volume) {
        backgroundMusic.setVolume(volume);
    }

    public float getSoundVolume() {
        return backgroundMusic.getVolume();
    }

    public void setToGameScreen(Screen sender) {
        if (gameScreen == null) gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void setToMainMenuScreen(Screen sender) {
        if (gameScreen != null) {
            gameScreen.dispose();
            gameScreen = null;
        }
        setScreen(mainMenu);
    }

    public void setToOptionsScreen(Screen sender) {
        optionsScreen.setSender(sender);
        setScreen(optionsScreen);
    }

    public void setToPauseScreen(Screen sender) {
        setScreen(pauseScreen);
    }

    public void setToScreen(Screen screen) {
        setScreen(screen);
    }

    public void setToLevelOverScreen() {
        setScreen(levelOverScreen);
    }

    public void setToGameOverScreen() {
        setScreen(gameOverScreen);
    }

    @Override
    public void dispose() {
        if (gameScreen != null) gameScreen.dispose();
        pauseScreen.dispose();
        mainMenu.dispose();
        optionsScreen.dispose();
        levelOverScreen.dispose();
        gameOverScreen.dispose();
        Assets.dispose();
        backgroundMusic.stop();
    }
}
