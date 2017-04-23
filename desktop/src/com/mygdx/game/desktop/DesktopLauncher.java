package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class  DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		//Configurations for Desktop Version
		cfg.resizable=false;
		cfg.height = 720;
		cfg.width = 1280;
		cfg.foregroundFPS=60;
		cfg.vSyncEnabled=true;
		//cfg.fullscreen=true;
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
