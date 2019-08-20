package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 4.4.2013
 * Time: 9:06
 * Mitja Naglič  mitja.n1@gmail.com
 */
public class WeaponsArrayComponent extends Component {
    private ArrayList<GunComponent> weaponsArray;

    public WeaponsArrayComponent() {
        weaponsArray = new ArrayList<GunComponent>();
    }

    public ArrayList<GunComponent> getWeaponsArray() {
        return weaponsArray;
    }

    public void setWeaponsArray(ArrayList<GunComponent> weaponsArray) {
        this.weaponsArray = weaponsArray;
    }
}
