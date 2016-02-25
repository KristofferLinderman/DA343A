package p1;

import javax.swing.Icon;

/**
 * Denna klassen tar emot en textfil som input och 
 * lagrar sedan innehållet i en ArrayList.
 * @author unknown
 *
 */
public class ArrayProducer implements IconProducer {
	private Icon[] icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
	public ArrayProducer(Icon[] icons, int delay, int times) {
		this.delay = delay;
		this.times = times;
		this.icons = icons;
	}
	/**
	 * Denna metod returnerar hur ofta bilderna 
	 * ska levereas vidare
	 * @return delay
	 */
	
	public int delay() {
		return delay;
	}
	/**
	 * Denna metod returnerar hur månag gånger som 
	 * bilden ska visas
	 * @return times 
	 */

	public int times() {
		return times;
	}

	/**
	 * Denna metod returnerar storleken på icon
	 * @return size
	 */
	public int size() {
		return (icons==null) ? 0 : icons.length;
	}
	
	/**
	 * Returnerar det som finns i currentIndex position
	 * @return currentIndex
	 */
	public Icon nextIcon() {
		if(icons==null || icons.length==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];
	}
	
	
}
