package fr.nathan.platformer.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

import fr.nathan.platformer.components.EntityScriptComponent;
import fr.nathan.platformer.scripts.EntityScript;

public class EntityScriptSystem extends EntityProcessingSystem {

	@Mapper
	private ComponentMapper<EntityScriptComponent> scriptMapper;

	@SuppressWarnings("unchecked")
	public EntityScriptSystem() {
		super(Aspect.getAspectForAll(EntityScriptComponent.class));
	}

	@Override
	protected void inserted(Entity e) {
		for (EntityScript script : scriptMapper.get(e).getScripts()) {
			script.init(world, e);
		}
	}

	@Override
	protected void process(Entity e) {
		for (EntityScript script : scriptMapper.get(e).getScripts()) {
			script.update(world, e);
		}
	}

	@Override
	protected void removed(Entity e) {
		for (EntityScript script : scriptMapper.get(e).getScripts()) {
			script.dispose(world, e);
		}
	}

}
