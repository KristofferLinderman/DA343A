package laboration5;

public class Exercise2a extends Thread {
	private String[] strings;
	private int pause;

	public Exercise2a(String[] strings) {
		this(strings, 500);
	}

	public Exercise2a(String[] strings, int paus) {
		this.strings = strings;
		this.pause = paus;
	}

	public void run() {
		int index = 0;

		while (index < strings.length) {
			System.out.println(strings[index++]);

			try {
				sleep(pause);
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {
		String[] strings1 = { "Jag heter Rut", "Du heter Sven", "Han heter Ola" };
		String[] strings2 = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		Exercise2a ex2a = new Exercise2a(strings1);
		Exercise2a ex2b = new Exercise2a(strings2);
		ex2a.start();
		ex2b.start();

	}
}
