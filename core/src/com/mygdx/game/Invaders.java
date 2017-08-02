package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
public class Invaders extends Entity{

    //Need sprite page.

    static int HoldingArea = 2000;
    static int numberofinvaders = 11;
    static int invaderswidth = 50;
    static boolean InBound;
    static boolean movingright = true;
    int counter = 0;
    //Controls int counter for how often invader moves
    int invadermovespeed = 25;
    static int invadermoveammount = 25;
    static int edgesofmovement = invadermoveammount;
    static int ammountmovedown = 50;
    static int spacebetween = 30 + invaderswidth;

    public Invaders(SpriteBatch batch, int posx) {
        super(
                new Texture("SpaceInvaderrainbow.png"),
                posx,
                600,
                invaderswidth,
                50,
                0,
                0,
                //ID 1 indicates enemy
                1,
                batch
        );
    }


    public void update(float delta) {

        //Defines InBound
        if(posx >= 0 && posx <= MyGdxGame.V_WIDTH - invaderswidth) {
            InBound = true;
        } else {InBound = false;}

        //Equates velx to posx
        posx += velx;

        //int counter for code not every frame
        counter++;

        //All within int counter to slow movement
        //Happens every x frames
        if (counter % invadermovespeed == 0) {

            //Moves side to side (only if InBound)
            if (InBound) {
                //Goes right, and if is false, goes left
                if (movingright) {
                    posx += invadermoveammount;
                } else if (!movingright) {
                    posx -= invadermoveammount;
                }
            }
        }

        //Moving invaders down in gamescreen method
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
        //Moves offscreen if hit
        posx = HoldingArea;
    }
}
