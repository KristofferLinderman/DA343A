package laboration7;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Controller implements ResultController {
	private GamesUI resultUI;
	private GameResults result;

	public Controller() {
		try {
			result = new GameResults("files/games.txt");
			result.addObserver(new InnerObserver());
			result.addObserver(new InnerConcoleObserver());
			resultUI = new GamesUI(this);
			showFrame(resultUI);
		} catch (IOException e) {
		}
		
	}

	private void showFrame(JPanel panel) {
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void start() {
		if (result != null) {
			result.startSimulation();
		}
	}

	@Override
	public void stop() {
		if (result != null) {
			result.stopSimulation();
		}
	}

	public class InnerObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					resultUI.newResult(arg.toString());
				}
			});
		}
	}
	
	public class InnerConcoleObserver implements Observer {
		
		@Override
		public void update(Observable o, Object arg) {
			System.out.println(arg);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
			}
		});
	}
}
