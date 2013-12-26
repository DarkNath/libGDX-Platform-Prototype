package fr.nathan.platformer.physics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public final class MainContactListener implements ContactListener {

	private List<ContactListener> listeners = new ArrayList<ContactListener>();

	public void addContactListener(ContactListener listener) {
		listeners.add(listener);
	}

	@Override
	public void beginContact(Contact contact) {
		for (ContactListener l : listeners) {
			l.beginContact(contact);
		}
	}

	@Override
	public void endContact(Contact contact) {
		for (ContactListener l : listeners) {
			l.endContact(contact);
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
