package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

    @Override
    public void create() {
        mainMenu = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        pauseScreen = new PauseScreen(this);
        setScreen(mainMenu);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data\\music\\Magellan  - Orbyss.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();

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

        backgroundMusic.dispose();
    }
}
