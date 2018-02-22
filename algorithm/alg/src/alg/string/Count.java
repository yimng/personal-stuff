package alg.string;

import stdio.StdIn;
import stdio.StdOut;

public class Count {

	private Count(){}
	
	public static void main(String args[]) {
		Alphabet a = new Alphabet(args[0]);
		int R = a.R();
		int []count = new int[R];
		while(StdIn.hasNextChar()) {
			char c = StdIn.readChar();
			if (a.contains(c)) {
				count[a.toIndex(c)]++;
			}
		}
		for (int i = 0; i < R; i++) {
			StdOut.println(a.toChar(i) + " : " + count[i]);
			
		}
	}
}
