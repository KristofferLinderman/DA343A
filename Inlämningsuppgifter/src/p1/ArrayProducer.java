package p1;

import javax.swing.Icon;

/**
 * ArrayProducer takes an array of icons accompanied with a specified delay and desired times to repeat.
 * @author Kristoffer
 */
public class ArrayProducer implements IconProducer {
	private Icon[] icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
	/**
	 * Creates an ArrayProducer conatining array of icons, a delay and information of how many times to repeat the array
	 * @param icons array of icons.
	 * @param delay in (ms) for the delay between the icons.
	 * @param times the number of times to go through the array
	 */
	public ArrayProducer(Icon[] icons, int delay, int times) {
		this.delay = delay;
		this.times = times;
		this.icons = icons;
	}
	
	/**
	 * Returns the delay
	 */
	public int delay() {
		return delay;
	}

	/**
	 * Returns the times to go through the array
	 */
	public int times() {
		return times;
	}

	/**
	 * Returns the size
	 */
	public int size() {
		return (icons==null) ? 0 : icons.length;
	}

	/**
	 * Returns the next icon in the array. Returns null if the array is empty
	 */
	public Icon nextIcon() {
		if(icons==null || icons.length==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];
	}
	
	
}
