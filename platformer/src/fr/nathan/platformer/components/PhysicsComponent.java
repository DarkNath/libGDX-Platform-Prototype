package fr.nathan.platformer.components;

import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Make any entity holding this component part of the physics simulation.
 * 
 * @author Nathan
 * 
 */
public class PhysicsComponent extends Component {

	private Body body;
	private boolean isGrounded = false;

	@Mapper
	private ComponentMapper<LocationComponent> locMapper;

	public PhysicsComponent(Body body) {
		this.body = body;
	}

	public Body getBody() {
		return body;
	}

	public boolean isGrounded() {
		return isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

}
