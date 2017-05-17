package alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {
	private int n;
	private Item[] stack;

	@SuppressWarnings("unchecked")
	public ResizingArrayStack() {
		stack = (Item[]) new Object[2];
		n = 0;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public void push(Item item) {
		if (n == stack.length) {
			resize(2 * n);
		}
		stack[n++] = item;
	}

	public Item pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack Underflow");
		}
		Item item = stack[n - 1];
		stack[n - 1] = null;
		n--;
		if (n > 0 && n == stack.length / 4) {
			resize(stack.length / 2);
		}

		return item;
	}

	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack Underflow");
		}
		return stack[n - 1];
	}

	public void resize(int cap) {
		@SuppressWarnings("unchecked")
		Item[] s = (Item[]) new Object[cap];
		for (int i = 0; i < n; i++) {
			s[i] = stack[i];
			stack[i] = null;
		}
		stack = s;
	}

	public int size() {
		return n;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item> {
		private int i;

		public ReverseArrayIterator() {
			i = n - 1;
		}

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return stack[i--];
		}
	}
}
