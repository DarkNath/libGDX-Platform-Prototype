package fr.nathan.platformer.entities.loaders

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import creator.EntityCreator;
import creator.ParamSet;

import fr.nathan.platformer.Constants;
import fr.nathan.platformer.assets.AssetManagerWrapper;
import fr.nathan.platformer.components.LocationComponent
import fr.nathan.platformer.components.MovementComponent
import fr.nathan.platformer.components.PhysicsComponent;
import fr.nathan.platformer.components.RenderComponent
import fr.nathan.platformer.entities.PhysicEntityLoader;
import fr.nathan.platformer.physics.BodyData;
import fr.nathan.platformer.physics.FixtureData;
import fr.nathan.platformer.physics.PhysicWorldWrapper;
import fr.nathan.platformer.util.UnitConvertor;

class PlayerEntityLoader extends PhysicEntityLoader {

	@Override
	public Body createBody(Entity entity, Vector2 pos) {
		BodyDef bodyDef = new BodyDef()
		bodyDef.type = BodyType.DynamicBody
		bodyDef.position.set pos.x + 0.5f as float, pos.y + 0.5f as float
		Body body = PhysicWorldWrapper.getInstance().getPhysicWorld().createBody bodyDef

		body.setFixedRotation true

		// main player fixture
		CircleShape playerShape = new CircleShape()
		playerShape.setRadius UnitConvertor.TO_UNIT(Constants.VIRTUAL_TILE_DIM / 2 as float)
		playerShape.setPosition new Vector2(0.3f, 0.0f)

		FixtureDef mainDef = new FixtureDef()
		mainDef.shape = playerShape
		mainDef.friction = 0.0f
		mainDef.restitution = 0.0f
		mainDef.density = 1
		body.createFixture mainDef

		playerShape.dispose()

		// feet player fixture

		FixtureDef feetDef = new FixtureDef()

		PolygonShape feetShape = new PolygonShape()
		feetShape.setAsBox UnitConvertor.TO_UNIT(10), UnitConvertor.TO_UNIT(5),
				new Vector2(0.3, -0.5f), 0

		feetDef.isSensor = true
		feetDef.density = 1
		feetDef.shape = feetShape

		Fixture feetFixture = body.createFixture feetDef

		FixtureData fdata = new FixtureData()
		fdata.id = 3

		feetFixture.setUserData fdata

		feetShape.dispose()

		BodyData bdata = new BodyData()
		bdata.type = "entity"
		bdata.entity = entity

		body.setUserData bdata
		
		return body;
	}

	@Override
	public void createComponents(Entity entity, Vector2 pos) {
		entity.addComponent new LocationComponent(pos.x, pos.y)
		entity.addComponent new RenderComponent(AssetManagerWrapper.getManager().get("warrior_sheet"),
				"character_warrior_right")
		entity.addComponent new MovementComponent()
		entity.addComponent new PhysicsComponent(body)
	}

	@Override
	public void loadProperties(MapProperties props) {		
	}
}
