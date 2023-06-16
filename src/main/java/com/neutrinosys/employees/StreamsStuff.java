package com.neutrinosys.employees;

import static java.util.Comparator.comparing;

public class StreamsStuff {
    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmerzzzzzz, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
            Flinstone, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
            Flinstone, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
            Flinstone, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
            Rubble, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
            Rubble, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
            Rubble, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
            Rubble, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone, Wilma2, 3/3/1910, Analyst, {projectCount=4}
            Flinstone, Wilma3, 3/3/1910, Analyst, {projectCount=5}
            Flinstone, Wilma4, 3/3/1910, Analyst, {projectCount=6}
            Flinstone, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            """;

       int sum = peopleText
                .lines()
                .map(Employee::createEmployee)
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName)
                        .thenComparingInt(Employee::getSalary).reversed())

                .mapToInt(StreamsStuff::showEmpAndGetSalary)
                .sum();

        System.out.println(sum);

    }

    public static int showEmpAndGetSalary(Employee e){
        System.out.println(e);
        return e.getSalary();
    }
}
