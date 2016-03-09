package p2;

import javax.swing.Icon;

public class Producer extends Thread {

	private Buffer<IconProducer> prodBuffer;
	private Buffer<Icon> iconBuffer;
	private IconProducer tempIProd;

	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer) {
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;
	}

	public void run() {
		try {
			tempIProd = prodBuffer.get();

			for (int i = 0; i < tempIProd.times(); i++) {
				for (int j = 0; j < tempIProd.size(); j++) {
					iconBuffer.put(tempIProd.nextIcon());
					Thread.sleep(tempIProd.delay());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
