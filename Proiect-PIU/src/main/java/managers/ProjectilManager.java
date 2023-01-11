package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectil;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Enemies.BAT;
import static helpz.Constants.Enemies.MONSTER;
import static helpz.Constants.Projectiles.ARROW;
import static helpz.Constants.Projectiles.BOMB;

public class ProjectilManager {

    private Playing playing;
    private ArrayList<Projectil> projectils = new ArrayList<>();
    private BufferedImage[] projectilImg;
    private int projID = 0;

    public ProjectilManager(Playing playing) {
        this.playing = playing;
        importImg();
    }

    private void importImg() {
        BufferedImage atlas = LoadSave.getSpriteAtlas("spriteatlas.png");
        projectilImg = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            projectilImg[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
        }
    }

    public void draw(Graphics g) {
        for (Projectil p : projectils) {
            if(p.isActive())
                g.drawImage(projectilImg[p.getProjectileType()], (int) p.getPos().x, (int) p.getPos().y, null);
        }
    }

    public void update() {
        for (Projectil p : projectils) {
            if (p.isActive()) {
                p.move();
                if (isProjectilHittingEnemy(p)) {
                    p.setActive(false);
                } else {
                }
            }
        }
    }

    private boolean isProjectilHittingEnemy(Projectil p) {
        for(Enemy e: playing.getEnemyManager().getEnemies()){
            if(e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void newProjectil(Tower t, Enemy e) {
        int type = getProjectilType(t);

        int xdist = (int) Math.abs(t.getX() - e.getX());
        int ydist = (int) Math.abs(t.getY() - e.getY());
        int totalDistance = xdist + ydist;

        float xPer = (float) xdist / totalDistance;
        float yPer = 1.0f - xPer;

        float xspeed = xPer * Constants.Projectiles.GetSpeed(type);
        float yspeed = Constants.Projectiles.GetSpeed(type) - xspeed;

        if (t.getX() > e.getX())
            xspeed *= -1;
        if (t.getY() > e.getY())
            yspeed *= -1;

        projectils.add(new Projectil(t.getX() + 16, t.getY() + 16, xspeed, yspeed,t.getDmg(), projID++, type));
    }

    private int getProjectilType(Tower t) {

        switch (t.getTowerType()) {
            case MONSTER:
                return ARROW;
            case BAT:
                return BOMB;
        }
        return 0;
    }
}
