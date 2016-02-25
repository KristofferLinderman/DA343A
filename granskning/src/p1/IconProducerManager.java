package p1;

import javax.swing.Icon;

public class IconProducerManager {
	private Buffer<IconProducer> buffer;

	public IconProducerManager(Buffer<IconProducer> buffer) {
		this.buffer = buffer;
	}

	public void addIconProducer(IconProducer ip) {
		buffer.put(ip);
	}

	private class Inner implements IconProducer {
		private Icon[] icons;
		private int delay = 0;
		private int times = 0;
		private int currentIndex = -1;

		public int delay() {
			return delay;
		}

		public int times() {
			return times;
		}

		public int size() {
			return (icons == null) ? 0 : icons.length;
		}

		public Icon nextIcon() {
			if (icons == null || icons.length == 0)
				return null;
			currentIndex = (currentIndex + 1) % icons.length;
			return icons[currentIndex];

		}
	}

}
