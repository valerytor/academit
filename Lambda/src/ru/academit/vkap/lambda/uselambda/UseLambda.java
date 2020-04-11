package ru.academit.vkap.lambda.uselambda;

import ru.academit.vkap.lambda.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class UseLambda {
    private String getNamesDistinct(List<Person> persons) {
        return persons.stream().map(n -> n.getName()).distinct().collect(Collectors.toList()).toString();
    }

    private String getFormatNamesDistinct(List<Person> persons) {
        return persons.stream().map(n -> n.getName()).distinct().collect(Collectors.joining(", ", "Имена: ", "."));
    }

    private double getYoungerPeople(List<Person> persons) {
        return persons.stream()
                .map(p -> p.getAge())
                .filter(p -> p < 18)
                .mapToInt(p -> p)
                .average().orElse(0);
    }

    private void getPeoplesMapGroup(List<Person> persons) {
        Map<String, Double> personsByAvgAge = persons.stream().collect(Collectors.groupingBy(n -> n.getName(), Collectors.averagingDouble(a -> a.getAge())));
        personsByAvgAge.forEach((n, a) -> System.out.printf("%10s: %s%n", n, a));
    }

    public void getPeoplesOrder(List<Person> persons) {
        System.out.println("People sorted: " + System.lineSeparator());
        persons.stream()
                .filter(a -> a.getAge() >= 20 && a.getAge() <= 45)
                .sorted((a1, a2) -> a1.getAge() - a2.getAge())
                .forEach(n -> System.out.println(n.getName()));
    }

    public void getInfiniteStream() {
        System.out.println("Please, enter integer number: ");
        Scanner scanner = new Scanner(System.in);
            int intLimit = scanner.nextInt();
            DoubleStream.iterate(1, z -> z + 1)
                    .map(z -> {
                        System.out.print("Number: " + z + " Square root: ");
                        return Math.sqrt(z);
                    })
                    .limit(intLimit)
                    .forEach(z -> System.out.println(z));
    }

    public void getFibonacciNumbers() {
        System.out.println("Numbers of Fibonacci");
        System.out.println("Please, enter integer number: ");
        Scanner scanner = new Scanner(System.in);
        int intLimit = scanner.nextInt();
        Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .map(x -> x[0])
                .limit(intLimit)
                .forEach(z -> System.out.println(z));
    }

    public static void main(String[] args) {
        UseLambda useLambda = new UseLambda();
        List<Person> list = Arrays.asList(new Person("Antuan", 13),
                new Person("Antuan", 12),
                new Person("Mark", 28),
                new Person("Angela", 18),
                new Person("Kristin", 22),
                new Person("Debra", 44),
                new Person("Fredi", 8));

        System.out.println("Distinct names:" + System.lineSeparator() + useLambda.getNamesDistinct(list));
        System.out.println("Format distinct names:" + System.lineSeparator() + useLambda.getFormatNamesDistinct(list));
        System.out.println("People younger than 18. Average age is: " + System.lineSeparator() + Double.toString(useLambda.getYoungerPeople(list)));
        System.out.println("People with group average of age:" + System.lineSeparator());
        useLambda.getPeoplesMapGroup(list);
        useLambda.getPeoplesOrder(list);
        System.out.println("===Task 2===");
        useLambda.getInfiniteStream();
        useLambda.getFibonacciNumbers();
    }
}
