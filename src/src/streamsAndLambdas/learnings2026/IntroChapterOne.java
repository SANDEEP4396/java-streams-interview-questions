package streamsAndLambdas.learnings2026;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntroChapterOne {

    public static void main(String[] args) {
        // Without Stream (Imperative style)
        List<String> names = Arrays.asList("alice", "bob", "charlie",
                "david");
        List<String> result = new ArrayList<>();

        for(String name : names){
            if(name.length() > 3){
                result.add(name.toUpperCase());
            }
        }

        System.out.println("Without Stream: " + result);

        // With Stream (Declarative style)
        List<String> streamResult = names.stream()
                .filter(name -> name.length() > 3) // Filter names longer than 3 characters
                .map(String::toUpperCase) // Convert to uppercase
                .toList(); // Collect results into a List
        System.out.println("With Stream: " + streamResult);
    }
}
