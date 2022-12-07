package objects;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class Tile {

	private BufferedImage sprite;
	private String name;
	private int id;

	public Tile(BufferedImage sprite, int id, String name)
	{
		this.sprite = sprite;
		this.id = id;
		this.name = name;
	}


}
