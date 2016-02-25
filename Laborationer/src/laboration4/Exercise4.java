package laboration4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Exercise4 {

	public void averageAge(String filename) throws IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			int nbrOfPersons, women = 0, avgAge = 0, avgeAgeWomen = 0;
			nbrOfPersons = ois.readInt();
			Person[] p = new Person[nbrOfPersons];
			
			for (int i = 0; i < nbrOfPersons; i++) {
				try {
					p[i] = (Person) ois.readObject();
					avgAge += p[i].getAge();
					
					if (p[i].getSex() == 'K') {
						avgeAgeWomen += p[i].getAge();
						women++;
					}
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				}
			}
			System.out.println("Genomsnittlig ålder är " + (double) avgAge / nbrOfPersons);
			System.out.println("Kvinnornas genomsnittlig ålder är " + (double) avgeAgeWomen / women);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Exercise4 ex4 = new Exercise4();
		ex4.averageAge("files/persons.dat");
	}

}
