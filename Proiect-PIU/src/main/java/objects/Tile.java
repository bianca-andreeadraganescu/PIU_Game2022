package objects;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class Tile {

	private BufferedImage sprite;
	private String name;
	private int id;
	private int tyleType;

	public Tile(BufferedImage sprite, int id, int tyleType)
	{
		this.sprite = sprite;
		this.id = id;
		this.tyleType = tyleType;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
