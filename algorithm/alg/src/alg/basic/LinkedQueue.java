package alg.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item>{
	private int n;
	private Node head;
	private Node tail;
	
	public LinkedQueue() {
		n = 0;
		head = null;
		tail = null;
	}
	
	public void enqueue(Item item) {
		Node oldtail = tail;
		tail = new Node();
		tail.item = item;
		tail.next = null;
		if (isEmpty()) {
			head = tail;
		} else {
			oldtail.next = tail;
		}
		n++;
	}
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue empty");
		}
		Item item = head.item;
		head = head.next;
		n--;
		if (isEmpty()) {
			tail = null;
		}
		return item;
	}
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	private class Node {
		private Item item;
		private Node next;
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
