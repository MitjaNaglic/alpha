package com.mitjanaglic.alpha.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mitjanaglic.alpha.game.constants.ids;
import com.mitjanaglic.alpha.game.utils.ParticleEffectLoader;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.5.2013
 * Time: 15:18
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class Assets {
    private static AssetManager assetManager = new AssetManager();
    private static BitmapFont menuFont;
    private static BitmapFont titleFont;
    private static BitmapFont debugFont;

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static void Load() {
        assetManager.load("data/png/textures/textures.atlas", TextureAtlas.class);

        //custom loader
        assetManager.setLoader(ParticleEffect.class, new ParticleEffectLoader(new InternalFileHandleResolver()));
        assetManager.load("data/particles/explosion.p", ParticleEffect.class);

        loadFonts();
        assetManager.finishLoading();
    }

    public static void loadLevel(int level) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.generateMipMaps = true;
        parameters.textureMinFilter = Texture.TextureFilter.MipMapLinearNearest;
        parameters.textureMagFilter = Texture.TextureFilter.Linear;
        assetManager.load(ids.createLevelId(level), TiledMap.class, parameters);

        //        TiledMap levelMap=assetManager.get("data/levels/Level1/Level1.tmx");
//        //TiledMap levelMap=new TmxMapLoader().load("data/levels/Level1/Level1.tmx");
//        TiledMapTileLayer l= (TiledMapTileLayer) levelMap.getLayers().get(0);
//        System.out.println(l.getName());
//        if (l.getCell(2,2)==null) System.out.println("null cell");
//        System.out.println(l.getCell(0, 0).toString());
//        System.out.println(l.getCell(0, 0).getTile().getId());
//        System.out.println(l.getCell(0, 0).getTile().toString());
//
//        TiledMapTileLayer l1= (TiledMapTileLayer) levelMap.getLayers().get(1);
//        System.out.println(l1.getName());
//        if (l1.getCell(2,2)==null) System.out.println("null cell");
//        System.out.println(l1.getCell(2,2).toString());
//        System.out.println(l1.getCell(2, 2).getTile().getId());
    }

    private static void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/font/NEUROPOL.ttf"));
        menuFont = generator.generateFont(Gdx.graphics.getHeight() / 25);
        titleFont = generator.generateFont(Gdx.graphics.getHeight() / 10);
        debugFont = generator.generateFont(Gdx.graphics.getHeight() / 30);
        generator.dispose();
    }

    public static BitmapFont getMenuFont() {
        return menuFont;
    }

    public static BitmapFont getTitleFont() {
        return titleFont;
    }

    public static BitmapFont getDebugFont() {
        return debugFont;
    }

    public static void dispose() {
        menuFont.dispose();
        titleFont.dispose();
        assetManager.dispose();
    }
}
