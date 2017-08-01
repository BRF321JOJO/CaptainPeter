package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by ahuja on 5/30/2017.
 */
public class bgm {
    Sound bgmusic;
    //public static boolean soundEnabled=true;

    public bgm(){
        bgmusic = Gdx.audio.newSound(Gdx.files.internal("bgm.mp3"));
    }
    public void play(){
        bgmusic.loop(0.5f);
    }
}
