package view;

import generator.CompressedXMLGenerator;
import generator.EncryptedPropertiesFileGenerator;
import generator.PropertiesFileGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import observer.Observer;
import observer.Subject;

@SuppressWarnings("serial")
public class ResultFileTypeComboBox extends JPanel implements Subject {
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public ResultFileTypeComboBox(Observer o) {
		registerObserver(o);

		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(resultFileType());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox resultFileType() {

		Vector model = new Vector();
		model.addElement(new ComboBoxItem("Properties",
				PropertiesFileGenerator.class));
		model.addElement(new ComboBoxItem("Compressed XML",
				CompressedXMLGenerator.class));
		model.addElement(new ComboBoxItem("Encrypted File",
				EncryptedPropertiesFileGenerator.class));

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
			observer.updateResultType(object);
		}
	}
}
