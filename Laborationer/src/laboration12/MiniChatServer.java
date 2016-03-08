package laboration12;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MiniChatServer implements Runnable{
	
	private Thread server;
	private DatagramPacket packet;
	private DatagramSocket socket;
	
	public MiniChatServer(int requestedPort){
		System.out.println("Requested port: " + requestedPort);
		
		try {
			socket = new DatagramSocket(requestedPort);
			server = new Thread(this);
			server.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("Server started");
		
		String request, message;
		
		try{
			while(true){
//				packet = new DatagramPacket(buf, length);
				socket.send(packet);
			}
		}catch(Exception e ){
			
		}
	}

}
