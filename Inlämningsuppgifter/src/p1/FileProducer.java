package p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * FilesProducer takes an ArrayList of icons accompanied with a specified delay
 * and desired times to repeat by reading the data from a file
 * 
 * @author Kristoffer
 *
 */
public class FileProducer implements IconProducer {
	private ArrayList<Icon> list = new ArrayList<Icon>();
	private int delay = 0;
	private int times = 0;
	private int currentIcon = -1;

	/**
	 * Reads the file for the delay, times and for the actual ArrayList of icons
	 * 
	 * @param filename
	 *            The file with the data
	 */
	public FileProducer(String filename) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			times = Integer.parseInt(br.readLine());
			delay = Integer.parseInt(br.readLine());
			String str = br.readLine();
			while (str != null) {
				addIcon(str);
				str = br.readLine();
			}
		} catch (IOException e) {
		}
	}

	/**
	 * Adds an icon to the ArrayList based on a filename
	 * 
	 * @param filename
	 *            The icon to add
	 */
	private void addIcon(String filename) {
		Icon icon = new ImageIcon(filename);
		if (icon != null) {
			list.add(icon);
		}
	}

	/**
	 * Reutrns the delay
	 */
	public int delay() {
		return delay;
	}

	/**
	 * Reutrns the delay
	 */
	public int times() {
		return times;
	}

	/**
	 * Returns the size
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Returns the next icon in the array. Returns null if the array is empty
	 */
	public Icon nextIcon() {
		if (list.size() == 0)
			return null;
		currentIcon = (currentIcon + 1) % list.size();
		return list.get(currentIcon);
	}

}