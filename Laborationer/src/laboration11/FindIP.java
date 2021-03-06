package laboration11;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindIP{
	private JPanel mainPanel;

	public FindIP() {
		String myIPAddress = "";

		try {
			InetAddress myAddress = InetAddress.getLocalHost();
			myIPAddress = "Name: " + myAddress.getHostName() + " Address: " + myAddress.getHostAddress();
		} catch (UnknownHostException ex) {
			System.out.println("Kan ej finna adressen till datorn");
		}

		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(420, 100));

		JLabel myIP = new JLabel(myIPAddress);

		JTextField hostName = new JTextField("Enter host name");
		JLabel hostIP = new JLabel();
		JButton findIP = new JButton("Get IP");

		findIP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InetAddress hostAddress = InetAddress.getByName(hostName.getText());
					hostIP.setText(hostAddress.getHostAddress());
				} catch (UnknownHostException ex) {
					System.out.println("Kan ej finna adressen till datorn");
				}
			}
		});

		mainPanel.add(myIP);
		mainPanel.add(hostName);
		mainPanel.add(hostIP);
		mainPanel.add(findIP);

	}

	public JPanel getPanel() {
		return mainPanel;
	}

	public static void main(String[] args) {
		FindIP fip = new FindIP();
		JFrame frame = new JFrame();
		frame.add(fip.getPanel());
		frame.setVisible(true);
		frame.pack();
	}

}
