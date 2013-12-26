package fr.nathan.platformer.map;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.nathan.platformer.Constants;
import fr.nathan.platformer.assets.AssetManagerWrapper;
import fr.nathan.platformer.entities.BaseEntityLoader;
import fr.nathan.platformer.entities.EntityLoadersManager;
import fr.nathan.platformer.physics.PhysicWorldWrapper;
import fr.nathan.platformer.systems.RenderSystem;
import fr.nathan.platformer.systems.ScrollingSystem;
import fr.nathan.platformer.util.UnitConvertor;

/**
 * Load maps and create entities.
 * 
 * @author Nathan
 * 
 */
public class MapManager {

	public static MapManager _classRef;
	private TiledMap currentMap;

	private MapManager() {

	}

	public static MapManager getInstance() {
		if (_classRef == null) {
			_classRef = new MapManager();
		}
		return _classRef;
	}

	public void loadMap(World world) {

		currentMap = (TiledMap) AssetManagerWrapper.getManager().get("world1");
		BatchTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(currentMap, 1f / Constants.TILE_DIM);

		TiledMapTileLayer layer = (TiledMapTileLayer) currentMap.getLayers().get("layer1");

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;

		PolygonShape tileShape = new PolygonShape();
		tileShape.setAsBox(UnitConvertor.TO_UNIT((Constants.VIRTUAL_TILE_DIM - 1) / 2),
				UnitConvertor.TO_UNIT((Constants.VIRTUAL_TILE_DIM - 1) / 2));

		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				if (layer.getCell(x, y) != null) {
					bodyDef.position.set(x + 0.5f, y + 0.5f);

					Body body = PhysicWorldWrapper.getInstance().getPhysicWorld().createBody(bodyDef);

					body.createFixture(tileShape, 1);
				}
			}
		}

		tileShape.dispose();

		loadEntities(world);

		world.getSystem(RenderSystem.class).setRenderer(renderer);

		world.getSystem(ScrollingSystem.class).setMapDimensions(
				((TiledMapTileLayer) currentMap.getLayers().get(0)).getWidth(),
				((TiledMapTileLayer) currentMap.getLayers().get(0)).getHeight());
		world.getSystem(ScrollingSystem.class).setPlayer(
				world.getManager(TagManager.class).getEntity(Constants.PLAYER_TAG));
	}

	private void loadEntities(World world) {
		for (MapObject mobj : currentMap.getLayers().get(Constants.ENTITY_LAYER).getObjects()) {
			MapProperties props = mobj.getProperties();

			if (props.get("type", String.class).equals(Constants.ENTITY_TYPE)) {
				Entity e;
				try {
					BaseEntityLoader loader = EntityLoadersManager.getManager().getLoader(
							Class.forName(props.get("loader", String.class)));
					loader.loadProperties(props);

					e = loader.createEntity(world, new Vector2(props.get("x", Integer.class) / Constants.TILE_DIM,
							props.get("y", Integer.class) / Constants.TILE_DIM));
				} catch (ClassNotFoundException ex) {
					Gdx.app.error(MapManager.class.getName(), "Invalid loader provided in " + mobj.getName()
							+ " entity", ex);
					continue;
				}

				String tag;
				if ((tag = props.get("tag", String.class)) != null) {
					world.getManager(TagManager.class).register(tag, e);
				}

				String groups;
				if ((groups = props.get("groups", String.class)) != null) {
					for (String group : groups.split(",")) {
						world.getManager(GroupManager.class).add(e, group);
					}
				}
			}
		}
	}

}
