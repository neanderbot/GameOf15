package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	ActionListener chronoHandler;
	Timer timer;
	int min = 0;
	int sec = 0;
	String timeFormat = "%02d:%02d\n";
	String time;
	JLabel chrono;

	public TimerPanel() {

		setLayout(new BorderLayout());
//		setBorder(BorderFactory.createLineBorder(Color.red));
		time = String.format(timeFormat, min, sec);

		chrono = new JLabel(time);
		chrono.setFont(new Font("Serif", Font.BOLD, 50));
		chrono.setBackground(G15Colour.bgColour);
		chrono.setForeground(G15Colour.timerColour);
		chrono.setOpaque(true);
		chrono.setAlignmentX(CENTER_ALIGNMENT);

		add(chrono, BorderLayout.CENTER);

		setBackground(G15Colour.bgColour);
		setOpaque(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		chrono.setText(time);

//		Graphics2D g2d = (Graphics2D) g;
//
//		g2d.setFont(new Font("Serif", Font.BOLD, 50));
//		g2d.setColor(G15Colour.timerColour);
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//		String chrono = String.format("%02d:%02d", min, sec);
//
//		FontMetrics fm = g2d.getFontMetrics();
//		Rectangle2D r = fm.getStringBounds(chrono, g2d);
//		
//		int x = (int) (this.getWidth() - r.getWidth()) / 2;
//		int y = (int) (this.getHeight() - r.getHeight()) / 2 + fm.getMaxAscent();
//
//		g2d.drawString(chrono, x, y);
	}

	public void startChrono() {
		timer.start();
	}

	public void stopChrono() {
		timer.stop();
	}

	public void restartChrono() {
		min = 0;
		sec = 0;
		timer.stop();
		timer.start();
	}

	public void setChrono() {
		chronoHandler = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sec < 59) {
					sec++;
				} else {
					sec = 0;
					min++;
				}
				time = String.format(timeFormat, min, sec);
				chrono.setText(time);
				repaint();

			}
		};

		timer = new Timer(1000, chronoHandler);
		timer.setRepeats(true);
	}

}
