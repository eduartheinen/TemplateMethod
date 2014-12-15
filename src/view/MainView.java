package view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6313156717813295316L;

	public MainView() {
		super(new BorderLayout());
		JPanel menuView = new MenuView();

		add(menuView, BorderLayout.CENTER);
	}

	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Conversor de Arquivos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new MainView();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
