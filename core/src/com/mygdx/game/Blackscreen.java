package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 7/31/2017.
 */
public class Blackscreen extends Image {

    public Blackscreen(SpriteBatch batch) {
        super (
                new Texture("Blackscreen.png"),
                0,
                0,
                1280,
                720,
                0,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {

        if (startScreen.startGame){
            vely++;
            posy-=vely;
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
