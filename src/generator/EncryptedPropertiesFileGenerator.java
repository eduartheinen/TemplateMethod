package generator;

import java.io.IOException;
import java.util.Map;

public class EncryptedPropertiesFileGenerator extends FileGenerator {

	private int delay;

	public EncryptedPropertiesFileGenerator() {
		//hardcoded
		this.delay = 23;
	}

	@Override
	protected byte[] process(byte[] bytes) throws IOException {
		byte[] newBytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			newBytes[i] = (byte) ((bytes[i] + delay) % Byte.MAX_VALUE);
		}
		return newBytes;
		
	}

	@Override
	protected String generateContent(Map<String, Object> properties) {
		StringBuilder propFileBuilder = new StringBuilder();
		for (String prop : properties.keySet()) {
			propFileBuilder.append(prop + "=" + properties.get(prop) + "\n");
		}
		return propFileBuilder.toString();
	}
}
