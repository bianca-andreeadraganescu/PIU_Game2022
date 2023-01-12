package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.*;

import helpz.Constants;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar {

    private Playing playing;
    private MyButton bMenu;

    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;
    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {

        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        towerButtons = new MyButton[3];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);
        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        for (MyButton b : towerButtons) {
            g.setColor(Color.lightGray);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImages()[b.getId()], b.x, b.y, b.width, b.height, null);

            drawButtonFeedback(g, b);
        }
    }

    public void draw(Graphics g) {

        // Background
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
        drawDisplayedTower(g);
    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower != null){
            g.setColor(Color.GRAY);
            g.fillRect(410,645, 220, 85);
            g.setColor(Color.BLACK);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImages()[displayedTower.getTowerType()], 420, 650, 50, 50 , null);
            g.setFont(new Font("Monospaced", Font.BOLD,15));
            g.drawString("" + Constants.Towers.GetName(displayedTower.getTowerType()) ,490,660);
            g.drawString("ID: " + displayedTower.getId() ,490,675);
            g.drawString("Damage: " + displayedTower.getDmg() ,490,690);
            g.drawString("Range: " + displayedTower.getRange() ,490,705);

            drawDisplayedTowerRange(g);
        }
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.white);
        //TODO: why this doesn't work
        // it is in center of a tower but not in the center of the circle
        g.drawOval(displayedTower.getX() + 16 - (int) displayedTower.getRange()/2
                , displayedTower.getY() + 16 - (int) displayedTower.getRange()/2,
                (int) (displayedTower.getRange()/32)*32, (int)( displayedTower.getRange()/32)*32);
        //g.drawOval(5,5,5,5);
    }


    public void displayTower(Tower t){
        displayedTower = t;
    }


    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else
            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x,y)){
                    selectedTower = new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }

    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for (MyButton b : towerButtons)
            b.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else {
            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        }
        else{
            for(MyButton b: towerButtons)
                if(b.getBounds().contains(x,y)){
                    b.setMousePressed(true);
                    return;
                }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        for(MyButton b : towerButtons)
            b.resetBooleans();
    }

}

