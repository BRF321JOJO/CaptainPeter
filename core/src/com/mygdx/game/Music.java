package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by ahuja on 5/30/2017.
 */
public class Music {
    Sound bgmusic;
    private static float bgmusicvolume = 0.5f;

    public Music(){
        bgmusic = Gdx.audio.newSound(Gdx.files.internal("bgm.mp3"));
    }
    public void play(){
        bgmusic.loop(bgmusicvolume);
    }
}
