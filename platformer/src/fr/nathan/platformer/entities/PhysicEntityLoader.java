package fr.nathan.platformer.entities;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicEntityLoader extends BaseEntityLoader {
	
	protected Body body;
	
	public abstract Body createBody(Entity entity, Vector2 pos);

	@Override
	public Entity createEntity(World world, Vector2 pos) {
		Entity entity = world.createEntity();
				
		body = createBody(entity, pos);
		
		createComponents(entity, pos);
		
		entity.addToWorld();

		return entity;
	}
}
