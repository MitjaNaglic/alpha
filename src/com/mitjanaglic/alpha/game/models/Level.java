package com.mitjanaglic.alpha.game.models;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

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
    private LinkedList<SpawnPoint> spawnPoints;

    public Level(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.spawnPoints = new LinkedList<SpawnPoint>();
        loadLevel();
    }

    private void loadLevel() {
        this.map = assetManager.get("data/levels/Level1/Level1.tmx", TiledMap.class);
        //TODO mipmap background
//        TiledMapTile tile = this.map.getTileSets().getTile(1);
//        if (tile != null) {
//            tile.getTextureRegion().getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Nearest);
//        }
        int width = Integer.parseInt(String.valueOf(map.getProperties().get("width")));
        int tilewidth = Integer.parseInt(String.valueOf(map.getProperties().get("tilewidth")));
        this.levelWidth = width * tilewidth;

        int height = Integer.parseInt(String.valueOf(map.getProperties().get("height")));
        int tileheight = Integer.parseInt(String.valueOf(map.getProperties().get("tileheight")));
        this.levelHeight = height * tileheight;

        processSpawnPoints();
    }

    private void processSpawnPoints() {
        MapObjects mapObjects = map.getLayers().get("spawns").getObjects();
        for (MapObject mapObject : mapObjects) {
            Integer x = (Integer) mapObject.getProperties().get("x");
            Integer y = (Integer) mapObject.getProperties().get("y");
            String entityType = mapObject.getName();

            spawnPoints.add(new SpawnPoint(x, y, entityType));
        }
        Collections.sort(spawnPoints);
    }

    public LinkedList<SpawnPoint> getSpawnPoints() {
        return spawnPoints;
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
