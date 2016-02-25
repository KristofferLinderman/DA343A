package l8games;

import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();
	
	public  void put(T obj) {
		buffer.addLast(obj);
		notifyAll();
	}
	
	public  T get() throws InterruptedException {
		while(buffer.isEmpty()) {
			wait();
		}
		return buffer.removeFirst();
	}
	
	public int size() {
		return buffer.size();
	}
}
