package p2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class IconServer implements Runnable {
	private IconManager iconManager;
	private ServerSocket serverSocket;

	public IconServer(IconManager iconManager, int port) {
		this.iconManager = iconManager;

		try {
			serverSocket = new ServerSocket(port);
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				new Listener(socket).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class Listener extends Thread implements Observer {
		private ObjectOutputStream outputStream;

		public Listener(Socket socket) {
			iconManager.addObserver(this);
			try {
				outputStream = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void update(Observable o, Object icon) {
			try {
				outputStream.writeObject(icon);
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}