package generator;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesFileGenerator extends FileGenerator {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected String generateContent(Map<String, Object> map) {
		Properties properties = new Properties();
		Iterator it = map.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) it.next();
			properties.setProperty(entry.getKey(), entry.getValue().toString());
		}

		StringWriter writer = new StringWriter();
		try {
			properties.store(new PrintWriter(writer), "Properties from Template Method");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.getBuffer().toString();
	}

	@Override
	protected byte[] process(byte[] bytes) throws IOException {
		return bytes;
	}
}
