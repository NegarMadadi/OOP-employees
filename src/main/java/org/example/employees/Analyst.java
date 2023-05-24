package org.example.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee{
    private int projectCount = 0;

    String analystRegex = "\\w+=(?<projectCount>\\w+)";
    Pattern analystPat = Pattern.compile(analystRegex);

    public Analyst(String personText) {
   super(personText);
            Matcher managerMat = analystPat.matcher(peopleMat.group("details"));
            if (managerMat.find()) {
                this.projectCount = Integer.parseInt(managerMat.group("projectCount"));
            }
        }

    public int getSalary() {
    return 2500 + projectCount * 2;
    }

}
