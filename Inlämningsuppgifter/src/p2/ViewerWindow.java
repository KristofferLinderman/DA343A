package p2;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ViewerWindow {
	public ViewerWindow(Viewer viewer) {
		this(viewer,0,0);
	}

	public ViewerWindow(Viewer viewer, int i, int j) {
		JFrame frame = new JFrame("Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(viewer);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(i, j);
		}
}
