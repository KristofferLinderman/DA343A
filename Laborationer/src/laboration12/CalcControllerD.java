package laboration12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalcControllerD implements CalcController {

	private Socket socket;
	private String ipAddress;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private int port;
	private CalcUI gui;

	public CalcControllerD() {
		ipAddress = "195.178.232.7";
		port = 3453;
		System.out.println("started");

		//QUESTION Output has to be before input to work??
		try {
			socket = new Socket(ipAddress, port);
			System.out.println("1,1");
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("1,2");
			inputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("1,3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Listener().start();
	}

	public void setGUI(CalcUI gui) {
		this.gui = gui;
	}

	public void newCalculation(String nbr1, String nbr2, String operator) {
		double nbr1D = Double.parseDouble(nbr1);
		double nbr2D = Double.parseDouble(nbr2);
		char oper = operator.charAt(0);

		try {
			outputStream.writeObject(new Expression(nbr1D, nbr2D, oper));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Listener extends Thread {
		public void run() {
			Calculation response;
			try {
				while (true) {
					response = (Calculation) inputStream.readObject();
					gui.setResult("" + response.getResult());
				}
			} catch (IOException|ClassNotFoundException e) {
				System.out.println(e);
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
//			gui.setResult("Disconneting from server");
		}
	}
}
