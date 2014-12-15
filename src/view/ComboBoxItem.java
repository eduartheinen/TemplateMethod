package view;

@SuppressWarnings("rawtypes")
public class ComboBoxItem {
	public String description;
	public Class readerWriter;

	public ComboBoxItem(String description, Class readerwriter) {
		this.description = description;
		this.readerWriter = readerwriter;
	}

	public String getDescription() {
		return description;
	}

	public Class getReaderWriter() {
		return readerWriter;
	}
}