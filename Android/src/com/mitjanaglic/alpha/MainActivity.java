package com.mitjanaglic.alpha;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mitjanaglic.alpha.game.Alpha;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 21.2.2013
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends AndroidApplication {
    private Alpha alpha;

    public void onCreate(Bundle savedInstanceState) {
        Log.w("alpha", "TEST BLA");
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        alpha = new Alpha();
        initialize(alpha, config);
    }
}