package laboration5;

public class Exercise2c implements Runnable {
	private String[] strings;
	private int pause;
	private InnerThread innerThread;

	public Exercise2c(String[] strings) {
		this(strings, 500);
	}

	public Exercise2c(String[] strings, int paus) {
		this.strings = strings;
		this.pause = paus;
	}

	public void start() {
		run();
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
			int index = 0;

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

		Exercise2c ex2a = new Exercise2c(strings1);
		Exercise2c ex2b = new Exercise2c(strings2);

		ex2a.start();
		ex2b.start();
	}

}
