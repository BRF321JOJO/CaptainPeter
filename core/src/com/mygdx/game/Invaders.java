package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
public class Invaders extends Entity{

    //Need sprite page.

    static int numberofinvaders = 11;
    static int invaderswidth = 50;
    static boolean InBound;
    static boolean movingright = true;
    int counter = 0;
    //Controls int counter for how often invader moves
    int invadermovespeed = 25;
    int invadermoveammount = 25;
    int edgesofmovement = 20;
    int ammountmovedown = 75;

    public Invaders(SpriteBatch batch, int posx) {
        super(
                new Texture("SpaceInvaderrainbow.png"),
                posx,
                600,
                invaderswidth,
                50,
                0,
                0,
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

                //Following two if statements moves down and makes go opposite direction once close enough to sides
                //Also, corrects for slow drift right and left
                if (posx <= edgesofmovement) {
                    posx = edgesofmovement;
                    posy -= ammountmovedown;
                    movingright = true;
                }
                //(Corrects for width of invader)
                if (posx >= MyGdxGame.V_WIDTH - edgesofmovement - invaderswidth) {
                    posx = MyGdxGame.V_WIDTH - edgesofmovement - invaderswidth;
                    posy -= ammountmovedown;
                    movingright = false;
                }
            }
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
