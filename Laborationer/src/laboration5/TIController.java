package laboration5;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TIController {
	private StartStopListener startStopListener = null;
	
	private void showFrame(TIPanel panel) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(450, 500));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public TIController(TwoIconLabel til) {
		TIPanel panel = new TIPanel(this,til);
		if(til instanceof StartStopListener) {
		    this.startStopListener = (StartStopListener)til;
		}
		panel.enableStartStop(til instanceof StartStopListener);
		showFrame(panel);
	}

	public void start() {
		startStopListener.start();
	}

	public void stop() {
		startStopListener.stop();
	}

	public static void main(String[] args) {
    	SwingUtilities.invokeLater( new Runnable() {
    		public void run() {
    			TwoIcon2 ti2 = new TwoIcon2(new ImageIcon("images/duckone.jpg"), new ImageIcon("images/ducktwo.jpg"),300,200,1000);
    			TwoIcon4 ti4 = new TwoIcon4(new ImageIcon("images/duckone.jpg"), new ImageIcon("images/ducktwo.jpg"),300,200,1000);
    			new TIController(ti4);
    		}
    	});
    }
}
