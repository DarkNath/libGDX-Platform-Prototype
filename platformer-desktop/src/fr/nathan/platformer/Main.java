package fr.nathan.platformer;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "platformer";
		cfg.useGL20 = false;
		cfg.width = Constants.SCREEN_WIDTH;
		cfg.height = Constants.SCREEN_HEIGTH;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
