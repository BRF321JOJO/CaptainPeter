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

    boolean xInBound;
    boolean yInBound;

    static int HoldingArea = 1500;
    static int laserheight = 30;
    Sound pew = Gdx.audio.newSound(Gdx.files.internal("Pew.mp3"));

    public Laser(SpriteBatch batch) {
        super (
                new Texture("Up laser.png"),
                    HoldingArea,
                    //Equal to height of player + posy of player
                    laserheight + Player.playerposy,
                    10,
                    laserheight,
                    0,
                    8,
                    0,
                    batch
            );
        }

        public void update(float delta) {

            //Defines inbound values

            //Defines xInBound
            if ((posx >=0) && (posx <= MyGdxGame.V_WIDTH)) {
                xInBound = true;
            } else {xInBound = false;}

            //Defined yInBound
            if ((posy >= 0) && (posy <= MyGdxGame.V_HEIGHT)) {
                yInBound = true;
            } else {yInBound = false;}


            //Only works if player laser (not invader)
            if(ID == 0) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !xInBound) {
                    //Resets y position
                    posy = laserheight + Player.playerposy;
                    //posx of laser depends on Player posx. [In GameScreen, update method]
                    if (Debug.pewsound) {
                        pew.play();
                    }
                    System.out.println("Pew, you shot a laser!");
                }
            }

            if (ID > 0) {
                //velx = -velx;
            }

            if (ID == 1) {

            }

            if (ID == 2) {

            }

            //Moves laser up
            if(xInBound){
                posy+= vely;
            }

            //Puts laser back to hold area once off screen
            if (posy >= MyGdxGame.V_HEIGHT) {
                posx = HoldingArea;
            }
        }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
        //If collides, set back to holding area to be able to be shot again
        posx = HoldingArea;
    }
}
