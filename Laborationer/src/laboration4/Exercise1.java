package laboration4;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Exercise1 {

	public void sum(String filename) throws IOException {

		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
			int sum = 0;
			int nbrOfElements;

			nbrOfElements = dis.readInt();
			System.out.println(nbrOfElements + " numbers in file\n");

			for (int i = 0; i < nbrOfElements; i++) {
				int temp = dis.readInt();
				sum += temp;
				System.out.println("Number " + (i + 1) + ": " + temp);
			}
			System.out.println("\nSum of numbers: " + sum);
		}
	}

	public static void main(String[] args) throws IOException {
		Exercise1 ex1 = new Exercise1();
		ex1.sum("files/primtal.dat");
	}
}