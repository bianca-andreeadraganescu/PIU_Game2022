package scenes;

import main.Game;

public class GameScene {

	protected Game game;
	protected int animationIndex;
	protected int ANNIMATION_SPEED = 25;
	protected int tick;

	public GameScene(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void updateTick(){
		tick++;
		if(tick >= ANNIMATION_SPEED){
			tick = 0;
			animationIndex++;
			if(animationIndex >= 4)
				animationIndex = 0;
		}
	}
}
