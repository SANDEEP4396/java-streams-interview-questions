package streamsAndLambdas.learnings2026.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreatingStreams {
    public static void main(String[] args) {

        //1. From a Collection — The most common. Any List, Set, or Map.entrySet() can call .stream() directly.
        // This is your go-to 90% of the time.
        List<String> names = List.of("Sandeep", "Sagar", "Priya", "Pavan");
        Stream<String> nameStreams = names.stream();
        System.out.println("Using stream:");
        nameStreams.filter(s -> s.startsWith("S"))
                .map(name -> name.substring(0, 3)) // Extract first 3 characters
                .forEach(System.out::println);

        // We can also make use of parallelStream() to process the stream in parallel, which can improve performance for large datasets.
        //splits work across CPU cores automatically. Just be cautious with side effects and shared mutable state when using parallel streams.
        Stream<String> parallelNameStreams = names.parallelStream();
        System.out.println("\nUsing parallelStream:");
        parallelNameStreams.filter(s -> s.startsWith("S"))
                .forEach(System.out::println);

        //2. From an Array — Arrays have a built-in method to create a stream.
        String[] nameArray = {"Sandeep", "Sagar", "Priya", "Pavan"};
        Stream<String> arrayStream = Stream.of(nameArray); // or Stream<String> arrayStream = Arrays.stream(nameArray);
        System.out.println("\nUsing Stream.of with array:");
        arrayStream
                .map(String::toUpperCase)
                .forEach(System.out::println);

        int[] nums = {1, 2, 3, 4, 5};
        // For primitive arrays, use IntStream, LongStream, or DoubleStream to avoid boxing overhead.
        int sum = Arrays.stream(nums).sum();
        System.out.println("\nSum of nums: " + sum);
        int max = Arrays.stream(nums).max().orElseThrow();
        System.out.println("Max of nums: " + max);
        double avg = Arrays.stream(nums).sum() / (double) nums.length;
        System.out.println("Average of nums: " + avg);


        //3. From static Stream.of() — When you have individual values (not already in a collection), just pass them inline. Great for testing or small fixed datasets.
        Stream<Integer> stream = Stream.of(10, 20, 30, 40);
        System.out.println("\nUsing Stream.of with individual values:");
        stream.map(num -> num * 2)
                .forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();
        System.out.println("\nUsing Stream.empty:");
        emptyStream.forEach(System.out::println); // No output, but no error either

        //4. Infinite Streams — Use Stream.generate() for a supplier-based infinite stream, or Stream.iterate() for a seed-based infinite stream. Just remember to limit them, or you'll end up with an infinite loop!
        System.out.println("\nUsing IntStream.range:");
        // Even numbers: 0, 2, 4, 6, 8
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("\nUsing IntStream.rangeClosed:");
        // Java 9+ overload with a predicate (acts like a while-loop):
        Stream.iterate(1, n -> n < 100, n -> n * 2)
                .forEach(System.out::println); // 1, 2, 4, 8, 16, 32, 64
        System.out.println("\nUsing Stream.generate:");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\nUsing Stream.generate with UUID:");
        Stream.generate(UUID::randomUUID)
                .limit(5)
                .forEach(System.out::println);
        //5. Using Range Methods — For primitive streams, you can create a stream of numbers in a specific range. IntStream.range(start, end) creates a stream from start (inclusive) to end (exclusive), while IntStream.rangeClosed(start, end) includes both endpoints.
        System.out.println("\nUsing IntStream.range:");
        IntStream.range(1, 5)
                .forEach(System.out::println); // 1,2,3,4

        System.out.println("\nUsing  IntStream.rangeClosed:");
        IntStream.rangeClosed(1, 5)
                .forEach(System.out::println);// 1,2,3,4,5

        // 6. From a File — Use Files.lines(Path) to create a stream of lines from a file. This is super handy for processing text files without loading the entire file into memory.
        Path filePath = Path.of("src/src/streamsAndLambdas/learnings2026/data.txt");
        System.out.println("\nUsing Files.lines:");
        try(Stream<String> lines = Files.lines(filePath)){
            lines.filter(line -> line.contains("ERROR"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 7. From a String — Use String.chars() to create an IntStream of character codes, or String.codePoints() for Unicode code points. This is useful for processing individual characters in a string.
       "hello".chars()
                .mapToObj(c -> String.valueOf((char) c))// Convert int to char and then to String. MapToObj is used to convert IntStream to Stream<String>.
                .forEach(System.out::println); // h e l l o

    // Count vowels
        long vowels = "Sandeep".toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) >= 0) // Check if the character is a vowel. indexOf returns -1 if the character is not found, so we check for >= 0 to confirm it's a vowel.
                .count(); // 3
        System.out.println("\nNumber of vowels in 'Sandeep': " + vowels);
    }
}
