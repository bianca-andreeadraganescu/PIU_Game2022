package managers;

import enemies.Enemy;
import helpz.LoadSave;
import helpz.Utilz;
import lombok.Getter;
import lombok.Setter;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Towers.ARCHER;

@Getter
@Setter
public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImages;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImages();

    }

    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas("spriteatlas.png");
        towerImages = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            towerImages[i] = atlas.getSubimage((i + 4) * 32, 32, 32, 32);
        }
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyIsClose(t);
        }
    }

    private void attackEnemyIsClose(Tower t) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (isEnemyInRange(t, e) == true) {
                    if (t.isCooldownOver()) {
                        playing.shootEnemy(t, e);
                        t.resetCooldown();
                    }
                } else {
                    // nothing
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }


    public void draw(Graphics g) {
        //g.drawImage(towerImages[ARCHER],tower.getX(), tower.getY(), null );
        for (Tower t : towers)
            g.drawImage(towerImages[t.getTowerType()], t.getX(), t.getY(), null);
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }

    public BufferedImage[] getTowerImg() {
        return towerImages;
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }
}
