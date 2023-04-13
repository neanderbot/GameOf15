package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private TimerPanel timerPanel;
	private CounterPanel counterPanel;

	public InfoPanel() {
		setBackground(G15Colour.bgColour);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		counterPanel = new CounterPanel();
		timerPanel = new TimerPanel();

		
		add(timerPanel);
		add(counterPanel);
		setPreferredSize(new Dimension(100, 50));
		setOpaque(true);

	}

	public void startChrono() {
		timerPanel.startChrono();
	}
	
	public void stopChrono() {
		timerPanel.stopChrono();
	}
	
	public void restartChrono() {
		timerPanel.restartChrono();
	}

	public void setChrono() {
		timerPanel.setChrono();
		timerPanel.startChrono();

	}
	
	public CounterPanel getCounterPanel() {
		return counterPanel;
	}

	public void resetCounter() {
		counterPanel.resetClickCounter();
//		counterPanel.repaint();
		
	}

}