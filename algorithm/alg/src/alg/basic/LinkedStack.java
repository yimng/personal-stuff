package alg.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;
	
	public LinkedStack() {
		first = null;
		n = 0;
	}
	
	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	
	public Item pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}
	
	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		return first.item;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Item item : this) {
			sb.append(item);
		}
		return sb.toString();
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
}
