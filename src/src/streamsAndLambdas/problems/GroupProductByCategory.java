package streamsAndLambdas.problems;

import multithreadingAndConcurrency.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupProductByCategory{
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(1,"Tomato","Veggie"),
                new Product(2,"Onion","Veggie"),
                new Product(3,"Apple","Fruit"),
                new Product(4,"Banana","Fruit"),
                new Product(5,"Dishwashing Pod","Soap"),
                new Product(6,"Hand soap","Soap"),
                new Product(7,"Milk","Diary")
        );

        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .forEach((String, product) -> System.out.println(String +":"+ product));
    }
}