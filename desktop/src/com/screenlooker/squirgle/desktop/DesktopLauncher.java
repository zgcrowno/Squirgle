package com.screenlooker.squirgle.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.screenlooker.squirgle.Squirgle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SQUIRGLE";
		config.width = 1920;
		config.height = 1080;
		config.foregroundFPS = 60;
		config.fullscreen = true;
		config.vSyncEnabled = true;
		config.forceExit = true;
		config.addIcon("icons/squirgleLogo128.png", Files.FileType.Internal);
		config.addIcon("icons/squirgleLogo32.png", Files.FileType.Internal);
		config.addIcon("icons/squirgleLogo16.png", Files.FileType.Internal);
		new LwjglApplication(new Squirgle(), config);
	}
}
