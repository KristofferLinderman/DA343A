package laboration12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalcControllerC implements CalcController {

	private Socket socket;
	private String ipAddress;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private int port;
	private CalcUI gui;

	public CalcControllerC() {
		ipAddress = "localhost";
		port = 3452;

		try {
			socket = new Socket(ipAddress, port);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Listener().start();
	}

	public void setGUI(CalcUI gui) {
		this.gui = gui;
	}

	public void newCalculation(String nbr1, String nbr2, String operator) {

		try {
			outputStream.writeDouble(Double.parseDouble(nbr1));
			outputStream.writeDouble(Double.parseDouble(nbr2));
			outputStream.writeChar(operator.charAt(0));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Listener extends Thread {
		public void run() {
			String response;
			try {
				while (true) {
					response = inputStream.readUTF();
					gui.setResult(response);
				}
			} catch (IOException e) {
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
			 gui.setResult("Disconneting from server");
		}
	}
}
