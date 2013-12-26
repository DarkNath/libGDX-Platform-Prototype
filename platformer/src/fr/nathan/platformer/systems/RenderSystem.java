package fr.nathan.platformer.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;

import fr.nathan.platformer.components.LocationComponent;
import fr.nathan.platformer.components.RenderComponent;
import fr.nathan.platformer.physics.PhysicWorldWrapper;

/**
 * Render the entities on screen.
 * 
 * @author Nathan
 * 
 */
public class RenderSystem extends EntityProcessingSystem implements Disposable {

	@Mapper
	private ComponentMapper<LocationComponent> locMapper;

	@Mapper
	private ComponentMapper<RenderComponent> renderMapper;

	private BatchTiledMapRenderer renderer;
	private SpriteBatch batch;

	private OrthographicCamera cam;

	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	@SuppressWarnings("unchecked")
	public RenderSystem(OrthographicCamera cam) {
		super(Aspect.getAspectForAll(LocationComponent.class, RenderComponent.class));
		this.cam = cam;
	}

	@Override
	protected void begin() {

		cam.update();

		renderer.setView(cam);
		renderer.render();

		batch.begin();
	}

	@Override
	protected void end() {
		batch.end();

		debugRenderer.render(PhysicWorldWrapper.getInstance().getPhysicWorld(), cam.combined);
	}

	@Override
	protected void process(Entity e) {
		Vector2 loc = locMapper.get(e).getLocation();
		RenderComponent renderComp = renderMapper.get(e);

		batch.draw(renderComp.getTexture(), loc.x, loc.y, renderComp.getUnitWidth(), renderComp.getUnitHeight());
	}

	public void setRenderer(BatchTiledMapRenderer renderer) {
		this.renderer = renderer;
		batch = this.renderer.getSpriteBatch();
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

}
