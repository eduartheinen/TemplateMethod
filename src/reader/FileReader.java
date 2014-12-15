package reader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public abstract class FileReader {
	public final HashMap<String, Object> parseFile(File selectedFile)
			throws IOException {
		String content = readFileContent(selectedFile);
		HashMap<String, Object> map = generateContent(content);
		return map;
	}

	protected abstract String readFileContent(File selectedFile);

	protected abstract HashMap<String, Object> generateContent(String content);
}
