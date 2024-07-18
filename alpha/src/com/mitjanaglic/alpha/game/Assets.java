package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mitjanaglic.alpha.game.constants.ids;
import com.mitjanaglic.alpha.game.utils.Fonts;
import com.mitjanaglic.alpha.game.utils.GameFontLoader;
import com.mitjanaglic.alpha.game.utils.ParticleEffectLoader;
import com.mitjanaglic.alpha.game.utils.ShaderLoader;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.5.2013
 * Time: 15:18
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class Assets {
    private static AssetManager assetManager;

    public static AssetManager getAssetManager() {
        if (assetManager == null) assetManager = new AssetManager();
        return assetManager;
    }

    public static void Load() {
        if (assetManager == null) assetManager = new AssetManager();
        assetManager.load("data/png/textures/textures.atlas", TextureAtlas.class);

        //custom loader
        assetManager.setLoader(ParticleEffect.class, new ParticleEffectLoader(new InternalFileHandleResolver()));
        assetManager.load("data/particles/explosion.p", ParticleEffect.class);
        assetManager.setLoader(Fonts.class, new GameFontLoader(new InternalFileHandleResolver()));
        GameFontLoader.GameFontParameters gameFontParameters = new GameFontLoader.GameFontParameters();
        assetManager.load("data/font/NEUROPOL.ttf", Fonts.class, gameFontParameters);
        assetManager.load("data/music/Magellan  - Orbyss.ogg", Music.class);
        assetManager.setLoader(ShaderProgram.class, new ShaderLoader(new InternalFileHandleResolver()));


        assetManager.finishLoading();
    }


    public static void loadLevel(int level) {
        if (assetManager == null) assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.generateMipMaps = true;
        parameters.textureMinFilter = Texture.TextureFilter.MipMapLinearNearest;
        parameters.textureMagFilter = Texture.TextureFilter.Linear;
        assetManager.load(ids.createLevelId(level), TiledMap.class, parameters);

        //debuggery
//        assetManager.finishLoading();
//        TiledMap levelMap=assetManager.get(ids.createLevelId(level));
//        TiledMapTileLayer l= (TiledMapTileLayer) levelMap.getLayers().get(0);
//        System.out.println("layer: "+l.getName());
//        if (l.getCell(0,0)==null) System.out.println("null cell");
//        System.out.println(l.getCell(0, 0).toString());
//        System.out.println(l.getCell(0, 0).getTile().getId());
//        System.out.println(l.getCell(0, 0).getTile().toString());
//
//        TiledMapTileLayer l1= (TiledMapTileLayer) levelMap.getLayers().get(1);
//        System.out.println("layer: "+l1.getName());
//        if (l1.getCell(2,2)==null) System.out.println("null cell");
//        System.out.println(l1.getCell(2,2).toString());
//        System.out.println(l1.getCell(2, 2).getTile().getId());
    }


    public static void dispose() {
        assetManager.dispose();
        assetManager = null;
    }
}
