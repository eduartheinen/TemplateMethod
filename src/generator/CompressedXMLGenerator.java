package generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressedXMLGenerator extends FileGenerator {

	@Override
	protected byte[] process(byte[] bytes) throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ZipOutputStream out = new ZipOutputStream(byteOut);
		out.putNextEntry(new ZipEntry("internal"));
		out.write(bytes);
		out.closeEntry();
		out.close();
		return byteOut.toByteArray();
	}

	@Override
	protected String generateContent(Map<String, Object> properties) {
		StringBuilder propFileBuilder = new StringBuilder();
		propFileBuilder.append("<?xml version=\"1.0\"?><properties>");
		for (String prop : properties.keySet()) {
			propFileBuilder.append("<" + prop + ">" + properties.get(prop)
					+ "</" + prop + ">");
		}

		propFileBuilder.append("</properties>");
		return propFileBuilder.toString();
	}
}
