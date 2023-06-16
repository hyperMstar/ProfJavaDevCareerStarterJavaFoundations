package com.neutrinosys.employees;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Main {


    private static Set<Employee> employees;
    private static Map<String, Integer> salaryMap;

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

        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        int totalSalaries = 0;
        Employee employee = null;
        employees = new TreeSet<>((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        salaryMap = new LinkedHashMap<>();
        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            Employee emp = employee;
            boolean add = employees.add(employee);
            salaryMap.put(emp.firstName, emp.getSalary());
        }

        for (Employee worker: employees) {
            System.out.println(worker.toString());
            totalSalaries += worker.getSalary();
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));
        System.out.println(employees.size());
        System.out.println(salaryMap.entrySet());
        for(Map.Entry<String, Integer> entry : salaryMap.entrySet()){
            System.out.printf("Key =%s, Value =%s%n", entry.getKey(), entry.getValue());
        }

    }

    private static void removeUndesirables(List<Employee> employees, List<String> removalNames){
        for(Iterator<Employee> it = employees.iterator(); it.hasNext();){
            Employee worker = it.next();
            if(worker != null){
                if(removalNames.contains(worker.firstName)){
                    it.remove();
                }
            }

        }
    }

    public int getSalary(String firstName) {
      return salaryMap.get(firstName);
    }
}
