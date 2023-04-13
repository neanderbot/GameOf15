package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton reshuffle;

	public ButtonPanel() {
		reshuffle = new JButton();
		formatButton();
		setLayout(new BorderLayout());
		add(reshuffle, BorderLayout.CENTER);
	}

	public JButton getReshuffleButton() {
		return reshuffle;
	}

	private void formatButton() {
		JLabel label = new JLabel("Reshuffle");
		label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		label.setForeground(G15Colour.bgColour);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		reshuffle.add(label);
		reshuffle.setBackground(G15Colour.buttonColour);
		setPreferredSize(new Dimension(200, 50));

		reshuffle.setBorderPainted(false);
	}

}
