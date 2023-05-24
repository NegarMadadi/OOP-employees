package org.example.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee {
    private int orgSize = 0;
    private int directReports = 0;
    private final String managerRegex = "\\w+=(?<org>\\w+),\\w+=(?<dr>\\w+)";
    private final Pattern managerPat = Pattern.compile(managerRegex);

    public Manager(String personText) {
        super(personText);
            Matcher managerMat = managerPat.matcher(peopleMat.group("details"));
            if (managerMat.find()) {
                this.orgSize = Integer.parseInt(managerMat.group("org"));
                this.directReports = Integer.parseInt(managerMat.group("dr"));
            }
        }
        public int getSalary() {
        return 3500 + orgSize * directReports;
    }

}
