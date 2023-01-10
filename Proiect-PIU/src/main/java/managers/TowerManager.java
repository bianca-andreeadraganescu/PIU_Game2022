package managers;

import helpz.LoadSave;
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
        for(int i=0; i<3; i++){
            towerImages[i] = atlas.getSubimage((i+4)*32,32, 32, 32);
        }
    }

    public void update(){

    }

    public void draw(Graphics g){
        //g.drawImage(towerImages[ARCHER],tower.getX(), tower.getY(), null );
        for(Tower t: towers)
            g.drawImage(towerImages[t.getTowerType()], t.getX(), t.getY(), null);
    }

    public BufferedImage[] getTowerImg(){
            return towerImages;
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }
}
