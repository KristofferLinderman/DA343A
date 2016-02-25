package laboration4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exercise2 {

	public void readMembers(String filename) throws IOException {

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader((new FileInputStream(filename)), "ISO-8859-1"))) {

			String inputLine = br.readLine();
			int nbrOfWomen = 0, nbrOfMembers = 0;

			while (inputLine != null) {// as long as there are more lines of
										// text

				String[] temp = inputLine.split(" ");

				System.out.println(temp[0]);

				if (temp[1].equals("K"))
					nbrOfWomen++;

				nbrOfMembers++;

				inputLine = br.readLine();
			}
			System.out.println("\nTotal number of members: " + nbrOfMembers);
			System.out.println("\nTotal number of women: " + nbrOfWomen);

		}

	}

	public static void main(String[] args) throws IOException {
		Exercise2 ex2 = new Exercise2();
		ex2.readMembers("files/medlemmar.txt");
	}
}
