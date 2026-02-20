package id.ac.polban.employee.model;

public class Department {
    private String name;
    private static int totalDepartment = 0;

    public Department(String name) {
        this.name = name;
        totalDepartment++;
    }

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public static int getTotalDepartment() { 
        return totalDepartment; 
    }
}