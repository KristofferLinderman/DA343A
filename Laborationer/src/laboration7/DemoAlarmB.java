package laboration7;

import java.util.Observable;
import java.util.Observer;

public class DemoAlarmB {
	public DemoAlarmB(int ms) {
		AlarmThreadB bt = new AlarmThreadB(ms);
		bt.startAlarm();
		bt.addAlarmListener(new AlarmPrinter());
		bt.addAlarmListener(new WakeUpPrinter());
		bt.addAlarmListener(new ConsolePrinter("Jah Bles, YOLO etc etc "));
	}
	
	public static void main(String[] args) {
		DemoAlarmB da = new DemoAlarmB(1000);
	}
	
	public class AlarmPrinter implements AlarmListener{
		public void alarm() {
			System.out.println("ALARM!");
		}
	}
	
	public class WakeUpPrinter implements AlarmListener{
		public void alarm() {
			System.out.println("WAKE UP!");
		}
	}
	public class ConsolePrinter implements AlarmListener {
		private String msg;

		public ConsolePrinter(String str) {
			msg = str;
		}
		@Override
		public void alarm() {
			System.out.println(msg);
		}
	}
	
}
