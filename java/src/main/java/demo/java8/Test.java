package demo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test {
    public static void main(String args[]) {
        List<Integer> weights = Arrays.asList(3,5,8,9);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);
    }
    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e: list) {
            result.add(f.apply(e));
        }
        return result;
    }
}

class Apple {
    private int weight;
    public Apple(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return "" + weight;
    }
}
