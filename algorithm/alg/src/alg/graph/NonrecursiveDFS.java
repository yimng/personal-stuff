package alg.graph;

import java.util.Iterator;

import alg.basic.LinkedStack;
import stdio.In;
import stdio.StdOut;

public class NonrecursiveDFS {
	private boolean[] marked;

	public NonrecursiveDFS(Graph G, int s) {
		marked = new boolean[G.V()];
		validateVertex(s);
		Iterator<Integer>[] adj = (Iterator[]) new Iterator[G.V()];
		for (int i = 0; i < G.V(); i++) {
			adj[i] = G.adj(s).iterator();
		}
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		marked[s] = true;
		stack.push(s);
		while (!stack.isEmpty()) {
			int v = stack.peek();
			if (adj[v].hasNext()) {
				int w = adj[v].next();
				if (!marked[w]) {
					marked[w] = true;
					stack.push(w);
				}
			} else {
				stack.pop();
			}
		}
	}

	public boolean marked(int v) {
		validateVertex(v);
		return marked[v];
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
		int s = Integer.parseInt(args[1]);
		NonrecursiveDFS dfs = new NonrecursiveDFS(G, s);
		for (int v = 0; v < G.V(); v++)
			if (dfs.marked(v))
				StdOut.print(v + " ");
		StdOut.println();
	}
}
