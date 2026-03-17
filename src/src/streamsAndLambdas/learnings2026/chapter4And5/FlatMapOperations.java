package streamsAndLambdas.learnings2026.chapter4And5;

import java.util.Arrays;
import java.util.List;

public class FlatMapOperations {
    public static void main(String[] args) {
        // Flatten list of lists
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d")
        );
        List<String> flattened = listOfLists.stream()
                .flatMap(List::stream) // Flatten the inner lists into a single stream
                .toList();
        System.out.println("Flattened List: " + flattened);

        // Split sentences into words
        List<String> sentences = Arrays.asList("Hello World", "Java Streams");
        List<String> words = sentences.stream().
                flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .toList();
        System.out.println("Words: " + words);

        //Find all numbers starting with 1.
        List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 32);
        myList.stream()
                .map(s -> s +"") // Convert to String
                .filter(s -> s.startsWith("1")) // Filter numbers starting with "1"
                .forEach(System.out::println);

        // Alternative with array
        int[] arr = {10, 15, 8, 49, 25, 98, 32};
        List<String> res = Arrays.stream(arr)
                .boxed()
                .map(s -> s + "") // Convert to String
                .filter(s -> s.startsWith("1")) // Filter numbers starting with "1"
                .toList();
        System.out.println("Numbers starting with 1: " + res);
    }
}
