package demo.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class DishTest {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("frech fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", false, 300, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        List<String> threeHighCaloricDishNames =
                menu.stream().filter(d -> {
                    System.out.println("filting :" + d.getName());
                    return d.getCalories() > 300;
                })
                        .map(d -> {
                            System.out.println("name: " + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);
        menu.forEach(System.out::println);
        List<Dish> vegetarian = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(vegetarian);
        List<Integer> numbers = Arrays.asList(4, 1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(d -> d % 2 == 0).distinct().forEach(System.out::println);
        menu.stream().map(Dish::getName).map(String::length).forEach(System.out::println);

        System.out.println("-----------------------------------------------");
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().map(n -> n * n).forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).filter(a -> {
                    return (a[0] + a[1]) % 3 == 0;
                }).collect(Collectors.toList());
        for (int[] a : pairs
                ) {
            System.out.print(a[0]);
            System.out.println(a[1]);
        }

        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = menu.stream().collect(maxBy(dishComparator));
        Double aDouble = menu.stream().collect(averagingInt(Dish::getCalories));
        IntSummaryStatistics summaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);
        String joinName = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(joinName);

        Map<Dish.Type, Dish> dishMap = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(dishMap);

        Map<Dish.Type, Integer> totolCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totolCaloriesByType);

        Map<Dish.Type, HashSet<Dish.CaloricLevel>> coloryLevelByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
            else return Dish.CaloricLevel.FAT;
        }, toCollection(HashSet::new))));
        System.out.println(coloryLevelByType);

        Map<Boolean, Map<Dish.Type, List<Dish>>> ve =
                menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));



    }



}
