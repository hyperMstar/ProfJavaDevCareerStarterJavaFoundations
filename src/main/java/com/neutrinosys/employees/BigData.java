package com.neutrinosys.employees;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class BigData {

    record Person (String firstName, String lastName, BigDecimal salary, String state, char gender){}
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> count =
           // Map<String(state). Map<char(gender), String(formatted-salary)>>
            Map<Boolean, Map<String, Long>> count = Files.lines(Path.of("C:/Users/hyper/Downloads/Hr5m/Hr5m.csv")).parallel()
                    .skip(1)
//                    .limit(200)
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
                    .collect(partitioningBy(p -> p.gender()=='F',
                            groupingBy(Person::state, counting())));
//                    .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n",count);
            System.out.println(count);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
