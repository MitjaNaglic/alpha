package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mitjanaglic.alpha.game.screens.GameScreen;
import com.mitjanaglic.alpha.game.screens.MenuScreen;

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

    @Override
    public void create() {
        mainMenu = new MenuScreen(this);
        gameScreen = new GameScreen();
        setScreen(mainMenu);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data\\music\\Magellan  - Orbyss.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();

    }

    public void setGameScreen() {
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
        backgroundMusic.dispose();
    }
}
