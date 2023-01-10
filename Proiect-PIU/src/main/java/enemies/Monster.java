package enemies;

import static helpz.Constants.Enemies.MONSTER;

public class Monster extends Enemy{
    public Monster(float x, float y, int id) {
        super(x, y, id, MONSTER);
    }
}
