package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.Constants;
import helpz.LoadSave;
import objects.Tile;
import static helpz.Constants.Tiles.*;

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
		tiles.add(MARGIN = new Tile(getSprite(9, 0), id++, MARGIN_TILE));
		tiles.add(REDLINE = new Tile(getSprite(7, 0), id++, REDLINE_TILE));
		tiles.add(MIDDLE = new Tile(getSprite(5, 2), id++, REDLINE_TILE));
		tiles.add(REDLINE = new Tile(getSprite(6, 0), id++, REDLINE_TILE));
		tiles.add(REDLINE = new Tile(getSprite(6, 1), id++, REDLINE_TILE));
		tiles.add(REDLINE = new Tile(getSprite(8, 2), id++, REDLINE_TILE));

	}

	public Tile getTile(int id){
		return tiles.get(id);
	}

	private void loadAtalas() {

		atlas = LoadSave.getSpriteAtlas("Tiles.png");

	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

}
