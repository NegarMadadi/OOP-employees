package org.example.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmerzzzz, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinston4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
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

        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        int totalSalaries = 0;
        Iemployee employee = null;
        List<Iemployee> employees = new ArrayList<>(16);

        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            employees.add(employee);

            if (employee instanceof Programmer) {
                System.out.println(((Programmer) employee).getIq());
            } else if (employee instanceof Manager) {
                System.out.println();
            } else if (employee instanceof Analyst) {
                System.out.println();
            } else if (employee instanceof CEO) {
                System.out.println();
                System.out.println("Default output");
            }
        }

        Iemployee myEmp = employees.get(5);
        System.out.println(employees.contains(myEmp));

        Iemployee employee1 = Employee.createEmployee("Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}");
        System.out.println(employees.contains(employee1));

        employees.sort((o1, o2) -> {
            if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
                int lNameResult = emp1.lastName.compareTo(emp2.lastName);
                return lNameResult != 0 ? lNameResult : Integer.compare(emp1.getSalary(),emp2.getSalary());
            }
            return 0;
        });

        System.out.println(myEmp.equals(employee1));

        List<String> undesirables = new ArrayList<>(List.of("Wilma5", "Barney4", "Fred2"));
        removeUndesirables(employees, undesirables);

        for (Iemployee worker : employees) {
            System.out.println(worker.toString());
            totalSalaries += worker.getSalary();
        }


        NumberFormat currencyInstant = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstant.format(totalSalaries));

        Weirdo larry = new Weirdo("David", "Larry", LocalDate.of(1988, 7, 4));
        System.out.println(larry.firstName());
        System.out.println(larry.lastName());

        Weirdo jake = new Weirdo("Snake", "Jake");
        System.out.println(jake.dob());
    }

    private static void removeUndesirables(List<Iemployee> employees, List<String> removalNames) {
        for (Iterator<Iemployee> it = employees.iterator(); it.hasNext(); ) {
            Iemployee worker = it.next();
            if (worker instanceof Employee tempWorker) {
                if (removalNames.contains(tempWorker.firstName)) {
                    it.remove();
                }
            }
        }
    }
}
