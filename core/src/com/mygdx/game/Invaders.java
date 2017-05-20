package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
public class Invaders extends Entity{

    //Need sprite page.

    static int numberofinvaders = 1;
    static int invaderswidth = 50;
    static boolean InBound;
    static boolean movingright = true;
    static boolean movingleft;

    public Invaders(SpriteBatch batch, int posx) {
        super(
                new Texture("badlogic.jpg"),
                posx,
                600,
                invaderswidth,
                50,
                1,
                0,
                0,
                batch
        );
    }


    public void update(float delta) {

        //Defines InBound
        if(posx >= 0 && posx <= MyGdxGame.V_WIDTH - invaderswidth) {
            InBound = true;
        } else {InBound = false;}


        //Code which moves them

        //Equates velx to posx
        posx += velx;

        //Moves side to side
        //Goes right, and if is false, goes left
        if (InBound && movingright) {
            posx += 10;
        } else {posx -= 10;}

        //Moves down and makes sets it go opposite direction once gets far enough to side of screen
        if (posx  <= 20) {
            posy -= 20;
            movingright = true;
        }
        //Corrects for width of invader
        if (posx >= MyGdxGame.V_WIDTH - 20 - invaderswidth) {
            posy -=20;
            movingright = false;
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
        //Moves offscreen if hit
        posx = Laser.HoldingArea;
    }
}
