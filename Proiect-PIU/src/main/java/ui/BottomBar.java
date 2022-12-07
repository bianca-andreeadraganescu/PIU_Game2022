package ui;

import objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class BottomBar {
    //bottom bar
    private int x, y, width, height;
    private Playing playing;
    private MyButton bMenu;

    private Tile selectedTile;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);

        int w=50;
        int h=50;
        int xStart=110;
        int yStart=650;
        int xOffset=(int)(w*1.1f);

        int i=0;
        for(Tile tile: playing.getTileManager().tiles){
            tileButtons.add(new MyButton(tile.getName(),xStart+xOffset*i
                    ,yStart,w,h,i));
            i++;
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        drawTileButtons(g);
        drawSelectedTIle(g);

    }

    private void drawSelectedTIle(Graphics g) {
        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(),550,650,50,50,null);
            g.setColor(Color.BLUE);
            g.drawRect(550,650,50,50);
        }
    }

    private BufferedImage getButtImg(int id) {
        return playing.getTileManager().getSprite(id);
    }


    private void drawTileButtons(Graphics g){
        for(MyButton b: tileButtons) {

            //Image
            g.drawImage(getButtImg(b.getId()),
                    b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
            //Mouseover
            if(b.isMouseOver()){
                g.setColor(Color.white);
            }else{
                g.setColor(Color.black);
            }
            //Border
            g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

            //MousePressed
            if(b.isMousePressed()) {
                g.drawRect(b.getX() + 1, b.getY() + 1, b.getWidth() - 2, b.getHeight() - 2);
                g.drawRect(b.getX() + 2, b.getY() + 2, b.getWidth() - 4, b.getHeight() - 4);
            }







            g.drawImage(getButtImg(b.getId()),
                    b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
        }
    }


    public void draw(Graphics g){
        //background color
        g.setColor(new Color(121, 80, 22));
        g.fillRect(x, y, width, height);

        //buttons
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else{
            for(MyButton b: tileButtons){
                if(b.getBounds().contains(x,y)){
                    selectedTile = playing.getTileManager().getTile(b.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }

    }
    // check if mouse is moved over button
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for(MyButton b : tileButtons){
            b.setMouseOver(false);
        }
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else{
            for(MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)){
                    b.setMouseOver(true);
                    return;
                }
            }
        }

    }

    public void mousePressed(int x, int y) {
        bMenu.resetBooleans();
        for(MyButton b : tileButtons){
            b.resetBooleans();
        }
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x,y)){
                    b.setMousePressed(true);
                    return;
                }
            }
        }

    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();

    }
}
