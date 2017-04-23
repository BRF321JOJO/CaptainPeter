package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 4/21/2017.
 */

public class Laser extends Entity{

    static boolean InBound;

    public Laser(SpriteBatch batch) {
        super (
                new Texture("laser.png"),
                    Constant.HoldingArea,
                    0,
                    15,
                    100,
                    0,
                    10,
                    0,
                    batch
            );
        }

        public void update(float delta) {

            //Defines InBound
            if (posy <=0 || posy >= 1000) {
                InBound = false;
            } else {InBound = true;}


            //Sets laser at 100 (only if off screen)
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                posx= Player.x+(50-7);
                posy=100;
                System.out.println("Pew, you shot a laser!");
            }

            //Moves laser right (only if on screen)
            else if (InBound) {
                posy += vely;
            }

            //Puts laser back to hold area once off screen
            if (posy == 1000) {
                posx = Constant.HoldingArea;
            }
            //posy of laser depends on Player posy. [In GameScreen, update method]

            //moves laser up
            if(posx>0 && posy <720){
                posy++;
            }
        }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {

    }
}
