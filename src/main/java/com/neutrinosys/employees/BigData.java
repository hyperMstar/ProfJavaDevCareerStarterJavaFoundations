package com.neutrinosys.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

public class BigData {

    record Person (String firstName, String lastName, long salary, String state){}
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            Map<String, String> count = Files.lines(Path.of("C:/Users/hyper/Downloads/Hr5m/Hr5m.csv")).parallel()
                    .skip(1)
//                    .limit(10)
                    .map(s-> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .collect(groupingBy(Person::state, TreeMap::new,
                            collectingAndThen(summingLong(Person::salary), s-> String.format("$%,d.00%n", s))));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n",count);
            System.out.println(count);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
