package helpz;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage spriteSheet;
    private BufferedImage[][] spriteArray;
    private static final int tileWidth = 32;
    private static final int tileHeight = 32;

    public SpriteSheet(BufferedImage img) {
        spriteSheet = img;
    }

    public BufferedImage crop(int x, int y, int w, int h, int scale) {
        return spriteSheet.getSubimage(x * scale, y * scale, scale * w, scale * h);
    }
}
