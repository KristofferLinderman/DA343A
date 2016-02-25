package laboration11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadTextFromServer {

	public static void main(String[] args){
		try {
			URL u = new URL("http://ddwap.mah.se/af5204/online.rtf");

			InputStreamReader isr = new InputStreamReader(u.openStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			System.out.println("Text från " + u.toString());
			int input;
			while ((input = br.read()) != -1) {
				System.out.print((char) input);
			}

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
