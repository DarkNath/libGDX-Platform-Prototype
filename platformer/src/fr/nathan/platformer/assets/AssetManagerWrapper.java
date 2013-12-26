package fr.nathan.platformer.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetManagerWrapper {

	private static AssetManagerWrapper _classRef;

	private Map<String, String> assetsMap = new HashMap<String, String>();
	private AssetManager assetManager = new AssetManager();

	private AssetManagerWrapper() {
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		load("world1", "data/maps/world1.tmx", TiledMap.class);
		load("warrior_sheet", "data/sprites/characters/warrior.pack", TextureAtlas.class);
		load("boss1_sheet", "data/sprites/boss/boss1.pack", TextureAtlas.class);
		load("monsters_sheet", "data/sprites/monsters/monsters.pack", TextureAtlas.class);
	}

	public static AssetManagerWrapper getManager() {
		if (_classRef == null) {
			_classRef = new AssetManagerWrapper();
		}
		return _classRef;
	}

	public boolean update() {
		return assetManager.update();
	}

	public float getProgress() {
		return assetManager.getProgress();
	}
	
	public void load(String assetKey, String path, Class<?> assetClass) {
		assetManager.load(path, assetClass);
		assetsMap.put(assetKey, path);
	}

	public Object get(String assetKey) {
		return assetManager.get(assetsMap.get(assetKey));
	}

	public void dispose() {
		assetManager.dispose();
	}
}
