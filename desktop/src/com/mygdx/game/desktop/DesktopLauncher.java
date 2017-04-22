package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		//Configurations for Desktop Version
		cfg.resizable=false;
		cfg.height = 1000;
		cfg.width = 1000;
		cfg.foregroundFPS=60;
		cfg.vSyncEnabled=true;
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
