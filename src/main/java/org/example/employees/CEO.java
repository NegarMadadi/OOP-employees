package org.example.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO implements Employee{
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private int avgStockPrice = 0;


    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);
    String CEORegex = "\\w+=(?<avgStockPrice>\\w+)";
    Pattern CEOPat = Pattern.compile(CEORegex);

    public CEO(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
            Matcher CEOMat = CEOPat.matcher(peopleMat.group("details"));
            if (CEOMat.find()) {
                this.avgStockPrice = Integer.parseInt(CEOMat.group("avgStockPrice"));

            }
        }
    }

    public int getSalary() {
        return 5000 * avgStockPrice;
    }

    @Override
    public String toString() {
        return String.format("%S, %s: %S", lastName, firstName, moneyFormat.format(getSalary()));
    }
}
