package fr.nathan.platformer.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import fr.nathan.platformer.Constants;
import fr.nathan.platformer.components.LocationComponent;
import fr.nathan.platformer.components.MovementComponent;
import fr.nathan.platformer.components.PhysicsComponent;
import fr.nathan.platformer.physics.PhysicWorldWrapper;

/**
 * Update the physics simulation and apply precomputed forces to dynamic
 * entities.
 * 
 * @author Nathan
 * 
 */
public class PhysicsSystem extends EntityProcessingSystem {

	@Mapper
	private ComponentMapper<LocationComponent> locMapper;

	@Mapper
	private ComponentMapper<PhysicsComponent> physicsMapper;

	@Mapper
	private ComponentMapper<MovementComponent> movMapper;

	@SuppressWarnings("unchecked")
	public PhysicsSystem() {
		super(Aspect.getAspectForAll(LocationComponent.class, PhysicsComponent.class));
	}

	@Override
	protected void process(Entity e) {
		PhysicsComponent dbc = physicsMapper.get(e);

		switch (dbc.getBody().getType()) {
		case DynamicBody:
			Vector2 p = dbc.getBody().getPosition();

			if (movMapper.has(e)) {
				Vector2 vel = dbc.getBody().getLinearVelocity();
				MovementComponent velComp = movMapper.get(e);

				float impulseX = (velComp.getVelX() - vel.x) * dbc.getBody().getMass();
				float impulseY;

				if (velComp.isJumping()) {
					velComp.setJumping(false);
					impulseY = Constants.NORMAL_JUMP_VELOCITY;
				} else {
					impulseY = 0;
				}

				dbc.getBody().applyLinearImpulse(new Vector2(impulseX, impulseY), dbc.getBody().getWorldCenter(), true);
			}
			locMapper.get(e).set(p.x - 0.5f, p.y - 0.5f);
			break;

		case KinematicBody:
			Vector2 p2 = dbc.getBody().getPosition();

			if (movMapper.has(e)) {
				dbc.getBody().setLinearVelocity(movMapper.get(e).getVel());
			}
			locMapper.get(e).set(p2.x - 0.5f, p2.y - 0.5f);
			break;

		default:
			break;
		}
	}

	@Override
	protected void end() {
		PhysicWorldWrapper.getInstance().getPhysicWorld().step(Gdx.graphics.getDeltaTime(), 8, 3);
	}

}
