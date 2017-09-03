package com.screenlooker.squirgle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.screenlooker.squirgle.Squirgle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SQUIRGLE";
		config.width = 768;
		config.height = 1024;
		config.forceExit = true;
		new LwjglApplication(new Squirgle(), config);
	}
}
