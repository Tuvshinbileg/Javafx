package epm.models;

public class EventManage {
   private String fromdate,todate,fromtime,toTime, eventName,eventDescription;

    public EventManage(String fromdate, String todate, String fromtime, String toTime, String eventName, String eventDescription) {
        this.fromdate = fromdate;
        this.todate = todate;
        this.fromtime = fromtime;
        this.toTime = toTime;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
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

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "EventManage{" +
                "fromdate='" + fromdate + '\'' +
                ", todate='" + todate + '\'' +
                ", fromtime='" + fromtime + '\'' +
                ", toTime='" + toTime + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}
