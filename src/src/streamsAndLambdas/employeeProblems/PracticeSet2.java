package streamsAndLambdas.employeeProblems;

import multithreadingAndConcurrency.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class PracticeSet2 {
    public static void main(String[] args) {
        final List<Employee> employeeList = Arrays.asList(
                new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0),
                new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0),
                new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0),
                new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0),
                new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0),
                new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0),
                new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0),
                new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0),
                new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0),
                new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5),
                new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0),
                new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0),
                new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0),
                new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5),
                new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0),
                new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0),
                new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        // Problem 1: How many male and female employees are there in the organization?
        System.out.println("Number of Male employees: " + employeeList.stream()
                .filter(employee -> Objects.equals(employee.getGender(), "Male"))
                .count());
        System.out.println("Number of Male employees: " + employeeList.stream()
                .filter(employee -> Objects.equals(employee.getGender(), "Female"))
                .count());
        System.out.println("Number of Male and Female employees: " + employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        System.out.println("------------------------------");

        //Problem 2: Print the name of all departments in the organization?
        employeeList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .forEach(System.out::println);
        System.out.println("------------------------------");

        //Problem 3 : What is the average age of male and female employees?
        System.out.println("Average age of female and male employees: " + employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));
        System.out.println("------------------------------");

        // Problem 4: Get the details of highest paid employee in the organization?
        System.out.println("Employee with highest salary: " + employeeList.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println("------------------------------");

        // Problem 5: Get the names of all employees who have joined after 2015?
        System.out.println("Employees joined after 2015:" +
                employeeList.stream()
                        .filter(employee -> employee.getYearOfJoining() > 2015)
                        .map(Employee::getName)
                        .toList());
        System.out.println("------------------------------");

        // Problem 6: Count the number of employees in each department?
        System.out.println("The number of employees in each department: " +
                employeeList.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())));
        System.out.println("------------------------------");

        // Problem 7: What is the average salary of each department?
        System.out.println("Average salary of each dept: " +
                employeeList.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))));
        System.out.println("------------------------------");

        // Problem 8 : Get the details of youngest male employee in the product development department?
        System.out.println("Youngest male employee in Product Dev Team: " +
                employeeList.stream()
                        .filter(employee -> Objects.equals(employee.getGender(), "Male") && Objects.equals(employee.getDepartment(), "Product Development"))
                        .min(Comparator.comparingInt(Employee::getAge)));
        System.out.println("------------------------------");

        // Problem 9 : Who has the most working experience in the organization?
        System.out.println("Employee with most years with org: " +
                employeeList.stream()
                        .min(Comparator.comparingInt(Employee::getYearOfJoining)));
        System.out.println("------------------------------");

        // Problem 10:  How many male and female employees are there in the sales and marketing team?
        System.out.println("Number male and female employees are there in the sales and marketing team: " +
                employeeList.stream()
                        .filter(employee -> Objects.equals(employee.getDepartment(), "Sales And Marketing"))
                        .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        System.out.println("------------------------------");

        // Problem 11 : What is the average salary of male and female employees?
        System.out.println("Average salary of male and female employees: " +
                employeeList.stream()
                        .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
        System.out.println("------------------------------");

        // Problem 12: List down the names of all employees in each department?
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())))
                .forEach((dept, employees) -> System.out.println(dept + " -> " + employees));
        System.out.println("------------------------------");

        // Problem 13 : What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics totalSalary = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(totalSalary);
        System.out.println(totalSalary);
        System.out.println("Total salary: " + totalSalary.getSum());
        System.out.println("Average Salary: " + totalSalary.getAverage());
        System.out.println("Max Salary: " + totalSalary.getMax());
        System.out.println("Min Salary: " + totalSalary.getMin());
        System.out.println("------------------------------");

        // Problem 14: Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> emp = employeeList.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getAge() > 25));
        emp.forEach((aBoolean, employeeList1) -> {
            List<String> names = employeeList1.stream().map(Employee::getName).toList();
            if (aBoolean) {
                System.out.println("Older than 25:" + names);
            } else {
                System.out.println("Younger than 25:" + names);
            }
        });
        System.out.println("------------------------------");

        // Problem 15: Who is the oldest employee in the organization? What is his age and which department he belongs to?

        Optional<Employee> oldestEmp = employeeList.stream()
                .max(Comparator.comparingInt(Employee::getAge));
        oldestEmp
                .ifPresent(employee -> System.out.println("Name: " + employee.getName() + " Age: " + employee.getAge() + " Dept: " + employee.getDepartment()));
    }
}
