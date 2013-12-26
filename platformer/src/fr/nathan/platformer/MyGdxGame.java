package fr.nathan.platformer;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import fr.nathan.platformer.screens.AssetsLoadingScreen;

public class MyGdxGame extends Game {

	private AssetsLoadingScreen loadingScreen;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		loadingScreen = new AssetsLoadingScreen(this);

		setScreen(loadingScreen);
	}
}
