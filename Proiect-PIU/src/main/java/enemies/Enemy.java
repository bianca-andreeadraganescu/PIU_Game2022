package enemies;

import helpz.Constants;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static helpz.Constants.Direction.*;

@Getter
@Setter
public abstract class Enemy {
    protected float x, y;
    protected Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;

    public Enemy(float x, float y, int id,int enemyType){
        this.x = x;
        this.y = y;
        this.ID = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 32, 32);
        lastDir = -1;
    }

    protected void setStartHealth(){
        health = Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }

    public float getHealthBarFloat(){
        return health/(float)maxHealth;
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

    public void hurt(int dmg) {
        this.health -= dmg;
        if(health <=0){
            alive = false;
        }
    }
}
