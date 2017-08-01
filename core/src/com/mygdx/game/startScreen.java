package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ahuja on 3/12/2017.
 */
public class startScreen {
    boolean startGame;
    int y;
    int x;
    int darkx=0;
    int darky=0;
    int yvel;
    Texture texture;
    boolean showStart=true;
    Texture dark= new Texture("dark.png");
    int startcounter=0;
    boolean go=false;
    int starttime=1;

    public startScreen(){
        startGame= false;
        showStart=true;
        x= (MyGdxGame.V_WIDTH-335)/2;
        y=(MyGdxGame.V_HEIGHT-335)/2;
        texture= new Texture("start screen.png");
        yvel=0;
    }
    public void update(){
        if (startGame!=true) {
            x=(MyGdxGame.V_WIDTH-335)/2;
            y=(MyGdxGame.V_HEIGHT-335)/2;
            yvel=0;
            darkx=0;
            darky=0;
            go=false;
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                startGame=true;
                showStart=true;
            }
        }
        if (startGame==true){
            startcounter++;
            if(startcounter>=60*starttime){
                go=true;
            }
            yvel++;
            y-=yvel;
            darky-=yvel;
        }
    }
}