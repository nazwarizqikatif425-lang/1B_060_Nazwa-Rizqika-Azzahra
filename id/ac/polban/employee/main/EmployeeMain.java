package id.ac.polban.employee.main;

import id.ac.polban.employee.model.*;
import id.ac.polban.employee.service.EmployeeService;

public class EmployeeMain {
    public static void main(String[] args) {

        Department engineering = new Department("Engineering");
        Department marketing   = new Department("Marketing");

        EmploymentType fullTime = new EmploymentType(EmploymentType.FULL_TIME);
        EmploymentType contract = new EmploymentType(EmploymentType.CONTRACT);

        Employee emp1 = new Employee(1, "Nazwa",  engineering, fullTime, 8000000);
        Employee emp2 = new Employee(2, "Alia",  marketing,   contract, 5000000);
        Employee emp3 = new Employee(3, "Fina", engineering, fullTime, 9000000);

        EmployeeService service = new EmployeeService();
        service.addEmployee(emp1);
        service.addEmployee(emp2);
        service.addEmployee(emp3);

        service.printAllEmployees();

        System.out.println("\nSetelah kenaikan gaji Nazwa 10%:");
        service.raiseSalary(1, 10);
        EmployeeService.printEmployeeInfo(service.getEmployee(1));
    }
}