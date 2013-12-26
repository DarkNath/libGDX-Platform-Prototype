package fr.nathan.platformer.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Wrapper around the box2d world instance.
 * 
 * @author Nathan
 * 
 */
public class PhysicWorldWrapper {

	private static PhysicWorldWrapper _classRef;

	private World physicWorld;
	private MainContactListener mainContactListener = new MainContactListener();

	private PhysicWorldWrapper() {

	}

	public void createPhysicWorld(Vector2 gravityVector) {
		physicWorld = new com.badlogic.gdx.physics.box2d.World(gravityVector, true);

		physicWorld.setContactListener(mainContactListener);
	}

	public static PhysicWorldWrapper getInstance() {
		if (_classRef == null) {
			_classRef = new PhysicWorldWrapper();
		}
		return _classRef;
	}

	public World getPhysicWorld() {
		return physicWorld;
	}

	public void addContactListener(ContactListener listener) {
		mainContactListener.addContactListener(listener);
	}
}
