package fr.nathan.platformer.scripts.monsters

import java.awt.geom.Arc2D.Float;

import com.artemis.Entity;
import static java.util.Calendar.*;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import fr.nathan.platformer.Constants;
import fr.nathan.platformer.components.MovementComponent;
import fr.nathan.platformer.scripts.EntityScript;

class BeeBehaviorScript implements EntityScript {

	private int directionChangeInterval;

	private MovementComponent movComp;
	
	public BeeBehaviorScript(int directionChangeInterval) {
		this.directionChangeInterval = directionChangeInterval;
	}

	@Override
	public void init(World world, Entity entity) {
		movComp = entity.getComponent(MovementComponent.class);
		
		def timer = new Timer()
		def task = { movComp.setVelX (movComp.getVelX() * -1 as float) }
		timer.schedule task as TimerTask, directionChangeInterval, directionChangeInterval
	}

	@Override
	public void update(World world, Entity entity) {
		movComp.setVelX movComp.getVelX()
	}

	@Override
	public void dispose(World world, Entity entity) {
	}
}
