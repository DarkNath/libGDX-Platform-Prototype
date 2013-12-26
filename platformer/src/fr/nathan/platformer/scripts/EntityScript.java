package fr.nathan.platformer.scripts;

import com.artemis.Entity;
import com.artemis.World;

public interface EntityScript {

	public void init(World world, Entity entity);
	public void update(World world, Entity entity);
	public void dispose(World world, Entity entity);
}
