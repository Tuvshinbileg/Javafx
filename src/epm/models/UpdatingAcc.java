package epm.models;

public class UpdatingAcc {
    String address;
    String city;
    String contactNo;
    String bankAccount;
    String email;

    public UpdatingAcc(String address, String city, String contactNo, String bankAccount, String email) {
        this.address = address;
        this.city = city;
        this.contactNo = contactNo;
        this.bankAccount = bankAccount;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
