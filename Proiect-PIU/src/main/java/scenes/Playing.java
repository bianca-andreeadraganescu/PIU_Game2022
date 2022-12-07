package scenes;

import helpz.LoadSave;
import lombok.Getter;
import lombok.Setter;
import main.Game;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private ActionBar bottomBar;
    private int mouseX, mouseY;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();

        bottomBar = new ActionBar(0, 640, 640, 100, this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("newLevel");
    }

    //	private void createDefaultLevel() {
//		int[] arr = new int[400];
//		for(int i=0; i<arr.length; i++){
//			arr[i] = 0;
//		}
//		LoadSave.CreateLevel("newLevel", arr);
//	}
    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }


    @Override
    public void render(Graphics g) {

        drawLevel(g);
        bottomBar.draw(g);
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }


//	private void drawSelectedTile(Graphics g) {
//		if(selectedTile != null && drawSelect){
//			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
//		}
//	}

//	public void setSelectedTile(Tile tile){
//		this.selectedTile = tile;
//		drawSelect = true;
//	}


    // it is checked if the bottom is in that area
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640)
            bottomBar.mouseClicked(x, y);
    }

//	private void changeTile(int x, int y) {
//		if(selectedTile != null){
//			int tileX = x/32;
//			int tileY = y/32;
//			if(lastTileX == tileX && lastTileY == tileY){
//				return;
//			}
//			lastTileX = tileX;
//			lastTileY = tileY;
//			lvl[tileY][tileX] = selectedTile.getId();
//		}
//
//	}

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640)
            bottomBar.mouseMoved(x, y);
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

}