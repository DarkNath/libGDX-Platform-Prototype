package fr.nathan.platformer.util;

import fr.nathan.platformer.Constants;

/**
 * Utility class to convert values from pixel to unit system or unit system to
 * pixel.
 * 
 * @author Nathan
 * 
 */
public class UnitConvertor {

	public static float TO_PIXEL(float v) {
		return v * Constants.UNIT_TO_PIXEL;
	}

	public static float TO_UNIT(float v) {
		return v * Constants.PIXEL_TO_UNIT;
	}
}
