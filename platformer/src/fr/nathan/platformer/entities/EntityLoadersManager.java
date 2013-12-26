package fr.nathan.platformer.entities;

import java.util.HashMap;
import java.util.Map;

public class EntityLoadersManager {

	private static EntityLoadersManager _classRef;

	private Map<Class<?>, BaseEntityLoader> loaders = new HashMap<Class<?>, BaseEntityLoader>();

	private EntityLoadersManager() {
	}

	public static EntityLoadersManager getManager() {
		if (_classRef == null) {
			_classRef = new EntityLoadersManager();
		}
		return _classRef;
	}

	public void addLoader(BaseEntityLoader loader) {
		loaders.put(loader.getClass(), loader);
	}

	public BaseEntityLoader getLoader(Class<?> loaderClass) {
		return loaders.get(loaderClass);
	}
}
