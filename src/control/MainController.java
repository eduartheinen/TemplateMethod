package control;

import generator.FileGenerator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import reader.FileReader;

public class MainController{
	private static MainController instance = new MainController();

	public static MainController getInstance() {
		return instance;
	}
	
	public void convertFile(String filename, File file, FileReader read, FileGenerator gen) {
		try {
			HashMap map = read.parseFile(file);
			gen.generateFile(filename, map);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
