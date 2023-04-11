package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Pageable;

import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Game15MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int width = 500;
	private int heigth = 700;

	private Board board;
	private ButtonPanel buttonPanel;

	public Game15MainFrame() {
		super("Game of 15");
		board = new Board();
		buttonPanel = new ButtonPanel();
		this.setLayout(new BorderLayout());

		add(board, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);

		setReshuffle();

		this.setPreferredSize(new Dimension(width, heigth));
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setVisible(true);
	}

	private void setReshuffle() {

		buttonPanel.getReshuffleButton().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				board.reshulffle();
			}

		});
	}

}
