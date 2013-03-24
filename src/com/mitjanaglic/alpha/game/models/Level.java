package com.mitjanaglic.alpha.game.models;

import com.badlogic.gdx.maps.tiled.TiledMap;

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
    private TiledMap map;

    public Level(TiledMap map) {
        this.map = map;
        loadLevel();
    }

    public float getCameraWidth() {
        return cameraWidth;
    }

    public float getCameraHeight() {
        return cameraHeight;
    }

    public void loadLevel() {
        int width = Integer.parseInt(String.valueOf(map.getProperties().get("width")));
        int tilewidth = Integer.parseInt(String.valueOf(map.getProperties().get("tilewidth")));
        this.width = width * tilewidth;

        int height = Integer.parseInt(String.valueOf(map.getProperties().get("height")));
        int tileheight = Integer.parseInt(String.valueOf(map.getProperties().get("tileheight")));
        this.height = height * tileheight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
