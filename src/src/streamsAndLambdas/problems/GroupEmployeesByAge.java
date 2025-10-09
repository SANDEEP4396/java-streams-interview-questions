package streamsAndLambdas.problems;

import entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Problem 1
public class GroupEmployeesByAge {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1,"Sandeep",30),
                new Employee(2,"Sagar",22),
                new Employee(3,"Dave",22),
                new Employee(4,"Test_Dave",22),
                new Employee(5,"Test",10),
                new Employee(6,"Test10",10),
                new Employee(7,"Test11",10),
                new Employee(8,"User",11));

        //Grouping by Returns Map
       employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge))
                .forEach((integer, employees1) -> System.out.println(integer +":"+ employees1));

    }
}