package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
public class Invaders extends Entity{

    //Need sprite page.

    public Invaders(SpriteBatch batch) {
        super(
                new Texture("badlogic.jpg"),
                0,
                0,
                0,
                0,
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
    @Override
    public void handleCollision(Entity e) {

    }
}
