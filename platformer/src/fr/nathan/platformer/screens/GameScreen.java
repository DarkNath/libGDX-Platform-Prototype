package fr.nathan.platformer.screens;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import fr.nathan.platformer.Constants;
import fr.nathan.platformer.PlayerController;
import fr.nathan.platformer.assets.AssetManagerWrapper;
import fr.nathan.platformer.entities.EntityLoadersManager;
import fr.nathan.platformer.entities.loaders.BeeEntityLoader;
import fr.nathan.platformer.entities.loaders.PlayerEntityLoader;
import fr.nathan.platformer.map.MapManager;
import fr.nathan.platformer.physics.PhysicWorldWrapper;
import fr.nathan.platformer.physics.listeners.PlayerFeetContactListener;
import fr.nathan.platformer.systems.EntityScriptSystem;
import fr.nathan.platformer.systems.PhysicsSystem;
import fr.nathan.platformer.systems.RenderSystem;
import fr.nathan.platformer.systems.ScrollingSystem;
import fr.nathan.platformer.util.UnitConvertor;

/**
 * Renders the main scene where the gameplay takes place.
 * 
 * @author Nathan
 * 
 */
public class GameScreen implements Screen {

	private OrthographicCamera cam;

	private World world = new World();
	private RenderSystem renderSystem;

	private BitmapFont font = new BitmapFont();
	private SpriteBatch overlayBatch = new SpriteBatch();

	public GameScreen() {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, UnitConvertor.TO_UNIT(Gdx.graphics.getWidth()),
				UnitConvertor.TO_UNIT(Gdx.graphics.getHeight()));
		cam.update();

		EntityLoadersManager.getManager().addLoader(new PlayerEntityLoader());
		EntityLoadersManager.getManager().addLoader(new BeeEntityLoader());

		initWorld();

		try {
			MapManager.getInstance().loadMap(world);
		} catch (Exception ex) {
			Gdx.app.error(GameScreen.class.getName(), "Failed to load map", ex);
		}

		Gdx.input.setInputProcessor(new PlayerController(world.getManager(TagManager.class).getEntity("PLAYER")));
	}

	private void initWorld() {
		// physic world
		PhysicWorldWrapper.getInstance().createPhysicWorld(new Vector2(0, Constants.GRAVITY));

		PhysicWorldWrapper.getInstance().addContactListener(new PlayerFeetContactListener());

		// artemis world
		world.setManager(new TagManager());
		world.setManager(new GroupManager());

		renderSystem = world.setSystem(new RenderSystem(cam), true);

		world.setSystem(new EntityScriptSystem());
		world.setSystem(new PhysicsSystem());
		world.setSystem(new ScrollingSystem(cam));

		world.initialize();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		world.setDelta(delta);
		world.process();

		renderSystem.process(); // draw game world

		// draw overlay
		overlayBatch.begin();
		font.draw(overlayBatch, String.format("%d FPS", Gdx.graphics.getFramesPerSecond()), 0, Gdx.graphics.getHeight());
		overlayBatch.end();

	}

	@Override
	public void resize(int width, int height) {
		// setViewport((int) UnitConvertor.TO_UNIT(Gdx.graphics.getWidth()),
		// (int) UnitConvertor.TO_UNIT(Gdx.graphics.getHeight()));
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
		renderSystem.dispose();
		overlayBatch.dispose();
		font.dispose();

		AssetManagerWrapper.getManager().dispose();
	}

	// private void setViewport(int width, int height) {
	// float centerX = width / 2;
	// float centerY = height / 2;
	//
	// cam.position.set(centerX, centerY, 0);
	// cam.viewportWidth = width;
	// cam.viewportHeight = height;
	// }
}
