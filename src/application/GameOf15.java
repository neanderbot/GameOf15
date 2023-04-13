package application;

import gui.Game15MainFrame;

public class GameOf15 {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game15MainFrame();
			}
		});
	}

}
