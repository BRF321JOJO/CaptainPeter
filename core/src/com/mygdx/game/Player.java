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

    static int x;

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

        x=posx;

        posx += velx;
        //if(posx>0 && posx<(MyGdxGame.V_WIDTH-width)) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(posx>0) {
                    velx = -CPlayer.Spaceshipspeed;
                }else {velx = 0;}
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if(posx<(MyGdxGame.V_WIDTH-width)) {
                    velx = CPlayer.Spaceshipspeed;
                }else {velx = 0;}
            }else {velx = 0;}
        //}else {velx=0;}

        //Prevents player from moving when button not pressed

    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {

    }


}
