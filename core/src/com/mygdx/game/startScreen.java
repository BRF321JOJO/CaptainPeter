package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ahuja on 3/12/2017.
 */
public class startScreen extends Image{

    public startScreen(SpriteBatch batch) {
        super (
                new Texture("start screen.png"),
                (MyGdxGame.V_WIDTH-335)/2,
                (MyGdxGame.V_HEIGHT-335)/2,
                1280,
                720,
                0,
                0,
                0,
                batch
        );
    }

    boolean startGame = false;
    boolean showStart = true;
    boolean go;
    int starttime=1;
    int startcounter=0;

    Texture dark = new Texture("dark.png");
    Texture texture;
    int darkx=0;
    int darky=0;

    public void update(){
        if (!startGame) {
            //Startscreen
            posx=(MyGdxGame.V_WIDTH-335)/2;
            posy=(MyGdxGame.V_HEIGHT-335)/2;
            vely=0;
            //Dark screen
            darkx=0;
            darky=0;
            go=false;
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                startGame = true;
                showStart = false;
            }
        }

        //Delays game start to allow start screens to leave
        if (startGame){
            startcounter++;
            if(startcounter>=60*starttime){
                go=true;
                startcounter = 0;
            }
            vely++;
            posy-=vely;
            darky-=vely;
        }
    }

    @Override
    public void render (){batch.draw(texture, posx, posy, width, height);}
}