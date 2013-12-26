package fr.nathan.platformer.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import fr.nathan.platformer.assets.AssetManagerWrapper;

public class AssetsLoadingScreen implements Screen {

	private Game game;

	public AssetsLoadingScreen(Game game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		if (AssetManagerWrapper.getManager().update()) {
			Gdx.app.debug(AssetsLoadingScreen.class.getName(), "done loading assets");

			game.setScreen(new GameScreen());
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
