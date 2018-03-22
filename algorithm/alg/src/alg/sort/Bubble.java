package alg.sort;

public class Bubble {

	public static void sort(Comparable[] a) {
		int l = a.length;
		for (int i = 0; i < l - 1; i++) {
			for (int j = 1; j < l - i; j++) {
				if (less(a, j-1, j)) {
					exch(a, j-1, j);
				}
			}
		}
	}

	private static boolean less(Comparable[] pq, int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private static void exch(Object[] pq, int i, int j) {
		Object tmp = pq[i];
		pq[i] = pq[j];
		pq[j] = tmp;
	}

	public static void main(String args[]) {
		Integer[] a = new Integer[] { 2, 4, 6, 7, 8 };

		sort(a);
		for (int i : a) {
			System.out.println(i);
		}
	}
}
