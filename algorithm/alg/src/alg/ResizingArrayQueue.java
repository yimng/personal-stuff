package alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item>{
	private Item[] queue;
	private int head;
	private int tail;
	private int n;
	
	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		queue = (Item[]) new Object[2];
		head = 0;
		tail = 0;
		n = 0;
	}
	
	public void resize(int cap) {
		@SuppressWarnings("unchecked")
		Item [] temp = (Item[]) new Object[cap];
		for (int i = 0; i < n; i++) {
			temp[i] = queue[(head+i) % queue.length];
		}
		head = 0;
		tail = n;
		queue = temp;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue underflow");
		}
		Item item = queue[head];
		queue[head] = null;
		head++;
		n--;
		if (head == queue.length) {
			head = 0;
		}
		if (n > 0 && n == queue.length/4) {
			resize(queue.length/2);
		}		
		return item;
	}
	
	public void enqueue(Item item) {
		if (n == queue.length) {
			resize(2*queue.length);
		}
		queue[tail++] = item;
		if (tail == queue.length) {
			tail = 0;
		}
		n++;
	}
	
	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue underflow");
		}
		return queue[head];
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class ArrayIterator implements Iterator<Item> {
		private int i = 0;
		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("queue underflow");
			}
			Item item = queue[(head + i) % queue.length];
			i++;
			return item;
		}
	}
	
}
