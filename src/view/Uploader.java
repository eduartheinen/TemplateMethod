/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author vinicius
 */
public class Uploader {

	public static void upload() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));

		int r = chooser.showOpenDialog(new JFrame());
		if (r == JFileChooser.APPROVE_OPTION) {

//			FileReader reader = new EncryptedPropertiesFileReader(23);
//			FileGenerator writer = new PropertiesFileGenerator();
//			try {
//				writer.generateFile("properties_from_encrypted(23)_from_compressedXML",
//						reader.parseFile(chooser.getSelectedFile()));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			

		}
		if (r == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancelando ... ");
		}
	}

}
