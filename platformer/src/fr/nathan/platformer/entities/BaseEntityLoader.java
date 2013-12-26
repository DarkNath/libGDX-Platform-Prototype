package fr.nathan.platformer.entities;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseEntityLoader {

	public BaseEntityLoader() {
	}

	public abstract void loadProperties(MapProperties props);

	public abstract Entity createEntity(World world, Vector2 pos);

	public abstract void createComponents(Entity entity, Vector2 pos);
}
