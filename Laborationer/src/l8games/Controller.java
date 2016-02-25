package l8games;

import java.io.IOException;

public class Controller {
	private Buffer<Game> buffer;
	private Fetcher fetcher;

	public Controller(Buffer<Game> buffer) {
		this.buffer = buffer;
		fetcher = new Fetcher();
		fetcher.start();
		
		// Skapa instans av tråd och starta tråden
	}

	// Inre klass vilken vilken hämtar Game-objekt ur buffer och visar objekten
	// i TextWindow. Använd println-metoden i TextWindow.

	private class Fetcher extends Thread {
		public void run() {
			Game game;
			while (!Thread.interrupted()) {
				try {
					game = buffer.get();
					TextWindow.println(game);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Buffer<Game> buffer = new Buffer<Game>();
		Controller controller = new Controller(buffer);
		GameResults gr = new GameResults("files/games.txt", buffer);
		gr.startSimulation();
		try {
			Thread.sleep(20500);
		} catch (InterruptedException e) {
		}
		gr.stopSimulation();
	}
}
