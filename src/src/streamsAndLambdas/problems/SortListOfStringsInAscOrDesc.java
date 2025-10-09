package streamsAndLambdas.problems;

import entity.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortListOfStringsInAscOrDesc {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple", "Google", "Samsung", "OnePlus", "Xo","TO","GOGO");
        List<String> listWithNulls = Arrays.asList("Apple", null, "Google", "Samsung", "OnePlus", null,null);

        System.out.println("Natural order: " + list.stream()
                .sorted()
                .toList());
        System.out.println("Descending: " + list.stream()
                .sorted(Comparator.reverseOrder())
                .toList());
        System.out.println("Compare with length: " + list.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toList());
        System.out.println("Sorted by last char: " + list.stream()
                .sorted(Comparator.comparing(s-> s.charAt(s.length()-1)))
                .toList());
        System.out.println("Sorted by length first and then natural order " + list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .toList());
        System.out.println("Sorted case sensitively" + list.stream()
                .sorted(Comparator.comparing(String::toLowerCase))
                .toList());
        System.out.println("Sorted by length first and then natural order " + list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .toList());
        System.out.println("Sorted by with nulls first " + listWithNulls.stream()
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .toList());
        System.out.println("Sorted by with nulls first " + listWithNulls.stream()
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .toList());
        List<Employee> employees = Arrays.asList(
                new Employee(1,"Sandeep",30),
                new Employee(2,"Sagar",22),
                new Employee(3,"Dave",22),
                new Employee(4,"Test_Dave",22),
                new Employee(5,"Test",10),
                new Employee(6,"Test10",10),
                new Employee(7,"Test11",10),
                new Employee(8,"User",11));
        System.out.println("Sort employees by Age" + employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge))
                .toList());
    }
}
