package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import objects.Tile;

public class TileManager {

	public Tile MARGIN, REDLINE, MIDDLE;
	private BufferedImage atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();

	public TileManager() {

		loadAtalas();
		createTiles();

	}

	private void createTiles() {
		int id=0;
		tiles.add(MARGIN = new Tile(getSprite(10, 0), id++, "MARGIN"));
		tiles.add(REDLINE = new Tile(getSprite(9, 0), id++, "REDLINE"));
		tiles.add(MIDDLE = new Tile(getSprite(11, 2), id++,"MIDLINE"));

	}

	public Tile getTile(int id){
		return tiles.get(id);
	}

	private void loadAtalas() {

		atlas = LoadSave.getSpriteAtlas();

	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

}
