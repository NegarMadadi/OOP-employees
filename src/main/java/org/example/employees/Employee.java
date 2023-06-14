package org.example.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements Iemployee , Chef{
    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    protected final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    public static final Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;
    protected final Matcher peopleMat;

    protected Employee() {
        peopleMat = null;
        lastName = "N/A";
        firstName = "N/A";
        dob = null;
    }

    public Employee(String personText) {
        peopleMat = Employee.PEOPLE_PAT.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
        }
    }

    public static final Iemployee createEmployee(String employeeText) {
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        if (peopleMat.find()) {
            return switch (peopleMat.group("role")) {

                case "Programmer" -> new Programmer(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> () -> 0;
            };
        } else {
            return  () -> 0;
        }
    }

    public abstract int getSalary();

    @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName, moneyFormat.format(getSalary()), getBonus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(dob, employee.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }

    public double getBonus() {
        return getSalary() * 1.10;
    }
}
