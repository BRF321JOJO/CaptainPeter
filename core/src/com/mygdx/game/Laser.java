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

    boolean InBound;
    static int HoldingArea = 2000;
    static int laserheight = 30;
    int laserid=0;
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

            //Defines InBound
            if ((posx >=0) && (posx <= 1280)) {
                InBound = true;
            } else {InBound = false;}

            if(laserid==0) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !InBound) {
                    //Resets y position
                    posy = laserheight + Player.playerposy;
                    //posx of laser depends on Player posx. [In GameScreen, update method]
                    System.out.println("Pew, you shot a laser!");
                }
            }

            //Moves laser up
            if(InBound){
                posy+= vely;
            }

            //Puts laser back to hold area once off screen
            if (posy >= MyGdxGame.V_HEIGHT || posy<=0) {
                posx = HoldingArea;
            }
        }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
        //If collides, set back to holding area to be able to be shot again
        posx = HoldingArea;
        //pew.play();
    }
}
