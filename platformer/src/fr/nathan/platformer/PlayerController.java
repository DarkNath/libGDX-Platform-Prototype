package fr.nathan.platformer;

import com.artemis.Entity;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import fr.nathan.platformer.components.MovementComponent;
import fr.nathan.platformer.components.PhysicsComponent;
import fr.nathan.platformer.components.RenderComponent;

/**
 * Input processor in charge of controlling the player entity.
 * 
 * @author Nathan
 * 
 */
public class PlayerController extends InputAdapter {

	private MovementComponent movComp;
	private PhysicsComponent physicsComponent;
	private RenderComponent renderComponent;

	public PlayerController(Entity player) {
		movComp = player.getComponent(MovementComponent.class);
		physicsComponent = player.getComponent(PhysicsComponent.class);
		renderComponent = player.getComponent(RenderComponent.class);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
			renderComponent.setTexture("character_warrior_right");
			movComp.setVelX(Constants.NORMAL_MOVEMENT_VELOCITY);
			return true;

		case Keys.LEFT:
			// renderComponent.setTexture("character_warrior_left");
			movComp.setVelX(-Constants.NORMAL_MOVEMENT_VELOCITY);
			return true;

		case Keys.SPACE:
			if (physicsComponent.isGrounded()) {
				movComp.setJumping(true);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
			// if the player was actually moving to the right
			if (movComp.getVelX() > 0)
				movComp.setVelX(0f); // cancel the movement
			return true;

		case Keys.LEFT:
			// if the player was actually moving to the left
			if (movComp.getVelX() < 0)
				movComp.setVelX(0f); // cancel the movement
			return true;
		}
		return false;
	}

}
