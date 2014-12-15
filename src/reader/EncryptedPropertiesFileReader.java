package reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class EncryptedPropertiesFileReader extends FileReader {

	private int delay;

	public EncryptedPropertiesFileReader() {
		//hardcoded
		this.delay = 23;
	}

	@Override
	protected String readFileContent(File selectedFile) {
		try {
			byte[] data = Files.readAllBytes(selectedFile.toPath());
			byte[] newData = new byte[data.length];

			for (int i = 0; i < data.length; i++) {
				newData[i] = (byte) ((data[i] + Byte.MAX_VALUE - delay) % Byte.MAX_VALUE);
			}

			return new String(newData, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected HashMap<String, Object> generateContent(String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] parts = content.split("\n");
		for (String part : parts) {
			String[] property = part.split("=");
			map.put(property[0], property[1]);
		}
		return map;
	}

}
