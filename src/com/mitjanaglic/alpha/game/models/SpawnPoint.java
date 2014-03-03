package com.mitjanaglic.alpha.game.models;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 11.4.2013
 * Time: 17:46
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class SpawnPoint implements Comparable<SpawnPoint> {
    private float x;
    private float y;
    private String entityId;

    public SpawnPoint(float x, float y, String entityId) {
        this.x = x;
        this.y = y;
        this.entityId = entityId;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
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

    @Override
    public int compareTo(SpawnPoint spawnPoint) {
        int returnValue;
        if (this.getY() == spawnPoint.getY()) returnValue = 0;
        else if (this.getY() > spawnPoint.getY()) returnValue = 1;
        else returnValue = -1;
        return returnValue;
    }
}
