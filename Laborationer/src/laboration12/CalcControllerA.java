package laboration12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalcControllerA implements CalcController{

	private Socket socket;
	private String ipAddress;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private int port;
	private CalcUI gui;

	public CalcControllerA() {
		ipAddress = "195.178.232.7";
		port = 3450;
	}

	public void setGUI(CalcUI gui) {
		this.gui = gui;
	}

	public void newCalculation(String nbr1, String nbr2, String operator) {

		try {

			socket = new Socket(ipAddress, port);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());

			outputStream.writeUTF(nbr1 + "," + nbr2 + "," + operator);
			gui.setResult(inputStream.readUTF());

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
