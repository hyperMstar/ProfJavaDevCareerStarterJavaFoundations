package com.neutrinosys.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.stream.Collectors.summingLong;

public class BigData {

    record Person (String firstName, String lastName, long salary, String state){}
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            long count = Files.lines(Path.of("C:/Users/hyper/Downloads/Hr5m/Hr5m.csv")).parallel()
                    .skip(1)
//                    .limit(10)
                    .map(s-> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .collect(summingLong(Person::salary));
            long endTime = System.currentTimeMillis();
            System.out.printf("$%,d.00%n",count);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
