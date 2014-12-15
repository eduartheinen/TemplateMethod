package reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesFileReader extends FileReader {
	private HashMap<String, Object> map;

	@Override
	protected String readFileContent(File selectedFile) {
		map = new HashMap<String, Object>();
		Properties prop = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(selectedFile);
			prop.load(file);
			for (String key : prop.stringPropertyNames()) {
				map.put(key, prop.get(key));
			}
			return prop.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected HashMap<String, Object> generateContent(String content) {
		/*
		 * Problema com o template:
		 * 
		 * A leitura de XML segue o template: readFileContent(File) depois
		 * generateContent(String). O método generateContent recebe a string
		 * retirada dos bytes do arquivo compactado, lê ela como um XML e insere
		 * a informação dos "nodes" em um hashMap que é retornado para criar o
		 * arquivo, como o programa exemplo da aula.
		 * 
		 * O problema é que a leitura do arquivo de propriedades é muito mais
		 * natural, não faz sentido transformar em String e depois inserir no
		 * map e não posso alterar os argumentos do método generateContent() sem
		 * receber um aviso de erro.
		 */
		return map;
	}
}
