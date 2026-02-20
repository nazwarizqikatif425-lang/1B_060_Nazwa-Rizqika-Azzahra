package id.ac.polban.employee.service;

import id.ac.polban.employee.model.*;
import java.util.HashMap;
import java.util.Map;

// mengelola operasi yang berkaitan dengan data dan aturan bisnis
public class EmployeeService {
    private Map<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(Employee emp) {
        employees.put(emp.getId(), emp);
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public void raiseSalary(int id, double percent) {
        Employee emp = employees.get(id);
        if (emp != null) {
            emp.setSalary(emp.getSalary() * (1 + percent / 100));
        }
    }

    public static void printEmployeeInfo(Employee emp) {
    System.out.println("===================================");
    System.out.println("  ID         : " + emp.getId());
    System.out.println("  Nama       : " + emp.getName());
    System.out.println("  Departemen : " + emp.getDepartment().getName());
    System.out.println("  Tipe       : " + emp.getType().getType());
    System.out.println("  Gaji       : Rp " + String.format("%,.0f", emp.getSalary()));
}

public void printAllEmployees() {
    System.out.println("====== DATA KARYAWAN ======");
    System.out.println("Total Employee  : " + Employee.getTotalEmployee());
    System.out.println("Total Departemen: " + Department.getTotalDepartment());
    System.out.println("===========================");
    for (Employee emp : employees.values()) {
        printEmployeeInfo(emp);
    }
}
}