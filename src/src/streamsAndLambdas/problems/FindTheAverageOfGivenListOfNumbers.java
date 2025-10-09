package streamsAndLambdas.problems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindTheAverageOfGivenListOfNumbers {
    public static void main(String[] args) {
        // One way is to use averagingInt which uses wrapper classes
        List<Integer> integers = Arrays.asList(1111,2,3,5,6,7,8,9);
       double res = integers.stream()
               .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(res);

        // Second way and the best way
                double res2= integers.stream()
                        .mapToInt(Integer::intValue) // Convert to IntStream
                        .average() // Calculate average directly on IntStream
                        .orElse(0); // Handle case where the stream is empty
                System.out.println("Result from 2nd solution: "+res2);

    }
}
