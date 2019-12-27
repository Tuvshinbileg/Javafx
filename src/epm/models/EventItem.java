package epm.models;

public class EventItem {
    private String day;
    private String month;
    private String name;
    private String loc;
    public EventItem(){

    }

    public EventItem(String day, String month, String name, String loc) {
        this.day = day;
        this.month = month;
        this.name = name;
        this.loc = loc;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "EventItem{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
