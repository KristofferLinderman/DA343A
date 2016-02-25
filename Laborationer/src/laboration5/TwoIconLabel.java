package laboration5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TwoIconLabel extends JLabel {
    private Icon icon1;
    private Icon icon2;
    private boolean state = false;

    public TwoIconLabel(Icon icon1, Icon icon2) {
    	this(icon1,icon2,200,200);
    }

    public TwoIconLabel(Icon icon1, Icon icon2, int width, int height) {
    	setPreferredSize(new Dimension(width,height));
    	setOpaque(true);
        setIcons(icon1,icon2);
    }

    public void setIcons(Icon icon1, Icon icon2) {
        this.icon1 = icon1;
        this.icon2 = icon2;
        setIcon(icon1);
        state = false;
    }
    
    public void changeIcon() {
        if (state) {
            setIcon(icon1);
        } else {
            setIcon(icon2);
        }
        state = !state;
    }
    
    public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new TCBPanel());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}

class TCBPanel extends JPanel {
	private TwoIconLabel lblTCL = new TwoIconLabel(new ImageIcon("images/orkanen.jpg"), new ImageIcon("images/stationen.jpg"),300,200);
	private JButton btnChange = new JButton("Byt bild");
	
	public TCBPanel() {
		setLayout(new BorderLayout());
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTCL.changeIcon();
			}
		});
		add(lblTCL,BorderLayout.CENTER);
		add(btnChange,BorderLayout.SOUTH);
	}
}