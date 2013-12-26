package fr.nathan.platformer;

public class Constants {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGTH = 480;

	public static final int[] CENTER_POINT = { SCREEN_WIDTH / 2, SCREEN_HEIGTH / 2 };

	/**
	 * Real size of tiles.
	 */
	public static final float TILE_DIM = 16f;

	/**
	 * In game size of tiles.
	 */
	public static final float VIRTUAL_TILE_DIM = 32f;

	public static final float UNIT_TO_PIXEL = VIRTUAL_TILE_DIM;
	public static final float PIXEL_TO_UNIT = 1f / UNIT_TO_PIXEL;

	/**
	 * Gravity value used in the physics simulation.
	 */
	public static final float GRAVITY = -9.8f;

	/**
	 * Movement speed.
	 */
	public static final float NORMAL_MOVEMENT_VELOCITY = 5f;

	/**
	 * Jump speed.
	 */
	public static final float NORMAL_JUMP_VELOCITY = 6;

	/*
	 * === TILED MAP RELATED ===
	 */

	/**
	 * Player entity tag.
	 */
	public static final String PLAYER_TAG = "PLAYER";

	/**
	 * Entity layer name.
	 */
	public static final String ENTITY_LAYER = "entities";

	/**
	 * Entity object type.
	 */
	public static final String ENTITY_TYPE = "entity";
}
