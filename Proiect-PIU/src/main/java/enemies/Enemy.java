package enemies;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static helpz.Constants.Direction.*;

@Getter
@Setter
public abstract class Enemy {
    private float x, y;
    private Rectangle bounds;
    private int health;
    private int ID;
    private int enemyType;
    private int lastDir;

    public Enemy(float x, float y, int id,int enemyType){
        this.x = x;
        this.y = y;
        this.ID = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 32, 32);
        lastDir = -1;
    }

    public void Move(float speed, int dir){
        lastDir = dir;
        switch (dir){
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;

        }
    }

    public void setPos(int x, int y){
        //for position fix, future move
        this.x = x;
        this.y = y;
    }
}
