package org.example.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee {
    private int linesOfCOde = 0;
    private int yearsOfExperience = 0;
    private int iq = 0;

    private final String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
    private final Pattern progPat = Pattern.compile(progRegex);

    public Programmer(String personText) {
        super(personText);
        Matcher progMat = progPat.matcher(super.peopleMat.group("details"));
        if (progMat.find()) {
            this.linesOfCOde = Integer.parseInt(progMat.group("locpd"));
            this.yearsOfExperience = Integer.parseInt(progMat.group("yoe"));
            this.iq = Integer.parseInt(progMat.group("iq"));
        }
    }

    public int getSalary() {
        return 3000 + linesOfCOde * yearsOfExperience * iq;
    }

    public int getLinesOfCOde() {
        return linesOfCOde;
    }

    public void setLinesOfCOde(int linesOfCOde) {
        this.linesOfCOde = linesOfCOde;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }
}

