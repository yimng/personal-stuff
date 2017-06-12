package alg.search;

import alg.basic.LinkedQueue;
import stdio.StdIn;
import stdio.StdOut;

public class SeparateChainingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	private int N;
	private int M;
	private SequentialSearchST<Key, Value>[] st;

	public SeparateChainingHashST() {
		this(INIT_CAPACITY);
	}

	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST<Key, Value>();
		}
	}

	private void resize(int chains) {
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST(chains);
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.M = temp.M;
		this.N = temp.N;
		this.st = temp.st;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}

		// double table size if average length of list >= 10
		if (N >= 10 * M)
			resize(2 * M);

		int i = hash(key);
		if (!st[i].contains(key))
			N++;
		st[i].put(key, val);
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key))
			N--;
		st[i].delete(key);

		// halve table size if average length of list <= 2
		if (M > INIT_CAPACITY && N <= 2 * M)
			resize(M / 2);
	}

	public Iterable<Key> keys() {
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}

	public static void main(String[] args) {
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}

		// print keys
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));

	}
}
