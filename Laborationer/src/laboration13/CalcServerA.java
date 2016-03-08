package laboration13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerA implements Runnable {
	private Calculator calculator;
	private ServerSocket serverSocket;
	private Thread serverThread;

	public CalcServerA(Calculator calculator, int port) {
		try {
			this.calculator = calculator;
			serverSocket = new ServerSocket(port);
			serverThread = new Thread(this);
			serverThread.start();
		} catch (IOException e) {
		}
	}

	public void run() {
		String[] parts;
		String response = "";
		double nbr1, nbr2, answer;
		char operation;
		System.out.println("Server A started");
		
		while (true) {
			//Tries to create a socket, input/output stream.
			try (Socket socket = serverSocket.accept();
					DataInputStream inputStream = new DataInputStream(socket.getInputStream());
					DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
				
				//Splits the request that came in to an array
				parts = inputStream.readUTF().split(",");
				
				if (parts.length == 3) {
					try {
						nbr1 = Double.parseDouble(parts[0]);
						nbr2 = Double.parseDouble(parts[1]);
						operation = parts[2].charAt(0);
						
						answer = calculator.calculate(nbr1, nbr2, operation);
						response = answer + "\n" + parts[0] + parts[2] + parts[1] + "=" + answer;
					} catch (Exception e) {
						response = e.toString() + "\n" + parts[0] + parts[2] + parts[1];
					}
				} else {
					response = "Wrong number of arguments: " + parts.length;
				}
				outputStream.writeUTF(response);
				outputStream.flush();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String[] args) {
		new CalcServerA(new Calculator(), 3450);
	}
}