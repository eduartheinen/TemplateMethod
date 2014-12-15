package generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public abstract class FileGenerator {
	public final void generateFile(String filename,
			Map<String, Object> properties) throws IOException {

		String content = generateContent(properties);
		byte[] bytes = content.getBytes();
		bytes = process(bytes);
		FileOutputStream fileout = new FileOutputStream(filename);
		fileout.write(bytes);
		fileout.close();
	}

	protected byte[] process(byte[] bytes) throws IOException {
		return bytes;
	}

	protected abstract String generateContent(Map<String, Object> properties);
}
