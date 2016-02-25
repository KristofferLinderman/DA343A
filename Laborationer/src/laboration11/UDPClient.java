package laboration11;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// parametrar: klientens lyssnarport och s�ndarport + serverns ip-nummer
public class UDPClient {
	private int serverPort;
	private Thread client = new Thread(new Listener());
	private DatagramSocket socket;
	private InetAddress ip;
	private ClientPanel panel = new ClientPanel();

	public UDPClient(int port, String ipAddress) {
		this.serverPort = port;
		try {
			ip = InetAddress.getByName(ipAddress);
			socket = new DatagramSocket();  // ledig port
			client.start();
			showClient();
		} catch(Exception e) { 
			System.err.println(e);
		}
	}

	private void showClient() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void sendMessage(String message) {
		byte[] outData;
		DatagramPacket packet;
		outData = message.getBytes();
		packet = new DatagramPacket(outData,outData.length,ip,serverPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	private void exit() {
		client.interrupt();
		socket.close();
		System.exit(0);
	}
	
	
	private class Listener extends Thread {
		public void run() {
			DatagramPacket packet;
			byte[] readBuffer = new byte[1024];
			while(!Thread.interrupted()) {
				try {
					packet = new DatagramPacket(readBuffer,readBuffer.length);
					socket.receive(packet);		
					panel.addResponse(new String(packet.getData(),0,packet.getLength()));
				} catch(Exception e) { 
					break; // socket.close();
				}
			}
		}
	}
	
	private class ClientPanel extends JPanel implements ActionListener {
		private JTextArea taResponse = new JTextArea();
		private JButton btnPut = new JButton("PUT");
		private JButton btnGet = new JButton("GET");
		private JButton btnClr = new JButton("CLR");
		private JButton btnQuit = new JButton("Avsluta");
		
		public ClientPanel() {
			setLayout(new BorderLayout());
			taResponse.setPreferredSize(new Dimension(200,300));
			add(menu(),BorderLayout.NORTH);
			add(new JScrollPane(taResponse),BorderLayout.CENTER);
		}
		
		private JPanel menu() {
			JPanel panel = new JPanel(new GridLayout(4,1));
			panel.add(btnPut);
			panel.add(btnGet);
			panel.add(btnClr);
			panel.add(btnQuit);
			btnPut.addActionListener(this);
			btnGet.addActionListener(this);
			btnClr.addActionListener(this);
			btnQuit.addActionListener(this);
			return panel;
		}
		
		public void addResponse(final String response) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
			        taResponse.append(response+"\n");
				}
			});
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnPut) {
				sendMessage( "PUT " + JOptionPane.showInputDialog( "Skriv text att spara" ) );
			} else if(e.getSource()==btnGet) {
				taResponse.setText("");
				sendMessage( "GET" );
			} else if(e.getSource()==btnClr) {
				sendMessage( "CLR" );
			} else if(e.getSource()==btnQuit) {
				exit();
			}
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new UDPClient(3500,"127.0.0.1");
			}
		});
	}
}
