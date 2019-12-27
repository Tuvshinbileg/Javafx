package epm.models;

public class ReportAdminAttendance {

    String empid;
    String entryDate;
    String entryTime;
    String exitTime;
    String whours;

    public ReportAdminAttendance(String empid, String entryDate, String entryTime, String exitTime, String whours) {
        this.empid = empid;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.whours = whours;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getWhours() {
        return whours;
    }

    public void setWhours(String whours) {
        this.whours = whours;
    }

    @Override
    public String toString() {
        return "ReportAdminAttendance{" +
                "empid='" + empid + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", whours='" + whours + '\'' +
                '}';
    }
}
