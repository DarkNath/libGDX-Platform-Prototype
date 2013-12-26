package fr.nathan.platformer.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the ability to move (and jump) of the entity.
 * 
 * @author Nathan
 * 
 */
public class MovementComponent extends Component {

	private Vector2 vel = new Vector2(0, 0);
	private boolean isJumping = false;

	public MovementComponent() {

	}
	
	public MovementComponent(Vector2 vel) {
		this.vel = vel;
	}

	public float getVelY() {
		return vel.y;
	}

	public void setVelY(float velY) {
		vel.y = velY;
	}

	public float getVelX() {
		return vel.x;
	}

	public void setVelX(float velX) {
		vel.x = velX;
	}

	public Vector2 getVel() {
		return vel;
	}

	public void addX(float amount) {
		vel.x += amount;
	}

	public void addY(float amount) {
		vel.y += amount;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
}
