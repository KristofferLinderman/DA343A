package p1;

import javax.swing.*;

public class Producer {
	private Buffer<IconProducer> prBuff;
	private Buffer<Icon> icBuff;

	public Producer(Buffer<IconProducer> producerBuffer, Buffer<Icon> iconBuffer) {
		prBuff = producerBuffer;
		icBuff = iconBuffer;
	}

	public void start() {
		new ProThread().start();
	}

	private class ProThread extends Thread {
		public void run() {
			IconProducer iP;
			while (!Thread.interrupted()) {
				try {
					iP = prBuff.get();
					while (iP != null) {
						icBuff.put(iP.nextIcon());
						Thread.sleep(iP.delay());
					}
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}
	}

}
