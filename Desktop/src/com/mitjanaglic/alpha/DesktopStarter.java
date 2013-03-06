package com.mitjanaglic.alpha;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class DesktopStarter {
    public static void main(String[] args) {
        new LwjglApplication(new Alpha(), "Alpha", 1280, 720, true);
    }
}
