package scenes;

import java.awt.Graphics;

import helpz.LevelBuild;
import lombok.Getter;
import lombok.Setter;
import main.Game;
import managers.TileManager;
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
	private BottomBar bottomBar;

	public Playing(Game game) {
		super(game);

		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
		bottomBar = new BottomBar(0,640, 640, 100, this);

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
	}





	// it is checked if the bottom is in that area
	@Override
	public void mouseClicked(int x, int y) {
		if(y>=640){
			bottomBar.mouseClicked(x,y);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if(y>=640){
			bottomBar.mouseMoved(x,y);
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
}