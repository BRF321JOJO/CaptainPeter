package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 7/31/2017.
 */
public class Deathscreen extends Image{

    static int width = 335;
    static int height = 335;

    public Deathscreen(SpriteBatch batch) {
        super (
                new Texture("Deathscreen.png"),
                (MyGdxGame.V_WIDTH - width)/2,
                (MyGdxGame.V_HEIGHT - width)/2,
                width,
                height,
                0,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
