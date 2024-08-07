package com.mitjanaglic.alpha.game.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.7.2013
 * Time: 19:02
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class ShaderLoader extends SynchronousAssetLoader<ShaderProgram, ShaderLoader.ShaderProgramParameter> {
    public ShaderLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public ShaderProgram load(AssetManager assetManager, String name, FileHandle fileHandle, ShaderLoader.ShaderProgramParameter shaderProgramParameter) {
        //read the files into strings
        final String VERTEX = resolve("data/shaders/" + name + ".vert").readString();
        final String FRAGMENT = resolve("data/shaders/" + name + ".frag").readString();


        ShaderProgram shaderProgram = new ShaderProgram(VERTEX, FRAGMENT);
        if (!shaderProgram.isCompiled())
            throw new GdxRuntimeException("could not compile shader: " + shaderProgram.getLog());

        return shaderProgram;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String s, FileHandle fileHandle, ShaderLoader.ShaderProgramParameter shaderProgramParameter) {
        return null;
    }

    static public class ShaderProgramParameter extends AssetLoaderParameters<ShaderProgram> {
        public ShaderProgramParameter() {
        }
    }
}
