package streamsAndLambdas.learnings2026.chapter4And5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DistinctOperations {
    public static void main(String[] args) {
        // Distinct — Removes duplicate elements from the stream. It uses the equals() method to determine equality, so it works well with custom objects if you override equals() and hashCode() properly.
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);

        List<Integer> distinctNumbers = numbers.stream()
                .distinct() // Remove duplicates
                .toList();
        System.out.println("Distinct Numbers: " + distinctNumbers);

        // Sorted - Natural order
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9);
        List<Integer> res = unsorted.stream()
                .sorted()
                .toList();
        System.out.println("Sorted Numbers: " + res);

        // // Reverse order
        List<Integer> reverseSorted = unsorted.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("Reverse Sorted Numbers: " + reverseSorted);

    }
}
