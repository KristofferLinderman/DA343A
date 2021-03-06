package laboration1;

public class ArrayQueue<T> implements Queue<T> {

	private T[] array;
	private int size = 0;

	public ArrayQueue(int length) {
		array = (T[]) new Object[length];
	}

	@Override
	public void add(T elem) {
		if (array.length < size) {
			throw new QueueException("ArrayQueue is full, please leave");
		}
		array[size] = elem;
		size++;
	}

	@Override
	public T remove() {
		if (isEmpty()) {
			throw new QueueException("ArrayQueue is empty, please leave");
		}
		T rm = array[0];
		size--;
		
		for(int i = 1; i< size;i++){
			array[i-1] = array[i];
		}
		array[size] = null;
		
		return rm;
	}

	@Override
	public T element() {
		if(isEmpty()){
			throw new QueueException("ArrayQueue is empty, please leave");
		}
		
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public int size() {
		return size;
	}

}
