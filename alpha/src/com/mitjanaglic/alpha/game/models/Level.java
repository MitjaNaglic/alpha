package com.mitjanaglic.alpha.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.mitjanaglic.alpha.game.Assets;
import com.mitjanaglic.alpha.game.constants.ids;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 8.3.2013
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class Level {
    private float levelWidth;
    private float levelHeight;
    private TiledMap map;
    private LinkedList<SpawnPoint> spawnPoints;
    private float levelEnd;
    private int currentLevelNum;

    public Level() {
        this.spawnPoints = new LinkedList<SpawnPoint>();
    }

    public void loadLevel(int level) {
        if (map != null)
            Assets.getAssetManager().unload(ids.createLevelId(currentLevelNum));
        currentLevelNum = level;
        Assets.loadLevel(level);
        Assets.getAssetManager().finishLoading();
        this.map = Assets.getAssetManager().get(ids.createLevelId(level), TiledMap.class);
        TiledMapTile tile = this.map.getTileSets().getTile(1);
        if (tile != null) {
            tile.getTextureRegion().getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Nearest);
        }
        int width = Integer.parseInt(String.valueOf(map.getProperties().get("width")));
        int tilewidth = Integer.parseInt(String.valueOf(map.getProperties().get("tilewidth")));
        this.levelWidth = width * tilewidth;

        int height = Integer.parseInt(String.valueOf(map.getProperties().get("height")));
        int tileheight = Integer.parseInt(String.valueOf(map.getProperties().get("tileheight")));
        this.levelHeight = height * tileheight;

        processSpawnPoints();
        processLevelEnd();
    }

    private void processLevelEnd() {
        MapObjects miscObjects = map.getLayers().get("misc").getObjects();
        this.levelEnd = (Float) miscObjects.get("levelEnd").getProperties().get("y");
    }

    private void processSpawnPoints() {
        MapObjects mapObjects = map.getLayers().get("spawns").getObjects();
        for (MapObject mapObject : mapObjects) {
            float x = (Float) mapObject.getProperties().get("x");
            float y = (Float) mapObject.getProperties().get("y");
            String entityType = mapObject.getName();

            spawnPoints.add(new SpawnPoint(x, y, entityType));
        }
        Collections.sort(spawnPoints);
    }

    public LinkedList<SpawnPoint> getSpawnPoints() {
        return spawnPoints;
    }

    public float getLevelWidth() {
        return levelWidth;
    }

    public TiledMap getMap() {
        return map;
    }

    public float getLevelHeight() {
        return levelHeight;
    }

    public float getLevelEnd() {
        return levelEnd;
    }
}
