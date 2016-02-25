package p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Denna klassen tar emot en textfil som input och lagrar sedan innehållet i en
 * ArrayList.
 * 
 * @author Frida
 */
public class FileProducer implements IconProducer {
	private ArrayList<Icon> icons = new ArrayList<Icon>();
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	private String test;

	/**
	 * Konstruktor som emot en fil som argument
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public FileProducer(String filename) throws FileNotFoundException {
		String str;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			times = Integer.parseInt(br.readLine());
			delay = Integer.parseInt(br.readLine());
			while ((str = br.readLine()) != null) {
				addIcon(str);
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Denna metod adderar icons
	 * 
	 * @param str
	 */

	private void addIcon(String str) {
		Icon i = new ImageIcon(str);
		if (i != null) {
			icons.add(i);
		}
	}

	/**
	 * Denna metod returnerar hur ofta bilderna ska levereas vidare
	 * 
	 * @return delay
	 */
	public int delay() {
		return delay;
	}

	/**
	 * Denna metod returnerar hur månag gånger som bilden ska visas
	 * 
	 * @return times
	 */
	public int times() {
		return times;
	}

	/**
	 * Denna metod returnerar storleken på icon
	 * 
	 * @return size
	 */
	public int size() {
		return icons.size();
	}

	/**
	 * Returnerar det som finns i currentIndex positionen
	 * 
	 * @return currentIndex
	 */
	public Icon nextIcon() {
		if (icons == null || icons.size() == 0)
			return null;
		currentIndex = (currentIndex + 1) % icons.size();
		return icons.get(currentIndex);

	}

}
