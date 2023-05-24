package org.example.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee {
    private int avgStockPrice = 0;

    String CEORegex = "\\w+=(?<avgStockPrice>\\w+)";
    Pattern CEOPat = Pattern.compile(CEORegex);

    public CEO(String personText) {
       super(personText);
            Matcher CEOMat = CEOPat.matcher(peopleMat.group("details"));
            if (CEOMat.find()) {
                this.avgStockPrice = Integer.parseInt(CEOMat.group("avgStockPrice"));

            }
        }

    public int getSalary() {
        return 5000 * avgStockPrice;
    }

}
