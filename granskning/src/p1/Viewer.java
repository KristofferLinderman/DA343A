package p1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Viewer extends JPanel implements Observer  {
	private JLabel lblIcon = new JLabel();
	
	public Viewer(int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
	}
	
	public Viewer(IconManager iconManager, int width, int height) {
		this(width,height);
		iconManager.addObserver(this);
	}

	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}

	public void update(Observable o, Object arg) {
	setIcon((Icon)arg);
	}
}
