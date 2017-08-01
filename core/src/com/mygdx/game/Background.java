package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 7/31/2017.
 */
public class Background extends Image{

    public Background(SpriteBatch batch) {
        super (
                new Texture("bg.jpg"),
                0,
                0,
                MyGdxGame.V_WIDTH,
                MyGdxGame.V_HEIGHT,
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
