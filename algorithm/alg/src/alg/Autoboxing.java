package alg;

import stdio.StdOut;

/******************************************************************************
 *  Compilation:  javac Autoboxing.java
 *  Execution:    java  Autoboxing
 *  Dependencies: StdOut.java
 *
 *  Headaches with autoboxing. Why does the following code behave
 *  the way it does?
 *
 *  % java Autoboxing
 *  42 and 42 are incomparable
 *  43 == 43
 *  142 and 142 are incomparable
 *
 *  Explanation:
 *    - cmp(new Integer(42), new Integer(42))
 *      first and second refer to different objects holding the value 42;
 *      thus, first != second.
 *      The expressions (first < second) and (first > second) autounbox
 *      the Integer values to 42, so neither expression is true.
 *
 *   - cmp(43, 43)
 *     the values 43 are autoboxed to the same Integer object because
 *     Java's Integer implementation caches the objects associated with
 *     the values -128 to 127 and the valueOf() method uses the cached
 *     values (and valueOf() gets called by the autoboxing).
 *     
 *   - cmp(142, 142)
 *     the values 142 are autoboxed to different Integer objects.
 *     
 *     
 *
 ******************************************************************************/

public class Autoboxing {

    public static void cmp(Integer first, Integer second) {
        if (first < second)
            StdOut.printf("%d < %d\n", first, second);
        else if (first == second)
            StdOut.printf("%d == %d\n", first, second);
        else if (first > second)
            StdOut.printf("%d > %d\n", first, second);
        else
            StdOut.printf("%d and %d are incomparable\n", first, second);
    }

    public static void main(String[] args) {
        cmp(new Integer(42), 43);
        cmp(new Integer(42), new Integer(42));
        cmp(43, 43);
        cmp(142, 142);

        double x1 = 0.0, y1 = -0.0;
        Double a1 = x1, b1 = y1;
        StdOut.println(x1 == y1);
        StdOut.println(a1.equals(b1));

        double x2 = 0.0/0.0, y2 = 0.0/0.0;
        Double a2 = x2, b2 = y2;
        StdOut.println(x2 != y2);
        StdOut.println(!a2.equals(b2));
    }

}