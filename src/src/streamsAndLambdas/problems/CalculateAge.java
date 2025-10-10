package streamsAndLambdas.problems;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class CalculateAge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your BirthDay in (YYYY-MM-DD) format: ");
        String birthDate = sc.nextLine();
        LocalDate date = LocalDate.parse(birthDate);
        LocalDate currentDate = LocalDate.now();

        Period age = Period.between(date, currentDate);

        System.out.println("You are currently: "+age.getYears()+" years, "+ age.getMonths()+" months, "+age.getDays()+" days old");
    }
}
