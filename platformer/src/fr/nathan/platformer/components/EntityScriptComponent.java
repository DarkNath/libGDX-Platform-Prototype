package fr.nathan.platformer.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.artemis.Component;

import fr.nathan.platformer.scripts.EntityScript;

public class EntityScriptComponent extends Component {

	List<EntityScript> scripts = new ArrayList<EntityScript>();

	public EntityScriptComponent(EntityScript... scripts) {
		this.scripts.addAll(Arrays.asList(scripts));
	}

	public List<EntityScript> getScripts() {
		return scripts;
	}
}
