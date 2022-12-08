package enemies;

import helpz.LoadSave;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private Enemy testEnemy;
    private float speed = 0.5F;

    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        testEnemy = new Enemy(32*1, 32*17, 0,0);
        loadEnemyImgs();
    }
    private void loadEnemyImgs(){
        // for performance loading img enemies
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        enemyImgs[0] = LoadSave.getSpriteAtlas().getSubimage(0, 32, 32, 32);
        enemyImgs[1] = LoadSave.getSpriteAtlas().getSubimage(32, 32, 32, 32);
        enemyImgs[2] = LoadSave.getSpriteAtlas().getSubimage(2*32, 32, 32, 32);
        enemyImgs[3] = LoadSave.getSpriteAtlas().getSubimage(3*32, 32, 32, 32);

    }

    public void update(){
        // is next tile a road go
        if(isNextTileRoad(testEnemy)){
            
        }
    }

    // we need to check if tile is possible , dir
    private boolean isNextTileRoad(Enemy e) {
        int newX = (int)(e.getX() + GetSpeedAndWidth(e.getLastDir()));
        int newY = (int)(e.getY() + GetSpeedAndHeight(e.getLastDir()));

        if(getTileType(newX, newY) == REDLINE_TILE){
            //keep moving in same direction
            e.Move(speed, e.getLastDir());
        }else if (isAtEnd(e)) {

        }else {
            //set new direction
            setNEwDirectionAndMove(e);
        }

        return false;
    }

    private void setNEwDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        // move in the perfect position
        int xCord = (int)(e.getX()/32);
        int yCord = (int)(e.getY()/32);
        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if(dir== LEFT || dir ==RIGHT){
            int newY = (int)(e.getY() + GetSpeedAndHeight(UP));
            if(getTileType((int)e.getX(), newY) == REDLINE_TILE){
                e.Move(speed, UP);
            } else{
                e.Move(speed,DOWN);
            }
        } else{
            int newX = (int) (e.getX() + GetSpeedAndWidth(RIGHT));
            if(getTileType(newX, (int)e.getY()) == REDLINE_TILE){
                e.Move(speed, RIGHT);
            } else{
                e.Move(speed,LEFT);
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
                if(yCord<0)
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

    private float GetSpeedAndHeight(int dir) {
        if(dir == UP){
            return -speed;
        } else if(dir == DOWN){
            return speed+32;
        }
        return 0;

    }

    private float GetSpeedAndWidth(int dir) {
        if(dir == LEFT){
            return -speed;
        } else if(dir == RIGHT){
            return speed+32;
        }
        return 0;
    }

    public void draw(Graphics g){
        drawEnemy(testEnemy, g);
    }

    private void drawEnemy(Enemy e, Graphics g){
        g.drawImage(enemyImgs[0],(int)e.getX(), (int)e.getY(), null );
    }

}
