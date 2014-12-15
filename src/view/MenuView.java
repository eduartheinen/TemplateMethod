package view;

import generator.FileGenerator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import observer.Observer;
import reader.FileReader;
import control.MainController;

public class MenuView extends JPanel implements Observer {
	private static final long serialVersionUID = 4170601937523669733L;
	ArrayList<Observer> observers = new ArrayList<Observer>();
	private File file;
	private FileGenerator gen;
	private FileReader read;
	private JTextField resultFileName = new JTextField();

	public MenuView() {

		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		this.add(selectFile());
		this.add(Box.createHorizontalStrut(5));

		this.add(new SourceFileTypeComboBox(this));
		this.add(Box.createHorizontalStrut(5));

		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(5));

		resultFileName.setPreferredSize(new Dimension(200, 24));
		this.add(resultFileName);
		this.add(Box.createHorizontalStrut(5));

		this.add(new ResultFileTypeComboBox(this));
		this.add(Box.createHorizontalStrut(5));

		this.add(convertFile());
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	private JButton selectFile() {
		JButton selectFileButton = new JButton("Escolha o arquivo");
		selectFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));

				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile();
				}
				if (r == JFileChooser.CANCEL_OPTION) {
					System.out.println("Cancelando ... ");
				}
			}
		});

		return selectFileButton;
	}

	private JButton convertFile() {
		JButton selectFileButton = new JButton("Converter o arquivo");
		selectFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().convertFile(
						resultFileName.getText(), file, read, gen);
			}
		});

		return selectFileButton;
	}

	@Override
	public void updateResultType(Class obj) {
		try {
			this.gen = (FileGenerator) obj.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateSourceType(Class obj) {
		try {
			this.read = (FileReader) obj.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}