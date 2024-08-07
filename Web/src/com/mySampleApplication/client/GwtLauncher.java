package com.mySampleApplication.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mitjanaglic.alpha.game.Alpha;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.3.2013
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(480, 320);
        return cfg;
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return new Alpha();
    }

    @Override
    public void log(String s, String s2, Throwable throwable) {

    }

    @Override
    public int getLogLevel() {
        return 0;
    }
}