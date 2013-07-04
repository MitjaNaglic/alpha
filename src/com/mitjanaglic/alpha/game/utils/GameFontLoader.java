package com.mitjanaglic.alpha.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 26.5.2013
 * Time: 17:02
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class GameFontLoader extends SynchronousAssetLoader<Fonts, GameFontLoader.GameFontParameters> {
    public GameFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Fonts load(AssetManager assetManager, String fileName, GameFontLoader.GameFontParameters gameFontParameters) {
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(resolve(fileName));
        Fonts fonts = new Fonts(freeTypeFontGenerator.generateFont(gameFontParameters.menuFontSize),
                freeTypeFontGenerator.generateFont(gameFontParameters.titleFontSize),
                freeTypeFontGenerator.generateFont(gameFontParameters.debugFontSize));
        return fonts;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String s, GameFontLoader.GameFontParameters gameFontParameters) {
        return null;
    }

    public static class GameFontParameters extends AssetLoaderParameters<Fonts> {
        public int menuFontSize = calcFontPxSize(30);
        public int debugFontSize = calcFontPxSize(25);
        public int titleFontSize = calcFontPxSize(80);

        private int calcFontPxSize(int size) {
            return (Gdx.graphics.getHeight() * size) / 1000;
        }
    }
}