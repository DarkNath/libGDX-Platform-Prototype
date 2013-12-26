package fr.nathan.platformer.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import fr.nathan.platformer.components.LocationComponent;

/**
 * Compute camera position regarding player position to create a scrolling.
 * 
 * @author Nathan
 * 
 */
public class ScrollingSystem extends VoidEntitySystem {

	@Mapper
	private ComponentMapper<LocationComponent> locMapper;

	private OrthographicCamera cam;

	private Entity player;

	private Vector2 maxPos = new Vector2();
	private Vector2 minPos = new Vector2();

	public ScrollingSystem(OrthographicCamera cam) {
		this.cam = cam;

		minPos.x = cam.viewportWidth / 2;
		minPos.y = cam.viewportHeight / 2;
	}

	@Override
	protected void processSystem() {
		Vector2 playerLoc = locMapper.get(player).getLocation();

		cam.position.set(playerLoc.x, playerLoc.y, 0);

		if (cam.position.x > maxPos.x)
			cam.position.x = maxPos.x;
		else if (cam.position.x < minPos.x)
			cam.position.x = minPos.x;

		if (cam.position.y > maxPos.y)
			cam.position.y = maxPos.y;
		else if (cam.position.y < minPos.y)
			cam.position.y = minPos.y;
	}

	public void setPlayer(Entity player) {
		this.player = player;
	}

	public void setMapDimensions(int width, int height) {
		maxPos.x = width - cam.viewportWidth / 2;
		maxPos.y = height - cam.viewportHeight / 2;
	}

}
