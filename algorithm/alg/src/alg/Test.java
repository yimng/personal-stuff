package alg;

import stdio.StdOut;

public class Test {

	public static void main(String[] args) {
		int r = mystery(2, 10);
		StdOut.println(r);
	}
	public static int mystery(int a, int b)
	{
		if (b == 0) return 0;
		if (b % 2 == 0) return mystery(a+a, b/2);
		return mystery(a+a, b/2) + a;
	}
}
