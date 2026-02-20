package id.ac.polban.employee.model;

public class EmploymentType {
    private String type;

    public static final String FULL_TIME = "Full-Time";
    public static final String PART_TIME = "Part-Time";
    public static final String CONTRACT  = "Contract";

    public EmploymentType(String type) { 
        this.type = type; 
    }
    public String getType() { 
        return type; 
    }
    public void setType(String type) { 
        this.type = type; 
    }
}