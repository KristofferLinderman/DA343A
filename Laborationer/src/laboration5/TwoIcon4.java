package laboration5;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class TwoIcon4 extends TwoIconLabel implements Runnable, StartStopListener {
	private long delay;
	private Thread thread = new Thread(this);
	private Runnable changeIcon = new ChangeIcon();

	public TwoIcon4(Icon icon1, Icon icon2) {
		this(icon1, icon2, 500);
	}

	public TwoIcon4(Icon icon1, Icon icon2, int delay) {
		this(icon1, icon2, 200, 200, delay);
	}

	public TwoIcon4(Icon icon1, Icon icon2, int width, int height, int delay) {
		super(icon1, icon2, width, height);
		this.delay = delay;
		thread.start();
	}

	public void run() {
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				break;
			}
			SwingUtilities.invokeLater(changeIcon);
		}
		thread = null;
	}

	private class ChangeIcon implements Runnable {
		public void run() {
			changeIcon();
		}
	}

	@Override
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
	}
}
