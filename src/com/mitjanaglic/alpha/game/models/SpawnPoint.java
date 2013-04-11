package com.mitjanaglic.alpha.game.models;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 11.4.2013
 * Time: 17:46
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class SpawnPoint {
    private int x;
    private int y;
    private String entityId;

    public SpawnPoint(int x, int y, String entityId) {
        this.x = x;
        this.y = y;
        this.entityId = entityId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
