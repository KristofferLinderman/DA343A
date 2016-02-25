package p1;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MainP1 {
	public static void main(String[] args) {
		Buffer<Icon> iconBuffer = new Buffer<Icon>();
		Buffer<IconProducer> producerBuffer	= new Buffer<IconProducer>();
		
		IconManager iconManager = new IconManager(iconBuffer);
		new ViewerWindow( new Viewer(iconManager,640,480));
		new ViewerWindow( new Viewer(iconManager,320,320));
		iconManager.start();
		
		Producer producer = new Producer(producerBuffer,iconBuffer);
		producer.start();
		
		IconProducerManager ipManager = new IconProducerManager(producerBuffer);		
		ipManager.addIconProducer(new ArrayProducer(getIconArray(),50,10));
	}
	
	private static Icon[] getIconArray() {
		Icon[] res = {new ImageIcon("files/new1.jpg"),
				new ImageIcon("files/new2.jpg"),
				new ImageIcon("files/new3.jpg"),
				new ImageIcon("files/new4.jpg"),
				new ImageIcon("files/new5.jpg"),
				new ImageIcon("files/new6.jpg"),
				new ImageIcon("files/new7.jpg"),
				new ImageIcon("files/new8.jpg"),
				new ImageIcon("files/new9.jpg"),
				new ImageIcon("files/new10.jpg")};
		return res;
	}
}


