package org.example.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer {
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private int linesOfCOde = 0;
    private int yearsOfExperience = 0;
    private int iq = 0;
    private int salary;

    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);

    private final String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
    private final Pattern progPat = Pattern.compile(progRegex);
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Programmer(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
            Matcher progMat = progPat.matcher("details");
            if (progMat.find()) {
                this.linesOfCOde = Integer.parseInt(progMat.group("locpd"));
                this.yearsOfExperience = Integer.parseInt(progMat.group("yoe"));
                this.iq = Integer.parseInt(progMat.group("iq"));
            }
        }
    }

    public int getSalary() {
     return    salary = 3000 + linesOfCOde * yearsOfExperience * iq;
    }

    @Override
    public String toString() {
        return String.format("%S, %s: %S",lastName, firstName,salary,moneyFormat.format(getSalary()));
    }
}
