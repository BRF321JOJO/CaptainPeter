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

    static boolean InBound;
    static int Spaceshipspeed = 4;
    static int playerposy = 10;
    static int shipwidth = 50;

    public Player(SpriteBatch batch) {
        super(
                new Texture("Petersmiletransparent.png"),
                //Posx will begin at calculated middle of screen
                MyGdxGame.V_WIDTH/2 - shipwidth/2,
                playerposy,
                shipwidth,
                50,
                0,
                0,
                2,
                batch
        );
    }

    //Methods

    public void update(float delta) {

        //Defines InBound
        if (posx >= 0 && posx <= MyGdxGame.V_WIDTH - shipwidth) {
            InBound = true;
        } else {InBound = false;}

        //Equates velocity to position
        posx += velx;

        //Moves left
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //Only moves if on screen in desired direction
            if(posx >= 0) {
                velx = -Spaceshipspeed;
                //Stops movement once off screen
            } else {velx = 0;}
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(posx <= MyGdxGame.V_WIDTH - shipwidth) {
                velx = Spaceshipspeed;
            } else {velx = 0;}
        }
        //Prevents player from constantly moving when button not pressed
        else {velx = 0;}
    }


    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
        System.out.println("ded");
    }
}
