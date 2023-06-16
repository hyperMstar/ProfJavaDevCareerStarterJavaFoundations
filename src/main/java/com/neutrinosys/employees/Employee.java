package com.neutrinosys.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements Comparable<Employee> {
    protected final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)})?";
    public static final Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
    protected final Matcher peopleMat;
    protected String lastName;
      protected String firstName;
      protected LocalDate dob;


    protected Employee() {
        peopleMat = null;
        lastName = "N/A";
        firstName = "N/A";
        dob = null;

    }

    protected Employee(String personText) {
        peopleMat = Employee.PEOPLE_PAT.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
        }
    }

    public static final Employee createEmployee(String employeeText){
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        if(peopleMat.find()) {
          return  switch (peopleMat.group("role")) {
                case "Programmer" -> new Programmer(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> new DummyEmployee();
            };
        } else {
            return new DummyEmployee();
        }
    }



    public abstract int getSalary();
    public double getBonus(){
        return getSalary() * 1.10;
    }

    public void greet(BiConsumer<String, String> biConsumer){
        String firstName = this.firstName;
        String lastName = this.lastName;
        biConsumer.accept(firstName,lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return String.format("%s, %s:, %s - %s", lastName, firstName, moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Employee)) return false;
//        Employee employee = (Employee) o;
//        return lastName.equals(employee.lastName) && firstName.equals(employee.firstName) && dob.equals(employee.dob);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee )) return false;
        Employee employee = (Employee) o;
        return lastName.equals(employee.lastName) && firstName.equals(employee.firstName) && dob.equals(employee.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }

    private static final class DummyEmployee extends Employee {
        @Override
        public int getSalary() {
            return 0;
        }
    }

    @Override
    public int compareTo(Employee o){
        Employee other = o;
        return this.lastName.compareTo(other.lastName);
    }

    public record Jumper(String firstName, String lastName){}
}



