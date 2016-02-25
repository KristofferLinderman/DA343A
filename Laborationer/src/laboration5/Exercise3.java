package laboration5;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Exercise3 {
	private String[] strings;
	private JLabel lblText;
	private int pause;
	private Timer timer;

	public Exercise3(String[] strings, JLabel lblText, int pause) {
		this.strings = strings;
		this.pause = pause;
		this.lblText = lblText;
	}

	public void start() {
		timer = new Timer();
		timer.schedule(new ToDo(), 0, 500);
	}

	private class ToDo extends TimerTask {
		private int index = 0;

		@Override
		public void run() {
			if (index < strings.length)
				lblText.setText(strings[index++]);
			else
				index = 0;
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				JLabel lblText = new JLabel();
				String[] texter = { "Det är två månader kvar på året", "23 * 6735 = 154905",
						"Den 28 oktober har Simone namnsdag" };
				lblText.setPreferredSize(new Dimension(400, 40));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(lblText);
				frame.pack();
				frame.setVisible(true);
				Exercise3 ex3 = new Exercise3(texter, lblText, 3000);
				ex3.start();
			}
		});
	}

}
