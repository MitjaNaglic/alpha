package com.mitjanaglic.alpha.game.constants;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 16:58
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class ids {
    public static final String PLAYER = "player";
    public static final String ENEMY = "enemy";
    public static final String DISC = "disc";
    public static final String MINOS = "minos";
    public static final String SCARAB = "scarab";
    public static final String PLAYER_BULLETS = "playerBullets";
    public static final String ENEMY_BULLETS = "enemyBullets";
    public static final String HITMARK = "hitmark";
    public static final String METEOR = "meteor";
    public static final String METEOR_SMALL = "meteorSmall";

    public static String createLevelId(int levelNum) {
        return "data/levels/Level" + levelNum + "/Level" + levelNum + ".tmx";
    }
}
