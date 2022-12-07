package scenes;

import java.awt.Graphics;
import java.io.FileNotFoundException;

import helpz.LevelBuild;
import helpz.LoadSave;
import lombok.Getter;
import lombok.Setter;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;
import ui.MyButton;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

@Getter
@Setter
public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private TileManager tileManager;
	private MyButton bMenu;
	private Tile selectedTile;

	private BottomBar bottomBar;
	private int mouseX, mouseY;
	private boolean drawSelect;
	private int lastTileX, lastTileY;

	public Playing(Game game)   {
		super(game);

		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
		bottomBar = new BottomBar(0,640, 640, 100, this);
		createDefaultLevel();
		loadDefaultLevel();
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("newLevel");
	}

	private void createDefaultLevel() {
		int[] arr = new int[400];
		for(int i=0; i<arr.length; i++){
			arr[i] = 0;
		}
		LoadSave.CreateLevel("newLevel", arr);
	}


	@Override
	public void render(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
			}
		}
		bottomBar.draw(g);
		drawSelectedTile(g);
	}

	private void drawSelectedTile(Graphics g) {
		if(selectedTile != null && drawSelect){
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
		}
	}

	public void setSelectedTile(Tile tile){
		this.selectedTile = tile;
		drawSelect = true;
	}



	// it is checked if the bottom is in that area
	@Override
	public void mouseClicked(int x, int y) {
		if(y>=640){
			bottomBar.mouseClicked(x,y);
		} else {
			changeTile(mouseX, mouseY);
		}
	}

	private void changeTile(int x, int y) {
		if(selectedTile != null){
			int tileX = x/32;
			int tileY = y/32;
			if(lastTileX == tileX && lastTileY == tileY){
				return;
			}
			lastTileX = tileX;
			lastTileY = tileY;
			lvl[tileY][tileX] = selectedTile.getId();
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
		if(y>=640){
			bottomBar.mouseMoved(x,y);
			drawSelect = false;
		}else{
			// for exact coordinates to snap
			drawSelect = true;
			mouseX =(x/32)*32;
			mouseY =(y/32)*32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(y>=640){
			bottomBar.mousePressed(x,y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		if(y>=640){
			bottomBar.mouseReleased(x,y);
		}
	}

	@Override
	public void mouseDragged(int x, int y) {
		if( y>=640){

		}else{
			changeTile(x,y);
		}
	}

	public void saveLevel() {
		LoadSave.SaveLevel("newLevel", lvl);
	}
}