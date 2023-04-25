package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Game15;

public class CounterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int clickCounter = 0;
	private JLabel number;
	private Game15 game;

	public CounterPanel() {
		setLayout(new BorderLayout());
//		setBorder(BorderFactory.createLineBorder(Color.black));
		number = new JLabel(String.valueOf(clickCounter));
		number.setFont(new Font("Serif", Font.BOLD, 50));
		number.setBackground(G15Colour.bgColour);
		number.setForeground(G15Colour.timerColour);
		number.setOpaque(true);

		add(number, BorderLayout.CENTER);

		setBackground(G15Colour.bgColour);
		setOpaque(true);
	}

	private void setLabel() {
		number.setText(String.valueOf(clickCounter));
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//		Graphics2D g2d = (Graphics2D) g;
//
//		g2d.setFont(new Font("Serif", Font.BOLD, this.getWidth() / 4));
//		g2d.setColor(G15Colour.timerColour);
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//
//		FontMetrics fm = g2d.getFontMetrics();
//		Rectangle2D r = fm.getStringBounds(String.valueOf(clickCounter), g2d);
//
//		int x = (int) (this.getWidth() - r.getWidth()) / 2;
//		int y = (int) (this.getHeight() - r.getHeight()) / 2 + fm.getMaxAscent();
//		
//		g2d.drawString(String.valueOf(clickCounter), x, y);
	}

	public void addClick() {
		clickCounter++;
		setLabel();
		repaint();
	}

	public int getClickCounter() {
		return clickCounter;
	}

	public void resetClickCounter() {
		clickCounter = 0;
		number.setText(String.valueOf(clickCounter));
		number.setForeground(G15Colour.timerColour);
		repaint();
	}

	public void setGame(Game15 game) {
		this.game = game;
	}

	public void setFinishedColour() {
		number.setForeground(G15Colour.tileColourFinished);
	}
}
