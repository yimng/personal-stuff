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
		st = new SequentialSearchST[M];
		for (int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST<Key, Value>();
		}
		this.M = M;
	}

	private void resize(int chains) {
		SeparateChainingHashST temp = new SeparateChainingHashST(chains);
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.st = temp.st;
		this.M = temp.M;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7ffffff) % M;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public Value get(Key key) {
		int i = hash(key);
		return st[i].get(key);
	}

	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		int i = hash(key);
		if (!st[i].contains(key)) {
			N++;
		}
		st[i].put(key, val);
	}

	public void delete(Key key) {
		int i = hash(key);
		if (st[i].contains(key)) {
			N--;
		}
		st[i].delete(key);
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
