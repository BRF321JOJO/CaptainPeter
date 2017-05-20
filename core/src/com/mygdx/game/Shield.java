package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Marla Scrub on 5/18/2017.
 */
public class Shield extends Entity{

    static int numberofshields = 4;
    static int shieldwidth = 100;
    //Must be a double so that can divide by any number when reducing height if collision hit
    //Allowed to be a double by super class because changed Entity class variable to receive doubles rather than int
    static double shieldheight = 75;

    //static int damagevalue = 0;

    public Shield(SpriteBatch batch, int posx) {
        super(
                new Texture("badlogic.jpg"),
                posx,
                100,
                shieldwidth,
                shieldheight,
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

        //Based of shrinking image when it is hit

        //Makes shield smaller to signal damage
        //(Total height) divided by (number of shield lives) equals (amount image height should decrease when hit)
        height -= shieldheight/10;

        //Moves image off screen if too small
        if (height <=0) {
            posx = Laser.HoldingArea;
        }

        //Below idea based off of damage coutner

        //Want to individualize damage values for shields
        //damagevalue++;
        //Moves off screen if damage too high
//        if (damagevalue == 10) {
//            posx = Laser.HoldingArea;
//        }
    }
}
