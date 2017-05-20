package alg.basic;

import stdio.StdIn;
import stdio.StdOut;

public class QuickUF {
	private int[] id;
	private int count;

	public QuickUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		int rp = find(p);
		int rq = find(q);
		if (rp == rq) {
			return;
		}
		id[rp] = rq;
		count--;
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickUF uf = new QuickUF(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) {
				continue;
			}
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(uf.count + "components");
	}
}
