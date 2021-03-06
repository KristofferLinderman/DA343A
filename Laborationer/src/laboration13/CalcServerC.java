package laboration13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerC implements Runnable {
	private Calculator calculator;
	private ServerSocket serverSocket;

	public CalcServerC(Calculator calculator, int port) {
		try {
			this.calculator = calculator;
			serverSocket = new ServerSocket(port);
			new Thread(this).start();
		} catch (IOException e) {
		}
	}

	public void run() {
		System.out.println("Server C started");

		// creates a new thread (ClientListener) for each accepted connection
		// made
		try {
			Socket socket = serverSocket.accept();
			new ClientListener(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// A tread that listens for requests without closing the connection between
	// requests.
	private class ClientListener extends Thread {
		private Socket socket;

		public ClientListener(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			double nbr1, nbr2, answer;
			char operation;
			String response = "";

			try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
					DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

				while (true) {
					nbr1 = inputStream.readDouble();
					nbr2 = inputStream.readDouble();
					operation = inputStream.readChar();

					answer = calculator.calculate(nbr1, nbr2, operation);
					response = answer + "\n" + nbr1 + " " + operation + " " + nbr2 + " = " + answer;

					outputStream.writeUTF(response);
					outputStream.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new CalcServerC(new Calculator(), 3452);
	}
}