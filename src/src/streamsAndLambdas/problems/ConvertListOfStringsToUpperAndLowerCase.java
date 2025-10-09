package streamsAndLambdas.problems;

import java.util.Arrays;
import java.util.List;

public class ConvertListOfStringsToUpperAndLowerCase {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple", "Google","Samsung","OnePlus");
        List<String> lowerCase = list.stream()
                .map(String::toLowerCase)
                .toList();
        List<String> upperCase = list.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("Upper Case:"+ upperCase);
        System.out.println("Lower Case:"+ lowerCase);
    }
}
