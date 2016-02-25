package laboration5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TIPanel extends JPanel {
	private TIController controller;
	private TwoIconLabel lblImages;
	private JButton btnStart = new JButton("Starta");
	private JButton btnStop = new JButton("Stoppa");

	public TIPanel(TIController controller, TwoIconLabel lbl) {
		this.controller = controller;
		this.lblImages = lbl;
		setLayout(new BorderLayout());
		add(lbl,BorderLayout.CENTER);
		add(initButtons(),BorderLayout.SOUTH);
	}
	
	private JPanel initButtons() {
		AL listener = new AL();
		JPanel panel = new JPanel(new GridLayout(2,1));
		btnStart.addActionListener(listener);
		btnStop.addActionListener(listener);
		panel.add(btnStart);
		panel.add(btnStop);
		return panel;
	}
	
	public void enableStartStop(boolean enable) {
		btnStart.setEnabled(enable);
		btnStop.setEnabled(enable);
	}
	
	public void changeIcon() {
		lblImages.changeIcon();
	}

	private class AL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnStart) {
				controller.start();
			} else if(e.getSource()==btnStop) {
				controller.stop();
			}
		}
	}
}
