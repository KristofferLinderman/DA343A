package laboration11;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class tester {
	public static void main(String[] args) {
		try {
			System.out.println("Denna dator:");
			InetAddress myAddress = InetAddress.getLocalHost();
			System.out.println(myAddress.getHostAddress());
			System.out.println(myAddress.getHostName());
			InetAddress address = InetAddress.getByName("www.mah.se");
			System.out.println();
			System.out.println(address);
		} catch (UnknownHostException ex) {
			System.out.println("Kan ej finna adressen till datorn");
		}
	}
	
}
