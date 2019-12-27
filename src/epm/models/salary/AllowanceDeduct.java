package epm.models.salary;

public class AllowanceDeduct {
    private String empId;

    private String salary;
    private String da;
    private String lta;
    private String hra;
    private String pf;

    public AllowanceDeduct(String empId, String salary, String da, String lta, String hra, String pf) {
        this.empId = empId;
        this.salary = salary;
        this.da = da;
        this.lta = lta;
        this.hra = hra;
        this.pf = pf;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }


    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getLta() {
        return lta;
    }

    public void setLta(String lta) {
        this.lta = lta;
    }

    public String getHra() {
        return hra;
    }

    public void setHra(String hra) {
        this.hra = hra;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    @Override
    public String toString() {
        return "AllowanceDeduct{" +
                "empId='" + empId + '\'' +
                ", salary='" + salary + '\'' +
                ", da='" + da + '\'' +
                ", lta='" + lta + '\'' +
                ", hra='" + hra + '\'' +
                ", pf='" + pf + '\'' +
                '}';
    }
}
