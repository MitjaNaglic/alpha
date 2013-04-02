package com.mitjanaglic.alpha.game.models;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 8.3.2013
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class Level {
    private int levelWidth;
    private int levelHeight;
    private TiledMap map;
    private AssetManager assetManager;

    public Level(AssetManager assetManager) {
        this.assetManager = assetManager;

        loadLevel();
    }

    private void loadLevel() {
        this.map = assetManager.get("data/levels/Level1/Level1.tmx", TiledMap.class);

        int width = Integer.parseInt(String.valueOf(map.getProperties().get("width")));
        int tilewidth = Integer.parseInt(String.valueOf(map.getProperties().get("tilewidth")));
        this.levelWidth = width * tilewidth;

        int height = Integer.parseInt(String.valueOf(map.getProperties().get("height")));
        int tileheight = Integer.parseInt(String.valueOf(map.getProperties().get("tileheight")));
        this.levelHeight = height * tileheight;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public TiledMap getMap() {
        return map;
    }

    public int getLevelHeight() {
        return levelHeight;
    }
}
