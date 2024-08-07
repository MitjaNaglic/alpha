package com.mitjanaglic.alpha.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
 * Date: 20.5.2013
 * Time: 19:48
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class LevelOverScreen implements Screen {
    private Stage stage;
    private TextureAtlas textureAtlas;
    private Fonts fonts;
    private Alpha alpha;
    private Screen self = this;

    public LevelOverScreen(Alpha alpha) {
        this.alpha = alpha;
        stage = new Stage();
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
        fonts = Assets.getAssetManager().get("data/font/NEUROPOL.ttf");

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(upRegion);
        textButtonStyle.down = new TextureRegionDrawable(downRegion);
        textButtonStyle.font = fonts.getMenuFont();
        textButtonStyle.fontColor = new Color(0, 0, 0, 1);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fonts.getMenuFont();

        Label levelDoneLabel = new Label("Level completed.", labelStyle);
        table.row().padBottom(200);
        table.add(levelDoneLabel);

        TextButton button3 = new TextButton("Return to main menu", textButtonStyle);
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
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(width, height, true);
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
        Gdx.input.setInputProcessor(null);
        stage.dispose();
    }
}
