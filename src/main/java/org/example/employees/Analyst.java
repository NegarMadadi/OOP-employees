package org.example.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst {
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private int projectCount = 0;


    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);
    String analystRegex = "\\w+=(?<projectCount>\\w+)";
    Pattern analystPat = Pattern.compile(analystRegex);

    public Analyst(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
            Matcher managerMat = analystPat.matcher(peopleMat.group("details"));
            if (managerMat.find()) {
                this.projectCount = Integer.parseInt(managerMat.group("projectCount"));

            }
        }
    }

    public int getSalary() {
    return 2500 + projectCount * 2;
    }

    @Override
    public String toString() {
        return String.format("%S, %s: %S", lastName, firstName, moneyFormat.format(getSalary()));
    }
}
