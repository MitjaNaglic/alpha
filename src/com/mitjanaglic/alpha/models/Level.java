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

    public Level() {
        loadTestLevel();
    }

    public void loadTestLevel() {
        width = 1270;
        height = 500000;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
