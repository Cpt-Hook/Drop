package standa.drop.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import standa.drop.DropGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Drop";
		config.fullscreen=false;
		config.width = 1980;
		config.height = 1080;
		config.backgroundFPS = -1;
		config.foregroundFPS = 60;
		config.useGL30= true;
		new LwjglApplication(new DropGame(), config);
	}
}
