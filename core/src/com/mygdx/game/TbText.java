package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Matthew on 5/22/2017.
 */

public class TbText extends TextButton {
    String _text;
    public TbText(String text, Skin skin) {
        super(text, skin);
        _text = text;
        this.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println(_text);
            }
        });
    }
}