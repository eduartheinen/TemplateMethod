package reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CompressedXMLReader extends FileReader {
	@SuppressWarnings("unused")
	@Override
	protected String readFileContent(File selectedFile) {
		StringBuilder propFileBuilder = new StringBuilder();
		try {

			FileInputStream fin = new FileInputStream(selectedFile);
			ZipInputStream zin = new ZipInputStream(fin);
			ZipEntry ze = null;

			while ((ze = zin.getNextEntry()) != null) {
				for (int c = zin.read(); c != -1; c = zin.read()) {
					propFileBuilder.append((char) c);
				}
				zin.closeEntry();
			}
			zin.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return propFileBuilder.toString();
	}

	@Override
	protected HashMap<String, Object> generateContent(String content) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(content));
			
			try {
				Document doc = db.parse(is);
				doc.getDocumentElement().normalize();

				NodeList nodesList = doc.getDocumentElement().getChildNodes();

				for (int i = 0; i < nodesList.getLength(); i++) {
					Node n = nodesList.item(i);
					map.put(n.getNodeName(), n.getFirstChild().getNodeValue());
				}

				return map;
			} catch (SAXException e) {
				// handle SAXException
			} catch (IOException e) {
				// handle IOException
			}
		} catch (ParserConfigurationException e1) {
			// handle ParserConfigurationException
		}
		return null;
	}
}
