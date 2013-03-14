package com.mitjanaglic.alpha.models;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 8.3.2013
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class Level {
    private int width;
    private int height;
    private float cameraWidth = 1280f;  //TODO to se uporabla samo za touch evente, neb smel bit tuki
    private float cameraHeight = 720f;

    public Level() {
        loadTestLevel();
    }

    public float getCameraWidth() {
        return cameraWidth;
    }

    public float getCameraHeight() {
        return cameraHeight;
    }

    public void loadTestLevel() {
        width = 1280;
        height = 500000;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
