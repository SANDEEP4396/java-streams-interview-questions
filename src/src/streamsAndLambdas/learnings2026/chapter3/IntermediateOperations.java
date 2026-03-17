package streamsAndLambdas.learnings2026.chapter3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntermediateOperations {
    public static void main(String[] args) {
        List<Integer> scores = List.of(45, 82, 33, 91, 55, 70,12,11,22);
        // 1. filter() — Selects elements based on a condition. In this case, we filter out scores greater than 35.
        System.out.println("Filtered Scores (>= 50):");
        Map<Boolean, List<Integer>> sortedScores = scores.stream()
                .collect(Collectors.partitioningBy(score -> score >= 35));
        System.out.println("Scores >= 35: " + sortedScores.get(true));
        System.out.println("Scores < 35: " + sortedScores.get(false));

        //Strings — starts with a capital letter or not:
        List<String> words = List.of("apple", "Banana", "cherry", "Dates");
        Map<Boolean, List<String>> sortedWords = words.stream()
                .collect(Collectors.partitioningBy(word -> Character.isUpperCase(word.charAt(0))));
        System.out.println("\nWords starting with uppercase:");
        System.out.println("Uppercase: " + sortedWords.get(true));
        System.out.println("Lowercase: " + sortedWords.get(false));

        // Adults vs minors:
        List<Person> people = List.of(
            new Person("Sandeep", 27),
            new Person("Ravi", 16),
            new Person("Priya", 22),
            new Person("Pavani", 50),
            new Person("Sagar", 11),
            new Person("Pavan", 44),
            new Person("Arjun", 15));

        Map<Boolean, List<Person>> adultsAndMinors = people.stream()
                .collect(Collectors.partitioningBy(person -> person.age() >= 18));
        System.out.println("\nAdults and Minors:");
        System.out.println("Adults: " + adultsAndMinors.get(true));
        System.out.println("Minors: " + adultsAndMinors.get(false));

        // Downstream collector — don't just collect the list, transform it
        //partitioningBy accepts a second argument — a downstream collector — so you can do more than just collect into a list:

        Map<Boolean, Long> countAdultsAndMinors = people.stream()
                .collect(Collectors.partitioningBy(person -> person.age()>=18, Collectors.counting()));
        System.out.println("\nCount of Adults and Minors:");
        System.out.println("Adults: " + countAdultsAndMinors.get(true));
        System.out.println("Minors: " + countAdultsAndMinors.get(false));

        // Get names of adults and minors: instead of counting, we can map to names and collect into a list:
        Map<Boolean, List<String>> namesOfAdultsAndMinors = people.stream()
                .collect(Collectors.partitioningBy(person -> person.age()>=18,
                        Collectors.mapping(Person::name, Collectors.toList())));
        System.out.println("\nNames of Adults and Minors:");
        System.out.println("Adults: " + namesOfAdultsAndMinors.get(true));
        System.out.println("Minors: " + namesOfAdultsAndMinors.get(false));

        // groupingBy example for contrast
        Map<String, List<Person>> byAge = people.stream()
                .collect(Collectors.groupingBy(p -> p.age() >= 18 ? "Adult" : "Minor"));
        System.out.println("\nGrouping by age:");
        System.out.println("Adults: " + byAge.get("Adult"));
        System.out.println("Minors: " + byAge.get("Minor"));
        // Works but partitioningBy is cleaner for boolean conditions
    }
}
record Person(String name, int age) {
}
