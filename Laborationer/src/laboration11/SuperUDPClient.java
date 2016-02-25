package laboration11;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JFrame;

public class SuperUDPClient {
	private InetAddress ip;
	private int port;
	private DatagramSocket socket;
	private Thread client;
	private UDPCalculator panel = new UDPCalculator(this);
	private boolean runThread = true;

	public SuperUDPClient(int port, String ipAddress) {
		this.port = port;
		try {
			ip = InetAddress.getByName(ipAddress);
			socket = new DatagramSocket(0);
			client = new Thread(new Listener());
			client.start();
			createWindow();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}

	private void createWindow() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private void exit() {
		runThread = false;
		socket.close();
		System.exit(0);
	}

	private class Listener extends Thread {
		public void run() {
			DatagramPacket packet;
			byte[] readBuffer = new byte[1024];
			String result;
			while (runThread) {
				try {
					packet = new DatagramPacket(readBuffer, readBuffer.length);
					socket.receive(packet);
					result = new String(packet.getData(), 0, packet.getLength());
					panel.setResult(result);
				} catch (Exception e) {
					break;
				}
			}
			exit();
		}
	}

	public void newCalculation(String nbr1, String nbr2, String operation) {
		byte[] outData;
		DatagramPacket packet;
		outData = (nbr1 + "," + nbr2 + "," + operation).getBytes();
		packet = new DatagramPacket(outData, outData.length, ip, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
