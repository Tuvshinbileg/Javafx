package epm.models.employee;

public class SimpleEmp {
    private String name;
    private String hourlySalary;
    private String department;
    private String designation;

    public SimpleEmp(String name, String hourlySalary, String department, String designation) {
        this.name = name;
        this.hourlySalary = hourlySalary;
        this.department = department;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(String hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "SimpleEmp{" +
                "name='" + name + '\'' +
                ", hourlySalary='" + hourlySalary + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
