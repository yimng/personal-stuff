package alg.sort;

public class IndexMinPQ<Key extends Comparable<Key>> {
	private int N;
	private int [] pq;
	private int [] qp;
	private Key[] keys;
	public IndexMinPQ(int maxN) {
		keys = (Key[])new Comparable[maxN + 1];
		pq = new int[maxN +1];
		qp = new int[maxN + 1];
		for (int i = 0; i <= maxN; i++) {
			qp[i] = -1;
		}
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public boolean contains(int k) {
		return qp[k] != -1;
	}
	
	public void insert(int k, Key key) {
		N++;
		qp[k] = N;
		pq[N] = k;
		keys[k] = key;
		swim(N);
	}
	
	
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) {
				j++;
			}
			if (!less(k, j)) break;
			exch(k,j);
			k = j;
		}
	}
	
	private boolean less(int i, int j){
		return keys[i].compareTo(keys[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key t = keys[i];
		keys[i] = keys[j];
		keys[j] = t;
	}
}
