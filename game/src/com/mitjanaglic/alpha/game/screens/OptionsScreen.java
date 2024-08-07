package com.mitjanaglic.alpha.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mitjanaglic.alpha.game.Alpha;
import com.mitjanaglic.alpha.game.Assets;
import com.mitjanaglic.alpha.game.utils.Fonts;
import com.mitjanaglic.alpha.game.utils.IReturnToSender;


/**
 * Created with IntelliJ IDEA.
 * User: mito
 * Date: 14.4.2013
 * Time: 18:34
 * Mitja Naglic  mitja.n1@gmail.com
 */
public class OptionsScreen implements Screen, IReturnToSender {
    private Stage stage;
    private TextureAtlas textureAtlas;
    private Fonts fonts;
    private Alpha alpha;
    private Screen sender;

    public OptionsScreen(Alpha alpha) {
        this.alpha = alpha;
        this.stage = new Stage();
        this.textureAtlas = Assets.getAssetManager().get("data/png/textures/textures.atlas", TextureAtlas.class);
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
        textButtonStyle.fontColor = new Color(0f, 0f, 0f, 1);

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = fonts.getMenuFont();
        TextureRegion checkboxCheckedRegion = textureAtlas.findRegion("ui/checkBoxChecked");
        TextureRegion checkboxUncheckedRegion = textureAtlas.findRegion("ui/checkBoxUnchecked");
        checkBoxStyle.checkboxOn = new TextureRegionDrawable(checkboxCheckedRegion);
        checkBoxStyle.checkboxOff = new TextureRegionDrawable(checkboxUncheckedRegion);

        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            CheckBox checkBox1 = new CheckBox("Enable Vsync", checkBoxStyle);
            checkBox1.setChecked(true);
            checkBox1.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    if (((CheckBox) actor).isChecked()) {
                        Gdx.graphics.setVSync(true);
                    } else {
                        Gdx.graphics.setVSync(false);
                    }
                }
            });
            table.row().align(Align.left).padBottom(40);
            table.add(checkBox1);

            CheckBox checkBox2 = new CheckBox("Fullscreen", checkBoxStyle);
            checkBox2.setChecked(Gdx.graphics.isFullscreen());
            checkBox2.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    if (((CheckBox) actor).isChecked()) {
                        Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode());
                    } else {
                        Gdx.graphics.setDisplayMode(1280, 720, false);
                    }
                }
            });
            table.row().align(Align.left).padBottom(100);
            table.add(checkBox2);
        }

        Label.LabelStyle labelStyle = new Label.LabelStyle(fonts.getMenuFont(), Color.WHITE);
        Label volumeLabel = new Label("Volume", labelStyle);
        table.row().padBottom(20);
        table.add(volumeLabel);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        TextureRegion sliderBackgroundRegion = textureAtlas.findRegion("ui/sliderBackground");
        TextureRegion sliderKnobRegion = textureAtlas.findRegion("ui/sliderKnob");
        sliderStyle.background = new TextureRegionDrawable(sliderBackgroundRegion);
        sliderStyle.knob = new TextureRegionDrawable(sliderKnobRegion);
//        sliderStyle.knobAfter = new TextureRegionDrawable(upRegion);
//        sliderStyle.knobBefore = new TextureRegionDrawable(upRegion);
        Slider slider = new Slider(0f, 1f, 0.01f, false, sliderStyle);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                alpha.setSoundVolume(((Slider) actor).getValue());
            }
        });
        slider.setHeight(20);
        slider.setWidth(200);
        slider.setValue(0);
        slider.setName("Sound volume");
        table.row().minWidth(200).padBottom(100);

        table.add(slider);
        slider.setValue(alpha.getSoundVolume());

        TextButton button3 = new TextButton("Back", textButtonStyle);
        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                alpha.setToScreen(sender);
            }
        });
        table.row().padBottom(20);
        table.add(button3);
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

    @Override
    public void setSender(Screen screen) {
        sender = screen;
    }
}
