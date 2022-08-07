package knight.arkham.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import knight.arkham.PongGame;

public class DesktopLauncher {

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "pong";
		config.width = 960;
		config.height = 640;

		config.forceExit = false;

		new LwjglApplication(new PongGame(), config);
	}
}
