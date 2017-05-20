package alg;

import java.util.Arrays;

import alg.basic.BinarySearch;

public class ThreeSumFast {
	public static int count(int [] a) {
		Arrays.sort(a);
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (BinarySearch.indexOf(a, -a[i] - a[j]) > j) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}
