package f8;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Viewer {
    private JTextArea textArea;
    
    public Viewer() {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			JFrame frame = new JFrame("Viewer");
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			textArea = new JTextArea();
    			textArea.setLineWrap(true);
    			textArea.setWrapStyleWord(true);
    			textArea.setPreferredSize(new Dimension(300,400));
    			frame.add(textArea);
    			frame.pack();
    			frame.setVisible(true);
    		}
    	});
    }
    
    public void append(String str) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
        		textArea.append(str);
    		}
    	});
    }
    
//    public synchronized void show(String[] message) {
    public void show(String[] message) {
        for(int i=0; i<message.length; i++) {
            append(message[i] + " ");
            try {
            	Thread.sleep(300);
            }catch(InterruptedException e) {}
        }
    }    
}
