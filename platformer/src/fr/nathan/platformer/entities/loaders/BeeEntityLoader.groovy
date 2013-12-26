package fr.nathan.platformer.entities.loaders

import fr.nathan.platformer.Constants;
import com.artemis.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import fr.nathan.platformer.physics.BodyData
import fr.nathan.platformer.physics.PhysicWorldWrapper;
import fr.nathan.platformer.scripts.monsters.BeeBehaviorScript
import fr.nathan.platformer.util.UnitConvertor;

import fr.nathan.platformer.components.EntityScriptComponent;
import fr.nathan.platformer.components.LocationComponent
import fr.nathan.platformer.components.MovementComponent
import fr.nathan.platformer.components.PhysicsComponent
import fr.nathan.platformer.components.RenderComponent
import fr.nathan.platformer.entities.PhysicEntityLoader;
import fr.nathan.platformer.assets.AssetManagerWrapper;

class BeeEntityLoader extends PhysicEntityLoader {

	private static String defaultDirectionChangeInterval = "3000";

	private int directionChangeInterval;

	@Override
	public Body createBody(Entity entity, Vector2 pos) {
		BodyDef bodyDef = new BodyDef()
		bodyDef.type = BodyType.KinematicBody
		bodyDef.position.set pos.x + 0.5f as float, pos.y + 0.5f as float
		Body body = PhysicWorldWrapper.getInstance().getPhysicWorld().createBody bodyDef

		body.setFixedRotation true

		// main player fixture
		CircleShape beeShape = new CircleShape();
		beeShape.setRadius UnitConvertor.TO_UNIT(Constants.VIRTUAL_TILE_DIM / 2 as float)

		FixtureDef mainDef = new FixtureDef()
		mainDef.shape = beeShape
		mainDef.friction = 0.0f
		mainDef.restitution = 0.0f
		mainDef.density = 1
		body.createFixture mainDef

		beeShape.dispose()

		BodyData bdata = new BodyData()
		bdata.type = "entity"
		bdata.entity = entity

		body.setUserData bdata

		return body;
	}

	@Override
	public void createComponents(Entity entity, Vector2 pos) {
		entity.addComponent new LocationComponent(pos.x, pos.y)
		entity.addComponent new RenderComponent(AssetManagerWrapper.getManager().get("monsters_sheet"),
				"red_bee_left")
		entity.addComponent new MovementComponent(new Vector2(-5f, 0f))
		entity.addComponent new PhysicsComponent(body)
		entity.addComponent new EntityScriptComponent(new BeeBehaviorScript(directionChangeInterval))
	}

	@Override
	public void loadProperties(MapProperties props) {
		directionChangeInterval = Integer.valueOf(props.get("direction_change_interval", defaultDirectionChangeInterval, String.class));
	}
}
