package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import logic.Game15;

public class Game15MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int width = 500;
	private int heigth = 700;

	private Board board;
	private ButtonPanel buttonPanel;

	private InfoPanel infoPanel;
	
	private Game15 game;

	public Game15MainFrame() {
		super("Game of 15");
		game = new Game15();
		board = new Board();
		buttonPanel = new ButtonPanel();
		infoPanel = new InfoPanel();
		this.setLayout(new BorderLayout());

		add(board, BorderLayout.PAGE_START);
		add(infoPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);

		setChrono();
		setReshuffle();
		setCounterPanelInBoard();
		setGame();

		this.setPreferredSize(new Dimension(width, heigth));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setVisible(true);

	}

	private void setGame() {
		board.setGame(game);
		infoPanel.setGame(game);
		
	}

	private void setCounterPanelInBoard() {
		board.setCounter(infoPanel.getCounterPanel());
	}

	private void setChrono() {
		infoPanel.setChrono();
	}

	private void setReshuffle() {

		buttonPanel.getReshuffleButton().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				board.reshulffle();
				infoPanel.resetCounter();
				infoPanel.restartChrono();

			}

		});
	}

}
