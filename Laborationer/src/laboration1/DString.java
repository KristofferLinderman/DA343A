package laboration1;

public class DString implements DynamicString { // implementera metoderna i
												// DynamicString - se lab
	private char[] text;
	private int length = 0;

	public DString() {
		this(10);
	}

	public DString(int size) {
		size = (size <= 0) ? 10 : size;
		text = new char[size];
		length = 0;
	}

	public DString(String str) {
		text = str.toCharArray();
		length = text.length;
	}

	public DString(DString str) {
		text = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			text[i] = str.charAt(i);
		}
		length = text.length;
	}

	private void grow() {
		char[] newArr = new char[length * 2];
		System.arraycopy(text, 0, newArr, 0, text.length);
		text = newArr;
	}

	public DynamicString append(char chr) {
		if (length == text.length) {
			grow();
		}
		text[length++] = chr;
		return this;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public char charAt(int index) {
		return text[index];
	}

	@Override
	public DynamicString append(DString str) {
		for (int i = 0; i < str.length; i++) {
			append(str.charAt(i));
		}
		return this;
	}

	@Override
	public DynamicString delete(int start, int end) {
		System.arraycopy(text, end, text, start, length - end);
		length -= (end - start);
		return this;
	}

	@Override
	public DynamicString delete(int index) {
		delete(index, index + 1);
		return this;
	}

	@Override
	public String substring(int start, int end) {
		return new String(text, start, end - start);
	}

	@Override
	public String substring(int start) {
		return substring(start, length);
	}

	@Override
	public int indexOf(char chr) {

		for (int i = 0; i < length; i++) {
			if (text[i] == chr) {
				return i;
			}
		}

		return -1;
	}

	public String toString() {
		return new String(text, 0, length);
	}

	@Override
	public void reverse() {
		char[] temp = new char[length];

		for (int i = 0; i < length; i++) {
			temp[i] = text[length - 1 - i];
		}

		text = temp;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DString) {

			DString temp = (DString) obj;

			if (temp.length != this.length)
				return false;

			for (int i = 0; i < length; i++) {
				if (temp.charAt(i) != text[i])
					return false;

			}
			return true;
		}
		return false;
	}

	public int compareTo(DString str) {
		int stringLength = Math.min(length, str.length);
		int result = 0;

		for (int i = 0; i < stringLength; i++) {
			int temp = str.charAt(i);
			
			if (text[i] > temp) {
				return 1;
			} else if (text[i] < temp) {
				return -1;
			}
		}

		if (result == 0) {
			result = length - str.length;
		}

		return result;
	}
}