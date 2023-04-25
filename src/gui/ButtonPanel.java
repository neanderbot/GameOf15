package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton reshuffleButton;
	private JButton recordsButton;

	public ButtonPanel() {
		reshuffleButton = new JButton();
		recordsButton = new JButton();
		formatReshuffleButton();
		formatRecordsButton();
		setBackground(G15Colour.buttonColour);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(reshuffleButton);
//		add(recordsButton);
		setPreferredSize(new Dimension(100, 50));
	}

	private void formatRecordsButton() {
		JLabel label = new JLabel("Statistics");
		label.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		label.setForeground(G15Colour.bgColour);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		recordsButton.add(label);
		recordsButton.setBackground(G15Colour.buttonColour);
		setPreferredSize(new Dimension(200, 100));

		recordsButton.setBorderPainted(false);
		
	}

	public JButton getReshuffleButton() {
		return reshuffleButton;
	}

	private void formatReshuffleButton() {
		JLabel label = new JLabel("Reshuffle");
		label.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		label.setForeground(G15Colour.bgColour);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		reshuffleButton.add(label);
		reshuffleButton.setBackground(G15Colour.buttonColour);
		int reshuffleButtonwidth = this.getWidth() / 2;
		System.out.println(this.getWidth());
		setPreferredSize(new Dimension(reshuffleButtonwidth, 100));

		reshuffleButton.setBorderPainted(false);
	}

	public JButton getRecordsButton() {
		return recordsButton;
	}

}
