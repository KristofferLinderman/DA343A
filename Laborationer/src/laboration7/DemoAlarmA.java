package laboration7;

import java.util.Observable;
import java.util.Observer;

public class DemoAlarmA {
	public DemoAlarmA(int ms) {
		AlarmThreadA at = new AlarmThreadA(ms);
		at.startAlarm();
		at.addObserver(new AlarmPrinter());
		at.addObserver(new WakeUpPrinter());
		at.addObserver(new ConsolePrinter("Jah Bless"));
	}

	public class AlarmPrinter implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("ALARM");
		}

	}

	public class WakeUpPrinter implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("WAKE UP!");
		}

	}

	public class ConsolePrinter implements Observer {
		private String msg;

		public ConsolePrinter(String str) {
			msg = str;
		}

		@Override
		public void update(Observable o, Object arg) {

			System.out.println(msg);
		}

	}

	public static void main(String[] args) {
		DemoAlarmA da = new DemoAlarmA(200);
	}
}
