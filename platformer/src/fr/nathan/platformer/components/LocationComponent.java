package fr.nathan.platformer.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Sets the entity in the space.
 * 
 * @author Nathan
 * 
 */
public class LocationComponent extends Component {
	private Vector2 loc = new Vector2();

	public LocationComponent(Float x, Float y) {
		loc.set(x, y);
	}

	public float getX() {
		return loc.x;
	}

	public float getY() {
		return loc.y;
	}

	public Vector2 getLocation() {
		return loc;
	}

	public void translate(float dx, float dy) {
		loc.add(dx, dy);
	}

	public void translate(Vector2 v) {
		loc.add(v);
	}

	public void set(Vector2 v) {
		loc.set(v);
	}

	public void set(float x, float y) {
		loc.set(x, y);
	}
}
