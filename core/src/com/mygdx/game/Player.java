package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
public class Player extends Entity {

    public Player(SpriteBatch batch) {
        super(
                new Texture("badlogic.jpg"),
                500,
                50,
                100,
                100,
                0,
                0,
                0,
                batch
        );
    }


    //Methods

    public void update(float delta) {

        posx += velx;

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velx = -CPlayer.Spaceshipspeed;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velx = CPlayer.Spaceshipspeed;
        }

        //Prevents player from moving when button not pressed
        else {velx = 0;}
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {

    }


}
