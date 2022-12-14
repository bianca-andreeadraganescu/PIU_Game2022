package helpz;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {

	//Incarcarea sprite-ului care contine toate tile-urile
	//Fiecare tile e de 32x32
	public static BufferedImage getSpriteAtlas(String name) {

		BufferedImage img = null;
		InputStream is1 = LoadSave.class.getClassLoader().getResourceAsStream(name);
		InputStream is2 = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

		try {
			img = ImageIO.read(is1);
			//img[1] = ImageIO.read(is2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	// txt file
//	public static void CreateFile() {
//		File txtFile = new File("testTextFile.txt");
//		try {
//			txtFile.createNewFile();
//		} catch (IOException e){
//			e.printStackTrace();
//		}
//	}


	public static void CreateLevel(String name, int[] idArr){
		File newLevel = new File(name + ".txt");
		if(newLevel.exists()){
			System.out.println("File: " + name + "already exists!");
			return;
		}else{
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			WriteToFile(newLevel, idArr);
		}

	}

	public static void SaveLevel(String name, int[][] idArr) {
		File levelFile = new File(name + ".txt");
		if(levelFile.exists()){
			WriteToFile(levelFile,Utilz.TwoDTo1DintArr(idArr));
		}else{
			System.out.println("File: "+ name + " does not exists! ");
			return;
		}

	}

	private static void WriteToFile(File f, int[] idArr) {
		try{
			PrintWriter pw = new PrintWriter(f);
			for(Integer i : idArr){
				pw.println(i);
			}
			pw.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static ArrayList<Integer> ReadFromFile(File file){
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()){
				list.add(Integer.parseInt(sc.nextLine()));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int[][] GetLevelData(String name){
		File lvlFile = new File(name + ".txt");
		if(lvlFile.exists()){
			ArrayList<Integer> list = ReadFromFile(lvlFile);
			return Utilz.ArrayListTo2DInt(list,20,20);
		}else{
			System.out.println("File: "+ name + " does not exists! ");
			return null;
		}
	}



}
