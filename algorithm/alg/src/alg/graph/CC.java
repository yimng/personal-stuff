package alg.graph;

import alg.basic.LinkedQueue;
import stdio.In;
import stdio.StdOut;

public class CC {
	private boolean[] marked; // marked[v] = has vertex v been marked?
	private int[] id; // id[v] = id of connected component containing v
	private int[] size; // size[id] = number of vertices in given component
	private int count; // number of connected components

	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		size = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
				count++;
			}
		}
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public int id(int v) {
		validateVertex(v);
		return id[v];
	}

	public int size(int v) {
		validateVertex(v);
		return size[id[v]];
	}

	public int count() {
		return count;
	}

	public boolean connected(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return id(v) == id(w);
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		CC cc = new CC(G);

		// number of connected components
		int m = cc.count();
		StdOut.println(m + " components");

		// compute list of vertices in each connected component
		LinkedQueue<Integer>[] components = (LinkedQueue<Integer>[]) new LinkedQueue[m];
		for (int i = 0; i < m; i++) {
			components[i] = new LinkedQueue<Integer>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[cc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < m; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}
