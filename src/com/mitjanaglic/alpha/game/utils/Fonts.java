package com.mitjanaglic.alpha.game.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 26.5.2013
 * Time: 16:58
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class Fonts implements Disposable {
    private BitmapFont menuFont;
    private BitmapFont titleFont;
    private BitmapFont debugFont;

    public Fonts(BitmapFont menuFont, BitmapFont titleFont, BitmapFont debugFont) {
        this.menuFont = menuFont;
        this.titleFont = titleFont;
        this.debugFont = debugFont;
    }

    public BitmapFont getMenuFont() {
        return menuFont;
    }

    public void setMenuFont(BitmapFont menuFont) {
        this.menuFont = menuFont;
    }

    public BitmapFont getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(BitmapFont titleFont) {
        this.titleFont = titleFont;
    }

    public BitmapFont getDebugFont() {
        return debugFont;
    }

    public void setDebugFont(BitmapFont debugFont) {
        this.debugFont = debugFont;
    }

    @Override
    public void dispose() {
        debugFont.dispose();
        titleFont.dispose();
        menuFont.dispose();
    }
}
