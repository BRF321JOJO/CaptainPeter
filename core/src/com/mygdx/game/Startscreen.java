package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ahuja on 3/12/2017.
 */
public class Startscreen extends Image{

    static boolean startGame = false;
    static boolean showStart = true;
    static boolean go = false;
    int starttime=1;
    int startcounter=0;

    static int width = 335;
    static int height = 335;

    public Startscreen(SpriteBatch batch) {
        super (
                new Texture("Startscreen.png"),
                (MyGdxGame.V_WIDTH - width)/2,
                (MyGdxGame.V_HEIGHT - height)/2,
                width,
                height,
                0,
                0,
                0,
                batch
        );
    }

    public void update(float delta){
        if (!startGame) {
            //Startscreen update whenever death
            posx = (MyGdxGame.V_WIDTH-335)/2;
            posy = (MyGdxGame.V_HEIGHT-335)/2;
            vely = 0;

            //Starts game when press enter
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                startGame = true;
            }
        }

        //Delays game start to allow start screens to leave
        if (startGame){
            startcounter++;
            if(startcounter>=60*starttime){
                go=true;
                startcounter = 0;
            }

            //Moves image offscreen downward when game starts
            vely++;
            posy-=vely;
        }
    }

    @Override
    public void render (){batch.draw(texture, posx, posy, width, height);}
}