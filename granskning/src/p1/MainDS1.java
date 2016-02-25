package p1;

import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainDS1 {
    private static Icon[] getIconArray() {
		Icon[] res = {new ImageIcon("files/new10.jpg"),
				new ImageIcon("files/new1.jpg"),
				new ImageIcon("files/new2.jpg"),
				new ImageIcon("files/new3.jpg"),
				new ImageIcon("files/new4.jpg"),
				new ImageIcon("files/new5.jpg"),
				new ImageIcon("files/new6.jpg"),
				new ImageIcon("files/new7.jpg"),
				new ImageIcon("files/new8.jpg"),
				new ImageIcon("files/new9.jpg")};
        return res;
    }
    
//    public static void main(String[] args) {
//        Buffer<IconProducer> producerBuffer    = new Buffer<IconProducer>();
//                
//        IconProducerManager ipManager = new IconProducerManager(producerBuffer);  
//      
//        ipManager.addIconProducer(new ArrayProducer(getIconArray(),1000,2));
//        
//        TestDS1 testDs1 = new TestDS1(producerBuffer);
//        testDs1.start();
//    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Buffer<IconProducer> producerBuffer = new Buffer<IconProducer>();
                
        IconProducerManager ipManager = new IconProducerManager(producerBuffer);        
        ipManager.addIconProducer(new FileProducer("files/new.txt"));
        
        TestDS1 testDs1 = new TestDS1(producerBuffer);
        testDs1.start();
    }
    
}
