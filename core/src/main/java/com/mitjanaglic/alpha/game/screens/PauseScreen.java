package com.mitjanaglic.alpha.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mitjanaglic.alpha.game.Alpha;
import com.mitjanaglic.alpha.game.Assets;
import com.mitjanaglic.alpha.game.utils.Fonts;

/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 23.3.2013
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class PauseScreen implements Screen {
    private Stage stage;
    private TextureAtlas textureAtlas;
    private Fonts fonts;
    private Alpha alpha;
    private Screen sender;
    private Screen self = this;

    public PauseScreen(Alpha alpha) {
        this.alpha = alpha;
        stage = new Stage();
        fonts = Assets.getAssetManager().get("data/font/NEUROPOL.ttf");
        loadData();
        createLayout();
    }

    private void createLayout() {
        Table table = new Table();
        table.setFillParent(true);
        table.setTransform(true);
        stage.addActor(table);

        TextureRegion upRegion = textureAtlas.findRegion("ui/buttonDefault");
        TextureRegion downRegion = textureAtlas.findRegion("ui/buttonSelected");

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(upRegion);
        textButtonStyle.down = new TextureRegionDrawable(downRegion);
        textButtonStyle.font = fonts.getMenuFont();
        textButtonStyle.fontColor = new Color(0, 0, 0, 1);


        TextButton button1 = new TextButton("Resume", textButtonStyle);
        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                alpha.setToGameScreen(self);
            }
        });
        table.row().padBottom(20);
        table.add(button1);

        TextButton button2 = new TextButton("Options", textButtonStyle);
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                alpha.setToOptionsScreen(self);
            }
        });
        table.row().padBottom(20);
        table.add(button2);

        TextButton button3 = new TextButton("Exit to main menu", textButtonStyle);
        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                alpha.setToMainMenuScreen(self);
            }
        });
        table.row().padBottom(20);
        table.add(button3);
    }

    private void loadData() {
        this.textureAtlas = Assets.getAssetManager().get("data/png/textures/textures.atlas", TextureAtlas.class);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.369f, 0.247f, 0.42f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        //Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        stage.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
