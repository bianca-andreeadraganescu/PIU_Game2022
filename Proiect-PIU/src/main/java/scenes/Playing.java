package scenes;

import enemies.EnemyManager;
import helpz.LoadSave;
import lombok.Getter;
import lombok.Setter;
import main.Game;
import managers.TowerManager;
import objects.Tower;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helpz.Constants.Tiles.MARGIN_TILE;
import static helpz.Constants.Tiles.MIDLINE_TILE;

@Getter
@Setter
public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private ActionBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private Tower selectedTower;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();

        bottomBar = new ActionBar(0, 640, 640, 100, this);

        enemyManager = new EnemyManager(this);

        towerManager = new TowerManager(this);
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


    public void update() {
        enemyManager.update();
        towerManager.update();
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    @Override
    public void render(Graphics g) {

        drawLevel(g);
        bottomBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        drawSelectedTower(g);
        drawHighLight(g);
    }
    public void drawHighLight(Graphics g){
        g.setColor(Color.GREEN);
       g.drawRect(mouseX, mouseY,32, 32);
    }

    private void drawSelectedTower(Graphics g) {
        if(selectedTower != null)
            g.drawImage(towerManager.getTowerImages()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    public int getTileType(int x, int y) {
        int xCord = x / 32;
        int yCord = y / 32;
        if (xCord < 0 || xCord > 19) {
            return 0;
        }
        if (yCord < 0 || yCord > 19) {
            return 0;
        }
        int id = lvl[y / 32][x / 32];
        return game.getTileManager().getTile(id).getTyleType();
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
        else {
            if(selectedTower != null) {
                if (isTileStone(mouseX, mouseY)) {
                    if(getTowerAt(mouseX, mouseY) == null) {
                        towerManager.addTower(selectedTower, mouseX, mouseY);
                        selectedTower = null;
                    }
                }
            } else {
                Tower t = getTowerAt(mouseX,mouseY);
                    bottomBar.displaayTower(t);
                }
            }
        }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x,y);
    }

    private boolean isTileStone(int x, int y) {
        int id = lvl[y/32][x/32];
        int tileType = game.getTileManager().getTile(id).getTyleType();

        return tileType == MARGIN_TILE;

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

    public TowerManager getTowerManager() {
        return towerManager;
    }
}