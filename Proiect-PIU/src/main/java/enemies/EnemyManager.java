package enemies;

import helpz.Constants;
import helpz.LoadSave;
import lombok.Getter;
import lombok.Setter;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Direction.*;
import static helpz.Constants.Enemies.*;
import static helpz.Constants.Tiles.*;

@Getter
@Setter
public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    //private float speed = 0.5F;
    private int healthBar = 20;
    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        addEnemy(0*32, 13*32, MONSTER);
        addEnemy(5*32, 13*32, BAT);
        loadEnemyImgs();
    }
    private void loadEnemyImgs(){
        // for performance loading img enemies
        BufferedImage atlas = LoadSave.getSpriteAtlas("spriteatlas.png");
        enemyImgs[0] = atlas.getSubimage(0, 32, 32, 32);
        enemyImgs[1] = atlas.getSubimage(32, 32, 32, 32);
//        for(int i=0; i< 2; i++){
//            enemyImgs[i] = atlas.getSubimage(i*32, 32, 32, 32);
//        }
    }

    public void update(){
        // is next tile a road go
        for(Enemy e: enemies){
            if(e.isAlive()) {
                updateEnemyMove(e);
            }
        }
    }

    // we need to check if tile is possible , dir
    private void updateEnemyMove(Enemy e) {
        if(e.getLastDir() == -1){
            setNEwDirectionAndMove(e);
        }
        int newX = (int)(e.getX() + GetSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int)(e.getY() + GetSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

        if(getTileType(newX, newY) == REDLINE_TILE){
            //keep moving in same direction
            e.Move(GetSpeed(e.getEnemyType()), e.getLastDir());
        }else if (isAtEnd(e)) {

        }else {
            //set new direction
            setNEwDirectionAndMove(e);
        }
    }

    private void setNEwDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        // move in the perfect position
        int xCord = (int)(e.getX()/32);
        int yCord = (int)(e.getY()/32);
        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if(dir== LEFT || dir ==RIGHT){
            int newY = (int)(e.getY() + GetSpeedAndHeight(UP, e.getEnemyType()));
            if(getTileType((int)e.getX(), newY) == REDLINE_TILE){
                e.Move(GetSpeed(e.getEnemyType()), UP);
            } else{
                e.Move(GetSpeed(e.getEnemyType()), DOWN);
            }
        } else{
            int newX = (int) (e.getX() + GetSpeedAndWidth(RIGHT, e.getEnemyType()));
            if(getTileType(newX, (int)e.getY()) == REDLINE_TILE){
                e.Move(GetSpeed(e.getEnemyType()), RIGHT);
            } else{
                e.Move(GetSpeed(e.getEnemyType()), LEFT);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir){
            case RIGHT:
                if(xCord<19)
                    xCord++;
                break;
            case DOWN:
                if(yCord<19)
                    yCord++;
                break;
        }
        e.setPos(xCord*32, yCord*32);
    }

    private boolean isAtEnd(Enemy e) {
        //TODO: here we ll check if the enemy is at the final of the road
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x,y);
    }

    private float GetSpeedAndHeight(int dir, int enemyType) {
        if(dir == UP){
            return -GetSpeed(enemyType);
        } else if(dir == DOWN){
            return GetSpeed(enemyType)+32;
        }
        return 0;

    }

    private float GetSpeedAndWidth(int dir, int enemyType) {
        if(dir == LEFT){
            return -GetSpeed(enemyType);
        } else if(dir == RIGHT){
            return GetSpeed(enemyType)+32;
        }
        return 0;
    }

    public void addEnemy(int x, int y, int enemyType){
        switch(enemyType){
            case MONSTER:
                enemies.add(new Monster(x,y,0));
                break;
            case BAT:
                enemies.add(new Bat(x, y, 0));
                break;
        }
    }

    public void draw(Graphics g){
        for(Enemy e: enemies){
            if(e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
            }
        }
    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX() + 16 - (getNewBarWidth(e)/2), (int)e.getY()-5,getNewBarWidth(e),3);
    }

    private int getNewBarWidth(Enemy e){
        return (int)(healthBar * e.getHealthBarFloat());
    }



    private void drawEnemy(Enemy e, Graphics g){
        g.drawImage(enemyImgs[e.getEnemyType()],(int)e.getX(), (int)e.getY(), null );
    }

}
