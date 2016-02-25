package f8;

public class Counter1 {
	private int counter;
	
	public int incCounter() {
		counter = counter + 1;
		return counter;
	}
	
//	public synchronized int incCounter() {
//		counter = counter + 1;
//		return counter;
//	}
}
