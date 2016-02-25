package f8;

import java.util.LinkedList;

public class RunOnThread {
	private Buffer<Runnable> buffer = new Buffer<Runnable>();
	private Worker worker;
	
	public synchronized void start() {
		if(worker==null) {
		    worker = new Worker();
		    worker.start();
		}
	}
	
	public synchronized void execute(Runnable runnable) {
		buffer.put(runnable);
	}
	
	public synchronized void stop() {
		buffer.clear();
		worker.interrupt();
	}
	
	private class Worker extends Thread {
		public void run() {
			while(!Thread.interrupted()) {
				try {
				    buffer.get().run();
				} catch(InterruptedException e) {
					System.out.println(e);
					break;
				}
			}
			worker = null;
			System.out.println(Thread.currentThread().getName() + " avslutas");
		}
	}
	
	private class Buffer<T> {
		private LinkedList<T> buffer = new LinkedList<T>();
		
		public synchronized void put(T obj) {
			buffer.addLast(obj);
			notifyAll();
		}
		
		public synchronized T get() throws InterruptedException {
			while(buffer.isEmpty()) {
				System.out.println(Thread.currentThread() + " is waiting");
				wait();
			}
			return buffer.removeFirst();
		}
			
		public synchronized void clear() {
			buffer.clear();
		}
		
		public synchronized int size() {
			return buffer.size();
		}
	}
}
