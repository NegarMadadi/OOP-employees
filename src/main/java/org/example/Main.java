package org.example;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinston4, Fred4, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=2000,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=2000,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=2000,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=2000,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=2000,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone, Wilma4, 3/3/1910, Analyst, {projectCount=6}
                Flinstone, Wilma5, 3/3/1910, Analyst, {projectCount=9}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                """;

        String managerRegex = "\\w+=(?<org>\\w),\\w+=(?<dr>\\w)";
        String analystRegex = "\\w+=(?<projectCount>\\w))";
        String CEORegex = "\\w+=(?<avgStockPrice>\\w)";

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w)";

        Pattern coderPat = Pattern.compile(progRegex);

        int totalSalaries = 0;
        while (peopleMat.find()) {
            totalSalaries += switch (peopleMat.group("role")) {
                case "Programmer" -> {
                    String details = peopleMat.group("details");
                    Matcher coderMat = coderPat.matcher(details);
                    int salary = 0;
                    if (coderMat.find()) {
                        int locpd = Integer.parseInt(coderMat.group("locpd"));
                        int yoe = Integer.parseInt(coderMat.group("yoe"));
                        int iq = Integer.parseInt(coderMat.group("iq"));
                        System.out.printf("Programmer loc: %S yoe:%S iq: %s%n", locpd, yoe, iq);
                        salary = 3000 + locpd * yoe * iq;
                    } else {
                        salary = 3000;
                    }
                    yield salary;
                }
                case "Manager" -> {
                    yield 3500;
                }
                case "Analyst" -> {
                    yield 2500;
                }
                case "CEO" -> {
                    yield 5000;
                }
                default -> 0;
            };
        }
        NumberFormat currencyInstant = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %S%n", currencyInstant.format(totalSalaries));
    }
}
