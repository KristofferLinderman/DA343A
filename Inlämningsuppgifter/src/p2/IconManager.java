package p2;

import java.util.Observable;
import javax.swing.Icon;

public class IconManager extends Observable {
	private IconThread iThread;
	private Buffer<Icon> iconBuffer;

	public IconManager(Buffer<Icon> iconBuffer) {
		iThread = new IconThread();
		this.iconBuffer = iconBuffer;
	}

	public void start() {
		iThread.start();
	}

	public Buffer<Icon> getIcons() {
		return iconBuffer;
	}

	private class IconThread extends Thread {
		private Icon icon;

		public void run() {

			try {
				icon = iconBuffer.get();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			while (icon != null) {
				setChanged();
				notifyObservers(icon);
				try {
					icon = iconBuffer.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
