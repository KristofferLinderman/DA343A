package laboration11;

import javax.swing.SwingUtilities;

public class StartSuperUDP {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new SuperUDPServer(3500);
					new SuperUDPClient(3500, "localhost");
				} catch (Exception e) {
					System.out.println("Program: " + e);
				}
			}
		});
	}
}
