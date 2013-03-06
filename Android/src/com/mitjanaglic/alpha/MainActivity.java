package com.mitjanaglic.alpha;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends AndroidApplication {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new Alpha(), false);
    }
}