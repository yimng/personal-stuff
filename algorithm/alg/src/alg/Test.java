package alg;

import stdio.StdOut;

public class Test {

	public static void main(String[] args) {
		Integer a1 = 100;
		Integer a2 = 100;
		System.out.println(a1 == a2);   // true

		Integer b1 = new Integer(100);
		Integer b2 = new Integer(100);
		System.out.println(b1 == b2);   // false

		Integer c1 = 150;
		Integer c2 = 150;
		System.out.println(c1 == c2);   // false
	}
	public static int mystery(int a, int b)
	{
		if (b == 0) return 0;
		if (b % 2 == 0) return mystery(a+a, b/2);
		return mystery(a+a, b/2) + a;
	}
}
