package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import logic.Game15;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;

	private Game15 game;
	private int borderMargin = 10;
	private int interTileMargin = 10;
	private int boardLength = 250;
	private int numberOfTiles;
	private int lengthPerTile;

	private CounterPanel counterPanel;

	public Board() {

		game = new Game15();
		numberOfTiles = Game15.getDimension() * Game15.getDimension();

		setLayout(new BorderLayout());

		Border border = BorderFactory.createLineBorder(G15Colour.bgColour, borderMargin, false);

		setBorder(border);
		setPreferredSize(new Dimension(boardLength, 500));

		setBackground(Color.black);
		setOpaque(true);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int index = getIndex(x, y);
				game.move(index);
				G15Colour.tileColour = game.isSolved() ? G15Colour.tileColourFinished : G15Colour.tileColourPlaying;

				counterPanel.addClick();

				repaint();
			}

		});

	}

	public int getIndex(int x, int y) {
		int row = y / lengthPerTile;
		int column = x / lengthPerTile;
		return 4 * row + column;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(G15Colour.bgColour);

		setLengthPerTile();

		G15Colour.tileColour = game.isSolved() ? G15Colour.tileColourFinished : G15Colour.tileColourPlaying;

		g2.setColor(G15Colour.tileColour);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int x = borderMargin;
		int y = borderMargin;

		for (int i = 0; i < numberOfTiles; i++) {

			x = i % Game15.getDimension() == 0 ? borderMargin : x + lengthPerTile;
			y = (i / Game15.getDimension()) * lengthPerTile + borderMargin;
			int tileNumber = game.getTiles()[i];
			String tile = String.valueOf(tileNumber);
			if (i != game.getBlankIndex()) {
				drawTile(g2, x, y, lengthPerTile, tile);
			}
		}
	}

	private void drawTile(Graphics2D g2, int x, int y, int length, String content) {
		int tileLength = length - interTileMargin;
		g2.fillRoundRect(x, y, tileLength, tileLength, 15, 15);
		g2.setFont(new Font("Serif", Font.BOLD, length / 2));
		g2.setColor(G15Colour.bgColour);

		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(content, g2);
		int xContent = x + (tileLength - (int) r.getWidth()) / 2;
		int yContent = y + (tileLength - (int) r.getHeight()) / 2 + fm.getMaxAscent();

		g2.drawString(content, xContent, yContent);
		g2.setColor(G15Colour.tileColour);
	}

	public Game15 getGame() {
		return game;
	}

	public void reshulffle() {
		game.shuffle();
		repaint();
	}

	private void setLengthPerTile() {
		int width = getWidth() - borderMargin;
		int height = getHeight() - borderMargin;

		int minLength = Math.min(width, height);

		lengthPerTile = minLength / Game15.getDimension();
	}

	public void setCounter(CounterPanel counterPanel) {
		this.counterPanel = counterPanel;

	}

}
