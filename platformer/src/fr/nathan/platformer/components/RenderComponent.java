package fr.nathan.platformer.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.nathan.platformer.Constants;

/**
 * 
 * @author Nathan
 * 
 */
public class RenderComponent extends Component {

	private TextureRegion region;
	private float unitWidth;
	private float unitHeight;

	private TextureAtlas textureAtlas;

	public RenderComponent(TextureRegion region) {
		this.region = region;

		unitWidth = region.getRegionWidth() / Constants.TILE_DIM;
		unitHeight = region.getRegionHeight() / Constants.TILE_DIM;

		// this.region.flip(false, true);
	}

	public RenderComponent(Texture text) {
		this(new TextureRegion(text));
	}

	public RenderComponent(TextureAtlas textureAtlas, String regionName) {
		this(textureAtlas.findRegion(regionName));

		this.textureAtlas = textureAtlas;
	}

	public RenderComponent(String path, Integer width, Integer height) {
		this(new TextureRegion(new Texture(Gdx.files.internal(path)), width, height));
	}

	public RenderComponent(String path) {
		this(new TextureRegion(new Texture(Gdx.files.internal(path))));
	}

	public TextureRegion getTexture() {
		return region;
	}

	public float getUnitWidth() {
		return unitWidth;
	}

	public float getUnitHeight() {
		return unitHeight;
	}

	public void setTexture(TextureRegion region) {
		this.region = region;
	}

	public void setTexture(String regionName) {
		this.region = textureAtlas.findRegion(regionName);
	}
}
