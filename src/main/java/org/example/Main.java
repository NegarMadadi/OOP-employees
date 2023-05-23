package org.example;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2000,yoe=8,iq=105}
                Flinston4, Fred4, 1/1/1900, Programmer, {locpd=2000,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone, Wilma4, 3/3/1910, Analyst, {projectCount=6}
                Flinstone, Wilma5, 3/3/1910, Analyst, {projectCount=9}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                """;


        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w)";
        Pattern coderPat = Pattern.compile(progRegex);

        String managerRegex = "\\w+=(?<org>\\w+),\\w+=(?<dr>\\w)";
        Pattern managerPat = Pattern.compile(managerRegex);

        String analystRegex = "\\w+=(?<projectCount>\\w+)";
        Pattern analystPat = Pattern.compile(analystRegex);

        String CEORegex = "\\w+=(?<avgStockPrice>\\w+)";
        Pattern CEOPat = Pattern.compile(CEORegex);

        int totalSalaries = 0;
        int salary = 0;


        while (peopleMat.find()) {
            totalSalaries += switch (peopleMat.group("role")) {
                case "Programmer" -> {
                    String details = peopleMat.group("details");
                    Matcher coderMat = coderPat.matcher(details);
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
                    String details = peopleMat.group("details");
                    Matcher managerMat = managerPat.matcher(details);
                    if (managerMat.find()) {
                        int org = Integer.parseInt(managerMat.group("org"));
                        int dr = Integer.parseInt(managerMat.group("dr"));
                        System.out.printf("Manager orgSize: %S dr: %s%n", org, dr);
                        salary = 3500 + org * dr;
                    } else {
                        salary = 3500;
                    }
                    yield salary;
                }

                case "Analyst" -> {
                    String details = peopleMat.group("details");
                    Matcher analystMat = analystPat.matcher(details);
                    if (analystMat.find()) {
                        int projectCount = Integer.parseInt(analystMat.group("projectCount"));
                        salary = 2500 + projectCount;

                    } else {
                        salary = 2500;
                    }
                    yield salary;
                }

                case "CEO" -> {
                    String details = peopleMat.group("details");
                    Matcher CEOMat = CEOPat.matcher(details);
                    if (CEOMat.find()) {
                        int avgStockPrice = Integer.parseInt(CEOMat.group("avgStockPrice"));
                        salary = 5000 + avgStockPrice;
                    } else {
                        salary = 5000;
                    }
                    yield salary;
                }

                default -> 0;
            };
        }
        NumberFormat currencyInstant = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %S%n", currencyInstant.format(totalSalaries));
    }
}
