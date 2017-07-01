package alg.graph;

import alg.basic.LinkedQueue;
import alg.basic.UF;
import alg.sort.MinPQ;
import stdio.In;
import stdio.StdOut;

public class LazyPrimMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;
	private double weight;
	private boolean[] marked;
	private LinkedQueue<Edge> mst;
	private MinPQ<Edge> pq;

	public LazyPrimMST(EdgeWeightedGraph G) {
		mst = new LinkedQueue<Edge>();
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) // run Prim from all vertices to
			if (!marked[v])
				prim(G, v); // get a minimum spanning forest

		// check optimality conditions
		assert check(G);
	}

	// run Prim's algorithm
	private void prim(EdgeWeightedGraph G, int s) {
		scan(G, s);
		while (!pq.isEmpty()) { // better to stop when mst has V-1 edges
			Edge e = pq.delMin(); // smallest edge on pq
			int v = e.either();
			int w = e.other(v); // two endpoints
			assert marked[v] || marked[w];
			if (marked[v] && marked[w])
				continue; // lazy, both v and w already scanned
			mst.enqueue(e); // add e to MST
			weight += e.weight();
			if (!marked[v])
				scan(G, v); // v becomes part of tree
			if (!marked[w])
				scan(G, w); // w becomes part of tree
		}
	}

	private void scan(EdgeWeightedGraph G, int v) {
		assert !marked[v];
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			if (!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

	public double weight() {
		return weight;
	}

	// check optimality conditions (takes time proportional to E V lg* V)
	private boolean check(EdgeWeightedGraph G) {

		// check weight
		double totalWeight = 0.0;
		for (Edge e : edges()) {
			totalWeight += e.weight();
		}
		if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
			System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
			return false;
		}

		// check that it is acyclic
		UF uf = new UF(G.V());
		for (Edge e : edges()) {
			int v = e.either(), w = e.other(v);
			if (uf.connected(v, w)) {
				System.err.println("Not a forest");
				return false;
			}
			uf.union(v, w);
		}

		// check that it is a spanning forest
		for (Edge e : G.edges()) {
			int v = e.either(), w = e.other(v);
			if (!uf.connected(v, w)) {
				System.err.println("Not a spanning forest");
				return false;
			}
		}

		// check that it is a minimal spanning forest (cut optimality
		// conditions)
		for (Edge e : edges()) {

			// all edges in MST except e
			uf = new UF(G.V());
			for (Edge f : mst) {
				int x = f.either(), y = f.other(x);
				if (f != e)
					uf.union(x, y);
			}

			// check that e is min weight edge in crossing cut
			for (Edge f : G.edges()) {
				int x = f.either(), y = f.other(x);
				if (!uf.connected(x, y)) {
					if (f.weight() < e.weight()) {
						System.err.println("Edge " + f + " violates cut optimality conditions");
						return false;
					}
				}
			}

		}

		return true;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		LazyPrimMST mst = new LazyPrimMST(G);
		for (Edge e : mst.edges()) {
			StdOut.println(e);
		}
		StdOut.printf("%.5f\n", mst.weight());
	}
}
