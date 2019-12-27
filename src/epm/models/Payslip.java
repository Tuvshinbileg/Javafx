package epm.models;

public class Payslip {
    String empid;
    String name;
    String department;
    String designation;
    String lossOfpay;
    String netsal;
    String paydate;
    String sno;

    public Payslip(){

    }
    public Payslip(String sno,String empid, String name, String department, String designation, String lossOfpay, String netsal, String paydate) {
        this.sno=sno;
        this.empid = empid;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.lossOfpay = lossOfpay;
        this.netsal = netsal;
        this.paydate = paydate;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLossOfpay() {
        return lossOfpay;
    }

    public void setLossOfpay(String lossOfpay) {
        this.lossOfpay = lossOfpay;
    }

    public String getNetsal() {
        return netsal;
    }

    public void setNetsal(String netsal) {
        this.netsal = netsal;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    @Override
    public String toString() {
        return "Payslip{" +
                "empid='" + empid + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", lossOfpay='" + lossOfpay + '\'' +
                ", netsal='" + netsal + '\'' +
                ", paydate='" + paydate + '\'' +
                '}';
    }
}
