package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import observer.Observer;
import observer.Subject;
import reader.CompressedXMLReader;
import reader.EncryptedPropertiesFileReader;
import reader.PropertiesFileReader;

@SuppressWarnings("serial")
public class SourceFileTypeComboBox extends JPanel implements Subject {
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public SourceFileTypeComboBox(Observer o) {
		registerObserver(o);
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(sourceFileType());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox sourceFileType() {

		Vector model = new Vector();
		model.addElement(new ComboBoxItem("Properties",
				PropertiesFileReader.class));
		model.addElement(new ComboBoxItem("Compressed XML",
				CompressedXMLReader.class));
		model.addElement(new ComboBoxItem("Encrypted File",
				EncryptedPropertiesFileReader.class));

		JComboBox combo = new JComboBox(model);
		combo.setSelectedIndex(0);
		combo.setRenderer(new ItemRenderer());

		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ComboBoxItem item = (ComboBoxItem) combo.getSelectedItem();
				notifyObservers(item.getReaderWriter());
			}
		});

		return combo;
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void notifyObservers(Class object) {
		for (Observer observer : observers) {
			observer.updateSourceType(object);
		}
	}
}
