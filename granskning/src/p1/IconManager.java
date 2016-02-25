package p1;

import java.util.Observable;

import javax.swing.Icon;

public class IconManager extends Observable {

	private Buffer<Icon> iBuffer;

	public IconManager(Buffer<Icon> iconBuffer) {
		iBuffer = iconBuffer;
	}

	public void start() {
		new IMThread().start();
	}

	private class IMThread extends Thread {
		Icon i;

		public void run() {
			while (!Thread.interrupted()) {
				try {
					i = iBuffer.get();
					setChanged();
					notifyObservers(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
