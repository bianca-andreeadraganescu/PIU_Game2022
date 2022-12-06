package helpz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

	//Incarcarea sprite-ului care contine toate tile-urile
	//Fiecare tile e de 32x32
	public static BufferedImage getSpriteAtlas() {

		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("Tiles.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
