package alg.string;

public class Quick3String {

	private static int charAt(String s, int d) {
		if (d < s.length())
			return s.charAt(d);
		else
			return -1;
	}

	public static void sort(String[] s) {
		int N = s.length;
		sort(s, 0, N - 1, 0);
	}

	private static void exch(String[] a, int i, int j) {
		String t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void sort(String[] s, int lo, int hi, int d) {
		if (lo >= hi) {
			return;
		}
		int lt = lo, gt = hi;
		int v = charAt(s[lo], d);
		int i = lo + 1;
		while (i < gt) {
			int t = charAt(s[i], d);
			if (t < v) {
				exch(s, lt++, i++);
			} else if (t > v) {
				exch(s, i, gt--);
			} else {
				i++;
			}
		}
		sort(s, lo, lt - 1, d);
		sort(s, lt, gt, d + 1);
		sort(s, gt + 1, hi, d);
	}
}
