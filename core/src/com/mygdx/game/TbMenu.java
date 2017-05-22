package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Matthew on 5/22/2017.
 */

public class TbMenu extends Skin{
    public TbMenu() {
        BitmapFont font = new BitmapFont();
        this.add("default", font);
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        this.add("background", new Texture(pixmap));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = this.newDrawable("background", Color.BLACK);
        textButtonStyle.over = this.newDrawable("background", Color.WHITE);
        textButtonStyle.font = this.getFont("default");
        this.add("default", textButtonStyle);
    }

    public Skin getSkin() {
        return this;
    }
}