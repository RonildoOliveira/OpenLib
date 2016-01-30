package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class CadLivro extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadLivro frame = new CadLivro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadLivro() {
		setBounds(100, 100, 450, 300);

	}

}
