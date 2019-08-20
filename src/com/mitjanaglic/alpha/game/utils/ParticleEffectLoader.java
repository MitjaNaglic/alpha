package com.mitjanaglic.alpha.game.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Array;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 20.5.2013
 * Time: 15:09
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ParticleEffectLoader extends SynchronousAssetLoader<ParticleEffect, ParticleEffectLoader.ParticleEffectParameter> {

    public ParticleEffectLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public ParticleEffect load(AssetManager assetManager, String fileName, FileHandle fileHandle,
                               ParticleEffectParameter parameter) {
        ParticleEffect effect = new ParticleEffect();
        FileHandle effectFile = resolve(fileName);
        FileHandle imgDir = effectFile.parent();
        effect.load(effectFile, imgDir);
        return effect;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle fileHandle,
                                                  ParticleEffectParameter parameter) {
        return null;
    }

    static public class ParticleEffectParameter extends AssetLoaderParameters<ParticleEffect> {
        public ParticleEffectParameter() {
        }
    }

}
