package ru.academit.vkap.lambda.uselambda;

import ru.academit.vkap.lambda.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class UseLambda {
    private static void printNamesDistinct(List<Person> persons) {
        System.out.println("Distinct names:");
        System.out.println(persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList()));
    }

    private static void printFormatNamesDistinct(List<Person> persons) {
        System.out.println("Format distinct names:");
        System.out.println(persons.stream().
                map(Person::getName).
                distinct().
                collect(Collectors.joining(", ", "Имена: ", ".")));
    }

    private static void printYoungerPeople(List<Person> persons) {
        System.out.println("People younger than 18. Average age is: ");
        System.out.println(persons.stream()
                .mapToInt(Person::getAge)
                .filter(p -> p < 18)
                .average());
    }

    private static void printPeoplesMapGroup(List<Person> persons) {
        System.out.println("People with group average of age:");
        Map<String, Double> personsAverageAgesByName = persons.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        personsAverageAgesByName.forEach((n, a) -> System.out.printf("%10s: %s%n", n, a));
    }

    public static void printPeoplesOrder(List<Person> persons) {
        System.out.println("People sorted: " + System.lineSeparator());
        persons.stream()
                .filter(a -> a.getAge() >= 20 && a.getAge() <= 45)
                .sorted((a1, a2) -> a2.getAge() - a1.getAge())
                .forEach(n -> System.out.println(n.getName()));
    }

    public static void printInfiniteStream(int rootCount) {
        DoubleStream.iterate(1, z -> z + 1)
                .map(z -> {
                    System.out.print("Number: " + z + " Square root: ");
                    return Math.sqrt(z);
                })
                .limit(rootCount)
                .forEach(System.out::println);
    }

    public static void printFibonacciNumbers(int fibonacciCount) {
        System.out.println("Numbers of Fibonacci");
        Stream.iterate(new long[]{0L, 1L}, x -> new long[]{x[1], x[0] + x[1]})
                .map(x -> x[0])
                .limit(fibonacciCount)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Person> list = Arrays.asList(new Person("Antuan", 13),
                new Person("Antuan", 12),
                new Person("Mark", 28),
                new Person("Angela", 18),
                new Person("Kristin", 22),
                new Person("Debra", 44),
                new Person("Fredi", 8));

        printNamesDistinct(list);
        printFormatNamesDistinct(list);
        printYoungerPeople(list);
        printPeoplesMapGroup(list);
        printPeoplesOrder(list);
        System.out.println("===Task 2===");
        int rootCount = 11;
        printInfiniteStream(rootCount);
        int fibonacciCount = 20;
        printFibonacciNumbers(fibonacciCount);
    }
}
