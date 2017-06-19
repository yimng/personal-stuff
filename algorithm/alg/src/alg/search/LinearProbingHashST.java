package alg.search;

import alg.basic.LinkedQueue;
import stdio.StdIn;
import stdio.StdOut;

public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;

	private int n; // number of key-value pairs in the symbol table
	private int m; // size of linear probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values

	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHashST(int capacity) {
		keys = (Key[]) new Object[capacity];
		vals = (Value[]) new Object[capacity];
		n = 0;
		m = capacity;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	// hash function for keys - returns value between 0 and M-1
	private int hash(Key key) {
		return (key.hashCode() & 0x7ffffff) % m;
	}

	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m = temp.m;
	}

	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		if (2 * n >= m) {
			resize(2 * m);
		}
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i] == key) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		n++;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i] == key) {
				return vals[i];
			}
		}
		return null;
	}

	public void delete(Key key) {
		if (!contains(key)) {
			return;
		}
		int i = hash(key);
		while (keys[i] != key) {
			i = (i + 1) % m;
		}
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % m;
		while (keys[i] != null) {
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}
		n--;

		if (n > 0 && n <= m / 8) {
			resize(m / 2);
		}
		assert check();
	}

	public Iterable<Key> keys() {
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				queue.enqueue(keys[i]);
			}
		}
		return queue;
	}

	private boolean check() {
		if (m < 2 * n) {
			System.err.println("Hash table size m = " + m + "; array size n = " + n);
			return false;
		}

		// check that each key in table can be found by get()
		for (int i = 0; i < m; i++) {
			if (keys[i] == null)
				continue;
			else if (get(keys[i]) != vals[i]) {
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}

		// print keys
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}

}
