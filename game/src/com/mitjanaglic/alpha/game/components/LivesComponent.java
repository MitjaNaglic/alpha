package com.mitjanaglic.alpha.game.components;

import com.artemis.Component;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 2.4.2013
 * Time: 14:40
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class LivesComponent extends Component {
    private int lives;

    public LivesComponent(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}
