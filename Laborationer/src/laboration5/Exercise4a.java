package laboration5;

public class Exercise4a implements Runnable {
	private String[] strings;
	private int pause;
	private InnerThread innerThread;
	private int index = 0;

	public Exercise4a(String[] strings) {
		this(strings, 500);
	}

	public Exercise4a(String[] strings, int paus) {
		this.strings = strings;
		this.pause = paus;
	}

	public void start() {
		run();
	}

	public void stop() {
		innerThread.interrupt();
	}

	@Override
	public void run() {
		if (innerThread == null) {
			innerThread = new InnerThread();
			innerThread.start();
		}
	}

	private class InnerThread extends Thread {

		public void run() {

			while (index < strings.length) {
				System.out.println(strings[index++]);

				try {
					Thread.sleep(pause);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		String[] strings1 = { "Jag heter Rut", "Du heter Sven", "Han heter Ola" };
		String[] strings2 = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		Exercise4a ex4a = new Exercise4a(strings1);
		Exercise4a ex4b = new Exercise4a(strings2);
		ex4a.start();
		ex4b.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		ex4a.stop();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		ex4a.start();
		ex4b.stop();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		ex4b.start();
	}

}
