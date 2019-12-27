package epm.models.employee;

public class EmployeeDetail {

    private String empcode;
    private String firstname;
    private String lastname;
    private String gender;
    private String address;
    private String contactno;
    private String dob;
    private String city;
    private String emaildid;
    private String department;
    private String designation;
    private String hourlySalary;
    private String doj;
    private String bankAccount;
    private String username;
    private String usertype;
    private String pincode;
    private String password;

    public EmployeeDetail(String empcode, String firstname, String lastname, String gender, String address, String contactno, String dob, String city, String emaildid, String department, String designation, String hourlySalary, String doj, String bankAccount, String username, String usertype, String pincode, String password) {
        this.empcode = empcode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.contactno = contactno;
        this.dob = dob;
        this.city = city;
        this.emaildid = emaildid;
        this.department = department;
        this.designation = designation;
        this.hourlySalary = hourlySalary;
        this.doj = doj;
        this.bankAccount = bankAccount;
        this.username = username;
        this.usertype = usertype;
        this.pincode = pincode;
        this.password = password;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmaildid() {
        return emaildid;
    }

    public void setEmaildid(String emaildid) {
        this.emaildid = emaildid;
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

    public String getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(String hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
