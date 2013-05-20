package com.mitjanaglic.alpha.game.constants;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static void Load() {
        assetManager.load("data/png/textures/textures.atlas", TextureAtlas.class);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.generateMipMaps = true;
//        parameters.textureMinFilter= Texture.TextureFilter.MipMapLinearNearest;
//        parameters.textureMagFilter= Texture.TextureFilter.Linear;
        assetManager.load("data/levels/Level1/Level1.tmx", TiledMap.class, parameters);
        //custom loader
        assetManager.setLoader(ParticleEffect.class, new ParticleEffectLoader(new InternalFileHandleResolver()));
        assetManager.load("data/particles/explosion.p", ParticleEffect.class);
    }
}
