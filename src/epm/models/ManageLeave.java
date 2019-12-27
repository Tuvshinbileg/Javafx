package epm.models;

public class ManageLeave {
    private String leavedate;
    private String empid;
    private String totalLeave;
    private String remainingLeave;
    private String reason;

    public ManageLeave(String leavedate, String empid, String totalLeave, String remainingLeave, String reason) {
        this.leavedate = leavedate;
        this.empid = empid;
        this.totalLeave = totalLeave;
        this.remainingLeave = remainingLeave;
        this.reason = reason;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(String totalLeave) {
        this.totalLeave = totalLeave;
    }

    public String getRemainingLeave() {
        return remainingLeave;
    }

    public void setRemainingLeave(String remainingLeave) {
        this.remainingLeave = remainingLeave;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ManageLeave{" +
                "leavedate='" + leavedate + '\'' +
                ", empid='" + empid + '\'' +
                ", totalLeave='" + totalLeave + '\'' +
                ", remainingLeave='" + remainingLeave + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
