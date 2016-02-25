package laboration5;

import java.util.Timer;
import java.util.TimerTask;

public class Exercise1 {
	private String[] strings;
	private Timer timer;

	public Exercise1(String[] strings) {
		this.strings = strings;

		timer = new Timer();
		timer.schedule(new ToDo(), 0, 500);
	}

	private class ToDo extends TimerTask {
		private int counter = 0;

		@Override
		public void run() {
			if (counter < strings.length)
				System.out.println(strings[counter++]);
			else
				timer.cancel();
		}
	}

	public static void main(String[] args) {
		String[] strings = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		Exercise1 ex1 = new Exercise1(strings);

		String[] strings1 = { "Jag heter Rut", "Du heter Sven", "Han heter Ola" };
		Exercise1 ex1a = new Exercise1(strings1);

	}
}
