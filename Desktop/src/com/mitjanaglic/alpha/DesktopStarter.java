package com.mitjanaglic.alpha;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mitjanaglic.alpha.game.Alpha;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class DesktopStarter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.useGL30 = true;
        config.vSyncEnabled = false;
        config.title = "Alpha";
        config.width = 1280;
        config.height = 720;
        config.fullscreen = false;
        config.resizable = false;
        config.foregroundFPS = 0;
        config.addIcon("data/png/icon/ic_launcher.png", Files.FileType.Local);
        new LwjglApplication(new Alpha(), config);
    }
}
