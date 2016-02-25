package f8;


public class Sender extends Thread {
    private String[] message;
    private Viewer viewer;
    
    public Sender(String[] message, Viewer visare) {
        this.message = message;
        viewer = visare;
    }
    
    public void run() {
//    	synchronized(viewer) { // testa även this
    		viewer.show(message);
//    	}
    }
}
