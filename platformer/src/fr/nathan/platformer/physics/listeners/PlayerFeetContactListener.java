package fr.nathan.platformer.physics.listeners;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import fr.nathan.platformer.components.PhysicsComponent;
import fr.nathan.platformer.physics.BodyData;
import fr.nathan.platformer.physics.FixtureData;

public class PlayerFeetContactListener implements ContactListener {
	private static int nbFootContacts = 0;

	/**
	 * Returns the fixture representing the player's feet between f1 and f2 or
	 * null
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	private Fixture getFeetFixture(Fixture f1, Fixture f2) {
		if (f1.getBody().equals(f2.getBody())) { // do not consider contact
													// between fixtures of same
													// body
			return null;
		}
		if (f1.getUserData() != null && ((FixtureData) f1.getUserData()).id == 3) {
			return f1;
		} else if (f2.getUserData() != null && ((FixtureData) f2.getUserData()).id == 3) {
			return f2;
		}
		return null;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture feetFixture;

		if ((feetFixture = getFeetFixture(contact.getFixtureA(), contact.getFixtureB())) != null) {
			Entity playerEntity = ((BodyData) feetFixture.getBody().getUserData()).entity;

			nbFootContacts++;
			if (nbFootContacts > 0) {
				playerEntity.getComponent(PhysicsComponent.class).setGrounded(true);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture feetFixture;

		if ((feetFixture = getFeetFixture(contact.getFixtureA(), contact.getFixtureB())) != null) {
			Entity playerEntity = ((BodyData) feetFixture.getBody().getUserData()).entity;

			nbFootContacts--;
			if (nbFootContacts <= 0) {
				playerEntity.getComponent(PhysicsComponent.class).setGrounded(false);
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
