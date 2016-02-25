package f8; 
public class Counter1Thread extends Thread {
    private Counter1 counter;
    
    public Counter1Thread(Counter1 counter, String name) {
    	this.counter = counter;
    	this.setName(name);
    }
     
    // Testa med Counter1-incCounter a) utan synchronized b) med synchronized
//    public void run() {
//    	int value;
//        System.out.println(getName() + " startar");
//        while (true) {
//        	value = counter.incCounter();
//            System.out.println(getName()+": "+value);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {}
//        }
//    }
    
      // testa med Counter1-incCounter utan synchronized
//    public void run() {
//    	int value;
//        System.out.println(getName() + " startar");
//        while (true) {
//        	synchronized(counter) {
//        	    value = counter.incCounter();
//        	}
//        	System.out.println(getName()+": "+value);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {}
//        }
//    }
    
    
    // testa med Counter1-incCounter utan synchronized
    public void run() {
    	int value;
        System.out.println(getName() + " startar");
        while (true) {
        	synchronized(counter) {
        	    value = counter.incCounter();
        	    System.out.println(getName()+": "+value);
        	}
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
    
    public static void main(String[] args) {
		Counter1 counter = new Counter1();
		Counter1Thread ct1 = new Counter1Thread(counter,"A");
		Counter1Thread ct2 = new Counter1Thread(counter,"B");
		ct1.start();
		ct2.start();
	}
}
