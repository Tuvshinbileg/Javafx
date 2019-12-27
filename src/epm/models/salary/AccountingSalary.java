package epm.models.salary;

public class AccountingSalary {
    private String fromdate;
    private String todate;
    private String workedHour;
    private String bonusSalary;
    private String lossOfPay;

    public AccountingSalary(String fromdate, String todate, String workedHour, String bonusSalary, String lossOfPay) {
        this.fromdate = fromdate;
        this.todate = todate;
        this.workedHour = workedHour;
        this.bonusSalary = bonusSalary;
        this.lossOfPay = lossOfPay;
    }

    @Override
    public String toString() {
        return "EventManage{" +
                "fromdate='" + fromdate + '\'' +
                ", todate='" + todate + '\'' +
                ", workedHour='" + workedHour + '\'' +
                ", bonusSalary='" + bonusSalary + '\'' +
                ", lossOfPay='" + lossOfPay + '\'' +
                '}';
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getWorkedHour() {
        return workedHour;
    }

    public void setWorkedHour(String workedHour) {
        this.workedHour = workedHour;
    }

    public String getBonusSalary() {
        return bonusSalary;
    }

    public void setBonusSalary(String bonusSalary) {
        this.bonusSalary = bonusSalary;
    }

    public String getLossOfPay() {
        return lossOfPay;
    }

    public void setLossOfPay(String lossOfPay) {
        this.lossOfPay = lossOfPay;
    }
}
