package lab8;
import java.util.Comparator;
import java.util.Arrays;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.lang.reflect.Array.get;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        String[] words = {"C", "A", "A", "B", "D", "D"};
        List<String> kolvo = Arrays.stream(words)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(kolvo);

        int max = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream().mapToInt(e -> e.intValue())
                .max()
                .getAsInt();

        System.out.println(
                Arrays.stream(words)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
//                        .sorted(Map.Entry.comparingByValue())
//                        .collect(Collectors.toList()))
//                        .sorted(Comparator.comparingLong(e -> e.getValue()))
//                        .collect(Collectors.toList())

                        .filter(e ->e.getValue() == Arrays.stream(words)
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .values().stream().mapToInt(x -> x.intValue())
                                .max()
                                .getAsInt())
                        .map(Map.Entry::getKey).collect(Collectors.toList())
                        .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0)

        );

        System.out.println(System.lineSeparator());

        System.out.println("Задание 2");
        List<Worker> workers = new ArrayList<>(Arrays.asList(
                new Worker("Виталий", 17, 100),
                new Worker("Марк", 15, 200),
                new Worker("Саня", 35, 300),
                new Worker("Никита", 75, 900),
                new Worker("Рома", 55, 700)
        ));
        System.out.println(workers.stream().mapToDouble(Worker -> Worker.salary).average());
        System.out.println(System.lineSeparator());

        System.out.println("Задание 3");
        Old(3, workers);
    }


    public static void Old(int n, List<Worker> x) {
        System.out.println(x.stream()
                .sorted((i1, i2) -> i2.age - i1.age)
                .map(Worker -> Worker.name)
                .limit(n)
                .collect(Collectors.joining(" , ", n + " самых старших сотрудников зовут ", ";")));
        System.out.println(System.lineSeparator());
    }

}
