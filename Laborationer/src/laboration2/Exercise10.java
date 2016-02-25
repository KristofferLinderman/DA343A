package laboration2;

import java.util.*;

public class Exercise10 {
	private LinkedList<String> list = new LinkedList<String>();

	public void add(String str) {
		list.add(str);
	}

	public void remove(String str) {
		list.remove(str);
	}

	public void print() {
		for (String str : list) {
			System.out.print(str + " ");
		}
	}

	public void printReverse() {
		for (int i = list.size() -1; i >= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
	}

	public int size() {
		return list.size();
	}

	public void print6() {
		for (String str : list) {
			if (str.length() > 5) {
				System.out.print(str + " ");
			}
		}
	}

	public int count(char chr) {
		int count = 0;
		for (String str : list) {
			if (str.charAt(0) == chr) {
				count++;
			}
		}
		return 0;
	}
}